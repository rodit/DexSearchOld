package xyz.rodit.dexsearch.schema2;

import com.util.mappings.DexTypeConverter;
import org.jf.dexlib2.iface.ClassDef;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;

public class TypeMatcher implements Matcher<String> {

    private final String typeName;
    private final boolean matchAny;
    private final boolean reference;
    private final boolean late;
    private final boolean superClass;

    public TypeMatcher(String typeName, boolean matchAny, boolean reference, boolean late, boolean superClass) {
        this.typeName = typeName;
        this.matchAny = matchAny;
        this.reference = reference;
        this.late = late;
        this.superClass = superClass;
    }

    public String getName() {
        return typeName;
    }

    public boolean isLate() {
        return late;
    }

    @Override
    public boolean matches(Resolver resolver, String type) {
        if (reference) {
            ClassMatch match = resolver.getResolved(typeName);
            if (match != null) {
                if (superClass) {
                    ClassDef cls = resolver.getDex().getClass(type);
                    ClassDef mType = match.getBound();
                    String superName;
                    while ((superName = mType.getSuperclass()) != null && !superName.equals("Ljava/lang/Object;")) {
                        mType = resolver.getDex().getClass(DexTypeConverter.toJavaType(mType.getSuperclass()));
                        if (mType == null) {
                            return false;
                        }
                        if (mType.equals(cls)) {
                            return true;
                        }
                    }
                    return match.getBound().getInterfaces().contains(type);
                }
                return match.getBound().getType().equals(type);
            }
            return false;
        }
        if (late) {
            ClassDef lateBinding = resolver.getDex().getClass(type);
            if (lateBinding != null) {
                resolver.putVeryLateBinding(typeName, lateBinding);
            }
        }
        return late || matchAny || typeName.equals(DexTypeConverter.toJavaType(type));
    }

    @Override
    public String toString() {
        return "TypeMatcher{" +
                "typeName='" + typeName + '\'' +
                ", matchAny=" + matchAny +
                ", reference=" + reference +
                ", late=" + late +
                ", superClass=" + superClass +
                '}';
    }
}
