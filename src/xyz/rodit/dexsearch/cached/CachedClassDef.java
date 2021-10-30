package xyz.rodit.dexsearch.cached;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;
import xyz.rodit.dexsearch.util.ListUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CachedClassDef implements ClassDef {

    private final ClassDef base;

    private String type;
    private int accessFlags = Integer.MIN_VALUE;
    private String superclass;
    private List<String> interfaces;
    private String sourceFile;
    private Set<Annotation> annotations;
    private List<Field> fields;
    private List<Method> methods;

    public CachedClassDef(ClassDef base) {
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
    public int getAccessFlags() {
        return accessFlags == Integer.MIN_VALUE ? accessFlags = base.getAccessFlags() : accessFlags;
    }

    @Override
    public String getSuperclass() {
        return superclass == null ? superclass = base.getSuperclass() : superclass;
    }

    @Override
    public List<String> getInterfaces() {
        return interfaces == null ? interfaces = base.getInterfaces() : interfaces;
    }

    @Override
    public String getSourceFile() {
        return sourceFile == null ? sourceFile = base.getSourceFile() : sourceFile;
    }

    @Override
    public Set<? extends Annotation> getAnnotations() {
        return annotations == null ? annotations = base.getAnnotations().stream().map(CachedAnnotation::new).collect(Collectors.toSet()) : annotations;
    }

    @Override
    public Iterable<? extends Field> getStaticFields() {
        return base.getStaticFields();
    }

    @Override
    public Iterable<? extends Field> getInstanceFields() {
        return base.getInstanceFields();
    }

    @Override
    public Iterable<? extends Field> getFields() {
        return fields == null ? fields = ListUtil.newList(base.getFields(), CachedField::new) : fields;
    }

    @Override
    public Iterable<? extends Method> getDirectMethods() {
        return base.getDirectMethods();
    }

    @Override
    public Iterable<? extends Method> getVirtualMethods() {
        return base.getVirtualMethods();
    }

    @Override
    public Iterable<? extends Method> getMethods() {
        return methods == null ? methods = ListUtil.newList(base.getMethods(), CachedMethod::new) : methods;
    }

    @Override
    public int length() {
        return base.length();
    }

    @Override
    public char charAt(int index) {
        return base.charAt(index);
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
