package de.bundeswehr.auf.final_fantasy.charakter.model.klassen.gegnertypen;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.AusruestungsGegenstandFactory;

public class UntoterSamurai extends Feind {

    public UntoterSamurai(int partyLevel) {
        super(partyLevel);
        super.setName("Untoter Samurai");
        super.setKlasse(new PDD());
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
        super.setGrafischeDarstellung("charakter/feind/SF_UndeadSamurai.png");
    }
}
