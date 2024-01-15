package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.Game;

public final class DebugHelper {

    private DebugHelper() {
    }

    public static void printStackTrace() {
        if (Game.debugModus) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[2];
            System.out.printf("[DEBUG] Stacktrace initiated in %s line %d:%n", element.getClassName(), element.getLineNumber());
            for (int i = 3; i < Thread.currentThread().getStackTrace().length; i++) {
                StackTraceElement traceElement = Thread.currentThread().getStackTrace()[i];
                System.out.println("\tat " + traceElement);
            }
        }
    }

}
