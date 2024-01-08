package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import trainer.faehigkeiten.FaehigkeitFactory;

public class Eismagier extends MDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,5,0,1,0,0,2,0,0,0,0};
    private final String GESCHICHTE = "#NAME# wuchs in einem abgelegenen Dorf auf, umgeben von schneebedeckten Bergen. Von Kindesbeinen an war er fasziniert von der Magie des Eises und verbrachte Stunden damit, kleine Eisskulpturen zu formen und die Naturerscheinungen des Winters zu studieren.\n" +
            "Mit der Zeit entdeckte #NAME# sein Talent zur Eismagie und begann, seine Fähigkeiten zu entwickeln. Er lernte von einem alten Eismagier, der ihn in den Künsten des Erschaffens und Beherrschens von Eis lehrte. Dabei erfuhr #NAME# auch von einer uralten Prophezeiung, die besagte, dass ein Eismagier kommen würde, um das Land vor einer drohenden Bedrohung zu retten.\n" +
            "Entschlossen, seine Bestimmung zu erfüllen, begab sich #NAME# auf eine abenteuerliche Reise, um seine Kräfte weiter zu stärken und andere Magier zu finden, die ihm helfen konnten. Auf seinem Weg traf er auf verschiedene Gefahren, aber auch auf treue Freunde, die ihn unterstützten.\n" +
            "Schließlich kam #NAME# an den Ort, an dem die drohende Bedrohung ihr Unwesen trieb: Ein böser Magier hatte das Land mit ewigem Frost belegt und die Menschen in Angst versetzt. Mit all seiner Entschlossenheit und der Macht des Eises kämpfte #NAME# gegen den bösen Magier an und brachte das Gleichgewicht zurück.\n" +
            "Seitdem reist #NAME# als legendärer Eismagier durch das Land, um den Menschen zu helfen und für Frieden zu sorgen. Sein freundliches Wesen und seine außergewöhnlichen Fähigkeiten machen ihn zu einem Helden, der von vielen bewundert wird.\n" ;

    public Eismagier(SpielerCharakter charakter) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + attribute[6]);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + attribute[3]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
