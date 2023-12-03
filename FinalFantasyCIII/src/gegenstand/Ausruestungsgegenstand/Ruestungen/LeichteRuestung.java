package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class LeichteRuestung extends Ruestung {

    private String[] leichteRuestungNamenArray = {"Des Kaisers neue Robe", "Robe des blinden Sehers", "Merlins Robe", "Morgenmantel der Muedigkeit", "Blauer Blitz",
            "Stoffrobe", "Lumineszenzrobe", "Robe des Daemonenfuersten", "Robe des dunklen Lords", "Robe des Gerechten"};

    public LeichteRuestung(int stufe) {
        super();
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public LeichteRuestung(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(leichteRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(leichteRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(2));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }
}
