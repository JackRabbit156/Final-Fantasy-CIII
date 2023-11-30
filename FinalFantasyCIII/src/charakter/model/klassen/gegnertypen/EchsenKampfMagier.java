package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class EchsenKampfMagier extends Feind {

    public EchsenKampfMagier(int partyLevel) {
        super(partyLevel);
        super.setName("Echsen-Kampfmagier");
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
        super.setGrafischeDarstellung("         ___\n" +
                "       ,-----,\n" +
                "      /\\|   |/\\\n" +
                "     |-- \\_/ --|\n" +
                "  .-----/   \\-----.\n" +
                " /   ,   . .   ,   \\\n" +
                "/  /`|    |    |'\\, \\\n" +
                "`\\ \\  \\-  |  -/  /`/'\n" +
                "  `\\\\_)`-- --'(_//\n" +
                "    |_|`-- --'|_|  _______\n" +
                "     ,'`-   -'`.,-'       `-.\n" +
                "    |\\--------/||            `-.      _,------.\n" +
                "   |\\---------/`|    .--.       `----'   ___--.`--.\n" +
                "    |\\---------/\\. .\"    `.            ,'      `---'\n" +
                "     ``-._______.-'        `-._______.-'");
    }
}
