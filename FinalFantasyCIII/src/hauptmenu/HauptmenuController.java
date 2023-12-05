package hauptmenu;

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.neuesspiel.NeuesSpielView;
import hauptmenu.speicherstand.SpeicherstandController;
import hauptmenu.speicherstand.SpeicherstandLadenView;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.stage.Stage;
import party.PartyController;
import statistik.StatistikController;
import view.AnsichtsTyp;
import view.ViewController;

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

	// TODO: JAVADOC SpielLaden
	public void spielLaden() {
		Node speicherstandLaden = new SpeicherstandLadenView(viewController, speicherstandController, partyController,
				statistikController, gameController, gameHubController);
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
}
