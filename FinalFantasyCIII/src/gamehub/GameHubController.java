package gamehub;

import java.util.ArrayList;
import java.util.List;

import charakter.controller.FeindController;
import gamehub.view.GameHubView;
import haendler.HaendlerController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.Speicherstand;
import hauptmenu.speicherstand.SpeicherstandController;
import inventar.InventarController;
import javafx.scene.control.Button;
import kampf.KampfController;
import party.PartyController;
import partystatus.PartyStatusController;
import schmiede.SchmiedeController;
import statistik.StatistikController;
import taverne.TaverneController;
import trainer.TrainerController;
import view.AnsichtsTyp;
import view.ViewController;

/**
 * Der Hauptcontroller fuer den Game Hub, der fuer die Koordination
 * verschiedener Spiel-Funktionalitaeten verantwortlich ist.
 *
 * @author HF Rode
 * @since 18.11.2023
 */
public class GameHubController {
	private InventarController inventarController;
	private GameController gameController;
	private PartyController partyController;
	private HaendlerController haendler;
	private SchmiedeController schmiede;
	private TaverneController taverne;
	private TrainerController trainer;
	private PartyStatusController partystatus;
	private StatistikController statistik;
	private HauptmenuController hauptmenuController;
	private FeindController feindController;
	private KampfController kampfController;
	private SpeicherstandController speicherstandController;
	private ViewController viewController;
	private GameHubView gameHubView;

	/**
	 * Die Klasse GameHubController koordiniert die Steuerung und Interaktionen im
	 * Game Hub, der als zentrale Anlaufstelle für verschiedene Spielaktivitäten
	 * dient.
	 *
	 * Sie erstellt und verwaltet Instanzen verschiedener Controller, die für
	 * spezifische Bereiche wie Händler, Schmiede, Taverne, Trainer, Inventar,
	 * Partystatus und Kämpfe zuständig sind. Zudem verbindet sie diese mit der
	 * entsprechenden grafischen Benutzeroberfläche (GameHubView) und steuert die
	 * Navigation und Anzeige der jeweiligen Aktivitäten.
	 *
	 * Die Klasse GameHubController integriert Funktionen für die Steuerung von
	 * Händler-, Schmiede-, Taverne-, Trainer-, Inventar-, Partystatus- und
	 * Kämpfen-Aktivitäten im Spiel.
	 *
	 * @param gameController          Der GameController für die allgemeine
	 *                                Spielsteuerung.
	 * @param partyController         Der PartyController für die Verwaltung der
	 *                                Spielerparty.
	 * @param statistikController     Der StatistikController für die
	 *                                Spielerstatistiken.
	 * @param hauptmenuController     Der HauptmenuController für die Navigation im
	 *                                Hauptmenü.
	 * @param speicherstandController Der SpeicherstandController für die
	 *                                Spielstandverwaltung.
	 * @param viewController          Der ViewController für die Steuerung der
	 *                                grafischen Benutzeroberfläche.
	 *
	 * @author Rode
	 * @since 06.12.2023
	 */
	public GameHubController(GameController gameController, PartyController partyController,
			StatistikController statistikController, HauptmenuController hauptmenuController,
			SpeicherstandController speicherstandController, ViewController viewController) {
		viewController.getOberStack().getChildren().clear();
		this.viewController = new ViewController(viewController.getPrimary(), hauptmenuController, gameController,
				partyController, viewController.getOberStack(), this);
		hauptmenuController.spielVorhandenProperty().set(true);
		hauptmenuController.setViewController(this.viewController);
		hauptmenuController.setGameHubController(this);
		this.gameController = gameController;
		this.partyController = partyController;
		this.hauptmenuController = hauptmenuController;
		this.haendler = new HaendlerController(partyController, this.viewController);
		this.schmiede = new SchmiedeController(partyController, this.viewController);
		this.trainer = new TrainerController(this, partyController, this.viewController);
		this.inventarController = new InventarController(partyController, this.viewController);
		this.partystatus = new PartyStatusController(partyController, this.viewController, inventarController);
		this.feindController = new FeindController();
		this.statistik = statistikController;
		this.taverne = new TaverneController(partyController, statistikController, this, this.viewController);
		this.kampfController = new KampfController(this.feindController, partyController, this.statistik,
				gameController, this, hauptmenuController, this.partystatus, speicherstandController,
				this.viewController);
		this.speicherstandController = new SpeicherstandController(this.viewController);
		this.gameHubView = new GameHubView(this);

		Button btnSchmiede = new Button("Schmiede");
		Button btnHaendler = new Button("Händler");
		Button btnTrainer = new Button("Trainer");
		Button btnTaverne = new Button("Taverne");
		Button btnPartyInventar = new Button("Party Inventar");
		Button btnKaempfen = new Button("Kämpfen");
		Button btnPartyStatus = new Button("Party Status");

		btnKaempfen.setOnMouseEntered(event -> gameHubView.ausloeserKampfHover());

		btnKaempfen.setOnMouseClicked(event -> kaempfenAnzeigen());

		btnKaempfen.setOnMouseExited(event -> gameHubView.entfernenKampfHover());

		btnPartyInventar.setOnMouseEntered(event -> gameHubView.ausloeserPartyHover());
		btnPartyInventar.setOnMouseClicked(event -> this.partyInventarAnzeigen());
		btnPartyInventar.setOnMouseExited(event -> gameHubView.entfernenPartyHover());

		btnTaverne.setOnMouseEntered(event -> gameHubView.ausloeserTaverneHover());
		btnTaverne.setOnMouseClicked(event -> this.taverneAnzeigen());
		btnTaverne.setOnMouseExited(event -> gameHubView.entfernenTaverneHover());

		btnTrainer.setOnMouseEntered(event -> gameHubView.ausloeserTrainerHover());
		btnTrainer.setOnMouseClicked(event -> this.trainerAnzeigen());
		btnTrainer.setOnMouseExited(event -> gameHubView.entfernenTrainerHover());

		btnHaendler.setOnMouseEntered(event -> gameHubView.ausloeserHaendlerHover());
		btnHaendler.setOnMouseClicked(event -> this.haendlerAnzeigen());
		btnHaendler.setOnMouseExited(event -> gameHubView.entfernenHaendlerHover());

		btnSchmiede.setOnMouseEntered(event -> gameHubView.ausloeserSchmiedeHover());
		btnSchmiede.setOnMouseClicked(event -> this.schmiedeAnzeigen());
		btnSchmiede.setOnMouseExited(event -> gameHubView.entfernenSchmiedeHover());

		btnPartyStatus.setOnMouseEntered(event -> gameHubView.ausloeserPartyStatusHover());
		btnPartyStatus.setOnMouseClicked(event -> this.partyStatusAnzeigen());
		btnPartyStatus.setOnMouseExited(event -> gameHubView.entfernenPartyStatusHover());

		List<Button> lstBtnhauptmenu = new ArrayList<>();
		lstBtnhauptmenu.add(btnHaendler);
		lstBtnhauptmenu.add(btnSchmiede);
		lstBtnhauptmenu.add(btnTaverne);
		lstBtnhauptmenu.add(btnTrainer);
		lstBtnhauptmenu.add(btnPartyInventar);
		lstBtnhauptmenu.add(btnPartyStatus);
		lstBtnhauptmenu.add(btnKaempfen);
		this.viewController.anmelden(gameHubView, lstBtnhauptmenu, AnsichtsTyp.MIT_OVERLAY);
	}

	/**
	 * Die Methode destroy() setzt alle internen Referenzen auf null, um
	 * sicherzustellen, dass keine weiteren Operationen auf den entsprechenden
	 * Instanzen durchgeführt werden können. Dies dient der Ressourcenfreigabe und
	 * der Bereinigung von nicht mehr benötigten Objekten. Vor allem beim Spielladen
	 * ist es wichtig dies durchzuführen.
	 *
	 * @author Rode
	 * @since 06.12.2023
	 */
	public void destroy() {
		inventarController = null;
		gameController = null;
		partyController = null;
		haendler = null;
		schmiede = null;
		taverne = null;
		trainer = null;
		partystatus = null;
		statistik = null;
		hauptmenuController.setGameHubController(null);
		hauptmenuController = null;
		feindController = null;
		kampfController = null;
		speicherstandController = null;
		viewController = null;
		gameHubView = null;
	}

	public void taverneAnzeigen() {
		taverne.taverneAnzeigen();
	}

	public void schmiedeAnzeigen() {
		schmiede.schmiedeAnzeigen();
	}

	public void haendlerAnzeigen() {
		haendler.haendlerAnzeigen(partyController);
	}

	public void partyStatusAnzeigen() {
		partystatus.partyStatusAnzeigen();
	}

	public void partyInventarAnzeigen() {
		inventarController.spielerinventarAnzeige();
	}

	public void trainerAnzeigen() {
		trainer.trainerAnzeigen();
	}

	public void kaempfenAnzeigen() {
		kampfController.kampfStarten();
	}

	public void speichern() {
		speicherstandController.speichern(new Speicherstand(partyController.getParty(),
				gameController.getSchwierigkeitsgrad(), gameController.isHardcore(), statistik.getStatistik()));
	}

	public GameController getGameController() {
		return gameController;
	}

	public PartyController getPartyController() {
		return partyController;
	}

	public HaendlerController getHaendler() {
		return haendler;
	}

	public SchmiedeController getSchmiede() {
		return schmiede;
	}

	public TaverneController getTaverne() {
		return taverne;
	}

	public TrainerController getTrainer() {
		return trainer;
	}

	public PartyStatusController getPartystatus() {
		return partystatus;
	}

	public StatistikController getStatistik() {
		return statistik;
	}

	public HauptmenuController getHauptmenuController() {
		return hauptmenuController;
	}

	public FeindController getFeindController() {
		return feindController;
	}

	public KampfController getKampfController() {
		return kampfController;
	}

	public SpeicherstandController getSpeicherstandController() {
		return speicherstandController;
	}

	public ViewController getViewController() {
		return viewController;
	}

	public GameHubView getGameHubView() {
		return gameHubView;
	}
}