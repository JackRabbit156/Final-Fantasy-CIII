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
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setIstSoeldnerItem(false);

    } public MittlereRuestung(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setIstSoeldnerItem(false);
    }
}
