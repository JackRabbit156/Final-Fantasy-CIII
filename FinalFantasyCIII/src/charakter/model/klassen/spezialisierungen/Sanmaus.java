package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Sanmaus extends Spezialisierung{

    public Sanmaus(Charakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() + 5);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + 15);
        charakter.setPhysischeAttacke(0);
    }
}
