package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhysischeAttacke extends Buff {

    public PhysischeAttacke(Charakter charakter, int physischeAttacke) {
        super(charakter, physischeAttacke < 0);
        this.physischeAttacke = physischeAttacke;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von A=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getPhysischeAttacke());
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + physischeAttacke);
        DebugHelper.tracef("auf A=%d angewendet", charakter.getPhysischeAttacke());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/physischeAttacke.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von A=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getPhysischeAttacke());
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() - physischeAttacke);
        DebugHelper.tracef("auf A=%d entfernt", charakter.getPhysischeAttacke());
        return this;
    }

}
