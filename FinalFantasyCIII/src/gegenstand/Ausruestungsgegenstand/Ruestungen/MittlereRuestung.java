package gegenstand.Ausruestungsgegenstand.Ruestungen;

public class MittlereRuestung {

    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] mittlereRuestungNamenArray = {"Diebesgildenruestung", "Lederruestung", "Nachtigallruestung", "Vulkanglasruestung", "Schattenruestung",
            "Kettenruestung", "Pelzruestung", "Schlafanzug des Henkers", "Assassinenruestung", "Rebellenruestung"};

    public MittlereRuestung(int stufe) {
        this.name = mittlereRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(mittlereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(3);
        this.mVtg = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(3);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public MittlereRuestung(int stufe, boolean istNichtKaufbar) {
        this.name = mittlereRuestungNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(mittlereRuestungNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pVtg =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(3);
        this.mVtg =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(3);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }
}
