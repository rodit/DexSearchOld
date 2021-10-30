package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.AnnotationElement;

import java.util.Set;
import java.util.stream.Collectors;

public class CachedAnnotation implements Annotation {

    private final Annotation base;

    private int visibility = Integer.MIN_VALUE;
    private String type;
    private Set<AnnotationElement> elements;

    public CachedAnnotation(Annotation base) {
        this.base = base;
    }

    @Override
    public int getVisibility() {
        return visibility == Integer.MIN_VALUE ? visibility = base.getVisibility() : visibility;
    }

    @Override
    public String getType() {
        return type == null ? type = base.getType() : type;
    }

    @Override
    public Set<? extends AnnotationElement> getElements() {
        return elements == null ? elements = base.getElements().stream().map(CachedAnnotationElement::new).collect(Collectors.toSet()) : elements;
    }

    @Override
    public int hashCode() {
        return base.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return base.equals(o);
    }

    @Override
    public int compareTo(Annotation annotation) {
        return base.compareTo(annotation);
    }
}
