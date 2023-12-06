package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Einhandwaffe extends Waffe {

    private String[] einHandWaffeNamenArray = {"Teufelsklinge", "Dreschflegel", "Ebenerzschwert", "Goldschwert", "Morgenstern",
            "Bastardschwert", "Klinge der Reinheit", "Dämonenklinge", "Stich", "Buttermesser des Grauens", "Kriegsgleve von Azzinoth", "Mjölnir", "Nadel"};
    /**
     * Konstruktor für Händler
     *
     * @param stufe -
     * @author OF Stetter
     * @since 06.12.23
     */
    public Einhandwaffe(int stufe) {
        this.setIcon("icons/einhandWaffe.png");
        this.setName(einHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(einHandWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    }
    /**
     * Konstruktor für Söldner/Gegner & Loot
     *
     * @param stufe           -
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Stetter
     * @since 06.12.23
     */public Einhandwaffe(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/einhandWaffe.png");
        this.setName(einHandWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(einHandWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
