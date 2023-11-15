package gamehub;

import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.gamecontroller.GameController;
import party.PartyController;
import party.PartyStatusController;
import statistik.StatistikController;

public class GameHubController {
    private GameController gameController;
    private PartyController partyController;
    private HaendlerController haendler;
    private SchmiedeController schmiede;
    private TaverneController taverne;
    private TrainerController trainer;
    private PartyStatusController partystatus;
    private StatistikController statistik;

    public GameHubController(GameController gameController, PartyController partyController, HaendlerController haendler, SchmiedeController schmiede, TaverneController taverne, TrainerController trainer, PartyStatusController partystatus, StatistikController statistik) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.haendler = haendler;
        this.schmiede = schmiede;
        this.taverne = taverne;
        this.trainer = trainer;
        this.partystatus = partystatus;
        this.statistik = statistik;
    }

    public PartyStatusController getPartystatus() {
        return partystatus;
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

    public StatistikController getStatistik() {
        return statistik;
    }
}
