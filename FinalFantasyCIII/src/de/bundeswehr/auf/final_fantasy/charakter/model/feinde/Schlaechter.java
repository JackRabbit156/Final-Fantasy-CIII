package de.bundeswehr.auf.final_fantasy.charakter.model.feinde;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;

public class Schlaechter extends Feind {

    public Schlaechter(int partyLevel) {
        super(partyLevel);
        setName("Schlächter");
        setKlasse(new TNK());
        setWaffe(AusruestungsGegenstandFactory.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getWaffe());
        setRuestung(AusruestungsGegenstandFactory.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getRuestung());
        setAccessoires(new Accessoire[3]);
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        setGesundheitsPunkte(getMaxGesundheitsPunkte());
        setManaPunkte(getMaxManaPunkte());
        setLevel(partyLevel);
        setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(getKlasse().getBezeichnung(), partyLevel));
        setGrafischeDarstellung("charakter/feind/SF_Butcher.png");
    }
}
