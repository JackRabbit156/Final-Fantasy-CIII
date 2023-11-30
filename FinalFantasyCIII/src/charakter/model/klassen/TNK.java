package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class TNK extends Klasse{

    public static final ArrayList<String> NUTZBARE_AUSRUESTUNG = new ArrayList<>(Arrays.asList("ZweihandWaffe", "SehrSchwereRuestung"));

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @since 30.11.2023
     * @author Lang
     */
    public TNK(){
        this.setBezeichnung("Tank");
    }

    /**
     * Setzt die Klasse und setzt Attributspunkte auf Standardwerte für die Klasse.
     * Grundsätzlich für die Erstellung und den Klassenwechsel des Hauptcharakters gedacht.
     * Vergebene Attributspunkte werden hier nicht beruecksichtigt
     *
     * @param spielerCharakter Der SpielerCharakter
     *
     * @since 30.11.2023
     * @author Lang
     */
    public TNK(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Tank");
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
    }
}
