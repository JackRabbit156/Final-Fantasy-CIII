package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class GesundheitsRegeneration extends Buff {

    public GesundheitsRegeneration(Charakter charakter, int gesundheitsRegeneration) {
        super(charakter, gesundheitsRegeneration < 0);
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    @Override
    public Buff apply() {
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() + gesundheitsRegeneration);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() - gesundheitsRegeneration);
        return this;
    }

}
