package gegenstand.verbrauchsgegenstand.heiltraenke;

import charakter.model.SpielerCharakter;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

public abstract class Heiltrank extends Verbrauchsgegenstand {

    private int heilwert;


    @Override
    public void gegenstandVerwenden(SpielerCharakter spielerCharakter) {
        if (spielerCharakter.getMaxGesundheitsPunkte() + this.getHeilwert() > spielerCharakter.getMaxGesundheitsPunkte()){
            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
        } else {
            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + this.getHeilwert());
        }
    }

    public int getHeilwert() {
        return heilwert;
    }

    public void setHeilwert(int heilwert) {
        this.heilwert = heilwert;
    }
}
