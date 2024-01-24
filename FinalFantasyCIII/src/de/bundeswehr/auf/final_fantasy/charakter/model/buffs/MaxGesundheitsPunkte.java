package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class MaxGesundheitsPunkte extends Buff {

    public MaxGesundheitsPunkte(Charakter charakter, int maxGesundheitsPunkte) {
        super(charakter, maxGesundheitsPunkte < 0);
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    @Override
    public Buff apply() {
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
        return this;
    }

    @Override
    public Buff remove() {
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
        return this;
    }

}
