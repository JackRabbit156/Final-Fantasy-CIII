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
import java.util.Map;


public class KaufenView extends BorderPane {

    private final Haendler haendler;
    private final HaendlerController haendlerController;
    private ObservableList<Waffe> waffenHaendler;
    private ObservableList<Ruestung> ruestungsHaendler;
    private ObservableList<Accessoire> accessoiresHaendler;
    private ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandHaendler;
    private ObservableMap<Material, IntegerProperty> materialHaendler;

    /**
     * Der Konstuktor der KaufView
     *
     * @param haendlerController der aktuellen Sitzung
     * @param haendler           der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public KaufenView(HaendlerController haendlerController, Haendler haendler) {
        this.haendler = haendler;
        this.haendlerController = haendlerController;

        TabPane kaufenPane = new TabPane();
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

        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandKaufenTableView = new TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>>(FXCollections.observableArrayList(verbrauchsgegenstandHaendler.entrySet()));

        HaendlerView.verbrauchsgegenstaendeKaufenTabelle(verbrauchsgegenstandKaufenTableView);
        kaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandKaufenTableView);
        verbrauchsgegenstandKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandkaufen(verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                verbrauchsgegenstandKaufenTableView.refresh();
            }
        });

        TableView<Map.Entry<Material, IntegerProperty>> materialKaufenTableView = new TableView<Map.Entry<Material, IntegerProperty>>(FXCollections.observableArrayList(materialHaendler.entrySet()));
        kaufenMaterialTab.setContent(materialKaufenTableView);
        HaendlerView.materialKaufenTabelle(materialKaufenTableView);
        materialKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialienkaufen(materialKaufenTableView.getSelectionModel().getSelectedItem().getKey());
                materialKaufenTableView.refresh();

//
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        kaufenPane.getTabs().addAll(kaufenWaffeTab, kaufenRuestungTab, kaufenAccessoireTab, kaufenVerbrauchsgegenstaendeTab, kaufenMaterialTab);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        kaufenPane.setMaxHeight(600);
        kaufenPane.setMaxWidth(1200);
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
    void kaufenRuestungAnzeigeAktualisieren() {
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
    void kaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendler.removeAll();
        accessoiresHaendler.clear();
        accessoiresHaendler.addAll(haendler.getKaufInventar().getInventarAccessiore());
    }


}


