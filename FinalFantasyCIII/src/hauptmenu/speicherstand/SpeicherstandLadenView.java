package hauptmenu.speicherstand;

import gamehub.GameHubController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.KonsolenAssistent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import party.PartyController;
import statistik.StatistikController;
import view.ViewController;

public class SpeicherstandLadenView extends BorderPane {
	private ViewController viewController;
	private HauptmenuController hauptmenuController;
	private SpeicherstandController speicherstandController;
	private PartyController partyController;
	private StatistikController statistikController;
	private GameController gameController;
	private GameHubController gameHubController;
	private boolean istSpeicherstandVorhanden;

	public SpeicherstandLadenView(ViewController viewController, SpeicherstandController speicherstandController,
			PartyController partyController, StatistikController statistikController, GameController gameController,
			GameHubController gameHubController) {
		this.viewController = viewController;
		this.speicherstandController = speicherstandController;
		this.partyController = partyController;
		this.statistikController = statistikController;
		this.gameController = gameController;
		this.gameHubController = gameHubController;

		try {
			Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
			istSpeicherstandVorhanden = true;
			if (auswahl != null) {
				partyController = new PartyController(auswahl.getParty());
				gameController = new GameController(auswahl.getSchwierigkeitsgrad(), auswahl.isHardcore(),
						partyController);
				statistikController = new StatistikController(auswahl.getStatistik());
				// gameHubController = new GameHubController(gameController, partyController,
				// statistikController,speicherstandController, viewController);
				KonsolenAssistent.clear();
				// gameHubController.hubAnzeigen();
			}
			else {
				istSpeicherstandVorhanden = false;
			}
			// hauptmenuAnzeigen();
		} catch (Exception e) {
//			viewController.toFront("hauptmenu");
		}

		if (!istSpeicherstandVorhanden) {
			this.setBackground(new Background(new BackgroundImage(new Image("hintergruende/hauptmenue.jpg"),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(1920, 1080, false, false, false, false))));
			Text titel = new Text("Spiel Laden");
			Label nutzerHinweis = new Label("Keine Speicherstände vorhanden");
			nutzerHinweis.setTextFill(Color.WHITE);
			nutzerHinweis.setFont(new Font("Lucida Calligraphy Italic", 50.0));
			Button zurueck = new Button("Zurück");
			zurueck.getStyleClass().add("hauptmenubutton");
			zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
			zurueck.setAlignment(Pos.CENTER);

			HBox top = new HBox(titel);
			top.setAlignment(Pos.CENTER);
			VBox center = new VBox(nutzerHinweis, zurueck);
			center.setSpacing(20);
			center.setAlignment(Pos.CENTER);
			this.setTop(top);
			this.setCenter(center);
		}
	}
}