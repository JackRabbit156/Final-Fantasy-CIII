package de.bundeswehr.auf.final_fantasy.menu.overlay.view;


import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
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
     *
     * @author Rode
     * @since 06.12.2023
     */
    public OverlayPartyMenue (SpielerCharakter spielerCharakter) {
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
        spielerCharImage.setMaxSize(80,80);
        getChildren().add(spielerCharImage);

        Color levelBoxColor = Color.WHITE;
        if (spielerCharakter.getKlasse() instanceof HLR) {
            levelBoxColor = Color.LIMEGREEN;
        }
        else if (spielerCharakter.getKlasse() instanceof MDD) {
            levelBoxColor = Color.CORNFLOWERBLUE;
        }
        else if (spielerCharakter.getKlasse() instanceof PDD) {
            levelBoxColor = Color.CRIMSON;
        }
        else if (spielerCharakter.getKlasse() instanceof TNK) {
            levelBoxColor = Color.GREY;
        }
        Rectangle levelBox = new Rectangle(25, 25);
        levelBox.setFill(levelBoxColor);
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
        healthBarSpielerChar.setStyle(healthBarColorAktualisieren(spielerCharakter.getGesundheitsPunkte(), spielerCharakter.getMaxGesundheitsPunkte()));
        Text gesundheitsPunkteSpielerCharAlsText = new Text(spielerCharakter.getGesundheitsPunkte() + " / " + spielerCharakter.getMaxGesundheitsPunkte());
        gesundheitsPunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        ProgressBar manaBarSpielerChar = new ProgressBar((double) spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
        manaBarSpielerChar.setMinSize(220, 27);
        manaBarSpielerChar.setMaxSize(220, 27);
        manaBarSpielerChar.setStyle("-fx-accent: #00BFFF;");
        Text manapunktePunkteSpielerCharAlsText = new Text(spielerCharakter.getManaPunkte() + " / " + spielerCharakter.getMaxManaPunkte());
        manapunktePunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        spielerCharPaneHP.getChildren().addAll(healthBarSpielerChar, gesundheitsPunkteSpielerCharAlsText);
        spielerCharPaneMP.getChildren().addAll(manaBarSpielerChar, manapunktePunkteSpielerCharAlsText);

        spielerCharVbox.getChildren().add(spielerCharPaneName);
        spielerCharVbox.getChildren().add(spielerCharPaneHP);
        spielerCharVbox.getChildren().add(spielerCharPaneMP);
        getChildren().add(spielerCharVbox);
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


