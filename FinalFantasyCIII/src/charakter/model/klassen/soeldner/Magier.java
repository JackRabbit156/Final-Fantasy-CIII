package charakter.model.klassen.soeldner;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

import java.util.Random;

public class Magier extends SpielerCharakter {
    public Magier (String name, String klasse, String geschichte, int partyLvl) {
        super(name, klasse, geschichte);
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

        super.setLevel(partyLvl);
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLvl));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLvl));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLvl), 2);
        super.setGrafischeDarstellung("Dummy Soeldner-Magier"); // TODO pruefen grafische Darstellung
    }
}
