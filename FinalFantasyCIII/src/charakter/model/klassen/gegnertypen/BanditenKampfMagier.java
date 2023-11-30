package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class BanditenKampfMagier extends Feind {

    public BanditenKampfMagier(int partyLevel) {
        super(partyLevel);
        super.setName("Banditen-Kampfmagier");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getWaffe());
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getRuestung());
        super.setAccessoires(new Accessoire[3]);
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), this.getLevel()));
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setManaPunkte(super.getMaxManaPunkte());
        super.setLevel(partyLevel);
        super.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(super.getKlasse().getBezeichnung(), partyLevel));
        super.setGrafischeDarstellung("           .WWWW.\n" +
                "          WWWW\"\"'\n" +
                "        .WWWW O O\n" +
                "     .WWWW\"WW.'-.\n" +
                "    WWWWWWWWWWWWW.\n" +
                "   WWWWWWWWWWWWWWW\n" +
                "   \"WWWWWWWWWW\"'\\___\n" +
                "    /  /__ __/\\___( \\\n" +
                "   (____( \\X(      /||\\\n" +
                "      / /||\\ \\\n" +
                "      \\______/\n" +
                "       \\ | \\ |\n" +
                "        )|  \\|\n" +
                "       (_|  /|\n" +
                "       |X| (X|\n" +
                "       |X| |X'._\n" +
                "      (__| (____)");
    }
}
