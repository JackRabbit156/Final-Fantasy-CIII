package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class ManaRegeneration extends Buff {

    public ManaRegeneration(Charakter charakter, int manaRegeneration) {
        super(charakter, manaRegeneration < 0);
        this.manaRegeneration = manaRegeneration;
    }

    @Override
    public Buff apply() {
        charakter.setManaRegeneration(charakter.getManaRegeneration() + manaRegeneration);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setManaRegeneration(charakter.getManaRegeneration() - manaRegeneration);
        return this;
    }

}
