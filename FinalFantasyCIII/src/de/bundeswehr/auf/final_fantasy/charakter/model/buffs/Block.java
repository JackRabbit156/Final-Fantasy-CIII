package de.bundeswehr.auf.final_fantasy.charakter.model.buffs;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;

public class Block extends Buff {

    public Block(Charakter charakter) {
        super(charakter);
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

}
