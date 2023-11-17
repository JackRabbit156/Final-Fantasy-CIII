package gegenstand.Ausruestungsgegenstand;

import hilfsklassen.ZufallsZahlenGenerator;

/**
 * @param
 * @author OF Kretschmer
 * @since 17.11.23
 * Objektklasse für die Accessoire
 */
public class Accessoire extends Ausruestungsgegenstand {

    /**
     * @param stufe
     * @author OF Kretschmer
     * @since 17.11.23
     * Konstruktor für Händler
     */
    public Accessoire(int stufe) {
        super();
        normalesAccessoire(stufe);
        this.setIstNichtKaufbar(true);
    }

    /**
     * @param stufe
     * @param istNichtKaufbar
     * @author OF Kretschmer
     * @since 17.11.23
     * Konstruktor für Söldner/Gegner & Loot
     */
    public Accessoire(int stufe, boolean istNichtKaufbar) {
        super();
        this.setIstNichtKaufbar(false);
        int einzigartigeChance = ZufallsZahlenGenerator.zufallsZahlIntAb1(100);
        //Der Wert in der If Prüfung regelt die Dropchance für einzigartige Accessoires
        if (einzigartigeChance > 5) {
            normalesAccessoire(stufe);
        } else {
            einzigartigesAccessoire(stufe);
        }

    }

    /**
     * @author OF Kretschmer
     * @since 17.11.23
     * @param stufe
     * Erstellt ein normales Accessoire
     */
    private void normalesAccessoire(int stufe) {
        this.setName(namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)]);
        this.setKaufwert(stufe * 20);
        this.setVerkaufswert(stufe * 16);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
    }

    private void einzigartigesAccessoire(int stufe) {
        int stelle = ZufallsZahlenGenerator.zufallsZahlIntAb0(einzigartigeNamenArray.length);
        this.setName(einzigartigeNamenArray[stelle]);
            //TODO this.setBonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
        if (this.getName().equals("Kaputter Ring")) {
            this.setVerkaufswert(stufe * 1);
            //TODO this.setBonusUmfang = 1;;
        } else {
            einzigartigeNamenArray[stelle] = "Kaputter Ring";
            this.setVerkaufswert(stufe * 16);
            //TODO this.setBonusUmfang = stufe *10;;
        }
        this.setKaufwert(stufe * 20);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
    }

    private static final String[] namenArray = {"Silberne Kette", "Goldene Kette", "Rubinhalskette", "Goldenes Diadem", "Blechring",
            "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette" +
            "Diamantring", "Rubinring", "Saphirring", "Silberring", " Smaragdring"};


    private static String[] einzigartigeNamenArray = {
            "Das Herz des Ozeans - Titanic",//Mana
            "Das Glas voll Dreck",//HP
            "Das Diadem von Ravenclaw",// Mana
            "Krone von Barenziah",// HP
            "Der eine Ring "};//beweglichkeit

    //TODO CharakterAttribut [] = {maxGesundheit,  gesundheitsRegeneration, beweglichkeit,maxMana, manaRegeneration};
}
