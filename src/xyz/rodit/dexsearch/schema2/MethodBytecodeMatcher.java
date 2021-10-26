package xyz.rodit.dexsearch.schema2;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.iface.reference.StringReference;
import org.jf.dexlib2.iface.reference.TypeReference;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;

import javax.lang.model.type.ReferenceType;

public abstract class MethodBytecodeMatcher implements Matcher<MethodImplementation> {

    protected abstract boolean matches(Resolver resolver, Instruction instruction);

    @Override
    public boolean matches(Resolver resolver, MethodImplementation method) {
        if (method == null) {
            return false;
        }

        for (Instruction i : method.getInstructions()) {
            if (matches(resolver, i)) {
                return true;
            }
        }

        return false;
    }

    public static class BytecodeStringMatcher extends MethodBytecodeMatcher {

        private final String string;
        private final boolean ignoreCase;
        private final boolean contains;

        public BytecodeStringMatcher(String string, boolean ignoreCase, boolean contains) {
            this.string = ignoreCase ? string.toLowerCase() : string;
            this.ignoreCase = ignoreCase;
            this.contains = contains;
        }

        @Override
        protected boolean matches(Resolver resolver, Instruction instruction) {
            if (instruction instanceof ReferenceInstruction) {
                ReferenceInstruction ref = (ReferenceInstruction) instruction;
                if (ref.getReference() instanceof StringReference) {
                    String string = ((StringReference) ref.getReference()).getString();
                    if (ignoreCase) {
                        string = string.toLowerCase();
                    }
                    return contains ? string.contains(this.string) : string.equals(this.string);
                }
            }
            return false;
        }
    }

    public static class BytecodeCallMatcher extends MethodBytecodeMatcher {

        private final TypeMatcher methodClass;
        private final NameMatcher methodName;

        public BytecodeCallMatcher(TypeMatcher methodClass, NameMatcher methodName) {
            this.methodClass = methodClass;
            this.methodName = methodName;
        }

        @Override
        protected boolean matches(Resolver resolver, Instruction instruction) {
            if (instruction instanceof ReferenceInstruction) {
                ReferenceInstruction ref = (ReferenceInstruction) instruction;
                if (ref.getReference() instanceof MethodReference) {
                    MethodReference method = (MethodReference) ref.getReference();
                    return methodName.matches(resolver, method.getName()) && methodClass.matches(resolver, method.getDefiningClass());
                }
            }
            return false;
        }
    }

    public static class BytecodeNewInstanceMatcher extends MethodBytecodeMatcher {

        private final TypeMatcher targetClass;

        public BytecodeNewInstanceMatcher(TypeMatcher targetClass) {
            this.targetClass = targetClass;
        }

        @Override
        protected boolean matches(Resolver resolver, Instruction instruction) {
            if (instruction.getOpcode() == Opcode.NEW_INSTANCE) {
                ReferenceInstruction ref = (ReferenceInstruction) instruction;
                return targetClass.matches(resolver, ((TypeReference) ref.getReference()).getType());
            }
            return false;
        }
    }

    public static class BytecodeTypeRefMatcher extends MethodBytecodeMatcher {

        private final TypeMatcher targetClass;

        public BytecodeTypeRefMatcher(TypeMatcher targetClass) {
            this.targetClass = targetClass;
        }

        @Override
        protected boolean matches(Resolver resolver, Instruction instruction) {
            if (instruction instanceof ReferenceInstruction) {
                ReferenceInstruction ref = (ReferenceInstruction) instruction;
                if (ref.getReference() instanceof TypeReference) {
                    return targetClass.matches(resolver, ((TypeReference) ref.getReference()).getType());
                }
            }
            return false;
        }
    }

    public static class BytecodeFieldRefMatcher extends MethodBytecodeMatcher {

        private final TypeMatcher fieldClass;
        private final NameMatcher fieldName;

        public BytecodeFieldRefMatcher(TypeMatcher fieldClass, NameMatcher fieldName) {
            this.fieldClass = fieldClass;
            this.fieldName = fieldName;
        }

        @Override
        protected boolean matches(Resolver resolver, Instruction instruction) {
            if (instruction instanceof ReferenceInstruction) {
                ReferenceInstruction ref = (ReferenceInstruction) instruction;
                if (ref.getReference() instanceof FieldReference) {
                    FieldReference field = (FieldReference) ref.getReference();
                    return fieldClass.matches(resolver, field.getType()) && fieldName.matches(resolver, field.getName());
                }
            }
            return false;
        }
    }
}
