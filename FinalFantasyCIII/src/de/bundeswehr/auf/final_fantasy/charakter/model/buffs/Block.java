package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends Buff {

    public Block(Charakter charakter) {
        super(charakter, false);
        this.physischeAttacke = charakter.getPhysischeAttacke();
        this.magischeAttacke = charakter.getMagischeAttacke();
    }

    @Override
    public Buff apply() {
        DebugHelper.tracef("Buff auf %s von V=%d MV=%d", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() + physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeAttacke);
        DebugHelper.tracef("auf V=%d MV=%d angewendet", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        return new ImageView(new Image("/icons/block.png", 0, height, true, true));
    }

    @Override
    public String getTooltip() {
        return "Blockt\n" +
                physischeAttacke + " physischen Schaden\n" +
                magischeAttacke + " magischen Schaden.";
    }

    @Override
    public Buff remove() {
        DebugHelper.tracef("Buff auf %s von V=%d MV=%d", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() - physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeAttacke);
        DebugHelper.tracef("auf V=%d MV=%d entfernt", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

}
