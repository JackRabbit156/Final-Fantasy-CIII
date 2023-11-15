package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Beserker extends Spezialisierung{

    public Beserker(Charakter charakter){
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + 1);
        charakter.setGenauigkeit(charakter.getGenauigkeit() - 1);
        charakter.setVerteidigung(charakter.getVerteidigung() - 2);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + 3);
    }
}
