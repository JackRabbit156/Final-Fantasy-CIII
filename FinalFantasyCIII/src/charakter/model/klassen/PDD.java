package charakter.model.klassen;

import charakter.model.SpielerCharakter;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;

public class PDD extends Klasse{

    public PDD(){
        this.setBezeichnung("Physischer DD");
    }

    public PDD(SpielerCharakter spielerCharakter) {
        this.setBezeichnung("Physischer DD");
        spielerCharakter.setMaxGesundheitsPunkte(20);
        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        spielerCharakter.setMaxManaPunkte(50);
        spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        spielerCharakter.setPhysischeAttacke(6);
        spielerCharakter.setMagischeAttacke(0);
        spielerCharakter.setGenauigkeit(5);
        spielerCharakter.setVerteidigung(4);
        spielerCharakter.setMagischeVerteidigung(2);
        spielerCharakter.setResistenz(5);
        spielerCharakter.setBeweglichkeit(5);
        spielerCharakter.setGesundheitsRegeneration(2);
        spielerCharakter.setManaRegeneration(4);
        spielerCharakter.setGrafischeDarstellung("          {}\n" +
                "         .--.\n" +
                "        /.--.\\\n" +
                "        |====|\n" +
                "        |`::`|\n" +
                "    .-;`\\..../`;-.\n" +
                "   /  |...::...|  \\\n" +
                "  |   /'''::'''\\   |\n" +
                "  ;--'\\   ::   /\\--;\n" +
                "  <__>,>._::_.<,<__>\n" +
                "  |  |/   ^^   \\|  |\n" +
                "  \\::/|        |\\::/\n" +
                "  |||\\|        |/|||\n" +
                "  ''' |___/\\___| '''\n" +
                "       \\_ || _/\n" +
                "       <_ >< _>\n" +
                "       |  ||  |\n" +
                "       |  ||  |\n" +
                "      _\\.:||:./_\n" +
                "     /____/\\____\\");
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getBezeichnung(), spielerCharakter.getLevel()));
    }
}
