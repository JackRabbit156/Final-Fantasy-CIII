package hauptmenu;

import hauptmenu.gamecontroller.GameController;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.AnsichtsTyp;
import view.ViewController;

import javax.management.monitor.GaugeMonitor;

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

        gameController.schwierigkeitsgradProperty().bind(Bindings.createStringBinding(() -> ((RadioButton)schwierigkeit.getSelectedToggle()).getText(),schwierigkeit.selectedToggleProperty()));
        CheckBox hardCore = new CheckBox("Hardcore: ");
        hardCore.selectedProperty().bindBidirectional(gameController.hardcoreProperty());
        VBox schwierigkeitsGrad = new VBox(schwierigkeitLbl, leicht, mittel, schwer, hardCore);
        Button hauptmenu = new Button("Hauptmenu");
        hauptmenu.setOnAction(event -> viewController.anmelden(viewController.getHauptmenuView(), null, AnsichtsTyp.OHNE_OVERLAY));
        hauptmenu.getStyleClass().add("hauptmenubutton");
        Button zurueck = new Button("Zurück");
        zurueck.getStyleClass().add("hauptmenubutton");
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        VBox menuPunkte = new VBox(speichern, schwierigkeitsGrad,hauptmenu, zurueck);
        this.getChildren().addAll(titel, menuPunkte);
    }
}
