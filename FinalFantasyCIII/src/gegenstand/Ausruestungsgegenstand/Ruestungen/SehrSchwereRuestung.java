package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class SehrSchwereRuestung extends Ruestung {

    private String[] sehrSchwereRuestungNamenArray = {"Stahlplattenruestung", "Chitinruestung", "Stachelpanzer der Verdammnis", "Froststahlruestung", "Lava geschmiedete Ruestung",
            "Goldpanzer des Drachenlords", "Diamantpanzer", "Obsidianruestung", "Mithrilruestung", "Daemonenstahlruestung"};

    public SehrSchwereRuestung(int stufe) {
        this.setName(sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public SehrSchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setName(sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }

}
