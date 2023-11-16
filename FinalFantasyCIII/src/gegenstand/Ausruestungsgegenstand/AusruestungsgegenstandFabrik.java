package gegenstand.Ausruestungsgegenstand;

import charakter.model.Charakter;
import charakter.model.klassen.HLR;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import gamehub.haendler.Haendler;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;

/**
 * @author 11777914 OLt Oliver Ebert
 * @since 16.11.2023
 * AusruestungsgegenstandFabrik bietet einen geregelten Zugriff auf die Konstruktoren der Unterklassen von Ausruestungsgegenstand
 * {@see Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
 * {@see Waffe }: Hier werden die Waffen für spezifische Klassen erstellt
 * {@see Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
 */
public abstract class AusruestungsgegenstandFabrik {
    private static final int MOEGLICHE_WAFFEN_KLASSEN_ANZAHL = 4;
    private static final int MOEGLICHE_RUESTUNG_KLASSEN_ANZAHL = 4;

    /**
     * @param objekt:           Objekt, fuer das eine Instanz der Klasse Ruestung erstellt werden soll.
     * @param derzeitigesLevel: Level der Ruestung
     *                          {@link Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
     *                          Ruestung bietet zwei Konstruktoren:
     *                          - kaufbare Ruestung fuer z.B. den Haendler: new Ruestung(String, Integer)
     *                          - nicht kaufbare Ruestung fuer z.B. Soeldner oder Gegner: new Ruestung(String, Integer, Boolean)
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     */
    public static Ruestung erstelleRuestungFuer(Object objekt, int derzeitigesLevel) {
        Ruestung returnRuestung;
        if (objekt instanceof Charakter) {
            Klasse charakterKlasse = ((Charakter) objekt).getKlasse();
            if (charakterKlasse instanceof HLR) {
                returnRuestung = new LeichteRuestung(derzeitigesLevel, true);
            } else if (charakterKlasse instanceof MDD) {
                returnRuestung = new MittlereRuestung(derzeitigesLevel, true);
            } else if (charakterKlasse instanceof PDD) {
                returnRuestung = new SchwereRuestung(derzeitigesLevel, true);
            } else {
                returnRuestung = new SehrSchwereRuestung(derzeitigesLevel, true);
            }
        } else if (objekt instanceof Haendler) {
            int zufallsZahl = (int) (Math.random() * MOEGLICHE_RUESTUNG_KLASSEN_ANZAHL);
            switch (zufallsZahl) {
                case 0:
                    returnRuestung = new LeichteRuestung(derzeitigesLevel);
                    break;
                case 1:
                    returnRuestung = new MittlereRuestung(derzeitigesLevel);
                    break;
                case 2:
                    returnRuestung = new SchwereRuestung(derzeitigesLevel);
                    break;
                default:
                    returnRuestung = new SehrSchwereRuestung(derzeitigesLevel);
                    break;
            }
        } else {
            System.err.println("AusruestungsgegenstandFabrik: Keine Ruestung für diese Klasse erstellbar! - null wird zurückgegeben");
            returnRuestung = null;
        }
        return returnRuestung;
    }

    /**
     * @param objekt:           Objekt, fuer das eine Instanz der Klasse Waffe erstellt werden soll.
     * @param derzeitigesLevel: Level der Waffe
     *                          {@link Waffe }: Hier werden die Waffe für spezifische Klassen erstellt
     *                          Waffe bietet zwei Konstruktoren:
     *                          - kaufbare Waffe fuer z.B. den Haendler: new Waffe(String, Integer)
     *                          - nicht kaufbare Waffe fuer z.B. Soeldner oder Gegner: new Waffe(String, Integer, Boolean)
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     */
    public static Waffe erstelleWaffeFuer(Object objekt, int derzeitigesLevel) {
        Waffe returnWaffe;
        if (objekt instanceof Charakter) {
            Klasse charakterKlasse = ((Charakter) objekt).getKlasse();
            if (charakterKlasse instanceof HLR) {
                returnWaffe = new Heilerwaffe(derzeitigesLevel, true);
            } else if (charakterKlasse instanceof MDD) {
                returnWaffe = new Magierwaffe(derzeitigesLevel, true);
            } else if (charakterKlasse instanceof PDD) {
                returnWaffe = new Einhandwaffe(derzeitigesLevel, true);
            } else {
                returnWaffe = new Zweihandwaffe(derzeitigesLevel, true);
            }
        } else if (objekt instanceof Haendler) {
            int zufallsZahl = (int) (Math.random() * MOEGLICHE_WAFFEN_KLASSEN_ANZAHL);
            switch (zufallsZahl) {
                case 0:
                    returnWaffe = new Heilerwaffe(derzeitigesLevel);
                    break;
                case 1:
                    returnWaffe = new Magierwaffe(derzeitigesLevel);
                    break;
                case 2:
                    returnWaffe = new Einhandwaffe(derzeitigesLevel);
                    break;
                default:
                    returnWaffe = new Zweihandwaffe(derzeitigesLevel);
                    break;
            }
        } else {
            System.err.println("AusruestungsgegenstandFabrik: Keine Waffe für diese Klasse erstellbar! - null wird zurückgegeben");
            returnWaffe = null;
        }
        return returnWaffe;
    }

    /**
     * @param derzeitigesLevel: Level der Accessoire
     *                          {@link Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
     *                          Accessoire bietet zwei Konstruktoren:
     *                          - kaufbare Accessoire fuer z.B. den Haendler: new Accessoire(Integer)
     *                          - nicht kaufbare Accessoire fuer z.B. Soeldner oder Gegner: new Accessoire(Integer, Boolean)
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     */
    public static Accessoire erstelleAccessoireFuer(Object objekt, int derzeitigesLevel) {
        Accessoire returnAccessoire;
        if (objekt instanceof Klasse) {
            returnAccessoire = new Accessoire(derzeitigesLevel, true);
        } else if (objekt instanceof Haendler) {
            returnAccessoire = new Accessoire(derzeitigesLevel);
        } else {
            System.err.println("AusruestungsgegenstandFabrik: Keine Accessoire für diese Klasse erstellbar! - null wird zurückgegeben");
            returnAccessoire = null;
        }
        return returnAccessoire;
    }
}
