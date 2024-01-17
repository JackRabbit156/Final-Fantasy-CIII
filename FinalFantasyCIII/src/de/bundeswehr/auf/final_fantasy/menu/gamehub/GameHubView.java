package de.bundeswehr.auf.final_fantasy.menu.gamehub;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;


public class GameHubView extends Pane {

    private static final double[] EGG_X = new double[] { 506, 922, 1185, 1513, 1294, 1455, 1196, 1425, 1196, 968, 857, 747, 453, 248, 143, 231, 379, 189, 710, 783, 1014, 1049, 1513, 1493, 1377,   0, 540, 440, 340,  60, 150,  84,  10,  80, 100, 1007 };
    private static final double[] EGG_Y = new double[] { 601, 522,  838,  997,  892,  586,  412,  434,  518, 250, 288, 253, 435, 441, 424, 619, 266, 383, 663, 627,  168,  346,  894,  729,  230, 107, 933, 993, 943, 963, 895, 813, 702, 733, 678,  110 };
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();

    private final Button btnViewHaendler;
    private final Button btnViewKaempfen;
    private final Button btnViewPartyInventar;
    private final Button btnViewPartyInventarEgg;
    private final Button btnViewPartyInventarEggHSC;
    private final Button btnViewPartyStatus;
    private final Button btnViewSchmiede;
    private final Button btnViewTaverne;
    private final Button btnViewTrainer;
    private int partyInventarEggHSC;

    /**
     * Die Klasse GameHubView repräsentiert die Ansicht des Game Hubs im Spiel,
     * die verschiedene Buttons und Elemente für Aktionen wie Schmiede, Händler, Taverne,
     * Partyinventar, Kämpfen, Trainer und Partystatus bereitstellt. Zudem gibt es ein
     * spezielles EasterEgg, das zufällig erscheint und bei Klicks eine bestimmte Anzahl
     * von klicks aktualisiert wird, wodurch eine Belohnung freigeschaltet werden kann.
     *
     * @param gameHubController Der zugehörige GameHubController, der die Interaktionen
     *                          mit den Buttons und Elementen steuert.
     * @author Rode
     * @since 06.12.2023
     */
    public GameHubView(GameHubController gameHubController) {
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

        btnViewHaendler = new Button();
        btnViewHaendler.setGraphic(new ImageView(imgHaendler));
        btnViewHaendler.getStyleClass().add("gameHubBuilding");
        btnViewHaendler.setMinSize(imgHaendler.getWidth(), imgHaendler.getHeight());
        btnViewHaendler.setMaxSize(imgHaendler.getWidth(), imgHaendler.getHeight());

        btnViewSchmiede = new Button();
        btnViewSchmiede.setGraphic(new ImageView(imgSchmiede));
        btnViewSchmiede.getStyleClass().add("gameHubBuilding");
        btnViewSchmiede.setMinSize(imgSchmiede.getWidth(), imgSchmiede.getHeight());
        btnViewSchmiede.setMaxSize(imgSchmiede.getWidth(), imgSchmiede.getHeight());

        btnViewTaverne = new Button();
        btnViewTaverne.setGraphic(new ImageView(imgTaverne));
        btnViewTaverne.getStyleClass().add("gameHubBuilding");
        btnViewTaverne.setMinSize(imgTaverne.getWidth(), imgTaverne.getHeight());
        btnViewTaverne.setMaxSize(imgTaverne.getWidth(), imgTaverne.getHeight());

        btnViewTrainer = new Button();
        btnViewTrainer.setGraphic(new ImageView(imgTrainer));
        btnViewTrainer.getStyleClass().add("gameHubBuilding");
        btnViewTrainer.setMinSize(imgTrainer.getWidth(), imgTrainer.getHeight());
        btnViewTrainer.setMaxSize(imgTrainer.getWidth(), imgTrainer.getHeight());

        btnViewPartyInventar = new Button();
        btnViewPartyInventar.setGraphic(new ImageView(imgPartyInventar));
        btnViewPartyInventar.getStyleClass().add("gameHubBuilding");
        btnViewPartyInventar.setMinSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());
        btnViewPartyInventar.setMaxSize(imgPartyInventar.getWidth(), imgPartyInventar.getHeight());

        btnViewPartyStatus = new Button();
        btnViewPartyStatus.setGraphic(new ImageView(imgPartyStatus));
        btnViewPartyStatus.getStyleClass().add("gameHubBuilding");
        btnViewPartyStatus.setMinSize(imgPartyStatus.getWidth(), imgPartyStatus.getHeight());
        btnViewPartyStatus.setMaxSize(imgPartyStatus.getWidth(), imgPartyStatus.getHeight());

        btnViewKaempfen = new Button();
        btnViewKaempfen.setGraphic(new ImageView(imgKaempfen));
        btnViewKaempfen.getStyleClass().add("gameHubBuilding");
        btnViewKaempfen.setMinSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());
        btnViewKaempfen.setMaxSize(imgKaempfen.getWidth(), imgKaempfen.getHeight());

        //--------------------------------------------------------------------------UIElemente formatieren
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);

        //--------------------------------------------------------------------------UIElemente Platzieren

        //Infos zur Platzierung für alle anderen, ein bild wird immer gesetzt von der oberen Ecke des Bildes 0,0 vond ort wird es gerender auf den hintergrund

        btnViewPartyInventarEgg.setLayoutX(506);
        btnViewPartyInventarEgg.setLayoutY(601);


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

        btnViewHaendler.setOnMouseEntered(event -> gameHubController.ausloeserHaendlerHover());
        btnViewHaendler.setOnMouseClicked(event -> gameHubController.haendlerAnzeigen());
        btnViewHaendler.setOnMouseExited(event -> gameHubController.entfernenHaendlerHover());

        btnViewSchmiede.setOnMouseEntered(event -> gameHubController.ausloeserSchmiedeHover());
        btnViewSchmiede.setOnMouseClicked(event -> gameHubController.schmiedeAnzeigen());
        btnViewSchmiede.setOnMouseExited(event -> gameHubController.entfernenSchmiedeHover());

        btnViewTaverne.setOnMouseEntered(event -> gameHubController.ausloeserTaverneHover());
        btnViewTaverne.setOnMouseClicked(event -> gameHubController.taverneAnzeigen());
        btnViewTaverne.setOnMouseExited(event -> gameHubController.entfernenTaverneHover());

        btnViewTrainer.setOnMouseEntered(event -> gameHubController.ausloeserTrainerHover());
        btnViewTrainer.setOnMouseClicked(event -> gameHubController.trainerAnzeigen());
        btnViewTrainer.setOnMouseExited(event -> gameHubController.entfernenTrainerHover());

        btnViewPartyInventar.setOnMouseEntered(event -> gameHubController.ausloeserPartyHover());
        btnViewPartyInventar.setOnMouseClicked(event -> gameHubController.partyInventarAnzeigen());
        btnViewPartyInventar.setOnMouseExited(event -> gameHubController.entfernenPartyHover());

        btnViewPartyStatus.setOnMouseEntered(event -> gameHubController.ausloeserPartyStatusHover());
        btnViewPartyStatus.setOnMouseClicked(event -> gameHubController.partyStatusAnzeigen());
        btnViewPartyStatus.setOnMouseExited(event -> gameHubController.entfernenPartyStatusHover());

        btnViewKaempfen.setOnMouseEntered(event -> gameHubController.ausloeserKampfHover());
        btnViewKaempfen.setOnMouseClicked(event -> gameHubController.kaempfenAnzeigen());
        btnViewKaempfen.setOnMouseExited(event -> gameHubController.entfernenKampfHover());

        btnViewPartyInventarEgg.setOnMouseClicked(event -> {
            this.btnViewPartyInventarEggHSC.setVisible(true);
            this.partyInventarEggHSC = this.partyInventarEggHSC + 1;
            int i = RANDOM_NUMBER_GENERATOR.nextInt(EGG_X.length);
            btnViewPartyInventarEgg.setLayoutX(EGG_X[i]);
            btnViewPartyInventarEgg.setLayoutY(EGG_Y[i]);
            btnViewPartyInventarEggHSC.setText("" + this.partyInventarEggHSC + "/50");
            if (this.partyInventarEggHSC == 50) {
                gameHubController.getPartyController().goldHinzufuegen(25_123);
                this.btnViewPartyInventarEggHSC.setVisible(false);
                this.btnViewPartyInventarEgg.setVisible(false);
            }

        });

        getChildren().addAll(btnViewSchmiede, btnViewHaendler, btnViewTaverne, btnViewPartyInventar, btnViewKaempfen, btnViewTrainer, btnViewPartyStatus, btnViewPartyInventarEgg, btnViewPartyInventarEggHSC);
    }

    /**
     * Löst den Hover-Effekt für den Händler-Button im Game Hub aus.
     *
     * @see #btnViewHaendler
     * @since 06.12.2023
     */
    public void ausloeserHaendlerHover() {
        btnViewHaendler.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Kampf-Button im Game Hub aus.
     *
     * @see #btnViewKaempfen
     * @since 06.12.2023
     */
    public void ausloeserKampfHover() {
        btnViewKaempfen.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Party-Inventar-Button im Game Hub aus.
     *
     * @see #btnViewPartyInventar
     * @since 06.12.2023
     */
    public void ausloeserPartyHover() {
        btnViewPartyInventar.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Party-Status-Button im Game Hub aus.
     *
     * @see #btnViewPartyStatus
     * @since 06.12.2023
     */
    public void ausloeserPartyStatusHover() {
        btnViewPartyStatus.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Schmiede-Button im Game Hub aus.
     *
     * @see #btnViewSchmiede
     * @since 06.12.2023
     */
    public void ausloeserSchmiedeHover() {
        btnViewSchmiede.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Tavernen-Button im Game Hub aus.
     *
     * @see #btnViewTaverne
     * @since 06.12.2023
     */
    public void ausloeserTaverneHover() {
        btnViewTaverne.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Löst den Hover-Effekt für den Trainer-Button im Game Hub aus.
     *
     * @see #btnViewTrainer
     * @since 06.12.2023
     */
    public void ausloeserTrainerHover() {
        btnViewTrainer.getStyleClass().add("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den Händler im Game Hub.
     *
     * @see #btnViewHaendler
     * @since 06.12.2023
     */
    public void entfernenHaendlerHover() {
        btnViewHaendler.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den Kampf im Game Hub.
     *
     * @see #btnViewKaempfen
     * @since 06.12.2023
     */
    public void entfernenKampfHover() {
        btnViewKaempfen.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für das Inventar im Game Hub.
     *
     * @see #btnViewPartyInventar
     * @since 06.12.2023
     */
    public void entfernenPartyHover() {
        btnViewPartyInventar.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den PartyStatus im Game Hub.
     *
     * @see #btnViewPartyStatus
     * @since 06.12.2023
     */
    public void entfernenPartyStatusHover() {
        btnViewPartyStatus.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für die Schmiede im Game Hub.
     *
     * @see #btnViewSchmiede
     * @since 06.12.2023
     */
    public void entfernenSchmiedeHover() {
        btnViewSchmiede.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für die Taverne im Game Hub.
     *
     * @see #btnViewTaverne
     * @since 06.12.2023
     */
    public void entfernenTaverneHover() {
        btnViewTaverne.getStyleClass().remove("gameHubBuilding-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für das Inventar im Game Hub.
     *
     * @see #btnViewTrainer
     * @since 06.12.2023
     */
    public void entfernenTrainerHover() {
        btnViewTrainer.getStyleClass().remove("gameHubBuilding-hover");
    }

}
