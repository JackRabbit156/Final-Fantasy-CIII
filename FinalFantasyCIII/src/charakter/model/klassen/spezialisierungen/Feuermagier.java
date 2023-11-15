package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Feuermagier extends Spezialisierung{

    public Feuermagier(Charakter charakter){
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + 4);
        charakter.setMagischeVerteidigung(charakter.getMagischeAttacke() - 2);
        charakter.setGenauigkeit(charakter.getGenauigkeit() - 1);
    }
}
