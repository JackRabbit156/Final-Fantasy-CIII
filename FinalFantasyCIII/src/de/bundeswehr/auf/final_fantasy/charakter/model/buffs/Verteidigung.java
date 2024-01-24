package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Verteidigung extends Buff {

    public Verteidigung(Charakter charakter, int verteidigung) {
        super(charakter);
        this.verteidigung = verteidigung;
    }

    @Override
    public Buff apply() {
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setVerteidigung(charakter.getVerteidigung() - verteidigung);
        return this;
    }

}
