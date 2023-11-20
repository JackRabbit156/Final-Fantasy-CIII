package gamehub.haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

import java.util.Map;


/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class HaendlerController {

    PartyController partyController;
    Haendler haendler;

    public HaendlerController(PartyController partyController) {
        this.partyController = partyController;
        this.haendler = new Haendler();
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 17.11.23
     * Zeigt das HaendlerMenue an mit den Optionen Kaufen/ Verkaufen/ Zurueckkaufen / zurueck zum Menue
     */
    public void haendlerAnzeigen(PartyController partyController) {
        boolean zurueckMenue = false;
        int eingabe;
        // Absprache Niels - so geht der Weg zurück zum GameHUB
        while (!zurueckMenue) {
            KonsolenAssistent.clear();
            System.out.println(Farbauswahl.RESET);
            haendlerBildAnzeigen();
            goldAnzeigen();
            haendlerMenueAnzeigen();
            boolean eingabeKorrekt = false;
            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= 4) {
                    eingabeKorrekt = true;
                    switch (eingabe) {
                        case 1:
                            KonsolenAssistent.clear();
//                            System.out.println("Wartet noch auf Niels");
//                            haendlerAnzeigen(partyController);
                            kaufenAnzeigen();
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
                            haendler.getZurueckkaufenVerbrauchsgegenstaende().clear();
                            haendler.getZurueckkaufenMaterial().clear();
                            // ToDo klappt nicht

                            zurueckMenue = true;
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

    private void sortimentErneuern() {
        for (int i = 0; i < 10; i++) {
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleWaffeFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleRuestungFuer(haendler, (int) partyController.getPartyLevel()));
        }

    }

    private void kaufenAnzeigen() {
        sortimentErneuern();
        boolean ja = true;
        while (ja) {
            KonsolenAssistent.clear();
            System.out.println("Was wollen sie Kaufen?");
            System.out.println("1. Waffen Kaufen");
            System.out.println("2. Ruestungen Kaufen");
            System.out.println("3. Accessoires Kaufen");
            System.out.println("4. Zurueck zum Haendler");
            int nutzerEingabe = ScannerHelfer.nextInt();
            switch (nutzerEingabe) {
                case 1:
                    waffenKaufen();
                    break;
                case 2:
                    ruestungKaufen();
                    break;
                case 3:
                    accessoiresKaufen();
                    break;
                case 4:
                    ja = false;
                    break;
                default:
                    System.out.println("Bitte geben sie 1-3 oder 4 fuers beenden ein");
                    break;
            }
        }
    }

    private void accessoiresKaufen() {
        System.out.println("Welches Accessoire wollen Sie kaufen");
        int nummer = 1;
        for (Accessoire accessoire : haendler.getKaufInventar().getInventarAccessiore()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.YELLOW + nummer + ".) Name: " + accessoire.getName() + " | Level: " + accessoire.getLevelAnforderung() + " | Beweglichkeit+ : " + accessoire.getBeweglichkeit() + " | Gesundheits Regeneration+ : " + accessoire.getGesundheitsRegeneration()
                        + " | Max Gesundheitspunkte+ : " + accessoire.getMaxGesundheitsPunkte()
                        + " | Max Manapunkte+ :" + accessoire.getMaxManaPunkte() + " | Kostet: " + accessoire.getKaufwert() + Farbauswahl.RESET);
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            } else {
                System.out.println(nummer + ".) Name: " + accessoire.getName() + " | Level: " + accessoire.getLevelAnforderung() + " | Beweglichkeit+ : " + accessoire.getBeweglichkeit() + " | Gesundheits Regeneration+ : " + accessoire.getGesundheitsRegeneration()
                        + " | Max Gesundheitspunkte+ : " + accessoire.getMaxGesundheitsPunkte()
                        + " | Max Manapunkte+ :" + accessoire.getMaxManaPunkte() + " | Kostet: " + accessoire.getKaufwert());
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            }

        }
        System.out.println("Wählen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarAccessiore().size()) {
            Accessoire tmp = haendler.getKaufInventar().getInventarAccessiore().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
            } else {
                System.out.println("Sire Sie haben nicht genuegend Gold!");
            }

        } else {
            System.out.println("Bitte Geben Sie eine gueltige Zahl ein!");
        }


    }

    private void ruestungKaufen() {
        System.out.println("Welche Ruestung wollen Sie kaufen");
        int nummer = 1;
        for (Ruestung ruestung : haendler.getKaufInventar().getInventarRuestung()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.BLUE + nummer + ".) Name: " + ruestung.getName() + " | Verteidigung+ : " + ruestung.getVerteidigung()
                        + " | Magische Verteidigung " + ruestung.getMagischeVerteidigung()
                        + " | Wert: " + ruestung.getKaufwert()
                        + Farbauswahl.RESET);
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            } else {
                System.out.println(nummer + ".) Name: " + ruestung.getName() + " | Verteidigung+ : " + ruestung.getVerteidigung()
                        + " | Magische Verteidigung " + ruestung.getMagischeVerteidigung()
                        + " | Wert: " + ruestung.getKaufwert());
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            }
        }
        System.out.println("Wählen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarRuestung().size()) {
            Ruestung tmp = haendler.getKaufInventar().getInventarRuestung().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
            } else {
                System.out.println("Sire Sie haben nicht genuegend Gold!");
            }

        } else {
            System.out.println("Bitte Geben Sie eine gueltige Zahl ein!");
        }
    }

    private void waffenKaufen() {
        System.out.println("Welche Waffen wollen Sie kaufen");
        int nummer = 1;
        for (Waffe waffen : haendler.getKaufInventar().getInventarWaffen()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.RED_BRIGHT + nummer + ".) Name: " + waffen.getName() + " | Angriff+ : " + waffen.getAttacke()
                        + " | Magische Anrgiff+ " + waffen.getMagischeAttacke()
                        + " | Wert: " + waffen.getKaufwert()
                        + Farbauswahl.RESET);
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            } else {
                System.out.println(nummer + ".) Name: " + waffen.getName() + " | Angriff+ : " + waffen.getAttacke()
                        + " | Magische Anrgiff+ " + waffen.getMagischeAttacke()
                        + " | Wert: " + waffen.getKaufwert());
                System.out.println("---------------------------------------------------------------------------");
                nummer++;
            }
        }
        System.out.println("Wählen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarWaffen().size()) {
            Waffe tmp = haendler.getKaufInventar().getInventarWaffen().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
            } else {
                System.out.println("Sire Sie haben nicht genuegend Gold!");
            }

        } else {
            System.out.println("Bitte Geben Sie eine gueltige Zahl ein!");
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 15.11.23
     * zeigt die Übersicht des Verkaufsmenue an und gibt dann die Moeglichkeit auszuwaehlen welche Art von Gegenstand man verkaufen
     * moechte, entsprechend geht ein Untermenue auf in dem dann die Gegenstaende der Kategorie angezeigt werden und ein verkaufen moeglich ist.
     */
    private void verkaufenAnzeigen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
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
                            verkaufenVerbrauchsgegenstaende(partyController);
                            // Öffnen Verbrauchsgegenstände Inventar mit verkaufsOption
                            break;
                        case 5:
                            KonsolenAssistent.clear();
                            verkaufenMaterial(partyController);
                            // Öffnen Materialien Inventar mit verkaufsOption
                            break;
                        case 6:
                            KonsolenAssistent.clear();
                            menuzurueck = true;
                            //TODO AUSKOMMENTIERT haendlerAnzeigen(partyController);
                            break;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                }
            }
        }
    }


    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Waffen.
     * Es werden alle Waffen des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenWaffe(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int auswahlObjekt;
            boolean eingabeKorrekt = false;
            int groesseWaffenInventar = partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().size();

            goldAnzeigen();
            System.out.println("Welche Waffe möchten Sie verkaufen?");
            for (int i = 0; i < groesseWaffenInventar; i++) {
                Waffe tmp = partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(i);
                System.out.printf("%d. %n", i + 1);
                printWaffe(tmp);
            }
            System.out.printf("%n%d. Zurück zur Verkaufsübersicht", (groesseWaffenInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseWaffenInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseWaffenInventar + 1) {
                        // Der Weg zurück ins Verkaufsmenü
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().remove(auswahlObjekt - 1);
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                }
            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Ruestung.
     * Es werden alle Ruestung des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenRuestung(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int auswahlObjekt;
            boolean eingabeKorrekt = false;
            int groesseRuestungsInventar = partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().size();
            goldAnzeigen();
            System.out.println("Welche Rüstung möchten Sie verkaufen?");
            for (int i = 0; i < groesseRuestungsInventar; i++) {
                Ruestung tmp = partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(i);
                System.out.printf("%d. %n", i + 1);
                printRuestung(tmp);
            }
            System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                    (groesseRuestungsInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseRuestungsInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseRuestungsInventar + 1) {// Der Weg zurück ins Verkaufsmenü
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().remove(auswahlObjekt - 1);
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                }
            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 16.11.23
     * oeffnet das Verkaufsmenue für Accessoire.
     * Es werden alle Accessoieres des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenAccessoire(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int auswahlObjekt;
            boolean eingabeKorrekt = false;
            int groesseAccessoireInventar = partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().size();
            goldAnzeigen();
            System.out.println("Welches Accessoire möchten Sie verkaufen?");
            for (int i = 0; i < groesseAccessoireInventar; i++) {
                Accessoire tmp = partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().get(i);
                System.out.printf("%d. %n", i + 1);
                printAccessoire(tmp);
            }
            System.out.printf("%n%d. Zurück zur Verkaufsübersicht",
                    (groesseAccessoireInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseAccessoireInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseAccessoireInventar + 1) {// Der Weg zurück ins Verkaufsmenü
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fügt es bei der Verkaufshistorie hinzu und entfernt das ausgewählte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().remove(auswahlObjekt - 1);
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                }
            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * oeffnet das Verkaufsmenue für Verbrauchsgegenstände.
     * Es werden alle Verbrauchsgegenstände des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenVerbrauchsgegenstaende(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
            int pruefungAnzahl = 0;
            boolean eingabeVerbrauchsgegenstandKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;

            goldAnzeigen();
            Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandInventar = partyController.getParty().getVerbrauchsgegenstaende();

            System.out.println("Welchen Verbrauchsgegenstand möchten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue(), entry.getKey().getVerkaufswert());
            }
            System.out.println((counter + 1) + ". zurück zur Verkaufsübersicht");
            //EINGABE
            while (!eingabeVerbrauchsgegenstandKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= verbrauchsgegenstandInventar.size() + 1) {
                    if (auswahlObjekt == verbrauchsgegenstandInventar.size() + 1) {
                        // Zurück zur Verkaufsübersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                        eingabeVerbrauchsgegenstandKorrekt = true;

                    } else {
                        System.out.println("Wie viele möchten Sie verkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stück. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt) {
                                eingabeAnzahlKorrekt = true;


                                //TODO VERSCHOBENE FUNKTION RICHTIGE EINGABE mit Veränderung Verbrauchsmaterial

                                for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
                                    if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                        partyController.goldHinzufuegen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                        entry.setValue(entry.getValue() - anzahlObjekt);

                                        haendler.getZurueckkaufenVerbrauchsgegenstaende().put(entry.getKey(), entry.getValue() + anzahlObjekt);
                                        KonsolenAssistent.clear();
                                        menuzurueck = true;
                                    }
                                }
                            } else {
                                System.out.println("So viele besitzen Sie nicht, geben Sie einen gültigen Wert ein!");
                            }
                        }
                    }
                } else {
                    System.out.println("Die Eingabe war fehlerhaft");
                }


            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * oeffnet das Verkaufsmenue für Material.
     * Es werden alle Material des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenMaterial(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
            int pruefungAnzahl = 0;
            boolean eingabeMaterialKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;

            goldAnzeigen();
            Map<Material, Integer> materialInventar = partyController.getParty().getMaterialien();

            System.out.println("Welches Material möchten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Material, Integer> entry : materialInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue(), entry.getKey().getVerkaufswert());
            }
            System.out.println((counter + 1) + ". zurück zur Verkaufsübersicht");
            //EINGABE
            while (!eingabeMaterialKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= materialInventar.size() + 1) {
                    eingabeMaterialKorrekt = true;
                    if (auswahlObjekt == materialInventar.size() + 1) {
                        // Zurück zur Verkaufsübersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {
                        System.out.println("Wie viele möchten Sie verkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Material, Integer> entry : materialInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stück. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
                                //TODO FUNKTION VON UNTERHALB RICHTIGE EINGABE KOMMENTAR VERSCHOBEN
                                for (Map.Entry<Material, Integer> entry : materialInventar.entrySet()) {
                                    if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                        partyController.goldHinzufuegen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                        entry.setValue(entry.getValue() - anzahlObjekt);
                                        haendler.getZurueckkaufenMaterial().put(entry.getKey(), anzahlObjekt);
                                        KonsolenAssistent.clear();
                                        menuzurueck = true;

                                    }
                                }
                            } else {
                                System.out.println("Falsche Eingabe!");
                            }
                        }
                    }
                } else {
                    System.out.println("Die Eingabe war fehlerhaft");
                }

                //RICHTIGE EINGABE mit Veränderung Verbrauchsmaterial


            }
        }
    }


    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * Öffnet ein Untermenü zum zurückkaufen von Gegenständen die in der akteullen Händlersitzung verkauf wurden
     */
    private void zurueckkaufenAnzeigen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int eingabe;
            boolean eingabeKorrekt = false;
            goldAnzeigen();
            while (!eingabeKorrekt) {
                zurueckkaufenAnzeigen();
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= 4) {
                    eingabeKorrekt = true;
                    switch (eingabe) {
                        case 1:
                            KonsolenAssistent.clear();
                            ausruestungsGegenstandZurueckkaufen(partyController);
                            break;
                        case 2:
                            KonsolenAssistent.clear();
                            verbrauchsGegenstandZurueckkaufen(partyController);

                            break;
                        case 3:
                            KonsolenAssistent.clear();
                            materialZurueckkaufen(partyController);

                            break;
                        case 4:
                            KonsolenAssistent.clear();
                            menuzurueck = true;
                            break;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft, geben Sie einen gültigen Wert ein");
                }
            }
        }
    }


    /**
     * @param gegenstand -
     *
     * @author HF Rode
     */
    private void gegenstandKaufen(Gegenstand gegenstand) {
//TODO Kaufmenü von Niels

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
     * @since 20.11.23
     * Zeigt das Untermenü  vom zurueckkaufen an.
     */
    private void zurueckkaufenAnzeigen() {
        System.out.println("Was möchten Sie zurueckkaufen?");
        System.out.println("1. Ausrüstungsgegenstände");
        System.out.println("2. Verbrauchsgegenstände");
        System.out.println("3. Materialien");
        System.out.println("4. Zurück zur Händlerübersicht");
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
     * @param waffe -
     *
     * @author OF Kretschmer
     * @since 16.11.23
     * Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     */
    private void printWaffe(Waffe waffe) {
        System.out.println("Name: " + waffe.getName());
        if (waffe.getAttacke() > 0) {
            System.out.println("Attacke: " + waffe.getAttacke());
        } else {
            System.out.println("MagischeAttacke: " + waffe.getMagischeAttacke());
        }
//        System.out.println("Bonus: " + waffe.getBonus() + " " + waffe.getBonusUmfang()); // Zur Zeit nicht Implementiert
        System.out.println("LevelAnforderung: " + waffe.getLevelAnforderung());
        System.out.printf(Farbauswahl.YELLOW + "Verkaufspreis %d", waffe.getVerkaufswert());
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * @param ruestung Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     *
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
        System.out.printf(Farbauswahl.YELLOW + "Verkaufspreis %d", ruestung.getVerkaufswert());
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * @param accessoire Gibt die Informationen die für den Verkauf und Rückkauf relevant sind aus
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    private void printAccessoire(Accessoire accessoire) {
        System.out.println("Name: " + accessoire.getName());
        System.out.printf("maxGesundheitspunkte +%d%n", accessoire.getMaxGesundheitsPunkte());
        System.out.printf("maxManaPunkte +%d%n", accessoire.getMaxManaPunkte());
        System.out.printf("gesundheitsRegeneration +%d%n", accessoire.getGesundheitsRegeneration());
        System.out.printf("manaRegeneration +%d%n", accessoire.getManaRegeneration());
        System.out.printf("beweglichkeit +%d%n", accessoire.getBeweglichkeit());
        System.out.println("LevelAnforderung: " + accessoire.getLevelAnforderung());
        System.out.printf(Farbauswahl.YELLOW + "Verkaufspreis %d", accessoire.getVerkaufswert());
        System.out.println(Farbauswahl.RESET);
    }


    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * ermöglicht das zurückkaufen eines Ausrüstungsgegenstandes
     */
    private void ausruestungsGegenstandZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int eingabe;
            boolean eingabeKorrekt = false;
            int listengroesse = haendler.getZurueckkaufenHistorie().size();

            goldAnzeigen();
            System.out.println("Was möchten Sie zurückkaufen? ");
            for (int i = 0; i < listengroesse; i++) {
                Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(i);
                System.out.printf("%d. %n", i + 1);
                if (tmp instanceof Waffe) {
                    printWaffe((Waffe) tmp);
                } else if (tmp instanceof Ruestung) {
                    printRuestung((Ruestung) tmp);
                } else if (tmp instanceof Accessoire) {
                    printAccessoire((Accessoire) tmp);
                }
            }
            System.out.printf("%d. zurück zur Übersicht %n", listengroesse + 1);


            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe > 0 && eingabe <= listengroesse + 1) {
                    eingabeKorrekt = true;
                    if (eingabe != listengroesse + 1) {
                        Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(eingabe - 1);
                        if (tmp.getVerkaufswert() > partyController.getPartyGold()) {
                            System.out.println("Sie verfügen nicht über genug Gold");
                        } else {
                            partyController.goldAbziehen(tmp.getVerkaufswert());
                            if (tmp instanceof Waffe) {
                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add((Waffe) tmp);
                            } else if (tmp instanceof Ruestung) {
                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add((Ruestung) tmp);
                            } else if (tmp instanceof Accessoire) {
                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add((Accessoire) tmp);
                            }
                            haendler.getZurueckkaufenHistorie().remove(eingabe - 1);
                        }
                    }
                    menuzurueck = true;
                } else {
                    System.out.println("Die Eingabe ist ungültig, geben Sie einen gültigen Wert ein. ");
                }
            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * ermöglicht das zurückkaufen eines Verbrauchsgegenstandes
     */
    private void verbrauchsGegenstandZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int listengroesse = haendler.getZurueckkaufenVerbrauchsgegenstaende().size();
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
            int pruefungAnzahl = 0;
            boolean eingabeVerbrauchsgegenstandKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;


            goldAnzeigen();
            System.out.println("Was möchten Sie zurückkaufen? ");


            Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandHistorie = haendler.getZurueckkaufenVerbrauchsgegenstaende();
            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                keyName[counter] = entry.getKey().getName();
                counter++;
                System.out.printf("%d. %5d x  %s %d Gold%n", counter, entry.getValue(), entry.getKey().getName(), entry.getKey().getVerkaufswert());
            }
            System.out.printf("%d. zurück zur Übersicht %n", listengroesse + 1);


            //EINGABE
            while (!eingabeVerbrauchsgegenstandKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= listengroesse + 1) {
                    if (auswahlObjekt == listengroesse + 1) {
                        // Zurück zur Verkaufsübersicht
                        KonsolenAssistent.clear();
                        eingabeVerbrauchsgegenstandKorrekt = true;
                        menuzurueck = true;
                    } else {
                        System.out.println("Wie viele möchten Sie zurueckkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Es sind %d Stück vorhanden. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt >= 0) {
                                eingabeAnzahlKorrekt = true;
                            } else {
                                System.out.println("So viele stehen nicht zur verfügung, geben Sie einen gültigen Wert ein!");
                            }
                        }

                        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                entry.setValue(entry.getValue() - anzahlObjekt);
                                partyController.getParty().getVerbrauchsgegenstaende().put(entry.getKey(), entry.getValue() + anzahlObjekt);
                                KonsolenAssistent.clear();
                                menuzurueck = true;
                            }
                        }
                    }
                } else {
                    System.out.println("Die Eingabe war fehlerhaft");
                }
            }
        }
    }

    /**
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * ermöglicht das zurückkaufen eines Materials
     */
    private void materialZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int listengroesse = haendler.getZurueckkaufenMaterial().size();
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
            int pruefungAnzahl = 0;
            boolean eingabeMaterialKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;


            goldAnzeigen();
            Map<Material, Integer> materialHistorie = haendler.getZurueckkaufenMaterial();

            System.out.println("Was möchten Sie zurückkaufen? ");

            for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                keyName[counter] = entry.getKey().getName();
                counter++;
                System.out.printf("%d. %5d x  %s %d Gold%n", counter, entry.getValue(), entry.getKey().getName(), entry.getKey().getVerkaufswert());
            }
            System.out.printf("%d. zurück zur Übersicht %n", listengroesse + 1);


            //EINGABE
            while (!eingabeMaterialKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= listengroesse + 1) {
                    eingabeMaterialKorrekt = true;
                    if (auswahlObjekt == listengroesse + 1) {
                        // Zurück zur Verkaufsübersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;

                    } else {
                        System.out.println("Wie viele möchten Sie zurueckkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Es sind %d Stück vorhanden. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
                                for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                                    if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                        partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                        entry.setValue(entry.getValue() - anzahlObjekt);
                                        partyController.getParty().getMaterialien().put(entry.getKey(), entry.getValue() + anzahlObjekt);
                                        KonsolenAssistent.clear();
                                        menuzurueck = true;
                                    }
                                }
                            } else {
                                System.out.println("So viele stehen nicht zur verfügung, geben Sie einen gültigen Wert ein!");
                            }
                        }


                    }
                } else {
                    System.out.println("Die Eingabe war fehlerhaft");
                }
            }
        }
    }
}

