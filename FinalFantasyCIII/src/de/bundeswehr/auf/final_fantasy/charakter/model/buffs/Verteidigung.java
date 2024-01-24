package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class Verteidigung extends Buff {

    public Verteidigung(Charakter charakter, int verteidigung) {
        super(charakter, verteidigung < 0);
        this.verteidigung = verteidigung;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von V=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung);
        DebugHelper.logf("auf V=%d angewendet", charakter.getVerteidigung());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von V=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() - verteidigung);
        DebugHelper.logf("auf V=%d entfernt", charakter.getVerteidigung());
        return this;
    }

}
