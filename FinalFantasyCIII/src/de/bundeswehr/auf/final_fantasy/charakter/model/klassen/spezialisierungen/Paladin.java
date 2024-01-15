package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Paladin extends TNK implements Spezialisierung {

    private static final String GESCHICHTE = "#NAME# wurde in einem friedlichen Dorf geboren, das von Dunkelheit bedroht war. Schon früh zeigte er eine außergewöhnliche Verbundenheit zur heiligen Magie und dem Glauben an das Gute.\n" +
            "Als junger Mann begab sich #NAME# auf eine spirituelle Reise, um seine magischen Fähigkeiten zu entwickeln und ein wahrer Champion des Lichts zu werden. Er studierte bei weisen Meistern der Paladin-Kunst und lernte, seine Macht für die Verteidigung der Unschuldigen einzusetzen.\n" +
            "Auf seinen Abenteuern stieß #NAME# auf dunkle Kreaturen, die den Frieden bedrohten. Mit seiner starken Rüstung und seinem göttlichen Schwert kämpfte er gegen das Böse an und rettete diejenigen, die seine Hilfe brauchten.\n" +
            "Im Laufe der Zeit wurde #NAME# zu einer Legende, ein Symbol der Hoffnung und der Gerechtigkeit. Menschen aus allen Ecken des Landes suchten seinen Schutz und seine Weisheit. Er führte eine Gruppe tapferer Gefährten an, um gemeinsam die Welt von den Schatten zu befreien.\n" +
            "Die Taten von #NAME# verbreiteten sich wie ein Lauffeuer und inspirierten andere, dem Pfad des Guten zu folgen. Er wurde zu einem Vorbild für viele, die nach Stärke und Führung suchten.\n" +
            "Bis heute wandelt #NAME# als Tank Paladin über das Land, immer bereit, die Schwachen zu beschützen und das Böse zu bekämpfen. Sein Glaube an das Licht und seine Tapferkeit machen ihn zu einem unverzichtbaren Verbündeten im Kampf für eine bessere Welt.\n";
    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     */
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public Paladin(SpielerCharakter charakter) {
        charakter.setKlasse(this);
        FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(charakter);
        charakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", charakter.getName()));
    }

    @Override
    public int[] getDefaultAttribute() {
        return DEFAULT_ATTRIBUTE;
    }

    /**
     * Erhöht die Max-Gesundheitspunkte um 120 und heilt sich auf 100% Gesundheitspunkte
     *
     * @param aktuellerCharakter
     * @param betroffenerCharakter
     * @return
     */
    public String spezialFaehigkeit(Charakter aktuellerCharakter, Charakter betroffenerCharakter) {
        // Paladin Spezialfähigkeit
        aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() + 120);
        aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte());
        return aktuellerCharakter.getName() + " hat die Paladin-Fähigkeit eingesetzt!\n"
                + "Ja bist du Deppert?!\n" + "100% Heilung und Maximale Gesundheit\nwurde stark erhöht.";
    }

}
