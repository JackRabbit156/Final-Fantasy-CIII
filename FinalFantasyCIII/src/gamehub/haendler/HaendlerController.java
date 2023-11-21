package gamehub.haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.manatraenke.GrosserManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.KleinerManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.MittlererManatrank;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.MittlererHeiltrank;
import hilfsklassen.AsciiHelfer;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

import java.util.Collections;
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
     * Erkennt das ausgewaehlte Verbrauchsgegenstand-Objekt anhand der übergebenen Nummer in der Map.
     *
     * @param map            Die Map von Verbrauchsgegenstaenden mit zugehörigen Anzahlen.
     * @param selectedNumber Die ausgewaehlte Nummer des Verbrauchsgegenstands.
     * @return Das Verbrauchsgegenstand-Objekt, das der ausgewaehlten Nummer entspricht,
     * oder null, wenn keine uebereinstimmung gefunden wurde.
     * @author HF Rode
     * @since 18.11.2023
     */
    private static Verbrauchsgegenstand erkenneAusgewaehltesVerbrauchsItem(Map<Verbrauchsgegenstand, Integer> map, int selectedNumber) {
        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : map.entrySet()) {
            if (nummer == selectedNumber) {
                return entry.getKey();
            }
            nummer++;
        }
        return null;
    }

    /**
     * Erkennt das ausgewählte Verbrauchsgegenstand-Objekt anhand der übergebenen Nummer in der Map.
     *
     * @param map            Die Map von Verbrauchsgegenständen mit zugehörigen Anzahlen.
     * @param selectedNumber Die ausgewählte Nummer des Verbrauchsgegenstands.
     *
     * @return Das Verbrauchsgegenstand-Objekt, das der ausgewählten Nummer entspricht,
     * oder null, wenn keine Übereinstimmung gefunden wurde.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static Material erkenneAusgewaehltesMaterialItem(Map<Material, Integer> map, int selectedNumber) {
        int nummer = 1;
        for (Map.Entry<Material, Integer> entry : map.entrySet()) {
            if (nummer == selectedNumber) {
                return entry.getKey();
            }
            nummer++;
        }
        return null;
    }

    /**
     * Zeigt das HaendlerMenue an mit den Optionen Kaufen/ Verkaufen/ Zurueckkaufen / zurueck zum Menue
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 17.11.23
     */
    public void haendlerAnzeigen(PartyController partyController) {
        boolean zurueckMenue = false;
        int eingabe;
        // Absprache Niels - so geht der Weg zurueck zum GameHUB
        while (!zurueckMenue) {
            KonsolenAssistent.clear();
            System.out.println(Farbauswahl.RESET);
            AsciiHelfer.haendlerBildAnzeigen();
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
                            zurueckMenue = true;
                            // löschen der verkaufshiytory
                            // Zurueck zum Menue
                            break;
                    }
                } else {
                    falscheEingabe();
                }
            }
        }
    }

    /**
     * Diese Methode erneuert das Sortiment des Haendlers, indem es die vorhandenen Kaufgegenstaende
     * löscht und neue Gegenstaende hinzufuegt. Es werden Accessoires, Waffen und Ruestungen fuer den
     * Haendler erstellt und dem Kaufinventar hinzugefuegt. Zusaetzlich werden Verbrauchsgegenstaende
     * wie Heil- und Manatraenke dem Verbrauchsgegenstands-Inventar des Haendlers hinzugefuegt.
     * <p>
     * Die Methode wird normalerweise aufgerufen, wenn der Spieler das Kaufmenue betritt oder wenn
     * sich das Sortiment des Haendlers aendern soll, z.B. nach einem bestimmten Zeitraum im Spiel.
     *
     * @author HF Rode
     * @see AusruestungsgegenstandFabrik
     * @since 21.11.2023
     */
    private void sortimentErneuern() {
        haendler.getKaufVerbrauchsInventar().clear();
        haendler.getKaufInventar().getInventarAccessiore().clear();
        haendler.getKaufInventar().getInventarWaffen().clear();
        haendler.getKaufInventar().getInventarRuestung().clear();
        haendler.getKaufMaterialInventar().clear();
        for (int i = 0; i < 10; i++) {
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleWaffeFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleRuestungFuer(haendler, (int) partyController.getPartyLevel()));
        }
        haendler.getKaufVerbrauchsInventar().put(new KleinerManatrank(), 10);
        haendler.getKaufVerbrauchsInventar().put(new MittlererManatrank(), 10);
        haendler.getKaufVerbrauchsInventar().put(new GrosserManatrank(), 10);

        haendler.getKaufVerbrauchsInventar().put(new KleinerHeiltrank(), 10);
        haendler.getKaufVerbrauchsInventar().put(new MittlererHeiltrank(), 10);
        haendler.getKaufVerbrauchsInventar().put(new GrosserHeiltrank(), 10);

        haendler.getKaufMaterialInventar().put(new Eisenerz(), 10);
        haendler.getKaufMaterialInventar().put(new Silbererz(), 10);
        haendler.getKaufMaterialInventar().put(new Golderz(), 10);
        haendler.getKaufMaterialInventar().put(new Mithril(), 10);


    }

    /**
     * Diese Methode zeigt dem Spieler eine Liste von Kaufmöglichkeiten im Haendler-Menü an.
     * Der Spieler kann zwischen dem Kauf von Waffen, Rüstungen, Accessoires und Verbrauchsgegenständen
     * wählen. Die Methode ruft die entsprechenden Methoden auf, um den Kaufvorgang für den ausgewählten
     * Gegenstandstyp zu ermöglichen. Der Spieler hat auch die Option, zum Haendler-Menü zurückzukehren.
     * <p>
     * Die Methode wird in einer Schleife ausgeführt, solange der Spieler Kaufaktionen durchführen möchte.
     * Ungültige Eingaben des Spielers werden behandelt und es wird eine entsprechende Meldung ausgegeben.
     *
     * @author HF Rode
     * @see #waffenKaufen()
     * @see #ruestungKaufen()
     * @see #accessoiresKaufen()
     * @see #verbrauchsgegenstandKaufen()
     * @since 21.11.2023
     */
    private void kaufenAnzeigen() {
        sortimentErneuern();
        boolean ja = true;
        while (ja) {
            goldAnzeigen();
            System.out.println("Was wollen sie Kaufen?");
            System.out.println("1. Waffen Kaufen");
            System.out.println("2. Ruestungen Kaufen");
            System.out.println("3. Accessoires Kaufen");
            System.out.println("4. Verbrauchsgegenstaende Kaufen");
            System.out.println("5. Upgrade Materialien Kaufen");
            System.out.println("6. Zurueck zum Haendler");
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
                    verbrauchsgegenstandKaufen();
                    break;
                case 5:
                    materialienkaufen();
                    break;
                case 6:
                    ja = false;
                    break;
                default:
                    System.out.println("Bitte geben sie 1-5 oder 6 fuers beenden ein");
                    int i = ScannerHelfer.nextInt();
                    KonsolenAssistent.clear();
                    break;
            }
        }
    }

    private void materialienkaufen() {
        KonsolenAssistent.clear();
        Map<Material, Integer> map = haendler.getKaufMaterialInventar();

        System.out.println(String.format("| %-30s | %-8s | %-5s |", "Gegenstand", "Anzahl", "Wert"));
        System.out.println(String.join("", Collections.nCopies(18 + 8 + 18 + 9, "-")));

        int nummer = 1;
        for (Map.Entry<Material, Integer> eintrag : map.entrySet()) {
            Material gegenstand = eintrag.getKey();
            int anzahl = eintrag.getValue();

            System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-20s" + Farbauswahl.RESET + " | %8d | " + Farbauswahl.YELLOW + "%5d" + Farbauswahl.RESET + " |%n",
                    nummer++, gegenstand.getName(), anzahl, gegenstand.getKaufwert());
        }
        System.out.println("Waehlen sie aus oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();

        if (auswahl > 0 && auswahl <= haendler.getKaufMaterialInventar().size()) {
            int grundwert;
            Material ausgewaehltergegenstand = erkenneAusgewaehltesMaterialItem(map, auswahl);
            if (ausgewaehltergegenstand == null) {
                System.out.println("Hier sollte man eigentlich nicht hinkommen, easteregg I guess <3");
            } else {
                if (partyController.getPartyGold() >= ausgewaehltergegenstand.getKaufwert()) {
                    if (partyController.getParty().getMaterialien().get(ausgewaehltergegenstand) == null) {
                        grundwert = 0;
                    } else {
                        grundwert = partyController.getParty().getMaterialien().get(ausgewaehltergegenstand);
                    }
                    partyController.getParty().getMaterialien().put(ausgewaehltergegenstand, grundwert + 1);
                    partyController.getParty().setGold(partyController.getPartyGold() - ausgewaehltergegenstand.getKaufwert());
                    haendler.getKaufMaterialInventar().put(ausgewaehltergegenstand, haendler.getKaufMaterialInventar().get(ausgewaehltergegenstand) - 1);
                    if (haendler.getKaufMaterialInventar().get(ausgewaehltergegenstand) <= 0) {
                        haendler.getKaufMaterialInventar().remove(ausgewaehltergegenstand);
                    }
                    System.out.println("Erfolgreich gekauft");
                } else {
                    nichtGenugGold();
                }
            }
        } else {
            KonsolenAssistent.clear();
        }
    }

    /**
     * Diese Methode ermöglicht es dem Spieler, Verbrauchsgegenstaende vom Haendler zu kaufen.
     * Es zeigt eine Liste von Verbrauchsgegenstaenden im Inventar des Haendlers an, einschließlich
     * Informationen wie Name, Anzahl im Inventar, Wert und ermöglicht dem Spieler die Auswahl
     * zum Kauf. Die ausgewaehlten Verbrauchsgegenstaende werden dem Inventar der Party hinzugefuegt,
     * und der entsprechende Goldbetrag wird von der Partykasse abgezogen.
     * <p>
     * Falls die Auswahl ungueltig ist oder der Spieler nicht genuegend Gold hat, werden entsprechende
     * Meldungen auf der Konsole ausgegeben. Bei erfolgreichem Kauf wird eine Bestaetigungsmeldung angezeigt.
     *
     * @author HF Rode
     * @since 21.11.2023
     */
    private void verbrauchsgegenstandKaufen() {
        KonsolenAssistent.clear();
        Map<Verbrauchsgegenstand, Integer> map = haendler.getKaufVerbrauchsInventar();

        System.out.println(String.format("| %-30s | %-8s | %-5s |", "Gegenstand", "Anzahl", "Wert"));
        System.out.println(String.join("", Collections.nCopies(18 + 8 + 18 + 9, "-")));

        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> eintrag : map.entrySet()) {
            Verbrauchsgegenstand gegenstand = eintrag.getKey();
            int anzahl = eintrag.getValue();

            System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-20s" + Farbauswahl.RESET + " | %8d | " + Farbauswahl.YELLOW + "%5d" + Farbauswahl.RESET + " |%n",
                    nummer++, gegenstand.getName(), anzahl, gegenstand.getKaufwert());
        }
        System.out.println("Waehlen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();

        if (auswahl > 0 && auswahl <= haendler.getKaufVerbrauchsInventar().size()) {
            int grundwert;
            Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesVerbrauchsItem(map, auswahl);
            if (ausgewaehltergegenstand == null) {
                System.out.println("Hier wurde ein unvorhergesehender Error erzeugt bei den Verbrauchsgegenstaenden 316");
            } else {
                if (partyController.getPartyGold() >= ausgewaehltergegenstand.getKaufwert()) {
                    if (partyController.getParty().getVerbrauchsgegenstaende().get(ausgewaehltergegenstand) == null) {
                        grundwert = 0;
                    } else {
                        grundwert = partyController.getParty().getVerbrauchsgegenstaende().get(ausgewaehltergegenstand);
                    }
                    partyController.getParty().getVerbrauchsgegenstaende().put(ausgewaehltergegenstand, grundwert + 1);
                    partyController.getParty().setGold(partyController.getPartyGold() - ausgewaehltergegenstand.getKaufwert());
                    haendler.getKaufVerbrauchsInventar().put(ausgewaehltergegenstand, haendler.getKaufVerbrauchsInventar().get(ausgewaehltergegenstand) - 1);
                    if (haendler.getKaufVerbrauchsInventar().get(ausgewaehltergegenstand) <= 0) {
                        haendler.getKaufVerbrauchsInventar().remove(ausgewaehltergegenstand);
                    }
                    System.out.println("Erfolgreich gekauft");
                } else {
                    nichtGenugGold();
                }
            }
        } else {
            KonsolenAssistent.clear();
        }
    }

    /**
     * Fuehrt den Kaufvorgang fuer Accessoires beim Haendler durch.
     * Zeigt die verfuegbaren Accessoires im Inventar des Haendlers an und ermöglicht dem Spieler,
     * ein Accessoire zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author HF Rode
     * @see Accessoire
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 20.11.2023
     */
    private void accessoiresKaufen() {
        System.out.println("Welches Accessoire wollen Sie kaufen");
        int nummer = 1;
        for (Accessoire accessoire : haendler.getKaufInventar().getInventarAccessiore()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.YELLOW + nummer + ".) Name: " + accessoire.getName() + " | Level: " + accessoire.getLevelAnforderung() + " | Beweglichkeit+ : " + accessoire.getBeweglichkeit() + " | Gesundheits Regeneration+ : " + accessoire.getGesundheitsRegeneration()
                        + " | Max Gesundheitspunkte+ : " + accessoire.getMaxGesundheitsPunkte()
                        + " | Max Manapunkte+ :" + accessoire.getMaxManaPunkte() + " | Kostet: " + accessoire.getKaufwert() + Farbauswahl.RESET);
            } else {
                System.out.println(nummer + ".) Name: " + accessoire.getName() + " | Level: " + accessoire.getLevelAnforderung() + " | Beweglichkeit+ : " + accessoire.getBeweglichkeit() + " | Gesundheits Regeneration+ : " + accessoire.getGesundheitsRegeneration()
                        + " | Max Gesundheitspunkte+ : " + accessoire.getMaxGesundheitsPunkte()
                        + " | Max Manapunkte+ :" + accessoire.getMaxManaPunkte() + " | Kostet: " + accessoire.getKaufwert());
            }
            System.out.println("---------------------------------------------------------------------------");
            nummer++;

        }
        System.out.println("Waehlen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarAccessiore().size()) {
            Accessoire tmp = haendler.getKaufInventar().getInventarAccessiore().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
                System.out.println("Erfolgreich gekauft");
            } else {
                nichtGenugGold();
            }
        } else {
            KonsolenAssistent.clear();
        }
    }

    /**
     * Fuehrt den Kaufvorgang fuer Ruestungen beim Haendler durch.
     * Zeigt die verfuegbaren Ruestungen im Inventar des Haendlers an und ermöglicht dem Spieler,
     * eine Ruestung zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author HF Rode
     * @see Ruestung
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 20.11.2023
     */
    private void ruestungKaufen() {
        System.out.println("Welche Ruestung wollen Sie kaufen");
        int nummer = 1;
        for (Ruestung ruestung : haendler.getKaufInventar().getInventarRuestung()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.BLUE + nummer + ".) Name: " + ruestung.getName() + " | Verteidigung+ : " + ruestung.getVerteidigung()
                        + " | Magische Verteidigung " + ruestung.getMagischeVerteidigung()
                        + " | Wert: " + ruestung.getKaufwert()
                        + Farbauswahl.RESET);
            } else {
                System.out.println(nummer + ".) Name: " + ruestung.getName() + " | Verteidigung+ : " + ruestung.getVerteidigung()
                        + " | Magische Verteidigung " + ruestung.getMagischeVerteidigung()
                        + " | Wert: " + ruestung.getKaufwert());
            }
            System.out.println("---------------------------------------------------------------------------");
            nummer++;
        }
        System.out.println("Waehlen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarRuestung().size()) {
            Ruestung tmp = haendler.getKaufInventar().getInventarRuestung().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
                System.out.println("Erfolgreich gekauft");
            } else {
                nichtGenugGold();
            }
        } else {
            KonsolenAssistent.clear();
        }
    }

    /**
     * Fuehrt den Kaufvorgang fuer Waffen beim Haendler durch.
     * Zeigt die verfuegbaren Waffen im Inventar des Haendlers an und ermöglicht dem Spieler,
     * eine Waffe zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author HF Rode
     * @see Waffe
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 20.11.2023
     */
    private void waffenKaufen() {
        System.out.println("Welche Waffen wollen Sie kaufen");
        int nummer = 1;
        for (Waffe waffen : haendler.getKaufInventar().getInventarWaffen()) {
            if (nummer % 2 == 0) {
                System.out.println(Farbauswahl.RED_BRIGHT + nummer + ".) Name: " + waffen.getName() + " | Angriff+ : " + waffen.getAttacke()
                        + " | Magische Anrgiff+ " + waffen.getMagischeAttacke()
                        + " | Wert: " + waffen.getKaufwert()
                        + Farbauswahl.RESET);
            } else {
                System.out.println(nummer + ".) Name: " + waffen.getName() + " | Angriff+ : " + waffen.getAttacke()
                        + " | Magische Anrgiff+ " + waffen.getMagischeAttacke()
                        + " | Wert: " + waffen.getKaufwert());
            }
            System.out.println("---------------------------------------------------------------------------");
            nummer++;
        }
        System.out.println("Waehlen sie oder druecken sie Enter um zurueck zu gehen: ");
        int auswahl = ScannerHelfer.nextInt();
        if (auswahl > 0 && auswahl <= haendler.getKaufInventar().getInventarWaffen().size()) {
            Waffe tmp = haendler.getKaufInventar().getInventarWaffen().get(auswahl - 1);
            if (partyController.getPartyGold() >= tmp.getKaufwert()) {
                partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(tmp);
                haendler.getKaufInventar().ausruestungsgegenstandEntfernen(tmp);
                partyController.getParty().setGold(partyController.getPartyGold() - tmp.getKaufwert());
                System.out.println("Erfolgreich gekauft");
            } else {
                nichtGenugGold();
            }
        } else {
            KonsolenAssistent.clear();
        }
    }

    /**
     * zeigt die uebersicht des Verkaufsmenue an und gibt dann die Moeglichkeit auszuwaehlen welche Art von Gegenstand man verkaufen
     * moechte, entsprechend geht ein Untermenue auf in dem dann die Gegenstaende der Kategorie angezeigt werden und ein verkaufen moeglich ist.
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 15.11.23
     **/
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
                            // Öffnen Ruestungsinventar mit verkaufsOption
                            break;
                        case 3:
                            KonsolenAssistent.clear();
                            verkaufenAccessoire(partyController);
                            // Öffnen Accessoireinventar mit verkaufsOption
                            break;
                        case 4:
                            KonsolenAssistent.clear();
                            verkaufenVerbrauchsgegenstaende(partyController);
                            // Öffnen Verbrauchsgegenstaende Inventar mit verkaufsOption
                            break;
                        case 5:
                            KonsolenAssistent.clear();
                            verkaufenMaterial(partyController);
                            // Öffnen Materialien Inventar mit verkaufsOption
                            break;
                        case 6:
                            KonsolenAssistent.clear();
                            menuzurueck = true;
                            break;
                    }
                } else {
                    falscheEingabe();
                }
            }
        }
    }


    /**
     * oeffnet das Verkaufsmenue für Waffen.
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 21.11.23
     */
    private void verkaufenWaffe(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int auswahlObjekt;
            boolean eingabeKorrekt = false;
            int groesseWaffenInventar = partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().size();

            goldAnzeigen();
            System.out.println("Welche Waffe moechten Sie verkaufen?");
            for (int i = 0; i < groesseWaffenInventar; i++) {
                Waffe tmp = partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(i);
                System.out.printf("%d. %n", i + 1);
                printWaffe(tmp);
            }
            System.out.printf("%n%d. Zurueck zur Verkaufsuebersicht", (groesseWaffenInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseWaffenInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseWaffenInventar + 1) {
                        // Der Weg zurueck ins Verkaufsmenue
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fuegt es bei der Verkaufshistorie hinzu und entfernt das ausgewaehlte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().remove(auswahlObjekt - 1);
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    }
                } else {
                    falscheEingabe();
                }
            }
        }
    }

    /**
     * oeffnet das Verkaufsmenue für Ruestung.
     *
     * oeffnet das Verkaufsmenue fuer Ruestung.
     *
     * @param partyController -
     * @author OF Kretschmer
     * @since 21.11.23
     * oeffnet das Verkaufsmenue fuer Ruestung.
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
            System.out.println("Welche Ruestung moechten Sie verkaufen?");
            for (int i = 0; i < groesseRuestungsInventar; i++) {
                Ruestung tmp = partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(i);
                System.out.printf("%d. %n", i + 1);
                printRuestung(tmp);
            }
            System.out.printf("%n%d. Zurueck zur Verkaufsuebersicht",
                    (groesseRuestungsInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseRuestungsInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseRuestungsInventar + 1) {// Der Weg zurueck ins Verkaufsmenue
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fuegt es bei der Verkaufshistorie hinzu und entfernt das ausgewaehlte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().remove(auswahlObjekt - 1);
                    }
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                } else {
                    falscheEingabe();
                }
            }
        }
    }

    /**
     * oeffnet das Verkaufsmenue fuer Accessoire.
     * Es werden alle Accessoieres des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 21.11.23
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
            System.out.printf("%n%d. Zurueck zur Verkaufsuebersicht",
                    (groesseAccessoireInventar + 1));
            while (!eingabeKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= groesseAccessoireInventar + 1) {
                    eingabeKorrekt = true;
                    if (auswahlObjekt == groesseAccessoireInventar + 1) {// Der Weg zurueck ins Verkaufsmenue
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {// fuegt es bei der Verkaufshistorie hinzu und entfernt das ausgewaehlte Objekt aus dem Inventar
                        haendler.getZurueckkaufenHistorie().add(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().get(auswahlObjekt - 1));
                        partyController.goldHinzufuegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().get(auswahlObjekt - 1).getVerkaufswert());
                        partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().remove(auswahlObjekt - 1);
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    }
                } else {
                   falscheEingabe();
                }
            }
        }
    }



    /**
     * Es werden alle Verbrauchsgegenstände des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     *
     * @param partyController -
     * @author OF Kretschmer
     * @since 21.11.23
     * oeffnet das Verkaufsmenue fuer Verbrauchsgegenstaende.
     * Es werden alle Verbrauchsgegenstaende des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     */
    private void verkaufenVerbrauchsgegenstaende(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt;
            int pruefungAnzahl = 0;
            boolean eingabeVerbrauchsgegenstandKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;

            goldAnzeigen();
            Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandInventar = partyController.getParty().getVerbrauchsgegenstaende();

            System.out.println("Welchen Verbrauchsgegenstand moechten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue(), entry.getKey().getVerkaufswert());
            }
            System.out.println((counter + 1) + ". zurueck zur Verkaufsuebersicht");
            //EINGABE
            while (!eingabeVerbrauchsgegenstandKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= verbrauchsgegenstandInventar.size() + 1) {
                    if (auswahlObjekt == verbrauchsgegenstandInventar.size() + 1) {
                        // Zurueck zur Verkaufsuebersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                        eingabeVerbrauchsgegenstandKorrekt = true;

                    } else {
                        System.out.println("Wie viele moechten Sie verkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stueck. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt) {
                                eingabeAnzahlKorrekt = true;

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
                                System.out.print(Farbauswahl.RED + "So viele besitzen Sie nicht, geben Sie einen gueltigen Wert ein!");
                                System.out.println(Farbauswahl.RESET);
                            }
                        }
                    }
                } else {
                    falscheEingabe();
                }


            }
        }
    }

    /**
     * oeffnet das Verkaufsmenue fuer Material.
     * Es werden alle Material des Inventars angezeigt und es kann eine ausgewaehlt werden zum verkaufen,
     * diese wird der Verkaufshistorie (zum zurueckkaufen) hinzugefuegt und aus dem Inventar geloescht.
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 21.11.23
     */
    private void verkaufenMaterial(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt;
            int pruefungAnzahl = 0;
            boolean eingabeMaterialKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;

            goldAnzeigen();
            Map<Material, Integer> materialInventar = partyController.getParty().getMaterialien();

            System.out.println("Welches Material moechten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Material, Integer> entry : materialInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue(), entry.getKey().getVerkaufswert());
            }
            System.out.println((counter + 1) + ". zurueck zur Verkaufsuebersicht");
            //EINGABE
            while (!eingabeMaterialKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= materialInventar.size() + 1) {
                    eingabeMaterialKorrekt = true;
                    if (auswahlObjekt == materialInventar.size() + 1) {
                        // Zurueck zur Verkaufsuebersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;
                    } else {
                        System.out.println("Wie viele moechten Sie verkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Material, Integer> entry : materialInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stueck. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
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
                                System.out.print(Farbauswahl.RED + "So viele besitzen Sie nicht, geben Sie einen gueltigen Wert ein!");
                                System.out.println(Farbauswahl.RESET);
                            }
                        }
                    }
                } else {
                    falscheEingabe();
                }
            }
        }
    }


    /**
     * oeffnet ein Untermenue zum zurueckkaufen von Gegenstaenden die in der akteullen Haendlersitzung verkauf wurden
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
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
                    falscheEingabe();
                }
            }
        }
    }


//     BEGIN HILFSMETHODEN


    /**
     * @author OF Kretschmer
     * @since 16.11.23
     * Zeigt das Menue des Haendlers an
     */
    private void haendlerMenueAnzeigen() {
        System.out.println("1. Kaufen");
        System.out.println("2. Verkaufen");
        System.out.println("3. Zurueckkaufen");
        System.out.println("4. Zurueck zum Menue");
    }

    /**
     * Zeigt das Untermenue zum verkaufen an
     *
     * @author OF Kretschmer
     * @since 16.11.23
     * Zeigt das Menue des Haendlers an
     */
    private void verkaufenMenueAnzeigen() {
        System.out.println("Was moechten Sie verkaufen?");
        System.out.println("1. Waffen");
        System.out.println("2. Ruestung");
        System.out.println("3. Accessoire");
        System.out.println("4. Verbrauchsgegenstaende");
        System.out.println("5. Materialien");
        System.out.println("6. Zurueck zur Haendleruebersicht");
    }

    /**
     * @author OF Kretschmer
     * @since 20.11.23
     * Zeigt das Untermenue  vom zurueckkaufen an.
     */
    private void zurueckkaufenAnzeigen() {
        System.out.println("Was moechten Sie zurueckkaufen?");
        System.out.println("1. Ausruestungsgegenstaende");
        System.out.println("2. Verbrauchsgegenstaende");
        System.out.println("3. Materialien");
        System.out.println("4. Zurueck zur Haendleruebersicht");
    }

    /**
     * Gibt den aktuellen Goldstand an.
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    private void goldAnzeigen() {
        System.out.printf(Farbauswahl.YELLOW + "Sie besitzen %d Gold. ", partyController.getPartyGold());
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Fehlermeldung das nicht genug Gold vorhanden ist
     *
     * @author OF Kretschmer
     * @since 21.11.23
     */
    private void nichtGenugGold() {
        System.out.print(Farbauswahl.RED + "Sie haben nicht genuegend Gold");
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Fehlermeldung das Eingabe fehlerhaft war
     *
     * @author OF Kretschmer
     * @since 21.11.23
     */
    private void falscheEingabe() {
        System.out.print(Farbauswahl.RED + "Die Eingabe war fehlerhaft, bitte geben Sie einen gueltigen Wert ein");
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Gibt die Informationen die fuer den Verkauf und Rueckkauf relevant sind aus
     *
     * @param waffe -
     *
     * @author OF Kretschmer
     * @since 16.11.23
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
     * @param ruestung Gibt die Informationen die fuer den Verkauf und Rueckkauf relevant sind aus
     * @param ruestung -
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
     * Gibt die Informationen die fuer den Verkauf und Rueckkauf relevant sind aus
     *
     * @param accessoire -
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
     * ermoeglicht das zurueckkaufen eines Ausruestungsgegenstandes
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    private void ausruestungsGegenstandZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int eingabe;
            boolean eingabeKorrekt = false;
            int listengroesse = haendler.getZurueckkaufenHistorie().size();

            goldAnzeigen();
            System.out.println("Was moechten Sie zurueckkaufen? ");
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
            System.out.printf("%d. zurueck zur uebersicht %n", listengroesse + 1);


            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe > 0 && eingabe <= listengroesse + 1) {
                    eingabeKorrekt = true;
                    if (eingabe != listengroesse + 1) {
                        Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(eingabe - 1);
                        if (tmp.getVerkaufswert() > partyController.getPartyGold()) {
                            nichtGenugGold();
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
                    falscheEingabe();
                }
            }
        }
    }


    /**
     * ermoeglicht das zurueckkaufen eines Verbrauchsgegenstandes
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    private void verbrauchsGegenstandZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int listengroesse = haendler.getZurueckkaufenVerbrauchsgegenstaende().size();
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
            int pruefungAnzahl = 0;
            boolean genugGold = false;
            boolean eingabeVerbrauchsgegenstandKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            int counter = 0;


            goldAnzeigen();
            System.out.println("Was moechten Sie zurueckkaufen? ");


            Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandHistorie = haendler.getZurueckkaufenVerbrauchsgegenstaende();
            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                keyName[counter] = entry.getKey().getName();
                counter++;
                System.out.printf("%d. %5d x  %s %d Gold%n", counter, entry.getValue(), entry.getKey().getName(), entry.getKey().getVerkaufswert());
            }
            System.out.printf("%d. zurueck zur uebersicht %n", listengroesse + 1);


            //EINGABE
            while (!eingabeVerbrauchsgegenstandKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= listengroesse + 1) {
                    if (auswahlObjekt == listengroesse + 1) {
                        // Zurueck zur Verkaufsuebersicht
                        KonsolenAssistent.clear();
                        eingabeVerbrauchsgegenstandKorrekt = true;
                        menuzurueck = true;
                    } else {
                        System.out.println("Wie viele moechten Sie zurueckkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Es sind %d Stueck vorhanden. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt >= 0) {
                                eingabeAnzahlKorrekt = true;
                            } else {
                                System.out.println("So viele stehen nicht zur verfuegung, geben Sie einen gueltigen Wert ein!");
                                System.out.print(Farbauswahl.RED + "So viele stehen nicht zur verfuegung, geben Sie einen gueltigen Wert ein!");
                                System.out.println(Farbauswahl.RESET);
                            }
                        }
                        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                if (partyController.getPartyGold() >= (entry.getKey().getVerkaufswert() * anzahlObjekt)) {
                                    genugGold = true;
                                }
                            }
                        }

                        if (genugGold) {
                            for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandHistorie.entrySet()) {

                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                    entry.setValue(entry.getValue() - anzahlObjekt);
                                    partyController.verbrauchsgegenstandHinzufuegen(entry.getKey(),anzahlObjekt);
                                    KonsolenAssistent.clear();
                                    menuzurueck = true;
                                }
                            }
                        } else {
                            nichtGenugGold();
                        }
                    }
                } else {
                    falscheEingabe();
                }
            }
        }

    }

    /**
     * ermoeglicht das zurueckkaufen eines Materials
     *
     * @param partyController -
     *
     * @author OF Kretschmer
     * @since 20.11.23
     * ermoeglicht das zurueckkaufen eines Materials
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
            boolean genugGold = false;
            int counter = 0;


            goldAnzeigen();
            Map<Material, Integer> materialHistorie = haendler.getZurueckkaufenMaterial();

            System.out.println("Was moechten Sie zurueckkaufen? ");

            for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                keyName[counter] = entry.getKey().getName();
                counter++;
                System.out.printf("%d. %5d x  %s %d Gold%n", counter, entry.getValue(), entry.getKey().getName(), entry.getKey().getVerkaufswert());
            }
            System.out.printf("%d. zurueck zur uebersicht %n", listengroesse + 1);


            //EINGABE
            while (!eingabeMaterialKorrekt) {
                auswahlObjekt = ScannerHelfer.nextInt();
                if (auswahlObjekt >= 1 && auswahlObjekt <= listengroesse + 1) {
                    eingabeMaterialKorrekt = true;
                    if (auswahlObjekt == listengroesse + 1) {
                        // Zurueck zur Verkaufsuebersicht
                        KonsolenAssistent.clear();
                        menuzurueck = true;

                    } else {
                        System.out.println("Wie viele moechten Sie zurueckkaufen? ");
                        while (!eingabeAnzahlKorrekt) {
                            for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue();
                                }
                            }
                            System.out.printf("Es sind %d Stueck vorhanden. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
                            } else {
                                System.out.print(Farbauswahl.RED + "So viele stehen nicht zur verfuegung, geben Sie einen gueltigen Wert ein!");
                                System.out.println(Farbauswahl.RESET);
                            }
                        }
                        for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                if (partyController.getPartyGold() >= (entry.getKey().getVerkaufswert() * anzahlObjekt)) {
                                    genugGold = true;
                                }
                            }
                        }
                        if (genugGold) {
                            for (Map.Entry<Material, Integer> entry : materialHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                    entry.setValue(entry.getValue() - anzahlObjekt);
                                    partyController.materialHinzufuegen(entry.getKey(),anzahlObjekt);
                                    KonsolenAssistent.clear();
                                    menuzurueck = true;
                                }
                            }
                        } else {
                            nichtGenugGold();
                        }
                    }
                } else {
                    falscheEingabe();
                }
            }
        }
    }
}

