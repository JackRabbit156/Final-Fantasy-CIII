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


public class KaufenView extends BorderPane {

    private final Haendler haendler;
    private final HaendlerController haendlerController;
    private final PartyController partyController;
    private ObservableList<Waffe> waffenHaendler;
    private ObservableList<Ruestung> ruestungsHaendler;
    private ObservableList<Accessoire> accessoiresHaendler;
    private ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendler;
    private ObservableMap<Material, IntegerProperty> materialHaendler;
    Label keinInhalt = new Label(" Mehr habe ich aktuell nicht, komm ein ander mal wieder! ");

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
        this.haendlerController = haendlerController;
        this.partyController = partyController;


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


        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenKaufenTableView = new TableView<>(waffenHaendler);
        waffenKaufenTableView.setPlaceholder(keinInhalt);
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
        ruestungKaufenTableView.setPlaceholder(keinInhalt);
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
        accessoireKaufenTableView.setPlaceholder(keinInhalt);
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

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandKaufenTableView = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandHaendler.entrySet()));

        HaendlerView.verbrauchsgegenstaendeKaufenTabelle(verbrauchsgegenstandKaufenTableView);
        kaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandKaufenTableView);
        verbrauchsgegenstandKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.verbrauchsgegenstandkaufen(verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandKaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialKaufenTableView = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialHaendler.entrySet()));
        kaufenMaterialTab.setContent(materialKaufenTableView);
        HaendlerView.materialKaufenTabelle(materialKaufenTableView);
        materialKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                haendlerController.materialienkaufen(materialKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialKaufenTableView.refresh();

            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        keinInhalt.getStyleClass().add("haendlerFehler");
        keinInhalt.setWrapText(true);
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
        kaufenPane.setMaxHeight(450);
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


