package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Genauigkeit extends Buff {

    public Genauigkeit(Charakter charakter, int genauigkeit) {
        super(charakter);
        this.genauigkeit = genauigkeit;
    }

    @Override
    public Buff apply() {
        charakter.setGenauigkeit(charakter.getGenauigkeit() + genauigkeit);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setGenauigkeit(charakter.getGenauigkeit() - genauigkeit);
        return this;
    }
    
}
