package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class Abwehr extends Buff {

    public Abwehr(Charakter charakter, int verteidigung, int magischeVerteidigung) {
        super(charakter, verteidigung < 0 || magischeVerteidigung < 0);
        this.verteidigung = verteidigung;
        this.magischeVerteidigung = magischeVerteidigung;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von V=%d MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung);
        DebugHelper.logf("auf V=%d MV=%d angewendet", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von V=%d MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() - verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeVerteidigung);
        DebugHelper.logf("auf V=%d MV=%d entfernt",  charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

}
