package trainer;

import charakter.model.SpielerCharakter;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.ViewController;

import java.util.ArrayList;

/**
 * The type Trainer view.
 */
public class TrainerView extends BorderPane {
    private TrainerController trainerController;
    private ArrayList<RadioButton> charaktaere;
    private VBox center;

    /**
     * Instantiates a new Trainer view.
     *
     * @param viewController    the view controller
     * @param trainerController the trainer controller
     * @since 05.12.2023
     */
    public TrainerView(ViewController viewController, TrainerController trainerController) {
        this.trainerController = trainerController;
        center = new VBox();
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        center.setSpacing(10.0);
        this.setCenter(center);
        aktualisiereParty();
        // Charakter auswahl !

        // Styling
        this.getStyleClass().add("trainerStyle");

        center.setAlignment(Pos.CENTER);
        this.setMaxWidth(1536.0);
    }

    private void charakterAuswahl(SpielerCharakter spielerCharakter) {
        this.trainerController.setCharakterAuswahl(spielerCharakter);
    }

    /** Aktualisiert die Anzeige der Party. Einzelne Charaktaere in einem RadiButton
     * @author Thomas Maass
     *
     * @since 05.12.2023
     */

    public void aktualisiereParty(){
        center.getChildren().clear();
        charaktaere = new ArrayList<>();
        // Einlesen aller Mitglieder in der Party ! dieParty[0] = Hauptcharakter
        SpielerCharakter[] dieParty = trainerController.getPartyController().getTeammitglieder();

        ToggleGroup charakterAuswahlToggleGroup = new ToggleGroup();
        for (int i = 0; i < dieParty.length; i++) {
            if (dieParty[i] != null) {
                final int counter = i;
                RadioButton btn = new RadioButton(dieParty[i].getName());
                btn.getStyleClass().add("trainerAttributeGrossesLabel");
                charakterAuswahlToggleGroup.getToggles().add(btn);
                btn.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        charakterAuswahl(dieParty[counter]);
                    }
                });
                charaktaere.add(btn);
            }
        }
        charakterAuswahlToggleGroup.selectToggle(charaktaere.get(0));

        center.getChildren().addAll(charaktaere);
    }

}
