package charakter.model.klassen;

import charakter.model.SpielerCharakter;

public class MDD extends Klasse{

    public MDD(){
        this.setBezeichnung("Magischer DD");
    }

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
        //TODO implement Fähigkeiten
    }
}
