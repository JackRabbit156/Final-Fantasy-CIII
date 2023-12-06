package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;


public class Schurke extends PDD implements Spezialisierung {

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,4,0,2,-4,0,0,3,0,0};
    private final String GESCHICHTE = "Ein mysteriöser Schurke namens #NAME# schleicht durch die Schatten und treibt sein Spiel der Täuschung. Seine Geschicklichkeit und sein scharfer Verstand machen ihn zu einem Meister des Diebstahls und der Betrügerei. Aufgewachsen als Waisenkind in den düstersten Gassen der Stadt, musste #NAME# früh lernen, zu überleben. Er entwickelte ein feines Gespür für Situationen und Menschen, und nutzte diese Fähigkeiten, um sich seinen Platz in der Welt zu sichern. Doch während er sich mit Gaunereien und zwielichtigen Geschäften beschäftigte, entdeckte #NAME# auch seine eigene Moral. Er begann, geheime Wünsche zu erfüllen und mächtige Schurken zu bekämpfen. Seine Verkleidung und sein Charme wurden zu Waffen im Kampf gegen das Unrecht. #NAME# wird für immer als ein Rätsel in den Herzen der Menschen bleiben - ein Held, der aus den Schatten erwächst und das Licht in die Dunkelheit bringt.";


    public Schurke(SpielerCharakter charakter) {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + attribute[8]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setVerteidigung(charakter.getVerteidigung() + attribute[5]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
