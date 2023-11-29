package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class BanditenTank extends Feind {

    public BanditenTank(int partyLevel) {
        super(partyLevel);
        super.setName("Banditen-Tank");
        super.setKlasse(new TNK());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getWaffe());
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getRuestung());
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 0);
        FeindController.ausruestungAnlegen(this, this.getAccessoire(0));
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 1);
        FeindController.ausruestungAnlegen(this, this.getAccessoire(1));
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this.getKlasse(), partyLevel), 2);
        FeindController.ausruestungAnlegen(this, this.getAccessoire(2));
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
