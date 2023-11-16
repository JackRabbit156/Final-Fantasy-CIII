package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Magierwaffe extends Ausruestungsgegenstand {

    private String name;
    private int pAtk;
    private int mAtk;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    String[] magierStabNamenArray = {"Nussknacker", "Saurons Stab", "Onyxstab", "Höllenzauberstab", "Zahnstocher",
            "Schimmersteinstab", "Meteoritenzauberstab", "Schlangenstab", "Stab des Erzmagiers", "Stab des Zeitalters" +
//         seltene Objekte
            "Drachenzornstab",//Mana
            "Merlins Stab",//HP
            "Elderstab"};//beweglichkeit

    public Magierwaffe(int stufe) {
        this.name = magierStabNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(magierStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pAtk = 0;
        this.mAtk = stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public Magierwaffe(int stufe, boolean istNichtKaufbar) {
        this.name = magierStabNamenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(magierStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pAtk =  0;
        this.mAtk =  stufe * ZufallsZahlenGenerator.ZufallsZahlIntAb0(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }



}
