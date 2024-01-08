package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import trainer.faehigkeiten.FaehigkeitFactory;

public class Sanmaus extends HLR implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,15,-5,0,5,0,0,0,0,0,0};
    private final String GESCHICHTE = "#NAME# wuchs in einer kleinen Stadt auf, in der der Ruf der Familie als begabte Sanitäter schon lange bekannt war. Von Kindesbeinen an lernte er von seinen Eltern die Kunst der medizinischen Versorgung und das Wissen über Heilkräuter.\n" +
            "Als junger Erwachsener schloss sich #NAME# einem Abenteuerteam an und reiste durch gefährliche Länder, um Menschen in Not zu helfen. Mit seinem medizinischen Geschick und seinem unerschütterlichen Mut brachte er Verletzte und Kranke in Sicherheit und behandelte ihre Wunden mit Sorgfalt und Präzision.\n" +
            "#NAME#'s Anpassungsfähigkeit und schnelle Entscheidungsfindung waren legendär, egal ob er mit blutigen Schlachtfeldern oder geheimnisvollen Krankheiten konfrontiert wurde. Seine Fähigkeit, unter Druck ruhig zu bleiben und lebensrettende Maßnahmen einzuleiten, beeindruckte alle, die mit ihm zusammenarbeiteten.\n" +
            "Mit jedem Abenteuer wuchs #NAME#'s Ruf als herausragender Sanitäter. Er entwickelte innovative Methoden, um seine Patienten zu behandeln und das Beste aus begrenzten Ressourcen herauszuholen. Seine Empathie und sein Mitgefühl waren stets spürbar, und er kämpfte unerbittlich für das Wohlergehen der Menschen, denen er diente.\n" +
            "Heute ist #NAME# eine inspirierende Figur in der medizinischen Gemeinschaft. Sein Name wird mit Hoffnung und Vertrauen assoziiert, und er ist bereit, jeden Herausforderungen anzunehmen, um Menschenleben zu retten. Mit seinem Sanitäter-Werkzeugkoffer und seiner Entschlossenheit steht er als Beschützer für diejenigen, die Hilfe brauchen.\n" +
            "Die Abenteuer von #NAME# werden von allen bewundert und seine Fähigkeit, in den schwierigsten Situationen zu handeln, macht ihn zu einem zuverlässigen Begleiter auf jeder Reise.\n";



    public Sanmaus(SpielerCharakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }

}
