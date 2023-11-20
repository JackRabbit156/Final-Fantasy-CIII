package gegenstand.verbrauchsgegenstand;

import charakter.model.SpielerCharakter;
import gegenstand.Gegenstand;

public abstract class Verbrauchsgegenstand extends Gegenstand {

    private String beschereibung;


    public abstract void gegenstandVerwenden(SpielerCharakter spielerCharakter);

    public String getBeschereibung() {
        return beschereibung;
    }

    public void setBeschereibung(String beschereibung) {
        this.beschereibung = beschereibung;
    }
}
