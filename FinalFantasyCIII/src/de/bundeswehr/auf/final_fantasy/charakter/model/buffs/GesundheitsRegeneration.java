package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GesundheitsRegeneration extends Buff {

    public GesundheitsRegeneration(Charakter charakter, int gesundheitsRegeneration) {
        super(charakter, gesundheitsRegeneration < 0);
        this.gesundheitsRegeneration = gesundheitsRegeneration;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von GR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGesundheitsRegeneration());
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() + gesundheitsRegeneration);
        DebugHelper.tracef("auf GR=%d angewendet", charakter.getGesundheitsRegeneration());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/gesundheitsRegeneration.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von GR=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGesundheitsRegeneration());
        charakter.setGesundheitsRegeneration(charakter.getGesundheitsRegeneration() - gesundheitsRegeneration);
        DebugHelper.tracef("auf GR=%d entfernt", charakter.getGesundheitsRegeneration());
        return this;
    }

}
