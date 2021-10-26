package xyz.rodit.dexsearch.schema2;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;

public class DefineMatcher implements Matcher<Object> {

    private final String defName;
    private final String defValue;

    public DefineMatcher(String defName, String defValue) {
        this.defName = defName;
        this.defValue = defValue;
    }

    public String getDefName() {
        return defName;
    }

    public String getDefValue() {
        return defValue;
    }

    public TypeName getType() {
        if(defValue.startsWith("\"")) {
            return ClassName.get(String.class);
        } else if(defValue.startsWith("'")){
            return TypeName.CHAR;
        } else if(defValue.contains(".")) {
            return TypeName.DOUBLE;
        }
        return TypeName.INT;
    }

    @Override
    public boolean matches(Resolver resolver, Object object) {
        return false;
    }
}
