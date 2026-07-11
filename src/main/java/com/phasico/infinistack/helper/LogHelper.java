package com.phasico.infinistack.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogHelper {

    private static final Logger LOG = LogManager.getLogger("InfiniStack");

    private LogHelper() {}

    public static void debug(String msg, Object... args) { LOG.debug(msg, args); }

    public static void info(String msg, Object... args) { LOG.info(msg, args); }

    public static void warn(String msg, Object... args) { LOG.warn(msg, args); }

    public static void error(String msg, Object... args) { LOG.error(msg, args); }

    public static void error(String msg, Throwable t) { LOG.error(msg, t); }
}
