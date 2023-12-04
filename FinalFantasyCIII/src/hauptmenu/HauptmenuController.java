package hauptmenu;

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.neuesspiel.NeuesSpielView;
import hauptmenu.speicherstand.Speicherstand;
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
		speicherstandController = new SpeicherstandController();
		this.viewController = new ViewController(primaryStage, this);
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
	 * @since 15.11.2023
	 */
	public void credits() {
		// TODO IMPLEMENT SHIT
		// viewController.toFront("credits");
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

	/**
	 * @author Nick
	 * @since 30.11.2023
	 */
	public void speichern() {
		try {
			speicherstandController
					.speichern(new Speicherstand(partyController.getParty(), gameController.getSchwierigkeitsgrad(),
							gameController.isHardcore(), statistikController.getStatistik()));
		} catch (Exception e) {
			System.out.println("Melvin wollte Exceptions bis zur höchsten Ebene geben. Ja Moin! Trottel...");
		}
	}

	public BooleanProperty spielVorhandenProperty() {
		return spielVorhanden;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
}
