package xyz.rodit.dexsearch.schema2;

import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Member;
import xyz.rodit.dexsearch.util.ListMatcher;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MemberMatcher<T extends Member> implements Matcher<T> {

    private final NameMatcher name;
    private final TypeMatcher type;
    private final List<AnnotationMatcher> annotations = new ArrayList<>();
    private final boolean isStatic;

    protected final boolean not;

    public MemberMatcher(NameMatcher name, TypeMatcher type, Collection<AnnotationMatcher> annotations, boolean isStatic, boolean not) {
        this.name = name;
        this.type = type;
        this.annotations.addAll(annotations);
        this.isStatic = isStatic;
        this.not = not;
    }

    public NameMatcher getName() {
        return name;
    }

    public TypeMatcher getType() {
        return type;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean shouldInvert() {
        return this.not;
    }

    public abstract String getMemberName(T object);

    public abstract String getType(T object);

    public abstract Collection<? extends Annotation> getAnnotations(T object);

    @Override
    public boolean matches(Resolver resolver, T object) {
        String name = getMemberName(object);
        String type = getType(object);
        return this.name.matches(resolver, name)
                && this.type.matches(resolver, type)
                && (!isStatic || (object.getAccessFlags() & AccessFlags.STATIC.getValue()) != 0)
                && (annotations.size() == 0 || ListMatcher.match(getAnnotations(object), annotations, (an, arg) -> arg.matches(resolver, an)));
    }

    @Override
    public String toString() {
        return "MemberMatcher{" +
                "name=" + name +
                ", type=" + type +
                ", annotations=" + annotations.size() +
                ", isStatic=" + isStatic +
                ", not=" + not +
                '}';
    }
}
