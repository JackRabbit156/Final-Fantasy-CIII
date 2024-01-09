package de.bundeswehr.auf.final_fantasy.menu.partystatus.view;

import javafx.scene.layout.VBox;
import de.bundeswehr.auf.final_fantasy.menu.partystatus.PartyStatusController;

public class PartyStatusView extends VBox {
    private PartyStatusController partyStatusController;
    private PartyStatusCharakterView[] chars;

    /**
     * Generiert die Partystatus ansicht und je Söldner eine Zeile
     * @param partyStatusController partystatusController
     * @author Nick
     * @since 06.12.2023
     */
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
