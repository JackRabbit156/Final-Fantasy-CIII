package haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Button;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;


/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class HaendlerController {

    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<Button> haendlerMenuButtons;
    private Haendler haendler;
    private HaendlerView haendlerView;
    private KaufenView kaufenView;
    private VerkaufenView verkaufenView;
    private ZurueckKaufenView zurueckKaufenView;

    public HaendlerController(PartyController partyController, ViewController viewController) {
        this.partyController = partyController;
        this.haendler = new Haendler();
        this.haendlerView = new HaendlerView(partyController, haendler);
        this.kaufenView = new KaufenView(partyController, this, haendler);
        this.verkaufenView = new VerkaufenView(partyController, this, haendler);
        this.zurueckKaufenView = new ZurueckKaufenView(partyController, this, haendler);
        Button buttonKaufen = new Button("Kaufen");
        buttonKaufen.setOnAction(event -> {
            this.kaufenView.kaufenWaffenAnzeigeAktualisieren();
            this.kaufenView.kaufenRuestungAnzeigeAktualisieren();
            this.kaufenView.kaufenAccessoireAnzeigeAktualisieren();
            viewController.anmelden(kaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonVerkaufen = new Button("Verkaufen");
        buttonVerkaufen.setOnAction(event -> {
            this.verkaufenView.verkaufenWaffenAnzeigeAktualisieren();
            this.verkaufenView.verkaufenRuestungAnzeigeAktualisieren();
            this.verkaufenView.verkaufenAccessoireAnzeigeAktualisieren();
            viewController.anmelden(verkaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonZurueckkaufen = new Button("Zurückkaufen");
        buttonZurueckkaufen.setOnAction(event -> {
            this.zurueckKaufenView.zurueckkaufenWaffenAnzeigeAktualisieren();
            this.zurueckKaufenView.zurueckkaufenRuestungAnzeigeAktualisieren();
            this.zurueckKaufenView.zurueckkaufenAccessoireAnzeigeAktualisieren();
            viewController.anmelden(zurueckKaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        // buttonZurueckkaufen.setOnAction((event -> viewController.anmelden(zurueckKaufenView,haendlerMenuButtons,AnsichtsTyp.MIT_OVERLAY)) );
        Button buttonGameHub = new Button("Zurück zum GameHUB");
        buttonGameHub.setOnAction(event -> viewController.aktuelleNachHinten());
        this.haendlerMenuButtons = new ArrayList<Button>(Arrays.asList(buttonKaufen, buttonVerkaufen, buttonZurueckkaufen, buttonGameHub));
        this.viewController = viewController;
    }

    public Haendler getHaendler() {
        return haendler;
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
     * @return Das Verbrauchsgegenstand-Objekt, das der ausgewählten Nummer entspricht,
     * oder null, wenn keine Übereinstimmung gefunden wurde.
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

    public void haendlerAnzeigen(PartyController partyController) {
        System.out.println("Händler anzeigen" + haendler.getKaufInventar().getInventarWaffen().size());
        sortimentErneuern();
        System.out.println("Sortiment erneuern" + haendler.getKaufInventar().getInventarWaffen().size());
        viewController.anmelden(this.haendlerView, this.haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);

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
        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.KLEINER_MANATRANK, 10);
        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.MITTLERER_MANATRANK, 10);
        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.GROSSER_MANATRANK, 10);

        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.KLEINER_HEILTRANK, 10);
        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.MITTLERER_HEILTRANK, 10);
        haendler.getKaufVerbrauchsInventar().put(Verbrauchsgegenstand.GROSSER_HEILTRANK, 10);

        haendler.getKaufMaterialInventar().put(Material.EISENERZ, 10);
        haendler.getKaufMaterialInventar().put(Material.SILBERERZ, 10);
        haendler.getKaufMaterialInventar().put(Material.GOLDERZ, 10);
        haendler.getKaufMaterialInventar().put(Material.MITHRIL, 10);


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
                        grundwert = partyController.getParty().getMaterialien().get(ausgewaehltergegenstand).get();
                    }
                    partyController.getParty().getMaterialien().get(ausgewaehltergegenstand).set(grundwert + 1);
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
                        grundwert = partyController.getParty().getVerbrauchsgegenstaende().get(ausgewaehltergegenstand).get();
                    }
                    partyController.getParty().getVerbrauchsgegenstaende().get(ausgewaehltergegenstand).set(grundwert + 1);
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

    // Kaufen

    /**
     * Fuehrt den Kaufvorgang fuer Waffen beim Haendler durch.
     * Zeigt die verfuegbaren Waffen im Inventar des Haendlers an und ermöglicht dem Spieler,
     * eine Waffe zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author OF Kretschmer
     * @see Waffe
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 04.12.2023
     */
    void waffenKaufen(Waffe waffe) {
        if (partyController.getPartyGold() >= waffe.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(waffe);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(waffe);
            partyController.getParty().setGold(partyController.getPartyGold() - waffe.getKaufwert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
        }

    }
    /**
     * Fuehrt den Kaufvorgang fuer Ruestungen beim Haendler durch.
     * Zeigt die verfuegbaren Ruestungen im Inventar des Haendlers an und ermöglicht dem Spieler,
     * eine Ruestung zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author OF Kretschmer
     * @see Ruestung
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 04.12.2023
     */
    void ruestungKaufen(Ruestung ruestung) {
        if (partyController.getPartyGold() >= ruestung.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(ruestung);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(ruestung);
            partyController.getParty().setGold(partyController.getPartyGold() - ruestung.getKaufwert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
        }
    }
    /**
     * Fuehrt den Kaufvorgang fuer Accessoires beim Haendler durch.
     * Zeigt die verfuegbaren Accessoires im Inventar des Haendlers an und ermöglicht dem Spieler,
     * ein Accessoire zu kaufen. Der Spieler wird nach seiner Auswahl gefragt, und der Kauf wird
     * durchgefuehrt, wenn genuegend Gold vorhanden ist.
     *
     * @throws IndexOutOfBoundsException Wenn die eingegebene Zahl außerhalb des gueltigen Bereichs liegt.
     * @author OF Kretschmer
     * @see Accessoire
     * @see PartyController
     * @see Haendler
     * @see ScannerHelfer#nextInt()
     * @since 04.12.2023
     */
    void accessoireKaufen(Accessoire accessoire) {

        if (partyController.getPartyGold() >= accessoire.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(accessoire);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(accessoire);
            partyController.getParty().setGold(partyController.getPartyGold() - accessoire.getKaufwert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
        }
    }

// Verkaufen
    void waffenVerkaufen(Waffe waffe) {
        haendler.getZurueckkaufenHistorieWaffe().add(waffe);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().remove(waffe);
        partyController.getParty().setGold(partyController.getPartyGold() + waffe.getVerkaufswert());
    }

    void ruestungVerkaufen(Ruestung ruestung) {
        haendler.getZurueckkaufenHistorieRuestung().add(ruestung);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().remove(ruestung);
        partyController.getParty().setGold(partyController.getPartyGold() + ruestung.getVerkaufswert());
    }

    void accessoireVerkaufen(Accessoire accessoire) {
        haendler.getZurueckkaufenHistorieAccessoire().add(accessoire);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().remove(accessoire);
        partyController.getParty().setGold(partyController.getPartyGold() + accessoire.getVerkaufswert());
    }


    // Zurueckkaufen

    void waffenZurueckkaufen(Waffe waffe) {
        if (partyController.getPartyGold() >= waffe.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(waffe);
            haendler.getZurueckkaufenHistorieWaffe().remove(waffe);
            partyController.getParty().setGold(partyController.getPartyGold() - waffe.getVerkaufswert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
        }
    }
    void ruestungZurueckkaufen(Ruestung ruestung) {
        if (partyController.getPartyGold() >= ruestung.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(ruestung);
            haendler.getZurueckkaufenHistorieRuestung().remove(ruestung);
            partyController.getParty().setGold(partyController.getPartyGold() - ruestung.getVerkaufswert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
        }
    }
    void accessoireZurueckkaufen(Accessoire accessoire) {
        if (partyController.getPartyGold() >= accessoire.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(accessoire);
            haendler.getZurueckkaufenHistorieAccessoire().remove(accessoire);
            partyController.getParty().setGold(partyController.getPartyGold() - accessoire.getVerkaufswert());
            kaufErfolgreich();
        } else {
            nichtGenugGold();
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


            Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandInventar = partyController.getParty().getVerbrauchsgegenstaende();

            System.out.println("Welchen Verbrauchsgegenstand moechten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue().get(), entry.getKey().getVerkaufswert());
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
                            for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue().get();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stueck. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt) {
                                eingabeAnzahlKorrekt = true;

                                for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandInventar.entrySet()) {
                                    if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                        partyController.goldHinzufuegen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                        entry.getValue().set(entry.getValue().get() - anzahlObjekt);

                                        haendler.getZurueckkaufenVerbrauchsgegenstaende().put(entry.getKey(), entry.getValue().get() + anzahlObjekt);
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


            Map<Material, IntegerProperty> materialInventar = partyController.getParty().getMaterialien();

            System.out.println("Welches Material moechten Sie verkaufen?");
            // Ausgabe MAP
            for (Map.Entry<Material, IntegerProperty> entry : materialInventar.entrySet()) {
                counter++;
                keyName[counter - 1] = entry.getKey().getName();
                System.out.printf("%d. %4s: %d Stk. %d Gold%n", counter, entry.getKey().getName(), entry.getValue().get(), entry.getKey().getVerkaufswert());
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
                            for (Map.Entry<Material, IntegerProperty> entry : materialInventar.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    pruefungAnzahl = entry.getValue().get();
                                }
                            }
                            System.out.printf("Sie besitzen %d Stueck. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
                                for (Map.Entry<Material, IntegerProperty> entry : materialInventar.entrySet()) {
                                    if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                        partyController.goldHinzufuegen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                        entry.getValue().set(entry.getValue().get() - anzahlObjekt);
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

                }
            }
        }
    }


//     BEGIN HILFSMETHODEN


    /**
     * Fehlermeldung das nicht genug Gold vorhanden ist
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void nichtGenugGold() {
        // Todo
        System.out.print(Farbauswahl.RED + "Sie haben nicht genuegend Gold");
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Meldung das Kauf erfolgreich
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufErfolgreich() {
        // ToDO
        System.out.print(Farbauswahl.GREEN + "Kauf war erfolgreich");
        System.out.println(Farbauswahl.RESET);
    }


    /**
     * ermoeglicht das zurueckkaufen eines Ausruestungsgegenstandes
     *
     * @param partyController -
     * @author OF Kretschmer
     * @since 20.11.23
     */
//    private void ausruestungsGegenstandZurueckkaufen(PartyController partyController) {
//        boolean menuzurueck = false;
//        while (!menuzurueck) {
//            int eingabe;
//            boolean eingabeKorrekt = false;
//            int listengroesse = haendler.getZurueckkaufenHistorie().size();
//
//
//            while (!eingabeKorrekt) {
//                eingabe = ScannerHelfer.nextInt();
//                if (eingabe > 0 && eingabe <= listengroesse + 1) {
//                    eingabeKorrekt = true;
//                    if (eingabe != listengroesse + 1) {
//                        Gegenstand tmp = haendler.getZurueckkaufenHistorie().get(eingabe - 1);
//                        if (tmp.getVerkaufswert() > partyController.getPartyGold()) {
//                            nichtGenugGold();
//                        } else {
//                            partyController.goldAbziehen(tmp.getVerkaufswert());
//                            if (tmp instanceof Waffe) {
//                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add((Waffe) tmp);
//                            } else if (tmp instanceof Ruestung) {
//                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add((Ruestung) tmp);
//                            } else if (tmp instanceof Accessoire) {
//                                partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add((Accessoire) tmp);
//                            }
//                            haendler.getZurueckkaufenHistorie().remove(eingabe - 1);
//                        }
//                    }
//                    menuzurueck = true;
//                } else {
//
//                }
//            }
//        }
//    }


    /**
     * ermoeglicht das zurueckkaufen eines Verbrauchsgegenstandes
     *
     * @param partyController -
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
                                    partyController.verbrauchsgegenstandHinzufuegen(entry.getKey(), anzahlObjekt);
                                    KonsolenAssistent.clear();
                                    menuzurueck = true;
                                }
                            }
                        } else {
                            nichtGenugGold();
                        }
                    }
                } else {

                }
            }
        }

    }

    /**
     * ermoeglicht das zurueckkaufen eines Materials
     *
     * @param partyController -
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
                                    partyController.materialHinzufuegen(entry.getKey(), anzahlObjekt);
                                    KonsolenAssistent.clear();
                                    menuzurueck = true;
                                }
                            }
                        } else {
                            nichtGenugGold();
                        }
                    }
                } else {

                }
            }
        }
    }
}

