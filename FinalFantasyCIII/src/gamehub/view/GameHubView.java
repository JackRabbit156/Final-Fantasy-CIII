package gamehub.view;

import gamehub.GameHubController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.ViewController;

import java.io.File;


public class GameHubView extends Pane {
    private final Button btnViewKaempfen;
    private final Button btnViewPartyInventar;
    private GameHubController gameHubController;
    private ViewController viewController;
    private MediaPlayer musicSpieler;
    private final Button btnViewTaverne;
    private final Button btnViewSchmiede;
    private final Button btnViewHaendler;
    private final Button btnViewTrainer;


    public GameHubView(GameHubController gameHubController) {
        this.gameHubController = gameHubController;



        //--------------------------------------------------------------------------UIElemente
        Image hintergrundBild = new Image("background/gameHubBG.jpg");
        ImageView hintergrundBildAnsicht = new ImageView(hintergrundBild);
        Image imgSchmiede = new Image("gamehub/blacksmith.png");
        Image imgHaendler = new Image("gamehub/haendler.png");
        Image imgTrainer = new Image("gamehub/trainer.png");
        Image imgTaverne = new Image("gamehub/taverne.png");
        Image imgPartyInventar = new Image("gamehub/partyInventar.gif", 166, 144, true, true);
        Image imgKaempfen = new Image("gamehub/kampfAnzeige.png");

        btnViewSchmiede = new Button();
        btnViewSchmiede.setGraphic(new ImageView(imgSchmiede));
        btnViewSchmiede.getStyleClass().add("gamehubbuttonSchmiede");

        btnViewSchmiede.setMinSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());
        btnViewSchmiede.setMaxSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());


        btnViewHaendler = new Button();
        btnViewHaendler.setGraphic(new ImageView(imgHaendler));
        btnViewHaendler.getStyleClass().add("gamehubbuttonHaendler");
        btnViewHaendler.setMinSize(imgHaendler.getWidth(),imgHaendler.getHeight());
        btnViewHaendler.setMaxSize(imgHaendler.getWidth(),imgHaendler.getHeight());

        btnViewTrainer = new Button();
        btnViewTrainer.setGraphic(new ImageView(imgTrainer));
        btnViewTrainer.getStyleClass().add("gamehubbuttonTrainer");
        btnViewTrainer.setMinSize(imgTrainer.getWidth(),imgTrainer.getHeight());
        btnViewTrainer.setMaxSize(imgTrainer.getWidth(),imgTrainer.getHeight());

        btnViewTaverne = new Button();
        btnViewTaverne.setGraphic(new ImageView(imgTaverne));
        btnViewTaverne.getStyleClass().add("gameHubButtonTaverne");
        btnViewTaverne.setMinSize(imgTaverne.getWidth(),imgTaverne.getHeight());
        btnViewTaverne.setMaxSize(imgTaverne.getWidth(),imgTaverne.getHeight());

        btnViewPartyInventar = new Button();
        btnViewPartyInventar.setGraphic(new ImageView(imgPartyInventar));
        btnViewPartyInventar.getStyleClass().add("gameHubButtonPartyInventar");
        btnViewPartyInventar.setMinSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());
        btnViewPartyInventar.setMaxSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());

        btnViewKaempfen = new Button();
        btnViewKaempfen.setGraphic(new ImageView(imgKaempfen));
        btnViewKaempfen.getStyleClass().add("gameHubButtonKampf");
        btnViewKaempfen.setMinSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());
        btnViewKaempfen.setMaxSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());

        //--------------------------------------------------------------------------UIElemente formatieren
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);

        //--------------------------------------------------------------------------UIElemente Platzieren

        //Infos zur Platzierung für alle anderen, ein bild wird immer gesetzt von der oberen Ecke des Bildes 0,0 vond ort wird es gerender auf den hintergrund

        btnViewSchmiede.setLayoutX(60);
        btnViewSchmiede.setLayoutY(332);

        btnViewHaendler.setLayoutX(148);
        btnViewHaendler.setLayoutY(610);

        btnViewTaverne.setLayoutX(298);
        btnViewTaverne.setLayoutY(771);

        btnViewPartyInventar.setLayoutX(525);
        btnViewPartyInventar.setLayoutY(500);
        //Position X Y vom kampf X-X und Y-Y
        btnViewKaempfen.setLayoutX(1330);
        btnViewKaempfen.setLayoutY(750);

        btnViewTrainer.setLayoutX(623);
        btnViewTrainer.setLayoutY(842);

//        add(btnViewHaendler, 2, 2);
//        add(btnViewTrainer, 2, 4);
//        add(btnViewTaverne, 4, 0);
//        add(btnViewPartyInventar, 3, 2);
//        add(btnViewKaempfen, 4, 4);

        //--------------------------------------------------------------------------Logik nur für den GamehubView
        btnViewSchmiede.setOnMouseClicked(event -> gameHubController.schmiedeAnzeigen());
        btnViewHaendler.setOnMouseClicked(event -> gameHubController.haendlerAnzeigen());
        btnViewTrainer.setOnMouseClicked(event -> gameHubController.trainerAnzeigen());
        btnViewTaverne.setOnMouseClicked(event -> gameHubController.taverneAnzeigen());
        btnViewPartyInventar.setOnMouseClicked(event -> gameHubController.partyInventarAnzeigen());
        btnViewKaempfen.setOnMouseClicked(event -> gameHubController.kaempfenAnzeigen());
        getChildren().addAll(btnViewSchmiede, btnViewHaendler, btnViewTaverne, btnViewPartyInventar, btnViewKaempfen, btnViewTrainer);

    }

    public void ausloeserKampfHover() {
        btnViewKaempfen.getStyleClass().add("gameHubButtonKampf-hover");
    }

    public void entfernenKampfHover() {
        btnViewKaempfen.getStyleClass().remove("gameHubButtonKampf-hover");
    }

    public void ausloeserPartyHover() {
        btnViewPartyInventar.getStyleClass().add("gameHubButtonPartyInventar-hover");
    }

    public void entfernenPartyHover() {
        btnViewPartyInventar.getStyleClass().remove("gameHubButtonPartyInventar-hover");
    }
}
