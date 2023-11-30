package hauptmenu;

import hauptmenu.gamecontroller.GameController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionenView extends VBox {
    public OptionenView(HauptmenuController hauptmenuController, GameController gameController) {
        Label titel = new Label("Optionen");
        titel.setTextFill(Color.WHITE);
        titel.setFont(new Font("Lucida Calligraphy Italic", 80.0));

        Button speichern = new Button("Speichern");
        speichern.getStyleClass().add("hauptmenubutton");
        speichern.setOnAction(event -> hauptmenuController.speichern());
        ToggleGroup schwierigkeit = new ToggleGroup();
        RadioButton leicht = new RadioButton("Leicht");
        RadioButton mittel = new RadioButton("Mittel");
        RadioButton schwer = new RadioButton("Schwer");
        switch (gameController.getSchwierigkeitsgrad()){
            case "Leicht":
                leicht.setSelected(true);
                break;
            case "Mittel":
                mittel.setSelected(true);
                break;
            case "Schwer":
                schwer.setSelected(true);
                break;
            default: leicht.setSelected(true);
            break;
        }

        VBox menuPunkte = new VBox();
    }
}
