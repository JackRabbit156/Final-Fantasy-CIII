package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class SehrSchwereRuestung extends Ruestung {

    private String[] sehrSchwereRuestungNamenArray = {"Stahlplattenrüstung", "Chitinrüstung", "Stachelpanzer der Verdammnis", "Froststahlrüstung", "Lava geschmiedete Rüstung",
            "Goldpanzer des Drachenlords", "Diamantpanzer", "Obsidianrüstung", "Mithrilrüstung", "Dämonenstahlrüstung"};

    public SehrSchwereRuestung(int stufe) {
        this.setIcon("icons/sehrSchwereRuestung.png");
        this.setName(sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public SehrSchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/sehrSchwereRuestung.png");
        this.setName(sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(6));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(2));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }

}
