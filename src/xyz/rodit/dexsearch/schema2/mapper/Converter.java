package xyz.rodit.dexsearch.schema2.mapper;

import com.util.mappings.ClassMapping;
import com.util.mappings.DexTypeConverter;
import xyz.rodit.dexsearch.schema2.ClassMatch;
import xyz.rodit.dexsearch.schema2.FieldMatcher;
import xyz.rodit.dexsearch.schema2.MethodMatcher;

public class Converter {

    public static ClassMapping createMapping(ClassMatch match) {
        ClassMapping mapping = new ClassMapping(match.getOwner().getName(), DexTypeConverter.toJavaType(match.getBound().getType()));
        for (FieldMatcher field : match.getFieldBindings().keySet()) {
            if (!field.getName().getName().equals("_")) {
                mapping.mapField(field.getName().getName(), match.getFieldBindings().get(field).getName());
            }
        }
        for (MethodMatcher method : match.getMethodBindings().keySet()) {
            if (!method.getName().getName().equals("_")) {
                mapping.mapMethod(method.getName().getName(), match.getMethodBindings().get(method).getName());
            }
        }
        return mapping;
    }
}
