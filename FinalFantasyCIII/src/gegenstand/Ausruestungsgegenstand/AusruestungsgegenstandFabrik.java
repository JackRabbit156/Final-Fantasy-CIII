package gegenstand.Ausruestungsgegenstand;

import charakter.model.klassen.HLR;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import haendler.Haendler;
import gegenstand.Ausruestungsgegenstand.Ruestungen.*;
import gegenstand.Ausruestungsgegenstand.Waffen.*;

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
        if (objekt instanceof Klasse) {
            if (objekt instanceof HLR) {
                returnRuestung = new LeichteRuestung(derzeitigesLevel, true);
            } else if (objekt instanceof MDD) {
                returnRuestung = new MittlereRuestung(derzeitigesLevel, true);
            } else if (objekt instanceof PDD) {
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
            System.err.println("AusruestungsgegenstandFabrik: Keine Ruestung fuer diese Klasse erstellbar! - null wird zurueckgegeben");
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
        if (objekt instanceof Klasse) {
            if (objekt instanceof HLR) {
                returnWaffe = new Heilerwaffe(derzeitigesLevel, true);
            } else if (objekt instanceof MDD) {
                returnWaffe = new Magierwaffe(derzeitigesLevel, true);
            } else if (objekt instanceof PDD) {
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
            System.err.println("AusruestungsgegenstandFabrik: Keine Waffe fuer diese Klasse erstellbar! - null wird zurueckgegeben");
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
            System.err.println("AusruestungsgegenstandFabrik: Keine Accessoire fuer diese Klasse erstellbar! - null wird zurueckgegeben");
            returnAccessoire = null;
        }
        return returnAccessoire;
    }

    /**
     * Ersetzt einen Konstruktor fuer die Klasse Waffe
     * @param name : Name
     * @param kaufswert: Kaufwert
     * @param verkaufswert: Verkaufwert
     * @param istnichtKaufbar: ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung: anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem: legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param attacke: grundwert der attacke fuer physischen Schaden
     * @param magischeAttacke: grundwert der attacke fuer magischen Schaden
     * @param waffentyp : Namen der Subklasse von Waffe
     * @return Eine Waffe mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Waffe waffeAusSpeicherstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                   int levelAnforderung, boolean istSoeldnerItem, int attacke, int magischeAttacke, String waffentyp) {
        Waffe returnWaffe = null;
        switch (waffentyp) {
            case "Einhandwaffe":
                returnWaffe = new Einhandwaffe(0);
                break;
            case "ZweihandWaffe":
                returnWaffe = new Zweihandwaffe(0);
                break;
            case "HeilerWaffe":
                returnWaffe = new Heilerwaffe(0);
                break;
            case "Magierwaffe":
                returnWaffe = new Magierwaffe(0);
                break;
            default:
                System.err.println("AusruestungsgegenstandFabrik: waffeAusSpielstandLaden: Waffe wurde nicht richtig erstellt - leere Waffe wird geladen");
                returnWaffe = new Waffe();
                break;
        }
        returnWaffe.setName(name);
        returnWaffe.setKaufwert(kaufswert);
        returnWaffe.setVerkaufswert(verkaufswert);
        returnWaffe.setIstNichtKaufbar(istnichtKaufbar);
        returnWaffe.setLevelAnforderung(levelAnforderung);
        returnWaffe.setIstSoeldnerItem(istSoeldnerItem);
        returnWaffe.setAttacke(attacke);
        returnWaffe.setMagischeAttacke(magischeAttacke);
        return returnWaffe;
    }

    /**
     * Ersetzt einen Konstruktor fuer die Klasse Ruestung
     * @param name : Name
     * @param kaufswert : Kaufwert
     * @param verkaufswert : Verkaufwert
     * @param istnichtKaufbar : ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung : anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem : legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param magischeVerteidigung: magischeVerteidigung
     * @param verteidigung : verteidigung
     * @param RuestungTyp : Namen der Subklasse von Ruestung
     * @return Eine Ruestung mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Ruestung ruestungAusSpeicherstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                   int levelAnforderung, boolean istSoeldnerItem, int magischeVerteidigung, int verteidigung, String RuestungTyp) {
        Ruestung returnRuestung = null;
        switch (RuestungTyp) {
            case "LeichteRuestung":
                returnRuestung = new LeichteRuestung(0);
                break;
            case "MittlereRuestung":
                returnRuestung = new MittlereRuestung(0);
                break;
            case "SchwereRuestung":
                returnRuestung = new SchwereRuestung(0);
                break;
            case "SehrSchwereRuestung":
                returnRuestung = new SehrSchwereRuestung(0);
                break;
            default:
                System.err.println("AusruestungsgegenstandFabrik: ruestungAusSpielstandLaden: Ruestung wurde nicht richtig erstellt - leere Ruestung wird geladen");
                returnRuestung = new Ruestung();
                break;
        }
        returnRuestung.setName(name);
        returnRuestung.setKaufwert(kaufswert);
        returnRuestung.setVerkaufswert(verkaufswert);
        returnRuestung.setIstNichtKaufbar(istnichtKaufbar);
        returnRuestung.setLevelAnforderung(levelAnforderung);
        returnRuestung.setIstSoeldnerItem(istSoeldnerItem);
        returnRuestung.setMagischeVerteidigung(magischeVerteidigung);
        returnRuestung.setVerteidigung(verteidigung);
        return returnRuestung;
    }

    /**
     * Ersetzt einen Konstruktor fuer die Klasse Accessiore
     * @param name: Name
     * @param kaufswert: Kaufwert
     * @param verkaufswert: Verkaufwert
     * @param istnichtKaufbar: ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung: anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem: legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param maxGesundheitsPunkte : Bonus fuer maxGesundheitsPunkte
     * @param maxManaPunkte : Bonus fuer maxManaPunkte
     * @param gesundheitsRegeneration : Bonus fuer gesundheitsRegeneration
     * @param manaRegeneration: Bonus fuer manaRegeneration
     * @param beweglichkeit : Bonus fuer beweglichkeit
     * @return Ein Accessiore mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Accessoire accessoireAusSpielstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                          int levelAnforderung, boolean istSoeldnerItem, int maxGesundheitsPunkte,
                                                          int maxManaPunkte, int gesundheitsRegeneration, int manaRegeneration,
                                                          int beweglichkeit){
        Accessoire accessoire = new Accessoire(0);
        accessoire.setName(name);
        accessoire.setKaufwert(kaufswert);
        accessoire.setVerkaufswert(verkaufswert);
        accessoire.setIstNichtKaufbar(istnichtKaufbar);
        accessoire.setLevelAnforderung(levelAnforderung);
        accessoire.setIstSoeldnerItem(istSoeldnerItem);
        accessoire.setMaxGesundheitsPunkte(maxGesundheitsPunkte);
        accessoire.setMaxManaPunkte(maxManaPunkte);
        accessoire.setGesundheitsRegeneration(gesundheitsRegeneration);
        accessoire.setManaRegeneration(manaRegeneration);
        accessoire.setBeweglichkeit(beweglichkeit);
        return accessoire;
    }
}
