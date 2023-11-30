package trainer;

import charakter.model.SpielerCharakter;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import party.PartyController;

import java.util.ArrayList;

public class TrainerCharakterAuswahlView extends VBox {
    public TrainerCharakterAuswahlView(TrainerController trainerController, PartyController partyController) {
        ArrayList<Button> charaktaere = new ArrayList<>();
        // Einlesen aller Mitglieder in der Party ! dieParty[0] = Hauptcharakter
        SpielerCharakter[] dieParty = trainerController.getPartyController().getTeammitglieder();

        for (int i=0;i<dieParty.length;i++){
            if (dieParty[i] != null) {
                Button btn = new Button(dieParty[i].getName());
                btn.setOnAction(event -> trainerController.setCharakterAuswahl(dieParty[charaktaere.indexOf(event.getTarget())]));
                charaktaere.add(btn);
            }
        }
        this.getChildren().addAll(charaktaere);

    }
}
