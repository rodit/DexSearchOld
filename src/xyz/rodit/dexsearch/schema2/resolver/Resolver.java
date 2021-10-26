package xyz.rodit.dexsearch.schema2.resolver;

import antlr.SchemaLexer;
import antlr.SchemaParser;
import com.google.gson.Gson;
import com.squareup.javapoet.*;
import com.util.mappings.MappedObject;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodParameter;
import xyz.rodit.dexsearch.DexBase;
import xyz.rodit.dexsearch.Logger;
import com.util.mappings.Mappings;
import com.util.mappings.DexTypeConverter;
import xyz.rodit.dexsearch.schema2.*;
import xyz.rodit.dexsearch.schema2.mapper.Converter;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public class Resolver {

    private final Map<String, ClassMatcher> classMatchers = new LinkedHashMap<>();
    private final DexBase dex;

    private final Map<String, ClassMatch> resolved = new HashMap<>();
    private final Map<String, String> dexToNice = new HashMap<>();
    private final Map<String, ClassDef> veryLateBindings = new HashMap<>();

    public Resolver(DexBase dex, Collection<ClassMatcher> classMatchers) {
        this.dex = dex;
        for (ClassMatcher matcher : classMatchers) {
            this.classMatchers.put(matcher.getName(), matcher);
        }
    }

    public DexBase getDex() {
        return dex;
    }

    public ClassMatcher getMatcher(String name) {
        return classMatchers.get(name);
    }

    public ClassMatch getResolved(String name) {
        return resolved.get(name);
    }

    public void unbind(String className) {
        resolved.remove(className);
    }

    public void putVeryLateBinding(String className, ClassDef bound) {
        veryLateBindings.put(className, bound);
    }

    private ClassMatcher generateLateClassMatcher(String className) {
        if (!classMatchers.containsKey(className)) {
            classMatchers.put(className, new ClassMatcher(className, false, false, false, false, true, ClassMatcher.ClassType.CLASS, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));
        }
        return classMatchers.get(className);
    }

    public boolean bindLate(String className, ClassDef bound) {
        if (!resolved.containsKey(className)) {
            if (bound == null) {
                Logger.info("Cannot late bind %s. No parent class bound.", className);
                return false;
            }
            ClassMatcher matcher = generateLateClassMatcher(className);
            Logger.info("Late binding %s to %s.", className, DexTypeConverter.toJavaType(bound.getType()));
            return resolve(matcher, Collections.singletonList(bound));
        } else {
            // TODO: Fix this for very fuzzy binding retries.
            // Logger.info("%s has already been late bound.", className);
            return true;
        }
    }

    public void resolveAll() {
        for (ClassMatcher matcher : classMatchers.values().stream().filter(ClassMatcher::isCertain).collect(Collectors.toList())) {
            resolve(matcher, dex.getClasses());
        }
        for (ClassMatcher matcher : classMatchers.values().stream().filter(m -> !m.isCertain() && !m.isLate()).collect(Collectors.toList())) {
            resolve(matcher, dex.getClasses());
        }

        HashMap<String, ClassDef> copy = new HashMap<>(veryLateBindings);
        for (String className : copy.keySet()) {
            if (!resolved.containsKey(className)) {
                ClassDef bound = copy.get(className);
                Logger.info("Trying to very late bind %s to %s.", className, DexTypeConverter.toJavaType(bound.getType()));
                if (resolve(generateLateClassMatcher(className), Collections.singletonList(bound))) {
                    Logger.info("Very late binding successful.");
                } else {
                    Logger.error("Very late binding failed.");
                }
            }
        }
    }

    public boolean resolve(ClassMatcher matcher, Collection<ClassDef> potential) {
        Logger.info("Attempting to resolve %s.", matcher.getName());
        MatchMetrics bestMetrics = new MatchMetrics(null);
        for (ClassDef cls : potential) {
            ClassMatch match = new ClassMatch(matcher, cls);
            if (matcher.matches(this, match)) {
                resolved.put(matcher.getName(), match);
                Logger.info("Resolved successfully as %s.", DexTypeConverter.toJavaType(match.getBound().getType()));
                return true;
            }
            if (bestMetrics.getScore() < match.getMetrics().getScore()) {
                bestMetrics = match.getMetrics();
            }
        }
        if (matcher.isFuzzy()) {
            ClassMatch bestMatch = bestMetrics.getOwner();
            Logger.info("Fuzzy matching %s to best match %s.", matcher.getName(), DexTypeConverter.toJavaType(bestMatch.getBound().getType()));
            Logger.info(bestMetrics.toString());
            resolved.put(matcher.getName(), bestMatch);
            return true;
        } else {
            Logger.error("Resolution unsuccessful for %s.", matcher.getName());
            Logger.error(bestMetrics.toString());
        }
        return false;
    }

    public void generateBindings(File outputDir, String packageName, long build, boolean generateClasses) throws IOException {
        Mappings.BindingEntry entry = new Mappings.BindingEntry();
        entry.build = build;
        entry.mappings = resolved.values().stream().map(Converter::createMapping).collect(Collectors.toList());
        try (FileWriter writer = new FileWriter(new File(outputDir, build + ".json"))) {
            new Gson().toJson(entry, writer);
        }

        if (!generateClasses) {
            return;
        }

        dexToNice.clear();
        for (String key : resolved.keySet()) {
            dexToNice.put(resolved.get(key).getBound().getType(), key);
        }

        for (String key : resolved.keySet()) {
            ClassMatch match = resolved.get(key);
            ClassMatcher owner = match.getOwner();
            ClassDef bound = match.getBound();

            boolean isEnum = (bound.getAccessFlags() & AccessFlags.ENUM.getValue()) != 0;

            ClassName typeName = ClassName.get(packageName, owner.getName());
            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(typeName)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(ClassName.get(MappedObject.class));

            for (DefineMatcher macro : owner.getDefinitions()) {
                FieldSpec field = FieldSpec.builder(macro.getType(), macro.getDefName(), Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(macro.getDefValue())
                        .build();
                typeBuilder.addField(field);
            }

            for (MethodMatcher method : match.getMethodBindings().keySet()) {
                String name = method.getName().getName();
                if (!name.equals("_") && !name.equals("<clinit>")) {
                    boolean isCtr = name.equals("<init>") && method.getName().isExact();

                    Method dexMethod = match.getMethodBindings().get(method);
                    SemiType returnType = getSemiType(dexMethod.getReturnType());
                    TypeName retClassName = returnType.getTypeName();

                    MethodSpec.Builder spec;

                    if (isCtr && dexMethod.getParameters().size() > 1) {
                        spec = MethodSpec.constructorBuilder();
                    } else if (!isCtr) {
                        spec = MethodSpec.methodBuilder(name)
                                .returns(returnType.getTypeName());
                    } else {
                        continue;
                    }

                    StringBuilder callArgsSb = new StringBuilder();
                    int i = 0;
                    for (MethodParameter param : dexMethod.getParameters()) {
                        SemiType type = getSemiType(param.getType());
                        String argName = "arg" + i++;
                        spec.addParameter(ParameterSpec.builder(type.getTypeName(), argName).build());
                        callArgsSb.append(argName);
                        if (i < dexMethod.getParameters().size()) {
                            callArgsSb.append(", ");
                        }
                    }
                    String callArgs = callArgsSb.toString();
                    if (i == 0) {
                        callArgs = "new Object[0]";
                    }

                    if ((dexMethod.getAccessFlags() & AccessFlags.STATIC.getValue()) != 0) {
                        spec.addModifiers(Modifier.STATIC);
                        if (dexMethod.getReturnType().equals("V")) {
                            spec.addStatement("callStatic($S, $S, $L)", owner.getName(), name, callArgs);
                        } else {
                            if (resolved.containsKey(returnType.name)) {
                                spec.addStatement("$T.wrap(callStatic($S, $S, $L))", retClassName, owner.getName(), name, callArgs);
                            } else {
                                spec.addStatement("return ($T) callStatic($S, $S, $L)", retClassName, owner.getName(), name, callArgs);
                            }
                        }
                    } else {
                        if (isCtr) {
                            spec.addStatement("this(new $T[] { $L })", Object.class, callArgs);
                        } else if (dexMethod.getReturnType().equals("V")) {
                            spec.addStatement("call($S, $L)", name, callArgs);
                        } else {
                            if (resolved.containsKey(returnType.name)) {
                                spec.addStatement("return $T.wrap(call($S, $L))", retClassName, name, callArgs);
                            } else {
                                spec.addStatement("return ($T) call($S, $L)", retClassName, name, callArgs);
                            }
                        }
                    }

                    typeBuilder.addMethod(spec.addModifiers(Modifier.PUBLIC).build());
                }
            }

            for (FieldMatcher field : match.getFieldBindings().keySet()) {
                String name = field.getName().getName();
                if (!name.equals("_")) {
                    Field dexField = match.getFieldBindings().get(field);
                    boolean isStatic = (dexField.getAccessFlags() & AccessFlags.STATIC.getValue()) != 0;
                    SemiType type = getSemiType(dexField.getType());

                    String camelName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    MethodSpec.Builder getter = MethodSpec.methodBuilder(isEnum ? name : "get" + camelName)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(type.getTypeName());
                    MethodSpec.Builder setter = MethodSpec.methodBuilder(isEnum ? name : "set" + camelName)
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(type.getTypeName(), "value");

                    String getStat;
                    String setStat;

                    if (isStatic) {
                        getter.addModifiers(Modifier.STATIC);
                        setter.addModifiers(Modifier.STATIC);
                        getStat = "getStatic($S, $S)";
                        setStat = "setStatic($S, $S, value)";
                    } else {
                        getStat = "get($S)";
                        setStat = "set($S, value)";
                    }

                    if (type.isNice()) {
                        getStat = String.format("return %s.wrap(%s)", type.get(), getStat);
                    } else {
                        getStat = String.format("return (%s) %s", type.get(), getStat);
                    }

                    if (isStatic) {
                        getter.addStatement(getStat, owner.getName(), name);
                        setter.addStatement(setStat, owner.getName(), name);
                    } else {
                        getter.addStatement(getStat, name);
                        setter.addStatement(setStat, name);
                    }

                    typeBuilder.addMethod(getter.build())
                            .addMethod(setter.build());
                }
            }

            if (isEnum) {
                MethodSpec valueOfMethod = MethodSpec.methodBuilder("valueOf")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ClassName.get(String.class), "value")
                        .addStatement("return ($T) $T.getEnumValue($S, value)", typeName, Mappings.class, owner.getName())
                        .returns(typeName)
                        .build();

                typeBuilder.addMethod(valueOfMethod);
            }

            MethodSpec wrapMethod = MethodSpec.methodBuilder("wrap")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(TypeName.OBJECT, "instance")
                    .returns(typeName)
                    .addStatement("return new $T(instance)", typeName)
                    .build();

            MethodSpec defCtr = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("this(new $T[0])", Object.class)
                    .build();

            MethodSpec instanceCtr = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(TypeName.OBJECT, "instance")
                    .addStatement("super($T.get($S), instance)", Mappings.class, owner.getName())
                    .build();

            MethodSpec argCtr = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ArrayTypeName.of(TypeName.OBJECT), "args")
                    .addStatement("super($T.get($S), args)", Mappings.class, owner.getName())
                    .build();

            typeBuilder.addMethod(wrapMethod)
                    .addMethod(defCtr)
                    .addMethod(instanceCtr)
                    .addMethod(argCtr);

            JavaFile javaFile = JavaFile.builder(packageName, typeBuilder.build())
                    .build();
            javaFile.writeTo(outputDir);
        }
    }

    private SemiType getSemiType(String type) {
        if (dexToNice.containsKey(type)) {
            return new SemiType(dexToNice.get(type), true, false);
        } else if (DexTypeConverter.isPrimitive(type)) {
            return new SemiType(DexTypeConverter.toJavaType(type), false, true);
        } else if (DexTypeConverter.isDexType(type)) {
            type = DexTypeConverter.toJavaType(type);
            return isJavaType(type)
                    ? new SemiType(type, false, false)
                    : new SemiType("Object", false, false);
        }
        return new SemiType(type, false, true);
    }

    public static Resolver create(DexBase dex, File file) throws IOException {
        SchemaLexer lexer = new SchemaLexer(CharStreams.fromStream(new FileInputStream(file)));
        SchemaParser parser = new SchemaParser(new CommonTokenStream(lexer));

        SchemaParser.SchemaContext context = parser.schema();
        SchemaVisitor visitor = new SchemaVisitor();
        List<ClassMatcher> matchers = context.classDef().stream().map(visitor::visitClassDef).collect(Collectors.toList());
        return new Resolver(dex, matchers);
    }

    private static boolean isJavaType(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public class SemiType {

        private final String name;
        private final boolean nice;
        private final boolean primitive;

        public SemiType(String name, boolean nice, boolean primitive) {
            this.name = name;
            this.nice = nice;
            this.primitive = primitive;
        }

        public String get() {
            return name;
        }

        public boolean isNice() {
            return nice;
        }

        public boolean isPrimitive() {
            return primitive;
        }

        public TypeName getTypeName() {
            if (isPrimitive()) {
                return TypeName.get(parseType(name));
            }
            return ClassName.bestGuess(name);
        }

        private Class<?> parseType(final String className) {
            switch (className) {
                case "boolean":
                    return boolean.class;
                case "byte":
                    return byte.class;
                case "short":
                    return short.class;
                case "int":
                    return int.class;
                case "long":
                    return long.class;
                case "float":
                    return float.class;
                case "double":
                    return double.class;
                case "char":
                    return char.class;
                case "void":
                    return void.class;
            }
            throw new AssertionError();
        }
    }
}
