package gamehub.haendler;


import gegenstand.Ausruestungsgegenstand.Accesssoire;
import gegenstand.Ausruestungsgegenstand.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
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
     * @since 15.11.23
     * <p>
     * Zeigt das HaendlerMenue an mit den Optionen Kaufen/ Verkaufen/ Zurueckkaufen / zurueck zum Menue
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
                        zurueckkaufenAnzeigen(partyController);
                        // Öffnet die Verkaufshistory
                        break;
                    case 4:
                        haendler.getZurueckkaufenHistorie().clear();
                        hub.anzeigen();
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
    private void verkaufenAnzeigen(PartyController partyController) {
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
                        verkaufenVerbrauchsgegenstände(partyController);
                        // Öffnen Verbrauchsgegenstände Inventar mit verkaufsOption
                        break;
                    case 5:
                        verkaufenMaterial(partyController);
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
    private void verkaufenWaffe(PartyController partyController) {

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
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht" (partyController.getInventar.getWaffenInventar.length + 2));
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
                        verkaufenWaffe(partyController);
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
    private void verkaufenRuestung(PartyController partyController) {

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
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht" (partyController.getInventar.getRuestungsInventar.length + 2));
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
                        verkaufenRuestung(partyController);
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
    private void verkaufenAccessoire(PartyController partyController) {

        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welches Accessoire möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getAccessoireInventar.length; i++) {
            Accesssoire tmp = partyController.getInventar.getAccessoireInventar(i);
            System.out.printf("%d. %n", i + 1);
            printAccessoire(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht" (partyController.getInventar.getAccessoireInventar.length + 2));
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
                        verkaufenAccessoire(partyController);
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }

        }
    }
    /**
     * @author OF Kretschmer
     * <p>
     * oeffnet das Verkaufsmenue für Verbrauchsgegenstände.
     * Es werden alle Verbrauchsgegenstände des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     * @since 16.11.23
     */
    private void verkaufenVerbrauchsgegenstände(PartyController partyController) {

        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welchen Verbrauchsgegenstand möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getVerbrauchsgegenstandInventar.size(); i++) {
            Accesssoire tmp = partyController.getInventar.getVerbrauchsgegenstandInventar(i);
            System.out.printf("%d. %n", i + 1);
            printVerbrauchsgegenstand(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht" (partyController.getInventar.getVerbrauchsgegenstandInventar.size() + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = scanner.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= partyController.getInventar.getVerbrauchsgegenstandInventar.size() + 2) {
                eingabeKorrekt = true;
                switch (auswahlObjekt) {
                    case (partyController.getInventar.getVerbrauchsgegenstanfInventar.length + 2):
                        // Der Weg zurück ins Verkaufsmenü
                        verkaufenAnzeigen(partyController);
                        break;
                    default:
                        // fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getVerbrauchsgegenstanfInventar(auswahlObjekt));
                        partyController.getVerbrauchsgegenstanfInventar(auswahlObjekt).remove;
                        verkaufenVerbrauchsgegenstände(partyController);
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }

        }
    }


    /**
     * @author OF Kretschmer
     * <p>
     * oeffnet das Verkaufsmenue für Material.
     * Es werden alle Material des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     * @since 16.11.23
     */
    private void verkaufenMaterial(PartyController partyController) {
        int auswahlObjekt;

        //     TODO   Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        boolean eingabeKorrekt = false;


        System.out.println("Welchen Verbrauchsgegenstand möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getMaterialInventar.size(); i++) {
            Material tmp = partyController.getInventar.getMaterialInventar(i);
            System.out.printf("%d. %n", i + 1);
             printMaterial(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht" (partyController.getInventar.getMaterialInventar.size() + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = scanner.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= partyController.getInventar.getMaterialInventar.size() + 2) {
                eingabeKorrekt = true;
                switch (auswahlObjekt) {
                    case (partyController.getInventar.getMaterialInventar.length + 2):
                        // Der Weg zurück ins Verkaufsmenü
                        verkaufenAnzeigen(partyController);
                        break;
                    default:
                        // fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getMaterialInventar(auswahlObjekt));
                        partyController.getInventar.getMaterialInventar(auswahlObjekt).remove;
                        verkaufenMaterial(partyController);
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
    }}

    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * <p>
     * Zeigt die Gegegenstände die zurückgekauft werden können, und ermöglicht das zurückkaufen (Dabei werden Sie wieder dem Inventar
     * hinzugefügt und aus der zurückkaufenListe entfernt.
     */
    private void zurueckkaufenAnzeigen(PartyController partyController) {
        int auswahlGegenstand;
        boolean eingabeKorrekt = false;
        System.out.println("Welchen Gegenstand möchten Sie zurückkaufen");

        for (int i = 0; i < haendler.getZurueckkaufenHistorie().size(); i++) {
            Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(i);
            System.out.printf("%d. %n", i + 1);
            if (tmp instanceof Waffe) {
                printWaffe((Waffe) tmp);
            } else if (tmp instanceof Ruestung) {
                printRuestung((Ruestung) tmp);
            } else if (tmp instanceof Accesssoire) {
                printRuestung((Accesssoire) tmp);
            } else if (tmp instanceof Verbrauchsgegenstand) {
                printRuestung((Verbrauchsgegenstand) tmp);
            } else (tmp instanceof Material) {
                printRuestung((Material) tmp);
            }
        } System.out.printf("%n%d. Zurück zum Händler", haendler.getZurueckkaufenHistorie().size() + 2);

        Scanner scanner = new Scanner(System.in);

        while (!eingabeKorrekt) {
            auswahlGegenstand = scanner.nextInt();
            if (auswahlGegenstand >= 1 && auswahlGegenstand <= haendler.getZurueckkaufenHistorie().size() + 2) {
                eingabeKorrekt = true;
                switch (auswahlGegenstand) {
                    case (haendler.getZurueckkaufenHistorie().size() + 2):
                        // Der Weg zurück zum Haendler
                        haendlerAnzeigen(partyController);
                        break;
                    default:
                        // Prüft die Art des Gegenstandes und fügt diesen dem entsprechenden Inventar wieder hinzu und nimmt ihn aus der zurückkaufenListe raus
                        if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Waffe) {
                            getRuestungsinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
                            zurueckkaufenAnzeigen(partyController);
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Ruestung) {
                            getWaffeninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
                            zurueckkaufenAnzeigen(partyController);
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Accesssoire) {
                            getGegenstandinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
                            zurueckkaufenAnzeigen(partyController);
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Verbrauchsgegenstand) {
                            getVerbrauchsgegenstandninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
                            zurueckkaufenAnzeigen(partyController);
                        } else (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Material) {
                        getMaterialinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                        haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
                        zurueckkaufenAnzeigen(partyController);
                    }
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }


    }

    private void gegenstandZurueckkaufen() {


    }


    private void gegenstandVerkaufen(Gegenstand gegenstand) {

    }

    /**
     * @param gegenstand
     * @author HF Rode
     */
    private void gegenstandKaufen(Gegenstand gegenstand) {


    }


//     BEGIN HILFSMETHODEN


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
        System.out.println("6. Zurück zur Händlerübersicht");
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


    /**
     * @param waffe Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
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

    /**
     * @param ruestung Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
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

    /**
     * @param accesssoire Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printAccessoire(Accesssoire accesssoire) {
        System.out.println("Name: " + accesssoire.getName);
        System.out.println("Bonus: " + accesssoire.getBonus + " " + accesssoire.getBonusUmfang);
        System.out.println("LevelAnforderung: " + accesssoire.getLvlAnforderung);
        System.out.println();
        System.out.println("Verkaufspreis: " + accesssoire.getVerkaufswert);
        System.out.println();
    }

    /**
     * @param verbrauchsgegenstand Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printVerbrauchsgegenstand(Verbrauchsgegenstand verbrauchsgegenstand) {
        System.out.println("Name: " + verbrauchsgegenstand.getName);
        System.out.println("Beschreibung: " + verbrauchsgegenstand.getBeschreibung);
        System.out.println();
        System.out.println("Verkaufspreis: " + verbrauchsgegenstand.getVerkaufswert);
        System.out.println();
    }

    /**
     * @param material Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printMaterial(Material material) {
        System.out.println("Name: " + material.getName);
        System.out.println();
        System.out.println("Verkaufspreis: " + material.getVerkaufswert);
        System.out.println();
    }


}
