package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class GesundheitsRegeneration extends Buff {

    public GesundheitsRegeneration(Charakter charakter, int gesundheitsRegeneration) {
        super(charakter, gesundheitsRegeneration < 0);
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von GR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGesundheitsRegeneration());
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() + gesundheitsRegeneration);
        DebugHelper.logf("auf GR=%d angewendet", charakter.getGesundheitsRegeneration());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von GR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGesundheitsRegeneration());
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() - gesundheitsRegeneration);
        DebugHelper.logf("auf GR=%d entfernt", charakter.getGesundheitsRegeneration());
        return this;
    }

}
