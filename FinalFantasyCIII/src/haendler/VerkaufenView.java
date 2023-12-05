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

public class VerkaufenView extends BorderPane {

    private final PartyController partyController;
    private final HaendlerController haendlerController;
    private final Haendler haendler;
    ObservableList<Waffe> waffenSpieler;
    ObservableList<Ruestung> ruestungsSpieler;
    ObservableList<Accessoire> accessoiresSpieler;

    ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandSpieler;
    ObservableMap<Material, IntegerProperty> materialSpieler;

    /**
     * Der Konstuktor der VerkaufenView
     *
     * @param partyController    der aktuellen Sitzung
     * @param haendlerController der aktuellen Sitzung
     * @param haendler           der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public VerkaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane verkaufenPane = new TabPane();
        Tab verkaufenWaffeTab = new Tab("Waffen");
        verkaufenWaffeTab.setClosable(false);
        Tab verkaufenRuestungTab = new Tab("Rüstung");
        verkaufenRuestungTab.setClosable(false);
        Tab verkaufenAccessoireTab = new Tab("Accessoire");
        verkaufenAccessoireTab.setClosable(false);
        Tab verkaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        verkaufenVerbrauchsgegenstaendeTab.setClosable(false);
        Tab verkaufenMaterialTab = new Tab("Material");
        verkaufenMaterialTab.setClosable(false);


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
        verbrauchsgegenstandSpieler = FXCollections.observableMap(partyController.getParty().getVerbrauchsgegenstaende());
        materialSpieler = FXCollections.observableMap(
                partyController.getParty().getMaterialien()
        );


        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenVerkaufenTableView = new TableView<>(waffenSpieler);
        waffenVerkaufenTableView.setPlaceholder(new Label("Mehr Waffen hast du nicht zum verkaufen!"));
        HaendlerView.waffenVerkaufenTabelle(waffenVerkaufenTableView);
        verkaufenWaffeTab.setContent(waffenVerkaufenTableView);
        waffenVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenVerkaufen(waffenVerkaufenTableView.getSelectionModel().getSelectedItem());
                waffenSpieler.remove(waffenVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Ruestung> ruestungVerkaufenTableView = new TableView<>(ruestungsSpieler);
        waffenVerkaufenTableView.setPlaceholder(new Label("Mehr Rüstungen hast du nicht zum verkaufen!"));
        HaendlerView.ruestungVerkaufenTabelle(ruestungVerkaufenTableView);
        verkaufenRuestungTab.setContent(ruestungVerkaufenTableView);
        ruestungVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungVerkaufen(ruestungVerkaufenTableView.getSelectionModel().getSelectedItem());
                ruestungsSpieler.remove(ruestungVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Accessoire> accessoireVerkaufenTableView = new TableView<>(accessoiresSpieler);
        waffenVerkaufenTableView.setPlaceholder(new Label("Mehr Accessoires hast du nicht zum verkaufen!"));
        HaendlerView.accessoireVerkaufenTabelle(accessoireVerkaufenTableView);
        verkaufenAccessoireTab.setContent(accessoireVerkaufenTableView);
        accessoireVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireVerkaufen(accessoireVerkaufenTableView.getSelectionModel().getSelectedItem());
                accessoiresSpieler.remove(accessoireVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandVerkaufenTableView = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandSpieler.entrySet()));

        HaendlerView.verbrauchsgegenständeVerkaufenTabelle(verbrauchsgegenstandVerkaufenTableView);
        verkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandVerkaufenTableView);
        verbrauchsgegenstandVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandverkaufen(verbrauchsgegenstandVerkaufenTableView.getSelectionModel().getSelectedItem().getKey());
              verbrauchsgegenstandVerkaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialVerkaufenTableView = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialSpieler.entrySet()));
        verkaufenMaterialTab.setContent(materialVerkaufenTableView);
        HaendlerView.materialVerkaufenTabelle(materialVerkaufenTableView);
        materialVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialienVerkaufen(materialVerkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialVerkaufenTableView.refresh();
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        verkaufenPane.getTabs().addAll(verkaufenWaffeTab, verkaufenRuestungTab, verkaufenAccessoireTab, verkaufenVerbrauchsgegenstaendeTab, verkaufenMaterialTab);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        verkaufenPane.setMaxHeight(600);
        verkaufenPane.setMaxWidth(1200);
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(verkaufenPane);
    }

    /**
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenWaffenAnzeigeAktualisieren() {
        waffenSpieler.clear();
        waffenSpieler.removeAll();
        waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenRuestungAnzeigeAktualisieren() {
        ruestungsSpieler.clear();
        ruestungsSpieler.removeAll();
        ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
    }

    /**
     * aktualisiert die Tabelle der Accessoires
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void verkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresSpieler.clear();
        accessoiresSpieler.removeAll();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
    }





}


