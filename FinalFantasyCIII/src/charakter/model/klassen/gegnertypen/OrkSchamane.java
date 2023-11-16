package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.HLR;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkSchamane extends Feind {

    public OrkSchamane(int partyLevel) {
        super(partyLevel);
        this.setName("Ork-Schame");
        this.setKlasse(new HLR());
        this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        this.setAccessoires(new Accessoire[3]);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 0);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 1);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 2);
        this.setGrafischeDarstellung("Dummy Ork-Schame");
    }
}
