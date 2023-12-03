package view;

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
    private PartyController partyController;


    public OverlayRechts(List<Button> buttons, ViewController viewController, PartyController partyController) {
        this.partyController = partyController;
        VBox menuaufbau = new VBox();
        VBox charBox = new VBox();
        menuaufbau.setLayoutX(10);
        menuaufbau.setLayoutY(60);
        StackPane charListPane = new StackPane();
        charBox.setMinSize(384, 500);
        int partyAnzahl = partyController.getTeammitglieder().length;
        //Todo - Ausklammern sobald die chars bilder haben nich vergessen
        if (partyAnzahl >= 1) {

            HBox charOne = new HBox();
            Button charOneImage = new Button();
            charOne.setMinSize(350, 80);
            VBox charOneVbox = new VBox();
            StackPane charOneStackPaneHP = new StackPane();
            StackPane charOneStackPaneMP = new StackPane();
            //TODO Image charOneAvatar = new Image(partyController.getParty().getHauptCharakter().getGrafischeDarstellung());
            Image charOneAvatar = new Image("charaktere/charplaceholder.png", 80, 80, true, true);
            charOneImage.setGraphic(new ImageView(charOneAvatar));
            charOneImage.getStyleClass().add("buttonAvatarPictures");
            charOneImage.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            //Test Borders
            charOneVbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
            charOneStackPaneHP.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
            charOneStackPaneHP.setMinSize(250, 40);
            charOneStackPaneMP.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
            charOneStackPaneMP.setMinSize(250, 40);
            charOne.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
            //Test Daten zuende
            charOne.getChildren().add(charOneImage);
            charOne.setLayoutX(16);
            charOne.setLayoutY(71);

            charOneVbox.getChildren().add(charOneStackPaneHP);
            charOneVbox.getChildren().add(charOneStackPaneMP);
            charOne.getChildren().add(charOneVbox);

            charListPane.getChildren().add(charOne);


//            if (partyAnzahl >= 2) {
//                HBox charTwo = new HBox();
//                charTwo.setMinSize(350,80);
//                VBox charTwoVbox = new VBox();
//                StackPane charTwoStackPaneHP = new StackPane();
//                StackPane charTwoStackPaneMP = new StackPane();
//                //TODO Image charTwoAvatar = new Image(partyController.getParty().getNebenCarakter(0).getGrafischeDarstellung());
//                if (partyAnzahl >= 3) {
//                    HBox charThree = new HBox();
//                    charThree.setMinSize(350,80);
//                    VBox charThreeVbox = new VBox();
//                    StackPane charThreeStackPaneHP = new StackPane();
//                    StackPane charThreeStackPaneMP = new StackPane();
//                    //TODO Image charThreeAvatar = new Image(partyController.getParty().getNebenCarakter(0).getGrafischeDarstellung());
//                    if (partyAnzahl >= 4) {
//                        HBox charFour = new HBox();
//                        charFour.setMinSize(350,80);
//                        VBox charFourVbox = new VBox();
//                        StackPane charFourStackPaneHP = new StackPane();
//                        StackPane charFourStackPaneMP = new StackPane();
//                        //Todo Image charFourAvatar = new Image(partyController.getParty().getNebenCarakter(0).getGrafischeDarstellung());
//                    }
//                }
//            }
            charBox.getChildren().add(charListPane);
        }


        //- Char 4 Ui Elemente


        VBox buttonBox = new VBox();
        Image standartButton = new Image("buttons/menuRechtsButtonDefault.png");
        Image standartButtongedrueckt = new Image("buttons/menuRechtsButtonDefaultPressed.png");
        Image optionButtonNichtGedrueckt = new Image("buttons/menuRechtsButtonOptionNotPressed.png", 50, 50, true, true);
        Image optionButtonGedrueckt = new Image("buttons/menuRechtsButtonOptionPressed.png", 50, 50, true, true);
        buttonBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
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

}
