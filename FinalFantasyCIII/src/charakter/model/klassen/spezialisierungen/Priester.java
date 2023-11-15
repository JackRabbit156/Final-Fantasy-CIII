package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Priester extends Spezialisierung{

    public Priester(Charakter charakter){
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + 10);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + 5);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung()+ 5);
    }
}
