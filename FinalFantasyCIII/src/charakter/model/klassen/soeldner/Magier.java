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
        super(name, klasse, geschichte, partyLvl, true);
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
        CharakterController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), new AusruestungsgegenstandInventar());
        CharakterController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), new AusruestungsgegenstandInventar());
        CharakterController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()), new AusruestungsgegenstandInventar());
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setManaPunkte(super.getMaxManaPunkte());
        this.setGrafischeDarstellung("charaktere/freund/mdd.png");
    }
}
