package xyz.rodit.dexsearch.schema2;

import xyz.rodit.dexsearch.schema2.resolver.Resolver;

public interface Matcher<T> {

    boolean matches(Resolver resolver, T object);
}
