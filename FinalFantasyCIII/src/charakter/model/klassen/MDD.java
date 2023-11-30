package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class MDD extends Klasse{

    /**
     * Setzt die Klasse bei Soeldnern und Feinden
     *
     * @since 30.11.2023
     * @author Lang
     */
    public MDD(){
        this.setBezeichnung("Magischer DD");
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
    public MDD(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Magischer DD");
        spielerCharakter.setMaxGesundheitsPunkte(10);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(20);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(3);
        spielerCharakter.setMagischeAttacke(6);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(2);
        spielerCharakter.setMagischeVerteidigung(4);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(5);
        spielerCharakter.setGesundheitsRegeneration(2);
        spielerCharakter.setManaRegeneration(4);
        spielerCharakter.setGrafischeDarstellung("              _,._      \n" +
                "  .||,       /_ _\\\\     \n" +
                " \\.`',/      |'L'| |    \n" +
                " = ,. =      | -,| L    \n" +
                " / || \\    ,-'\\\"/,'`.   \n" +
                "   ||     ,'   `,,. `.  \n" +
                "   ,|____,' , ,;' \\| |  \n" +
                "  (3|\\    _/|/'   _| |  \n" +
                "   ||/,-''  | >-'' _,\\\\ \n" +
                "   ||'      ==\\ ,-'  ,' \n" +
                "   ||       |  V \\ ,|   \n" +
                "   ||       |    |` |   \n" +
                "   ||       |    |   \\  \n" +
                "   ||       |    \\    \\ \n" +
                "   ||       |     |    \\\n" +
                "   ||       |      \\_,-'\n" +
                "   ||       |___,,--\")_\\\n" +
                "   ||         |_|   ccc/\n" +
                "   ||        ccc/       \n" +
                "   ||                    ");
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
    }
}
