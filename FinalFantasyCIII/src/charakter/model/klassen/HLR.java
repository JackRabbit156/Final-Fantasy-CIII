package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;

public class HLR extends Klasse {

    public HLR(){
        this.setBezeichnung("Healer");
    }
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
