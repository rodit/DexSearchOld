package xyz.rodit.dexsearch.schema2;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.*;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;
import xyz.rodit.dexsearch.util.ListMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationMatcher implements Matcher<Annotation> {

    private final TypeMatcher type;
    private final List<ArgumentMatcher> arguments = new ArrayList<>();

    public AnnotationMatcher(TypeMatcher type, Collection<ArgumentMatcher> matchers) {
        this.type = type;
        arguments.addAll(matchers);
    }

    @Override
    public boolean matches(Resolver resolver, Annotation annotation) {
        return type.matches(resolver, annotation.getType())
                && ListMatcher.match(annotation.getElements(), arguments, (an, arg) -> arg.matches(resolver, an));
    }

    public static class ArgumentMatcher implements Matcher<AnnotationElement> {

        private final Object value;

        public ArgumentMatcher(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public boolean matches(Resolver resolver, AnnotationElement elem) {
            EncodedValue val = elem.getValue();
            if (val instanceof StringEncodedValue) {
                return value.equals(((StringEncodedValue) val).getValue());
            } else if (val instanceof IntEncodedValue) {
                return value.equals(((IntEncodedValue) val).getValue());
            } else if (val instanceof LongEncodedValue) {
                return value.equals(((LongEncodedValue) val).getValue());
            } else if (val instanceof FloatEncodedValue) {
                return value.equals(((FloatEncodedValue) val).getValue());
            } else if (val instanceof DoubleEncodedValue) {
                return value.equals(((DoubleEncodedValue) val).getValue());
            } else if (val instanceof BooleanEncodedValue) {
                return value.equals(((BooleanEncodedValue) val).getValue());
            } else if (val instanceof CharEncodedValue) {
                return value.equals(((CharEncodedValue) val).getValue());
            }
            return false;
        }
    }
}
