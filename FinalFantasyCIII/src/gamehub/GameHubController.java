package gamehub;

import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import party.PartyController;
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
        einleser = ScannerHelfer.sc;
    }

    public static void hubAnzeigen() throws IOException, InterruptedException {
        String[] menuOption = {"H'ä'ndler", "Schmiede", "Partystatus Einsehen", "Ausruhen", "Kampf starten", "DEVELOP: Spiel beenden"};


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
                    navigateMenu(-1, menuOption.length);
                    break;
                case 's':
                    KonsolenAssistent.clear();
                    navigateMenu(1, menuOption.length);
                    break;
                case 'e':
                    KonsolenAssistent.clear();
                    executeSelectedOption(selectedOption, menuOption);
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Invalid input. Use 'w' to move up, 's' to move down, and e to select.");
                    break;
            }
        }
    }

    private static void navigateMenu(int direction, int menuLength) {
        selectedOption = (selectedOption + direction + menuLength) % menuLength;
    }

    private static void executeSelectedOption(int selectedOption, String[] menuOptions) {
        System.out.println("Executing: " + menuOptions[selectedOption]);
        System.out.println(selectedOption);
        // Hier kann man einfügen, was passieren soll
        switch (selectedOption) {
//        case 0:
//            newGame();
//            break;
//        case 1:
//            load();
//            break;
            case 5:
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


