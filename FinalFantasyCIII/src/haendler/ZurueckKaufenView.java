package haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import party.PartyController;

import java.util.Map;

public class ZurueckKaufenView extends BorderPane {

    PartyController partyController;
    HaendlerController haendlerController;
    Haendler haendler;
    ObservableList<Waffe> waffenHaendlerHistory;
    ObservableList<Ruestung> ruestungsHaendlerHistory;
    ObservableList<Accessoire> accessoiresHaendlerHistory;
    ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendlerHistory;
    ObservableMap<Material, IntegerProperty> materialHaendlerHistory;

    /**
     * Der Konstuktor der ZurueckKaufenView
     *
     * @param partyController    der aktuellen Sitzung
     * @param haendlerController der aktuellen Sitzung
     * @param haendler           der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
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


        /// Füllt den Inhalt der ZurueckKaufentabellen
        waffenHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieWaffe()
        );
        ruestungsHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieRuestung()
        );
        accessoiresHaendlerHistory = FXCollections.observableArrayList(
                haendler.getZurueckkaufenHistorieAccessoire()
        );
        verbrauchsgegenstandHaendlerHistory = FXCollections.observableMap(haendler.getZurueckkaufenVerbrauchsgegenstaende());
        materialHaendlerHistory = FXCollections.observableMap(
                haendler.getZurueckkaufenMaterial()

        );


        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenZurueckkaufen = new TableView<>(waffenHaendlerHistory);
        HaendlerView.waffenKaufenTabelle(waffenZurueckkaufen);
        zurueckkaufenWaffe.setContent(waffenZurueckkaufen);
        waffenZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenZurueckkaufen(waffenZurueckkaufen.getSelectionModel().getSelectedItem());
                waffenHaendlerHistory.remove(waffenZurueckkaufen.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Ruestung> ruestungZurueckaufen = new TableView<>(ruestungsHaendlerHistory);
        HaendlerView.ruestungVerkaufenTabelle(ruestungZurueckaufen);
        zurueckkaufenRuestung.setContent(ruestungZurueckaufen);
        ruestungZurueckaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungZurueckkaufen(ruestungZurueckaufen.getSelectionModel().getSelectedItem());
                ruestungsHaendlerHistory.remove(ruestungZurueckaufen.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Accessoire> accessoireZurueckkaufen = new TableView<>(accessoiresHaendlerHistory);
        HaendlerView.accessoireKaufenTabelle(accessoireZurueckkaufen);
        zurueckkaufenAccessoire.setContent(accessoireZurueckkaufen);
        accessoireZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireZurueckkaufen(accessoireZurueckkaufen.getSelectionModel().getSelectedItem());
                accessoiresHaendlerHistory.remove(accessoireZurueckkaufen.getSelectionModel().getSelectedItem());
            }
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandZurueckkaufen = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandHaendlerHistory.entrySet()));
        HaendlerView.verbrauchsgegenständeVerkaufenTabelle(verbrauchsgegenstandZurueckkaufen);
        zurueckkaufenVerbrauchsgegenstände.setContent(verbrauchsgegenstandZurueckkaufen);
        verbrauchsgegenstandZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandZurueckkaufen(verbrauchsgegenstandZurueckkaufen.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandZurueckkaufen.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialZurueckkaufen = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialHaendlerHistory.entrySet()));
        zurueckkaufenMaterial.setContent(materialZurueckkaufen);
        HaendlerView.materialVerkaufenTabelle(materialZurueckkaufen);
        materialZurueckkaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialZurueckkaufen(materialZurueckkaufen.getSelectionModel().getSelectedItem().getKey());
                materialZurueckkaufen.refresh();
            }
        });


        //Fügt die Komponenten der Ansicht hinzu
        zurueckkaufenPane.getTabs().addAll(zurueckkaufenWaffe, zurueckkaufenRuestung, zurueckkaufenAccessoire, zurueckkaufenVerbrauchsgegenstände, zurueckkaufenMaterial);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        zurueckkaufenPane.setMaxHeight(600);
        zurueckkaufenPane.setMaxWidth(1000);
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(zurueckkaufenPane);
    }

    /**
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void zurueckkaufenWaffenAnzeigeAktualisieren() {
        waffenHaendlerHistory.clear();
        waffenHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieWaffe());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void zurueckkaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendlerHistory.clear();
        ruestungsHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieRuestung());
    }

    /**
     * aktualisiert die Tabelle der Accessoires
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void zurueckkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendlerHistory.clear();
        accessoiresHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieAccessoire());
    }


}



