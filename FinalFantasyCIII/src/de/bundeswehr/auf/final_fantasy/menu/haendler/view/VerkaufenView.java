package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.menu.haendler.HaendlerController;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

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
    Label keinInhalt = new Label(" Mehr Gegenstände hast du davon nicht zum verkaufen! ");

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
        verkaufenPane.getStyleClass().addAll("tabpaneschmiede");
        verkaufenPane.setStyle("selected-tab-color: red");
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
        waffenVerkaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.waffenVerkaufenTabelle(waffenVerkaufenTableView);
        verkaufenWaffeTab.setContent(waffenVerkaufenTableView);
        waffenVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.waffenVerkaufen(waffenVerkaufenTableView.getSelectionModel().getSelectedItem());
                waffenSpieler.remove(waffenVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Ruestung> ruestungVerkaufenTableView = new TableView<>(ruestungsSpieler);
        ruestungVerkaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.ruestungVerkaufenTabelle(ruestungVerkaufenTableView);
        verkaufenRuestungTab.setContent(ruestungVerkaufenTableView);
        ruestungVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.ruestungVerkaufen(ruestungVerkaufenTableView.getSelectionModel().getSelectedItem());
                ruestungsSpieler.remove(ruestungVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });
        TableView<Accessoire> accessoireVerkaufenTableView = new TableView<>(accessoiresSpieler);
        accessoireVerkaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.accessoireVerkaufenTabelle(accessoireVerkaufenTableView);
        verkaufenAccessoireTab.setContent(accessoireVerkaufenTableView);
        accessoireVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.accessoireVerkaufen(accessoireVerkaufenTableView.getSelectionModel().getSelectedItem());
                accessoiresSpieler.remove(accessoireVerkaufenTableView.getSelectionModel().getSelectedItem());
            }
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandVerkaufenTableView = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandSpieler.entrySet()));
        HaendlerView.verbrauchsgegenständeVerkaufenTabelle(verbrauchsgegenstandVerkaufenTableView);
        verkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandVerkaufenTableView);
        verbrauchsgegenstandVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.verbrauchsgegenstandverkaufen(verbrauchsgegenstandVerkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandVerkaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialVerkaufenTableView = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialSpieler.entrySet()));
        verkaufenMaterialTab.setContent(materialVerkaufenTableView);
        HaendlerView.materialVerkaufenTabelle(materialVerkaufenTableView);
        materialVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.materialienVerkaufen(materialVerkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialVerkaufenTableView.refresh();
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        keinInhalt.getStyleClass().add("haendlerFehler");
        keinInhalt.setWrapText(true);
        verkaufenPane.getTabs().addAll(verkaufenWaffeTab, verkaufenRuestungTab, verkaufenAccessoireTab, verkaufenVerbrauchsgegenstaendeTab, verkaufenMaterialTab);
        VBox top = new VBox();
        VBox platzhalter = new VBox();
        platzhalter.setMinHeight(50);
        VBox verkaufenText = new VBox();
        top.getChildren().addAll(platzhalter, verkaufenText);
        Label label = new Label("Verkaufen: Wir kaufen Ihren Schrott!");
        label.getStyleClass().add("haendler");
        label.setWrapText(true);
        verkaufenText.setPadding(new Insets(0, 0, 20, 200));
        verkaufenText.getChildren().add(label);
        verkaufenText.setAlignment(Pos.BOTTOM_LEFT);
        this.setTop(top);
        verkaufenPane.setMaxHeight(450);
        verkaufenPane.setMaxWidth(1000);
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
    public void verkaufenWaffenAnzeigeAktualisieren() {
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
    public void verkaufenRuestungAnzeigeAktualisieren() {
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
    public void verkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresSpieler.clear();
        accessoiresSpieler.removeAll();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Verbrauchsgegenstaenden
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */
    public void verkaufenVerbrauchsgegenstandAktualisieren() {
        // TODO
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Materialien
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */
    public void verkaufenMaterialAktualisieren() {
        // TODO
    }

}


