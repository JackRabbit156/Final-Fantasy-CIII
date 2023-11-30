package charakter.model.klassen.gegnertypen;


import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkKrieger extends Feind {

    public OrkKrieger(int partyLevel) {
        super(partyLevel);
        super.setName("Ork-Krieger");
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
        super.setGrafischeDarstellung("          ___\n" +
                "        ,-----,    /~~.\n" +
                "       /\\|   |/\\  ;   '\n" +
                "      |-- \\_/ --|/\n" +
                "   .-----/   \\-/---.\n" +
                "  /   ,   . ./  ,   \\\n" +
                " /   )|__. / .__|'\\, \\\n" +
                "; .'`  | / |   |  /` ;\n" +
                " \\  \\_\\/`-- --'(/_ /\n" +
                "   \\  > `-----'|_|\n" +
                "   /~~ [Y~YYY~Y]\n" +
                "./     !!\\YYY/!!\n" +
                "       ;   Y   :\n" +
                "       1`  |  '1\n" +
                "       : ` | ' ;");
    }
}
