package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import java.util.Random;

/**
 * @author OF  Kretschmer
 * @since 15.11.23
 * Hilfsklasse für die Berechnung von Zufallszahlen
 */

public class ZufallsZahlenGenerator {

    private static final Random randomNumberGenerator = new Random();

    /**
     * Bei der Gegenstandserzeugung für Händler um den Gegenstand +/- 2 Stufen um die aktuelle Charakterstufe
     * zu generieren
     *
     * @param stufe int für die Stufe
     * @return Gegenstandstufe im Bereich +2/-2 um die aktuelle Stufe, mindestens Stufe 1
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int gegenstandsstufeFuerHaendler(int stufe) {
        int gegenstandsStufe = stufe;
        int zufallszahl = randomNumberGenerator.nextInt(5);
        gegenstandsStufe = gegenstandsStufe + (zufallszahl - 2);
        if (gegenstandsStufe < 1) {
            gegenstandsStufe = 1;
        }
        return gegenstandsStufe;
    }

    /**
     * Gibt einen Int Wert zwischen 0 und dem eingegeben Wert (exklusive) zurück
     *
     * @param bound Grenzzahl
     * @return Zufallszahl von 0 bis bound
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb0(int bound) {
        return randomNumberGenerator.nextInt(bound);
    }

    /**
     * Gibt einen Int Wert zwischen 0 und dem eingegeben Wert (inklusive) zurück
     *
     * @param maxWert Die höchste Zahl die rauskommen soll
     * @return Zufallszahl von 0 bis maxWert
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb0Inklusive(int maxWert) {
        return randomNumberGenerator.nextInt(maxWert + 1);
    }

    /**
     * Gibt einen Int wert zwischen 1 und dem eingegeben Wert (exklusive) zurück
     *
     * @param bound Grenzzahl
     * @return Zufallszahl von 1 bis bound
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb1(int bound) {
        return randomNumberGenerator.nextInt(bound - 1) + 1;
    }

    /**
     * Gibt einen Int wert zwischen 1 und dem eingegeben Wert (inklusive) zurück
     *
     * @param maxWert Die höchste Zahl die rauskommen soll
     * @return Zufallszahl von 1 bis maxWert
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb1Inklusive(int maxWert) {
        return randomNumberGenerator.nextInt(maxWert) + 1;
    }


}
