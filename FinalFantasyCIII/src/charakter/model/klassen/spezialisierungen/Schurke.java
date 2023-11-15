package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;


public class Schurke extends Spezialisierung{

    public Schurke(Charakter charakter) {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + 3);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + 2);
        charakter.setVerteidigung(charakter.getVerteidigung() - 4);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + 4);
    }
}
