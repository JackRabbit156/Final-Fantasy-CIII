package de.bundeswehr.auf.final_fantasy.menu.hauptmenu;

import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.controller.GameController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.neuesspiel.NeuesSpielView;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand.view.SpeicherstandLadenView;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.stage.Stage;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import de.bundeswehr.auf.final_fantasy.view.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.view.ViewController;

public class HauptmenuController {

	private GameController gameController;
	private final SpeicherstandController speicherstandController;
	private PartyController partyController;
	private GameHubController gameHubController;
	private StatistikController statistikController = new StatistikController();
	private ViewController viewController;
	private BooleanProperty spielVorhanden = new SimpleBooleanProperty(false);

	public HauptmenuController(Stage primaryStage) {
		this.viewController = new ViewController(primaryStage, this);
		this.speicherstandController = new SpeicherstandController(this.viewController);
	}

	/**
	 * Erste Implementation von neuesSpiel. Methoden ausgelagert zu
	 * NeuesSpielMethoden.
	 *
	 * @author F Lang
	 * @since 16.11.2023
	 */
	public void neuesSpiel() {
		Node neuesSpiel = new NeuesSpielView(viewController, this, speicherstandController);
		viewController.anmelden(neuesSpiel, null, AnsichtsTyp.OHNE_OVERLAY);
	}

	/**
	 * Funktion zum Öffnen der Spielstandladen-Ansicht um einene Spielstand auszuwählen und zu laden
	 * @author Melvin
	 * @since 05.12.2023
	 */
	public void spielLaden() {
		Node speicherstandLaden = new SpeicherstandLadenView(viewController, speicherstandController, this);
		viewController.anmelden(speicherstandLaden, null, AnsichtsTyp.OHNE_OVERLAY);
	}

	/**
	 * @author SF Maass
	 * @since 04.12.23
	 */
	public void credits() {
		viewController.creditsAnzeigen();
	}

	/**
	 * Beendet das Spiel
	 *
	 * @author Nick, Dennis
	 * @since 30.11.2023
	 */
	public void spielBeenden() {
		Platform.exit();
	}

	public BooleanProperty spielVorhandenProperty() {
		return spielVorhanden;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public GameHubController getGameHubController() {
		return gameHubController;
	}

	public void setGameHubController(GameHubController gameHubController) {
		this.gameHubController = gameHubController;
	}
}
