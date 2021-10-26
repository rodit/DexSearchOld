package xyz.rodit.dexsearch.schema2;

import com.util.mappings.DexTypeConverter;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;

import java.util.HashMap;
import java.util.Map;

public class ClassMatch {

    private final ClassMatcher owner;
    private final ClassDef bound;

    private Map<FieldMatcher, Field> fields = new HashMap<>();
    private Map<MethodMatcher, Method> methods = new HashMap<>();
    private MatchMetrics metrics = new MatchMetrics(this);

    public ClassMatch(ClassMatcher owner, ClassDef bound) {
        this.owner = owner;
        this.bound = bound;
    }

    public ClassMatcher getOwner() {
        return owner;
    }

    public ClassDef getBound() {
        return bound;
    }

    public Map<FieldMatcher, Field> getFieldBindings() {
        return fields;
    }

    public Map<MethodMatcher, Method> getMethodBindings() {
        return methods;
    }

    public MatchMetrics getMetrics() {
        return metrics;
    }

    public void bind(FieldMatcher matcher, Field field) {
        fields.put(matcher, field);
        metrics.matched(MatchMetrics.FIELD);
    }

    public void bind(MethodMatcher matcher, Method method) {
        methods.put(matcher, method);
        metrics.matched(MatchMetrics.METHOD);
    }

    @Override
    public String toString() {
        return "ClassMatch{" +
                "owner=" + owner.getName() +
                ", bound=" + DexTypeConverter.toJavaType(bound.getType()) +
                ", fields=" + fields.size() +
                ", methods=" + methods.size() +
                '}';
    }
}
