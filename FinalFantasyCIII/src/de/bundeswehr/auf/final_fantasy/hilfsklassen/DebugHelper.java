package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.Game;

import java.io.PrintStream;

public final class DebugHelper {

    private DebugHelper() {
    }

    public static void printStackTrace() {
        printStackTrace(System.out);
    }

    public static void printStackTraceAsError() {
        printStackTrace(System.err);
    }

    private static void printStackTrace(PrintStream stream) {
        if (Game.debugModus) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[2];
            stream.printf("[DEBUG] Stacktrace initiated in %s.%s() [line %d]:%n",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
            for (int i = 3; i < Thread.currentThread().getStackTrace().length; i++) {
                stream.println("\tfrom " + Thread.currentThread().getStackTrace()[i]);
            }
        }
    }

}
