package hilfsklassen;

import java.util.Scanner;

public class ScannerHelfer {
    private static Scanner sc = new Scanner(System.in);


    /**
     * @author Nick
     * @since 16.11.2023
     * @return Eingabezahl / bei Falscheingabe {@link Integer#MAX_VALUE}
     */
    public static int nextInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * @author Nick
     * @since 16.11.2023
     * @see Scanner#nextLine()
     */
    public static String nextLine(){
        return sc.nextLine();
    }

    /**
     * TODO Info für dich nick, habe den returnwert ausgetauscht und in e geändert somit muss man nurnoch enter drücken um ins Menü zu kommen
     * @author Nick
     * @since 16.11.2023
     * @return Ersten eingegebenen Char || Bei Falscheingabe '\0'
     */
    public static char nextChar(){
        try {
            return sc.nextLine().charAt(0);
        } catch(IndexOutOfBoundsException e){
            return 'e';
        }
    }

    /**
     * Schliesst den Scanner beim GameOver und Spiel Beenden
     * @author Ridder
     * @since 16.11.2023
     */
    public static void close(){
        sc.close();
    }
}
