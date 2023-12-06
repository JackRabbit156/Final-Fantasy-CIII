package hauptmenu;

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandSpeichernView;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.AnsichtsTyp;
import view.ViewController;

public class OptionenView extends VBox {

	/**
	 * Erstellt die View Optionen und legt die Inhalte und Funktionen fest
	 * @param hauptmenuController hauptmenuController
	 * @param gameController gameController
	 * @param viewController viewController
	 * @param gameHubController gameHubController
	 * @author Nick
	 * @since 06.12.2023
	 */
	public OptionenView(HauptmenuController hauptmenuController, GameController gameController,
			ViewController viewController, GameHubController gameHubController) {
		Label titel = new Label("Optionen");
		titel.setTextFill(Color.WHITE);
		titel.setFont(new Font("Lucida Calligraphy Italic", 80.0));

		Button speichern = new Button("Speichern");
		speichern.getStyleClass().add("hauptmenubutton");
		speichern.setOnAction(event -> {
			gameHubController.speichern();
			Node neuerSpeicherstand = new SpeicherstandSpeichernView(gameHubController.getSpeicherstandController());
			viewController.anmelden(neuerSpeicherstand, null, AnsichtsTyp.OHNE_OVERLAY);
		});
		Label schwierigkeitLbl = new Label("Schwierigkeitsgrad:");
		RadioButton leicht = new RadioButton("Leicht");
		RadioButton mittel = new RadioButton("Mittel");
		RadioButton schwer = new RadioButton("Schwer");
		ToggleGroup schwierigkeit = new ToggleGroup();
		leicht.setToggleGroup(schwierigkeit);
		mittel.setToggleGroup(schwierigkeit);
		schwer.setToggleGroup(schwierigkeit);
		switch (gameController.getSchwierigkeitsgrad()) {
		case "Leicht":
			leicht.setSelected(true);
			break;
		case "Mittel":
			mittel.setSelected(true);
			break;
		case "Schwer":
			schwer.setSelected(true);
			break;
		default:
			leicht.setSelected(true);
			break;
		}
		gameController.schwierigkeitsgradProperty().addListener((observable, oldValue, newValue) -> {
			switch (newValue) {
			case "Leicht":
				leicht.setSelected(true);
				break;
			case "Mittel":
				mittel.setSelected(true);
				break;
			case "Schwer":
				schwer.setSelected(true);
				break;
			default:
				leicht.setSelected(true);
				break;
			}
		});

		gameController.schwierigkeitsgradProperty()
				.bind(Bindings.createStringBinding(() -> ((RadioButton) schwierigkeit.getSelectedToggle()).getText(),
						schwierigkeit.selectedToggleProperty()));
		CheckBox hardCore = new CheckBox("Hardcore: ");
		hardCore.selectedProperty().bindBidirectional(gameController.hardcoreProperty());
		VBox schwierigkeitsGrad = new VBox(schwierigkeitLbl, leicht, mittel, schwer, hardCore);
		schwierigkeitsGrad.setAlignment(Pos.CENTER_LEFT);
		schwierigkeitsGrad.setSpacing(10.0);
		schwierigkeitsGrad.getStyleClass().add("schwierigkeitsauswahl");
		Button hauptmenu = new Button("Hauptmenu");
		hauptmenu.setOnAction(
				event -> viewController.anmelden(viewController.getHauptmenuView(), null, AnsichtsTyp.OHNE_OVERLAY));
		hauptmenu.getStyleClass().add("hauptmenubutton");
		Button zurueck = new Button("Zurück");
		zurueck.getStyleClass().add("hauptmenubutton");
		zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
		VBox menuPunkte = new VBox(speichern, schwierigkeitsGrad, hauptmenu, zurueck);
		menuPunkte.setAlignment(Pos.CENTER);
		menuPunkte.setSpacing(25.0);
		this.getChildren().addAll(titel, menuPunkte);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50.0);
		this.setBackground(new Background(new BackgroundImage(new Image("background/hauptmenue.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1920, 1080, false, false, false, false))));
	}
}
