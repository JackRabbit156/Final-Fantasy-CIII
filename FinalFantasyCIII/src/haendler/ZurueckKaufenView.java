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

public class ZurueckKaufenView extends BorderPane {

    private final PartyController partyController;
    private final HaendlerController haendlerController;
    private final Haendler haendler;
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
        waffenZurueckkaufenTableView.setPlaceholder(new Label("Mehr hast du nicht verkauft!"));
        HaendlerView.waffenKaufenTabelle(waffenZurueckkaufenTableView);
        zurueckkaufenWaffeTab.setContent(waffenZurueckkaufenTableView);
        waffenZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenZurueckkaufen(waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                    waffenHaendlerHistory.remove(waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
        TableView<Ruestung> ruestungZurueckaufenTableView = new TableView<>(ruestungsHaendlerHistory);
        waffenZurueckkaufenTableView.setPlaceholder(new Label("Mehr hast du nicht verkauft!"));
        HaendlerView.ruestungVerkaufenTabelle(ruestungZurueckaufenTableView);
        zurueckkaufenRuestungTab.setContent(ruestungZurueckaufenTableView);
        ruestungZurueckaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungZurueckkaufen(ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                ruestungsHaendlerHistory.remove(ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem());
            }}
        });
        TableView<Accessoire> accessoireZurueckkaufenTableView = new TableView<>(accessoiresHaendlerHistory);
        accessoireZurueckkaufenTableView.setPlaceholder(new Label("Mehr hast du nicht verkauft!"));
        HaendlerView.accessoireVerkaufenTabelle(accessoireZurueckkaufenTableView);
        zurueckkaufenAccessoireTab.setContent(accessoireZurueckkaufenTableView);
        accessoireZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireZurueckkaufen(accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem());
                if (partyController.getPartyGold() >= accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem().getVerkaufswert()) {
                accessoiresHaendlerHistory.remove(accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem());
            }}
        });

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandZurueckkaufenTableView = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandHaendlerHistory.entrySet()));
        HaendlerView.verbrauchsgegenständeVerkaufenTabelle(verbrauchsgegenstandZurueckkaufenTableView);
        zurueckkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandZurueckkaufenTableView);
        verbrauchsgegenstandZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandZurueckkaufen(verbrauchsgegenstandZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandZurueckkaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialZurueckkaufenTableView = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialHaendlerHistory.entrySet()));
        zurueckkaufenMaterialTab.setContent(materialZurueckkaufenTableView);
        HaendlerView.materialVerkaufenTabelle(materialZurueckkaufenTableView);
        materialZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialZurueckkaufen(materialZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialZurueckkaufenTableView.refresh();
            }
        });


        //Fügt die Komponenten der Ansicht hinzu
        zurueckkaufenPane.getTabs().addAll(zurueckkaufenWaffeTab, zurueckkaufenRuestungTab, zurueckkaufenAccessoireTab, zurueckkaufenVerbrauchsgegenstaendeTab, zurueckkaufenMaterialTab);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        zurueckkaufenPane.setMaxHeight(600);
        zurueckkaufenPane.setMaxWidth(1200);
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
        waffenHaendlerHistory.removeAll();
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
        waffenHaendlerHistory.removeAll();
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
        accessoiresHaendlerHistory.removeAll();
        accessoiresHaendlerHistory.addAll(haendler.getZurueckkaufenHistorieAccessoire());
    }


}



