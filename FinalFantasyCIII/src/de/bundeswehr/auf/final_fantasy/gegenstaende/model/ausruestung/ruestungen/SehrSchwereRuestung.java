package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class SehrSchwereRuestung extends Ruestung {

    private final static String[] NAMEN = { "Stahlplattenrüstung", "Chitinrüstung", "Stachelpanzer der Verdammnis",
            "Froststahlrüstung", "Lava geschmiedete Rüstung", "Goldpanzer des Drachenlords", "Diamantpanzer",
            "Obsidianrüstung", "Mithrilrüstung", "Dämonenstahlrüstung" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public SehrSchwereRuestung(int stufe) {
        this.setIcon("icons/sehrSchwereRuestung.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(1));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(6));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2));
        this.setLevelAnforderung(ZufallsZahlenGenerator.gegenstandsstufeFuerHaendler(stufe));
        this.setIstSoeldnerItem(false);
    }

    /**
     * Konstruktor für Söldner/Gegner und Loot
     *
     * @param stufe  wird genau auf dieser Levelstufe erstellt
     * @param istNichtKaufbar zusätzlicher Parameter um Konstruktor zu unterscheiden
     * @author OF Stetter
     * @since 06.12.23
     */
    public SehrSchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/sehrSchwereRuestung.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(1));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(6));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
