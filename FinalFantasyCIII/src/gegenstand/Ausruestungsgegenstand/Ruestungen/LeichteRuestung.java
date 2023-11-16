package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

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
        this.name = leichteRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(leichteRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(2);
        this.mVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = leichteRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(leichteRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(2);
        this.mVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
