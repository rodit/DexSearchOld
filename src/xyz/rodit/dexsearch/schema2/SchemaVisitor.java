package xyz.rodit.dexsearch.schema2;

import antlr.SchemaParser;
import antlr.SchemaParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaVisitor extends SchemaParserBaseVisitor<Matcher<?>> {

    private ClassMatcher.ClassType getClassType(SchemaParser.ClassDefContext ctx) {
        if (ctx.ENUM() != null) {
            return ClassMatcher.ClassType.ENUM;
        } else if (ctx.INTERFACE() != null) {
            return ClassMatcher.ClassType.INTERFACE;
        }
        return ClassMatcher.ClassType.CLASS;
    }

    @Override
    public ClassMatcher visitClassDef(SchemaParser.ClassDefContext ctx) {
        String name = ctx.identifier().getText();
        boolean fuzzy = ctx.FUZZY() != null;
        boolean veryFuzzy = ctx.VERY_FUZZY() != null;
        boolean exact = ctx.EXACT() != null;
        boolean certain = ctx.CERTAIN() != null;
        boolean late = ctx.LATE() != null;
        if (late && certain || late && exact) {
            throw new RuntimeException("Class matcher cannot be both late and certain/exact.");
        }
        TypeMatcher superclass = ctx.extendsStatement() != null ? visitExtendsStatement(ctx.extendsStatement()) : null;
        List<TypeMatcher> interfaces = ctx.implementsStatement() != null ? ctx.implementsStatement().typeIdentifier().stream().map(this::visitTypeIdentifier).collect(Collectors.toList()) : new ArrayList<>();
        List<AnnotationMatcher> annotations = ctx.annotation().stream().map(this::visitAnnotation).collect(Collectors.toList());
        List<FieldMatcher> fields = ctx.fieldDef().stream().map(this::visitFieldDef).collect(Collectors.toList());
        List<MethodMatcher> methods = ctx.methodDef().stream().map(this::visitMethodDef).collect(Collectors.toList());
        List<DefineMatcher> definitions = ctx.macroDef().stream().map(this::visitMacroDef).collect(Collectors.toList());
        return new ClassMatcher(name, fuzzy, veryFuzzy, exact, certain, late, getClassType(ctx), superclass, interfaces, annotations, fields, methods, definitions);
    }

    @Override
    public TypeMatcher visitExtendsStatement(SchemaParser.ExtendsStatementContext ctx) {
        return visitTypeIdentifier(ctx.typeIdentifier(), true);
    }

    @Override
    public DefineMatcher visitMacroDef(SchemaParser.MacroDefContext ctx) {
        return new DefineMatcher(ctx.IDENTIFIER().getText(), ctx.constant().getText());
    }

    @Override
    public FieldMatcher visitFieldDef(SchemaParser.FieldDefContext ctx) {
        NameMatcher name = visitIdentifier(ctx.identifier());
        TypeMatcher type = visitTypeIdentifier(ctx.typeIdentifier());
        List<AnnotationMatcher> annotations = ctx.annotation().stream().map(this::visitAnnotation).collect(Collectors.toList());
        boolean not = ctx.NOT() != null;
        boolean optional = false;
        int optionalCount = 1;
        if (ctx.optionalExpression() != null) {
            optional = true;
            TerminalNode countNode = ctx.optionalExpression().INT_LIT();
            if (countNode != null) {
                optionalCount = Integer.parseInt(countNode.getText());
            }
        }
        if (not && optional) {
            throw new RuntimeException("Field cannot be marked with NOT and OPTIONAL operator.");
        }
        return new FieldMatcher(name, type, annotations, ctx.STATIC() != null, not, optional, optionalCount);
    }

    @Override
    public MethodMatcher visitMethodDef(SchemaParser.MethodDefContext ctx) {
        NameMatcher name = visitIdentifier(ctx.identifier());
        TypeMatcher type = visitTypeIdentifier(ctx.typeIdentifier());
        List<TypeMatcher> args = ctx.methodArgList().typeIdentifier().stream().map(this::visitTypeIdentifier).collect(Collectors.toList());
        List<AnnotationMatcher> annotations = ctx.annotation().stream().map(this::visitAnnotation).collect(Collectors.toList());
        List<MethodBytecodeMatcher> body = ctx.methodBody() != null ? ctx.methodBody().bytecodeIdentifier().stream().map(this::visitBytecodeIdentifier).collect(Collectors.toList()) : new ArrayList<>();
        return new MethodMatcher(name, type, annotations, args, body, ctx.STATIC() != null, ctx.NOT() != null);
    }

    @Override
    public MethodBytecodeMatcher visitBytecodeIdentifier(SchemaParser.BytecodeIdentifierContext ctx) {
        return (MethodBytecodeMatcher) super.visitBytecodeIdentifier(ctx);
    }

    @Override
    public MethodBytecodeMatcher.BytecodeStringMatcher visitBytecodeString(SchemaParser.BytecodeStringContext ctx) {
        boolean ignoreCase = false;
        boolean contains = false;
        for (SchemaParser.BytecodeStringOptionContext opt : ctx.bytecodeStringOption()) {
            ignoreCase |= opt.BYTECODE_STRING_IGNORE_CASE() != null;
            contains |= opt.BYTECODE_STRING_CONTAINS() != null;
        }
        return new MethodBytecodeMatcher.BytecodeStringMatcher(parseLiteral(ctx.STRING_LIT().getText()), ignoreCase, contains);
    }

    @Override
    public MethodBytecodeMatcher.BytecodeCallMatcher visitBytecodeCall(SchemaParser.BytecodeCallContext ctx) {
        NameMatcher name = visitBytecodeMethodName(ctx.identifier());
        return new MethodBytecodeMatcher.BytecodeCallMatcher(visitTypeIdentifier(ctx.typeIdentifier()), name);
    }

    @Override
    public MethodBytecodeMatcher.BytecodeNewInstanceMatcher visitBytecodeNewInstance(SchemaParser.BytecodeNewInstanceContext ctx) {
        return new MethodBytecodeMatcher.BytecodeNewInstanceMatcher(visitTypeIdentifier(ctx.typeIdentifier()));
    }

    @Override
    public MethodBytecodeMatcher.BytecodeTypeRefMatcher visitBytecodeTypeReference(SchemaParser.BytecodeTypeReferenceContext ctx) {
        return new MethodBytecodeMatcher.BytecodeTypeRefMatcher(visitTypeIdentifier(ctx.typeIdentifier()));
    }

    @Override
    public MethodBytecodeMatcher.BytecodeFieldRefMatcher visitBytecodeFieldReference(SchemaParser.BytecodeFieldReferenceContext ctx) {
        NameMatcher name = visitBytecodeFieldName(ctx.identifier());
        return new MethodBytecodeMatcher.BytecodeFieldRefMatcher(visitTypeIdentifier(ctx.typeIdentifier()), name);
    }

    private MethodBytecodeMatcher.BytecodeMethodNameMatcher visitBytecodeMethodName(SchemaParser.IdentifierContext ctx) {
        boolean reference = ctx.EXACT_MODIFIER() != null;
        return new MethodBytecodeMatcher.BytecodeMethodNameMatcher(reference ? ctx.getText().substring(1) : ctx.getText(), reference);
    }

    private MethodBytecodeMatcher.BytecodeFieldNameMatcher visitBytecodeFieldName(SchemaParser.IdentifierContext ctx) {
        boolean reference = ctx.EXACT_MODIFIER() != null;
        return new MethodBytecodeMatcher.BytecodeFieldNameMatcher(reference ? ctx.getText().substring(1) : ctx.getText(), reference);
    }

    @Override
    public AnnotationMatcher visitAnnotation(SchemaParser.AnnotationContext ctx) {
        TypeMatcher type = visitTypeIdentifier(ctx.typeIdentifier());
        List<AnnotationMatcher.ArgumentMatcher> args = ctx.attribArgList().constant().stream().map(this::visitConstant).collect(Collectors.toList());
        return new AnnotationMatcher(type, args);
    }

    @Override
    public AnnotationMatcher.ArgumentMatcher visitConstant(SchemaParser.ConstantContext ctx) {
        Object value = null;
        if (ctx.INT_LIT() != null) {
            value = Long.parseLong(ctx.getText());
        } else if (ctx.STRING_LIT() != null) {
            value = parseLiteral(ctx.getText());
        } else if (ctx.BOOL_LIT() != null) {
            value = ctx.getText().equalsIgnoreCase("true");
        } else if (ctx.CHAR_LIT() != null) {
            value = ctx.getText().charAt(1);
        } else if (ctx.FLOAT_LIT() != null) {
            value = Double.valueOf(ctx.getText());
        }
        return new AnnotationMatcher.ArgumentMatcher(value);
    }

    @Override
    public NameMatcher visitIdentifier(SchemaParser.IdentifierContext ctx) {
        boolean exact = ctx.EXACT_MODIFIER() != null;
        return new NameMatcher(exact ? ctx.getText().substring(1) : ctx.getText(), exact);
    }

    @Override
    public TypeMatcher visitTypeIdentifier(SchemaParser.TypeIdentifierContext ctx) {
        return visitTypeIdentifier(ctx, false);
    }

    public TypeMatcher visitTypeIdentifier(SchemaParser.TypeIdentifierContext ctx, boolean forceSuperclass) {
        boolean varargs = ctx.VARARGS_ANY() != null;
        boolean reference = !varargs && ctx.baseTypeIdentifier().referencedTypeIdentifier() != null;
        boolean superClass = forceSuperclass || ctx.EXTENDS() != null;
        boolean late = !varargs && ctx.baseTypeIdentifier().lateTypeIdentifier() != null;
        String typeName = varargs ? ctx.getText() : ctx.baseTypeIdentifier().getText();
        typeName = typeName.substring(reference || late ? 1 : 0);
        return new TypeMatcher(typeName, !varargs && ctx.baseTypeIdentifier().MATCH_ANY() != null, reference, late, superClass);
    }

    private String parseLiteral(String str) {
        return escapeString(str.substring(1, str.length() - 1));
    }

    private String escapeString(String str) {
        return str.replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\'", "'")
                .replace("\\\"", "\"");
    }
}
