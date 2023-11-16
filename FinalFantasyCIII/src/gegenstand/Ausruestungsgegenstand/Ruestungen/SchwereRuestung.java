package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class SchwereRuestung extends Ruestung {

    String[] schwereRuestungNamenArray = {"Drachenschuppenruestung", "Knochenruestung", "Eisenruestung", "Ebenerzruestung", "Wolfsruestung",
            "Zwergenstahlruestung", "Kristallruestung", "Eisenruestung", "Ruestung des schwarzen Ritters", "Orkruestung"};

    public SchwereRuestung(int stufe) {
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public SchwereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }
}
