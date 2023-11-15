package hilfsklassen;

import java.util.Random;

/**
 * @Author OF  Kretschmer
 *
 * Hilfsklasse für die Berechnung von Zufallszahlen
 */

public class ZufallsZahlenGenerator {


    /**
     * @author OF Kretschmer
     * @param maxWert
     * @return Zufallszahl von 0 -  maxWert (Parameter)
     */
   public static int ZufallsZahlIntAb0 (int maxWert){
       int zufallszahl;
       Random randomNumberGenerator = new Random();
       zufallszahl = randomNumberGenerator.nextInt(maxWert);

       return zufallszahl;

   }
    /**
     * @author OF Kretschmer
     * @param maxWert
     * @return Zufallszahl von 1 -  maxWert (Parameter)
     */
    public static int ZufallsZahlIntAb1 (int maxWert){
        int zufallszahl;
        Random randomNumberGenerator = new Random();
        zufallszahl = randomNumberGenerator.nextInt(maxWert);

        return zufallszahl +1;

    } /**
     * @author OF Kretschmer
     * @param maxWert
     * @return Zufallszahl von -2 -  +2 (Parameter)
     *
     * Genutzt bei der Gegenstandserzeugung um den Gegenstand +/- 2 Stufen um die aktuelle Charakterstufe zu generieren
     */
    public static int ZufallsZahlIntGegenstandsstufe (){
        int zufallszahl;
        Random randomNumberGenerator = new Random();
        zufallszahl = randomNumberGenerator.nextInt(4);

        return zufallszahl -2;

    }



}
