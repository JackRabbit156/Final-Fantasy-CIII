package hilfsklassen;

import java.io.IOException;

public class KonsolenAssistent {
    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
    }
}
