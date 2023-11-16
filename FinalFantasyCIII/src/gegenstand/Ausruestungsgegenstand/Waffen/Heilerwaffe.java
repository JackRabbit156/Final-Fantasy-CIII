package gegenstand.Ausruestungsgegenstand.Waffen;

public class Heilerwaffe {

    private String name;
    private int pAtk;
    private int mAtk;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] heilerStabNamenArray = {"Hohestab des Waechters", "Holunderholzgehstock", "Stab des Hoersaalaeltesten", "Stab des Schamanen", "Richtstab",
            "Stab des Lichts", "Beichtstab", "Stab der Zwillingswelten", "Ritualstab", "Stab des Feldwebels" +
//         seltene Objekte
            "Kristalldrachenstab",//Mana
            "Stab der Mondfinsternis",//HP
            "Zorn des Lichts"};//beweglichkeit

    public Heilerwaffe(int stufe) {
        this.name = heilerStabNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(heilerStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pAtk = 0;
        this.mAtk = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public Heilerwaffe(int stufe, boolean istNichtKaufbar) {
        this.name = heilerStabNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(heilerStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pAtk =  0;
        this.mAtk =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }

}
