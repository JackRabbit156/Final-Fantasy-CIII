package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.List;

public class Priester extends HLR implements Spezialisierung {

    private static final String GESCHICHTE = "#NAME# wurde in einem abgelegenen Kloster geboren, das für seine Heilkünste bekannt war. Schon als Kind zeigte er ein außergewöhnliches Talent für die spirituelle Heilung und den Glauben an die Güte der Welt.\n" +
            "Als Jugendlicher verließ #NAME# das Kloster, um als wandernder Heiler in den umliegenden Dörfern zu dienen. Er studierte bei weisen Priestern und erlernte die Kunst der spirituellen Heilung, während er Menschen von verschiedenen Krankheiten und Verletzungen befreite.\n" +
            "Während seiner Abenteuer kam #NAME# in Kontakt mit den Auswirkungen des Bösen und der Dunkelheit. Er wusste, dass er nicht nur körperliche Leiden heilen musste, sondern auch die seelische Last vieler Menschen lindern musste.\n" +
            "Mit seinem Gebetbuch und seinem heiligen Symbol sammelte #NAME# Anhänger um sich und gründete eine Gemeinschaft von Gläubigen, die die Macht der Liebe und des Mitgefühls verbreiteten. Seine Fähigkeiten als Priester wurden immer stärker, während er diejenigen heilte, die von Ängsten und Schmerzen geplagt waren.\n" +
            "Heute steht #NAME# als strahlendes Beispiel für Hoffnung und Hingabe. Er reist durch das Land und heilt die Wunden von Körper, Geist und Seele. Seine Präsenz bringt Trost und Gelassenheit in Zeiten der Not und erinnert die Menschen daran, dass das Licht der Heilung immer vorhanden ist.\n" +
            "Die Taten von #NAME# werden von allen bewundert und seine Fähigkeit, andere zu heilen und zu trösten, macht ihn zu einem unverzichtbaren Begleiter auf jedem Abenteuer.\n";
    /**
     * 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
     * 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
     * 10 = manaRegeneration
     */
    private static final int[] DEFAULT_ATTRIBUTE = { 0, 10, 0, 0, 5, 0, 5, 0, 0, 0, 0 };

    public Priester(SpielerCharakter charakter) {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + DEFAULT_ATTRIBUTE[1]);
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

    /**
     * Erhöht die Gesundheit und Mana um 20 und alle Statuswerte des Teams um 30, bzw. 80 (für Feinde)
     *
     * @param aktuellerCharakter
     * @param betroffenerCharakter
     * @param freundeDieNochLeben
     * @param feindeDieNochLeben
     * @return
     */
    public String spezialFaehigkeit(Charakter aktuellerCharakter, Charakter betroffenerCharakter, List<SpielerCharakter> freundeDieNochLeben, List<Feind> feindeDieNochLeben) {
        // Priester Spezialfähigkeit
        int kleinerBonus = 20;
        if (betroffenerCharakter instanceof Feind) {
            int bonus = 80;
            for (Feind feind : feindeDieNochLeben) {
                feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + kleinerBonus);
                feind.setGesundheitsPunkte(feind.getGesundheitsPunkte() + kleinerBonus);
                feind.setMaxManaPunkte(feind.getMaxManaPunkte() + kleinerBonus);
                feind.setManaPunkte(feind.getManaPunkte() + kleinerBonus);
                feind.setPhysischeAttacke(feind.getPhysischeAttacke() + bonus);
                feind.setMagischeAttacke(feind.getMagischeAttacke() + bonus);
                feind.setVerteidigung(feind.getVerteidigung() + bonus);
                feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + bonus);
                feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + bonus);
                feind.setManaRegeneration(feind.getManaRegeneration() + bonus);
                feind.setGenauigkeit(feind.getGenauigkeit() + bonus);
                feind.setBeweglichkeit(feind.getBeweglichkeit() + bonus);
                feind.setResistenz(feind.getResistenz() + bonus);
            }
        }
        else if (betroffenerCharakter instanceof SpielerCharakter) {
            int bonus = 30;
            for (SpielerCharakter charakter : freundeDieNochLeben) {
                charakter.setMaxGesundheitsPunkte(charakter.getMaxGesundheitsPunkte() + kleinerBonus);
                charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() + kleinerBonus);
                charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + kleinerBonus);
                charakter.setManaPunkte(charakter.getManaPunkte() + kleinerBonus);
                charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + bonus);
                charakter.setMagischeAttacke(charakter.getMagischeAttacke() + bonus);
                charakter.setVerteidigung(charakter.getVerteidigung() + bonus);
                charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + bonus);
                charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() + bonus);
                charakter.setManaRegeneration(charakter.getManaRegeneration() + bonus);
                charakter.setGenauigkeit(charakter.getGenauigkeit() + bonus);
                charakter.setBeweglichkeit(charakter.getBeweglichkeit() + bonus);
                charakter.setResistenz(charakter.getResistenz() + bonus);
            }
        }
        return aktuellerCharakter.getName() + " hat die Priester-Fähigkeit eingesetzt!\n"
                + "Da brat mir doch einer nen Storch...\n"
                + "Statuswerte des Teams wurden stark erhöht.";
    }
}
