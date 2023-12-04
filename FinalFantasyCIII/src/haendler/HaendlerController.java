package haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Button;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class HaendlerController {

    private final PartyController partyController;
    private final ViewController viewController;
    private ArrayList<Button> haendlerMenuButtons;
    private final Haendler haendler;
    private final HaendlerView haendlerView;
    private final KaufenView kaufenView;
    private final VerkaufenView verkaufenView;
    private final ZurueckKaufenView zurueckKaufenView;


    /**
     * Der Konstuktor des HändlerControllers
     *
     * @param partyController der aktuellen Sitzung
     * @param viewController  der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
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
            viewController.aktuelleNachHinten();
            viewController.anmelden(kaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonVerkaufen = new Button("Verkaufen");
        buttonVerkaufen.setOnAction(event -> {
            this.verkaufenView.verkaufenWaffenAnzeigeAktualisieren();
            this.verkaufenView.verkaufenRuestungAnzeigeAktualisieren();
            this.verkaufenView.verkaufenAccessoireAnzeigeAktualisieren();
            viewController.aktuelleNachHinten();
            viewController.anmelden(verkaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonZurueckkaufen = new Button("Zurückkaufen");
        buttonZurueckkaufen.setOnAction(event -> {
            this.zurueckKaufenView.zurueckkaufenWaffenAnzeigeAktualisieren();
            this.zurueckKaufenView.zurueckkaufenRuestungAnzeigeAktualisieren();
            this.zurueckKaufenView.zurueckkaufenAccessoireAnzeigeAktualisieren();
            viewController.aktuelleNachHinten();
            viewController.anmelden(zurueckKaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonGameHub = new Button("Zurück zum GameHUB");
        buttonGameHub.setOnAction(event -> viewController.aktuelleNachHinten());
        this.haendlerMenuButtons = new ArrayList<>(Arrays.asList(buttonKaufen, buttonVerkaufen, buttonZurueckkaufen, buttonGameHub));
        this.viewController = viewController;
        kaufenView.toBack();
        verkaufenView.toBack();
        zurueckKaufenView.toBack();
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
    private static Verbrauchsgegenstand erkenneAusgewaehltesVerbrauchsItem(Map<Verbrauchsgegenstand, IntegerProperty> map, int selectedNumber) {
        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : map.entrySet()) {
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
    private static Material erkenneAusgewaehltesMaterialItem(Map<Material, IntegerProperty> map, int selectedNumber) {
        int nummer = 1;
        for (Map.Entry<Material, IntegerProperty> entry : map.entrySet()) {
            if (nummer == selectedNumber) {
                return entry.getKey();
            }
            nummer++;
        }
        return null;
    }

    /**
     * Ruf die Gui der HändlerAnzeige auf
     * Beim Aufruf des Händlers wird das Sortiment erneuert.
     *
     * @param partyController auf den sich die Sitzung des Händlers bezieht
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void haendlerAnzeigen(PartyController partyController) {
        sortimentErneuern();
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
     * @author OF Kretschmer (Auf GUI und IntegerProperty umgebaut)
     * @see AusruestungsgegenstandFabrik
     * @since 21.11.2023
     */
    private void sortimentErneuern() {
        haendler.getKaufInventar().getInventarAccessiore().clear();
        haendler.getKaufInventar().getInventarWaffen().clear();
        haendler.getKaufInventar().getInventarRuestung().clear();

        for (int i = 0; i < 10; i++) {
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleAccessoireFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleWaffeFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsgegenstandFabrik.erstelleRuestungFuer(haendler, (int) partyController.getPartyLevel()));
        }

        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.KLEINER_HEILTRANK).setValue(10);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.MITTLERER_HEILTRANK).setValue(10);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.GROSSER_HEILTRANK).setValue(10);

        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.KLEINER_MANATRANK).setValue(10);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.MITTLERER_MANATRANK).setValue(10);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstand.GROSSER_MANATRANK).setValue(10);

        haendler.getKaufMaterialInventar().get(Material.EISENERZ).setValue(10);
        haendler.getKaufMaterialInventar().get(Material.SILBERERZ).setValue(10);
        haendler.getKaufMaterialInventar().get(Material.GOLDERZ).setValue(10);
        haendler.getKaufMaterialInventar().get(Material.MITHRIL).setValue(10);


    }


    // Kaufen

    /**
     * Kauft die übergebene Waffe
     *
     * @param waffe die gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void waffenKaufen(Waffe waffe) {
        if (partyController.getPartyGold() >= waffe.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(waffe);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(waffe);
            partyController.getParty().setGold(partyController.getPartyGold() - waffe.getKaufwert());
            kaufErfolgreich(waffe);
        } else {
            nichtGenugGold(waffe);
        }

    }

    /**
     * Kauft die übergebene Rüstung
     *
     * @param ruestung die gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void ruestungKaufen(Ruestung ruestung) {
        if (partyController.getPartyGold() >= ruestung.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(ruestung);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(ruestung);
            partyController.getParty().setGold(partyController.getPartyGold() - ruestung.getKaufwert());
            kaufErfolgreich(ruestung);
        } else {
            nichtGenugGold(ruestung);
        }
    }

    /**
     * Kauft das übergebene Accessoire
     *
     * @param accessoire das gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void accessoireKaufen(Accessoire accessoire) {

        if (partyController.getPartyGold() >= accessoire.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(accessoire);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(accessoire);
            partyController.getParty().setGold(partyController.getPartyGold() - accessoire.getKaufwert());
            kaufErfolgreich(accessoire);
        } else {
            nichtGenugGold(accessoire);
        }
    }

    /**
     * Kauft den übergebenen Verbrachsgegentand
     *
     * @param verbrauchsgegenstand der gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verbrauchsgegenstandkaufen(Verbrauchsgegenstand verbrauchsgegenstand) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= verbrauchsgegenstand.getKaufwert()) {
            partyController.verbrauchsgegenstandHinzufuegen(verbrauchsgegenstand, anzahl);
            haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).setValue(haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * verbrauchsgegenstand.getKaufwert()));
            kaufErfolgreich(verbrauchsgegenstand);
        } else {
            nichtGenugGold(verbrauchsgegenstand);
        }
    }

    /**
     * Kauft das übergebene Material
     *
     * @param material das gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void materialienkaufen(Material material) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= material.getKaufwert()) {
            partyController.materialHinzufuegen(material, anzahl);
            haendler.getKaufMaterialInventar().get(material).setValue(haendler.getKaufMaterialInventar().get(material).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * material.getKaufwert()));
            kaufErfolgreich(material);
        } else {
            nichtGenugGold(material);
        }
    }


    // Verkaufen

    /**
     * verkauft die übergebene Waffe an den Händler
     *
     * @param waffe die verkauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void waffenVerkaufen(Waffe waffe) {
        haendler.getZurueckkaufenHistorieWaffe().add(waffe);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().remove(waffe);
        partyController.getParty().setGold(partyController.getPartyGold() + waffe.getVerkaufswert());
    }

    /**
     * verkauft die übergebene Rüstung an den Händler
     *
     * @param ruestung die verkauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void ruestungVerkaufen(Ruestung ruestung) {
        haendler.getZurueckkaufenHistorieRuestung().add(ruestung);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().remove(ruestung);
        partyController.getParty().setGold(partyController.getPartyGold() + ruestung.getVerkaufswert());
    }

    /**
     * verkauft das übergebene Accessoire an den Händler
     *
     * @param accessoire das verkauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void accessoireVerkaufen(Accessoire accessoire) {
        haendler.getZurueckkaufenHistorieAccessoire().add(accessoire);
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().remove(accessoire);
        partyController.getParty().setGold(partyController.getPartyGold() + accessoire.getVerkaufswert());
    }


    // Zurueckkaufen

    /**
     * Kauft die übergebene Waffe vom Händler zurück
     *
     * @param waffe die zurückgekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void waffenZurueckkaufen(Waffe waffe) {
        if (partyController.getPartyGold() >= waffe.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(waffe);
            haendler.getZurueckkaufenHistorieWaffe().remove(waffe);
            partyController.getParty().setGold(partyController.getPartyGold() - waffe.getVerkaufswert());
            kaufErfolgreich(waffe);
        } else {
            nichtGenugGold(waffe);
        }
    }

    /**
     * Kauft die übergebene Rüstung vom Händler zurück
     *
     * @param ruestung die zurückgekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void ruestungZurueckkaufen(Ruestung ruestung) {
        if (partyController.getPartyGold() >= ruestung.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(ruestung);
            haendler.getZurueckkaufenHistorieRuestung().remove(ruestung);
            partyController.getParty().setGold(partyController.getPartyGold() - ruestung.getVerkaufswert());
            kaufErfolgreich(ruestung);
        } else {
            nichtGenugGold(ruestung);
        }
    }

    /**
     * Kauft das übergebene Accessoire vom Händler zurück
     *
     * @param accessoire das zurückgekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void accessoireZurueckkaufen(Accessoire accessoire) {
        if (partyController.getPartyGold() >= accessoire.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(accessoire);
            haendler.getZurueckkaufenHistorieAccessoire().remove(accessoire);
            partyController.getParty().setGold(partyController.getPartyGold() - accessoire.getVerkaufswert());
            kaufErfolgreich(accessoire);
        } else {
            nichtGenugGold(accessoire);
        }
    }

    /**
     * Kauft das übergebene Verbrauchsgegenstant vom Händler zurück
     *
     * @param verbrauchsgegenstand das zurückgekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verbrauchsgegenstandZurueckkaufen(Verbrauchsgegenstand verbrauchsgegenstand) {
        if (partyController.getPartyGold() >= verbrauchsgegenstand.getKaufwert()) {

            kaufErfolgreich(verbrauchsgegenstand);
        } else {
            nichtGenugGold(verbrauchsgegenstand);
        }
    }

    /**
     * Kauft das übergebene Material vom Händler zurück
     *
     * @param material das zurückgekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void materialZurueckkaufen(Material material) {
        if (partyController.getPartyGold() >= material.getKaufwert()) {

            kaufErfolgreich(material);
        } else {
            nichtGenugGold(material);
        }
    }


    /**
     * Fehlermeldung das nicht genug Gold vorhanden ist
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void nichtGenugGold(Gegenstand gegenstand) {
        // Todo Ausgabe auf GUi
        System.out.print(Farbauswahl.RED + "Sie haben nicht genuegend Gold für " + gegenstand.getName());
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Meldung das Kauf erfolgreich
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufErfolgreich(Gegenstand gegenstand) {
        // ToDO ausgabe auf GUI
        System.out.print(Farbauswahl.GREEN + "Kauf von " + gegenstand.getName() + "war erfolgreich");
        System.out.println(Farbauswahl.RESET);
    }



//     Alter Code


    /**
     * ermoeglicht das zurueckkaufen eines Verbrauchsgegenstandes
     *
     * @param partyController -
     * @author OF Kretschmer
     * @since 20.11.23
     */
    /*
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


            Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHistorie = haendler.getZurueckkaufenVerbrauchsgegenstaende();
            for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandHistorie.entrySet()) {
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
                            for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
//                                    pruefungAnzahl = entry.getValue();
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
                        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandHistorie.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                if (partyController.getPartyGold() >= (entry.getKey().getVerkaufswert() * anzahlObjekt)) {
                                    genugGold = true;
                                }
                            }
                        }

                        if (genugGold) {
                            for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandHistorie.entrySet()) {

                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
//                                    entry.setValue(entry.getValue() - anzahlObjekt);
                                    partyController.verbrauchsgegenstandHinzufuegen(entry.getKey(), anzahlObjekt);
                                    KonsolenAssistent.clear();
                                    menuzurueck = true;
                                }
                            }
                        } else {
//                            nichtGenugGold();
                        }
                    }
                } else {

                }
            }
        }

    }
*/
    /**
     * ermoeglicht das zurueckkaufen eines Materials
     *
     * @param partyController -
     * @author OF Kretschmer
     * @since 20.11.23
     * ermoeglicht das zurueckkaufen eines Materials
     */
    /*
    private void materialZurueckkaufen(PartyController partyController) {
        boolean menuzurueck = false;
        while (!menuzurueck) {
            int listengroesse = haendler.getZurueckkaufenMaterial().size();
            String[] keyName = new String[6];
            int auswahlObjekt;
            int anzahlObjekt = 1;
//            IntegerProperty pruefungAnzahl = 0;
            boolean eingabeMaterialKorrekt = false;
            boolean eingabeAnzahlKorrekt = false;
            boolean genugGold = false;
            int counter = 0;


            Map<Material, IntegerProperty> materialHistorie = haendler.getZurueckkaufenMaterial();

            System.out.println("Was moechten Sie zurueckkaufen? ");

            for (Map.Entry<Material, IntegerProperty> entry : materialHistorie.entrySet()) {
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
                            for (Map.Entry<Material, IntegerProperty> entry : materialHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
//                                    pruefungAnzahl = entry.getValue();
                                }
                            }
//                            System.out.printf("Es sind %d Stueck vorhanden. ", pruefungAnzahl);
                            anzahlObjekt = ScannerHelfer.nextInt();
//                            if (pruefungAnzahl >= anzahlObjekt && anzahlObjekt > 0) {
                                eingabeAnzahlKorrekt = true;
                            } else {
                                System.out.print(Farbauswahl.RED + "So viele stehen nicht zur verfuegung, geben Sie einen gueltigen Wert ein!");
                                System.out.println(Farbauswahl.RESET);
                            }
                        }
                        for (Map.Entry<Material, IntegerProperty> entry : materialHistorie.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                if (partyController.getPartyGold() >= (entry.getKey().getVerkaufswert() * anzahlObjekt)) {
                                    genugGold = true;
                                }
                            }
                        }
                        if (genugGold) {
                            for (Map.Entry<Material, IntegerProperty> entry : materialHistorie.entrySet()) {
                                if (entry.getKey().getName().equalsIgnoreCase(keyName[auswahlObjekt - 1])) {
                                    partyController.goldAbziehen(entry.getKey().getVerkaufswert() * anzahlObjekt);
                                    entry.setValue(anzahlObjekt);
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
    */
}

