package charakter.model;

import charakter.model.klassen.Klasse;

import java.util.Random;

public abstract class Feind extends Charakter{


    /**
     *Erstellt Feind
     *
     * @param partyLvl
     *
     * @author Lang
     * @since 20.11.2023
     */
    public Feind(int partyLvl){
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
    }
}
