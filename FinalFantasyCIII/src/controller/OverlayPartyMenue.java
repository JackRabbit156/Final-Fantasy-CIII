package controller;


import charakter.model.SpielerCharakter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OverlayPartyMenue {

    public static void charLayout(Pane charListPane, SpielerCharakter spielerCharakter, double x, double y) {
        HBox spielerChar = new HBox();
        Button spielerCharImage = new Button();
        spielerChar.setMinSize(350, 80);
        VBox spielerCharVbox = new VBox();
        StackPane spielerCharStackPaneName = new StackPane();
        spielerCharStackPaneName.setMinSize(250, 27);
        StackPane spielerCharStackPaneHP = new StackPane();
        spielerCharStackPaneHP.setMinSize(250, 25);
        StackPane spielerCharStackPaneMP = new StackPane();
        spielerCharStackPaneMP.setMinSize(250, 25);
        spielerChar.setAlignment(Pos.CENTER);

        // TODO ----Image spielerCharAvatar = new Image(spielerCharakter.getGrafischeDarstellung());
        Image spielerCharAvatar = new Image("charaktere/charplaceholder.png", 80, 80, true, true);
        spielerCharImage.setGraphic(new ImageView(spielerCharAvatar));
        spielerCharImage.getStyleClass().add("buttonAvatarPictures");
        spielerCharImage.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Text spielerCharName = new Text(spielerCharakter.getName());
        spielerCharName.setStyle("partystatusCharakterBarText");
        spielerCharStackPaneName.getChildren().add(spielerCharName);

        ProgressBar healthBarSpielerChar = new ProgressBar((double) spielerCharakter.getGesundheitsPunkte() / (double) spielerCharakter.getMaxGesundheitsPunkte());
        healthBarSpielerChar.setPrefSize(220, 27);
        healthBarSpielerChar.setStyle(healthBarColorAktualisieren(spielerCharakter.getGesundheitsPunkte(), spielerCharakter.getMaxGesundheitsPunkte()));
        Text gesundheitsPunkteSpielerCharAlsText = new Text(spielerCharakter.getGesundheitsPunkte() + " / " + spielerCharakter.getMaxGesundheitsPunkte());
        gesundheitsPunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        ProgressBar manaBarSpielerChar = new ProgressBar((double) spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
        manaBarSpielerChar.setPrefSize(220, 27);
        manaBarSpielerChar.setStyle("-fx-accent: #0051ff;");
        Text manapunktePunkteSpielerCharAlsText = new Text(spielerCharakter.getManaPunkte() + " / " + spielerCharakter.getMaxManaPunkte());
        manapunktePunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        spielerCharStackPaneHP.getChildren().addAll(healthBarSpielerChar, gesundheitsPunkteSpielerCharAlsText);
        spielerCharStackPaneMP.getChildren().addAll(manaBarSpielerChar, manapunktePunkteSpielerCharAlsText);


        spielerChar.getChildren().add(spielerCharImage);
        spielerChar.setLayoutX(x);
        spielerChar.setLayoutY(y);

        spielerCharVbox.getChildren().add(spielerCharStackPaneName);
        spielerCharVbox.getChildren().add(spielerCharStackPaneHP);
        spielerCharVbox.getChildren().add(spielerCharStackPaneMP);
        spielerChar.getChildren().add(spielerCharVbox);

        charListPane.getChildren().add(spielerChar);


    }

    private static String healthBarColorAktualisieren(double gesundheitsPunkte, double maxGesundheitsPunkte) {
        String colorHealthBar;
        if ((gesundheitsPunkte / maxGesundheitsPunkte) >= 0.5) {
            colorHealthBar = "-fx-accent: #00FF00;";
        } else if ((gesundheitsPunkte / maxGesundheitsPunkte) >= 0.2) {
            colorHealthBar = "-fx-accent: #FF8C00;";
        } else {
            colorHealthBar = "-fx-accent: #FF0000;";
        }
        return colorHealthBar;
    }
}


