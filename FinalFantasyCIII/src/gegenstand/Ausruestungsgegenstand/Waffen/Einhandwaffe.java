package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Einhandwaffe extends Waffe {

    private String name;
    private int pAtk;
    private int mAtk;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] einHandWaffeNamenArray = {"Teufelsklinge", "Dreschflegel", "Ebenerzschwert", "Goldschwert", "Morgenstern",
            "Bastardschwert", "Klinge der Reinheit", "Daemonenklinge", "Stich", "Buttermesser des Grauens" +
//         seltene Objekte
            "Kriegsgleve von Azzinoth",//Mana
            "Mjoelnir",//HP
            "Nadel"};//beweglichkeit

    public Einhandwaffe(int stufe) {
        this.name = einHandWaffeNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(einHandWaffeNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pAtk = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.mAtk = 0;
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public Einhandwaffe(int stufe, boolean istNichtKaufbar) {
        this.name = einHandWaffeNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(einHandWaffeNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pAtk =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.mAtk =  0;
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
