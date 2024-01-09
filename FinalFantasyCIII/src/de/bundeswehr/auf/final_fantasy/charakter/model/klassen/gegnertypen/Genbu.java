package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.gegnertypen;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.AusruestungsGegenstandFactory;

public class Genbu extends Feind {

    public Genbu(int partyLevel) {
        super(partyLevel);
        super.setName("Genbu");
        super.setKlasse(new TNK());
        super.setWaffe(AusruestungsGegenstandFactory.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getWaffe());
        super.setRuestung(AusruestungsGegenstandFactory.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getRuestung());
        super.setAccessoires(new Accessoire[3]);
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setManaPunkte(super.getMaxManaPunkte());
        super.setLevel(partyLevel);
        super.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(super.getKlasse().getBezeichnung(), partyLevel));
        super.setGrafischeDarstellung("charakter/feind/SF_Genbu.png");
    }
}
