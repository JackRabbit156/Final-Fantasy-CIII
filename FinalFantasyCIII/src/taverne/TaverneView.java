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

        int soeldnerIndex = 0;

        Label soeldnerName;
        if (!taverneController.istKeinSoeldnerVorhanden()) {
            soeldnerName = new Label(taverneController.getSoeldner()[soeldnerIndex].getName());
        } else {
            soeldnerName = new Label("Keine Söldner vorhanden!");
        }
        soeldnerName.setStyle("-fx-font: 60px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");


        HBox soeldnerHBox = new HBox();
        Image soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung());
        ImageView soeldnerView = new ImageView(soeldnerBild);
        Button soeldnerAnzeige; // Platzhalter! TODO soll nur angezeigt werden wenn Söldner vorhanden
        if (!taverneController.istKeinSoeldnerVorhanden()) {
            soeldnerAnzeige = new Button();
        } else {
            soeldnerAnzeige = new Button("Keine Söldner vorhanden!");
        }
        soeldnerAnzeige.setBackground(null);
        soeldnerAnzeige.setGraphic(soeldnerView);
        soeldnerHBox.setAlignment(Pos.CENTER);
        soeldnerHBox.getChildren().add(soeldnerAnzeige);

        HBox buttons = new HBox();
        Polygon naechsterPfeil = new Polygon();
        naechsterPfeil.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        HBox naechsterHBox = new HBox(4.0);
        naechsterHBox.setAlignment(Pos.CENTER);
        Button naechster = new Button("nächster");
        naechster.setBackground(null);
        naechster.setMaxHeight(30.0);
        naechsterHBox.getStyleClass().add("hauptmenubutton");
        naechsterHBox.setMaxHeight(30.0);
        naechsterHBox.getChildren().addAll(naechster, naechsterPfeil); // TODO soll nur angezeigt werden wenn Söldner vorhanden
//        naechster.setOnAction(event -> {
//            if (soeldnerIndex < taverneController.getSoeldner().length - 1) {
//                soeldnerIndex++;
//            }
//        });
        Polygon vorherigerPfeil = new Polygon();
        vorherigerPfeil.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        vorherigerPfeil.setScaleX(-1.0);
        Button vorheriger = new Button("vorheriger"); // TODO soll nur angezeigt werden wenn Söldner vorhanden
        vorheriger.setGraphic(vorherigerPfeil);
        vorheriger.setPrefHeight(68);
        vorheriger.setBackground(null);
        vorheriger.getStyleClass().add("hauptmenubutton");
//        vorheriger.setOnAction(event -> );

        buttons.setSpacing(50.0);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(vorheriger, naechsterHBox);

        VBox soeldnerKlasseGeschichte = new VBox();
        Label soeldnerKlasse = new Label(taverneController.getSoeldner()[soeldnerIndex].getKlasse().getBezeichnung());
        soeldnerKlasse.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ddb622");
        Label soeldnerGeschichte = new Label(taverneController.getSoeldner()[soeldnerIndex].getGeschichte());
        soeldnerGeschichte.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #b3744c");
        soeldnerKlasseGeschichte.setAlignment(Pos.CENTER);
        soeldnerKlasseGeschichte.getChildren().addAll(soeldnerKlasse, soeldnerGeschichte);


        this.getChildren().addAll(soeldnerName, soeldnerHBox, buttons, soeldnerKlasseGeschichte);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);


    }
}
