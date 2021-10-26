package com.util.mappings;

import java.util.HashMap;
import java.util.Map;

public class DexTypeConverter {
    private static final Map<String, String> primMap = new HashMap<>();
    private static final Map<String, String> typeCache = new HashMap<>();

    static {
        Map[] maps = new Map[]{primMap, typeCache};
        for (Map map : maps) {
            map.put("B", "byte");
            map.put("C", "char");
            map.put("D", "double");
            map.put("F", "float");
            map.put("I", "int");
            map.put("J", "long");
            map.put("S", "short");
            map.put("Z", "boolean");
            map.put("V", "void");
        }
    }

    public static boolean isPrimitive(String type) {
        return primMap.containsKey(type) || primMap.containsValue(type);
    }

    public static boolean isDexType(String type) {
        return primMap.containsKey(type) || (type.startsWith("L") && type.endsWith(";"));
    }

    public static String toJavaType(String dex) {
        String result = typeCache.get(dex);
        if (result == null) {
            if (dex.endsWith(";")) {
                result = dex.substring(1, dex.length() - 1).replace("/", ".");
            } else if (dex.startsWith("[")) {
                result = toJavaType(dex.substring(1)) + "[]";
            } else if (primMap.containsKey(dex)) {
                result = primMap.get(dex);
            }
            typeCache.put(dex, result);
        }
        return result;
    }
}
