package hauptmenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HauptmenuView extends VBox {

    public HauptmenuView(
            HauptmenuController hauptmenuController
    ) {

        this.setBackground(new Background(new BackgroundImage(new Image("hintergruende/hauptmenue.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        Button neuesSpiel = new Button("Neues Spiel");
        neuesSpiel.setOnAction(event -> hauptmenuController.neuesSpiel());
        Button spielLaden = new Button("Spiel laden");
        spielLaden.setOnAction(event -> hauptmenuController.spielLaden());
        Button optionen = new Button("Optionen");
        optionen.setOnAction(event -> hauptmenuController.optionen());
        Button credits = new Button("Credits");
        credits.setOnAction(event -> hauptmenuController.credits());
        Button spielBeenden = new Button("Spiel Beenden");
        spielBeenden.setOnAction(event -> hauptmenuController.spielBeenden());


        this.getChildren().addAll(neuesSpiel, spielLaden, optionen, credits, spielBeenden);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);

    }
}
