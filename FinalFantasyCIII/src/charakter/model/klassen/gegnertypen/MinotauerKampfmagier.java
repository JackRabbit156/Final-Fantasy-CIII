package charakter.model.klassen.gegnertypen;

import charakter.controller.FeindController;
import charakter.model.Feind;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class MinotauerKampfmagier extends Feind {

    public MinotauerKampfmagier(int partyLevel) {
        super(partyLevel);
        super.setName("Minotauer-Kampfmagier");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getWaffe());
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(this.getKlasse(), partyLevel));
        FeindController.ausruestungAnlegen(this, this.getRuestung());
        super.setAccessoires(new Accessoire[3]);
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this, this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this, this.getLevel()));
        FeindController.ausruestungAnlegen(this, AusruestungsgegenstandFabrik.erstelleAccessoireFuer(this, this.getLevel()));
        super.setGesundheitsPunkte(super.getMaxGesundheitsPunkte());
        super.setManaPunkte(super.getMaxManaPunkte());
        super.setLevel(partyLevel);
        super.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(super.getKlasse().getBezeichnung(), partyLevel));
        super.setGrafischeDarstellung("\t         -\"\"\\\n" +
                "    .-\"  .`)     (\n" +
                "   j   .'_+     :[                )      .^--..\n" +
                "  i    -\"       |l                ].    /      i\n" +
                " ,\" .:j         `8o  _,,+.,.--,   d|   `:::;    b\n" +
                " i  :'|          \"88p;.  (-.\"_\"-.oP        \\.   :\n" +
                " ; .  (            >,%%%   f),):8\"          \\:'  i\n" +
                "i  :: j          ,;%%%:; ; ; i:%%%.,        i.   `.\n" +
                "i  `: ( ____  ,-::::::' ::j  [:```          [8:   )\n" +
                "<  ..``'::::8888oooooo.  :(jj(,;,,,         [8::  <\n" +
                "`. ``:.      oo.8888888888:;%%%8o.::.+888+o.:`:'  |\n" +
                " `.   `        `o`88888888b`%%%%%88< Y888P\"\"'-    ;\n" +
                "   \"`---`.       Y`888888888;;.,\"888b.\"\"\"..::::'-'\n" +
                "          \"-....  b`8888888:::::.`8888._::-\"\n" +
                "             `:::. `:::::O:::::::.`%%'|\n" +
                "              `.      \"``::::::''    .'\n" +
                "                `.                   <\n" +
                "                  +:         `:   -';\n" +
                "                   `:         : .::/\n" +
                "                    ;+_  :::. :..;;;        \n" +
                "                    ;;;;,;;;;;;;;,;;");
    }
}
