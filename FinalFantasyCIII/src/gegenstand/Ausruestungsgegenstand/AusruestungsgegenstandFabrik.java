package gegenstand.Ausruestungsgegenstand;

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
    private static final String[] moeglicheKlassen = {"HLR", "MDD", "PDD", "TNK"};

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     * @param objekt: Objekt, fuer das eine Instanz der Klasse Ruestung erstellt werden soll.
     * @param derzeitigesLevel: Level der Ruestung
     * {@link Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
     *                        Ruestung bietet zwei Konstruktoren:
     *                        - kaufbare Ruestung fuer z.B. den Haendler: new Ruestung(String, Integer)
     *                        - nicht kaufbare Ruestung fuer z.B. Soeldner oder Gegner: new Ruestung(String, Integer, Boolean)
     */
//    public static Ruestung erstelleRuestungFuer(Object objekt , int derzeitigesLevel){
//        Ruestung returnRuestung;
//        if(objekt instanceof Klasse){
//            if(objekt instanceof HLR){
//                returnRuestung = new Ruestung("HLR", derzeitigesLevel, true);
//            } else if (objekt instanceof MDD){
//                returnRuestung = new Ruestung("MDD", derzeitigesLevel, true);
//            } else if(objekt instanceof PDD){
//                returnRuestung = new Ruestung("PDD", derzeitigesLevel, true);
//            } else {
//                returnRuestung = new Ruestung("TNK", derzeitigesLevel, true);
//            }
//        } else if(objekt instanceof Haendler){
//            returnRuestung = new Ruestung(moeglicheKlassen[(int) Math.random()*moeglicheKlassen.length], derzeitigesLevel);
//        } else{
//            System.err.println("AusruestungsgegenstandFabrik: Keine Ruestung für diese Klasse erstellbar! - null wird zurückgegeben");
//            returnRuestung = null;
//        }
//        return returnRuestung;
//    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     * @param objekt: Objekt, fuer das eine Instanz der Klasse Waffe erstellt werden soll.
     * @param derzeitigesLevel: Level der Waffe
     * {@link Waffe }: Hier werden die Waffe für spezifische Klassen erstellt
     *                        Waffe bietet zwei Konstruktoren:
     *                        - kaufbare Waffe fuer z.B. den Haendler: new Waffe(String, Integer)
     *                        - nicht kaufbare Waffe fuer z.B. Soeldner oder Gegner: new Waffe(String, Integer, Boolean)
     */
//    public static Waffe erstelleWaffeFuer(Object objekt , int derzeitigesLevel){
//        Waffe returnWaffe;
//        if(objekt instanceof Klasse){
//            if(objekt instanceof HLR){
//                returnWaffe = new Waffe("HLR", derzeitigesLevel, true);
//            } else if (objekt instanceof MDD){
//                returnWaffe = new Waffe("MDD", derzeitigesLevel, true);
//            } else if(objekt instanceof PDD){
//                returnWaffe = new Waffe("PDD", derzeitigesLevel, true);
//            } else {
//                returnWaffe = new Waffe("TNK", derzeitigesLevel, true);
//            }
//        } else if(objekt instanceof Haendler){
//            returnWaffe = new Waffe(moeglicheKlassen[(int) Math.random()*moeglicheKlassen.length], derzeitigesLevel);
//        } else{
//            System.err.println("AusruestungsgegenstandFabrik: Keine Waffe für diese Klasse erstellbar! - null wird zurückgegeben");
//            returnWaffe = null;
//        }
//        return returnWaffe;
//    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     * @param derzeitigesLevel: Level der Accessoire
     * {@link Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
     *                        Accessoire bietet zwei Konstruktoren:
     *                        - kaufbare Accessoire fuer z.B. den Haendler: new Accessoire(Integer)
     *                        - nicht kaufbare Accessoire fuer z.B. Soeldner oder Gegner: new Accessoire(Integer, Boolean)
     */
//    public static Accessoire erstelleAccessoireFuer(Object objekt , int derzeitigesLevel){
//        Accessoire returnAccessoire;
//        if(objekt instanceof Klasse){
//            if(objekt instanceof HLR){
//                returnAccessoire = new Accessoire(derzeitigesLevel, true);
//            } else if (objekt instanceof MDD){
//                returnAccessoire = new Accessoire(derzeitigesLevel, true);
//            } else if(objekt instanceof PDD){
//                returnAccessoire = new Accessoire(derzeitigesLevel, true);
//            } else {
//                returnAccessoire = new Accessoire(derzeitigesLevel, true);
//            }
//        } else if(objekt instanceof Haendler){
//            returnAccessoire = new Accessoire(derzeitigesLevel);
//        } else{
//            System.err.println("AusruestungsgegenstandFabrik: Keine Accessoire für diese Klasse erstellbar! - null wird zurückgegeben");
//            returnAccessoire = null;
//        }
//        return returnAccessoire;
//    }
}
