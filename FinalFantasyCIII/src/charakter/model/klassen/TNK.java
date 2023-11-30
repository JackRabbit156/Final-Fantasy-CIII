package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class TNK extends Klasse{


    public TNK(){
        this.setBezeichnung("Tank");
    }

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
