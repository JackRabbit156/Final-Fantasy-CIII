package de.bundeswehr.auf.final_fantasy.menu.speicherstand.view;

import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.Speicherstand;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
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
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpeicherstandLadenView extends BorderPane {

    public static final Pattern PATTERN = Pattern.compile(".*(\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}).*");

    public SpeicherstandLadenView(ViewController viewController, SpeicherstandController speicherstandController,
                                  HauptmenuController hauptmenuController) {
        boolean istSpeicherstandVorhanden = speicherstandController.istSpeicherstandVorhanden();
        if (istSpeicherstandVorhanden) {
            ListView<String> speicherstaende = new ListView<>(speicherstandController.speicherstaendeAbrufen());
            speicherstaende.setMaxSize(600, 200);
            speicherstaende.getSelectionModel().selectFirst();
            speicherstaende.getStyleClass().add("spielLadenlv");

            Text titel = new Text("Spiel Laden");
            Button btnSpielstandLaden = new Button("Spielstand laden");
            Button btnAbbrechen = new Button("Abbrechen");
            btnAbbrechen.getStyleClass().add("hauptmenubutton");
            btnSpielstandLaden.getStyleClass().add("hauptmenubutton");
            HBox top = new HBox(titel);
            top.setAlignment(Pos.CENTER);
            btnAbbrechen.setOnAction(event -> viewController.aktuelleNachHinten());

            btnSpielstandLaden.setOnAction(event -> {
                Matcher matcher = PATTERN.matcher(speicherstaende.getSelectionModel().getSelectedItem());
                if (!matcher.matches()) {
                    throw new RuntimeException("Ungültiger Eintrag: " + speicherstaende.getSelectionModel().getSelectedItem());
                }
                Speicherstand geladenerSpeicherstand = speicherstandController.speicherstandLaden(matcher.group(1));
                PartyController newParty = new PartyController(geladenerSpeicherstand.getParty());
                if (hauptmenuController.getGameHubController() != null) {
                    hauptmenuController.getGameHubController().destroy();
                }
                new GameHubController(
                        new Game(geladenerSpeicherstand.getSchwierigkeitsgrad(),
                                geladenerSpeicherstand.isHardcore(), newParty),
                        newParty, new StatistikController(geladenerSpeicherstand.getStatistik()), hauptmenuController,
                        speicherstandController, viewController);
            });
            VBox center = new VBox(speicherstaende, btnSpielstandLaden, btnAbbrechen);

            speicherstaende.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    btnSpielstandLaden.getOnAction().handle(null);
                }
            });

            // Haupt-Node
            this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    new BackgroundSize(1920, 1080, false, false, false, false))));
            center.setSpacing(20);
            center.setAlignment(Pos.CENTER);
            this.setTop(top);
            this.setCenter(center);
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
