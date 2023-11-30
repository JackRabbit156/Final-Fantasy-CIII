package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class SchwereRuestung extends Ruestung {

    private String[] schwereRuestungNamenArray = {"Drachenschuppenruestung", "Knochenruestung", "Eisenruestung", "Ebenerzruestung", "Wolfsruestung",
            "Zwergenstahlruestung", "Kristallruestung", "Eisenruestung", "Ruestung des schwarzen Ritters", "Orkruestung"};

    public SchwereRuestung(int stufe) {
        super();
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public SchwereRuestung(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(schwereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(schwereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }
}
