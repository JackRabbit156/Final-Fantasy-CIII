package gamehub.trainer.faehigkeiten;

import java.util.ArrayList;
import java.util.Arrays;

//TODO: JAVA DOC ERGAENZEN!
public class NeueFaehigkeiten {

    public static ArrayList<Faehigkeit> neuePDDFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard physischer Angriff",
                        "Der Standardangriff dieser Klasse.",
                        0,
                        1,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "physisch",
                        "gesundheitsPunkte"),
                //Flächenangriff
                new Faehigkeit("physischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        20,
                        0,
                        1,
                        false,
                        110,
                        2,
                        1,
                        "physisch",
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("kritischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1.1,
                        "physisch",
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills PDD implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueMDDFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard magischer Angriff",
                        "Der Standardangriff dieser Klasse.",
                        0,
                        1,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "magisch",
                        "gesundheitsPunkte"
                ),
                //Flächenangriff
                new Faehigkeit("magischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        20,
                        0,
                        1,
                        false,
                        110,
                        2,
                        1,
                        "magisch",
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("kritischer magischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1.1,
                        "magisch",
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills MDD implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueTNKFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard Verteidiger-Angriff",
                        "Der Standardangriff dieser Klasse.",
                        0,
                        1,
                        1,
                        false,
                        80,
                        1,
                        1,
                        "physisch",
                        "gesundheitsPunkte"
                ),
                //Schild
                new Faehigkeit("Schild",
                        "Erhoeht eigene Lebenspunkte.",
                        20,
                        0,
                        1,
                        true,
                        130,
                        1,
                        1,
                        "physisch",
                        "gesundheitsPunkte"
                ),
                //Motivation
                new Faehigkeit("Motivation",
                        "Erhoeht das Mana eine Teammitglieds",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "magisch",
                        "manaPunkte"
                )
                //TODO: weitere Skills PDD implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueHLRFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Standard
                new Faehigkeit("Standard Heilung",
                        "Der Standardheilung dieser Klasse.",
                        0,
                        1,
                        1,
                        true,
                        100,
                        1,
                        1,
                        "magisch",
                        "gesundheitsPunkte"
                ),
                //Flächenangriff
                new Faehigkeit("Flaechenheilung",
                        "Kann mehrere Teammitglieder auswaehlen und heilen.",
                        20,
                        0,
                        1,
                        true,
                        120,
                        2,
                        1,
                        "magisch",
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("Einschuechterung",
                        "Schwaecht einen Gegner und reduziert dessen Verteidigung & magische Verteidigung",
                        15,
                        0,
                        1,
                        false,
                        100,
                        1,
                        1,
                        "magisch",
                        "abwehr")
                //TODO: weitere Skills HLR implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueBerserkerFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Berserker Spezial
                new Faehigkeit("Roter Nebel",
                        "Spezial von Berserker: Greift einen Gegner mit großem Schaden an - verliert jedoch selber Lebenspunkte.",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        "physisch",
                        "berserkerSpezial"
                )

                //TODO: weitere Skills Berserker implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueSchurkeFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Schurke Spezial
                new Faehigkeit("Finte",
                        "Spezial von Schurke: Ein zufaelliger Gegner greift einen anderen Gegner mit einem seiner Faehigkeiten an.",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "physisch",
                        "schurkeSpezial"
                )
                //TODO: weitere Skills Schurke implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueFeuermagierFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Feuermagier Spezial
                new Faehigkeit("Feuersturm",
                        "Spezial von Feuermagier: grossse Feuerball, Junge!",
                        50,
                        1,
                        10,
                        false,
                        400,
                        1,
                        1,
                        "magisch",
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills Feuermagier implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueEismagierFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Eismagier Spezial
                new Faehigkeit("Eissturm",
                        "Spezial von Eismagier: Ein Gegner muss im naechstem Zug aussetzen.",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "magisch",
                        "eismagierSpezial"
                )
                //TODO: weitere Skills Eismagier implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueRabaukeFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Rabauke Spezial
                new Faehigkeit("Ausweichen",
                        "Spezial von Rabauke: Er weicht allen Schlägen eine Runde aus.",
                        50,
                        1,
                        10,
                        false,
                        100,
                        1,
                        1,
                        "physisch",
                        "rabaukeSpezial"
                )
                //TODO: weitere Skills Rabauke implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neuePaladinFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Paladin Spezial
                new Faehigkeit("Fels in der Brandung",
                        "Spezial von Paladin: Erhoeht die Max-Gesundheitspunkte und heilt sich auf 100% Gesundheitspunkte.",
                        50,
                        1,
                        10,
                        true,
                        200,
                        1,
                        1,
                        "magisch",
                        "paladinSpezial"
                )
                //TODO: weitere Skills Paladin implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neuePriesterFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //Priester Spezial
                new Faehigkeit("Sonnenaura!!!",
                        "Spezial von Priester: Erhoeht alle Statuswerte des Teams",
                        50,
                        1,
                        10,
                        true,
                        0,
                        1,
                        1,
                        "magisch",
                        "priesterSpezial"
                )
                //TODO: weitere Skills Priester implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueSanmausFaehigkeiten() {
        return new ArrayList<>(Arrays.asList(
                //SanMaus Spezial
                new Faehigkeit("Neukrank!",
                        "Spezial von Sanmaus: Teammitglied wird wiederbelebt.",
                        50,
                        1,
                        10,
                        true,
                        100,
                        1,
                        1,
                        "magisch",
                        "sanmausSpezial"
                )
                //TODO: weitere Skills SanMaus implementieren
        ));
    }
}
