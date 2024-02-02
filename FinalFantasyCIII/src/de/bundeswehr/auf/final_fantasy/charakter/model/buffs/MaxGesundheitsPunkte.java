package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class MaxGesundheitsPunkte extends Buff {

    public MaxGesundheitsPunkte(Charakter charakter, int maxGesundheitsPunkte) {
        super(charakter, maxGesundheitsPunkte < 0);
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von maxGP=%d GP=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMaxGesundheitsPunkte(), charakter.getGesundheitsPunkte());
        charakter.setMaxGesundheitsPunkte(charakter.getMaxGesundheitsPunkte() + maxGesundheitsPunkte);
        if (charakter.getMaxGesundheitsPunkte() < charakter.getGesundheitsPunkte()) {
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxGesundheitsPunkte nun
            // mehr aktuelle als maxGesundheitsPunkte hat, müssen die aktuellen HP auf den neuen
            // maxGesundheitsPunkte-Stand gesetzt werden (impliziert Schaden verursachen)
            charakter.setGesundheitsPunkte(charakter.getMaxGesundheitsPunkte());
        }
        else {
            // Die aktuellen Gesundheitspunkte müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxGesundheitsPunkte mit einem Heal einhergeht
            charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() + maxGesundheitsPunkte);
        }
        DebugHelper.tracef("auf maxGP=%d GP=%d angewendet", charakter.getMaxGesundheitsPunkte(), charakter.getGesundheitsPunkte());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von maxGP=%d GP=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMaxGesundheitsPunkte(), charakter.getGesundheitsPunkte());
        charakter.setMaxGesundheitsPunkte(charakter.getMaxGesundheitsPunkte() - maxGesundheitsPunkte);
        if (charakter.getMaxGesundheitsPunkte() < charakter.getGesundheitsPunkte()) {
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxGesundheitsPunkte nun
            // mehr aktuelle als maxGesundheitsPunkte hat, müssen die aktuellen HP auf den neuen
            // maxGesundheitsPunkte-Stand gesetzt werden (impliziert Schaden verursachen)
            charakter.setGesundheitsPunkte(charakter.getMaxGesundheitsPunkte());
        }
        else {
            // Die aktuellen Gesundheitspunkte müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxGesundheitsPunkte mit einem Heal einhergeht
            charakter.setGesundheitsPunkte(charakter.getGesundheitsPunkte() - maxGesundheitsPunkte);
        }
        DebugHelper.tracef("auf maxGP=%d GP=%d entfernt", charakter.getMaxGesundheitsPunkte(), charakter.getGesundheitsPunkte());
        return this;
    }

}
