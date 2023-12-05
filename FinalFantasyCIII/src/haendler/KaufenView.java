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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import party.PartyController;

import java.util.Map;


public class KaufenView extends BorderPane {

    PartyController partyController;
    HaendlerController haendlerController;
    Haendler haendler;
    ObservableList<Waffe> waffenHaendler;
    ObservableList<Ruestung> ruestungsHaendler;
    ObservableList<Accessoire> accessoiresHaendler;
    ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendler;
    ObservableMap<Material, IntegerProperty> materialHaendler;

    /**
     * Der Konstuktor der KaufView
     *
     * @param partyController    der aktuellen Sitzung
     * @param haendlerController der aktuellen Sitzung
     * @param haendler           der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public KaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane kaufenPane = new TabPane();
        Tab kaufenWaffeTab = new Tab("Waffen");
        kaufenWaffeTab.setClosable(false);
        Tab kaufenRuestungTab = new Tab("Rüstung");
        kaufenRuestungTab.setClosable(false);
        Tab kaufenAccessoireTab = new Tab("Accessoire");
        kaufenAccessoireTab.setClosable(false);
        Tab kaufenVerbrauchsgegenständeTab = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenständeTab.setClosable(false);
        Tab kaufenMaterialTab = new Tab("Material");
        kaufenMaterialTab.setClosable(false);


        // Füllt den Inhalt der Kauftabellen
        waffenHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarWaffen()
        );
        ruestungsHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarRuestung()
        );
        accessoiresHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarAccessiore()
        );
        verbrauchsgegenstandHaendler = FXCollections.observableMap(haendler.getKaufVerbrauchsInventar());
        materialHaendler = FXCollections.observableMap(
                haendler.getKaufMaterialInventar()
        );


        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenKaufenTableView = new TableView<>(waffenHaendler);
        waffenKaufenTableView.setPlaceholder(new Label("Mehr habe ich aktuell nicht, komm ein ander mal wieder!"));
        HaendlerView.waffenKaufenTabelle(waffenKaufenTableView);
        kaufenWaffeTab.setContent(waffenKaufenTableView);
        waffenKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenKaufen(waffenKaufenTableView.getSelectionModel().getSelectedItem());
                waffenHaendler.remove(waffenKaufenTableView.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Ruestung> ruestungKaufenTableView = new TableView<>(ruestungsHaendler);
        ruestungKaufenTableView.setPlaceholder(new Label("Mehr habe ich aktuell nicht, komm ein ander mal wieder!"));
        HaendlerView.ruestungKaufenTabelle(ruestungKaufenTableView);
        kaufenRuestungTab.setContent(ruestungKaufenTableView);
        ruestungKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungKaufen(ruestungKaufenTableView.getSelectionModel().getSelectedItem());
                ruestungsHaendler.remove(ruestungKaufenTableView.getSelectionModel().getSelectedItem());

            }
        });
        TableView<Accessoire> accessoireKaufenTableView = new TableView<>(accessoiresHaendler);
        accessoireKaufenTableView.setPlaceholder(new Label("Mehr habe ich aktuell nicht, komm ein ander mal wieder!"));
        HaendlerView.accessoireKaufenTabelle(accessoireKaufenTableView);
        kaufenAccessoireTab.setContent(accessoireKaufenTableView);
        accessoireKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireKaufen(accessoireKaufenTableView.getSelectionModel().getSelectedItem());
                accessoiresHaendler.remove(accessoireKaufenTableView.getSelectionModel().getSelectedItem());

            }
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandKaufen = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandHaendler.entrySet()));

        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen);
        kaufenVerbrauchsgegenständeTab.setContent(verbrauchsgegenstandKaufen);
        verbrauchsgegenstandKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandkaufen(verbrauchsgegenstandKaufen.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandKaufen.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialKaufen = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialHaendler.entrySet()));
        kaufenMaterialTab.setContent(materialKaufen);
        HaendlerView.materialKaufenTabelle(materialKaufen);
        materialKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialienkaufen(materialKaufen.getSelectionModel().getSelectedItem().getKey());
                materialKaufen.refresh();

//
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        kaufenPane.getTabs().addAll(kaufenWaffeTab, kaufenRuestungTab, kaufenAccessoireTab, kaufenVerbrauchsgegenständeTab, kaufenMaterialTab);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        kaufenPane.setMaxHeight(600);
        kaufenPane.setMaxWidth(1000);
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(kaufenPane);
    }

    /**
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenWaffenAnzeigeAktualisieren() {
        waffenHaendler.clear();
        waffenHaendler.addAll(haendler.getKaufInventar().getInventarWaffen());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendler.clear();
        ruestungsHaendler.addAll(haendler.getKaufInventar().getInventarRuestung());
    }

    /**
     * aktualisiert die Tabelle der Accessoires
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendler.clear();
        accessoiresHaendler.addAll(haendler.getKaufInventar().getInventarAccessiore());
    }


}


