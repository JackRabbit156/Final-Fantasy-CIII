package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Sanmaus extends HLR implements Spezialisierung {

    private static final String GESCHICHTE = "#NAME# wuchs in einer kleinen Stadt auf, in der der Ruf der Familie als begabte Sanitäter schon lange bekannt war. Von Kindesbeinen an lernte er von seinen Eltern die Kunst der medizinischen Versorgung und das Wissen über Heilkräuter.\n" +
            "Als junger Erwachsener schloss sich #NAME# einem Abenteuerteam an und reiste durch gefährliche Länder, um Menschen in Not zu helfen. Mit seinem medizinischen Geschick und seinem unerschütterlichen Mut brachte er Verletzte und Kranke in Sicherheit und behandelte ihre Wunden mit Sorgfalt und Präzision.\n" +
            "#NAME#'s Anpassungsfähigkeit und schnelle Entscheidungsfindung waren legendär, egal ob er mit blutigen Schlachtfeldern oder geheimnisvollen Krankheiten konfrontiert wurde. Seine Fähigkeit, unter Druck ruhig zu bleiben und lebensrettende Maßnahmen einzuleiten, beeindruckte alle, die mit ihm zusammenarbeiteten.\n" +
            "Mit jedem Abenteuer wuchs #NAME#'s Ruf als herausragender Sanitäter. Er entwickelte innovative Methoden, um seine Patienten zu behandeln und das Beste aus begrenzten Ressourcen herauszuholen. Seine Empathie und sein Mitgefühl waren stets spürbar, und er kämpfte unerbittlich für das Wohlergehen der Menschen, denen er diente.\n" +
            "Heute ist #NAME# eine inspirierende Figur in der medizinischen Gemeinschaft. Sein Name wird mit Hoffnung und Vertrauen assoziiert, und er ist bereit, jeden Herausforderungen anzunehmen, um Menschenleben zu retten. Mit seinem Sanitäter-Werkzeugkoffer und seiner Entschlossenheit steht er als Beschützer für diejenigen, die Hilfe brauchen.\n" +
            "Die Abenteuer von #NAME# werden von allen bewundert und seine Fähigkeit, in den schwierigsten Situationen zu handeln, macht ihn zu einem zuverlässigen Begleiter auf jeder Reise.\n";
    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     */
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 15, -5, 0, 5, 0, 0, 0, 0, 0, 0 };

    public Sanmaus(SpielerCharakter charakter) {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + DEFAULT_ATTRIBUTE[1]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + DEFAULT_ATTRIBUTE[2]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + DEFAULT_ATTRIBUTE[4]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

}
