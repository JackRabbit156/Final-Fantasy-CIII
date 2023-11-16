package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

public class LeichteRuestung extends Ruestung {

    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] leichteRuestungNamenArray = {"Des Kaisers neue Robe", "Robe des blinden Sehers", "Merlins Robe", "Morgenmantel der Muedigkeit", "Blauer Blitz",
            "Stoffrobe", "Lumineszenzrobe", "Robe des Daemonenfuersten", "Robe des dunklen Lords", "Robe des Gerechten"};

    public LeichteRuestung(int stufe) {
        this.name = leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2);
        this.mVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2);
        this.mVtg = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
