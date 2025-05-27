package com.phasico.infinistack.helper;

import java.time.format.DateTimeFormatter;
import com.phasico.infinistack.helper.Configurables;

public class Logger {

    public enum Level {
        DEBUG, INFO, WARN, ERROR
    }


    private static Level currentLevel = Configurables.isDebugging ? Level.DEBUG : Level.INFO;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void debug(String msg) {
        log(Level.DEBUG, msg);
    }

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
        if (level.ordinal() >= currentLevel.ordinal()) {
            System.out.println("[InfiniStack/" + level.name() + "] " + msg);
        }
    }
}