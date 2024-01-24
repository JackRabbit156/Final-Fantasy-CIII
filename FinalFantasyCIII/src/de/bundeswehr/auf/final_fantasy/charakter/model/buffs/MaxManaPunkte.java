package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;

public class MaxManaPunkte extends Buff {

    public MaxManaPunkte(Charakter charakter, int maxManaPunkte) {
        super(charakter, maxManaPunkte < 0);
        this.maxManaPunkte = maxManaPunkte;
    }

    @Override
    public Buff apply() {
        DebugHelper.logf("%s auf %s von maxMP=%d MP=%d", debuff ? "Debuff" : "Buff", charakter.getName(),
                charakter.getMaxManaPunkte(), charakter.getManaPunkte());
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
        DebugHelper.logf("auf maxMP=%d MP=%d angewendet", charakter.getMaxManaPunkte(), charakter.getManaPunkte());
        return this;
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("%s auf %s von maxMP=%d MP=%d", debuff ? "Debuff" : "Buff", charakter.getName(),
                charakter.getMaxManaPunkte(), charakter.getManaPunkte());
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
        DebugHelper.logf("auf maxMP=%d MP=%d entfernt", charakter.getMaxManaPunkte(), charakter.getManaPunkte());
        return this;
    }

}
