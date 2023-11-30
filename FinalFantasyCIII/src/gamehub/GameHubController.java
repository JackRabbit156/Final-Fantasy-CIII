package gamehub;

import charakter.controller.FeindController;
import gamehub.view.GameHubView;
import haendler.HaendlerController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import kampf.KampfController;
import party.PartyController;
import party.PartyStatusController;
import schmiede.SchmiedeController;
import statistik.StatistikController;
import taverne.TaverneController;
import trainer.TrainerController;
import view.ViewController;


/**
 * Der Hauptcontroller fuer den Game Hub, der fuer die Koordination
 * verschiedener Spiel-Funktionalitaeten verantwortlich ist.
 *
 * @author HF Rode
 * @since 18.11.2023
 */
public class GameHubController {
    private final GameController gameController;
    private final PartyController partyController;
    private final HaendlerController haendler;
    private final SchmiedeController schmiede;
    private final TaverneController taverne;
    private final TrainerController trainer;
    private final PartyStatusController partystatus;
    private final StatistikController statistik;
    private final HauptmenuController hauptmenuController;
    private final FeindController feindController;
    private KampfController kampfController;
    private SpeicherstandController speicherstandController;
    private ViewController viewController;
    private GameHubView gameHubView;

    /**
     * Konstruktor für den GameHubController.
     *
     * @param gameController  Der GameController.
     * @param partyController Der PartyController.
     *
     * @author HF Rode
     */
    public GameHubController(GameController gameController, PartyController partyController,
                             StatistikController statistikController, HauptmenuController hauptmenuController,
                             SpeicherstandController speicherstandController, ViewController viewController) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.hauptmenuController = hauptmenuController;
        this.haendler = new HaendlerController(partyController);
        this.schmiede = new SchmiedeController(partyController);
        this.trainer = new TrainerController(this, partyController);
        this.partystatus = new PartyStatusController(partyController);
        this.feindController = new FeindController();
        this.statistik = statistikController;
        this.taverne = new TaverneController(partyController, statistikController, this);
        this.kampfController = new KampfController(feindController, partyController, statistik, gameController, this,
                hauptmenuController, partystatus, speicherstandController);
        this.speicherstandController = speicherstandController;
        this.viewController = viewController;
        this.gameHubView = new GameHubView(this);
        viewController.ansichtHinzufuegen(gameHubView);
    }
}