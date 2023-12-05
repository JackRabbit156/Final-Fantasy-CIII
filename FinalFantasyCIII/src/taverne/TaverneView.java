package taverne;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;

public class TaverneView extends VBox {

    private Label soeldnerName;
    private ImageView soeldnerView;
    private Label soeldnerKlasse;
//    private Label soeldnerGeschichte;
    private TextArea soeldnerGeschichte;
    private int soeldnerIndex = 0;

    public TaverneView(TaverneController taverneController) {

        this.setBackground(new Background(new BackgroundImage(new Image("background/taverne.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        soeldnerName = new Label();
        soeldnerView = new ImageView();

        // das erste nicht-null Element finden
        for (int i = soeldnerIndex; i < taverneController.getSoeldner().length; i++) {
            if (taverneController.getSoeldner()[i] != null) {
                soeldnerIndex = i;
                break;
            }
        }

        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            soeldnerName.setText(taverneController.getSoeldner()[soeldnerIndex].getName());
            Image soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung());
            soeldnerView.setImage(soeldnerBild);
        } else {
            soeldnerName.setText("Keine Söldner zum anheuern vorhanden!");
            soeldnerView.setImage(null);
        }
        soeldnerName.setStyle("-fx-font: 30px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");


        HBox soeldnerHBox = new HBox();
        Image soeldnerBild = null;
        Button soeldnerAnzeige;
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerAnzeige = new Button();
        } else {
            soeldnerAnzeige = new Button("");
        }
        soeldnerView = new ImageView(soeldnerBild);
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
        naechster.setBorder(null);
        naechster.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        naechsterHBox.getStyleClass().add("hauptmenubutton");
        naechsterHBox.setMaxHeight(30.0);
        naechsterHBox.getChildren().addAll(naechster, naechsterPfeil);
        naechster.setOnAction(event -> {
            if (soeldnerIndex < taverneController.getSoeldner().length - 1) {
                soeldnerIndex++;
                if (taverneController.getSoeldner()[soeldnerIndex] == null) {
                    for (int i = soeldnerIndex; i < taverneController.getSoeldner().length; i++) {
                        if (taverneController.getSoeldner()[i] != null) {
                            soeldnerIndex = i;
                            break;
                        }
                    }
                }
                updateSoeldnerAnzeige(taverneController, soeldnerIndex);
            }
        });
        naechsterHBox.setOnMouseClicked(event -> {
            if (soeldnerIndex < taverneController.getSoeldner().length - 1) {
                soeldnerIndex++;
                if (taverneController.getSoeldner()[soeldnerIndex] == null) {
                    for (int i = soeldnerIndex; i < taverneController.getSoeldner().length; i++) {
                        if (taverneController.getSoeldner()[i] != null) {
                            soeldnerIndex = i;
                            break;
                        }
                    }
                }
                updateSoeldnerAnzeige(taverneController, soeldnerIndex);
            }
        });
        Polygon vorherigerPfeil = new Polygon();
        vorherigerPfeil.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        vorherigerPfeil.setScaleX(-1.0);
        Button vorheriger = new Button("vorheriger");
        vorheriger.setGraphic(vorherigerPfeil);
        vorheriger.setPrefHeight(68);
        vorheriger.setBackground(null);
        vorheriger.getStyleClass().add("hauptmenubutton");
        vorheriger.setOnAction(event -> {
            if (soeldnerIndex > 0) {
                soeldnerIndex--;
//                System.out.println("soeldnerindex " + soeldnerIndex);
                if (taverneController.getSoeldner()[soeldnerIndex] == null) {
                    for (int i = soeldnerIndex; i > 0; --i) {
                        if (taverneController.getSoeldner()[i] != null) {
//                            System.out.println(taverneController.getSoeldner()[i]);
                            soeldnerIndex = i;
                            break;
                        } else {
                            soeldnerIndex--;
                        }
                    }
                }
//                System.out.println("soeldnerindex " + soeldnerIndex);
                updateSoeldnerAnzeige(taverneController, soeldnerIndex);
            }
        });
        buttons.setSpacing(50.0);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(vorheriger, naechsterHBox);
        buttons.setDisable(taverneController.istKeinSoeldnerVorhanden().getValue());
        buttons.setVisible(!taverneController.istKeinSoeldnerVorhanden().getValue());

        VBox soeldnerKlasseGeschichte = new VBox();
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            soeldnerKlasse = new Label(taverneController.getSoeldner()[soeldnerIndex].getKlasse().getBezeichnung());
            soeldnerKlasse.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ddb622");
            soeldnerGeschichte = new TextArea(taverneController.getSoeldner()[soeldnerIndex].getGeschichte());
            soeldnerGeschichte.setWrapText(true);
//            soeldnerGeschichte.setBackground(Background.EMPTY);
            soeldnerGeschichte.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #b3744c;");
            soeldnerGeschichte.getStyleClass().add("tavernetext-area");
            soeldnerKlasseGeschichte.setAlignment(Pos.CENTER);
            soeldnerKlasseGeschichte.getChildren().addAll(soeldnerKlasse, soeldnerGeschichte);
        }
        this.getChildren().addAll(soeldnerName, soeldnerHBox, buttons, soeldnerKlasseGeschichte);
        this.setMaxWidth(1536.0);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);
    }

    public void updateSoeldnerAnzeige(TaverneController taverneController, int soeldnerIndex) {

        if (!taverneController.istKeinSoeldnerVorhanden().getValue() && taverneController.getSoeldner()[soeldnerIndex] != null) {
            soeldnerName.setText(taverneController.getSoeldner()[soeldnerIndex].getName());
            Image soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerView.setImage(soeldnerBild);
            soeldnerKlasse.setText(taverneController.getSoeldner()[soeldnerIndex].getKlasse().getBezeichnung());
            soeldnerGeschichte.setText(taverneController.getSoeldner()[soeldnerIndex].getGeschichte());
        } else if (taverneController.istKeinSoeldnerVorhanden().getValue()) {
            soeldnerName.setText("Keine Söldner vorhanden!");
            soeldnerView.setImage(null);
            soeldnerKlasse.setText("");
            soeldnerGeschichte.setText("");
        }

    }

    public int getSoeldnerIndex() {
        return soeldnerIndex;
    }

}
