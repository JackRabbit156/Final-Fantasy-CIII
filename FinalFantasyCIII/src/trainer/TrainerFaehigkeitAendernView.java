package trainer;

import charakter.model.SpielerCharakter;

import charakter.model.klassen.spezialisierungen.Spezialisierung;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import trainer.faehigkeiten.View.FaehigkeitenSpielerCharakterAnzeige;

public class TrainerFaehigkeitAendernView extends BorderPane {
    TrainerController trainerController;
    SpielerCharakter akuellerCharakter;
    CornerRadii hintergrundRadii = new CornerRadii(15);
    BackgroundFill selektiertFuellung = new BackgroundFill(Color.rgb(249, 167, 79, 0.8), hintergrundRadii, Insets.EMPTY);
    Background selektiertHintergrund = new Background(selektiertFuellung);
    BorderWidths rahmenBreite = new BorderWidths(10);
    CornerRadii rahmenRadii = new CornerRadii(10);
    BackgroundFill unselektiertFuellung = new BackgroundFill(Color.rgb(94, 57, 34, 0.6), hintergrundRadii, Insets.EMPTY);
    Border selektiertRahmen = new Border(new BorderStroke(unselektiertFuellung.getFill(), BorderStrokeStyle.SOLID, rahmenRadii, rahmenBreite));

    public TrainerFaehigkeitAendernView(TrainerController trainerController) {
        this.trainerController = trainerController;
        this.akuellerCharakter = trainerController.getAktuellerCharakter();
        //Anzeige Tabelle
        FaehigkeitenSpielerCharakterAnzeige anzeige = new FaehigkeitenSpielerCharakterAnzeige(this.akuellerCharakter);
        this.setCenter(anzeige);

        double breite = FaehigkeitenSpielerCharakterAnzeige.BREITE;
        double hoehe = FaehigkeitenSpielerCharakterAnzeige.HOEHE;

        //Header
        ImageView iconCharakter = new ImageView(this.akuellerCharakter.getGrafischeDarstellung());
        iconCharakter.setFitHeight(150);
        iconCharakter.setFitWidth(150);

        GridPane charakterEigenschaften = new GridPane();
        Label headerText = new Label("Fähigkeiten für " + this.akuellerCharakter.getName());
        headerText.setPrefHeight(hoehe);
        headerText.setPrefWidth(3 * breite);
        headerText.setMouseTransparent(false);
        charakterEigenschaften.add(headerText, 0, 0, 3, 1);
        Label klasseText = new Label("Klasse: " + this.akuellerCharakter.getKlasse().getBezeichnung());
        klasseText.setPrefWidth(breite);
        klasseText.setPrefHeight(hoehe);
        klasseText.setMouseTransparent(false);
        charakterEigenschaften.add(klasseText, 0, 1);
        Label spezialisierungText = new Label("Spezialisierung: " + ((this.akuellerCharakter.getKlasse() instanceof Spezialisierung) ? this.akuellerCharakter.getKlasse().getClass().getSimpleName() : " - "));
        spezialisierungText.setPrefWidth(breite);
        spezialisierungText.setPrefHeight(hoehe);
        charakterEigenschaften.add(spezialisierungText, 1, 1, 1, 2);
        charakterEigenschaften.setAlignment(Pos.CENTER);

        //Stärke
        Label effektStaerkeText = new Label("Stärke");
        effektStaerkeText.setAlignment(Pos.CENTER);
        effektStaerkeText.setPrefHeight(hoehe);
        effektStaerkeText.setPrefWidth(breite);
        effektStaerkeText.setMouseTransparent(false);
        TextArea beschreibungStaerke = new TextArea("Die Stärke beschreibt den Effekt dieser Fähigkeit auf den ausgewählten Charakter. Dieser kann sowohl positiv (Heilung) wie auch negativ (Schaden) ausfallen.");
        beschreibungStaerke.setPrefWidth(breite);
        beschreibungStaerke.setMaxHeight(hoehe);
        beschreibungStaerke.setWrapText(true);
        beschreibungStaerke.setMouseTransparent(false);
        VBox staerkeVBox = new VBox(effektStaerkeText, beschreibungStaerke);
        staerkeVBox.setAlignment(Pos.CENTER);

        // Anzahl Ziele
        Label anzahlZieleText = new Label("Anzahl Ziele");
        anzahlZieleText.setAlignment(Pos.CENTER);
        anzahlZieleText.setPrefWidth(breite);
        anzahlZieleText.setPrefHeight(hoehe);
        anzahlZieleText.setMouseTransparent(false);
        TextArea beschreibungAnzahlZiele = new TextArea("Anzahl Ziele legt fest wie viele Charaktere durch diese Fähigkeit ausgewählt werden können.");
        beschreibungAnzahlZiele.setPrefWidth(breite);
        beschreibungAnzahlZiele.setMaxHeight(hoehe);
        beschreibungAnzahlZiele.setWrapText(true);
        beschreibungAnzahlZiele.setMouseTransparent(false);
        VBox anzahlZieleTextVBox = new VBox(anzahlZieleText, beschreibungAnzahlZiele);
        anzahlZieleTextVBox.setAlignment(Pos.CENTER);

        //Wahrscheinlichkeit
        Label wahrscheinlichkeitText = new Label("Wahrscheinlichkeit");
        wahrscheinlichkeitText.setAlignment(Pos.CENTER);
        wahrscheinlichkeitText.setPrefHeight(hoehe);
        wahrscheinlichkeitText.setPrefWidth(breite);
        wahrscheinlichkeitText.setMouseTransparent(false);
        TextArea beschreibungWahrscheinlichkeit = new TextArea("Die Wahrscheinlichkeit erhöht die Chance auf einen kritischen Treffer.");
        beschreibungWahrscheinlichkeit.setPrefWidth(breite);
        beschreibungWahrscheinlichkeit.setMaxHeight(hoehe);
        beschreibungWahrscheinlichkeit.setWrapText(true);
        beschreibungWahrscheinlichkeit.setMouseTransparent(false);
        VBox wahrscheinlichkeitVBox = new VBox(wahrscheinlichkeitText, beschreibungWahrscheinlichkeit);
        wahrscheinlichkeitVBox.setAlignment(Pos.CENTER);

        //Freie Punkte
        Label punkteText = new Label("Punkte:");
        Label freiePunkte = new Label(Integer.toString(this.akuellerCharakter.getOffeneFaehigkeitspunkte()));
        VBox punkteVBox = new VBox(punkteText, freiePunkte);
        punkteText.setAlignment(Pos.CENTER);
        punkteText.setPrefWidth(breite);
        punkteText.setPrefHeight(hoehe);
        punkteVBox.setAlignment(Pos.CENTER);

        headerText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        klasseText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        spezialisierungText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        effektStaerkeText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungStaerke.getStyleClass().add("trainerFaehigkeitenTextArea");
        anzahlZieleText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungAnzahlZiele.getStyleClass().add("trainerFaehigkeitenTextArea");
        wahrscheinlichkeitText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungWahrscheinlichkeit.getStyleClass().add("trainerFaehigkeitenTextArea");
        punkteText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        freiePunkte.getStyleClass().add("trainerFaehigkeitenTextFieldGross");

        HBox headerZeile = new HBox(iconCharakter, charakterEigenschaften, staerkeVBox, anzahlZieleTextVBox, wahrscheinlichkeitVBox, punkteVBox);
        headerZeile.setPadding(new Insets(0,8,10,8));
        headerZeile.setBackground(selektiertHintergrund);
        headerZeile.setBorder(selektiertRahmen);
        headerZeile.setMinWidth(100 + 6 * breite + (2 * headerZeile.getPadding().getLeft()));
        headerZeile.setAlignment(Pos.TOP_LEFT);
        VBox platzhalterOben = new VBox();
        platzhalterOben.setMinHeight(100);
        platzhalterOben.setAlignment(Pos.BOTTOM_LEFT);
        VBox oben = new VBox(platzhalterOben, headerZeile);
        this.setTop(oben);

//Rechts
        VBox platzhalterRechts = new VBox();
        platzhalterRechts.setMinWidth(384);
        this.setRight(platzhalterRechts);

//Unten
//        Button aufwertenButton = new Button("aufwerten");
//        aufwertenButton.getStyleClass().add("hauptmenubutton");
        Button zuruecksetzenButton = new Button("Fähigkeiten zurücksetzten");
        zuruecksetzenButton.getStyleClass().add("hauptmenubutton");
        zuruecksetzenButton.setMinWidth(400);
        HBox unten = new HBox(zuruecksetzenButton);
        unten.setSpacing(100);
        unten.setAlignment(Pos.CENTER_LEFT);
        unten.setPadding(new Insets(30));
        this.setBottom(unten);

        BackgroundImage hintergrund = new BackgroundImage(
                new Image("background/faehigkeitenHintergrund.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false)
        );
        this.setBackground(new Background(hintergrund));
    }
}
