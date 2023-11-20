package gegenstand.verbrauchsgegenstand.Manatränke;

import charakter.model.SpielerCharakter;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

public class Manatrank extends Verbrauchsgegenstand {

    int manaregenartion;

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
