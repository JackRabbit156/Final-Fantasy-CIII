package gegenstand.Ausruestungsgegenstand.Waffen;
import hilfsklassen.ZufallsZahlenGenerator;

public class Heilerwaffe extends Waffe {

    private String[] heilerStabNamenArray = {"Hohestab des Wächters", "Holunderholzgehstock", "Stab des Hörsaalältesten", "Stab des Schamanen", "Richtstab",
            "Stab des Lichts", "Beichtstab", "Stab der Zwillingswelten", "Ritualstab", "Stab des Feldwebels", "Kristalldrachenstab", "Stab der Mondfinsternis", "Zorn des Lichts"};//beweglichkeit

    public Heilerwaffe(int stufe) {
        this.setIcon("icons/Heilerwaffe.png");
        this.setName(heilerStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(heilerStabNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(true);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);

    } public Heilerwaffe(int stufe, boolean istNichtKaufbar) {
        this.setIcon("icons/Heilerwaffe.png");
        this.setName(heilerStabNamenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(heilerStabNamenArray.length-1)]);
        this.setKaufwert(stufe * 3);
        this.setVerkaufswert(stufe * 2);
        this.setIstNichtKaufbar(false);
        this.setAttacke(0);
        this.setMagischeAttacke(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb1(4));
        this.setGenauigkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(5));
        this.setBeweglichkeit(stufe * ZufallsZahlenGenerator.zufallsZahlIntAb0(3));
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }

}
