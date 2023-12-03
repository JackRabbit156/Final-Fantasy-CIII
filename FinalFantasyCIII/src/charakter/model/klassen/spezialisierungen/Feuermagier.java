package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Feuermagier extends MDD implements Spezialisierung {

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,0,4,-1,0,-2,0,0,0,0};
    private final String geschichte = "Ein furchtloser Feuermagier namens #NAME# wurde als Kind von einem alten Magiermeister entdeckt und unter seine Obhut genommen. Von diesem Tag an widmete sich #NAME# eifrig dem Studium der uralten Kunst des Feuermagiers. Durch jahrelanges Training und harte Arbeit erlernte er die Beherrschung der Flammen und wurde zu einem wahren Meister seines Fachs. Sein Temperament und sein unerschütterlicher Wille machten ihn zu einem gefürchteten Verbündeten in den Schlachten gegen das Böse. Doch #NAME# war nicht nur ein mächtiger Krieger, sondern auch ein weiser Berater und ein Beschützer der Schwachen. Mit seiner Fähigkeit, Feuer zu manipulieren, entfachte er nicht nur Zerstörung, sondern auch Hoffnung in den Herzen der Menschen. #NAME# wird immer als eine leuchtende Flamme in der Geschichte der Abenteuer weiterbrennen, deren Wärme und Kraft nie vergessen werden.";


    public Feuermagier(SpielerCharakter charakter){
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + attribute[3]);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + attribute[6]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(geschichte.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
