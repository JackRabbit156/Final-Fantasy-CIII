package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.hilfsklassen.PlaceHolder;
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

public class KaufenView extends BorderPane {

    private final Haendler haendler;
    private final ObservableList<Waffe> waffenHaendler;
    private final ObservableList<Ruestung> ruestungsHaendler;
    private final ObservableList<Accessoire> accessoiresHaendler;
    private final ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendler;
    private final ObservableMap<Material, IntegerProperty> materialHaendler;

    /**
     * Der Konstuktor der KaufView
     *
     * @param haendlerController der aktuellen Sitzung
     * @param haendler           der aktuellen Sitzung
     * @param partyController    der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public KaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.haendler = haendler;

        TabPane kaufenPane = new TabPane();
        kaufenPane.getStyleClass().addAll("tabpaneschmiede");
        kaufenPane.setStyle("selected-tab-color: red");
        Tab kaufenWaffeTab = new Tab("Waffen");
        kaufenWaffeTab.setClosable(false);
        Tab kaufenRuestungTab = new Tab("Rüstung");
        kaufenRuestungTab.setClosable(false);
        Tab kaufenAccessoireTab = new Tab("Accessoire");
        kaufenAccessoireTab.setClosable(false);
        Tab kaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenstaendeTab.setClosable(false);
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
        verbrauchsgegenstandHaendler = FXCollections.observableMap(
                haendler.getKaufVerbrauchsInventar());
        materialHaendler = FXCollections.observableMap(
                haendler.getKaufMaterialInventar()
        );

        // Befüllt die einzelnen Tabs mit (Waffe/Rüstung/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenKaufenTableView = new TableView<>(waffenHaendler);
        waffenKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Waffen zu kaufen"));
        HaendlerView.waffenKaufenTabelle(waffenKaufenTableView);
        kaufenWaffeTab.setContent(waffenKaufenTableView);
        waffenKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.waffenKaufen(waffenKaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= waffenKaufenTableView.getSelectionModel().getSelectedItem().getKaufwert()) {
                    waffenHaendler.remove(waffenKaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableView<Ruestung> ruestungKaufenTableView = new TableView<>(ruestungsHaendler);
        ruestungKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Rüstungen zu kaufen"));
        HaendlerView.ruestungKaufenTabelle(ruestungKaufenTableView);
        kaufenRuestungTab.setContent(ruestungKaufenTableView);
        ruestungKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.ruestungKaufen(ruestungKaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= ruestungKaufenTableView.getSelectionModel().getSelectedItem().getKaufwert()) {
                    ruestungsHaendler.remove(ruestungKaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableView<Accessoire> accessoireKaufenTableView = new TableView<>(accessoiresHaendler);
        accessoireKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Accessoires zu kaufen"));
        HaendlerView.accessoireKaufenTabelle(accessoireKaufenTableView);
        kaufenAccessoireTab.setContent(accessoireKaufenTableView);
        accessoireKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.accessoireKaufen(accessoireKaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= accessoireKaufenTableView.getSelectionModel().getSelectedItem().getKaufwert()) {
                    accessoiresHaendler.remove(accessoireKaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandKaufenTableView = new TableView<>(FXCollections.observableArrayList(verbrauchsgegenstandHaendler.entrySet()));
        HaendlerView.verbrauchsgegenstaendeKaufenTabelle(verbrauchsgegenstandKaufenTableView);
        kaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandKaufenTableView);
        verbrauchsgegenstandKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.verbrauchsgegenstandkaufen(verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandKaufenTableView.refresh();
            }
        });
        TableView<Map.Entry<Material, IntegerProperty>> materialKaufenTableView = new TableView<>(FXCollections.observableArrayList(materialHaendler.entrySet()));
        kaufenMaterialTab.setContent(materialKaufenTableView);
        HaendlerView.materialKaufenTabelle(materialKaufenTableView);
        materialKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.materialienkaufen(materialKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialKaufenTableView.refresh();

            }
        });
        //Fügt die Komponenten der Ansicht hinzu
        kaufenPane.getTabs().addAll(kaufenWaffeTab, kaufenRuestungTab, kaufenAccessoireTab, kaufenVerbrauchsgegenstaendeTab, kaufenMaterialTab);
        VBox top = new VBox();
        VBox platzhalter = new VBox();
        platzhalter.setMinHeight(50);
        VBox kaufenText = new VBox();
        top.getChildren().addAll(platzhalter, kaufenText);
        Label label = new Label("Kaufen: Lieber Kunde! Bitte fassen Sie nur die Ware an die Sie kaufen möchten!");
        label.getStyleClass().add("haendler");
        label.setWrapText(true);
        kaufenText.setPadding(new Insets(0, 0, 20, 200));
        kaufenText.getChildren().add(label);
        kaufenText.setAlignment(Pos.BOTTOM_LEFT);
        this.setTop(top);
        kaufenPane.setMaxSize(1300, 450);
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(kaufenPane);
        setPadding(new Insets(0, 384, 0, 0));
    }

    /**
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void kaufenWaffenAnzeigeAktualisieren() {
        waffenHaendler.removeAll();
        waffenHaendler.clear();
        waffenHaendler.addAll(haendler.getKaufInventar().getInventarWaffen());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void kaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendler.removeAll();
        ruestungsHaendler.clear();
        ruestungsHaendler.addAll(haendler.getKaufInventar().getInventarRuestung());
    }

    /**
     * aktualisiert die Tabelle der Accessoires
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void kaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendler.removeAll();
        accessoiresHaendler.clear();
        accessoiresHaendler.addAll(haendler.getKaufInventar().getInventarAccessiore());
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Verbrauchsgegenstaenden
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */
    public void kaufenVerbrauchsgegenstandAktualisieren() {
        // TODO
    }

    /**
     * (ohne funktion)
     * aktualisiert die Tabelle der Materialien
     *
     * @author OF Kretschmer
     * @since 06.12.23
     */

    public void kaufenMaterialAktualisieren() {
        // TODO
    }


}


