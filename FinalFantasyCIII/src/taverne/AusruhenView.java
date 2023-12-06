package taverne;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.ViewController;

public class AusruhenView extends VBox {
    /**
     * Konstruktor für die AusruhenView-Klasse..
     * Die Klasse AusruhenView zeigt die grafische Benutzeroberfläche für das Ausruhen der Party.
     * Es werden HP und MP der Partymitglieder aufgefüllt. Mit einem Klick kommt man zurück in die Taverne.
     * @author Dennis
     * @since 05.12.2023
     * @param viewController Der ViewController für die Steuerung der Ansicht (zurückkommen).
     */
    public AusruhenView(ViewController viewController) {

        this.setBackground(new Background(new BackgroundImage(new Image("background/lagerfeuer.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        VBox vBox = new VBox();
        Label label = new Label("HP und MP der Party wieder aufgefüllt!");
        label.setTextFill(Color.GREEN);
        label.setStyle("-fx-font: 50px 'Lucida Calligraphy Italic'");
        label.setAlignment(Pos.TOP_CENTER);
        Label label2 = new Label("Klicken zum zurückkehren");
        label2.setTextFill(Color.WHITE);
        label2.setStyle("-fx-font: 50px 'Lucida Calligraphy Italic'");
        label2.setAlignment(Pos.BOTTOM_CENTER);
        label2.setPadding(new Insets(900,0,0,0));
        vBox.getChildren().addAll(label,label2);
        vBox.setAlignment(Pos.CENTER);

        this.getChildren().add(vBox);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> viewController.aktuelleNachHinten()
        );

    }

}
