package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Abwehr extends Buff {

    public Abwehr(Charakter charakter, int verteidigung, int magischeVerteidigung) {
        super(charakter, verteidigung < 0 || magischeVerteidigung < 0);
        this.verteidigung = verteidigung;
        this.magischeVerteidigung = magischeVerteidigung;
    }

    @Override
    public Buff apply() {
        charakter.setVerteidigung(charakter.getVerteidigung() - verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeVerteidigung);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung);
        return this;
    }

}
