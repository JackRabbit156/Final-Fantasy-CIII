package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class LeichteRuestung extends Ruestung {

    private String[] leichteRuestungNamenArray = {"Des Kaisers neue Robe", "Robe des blinden Sehers", "Merlin's Robe", "Morgenmantel der Müdigkeit", "Blauer Blitz",
            "Stoffrobe", "Lumineszenzrobe", "Robe des Dämonenfürsten", "Robe des dunklen Lords", "Robe des Gerechten"};
    /**
     * Konstruktor für Händler
     *
     * @param stufe -
     * @author OF Stetter
     * @since 06.12.23
     */
    public LeichteRuestung(int stufe) {
        this.setIcon("icons/leichteRuestung.png");
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
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
     */public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/leichteRuestung.png");
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
    }
}
