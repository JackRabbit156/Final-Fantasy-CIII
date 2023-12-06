package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Schildwaffe extends Waffe{

    private String[] schildwaffeNamenArray = {"Teufelsblocker", "Lavaschmiedebollwerk", "Drachenherzflammenschild", "Schwarzschädelschild",
            "Schildkrötenpanzer", "Quantenschild", "Obsidianschild", "Tryptychonschild", "Phasenverschobenes Bollwerk", "Hornhaut des Hauptgefreiten", "Schild des Kristalldrachens", "Schildi", "Schild der Assimilierung"};
    /**
     * Konstruktor für Händler
     *
     * @param stufe -
     * @author OF Stetter
     * @since 06.12.23
     */
    public Schildwaffe(int stufe) {
        this.setIcon("icons/schildWaffe.png");
        this.setName(schildwaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schildwaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(1));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(7));
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
     */public Schildwaffe(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/schildWaffe.png");
        this.setName(schildwaffeNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schildwaffeNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(1));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(7));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
