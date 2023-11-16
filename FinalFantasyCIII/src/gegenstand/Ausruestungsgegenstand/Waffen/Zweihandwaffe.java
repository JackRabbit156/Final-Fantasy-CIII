package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Zweihandwaffe extends Waffe {

    String[] zweiHandWaffeNamenArray = {"Drachentoeter", "Loewentoeter", "Rubinhalskette", "Vulkanschwert", "Titanenschwert",
            "Vikingeraxt", "Anduril", "Narsil", "Goetterhammer", "Bluternter" + "Frostgram", "Weltenspalter", "Schwert von Gryffindor"};

    public Zweihandwaffe(int stufe) {
        this.setName(zweiHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(zweiHandWaffeNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmAtk(0);
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public Zweihandwaffe(int stufe, boolean istNichtKaufbar) {
        this.setName(zweiHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(zweiHandWaffeNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmAtk(0);
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }
}
