package gamehub;

import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.gamecontroller.GameController;
import party.PartyController;
import party.PartyStatusController;
import statistik.StatistikController;

import java.util.Scanner;

public class GameHubController {
    private GameController gameController;
    private PartyController partyController;
    private HaendlerController haendler;
    private SchmiedeController schmiede;
    private TaverneController taverne;
    private TrainerController trainer;
    private PartyStatusController partystatus;
    private StatistikController statistik;
    private Scanner einleser;
    private int selectedOption = 0;


    public GameHubController(GameController gameController, PartyController partyController, HaendlerController haendler, SchmiedeController schmiede, TaverneController taverne, TrainerController trainer, PartyStatusController partystatus, StatistikController statistik, Scanner einleser) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.haendler = haendler;
        this.schmiede = schmiede;
        this.taverne = taverne;
        this.trainer = trainer;
        this.partystatus = partystatus;
        this.statistik = statistik;
        this.einleser = einleser;
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

    private void hubAnzeigen() {
        String[] menuOption = {"Händler", "Schmiede", "Partystatus Einsehen", "Ausruhen", "Kampf starten", "DEVELOP: Spiel beenden"};


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
                    navigateMenu(-1, menuOption.length);
                    break;
                case 's':
                    navigateMenu(1, menuOption.length);
                    break;
                case 'e':
                    executeSelectedOption(selectedOption, menuOption);
                    break;
                default:
                    System.out.println("Invalid input. Use 'w' to move up, 's' to move down, and e to select.");
                    break;
            }
        }
    }

    private void navigateMenu(int direction, int menuLength) {
        this.selectedOption = (selectedOption + direction + menuLength) % menuLength;
    }

    private void executeSelectedOption(int selectedOption, String[] menuOptions) {
        System.out.println("Executing: " + menuOptions[selectedOption]);
        // Hier kann man einfügen, was passieren soll
        switch (selectedOption) {
//        case 0:
//            newGame();
//            break;
//        case 1:
//            load();
//            break;
            case 6:
                System.exit(0);
                break;
        }
        // anstelle eines Switch Case könnte man auch einfach ein IF nehmen.
    }


