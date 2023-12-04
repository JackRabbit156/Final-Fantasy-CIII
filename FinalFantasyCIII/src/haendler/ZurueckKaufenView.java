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

public class ZurueckKaufenView extends BorderPane {

    PartyController partyController;
    HaendlerController haendlerController;
    Haendler haendler;
    ObservableList<Waffe> waffenHaendlerHistory;
    ObservableList<Ruestung> ruestungsHaendlerHistory;
    ObservableList<Accessoire> accessoiresHaendlerHistory;

    public ZurueckKaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane zurueckkaufenPane = new TabPane();
        Tab zurueckkaufenWaffe = new Tab("Waffen");
        zurueckkaufenWaffe.setClosable(false);
        Tab zurueckkaufenRuestung = new Tab("Rüstung");
        zurueckkaufenRuestung.setClosable(false);
        Tab zurueckkaufenAccessoire = new Tab("Accessoire");
        zurueckkaufenAccessoire.setClosable(false);
        Tab zurueckkaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        zurueckkaufenVerbrauchsgegenstände.setClosable(false);
        Tab zurueckkaufenMaterial = new Tab("Material");
        zurueckkaufenMaterial.setClosable(false);


        // Kauf Tabelle mit Inhalt füllen
        waffenHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieWaffe()
        );
        ruestungsHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieRuestung()
        );
        accessoiresHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieAccessoire()
        );
        // ToDO Verbrauchsgegenstände und ins Tabs einbinden
        // ToDo Material und ins Tabs einbinden


        // Kaufen Tab 1 - 5 erstellen und befüllen
        TableView<Waffe> waffenZurueckkaufen = new TableView<>(waffenHaendlerHistory);
        HaendlerView.waffenKaufenTabelle(waffenZurueckkaufen);
        zurueckkaufenWaffe.setContent(waffenZurueckkaufen);
        waffenZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenZurueckkaufen(waffenZurueckkaufen.getSelectionModel().getSelectedItem());
                zurueckkaufenWaffenAnzeigeAktualisieren();
            }
        });
        TableView<Ruestung> ruestungZurueckaufen = new TableView<>(ruestungsHaendlerHistory);
        HaendlerView.ruestungKaufenTabelle(ruestungZurueckaufen);
        zurueckkaufenRuestung.setContent(ruestungZurueckaufen);
        ruestungZurueckaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungZurueckkaufen(ruestungZurueckaufen.getSelectionModel().getSelectedItem());
                zurueckkaufenRuestungAnzeigeAktualisieren();

            }
        });
        TableView<Accessoire> accessoireZurueckkaufen = new TableView<>(accessoiresHaendlerHistory);
        HaendlerView.accessoireKaufenTabelle(accessoireZurueckkaufen);
        zurueckkaufenAccessoire.setContent(accessoireZurueckkaufen);
        accessoireZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireZurueckkaufen(accessoireZurueckkaufen.getSelectionModel().getSelectedItem());
                zurueckkaufenAccessoireAnzeigeAktualisieren();

            }
        });
        // Todo Verbrauchsgegenstände und Materialien fehlen noch
        TableView<Verbrauchsgegenstand> verbrauchsgegenstandKaufen = new TableView<>();
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen);
        TableView<Material> materialKaufen = new TableView<>();
        HaendlerView.materialKaufenTabelle(materialKaufen);

        zurueckkaufenPane.getTabs().addAll(zurueckkaufenWaffe, zurueckkaufenRuestung, zurueckkaufenAccessoire, zurueckkaufenVerbrauchsgegenstände, zurueckkaufenMaterial);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        this.setCenter(zurueckkaufenPane);
    }

    void zurueckkaufenWaffenAnzeigeAktualisieren() {
        waffenHaendlerHistory.clear();
        waffenHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieWaffe());
    }

    void zurueckkaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendlerHistory.clear();
        ruestungsHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieRuestung());
    }
    void zurueckkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendlerHistory.clear();
        accessoiresHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieAccessoire());
    }


}



