package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.Game;

import java.io.PrintStream;

public final class DebugHelper {

    private DebugHelper() {
    }

    public static boolean isDebug() {
        return Game.DEBUG_LEVEL.ordinal() <= DebugLevel.DEBUG.ordinal();
    }

    public static boolean isTrace() {
        return Game.DEBUG_LEVEL == DebugLevel.TRACE;
    }

    public static void log(Object o) {
        if (isDebug()) {
            System.out.println("[DEBUG] " + o);
        }
    }

    public static void logf(String format, Object... o) {
        if (isDebug()) {
            System.out.printf("[DEBUG] " + format + "%n", o);
        }
    }

    public static void printStackTrace() {
        printStackTrace(System.out);
    }

    public static void printStackTraceAsError() {
        printStackTrace(System.err);
    }

    public static void trace(Object o) {
        if (isTrace()) {
            System.out.println("[TRACE] " + o);
        }
    }

    public static void tracef(String format, Object... o) {
        if (isTrace()) {
            System.out.printf("[TRACE] " + format + "%n", o);
        }
    }

    private static void printStackTrace(PrintStream stream) {
        if (isDebug()) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[3];
            stream.printf("[DEBUG] Stacktrace initiated in %s.%s() (%s:%d):%n",
                    element.getClassName(), element.getMethodName(), element.getFileName(), element.getLineNumber());
            for (int i = 4; i < Thread.currentThread().getStackTrace().length; i++) {
                stream.println("\tfrom " + Thread.currentThread().getStackTrace()[i]);
            }
        }
    }

}
