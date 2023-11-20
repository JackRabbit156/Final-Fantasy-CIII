package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.PDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class BanditenKampfMagier extends Feind {

    public BanditenKampfMagier(int partyLevel) {
        super(partyLevel);
        this.setName("Banditen-Kampfmagier");
        this.setKlasse(new PDD());
        this.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        this.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        this.setAccessoires(new Accessoire[3]);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 0);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 1);
        this.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 2);
        this.setGrafischeDarstellung("Dummy Banditen-Kampfmagier");
        this.setLevel(partyLevel);
        this.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(this.getKlasse().getBezeichnung(), partyLevel));
    }
}
