package gegenstand.Ausruestungsgegenstand.Ruestungen;

import hilfsklassen.ZufallsZahlenGenerator;

public class MittlereRuestung extends Ruestung {

    private String[] mittlereRuestungNamenArray = {"Diebesgildenrüstung", "Lederrüstung", "Nachtigallrüstung", "Vulkanglasrüstung", "Schattenrüstung",
            "Kettenrüstung", "Pelzrüstung", "Schlafanzug des Henkers", "Assassinenrüstung", "Rebellenrüstung"};

    public MittlereRuestung(int stufe) {
        this.setIcon("icons/mittlereRuestung.png");
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public MittlereRuestung(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/mittlereRuestung.png");
        this.setName(mittlereRuestungNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(mittlereRuestungNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMagischeVerteidigung(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setResistenz(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
        this.setMaxGesundheitsPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setMaxManaPunkte(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }
}
