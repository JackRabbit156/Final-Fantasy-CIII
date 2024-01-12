package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Feuermagier extends MDD implements Spezialisierung {

    private static final String GESCHICHTE = "Ein furchtloser Feuermagier namens #NAME# wurde als Kind von einem alten Magiermeister entdeckt und unter seine Obhut genommen. Von diesem Tag an widmete sich #NAME# eifrig dem Studium der uralten Kunst des Feuermagiers. Durch jahrelanges Training und harte Arbeit erlernte er die Beherrschung der Flammen und wurde zu einem wahren Meister seines Fachs. Sein Temperament und sein unerschütterlicher Wille machten ihn zu einem gefürchteten Verbündeten in den Schlachten gegen das Böse. Doch #NAME# war nicht nur ein mächtiger Krieger, sondern auch ein weiser Berater und ein Beschützer der Schwachen. Mit seiner Fähigkeit, Feuer zu manipulieren, entfachte er nicht nur Zerstörung, sondern auch Hoffnung in den Herzen der Menschen. #NAME# wird immer als eine leuchtende Flamme in der Geschichte der Abenteuer weiterbrennen, deren Wärme und Kraft nie vergessen werden.";
    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     */
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 0, 0, 4, -1, 0, -2, 0, 0, 0, 0 };

    public Feuermagier(SpielerCharakter charakter) {
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + DEFAULT_ATTRIBUTE[3]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + DEFAULT_ATTRIBUTE[4]);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + DEFAULT_ATTRIBUTE[6]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

}
