package partystatus;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PartyStatusView extends VBox {
    private PartyStatusController partyStatusController;
    private PartyStatusCharakterView[] chars;

    public PartyStatusView(PartyStatusController partyStatusController) {
        this.partyStatusController = partyStatusController;
        chars = new PartyStatusCharakterView[4];
        for (int i = 0; i < partyStatusController.getPartyMitglieder().length; i++) {
            chars[i] = new PartyStatusCharakterView(partyStatusController.getPartyMitglieder()[i]);
        }
        this.getChildren().addAll(chars);
        this.getStyleClass().add("partystatusContainer");
    }

    /**
     * Aktualisiert alle Anzeigen welche sich auf Charakter-Daten beziehen
     * @author Nick
     * @since 03.12.2023
     */
    public void anzeigeAktualiseren(){
        for (int i = 0; i < partyStatusController.getPartyMitglieder().length; i++) {
            chars[i].ansichtAktualisieren(partyStatusController.getPartyMitglieder()[i]);
        }
    }
}
