package charakter.model.klassen.spezialisierungen;

import charakter.model.klassen.TNK;
import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFactory;

public class Rabauke extends TNK implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,3,0,-2,0,0,0,0,0,0};
    private final String GESCHICHTE = "#NAME# wuchs in den Straßen einer pulsierenden Stadt auf, geplagt von Gewalt und Kriminalität. Als jüngstes von vier Geschwistern lernte er früh, sich selbst zu verteidigen und für das zu kämpfen, was ihm wichtig war.\n" +
            "Mit der Zeit entwickelte #NAME# eine beeindruckende körperliche Stärke und hatte einen unerschütterlichen Mut. Er wurde bekannt als derjenige, der nicht nur stark, sondern auch gerecht war. Menschen, die Bedrückung erfuhren, konnten immer auf #NAME# zählen, um sie zu beschützen.\n" +
            "Doch #NAME# spürte, dass seine Aufgabe größer war. Er träumte davon, das Böse in seiner Stadt zu bekämpfen und für Gerechtigkeit zu sorgen. Um dies zu erreichen, begab er sich auf eine abenteuerliche Reise, um seine Kampffähigkeiten weiter zu perfektionieren und verschiedene Techniken des Nahkampfes zu erlernen.\n" +
            "Unterwegs traf #NAME# auf andere tapfere Abenteurer, die an seiner Seite kämpften und ihn inspirierten. Gemeinsam stellten sie sich gefährlichen Banden und korrupten Machthabern entgegen, um die Stadt von ihrem Unrecht zu befreien.\n" +
            "Schließlich wurde #NAME# zu einem berühmten Tank Rabauken, der durch sein mutiges Vorgehen und seine Fähigkeit, die Aufmerksamkeit der Gegner auf sich zu ziehen, bekannt war. Sein tapferes Herz und seine Entschlossenheit machten ihn zu einem Symbol der Hoffnung für die Menschen und ließen ihn zu einem Beschützer der Schwachen werden.\n" +
            "Bis heute kämpft #NAME# gegen das Böse und setzt sich für die Schwachen ein, stets bereit, seine Faust zu erheben und für das einzustehen, woran er glaubt. Sein Ruf als Tank Rabauken eilt ihm voraus und er ist für viele ein Symbol der Stärke und des Mutes.\n";


    public Rabauke(SpielerCharakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
