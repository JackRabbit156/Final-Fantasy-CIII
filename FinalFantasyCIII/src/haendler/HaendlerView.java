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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import party.PartyController;


public class HaendlerView extends BorderPane {

    PartyController partyController;
    Haendler haendler;

    public HaendlerView(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;

        VBox top = new VBox();
        top.setMinHeight(30);
       this.setTop(top);

        VBox center = new VBox();
        this.setBackground(new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(center);
    }






    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenKaufenTabelle(TableView tabelle) {
//        TableViewFueller.iconFuellen(tabelle);
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
//        TableViewFueller.iconFuellen(tabelle);
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
//        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableViewFueller.maxManaPunkteAccFuellen(tabelle);
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
//        TableViewFueller.iconFuellen(tabelle);
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
//        TableViewFueller.iconFuellen(tabelle);
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
        public static void waffenVerkaufenTabelle (TableView tabelle){
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
