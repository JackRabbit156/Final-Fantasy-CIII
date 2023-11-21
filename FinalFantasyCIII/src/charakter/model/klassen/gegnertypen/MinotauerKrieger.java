package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.PDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class MinotauerKrieger extends Feind {

    public MinotauerKrieger(int partyLevel) {
        super(partyLevel);
        super.setName("Minotauer-Krieger");
        super.setKlasse(new PDD());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(super.getKlasse(), partyLevel));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(super.getKlasse(), partyLevel));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 2);
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
