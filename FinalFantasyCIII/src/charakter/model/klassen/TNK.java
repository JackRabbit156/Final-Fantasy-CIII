package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class TNK extends Klasse {

    public static final ArrayList<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("ZweihandWaffe", "SehrSchwereRuestung"));
    private static final String geschichte = "#NAME# stammt aus einem fernen Dorf, das oft von wilden Kreaturen bedroht wurde. Von klein auf erkannte er seine unglaubliche Stärke und Entschlossenheit, um seine Gemeinschaft zu beschützen. Er trainierte hart und lernte die Kunst des Kampfes mit mächtigen Waffen und Rüstungen.\n" +
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
        this.setBezeichnung("Tank");
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
        this.setBezeichnung("Tank");
        this.setNutzbareAusruestung(NUTZBARE_AUSRUESTUNG);
        spielerCharakter.setMaxGesundheitsPunkte(30);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(10);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(4);
        spielerCharakter.setMagischeAttacke(0);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(6);
        spielerCharakter.setMagischeVerteidigung(4);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(2);
        spielerCharakter.setGesundheitsRegeneration(4);
        spielerCharakter.setManaRegeneration(2);
        spielerCharakter.setGrafischeDarstellung("\n" +
                "\n" +
                "               _A\n" +
                "             .'`\"`'.\n" +
                "            /   , , \\ \n" +
                "           |   <\\^/> |\n" +
                "           |  < (_) >|\n" +
                "           /====\\\n" +
                "          (.---._ _.-.)\n" +
                "           |/   a` a |\n" +
                "           (      _\\ |\n" +
                "            \\    __  ;\n" +
                "            |\\   .  /\n" +
                "         _.'\\ '----;'-.\n" +
                "     _.-'  O ;-.__.'\\O `o.\n" +
                "    /o \\      \\/-.-\\/|    \\\n" +
                "   |    ;,     '.|\\| /\n" +
                "\n" +
                "\n");
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGeschichte(geschichte.replaceAll("#NAME#", spielerCharakter.getName()));
    }

    public static String getGeschichte() {
        return geschichte;
    }
}
