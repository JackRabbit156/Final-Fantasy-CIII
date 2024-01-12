package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class Schild extends Waffe{

    private static final String[] NAMEN = { "Teufelsblocker", "Lavaschmiedebollwerk", "Drachenherzflammenschild",
            "Schwarzschädelschild", "Schildkrötenpanzer", "Quantenschild", "Obsidianschild", "Tryptychonschild",
            "Phasenverschobenes Bollwerk", "Hornhaut des Hauptgefreiten", "Schild des Kristalldrachens", "Schildi",
            "Schild der Assimilierung" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public Schild(int stufe) {
        this.setIcon("icons/schildWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(1));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(7));
        this.setLevelAnforderung(ZufallsZahlenGenerator.gegenstandsstufeFuerHaendler(stufe));
        this.setIstSoeldnerItem(false);
    }

    /**
     * Konstruktor für Söldner/Gegner und Loot
     *
     * @param stufe wird genau auf dieser Levelstufe erstellt
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Stetter
     * @since 06.12.23
     */
    public Schild(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/schildWaffe.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setMagischeAttacke(0);
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(1));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(7));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
