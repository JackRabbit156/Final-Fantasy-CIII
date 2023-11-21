package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.PDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class BanditenKampfMagier extends Feind {

    public BanditenKampfMagier(int partyLevel) {
        super(partyLevel);
        super.setName("Banditen-Kampfmagier");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(super.getKlasse(), partyLevel));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(super.getKlasse(), partyLevel));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 2);
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
