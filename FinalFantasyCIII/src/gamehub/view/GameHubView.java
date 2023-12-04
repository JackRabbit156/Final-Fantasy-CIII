package gamehub.view;

import gamehub.GameHubController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;


public class GameHubView extends Pane {
    private final Button btnViewKaempfen;
    private final Button btnViewPartyInventar;
    private final Button btnViewTaverne;
    private final Button btnViewSchmiede;
    private final Button btnViewHaendler;
    private final Button btnViewTrainer;
    private final Button btnViewPartyInventarEgg;
    private final Button btnViewPartyInventarEggHSC;
    private final Button btnViewPartyStatus;
    Random rng = new Random();
    private int partyInventarEggHSC = 0;
    private int rngCounter = 0;
    private double[] partyInventarEggCordX;
    private double[] partyInventarEggCordY;


    public GameHubView(GameHubController gameHubController) {
        this.partyInventarEggCordX = new double[]{506, 922, 1185, 1513, 1294, 1455, 1196, 1425, 1196, 968, 857, 747, 453, 248, 143, 231, 379, 189, 710, 783, 1014, 1049, 1513, 1493, 1377, 0, 540, 440, 340, 60, 150, 84, 10, 80, 100, 1007};
        this.partyInventarEggCordY = new double[]{601, 522, 838, 997, 892, 586, 412, 434, 518, 250, 288, 253, 435, 441, 424, 619, 266, 383, 663, 627, 168, 346, 894, 729, 230, 107, 933, 993, 943, 963, 895, 813, 702, 733, 678, 110};

        //--------------------------------------------------------------------------UIElemente
        Image hintergrundBild = new Image("background/gameHubBG.jpg");
        Image imgSchmiede = new Image("gamehub/blacksmith.png");
        Image imgHaendler = new Image("gamehub/haendler.png");
        Image imgTrainer = new Image("gamehub/trainer.png");
        Image imgTaverne = new Image("gamehub/taverne.png");
        Image imgPartyInventar = new Image("gamehub/partyInventar.png");
        Image imgKaempfen = new Image("gamehub/kampfAnzeige.png");
        Image imgPartyInventarEgg = new Image("gamehub/partyInventarEgg.png");
        Image imgPartyStatus = new Image("gamehub/partyStatus.png");

        ImageView hintergrundBildAnsicht = new ImageView(hintergrundBild);

        btnViewPartyInventarEgg = new Button();
        btnViewPartyInventarEgg.setGraphic(new ImageView(imgPartyInventarEgg));
        btnViewPartyInventarEgg.getStyleClass().add("gameHubButtonegg");
        btnViewPartyInventarEgg.setMinSize(imgPartyInventarEgg.getWidth(), imgPartyInventarEgg.getHeight());
        btnViewPartyInventarEgg.setMaxSize(imgPartyInventarEgg.getWidth(), imgPartyInventarEgg.getHeight());

        btnViewPartyInventarEggHSC = new Button("" + this.partyInventarEggHSC);
        btnViewPartyInventarEggHSC.setVisible(false);
        btnViewPartyInventarEggHSC.getStyleClass().add("gameHubButtonPIHSC");
        btnViewPartyInventarEggHSC.setMinSize(imgPartyInventarEgg.getWidth(), imgPartyInventarEgg.getHeight());
        btnViewPartyInventarEggHSC.setMaxSize(imgPartyInventarEgg.getWidth(), imgPartyInventarEgg.getHeight());

        btnViewSchmiede = new Button();
        btnViewSchmiede.setGraphic(new ImageView(imgSchmiede));
        btnViewSchmiede.getStyleClass().add("gameHubButtonSchmiede");
        btnViewSchmiede.setMinSize(imgSchmiede.getWidth(), imgSchmiede.getHeight());
        btnViewSchmiede.setMaxSize(imgSchmiede.getWidth(), imgSchmiede.getHeight());


        btnViewHaendler = new Button();
        btnViewHaendler.setGraphic(new ImageView(imgHaendler));
        btnViewHaendler.getStyleClass().add("gameHubButtonHaendler");
        btnViewHaendler.setMinSize(imgHaendler.getWidth(), imgHaendler.getHeight());
        btnViewHaendler.setMaxSize(imgHaendler.getWidth(), imgHaendler.getHeight());

        btnViewTrainer = new Button();
        btnViewTrainer.setGraphic(new ImageView(imgTrainer));
        btnViewTrainer.getStyleClass().add("gameHubButtonTrainer");
        btnViewTrainer.setMinSize(imgTrainer.getWidth(), imgTrainer.getHeight());
        btnViewTrainer.setMaxSize(imgTrainer.getWidth(), imgTrainer.getHeight());

        btnViewTaverne = new Button();
        btnViewTaverne.setGraphic(new ImageView(imgTaverne));
        btnViewTaverne.getStyleClass().add("gameHubButtonTaverne");
        btnViewTaverne.setMinSize(imgTaverne.getWidth(), imgTaverne.getHeight());
        btnViewTaverne.setMaxSize(imgTaverne.getWidth(), imgTaverne.getHeight());

        btnViewPartyInventar = new Button();
        btnViewPartyInventar.setGraphic(new ImageView(imgPartyInventar));
        btnViewPartyInventar.getStyleClass().add("gameHubButtonPartyInventar");
        btnViewPartyInventar.setMinSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());
        btnViewPartyInventar.setMaxSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());

        btnViewPartyStatus = new Button();
        btnViewPartyStatus.setGraphic(new ImageView(imgPartyStatus));
        btnViewPartyStatus.getStyleClass().add("gameHubButtonPartyStatus");
        btnViewPartyStatus.setMinSize(imgPartyStatus.getWidth(), imgPartyStatus.getHeight());
        btnViewPartyStatus.setMaxSize(imgPartyStatus.getWidth(), imgPartyStatus.getHeight());

        btnViewKaempfen = new Button();
        btnViewKaempfen.setGraphic(new ImageView(imgKaempfen));
        btnViewKaempfen.getStyleClass().add("gameHubButtonKampf");
        btnViewKaempfen.setMinSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());
        btnViewKaempfen.setMaxSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());

        //--------------------------------------------------------------------------UIElemente formatieren
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1513);
        setMinHeight(997);

        //--------------------------------------------------------------------------UIElemente Platzieren

        //Infos zur Platzierung für alle anderen, ein bild wird immer gesetzt von der oberen Ecke des Bildes 0,0 vond ort wird es gerender auf den hintergrund

        btnViewPartyInventarEgg.setLayoutX(922);
        btnViewPartyInventarEgg.setLayoutY(522);


        btnViewPartyInventarEggHSC.setLayoutX(15);
        btnViewPartyInventarEggHSC.setLayoutX(15);

        btnViewSchmiede.setLayoutX(710);
        btnViewSchmiede.setLayoutY(58);


        btnViewHaendler.setLayoutX(96);
        btnViewHaendler.setLayoutY(159);

        btnViewTaverne.setLayoutX(453);
        btnViewTaverne.setLayoutY(320);

        btnViewPartyInventar.setLayoutX(797);
        btnViewPartyInventar.setLayoutY(564);

        btnViewKaempfen.setLayoutX(1141);
        btnViewKaempfen.setLayoutY(112);

        btnViewTrainer.setLayoutX(1049);
        btnViewTrainer.setLayoutY(262);

        btnViewPartyStatus.setLayoutX(906);
        btnViewPartyStatus.setLayoutY(374);

        btnViewSchmiede.setOnMouseClicked(event -> {
            gameHubController.schmiedeAnzeigen();
        });
        btnViewHaendler.setOnMouseClicked(event -> {
            gameHubController.haendlerAnzeigen();
        });
        btnViewTrainer.setOnMouseClicked(event -> {
            gameHubController.trainerAnzeigen();
        });
        btnViewTaverne.setOnMouseClicked(event -> {
            gameHubController.taverneAnzeigen();
        });
        btnViewPartyInventar.setOnMouseClicked(event -> {
            gameHubController.partyInventarAnzeigen();
            btnViewPartyInventarEggHSC.setVisible(false);
            btnViewPartyInventarEgg.setVisible(false);
        });
        btnViewPartyInventarEgg.setOnMouseClicked(event -> {
            this.btnViewPartyInventarEggHSC.setVisible(true);
            this.partyInventarEggHSC = this.partyInventarEggHSC + 1;
            this.rngCounter = this.rng.nextInt(36);
            btnViewPartyInventarEgg.setLayoutX(this.partyInventarEggCordX[this.rngCounter]);
            btnViewPartyInventarEgg.setLayoutY(this.partyInventarEggCordY[this.rngCounter]);
            btnViewPartyInventarEggHSC.setText("" + this.partyInventarEggHSC + "/50");
            if (this.partyInventarEggHSC == 50) {
                gameHubController.getPartyController().goldHinzufuegen(25_123);
                this.btnViewPartyInventarEggHSC.setVisible(false);
                this.btnViewPartyInventarEgg.setVisible(false);
            }

        });
        btnViewKaempfen.setOnMouseClicked(event -> {
            gameHubController.kaempfenAnzeigen();
        });

        btnViewPartyStatus.setOnMouseClicked(event -> {
            gameHubController.partyStatusAnzeigen();
        });

        getChildren().addAll(btnViewSchmiede, btnViewHaendler, btnViewTaverne, btnViewPartyInventar, btnViewKaempfen, btnViewTrainer, btnViewPartyStatus, btnViewPartyInventarEgg, btnViewPartyInventarEggHSC);

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

    public void ausloeserTaverneHover() {
        btnViewTaverne.getStyleClass().add("gameHubButtonTaverne-hover");
    }

    public void entfernenTaverneHover() {
        btnViewTaverne.getStyleClass().remove("gameHubButtonTaverne-hover");
    }

    public void ausloeserTrainerHover() {
        btnViewTrainer.getStyleClass().add("gameHubButtonTrainer-hover");
    }

    public void entfernenTrainerHover() {
        btnViewTrainer.getStyleClass().remove("gameHubButtonTrainer-hover");
    }

    public void ausloeserHaendlerHover() {
        btnViewHaendler.getStyleClass().add("gameHubButtonHaendler-hover");
    }

    public void entfernenHaendlerHover() {
        btnViewHaendler.getStyleClass().remove("gameHubButtonHaendler-hover");
    }

    public void ausloeserSchmiedeHover() {
        btnViewSchmiede.getStyleClass().add("gameHubButtonTrainer-hover");
    }

    public void entfernenSchmiedeHover() {
        btnViewSchmiede.getStyleClass().remove("gameHubButtonTrainer-hover");
    }

    public void ausloeserPartyStatusHover() {
        btnViewPartyStatus.getStyleClass().add("gameHubButtonPartyStatus-hover");
    }

    public void entfernenPartyStatusHover() {
        btnViewPartyStatus.getStyleClass().remove("gameHubButtonPartyStatus-hover");
    }
}
