package haendler;


import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.TableViewFueller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import party.PartyController;



public class HaendlerView extends TabPane {

    PartyController partyController;
    Haendler haendler;

    public HaendlerView(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;

    }

    public void start(Stage stage) {



        TabPane kaufenPane = new TabPane();
        Tab kaufenWaffe = new Tab("Waffen");
        Tab kaufenRuestung = new Tab("Rüstung");
        Tab kaufenAccessoire = new Tab("Accessoire");
        Tab kaufenVerbrauchsgegenstände = new Tab("Verbrauchsgegenstände");
        Tab kaufenMaterial = new Tab("Material");
        kaufenPane.getTabs().addAll(kaufenWaffe, kaufenRuestung , kaufenAccessoire, kaufenVerbrauchsgegenstände , kaufenMaterial);



        // Kauf Tabelle mit Inhalt füllen
        ObservableList<Waffe> waffenHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarWaffen()
        );
        ObservableList<Ruestung> ruestungsHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarRuestung()
        );
        ObservableList<Accessoire> accessoiresHaendler = FXCollections.observableArrayList(
                haendler.getKaufInventar().getInventarAccessiore()
        );
        // ToDO Verbrauchsgegenstände
        // ToDo Material


        // Kaufen Tab 1 - 5 erstellen und befüllen
        TableView<Waffe> waffenKaufen = new TableView<>(waffenHaendler);
        waffenKaufenTabelle(waffenKaufen);
        TableView<Ruestung> ruestungKaufen = new TableView<>(ruestungsHaendler);
        ruestungKaufenTabelle(ruestungKaufen);
        TableView<Accessoire> accessoireKaufen = new TableView<>(accessoiresHaendler);
        accessoireKaufenTabelle(accessoireKaufen);
        TableView<Verbrauchsgegenstand> verbrauchsgegenstandKaufen = new TableView<>();
        verbrauchsgegenständeKaufenTabelle(verbrauchsgegenstandKaufen);
        TableView<Material> materialKaufen = new TableView<>();
        materialKaufenTabelle(materialKaufen);


    }


    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.waffenTypFuellen(tabelle);
        TableViewFueller.attakeFuellen(tabelle);
        TableViewFueller.magischeAttakeFuellen(tabelle);
        TableViewFueller.genauigkeitWaffeFuellen(tabelle);
        TableViewFueller.beweglichkeitWaffeFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Rüstung
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 31.11.23
     */
    public static void ruestungKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.ruestungsTypFuellen(tabelle);
        TableViewFueller.verteidigungFuellen(tabelle);
        TableViewFueller.magischeVerteidigungFuellen(tabelle);
        TableViewFueller.resistenzFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Accessoire
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 31.11.23
     */
    public static void accessoireKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.beweglichkeitAccFuellen(tabelle);
        TableViewFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableViewFueller.manaRegenerationAccFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 31.11.23
     */
    public static void verbrauchsgegenständeKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.beschreibungFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 31.11.23
     */
    public static void materialKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }


    /*
    Verkaufen von Gegenständen
     */

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenVerkaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.waffenTypFuellen(tabelle);
        TableViewFueller.attakeFuellen(tabelle);
        TableViewFueller.magischeAttakeFuellen(tabelle);
        TableViewFueller.genauigkeitWaffeFuellen(tabelle);
        TableViewFueller.beweglichkeitWaffeFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }


    /*
    Zurückkaufen
    Todo evtl von verkaufen übernehmen zum füllen der Tabelle
     */

}
