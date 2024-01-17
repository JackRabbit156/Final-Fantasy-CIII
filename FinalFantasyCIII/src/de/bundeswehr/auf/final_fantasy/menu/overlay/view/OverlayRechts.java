package de.bundeswehr.auf.final_fantasy.menu.overlay.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.view.AttributeAendernView;

import java.util.List;

public class OverlayRechts extends Pane {

    private final PartyController partyController;
    private final VBox charBox;

    /**
     * Stellt ein Overlay-Menü dar, um die Informationen eines Spielercharakters anzuzeigen.
     * Dieses Menü enthält den Avatar des Charakters, den Namen, die Lebenspunkteleiste und die Manaleiste.
     * Diese chars werden im Rechten Overlay Menü angezeigt
     *
     * @param buttons wird benötigt umd die buttons zu zeigen bzw zu erstellen
     * @param viewController um auf die übergeordnete eben zuzugreifen und anzupassen
     * @param partyController wird benötigt uma uf char informationen zuzugreifen
     * @param view die View welche gesetzt wird
     *
     * @author Rode
     * @since 06.12.2023
     */
    public OverlayRechts(List<Button> buttons, ViewController viewController, PartyController partyController, Node view) {
        this.partyController = partyController;
        VBox menuaufbau = new VBox();
        charBox = new VBox();
        menuaufbau.setLayoutX(9);
        menuaufbau.setLayoutY(57);
        charBox.setMinSize(384, 500);
        charBox.setSpacing(10.0);

        erneuereCharList();
        VBox buttonBox = new VBox();
        Image standardButton = new Image("buttons/menuRechtsButtonDefault.png");
        Image standardButtonGedrueckt = new Image("buttons/menuRechtsButtonDefaultPressed.png");
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

                ImageView buttonImageView = new ImageView(standardButton);
                buttonImageView.setFitWidth(250);
                buttonImageView.setFitHeight(50);

                Text buttonText = new Text(button.getText());
                buttonText.getStyleClass().add("button-text");

                StackPane buttonPane = new StackPane(buttonImageView, buttonText);
                buttonPane.setAlignment(Pos.CENTER);

                button.setGraphic(buttonPane);
                button.setOnMousePressed(event -> buttonImageView.setImage(standardButtonGedrueckt));
                button.setOnMouseReleased(event -> buttonImageView.setImage(standardButton));

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
        if(view instanceof AttributeAendernView){
            optionen.setDisable(true);
        }
        this.setMaxSize(384.0, 1080.0);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

	/**
	 * Aktualisiert die Liste der Spielercharaktere im Overlay. Entfernt alle
	 * vorhandenen Elemente und fügt dann für jeden Spielercharakter im Team ein
	 * neues Overlay hinzu.
	 *
	 * @see OverlayPartyMenue
	 * @author Rode
	 * @since 06.12.2023
	 */
	public void erneuereCharList() {
		charBox.getChildren().clear();
		for (SpielerCharakter spielerCharakter : partyController.getTeammitglieder()) {
			if (spielerCharakter != null) {
				charBox.getChildren().add(new OverlayPartyMenue(spielerCharakter));
			}
		}
	}

}
