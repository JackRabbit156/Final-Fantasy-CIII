package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;

public class Paladin extends Spezialisierung{

    public Paladin(Charakter charakter){
        charakter.getKlasse().setGewichtsklasse("Sehr Schwer");
        charakter.getKlasse().setRuestungstyp("Sehr schwer");
    }
}
