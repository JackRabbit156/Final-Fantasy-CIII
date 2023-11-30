package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.HLR;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class OrkSchamane extends Feind {

    public OrkSchamane(int partyLevel) {
        super(partyLevel);
        super.setName("Ork-Schame");
        super.setKlasse(new HLR());
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
        super.setGrafischeDarstellung("\t\t   .....\n" +
                "\t\t  C C  /\n" +
                "\t\t /<   /\n" +
                "  ___ __________/_#__=o\n" +
                " /(- /(\\_\\________   \\\n" +
                " \\ ) \\ )_      \\o     \\\n" +
                " /|\\ /|\\       |'     |\n" +
                "\t        |     _|\n" +
                "\t        /o   __\\\n" +
                "\t       / '     |\n" +
                "\t       / /      |\n" +
                "\t       /_/\\______|\n" +
                "\t       (   _(    <\n" +
                "\t        \\    \\    \\\n" +
                "\t         \\    \\    |\n" +
                "\t          \\____\\____\\\n" +
                "\t        ____\\_\\__\\_\\\n" +
                "\t       /`   /`     o\\\n" +
                "\t       |___ |_______|");
    }
}
