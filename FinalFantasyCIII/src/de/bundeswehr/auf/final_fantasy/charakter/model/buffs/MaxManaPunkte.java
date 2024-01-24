package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class MaxManaPunkte extends Buff {

    public MaxManaPunkte(Charakter charakter, int maxManaPunkte) {
        super(charakter, maxManaPunkte < 0);
        this.maxManaPunkte = maxManaPunkte;
    }

    @Override
    public Buff apply() {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + maxManaPunkte);
        if (charakter.getMaxManaPunkte() < charakter.getManaPunkte()) {
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxManaPunkte nun
            // mehr aktuelle als maxManaPunkte hat, müssen die aktuellen MP auf den neuen
            // maxManaPunkte-Stand gesetzt werden (impliziert Reduktion der MP)
            charakter.setManaPunkte(charakter.getMaxManaPunkte());
        }
        else {
            // Die aktuellen MP müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxManaPunkte diese mit anhebt
            charakter.setManaPunkte(charakter.getManaPunkte() + maxManaPunkte);
        }
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() - maxManaPunkte);
        if (charakter.getMaxManaPunkte() < charakter.getManaPunkte()) {
            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxManaPunkte nun
            // mehr aktuelle als maxManaPunkte hat, müssen die aktuellen MP auf den neuen
            // maxManaPunkte-Stand gesetzt werden (impliziert Reduktion der MP)
            charakter.setManaPunkte(charakter.getMaxManaPunkte());
        }
        else {
            // Die aktuellen MP müssen um den gleichen Wert erhöht werden
            // da die Erhöhung der maxManaPunkte diese mit anhebt
            charakter.setManaPunkte(charakter.getManaPunkte() - maxManaPunkte);
        }
        return this;
    }

}
