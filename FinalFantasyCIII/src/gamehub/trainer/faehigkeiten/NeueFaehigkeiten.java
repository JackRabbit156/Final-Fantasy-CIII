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
                        10,
                        1,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"),
                //Flächenangriff
                new Faehigkeit("physischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        20,
                        0,
                        1,
                        false,
                        10,
                        2,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("kritischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        15,
                        0,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1.1,
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
                        10,
                        1,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Flächenangriff
                new Faehigkeit("magischer Flaechenangriff",
                        "Kann mehrere Gegner anvisieren und angreifen.",
                        20,
                        0,
                        1,
                        false,
                        10,
                        2,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("kritischer magischer Schlag",
                        "Visiert einen Gegner an und schlaegt mit erhoehter Wahrscheinlichkeit auf einen kritischen Treffer zu.",
                        15,
                        0,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1.1,
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
                        10,
                        1,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Schild
                new Faehigkeit("Schild",
                        "Erhoeht eigene Lebenspunkte.",
                        20,
                        0,
                        1,
                        true,
                        20,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Motivation
                new Faehigkeit("Motivation",
                        "Erhoeht das Mana eine Teammitglieds",
                        15,
                        0,
                        1,
                        false,
                        0,
                        1,
                        1,
                        1.1,
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
                        10,
                        1,
                        1,
                        true,
                        0,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Flächenangriff
                new Faehigkeit("Flaechenheilung",
                        "Kann mehrere Teammitglieder auswaehlen und heilen.",
                        20,
                        0,
                        1,
                        true,
                        15,
                        2,
                        1,
                        1,
                        "gesundheitsPunkte"
                ),
                //Kritischer Schlag
                new Faehigkeit("Einschuechterung",
                        "Schwaecht einen Gegner und fuegt diese Gesundheitspunkte einem zufaelligen Mitspieler hinzu.",
                        15,
                        0,
                        1,
                        false,
                        10,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte")
                //TODO: weitere Skills HLR implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueBerserkerFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Berserker Spezial
                new Faehigkeit("Roter Nebel",
                        "Der Spezialangriff dieser Klasse: Greift einen zufälligen Gegner mit großem Schaden an - verliert jedoch selber Lebenspunkte.",
                        50,
                        1,
                        10,
                        false,
                        50,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                )

                //TODO: weitere Skills Berserker implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueSchurkeFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Schurke Spezial
                new Faehigkeit("Finte",
                        "Der Spezialangriff dieser Klasse: Ein zufaelliger Gegner greift einen anderen Gegner mit einem seiner Faehigkeiten an.",
                        50,
                        1,
                        10,
                        false,
                        1,
                        1,
                        1,
                        1,
                        "werIstDranAttribut"
                )
                //TODO: weitere Skills Schurke implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueFeuermagierFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Feuermagier Spezial
                new Faehigkeit("Feuersturm",
                        "Der Spezialangriff dieser Klasse: grossse Feuerball, Junge!",
                        50,
                        1,
                        10,
                        false,
                        40,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills Feuermagier implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueEismagierFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Eismagier Spezial
                new Faehigkeit("Eissturm",
                        "Der Spezialangriff dieser Klasse: Ein Gegner muss im naechstem Zug aussetzen.",
                        50,
                        1,
                        10,
                        false,
                        0,
                        1,
                        1,
                        1,
                        "werIstDranAttribut"
                )
                //TODO: weitere Skills Eismagier implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueRabaukeFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Rabauke Spezial
                new Faehigkeit("Ausweichen",
                        "Der Spezialangriff dieser Klasse: Der erste Schlag des Gegners verfehlt das Ziel.",
                        50,
                        1,
                        10,
                        false,
                        0,
                        1,
                        1,
                        1,
                        "werIstDranAttribut"
                )
                //TODO: weitere Skills Rabauke implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neuePaladinFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Paladin Spezial
                new Faehigkeit("Fels in der Brandung",
                        "Der Spezialangriff dieser Klasse: Erhoeht die Gesundheitspunkte um einen Brocken.",
                        50,
                        1,
                        10,
                        true,
                        80,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills Paladin implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neuePriesterFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //Priester Spezial
                new Faehigkeit("Bekehren",
                        "Der Spezialangriff dieser Klasse: Ein Gegner läuft zum eigenen Team über - wenn Platz ist",
                        50,
                        1,
                        10,
                        true,
                        0,
                        1,
                        1,
                        1,
                        "TeamZugehoerigkeit"
                )
                //TODO: weitere Skills Priester implementieren
        ));
    }

    public static ArrayList<Faehigkeit> neueSanmausFaehigkeit() {
        return new ArrayList<>(Arrays.asList(
                //SanMaus Spezial
                new Faehigkeit("Neukrank!",
                        "Der Spezialangriff dieser Klasse: Teammitglied wird wiederbelebt.",
                        50,
                        1,
                        10,
                        true,
                        0,
                        1,
                        1,
                        1,
                        "gesundheitsPunkte"
                )
                //TODO: weitere Skills SanMaus implementieren
        ));
    }
}
