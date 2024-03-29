package de.bundeswehr.auf.final_fantasy.menu.overlay.view;


import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OverlayPartyMenue extends HBox {

    /**
     * Stellt ein Overlay-Menü dar, um die Informationen eines Spielercharakters im Rechten Overlay anzuzeigen.
     * Dieses Menü enthält den Avatar des Charakters, den Namen, die Lebenspunkteleiste und die Manaleiste.
     * Diese Klasse wird so oft aufgerufen wie chars in der Party sind. dies wird außerhalb gesteuert
     *
     * @param spielerCharakter Der Spielercharakter, für den das Overlay erstellt wird.
     * @author Rode
     * @since 06.12.2023
     */
    public OverlayPartyMenue(SpielerCharakter spielerCharakter) {
        Button spielerCharImage = new Button();
        setMinSize(350, 80);
        VBox spielerCharVbox = new VBox();
        HBox spielerCharPaneName = new HBox(5);
        spielerCharPaneName.setAlignment(Pos.CENTER_LEFT);
        spielerCharPaneName.setPrefSize(250, 27);
        spielerCharPaneName.setPadding(new Insets(0, 0, 0, 15));
        StackPane spielerCharPaneHP = new StackPane();
        spielerCharPaneHP.setPrefSize(250, 25);
        StackPane spielerCharPaneMP = new StackPane();
        spielerCharPaneMP.setPrefSize(250, 25);
        setAlignment(Pos.CENTER);

        Image spielerCharAvatar = new Image(spielerCharakter.getGrafischeDarstellung(), 80, 80, false, true);
        spielerCharImage.setGraphic(new ImageView(spielerCharAvatar));
        spielerCharImage.getStyleClass().add("buttonAvatarPictures");
        spielerCharImage.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        spielerCharImage.setMaxSize(80, 80);
        getChildren().add(spielerCharImage);

        Rectangle levelBox = new Rectangle(25, 25);
        levelBox.setFill(ColorHelper.getFill(spielerCharakter));
        levelBox.setStroke(Color.BLACK);
        Text level = new Text(spielerCharakter.getLevel() + "");
        level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18));

        StackPane stackPaneLevelAnzeige = new StackPane();
        stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
        spielerCharPaneName.getChildren().add(stackPaneLevelAnzeige);

        Label spielerCharName = new Label(spielerCharakter.getName());
        spielerCharName.setMaxWidth(185);
        spielerCharName.getStyleClass().add("spielerCharNameText");
        spielerCharPaneName.getChildren().add(spielerCharName);

        ProgressBar healthBarSpielerChar = new ProgressBar((double) spielerCharakter.getGesundheitsPunkte() / (double) spielerCharakter.getMaxGesundheitsPunkte());
        healthBarSpielerChar.setMinSize(220, 27);
        healthBarSpielerChar.setMaxSize(220, 27);
        healthBarSpielerChar.setStyle(ColorHelper.healthBarColor(spielerCharakter));
        Text gesundheitsPunkteSpielerCharAlsText = new Text(spielerCharakter.getGesundheitsPunkte() + " / " + spielerCharakter.getMaxGesundheitsPunkte() + " HP");
        gesundheitsPunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        ProgressBar manaBarSpielerChar = new ProgressBar((double) spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
        manaBarSpielerChar.setMinSize(220, 27);
        manaBarSpielerChar.setMaxSize(220, 27);
        manaBarSpielerChar.setStyle("-fx-accent: -fx-blue;");
        Text manapunktePunkteSpielerCharAlsText = new Text(spielerCharakter.getManaPunkte() + " / " + spielerCharakter.getMaxManaPunkte() + " MP");
        manapunktePunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        spielerCharPaneHP.getChildren().addAll(healthBarSpielerChar, gesundheitsPunkteSpielerCharAlsText);
        spielerCharPaneMP.getChildren().addAll(manaBarSpielerChar, manapunktePunkteSpielerCharAlsText);

        spielerCharVbox.getChildren().add(spielerCharPaneName);
        spielerCharVbox.getChildren().add(spielerCharPaneHP);
        spielerCharVbox.getChildren().add(spielerCharPaneMP);
        getChildren().add(spielerCharVbox);
    }

}


