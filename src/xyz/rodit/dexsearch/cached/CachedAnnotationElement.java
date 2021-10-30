package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.EncodedValue;

public class CachedAnnotationElement implements AnnotationElement {

    private final AnnotationElement base;

    private String name;
    private EncodedValue value;

    public CachedAnnotationElement(AnnotationElement base) {
        this.base = base;
    }

    @Override
    public String getName() {
        return name == null ? name = base.getName() : name;
    }

    @Override
    public EncodedValue getValue() {
        return value == null ? value = base.getValue() : value;
    }

    @Override
    public int compareTo(AnnotationElement annotationElement) {
        return base.compareTo(annotationElement);
    }
}
