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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import party.PartyController;


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
     *
     * @param partyController der aktuellen Sitzung
     * @param haendlerController der aktuellen Sitzung
     * @param haendler der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public KaufenView(PartyController partyController, HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendlerController = haendlerController;
        this.haendler = haendler;


        TabPane kaufenPane = new TabPane();
        Tab kaufenWaffe = new Tab("Waffen");
        kaufenWaffe.setClosable(false);
        Tab kaufenRuestung = new Tab("Rüstung");
        kaufenRuestung.setClosable(false);
        Tab tabKaufenAccessoire = new Tab("Accessoire");
        tabKaufenAccessoire.setClosable(false);
        Tab kaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenstände.setClosable(false);
        Tab kaufenMaterial = new Tab("Material");
        kaufenMaterial.setClosable(false);


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
        //ToDO Verbrauchsgegenstände und Materialien gehen nnoch nicht
        System.out.println("Größe VG"  + haendler.getKaufVerbrauchsInventar().size());
        verbrauchsgegenstandHaendler =  FXCollections.observableMap(
                haendler.getKaufVerbrauchsInventar()
        );
        materialHaendler = FXCollections.observableMap(
                haendler.getKaufMaterialInventar()
        );



        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenKaufen = new TableView<>(waffenHaendler);
        HaendlerView.waffenKaufenTabelle(waffenKaufen);
        kaufenWaffe.setContent(waffenKaufen);
        waffenKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.waffenKaufen(waffenKaufen.getSelectionModel().getSelectedItem());
                kaufenWaffenAnzeigeAktualisieren();
            }
        });
        TableView<Ruestung> ruestungKaufen = new TableView<>(ruestungsHaendler);
        HaendlerView.ruestungKaufenTabelle(ruestungKaufen);
        kaufenRuestung.setContent(ruestungKaufen);
        ruestungKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.ruestungKaufen(ruestungKaufen.getSelectionModel().getSelectedItem());
                kaufenRuestungAnzeigeAktualisieren();

            }
        });
        TableView<Accessoire> accessoireKaufen = new TableView<>(accessoiresHaendler);
        HaendlerView.accessoireKaufenTabelle(accessoireKaufen);
        tabKaufenAccessoire.setContent(accessoireKaufen);
        accessoireKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.accessoireKaufen(accessoireKaufen.getSelectionModel().getSelectedItem());
                kaufenAccessoireAnzeigeAktualisieren();

            }
        });
        // Todo Verbrauchsgegenstände und Materialien fehlen noch


        TableView<Verbrauchsgegenstand> verbrauchsgegenstandKaufen = new TableView<>();
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen, haendler);
        kaufenVerbrauchsgegenstände.setContent(verbrauchsgegenstandKaufen);
        verbrauchsgegenstandKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.verbrauchsgegenstandkaufen(verbrauchsgegenstandKaufen.getSelectionModel().getSelectedItem());
                kaufenVerbrauchsgegenstaendeAktualisieren();
            }
        });

        TableView<Material> materialKaufen = new TableView<>();
        kaufenMaterial.setContent(materialKaufen);
        HaendlerView.materialKaufenTabelle(materialKaufen);
        materialKaufen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                haendlerController.materialienkaufen(materialKaufen.getSelectionModel().getSelectedItem());
                kaufenMaterialAktualisieren();
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        kaufenPane.getTabs().addAll(kaufenWaffe, kaufenRuestung, tabKaufenAccessoire, kaufenVerbrauchsgegenstände, kaufenMaterial);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        this.setCenter(kaufenPane);
    }

    /**
     * aktualisiert die Tabelle der Waffen

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenWaffenAnzeigeAktualisieren() {
        waffenHaendler.clear();
        waffenHaendler.addAll(haendler.getKaufInventar().getInventarWaffen());
    }

    /**
     * aktualisiert die Tabelle der Rüstungen

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenRuestungAnzeigeAktualisieren() {
        ruestungsHaendler.clear();
        ruestungsHaendler.addAll(haendler.getKaufInventar().getInventarRuestung());
    }
    /**
     * aktualisiert die Tabelle der Accessoires

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenAccessoireAnzeigeAktualisieren() {
        accessoiresHaendler.clear();
        accessoiresHaendler.addAll(haendler.getKaufInventar().getInventarAccessiore());
    }
    /**
     * aktualisiert die Tabelle der Verbrauchsgegenständen
     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenVerbrauchsgegenstaendeAktualisieren() {
        verbrauchsgegenstandHaendler.clear();
        verbrauchsgegenstandHaendler.putAll(haendler.getKaufVerbrauchsInventar());

    }
    /**
     * aktualisiert die Tabelle der Materialien

     * @author OF Kretschmer
     * @since 04.12.23
     */
    void kaufenMaterialAktualisieren() {
        materialHaendler.clear();
        materialHaendler.putAll(haendler.getKaufMaterialInventar());
    }

}



