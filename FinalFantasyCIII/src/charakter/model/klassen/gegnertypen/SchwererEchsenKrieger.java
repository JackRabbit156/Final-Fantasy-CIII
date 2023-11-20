package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class SchwererEchsenKrieger extends Feind {

    public SchwererEchsenKrieger(int partyLevel) {
        super(partyLevel);
        this.setName("Schwerer-Echsenkrieger");
        this.setKlasse(new TNK());
        this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        this.setAccessoires(new Accessoire[3]);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 0);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 1);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 2);
        this.setGrafischeDarstellung("Dummy Schwerer-Echsenkrieger");
        this.setLevel(partyLevel);
        this.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getKlasse().getBezeichnung(), partyLevel));
    }
}
