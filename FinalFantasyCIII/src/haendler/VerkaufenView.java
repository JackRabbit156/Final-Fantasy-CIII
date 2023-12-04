package haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import party.PartyController;

public class VerkaufenView extends BorderPane {

    PartyController partyController;
    HaendlerController haendlerController;
    Haendler haendler;
    ObservableList<Waffe> waffenSpieler;
    ObservableList<Ruestung> ruestungsSpieler;
    ObservableList<Accessoire> accessoiresSpieler;

    /**
     * Der Konstuktor der VerkaufenView
     *
     *
     * @param partyController der aktuellen Sitzung
     * @param haendlerController der aktuellen Sitzung
     * @param haendler der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public VerkaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane verkaufenPane = new TabPane();
        Tab verkaufenWaffe = new Tab("Waffen");
        verkaufenWaffe.setClosable(false);
        Tab verkaufenRuestung = new Tab("Rüstung");
        verkaufenRuestung.setClosable(false);
        Tab verkaufenAccessoire = new Tab("Accessoire");
        verkaufenAccessoire.setClosable(false);
        Tab verkaufenVerbrauchsgegenstaende = new Tab("Verbrauchsgegenstände");
        verkaufenVerbrauchsgegenstaende.setClosable(false);
        Tab verkaufenMaterial = new Tab("Material");
        verkaufenMaterial.setClosable(false);


        // Füllt den Inhalt der verkauftabellen
        waffenSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen()

        );
        ruestungsSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung()
        );
        accessoiresSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore()
        );
        // ToDO Verbrauchsgegenstände und ins Tabs einbinden
        // ToDo Material und ins Tabs einbinden


        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenVerkaufen = new TableView<>(waffenSpieler);
        HaendlerView.waffenKaufenTabelle(waffenVerkaufen);
        verkaufenWaffe.setContent(waffenVerkaufen);
        waffenVerkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenVerkaufen(waffenVerkaufen.getSelectionModel().getSelectedItem());
                verkaufenWaffenAnzeigeAktualisieren();
            }
        });
        TableView<Ruestung> ruestungVerkaufen = new TableView<>(ruestungsSpieler);
        HaendlerView.ruestungVerkaufenTabelle(ruestungVerkaufen);
        verkaufenRuestung.setContent(ruestungVerkaufen);
        ruestungVerkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungVerkaufen(ruestungVerkaufen.getSelectionModel().getSelectedItem());
                verkaufenRuestungAnzeigeAktualisieren();

            }
        });
        TableView<Accessoire> accessoireVerkaufen = new TableView<>(accessoiresSpieler);
        HaendlerView.accessoireKaufenTabelle(accessoireVerkaufen);
        verkaufenAccessoire.setContent(accessoireVerkaufen);
        accessoireVerkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireVerkaufen(accessoireVerkaufen.getSelectionModel().getSelectedItem());
                verkaufenAccessoireAnzeigeAktualisieren();

            }
        });
        // Todo Verbrauchsgegenstände und Materialien fehlen noch
        TableView<Verbrauchsgegenstand> verbrauchsgegenstandVerkaufen = new TableView<>();
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandVerkaufen, haendler);
        TableView<Material> materialVerkaufen = new TableView<>();
        HaendlerView.materialKaufenTabelle(materialVerkaufen);


        //Fügt die Komponenten der Ansicht hinzu
        verkaufenPane.getTabs().addAll(verkaufenWaffe, verkaufenRuestung, verkaufenAccessoire, verkaufenVerbrauchsgegenstaende, verkaufenMaterial);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        this.setCenter(verkaufenPane);
    }

    /**
     * aktualisiert die Tabelle der Waffen

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenWaffenAnzeigeAktualisieren() {
        waffenSpieler.clear();
        waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
    }
    /**
     * aktualisiert die Tabelle der Rüstungen

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenRuestungAnzeigeAktualisieren() {
        ruestungsSpieler.clear();
        ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
    }
    /**
     * aktualisiert die Tabelle der Accessoires

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresSpieler.clear();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
    }


    /**(Ohne Funktion)
     * aktualisiert die Tabelle der Verbrauchsgegenständen
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenVerbrauchsgegenstaendeAktualisieren() {


    }
    /**(Ohne Funktion)
     * aktualisiert die Tabelle der Materialien

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenMaterialAktualisieren() {

    }


}


