package partystatus;

import charakter.model.SpielerCharakter;
import javafx.scene.control.Button;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.*;

public class PartyStatusController {
    private final PartyController partyController;
    private PartyStatusView partyStatusView;
    private ViewController viewController;
    private ArrayList<Button> buttons;

    public PartyStatusController(PartyController partyController, ViewController viewController) {
        this.partyController = partyController;
        partyStatusView = new PartyStatusView(this);
        this.viewController = viewController;
        buttons = new ArrayList<>();
        Button zumInventar = new Button("Zum Inventar(DUMMY)");
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
