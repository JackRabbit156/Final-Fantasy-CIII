package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import javafx.scene.control.Tooltip;
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
        DebugHelper.logf("Buff auf %s von V=%d MV=%d", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() + physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeAttacke);
        DebugHelper.logf("auf V=%d MV=%d angewendet", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        ImageView iv = new ImageView(new Image("/icons/block.png", 0, height, true, true));
        Tooltip.install(iv, new Tooltip(getTooltip()));
        return iv;
    }

    @Override
    public String getTooltip() {
        StringBuilder sb = new StringBuilder();
        sb.append("Blockt\n");
        sb.append(physischeAttacke);
        sb.append(" physischen Schaden\n");
        sb.append(magischeAttacke);
        sb.append(" magischen Schaden.");
        return sb.toString();
    }

    @Override
    public Buff remove() {
        DebugHelper.logf("Buff auf %s von V=%d MV=%d", charakter.getName(), charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        charakter.setVerteidigung(charakter.getVerteidigung() - physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeAttacke);
        DebugHelper.logf("auf V=%d MV=%d entfernt", charakter.getVerteidigung(), charakter.getMagischeVerteidigung());
        return this;
    }

}
