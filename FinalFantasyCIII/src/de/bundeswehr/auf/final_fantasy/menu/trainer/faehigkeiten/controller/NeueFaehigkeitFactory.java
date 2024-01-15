package de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Berserker;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Attribute;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.SpezialFaehigkeiten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class NeueFaehigkeitFactory {

    private NeueFaehigkeitFactory() {
    }

    /**
     * neuePDDFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse PDD
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neuePDDFaehigkeiten() {
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
                        Attribute.GP,
                        Faehigkeit.TYP_PHYSISCH),
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
                        Attribute.GP,
                        Faehigkeit.TYP_PHYSISCH
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
                        Attribute.GP,
                        Faehigkeit.TYP_PHYSISCH
                )
        ));
    }

    /**
     * neueMDDFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse MDD
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueMDDFaehigkeiten() {
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
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
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
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
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
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neueTNKFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse TNK
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueTNKFaehigkeiten() {
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
                        Attribute.GP,
                        Faehigkeit.TYP_PHYSISCH
                ),
                //Schild
                new Faehigkeit("Schild",
                        "Mittelstarke Heilung auf ein Teammitglied.",
                        "icons/Faehigkeiten/TNKSchild.png",
                        20,
                        0,
                        1,
                        true,
                        140,
                        1,
                        1,
                        Attribute.GP,
                        Faehigkeit.TYP_PHYSISCH
                ),
                //Motivation
                new Faehigkeit("Motivation",
                        "Erhoeht das Mana eines Teammitglieds",
                        "icons/Faehigkeiten/TNKMana.png",
                        15,
                        0,
                        1,
                        true,
                        100,
                        1,
                        1,
                        Attribute.MP,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neueHLRFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse HLR
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueHLRFaehigkeiten() {
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
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
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
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
                ),
                //Flächenangriff
                new Faehigkeit("Flächenheilung",
                        "Kann mehrere Teammitglieder auswaehlen und heilen.",
                        "icons/Faehigkeiten/FlaechenHeilung.png",
                        20,
                        0,
                        1,
                        true,
                        120,
                        2,
                        1,
                        Attribute.GP,
                        Faehigkeit.TYP_MAGISCH
                ),
                //Kritischer Schlag
                new Faehigkeit("Einschüchterung",
                        "Schwächt einen Gegner und reduziert dessen Verteidigung & magische Verteidigung",
                        "icons/Faehigkeiten/HLREinschuechterung.png",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1,
                        Attribute.ABW,
                        Faehigkeit.TYP_MAGISCH)
        ));
    }

    /**
     * neueBerserkerFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Berserker
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see Berserker
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueBerserkerFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Berserker Spezial
                new Faehigkeit("Roter Nebel",
                        "Spezial von Berserker: Greift einen Gegner mit großem Schaden an - verliert jedoch selber Lebenspunkte.",
                        "icons/Faehigkeiten/BerserkerSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        SpezialFaehigkeiten.BERSERKER,
                        Faehigkeit.TYP_PHYSISCH
                )
        ));
    }

    /**
     * neueSchurkeFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Schurke
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Schurke
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueSchurkeFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Schurke Spezial
                new Faehigkeit("Finte",
                        "Spezial von Schurke: Ein zufälliger Gegner greift einen anderen Gegner mit einem seiner Fähigkeiten an.",
                        "icons/Faehigkeiten/SchurkeSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        SpezialFaehigkeiten.SCHURKE,
                        Faehigkeit.TYP_PHYSISCH
                )
        ));
    }

    /**
     * neueFeuermagierFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Feuermagier
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Feuermagier
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueFeuermagierFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Feuermagier Spezial
                new Faehigkeit("Feuersturm",
                        "Spezial von Feuermagier: Großer Feuerball, Junge!",
                        "icons/Faehigkeiten/FeuermagierSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        SpezialFaehigkeiten.FEUERMAGIER,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neueEismagierFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Eismagier
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Eismagier
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueEismagierFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Eismagier Spezial
                new Faehigkeit("Eissturm",
                        "Spezial von Eismagier: Ein Gegner muss im nächstem Zug aussetzen.",
                        "icons/Faehigkeiten/EismagierSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        SpezialFaehigkeiten.EISMAGIER,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neueRabaukeFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Rabauke
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Rabauke
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueRabaukeFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Rabauke Spezial
                new Faehigkeit("Ausweichen",
                        "Spezial von Rabauke: Er weicht allen Schlägen eine Runde aus.",
                        "icons/Faehigkeiten/RabaukeSpezial.png",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        SpezialFaehigkeiten.RABAUKE,
                        Faehigkeit.TYP_PHYSISCH
                )
        ));
    }

    /**
     * neuePaladinFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Paladin
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Paladin
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neuePaladinFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Paladin Spezial
                new Faehigkeit("Fels in der Brandung",
                        "Spezial von Paladin: Erhöht die Max-Gesundheitspunkte und heilt sich auf 100% Gesundheitspunkte.",
                        "icons/Faehigkeiten/PaladinSpezial.png",
                        50,
                        1,
                        10,
                        true,
                        200,
                        1,
                        1,
                        SpezialFaehigkeiten.PALADIN,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neuePriesterFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Priester
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Priester
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neuePriesterFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
                //Priester Spezial
                new Faehigkeit("Sonnenaura!!!",
                        "Spezial von Priester: Erhöht alle Statuswerte des Teams",
                        "icons/Faehigkeiten/PriesterSpezial.png",
                        50,
                        1,
                        10,
                        true,
                        0,
                        1,
                        1,
                        SpezialFaehigkeiten.PRIESTER,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

    /**
     * neueSanmausFaehigkeiten gibt eine neue Liste von Faehigkeit zurueck fuer die Klasse Sanmaus
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     * @see de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.Sanmaus
     * @return ArrayList(Faehigkeit) : Gibt eine ArrayList von Faehigkeit zurueck.
     */
    public static List<Faehigkeit> neueSanmausFaehigkeiten() {
        return new ArrayList<>(Collections.singletonList(
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
                        SpezialFaehigkeiten.SANMAUS,
                        Faehigkeit.TYP_MAGISCH
                )
        ));
    }

}
