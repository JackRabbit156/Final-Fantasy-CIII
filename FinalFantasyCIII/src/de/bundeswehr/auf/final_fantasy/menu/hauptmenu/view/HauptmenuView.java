package de.bundeswehr.auf.final_fantasy.menu.hauptmenu.view;

import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HauptmenuView extends VBox {

    /**
     * Konstruktor für die HauptmenuView-Klasse.
     * Die Klasse HauptmenuView zeigt die grafische Benutzeroberfläche für das Hauptmenü an.
     *
     * @param hauptmenuController Der Controller für das Hauptmenü, zum aufrufen der Methoden "Neues Spiel", "Spiel laden", "Credits", "Spiel beenden", und zum prüfen der spielVorhandenProperty (zurück-Button)
     * @param viewController      Der Controller wird zum AktuelleAnsichtNachHinten-schieben benötigt
     * @author Dennis
     * @since 01.12.2023
     */
    public HauptmenuView(HauptmenuController hauptmenuController, ViewController viewController) {
        this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        Button neuesSpiel = new Button("Neues Spiel");
        neuesSpiel.setOnAction(event -> hauptmenuController.neuesSpiel());
        neuesSpiel.getStyleClass().add("hauptmenubutton");

        Button spielLaden = new Button("Spiel laden");
        spielLaden.setOnAction(event -> hauptmenuController.spielLaden());
        spielLaden.getStyleClass().add("hauptmenubutton");

        Button hilfe = new Button("Hilfe");
        hilfe.setOnAction(event -> hauptmenuController.hilfe());
        hilfe.getStyleClass().add("hauptmenubutton");

        Button credits = new Button("Credits");
        credits.setOnAction(event -> hauptmenuController.credits());
        credits.getStyleClass().add("hauptmenubutton");

        Button spielBeenden = new Button("Spiel Beenden");
        spielBeenden.setOnAction(event -> hauptmenuController.spielBeenden());
        spielBeenden.getStyleClass().add("hauptmenubutton");

        Button zurueck = new Button("Zurück");
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        zurueck.visibleProperty().bind(hauptmenuController.spielVorhandenProperty());
        zurueck.getStyleClass().add("hauptmenubutton");

        this.getChildren().addAll(neuesSpiel, spielLaden, hilfe, credits, spielBeenden, zurueck);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);
    }

}
