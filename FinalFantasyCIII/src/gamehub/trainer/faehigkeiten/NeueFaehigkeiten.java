package gamehub.trainer.faehigkeiten;

public class NeueFaehigkeiten {

    public static Faehigkeit[] neuePDDFaehigkeiten() {
        return new Faehigkeit[]{
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
        };
    }

    public static Faehigkeit[] neueMDDFaehigkeiten() {
        return new Faehigkeit[]{
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
        };
    }

    public static Faehigkeit[] neueTNKFaehigkeiten() {
        return new Faehigkeit[]{
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
        };
    }

    public static Faehigkeit[] neueHLRFaehigkeiten() {
        return new Faehigkeit[]{
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
        };
    }
}
