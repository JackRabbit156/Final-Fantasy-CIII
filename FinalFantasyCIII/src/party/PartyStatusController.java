package party;

import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

import java.io.IOException;
import java.util.Scanner;

public class PartyStatusController {
    private PartyController partyController;
    private int ausgewaehlteOption = 0;

    private String[] menuOption;
    private Scanner einleser;

    public PartyStatusController(PartyController partyController) {
        this.partyController = partyController;
        this.einleser = ScannerHelfer.sc;
        menuOption = new String[]{"Ausruestung", "Verbrauchsgegenstaende","Upgrade Materialien"};
    }

    public void spielerinventarAnzeige() {
        while (true) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
            // TODO Hir könnte ASCII ART SEIN
            for (int i = 0; i < menuOption.length; i++) {
                if (i == ausgewaehlteOption) {
                    System.out.println(Farbauswahl.YELLOW + ">> " + menuOption[i] + Farbauswahl.RESET);
                } else {
                    System.out.println(Farbauswahl.GREEN + "   " + menuOption[i] + Farbauswahl.RESET);
                }
            }

            char userInput = einleser.next().charAt(0);

            switch (userInput) {
                case 'w':
                    KonsolenAssistent.clear();
                    this.navigateMenu(-1, menuOption.length);
                    break;
                case 's':
                    KonsolenAssistent.clear();
                    this.navigateMenu(1, menuOption.length);
                    break;
                case 'e':
                    KonsolenAssistent.clear();
                    this.executeSelectedOption();
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Fehlerhafte Eingabe. Benutze 'w' zum Hochgehen, 's' um runter zu gehen  und 'e' um zu bestaetigen.");
                    break;
            }
        }
    }

    /**
     * Navigiert durch die Menüoptionen basierend auf der Benutzereingabe.
     *
     * @param direction  Die Richtung der Navigation (-1 für nach oben, 1 für nach unten).
     * @param menuLength Die Länge des Menüs.
     *
     * @author HF Rode
     */
    private void navigateMenu(int direction, int menuLength) {
        ausgewaehlteOption = (ausgewaehlteOption + direction + menuLength) % menuLength;
    }

    /**
     * Führt die ausgewählte Menüoption basierend auf der Benutzereingabe aus.
     * @author HF Rode
     */
    private void executeSelectedOption() {
        System.out.println(Farbauswahl.RED + "Starte: " + menuOption[ausgewaehlteOption]);
        System.out.println(ausgewaehlteOption);
        switch (menuOption[ausgewaehlteOption]) {
            case "Ausruestung":
                menuOption = new String[]{"Waffen", "Ruestung","Accesoirs", "Zurueck"};
                ausgewaehlteOption = 0;
                break;
            case "Verbrauchsgegenstaende":
                this.verbrauchsgegenstaendeAnzeige();
                break;
            case "Upgrade Materialien":
                this.upgradematerialienAnzeige();
                break;
            case "Waffen":
                this.waffenAnzeigen();
                break;
            case "Ruestung":
                this.ruestungAnzeigen();
                break;
            case "Accesoirs":
                this.accesoirsAnzeigen();
                break;
            case "Zurueck":
                menuOption = new String[]{"Ausruestung", "Verbrauchsgegenstaende","Upgrade Materialien"};
                ausgewaehlteOption = 0;
                break;




        }
    }

    private void accesoirsAnzeigen() {
        System.out.println("GLITZER");
    }

    private void ruestungAnzeigen() {
        System.out.println("BESCHUETZEND");
    }

    private void waffenAnzeigen() {
        System.out.println("SCHARF!");
    }

    private void upgradematerialienAnzeige() {
        System.out.println("VERSTAERKEND!");
    }

    private void verbrauchsgegenstaendeAnzeige() {
        System.out.println("VERBRAUCHEND");
    }

    public void partystatusmenuAnzeigen() {
        this.spielerinventarAnzeige();
    }
}
