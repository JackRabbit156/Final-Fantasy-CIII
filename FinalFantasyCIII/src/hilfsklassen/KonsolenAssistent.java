package hilfsklassen;

public class KonsolenAssistent {
    public static void clear() {
        try {
            new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("----> Clear nicht Moeglich <----");
        }
    }
}
