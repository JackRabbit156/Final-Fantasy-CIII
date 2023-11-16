package gamehub.trainer;

import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

public class Trainer {

    private void trainerMenu() {
        //Hauptmenu des Trainers
        // Klasse tauschen
        System.out.println("1.         Klasse tauschen");

        // Spezialisierung waehlen
        System.out.println("2.         Spezialisierung tauschen");
        // Attributpunkte vergeben
        System.out.println("3.         Attribute vergeben");
        // zurueck zum HUB
        System.out.println("0.          Zurueck zum HUB");
        System.out.println("\n");
        System.out.println("Bitte treffen Sie Ihre Auswahl");
        int scannerEingabe = ScannerHelfer.sc.nextInt();
        switch (scannerEingabe) {
            case 1:
                //Klasse tauschen aufrufen
                menuKlasseWechseln();
                break;
            case 2:
                //Spezialisierung tasuchen aufrufen
                menuSpezialisierungKaufen();
                break;
            case 3:
                //Attribute vergeben
                menuAttributeKaufen();
                break;
            case 0:
                GameHubController.hubAnzeigen(); //@TODO: Hier muss noch was eingetragen werden
            default:
                System.out.println("Die Eingabe ist ungültig");
                break;
        }
    }

    private SpielerCharakter trainerCharakterAuswahl() {
        SpielerCharakter[] dasTeam = // getTeammitglieder
        for (int i = 0; i < dasTeam.length) {
            if (dasTeam[i].equals(!null)) {
                System.out.printf("#%f   Name : %s    Klasse %s     Spezialisierung %s", i, dasTeam[i].getName(), dasTeam[i].getKlasse(), .getSpezialisierung());
            } else {
                System.out.println("#i  Kein Charakter vorhaden");
            }

        }
        System.out.println("Bitte treffen Sie Ihre Auswahl. welchen Charakter wollen Sie anpassen !");
    }

    private void menuKlasseWechseln() {
        // Wechsel der Klasse gegen Gebuehr
        //@TODO: Hier muss noch was eingetragen werden
        System.out.println("Noch nicht implemeniert !");

    }

    private void menuSpezialisierungKaufen() {
        //@TODO: Hier muss noch was eingetragen werden
        System.out.println("Noch nicht implemeniert !");
    }

    private void menuAttributeKaufen() {
        //@TODO: Hier muss noch was eingetragen werden
        System.out.println("Noch nicht implemeniert !");
    }
}
