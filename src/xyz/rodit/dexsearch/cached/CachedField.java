package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.HiddenApiRestriction;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.value.EncodedValue;

import java.util.Set;
import java.util.stream.Collectors;

public class CachedField implements Field {

    private final Field base;

    private String definingClass;
    private String name;
    private String type;
    private int accessFlags = Integer.MIN_VALUE;
    private EncodedValue initialValue;
    private Set<Annotation> annotations;
    private Set<HiddenApiRestriction> hiddenApiRestrictions;

    public CachedField(Field base) {
        this.base = base;
    }

    @Override
    public String getDefiningClass() {
        return definingClass == null ? definingClass = base.getDefiningClass() : definingClass;
    }

    @Override
    public String getName() {
        return name == null ? name = base.getName() : name;
    }

    @Override
    public String getType() {
        return type == null ? type = base.getType() : type;
    }

    @Override
    public int compareTo(FieldReference fieldReference) {
        return base.compareTo(fieldReference);
    }

    @Override
    public int getAccessFlags() {
        return accessFlags == Integer.MIN_VALUE ? accessFlags = base.getAccessFlags() : accessFlags;
    }

    @Override
    public EncodedValue getInitialValue() {
        return initialValue == null ? initialValue = base.getInitialValue() : initialValue;
    }

    @Override
    public Set<? extends Annotation> getAnnotations() {
        return annotations == null ? annotations = base.getAnnotations().stream().map(CachedAnnotation::new).collect(Collectors.toSet()) : annotations;
    }

    @Override
    public Set<HiddenApiRestriction> getHiddenApiRestrictions() {
        return hiddenApiRestrictions == null ? hiddenApiRestrictions = base.getHiddenApiRestrictions() : hiddenApiRestrictions;
    }

    @Override
    public void validateReference() throws InvalidReferenceException {
        base.validateReference();
    }
}
