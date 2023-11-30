package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkKampfMagier extends Feind {

    public OrkKampfMagier(int partyLevel) {
        super(partyLevel);
        super.setName("Ork-Kampfmagier");
        super.setKlasse(new PDD());
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
