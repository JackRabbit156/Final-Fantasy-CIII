package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Beweglichkeit extends Buff {

    public Beweglichkeit(Charakter charakter, int beweglichkeit) {
        super(charakter);
        this.beweglichkeit = beweglichkeit;
    }

    @Override
    public Buff apply() {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + beweglichkeit);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() - beweglichkeit);
        return this;
    }

}
