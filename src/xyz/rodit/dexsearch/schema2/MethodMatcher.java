package xyz.rodit.dexsearch.schema2;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;
import xyz.rodit.dexsearch.util.ListMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MethodMatcher extends MemberMatcher<Method> {

    private final List<TypeMatcher> args = new ArrayList<>();
    private final List<MethodBytecodeMatcher> body = new ArrayList<>();

    public MethodMatcher(NameMatcher name, TypeMatcher type, Collection<AnnotationMatcher> annotations, Collection<TypeMatcher> args, Collection<MethodBytecodeMatcher> body, boolean isStatic, boolean not) {
        super(name, type, annotations, isStatic, not);
        this.args.addAll(args);
        this.body.addAll(body);
    }

    public List<TypeMatcher> getArgs() {
        return args;
    }

    @Override
    public String getMemberName(Method method) {
        return method.getName();
    }

    @Override
    public String getType(Method method) {
        return method.getReturnType();
    }

    @Override
    public Collection<? extends Annotation> getAnnotations(Method method) {
        return method.getAnnotations();
    }

    @Override
    public boolean matches(Resolver resolver, Method method) {
        return super.matches(resolver, method)
                && ListMatcher.match(method.getParameters(), args, (param, arg) -> arg.matches(resolver, param.getType()), (param, arg) -> arg.getName().equals("..."))
                && body.stream().allMatch(b -> b.matches(resolver, method.getImplementation()));
    }
}
