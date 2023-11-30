package gamehub.view;

import gamehub.GameHubController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import view.ViewController;

import java.util.ArrayList;
import java.util.List;

import static view.AnsichtsTyp.MIT_OVERLAY;


public class GameHubView extends GridPane {
    private GameHubController gameHubController;
    private ViewController viewController;


    public GameHubView(GameHubController gameHubController) {
        this.gameHubController = gameHubController;


        //--------------------------------------------------------------------------UIElemente
        Button btnViewSchmiede = new Button();
        Button btnViewHaendler = new Button();
        Button btnViewTrainer = new Button();
        Button btnViewTaverne = new Button();
        Button btnViewPartyInventar = new Button();
        Button btnViewKaempfen = new Button();

        //---- UI Elemente für die Button List

        Button btnSchmiede = new Button("Schmiede");
        Button btnHaendler = new Button("Händler");
        Button btnTrainer = new Button("Trainer");
        Button btnTaverne = new Button("Taverne");
        Button btnPartyInventar = new Button("Party Inventar");
        Button btnKaempfen = new Button("Kämpfen");

        List<Button> lstBtnhauptmenu = new ArrayList<>();
        lstBtnhauptmenu.add(btnHaendler);
        lstBtnhauptmenu.add(btnSchmiede);
        lstBtnhauptmenu.add(btnTaverne);
        lstBtnhauptmenu.add(btnTrainer);
        lstBtnhauptmenu.add(btnPartyInventar);
        lstBtnhauptmenu.add(btnKaempfen);

        viewController.anmelden(this, lstBtnhauptmenu, MIT_OVERLAY);

        //--------------------------------------------------------------------------UIElemente Platzieren
    }
}
