package trainer;

import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import party.Party;
import party.PartyController;
import view.ViewController;

import java.util.ArrayList;

public class TrainerView extends BorderPane {
    private TrainerController trainerController;

    public TrainerView(ViewController viewController, TrainerController trainerController) {
        this.trainerController = trainerController;
        VBox center = new VBox();
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        this.setCenter(center);

        // Charakter auswahl !
        ArrayList<RadioButton> charaktaere = new ArrayList<>();
        // Einlesen aller Mitglieder in der Party ! dieParty[0] = Hauptcharakter
        SpielerCharakter[] dieParty = trainerController.getPartyController().getTeammitglieder();
        //TODO: Dummy Rausnehmen
        dieParty[1] = new SpielerCharakter("Test", "Magischer DD", "bklub");

        ToggleGroup charakterAuswahlToggleGroup = new ToggleGroup();
        for (int i = 0; i < dieParty.length; i++) {
            if (dieParty[i] != null) {
                final int counter = i;
                RadioButton btn = new RadioButton(dieParty[i].getName());
                //Button btn = new Button(dieParty[i].getName());
                //btn.setOnAction(event -> trainerController.setCharakterAuswahl(dieParty[charaktaere.indexOf(event.getTarget())]));
                //btn.selectedProperty().bind();
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
//        charakterAuswahlToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> charakterAuswahl(dieParty[charakterAuswahlToggleGroup.getSelectedToggle().]));
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(charaktaere);
    }

    private void charakterAuswahl(SpielerCharakter spielerCharakter) {
        this.trainerController.setCharakterAuswahl(spielerCharakter);
    }



}
