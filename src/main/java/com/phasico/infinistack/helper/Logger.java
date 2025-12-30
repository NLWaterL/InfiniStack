package com.phasico.infinistack.helper;

@Deprecated //This whole class is really just a debug-helper. Would be removed quickly.
public class Logger {

    public enum Level {
        DEBUG, INFO, WARN, ERROR
    }

    public static void debug(String msg) { log(Level.DEBUG, msg); }

    public static void info(String msg) {
        log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        log(Level.WARN, msg);
    }

    public static void error(String msg) {
        log(Level.ERROR, msg);
    }

    private static void log(Level level, String msg) {
            System.out.println("[InfiniStack/" + level.name() + "] " + msg);
    }
}