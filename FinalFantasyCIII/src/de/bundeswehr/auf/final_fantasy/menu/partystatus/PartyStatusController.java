package de.bundeswehr.auf.final_fantasy.menu.partystatus;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.inventar.controller.InventarController;
import javafx.scene.control.Button;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.view.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.view.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.partystatus.view.PartyStatusView;

import java.util.*;

public class PartyStatusController {
    private final PartyController partyController;
    private PartyStatusView partyStatusView;
    private ViewController viewController;
    private InventarController inventarController;
    private ArrayList<Button> buttons;

    /**
     * Generiert die Knöpfe für die Leiste und die Partystatusansicht
     * @param partyController partyController
     * @param viewController viewController
     * @param inventarController inventarController
     * @author Nick
     * @since 06.12.2023
     */
    public PartyStatusController(PartyController partyController, ViewController viewController, InventarController inventarController) {
        this.inventarController = inventarController;
        this.partyController = partyController;
        partyStatusView = new PartyStatusView(this);
        this.viewController = viewController;
        buttons = new ArrayList<>();
        Button zumInventar = new Button("Zum Inventar");
        zumInventar.setOnAction(event -> inventarController.spielerinventarAnzeige());
        buttons.add(zumInventar);
        Button zurueck = new Button("Zurück");
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        buttons.add(zurueck);
    }


    /**
     * Zeigt die Partyübersichtsansicht an
     * @author Nick
     * @since 03.12.2023
     */
    public void partyStatusAnzeigen() {
        partyStatusView.anzeigeAktualiseren();
        viewController.anmelden(partyStatusView, buttons, AnsichtsTyp.MIT_OVERLAY);
    }

    public SpielerCharakter[] getPartyMitglieder() {
        return partyController.getTeammitglieder();
    }

}
