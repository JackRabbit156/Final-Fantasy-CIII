package gamehub.trainer;

import charakter.controller.CharakterController;
import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import charakter.model.klassen.spezialisierungen.Spezialisierung;
import gamehub.GameHubController;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;

import java.util.ArrayList;

public class Trainer {
private TrainerController trainerController;

    public Trainer(TrainerController trainerController) {
        this.trainerController = trainerController;
    }
//Deklaration der Variablen
private SpielerCharakter currentCharakter = null;





    // VORGABEWERTE !!!!!

    int basisKostenKlassenwechsel = 500; // Vorgaben fuer die Kosten des Klassenwechsels
    int basisKostenSpezialisierung = 1000; // Vorgaben fuer die Kosten der Spezialisierung
    int basisKostenFaehigkeitenAnpassen = 1000; // Vorgaben für die Anpassung der Faehigkeiten

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
            int scannerEingabe = ScannerHelfer.nextInt();
            switch (scannerEingabe) {
                case 1:
                    //Klasse tauschen aufrufen
                    gueltigeEingabe=true;
                    menuKlasseWechseln();
                    break;
                case 2:
                    //Spezialisierung tasuchen aufrufen
                    gueltigeEingabe=true;
                    menuSpezialisierungKaufen();
                    break;
                case 3:
                    //Attribute vergeben
                    gueltigeEingabe=true;
                    menuFaehigkeitenAufwerten();
                    break;
                case 0:
                    trainerController.getGameHubController().hubAnzeigen();
                    gueltigeEingabe=true;
                    break;
                default:
                    System.out.println("Die Eingabe ist ungültig");
                    break;
            }
        }
    }
    public void trainerCharakterAuswahl() {
        SpielerCharakter[] dasTeam = trainerController.getPartyController().getTeammitglieder(); // @TODO: Methode noch nicht erreichbar

        for (int i = 0; i < 4; i++) {
            if (dasTeam[i]!=null) {
                System.out.printf("#%d   Name : %s    Klasse %s     Spezialisierung %s", i, dasTeam[i].getName(), dasTeam[i].getKlasse().getBezeichnung(),dasTeam[i].getKlasse().getBezeichnung()+"\n");

            }

        }
        System.out.println("Bitte treffen Sie Ihre Auswahl. welchen Charakter wollen Sie anpassen !");
        int scannerEingabe = ScannerHelfer.nextInt();
        if (dasTeam[scannerEingabe] != null) {
            currentCharakter =  dasTeam[scannerEingabe];
        } else {
            System.out.println("Falsche Eingabe !");
            trainerCharakterAuswahl();
        }
    }

    public void menuKlasseWechseln() {
        // Wechsel der Klasse gegen Gebuehr
        // Eine Int Variable fuer den Kalssenwechsel. Sperrt ein AuswahlFeld
        int gesperrteAuswahl=-1;
        // Aufruf des Charakterauswahl
        SpielerCharakter derCharakter = currentCharakter;
        // Wir brauchen mehr Scanner
        int nutzerAuswahl;
        boolean gueltigeEingabe = false;
        int klassenauswahl=0;

        System.out.println("Der Charakter hat folgende Werte");
        System.out.println("Name : " + derCharakter.getName() + " hat die Klasse " + derCharakter.getKlasse());
        System.out.println("Der Wechsel zu einer anderen Klasse kostet 500 Gold");

        //@TODO: Ausgabe der Möglichen (In Schleife mit Nummer zur Auswahl)Klassen zu denen er wechseln kann (ausser der aktiven)
        // @TODO: Hier fehlt noch die Methode um weiter zu machen !

        // Anzeigend er möglichen Klassen
        while (!gueltigeEingabe) {
            System.out.println("Folgende Klassen stehen zum Wechsel zur Verfügung !");
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Physicher DD"))) {
                System.out.println("1.      Haudrauf");
            } else {
                gesperrteAuswahl = 1;
            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Magischer DD"))) {
                System.out.println("2.      Magier");
            } else {
                gesperrteAuswahl = 2;
            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Tank"))) {
                System.out.println("3.      Panzer");
            } else {
                gesperrteAuswahl = 3;
            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Healer"))) {
                System.out.println("4.      Heiler");
            } else {
                gesperrteAuswahl = 4;
            }
            System.out.println("0.      Abbruch");

            System.out.println("Bitte wählen Sie aus den verfügbaren Klassen");

            nutzerAuswahl = ScannerHelfer.nextInt();
            switch (nutzerAuswahl) {
                case 1:
                    // Wechsel zu PDD
                    if (gesperrteAuswahl == 1) {
                        break;
                    }
                    klassenauswahl=nutzerAuswahl;
                    break;
                case 2:
                    // wechsel zu MDD
                    if (gesperrteAuswahl == 2) {
                        break;
                    }
                    klassenauswahl=nutzerAuswahl;
                    break;
                case 3:
                    // Wechsel zu TNK
                    if (gesperrteAuswahl == 3) {
                        break;
                    }
                    klassenauswahl=nutzerAuswahl;
                    break;
                case 4:
                    // Wechsel zu HLR
                    if (gesperrteAuswahl == 4) {
                        break;
                    }
                    klassenauswahl=nutzerAuswahl;
                    break;
                case 0:
                    System.out.println("Klassen wechsel wurde abgebrochen");
                    trainerAnzeigen();
                    break;
                default:
                    System.out.println("ungültige Eingabe");
                    break;
            }

        }
        gueltigeEingabe=false;
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
            CharakterController.klasseAendern(derCharakter, meineKlasse[klassenauswahl]);
            // Durch den Wechsel der Klasse wir die Spezialisierung zurueckgesetzt !

        }
        System.out.println("\n Druecke eine Taste um in Trainer Menue zu gelangen");
        ScannerHelfer.nextLine();
        trainerAnzeigen();

    }

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
