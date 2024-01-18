package de.bundeswehr.auf.final_fantasy.menu.hauptmenu;

import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.view.NeuesSpielView;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.view.SpeicherstandLadenView;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.stage.Stage;

public class HauptmenuController {

    private GameHubController gameHubController;
    private final SpeicherstandController speicherstandController;
    private final BooleanProperty spielVorhanden = new SimpleBooleanProperty(false);
    private final StatistikController statistikController = new StatistikController();
    private ViewController viewController;

    public HauptmenuController(Stage primaryStage) {
        this.viewController = new ViewController(primaryStage, this);
        this.speicherstandController = new SpeicherstandController(this.viewController);
    }

    /**
     * @author SF Maass
     * @since 04.12.23
     */
    public void credits() {
        viewController.creditsAnzeigen();
    }

    public GameHubController getGameHubController() {
        return gameHubController;
    }

    /**
     * @author OFR Rieger
     * @since 18.01.24
     */
    public void hilfe() {
        viewController.hilfeAnzeigen();
    }

    /**
     * Erste Implementation von neuesSpiel. Methoden ausgelagert zu
     * NeuesSpielMethoden.
     *
     * @author F Lang
     * @since 16.11.2023
     */
    public void neuesSpiel() {
        Node neuesSpiel = new NeuesSpielView(viewController, this, speicherstandController);
        viewController.anmelden(neuesSpiel, null, AnsichtsTyp.OHNE_OVERLAY);
    }

    public void setGameHubController(GameHubController gameHubController) {
        this.gameHubController = gameHubController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * Beendet das Spiel
     *
     * @author Nick, Dennis
     * @since 30.11.2023
     */
    public void spielBeenden() {
        Platform.exit();
    }

    /**
     * Funktion zum Öffnen der Spielstandladen-Ansicht um einene Spielstand auszuwählen und zu laden
     *
     * @author Melvin
     * @since 05.12.2023
     */
    public void spielLaden() {
        Node speicherstandLaden = new SpeicherstandLadenView(viewController, speicherstandController, this);
        viewController.anmelden(speicherstandLaden, null, AnsichtsTyp.OHNE_OVERLAY);
    }

    public BooleanProperty spielVorhandenProperty() {
        return spielVorhanden;
    }

}
