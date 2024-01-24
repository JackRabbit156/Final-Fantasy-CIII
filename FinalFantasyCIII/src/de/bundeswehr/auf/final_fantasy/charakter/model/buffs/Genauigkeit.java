package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class Genauigkeit extends Buff {

    public Genauigkeit(Charakter charakter, int genauigkeit) {
        super(charakter, genauigkeit < 0);
        this.genauigkeit = genauigkeit;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von G=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGenauigkeit());
        charakter.setGenauigkeit(charakter.getGenauigkeit() + genauigkeit);
        DebugHelper.logf("auf G=%d angewendet", charakter.getGenauigkeit());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von G=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGenauigkeit());
        charakter.setGenauigkeit(charakter.getGenauigkeit() - genauigkeit);
        DebugHelper.logf("auf G=%d entfernt", charakter.getGenauigkeit());
        return this;
    }
    
}
