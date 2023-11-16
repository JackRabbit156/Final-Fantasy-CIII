package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

public class Magierwaffe extends Waffe {

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
        this.name = magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = true;
        this.pAtk = 0;
        this.mAtk = stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;

    } public Magierwaffe(int stufe, boolean istNichtKaufbar) {
        this.name = magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length)];
        this.kaufwert = stufe * 3;
        this.verkaufswert = stufe * 2;
        this.kaufbar = false;
        this.pAtk =  0;
        this.mAtk =  stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soeldnerItem = false;
    }



}
