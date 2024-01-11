package de.bundeswehr.auf.final_fantasy.charakter.model.klassen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TNK extends Klasse {

    public static final List<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("ZweihandWaffe", "EinhandWaffe", "Schild", "SehrSchwereRuestung"));

    private static final String DARSTELLUNG = "charakter/freund/tank.png";
    // 0 = maxGesundheitsPunkte, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegeneration,
    // 10 = manaRegeneration
    private static final int[] DEFAULT_ATTRIBUTE = { 30, 10, 4, 0, 5, 6, 4, 5, 2, 4, 2 };
    private static final String GESCHICHTE = "#NAME# stammt aus einem fernen Dorf, das oft von wilden Kreaturen bedroht wurde. Von klein auf erkannte er seine unglaubliche Stärke und Entschlossenheit, um seine Gemeinschaft zu beschützen. Er trainierte hart und lernte die Kunst des Kampfes mit mächtigen Waffen und Rüstungen.\n" +
            "Als die Angriffe der Kreaturen immer häufiger wurden, entschied sich #NAME#, sein Dorf zu verlassen und Abenteurer zu werden. Mit seiner massiven Statur und der Fähigkeit, Hiebe abzuwehren, wurde er schnell als unerschütterlicher Tank bekannt.\n" +
            "Egal ob in dunklen Dungeons oder in epischen Schlachten gegen übermächtige Gegner, #NAME# war immer vorne dabei und nahm auf sich, was auch immer nötig war, um seine Verbündeten zu schützen. Seine Furchtlosigkeit und sein außergewöhnliches Teamspiel machten ihn zu einem wertvollen Mitglied jedes Abenteuerteams.\n" +
            "Mit seiner imposanten Ausrüstung und seinem unnachgiebigen Geist stand #NAME# als Schild für die Schwachen und Verteidiger für die Verletzten. Sein Name verbreitete sich wie ein Flüstern unter den Feinden, die versuchten, sein Team zu besiegen, aber keiner war je in der Lage, ihn zu bezwingen.\n" +
            "Heute gilt #NAME# als Legende in der Welt der Abenteurer. Seine Tapferkeit und sein unbeugsamer Wille sind weit bekannt und er ist bereit, jedem Feind gegenüberzutreten, der es wagt, seine Freunde in Gefahr zu bringen. Mit seiner stoischen Präsenz und der Fähigkeit, Schaden zu absorbieren, setzt er alles daran, die Dunkelheit zu besiegen.\n" +
            "Die Abenteuer von #NAME# werden als inspirierende Geschichten weitergegeben und seine Bereitschaft, sich in die Schlacht zu stürzen, macht ihn zu einem unverzichtbaren Beschützer in jeder Herausforderung.\n";

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @author Lang
     * @since 30.11.2023
     */
    public TNK() {
        this.setBezeichnung(Klasse.TNK);
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
    }

    /**
     * Setzt die Klasse und setzt Attributspunkte auf Standardwerte für die Klasse.
     * Grundsätzlich für die Erstellung und den Klassenwechsel des Hauptcharakters gedacht.
     * Vergebene Attributspunkte werden hier nicht beruecksichtigt
     *
     * @param spielerCharakter Der SpielerCharakter
     * @author Lang
     * @since 30.11.2023
     */
    public TNK(SpielerCharakter spielerCharakter) {
        this.setBezeichnung(Klasse.TNK);
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(DEFAULT_ATTRIBUTE[0]);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(DEFAULT_ATTRIBUTE[1]);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(DEFAULT_ATTRIBUTE[2]);
        spielerCharakter.setMagischeAttacke(DEFAULT_ATTRIBUTE[3]);
        spielerCharakter.setGenauigkeit(DEFAULT_ATTRIBUTE[4]);
        spielerCharakter.setVerteidigung(DEFAULT_ATTRIBUTE[5]);
        spielerCharakter.setMagischeVerteidigung(DEFAULT_ATTRIBUTE[6]);
        spielerCharakter.setResistenz(DEFAULT_ATTRIBUTE[7]);
        spielerCharakter.setBeweglichkeit(DEFAULT_ATTRIBUTE[8]);
        spielerCharakter.setGesundheitsRegeneration(DEFAULT_ATTRIBUTE[9]);
        spielerCharakter.setManaRegeneration(DEFAULT_ATTRIBUTE[10]);
        spielerCharakter.setGrafischeDarstellung(DARSTELLUNG);
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGeschichte(GESCHICHTE.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return GESCHICHTE;
    }

    @Override
    public String getDarstellung() {
        return DARSTELLUNG;
    }

}
