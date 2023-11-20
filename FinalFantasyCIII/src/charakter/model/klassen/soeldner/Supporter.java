package charakter.model.klassen.soeldner;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.TNK;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

import java.util.Random;

public class Supporter extends SpielerCharakter {
    public Supporter (String name, String klasse, String geschichte, int partyLvl) {
        super(name, klasse, geschichte);
        Random rnd = new Random();
        this.setMaxGesundheitsPunkte(rnd.nextInt(100) * (partyLvl / 10));
        this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
        this.setMaxManaPunkte(rnd.nextInt(100) * (partyLvl / 10));
        this.setManaPunkte(getMaxManaPunkte());
        this.setPhysischeAttacke(rnd.nextInt(100) * (partyLvl / 10));
        this.setMagischeAttacke(rnd.nextInt(100) * (partyLvl / 10));
        this.setGenauigkeit(rnd.nextInt(100) * (partyLvl / 10));
        this.setVerteidigung(rnd.nextInt(100) * (partyLvl / 10));
        this.setMagischeVerteidigung(rnd.nextInt(100) * (partyLvl / 10));
        this.setResistenz(rnd.nextInt(100) * (partyLvl / 10));
        this.setBeweglichkeit(rnd.nextInt(100) * (partyLvl / 10));
        this.setGesundheitsRegeneration(rnd.nextInt(100) * (partyLvl / 10));
        this.setManaRegeneration(rnd.nextInt(100) * (partyLvl / 10));

        this.setLevel(partyLvl);
        this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLvl));
        this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLvl));
        this.setAccessoires(new Accessoire[3]);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 0);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 1);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 2);
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
        //TODO implement Fähigkeiten
    }
}
