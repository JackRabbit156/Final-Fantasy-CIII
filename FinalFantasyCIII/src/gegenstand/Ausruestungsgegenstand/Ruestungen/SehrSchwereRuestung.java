package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

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
        this.name = sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5);
        this.mVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public SehrSchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = sehrSchwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(sehrSchwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg =  stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(5);
        this.mVtg =  stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(1);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
