package de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand.view;

import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.controller.GameController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand.Speicherstand;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand.SpeicherstandController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import de.bundeswehr.auf.final_fantasy.view.ViewController;

public class SpeicherstandLadenView extends BorderPane {

    private ViewController viewController;
    private SpeicherstandController speicherstandController;
    private boolean istSpeicherstandVorhanden;

    public SpeicherstandLadenView(ViewController viewController, SpeicherstandController speicherstandController,
                                  HauptmenuController hauptmenuController) {
        this.viewController = viewController;
        this.speicherstandController = speicherstandController;

        istSpeicherstandVorhanden = speicherstandController.istSpeicherstandVorhanden();
        if (istSpeicherstandVorhanden) {

            // Center
            ObservableList<String> olSpeicherstaendeEintraege = speicherstandController.speicherstaendeAbrufen();
            ListView<String> lvSpeicherstaende = new ListView<>(olSpeicherstaendeEintraege);
            lvSpeicherstaende.setMaxSize(600, 200);
            lvSpeicherstaende.getSelectionModel().selectFirst();
//			lvSpeicherstaende.setStyle(" -fx-control-inner-background: #F4A460;"
//					+ " -fx-control-inner-background-alt: derive(-fx-control-inner-background, 20%);"
//					+ " -fx-font-size: 20px; -fx-font-family: 'SketchFlow Print';");
            lvSpeicherstaende.getStyleClass().add("spielLadenlv");
            Text titel = new Text("Spiel Laden");
            Button btnSpielstandLaden = new Button("Spielstand laden");
            Button btnAbbrechen = new Button("Abbrechen");
            btnAbbrechen.getStyleClass().add("hauptmenubutton");
            btnSpielstandLaden.getStyleClass().add("hauptmenubutton");
            HBox top = new HBox(titel);
            top.setAlignment(Pos.CENTER);
            btnAbbrechen.setOnMouseClicked(event -> {
                viewController.aktuelleNachHinten();
            });

            btnSpielstandLaden.setOnMouseClicked(event -> {
                String auswahlString = lvSpeicherstaende.getSelectionModel().getSelectedItem();
                String[] auswahlSplit = auswahlString.split(" | ");
                Speicherstand geladenerSpeicherstand = speicherstandController
                        .speicherstandLaden(auswahlSplit[0].trim());
                PartyController newParty = new PartyController(geladenerSpeicherstand.getParty());
                if (hauptmenuController.getGameHubController() != null) {
                    hauptmenuController.getGameHubController().destroy();
                }
                new GameHubController(
                        new GameController(geladenerSpeicherstand.getSchwierigkeitsgrad(),
                                geladenerSpeicherstand.isHardcore(), newParty),
                        newParty, new StatistikController(geladenerSpeicherstand.getStatistik()), hauptmenuController,
                        speicherstandController, viewController);
            });
            VBox center = new VBox(lvSpeicherstaende, btnSpielstandLaden, btnAbbrechen);

            // Haupt-Node
            this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    new BackgroundSize(1920, 1080, false, false, false, false))));
            center.setSpacing(20);
            center.setAlignment(Pos.CENTER);
            this.setTop(top);
            this.setCenter(center);

//			Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
//			partyController = new PartyController(auswahl.getParty());
//			gameController = new GameController(auswahl.getSchwierigkeitsgrad(), auswahl.isHardcore(), partyController);
//			statistikController = new StatistikController(auswahl.getStatistik());
        }
        else {
            this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    new BackgroundSize(1920, 1080, false, false, false, false))));
            Text titel = new Text("Spiel Laden");
            Label nutzerHinweis = new Label("Keine Speicherstände vorhanden");
            nutzerHinweis.setTextFill(Color.WHITE);
            nutzerHinweis.setFont(new Font("Lucida Calligraphy Italic", 50.0));
            Button zurueck = new Button("Zurück");
            zurueck.getStyleClass().add("hauptmenubutton");
            zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
            zurueck.setAlignment(Pos.CENTER);

            HBox top = new HBox(titel);
            top.setAlignment(Pos.CENTER);
            VBox center = new VBox(nutzerHinweis, zurueck);
            center.setSpacing(20);
            center.setAlignment(Pos.CENTER);
            this.setTop(top);
            this.setCenter(center);
        }
    }
}
