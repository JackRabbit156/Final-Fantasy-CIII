package haendler;



import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.TableViewFueller;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import party.PartyController;
import java.util.Map;


public class HaendlerView extends BorderPane {

    PartyController partyController;
    Haendler haendler;


    /**
     * Der Konstuktor der HändlerView
     *
     * @param partyController der aktuellen Sitzung
     * @param haendler        der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public HaendlerView(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;

        VBox top = new VBox();
        top.setMinHeight(30);
        this.setTop(top);

        VBox center = new VBox();
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(center);
    }


//Kaufen

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
     * @since 30.11.23
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
     * @since 30.11.23
     */
    public static void accessoireKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
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
     * @since 30.11.23
     */
    public static void verbrauchsgegenständeKaufenTabelle(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
//        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameVGFuellen(tabelle);
        TableViewFueller.beschreibungMapFuellen(tabelle);
        TableViewFueller.kaufpreisMapVGFuellen(tabelle);
        TableViewFueller.mengeVGFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void materialKaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameMaterialFuellen(tabelle);
        TableViewFueller.kaufpreisMapMaterialFuellen(tabelle);
        TableViewFueller.mengeMaterialFuellen(tabelle);
    }


//    Verkaufen von Gegenständen


    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Waffen
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


    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Rüstung
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void ruestungVerkaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.ruestungsTypFuellen(tabelle);
        TableViewFueller.verteidigungFuellen(tabelle);
        TableViewFueller.magischeVerteidigungFuellen(tabelle);
        TableViewFueller.resistenzFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Accessoire
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void accessoireVerkaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableViewFueller.maxManaPunkteAccFuellen(tabelle);
        TableViewFueller.beweglichkeitAccFuellen(tabelle);
        TableViewFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableViewFueller.manaRegenerationAccFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }


    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verbrauchsgegenständeVerkaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameVGFuellen(tabelle);
        TableViewFueller.beschreibungMapFuellen(tabelle);
        TableViewFueller.verkaufpreisMapVGFuellen(tabelle);
        TableViewFueller.mengeVGFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und  zurückkaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void materialVerkaufenTabelle(TableView tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameMaterialFuellen(tabelle);
        TableViewFueller.verkaufpreisMapMaterialFuellen(tabelle);
        TableViewFueller.mengeMaterialFuellen(tabelle);
    }


}
