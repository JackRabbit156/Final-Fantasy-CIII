package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class MagischeAttacke extends Buff {

    public MagischeAttacke(Charakter charakter, int magischeAttacke) {
        super(charakter, magischeAttacke < 0);
        this.magischeAttacke = magischeAttacke;
    }

    @Override
    public Buff apply() {
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + magischeAttacke);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() - magischeAttacke);
        return this;
    }

}
