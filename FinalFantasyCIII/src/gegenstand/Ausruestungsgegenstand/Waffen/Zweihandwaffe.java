package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Zweihandwaffe extends Waffe {

    private String[] zweiHandWaffeNamenArray = {"Drachentoeter", "Loewentoeter", "Vulkanschwert", "Titanenschwert",
            "Vikingeraxt", "Anduril", "Narsil", "Goetterhammer", "Bluternter", "Frostgram", "Weltenspalter", "Schwert von Gryffindor", "Dicker Zweihandpruegel"};

    public Zweihandwaffe(int stufe) {
        this.setName(zweiHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(zweiHandWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public Zweihandwaffe(int stufe, boolean istNichtKaufbar) {
        this.setName(zweiHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(zweiHandWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }
}
