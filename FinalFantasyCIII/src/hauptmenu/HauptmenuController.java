package hauptmenu;

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.neuesspiel.NeuesSpielMethoden;
import hauptmenu.speicherstand.Speicherstand;
import hauptmenu.speicherstand.SpeicherstandController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.stage.Stage;
import party.PartyController;
import statistik.StatistikController;
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
     * Steuert das initiale Hauptmenue des Spiels. Hier kann zwischen verschiedenen
     * Optionen wie einem neuen Spiel, dem Laden eines Spiels, den Optionen, den
     * Credits und Spiel beenden gewaehlt werden.
     *
     * @author OF Ridder
     * @since 16.11.2023
     */
    public void hauptmenuAnzeigen() {
        //viewController.toFront("hauptmenu");
    }

    /**
     * Erste Implementation von neuesSpiel. Methoden ausgelagert zu
     * NeuesSpielMethoden.
     *
     * @author F Lang
     * @since 16.11.2023
     */
    public void neuesSpiel() {
        //TODO: VIEW ERSTELLEN ALS KLASSE DIE NODE (HBOX/VBOX/ETC) EXTENDED UND IM VIEWCONTROLLER ALS ATTRIBUT HÄLT
        try {
            //viewController.toFront("neuesSpiel");
            NeuesSpielMethoden ngm = new NeuesSpielMethoden();
            System.out.println(
                    "Geschichte des Hauptcharakters: Der Hauptcharakter des Spielers hat einen ganzen Haufen Git-Tickets in ueberzogenem Umfang erhalten und ist deswegen sauer.");
            partyController = ngm.neueParty();
            gameController = new GameController(true, partyController);
            statistikController = new StatistikController();
            gameHubController = new GameHubController(gameController, partyController, statistikController, this,
                    speicherstandController);
            KonsolenAssistent.clear();
            gameHubController.hubAnzeigen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: JAVADOC SpielLaden
    public void spielLaden() {
        try {
            Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
            if (auswahl != null) {
                partyController = new PartyController(auswahl.getParty());
                gameController = new GameController(auswahl.getSchwierigkeitsgrad(), auswahl.isHardcore(), partyController);
                statistikController = new StatistikController(auswahl.getStatistik());
                gameHubController = new GameHubController(gameController, partyController, statistikController, this,
                        speicherstandController);
                KonsolenAssistent.clear();
                gameHubController.hubAnzeigen();
            }
            hauptmenuAnzeigen();
        } catch (Exception e) {
            System.err.println("Keine Spielstaende vorhanden!");
            hauptmenuAnzeigen();
        }
    }


    /**
     * Gibt die Optionsansicht aus
     *
     * @author OF Schroeder
     * @since 15.11.2023
     */

    public void optionen() {
        //TODO IMPLEMENT SHIT
        //viewController.toFront("optionen");
    }

    /**
     * @author SF Maass
     * @since 15.11.2023
     */
    public void credits() {
        //TODO IMPLEMENT SHIT
        //viewController.toFront("credits");
    }

    /**
     * Beendet das Spiel
     *
     * @author Nick, Dennis
     * @since 30.11.2023
     */
    public void spielBeenden() {
        Platform.exit();
//        System.exit(0);
    }

    /**
     * angepasstes hauptmenueAnzeigen zum aufrufen aus dem GameHub: Hier kann
     * zwischen Spiel speichern, Spiel laden, Schwierigkeit aendern, zurueck ins
     * GameHub, oder Spiel beenden gewaehlt werden.
     *
     * @author OF Ridder
     * @since 20.11.2023
     */
    public void hauptmenuAnzeigenLaufendesSpiel() {
        //TODO AUF GRAFISCHE OBERFLÄCHE HEBEN
        try {
            // BANNER
            System.out.println(" _____                                                          _____ \n"
                    + "( ___ )                                                        ( ___ )\n"
                    + " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n"
                    + " |   |  _   _                   _                        _   _  |   | \n"
                    + " |   | | | | | __ _ _   _ _ __ | |_ _ __ ___   ___ _ __ (_) (_) |   | \n"
                    + " |   | | |_| |/ _` | | | | '_ \\| __| '_ ` _ \\ / _ \\ '_ \\| | | | |   | \n"
                    + " |   | |  _  | (_| | |_| | |_) | |_| | | | | |  __/ | | | |_| | |   | \n"
                    + " |   | |_| |_|\\__,_|\\__,_| .__/ \\__|_| |_| |_|\\___|_| |_|\\__,_| |   | \n"
                    + " |   |                   |_|                                    |   | \n"
                    + " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n"
                    + "(_____)                                                        (_____)\n");

            // Hauptmenue-Auswahlmoeglichkeiten
            System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen:" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "1 = Spiel speichern" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "2 = Spiel laden" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "3 = Schwierigkeiten aendern" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "4 = zurueck ins GameHub" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "5 = Spiel beenden" + Farbauswahl.RESET);
            int eingabe = ScannerHelfer.nextInt();
            switch (eingabe) {
                case 1:
                    speicherstandController
                            .speichern(new Speicherstand(partyController.getParty(), gameController.getSchwierigkeitsgrad(),
                                    gameController.isHardcore(), statistikController.getStatistik()));
                    break;
                case 2:
                    Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
                    partyController = new PartyController(auswahl.getParty());
                    gameController = new GameController(auswahl.getSchwierigkeitsgrad(), auswahl.isHardcore(),
                            partyController);
                    statistikController = new StatistikController(auswahl.getStatistik());
                    gameHubController = new GameHubController(gameController, partyController, statistikController, this,
                            speicherstandController);
                    gameHubController.hubAnzeigen();
                    break;
                case 3:
                    if (gameController == null) {
                        System.err.println("Erstellen Sie ein neues Spiel, oder laden Sie ein vorhandenes!");
                        hauptmenuAnzeigen();
                    } else {
                        gameController.schwierigkeitsAuswahl();
                        gameHubController.hubAnzeigen();
                    }
                    break;
                case 4:
                    KonsolenAssistent.clear();
                    break;
                case 5:
                    KonsolenAssistent.clear();
                    System.exit(0);
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!"
                            + Farbauswahl.RESET);
                    hauptmenuAnzeigen();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BooleanProperty spielVorhandenProperty() {
        return spielVorhanden;
    }
}
