package inventar.view;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.TableViewFueller;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import party.PartyController;
import view.ViewController;

import java.util.ArrayList;


public class InventarView extends BorderPane {
    private final Image hintergrundBild;
    private final ImageView hintergrundBildAnsicht;
    ObservableList<Waffe> waffenSpieler;
    ObservableList<Ruestung> ruestungsSpieler;
    ObservableList<Accessoire> accessoiresSpieler;
    ObservableMap<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandSpieler;
    ObservableMap<Material, IntegerProperty> materialSpieler;
    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public InventarView(PartyController partyController, ViewController viewController, ArrayList<SpielerCharakter> aktiveParty) {
        this.partyController = partyController;
        this.viewController = viewController;
        this.aktiveParty = aktiveParty;
        waffenSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
        ruestungsSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
        accessoiresSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
        verbrauchsgegenstandSpieler = FXCollections.observableMap(partyController.getParty().getVerbrauchsgegenstaende());
        materialSpieler = FXCollections.observableMap(partyController.getParty().getMaterialien());
        hintergrundBild = new Image("background/inventoryBG.png");
        hintergrundBildAnsicht = new ImageView(hintergrundBild);


        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
    }

    public static void waffenbefuellenTabelle(TableView tabelle) {
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

    public static void accessoirefuellenTabelle(TableView tabelle) {
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

    public static void ruestungfuellenTabelle(TableView tabelle) {
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

    public void inventarOeffnen() {
        getChildren().clear();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
        TabPane tpInventarListe = new TabPane();
        Tab tbWaffe = new Tab("Waffen");
        tbWaffe.setClosable(false);
        Tab tbRuestung = new Tab("Rüstung");
        tbRuestung.setClosable(false);
        Tab tbAccessoire = new Tab("Accessoire");
        tbAccessoire.setClosable(false);

        TableView<Waffe> waffenAnzeigen = new TableView<>(waffenSpieler);
        waffenbefuellenTabelle(waffenAnzeigen);
        tbWaffe.setContent(waffenAnzeigen);

        TableView<Ruestung> ruestungAnzeigen = new TableView<>(ruestungsSpieler);
        ruestungfuellenTabelle(ruestungAnzeigen);
        tbRuestung.setContent(ruestungAnzeigen);

        TableView<Accessoire> accessoiresAnzeigen = new TableView<>(accessoiresSpieler);
        accessoirefuellenTabelle(accessoiresAnzeigen);
        tbAccessoire.setContent(accessoiresAnzeigen);

        tpInventarListe.getTabs().addAll(tbWaffe, tbRuestung, tbAccessoire);
        VBox top = new VBox();
        top.setMinHeight(40);
        this.setTop(top);
        this.setCenter(tpInventarListe);
    }

    public void ausruestungAendern() {
        getChildren().clear();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
    }


}
