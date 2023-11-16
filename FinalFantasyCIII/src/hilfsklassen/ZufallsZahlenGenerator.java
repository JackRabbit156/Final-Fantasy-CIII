package hilfsklassen;

import java.util.Random;

/**
 * @Author OF  Kretschmer
 * @since 15.11.23
 * Hilfsklasse für die Berechnung von Zufallszahlen
 */

public class ZufallsZahlenGenerator {


    /**
     * @param maxWert
     * @return Zufallszahl von 0 -  maxWert (Parameter)
     * @author OF Kretschmer
     * @since 15.11.23
     */
    public static int zufallsZahlIntAb0(int maxWert) {
        int zufallszahl;
        Random randomNumberGenerator = new Random();
        zufallszahl = randomNumberGenerator.nextInt(maxWert);

        return zufallszahl;

    }

    /**
     * @param maxWert
     * @return Zufallszahl von 1 -  maxWert (Parameter)
     * @author OF Kretschmer
     * @since 15.11.23
     */
    public static int zufallsZahlIntAb1(int maxWert) {
        int zufallszahl;
        Random randomNumberGenerator = new Random();
        zufallszahl = randomNumberGenerator.nextInt(maxWert);

        return zufallszahl + 1;

    }

    /**
     * @return Zufallszahl von -2 -  +2 (Parameter)
     * <p>
     * Genutzt bei der Gegenstandserzeugung um den Gegenstand +/- 2 Stufen um die aktuelle Charakterstufe zu generieren
     * @author OF Kretschmer
     * @since 15.11.23
     */
    public static int zufallsZahlIntGegenstandsstufe() {
        int zufallszahl;
        Random randomNumberGenerator = new Random();
        zufallszahl = randomNumberGenerator.nextInt(4);

        return zufallszahl - 2;

    }


}
