package view;

import controller.OverlayPartyMenue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import party.PartyController;

import java.util.List;

public class OverlayRechts extends Pane {

    private final Pane charListPane = new Pane();
    private final PartyController partyController;
    private double x = 10;
    private double y = 0;
    private int partyAnzahl;

    public OverlayRechts(List<Button> buttons, ViewController viewController, PartyController partyController) {
        this.partyController = partyController;
        VBox menuaufbau = new VBox();
        VBox charBox = new VBox();
        menuaufbau.setLayoutX(9);
        menuaufbau.setLayoutY(57);
        charBox.setMinSize(384, 500);

        erneuereCharList();


        charBox.getChildren().add(charListPane);
        VBox buttonBox = new VBox();
        Image standartButton = new Image("buttons/menuRechtsButtonDefault.png");
        Image standartButtongedrueckt = new Image("buttons/menuRechtsButtonDefaultPressed.png");
        Image optionButtonNichtGedrueckt = new Image("buttons/menuRechtsButtonOptionNotPressed.png", 50, 50, true, true);
        Image optionButtonGedrueckt = new Image("buttons/menuRechtsButtonOptionPressed.png", 50, 50, true, true);
        buttonBox.setAlignment(Pos.CENTER);
        ImageView hintergrundMenu = new ImageView(new Image("background/menuLeisteRechts.png"));
        this.getChildren().add(hintergrundMenu);
        setMinWidth(384);
        setMinHeight(1080);

        menuaufbau.getChildren().add(charBox);
        menuaufbau.getChildren().add(buttonBox);
        this.getChildren().add(menuaufbau);

        if (buttons != null) {
            for (Button button : buttons) {
                button.getStyleClass().add("buttonMenueLeisteRechts");

                ImageView buttonImageView = new ImageView(standartButton);
                buttonImageView.setFitWidth(250);
                buttonImageView.setFitHeight(50);

                Text buttonText = new Text(button.getText());
                buttonText.getStyleClass().add("button-text");

                StackPane buttonPane = new StackPane(buttonImageView, buttonText);
                buttonPane.setAlignment(Pos.CENTER);

                button.setGraphic(buttonPane);
                button.setOnMousePressed(event -> buttonImageView.setImage(standartButtongedrueckt));
                button.setOnMouseReleased(event -> buttonImageView.setImage(standartButton));

                button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                buttonBox.getChildren().add(button);
            }
        }

        Button optionen = new Button("Optionen");
        optionen.getStyleClass().add("buttonMenueLeisteRechts");
        optionen.setGraphic(new ImageView(optionButtonNichtGedrueckt));
        optionen.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        optionen.setMaxSize(50, 50);
        optionen.setOnMousePressed(event -> optionen.setGraphic(new ImageView(optionButtonGedrueckt)));
        optionen.setOnMouseReleased(event -> optionen.setGraphic(new ImageView(optionButtonNichtGedrueckt)));
        optionen.setOnAction(event -> viewController.optionenAnzeigen());
        buttonBox.getChildren().add(optionen);
        this.setMaxSize(384.0, 1080.0);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void erneuereCharList() {
        for (int i = 0; i < 3; i++) {
            if (partyController.getTeammitglieder()[i] != null) {
                System.out.println(i);
                this.partyAnzahl = i + 1;
            }
        }
        OverlayPartyMenue.charLayout(charListPane, this.partyController.getParty().getHauptCharakter(), x, y);
        if (partyAnzahl > 1) {
            for (int i = 0; i < partyAnzahl - 1; i++) {
                y = y + 115;
                OverlayPartyMenue.charLayout(charListPane, partyController.getParty().getNebenCarakter(i), x, y);
            }
        }

    }

}
