package gamehub;

import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import kampf.KampfController;
import party.PartyController;
import party.PartyStatusController;
import statistik.StatistikController;

import java.io.IOException;
import java.util.Scanner;


/**
 * Der Hauptcontroller für den Game Hub, der für die Koordination verschiedener Spiel-Funktionalitäten verantwortlich ist.
 *
 * @author HF Rode
 * @version 0.9
 */
public class GameHubController {
    private static int ausgewaehlteOption = 0;
    private final GameController gameController;
    private final PartyController partyController;
    private final HaendlerController haendler;
    private final SchmiedeController schmiede;
    private final TaverneController taverne;
    private final KampfController kampfController;
    private final TrainerController trainer;
    private final PartyStatusController partystatus;
    private final StatistikController statistik;

    /**
     * Konstruktor für den GameHubController.
     *
     * @param gameController  Der GameController.
     * @param partyController Der PartyController.
     *
     * @author HF Rode
     */
    public GameHubController(GameController gameController, PartyController partyController, StatistikController statistikController) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.haendler = new HaendlerController();
        this.schmiede = new SchmiedeController();
        this.kampfController = new KampfController();
        this.taverne = new TaverneController();
        this.trainer = new TrainerController();
        this.partystatus = new PartyStatusController();
        this.statistik = statistikController;
    }

    /**
     * Zeigt den Game Hub an und behandelt Benutzereingaben für die Navigation.
     *
     * @author HF Rode
     */
    public void hubAnzeigen() {
        String[] menuOption = {"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status", "Kaempfen Gehen", "DEVELOP: Spiel beenden"};


        while (true) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
            // TODO Hier kommt die MAP hin für die ansicht später
            for (int i = 0; i < menuOption.length; i++) {
                if (i == ausgewaehlteOption) {
                    System.out.println(Farbauswahl.YELLOW + ">> " + menuOption[i] + Farbauswahl.RESET);
                } else {
                    System.out.println(Farbauswahl.GREEN + "   " + menuOption[i] + Farbauswahl.RESET);
                }
            }

            char userInput = ScannerHelfer.nextChar();

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
                    this.executeSelectedOption(ausgewaehlteOption, menuOption);
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Invalid input. Use 'w' to move up, 's' to move down, and e to select.");
                    break;
            }
        }
    }

    /**
     * Navigiert durch die Menüoptionen basierend auf der Benutzereingabe.
     *
     * @param direction  Die Richtung der Navigation (-1 für nach oben, 1 für nach unten).
     * @param menuLength Die Länge des Menüs.
     *
     * @author HF Rode
     */
    private void navigateMenu(int direction, int menuLength) {
        ausgewaehlteOption = (ausgewaehlteOption + direction + menuLength) % menuLength;
    }

    /**
     * Führt die ausgewählte Menüoption basierend auf der Benutzereingabe aus.
     *
     * @param ausgewaehlteOption Die Indexnummer der ausgewählten Option.
     * @param menuOption         Das Array der Menüoptionen.
     *
     * @author HF Rode
     */
    private void executeSelectedOption(int ausgewaehlteOption, String[] menuOption) {
        System.out.println(Farbauswahl.RED + "Starte: " + menuOption[ausgewaehlteOption]);
        System.out.println(ausgewaehlteOption);
        switch (ausgewaehlteOption) {
            /*
            TODO Bitte das entfernen was benötigt wird.
             */
//             case 0:
//                 this.haendler.haendlerAnzeigen();
//                 break;
//             case 1:
//                 this.schmiede.schmiedAnzeigen();
//                 break;
//             case 2:
//                 this.taverne.taverneAnzeigen();
//                 break;
//             case 3:
//                 this.trainer.trainerAnzeigen();
//                 break;
//             case 4:
//                 this.partystatus.partystatusmenuAnzeigen();
//                 break;
//             case 5:
//                 this.kampfController.kampfBeginnen();
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Keine gültige funktion");
                break;
        }
    }

    /**
     * Gibt den KampfController zurück.
     *
     * @return Der KampfController.
     *
     * @autor HF Rode
     */
    public KampfController getKampfController() {
        return kampfController;
    }

    /**
     * Gibt den PartyStatusController zurück.
     *
     * @return Der PartyStatusController.
     *
     * @autor HF Rode
     */
    public PartyStatusController getPartystatus() {
        return partystatus;
    }

    /**
     * Gibt den GameController zurück.
     *
     * @return Der GameController.
     *
     * @autor HF Rode
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Gibt den PartyController zurück.
     *
     * @return Der PartyController.
     *
     * @autor HF Rode
     */
    public PartyController getPartyController() {
        return partyController;
    }

    /**
     * Gibt den HaendlerController zurück.
     *
     * @return Der HaendlerController.
     *
     * @autor HF Rode
     */
    public HaendlerController getHaendler() {
        return haendler;
    }

    /**
     * Gibt den SchmiedeController zurück.
     *
     * @return Der SchmiedeController.
     *
     * @autor HF Rode
     */
    public SchmiedeController getSchmiede() {
        return schmiede;
    }

    /**
     * Gibt den TaverneController zurück.
     *
     * @return Der TaverneController.
     *
     * @autor HF Rode
     */
    public TaverneController getTaverne() {
        return taverne;
    }

    /**
     * Gibt den TrainerController zurück.
     *
     * @return Der TrainerController.
     *
     * @autor HF Rode
     */
    public TrainerController getTrainer() {
        return trainer;
    }

    /**
     * Gibt den StatistikController zurück.
     *
     * @return Der StatistikController.
     *
     * @autor HF Rode
     */
    public StatistikController getStatistik() {
        return statistik;
    }
}


