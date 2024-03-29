package de.bundeswehr.auf.final_fantasy.menu.overlay;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.view.*;
import de.bundeswehr.auf.final_fantasy.menu.overlay.view.LeisteOben;
import de.bundeswehr.auf.final_fantasy.menu.overlay.view.OverlayRechts;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Stack;

public class ViewController {

    /**
     * Ein Eintrag im Verlauf
     *
     * @author Nick
     * @since 06.12.2023
     */
    private static class ViewObjekt {
        AnsichtsTyp ansichtsTyp;
        List<Button> buttons;
        Node view;

        public ViewObjekt(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
            this.view = view;
            this.buttons = buttons;
            this.ansichtsTyp = ansichtsTyp;
        }

    }

    private Game gameController;
    private GameHubController gameHubController;
    private final HauptmenuController hauptmenuController;
    private final HauptmenuView hauptmenuView;
    private final StackPane oberStack;
    private OverlayRechts overlayRechts;
    private PartyController partyController;
    private final Stage primary;
    private final TitelView titelbildschirm;
    private final Stack<ViewObjekt> verlauf;

    /**
     * Initialer Constructor zum erstellen der ersten Views
     *
     * @param primary             Stage aus der Anzuzeigenden Ansicht
     * @param hauptmenuController de.bundeswehr.auf.final_fantasy.menu.hauptmenu
     * @author Nick
     * @since 01.12.2023
     */
    public ViewController(Stage primary, HauptmenuController hauptmenuController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController, this);
        this.verlauf = new Stack<>();
        this.hauptmenuController = hauptmenuController;
        this.oberStack = new StackPane();
        this.oberStack.getChildren().addAll(hauptmenuView, titelbildschirm);
        this.oberStack.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        primary.setScene(new Scene(this.oberStack));
        primary.setTitle("Final Fantasy CIII");
        primary.getIcons().add(new Image("icons/gameicon.png"));
        // TODO rechter Bildschirm-Hack
        primary.setX(1920);
        primary.setFullScreen(true);
        primary.maximizedProperty().addListener((observable, oldValue, newValue) -> primary.setFullScreen(true));
        primary.show();
    }

    /**
     * Constructor zum aufrufen im GameHub um einen GameController zu übergeben, dadurch wird ein aktives Spiel sichergestellt.
     *
     * @param primary             die Primarystage
     * @param hauptmenuController de.bundeswehr.auf.final_fantasy.menu.hauptmenu
     * @param gameController      game
     * @param partyController     de.bundeswehr.auf.final_fantasy.party
     * @param oberStack           Die Hauptstackpane des Viewcontrollers
     * @param gameHubController   gamehub
     * @author Nick
     * @since 01.12.2023
     */
    public ViewController(Stage primary, HauptmenuController hauptmenuController, Game gameController, PartyController partyController, StackPane oberStack, GameHubController gameHubController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController, this);
        this.verlauf = new Stack<>();
        this.hauptmenuController = hauptmenuController;
        this.gameController = gameController;
        this.partyController = partyController;
        this.gameHubController = gameHubController;
        this.oberStack = oberStack;
        this.oberStack.setAlignment(Pos.CENTER_LEFT);
    }

    public void aktualisiereCharListe() {
        overlayRechts.erneuereCharList();
    }

    /**
     * Setzt die oberste View nach ganz hinten um die zuletzt geöffnete anzuzeigen
     *
     * @author Nick
     * @since 30.11.2023
     */
    public void aktuelleNachHinten() {
        ViewObjekt letzte = this.verlauf.pop();
        if (letzte.ansichtsTyp == AnsichtsTyp.KEIN_CACHING) {
            oberStack.getChildren().remove(letzte.view);
        }
        if (this.verlauf.peek().ansichtsTyp == AnsichtsTyp.MIT_OVERLAY) {
            aktualisiereCharListe();
        }
        toFront(verlauf.peek().view, verlauf.peek().buttons, verlauf.peek().ansichtsTyp);
    }

    /**
     * setzt Eine Ansicht nach Vorne und behandelt die Möglichkeit des Overlays anhand des Enums; Aktualisiert die Buttons im Overlay;
     *
     * @param view        node die nach vorne geholt werden soll
     * @param buttons     nullable anzuzeigende Knöpfe
     * @param ansichtsTyp Enum mit oder ohne Overlay
     * @author Dennis, Nick, Markus
     * @since 30.11.2023
     */
    public void anmelden(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
        if (!oberStack.getChildren().contains(view)) {
            ansichtHinzufuegen(view);
        }
        toFront(view, buttons, ansichtsTyp);
        this.verlauf.push(new ViewObjekt(view, buttons, ansichtsTyp));
    }

    /**
     * Credits anzeigen
     *
     * @author Nick
     * @since 04.12.2023
     */
    public void creditsAnzeigen() {
        anmelden(new CreditsView(this), null, AnsichtsTyp.OHNE_OVERLAY);
    }

    public HauptmenuView getHauptmenuView() {
        return hauptmenuView;
    }

    public StackPane getOberStack() {
        return oberStack;
    }

    public Stage getPrimary() {
        return primary;
    }

    /**
     * Credits anzeigen
     *
     * @author OFR Rieger
     * @since 18.01.24
     */
    public void hilfeAnzeigen() {
        anmelden(new HilfeView(this), null, AnsichtsTyp.OHNE_OVERLAY);
    }

    /**
     * Zeigt die Optionsview an, Absprungspunkt ins Hauptmenu, sowie Speichern
     *
     * @author Nick
     * @since 06.12.2023
     */
    public void optionenAnzeigen() {
        anmelden(new OptionenView(hauptmenuController, gameController, this, gameHubController), null, AnsichtsTyp.OHNE_OVERLAY);
    }

    /**
     * Fügt die gegebene Node der StackPane hinzu
     *
     * @param ansicht die View welche von den Controllern erstellt wird
     * @author Nick, Markus
     * @since 30.11.2023
     */
    private void ansichtHinzufuegen(Node ansicht) {
        this.oberStack.getChildren().add(ansicht);
    }

    private void toFront(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
        switch (ansichtsTyp) {
            case MIT_OVERLAY:
                view.toFront();
                overlayRechts = new OverlayRechts(buttons, this, partyController, view);
                LeisteOben leisteOben = new LeisteOben(partyController);
                ansichtHinzufuegen(overlayRechts);
                ansichtHinzufuegen(leisteOben);
                StackPane.setAlignment(leisteOben, Pos.TOP_LEFT);
                StackPane.setAlignment(overlayRechts, Pos.BOTTOM_RIGHT);
                overlayRechts.toFront();
                leisteOben.toFront();
                break;
            case OHNE_OVERLAY:
            case KEIN_CACHING:
            default:
                view.toFront();
                break;
        }
    }

}
