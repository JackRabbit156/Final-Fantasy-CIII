package de.bundeswehr.auf.final_fantasy.menu.hauptmenu.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;

/**
 * Die Darstellung der Credits vom Hauptmenue
 * @author Thomas Maass
 * @since 05.12.2023
 */
public class CreditsView extends VBox {

    /**
     * Instantiates a new Credits de.bundeswehr.auf.final_fantasy.menu.overlay.view.
     *
     * @param viewController the de.bundeswehr.auf.final_fantasy.menu.overlay.view de.bundeswehr.auf.final_fantasy.controller
     */
    public CreditsView(ViewController viewController) {
        this.setBackground(new Background(new BackgroundImage(new Image("/background/creditsbackground.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));
        Label hinweis = new Label("Klicken zum fortfahren");
        hinweis.setTextFill(Color.WHITE);
        hinweis.setFont(new Font(Font.getDefault().getName(), 50.0));

        this.setSpacing(600.0);
        this.setPadding(new Insets(40.0));
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(hinweis);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> viewController.aktuelleNachHinten());
    }

}
