package hilfsklassen;

import java.util.Random;

/**
 * @author OF  Kretschmer
 * @since 15.11.23
 * Hilfsklasse für die Berechnung von Zufallszahlen
 */

public class ZufallsZahlenGenerator {
    private static final Random randomNumberGenerator = new Random();

    /**
     * Gibt einen Int Wert zwischen 0 und dem eingegeben Wert (inklusive) zurück
     * @param maxWert Die höchste Zahl die rauskommen soll
     * @return Zufallszahl von 0 -  maxWert (Parameter)
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb0(int maxWert) {
        int zufallszahl;
        zufallszahl = randomNumberGenerator.nextInt(maxWert+1);

        return zufallszahl;

    }

    /**
     * Gibt einen Int wert zwischen 1 und dem eingegeben Wert (inklusive) zurück
     * @param maxWert Die Höchste Zahl die rauskommen soll
     * @return Zufallszahl von 1 -  maxWert (Parameter)
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntAb1(int maxWert) {
        int zufallszahl;
        zufallszahl = randomNumberGenerator.nextInt(maxWert);

        return zufallszahl + 1;

    }

    /**
     * Gegenstandstufe im Bereich +2/-2 um die aktuelle Stufe des Charakters, mind Stufe 1
     * @return Int für Gegenstandstufe im Bereich +2/-2 um die aktuelle Stufe des Charakters, mind Stufe 1
     * <p>
     * Genutzt bei der Gegenstandserzeugung um den Gegenstand +/- 2 Stufen um die aktuelle Charakterstufe zu generieren
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static int zufallsZahlIntGegenstandsstufe(int stufe) {
        int zufallszahl;
        int gegenstandsStufe = stufe;
        zufallszahl = randomNumberGenerator.nextInt(5);
        gegenstandsStufe = gegenstandsStufe + (zufallszahl-2);
        if (gegenstandsStufe < 1){
            gegenstandsStufe = 1;
        }

        return gegenstandsStufe;

    }


}
