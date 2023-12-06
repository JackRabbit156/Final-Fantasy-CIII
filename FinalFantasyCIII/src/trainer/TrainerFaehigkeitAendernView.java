package trainer;

import charakter.model.SpielerCharakter;

import charakter.model.klassen.spezialisierungen.Spezialisierung;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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

    FaehigkeitenSpielerCharakterAnzeige anzeige;
    ImageView iconCharakter;
    Label headerText, klasseText, spezialisierungText, freiePunkteText;

    /**
     * TrainerFaehigkeitAendernView legt den Style der View fest in der die Fähigkeiten angezeigt werden sollen
     * @param trainerController Verbindung dieser Klasse mit dem Model/Rest der Anwendung
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     */
    public TrainerFaehigkeitAendernView(TrainerController trainerController) {
        this.trainerController = trainerController;
        this.akuellerCharakter = trainerController.getAktuellerCharakter();
        //Anzeige Tabelle
        anzeige = new FaehigkeitenSpielerCharakterAnzeige(trainerController);
        this.setCenter(anzeige);

        double breite = 180;
        double hoehe = 60;

    //Header
        iconCharakter = new ImageView(this.akuellerCharakter.getGrafischeDarstellung());
        iconCharakter.setFitHeight(150);
        iconCharakter.setFitWidth(150);

        GridPane charakterEigenschaften = new GridPane();
        headerText = new Label("Fähigkeiten für " + this.akuellerCharakter.getName());
        headerText.setPrefHeight(hoehe);
        headerText.setPrefWidth(3 * breite);
        headerText.setMouseTransparent(false);
        charakterEigenschaften.add(headerText, 0, 0, 3, 1);
        klasseText = new Label("Klasse: " + this.akuellerCharakter.getKlasse().getBezeichnung());
        klasseText.setPrefWidth(breite);
        klasseText.setPrefHeight(hoehe);
        klasseText.setMouseTransparent(false);
        charakterEigenschaften.add(klasseText, 0, 1);
        spezialisierungText = new Label("Spezialisierung: " + ((this.akuellerCharakter.getKlasse() instanceof Spezialisierung) ? this.akuellerCharakter.getKlasse().getClass().getSimpleName() : " - "));
        spezialisierungText.setPrefWidth(2*breite);
        spezialisierungText.setPrefHeight(hoehe);
        charakterEigenschaften.add(spezialisierungText, 1, 1, 1, 2);
        charakterEigenschaften.setAlignment(Pos.CENTER);

    //Stärke
        Label effektStaerkeText = new Label("Stärke");
        effektStaerkeText.setAlignment(Pos.CENTER);
        effektStaerkeText.setPrefHeight(hoehe);
        effektStaerkeText.setPrefWidth(breite);
        effektStaerkeText.setMouseTransparent(false);
        Tooltip beschreibungStaerke = new Tooltip("Die Stärke beschreibt den Effekt dieser Fähigkeit auf den ausgewählten Charakter. Dieser kann sowohl positiv (Heilung) wie auch negativ (Schaden) ausfallen.");
        Tooltip.install(effektStaerkeText, beschreibungStaerke);
        beschreibungStaerke.setPrefWidth(breite);
        beschreibungStaerke.setWrapText(true);
        VBox staerkeVBox = new VBox(effektStaerkeText);
        staerkeVBox.setAlignment(Pos.CENTER);

    // Anzahl Ziele
        Label anzahlZieleText = new Label("Anzahl Ziele");
        anzahlZieleText.setAlignment(Pos.CENTER);
        anzahlZieleText.setPrefWidth(breite);
        anzahlZieleText.setPrefHeight(hoehe);
        anzahlZieleText.setMouseTransparent(false);
        Tooltip beschreibungAnzahlZiele = new Tooltip("Anzahl Ziele legt fest wie viele Charaktere durch diese Fähigkeit ausgewählt werden können.");
        Tooltip.install(anzahlZieleText, beschreibungAnzahlZiele);
        beschreibungAnzahlZiele.setPrefWidth(breite);
        beschreibungAnzahlZiele.setWrapText(true);
        VBox anzahlZieleTextVBox = new VBox(anzahlZieleText);
        anzahlZieleTextVBox.setAlignment(Pos.CENTER);

    //Wahrscheinlichkeit
        Label wahrscheinlichkeitText = new Label("Chance");
        wahrscheinlichkeitText.setAlignment(Pos.CENTER);
        wahrscheinlichkeitText.setPrefHeight(hoehe);
        wahrscheinlichkeitText.setPrefWidth(breite);
        wahrscheinlichkeitText.setMouseTransparent(false);
        Tooltip beschreibungWahrscheinlichkeit = new Tooltip("Die Chance erhöht die Wahrscheinlichkeit auf einen kritischen Treffer.");
        Tooltip.install(wahrscheinlichkeitText, beschreibungWahrscheinlichkeit);
        beschreibungWahrscheinlichkeit.setPrefWidth(breite);
        beschreibungWahrscheinlichkeit.setWrapText(true);
        VBox wahrscheinlichkeitVBox = new VBox(wahrscheinlichkeitText);
        wahrscheinlichkeitVBox.setAlignment(Pos.CENTER);

    //Freie Punkte
        Label punkteText = new Label("verfügbare\nPunkte:");
        punkteText.setPrefWidth(breite);
        punkteText.setPrefHeight(hoehe);
        punkteText.setAlignment(Pos.CENTER);
        freiePunkteText = new Label(Integer.toString(this.akuellerCharakter.getOffeneFaehigkeitspunkte()));
        freiePunkteText.setPrefSize(breite, hoehe);
        freiePunkteText.setAlignment(Pos.CENTER);
        VBox punkteVBox = new VBox(punkteText, freiePunkteText);
        punkteVBox.setAlignment(Pos.CENTER);

        headerText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        klasseText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        spezialisierungText.getStyleClass().add("trainerFaehigkeitenTextFieldKlein");
        effektStaerkeText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungStaerke.getStyleClass().add("trainerBeschreibungNoScrollbar");
        anzahlZieleText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungAnzahlZiele.getStyleClass().add("trainerBeschreibungNoScrollbar");
        wahrscheinlichkeitText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        beschreibungWahrscheinlichkeit.getStyleClass().add("trainerBeschreibungNoScrollbar");
        punkteText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");
        freiePunkteText.getStyleClass().add("trainerFaehigkeitenTextFieldGross");

        HBox headerZeile = new HBox(iconCharakter, charakterEigenschaften, staerkeVBox, anzahlZieleTextVBox, wahrscheinlichkeitVBox, punkteVBox);
        headerZeile.setPadding(new Insets(0, 8, 10, 18));
        headerZeile.setBackground(selektiertHintergrund);
        headerZeile.setBorder(selektiertRahmen);
        headerZeile.setMinWidth(100 + 6 * breite + (2 * headerZeile.getPadding().getLeft()));
        headerZeile.setAlignment(Pos.TOP_LEFT);
        VBox platzhalterOben = new VBox();
        platzhalterOben.setMinHeight(100);
        platzhalterOben.setAlignment(Pos.BOTTOM_LEFT);
        VBox oben = new VBox(platzhalterOben, headerZeile);
        oben.setMaxWidth(1536);
        this.setTop(oben);

//Rechts
        VBox platzhalterRechts = new VBox();
        platzhalterRechts.setMinWidth(384);
        this.setRight(platzhalterRechts);

//Unten
        Button zuruecksetzenButton = new Button("Fähigkeiten zurücksetzen");
        zuruecksetzenButton.setOnAction(event -> trainerController.faehigkeitenZuruecksetzen());
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

    /**
     * Die TrainerFaehigkeiAendernView wird neu gezeichnet / aktualisiert
     * @author Oliver Ebert
     * @since 06.12.2023
     */
    public void anzeigeVorbereiten() {
        this.akuellerCharakter = trainerController.getAktuellerCharakter();
        this.anzeige.getItems().setAll(akuellerCharakter.getFaehigkeiten());
        this.iconCharakter.setImage(new Image(this.akuellerCharakter.getGrafischeDarstellung()));

        this.headerText.setText("Fähigkeiten für " + this.akuellerCharakter.getName());
        this.klasseText.setText("Klasse: " + this.akuellerCharakter.getKlasse().getBezeichnung());
        this.spezialisierungText.setText("Spezialisierung: " + ((this.akuellerCharakter.getKlasse() instanceof Spezialisierung) ? this.akuellerCharakter.getKlasse().getClass().getSimpleName() : " - "));
        this.freiePunkteText.setText(Integer.toString(this.akuellerCharakter.getOffeneFaehigkeitspunkte()));
    }
}
