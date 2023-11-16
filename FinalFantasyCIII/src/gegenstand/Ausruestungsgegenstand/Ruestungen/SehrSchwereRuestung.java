package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class SehrSchwereRuestung extends Ruestung {

    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] sehrSchwereRuestungNamenArray = {"Stahlplattenruestung", "Chitinruestung", "Stachelpanzer der Verdammnis", "Froststahlruestung", "Lava geschmiedete Ruestung",
            "Goldpanzer des Drachenlords", "Diamantpanzer", "Obsidianruestung", "Mithrilruestung", "Daemonenstahlruestung"};

    public SehrSchwereRuestung(int stufe) {
        this.name = sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(5);
        this.mVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(1);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public SehrSchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(5);
        this.mVtg =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(1);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
