package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class LeichteRuestung extends Ruestung {

    String[] leichteRuestungNamenArray = {"Des Kaisers neue Robe", "Robe des blinden Sehers", "Merlins Robe", "Morgenmantel der Muedigkeit", "Blauer Blitz",
            "Stoffrobe", "Lumineszenzrobe", "Robe des Daemonenfuersten", "Robe des dunklen Lords", "Robe des Gerechten"};

    public LeichteRuestung(int stufe) {
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }
}
