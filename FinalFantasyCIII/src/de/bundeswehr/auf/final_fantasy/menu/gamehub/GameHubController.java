package de.bundeswehr.auf.final_fantasy.menu.gamehub;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.menu.haendler.controller.HaendlerController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.inventar.InventarController;
import de.bundeswehr.auf.final_fantasy.menu.kampf.controller.KampfController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.partystatus.PartyStatusController;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.SchmiedeController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.Speicherstand;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.taverne.TaverneController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Hauptcontroller fuer den Game Hub, der fuer die Koordination
 * verschiedener Spiel-Funktionalitaeten verantwortlich ist.
 *
 * @author HF Rode
 * @since 18.11.2023
 */
public class GameHubController {

    private final Button btnHaendler;
    private final Button btnKaempfen;
    private final Button btnPartyInventar;
    private final Button btnPartyStatus;
    private final Button btnSchmiede;
    private final Button btnTaverne;
    private final Button btnTrainer;
    private FeindController feindController;
    private Game gameController;
    private GameHubView gameHubView;
    private HaendlerController haendlerController;
    private HauptmenuController hauptmenuController;
    private InventarController inventarController;
    private KampfController kampfController;
    private PartyController partyController;
    private PartyStatusController partyStatusController;
    private SchmiedeController schmiedeController;
    private SpeicherstandController speicherstandController;
    private StatistikController statistikController;
    private TaverneController taverneController;
    private TrainerController trainerController;
    private ViewController viewController;

    /**
     * Die Klasse GameHubController koordiniert die Steuerung und Interaktionen im
     * Game Hub, der als zentrale Anlaufstelle für verschiedene Spielaktivitäten
     * dient.
     * <p>
     * Sie erstellt und verwaltet Instanzen verschiedener Controller, die für
     * spezifische Bereiche wie Händler, Schmiede, Taverne, Trainer, Inventar,
     * Partystatus und Kämpfe zuständig sind. Zudem verbindet sie diese mit der
     * entsprechenden grafischen Benutzeroberfläche (GameHubView) und steuert die
     * Navigation und Anzeige der jeweiligen Aktivitäten.
     * <p>
     * Die Klasse GameHubController integriert Funktionen für die Steuerung von
     * Händler-, Schmiede-, Taverne-, Trainer-, Inventar-, Partystatus- und
     * Kämpfen-Aktivitäten im Spiel.
     *
     * @param gameController          Der GameController für die allgemeine
     *                                Spielsteuerung.
     * @param partyController         Der PartyController für die Verwaltung der
     *                                Spielerparty.
     * @param statistikController     Der StatistikController für die
     *                                Spielerstatistiken.
     * @param hauptmenuController     Der HauptmenuController für die Navigation im
     *                                Hauptmenü.
     * @param speicherstandController Der SpeicherstandController für die
     *                                Spielstandverwaltung.
     * @param viewController          Der ViewController für die Steuerung der
     *                                grafischen Benutzeroberfläche.
     * @author Rode
     * @since 06.12.2023
     */
    public GameHubController(Game gameController, PartyController partyController,
                             StatistikController statistikController, HauptmenuController hauptmenuController,
                             SpeicherstandController speicherstandController, ViewController viewController) {
        viewController.getOberStack().getChildren().clear();
        this.viewController = new ViewController(viewController.getPrimary(), hauptmenuController, gameController,
                partyController, viewController.getOberStack(), this);
        hauptmenuController.spielVorhandenProperty().set(true);
        hauptmenuController.setViewController(this.viewController);
        hauptmenuController.setGameHubController(this);
        this.gameController = gameController;
        this.partyController = partyController;
        this.hauptmenuController = hauptmenuController;
        this.haendlerController = new HaendlerController(partyController, this.viewController);
        this.schmiedeController = new SchmiedeController(partyController, this.viewController);
        this.trainerController = new TrainerController(this, partyController, this.viewController);
        this.inventarController = new InventarController(partyController, this.viewController);
        this.partyStatusController = new PartyStatusController(partyController, this.viewController, inventarController);
        this.feindController = new FeindController();
        this.statistikController = statistikController;
        this.taverneController = new TaverneController(partyController, statistikController, this, this.viewController);
        this.kampfController = new KampfController(feindController, partyController, this.statistikController,
                gameController, hauptmenuController, speicherstandController,
                this.viewController);
        this.speicherstandController = new SpeicherstandController(this.viewController);
        this.gameHubView = new GameHubView(this);

        btnHaendler = new Button("Händler");
        btnHaendler.setOnMouseEntered(event -> gameHubView.ausloeserHaendlerHover());
        btnHaendler.setOnMouseClicked(event -> haendlerAnzeigen());
        btnHaendler.setOnMouseExited(event -> gameHubView.entfernenHaendlerHover());

        btnSchmiede = new Button("Schmiede");
        btnSchmiede.setOnMouseEntered(event -> gameHubView.ausloeserSchmiedeHover());
        btnSchmiede.setOnMouseClicked(event -> schmiedeAnzeigen());
        btnSchmiede.setOnMouseExited(event -> gameHubView.entfernenSchmiedeHover());

        btnTaverne = new Button("Taverne");
        btnTaverne.setOnMouseEntered(event -> gameHubView.ausloeserTaverneHover());
        btnTaverne.setOnMouseClicked(event -> taverneAnzeigen());
        btnTaverne.setOnMouseExited(event -> gameHubView.entfernenTaverneHover());

        btnTrainer = new Button("Trainer");
        btnTrainer.setOnMouseEntered(event -> gameHubView.ausloeserTrainerHover());
        btnTrainer.setOnMouseClicked(event -> trainerAnzeigen());
        btnTrainer.setOnMouseExited(event -> gameHubView.entfernenTrainerHover());

        btnPartyInventar = new Button("Inventar");
        btnPartyInventar.setOnMouseEntered(event -> gameHubView.ausloeserPartyHover());
        btnPartyInventar.setOnMouseClicked(event -> partyInventarAnzeigen());
        btnPartyInventar.setOnMouseExited(event -> gameHubView.entfernenPartyHover());

        btnPartyStatus = new Button("Party Status");
        btnPartyStatus.setOnMouseEntered(event -> gameHubView.ausloeserPartyStatusHover());
        btnPartyStatus.setOnMouseClicked(event -> partyStatusAnzeigen());
        btnPartyStatus.setOnMouseExited(event -> gameHubView.entfernenPartyStatusHover());

        btnKaempfen = new Button("Kämpfen");
        btnKaempfen.setOnMouseEntered(event -> gameHubView.ausloeserKampfHover());
        btnKaempfen.setOnMouseClicked(event -> kaempfenAnzeigen());
        btnKaempfen.setOnMouseExited(event -> gameHubView.entfernenKampfHover());

        List<Button> lstBtnhauptmenu = new ArrayList<>();
        lstBtnhauptmenu.add(btnHaendler);
        lstBtnhauptmenu.add(btnSchmiede);
        lstBtnhauptmenu.add(btnTaverne);
        lstBtnhauptmenu.add(btnTrainer);
        lstBtnhauptmenu.add(btnPartyInventar);
        lstBtnhauptmenu.add(btnPartyStatus);
        lstBtnhauptmenu.add(btnKaempfen);
        this.viewController.anmelden(gameHubView, lstBtnhauptmenu, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Die Methode destroy() setzt alle internen Referenzen auf null, um
     * sicherzustellen, dass keine weiteren Operationen auf den entsprechenden
     * Instanzen durchgeführt werden können. Dies dient der Ressourcenfreigabe und
     * der Bereinigung von nicht mehr benötigten Objekten. Vor allem beim Spielladen
     * ist es wichtig dies durchzuführen.
     *
     * @author Rode
     * @since 06.12.2023
     */
    public void destroy() {
        inventarController = null;
        gameController = null;
        partyController = null;
        haendlerController = null;
        schmiedeController = null;
        taverneController = null;
        trainerController = null;
        partyStatusController = null;
        statistikController = null;
        hauptmenuController.setGameHubController(null);
        hauptmenuController = null;
        feindController = null;
        kampfController = null;
        speicherstandController = null;
        viewController = null;
        gameHubView = null;
    }

    public FeindController getFeindController() {
        return feindController;
    }

    public Game getGameController() {
        return gameController;
    }

    public PartyController getPartyController() {
        return partyController;
    }

    public SchmiedeController getSchmiedeController() {
        return schmiedeController;
    }

    public SpeicherstandController getSpeicherstandController() {
        return speicherstandController;
    }

    public TaverneController getTaverneController() {
        return taverneController;
    }

    public TrainerController getTrainerController() {
        return trainerController;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void haendlerAnzeigen() {
        haendlerController.haendlerAnzeigen();
    }

    public void kaempfenAnzeigen() {
        kampfController.kampfStarten();
    }

    public void partyInventarAnzeigen() {
        inventarController.spielerInventarAnzeige();
    }

    public void partyStatusAnzeigen() {
        partyStatusController.partyStatusAnzeigen();
    }

    public void schmiedeAnzeigen() {
        schmiedeController.schmiedeAnzeigen();
    }

    public void speichern() {
        speicherstandController.speichern(new Speicherstand(partyController.getParty(),
                gameController.getSchwierigkeitsgrad(), gameController.isHardcore(), statistikController.getStatistik()));
    }

    public void taverneAnzeigen() {
        taverneController.taverneAnzeigen();
    }

    public void trainerAnzeigen() {
        trainerController.trainerAnzeigen();
    }

    /**
     * Löst den Hover-Effekt für den Händler-Button im Game Hub aus.
     *
     * @see #btnHaendler
     * @since 06.12.2023
     */
    public void ausloeserHaendlerHover() {
        btnHaendler.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Kampf-Button im Game Hub aus.
     *
     * @see #btnKaempfen
     * @since 06.12.2023
     */
    public void ausloeserKampfHover() {
        btnKaempfen.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Party-Inventar-Button im Game Hub aus.
     *
     * @see #btnPartyInventar
     * @since 06.12.2023
     */
    public void ausloeserPartyHover() {
        btnPartyInventar.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Party-Status-Button im Game Hub aus.
     *
     * @see #btnPartyStatus
     * @since 06.12.2023
     */
    public void ausloeserPartyStatusHover() {
        btnPartyStatus.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Schmiede-Button im Game Hub aus.
     *
     * @see #btnSchmiede
     * @since 06.12.2023
     */
    public void ausloeserSchmiedeHover() {
        btnSchmiede.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Tavernen-Button im Game Hub aus.
     *
     * @see #btnTaverne
     * @since 06.12.2023
     */
    public void ausloeserTaverneHover() {
        btnTaverne.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Löst den Hover-Effekt für den Trainer-Button im Game Hub aus.
     *
     * @see #btnTrainer
     * @since 06.12.2023
     */
    public void ausloeserTrainerHover() {
        btnTrainer.getStyleClass().add("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den Händler im Game Hub.
     *
     * @see #btnHaendler
     * @since 06.12.2023
     */
    public void entfernenHaendlerHover() {
        btnHaendler.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den Kampf im Game Hub.
     *
     * @see #btnKaempfen
     * @since 06.12.2023
     */
    public void entfernenKampfHover() {
        btnKaempfen.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für das Inventar im Game Hub.
     *
     * @see #btnPartyInventar
     * @since 06.12.2023
     */
    public void entfernenPartyHover() {
        btnPartyInventar.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für den PartyStatus im Game Hub.
     *
     * @see #btnPartyStatus
     * @since 06.12.2023
     */
    public void entfernenPartyStatusHover() {
        btnPartyStatus.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für die Schmiede im Game Hub.
     *
     * @see #btnSchmiede
     * @since 06.12.2023
     */
    public void entfernenSchmiedeHover() {
        btnSchmiede.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für die Taverne im Game Hub.
     *
     * @see #btnTaverne
     * @since 06.12.2023
     */
    public void entfernenTaverneHover() {
        btnTaverne.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

    /**
     * Entfernt den Hover-Effekt vom Button für das Inventar im Game Hub.
     *
     * @see #btnTrainer
     * @since 06.12.2023
     */
    public void entfernenTrainerHover() {
        btnTrainer.getStyleClass().remove("buttonMenueLeisteRechts-hover");
    }

}