package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.TNK;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Paladin extends TNK implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,0,0,0,0,0,0,0,0,0};
    private final String GESCHICHTE = "#NAME# wurde in einem friedlichen Dorf geboren, das von Dunkelheit bedroht war. Schon früh zeigte er eine außergewöhnliche Verbundenheit zur heiligen Magie und dem Glauben an das Gute.\n" +
            "Als junger Mann begab sich #NAME# auf eine spirituelle Reise, um seine magischen Fähigkeiten zu entwickeln und ein wahrer Champion des Lichts zu werden. Er studierte bei weisen Meistern der Paladin-Kunst und lernte, seine Macht für die Verteidigung der Unschuldigen einzusetzen.\n" +
            "Auf seinen Abenteuern stieß #NAME# auf dunkle Kreaturen, die den Frieden bedrohten. Mit seiner starken Rüstung und seinem göttlichen Schwert kämpfte er gegen das Böse an und rettete diejenigen, die seine Hilfe brauchten.\n" +
            "Im Laufe der Zeit wurde #NAME# zu einer Legende, ein Symbol der Hoffnung und der Gerechtigkeit. Menschen aus allen Ecken des Landes suchten seinen Schutz und seine Weisheit. Er führte eine Gruppe tapferer Gefährten an, um gemeinsam die Welt von den Schatten zu befreien.\n" +
            "Die Taten von #NAME# verbreiteten sich wie ein Lauffeuer und inspirierten andere, dem Pfad des Guten zu folgen. Er wurde zu einem Vorbild für viele, die nach Stärke und Führung suchten.\n" +
            "Bis heute wandelt #NAME# als Tank Paladin über das Land, immer bereit, die Schwachen zu beschützen und das Böse zu bekämpfen. Sein Glaube an das Licht und seine Tapferkeit machen ihn zu einem unverzichtbaren Verbündeten im Kampf für eine bessere Welt.\n";


    public Paladin(SpielerCharakter charakter){
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
