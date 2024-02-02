package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ManaRegeneration extends Buff {

    public ManaRegeneration(Charakter charakter, int manaRegeneration) {
        super(charakter, manaRegeneration < 0);
        this.manaRegeneration = manaRegeneration;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von MR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getManaRegeneration());
        charakter.setManaRegeneration(charakter.getManaRegeneration() + manaRegeneration);
        DebugHelper.tracef("auf MR=%d angewendet", charakter.getManaRegeneration());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/manaRegeneration.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von MR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getManaRegeneration());
        charakter.setManaRegeneration(charakter.getManaRegeneration() - manaRegeneration);
        DebugHelper.tracef("auf MR=%d entfernt", charakter.getManaRegeneration());
        return this;
    }

}
