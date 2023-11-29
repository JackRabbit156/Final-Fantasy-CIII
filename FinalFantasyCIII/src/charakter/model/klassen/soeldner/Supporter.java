package charakter.model.klassen.soeldner;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.TNK;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import party.AusruestungsgegenstandInventar;

import java.util.Random;

public class Supporter extends SpielerCharakter {
    public Supporter (String name, String klasse, String geschichte, int partyLvl) {
        super(name, klasse, geschichte, partyLvl);
        Random rnd = new Random();
        super.setMaxGesundheitsPunkte(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setMaxManaPunkte(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setManaPunkte(getMaxManaPunkte());
        super.setPhysischeAttacke(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setMagischeAttacke(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setGenauigkeit(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setVerteidigung(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setMagischeVerteidigung(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setResistenz(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setBeweglichkeit(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setGesundheitsRegeneration(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);
        super.setManaRegeneration(((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) > 0 ? ((int)Math.round(rnd.nextInt(100) * (partyLvl / 10.0))) : 1);

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
        this.setGrafischeDarstellung("\n" +
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
    }
}
