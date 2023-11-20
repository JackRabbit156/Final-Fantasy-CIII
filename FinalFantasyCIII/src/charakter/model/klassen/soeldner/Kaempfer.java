package charakter.model.klassen.soeldner;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.PDD;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

import java.util.Random;

public class Kaempfer extends SpielerCharakter {

    public Kaempfer(String name, String klasse, String geschichte, int partyLvl) {
        super(name, klasse, geschichte, partyLvl);
        Random rnd = new Random();
        super.setMaxGesundheitsPunkte((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setMaxManaPunkte((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setManaPunkte(getMaxManaPunkte());
        super.setPhysischeAttacke((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setMagischeAttacke((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setGenauigkeit((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setVerteidigung((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setMagischeVerteidigung((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setResistenz((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setBeweglichkeit((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setGesundheitsRegeneration((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);
        super.setManaRegeneration((rnd.nextInt(100) * (partyLvl/10)) > 0 ? (rnd.nextInt(100) * (partyLvl/10)) : 1);

        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLvl));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLvl));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 2);
        this.setGrafischeDarstellung("          {}\n" +
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
        //TODO implement Fähigkeiten
    }
}
