package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MagischeAttacke extends Buff {

    public MagischeAttacke(Charakter charakter, int magischeAttacke) {
        super(charakter, magischeAttacke < 0);
        this.magischeAttacke = magischeAttacke;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von MA=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeAttacke());
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + magischeAttacke);
        DebugHelper.tracef("auf MA=%d angewendet", charakter.getMagischeAttacke());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/magischeAttacke.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von MA=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getMagischeAttacke());
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() - magischeAttacke);
        DebugHelper.tracef("auf MA=%d entfernt", charakter.getMagischeAttacke());
        return this;
    }

}
