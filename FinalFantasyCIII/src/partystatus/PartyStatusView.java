package partystatus;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PartyStatusView extends VBox {
    private PartyStatusController partyStatusController;
    private PartyStatusCharakterView[] chars;

    public PartyStatusView(PartyStatusController partyStatusController) {
        this.partyStatusController = partyStatusController;
        Label titel = new Label("PartyStatus");
        chars = new PartyStatusCharakterView[4];
        for (int i = 0; i < partyStatusController.getPartyMitglieder().length; i++) {
            chars[i] = new PartyStatusCharakterView(partyStatusController.getPartyMitglieder()[i]);
        }

    }

    public void anzeigeAktualiseren(){
        partyStatusController.getPartyMitglieder();
    }
}
