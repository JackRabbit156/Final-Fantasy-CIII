package gamehub.partystatus;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

public class GameHubPartyStatusController {

    GameHubController gameHubController;
    PartyController partyController;

    public GameHubPartyStatusController(GameHubController gameHubController, PartyController partyController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
    }

    /**
     * Aufrufmethode fuer das GameHub Partystatusmenue
     *
     * @since 18.11.2023
     * @author Lang
     */
    public void anzeigen(){
        System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1. Charakterauswahl" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2. Inventar" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3. Zurueck" + Farbauswahl.RESET);
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe){
            case 1:
                SpielerCharakter charakterAuswahl = null;
                while (charakterAuswahl == null){
                    charakterAuswahl = spielerCharakterAuswaehlen();
                }
                charakterOptionen(charakterAuswahl);
                break;

            case 2:
                //TODO Inventar oeffenen implementieren
                //vorlaeufige rueckkehr zum start der methode
                anzeigen();
                break;

            case 3:
                gameHubController.hubAnzeigen();
                break;

            default:
                System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe." + Farbauswahl.RESET);
                anzeigen();
                break;
        }
    }

    /**
     * Wählt den SpielerCharakter aus
     * @return SpielerCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public SpielerCharakter spielerCharakterAuswaehlen(){
        SpielerCharakter auswahl = null;

        System.out.println(Farbauswahl.YELLOW + "Charakter auswaehlen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1. " + partyController.getParty().getHauptCharakter().getName() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2. " + partyController.getParty().getNebenCarakter(0).getName() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3. " + partyController.getParty().getNebenCarakter(1).getName() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "4. " + partyController.getParty().getNebenCarakter(2).getName() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "5. Zurueck" + Farbauswahl.RESET);
        int eingabe = ScannerHelfer.nextInt();

        switch (eingabe){
            case 1:
                auswahl = partyController.getParty().getHauptCharakter();
                break;

            case 2:
                auswahl = partyController.getParty().getNebenCarakter(0);
                break;

            case 3:
                auswahl = partyController.getParty().getNebenCarakter(1);
                break;

            case 4:
                auswahl = partyController.getParty().getNebenCarakter(2);
                break;

            case 5:
                anzeigen();
                break;

            default:
                System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe." + Farbauswahl.RESET);
                break;
        }
        return auswahl;
    }

    /**
     * Stellt Methoden für den ausgwaehlten Spielercharakter bereit
     * @param spielerCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public void charakterOptionen(SpielerCharakter spielerCharakter){
        System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1. Stats anzeigen" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2. Ausruestung anzeigen" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3. Ausruestung aendern" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "4. Zurück" + Farbauswahl.RESET);
        int eingabe = ScannerHelfer.nextInt();

        switch (eingabe){
            case 1:
                CharakterController.statsAnzeigen(spielerCharakter);
                System.out.println(Farbauswahl.YELLOW + "Taste druecken zum zurueckkehren");
                break;

            case 2:
                CharakterController.charakterInventarAnzeigen(spielerCharakter);
                System.out.println(Farbauswahl.YELLOW + "Taste druecken zum zurueckkehren");
                break;

            case 3:
                //TODO implementierung wenn Inventar fertig
                break;

            case 4:
                spielerCharakterAuswaehlen();
                break;

            default:
                System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe." + Farbauswahl.RESET);
                System.out.println(Farbauswahl.YELLOW + "Taste druecken zum zurueckkehren");
                break;
        }
        ScannerHelfer.nextLine();
        charakterOptionen(spielerCharakter);
    }
}
