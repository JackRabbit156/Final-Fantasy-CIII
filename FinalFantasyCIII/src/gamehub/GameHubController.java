package gamehub;

import charakter.controller.FeindController;
import gamehub.haendler.HaendlerController;
import gamehub.schmiede.SchmiedeController;
import gamehub.taverne.TaverneController;
import gamehub.trainer.TrainerController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.AsciiHelfer;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import kampf.KampfController;
import party.PartyController;
import party.PartyStatusController;
import statistik.StatistikController;

/**
 * Der Hauptcontroller für den Game Hub, der für die Koordination verschiedener
 * Spiel-Funktionalitäten verantwortlich ist.
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
    private int ausgewaehlteOption = 0;
    private boolean menuaktiv;

    /**
     * Konstruktor für den GameHubController.
     *
     * @param gameController  Der GameController.
     * @param partyController Der PartyController.
     *
     * @author HF Rode
     */
    public GameHubController(GameController gameController, PartyController partyController,
                             StatistikController statistikController, HauptmenuController hauptmenuController) {
        this.gameController = gameController;
        this.partyController = partyController;
        this.hauptmenuController = hauptmenuController;
        this.haendler = new HaendlerController(partyController);
        this.schmiede = new SchmiedeController(partyController);
        this.trainer = new TrainerController();
        this.partystatus = new PartyStatusController(partyController);
        this.feindController = new FeindController();
        this.statistik = statistikController;
        this.taverne = new TaverneController(partyController, statistikController, this);
        this.kampfController = new KampfController(feindController, partyController, statistik, gameController, this,
                hauptmenuController, partystatus);
        menuaktiv = true;
    }

    /**
     * Zeigt den Game Hub an und behandelt Benutzereingaben für die Navigation. Malt
     * eine MAP die sich dynamisch je nach auswahl an
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    public void hubAnzeigen() {
        menuaktiv = true;
        String[] menuOption = new String[]{"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status",
                "Kaempfen Gehen", "Zurueck zum Hauptmenue"};

        while (menuaktiv) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
            if (ausgewaehlteOption == 0) {
                AsciiHelfer.stadtHaendler();
            } else if (ausgewaehlteOption == 1) {
                AsciiHelfer.stadtSchmiede();
            } else if (ausgewaehlteOption == 2) {
                AsciiHelfer.stadtTaverne();
            } else if (ausgewaehlteOption == 3) {
                AsciiHelfer.stadtTrainer();
            } else if (ausgewaehlteOption == 4) {
                AsciiHelfer.stadtPartyStatus();
            } else if (ausgewaehlteOption == 5) {
                AsciiHelfer.stadtKampf();
            } else if (ausgewaehlteOption == 6) {
                AsciiHelfer.stadtPartyStatus();
            }
            for (int i = 0; i < menuOption.length; i++) {
                if (i == ausgewaehlteOption) {
                    System.out.println(Farbauswahl.YELLOW + ">> " + menuOption[i] + Farbauswahl.RESET);
                } else {
                    System.out.println(Farbauswahl.GREEN + "   " + menuOption[i] + Farbauswahl.RESET);
                }
            }

            char nutzerEingabe = ScannerHelfer.nextChar();

            switch (nutzerEingabe) {
                case 'w':
                    KonsolenAssistent.clear();
                    this.menueNavigieren(-1, menuOption.length);
                    break;
                case 's':
                    KonsolenAssistent.clear();
                    this.menueNavigieren(1, menuOption.length);
                    break;
                case 'e':
                    KonsolenAssistent.clear();
                    this.auswahlBestaetigen();
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println(
                            "Fehlerhafte Eingabe. Benutze 'w' zum Hochgehen, 's' um runter zu gehen  und die Entertaste um zu bestaetigen.");
                    break;
            }
        }
    }

    /**
     * Navigiert durch die Menüoptionen basierend auf der Benutzereingabe.
     *
     * @param direction  Die Richtung der Navigation (-1 für nach oben, 1 für nach
     *                   unten).
     * @param menuLength Die Länge des Menüs.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private void menueNavigieren(int direction, int menuLength) {
        ausgewaehlteOption = (ausgewaehlteOption + direction + menuLength) % menuLength;
    }

    /**
     * Führt die ausgewählte Menüoption basierend auf der Benutzereingabe aus.
     *
     * @author HF Rode
     * @since 18.11.2023
     * zurück wollt einfach euer menü beenden da dieses menü eures aufruft
     * kommt ihr automatisch hierhin zurück.
     */
    private void auswahlBestaetigen() {
        switch (ausgewaehlteOption) {
            case 0:
                this.haendler.haendlerAnzeigen(partyController);
                break;
            case 1:
                this.schmiede.schmiedeAnzeigen();
                break;
            case 2:
                this.taverne.taverneAnzeigen();
                break;
            case 3:
                // this.trainer.trainerAnzeigen();
                break;
            case 4:
                this.partystatus.spielerinventarAnzeige();
                break;
            case 5:
                this.kampfController = new KampfController(feindController, partyController, statistik, gameController, this, hauptmenuController, partystatus);
                this.kampfController.kampfStarten();
            case 6:
                this.hauptmenuController.hauptmenuAnzeigen();
                break;
            default:
                System.out.println(
                        "Fehlerhafte Eingabe was zum, wie auch immer ihr das geschafft habt tapferer held, sieht dies als easteregg und jetzt nochmal");
                break;
        }
    }
}
