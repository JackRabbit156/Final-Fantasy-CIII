package haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import javafx.scene.control.Button;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;
import java.util.ArrayList;
import java.util.Arrays;



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
            kaufNichtmoeglich(waffe);
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
            kaufNichtmoeglich(ruestung);
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
            kaufNichtmoeglich(accessoire);
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
        if (partyController.getPartyGold() >= verbrauchsgegenstand.getKaufwert() && haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).getValue() >= anzahl) {
            partyController.verbrauchsgegenstandHinzufuegen(verbrauchsgegenstand, anzahl);
            haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).setValue(haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * verbrauchsgegenstand.getKaufwert()));
            kaufErfolgreich(verbrauchsgegenstand);
        } else {
            kaufNichtmoeglich(verbrauchsgegenstand);
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
        if (partyController.getPartyGold() >= material.getKaufwert() && haendler.getKaufMaterialInventar().get(material).getValue() >= anzahl) {
            partyController.materialHinzufuegen(material, anzahl);
            haendler.getKaufMaterialInventar().get(material).setValue(haendler.getKaufMaterialInventar().get(material).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * material.getKaufwert()));
            kaufErfolgreich(material);
        } else {
            kaufNichtmoeglich(material);
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

    /**
     * verkauft den übergebenen Verbrachsgegentand
     *
     * @param verbrauchsgegenstand der gekauft werden soll
     * @author OF Kretschmer
     * @since 05.12.23
     */
    void verbrauchsgegenstandverkaufen(Verbrauchsgegenstand verbrauchsgegenstand) {
        int anzahl = 1;
        if (partyController.getParty().getVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() >= anzahl) {
        partyController.verbrauchsgegenstandEntnehmen(verbrauchsgegenstand, anzahl);
        haendler.getZurueckkaufenVerbrauchsgegenstaende().get(verbrauchsgegenstand).setValue(haendler.getZurueckkaufenVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() + anzahl);
        partyController.getParty().setGold(partyController.getPartyGold() + (anzahl * verbrauchsgegenstand.getKaufwert()));
        verkaufErfolgreich(verbrauchsgegenstand);
    } else {
        nichtGenugGegenstand(verbrauchsgegenstand);
    }
    }

    /**
     * verkauft das übergebene Material
     *
     * @param material das gekauft werden soll
     * @author OF Kretschmer
     * @since 05.12.23
     */
    void materialienVerkaufen(Material material) {
        int anzahl = 1;
        if (partyController.getParty().getMaterialien().get(material).getValue() >= anzahl) {
        partyController.materialEntnehmen(material, anzahl);
        haendler.getZurueckkaufenMaterial().get(material).setValue(haendler.getZurueckkaufenMaterial().get(material).getValue() + anzahl);
        partyController.getParty().setGold(partyController.getPartyGold() + (anzahl * material.getKaufwert()));
        verkaufErfolgreich(material);
        } else {
            nichtGenugGegenstand(material);
        }
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
            kaufNichtmoeglich(waffe);
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
            kaufNichtmoeglich(ruestung);
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
            kaufNichtmoeglich(accessoire);
        }
    }

    /**
     Kauft das übergebene Verbrauchsgegenstant vom Händler zurück
     *
     * @param verbrauchsgegenstand der gekauft werden soll
     * @author OF Kretschmer
     * @since 05.12.23
     */
    void verbrauchsgegenstandZurueckkaufen(Verbrauchsgegenstand verbrauchsgegenstand) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= verbrauchsgegenstand.getVerkaufswert() && haendler.getZurueckkaufenVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() >= anzahl) {
            partyController.verbrauchsgegenstandHinzufuegen(verbrauchsgegenstand, anzahl);
            haendler.getZurueckkaufenVerbrauchsgegenstaende().get(verbrauchsgegenstand).setValue(haendler.getZurueckkaufenVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * verbrauchsgegenstand.getVerkaufswert()));
            kaufErfolgreich(verbrauchsgegenstand);
        } else {
            kaufNichtmoeglich(verbrauchsgegenstand);
        }
    }

    /**
     * Kauft das übergebene Material vom Händler zurück
     *
     * @param material das gekauft werden soll
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void materialZurueckkaufen(Material material) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= material.getVerkaufswert() && haendler.getZurueckkaufenMaterial().get(material).getValue() >= anzahl) {
            partyController.materialHinzufuegen(material, anzahl);
            haendler.getZurueckkaufenMaterial().get(material).setValue(haendler.getZurueckkaufenMaterial().get(material).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * material.getKaufwert()));
            kaufErfolgreich(material);
        } else {
            kaufNichtmoeglich(material);
        }
    }






    /**
     * Fehlermeldung das nicht genug Gold vorhanden ist
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufNichtmoeglich(Gegenstand gegenstand) {
        // Todo Ausgabe auf GUi
        System.out.print(Farbauswahl.RED + "Sie haben nicht genuegend Gold für " + gegenstand.getName()+  " oder es stehen nicht genug Artikel zur verfügung");
        System.out.println(Farbauswahl.RESET);
    }
    /**
     * Fehlermeldung das nicht genug Gold vorhanden ist
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void nichtGenugGegenstand(Gegenstand gegenstand) {
        // Todo Ausgabe auf GUi
        System.out.print(Farbauswahl.RED + "Sie haben nicht genuegend "  + gegenstand.getName() + "zum verkaufen ");
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
        System.out.print(Farbauswahl.GREEN + "Kauf von " + gegenstand.getName() + " war erfolgreich");
        System.out.println(Farbauswahl.RESET);
    }

    /**
     * Meldung das Kauf erfolgreich
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufErfolgreich(Gegenstand gegenstand) {
        // ToDO ausgabe auf GUI
        System.out.print(Farbauswahl.GREEN + "Verkauf von " + gegenstand.getName() + " war erfolgreich");
        System.out.println(Farbauswahl.RESET);
    }



}

