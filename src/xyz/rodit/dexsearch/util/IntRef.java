package xyz.rodit.dexsearch.util;

public class IntRef {

    private int value;

    public IntRef() {
        this(0);
    }

    public IntRef(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public void inc() {
        value++;
    }

    public void dec() {
        value--;
    }
}
