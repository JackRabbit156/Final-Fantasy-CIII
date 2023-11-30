package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

import java.util.ArrayList;
import java.util.Arrays;

public class HLR extends Klasse {

    //TODO für andere Klassen implementieren
    ArrayList<String> nutzbareAusruestungsgegenstaende = new ArrayList<>(Arrays.asList("Heilerwaffe", "LeichteRuestung"));

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @since 30.11.2023
     * @author Lang
     */
    public HLR(){
        this.setBezeichnung("Healer");
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
    public HLR(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Healer");
        spielerCharakter.setMaxGesundheitsPunkte(10);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(20);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(3);
        spielerCharakter.setMagischeAttacke(0);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(2);
        spielerCharakter.setMagischeVerteidigung(4);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(5);
        spielerCharakter.setGesundheitsRegeneration(2);
        spielerCharakter.setManaRegeneration(4);
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
        spielerCharakter.setGrafischeDarstellung("         .---\n" +
                "        / # o\n" +
                "        \\,__>\n" +
                "     .o-'-'--._\n" +
                "    / |\\_      '.\n" +
                "   |  |  \\   -,  \\\n" +
                "   \\  /   \\__| ) |\n" +
                "    '|_____[)) |,/\n" +
                "       |===H=|\\ >>\n" +
                "       \\  __,| \\_\\\n" +
                "        \\/   \\  \\_\\\n" +
                "        |\\    |  \\/\n" +
                "        | \\   \\   \\\\\n" +
                "        |  \\   |   \\\\\n" +
                "        |__|\\ ,-ooD \\\\\n" +
                "        |--\\_(\\.-'   \\o\n" +
                "        '-.__)");
    }
}
