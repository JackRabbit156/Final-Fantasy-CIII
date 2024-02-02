package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Resistenz extends Buff {

    public Resistenz(Charakter charakter, int resistenz) {
        super(charakter, resistenz < 0);
        this.resistenz = resistenz;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von R=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getResistenz());
        charakter.setResistenz(charakter.getResistenz() + resistenz);
        DebugHelper.tracef("auf R=%d angewendet", charakter.getResistenz());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/resistenz.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von R=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getResistenz());
        charakter.setResistenz(charakter.getResistenz() - resistenz);
        DebugHelper.tracef("auf R=%d entfernt", charakter.getResistenz());
        return this;
    }
    
}
