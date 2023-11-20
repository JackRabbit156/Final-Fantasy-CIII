package gamehub.haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
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
                            KonsolenAssistent.clear();
                            //TODO
                            // Öffnen kaufmenü von Niels
                            break;
                        case 2:
                            KonsolenAssistent.clear();
                            verkaufenAnzeigen(partyController);
                            // Öffnet Spielerinventare mit Verkaufsoptionen
                            break;
                        case 3:
                            KonsolenAssistent.clear();
                            zurueckkaufenAnzeigen(partyController);
                            // Öffnet die Verkaufshistory
                            break;
                        case 4:
                            KonsolenAssistent.clear();
                            haendler.getZurueckkaufenHistorie().clear();
                            zurückMenue = true;
                            // löschen der verkaufshiytory
                            // Zurück zum Menü
                            break;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, geben Sie einen gültigen Wert ein");
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
                        KonsolenAssistent.clear();
                        verkaufenWaffe(partyController);
                        // Öffnen Waffeninventar mit verkaufsOption
                        break;
                    case 2:
                        KonsolenAssistent.clear();
                        verkaufenRuestung(partyController);
                        // Öffnen Rüstungsinventar mit verkaufsOption
                        break;
                    case 3:
                        KonsolenAssistent.clear();
                        verkaufenAccessoire(partyController);
                        // Öffnen Accessoireinventar mit verkaufsOption
                        break;
                    case 4:
                        KonsolenAssistent.clear();
                        verkaufenVerbrauchsgegenstände(partyController);
                        // Öffnen Verbrauchsgegenstände Inventar mit verkaufsOption
                        break;
                    case 5:
                        KonsolenAssistent.clear();
                        verkaufenMaterial(partyController);
                        // Öffnen Materialien Inventar mit verkaufsOption
                        break;
                    case 6:
                        KonsolenAssistent.clear();
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
        int groeßeWaffenInventar = partyController.getInventar.getWaffenInventar.length;

        goldAnzeigen();
        System.out.println("Welche Waffe möchten Sie verkaufen?");
        for (int i = 0; i < groeßeWaffenInventar; i++) {
            Waffe tmp = partyController.getInventar.getWaffenInventar(i);
            System.out.printf("%d. %n", i + 1);
            printWaffe(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht", (groeßeWaffenInventar + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= groeßeWaffenInventar + 2) {
                eingabeKorrekt = true;
                if (auswahlObjekt == groeßeWaffenInventar + 2) {
                    // Der Weg zurück ins Verkaufsmenü
                    KonsolenAssistent.clear();
                    verkaufenAnzeigen(partyController);
                } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                    haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getWaffenInventar(auswahlObjekt));
                    partyController.goldHinzufuegen(partyController.getInventar.getWaffenInventar(auswahlObjekt).getVerkaufspreis);
                    partyController.getInventar.getWaffenInventar(auswahlObjekt).remove;
                    KonsolenAssistent.clear();
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
        int groeßeRuestungsInventar = partyController.getInventar.getRuestungsInventar.length;
        goldAnzeigen();
        System.out.println("Welche Rüstung möchten Sie verkaufen?");
        for (int i = 0; i < groeßeRuestungsInventar; i++) {
            Ruestung tmp = partyController.getInventar.getRuestungsInventar(i);
            System.out.printf("%d. %n", i + 1);
            printRuestung(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (groeßeRuestungsInventar + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= groeßeRuestungsInventar + 2) {
                eingabeKorrekt = true;
                if (auswahlObjekt == groeßeRuestungsInventar + 2) {// Der Weg zurück ins Verkaufsmenü
                    KonsolenAssistent.clear();
                    verkaufenAnzeigen(partyController);
                } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                    haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getRuestungsInventar(auswahlObjekt));
                    partyController.goldHinzufuegen(partyController.getInventar.getRuestungInventar(auswahlObjekt).getVerkaufspreis);
                    partyController.getInventar.getRuestungsInventar(auswahlObjekt).remove;
                    KonsolenAssistent.clear();
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
        int groeßeAccessoireInventar = partyController.getInventar.getAccessoireInventar.length;
        goldAnzeigen();
        System.out.println("Welches Accessoire möchten Sie verkaufen?");
        for (int i = 0; i < groeßeAccessoireInventar; i++) {
            Accessoire tmp = partyController.getInventar.getAccessoireInventar(i);
            System.out.printf("%d. %n", i + 1);
            printAccessoire(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (groeßeAccessoireInventar + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= groeßeAccessoireInventar + 2) {
                eingabeKorrekt = true;
                if (auswahlObjekt == groeßeAccessoireInventar + 2) {// Der Weg zurück ins Verkaufsmenü
                    KonsolenAssistent.clear();
                    verkaufenAnzeigen(partyController);
                } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                    haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getAccessoireInventar(auswahlObjekt));
                    partyController.goldHinzufuegen(partyController.getInventar.getAccessoireInventar(auswahlObjekt).getVerkaufspreis);
                    partyController.getInventar.getAccessoireInventar(auswahlObjekt).remove;
                    KonsolenAssistent.clear();
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
        int groeßeVerbrauchsgegenstandInventar = partyController.getInventar.getVerbrauchsgegenstandInventar.size();
        goldAnzeigen();
        System.out.println("Welchen Verbrauchsgegenstand möchten Sie verkaufen?");
        for (int i = 0; i < groeßeVerbrauchsgegenstandInventar; i++) {
            Verbrauchsgegenstand tmp = partyController.getInventar.getVerbrauchsgegenstandInventar(i);
            System.out.printf("%d. %n", i + 1);
            printVerbrauchsgegenstand(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (groeßeVerbrauchsgegenstandInventar + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= groeßeVerbrauchsgegenstandInventar + 2) {
                eingabeKorrekt = true;
                if (auswahlObjekt == groeßeVerbrauchsgegenstandInventar + 2) {// Der Weg zurück ins Verkaufsmenü
                    KonsolenAssistent.clear();
                    verkaufenAnzeigen(partyController);
                } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                    haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getVerbrauchsgegenstanfInventar(auswahlObjekt));
                    partyController.goldHinzufuegen(partyController.getInventar.getVerbrauchsgegenstandInventar(auswahlObjekt).getVerkaufspreis);
                    partyController.getVerbrauchsgegenstandInventar(auswahlObjekt).remove;
                    KonsolenAssistent.clear();
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
        int groeßeMaterialInventar = partyController.getInventar.getMaterialInventar.size();
        goldAnzeigen();
        System.out.println("Welches Material möchten Sie verkaufen?");
        for (int i = 0; i < groeßeMaterialInventar; i++) {
            Material tmp = partyController.getInventar.getMaterialInventar(i);
            System.out.printf("%d. %n", i + 1);
            printMaterial(tmp);
        }
        System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                (groeßeMaterialInventar + 2));
        while (!eingabeKorrekt) {
            auswahlObjekt = ScannerHelfer.nextInt();
            if (auswahlObjekt >= 1 && auswahlObjekt <= groeßeMaterialInventar + 2) {
                eingabeKorrekt = true;
                if (auswahlObjekt == groeßeMaterialInventar + 2) {// Der Weg zurück ins Verkaufsmenü
                    KonsolenAssistent.clear();
                    verkaufenAnzeigen(partyController);
                } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                    haendler.getZurueckkaufenHistorie().add(partyController.getInventar.getMaterialInventar(auswahlObjekt));
                    partyController.goldHinzufuegen(partyController.getInventar.getMaterialInventar(auswahlObjekt).getVerkaufspreis);
                    partyController.getInventar.getMaterialInventar(auswahlObjekt).remove;
                    KonsolenAssistent.clear();
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
            } else if (tmp instanceof Material) {
                printMaterial((Material) tmp);
            }
        }
        System.out.printf("%n%d. Zurück zum Händler", haendler.getZurueckkaufenHistorie().size() + 2);

        while (!eingabeKorrekt) {
            auswahlGegenstand = ScannerHelfer.nextInt();
            if (auswahlGegenstand >= 1 && auswahlGegenstand <= haendler.getZurueckkaufenHistorie().size() + 2) {
                eingabeKorrekt = true;
                if (auswahlGegenstand == haendler.getZurueckkaufenHistorie().size() + 2) {// Der Weg zurück zum Haendler
                    KonsolenAssistent.clear();
                    haendlerAnzeigen(partyController);
                } else {// Prüft die Art des Gegenstandes und fügt diesen dem entsprechenden Inventar wieder hinzu und nimmt ihn aus der zurückkaufenListe raus, wenn genug Gold vorhanden ist.
                    if (partyController.getPartyGold() >= (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1).getVerkaufswert())) {
                        if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Waffe) {
                            partyController.getWaffeninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Ruestung) {
                            partyController.getRuestungsinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Accessoire) {
                            partyController.getGegenstandinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Verbrauchsgegenstand) {
                            partyController.getVerbrauchsgegenstandninventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
                        } else if (haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1) instanceof Material) {
                            partyController.getMaterialinventar.add(haendler.getZurueckkaufenHistorie().get(auswahlGegenstand - 1));
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
//TODO Kaufmenü von Niels

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
        KonsolenAssistent.clear();
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
        System.out.printf(Farbauswahl.YELLOW + "Sie besitzen %d Gold. ", partyController.getPartyGold());
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
//        System.out.println("Bonus: " + waffe.getBonus() + " " + waffe.getBonusUmfang()); // Zur Zeit nicht Implementiert
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
//        System.out.println("Bonus: " + ruestung.getBonus() + " " + ruestung.getBonusUmfang()); // Zur Zeit nicht implementiert
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
