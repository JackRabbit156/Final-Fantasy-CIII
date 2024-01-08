package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.HLR;
import trainer.faehigkeiten.FaehigkeitFactory;
import gegenstaende.ausruestung.Accessoire;
import gegenstaende.ausruestung.AusruestungsGegenstandFactory;

public class Krankenschwester extends Feind {

    public Krankenschwester(int partyLevel) {
        super(partyLevel);
        super.setName("Krankenschwester");
        super.setKlasse(new HLR());
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
        super.setGrafischeDarstellung("charaktere/feind/SF_Nurse.png");
    }
}
