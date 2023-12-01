package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

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
        this.setMaxSize(250.0, 500.0);
        this.setSpacing(30.0);
    }

}
