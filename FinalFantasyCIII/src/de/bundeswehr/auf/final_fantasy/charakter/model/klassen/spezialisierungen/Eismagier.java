package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Eismagier extends MDD implements Spezialisierung {

    private static final String GESCHICHTE = "#NAME# wuchs in einem abgelegenen Dorf auf, umgeben von schneebedeckten Bergen. Von Kindesbeinen an war er fasziniert von der Magie des Eises und verbrachte Stunden damit, kleine Eisskulpturen zu formen und die Naturerscheinungen des Winters zu studieren.\n" +
            "Mit der Zeit entdeckte #NAME# sein Talent zur Eismagie und begann, seine Fähigkeiten zu entwickeln. Er lernte von einem alten Eismagier, der ihn in den Künsten des Erschaffens und Beherrschens von Eis lehrte. Dabei erfuhr #NAME# auch von einer uralten Prophezeiung, die besagte, dass ein Eismagier kommen würde, um das Land vor einer drohenden Bedrohung zu retten.\n" +
            "Entschlossen, seine Bestimmung zu erfüllen, begab sich #NAME# auf eine abenteuerliche Reise, um seine Kräfte weiter zu stärken und andere Magier zu finden, die ihm helfen konnten. Auf seinem Weg traf er auf verschiedene Gefahren, aber auch auf treue Freunde, die ihn unterstützten.\n" +
            "Schließlich kam #NAME# an den Ort, an dem die drohende Bedrohung ihr Unwesen trieb: Ein böser Magier hatte das Land mit ewigem Frost belegt und die Menschen in Angst versetzt. Mit all seiner Entschlossenheit und der Macht des Eises kämpfte #NAME# gegen den bösen Magier an und brachte das Gleichgewicht zurück.\n" +
            "Seitdem reist #NAME# als legendärer Eismagier durch das Land, um den Menschen zu helfen und für Frieden zu sorgen. Sein freundliches Wesen und seine außergewöhnlichen Fähigkeiten machen ihn zu einem Helden, der von vielen bewundert wird.\n";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 5, 0, 1, 0, 0, 2, 0, 0, 0, 0 };

    public Eismagier(SpielerCharakter charakter) {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + DEFAULT_ATTRIBUTE[1]);
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + DEFAULT_ATTRIBUTE[3]);
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
