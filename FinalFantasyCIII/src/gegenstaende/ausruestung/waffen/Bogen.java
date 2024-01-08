package gegenstaende.ausruestung.waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Bogen extends Waffe{

    private String[] bogenWaffeNamenArray = {"Lebendiger Langbogen", "Phönixbogen des Sonnenzorns", "Quantenbogen", "Rae'shalare", "Voodoojagdbogen",
            "Dämonenknochenbogen", "Armor's Bogen", "Golemherzbogen", "Belthronding", "Schattenbogen", "Witwenmacher", "Vulkanglasbogen", "Ellen Bogen"};
    /**
     * Konstruktor für Händler
     *
     * @param stufe -
     * @author OF Stetter
     * @since 06.12.23
     */
    public Bogen(int stufe) {
        this.setIcon("icons/bogenWaffe.png");
        this.setName(bogenWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(bogenWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    }
    /**
     * Konstruktor für Söldner/Gegner und Loot
     *
     * @param stufe           -
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Stetter
     * @since 06.12.23
     */public Bogen(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/bogenWaffe.png");
        this.setName(bogenWaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(bogenWaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
