package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class MagischeVerteidigung extends Buff {

    public MagischeVerteidigung(Charakter charakter, int magischeVerteidigung) {
        super(charakter, magischeVerteidigung < 0);
        this.magischeVerteidigung = magischeVerteidigung;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeVerteidigung());
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung);
        DebugHelper.tracef("auf MV=%d angewendet", charakter.getMagischeVerteidigung());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeVerteidigung());
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeVerteidigung);
        DebugHelper.tracef("auf MV=%d entfernt", charakter.getMagischeVerteidigung());
        return this;
    }

}
