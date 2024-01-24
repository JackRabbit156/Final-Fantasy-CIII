package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class PhysischeAttacke extends Buff {

    public PhysischeAttacke(Charakter charakter, int physischeAttacke) {
        super(charakter, physischeAttacke < 0);
        this.physischeAttacke = physischeAttacke;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von A=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getPhysischeAttacke());
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + physischeAttacke);
        DebugHelper.logf("auf A=%d angewendet", charakter.getPhysischeAttacke());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von A=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getPhysischeAttacke());
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() - physischeAttacke);
        DebugHelper.logf("auf A=%d entfernt", charakter.getPhysischeAttacke());
        return this;
    }

}
