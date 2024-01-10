package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;

public abstract class Manatrank extends Verbrauchsgegenstand {

    int manaregenartion;

    /**
     * Wendet den Gegenstand auf den Charakter an
     * @param spielerCharakter zielcharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    @Override
    public void gegenstandVerwenden(SpielerCharakter spielerCharakter) {
        if (spielerCharakter.getManaPunkte() + this.manaregenartion > spielerCharakter.getMaxManaPunkte()){
            spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
        } else {
            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + this.manaregenartion);
        }
    }

    public int getManaregenartion() {
        return manaregenartion;
    }

    public void setManaregenartion(int manaregenartion) {
        this.manaregenartion = manaregenartion;
    }
}
