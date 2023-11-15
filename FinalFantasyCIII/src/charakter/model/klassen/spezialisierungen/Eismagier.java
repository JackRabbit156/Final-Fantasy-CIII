package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Eismagier extends Spezialisierung{

    public Eismagier(Charakter charakter) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + 2);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + 5);
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + 1);
    }
}
