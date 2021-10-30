package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.MethodParameter;

import java.util.Set;
import java.util.stream.Collectors;

public class CachedMethodParameter implements MethodParameter {

    private final MethodParameter base;

    private String type;
    private Set<Annotation> annotations;
    private String name;
    private String signature;

    public CachedMethodParameter(MethodParameter base) {
        this.base = base;
    }

    @Override
    public String getType() {
        return type == null ? type = base.getType() : type;
    }

    @Override
    public int compareTo(CharSequence charSequence) {
        return base.compareTo(charSequence);
    }

    @Override
    public Set<? extends Annotation> getAnnotations() {
        return annotations == null ? annotations = base.getAnnotations().stream().map(CachedAnnotation::new).collect(Collectors.toSet()) : annotations;
    }

    @Override
    public String getName() {
        return name == null ? name = base.getSignature() : signature;
    }

    @Override
    public String getSignature() {
        return signature == null ? signature = base.getSignature() : signature;
    }

    @Override
    public int length() {
        return base.length();
    }

    @Override
    public char charAt(int index) {
        return  base.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return base.subSequence(start, end);
    }

    @Override
    public void validateReference() throws InvalidReferenceException {
        base.validateReference();
    }
}
