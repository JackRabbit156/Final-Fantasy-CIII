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
        Tab verkaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        verkaufenVerbrauchsgegenstände.setClosable(false);
        Tab verkaufenMaterial = new Tab("Material");
        verkaufenMaterial.setClosable(false);


        // Kauf Tabelle mit Inhalt füllen
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


        // Kaufen Tab 1 - 5 erstellen und befüllen
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
        HaendlerView.ruestungKaufenTabelle(ruestungVerkaufen);
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
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandVerkaufen);
        TableView<Material> materialVerkaufen = new TableView<>();
        HaendlerView.materialKaufenTabelle(materialVerkaufen);

        verkaufenPane.getTabs().addAll(verkaufenWaffe, verkaufenRuestung, verkaufenAccessoire, verkaufenVerbrauchsgegenstände, verkaufenMaterial);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        this.setCenter(verkaufenPane);
    }

    void verkaufenWaffenAnzeigeAktualisieren() {
        waffenSpieler.clear();
        waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
    }

    void verkaufenRuestungAnzeigeAktualisieren() {
        ruestungsSpieler.clear();
        ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
    }
    void verkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresSpieler.clear();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
    }


}



