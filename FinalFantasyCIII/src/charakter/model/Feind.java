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
     * @since 15.11.2023
     */
    public Feind(int partyLvl){
        Random rnd = new Random();

        this.setMaxGesundheitsPunkte(rnd.nextInt(100) * (partyLvl/10));
        this.setGesundheitsPunkte(this.getMaxGesundheitsPunkte());
        this.setMaxManaPunkte(rnd.nextInt(100) * (partyLvl/10));
        this.setManaPunkte(getMaxManaPunkte());
        this.setPhysischeAttacke(rnd.nextInt(100) * (partyLvl/10));
        this.setMagischeAttacke(rnd.nextInt(100) * (partyLvl/10));
        this.setGenauigkeit(rnd.nextInt(100) * (partyLvl/10));
        this.setVerteidigung(rnd.nextInt(100) * (partyLvl/10));
        this.setMagischeVerteidigung(rnd.nextInt(100) * (partyLvl/10));
        this.setResistenz(rnd.nextInt(100) * (partyLvl/10));
        this.setBeweglichkeit(rnd.nextInt(100) * (partyLvl/10));
        this.setGesundheitsRegeneration(rnd.nextInt(100) * (partyLvl/10));
        this.setManaRegeneration(rnd.nextInt(100) * (partyLvl/10));

        //TODO implement Fähigkeiten, Waffe, Rüstung, Accessiore
    }
}
