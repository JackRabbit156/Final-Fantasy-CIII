package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Rabauke extends Spezialisierung{

    public Rabauke(Charakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() - 2);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + 3);
        charakter.getKlasse().setGewichtsklasse("Mittel");
        charakter.getKlasse().setRuestungstyp("Mittel");
    }
}
