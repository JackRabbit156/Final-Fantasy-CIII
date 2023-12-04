package trainer.faehigkeiten.View;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import trainer.faehigkeiten.Faehigkeit;

import java.text.DecimalFormat;

public class FaehigkeitAnzeige extends HBox {
    private static final double HORIZONTALE_LUECKE = 10;
    private static final double VERTIKALE_LUECKE = 10;

    public static final DecimalFormat doubleFormat = new DecimalFormat("#.##");
    private static final Font ueberschrift = new Font("Lucida Calligraphy Italic", 20);
    private static final Font kleineSchrift = new Font("Lucida Calligraphy Italic", 12);
    private static final BackgroundFill fill = new BackgroundFill(Color.rgb(179, 116, 76), CornerRadii.EMPTY, Insets.EMPTY);
    private static final Background hintergrund = new Background(fill);

    private BooleanProperty istSelektiert = new SimpleBooleanProperty();

    Faehigkeit faehigkeit;


    public FaehigkeitAnzeige(Faehigkeit faehigkeit) {
        this.faehigkeit = faehigkeit;
        double breite = FaehigkeitenSpielerCharakterAnzeige.BREITE;
        double hoehe = FaehigkeitenSpielerCharakterAnzeige.HOEHE;


        ImageView iconFaehigkeit = new ImageView(new Image(faehigkeit.getIcon(), 32, 32, true, true));
        VBox iconVBox = new VBox(iconFaehigkeit);
        iconVBox.setPadding(new Insets(20));
        iconVBox.setAlignment(Pos.CENTER);
//        iconFaehigkeit.setFitHeight(100);
//        iconFaehigkeit.setFitWidth(100);

        GridPane gripPaneNameBeschreibung = new GridPane();
        TextField nameText = new TextField(faehigkeit.getName());
        nameText.setAlignment(Pos.CENTER);
        nameText.setMouseTransparent(true);
        nameText.setEditable(false);
        nameText.setPrefWidth(3 * breite);
        nameText.setPrefHeight(hoehe);
        gripPaneNameBeschreibung.add(nameText, 0, 0, 3, 1);


        //TODO: cooles Icon oder so
        TextField levelText = new TextField("Level: " + Integer.toString(faehigkeit.getLevel()));
        levelText.setAlignment(Pos.CENTER_LEFT);
        levelText.setMouseTransparent(true);
        levelText.setEditable(false);
        levelText.setPrefHeight(hoehe);
        levelText.setPrefWidth(breite);
        gripPaneNameBeschreibung.add(levelText, 0, 1);

        TextField manaKostenText = new TextField("Kosten: " + Integer.toString(faehigkeit.getManaKosten()) + " MP");
        manaKostenText.setAlignment(Pos.CENTER);
        manaKostenText.setMouseTransparent(true);
        manaKostenText.setEditable(false);
        manaKostenText.setPrefWidth(breite);
        manaKostenText.setPrefHeight(hoehe);
        gripPaneNameBeschreibung.add(manaKostenText, 1, 1);

        //TODO: Kursiv / gräulicher Text
        TextField istFreundlichText = new TextField("Typ: " + ((faehigkeit.isIstFreundlich()) ? "Support-Fähigkeit" : "Angriffs-Fähigkeit"));
        istFreundlichText.setAlignment(Pos.CENTER_RIGHT);
        istFreundlichText.setMouseTransparent(true);
        istFreundlichText.setEditable(false);
        istFreundlichText.setPrefHeight(hoehe);
        istFreundlichText.setPrefWidth(breite);
        gripPaneNameBeschreibung.add(istFreundlichText, 2, 1);

        TextArea beschreibungText = new TextArea(faehigkeit.getBeschreibung());
        beschreibungText.setWrapText(true);
        beschreibungText.setMaxHeight(40);
        beschreibungText.setMouseTransparent(true);
        this.istSelektiert.addListener((observable, oldValue, newValue) -> beschreibungText.setMouseTransparent(!newValue));
        beschreibungText.setEditable(false);
        beschreibungText.setPrefHeight(hoehe);
        beschreibungText.setPrefWidth(3 * breite);
        gripPaneNameBeschreibung.add(beschreibungText, 0, 2, 3, 2);

        TextField effektStaerkeText = new TextField(Integer.toString(faehigkeit.getEffektStaerke()));
        effektStaerkeText.setMouseTransparent(true);
        effektStaerkeText.setEditable(false);
        effektStaerkeText.setAlignment(Pos.CENTER);
        effektStaerkeText.setPrefWidth(breite);
        effektStaerkeText.setPrefHeight(hoehe);
        effektStaerkeText.prefHeightProperty().bind(gripPaneNameBeschreibung.heightProperty());

        TextField zielAnzahlText = new TextField(Integer.toString(faehigkeit.getZielAnzahl()));
        zielAnzahlText.setMouseTransparent(true);
        zielAnzahlText.setEditable(false);
        zielAnzahlText.setAlignment(Pos.CENTER);
        zielAnzahlText.setPrefHeight(hoehe);
        zielAnzahlText.setPrefWidth(breite);
        zielAnzahlText.prefHeightProperty().bind(gripPaneNameBeschreibung.heightProperty());

        TextField wahrscheinlichkeitText = new TextField("+ " + doubleFormat.format((faehigkeit.getWahrscheinlichkeit() - 1)*100) + "%");
        wahrscheinlichkeitText.setMouseTransparent(true);
        wahrscheinlichkeitText.setEditable(false);
        wahrscheinlichkeitText.setAlignment(Pos.CENTER);
        wahrscheinlichkeitText.setPrefWidth(breite);
        wahrscheinlichkeitText.setPrefHeight(hoehe);
        wahrscheinlichkeitText.prefHeightProperty().bind(gripPaneNameBeschreibung.heightProperty());

//Style
        //Transparenz
//        BackgroundFill transparentFill = new BackgroundFill(Color.rgb(255, 255, 255, 0.4), CornerRadii.EMPTY, Insets.EMPTY);
//        Background transparentBackground = new Background(transparentFill);
//
//        nameText.setBackground(transparentBackground);
//        levelText.setBackground(transparentBackground);
//        manaKostenText.setBackground(transparentBackground);
//        istFreundlichText.setBackground(transparentBackground);
//        beschreibungText.setBackground(transparentBackground);
////        beschreibungText.setStyle("-fx-control-inner-background: rgba(255, 255, 255, 0.1);");
//        effektStaerkeText.setBackground(transparentBackground);
//        zielAnzahlText.setBackground(transparentBackground);
//        wahrscheinlichkeitText.setBackground(transparentBackground);
//        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
//
//        double transparenz = 0.5;
//        nameText.setOpacity(transparenz);
//        levelText.setOpacity(transparenz);
//        manaKostenText.setOpacity(transparenz);
//        istFreundlichText.setOpacity(transparenz);
//        beschreibungText.setOpacity(transparenz);
//        effektStaerkeText.setOpacity(transparenz);
//        zielAnzahlText.setOpacity(transparenz);
//        wahrscheinlichkeitText.setOpacity(transparenz);

        //CSS-Styles#
        nameText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        levelText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        manaKostenText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        istFreundlichText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        beschreibungText.getStyleClass().add("trainerFaehigkeitenTextArea");
        effektStaerkeText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        zielAnzahlText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        wahrscheinlichkeitText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");

        this.getChildren().addAll(iconVBox, gripPaneNameBeschreibung, effektStaerkeText, zielAnzahlText, wahrscheinlichkeitText);
    }

    public boolean isIstSelektiert() {
        return istSelektiert.get();
    }

    public BooleanProperty istSelektiertProperty() {
        return istSelektiert;
    }

    public void setIstSelektiert(boolean istSelektiert) {
        this.istSelektiert.set(istSelektiert);
    }

    public Faehigkeit getFaehigkeit() {
        return faehigkeit;
    }

    public void setFaehigkeit(Faehigkeit faehigkeit) {
        this.faehigkeit = faehigkeit;
    }
}
