package gegenstand.Ausruestungsgegenstand;

import hilfsklassen.ZufallsZahlenGenerator;
/**
 * @author OF Kretschmer
 * @param
 *@since  16.11.23
 * Objektklasse für die Accessoire
 */
public class Accessoire extends Ausrüstungsgegenstand {

  String name;
  int kaufwert;
  int verkaufswert;
  boolean kaufbar;
  CharakterAttribut bonus;
  int bonusumfang;
  int levelAnforderung;
  boolean soelderItem;

 String[] namenArray = {"Silberne Kette", "Goldene Kette","Rubinhalskette","Goldenes Diadem", "Blechring",
         "Goldener Ring", "Edle Brosche", "Diamantdiadem", "Holzkette", "Smaragdkette" +
         "Diamantring", "Rubinring", "Saphirring", "Silberring"," Smaragdring" +
//         seltene Objekte
         "Das Herz des Ozeans - Titanic",//Mana
         "Das Glas voll Dreck",//HP
         "Das Diadem von Ravenclaw",// Mana
         "Krone von Barenziah",// HP
         "Der eine Ring "};//beweglichkeit
 CharakterAttribut [] = {maxGesundheit,  gesundheitsRegeneration, beweglichkeit,maxMana, manaRegeneration};



    public Accessoire( int stufe) {
        this.name = namenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(namenArray.length)];
        this.kaufbar = true;
        this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.ZufallsZahlIntAb0(4)
        this.bonusumfang = ZufallsZahlenGenerator.ZufallsZahlIntAb1(10);
        this.levelAnforderung =stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.kaufwert = this.levelAnforderung * 20;
        this.verkaufswert =  this.kaufwert * 16;
        this.soelderItem = false;

    } public Accessoire( int stufe, boolean istNichtKaufbar) {
        this.name = namenArray[ZufallsZahlenGenerator.ZufallsZahlIntAb0(namenArray.length)];
        this.kaufwert = 2;
        this.verkaufswert = 1;
        this.kaufbar = false;
        this.bonus =  CharakterAttribut [ZufallsZahlenGenerator.ZufallsZahlIntAb0(4)
        this.bonusumfang = ZufallsZahlenGenerator.ZufallsZahlIntAb1(10);
        this.levelAnforderung = stufe + ZufallsZahlenGenerator.ZufallsZahlIntGegenstandsstufe();
        this.soelderItem = false;
    }




}
