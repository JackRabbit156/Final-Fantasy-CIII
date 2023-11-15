package hauptmenu;

import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.Speicherstand;
import party.PartyController;

public class HauptmenuController {



    /**
     * Gibt die Optionsansicht aus
     * @author 11750396
     * @since 15.11.2023
     */
    public void optionen(){
        System.out.println("++++++Optionen++++++");
        System.out.println("1: Spiel speichern");
        System.out.println("2: Speicherstand laden");
        System.out.println("3: Schwierigkeitsgrad ändern");
        System.out.println("4: Zurück zum Hauptmenü");
        int eingabe = ScannerHelfer.sc.nextInt();
        switch(eingabe){
            case 1:
                speicherstandController.speichern(new Speicherstand(partyController.getParty(), gameController.getSchwierigkeitsgrad(), gameController.isHardcore()));
                break;
            case 2:
                Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
                partyController = new PartyController(auswahl.party);
                gameController = new GameController(auswahl.schwierigkeitsgrad, auswahl.hardcore, partyController);
                break;
            case 3:
                gameController.schwierigkeitsAuswahl();
                break;
            case 4:
                hauptmenuAnzeigen();
                break;
            default:
                System.err.println("Falsche Eingabe!");
                optionen();
                break;
        }
    }
}
