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
        VBox charBox = new VBox();
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(30.0);
        charBox.setMinSize(384,500);
        this.getChildren().add(charBox);
        this.getChildren().add(buttonBox);

        if (buttons != null) {
            for (Button button : buttons) {
                button.getStyleClass().add("buttonMenueLeisteRechts");
                buttonBox.getChildren().add(button);

            }
        }
        Button optionen = new Button("Optionen");
        optionen.getStyleClass().add("buttonMenueLeisteRechts");
        optionen.setOnAction(event -> viewController.optionenAnzeigen());
        buttonBox.getChildren().add(optionen);
        this.setMaxSize(384.0, 1080.0);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
