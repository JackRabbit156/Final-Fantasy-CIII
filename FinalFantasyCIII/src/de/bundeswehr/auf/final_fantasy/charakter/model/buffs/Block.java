package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
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
        charakter.setVerteidigung(charakter.getVerteidigung() + physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + magischeAttacke);
        return this;
    }

    @Override
    public Buff remove() {
        charakter.setVerteidigung(charakter.getVerteidigung() - physischeAttacke);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - magischeAttacke);
        return this;
    }

    @Override
    public ImageView getIcon(int height) {
        ImageView iv = new ImageView(new Image("/icons/block.png", 0, height, true, true));
        Tooltip.install(iv, new Tooltip(getTooltip()));
        return iv;
    }

    @Override
    protected String getTooltip() {
        StringBuilder sb = new StringBuilder();
        sb.append("Blockt\n");
        sb.append(physischeAttacke);
        sb.append(" physischen Schaden\n");
        sb.append(magischeAttacke);
        sb.append(" magischen Schaden.");
        return sb.toString();
    }

}
