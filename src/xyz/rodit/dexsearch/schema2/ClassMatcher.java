package xyz.rodit.dexsearch.schema2;

import com.google.common.collect.Lists;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;
import xyz.rodit.dexsearch.util.ListMatcher;

import java.util.*;
import java.util.stream.Collectors;

public class ClassMatcher implements Matcher<ClassMatch> {

    public enum ClassType {
        CLASS,
        INTERFACE,
        ENUM
    }

    private final String name;
    private final boolean fuzzy;
    private final boolean veryFuzzy;
    private final boolean exact;
    private final boolean certain;
    private final boolean late;
    private final ClassType type;
    private final TypeMatcher superclass;
    private final List<TypeMatcher> interfaces = new ArrayList<>();
    private final List<AnnotationMatcher> annotations = new ArrayList<>();
    private final List<FieldMatcher> fields = new ArrayList<>();
    private final List<MethodMatcher> methods = new ArrayList<>();

    private final List<DefineMatcher> definitions = new ArrayList<>();

    public ClassMatcher(String name, boolean fuzzy, boolean veryFuzzy, boolean exact, boolean certain, boolean late, ClassType type, TypeMatcher superclass, Collection<TypeMatcher> interfaces, Collection<AnnotationMatcher> annotations, Collection<FieldMatcher> fields, Collection<MethodMatcher> methods, Collection<DefineMatcher> definitions) {
        this.name = name;
        this.fuzzy = fuzzy;
        this.veryFuzzy = veryFuzzy;
        this.exact = exact;
        this.certain = certain;
        this.late = late;
        this.type = type;
        this.superclass = superclass;
        this.interfaces.addAll(interfaces);
        this.annotations.addAll(annotations);
        this.fields.addAll(fields);
        this.methods.addAll(methods);

        this.definitions.addAll(definitions);
    }

    public String getName() {
        return name;
    }

    public boolean isFuzzy() {
        return fuzzy || veryFuzzy;
    }

    public boolean isVeryFuzzy() {
        return veryFuzzy;
    }

    public boolean isCertain() {
        return certain;
    }

    public boolean isLate() {
        return late;
    }

    public boolean isEnum() {
        return type == ClassType.ENUM;
    }

    public boolean isInterface() {
        return type == ClassType.INTERFACE;
    }

    public List<DefineMatcher> getDefinitions() {
        return definitions;
    }

    @Override
    public boolean matches(Resolver resolver, ClassMatch binder) {
        int classAccess = binder.getBound().getAccessFlags();
        if (type == ClassType.INTERFACE && (classAccess & AccessFlags.INTERFACE.getValue()) == 0) {
            binder.getMetrics().fail("type", "Type is not an interface.", MatchMetrics.FailureType.CLASS_TYPE);
            if (!veryFuzzy) {
                return false;
            }
        } else if (type == ClassType.ENUM && (classAccess & AccessFlags.ENUM.getValue()) == 0) {
            binder.getMetrics().fail("type", "Type is not an enum.", MatchMetrics.FailureType.CLASS_TYPE);
            if (!veryFuzzy) {
                return false;
            }
        }
        binder.getMetrics().matched(MatchMetrics.TYPE);

        if (superclass != null) {
            if (!superclass.matches(resolver, binder.getBound().getSuperclass())) {
                binder.getMetrics().fail("superclass", "Class does not have superclass '" + superclass + "'.", MatchMetrics.FailureType.INHERITANCE);
                if (!veryFuzzy) {
                    return false;
                }
            }
        }

        if (!this.interfaces.isEmpty()) {
            List<String> interfaces = binder.getBound().getInterfaces();

            for (TypeMatcher imatcher : this.interfaces) {
                if (interfaces.stream().noneMatch(i -> imatcher.matches(resolver, i))) {
                    binder.getMetrics().fail("interfaces", "Class does not implement interface '" + imatcher + "'.", MatchMetrics.FailureType.INHERITANCE);
                    if (!veryFuzzy) {
                        return false;
                    }
                }
            }
        }
        binder.getMetrics().matched(MatchMetrics.INHERITANCE);

        if (annotations.size() > 0 && !ListMatcher.match(binder.getBound().getAnnotations(), annotations, (an, arg) -> arg.matches(resolver, an))) {
            binder.getMetrics().fail("annotations", "Class does not have all required annotations.", MatchMetrics.FailureType.ANNOTATIONS);
            if (!veryFuzzy) {
                return false;
            }
        }
        binder.getMetrics().matched(MatchMetrics.ANNOTATIONS);

        List<Field> fields = Lists.newArrayList(binder.getBound().getFields());
        if (exact) {
            long fieldRuleCount = this.fields.stream().filter(f -> !f.shouldInvert()).count();
            if (fields.size() != fieldRuleCount) {
                binder.getMetrics().fail("fields", "Expected exactly " + fieldRuleCount + " fields but class has " + fields.size() + ".", MatchMetrics.FailureType.FIELD);
                if (!veryFuzzy) {
                    return false;
                }
            }
        }

        List<Method> methods = Lists.newArrayList(binder.getBound().getMethods());
        if (exact) {
            long methodRuleCount = this.methods.stream().filter(m -> !m.shouldInvert()).count();
            if (methods.size() != methodRuleCount) {
                binder.getMetrics().fail("fields", "Expected exactly " + methodRuleCount + " methods but class has " + fields.size() + ".", MatchMetrics.FailureType.METHOD);
                if (!veryFuzzy) {
                    return false;
                }
            }
        }

        Map<String, ClassDef> lateBoundFieldTypes = new HashMap<>();

        List<FieldMatcher> fieldsToMatch = new ArrayList<>();
        for (FieldMatcher field : this.fields) {
            if (field.isOptional()) {
                for (int i = 0; i < field.getOptionalCount(); i++) {
                    fieldsToMatch.add(field);
                }
            } else {
                fieldsToMatch.add(field);
            }
        }

        while (!fieldsToMatch.isEmpty()) {
            FieldMatcher toMatch = fieldsToMatch.get(0);

            Optional<Field> match = fields.stream().filter(f -> toMatch.matches(resolver, f)).findFirst();
            if (match.isPresent()) {
                if (toMatch.shouldInvert()) {
                    binder.getMetrics().fail("fields", "Class contains definition for strictly non-present field '" + toMatch.getName() + "'.", MatchMetrics.FailureType.FIELD);
                    if (!veryFuzzy) {
                        return false;
                    }
                }

                binder.bind(toMatch, match.get());
                fields.remove(match.get());

                if (toMatch.getType().isLate()) {
                    lateBoundFieldTypes.put(toMatch.getType().getName(), resolver.getDex().getClass(match.get().getType()));
                }
            } else if (!toMatch.shouldInvert() && !toMatch.isOptional()) {
                binder.getMetrics().fail("fields", "Class does not contain definition for required field '" + toMatch.getName() + "'.", MatchMetrics.FailureType.FIELD);
                if (!veryFuzzy) {
                    return false;
                }
            }

            fieldsToMatch.remove(0);
        }

        List<MethodMatcher> methodsToMatch = Lists.newArrayList(this.methods);
        while (!methodsToMatch.isEmpty()) {
            MethodMatcher toMatch = methodsToMatch.get(0);

            Optional<Method> match = methods.stream().filter(m -> toMatch.matches(resolver, m)).findFirst();
            if (match.isPresent()) {
                if (toMatch.shouldInvert()) {
                    binder.getMetrics().fail("methods", "Class contains definition for strictly non-present method '" + toMatch.getName() + "'.", MatchMetrics.FailureType.METHOD);
                    if (!veryFuzzy) {
                        return false;
                    }
                }

                binder.bind(toMatch, match.get());
                methods.remove(match.get());
            } else if (!toMatch.shouldInvert()) {
                binder.getMetrics().fail("fields", "Class does not contain definition for required method '" + toMatch.getName() + "'.", MatchMetrics.FailureType.METHOD);
                if (!veryFuzzy) {
                    return false;
                }
            }

            methodsToMatch.remove(0);
        }

        List<String> lateBound = new ArrayList<>();
        for (String key : lateBoundFieldTypes.keySet()) {
            if (!resolver.bindLate(key, lateBoundFieldTypes.get(key))) {
                lateBound.forEach(resolver::unbind);
                binder.getMetrics().fail("late", "Failed to late bind field '" + key + "'.", MatchMetrics.FailureType.FIELD);
                if (!veryFuzzy) {
                    return false;
                }
            }
            lateBound.add(key);
        }

        return binder.getMetrics().getReasons().size() == 0;
    }
}
