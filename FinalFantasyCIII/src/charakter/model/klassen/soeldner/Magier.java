package charakter.model.klassen.soeldner;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import party.AusruestungsgegenstandInventar;

import java.util.Random;

public class Magier extends SpielerCharakter {
    public Magier (String name, String klasse, String geschichte, int partyLvl) {
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
        CharakterController.ausruestungAnlegen(this, this.getWaffe(), new AusruestungsgegenstandInventar());
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLvl));
        CharakterController.ausruestungAnlegen(this, this.getRuestung(), new AusruestungsgegenstandInventar());
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 0);
        CharakterController.ausruestungAnlegen(this, this.getAccessoire(0), new AusruestungsgegenstandInventar());
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 1);
        CharakterController.ausruestungAnlegen(this, this.getAccessoire(1), new AusruestungsgegenstandInventar());
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 2);
        CharakterController.ausruestungAnlegen(this, this.getAccessoire(2), new AusruestungsgegenstandInventar());
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setManaPunkte(super.getMaxManaPunkte());
        this.setGrafischeDarstellung("              _,._      \n" +
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
    }
}
