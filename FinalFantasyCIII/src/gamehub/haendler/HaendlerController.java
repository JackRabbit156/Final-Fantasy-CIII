package gamehub.haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import party.PartyController;


/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class HaendlerController {

    PartyController partyController;
    Haendler haendler;

    public HaendlerController(PartyController partyController) {
        this.partyController = partyController;
    }

    /**
     * @author OF Kretschmer
     * @since 17.11.23
     * Zeigt das HaendlerMenue an mit den Optionen Kaufen/ Verkaufen/ Zurueckkaufen / zurueck zum Menue
     */
    public void haendlerAnzeigen(PartyController partyController) {
        boolean zurückMenue = false;
        int eingabe;
        boolean eingabeKorrekt = false;
        // Absprache Niels - so geht der Weg zurück zum GameHUB
        while (!zurückMenue) {
            haendlerBildAnzeigen();
            goldAnzeigen();
            haendlerMenueAnzeigen();
            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= 4) {
                    eingabeKorrekt = true;
                    switch (eingabe) {
                        case 1:
                            //TODO
                            // Öffnen kaufmenü von Niels
                            break;
                        case 2:
                            verkaufenAnzeigen(partyController);
                            // Öffnet Spielerinventare mit Verkaufsoptionen
                            break;
                        case 3:
                            zurueckkaufenAnzeigen(partyController);
                            // Öffnet die Verkaufshistory
                            break;
                        case 4:
                            haendler.getZurueckkaufenHistorie().clear();
                            zurückMenue = true;
                            // löschen der verkaufshiytory
                            // Zurück zum Menü
                            break;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft");
                }
            }
        }
    }

    /**
     * @author OF Kretschmer
     * @since 15.11.23
     * zeigt die Übersicht des Verkaufsmenue an und gibt dann die Moeglichkeit auszuwaehlen welche Art von Gegenstand man verkaufen
     * moechte, entsprechend geht ein Untermenue auf in dem dann die Gegenstaende der Kategorie angezeigt werden und ein verkaufen moeglich ist.
     */
    private void verkaufenAnzeigen(PartyController partyController) {
        goldAnzeigen();
        verkaufenMenueAnzeigen();
        int eingabe;
        boolean eingabeKorrekt = false;

        while (!eingabeKorrekt) {
            eingabe = ScannerHelfer.nextInt();
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
                        // Geht zurück zur HaendlerÜbersicht
                        break;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }
    }


    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Waffen.
     * Es werden alle Waffen des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenWaffe(PartyController partyController) {

        int auswahlObjekt;
        boolean eingabeKorrekt = false;

        goldAnzeigen();
        System.out.println("Welche Waffe möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getWaffenInventar.length; i++) {
            Waffe tmp = partyController.getInventar.getWaffenInventar(i);
            System.out.printf("%d. %n", i + 1);
            printWaffe(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht", (partyController.getInventar.getWaffenInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
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
                        partyController.goldHinzufuegen(partyController.getInventar.getWaffenInventar(auswahlObjekt).getVerkaufspreis);
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
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Ruestung.
     * Es werden alle Ruestung des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenRuestung(PartyController partyController) {

        int auswahlObjekt;
        boolean eingabeKorrekt = false;
        goldAnzeigen();
        System.out.println("Welche Rüstung möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getRuestungsInventar.length; i++) {
            Ruestung tmp = partyController.getInventar.getRuestungsInventar(i);
            System.out.printf("%d. %n", i + 1);
            printRuestung(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (partyController.getInventar.getRuestungsInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
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
                        partyController.goldHinzufuegen(partyController.getInventar.getRuestungInventar(auswahlObjekt).getVerkaufspreis);
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
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Accessoire.
     * Es werden alle Accessoieres des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenAccessoire(PartyController partyController) {

        int auswahlObjekt;
        boolean eingabeKorrekt = false;
        goldAnzeigen();
        System.out.println("Welches Accessoire möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getAccessoireInventar.length; i++) {
            Accesssoire tmp = partyController.getInventar.getAccessoireInventar(i);
            System.out.printf("%d. %n", i + 1);
            printAccessoire(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (partyController.getInventar.getAccessoireInventar.length + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
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
                        partyController.goldHinzufuegen(partyController.getInventar.getAccessoireInventar(auswahlObjekt).getVerkaufspreis);
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
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Verbrauchsgegenstände.
     * Es werden alle Verbrauchsgegenstände des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenVerbrauchsgegenstände(PartyController partyController) {

        int auswahlObjekt;
        boolean eingabeKorrekt = false;
        goldAnzeigen();
        System.out.println("Welchen Verbrauchsgegenstand möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getVerbrauchsgegenstandInventar.size(); i++) {
            Verbrauchsgegenstand tmp = partyController.getInventar.getVerbrauchsgegenstandInventar(i);
            System.out.printf("%d. %n", i + 1);
            printVerbrauchsgegenstand(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (partyController.getInventar.getVerbrauchsgegenstandInventar.size() + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
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
                        partyController.goldHinzufuegen(partyController.getInventar.getVerbrauchsgegenstandInventar(auswahlObjekt).getVerkaufspreis);
                        partyController.getVerbrauchsgegenstandInventar(auswahlObjekt).remove;
                        verkaufenVerbrauchsgegenstände(partyController);
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }
    }


    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Material.
     * Es werden alle Material des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenMaterial(PartyController partyController) {
        int auswahlObjekt;
        boolean eingabeKorrekt = false;
        goldAnzeigen();
        System.out.println("Welches Material möchten Sie verkaufen?");
        for (int i = 0; i < partyController.getInventar.getMaterialInventar.size(); i++) {
            Material tmp = partyController.getInventar.getMaterialInventar(i);
            System.out.printf("%d. %n", i + 1);
            printMaterial(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (partyController.getInventar.getMaterialInventar.size() + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
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
                        partyController.goldHinzufuegen(partyController.getInventar.getMaterialInventar(auswahlObjekt).getVerkaufspreis);
                        partyController.getInventar.getMaterialInventar(auswahlObjekt).remove;
                        verkaufenMaterial(partyController);
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }
    }

    /**
     * @author OF Kretschmer
     * @since 17.11.23
     * Zeigt die Gegegenstände die zurückgekauft werden können, und ermöglicht das zurückkaufen (Dabei werden Sie wieder dem Inventar
     * hinzugefügt und aus der zurückkaufenListe entfernt.
     */
    private void zurueckkaufenAnzeigen(PartyController partyController) {
        int auswahlGegenstand;
        boolean eingabeKorrekt = false;
        goldAnzeigen();
        System.out.println("Welchen Gegenstand möchten Sie zurückkaufen");

        for (int i = 0; i < haendler.getZurueckkaufenHistorie().size(); i++) {
            Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(i);
            System.out.printf("%d. %n", i + 1);
            if (tmp instanceof Waffe) {
                printWaffe((Waffe) tmp);
            } else if (tmp instanceof Ruestung) {
                printRuestung((Ruestung) tmp);
            } else if (tmp instanceof Accessoire) {
                printAccessoire((Accessoire) tmp);
            } else if (tmp instanceof Verbrauchsgegenstand) {
                printVerbrauchsgegenstand((Verbrauchsgegenstand) tmp);
            } else (tmp instanceof Material) {
                printMaterial((Material) tmp);
            }
        } System.out.printf("%n%d. Zurück zum Händler", haendler.getZurueckkaufenHistorie().size() + 2);

        while (!eingabeKorrekt) {
            auswahlGegenstand = ScannerHelfer.nextInt();
            if (auswahlGegenstand >= 1 && auswahlGegenstand <= haendler.getZurueckkaufenHistorie().size() + 2) {
                eingabeKorrekt = true;
                switch (auswahlGegenstand) {
                    case (haendler.getZurueckkaufenHistorie().size() + 2):
                        // Der Weg zurück zum Haendler
                        haendlerAnzeigen(partyController);
                        break;
                    default:
                        // Prüft die Art des Gegenstandes und fügt diesen dem entsprechenden Inventar wieder hinzu und nimmt ihn aus der zurückkaufenListe raus, wenn genug Gold vorhanden ist.
                        if (partyController.getPartyGold() >= (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1).getVerkaufswert())) {
                            if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Waffe) {
                                getWaffeninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Ruestung) {
                                getRuestungsinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Accessoire) {
                                getGegenstandinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Verbrauchsgegenstand) {
                                getVerbrauchsgegenstandninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Material) {
                                getMaterialinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                            }
                            zurueckKaufen(auswahlGegenstand);
                        } else {
                            System.out.println("Sie verfügen nicht über genug Gold");
                        }
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }
    }


    /**
     * @param gegenstand
     * @author HF Rode
     */
    private void gegenstandKaufen(Gegenstand gegenstand) {
//TODO Kaufmenü

    }


//     BEGIN HILFSMETHODEN

    /**
     * @author OF Kretschmer
     * @since 17.11.23
     * zieht das Gold ab, nimmt das Objekt aus der Verkaufshistorie, geht zurück zur ZurückkaufenÜbersicht
     */
    private void zurueckKaufen(int auswahlGegenstand) {
        partyController.goldAbziehen(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1).getVerkaufswert());
        haendler.getZurueckkaufenHistorie().remove(auswahlGegenstand - 1);
        zurueckkaufenAnzeigen(partyController);
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
        System.out.println("6. Zurück zur Händlerübersicht");
    }


    /**
     * @author OF Kretschmer
     * @since 20.11.23
     * Gibt den aktuellen Goldstand an.
     */
    private void goldAnzeigen() {
        System.out.printf(Farbauswahl.YELLOW +"Sie besitzen %d Gold. ", partyController.getPartyGold());
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * @author OF Kretschmer
     * @since 15.11.23
     * Zeigt die Grafik im Menü des Händlers an
     */
    private void haendlerBildAnzeigen() {
        System.out.println("              \t \\/\\/\\/\\/\\/\\\n" +
                "                 |  _   _  |\n" +
                "                 |\\|_|-|_|/|  ___________________________\n" +
                "                 |   /\\    | /\t\tMOIN!            \\\n" +
                "                 |  \\~~~~/ |< Wie kann ich Ihnen helfen?  )\n" +
                "                 |   ~~~~  | \\___________________________/\n" +
                "                 \\_________/\n" +
                "          ____________||___________\n" +
                "         /  ____               ___ \\\n" +
                "         | /   |  Gold-Digger  |  \\ \\\n" +
                " ________|_|___|_______________|___|_|__________________\n" +
                "|\\        /| ________________            /^\\            \\\n" +
                "|\\\\ O|===|* >________________>           \\*/    _,-,     \\\n" +
                "||\\\\      \\|                             \"I\"   T_  |      \\\n" +
                "|| \\\\                 |`-._/\\_.-`|        I    ||`-'       \\\n" +
                "\\|_|\\\\       (        |    ||    |        I    ||           \\\n" +
                "     \\\\       \\       |___o()o___|  mm    I    ||            \\\n" +
                "      \\\\       )      |__((<>))__|  )(    I    ~~    ____     \\\n" +
                "       \\\\ ##--------> \\   o\\/o   / (  )   I         |    |     \\\n" +
                "        \\\\     )       \\   ||   /  |  |   I \t     \\  /       \\\n" +
                "         \\\\   /         \\  ||  /   |__|   I           \\/         \\\n" +
                "          \\\\ (           '.||.'                       **          \\  \n" +
                "           \\\\______________________________________________________\\\n" +
                "            \\  ____________________________________________________ |\n" +
                "             || |                                                || |\n" +
                "             || |                                                || |\n" +
                "             || |                                                || |\n" +
                "             \\|_|                                                \\|_|");

    }


    /**
     * @param waffe Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printWaffe(Waffe waffe) {
        System.out.println("Name: " + waffe.getName());
        if (waffe.getAttacke() > 0) {
            System.out.println("Attacke: " + waffe.getAttacke());
        } else {
            System.out.println("MagischeAttacke: " + waffe.getMagischeAttake());
        }
        System.out.println("Bonus: " + waffe.getBonus() + " " + waffe.getBonusUmfang());
        System.out.println("LevelAnforderung: " + waffe.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + waffe.getVerkaufswert());
        System.out.println();
    }

    /**
     * @param ruestung Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printRuestung(Ruestung ruestung) {
        System.out.println("Name: " + ruestung.getName());
        if (ruestung.getVerteidigung() > 0) {
            System.out.println("Verteidigung: " + ruestung.getVerteidigung());
        } else {
            System.out.println("MagischeVerteidigung: " + ruestung.getMagischeVerteidigung());
        }
        System.out.println("Bonus: " + ruestung.getBonus() + " " + ruestung.getBonusUmfang());
        System.out.println("LevelAnforderung: " + ruestung.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + ruestung.getVerkaufswert());
        System.out.println();
    }

    /**
     * @param accessoire Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printAccessoire(Accessoire accessoire) {
        System.out.println("Name: " + accessoire.getName());
        System.out.println("Bonus: " + accessoire.getBonus + " " + accessoire.getBonusumfang()); // Bonus noch im Accessoire
        System.out.println("LevelAnforderung: " + accessoire.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + accessoire.getVerkaufswert());
        System.out.println();
    }

    /**
     * @param verbrauchsgegenstand Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printVerbrauchsgegenstand(Verbrauchsgegenstand verbrauchsgegenstand) {
        System.out.println("Name: " + verbrauchsgegenstand.getName());
        System.out.println("Beschreibung: " + verbrauchsgegenstand.getBeschreibung());
        System.out.println();
        System.out.println("Verkaufspreis: " + verbrauchsgegenstand.getVerkaufswert());
        System.out.println();
    }

    /**
     * @param material Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     * @author OF Kretschmer
     * @since 16.11.23
     */
    private void printMaterial(Material material) {
        System.out.println("Name: " + material.getName());
        System.out.println();
        System.out.println("Verkaufspreis: " + material.getVerkaufswert());
        System.out.println();
    }


}
