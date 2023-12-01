package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class OverlayRechts extends VBox {

    public OverlayRechts(List<Button> buttons, ViewController viewController) {
        if(buttons != null) {
        for (Button button : buttons) {
            this.getChildren().add(button);
        }
        Button optionen = new Button("Optionen");
        optionen.setOnAction(event -> viewController.optionenAnzeigen());
        this.getChildren().add(optionen);
    }
        this.setMaxSize(384.0, 1050.0);
        this.setSpacing(30.0);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
