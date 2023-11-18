package gamehub.partystatus;

import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

public class GameHubPartyStatusController {

    public void anzeigen(GameHubController gameHubController, PartyController partyController){
        System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1. Charakterauswahl" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2. Inventar" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3. Zurueck" + Farbauswahl.RESET);
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe){
            case 1:
                SpielerCharakter auswahl;

                System.out.println(Farbauswahl.YELLOW + "Charakter auswaehlen:" + Farbauswahl.RESET);
                System.out.println(Farbauswahl.CYAN + "1. " + partyController.getParty().getHauptCharakter().getName() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.CYAN + "2. " + partyController.getParty().getNebenCarakter(0).getName() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.CYAN + "3. " + partyController.getParty().getNebenCarakter(1).getName() + Farbauswahl.RESET);
        }
    }
}
