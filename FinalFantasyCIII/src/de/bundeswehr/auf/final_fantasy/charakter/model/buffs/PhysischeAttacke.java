package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class PhysischeAttacke extends Buff {

    public PhysischeAttacke(Charakter charakter, int physischeAttacke) {
        super(charakter, physischeAttacke < 0);
        this.physischeAttacke = physischeAttacke;
    }

    @Override
    public Buff apply() {
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + physischeAttacke);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() - physischeAttacke);
        return this;
    }

}
