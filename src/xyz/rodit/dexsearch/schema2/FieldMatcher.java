package xyz.rodit.dexsearch.schema2;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Field;

import java.util.Collection;

public class FieldMatcher extends MemberMatcher<Field> {

    private final boolean optional;
    private final int optionalCount;

    public FieldMatcher(NameMatcher name, TypeMatcher type, Collection<AnnotationMatcher> annotations, boolean isStatic, boolean not, boolean optional, int optionalCount) {
        super(name, type, annotations, isStatic, not);
        this.optional = optional;
        this.optionalCount = optionalCount;
    }

    @Override
    public String getMemberName(Field field) {
        return field.getName();
    }

    @Override
    public String getType(Field field) {
        return field.getType();
    }

    @Override
    public Collection<? extends Annotation> getAnnotations(Field field) {
        return field.getAnnotations();
    }

    public boolean isOptional() {
        return optional;
    }

    public int getOptionalCount() {
        return optionalCount;
    }
}
