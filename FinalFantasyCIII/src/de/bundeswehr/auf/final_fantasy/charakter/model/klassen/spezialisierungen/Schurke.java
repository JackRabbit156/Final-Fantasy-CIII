package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;


public class Schurke extends PDD implements Spezialisierung {

    private static final String GESCHICHTE = "Ein mysteriöser Schurke namens #NAME# schleicht durch die Schatten und treibt sein Spiel der Täuschung. Seine Geschicklichkeit und sein scharfer Verstand machen ihn zu einem Meister des Diebstahls und der Betrügerei. Aufgewachsen als Waisenkind in den düstersten Gassen der Stadt, musste #NAME# früh lernen, zu überleben. Er entwickelte ein feines Gespür für Situationen und Menschen, und nutzte diese Fähigkeiten, um sich seinen Platz in der Welt zu sichern. Doch während er sich mit Gaunereien und zwielichtigen Geschäften beschäftigte, entdeckte #NAME# auch seine eigene Moral. Er begann, geheime Wünsche zu erfüllen und mächtige Schurken zu bekämpfen. Seine Verkleidung und sein Charme wurden zu Waffen im Kampf gegen das Unrecht. #NAME# wird für immer als ein Rätsel in den Herzen der Menschen bleiben - ein Held, der aus den Schatten erwächst und das Licht in die Dunkelheit bringt.";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 0, 4, 0, 2, -4, 0, 0, 3, 0, 0 };

    public Schurke(SpielerCharakter charakter) {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + DEFAULT_ATTRIBUTE[8]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + DEFAULT_ATTRIBUTE[4]);
        charakter.setVerteidigung(charakter.getVerteidigung() + DEFAULT_ATTRIBUTE[5]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + DEFAULT_ATTRIBUTE[2]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

}
