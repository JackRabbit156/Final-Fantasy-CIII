package charakter.model.klassen.gegnertypen;

import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkKampfMagier extends Feind {

    public OrkKampfMagier(int partyLevel) {
        super(partyLevel);
        super.setName("Ork-Kampfmagier");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(super.getKlasse(), partyLevel));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(super.getKlasse(), partyLevel));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 2);
        super.setLevel(partyLevel);
        super.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(super.getKlasse().getBezeichnung(), partyLevel));
        super.setGrafischeDarstellung("              *\n" +
                "             / \\\n" +
                "            /___\\\n" +
                "           ( o o )            * *\n" +
                "           )  L  (           /   * *\n" +
                "   ________()(-)()________  /     * * *\n" +
                " E\\| _____ )()()() ______ |/B     * * *\n" +
                "   |/      ()()()(       \\|      * * * *\n" +
                "           | )() |\n" +
                "           /     \\\n" +
                "          / *  *  \\\n" +
                "         /   *  *  \\\n" +
                "        / *_  *  _  \\   \n" +
                "        ~~/_|~~~|_\\~~");
    }
}
