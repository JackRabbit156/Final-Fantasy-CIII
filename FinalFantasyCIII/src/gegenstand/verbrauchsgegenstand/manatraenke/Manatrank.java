package gegenstand.verbrauchsgegenstand.manatraenke;

import charakter.model.SpielerCharakter;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

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
