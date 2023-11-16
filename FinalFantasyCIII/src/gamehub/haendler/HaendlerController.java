package gamehub.haendler;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import gegenstand.Ausruestungsgegenstand.Accesssoire;
import gegenstand.Ausruestungsgegenstand.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffe;
import gegenstand.Gegenstand;
import party.PartyController;

import java.util.Scanner;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class HaendlerController {

    Haendler haendler;

    /**
     * @author OF Kretschmer
     * <p>
     * Zeigt das HaendlerMenue an mit den Optionen Kaufen/ Verkaufen/ Zurueckkaufen / zurueck zum Menue
     * @since 15.11.23
     */
    public void haendlerAnzeigen(PartyController partyController) {
        haendlerBildAnzeigen();
        haendlerMenueAnzeigen();
//     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        int eingabe;
        boolean eingabeKorrekt = false;

        eingabe = scanner.nextInt();
        while (!eingabeKorrekt) {
            if (eingabe >= 1 && eingabe <= 4) {
                eingabeKorrekt = true;
                switch (eingabe) {
                    case 1:
                        // Öffnen kaufmenü von Niels
                        break;
                    case 2:
                        verkaufenAnzeigen(partyController);
                        break;
                    case 3:
                        // Öffnet die Verkaufshistory
                        break;
                    case 4:
                        // Zurück zum Menü
                        // löschen der verkaufshiytory
                        break;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft");
            }
        }

    }

    /**
     * @author OF Kretschmer
     * <p>
     * zeigt die Übersicht des Verkaufsmenue an und gibt dann die Moeglichkeit auszuwaehlen welche Art von Gegenstand man verkaufen
     * moechte, entsprechend geht ein Untermenue auf in dem dann die Gegenstaende der Kategorie angezeigt werden und ein verkaufen moeglich ist.
     * @since 15.11.23
     */
    public void verkaufenAnzeigen(PartyController partyController) {
        verkaufenMenueAnzeigen();
//     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        int eingabe;

        boolean eingabeKorrekt = false;

        while (!eingabeKorrekt) {
            eingabe = scanner.nextInt();
            if (eingabe >= 1 && eingabe <= 6) {
                eingabeKorrekt = true;
                switch (eingabe) {
                    case 1:
                        verkaufenWaffe(partyController);
                        // Öffnen Waffeninventar mit verkaufsOption
                        break;
                    case 2:
                        verkaufenRuestung(partyController);
                        // Öffnen Rüstungsinventar mit verkaufsOption
                        break;
                    case 3:
                        verkaufenAccessoire(partyController);
                        // Öffnen Accessoireinventar mit verkaufsOption
                        break;
                    case 4:
                        // Öffnen Verbrauchsgegenstände Inventar mit verkaufsOption
                        break;
                    case 5:
                        // Öffnen Materialien Inventar mit verkaufsOption
                        break;
                    case 6:
                        haendlerAnzeigen(partyController);
                        break;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }


    }

}

    /**
     * @author OF Kretschmer
     * <p>
     * oeffnet das Verkaufsmenue für Waffen.
     * Es werden alle Waffen des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     * @since 16.11.23
     */
    public void verkaufenWaffe(PartyController partyController) {

        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welche Waffe möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getWaffenInventar.length; i++) {
            Waffe tmp = partyController.getInventar.getWaffenInventar(i);
            System.out.printf("%d. %n", i + 1);
            printWaffe(tmp);
        }
        System.out.printf("%n%d. Zurück zum Händler" (partyController.getInventar.getWaffenInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = scanner.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= partyController.getInventar.getWaffenInventar.length + 2) {
                eingabeKorrekt = true;
                switch (auswahlObjekt) {
                    case (partyController.getInventar.getWaffenInventar.length + 2):
                        // Der Weg zurück ins Verkaufsmenü
                        verkaufenAnzeigen(partyController);
                        break;
                    default:
                        // fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getWaffenInventar(auswahlObjekt));
                        partyController.getInventar.getWaffenInventar(auswahlObjekt).remove;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }

        }
    }

    /**
     * @author OF Kretschmer
     * <p>
     * oeffnet das Verkaufsmenue für Ruestung.
     * Es werden alle Ruestung des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     * @since 16.11.23
     */
    public void verkaufenRuestung(PartyController partyController) {

        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welche Rüstung möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getRuestungsInventar.length; i++) {
            Ruestung tmp = partyController.getInventar.getRuestungsInventar(i);
            System.out.printf("%d. %n", i + 1);
            printRuestung(tmp);
        }
        System.out.printf("%n%d. Zurück zum Händler" (partyController.getInventar.getRuestungsInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = scanner.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= partyController.getInventar.getRuestungsInventar.length + 2) {
                eingabeKorrekt = true;
                switch (auswahlObjekt) {
                    case (partyController.getInventar.getRuestungsInventar.length + 2):
                        // Der Weg zurück ins Verkaufsmenü
                        verkaufenAnzeigen(partyController);
                        break;
                    default:
                        // fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getRuestungsInventar(auswahlObjekt));
                        partyController.getInventar.getRuestungsInventar(auswahlObjekt).remove;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }

        }
    }

    /**
     * @author OF Kretschmer
     * <p>
     * oeffnet das Verkaufsmenue für Accessoire.
     * Es werden alle Accessoieres des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     * @since 16.11.23
     */
    public void verkaufenAccessoire(PartyController partyController) {

        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welche Rüstung möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getAccessoireInventar.length; i++) {
            Accesssoire tmp = partyController.getInventar.getRuestungsInventar(i);
            System.out.printf("%d. %n", i + 1);
            printAccessoire(tmp);
        }
        System.out.printf("%n%d. Zurück zum Händler" (partyController.getInventar.getAccessoireInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = scanner.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= partyController.getInventar.getAccessoireInventar.length + 2) {
                eingabeKorrekt = true;
                switch (auswahlObjekt) {
                    case (partyController.getInventar.getAccessoireInventar.length + 2):
                        // Der Weg zurück ins Verkaufsmenü
                        verkaufenAnzeigen(partyController);
                        break;
                    default:
                        // fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getAccessoireInventar(auswahlObjekt));
                        partyController.getInventar.getAccessoireInventar(auswahlObjekt).remove;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }

        }
    }

    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * Öffnet das Spielerinventar und ermöglicht das verkaufen von Gegenständen
     */
    private void gegenstandVerkaufen(Gegenstand gegenstand) {
        // TODO Aufruf Spielerinventar muss eig vorher passieren inkl auswahl Gegenstand (Anzahl fehlt)


        //TODO verkaufsprozess überlegen  -> Objekt raussuchen und removen in Anzahl N

    }

    /**
     * @param gegenstand
     * @author HF Rode
     */
    private void gegenstandKaufen(Gegenstand gegenstand) {


    }

    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * Zeigt das Menü des Händlers an
     */
    private void haendlerMenueAnzeigen() {
        System.out.println("1. Kaufen");
        System.out.println("2. Verkaufen");
        System.out.println("3. Zurückkaufen");
        System.out.println("4. Zurück zum Menü");
    }

    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * Zeigt das Menü des Händlers an
     */
    private void verkaufenMenueAnzeigen() {
        System.out.println("Was möchten Sie verkaufen?");
        System.out.println("1. Waffen");
        System.out.println("2. Rüstung");
        System.out.println("3. Accessoire");
        System.out.println("4. Verbrauchsgegenstände");
        System.out.println("5. Materialien");
        System.out.println("6. Zurück zum Menü");
    }


    /**
     * @author OF Kretschmer
     * @since 15.11.23
     * Zeigt die Grafik im Menü des Händlers an
     */
    private void haendlerBildAnzeigen() {
        System.out.println(" Hier ist der Händler");
//        Grafik einbinden
    }


    private void printWaffe(Waffe waffe) {
        System.out.println("Name: " + waffe.getName);
        if (waffe.getAttake > 0) {
            System.out.println("Attacke: " + waffe.getAttake);
        } else {
            System.out.println("MagischeAttacke: " + waffe.getMagischeAttake);
        }
        System.out.println("Bonus: " + waffe.getBonus + " " + waffe.getBonusUmfang);
        System.out.println("LevelAnforderung: " + waffe.getLvlAnforderung);
        System.out.println();
        System.out.println("Verkaufspreis: " + waffe.getVerkaufswert);
        System.out.println();
    }
    private void printRuestung(Ruestung ruestung) {
        System.out.println("Name: " + ruestung.getName);
        if (ruestung.getVerteidigung > 0) {
            System.out.println("Verteidigung: " + ruestung.getVerteidigung);
        } else {
            System.out.println("MagischeVerteidigung: " + ruestung.getMagischeVerteidigung);
        }
        System.out.println("Bonus: " + ruestung.getBonus + " " + ruestung.getBonusUmfang);
        System.out.println("LevelAnforderung: " + ruestung.getLvlAnforderung);
        System.out.println();
        System.out.println("Verkaufspreis: " + ruestung.getVerkaufswert);
        System.out.println();
    }
    private void printAccessoire(Accesssoire accesssoire) {
        System.out.println("Name: " + accesssoire.getName);
        System.out.println("Bonus: " + accesssoire.getBonus + " " + accesssoire.getBonusUmfang);
        System.out.println("LevelAnforderung: " + accesssoire.getLvlAnforderung);
        System.out.println();
        System.out.println("Verkaufspreis: " + accesssoire.getVerkaufswert);
        System.out.println();
    }
}
