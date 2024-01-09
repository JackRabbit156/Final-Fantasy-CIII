package de.bundeswehr.auf.final_fantasy.charakter.model;

import java.util.Random;

public abstract class Feind extends Charakter{


    /**
     *Erstellt Feind
     *
     * @param partyLvl int
     *
     * @author Lang
     * @since 20.11.2023
     */
    public Feind(int partyLvl){
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
        super.setLevel(partyLvl);
    }
}
