package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Resistenz extends Buff {

    public Resistenz(Charakter charakter, int resistenz) {
        super(charakter);
        this.resistenz = resistenz;
    }

    @Override
    public Buff apply() {
        charakter.setResistenz(charakter.getResistenz() + resistenz);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setResistenz(charakter.getResistenz() - resistenz);
        return this;
    }
    
}
