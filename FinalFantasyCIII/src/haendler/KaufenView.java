package haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import party.PartyController;

public class KaufenView extends BorderPane {

    PartyController partyController;
    Haendler haendler;

    public KaufenView(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;


        TabPane kaufenPane = new TabPane();
        Tab kaufenWaffe = new Tab("Waffen");
        kaufenWaffe.setClosable(false);
        Tab kaufenRuestung = new Tab("Rüstung");
        kaufenRuestung.setClosable(false);
        Tab kaufenAccessoire = new Tab("Accessoire");
        kaufenAccessoire.setClosable(false);
        Tab kaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenstände.setClosable(false);
        Tab kaufenMaterial = new Tab("Material");
        kaufenMaterial.setClosable(false);


        // Kauf Tabelle mit Inhalt füllen
        ObservableList<Waffe> waffenHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarWaffen()
        );
        System.out.println("Größe" + haendler.getKaufInventar().getInventarWaffen().size());
        ObservableList<Ruestung> ruestungsHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarRuestung()
        );
        ObservableList<Accessoire> accessoiresHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarAccessiore()
        );
        // ToDO Verbrauchsgegenstände und ins Tabs einbinden
        // ToDo Material und ins Tabs einbinden


        // Kaufen Tab 1 - 5 erstellen und befüllen
        TableView<Waffe> waffenKaufen = new TableView<>(waffenHaendler);
        HaendlerView.waffenKaufenTabelle(waffenKaufen);
        kaufenWaffe.setContent(waffenKaufen);
        TableView<Ruestung> ruestungKaufen = new TableView<>(ruestungsHaendler);
        HaendlerView.ruestungKaufenTabelle(ruestungKaufen);
        kaufenRuestung.setContent(ruestungKaufen);
        TableView<Accessoire> accessoireKaufen = new TableView<>(accessoiresHaendler);
        HaendlerView.accessoireKaufenTabelle(accessoireKaufen);
        kaufenAccessoire.setContent(accessoireKaufen);
        TableView<Verbrauchsgegenstand> verbrauchsgegenstandKaufen = new TableView<>();
        HaendlerView.verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen);
        TableView<Material> materialKaufen = new TableView<>();
        HaendlerView.materialKaufenTabelle(materialKaufen);

        kaufenPane.getTabs().addAll(kaufenWaffe, kaufenRuestung, kaufenAccessoire, kaufenVerbrauchsgegenstände, kaufenMaterial);
        this.setCenter(kaufenPane);
    }
}



