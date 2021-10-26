package xyz.rodit.dexsearch.util;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class ListMatcher {

    public static <T0, T1> boolean match(Collection<T0> c0, Collection<T1> c1, ElementMatcher<T0, T1> matcher) {
        return match(c0, c1, matcher, null);
    }

    public static <T0, T1> boolean match(Collection<T0> c0, Collection<T1> c1, ElementMatcher<T0, T1> matcher, UntilPredicate<T0, T1> until) {
        List<T0> l0 = Lists.newArrayList(c0);
        List<T1> l1 = Lists.newArrayList(c1);

        if (until == null && l0.size() != l1.size()) {
            return false;
        }

        if (l0.size() == 0 && l1.size() != 0) {
            return false;
        }

        int minSize = Math.min(l0.size(), l1.size());
        int maxSize = Math.max(l0.size(), l1.size());

        for (int i = 0; i < maxSize; i++) {
            if (until != null) {
                if (i >= minSize) {
                    return false;
                }
                if (until.until(l0.get(i), l1.get(i))) {
                    return true;
                }
            }
            if (!matcher.match(l0.get(i), l1.get(i))) {
                return false;
            }
        }
        return true;
    }

    public interface ElementMatcher<T0, T1> {

        boolean match(T0 o0, T1 o1);
    }

    public interface UntilPredicate<T0, T1> {

        boolean until(T0 o0, T1 o1);
    }
}
