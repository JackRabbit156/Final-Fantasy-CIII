package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Abwehr extends Buff {

    public Abwehr(Charakter charakter, int verteidigung, int magischeVerteidigung) {
        super(charakter, verteidigung < 0 || magischeVerteidigung < 0);
        this.verteidigung = verteidigung;
        this.magischeVerteidigung = magischeVerteidigung;
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("%s auf %s von V=%d MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() + verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeVerteidigung);
        DebugHelper.tracef("auf V=%d MV=%d angewendet", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/Faehigkeiten/HLREinschuechterung.png", 0, height, true, true));
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("%s auf %s von V=%d MV=%d", debuff ? "Debuff" : "Buff", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() - verteidigung);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeVerteidigung);
        DebugHelper.tracef("auf V=%d MV=%d entfernt",  charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

}
