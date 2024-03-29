package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Genauigkeit extends Buff {

    public Genauigkeit(Charakter charakter, int genauigkeit) {
        super(charakter, genauigkeit < 0);
        this.genauigkeit = genauigkeit;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von G=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGenauigkeit());
        charakter.setGenauigkeit(charakter.getGenauigkeit() + genauigkeit);
        DebugHelper.tracef("auf G=%d angewendet", charakter.getGenauigkeit());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/genauigkeit.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von G=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getGenauigkeit());
        charakter.setGenauigkeit(charakter.getGenauigkeit() - genauigkeit);
        DebugHelper.tracef("auf G=%d entfernt", charakter.getGenauigkeit());
        return this;
    }
    
}
