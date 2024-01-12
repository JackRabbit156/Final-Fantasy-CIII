package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public class LeichteRuestung extends Ruestung {

    private static final String[] NAMEN = { "Des Kaisers neue Robe", "Robe des blinden Sehers", "Merlin's Robe",
            "Morgenmantel der Müdigkeit", "Blauer Blitz", "Stoffrobe", "Lumineszenzrobe", "Robe des Dämonenfürsten",
            "Robe des dunklen Lords", "Robe des Gerechten" };

    /**
     * Konstruktor für Händler
     *
     * @param stufe diese LevelStufe +/- 2
     * @author OF Stetter
     * @since 06.12.23
     */
    public LeichteRuestung(int stufe) {
        this.setIcon("icons/leichteRuestung.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(6));
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
    public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/leichteRuestung.png");
        this.setName(NAMEN[ZufallsZahlenGenerator.zufallsZahlAb0(NAMEN.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(2));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(6));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }

}
