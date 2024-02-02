package de.bundeswehr.auf.final_fantasy.menu.speicherstand.view;

import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;

public class SpeicherstandSpeichernView extends BorderPane {

    private ViewController viewController;

    public SpeicherstandSpeichernView(SpeicherstandController speicherstandController) {
        this.viewController = speicherstandController.getViewController();

        this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        Text titel = new Text("Spiel Speichern");
        Label nutzerHinweis = new Label("Spielstand erfolgreich gespeichert!");
        nutzerHinweis.setTextFill(Color.WHITE);
        nutzerHinweis.setFont(new Font("Lucida Calligraphy Italic", 50.0));
        Button zurueck = new Button("Zurück");
        zurueck.getStyleClass().add("hauptmenubutton");
        zurueck.getStyleClass().add("OK");
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
