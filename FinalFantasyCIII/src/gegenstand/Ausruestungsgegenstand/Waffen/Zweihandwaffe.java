package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Zweihandwaffe extends Waffe {

    private String name;
    private int pAtk;
    private int mAtk;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] zweiHandWaffeNamenArray = {"Drachentoeter", "Loewentoeter", "Rubinhalskette", "Vulkanschwert", "Titanenschwert",
            "Vikingeraxt", "Anduril", "Narsil", "Goetterhammer", "Bluternter" +
//         seltene Objekte
            "Frostgram",//Mana
            "Weltenspalter",//HP
            "Schwert von Gryffindor"};//beweglichkeit

    public Zweihandwaffe(int stufe) {
        this.name = zweiHandWaffeNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(zweiHandWaffeNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pAtk = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.mAtk = 0;
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public Zweihandwaffe(int stufe, boolean istNichtKaufbar) {
        this.name = zweiHandWaffeNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(zweiHandWaffeNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pAtk =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.mAtk =  0;
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }
}
