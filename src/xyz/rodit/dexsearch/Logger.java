package xyz.rodit.dexsearch;

public class Logger {

    public static void info(String message, Object... format) {
        System.out.printf((message) + "%n", format);
    }

    public static void error(String message, Object... format) {
        System.err.printf((message) + "%n", format);
    }
}
