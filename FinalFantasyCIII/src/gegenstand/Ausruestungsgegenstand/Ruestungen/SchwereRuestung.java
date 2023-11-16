package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

public class SchwereRuestung extends Ruestung {

    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] schwereRuestungNamenArray = {"Drachenschuppenruestung", "Knochenruestung", "Eisenruestung", "Ebenerzruestung", "Wolfsruestung",
            "Zwergenstahlruestung", "Kristallruestung", "Eisenruestung", "Ruestung des schwarzen Ritters", "Orkruestung"};

    public SchwereRuestung(int stufe) {
        this.name = schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.mVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public SchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg =  stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.mVtg =  stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }
}
