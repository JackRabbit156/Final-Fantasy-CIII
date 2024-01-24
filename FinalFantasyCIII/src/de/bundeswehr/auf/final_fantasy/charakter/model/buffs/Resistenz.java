package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class Resistenz extends Buff {

    public Resistenz(Charakter charakter, int resistenz) {
        super(charakter, resistenz < 0);
        this.resistenz = resistenz;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von R=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getResistenz());
        charakter.setResistenz(charakter.getResistenz() + resistenz);
        DebugHelper.logf("auf R=%d angewendet", charakter.getResistenz());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von R=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getResistenz());
        charakter.setResistenz(charakter.getResistenz() - resistenz);
        DebugHelper.logf("auf R=%d entfernt", charakter.getResistenz());
        return this;
    }
    
}
