package gegenstand.Ausruestungsgegenstand;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import gegenstand.Ausruestungsgegenstand.Ruestungen.*;
import gegenstand.Ausruestungsgegenstand.Waffen.*;
import haendler.Haendler;
import hilfsklassen.ZufallsZahlenGenerator;

/**
 * AusruestungsgegenstandFabrik bietet einen geregelten Zugriff auf die Konstruktoren der Unterklassen von Ausruestungsgegenstand
 *
 * @author 11777914 OLt Oliver Ebert
 * @since 16.11.2023
 * {@see Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
 * {@see Waffe }: Hier werden die Waffen für spezifische Klassen erstellt
 * {@see Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
 */
public abstract class AusruestungsgegenstandFabrik {
    private static final int MOEGLICHE_WAFFEN_KLASSEN_ANZAHL = 6;
    private static final int MOEGLICHE_RUESTUNG_KLASSEN_ANZAHL = 4;

    /**
     * Erstellt passende Ruestung fuer die möglichen Klassen
     *
     * @param klasse           Klasse, fuer das eine Instanz der Klasse Ruestung erstellt werden soll. -> es gibt auch einzigartige, nicht kaufbare Items
     * @param derzeitigesLevel Level der Ruestung
     *                         {@link Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Ruestung erstelleRuestungFuer(Klasse klasse, int derzeitigesLevel) {
        Ruestung returnRuestung;
        if (klasse instanceof HLR) {
            returnRuestung = new LeichteRuestung(derzeitigesLevel, true);
        } else if (klasse instanceof MDD) {
            returnRuestung = new MittlereRuestung(derzeitigesLevel, true);
        } else if (klasse instanceof PDD) {
            returnRuestung = new SchwereRuestung(derzeitigesLevel, true);
        } else if (klasse instanceof TNK) {
            returnRuestung = new SehrSchwereRuestung(derzeitigesLevel, true);
        } else {
            System.err.println("AusruestungsgegenstandFabrik: Keine Ruestung fuer diese Klasse erstellbar! - null wird zurueckgegeben");
            returnRuestung = null;
        }
        return returnRuestung;
    }

    /**
     * Erstellt Ruestung fuer einen Haendler -> nur kaufbare Items
     *
     * @param haendler         Haenlder, fuer das eine Instanz der Klasse Ruestung erstellt werden soll.
     * @param derzeitigesLevel Level der Ruestung
     *                         {@link Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Ruestung erstelleRuestungFuer(Haendler haendler, int derzeitigesLevel) {
        Ruestung returnRuestung;
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
        return returnRuestung;
    }

    /**
     * Erstellt passende Ruestung fuer einen SpielCharakter -> SoeldnerItems möglich
     *
     * @param spielerCharakter SpielCharakter, fuer das eine Instanz der Klasse Ruestung erstellt werden soll.
     * @param derzeitigesLevel Level der Ruestung
     *                         {@link Ruestung }: Hier werden die Ruestungen für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Ruestung erstelleRuestungFuer(SpielerCharakter spielerCharakter, int derzeitigesLevel) {
        Ruestung returnRuestung;
        returnRuestung = erstelleRuestungFuer(spielerCharakter.getKlasse(), derzeitigesLevel);
        if (spielerCharakter.isSoeldner()) {
            returnRuestung.setIstSoeldnerItem(true);
        }
        return returnRuestung;
    }

    /**
     * Erstellt passende Waffe fuer die möglichen Klassen -> es gibt auch einzigartige, nicht kaufbare Items
     *
     * @param klasse:           Klasse, fuer die eine Instanz der Klasse Waffe erstellt werden soll.
     * @param derzeitigesLevel: Level der Waffe
     *                          {@link Waffe }: Hier werden die Waffe für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Waffe erstelleWaffeFuer(Klasse klasse, int derzeitigesLevel) {
        Waffe returnWaffe;
        int zufaelligeZahl = ZufallsZahlenGenerator.zufallsZahlIntAb0(2);
        if (klasse instanceof HLR) {
            switch (zufaelligeZahl) {
                case 0:
                    returnWaffe = new Heilerwaffe(derzeitigesLevel, true);
                    break;
                case 1:
                    returnWaffe = new Magierwaffe(derzeitigesLevel, true);
                    break;
                default:
                    returnWaffe = new Bogenwaffe(derzeitigesLevel, true);
                    break;
            }
        } else if (klasse instanceof MDD) {
            switch (zufaelligeZahl) {
                case 0:
                    returnWaffe = new Einhandwaffe(derzeitigesLevel, true);
                    break;
                case 1:
                    returnWaffe = new Magierwaffe(derzeitigesLevel, true);
                    break;
                default:
                    returnWaffe = new Bogenwaffe(derzeitigesLevel, true);
                    break;
            }
        } else if (klasse instanceof PDD) {
            switch (zufaelligeZahl) {
                case 0:
                    returnWaffe = new Einhandwaffe(derzeitigesLevel, true);
                    break;
                case 1:
                    returnWaffe = new Zweihandwaffe(derzeitigesLevel, true);
                    break;
                default:
                    returnWaffe = new Bogenwaffe(derzeitigesLevel, true);
                    break;
            }
        } else if (klasse instanceof TNK) {
            switch (zufaelligeZahl) {
                case 0:
                    returnWaffe = new Einhandwaffe(derzeitigesLevel, true);
                    break;
                case 1:
                    returnWaffe = new Zweihandwaffe(derzeitigesLevel, true);
                    break;
                default:
                    returnWaffe = new Schildwaffe(derzeitigesLevel, true);
                    break;
            }
        } else {
            System.err.println("AusruestungsgegenstandFabrik: Keine Waffe fuer diese Klasse erstellbar! - null wird zurueckgegeben");
            returnWaffe = null;
        }
        return returnWaffe;
    }

    /**
     * Erstellt Waffe fuer einen Haendler -> nur kaufbare Items
     *
     * @param haendler:         Haendler, fuer den eine Instanz der Klasse Waffe erstellt werden soll.
     * @param derzeitigesLevel: Level der Waffe
     *                          {@link Waffe }: Hier werden die Waffe für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     */
    public static Waffe erstelleWaffeFuer(Haendler haendler, int derzeitigesLevel) {
        Waffe returnWaffe;
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
            case 3:
                returnWaffe = new Bogenwaffe(derzeitigesLevel);
                break;
            case 4:
                returnWaffe = new Schildwaffe(derzeitigesLevel);
                break;
            default:
                returnWaffe = new Zweihandwaffe(derzeitigesLevel);
                break;
        }
        return returnWaffe;
    }

    /**
     * Erstellt passende Waffe fuer einen SpielCharakter -> SoeldnerItems möglich
     *
     * @param spielerCharakter SpielCharakter, fuer das eine Instanz der Klasse Waffe erstellt werden soll.
     * @param derzeitigesLevel Level der Waffe
     *                         {@link Waffe }: Hier werden die Waffe für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Waffe erstelleWaffeFuer(SpielerCharakter spielerCharakter, int derzeitigesLevel) {
        Waffe returnWaffe;
        returnWaffe = erstelleWaffeFuer(spielerCharakter.getKlasse(), derzeitigesLevel);
        if (spielerCharakter.isSoeldner()) {
            returnWaffe.setIstSoeldnerItem(true);
        }
        return returnWaffe;
    }

    /**
     * Erstellt passende Accessoires fuer die Klassen -> es gibt auch einzigartige, nicht kaufbare Items
     *
     * @param klasse            Klasse für den das Accessoire erstellt wird
     * @param derzeitigesLevel: Level der Accessoire
     *                          {@link Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
     *                          Accessoire bietet zwei Konstruktoren:
     *                          - kaufbare Accessoire fuer z.B. den Haendler: new Accessoire(Integer)
     *                          - nicht kaufbare Accessoire fuer z.B. Soeldner oder Gegner: new Accessoire(Integer, Boolean)
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Accessoire erstelleAccessoireFuer(Klasse klasse, int derzeitigesLevel) {
        return new Accessoire(derzeitigesLevel, true);
    }

    /**
     * Erstellt ein Accessoire fuer einen Haendler -> nur kaufbare Items
     *
     * @param haendler          Haendler für den das Accessoire erstellt wird
     * @param derzeitigesLevel: Level der Accessoire
     *                          {@link Accessoire }: Hier werden die Accessoire für spezifische Klassen erstellt
     *                          Accessoire bietet zwei Konstruktoren:
     *                          - kaufbare Accessoire fuer z.B. den Haendler: new Accessoire(Integer)
     *                          - nicht kaufbare Accessoire fuer z.B. Soeldner oder Gegner: new Accessoire(Integer, Boolean)
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Accessoire erstelleAccessoireFuer(Haendler haendler, int derzeitigesLevel) {
        return new Accessoire(derzeitigesLevel);
    }

    /**
     * Erstellt passende Accessoire fuer einen SpielCharakter -> SoeldnerItems möglich
     *
     * @param spielerCharakter SpielCharakter, fuer das eine Instanz der Klasse Accessoire erstellt werden soll.
     * @param derzeitigesLevel Level des Accessoire
     *                         {@link Accessoire }: Hier werden die Accessoires für spezifische Klassen erstellt
     * @author 11777914 OLt Oliver Ebert
     * @since 30.11.2023
     */
    public static Accessoire erstelleAccessoireFuer(SpielerCharakter spielerCharakter, int derzeitigesLevel) {
        Accessoire returnAccessoire;
        returnAccessoire = erstelleAccessoireFuer(spielerCharakter.getKlasse(), derzeitigesLevel);
        if (spielerCharakter.isSoeldner()) {
            returnAccessoire.setIstSoeldnerItem(true);
        }
        return returnAccessoire;
    }

    /**
     * Ersetzt einen Konstruktor fuer die Klasse Waffe
     *
     * @param name              : Name
     * @param kaufswert:        Kaufwert
     * @param verkaufswert:     Verkaufwert
     * @param istnichtKaufbar:  ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung: anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem:  legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param attacke:          grundwert der attacke fuer physischen Schaden
     * @param magischeAttacke:  grundwert der attacke fuer magischen Schaden
     * @param waffentyp         : Namen der Subklasse von Waffe
     * @return Eine Waffe mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Waffe waffeAusSpeicherstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                   int levelAnforderung, boolean istSoeldnerItem, int attacke, int magischeAttacke, String waffentyp) {
        Waffe returnWaffe;
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
     *
     * @param name                  : Name
     * @param kaufswert             : Kaufwert
     * @param verkaufswert          : Verkaufwert
     * @param istnichtKaufbar       : ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung      : anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem       : legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param magischeVerteidigung: magischeVerteidigung
     * @param verteidigung          : verteidigung
     * @param RuestungTyp           : Namen der Subklasse von Ruestung
     * @return Eine Ruestung mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Ruestung ruestungAusSpeicherstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                         int levelAnforderung, boolean istSoeldnerItem, int magischeVerteidigung, int verteidigung, String RuestungTyp) {
        Ruestung returnRuestung;
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
     *
     * @param name:                   Name
     * @param kaufswert:              Kaufwert
     * @param verkaufswert:           Verkaufwert
     * @param istnichtKaufbar:        ist der Gegenstand beim Haenlder erhaeltlich
     * @param levelAnforderung:       anforderung zum Tragen des Gegenstandes
     * @param istSoeldnerItem:        legt fest, ob der Gegenstand verschwindet, sobald im Inventar
     * @param maxGesundheitsPunkte    : Bonus fuer maxGesundheitsPunkte
     * @param maxManaPunkte           : Bonus fuer maxManaPunkte
     * @param gesundheitsRegeneration : Bonus fuer gesundheitsRegeneration
     * @param manaRegeneration:       Bonus fuer manaRegeneration
     * @param beweglichkeit           : Bonus fuer beweglichkeit
     * @return Ein Accessiore mit den gespeicherten Parametern
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static Accessoire accessoireAusSpielstandLaden(String name, int kaufswert, int verkaufswert, boolean istnichtKaufbar,
                                                          int levelAnforderung, boolean istSoeldnerItem, int maxGesundheitsPunkte,
                                                          int maxManaPunkte, int gesundheitsRegeneration, int manaRegeneration,
                                                          int beweglichkeit) {
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
