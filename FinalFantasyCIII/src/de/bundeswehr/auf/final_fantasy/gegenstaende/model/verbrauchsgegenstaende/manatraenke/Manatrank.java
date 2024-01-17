package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;

public abstract class Manatrank extends Verbrauchsgegenstand {

    int manaRegeneration;

    /**
     * Wendet den Gegenstand auf den Charakter an
     * @param spielerCharakter zielcharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    @Override
    public void gegenstandVerwenden(SpielerCharakter spielerCharakter) {
        if (spielerCharakter.getManaPunkte() + this.manaRegeneration > spielerCharakter.getMaxManaPunkte()){
            spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        } else {
            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + this.manaRegeneration);
        }
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }
}
