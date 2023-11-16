package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Magierwaffe extends Waffe {

    String[] magierStabNamenArray = {"Nussknacker", "Saurons Stab", "Onyxstab", "Höllenzauberstab", "Zahnstocher",
            "Schimmersteinstab", "Meteoritenzauberstab", "Schlangenstab", "Stab des Erzmagiers", "Stab des Zeitalters" + "Drachenzornstab", "Merlins Stab", "Elderstab"};

    public Magierwaffe(int stufe) {
        this.setName(magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(true);
        this.setpAtk(0);
        this.setmAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);

    } public Magierwaffe(int stufe, boolean istNichtKaufbar) {
        this.setName(magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setKaufbar(false);
        this.setpAtk(0);
        this.setmAtk(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setLevelAnforderung(stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe());
        this.setSoeldnerItem(false);
    }



}
