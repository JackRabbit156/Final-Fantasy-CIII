package inventar.view;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import haendler.Haendler;
import hilfsklassen.TableViewFueller;
import inventar.controller.OverlayPartyMenueInventar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public InventarView(PartyController partyController, ViewController viewController, ArrayList<SpielerCharakter> aktiveParty) {
        this.partyController = partyController;
        this.viewController = viewController;
        this.aktiveParty = aktiveParty;
        hintergrundBild = new Image("background/inventoryBG.png");
        hintergrundBildAnsicht = new ImageView(hintergrundBild);

        waffenSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
        accessoiresSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
        ruestungsSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());


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
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    public SpielerCharakter getAusgewaehlterChar() {
        return ausgewaehlterChar;
    }

    public void setAusgewaehlterChar(SpielerCharakter ausgewaehlterChar) {
        this.ausgewaehlterChar = ausgewaehlterChar;
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


        VBox top = new VBox();
        top.setMinHeight(40);
        this.setTop(top);
        //this.setCenter(//TODO);
    }

    public void verbrauchsGegenstaendeOeffnen() {
        getChildren().clear();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
        HBox geteilteAnsicht = new HBox();
        VBox charBox = new VBox();
        VBox gegenstandAuswahlBox = new VBox();
        gegenstandAuswahlBox.setPrefSize(768, 1080);

        gegenstandAuswahlBox.getChildren().add(new OverlayPartyMenueInventar(this, gegenstandAuswahlBox, partyController));


        charBox.getChildren().clear();
        for (SpielerCharakter spielerCharakter : partyController.getTeammitglieder()) {
            if (spielerCharakter != null) {
                charBox.getChildren().add(new OverlayPartyMenueInventar(spielerCharakter, this, charBox));
            }
        }


        charBox.setPadding(new Insets(200, 0, 0, 0));
        gegenstandAuswahlBox.setPadding(new Insets(200, 0, 0, 0));
        geteilteAnsicht.setAlignment(Pos.CENTER);
        geteilteAnsicht.getChildren().add(charBox);
        geteilteAnsicht.getChildren().add(gegenstandAuswahlBox);
        VBox top = new VBox();
        top.setMinHeight(40);
        this.setTop(top);
        this.setCenter(geteilteAnsicht);
    }

    public void entferneCssVonAllenButtons(VBox vbox) {
        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                for (Node childNode : hbox.getChildren()) {
                    if (childNode instanceof Button) {
                        Button button = (Button) childNode;
                        button.getStyleClass().clear();
                        button.getStyleClass().add("charboxButton");
                    }
                }
            } else if (node instanceof Button) {
                Button button = (Button) node;
                button.getStyleClass().clear();
                button.getStyleClass().add("charboxButton");
            }

        }
    }
}


