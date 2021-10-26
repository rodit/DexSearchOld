package xyz.rodit.dexsearch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListUtil {

    public static <T, R> List<R> newList(Iterable<? extends T> iterable, Function<T, R> mapper) {
        List<R> list = new ArrayList<>();
        for (T obj : iterable) {
            list.add(mapper.apply(obj));
        }
        return list;
    }
}
