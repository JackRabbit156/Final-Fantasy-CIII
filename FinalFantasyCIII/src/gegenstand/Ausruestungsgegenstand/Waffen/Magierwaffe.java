package gegenstand.Ausruestungsgegenstand.Waffen;

import hilfsklassen.ZufallsZahlenGenerator;

public class Magierwaffe extends Waffe {

    private String[] magierStabNamenArray = {"Nussknacker", "Sauron's Stab", "Onyxstab", "Höllenzauberstab", "Zahnstocher",
            "Schimmersteinstab", "Meteoritenzauberstab", "Schlangenstab", "Stab des Erzmagiers", "Stab des Zeitalters", "Drachenzornstab", "Merlin's Stab", "Elderstab"};

    public Magierwaffe(int stufe) {
        this.setIcon("icons/magierWaffe.png");
        this.setName(magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(4));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(4));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public Magierwaffe(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/magierWaffe.png");
        this.setName(magierStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(magierStabNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(4));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(4));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }



}
