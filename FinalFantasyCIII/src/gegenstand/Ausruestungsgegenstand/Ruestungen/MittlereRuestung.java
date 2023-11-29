package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class MittlereRuestung extends Ruestung {

    private String[] mittlereRuestungNamenArray = {"Diebesgildenruestung", "Lederruestung", "Nachtigallruestung", "Vulkanglasruestung", "Schattenruestung",
            "Kettenruestung", "Pelzruestung", "Schlafanzug des Henkers", "Assassinenruestung", "Rebellenruestung"};

    public MittlereRuestung(int stufe) {
        super();
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public MittlereRuestung(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }
}
