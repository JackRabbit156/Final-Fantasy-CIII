package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Priester extends HLR implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,10,0,0,5,0,5,0,0,0,0};
    private final String geschichte =  "#NAME# wurde in einem abgelegenen Kloster geboren, das für seine Heilkünste bekannt war. Schon als Kind zeigte er ein außergewöhnliches Talent für die spirituelle Heilung und den Glauben an die Güte der Welt.\n" +
            "Als Jugendlicher verließ #NAME# das Kloster, um als wandernder Heiler in den umliegenden Dörfern zu dienen. Er studierte bei weisen Priestern und erlernte die Kunst der spirituellen Heilung, während er Menschen von verschiedenen Krankheiten und Verletzungen befreite.\n" +
            "Während seiner Abenteuer kam #NAME# in Kontakt mit den Auswirkungen des Bösen und der Dunkelheit. Er wusste, dass er nicht nur körperliche Leiden heilen musste, sondern auch die seelische Last vieler Menschen lindern musste.\n" +
            "Mit seinem Gebetbuch und seinem heiligen Symbol sammelte #NAME# Anhänger um sich und gründete eine Gemeinschaft von Gläubigen, die die Macht der Liebe und des Mitgefühls verbreiteten. Seine Fähigkeiten als Priester wurden immer stärker, während er diejenigen heilte, die von Ängsten und Schmerzen geplagt waren.\n" +
            "Heute steht #NAME# als strahlendes Beispiel für Hoffnung und Hingabe. Er reist durch das Land und heilt die Wunden von Körper, Geist und Seele. Seine Präsenz bringt Trost und Gelassenheit in Zeiten der Not und erinnert die Menschen daran, dass das Licht der Heilung immer vorhanden ist.\n" +
            "Die Taten von #NAME# werden von allen bewundert und seine Fähigkeit, andere zu heilen und zu trösten, macht ihn zu einem unverzichtbaren Begleiter auf jedem Abenteuer.\n";


    public Priester(SpielerCharakter charakter){
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung()+ attribute[6]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(geschichte.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
