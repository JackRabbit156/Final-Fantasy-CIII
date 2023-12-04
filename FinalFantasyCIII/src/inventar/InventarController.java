package inventar;

import charakter.model.SpielerCharakter;
import inventar.view.InventarView;
import javafx.scene.control.Button;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.ArrayList;
import java.util.List;

public class InventarController {
    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;
    private InventarView inventarView;
    private AnsichtsTyp ansichtsTyp = AnsichtsTyp.MIT_OVERLAY;
    private final List<Button> lstInventoryButtons;

    public InventarController(PartyController partyController, ViewController viewController) {
        this.viewController = viewController;
        this.partyController = partyController;
        this.aktiveParty = fuellePartyList();
        this.inventarView = new InventarView(partyController,viewController,this.aktiveParty);
        lstInventoryButtons = new ArrayList<>();


        //--UI Elemente Buttons für die Button Liste
        Button btnInventarOeffnen = new Button("Inventar Öffnen");
        Button btnAusruestungAendern = new Button("Ausrüstung Wechseln");
        Button btnZuerueckZum = new Button("Zurück");


        btnInventarOeffnen.setOnMouseClicked(event -> inventarView.inventarOeffnen());
        btnAusruestungAendern.setOnMouseClicked(event -> inventarView.ausruestungAendern());
        btnZuerueckZum.setOnMouseClicked(event -> viewController.aktuelleNachHinten());

        lstInventoryButtons.add(btnInventarOeffnen);
        lstInventoryButtons.add(btnAusruestungAendern);
        lstInventoryButtons.add(btnZuerueckZum);


    }

    private ArrayList<SpielerCharakter> fuellePartyList() {
        ArrayList<SpielerCharakter> auffang = new ArrayList<>();

        auffang.add(partyController.getParty().getHauptCharakter());


        SpielerCharakter[] nebencharArray = this.partyController.getParty().getNebenCharakter();
        for (SpielerCharakter nebencharakter : nebencharArray) {
            if (nebencharakter != null) {
                auffang.add(nebencharakter);
            }
        }
        return auffang;
    }

    public void spielerinventarAnzeige() {
        viewController.anmelden(inventarView,lstInventoryButtons,ansichtsTyp);
    }
}
