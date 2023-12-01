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
        this.viewController = new ViewController(viewController.getPrimary(), hauptmenuController, gameController, viewController.getOberStack());
        hauptmenuController.spielVorhandenProperty().set(true);
        hauptmenuController.setViewController(this.viewController);
        this.gameController = gameController;
        this.partyController = partyController;
        this.hauptmenuController = hauptmenuController;
        this.haendler = new HaendlerController(partyController);
        this.schmiede = new SchmiedeController(partyController);
        this.trainer = new TrainerController(this, partyController, this.viewController);
        this.partystatus = new PartyStatusController(partyController);
        this.feindController = new FeindController();
        this.statistik = statistikController;
        this.taverne = new TaverneController(partyController, statistikController, this, this.viewController);
        this.kampfController = new KampfController(feindController, partyController, statistik, gameController, this,
                hauptmenuController, partystatus, speicherstandController);
        this.speicherstandController = speicherstandController;
        this.gameHubView = new GameHubView(this);
        this.viewController.anmelden(gameHubView, null, AnsichtsTyp.MIT_OVERLAY);
    }

    public void taverneAnzeigen(){
        taverne.taverneAnzeigen();
    }
    public void schmiedeAnzeigen(){
        schmiede.schmiedeAnzeigen();
    }
    public void haendlerAnzeigen(){
        haendler.haendlerAnzeigen(partyController);
    }
    public void partyInventarAnzeigen(){
        partystatus.spielerinventarAnzeige();
    }
    public void trainerAnzeigen(){
        trainer.trainerAnzeigen();
    }
    public void kaempfenAnzeigen(){
        kampfController.kampfStarten();
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