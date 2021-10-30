package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.HiddenApiRestriction;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.dexlib2.iface.reference.MethodReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CachedMethod implements Method {

    private final Method base;

    private String definingClass;
    private String name;
    private List<? extends CharSequence> parameterTypes;
    private List<MethodParameter> parameters;
    private String returnType;
    private int accessFlags = Integer.MIN_VALUE;
    private Set<Annotation> annotations;
    private Set<HiddenApiRestriction> hiddenApiRestrictions;
    private MethodImplementation methodImplementation;

    public CachedMethod(Method base) {
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
    public List<? extends CharSequence> getParameterTypes() {
        return parameterTypes == null ? parameterTypes = base.getParameterTypes() : parameterTypes;
    }

    @Override
    public List<? extends MethodParameter> getParameters() {
        return parameters == null ? parameters = base.getParameters().stream().map(CachedMethodParameter::new).collect(Collectors.toList()) : parameters;
    }

    @Override
    public String getReturnType() {
        return returnType == null ? returnType = base.getReturnType() : returnType;
    }

    @Override
    public int compareTo(MethodReference methodReference) {
        return base.compareTo(methodReference);
    }

    @Override
    public int getAccessFlags() {
        return accessFlags == Integer.MIN_VALUE ? accessFlags = base.getAccessFlags() : accessFlags;
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
    public MethodImplementation getImplementation() {
        if (methodImplementation == null) {
            MethodImplementation baseImplementation = base.getImplementation();
            if (baseImplementation == null) {
                return null;
            }
            methodImplementation = new CachedMethodImplementation(baseImplementation);
        }
        return methodImplementation;
    }

    @Override
    public void validateReference() throws InvalidReferenceException {
        base.validateReference();
    }
}
