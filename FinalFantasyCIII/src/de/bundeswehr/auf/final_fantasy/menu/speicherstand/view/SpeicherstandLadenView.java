package de.bundeswehr.auf.final_fantasy.menu.speicherstand.view;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.Speicherstand;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpeicherstandLadenView extends BorderPane {

    public static final Pattern PATTERN = Pattern.compile(".*(\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}).*");

    public SpeicherstandLadenView(ViewController viewController, SpeicherstandController speicherstandController,
                                  HauptmenuController hauptmenuController) {
        Text titel = new Text("Spiel Laden");
        HBox top = new HBox(titel);
        top.setAlignment(Pos.CENTER);
        this.setTop(top);

        VBox center;

        if (speicherstandController.istSpeicherstandVorhanden()) {
            ListView<String> speicherstaende = new ListView<>(speicherstandController.speicherstaendeAbrufen());
            speicherstaende.setMaxSize(600, 200);
            speicherstaende.getSelectionModel().selectLast();
            speicherstaende.getStyleClass().add("spielLadenlv");
            speicherstaende.scrollTo(speicherstaende.getItems().size());
            MenuItem loeschen = new MenuItem("Löschen");
            loeschen.setOnAction(event -> {
                if (speicherstaende.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                try {
                    speicherstandController.entferneSpeicherstand(getSelectedSpeicherstandZeit(speicherstaende));
                    speicherstaende.getItems().remove(speicherstaende.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            speicherstaende.setContextMenu(new ContextMenu(loeschen));

            Button btnSpielstandLaden = new Button("Spielstand laden");
            btnSpielstandLaden.getStyleClass().add("hauptmenubutton");
            btnSpielstandLaden.setOnAction(event -> {
                if (speicherstaende.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                Speicherstand speicherstand = speicherstandController.speicherstandLaden(getSelectedSpeicherstandZeit(speicherstaende));
                PartyController partyController = new PartyController(speicherstand.getParty());
                if (hauptmenuController.getGameHubController() != null) {
                    hauptmenuController.getGameHubController().destroy();
                }
                new GameHubController(
                        new Game(speicherstand.getSchwierigkeitsgrad(),
                                speicherstand.isHardcore(), partyController),
                        partyController,
                        new StatistikController(speicherstand.getStatistik()),
                        hauptmenuController,
                        speicherstandController,
                        viewController);
            });
            speicherstaende.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    btnSpielstandLaden.getOnAction().handle(null);
                }
            });

            Button btnAbbrechen = new Button("Abbrechen");
            btnAbbrechen.getStyleClass().add("hauptmenubutton");
            btnAbbrechen.setOnAction(event -> viewController.aktuelleNachHinten());

            center = new VBox(speicherstaende, btnSpielstandLaden, btnAbbrechen);
        }
        else {
            Label nutzerHinweis = new Label("Keine Speicherstände vorhanden");
            nutzerHinweis.setTextFill(Color.WHITE);
            nutzerHinweis.setFont(new Font("Lucida Calligraphy Italic", 50.0));

            Button zurueck = new Button("Zurück");
            zurueck.getStyleClass().add("hauptmenubutton");
            zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
            zurueck.setAlignment(Pos.CENTER);

            center = new VBox(nutzerHinweis, zurueck);
        }

        center.setSpacing(20);
        center.setAlignment(Pos.CENTER);
        this.setCenter(center);

        this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

    private String getSelectedSpeicherstandZeit(ListView<String> speicherstaende) {
        Matcher matcher = PATTERN.matcher(speicherstaende.getSelectionModel().getSelectedItem());
        if (!matcher.matches()) {
            throw new RuntimeException("Ungültiger Eintrag: " + speicherstaende.getSelectionModel().getSelectedItem());
        }
        return matcher.group(1);
    }

}
