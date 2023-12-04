package haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
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
import javafx.stage.Stage;
import party.PartyController;

public class KaufenView extends BorderPane {

    PartyController partyController;
    HaendlerController haendlerController;
    Haendler haendler;
    ObservableList<Waffe> waffenHaendler;

    public KaufenView(PartyController partyController,HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane kaufenPane = new TabPane();
        Tab kaufenWaffe = new Tab("Waffen");
        kaufenWaffe.setClosable(false);
        Tab kaufenRuestung = new Tab("Rüstung");
        kaufenRuestung.setClosable(false);
        Tab kaufenAccessoire = new Tab("Accessoire");
        kaufenAccessoire.setClosable(false);
        Tab kaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenstände.setClosable(false);
        Tab kaufenMaterial = new Tab("Material");
        kaufenMaterial.setClosable(false);


        // Kauf Tabelle mit Inhalt füllen
        waffenHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarWaffen()
        );
        ObservableList<Ruestung> ruestungsHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarRuestung()
        );
        ObservableList<Accessoire> accessoiresHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarAccessiore()
        );
        // ToDO Verbrauchsgegenstände und ins Tabs einbinden
        // ToDo Material und ins Tabs einbinden


        // Kaufen Tab 1 - 5 erstellen und befüllen
        TableView<Waffe> waffenKaufen = new TableView<>(waffenHaendler);
        HaendlerView.waffenKaufenTabelle(waffenKaufen);
        kaufenWaffe.setContent(waffenKaufen);
        waffenKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
            haendlerController.waffenKaufen(waffenKaufen.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Ruestung> ruestungKaufen = new TableView<>(ruestungsHaendler);
        HaendlerView.ruestungKaufenTabelle(ruestungKaufen);
        kaufenRuestung.setContent(ruestungKaufen);
        TableView<Accessoire> accessoireKaufen = new TableView<>(accessoiresHaendler);
        HaendlerView.accessoireKaufenTabelle(accessoireKaufen);
        kaufenAccessoire.setContent(accessoireKaufen);
        TableView<Verbrauchsgegenstand> verbrauchsgegenstandKaufen = new TableView<>();
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen);
        TableView<Material> materialKaufen = new TableView<>();
        HaendlerView.materialKaufenTabelle(materialKaufen);

        kaufenPane.getTabs().addAll(kaufenWaffe, kaufenRuestung, kaufenAccessoire, kaufenVerbrauchsgegenstände, kaufenMaterial);
        this.setCenter(kaufenPane);
    }

    public void kaufenWaffenAnzeigeAktualisieren(){
        waffenHaendler.clear();
        waffenHaendler.addAll(haendler.getKaufInventar().getInventarWaffen());
    }
}



