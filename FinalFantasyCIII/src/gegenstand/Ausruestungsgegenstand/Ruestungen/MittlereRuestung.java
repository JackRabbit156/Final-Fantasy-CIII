package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class MittlereRuestung extends Ruestung {

    String[] mittlereRuestungNamenArray = {"Diebesgildenruestung", "Lederruestung", "Nachtigallruestung", "Vulkanglasruestung", "Schattenruestung",
            "Kettenruestung", "Pelzruestung", "Schlafanzug des Henkers", "Assassinenruestung", "Rebellenruestung"};

    public MittlereRuestung(int stufe) {
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public MittlereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setmVtg(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }
}
