package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class SchwereRuestung extends Ruestung {

    private String[] schwereRuestungNamenArray = {"Drachenschuppenruestung", "Knochenruestung", "Eisenruestung", "Ebenerzruestung", "Wolfsruestung",
            "Zwergenstahlruestung", "Kristallruestung", "Eisenruestung", "Ruestung des schwarzen Ritters", "Orkruestung"};

    public SchwereRuestung(int stufe) {
        super();
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setIstSoeldnerItem(false);

    } public SchwereRuestung(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setIstSoeldnerItem(false);
    }
}
