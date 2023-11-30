package hauptmenu;

import hauptmenu.gamecontroller.GameController;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.ViewController;

public class OptionenView extends VBox {
    public OptionenView(HauptmenuController hauptmenuController, GameController gameController, ViewController viewController) {
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
        leicht.setToggleGroup(schwierigkeit);
        mittel.setToggleGroup(schwierigkeit);
        schwer.setToggleGroup(schwierigkeit);
        gameController.schwierigkeitsgradProperty().bind(Bindings.createStringBinding(() -> ((RadioButton)schwierigkeit.getSelectedToggle()).getText(),schwierigkeit.selectedToggleProperty()));
        gameController.schwierigkeitsgradProperty().addListener((observable, oldValue, newValue) -> {
        switch (newValue){
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
        });
        CheckBox hardCore = new CheckBox("Hardcore: ");
        hardCore.selectedProperty().bind(gameController.hardcoreProperty());
        VBox schwierigkeitsGrad = new VBox(schwierigkeitLbl, leicht, mittel, schwer, hardCore);
        Button zurueck = new Button("Zurück");
        zurueck.getStyleClass().add("hauptmenubutton");
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        VBox menuPunkte = new VBox(speichern, schwierigkeitsGrad, zurueck);

        this.getChildren().addAll(titel, menuPunkte);
    }
}
