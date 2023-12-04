package trainer.faehigkeiten;

import charakter.model.klassen.spezialisierungen.Berserker;

import java.util.ArrayList;
import java.util.Arrays;

public class NeueFaehigkeiten {

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neuePDDFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse PDD
     * @see charakter.model.klassen.PDD
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neuePDDFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard physischer Angriff",
                        "Der Standardangriff dieser Klasse.",
                        "icons/Faehigkeiten/StandardAngriff.png",
                        0,
                        1,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "physisch"),
                //Flächenangriff
                new Faehigkeit("Physischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        "icons/Faehigkeiten/FlaechePhysisch.png",
                        20,
                        0,
                        1,
                        false,
                        110,
                        2,
                        1,
                        "gesundheitsPunkte",
                        "physisch"
                ),
                //Kritischer Schlag
                new Faehigkeit("Kritischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        "icons/Faehigkeiten/kritAngriff.png",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1.1,
                        "gesundheitsPunkte",
                        "physisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueMDDFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse MDD
     * @see charakter.model.klassen.MDD
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueMDDFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard magischer Angriff",
                        "Der Standardangriff dieser Klasse.",
                        "icons/Faehigkeiten/magischStandard.png",
                        0,
                        1,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                ),
                //Flächenangriff
                new Faehigkeit("Magischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        "icons/Faehigkeiten/flaecheMagisch.png",
                        20,
                        0,
                        1,
                        false,
                        110,
                        2,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                ),
                //Kritischer Schlag
                new Faehigkeit("Kritischer magischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        "icons/Faehigkeiten/kritMagisch.png",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1.1,
                        "gesundheitsPunkte",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueTNKFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse TNK
     * @see charakter.model.klassen.TNK
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueTNKFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard Verteidiger-Angriff",
                        "Der Standardangriff dieser Klasse.",
                        "icons/Faehigkeiten/StandardAngriff.png",
                        0,
                        1,
                        1,
                        false,
                        80,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "physisch"
                ),
                //Schild
                new Faehigkeit("Schild",
                        "Erhoeht eigene Lebenspunkte.",
                        "icons/Faehigkeiten/TNKSchild.png",
                        20,
                        0,
                        1,
                        true,
                        130,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "physisch"
                ),
                //Motivation
                new Faehigkeit("Motivation",
                        "Erhoeht das Mana eine Teammitglieds",
                        "icons/Faehigkeiten/TNKMana.png",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "manaPunkte",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueHLRFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse HLR
     * @see charakter.model.klassen.HLR
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueHLRFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard Angriff
                new Faehigkeit("Standard magischer Angriff",
                        "Ein schwacher Standardangriff dieser Klasse.",
                        "icons/Faehigkeiten/magischStandard.png",
                        0,
                        1,
                        1,
                        false,
                        60,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                ),
                //Standard Heilung
                new Faehigkeit("Standard Heilung",
                        "Der Standardheilung dieser Klasse.",
                        "icons/Faehigkeiten/HLRStandard.png",
                        0,
                        1,
                        1,
                        true,
                        100,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                ),
                //Flächenangriff
                new Faehigkeit("Flaechenheilung",
                        "Kann mehrere Teammitglieder auswaehlen und heilen.",
                        "icons/Faehigkeiten/FlaechenHeilung.png",
                        20,
                        0,
                        1,
                        true,
                        120,
                        2,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                ),
                //Kritischer Schlag
                new Faehigkeit("Einschuechterung",
                        "Schwaecht einen Gegner und reduziert dessen Verteidigung & magische Verteidigung",
                        "icons/Faehigkeiten/HLREinschuechterung.png",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "abwehr",
                        "magisch")
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueBerserkerFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Berserker
     * @see Berserker
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueBerserkerFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Berserker Spezial
                new Faehigkeit("Roter Nebel",
                        "Spezial von Berserker: Greift einen Gegner mit grossem Schaden an - verliert jedoch selber Lebenspunkte.",
                        "icons/Faehigkeiten/BerserkerSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        "berserkerSpezial",
                        "physisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueSchurkeFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Schurke
     * @see charakter.model.klassen.spezialisierungen.Schurke
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueSchurkeFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Schurke Spezial
                new Faehigkeit("Finte",
                        "Spezial von Schurke: Ein zufaelliger Gegner greift einen anderen Gegner mit einem seiner Faehigkeiten an.",
                        "icons/Faehigkeiten/SchurkeSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "schurkeSpezial",
                        "physisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueFeuermagierFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Feuermagier
     * @see charakter.model.klassen.spezialisierungen.Feuermagier
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueFeuermagierFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Feuermagier Spezial
                new Faehigkeit("Feuersturm",
                        "Spezial von Feuermagier: grossse Feuerball, Junge!",
                        "icons/Faehigkeiten/FeuermagierSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        "gesundheitsPunkte",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueEismagierFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Eismagier
     * @see charakter.model.klassen.spezialisierungen.Eismagier
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueEismagierFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Eismagier Spezial
                new Faehigkeit("Eissturm",
                        "Spezial von Eismagier: Ein Gegner muss im naechstem Zug aussetzen.",
                        "icons/Faehigkeiten/EismagierSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "eismagierSpezial",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueRabaukeFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Rabauke
     * @see charakter.model.klassen.spezialisierungen.Rabauke
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueRabaukeFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Rabauke Spezial
                new Faehigkeit("Ausweichen",
                        "Spezial von Rabauke: Er weicht allen Schlaegen eine Runde aus.",
                        "icons/Faehigkeiten/RabaukeSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "rabaukeSpezial",
                        "physisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neuePaladinFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Paladin
     * @see charakter.model.klassen.spezialisierungen.Paladin
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neuePaladinFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Paladin Spezial
                new Faehigkeit("Fels in der Brandung",
                        "Spezial von Paladin: Erhoeht die Max-Gesundheitspunkte und heilt sich auf 100% Gesundheitspunkte.",
                        "icons/Faehigkeiten/PaladinSpezial.png",
                        50,
                        1,
                        10,
                        true,
                        200,
                        1,
                        1,
                        "paladinSpezial",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neuePriesterFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Priester
     * @see charakter.model.klassen.spezialisierungen.Priester
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neuePriesterFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Priester Spezial
                new Faehigkeit("Sonnenaura!!!",
                        "Spezial von Priester: Erhoeht alle Statuswerte des Teams",
                        "icons/Faehigkeiten/PriesterSpezial.png",
                        50,
                        1,
                        10,
                        true,
                        0,
                        1,
                        1,
                        "priesterSpezial",
                        "magisch"
                )
        ));
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * neueSanmausFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Sanmaus
     * @see charakter.model.klassen.spezialisierungen.Sanmaus
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static ArrayList<Faehigkeit> neueSanmausFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //SanMaus Spezial
                new Faehigkeit("Neukrank!",
                        "Spezial von Sanmaus: Teammitglied wird wiederbelebt.",
                        "icons/Faehigkeiten/SanmausSpezial.png",
                        50,
                        1,
                        10,
                        true,
                        100,
                        1,
                        1,
                        "sanmausSpezial",
                        "magisch"
                )
        ));
    }
}
