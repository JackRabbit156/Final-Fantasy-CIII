package de.bundeswehr.auf.final_fantasy.menu.haendler.controller;

import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Verbrauchsgegenstaende;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.menu.haendler.view.HaendlerView;
import de.bundeswehr.auf.final_fantasy.menu.haendler.view.KaufenView;
import de.bundeswehr.auf.final_fantasy.menu.haendler.view.VerkaufenView;
import de.bundeswehr.auf.final_fantasy.menu.haendler.view.ZurueckKaufenView;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */
public class HaendlerController {

    private final ObjectProperty<Object> aktiveView = new SimpleObjectProperty<>();
    private final Haendler haendler;
    private List<Button> haendlerMenuButtons;
    private final HaendlerView haendlerView;
    private final KaufenView kaufenView;
    private final PartyController partyController;
    private final VerkaufenView verkaufenView;
    private final ViewController viewController;
    private final ZurueckKaufenView zurueckKaufenView;

    /**
     * Der Konstruktor des HaendlerControllers
     *
     * @param partyController der aktuellen Sitzung
     * @param viewController  der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public HaendlerController(PartyController partyController, ViewController viewController) {
        this.partyController = partyController;
        this.viewController = viewController;
        this.haendler = new Haendler();
        this.haendlerView = new HaendlerView();
        this.kaufenView = new KaufenView(partyController, haendler);
        this.verkaufenView = new VerkaufenView(partyController, haendler);
        this.zurueckKaufenView = new ZurueckKaufenView(partyController, haendler);
        Button buttonKaufen = new Button("Kaufen");
        buttonKaufen.disableProperty().bind(aktiveView.isEqualTo(kaufenView));
        buttonKaufen.setOnAction(event -> {
            kaufenView.aktualisieren();
            aktiveView.set(kaufenView);
            viewController.aktuelleNachHinten();
            viewController.anmelden(kaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonVerkaufen = new Button("Verkaufen");
        buttonVerkaufen.disableProperty().bind(aktiveView.isEqualTo(verkaufenView));
        buttonVerkaufen.setOnAction(event -> {
            verkaufenView.aktualisieren();
            aktiveView.set(verkaufenView);
            viewController.aktuelleNachHinten();
            viewController.anmelden(verkaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonZurueckkaufen = new Button("Zurückkaufen");
        buttonZurueckkaufen.disableProperty().bind(aktiveView.isEqualTo(zurueckKaufenView));
        buttonZurueckkaufen.setOnAction(event -> {
            zurueckKaufenView.aktualisieren();
            aktiveView.set(zurueckKaufenView);
            viewController.aktuelleNachHinten();
            viewController.anmelden(zurueckKaufenView, haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        });
        Button buttonGameHub = new Button("Zurück");
        buttonGameHub.setOnAction(event -> viewController.aktuelleNachHinten());
        haendlerMenuButtons = new ArrayList<>(Arrays.asList(buttonKaufen, buttonVerkaufen, buttonZurueckkaufen, buttonGameHub));
        kaufenView.toBack();
        verkaufenView.toBack();
        zurueckKaufenView.toBack();
    }

    /**
     * Ruf die Gui der HaendlerAnzeige auf
     * Beim Aufruf des Händlers wird das Sortiment erneuert.
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void haendlerAnzeigen() {
        aktiveView.set(null);
        sortimentErneuern();
        viewController.anmelden(this.haendlerView, this.haendlerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Diese Methode erneuert das Sortiment des Händlers, indem es die vorhandenen Kaufgegenstände
     * löscht und neue Gegenstände hinzufuegt. Es werden Accessoires, Waffen und Rüstungen für den
     * Händler erstellt und dem Kaufinventar hinzugefügt. Zusätzlich werden Verbrauchsgegenstände
     * wie Heil- und Manatränke dem Verbrauchsgegenstands-Inventar des Händlers hinzugefügt.
     * <p>
     * Die Methode wird normalerweise aufgerufen, wenn der Spieler das Kaufmenü betritt oder wenn
     * sich das Sortiment des Händlers ändern soll, z.B. nach einem bestimmten Zeitraum im Spiel.
     *
     * @author HF Rode
     * @author OF Kretschmer (Auf GUI und IntegerProperty umgebaut)
     * @see AusruestungsGegenstandFactory
     * @since 04.12.2023
     */
    private void sortimentErneuern() {
        haendler.getKaufInventar().getInventarAccessoire().clear();
        haendler.getKaufInventar().getInventarWaffen().clear();
        haendler.getKaufInventar().getInventarRuestung().clear();

        for (int i = 0; i < 10; i++) {
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsGegenstandFactory.erstelleAccessoireFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsGegenstandFactory.erstelleWaffeFuer(haendler, (int) partyController.getPartyLevel()));
            haendler.getKaufInventar().ausruestungsgegenstandHinzufuegen(AusruestungsGegenstandFactory.erstelleRuestungFuer(haendler, (int) partyController.getPartyLevel()));
        }
        int anzahl = 999;
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.KLEINER_HEILTRANK).setValue(anzahl);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.MITTLERER_HEILTRANK).setValue(anzahl);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.GROSSER_HEILTRANK).setValue(anzahl);

        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.KLEINER_MANATRANK).setValue(anzahl);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.MITTLERER_MANATRANK).setValue(anzahl);
        haendler.getKaufVerbrauchsInventar().get(Verbrauchsgegenstaende.GROSSER_MANATRANK).setValue(anzahl);

        haendler.getKaufMaterialInventar().get(Materialien.EISENERZ).setValue(anzahl);
        haendler.getKaufMaterialInventar().get(Materialien.SILBERERZ).setValue(anzahl);
        haendler.getKaufMaterialInventar().get(Materialien.GOLDERZ).setValue(anzahl);
        haendler.getKaufMaterialInventar().get(Materialien.MITHRIL).setValue(anzahl);
    }

}

