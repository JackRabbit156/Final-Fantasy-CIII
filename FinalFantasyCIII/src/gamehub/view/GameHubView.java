package gamehub.view;

import gamehub.GameHubController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.ArrayList;
import java.util.List;


public class GameHubView extends Pane {
    private GameHubController gameHubController;
    private ViewController viewController;
    private final Button btnViewKaempfen;
    private final Button btnViewPartyInventar;


    public GameHubView(GameHubController gameHubController) {
        this.gameHubController = gameHubController;


        //--------------------------------------------------------------------------UIElemente
        Region hintergrund = new Region();
        Image hintergrundBild = new Image("background/gameHubBG.jpg");
        ImageView hintergrundBildAnsicht = new ImageView(hintergrundBild);
        Image imgSchmiede = new Image("gamehub/placeholder.png");
        Image imgHaendler = new Image("gamehub/placeholder.png");
        Image imgTrainer = new Image("gamehub/placeholder.png");
        Image imgTaverne = new Image("gamehub/placeholder.png");
        Image imgPartyInventar = new Image("gamehub/partyInventar.gif",166,144,true,true);
        Image imgKaempfen = new Image("gamehub/kampfAnzeige.png");

        Button btnViewSchmiede = new Button("Schmiede");
        btnViewSchmiede.setGraphic(new ImageView(imgSchmiede));
        btnViewSchmiede.getStyleClass().add("gamehubbutton");
//        btnViewSchmiede.setMinSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());
//        btnViewSchmiede.setMaxSize(imgSchmiede.getWidth(),imgSchmiede.getHeight());


        Button btnViewHaendler = new Button("Händler");
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

        btnViewPartyInventar = new Button();
        btnViewPartyInventar.setGraphic(new ImageView(imgPartyInventar));
        btnViewPartyInventar.getStyleClass().add("gameHubButtonPartyInventar");
        btnViewPartyInventar.setMinSize(imgPartyInventar.getWidth(),imgPartyInventar.getHeight());
        btnViewPartyInventar.setMaxSize(imgPartyInventar.getWidth(),imgPartyInventar.getHeight());

        btnViewKaempfen = new Button();
        btnViewKaempfen.setGraphic(new ImageView(imgKaempfen));
        btnViewKaempfen.getStyleClass().add("gameHubButtonKampf");
        btnViewKaempfen.setMinSize(imgKaempfen.getWidth(),imgKaempfen.getHeight());
        btnViewKaempfen.setMaxSize(imgKaempfen.getWidth(),imgKaempfen.getHeight());

        //--------------------------------------------------------------------------UIElemente formatieren
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);

        //--------------------------------------------------------------------------UIElemente Platzieren

        //Infos zur Platzierung für alle anderen, ein bild wird immer gesetzt von der oberen Ecke des Bildes 0,0 vond ort wird es gerender auf den hintergrund

        btnViewSchmiede.setLayoutX(138);
        btnViewSchmiede.setLayoutY(458);
        btnViewHaendler.setLayoutX(39);
        btnViewHaendler.setLayoutY(650);
        btnViewTaverne.setLayoutX(180);
        btnViewTaverne.setLayoutY(829);
        btnViewPartyInventar.setLayoutX(525);
        btnViewPartyInventar.setLayoutY(500);
        //Position X Y vom kampf X-X und Y-Y
        btnViewKaempfen.setLayoutX(1330);
        btnViewKaempfen.setLayoutY(750);
        btnViewTrainer.setLayoutX(582);
        btnViewTrainer.setLayoutY(931);

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
        getChildren().addAll(btnViewSchmiede,btnViewHaendler,btnViewTaverne, btnViewPartyInventar, btnViewKaempfen,btnViewTrainer);

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
