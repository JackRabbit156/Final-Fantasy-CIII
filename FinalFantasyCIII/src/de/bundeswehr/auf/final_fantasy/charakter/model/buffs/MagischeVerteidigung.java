package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class MagischeVerteidigung extends Buff {

    public MagischeVerteidigung(Charakter charakter, int magischeVerteidigung) {
        super(charakter, magischeVerteidigung < 0);
        this.magischeVerteidigung = magischeVerteidigung;
    }

    @Override
    public Buff apply() {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeVerteidigung);
        return this;
    }

}
