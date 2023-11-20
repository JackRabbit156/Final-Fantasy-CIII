package gamehub.trainer;

import charakter.controller.CharakterController;
import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import charakter.model.klassen.spezialisierungen.Spezialisierung;
import gamehub.GameHubController;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;

import java.util.ArrayList;


/**
 * @param
 * @author Thomas Maass
 * @return
 * @since 20.11.23
 */

public class Trainer {
    private TrainerController trainerController;

    /**
     * Instantiates a new Trainer.
     *
     * @param trainerController the trainer controller
     */
    public Trainer(TrainerController trainerController) {
        this.trainerController = trainerController;
    }

    //Deklaration der Variablen
    private SpielerCharakter currentCharakter = null;


    // VORGABEWERTE !!!!!

    /**
     * The Basis kosten klassenwechsel.
     */
    int basisKostenKlassenwechsel = 500; // Vorgaben fuer die Kosten des Klassenwechsels
    /**
     * The Basis kosten spezialisierung.
     */
    int basisKostenSpezialisierung = 1000; // Vorgaben fuer die Kosten der Spezialisierung
    /**
     * The Basis kosten faehigkeiten anpassen.
     */
    int basisKostenFaehigkeitenAnpassen = 1000; // Vorgaben für die Anpassung der Faehigkeiten

    /**
     * Trainer anzeigen.
     */
    public void trainerAnzeigen() {
        boolean gueltigeEingabe = false;
        while (!gueltigeEingabe) {
            //Hauptmenu des Trainers
            // Klasse tauschen
            System.out.println("1.         Klasse tauschen");

            // Spezialisierung waehlen
            System.out.println("2.         Spezialisierung tauschen");

            // Attributpunkte vergeben
            System.out.println("3.         Faehigkeiten anpassen/erweitern");

            // zurueck zum HUB
            System.out.println("0.         Zurueck zum HUB");
            System.out.println("\n");
            System.out.println("Bitte treffen Sie Ihre Auswahl");
            System.out.println("----------------------------------------------------------------------");

            int scannerEingabe = ScannerHelfer.nextInt();
            switch (scannerEingabe) {
                case 1:
                    //Klasse tauschen aufrufen
                    gueltigeEingabe = true;
                    menuKlasseWechseln();
                    break;
                case 2:
                    //Spezialisierung tasuchen aufrufen
                    gueltigeEingabe = true;
                    menuSpezialisierungKaufen();
                    break;
                case 3:
                    //Attribute vergeben
                    gueltigeEingabe = true;
                    menuFaehigkeitenAufwerten();
                    break;
                case 0:
                    //Erstmal auskommentiert. Nils sagt das geht so ! Hoffen wir es
                    trainerController.getGameHubController().hubAnzeigen();
                    gueltigeEingabe = true;
                    break;
                default:
                    System.out.println("Die Eingabe ist ungültig");
                    break;
            }
        }
    }

    /**
     * Trainer charakter auswahl.
     */
    public SpielerCharakter trainerCharakterAuswahl() {
        SpielerCharakter[] dasTeam = trainerController.getPartyController().getTeammitglieder();

        for (int i = 0; i < dasTeam.length; i++) {
            if (dasTeam[i] != null) {
                System.out.printf("%d.   Name : %s    Klasse %s     Spezialisierung %s \n", i, dasTeam[i].getName(), dasTeam[i].getKlasse().getBezeichnung(), dasTeam[i].getKlasse().getBezeichnung());

            }

        }
        System.out.println("Bitte treffen Sie Ihre Auswahl. welchen Charakter wollen Sie anpassen !");
        int scannerEingabe = ScannerHelfer.nextInt();
        if (dasTeam[scannerEingabe] != null) {
            currentCharakter = dasTeam[scannerEingabe];
        } else {
            System.out.println("Falsche Eingabe !");
            trainerCharakterAuswahl();
        }
        return currentCharakter;
    }

    /**
     * Menu klasse wechseln.
     */
    public void menuKlasseWechseln() {
        // Wechsel der Klasse gegen Gebuehr
        // Eine Int Variable fuer den Kalssenwechsel. Sperrt ein AuswahlFeld
        int gesperrteAuswahl = -1;
        // Aufruf des Charakterauswahl
        SpielerCharakter derCharakter = trainerCharakterAuswahl();

        // Wir brauchen mehr Scanner
        int nutzerAuswahl;
        boolean gueltigeEingabe = false;
        int klassenauswahl = 0;
        KonsolenAssistent.clear();
        System.out.println("Der Charakter hat folgende Werte");
        System.out.println("Name : " + derCharakter.getName() + " hat die Klasse " + derCharakter.getKlasse().getBezeichnung());
        System.out.println("Der Wechsel zu einer anderen Klasse kostet 500 Gold. Sie haben derzeit " + trainerController.getPartyController().getPartyGold());

        //@TODO: Ausgabe der Möglichen (In Schleife mit Nummer zur Auswahl)Klassen zu denen er wechseln kann (ausser der aktiven)
        // @TODO: Hier fehlt noch die Methode um weiter zu machen !

        // Anzeigend er möglichen Klassen
        while (!gueltigeEingabe) {
            System.out.println("Folgende Klassen stehen zum Wechsel zur Verfügung !");
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Physicher DD"))) {
                System.out.println("1.      Physicher DD");
            } else {
                gesperrteAuswahl = 1;

            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Magischer DD"))) {
                System.out.println("2.      Magischer DD");
            } else {
                gesperrteAuswahl = 2;

            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Tank"))) {
                System.out.println("3.      Tank");
            } else {
                gesperrteAuswahl = 3;
            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Healer"))) {
                System.out.println("4.      Healer");
            } else {
                gesperrteAuswahl = 4;
            }
            System.out.println("0.      Abbruch");

            System.out.println("Bitte wählen Sie aus den verfügbaren Klassen");

            nutzerAuswahl = ScannerHelfer.nextInt();
            switch (nutzerAuswahl) {
                case 1:
                    // Wechsel zu PDD1
                    if (gesperrteAuswahl == 1) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }

                case 2:
                    // wechsel zu MDD
                    if (gesperrteAuswahl == 2) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }
                case 3:
                    // Wechsel zu TNK
                    if (gesperrteAuswahl == 3) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;

                    }

                case 4:
                    // Wechsel zu HLR
                    if (gesperrteAuswahl == 4) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }

                case 0:
                    System.out.println("Klassen wechsel wurde abgebrochen");
                    gueltigeEingabe = true;
                    trainerController.trainerAnzeigen();
                    break;
                default:
                    System.out.println("ungültige Eingabe");
                    break;
            }
        }
        gueltigeEingabe = false;
        //0=MDD / 1=HLR / 2=TNK / 3=HLR
        Klasse[] meineKlasse = {new PDD(), new MDD(), new TNK(), new HLR()};

        //charakterController.klasseAendern(derCharakter, meineKlasse[0]);

        // Nach Auswahl der neuen Klassen wird der Spieler erneut gefragt ob es das ist was er will
        // mit dem Hinweis darauf das seine (Klassen Level) KOMPLETT VERLOREN GEHEN !
        if (trainerController.getPartyController().getPartyGold() < basisKostenKlassenwechsel) {
            System.out.println("Du kannst die den Wechsel der Klasse nicht leisten");
            System.out.println("Es wuerde dich " + basisKostenKlassenwechsel + " kosten. Du hast aber nur " + trainerController.getPartyController().getPartyGold() + ". Schwierig !!");
        } else {
            // Hier wird die Klasse gewechselt
            CharakterController.klasseAendern(derCharakter, meineKlasse[klassenauswahl - 1]);
            trainerController.getPartyController().goldAbziehen(basisKostenKlassenwechsel);
            System.out.println("Der Charakter hat nur die Klasse " + derCharakter.getKlasse().getBezeichnung());
            System.out.println("Das Golder der Party beträgt nun " + trainerController.getPartyController().getPartyGold());
            // Durch den Wechsel der Klasse wir die Spezialisierung zurueckgesetzt !

        }
        System.out.println("\n Druecke eine Taste um in Trainer Menue zu gelangen");
        ScannerHelfer.nextLine();

        trainerAnzeigen();

    }


    /**
     * Menu spezialisierung kaufen.
     */
    public void menuSpezialisierungKaufen() {
        //@TODO: Hier muss noch was eingetragen werden
        System.out.println("Noch nicht implemeniert !");

        // Die Bezeichnung der der Oberklasse bleibt auch wechsel der Spezielisierung gleich Also bei Rabauke ist die Bezeichnung "Tank"

        //SpielerCharakter derCharakter = (trainerCharakterAuswahl());

        /*derCharakter.getKlasse() instanceof Spezialisierung;
        //derCharakter.getKlasse().getClass().getSimpleName();*/
        // Am Ende jeder Methode muss der currentCharakter auf NULL gesetzt werden !

        //Variable muss noch definiert werden.
        //derCharakter = null;

        /*
         * Hier geht es los. Der Rest oben drüber ist nur eine Erinnerung !
         *         * */


    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 17.11.2023
     * {@see Faehigkeit }: Hier liegen die Attribute der Faehigkeit. Auch kann eine Faehigkeit hier aufgewertet werden.
     * Oefnnet das Menue, um Faehigkeiten fuer einen gewaehlten Charakter aufzuwerten
     */
    private void menuFaehigkeitenAufwerten() {
        System.out.println("\nFuer welchen Charakter sollen Faehigkeiten aufgwertet werden?: (Bitte waehlen)");
        trainerCharakterAuswahl();

        if (currentCharakter != null) {
            menuFaehigkeitWaehlen(currentCharakter);
        } else {
            System.err.println("Trainer.menuFaehigkeitenKaufen: Keinen Charakter ausgewaehlt");
        }
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 17.11.2023
     * {@object character}: Der Charakter, fuer den eine Aenderung der Faehigkeiten vorgenommen wird.
     * {@see Faehigkeit }: Hier liegen die Attribute der Faehigkeit. Auch kann eine Faehigkeit hier aufgewertet werden.
     * Oefnnet das Menue, um eine Faehigkeit fuer einen  gegebenen Charakter aufzuwerten
     */
    private void menuFaehigkeitWaehlen(SpielerCharakter charakter) {
        ArrayList<Faehigkeit> faehigkeiten = charakter.getFaehigkeiten();
        System.out.println(Farbauswahl.YELLOW + "Faehigkeiten-Menue:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.RED + charakter.getName() + Farbauswahl.RESET + " hat diesen Faehigkeiten-Baum: ");
        for (Faehigkeit faehigkeit : faehigkeiten) {
            System.out.println(faehigkeit);
        }
        System.out.println("\nWelche Faehigkeit soll verbessert werden?: (Zahl eingeben - mit 0 abbrechen)");
        int nutzerAuswahl = ScannerHelfer.nextInt();
        if (0 < nutzerAuswahl && nutzerAuswahl <= faehigkeiten.size()) {
            //TODO:Kosten für Aufwertung verrechnen
            //Faehigkeit.faehigkeitAufwerten(faehigkeiten.get(nutzerAuswahl)); //@TODO: Kommentar entfernen !
            //TODO: Faehigkeit soll zurueckgeben was fuer wen aufgwertet wurde
        } else if (nutzerAuswahl != 0) { //Bei 0 wird das Menue geschlossen
            System.err.println("Nutzereingabe ungueltig! -  erneut waehlen!");
            menuFaehigkeitWaehlen(charakter);

        }
        currentCharakter = null;
    }
}
