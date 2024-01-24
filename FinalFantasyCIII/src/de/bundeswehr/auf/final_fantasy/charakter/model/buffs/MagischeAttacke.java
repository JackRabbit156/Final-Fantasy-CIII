package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class MagischeAttacke extends Buff {

    public MagischeAttacke(Charakter charakter, int magischeAttacke) {
        super(charakter, magischeAttacke < 0);
        this.magischeAttacke = magischeAttacke;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von MA=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeAttacke());
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + magischeAttacke);
        DebugHelper.logf("auf MA=%d angewendet", charakter.getMagischeAttacke());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von MA=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeAttacke());
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() - magischeAttacke);
        DebugHelper.logf("auf MA=%d entfernt", charakter.getMagischeAttacke());
        return this;
    }

}
