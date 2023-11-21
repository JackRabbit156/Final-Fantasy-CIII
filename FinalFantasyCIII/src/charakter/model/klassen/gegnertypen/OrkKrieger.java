package charakter.model.klassen.gegnertypen;


import charakter.model.Feind;
import charakter.model.klassen.PDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkKrieger extends Feind {

    public OrkKrieger(int partyLevel) {
        super(partyLevel);
        super.setName("Ork-Krieger");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(super.getKlasse(), partyLevel));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(super.getKlasse(), partyLevel));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 2);
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
