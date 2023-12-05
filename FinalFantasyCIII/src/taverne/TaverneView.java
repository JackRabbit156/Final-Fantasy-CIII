package taverne;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;

public class TaverneView extends VBox {

    private int soeldnerIndex = 0;
    private Label soeldnerName;
    private ImageView soeldnerView;
    private Label soeldnerKlasse;
    private TextArea soeldnerGeschichte;
    private Label waffenLabel;
    private ImageView waffeIcon;
    private Label physAttackeLabel;
    private ImageView physischeAttackeIcon;
    private Label magAttackeLabel;
    private ImageView magAttackeIcon;
    private Label ruestungLabel;
    private ImageView ruestungIcon;
    private Label verteidigungLabel;
    private ImageView verteidigungIcon;
    private Label magVerteidigungLabel;
    private ImageView magVerteidigungIcon;
    private Label gesundheitLabel;
    private ImageView gesundheitIcon;
    private Label manaLabel;
    private ImageView manaIcon;
    private Label levelLabel;
    private ImageView erfahrungBisLevelUpIcon;
    private Label genauigkeitLabel;
    private ImageView genauIcon;
    private Label hpRegenerationLabel;
    private ImageView gesRegIcon;
    private Label mpRegenerationLabel;
    private ImageView manaRIcon;
    private Label resistenzLabel;
    private ImageView resiIcon;
    private Label beweglichkeitLabel;
    private ImageView bewegIcon;
    private HBox level;
    private HBox soeldnerKlasseHBox;
    private Image soeldnerBild;

    public TaverneView(TaverneController taverneController) {
        this.setBackground(new Background(new BackgroundImage(new Image("background/taverne.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));
        BorderPane hauptBorderPane = new BorderPane();
        HBox soeldnerHBox = new HBox();
        Label leer = new Label("");

        // das erste nicht-null Element finden
        for (int i = soeldnerIndex; i < taverneController.getSoeldner().length; i++) {
            if (taverneController.getSoeldner()[i] != null) {
                soeldnerIndex = i;
                break;
            }
        }

        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            soeldnerName = new Label("Name: " + taverneController.getSoeldner()[soeldnerIndex].getName());
            soeldnerKlasse = new Label("Klasse: " + taverneController.getSoeldner()[soeldnerIndex].getKlasse().getBezeichnung());
            ImageView soeldnerKlasseIcon = new ImageView(new Image("/icons/klassen.png", 32.0, 0, true, false));
            soeldnerKlasseHBox = new HBox(soeldnerKlasseIcon, soeldnerKlasse);
            soeldnerKlasseHBox.setAlignment(Pos.CENTER);
            soeldnerKlasseHBox.setSpacing(10);
            Button soeldnerAnzeige = new Button();
            soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerView = new ImageView(soeldnerBild);
            soeldnerAnzeige.setBackground(null);
            soeldnerAnzeige.setGraphic(soeldnerView);
            soeldnerHBox.setAlignment(Pos.CENTER);
            soeldnerHBox.getChildren().add(soeldnerAnzeige);
        }

        // BUTTONS
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
        // BUTTONS ENDE

        VBox soeldnerGeschichte = new VBox();
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            this.soeldnerGeschichte = new TextArea(taverneController.getSoeldner()[soeldnerIndex].getGeschichte());
            this.soeldnerGeschichte.setWrapText(true);
            this.soeldnerGeschichte.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #b3744c;");
            this.soeldnerGeschichte.getStyleClass().add("tavernetext-area");
            this.soeldnerGeschichte.setPrefHeight(350);
            soeldnerGeschichte.setAlignment(Pos.BOTTOM_CENTER);
            soeldnerGeschichte.setPadding(new Insets(30,30,30,30));
            soeldnerGeschichte.getChildren().addAll(this.soeldnerGeschichte);
        }

        VBox statsLinks = new VBox();
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            gesundheitLabel = new Label("Max-Gesundheit: " + taverneController.getSoeldner()[soeldnerIndex].getMaxGesundheitsPunkte());
            gesundheitIcon = new ImageView(new Image("/icons/hp.png", 32.0, 0, true, false));
            HBox gesundheit = new HBox(gesundheitLabel, gesundheitIcon);
            gesundheit.setAlignment(Pos.CENTER_RIGHT);
            gesundheit.setSpacing(10);
            manaLabel = new Label("Max-Mana: " + taverneController.getSoeldner()[soeldnerIndex].getMaxManaPunkte());
            manaIcon = new ImageView(new Image("/icons/mp.png", 32.0, 0, true, false));
            HBox mana = new HBox(manaLabel, manaIcon);
            mana.setAlignment(Pos.CENTER_RIGHT);
            mana.setSpacing(10);
            levelLabel = new Label("Level: " + taverneController.getSoeldner()[soeldnerIndex].getLevel());
            erfahrungBisLevelUpIcon = new ImageView(new Image("/icons/lvl.png", 32.0, 0, true, false));
            level = new HBox(erfahrungBisLevelUpIcon, levelLabel);
            level.setAlignment(Pos.CENTER_RIGHT);
            level.setSpacing(10);
            genauigkeitLabel = new Label("Genauigkeit: " + taverneController.getSoeldner()[soeldnerIndex].getGenauigkeit());
            genauIcon = new ImageView(new Image("/icons/genauigkeit.png", 32.0, 0, true, false));
            HBox genauigkeit = new HBox(genauigkeitLabel,genauIcon);
            genauigkeit.setAlignment(Pos.CENTER_RIGHT);
            genauigkeit.setSpacing(10);
            hpRegenerationLabel = new Label("HP-Regeneration: " + taverneController.getSoeldner()[soeldnerIndex].getGesundheitsRegeneration());
            gesRegIcon = new ImageView(new Image("/icons/gesundheitsRegeneration.png", 32.0, 0, true, false));
            HBox hpRegeneration = new HBox(hpRegenerationLabel, gesRegIcon);
            hpRegeneration.setAlignment(Pos.CENTER_RIGHT);
            hpRegeneration.setSpacing(10);
            mpRegenerationLabel = new Label("MP-Regeneration: " + taverneController.getSoeldner()[soeldnerIndex].getManaRegeneration());
            manaRIcon = new ImageView(new Image("/icons/manaRegeneration.png", 32.0, 0, true, false));
            HBox manaReg = new HBox(mpRegenerationLabel, manaRIcon);
            manaReg.setAlignment(Pos.CENTER_RIGHT);
            manaReg.setSpacing(10);
            resistenzLabel = new Label("Resistenz: " + taverneController.getSoeldner()[soeldnerIndex].getResistenz());
            resiIcon = new ImageView(new Image("/icons/resistenz.png", 32.0, 0, true, false));
            HBox resistenz = new HBox(resistenzLabel, resiIcon);
            resistenz.setAlignment(Pos.CENTER_RIGHT);
            resistenz.setSpacing(10);
            beweglichkeitLabel = new Label("Beweglichkeit: " + taverneController.getSoeldner()[soeldnerIndex].getBeweglichkeit());
            bewegIcon = new ImageView(new Image("/icons/beweglichkeit.png", 32.0, 0, true, false));
            HBox beweglichkeit = new HBox(beweglichkeitLabel,bewegIcon);
            beweglichkeit.setAlignment(Pos.CENTER_RIGHT);
            beweglichkeit.setSpacing(10);
//        beweglichkeitLabel.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: red; -fx-text-inner-color: red");
            statsLinks.getChildren().addAll(gesundheit,mana,genauigkeit,hpRegeneration,manaReg,resistenz,beweglichkeit);
            statsLinks.setPadding(new Insets(10,30,0,150));
            statsLinks.setSpacing(25);
            statsLinks.getStyleClass().add("tavernelabels");
//        statsLinks.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: red; -fx-text-inner-color: red");
        }

        VBox statsRechts = new VBox();
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            waffenLabel = new Label("Waffe: " + taverneController.getSoeldner()[soeldnerIndex].getWaffe().getName());
            waffeIcon = new ImageView(taverneController.getSoeldner()[soeldnerIndex].getWaffe().getIcon());
            HBox waffe = new HBox(waffeIcon, waffenLabel);
            waffe.setSpacing(10);
            physAttackeLabel = new Label("Physische Attacke: " + taverneController.getSoeldner()[soeldnerIndex].getPhysischeAttacke());
            physischeAttackeIcon = new ImageView(new Image("/icons/physischeAttacke.png", 32.0, 0, true, false));
            HBox physAttacke = new HBox(physischeAttackeIcon, physAttackeLabel);
            physAttacke.setSpacing(10);
            magAttackeLabel = new Label("Magische Attacke: " + taverneController.getSoeldner()[soeldnerIndex].getMagischeAttacke());
            magAttackeIcon = new ImageView(new Image("/icons/magischeAttacke.png", 32.0, 0, true, false));
            HBox magAttacke = new HBox(magAttackeIcon, magAttackeLabel);
            magAttacke.setSpacing(10);
            ruestungLabel = new Label("Rüstung: " + taverneController.getSoeldner()[soeldnerIndex].getRuestung().getName());
            ruestungIcon = new ImageView(taverneController.getSoeldner()[soeldnerIndex].getRuestung().getIcon());
            HBox ruestung = new HBox(ruestungIcon, ruestungLabel);
            ruestung.setSpacing(10);
            verteidigungLabel = new Label("Verteidigung: " + taverneController.getSoeldner()[soeldnerIndex].getVerteidigung());
            verteidigungIcon = new ImageView(new Image("/icons/physischeVerteidigung.png", 32.0, 0, true, false));
            HBox verteidigung = new HBox(verteidigungIcon, verteidigungLabel);
            verteidigung.setSpacing(10);
            magVerteidigungLabel = new Label("Magische Verteidigung: " + taverneController.getSoeldner()[soeldnerIndex].getMagischeVerteidigung());
            magVerteidigungIcon = new ImageView(new Image("/icons/magischeVerteidigung.png", 32.0, 0, true, false));
            HBox magVerteidigung = new HBox(magVerteidigungIcon, magVerteidigungLabel);
            magVerteidigung.setSpacing(10);
//        Label accessoire = new Label("Accessoire: " + Arrays.toString(taverneController.getSoeldner()[soeldnerIndex].getAccessoires().));
            statsRechts.getChildren().addAll(waffe,physAttacke,magAttacke,ruestung,verteidigung,magVerteidigung);/*accessoire*/
            statsRechts.setPadding(new Insets(50,150,0,30));
//        statsRechts.setMaxWidth(500);
            soeldnerGeschichte.getStyleClass().add("tavernelabels");
//        statsRechts.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: red; -fx-text-inner-color: red");
            statsRechts.setSpacing(25);
            statsRechts.getStyleClass().add("tavernelabels");
        }

        HBox hBoxBorderPaneTop = new HBox();
        if (!taverneController.istKeinSoeldnerVorhanden().getValue()) {
            hBoxBorderPaneTop = new HBox(soeldnerName, level, soeldnerKlasseHBox);
            hBoxBorderPaneTop.setAlignment(Pos.CENTER);
            hBoxBorderPaneTop.setSpacing(25.0);
            hBoxBorderPaneTop.setPrefHeight(150.0);
            hBoxBorderPaneTop.setPrefWidth(1536.0);
            hBoxBorderPaneTop.getStyleClass().add("taverneTop");
        } else {
            leer.setText("Keine Söldner zum anheuern vorhanden!");
            leer.setStyle("-fx-font: 30px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");
            leer.setPadding(new Insets(0,0,540,0));
//            System.out.println("TEST");
        }

        VBox vBoxBorderPaneCenter = new VBox();
        vBoxBorderPaneCenter.setAlignment(Pos.CENTER);
        vBoxBorderPaneCenter.getChildren().addAll(soeldnerHBox, buttons);

        hauptBorderPane.setTop(hBoxBorderPaneTop);
        hauptBorderPane.setLeft(statsLinks);
        hauptBorderPane.setCenter(vBoxBorderPaneCenter);
        hauptBorderPane.setRight(statsRechts);
        hauptBorderPane.setBottom(soeldnerGeschichte);

        this.getChildren().addAll(hauptBorderPane, leer);
        this.setMaxWidth(1536.0);
        this.setAlignment(Pos.BOTTOM_CENTER);

    }

    public void updateSoeldnerAnzeige(TaverneController taverneController, int soeldnerIndex) {

        if (!taverneController.istKeinSoeldnerVorhanden().getValue() && taverneController.getSoeldner()[soeldnerIndex] != null) {
            soeldnerName.setText("Name: " + taverneController.getSoeldner()[soeldnerIndex].getName());
            soeldnerBild = new Image(taverneController.getSoeldner()[soeldnerIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerView.setImage(soeldnerBild);
            soeldnerKlasse.setText("Klasse: " + taverneController.getSoeldner()[soeldnerIndex].getKlasse().getBezeichnung());
            soeldnerGeschichte.setText(taverneController.getSoeldner()[soeldnerIndex].getGeschichte());
            waffenLabel.setText("Waffe: " + taverneController.getSoeldner()[soeldnerIndex].getWaffe().getName());
            waffeIcon.setImage(new Image(taverneController.getSoeldner()[soeldnerIndex].getWaffe().getIcon()));
            physAttackeLabel.setText("Physische Attacke: " + taverneController.getSoeldner()[soeldnerIndex].getPhysischeAttacke());
            physischeAttackeIcon.setImage(new Image("/icons/physischeAttacke.png", 32.0, 0, true, false));
            magAttackeLabel.setText("Magische Attacke: " + taverneController.getSoeldner()[soeldnerIndex].getMagischeAttacke());
            magAttackeIcon.setImage(new Image("/icons/magischeAttacke.png", 32.0, 0, true, false));
            ruestungLabel.setText("Rüstung: " + taverneController.getSoeldner()[soeldnerIndex].getRuestung().getName());
            ruestungIcon.setImage(new Image(taverneController.getSoeldner()[soeldnerIndex].getRuestung().getIcon()));
            verteidigungLabel.setText("Verteidigung: " + taverneController.getSoeldner()[soeldnerIndex].getVerteidigung());
            verteidigungIcon.setImage(new Image("/icons/physischeVerteidigung.png", 32.0, 0, true, false));
            magVerteidigungLabel.setText("Magische Verteidigung: " + taverneController.getSoeldner()[soeldnerIndex].getMagischeVerteidigung());
            magVerteidigungIcon.setImage(new Image("/icons/magischeVerteidigung.png", 32.0, 0, true, false));

            gesundheitLabel.setText("Max-Gesundheit: " + taverneController.getSoeldner()[soeldnerIndex].getMaxGesundheitsPunkte());
            gesundheitIcon.setImage(new Image("/icons/hp.png", 32.0, 0, true, false));
            manaLabel.setText("Max-Mana: " + taverneController.getSoeldner()[soeldnerIndex].getMaxManaPunkte());
            manaIcon.setImage(new Image("/icons/mp.png", 32.0, 0, true, false));
            levelLabel.setText("Level: " + taverneController.getSoeldner()[soeldnerIndex].getLevel());
            erfahrungBisLevelUpIcon.setImage(new Image("/icons/lvl.png", 32.0, 0, true, false));
            genauigkeitLabel.setText("Genauigkeit: " + taverneController.getSoeldner()[soeldnerIndex].getGenauigkeit());
            genauIcon.setImage(new Image("/icons/genauigkeit.png", 32.0, 0, true, false));
            hpRegenerationLabel.setText("HP-Regeneration: " + taverneController.getSoeldner()[soeldnerIndex].getGesundheitsRegeneration());
            gesRegIcon.setImage(new Image("/icons/gesundheitsRegeneration.png", 32.0, 0, true, false));
            mpRegenerationLabel.setText("MP-Regeneration: " + taverneController.getSoeldner()[soeldnerIndex].getManaRegeneration());
            manaRIcon.setImage(new Image("/icons/manaRegeneration.png", 32.0, 0, true, false));
            resistenzLabel.setText("Resistenz: " + taverneController.getSoeldner()[soeldnerIndex].getResistenz());
            resiIcon.setImage(new Image("/icons/resistenz.png", 32.0, 0, true, false));
            beweglichkeitLabel.setText("Beweglichkeit: " + taverneController.getSoeldner()[soeldnerIndex].getBeweglichkeit());
            bewegIcon.setImage(new Image("/icons/beweglichkeit.png", 32.0, 0, true, false));

        }

    }

    public int getSoeldnerIndex() {
        return soeldnerIndex;
    }

}
