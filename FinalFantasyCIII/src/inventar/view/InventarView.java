package inventar.view;

import charakter.model.SpielerCharakter;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import party.PartyController;
import view.ViewController;

import java.util.ArrayList;

public class InventarView extends Pane {
    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public InventarView(PartyController partyController, ViewController viewController, ArrayList<SpielerCharakter> aktiveParty) {
        this.partyController = partyController;
        this.viewController = viewController;
        this.aktiveParty = aktiveParty;
        Image hintergrundBild = new Image("background/inventoryBG.png");
        ImageView hintergrundBildAnsicht = new ImageView(hintergrundBild);
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
    }

    public void inventarOeffnen() {
        getChildren().clear();
        TabPane inventarListe = new TabPane();

    }

    public void ausruestungAendern() {
        getChildren().clear();
    }
}
