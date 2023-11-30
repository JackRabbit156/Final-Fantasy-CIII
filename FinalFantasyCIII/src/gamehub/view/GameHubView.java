package gamehub.view;

import gamehub.GameHubController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import view.ViewController;

import java.util.ArrayList;
import java.util.List;


public class GameHubView extends GridPane {
    private GameHubController gameHubController;
    private ViewController viewController;


    public GameHubView(GameHubController gameHubController) {
        this.gameHubController = gameHubController;


        //--------------------------------------------------------------------------UIElemente
        Region hintergrund = new Region();
        Image hintergrundBild = new Image("background/gameHubBG.jpg");
        Image imgSchmiede = new Image("gamehub/placeholder.png");
        Image imgHaendler = new Image("gamehub/placeholder.png");
        Image imgTrainer = new Image("gamehub/placeholder.png");
        Image imgTaverne = new Image("gamehub/placeholder.png");
        Image imgPartyInventar = new Image("gamehub/placeholder.png");
        Image imgKaempfen = new Image("gamehub/placeholder.png");

        Button btnViewSchmiede = new Button("Schmiede");
        btnViewSchmiede.setGraphic(new ImageView(imgSchmiede));
        btnViewSchmiede.getStyleClass().add("gamehubbutton");
//        btnViewSchmiede.setMinSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());
//        btnViewSchmiede.setMaxSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());


        Button btnViewHaendler = new Button("Haendler");
        btnViewHaendler.setGraphic(new ImageView(imgHaendler));
        btnViewHaendler.getStyleClass().add("gamehubbutton");
//        btnViewHaendler.setMinSize(imgHaendler.getWidth(),imgHaendler.getHeight());
//        btnViewHaendler.setMaxSize(imgHaendler.getWidth(),imgHaendler.getHeight());

        Button btnViewTrainer = new Button("Trainer");
        btnViewTrainer.setGraphic(new ImageView(imgTrainer));
        btnViewTrainer.getStyleClass().add("gamehubbutton");
//        btnViewTrainer.setMinSize(imgTrainer.getWidth(),imgTrainer.getHeight());
//        btnViewTrainer.setMaxSize(imgTrainer.getWidth(),imgTrainer.getHeight());

        Button btnViewTaverne = new Button("Taverne");
        btnViewTaverne.setGraphic(new ImageView(imgTaverne));
        btnViewTaverne.getStyleClass().add("gamehubbutton");
//        btnViewTaverne.setMinSize(imgTaverne.getWidth(),imgTaverne.getHeight());
//        btnViewTaverne.setMaxSize(imgTaverne.getWidth(),imgTaverne.getHeight());

        Button btnViewPartyInventar = new Button("Party Inventar");
        btnViewPartyInventar.setGraphic(new ImageView(imgPartyInventar));
        btnViewPartyInventar.getStyleClass().add("gamehubbutton");
//        btnViewPartyInventar.setMinSize(imgPartyInventar.getWidth(),imgPartyInventar.getHeight());
//        btnViewPartyInventar.setMaxSize(imgPartyInventar.getWidth(),imgPartyInventar.getHeight());

        Button btnViewKaempfen = new Button("Kämpfen");
        btnViewKaempfen.setGraphic(new ImageView(imgKaempfen));
        btnViewKaempfen.getStyleClass().add("gamehubbutton");
        btnViewKaempfen.setMinSize(imgKaempfen.getWidth(),imgKaempfen.getHeight());
        btnViewKaempfen.setMaxSize(imgKaempfen.getWidth(),imgKaempfen.getHeight());

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

        // viewController.anmelden(this, lstBtnhauptmenu, MIT_OVERLAY); TODO--Sobald Funktionsfähig auskommentieren viewController.anmelden


        //--------------------------------------------------------------------------UIElemente Platzieren
        hintergrund.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundImage(
                hintergrundBild,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundPosition.DEFAULT,
                new javafx.scene.layout.BackgroundSize(1.0, 1.0, true, true, false, false)
        )));
        add(hintergrund, 0, 0, GridPane.REMAINING, GridPane.REMAINING);
        add(btnViewSchmiede, 2, 0);
        add(btnViewHaendler, 2, 2);
        add(btnViewTrainer, 2, 4);
        add(btnViewTaverne, 4, 0);
        add(btnViewPartyInventar, 3, 2);
        add(btnViewKaempfen, 4, 4);


        btnViewSchmiede.setOnMouseClicked(event -> gameHubController.schmiedeAnzeigen());
        btnViewHaendler.setOnMouseClicked(event -> gameHubController.haendlerAnzeigen());
        btnViewTrainer.setOnMouseClicked(event -> gameHubController.trainerAnzeigen());
        btnViewTaverne.setOnMouseClicked(event -> gameHubController.taverneAnzeigen());
        btnViewPartyInventar.setOnMouseClicked(event -> gameHubController.partyInventarAnzeigen());
        btnViewKaempfen.setOnMouseClicked(event -> gameHubController.kaempfenAnzeigen());


    }
}
