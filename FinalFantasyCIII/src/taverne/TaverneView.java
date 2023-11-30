package taverne;

import hauptmenu.HauptmenuController;
import hauptmenu.speicherstand.SpeicherstandController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import view.ViewController;

public class TaverneView extends VBox {

    public TaverneView(TaverneController taverneController) {

        this.setBackground(new Background(new BackgroundImage(new Image("background/taverne1.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        Label soeldnerName = new Label("PLATZHALTER SÖLDNERNAME"); // TODO soll nur angezeigt werden wenn Söldner vorhanden
        Label keineSoeldner = new Label("Platzhalter keine Söldner"); // TODO soll nur angezeigt werden wenn keine Söldner vorhanden
//        Label soeldnerName = new Label(if(taverneController.istKeinSoeldnerVorhanden){"Keine Söldner verfügbar!"} else {PLATZHALTERSOELDNERNAME};); // TODO if Abfrage ob Söldner vorhanden um die oberen beiden Labels zusammenzufassenb
        soeldnerName.setStyle("-fx-font: 60px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");
        keineSoeldner.setStyle("-fx-font: 60px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");

        HBox soeldnerHBox = new HBox();
        Image soeldnerBild = new Image("soeldner/soeldner1.png");
        ImageView soeldnerView = new ImageView(soeldnerBild);
        Button soeldnerAnzeige = new Button(); // Platzhalter! TODO soll nur angezeigt werden wenn Söldner vorhanden
        soeldnerAnzeige.setBackground(null);
        soeldnerAnzeige.setGraphic(soeldnerView);
        soeldnerHBox.setAlignment(Pos.CENTER);
        soeldnerHBox.getChildren().add(soeldnerAnzeige);

        HBox buttons = new HBox();

        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        HBox naechsterHBox = new HBox(4.0);
        naechsterHBox.setAlignment(Pos.CENTER);
        Button naechster = new Button("nächster");
        naechster.setBackground(null);
        naechster.setMaxHeight(30.0);
        naechsterHBox.getStyleClass().add("hauptmenubutton");
        naechsterHBox.setMaxHeight(30.0);
        naechsterHBox.getChildren().addAll(naechster, arrow); // TODO soll nur angezeigt werden wenn Söldner vorhanden
//        naechster.setOnAction(event -> );


        Polygon arrow2 = new Polygon();
        arrow2.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        arrow2.setScaleX(-1.0);
        Button vorheriger = new Button("vorheriger"); // TODO soll nur angezeigt werden wenn Söldner vorhanden
        vorheriger.setGraphic(arrow2);
        vorheriger.setPrefHeight(68);
        vorheriger.setBackground(null);
        vorheriger.getStyleClass().add("hauptmenubutton");
//        vorheriger.setOnAction(event -> );

        buttons.setSpacing(50.0);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(vorheriger,naechsterHBox);

        VBox soeldnerKlasseGeschichte = new VBox();
        Label soeldnerKlasse = new Label("PLATZHALTER SÖLDNERKLASSE");
        soeldnerKlasse.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ddb622");
//        soeldnerKlasse.setText();
        Label soeldnerGeschichte = new Label("PLATZHALTER SÖLDNERGESCHICHTE");
//        soeldnerGeschichte.setText();
        soeldnerGeschichte.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #b3744c");
        soeldnerKlasseGeschichte.setAlignment(Pos.CENTER);
        soeldnerKlasseGeschichte.getChildren().addAll(soeldnerKlasse,soeldnerGeschichte);


        this.getChildren().addAll(soeldnerName, soeldnerHBox, buttons, soeldnerKlasseGeschichte);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);


        // TODO Buttons für die Seitenleiste
        Button anheuern = new Button();
//        anheuern.setOnAction();
        Button feuern = new Button();
//        feuern.setOnAction();
        Button ausruhen = new Button();
//        ausruhen.setOnAction();
        Button zurueck = new Button();
//        zurueck.setOnAction();
    }
}
