package charakter.model.klassen.gegnertypen;

import charakter.model.Feind;
import charakter.model.klassen.TNK;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;

public class GepanzerterMinotauer extends Feind {

    public GepanzerterMinotauer(int partyLevel) {
        super(partyLevel);
        super.setName("Gepanzerter-Minotauer");
        super.setKlasse(new TNK());
        super.setWaffe(AusruestungsgegenstandFabrik.erstelleWaffeFuer(super.getKlasse(), partyLevel));
        super.setRuestung(AusruestungsgegenstandFabrik.erstelleRuestungFuer(super.getKlasse(), partyLevel));
        super.setAccessoires(new Accessoire[3]);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 0);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 1);
        super.setAccessoire(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(super.getKlasse(), partyLevel), 2);
        super.setLevel(partyLevel);
        super.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(super.getKlasse().getBezeichnung(), partyLevel));
        super.setGrafischeDarstellung("                                                                _\n" +
                "                                                              _( (~\\\n" +
                "       _ _                        /                          ( \\> > \\\n" +
                "   -/~/ / ~\\                     :;                \\       _  > /(~\\/\n" +
                "  || | | /\\ ;\\                   |l      _____     |;     ( \\/ /   /\n" +
                "  _\\\\)\\)\\)/ ;;;                  `8o __-~     ~\\   d|      \\   \\  //\n" +
                " ///(())(__/~;;\\                  \"88p;.  -. _\\_;.oP        (_._/ /\n" +
                "(((__   __ \\\\   \\                  `>,% (\\  (\\./)8\"         ;:'  i\n" +
                ")))--`.'-- (( ;,8 \\               ,;%%%:  ./V^^^V'          ;.   ;.\n" +
                "((\\   |   /)) .,88  `: ..,,;;;;,-::::::'_::\\   ||\\         ;[8:   ;\n" +
                " )|  ~-~  |(|(888; ..``'::::8888oooooo.  :\\`^^^/,,~--._    |88::| |\n" +
                "  \\ -===- /|  \\8;; ``:.      oo.8888888888:`((( o.ooo8888Oo;:;:'  |\n" +
                " |_~-___-~_|   `-\\.   `        `o`88888888b` )) 888b88888P\"\"'     ;\n" +
                "  ;~~~~;~~         \"`--_`.       b`888888888;(.,\"888b888\"  ..::;-'\n" +
                "   ;      ;              ~\"-....  b`8888888:::::.`8888. .:;;;''\n" +
                "      ;    ;                 `:::. `:::OOO:::::::.`OO' ;;;''\n" +
                " :       ;                     `.      \"``::::::''    .'\n" +
                "    ;                           `.   \\_              /\n" +
                "  ;       ;                       +:   ~~--  `:'  -';\n" +
                "                                   `:         : .::/\n" +
                "      ;                            ;;+_  :::. :..;;;");
    }
}
