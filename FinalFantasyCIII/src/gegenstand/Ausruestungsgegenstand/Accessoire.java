package gegenstand.Ausruestungsgegenstand;

import hilfsklassen.ZufallsZahlenGenerator;

/**
 * @param
 * @author OF Kretschmer
 * @since 16.11.23
 * Objektklasse für die Accessoire
 */
public class Accessoire extends Ausruestungsgegenstand {

    public Accessoire(int stufe) {
        super();
        this.setName(namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)]);
        this.setKaufwert(stufe * 20);
        this.setVerkaufswert(stufe * 16);
        this.setIstNichtKaufbar(true);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
    }

    public Accessoire(int stufe, boolean istNichtKaufbar) {
        super();
        int einzigartigeChance = ZufallsZahlenGenerator.zufallsZahlIntAb1(100);
        if(einzigartigeChance > 5){
        this.setName(namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)]);
        this.setKaufwert(stufe * 20);
        this.setVerkaufswert(stufe * 16);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
        this.setIstSoeldnerItem(false);
        //TODO this.setBonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
        //TODO this.setBonusUmfang = stufe *3;;
        }else {
            int stelle = ZufallsZahlenGenerator.zufallsZahlIntAb0(einzigartigeNamenArray.length);
            this.setName(einzigartigeNamenArray[stelle]);
            if(this.getName().equals("Kaputter Ring")){
                this.setVerkaufswert(stufe * 1);
                //TODO this.setBonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
                //TODO this.setBonusUmfang = stufe *1;;
            }else {
                einzigartigeNamenArray[stelle] = "Kaputter Ring";
            this.setVerkaufswert(stufe * 16);
                //TODO this.setBonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
                //TODO this.setBonusUmfang = stufe *10;;
            }
            this.setKaufwert(stufe * 20);
            this.setIstNichtKaufbar(istNichtKaufbar);
            this.setLevelAnforderung(ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe(stufe));
            this.setIstSoeldnerItem(false);
        }

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
