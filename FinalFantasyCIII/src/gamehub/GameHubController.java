package gamehub;

import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;
import kampf.KampfController;
import party.PartyStatusController;
import statistik.StatistikController;

import java.io.IOException;
import java.util.Scanner;

public class GameHubController {
    private static int selectedOption = 0;
    private static Scanner einleser;
    private GameController gameController;
    private PartyController partyController;
    private HaendlerController haendler;
    private SchmiedeController schmiede;
    private TaverneController taverne;
    private KampfController kampfController;
    private TrainerController trainer;
    private PartyStatusController partystatus;
    private StatistikController statistik;

    public KampfController getKampfController() {
        return kampfController;
    }

    public GameHubController(GameController gameController, PartyController partyController, HaendlerController haendler, SchmiedeController schmiede, TaverneController taverne, TrainerController trainer, PartyStatusController partystatus, StatistikController statistik, KampfController kampfController) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.haendler = haendler;
        this.schmiede = schmiede;
        this.kampfController = kampfController;
        this.taverne = taverne;
        this.trainer = trainer;
        this.partystatus = partystatus;
        this.statistik = statistik;
        einleser = ScannerHelfer.sc;
    }

    public void hubAnzeigen() throws IOException, InterruptedException {
        String[] menuOption = {"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status", "Kaempfen Gehen", "DEVELOP: Spiel beenden"};


        while (true) {
            System.out.println(Farbauswahl.RED + "Select an option:" + Farbauswahl.RESET);

            for (int i = 0; i < menuOption.length; i++) {
                if (i == selectedOption) {
                    // Hier wird der Bereich gezeichnet wo man sich gerade befindet und in Gelb dargestellt
                    System.out.println(Farbauswahl.YELLOW + ">> " + menuOption[i] + Farbauswahl.RESET);
                } else {
                    // Hier wird alles gezeichnet was momentan nicht ausgewaehlt ist.
                    System.out.println(Farbauswahl.GREEN + "   " + menuOption[i] + Farbauswahl.RESET);
                }
            }

            char userInput = einleser.next().charAt(0);

            switch (userInput) {
                case 'w':
                    KonsolenAssistent.clear();
                    this.navigateMenu(-1, menuOption.length);
                    break;
                case 's':
                    KonsolenAssistent.clear();
                    this.navigateMenu(1, menuOption.length);
                    break;
                case 'e':
                    KonsolenAssistent.clear();
                    this.executeSelectedOption(selectedOption, menuOption);
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Invalid input. Use 'w' to move up, 's' to move down, and e to select.");
                    break;
            }
        }
    }

    private void navigateMenu(int direction, int menuLength) {
        selectedOption = (selectedOption + direction + menuLength) % menuLength;
    }

    private void executeSelectedOption(int selectedOption, String[] menuOptions) {
        System.out.println(Farbauswahl.RED + "Starte: " + menuOptions[selectedOption]);
        System.out.println(selectedOption);
        // Hier kann man einfügen, was passieren soll
        // {"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status", "Kaempfen Gehen", "DEVELOP: Spiel beenden"}
        switch (selectedOption) {
           // case 0:
           //     this.haendler.haendlerAnzeigen();
           //     break;
           // case 1:
           //     this.schmiede.schmiedAnzeigen();
           //     break;
           // case 2:
           //     this.taverne.taverneAnzeigen();
           //     break;
           // case 3:
           //     this.trainer.trainerAnzeigen();
           //     break;
           // case 4:
           //     this.partystatus.partystatusmenuAnzeigen();
           //     break;
           // case 5:
           //     this.kampfController.kampfBeginnen();
            case 6:
                System.exit(0);
                break;
        }
        // anstelle eines Switch Case könnte man auch einfach ein IF nehmen.
    }

    public Scanner getEinleser() {
        return einleser;
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


