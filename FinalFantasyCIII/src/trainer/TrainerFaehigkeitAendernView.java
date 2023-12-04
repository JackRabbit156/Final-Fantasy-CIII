package trainer;

import charakter.model.SpielerCharakter;

import charakter.model.klassen.spezialisierungen.Spezialisierung;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import trainer.faehigkeiten.View.FaehigkeitenSpielerCharakterAnzeige;

public class TrainerFaehigkeitAendernView extends BorderPane {
    TrainerController trainerController;
    SpielerCharakter akuellerCharakter;

    public TrainerFaehigkeitAendernView(TrainerController trainerController) {
        this.trainerController = trainerController;
        this.akuellerCharakter = trainerController.getAktuellerCharakter();
        //Anzeige Tabelle
        FaehigkeitenSpielerCharakterAnzeige anzeige = new FaehigkeitenSpielerCharakterAnzeige(this.akuellerCharakter);
        this.setCenter(anzeige);

        double breite = FaehigkeitenSpielerCharakterAnzeige.BREITE;
        double hoehe = FaehigkeitenSpielerCharakterAnzeige.HOEHE;

        //Header
        ImageView iconCharakter = new ImageView(new Image("icons/gameicon.png")); //TODO: auf spielerCharakter.getIcon() setzten
        iconCharakter.setFitHeight(100);
        iconCharakter.setFitWidth(100);

        GridPane charakterEigenschaften = new GridPane();
        TextField headerText = new TextField("Fähigkeiten für " + this.akuellerCharakter.getName());
        headerText.setPrefHeight(hoehe);
        headerText.setPrefWidth(3*breite);
        charakterEigenschaften.add(headerText, 0, 0, 3, 1);
        TextField klasseText = new TextField("Klasse: "+ this.akuellerCharakter.getKlasse().getBezeichnung());
        klasseText.setPrefWidth(breite);
        klasseText.setPrefHeight(hoehe);
        charakterEigenschaften.add(klasseText, 0, 1);
//        TextField platzhalter = new TextField("");
//        platzhalter.setPrefHeight(hoehe);
//        platzhalter.setPrefWidth(breite);
//        charakterEigenschaften.add(platzhalter, 1, 1);
        TextField spezialisierungText = new TextField("Spezialisierung: "+ ((this.akuellerCharakter.getKlasse() instanceof Spezialisierung) ? this.akuellerCharakter.getKlasse().getClass().getSimpleName() : " - "));
        spezialisierungText.setPrefWidth(breite);
        spezialisierungText.setPrefHeight(hoehe);
        charakterEigenschaften.add(spezialisierungText, 1, 1);
        charakterEigenschaften.setAlignment(Pos.CENTER);

        //Stärke
        TextField effektStaerkeText = new TextField("Stärke");
        effektStaerkeText.setAlignment(Pos.CENTER);
        effektStaerkeText.setPrefHeight(hoehe);
        effektStaerkeText.setPrefWidth(breite);
        effektStaerkeText.setEditable(false);
        TextArea beschreibungStaerke =  new TextArea("Die Stärke beschreibt den Effekt dieser Fähigkeit auf den ausgewählten Charakter. Dieser kann sowohl positiv (Heilung) wie auch negativ (Schaden) ausfallen.");
        beschreibungStaerke.setPrefWidth(breite);
        beschreibungStaerke.setMaxHeight(hoehe);
        beschreibungStaerke.setWrapText(true);
        beschreibungStaerke.setEditable(false);
        VBox staerkeVBox  =new VBox(effektStaerkeText, beschreibungStaerke);
        staerkeVBox.setAlignment(Pos.CENTER);

        // Anzahl Ziele
        TextField anzahlZieleText = new TextField("Anzahl Ziele");
        anzahlZieleText.setAlignment(Pos.CENTER);
        anzahlZieleText.setPrefWidth(breite);
        anzahlZieleText.setPrefHeight(hoehe);
        anzahlZieleText.setEditable(false);
        TextArea beschreibungAnzahlZiele = new TextArea("Anzahl Ziele legt fest wie viele Charaktere durch diese Fähigkeit ausgewählt werden können.");
        beschreibungAnzahlZiele.setPrefWidth(breite);
        beschreibungAnzahlZiele.setMaxHeight(hoehe);
        beschreibungAnzahlZiele.setWrapText(true);
        beschreibungAnzahlZiele.setEditable(false);
        VBox anzahlZieleTextVBox = new VBox(anzahlZieleText, beschreibungAnzahlZiele);
        anzahlZieleTextVBox.setAlignment(Pos.CENTER);

        //Wahrscheinlichkeit
        TextField wahrscheinlichkeitText = new TextField("Wahrscheinlichkeit");
        wahrscheinlichkeitText.setAlignment(Pos.CENTER);
        wahrscheinlichkeitText.setPrefHeight(hoehe);
        wahrscheinlichkeitText.setPrefWidth(breite);
        wahrscheinlichkeitText.setEditable(false);
        TextArea beschreibungWahrscheinlichkeit = new TextArea("Die Wahrscheinlichkeit erhöht die Chance auf einen kritischen Treffer.");
        beschreibungWahrscheinlichkeit.setPrefWidth(breite);
        beschreibungWahrscheinlichkeit.setMaxHeight(hoehe);
        beschreibungWahrscheinlichkeit.setWrapText(true);
        beschreibungWahrscheinlichkeit.setEditable(false);
        VBox wahrscheinlichkeitVBox = new VBox(wahrscheinlichkeitText, beschreibungWahrscheinlichkeit);
        wahrscheinlichkeitVBox.setAlignment(Pos.CENTER);

        HBox headerZeile = new HBox(iconCharakter, charakterEigenschaften, staerkeVBox, anzahlZieleTextVBox, wahrscheinlichkeitVBox);
        headerZeile.setPadding(new Insets(8));
        headerZeile.setMinWidth(100 + 6*breite + (2*headerZeile.getPadding().getLeft()));
        headerZeile.setAlignment(Pos.BOTTOM_LEFT);
        this.setTop(headerZeile);
    }
}
