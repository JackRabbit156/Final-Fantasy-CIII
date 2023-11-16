package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Einhandwaffe extends Waffe {

    String[] einHandWaffeNamenArray = {"Teufelsklinge", "Dreschflegel", "Ebenerzschwert", "Goldschwert", "Morgenstern",
            "Bastardschwert", "Klinge der Reinheit", "Daemonenklinge", "Stich", "Buttermesser des Grauens" + "Kriegsgleve von Azzinoth", "Mjoelnir", "Nadel"};

    public Einhandwaffe(int stufe) {
        this.setName(einHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(einHandWaffeNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmAtk(0);
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public Einhandwaffe(int stufe, boolean istNichtKaufbar) {
        this.setName(einHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(einHandWaffeNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmAtk(0);
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }

}
