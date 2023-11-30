package hauptmenu;

import hauptmenu.gamecontroller.GameController;
import javafx.scene.control.*;
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
        Label schwierigkeitLbl = new Label("Schwierigkeitsgrad:");
        RadioButton leicht = new RadioButton("Leicht");
        RadioButton mittel = new RadioButton("Mittel");
        RadioButton schwer = new RadioButton("Schwer");
        ToggleGroup schwierigkeit = new ToggleGroup();
        //TODO
        leicht.setToggleGroup(schwierigkeit);
        mittel.setToggleGroup(schwierigkeit);
        schwer.setToggleGroup(schwierigkeit);
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
        CheckBox hardCore = new CheckBox("Hardcore: ");
        hardCore.selectedProperty().bind(gameController.getHardcoreProperty());
        VBox schwierigkeitsGrad = new VBox(schwierigkeitLbl, leicht, mittel, schwer);

        VBox menuPunkte = new VBox();
    }
}
