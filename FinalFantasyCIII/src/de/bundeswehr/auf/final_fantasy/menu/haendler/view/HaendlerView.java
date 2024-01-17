package de.bundeswehr.auf.final_fantasy.menu.haendler.view;


import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class HaendlerView extends BorderPane {

    /**
     * Der Konstruktor der HaendlerView
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public HaendlerView() {
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

}
