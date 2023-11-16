package gegenstand.Ausruestungsgegenstand;

import hilfsklassen.ZufallsZahlenGenerator;
/**
 * @author OF Kretschmer
 * @param
 *@since  16.11.23
 * Objektklasse für die Accessoire
 */
public class Accessoire extends Ausruestungsgegenstand {

  private String name;
  private int kaufwert;
  private int verkaufswert;
  private boolean kaufbar;
  //TODO private CharakterAttribut bonus;
  private int bonusumfang;
  private int levelAnforderung;
  private boolean soelderItem;

 String[] namenArray = {"Silberne Kette", "Goldene Kette","Rubinhalskette","Goldenes Diadem", "Blechring",
         "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette" +
         "Diamantring", "Rubinring", "Saphirring", "Silberring"," Smaragdring" +
//         seltene Objekte
         "Das Herz des Ozeans - Titanic",//Mana
         "Das Glas voll Dreck",//HP
         "Das Diadem von Ravenclaw",// Mana
         "Krone von Barenziah",// HP
         "Der eine Ring "};//beweglichkeit
 //TODO CharakterAttribut [] = {maxGesundheit,  gesundheitsRegeneration, beweglichkeit,maxMana, manaRegeneration};



    public Accessoire( int stufe) {
        this.name = namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)];
        this.kaufbar = true;
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4);
        this.bonusumfang = ZufallsZahlenGenerator.zufallsZahlIntAb1(10);
        this.levelAnforderung =stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.kaufwert = this.levelAnforderung * 20;
        this.verkaufswert =  this.kaufwert * 16;
        this.soelderItem = false;

    } public Accessoire( int stufe, boolean istNichtKaufbar) {
        this.name = namenArray[ZufallsZahlenGenerator.zufallsZahlIntAb0(namenArray.length)];
        this.kaufwert = 2;
        this.verkaufswert = 1;
        this.kaufbar = false;
        //TODO this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.zufallsZahlIntAb0(4)
        this.bonusumfang = ZufallsZahlenGenerator.zufallsZahlIntAb1(10);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.zufallsZahlIntGegenstandsstufe();
        this.soelderItem = false;
    }

    public String getName() {
        return name;
    }

    public int getKaufwert() {
        return kaufwert;
    }

    public int getVerkaufswert() {
        return verkaufswert;
    }

    public boolean isKaufbar() {
        return kaufbar;
    }

//    TODO public CharakterAttribut getBonus() {
//        return bonus;
//    }

    public int getBonusumfang() {
        return bonusumfang;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isSoelderItem() {
        return soelderItem;
    }
}
