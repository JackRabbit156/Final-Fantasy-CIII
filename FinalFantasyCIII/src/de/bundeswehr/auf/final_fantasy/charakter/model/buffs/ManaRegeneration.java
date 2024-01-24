package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class ManaRegeneration extends Buff {

    public ManaRegeneration(Charakter charakter, int manaRegeneration) {
        super(charakter, manaRegeneration < 0);
        this.manaRegeneration = manaRegeneration;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von MR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getManaRegeneration());
        charakter.setManaRegeneration(charakter.getManaRegeneration() + manaRegeneration);
        DebugHelper.logf("auf MR=%d angewendet", charakter.getManaRegeneration());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von MR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getManaRegeneration());
        charakter.setManaRegeneration(charakter.getManaRegeneration() - manaRegeneration);
        DebugHelper.logf("auf MR=%d entfernt", charakter.getManaRegeneration());
        return this;
    }

}
