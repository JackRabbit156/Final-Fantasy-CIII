package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
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

public class ZurueckKaufenView extends BorderPane {

    private final Haendler haendler;
    ObservableList<Waffe> waffenHaendlerHistory;
    ObservableList<Ruestung> ruestungsHaendlerHistory;
    ObservableList<Accessoire> accessoiresHaendlerHistory;
    ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendlerHistory;
    ObservableMap<Material, IntegerProperty> materialHaendlerHistory;
    Label keinInhalt = new Label("Mehr hast du nicht verkauft!");


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
        this.haendler = haendler;

        TabPane zurueckkaufenPane = new TabPane();
        zurueckkaufenPane.getStyleClass().addAll("tabpaneschmiede");
        zurueckkaufenPane.setStyle("selected-tab-color: red");
        Tab zurueckkaufenWaffeTab = new Tab("Waffen");
        zurueckkaufenWaffeTab.setClosable(false);
        Tab zurueckkaufenRuestungTab = new Tab("Rüstung");
        zurueckkaufenRuestungTab.setClosable(false);
        Tab zurueckkaufenAccessoireTab = new Tab("Accessoire");
        zurueckkaufenAccessoireTab.setClosable(false);
        Tab zurueckkaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        zurueckkaufenVerbrauchsgegenstaendeTab.setClosable(false);
        Tab zurueckkaufenMaterialTab = new Tab("Material");
        zurueckkaufenMaterialTab.setClosable(false);

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
        TableView<Waffe> waffenZurueckkaufenTableView = new TableView<>(waffenHaendlerHistory);
        waffenZurueckkaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.waffenKaufenTabelle(waffenZurueckkaufenTableView);
        zurueckkaufenWaffeTab.setContent(waffenZurueckkaufenTableView);
        waffenZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.waffenZurueckkaufen(waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                    waffenHaendlerHistory.remove(waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });

        TableView<Ruestung> ruestungZurueckaufenTableView = new TableView<>(ruestungsHaendlerHistory);
        ruestungZurueckaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.ruestungVerkaufenTabelle(ruestungZurueckaufenTableView);
        zurueckkaufenRuestungTab.setContent(ruestungZurueckaufenTableView);
        ruestungZurueckaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.ruestungZurueckkaufen(ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                    ruestungsHaendlerHistory.remove(ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableView<Accessoire> accessoireZurueckkaufenTableView = new TableView<>(accessoiresHaendlerHistory);
        accessoireZurueckkaufenTableView.setPlaceholder(keinInhalt);
        HaendlerView.accessoireVerkaufenTabelle(accessoireZurueckkaufenTableView);
        zurueckkaufenAccessoireTab.setContent(accessoireZurueckkaufenTableView);
        accessoireZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.accessoireZurueckkaufen(accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                    accessoiresHaendlerHistory.remove(accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandZurueckkaufenTableView = new TableView<>(FXCollections.observableArrayList(verbrauchsgegenstandHaendlerHistory.entrySet()));
        HaendlerView.verbrauchsgegenstaendeVerkaufenTabelle(verbrauchsgegenstandZurueckkaufenTableView);
        zurueckkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandZurueckkaufenTableView);
        verbrauchsgegenstandZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.verbrauchsgegenstandZurueckkaufen(verbrauchsgegenstandZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandZurueckkaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialZurueckkaufenTableView = new TableView<>(FXCollections.observableArrayList(materialHaendlerHistory.entrySet()));
        zurueckkaufenMaterialTab.setContent(materialZurueckkaufenTableView);
        HaendlerView.materialVerkaufenTabelle(materialZurueckkaufenTableView);
        materialZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.materialZurueckkaufen(materialZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialZurueckkaufenTableView.refresh();
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        keinInhalt.getStyleClass().add("haendlerFehler");
        keinInhalt.setWrapText(true);
        zurueckkaufenPane.getTabs().addAll(zurueckkaufenWaffeTab, zurueckkaufenRuestungTab, zurueckkaufenAccessoireTab, zurueckkaufenVerbrauchsgegenstaendeTab, zurueckkaufenMaterialTab);
        VBox top = new VBox();
        VBox platzhalter = new VBox();
        platzhalter.setMinHeight(50);
        VBox kaufenText = new VBox();
        top.getChildren().addAll(platzhalter, kaufenText);
        Label label = new Label("Zurückkaufen: Hier bekommen Sie Ihren Schrott wieder!");
        label.getStyleClass().add("haendler");
        label.setWrapText(true);
        kaufenText.setPadding(new Insets(0, 0, 20, 200));
        kaufenText.getChildren().add(label);
        kaufenText.setAlignment(Pos.BOTTOM_LEFT);
        this.setTop(top);
        zurueckkaufenPane.setMaxSize(1300, 450);
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(zurueckkaufenPane);
        setPadding(new Insets(0, 384, 0, 0));
    }

    /**
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void zurueckkaufenWaffenAnzeigeAktualisieren() {
        waffenHaendlerHistory.clear();
        waffenHaendlerHistory.removeAll();
        waffenHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieWaffe());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void zurueckkaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendlerHistory.clear();
        waffenHaendlerHistory.removeAll();
        ruestungsHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieRuestung());
    }

    /**
     * aktualisiert die Tabelle der Accessoires
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void zurueckkaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendlerHistory.clear();
        accessoiresHaendlerHistory.removeAll();
        accessoiresHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieAccessoire());
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Verbrauchsgegenstaenden
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */
    public void zurueckkaufenVerbrauchsgegenstandAktualisieren() {
        // TODO
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Materialien
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */
    public void zurueckkaufenMaterialAktualisieren() {
        // TODO
    }

}



