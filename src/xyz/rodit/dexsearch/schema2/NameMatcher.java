package xyz.rodit.dexsearch.schema2;

import xyz.rodit.dexsearch.schema2.resolver.Resolver;

public class NameMatcher implements Matcher<String> {

    private final String name;
    private final boolean exact;

    public NameMatcher(String name, boolean exact) {
        this.name = name;
        this.exact = exact;
    }

    public String getName() {
        return name;
    }

    public boolean isExact() {
        return exact;
    }

    @Override
    public boolean matches(Resolver resolver, String name) {
        return !exact || this.name.equals(name);
    }

    @Override
    public String toString() {
        return "NameMatcher{" +
                "name='" + name + '\'' +
                ", exact=" + exact +
                '}';
    }
}
