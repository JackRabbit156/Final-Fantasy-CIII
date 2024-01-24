package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class Beweglichkeit extends Buff {

    public Beweglichkeit(Charakter charakter, int beweglichkeit) {
        super(charakter, beweglichkeit < 0);
        this.beweglichkeit = beweglichkeit;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von B=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getBeweglichkeit());
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + beweglichkeit);
        DebugHelper.logf("auf B=%d angewendet", charakter.getBeweglichkeit());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von B=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getBeweglichkeit());
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() - beweglichkeit);
        DebugHelper.logf("auf B=%d entfernt", charakter.getBeweglichkeit());
        return this;
    }

}
