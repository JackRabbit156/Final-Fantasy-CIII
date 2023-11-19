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
        this.setVerkaufswert(this.getKaufwert() * 16);
        this.setIstNichtKaufbar(true);
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
    }

    public Accessoire(int stufe, boolean istNichtKaufbar) {
        super();
        this.setName(namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)]);
        this.setKaufwert(stufe * 20);
        this.setVerkaufswert(this.getKaufwert() * 16);
        this.setIstNichtKaufbar(istNichtKaufbar);
        this.setLevelAnforderung(stufe);
        this.setIstSoeldnerItem(false);
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
    }

    private static String[] namenArray = {"Silberne Kette", "Goldene Kette", "Rubinhalskette", "Goldenes Diadem", "Blechring",
            "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette" +
            "Diamantring", "Rubinring", "Saphirring", "Silberring", " Smaragdring" +
//         seltene Objekte
            "Das Herz des Ozeans - Titanic",//Mana
            "Das Glas voll Dreck",//HP
            "Das Diadem von Ravenclaw",// Mana
            "Krone von Barenziah",// HP
            "Der eine Ring "};//beweglichkeit
    //TODO CharakterAttribut [] = {maxGesundheit,  gesundheitsRegeneration, beweglichkeit,maxMana, manaRegeneration};
}
