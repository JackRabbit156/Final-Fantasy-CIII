package view;

import gamehub.GameHubController;
import hauptmenu.*;
import hauptmenu.gamecontroller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import party.PartyController;

import java.util.List;
import java.util.Stack;

public class ViewController {

    private Stage primary;
    private StackPane oberStack;
    private TitelView titelbildschirm;
    private HauptmenuView hauptmenuView;
    private Stack<ViewObjekt> verlauf;
    private HauptmenuController hauptmenuController;
    private GameController gameController;
    private PartyController partyController;
    private OverlayRechts overlayRechts;
    private GameHubController gameHubController;

    /**
     * Ein Eintrag im Verlauf
     * @author Nick
     * @since 06.12.2023
     */
    private class ViewObjekt {
        Node view;
        List<Button> buttons;
        AnsichtsTyp ansichtsTyp;

        public ViewObjekt(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
            this.view = view;
            this.buttons = buttons;
            this.ansichtsTyp = ansichtsTyp;
        }
    }

    /**
     * Initialer Constructor zum erstellen der ersten Views
     *
     * @param primary             Stage aus der Anzuzeigenden Ansicht
     * @param hauptmenuController hauptmenu
     * @author Nick
     * @since 01.12.2023
     */
    public ViewController(Stage primary, HauptmenuController hauptmenuController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController, this);
        this.verlauf = new Stack<>();
        this.hauptmenuController = hauptmenuController;
        oberStack = new StackPane();
        oberStack.getChildren().addAll(hauptmenuView, titelbildschirm);
        oberStack.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primary.setScene(new Scene(this.oberStack));
        primary.setTitle("Final Fantasy CIII");
        primary.getIcons().add(new Image("icons/gameicon.png"));
        primary.setFullScreen(true);
        primary.maximizedProperty().addListener((observable, oldValue, newValue) -> primary.setFullScreen(true));
        primary.show();
    }

    /**
     * Constructor zum aufrufen im GameHub um einen GameController zu übergeben, dadurch wird ein aktives Spiel sichergestellt.
     * @param primary die Primarystage
     * @param hauptmenuController hauptmenu
     * @param gameController game
     * @param partyController party
     * @param oberstack Die Hauptstackpane des Viewcontrollers
     * @param gameHubController gamehub
     * @author Nick
     * @since 01.12.2023
     */
    public ViewController(Stage primary, HauptmenuController hauptmenuController, GameController gameController, PartyController partyController, StackPane oberstack, GameHubController gameHubController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController, this);
        this.verlauf = new Stack<>();
        this.hauptmenuController = hauptmenuController;
        this.gameController = gameController;
        this.partyController = partyController;
        this.gameHubController = gameHubController;
        oberStack = oberstack;
        this.oberStack.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * setzt Eine Ansicht nach Vorne und behandelt die Möglichkeit des Overlays anhand des Enums; Aktualisiert die Buttons im Overlay;
     *
     * @param view node die nach vorne geholt werden soll
     * @param ansichtsTyp Enum mit oder ohne Overlay
     * @param buttons wird benötigt um die buttons ins overlay zu setzen, bzw weiterzugeben an die Overlay funktion
     * @author Dennis, Nick, Markus, Rode
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
     * Fügt die gegebene Node der StackPane hinzu
     *
     * @param ansicht die View welche von den Controllern erstellt wird
     * @author Nick, Markus
     * @since 30.11.2023
     */
    private void ansichtHinzufuegen(Node ansicht) {
        this.oberStack.getChildren().add(ansicht);
    }

    public HauptmenuView getHauptmenuView() {
        return hauptmenuView;
    }

    /**
     * Setzt die oberste View nach ganz hinten um die zuletzt geöffnete anzuzeigen
     *
     * @author Nick
     * @since 30.11.2023
     */
    public void aktuelleNachHinten() {
        ViewObjekt letzte = this.verlauf.pop();
        if(letzte.ansichtsTyp == AnsichtsTyp.NICHT_CACHE){
            oberStack.getChildren().remove(letzte.view);
        }
        if(this.verlauf.peek().ansichtsTyp == AnsichtsTyp.MIT_OVERLAY){
            aktualisiereCharListe();
        }
        toFront(verlauf.peek().view, verlauf.peek().buttons, verlauf.peek().ansichtsTyp);
    }

    /**
     * Zeigt die Optionsview an, Absprungspunkt ins Hauptmenu, sowie Speichern
     * @author Nick
     * @since 06.12.2023
     */
    public void optionenAnzeigen() {
        anmelden(new OptionenView(hauptmenuController, gameController, this, gameHubController), null, AnsichtsTyp.OHNE_OVERLAY);
    }


    private void toFront(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
        switch (ansichtsTyp) {
            case OHNE_OVERLAY:
                view.toFront();
                break;
            case MIT_OVERLAY:
                view.toFront();
                overlayRechts = new OverlayRechts(buttons, this, partyController);
                LeisteOben leisteOben = new LeisteOben(partyController);
                ansichtHinzufuegen(overlayRechts);
                ansichtHinzufuegen(leisteOben);
                oberStack.setAlignment(leisteOben, Pos.TOP_LEFT);
                oberStack.setAlignment(overlayRechts, Pos.BOTTOM_RIGHT);
                overlayRechts.toFront();
                leisteOben.toFront();
                break;
            case NICHT_CACHE:
                view.toFront();
            default:
                break;
        }
    }
    /**
     * Credits anzeigen
     * @author Nick
     * @since 04.12.2023
     */
    public void creditsAnzeigen(){
        anmelden(new CreditsView(this), null, AnsichtsTyp.OHNE_OVERLAY);
    }

    public void aktualisiereCharListe() {
        overlayRechts.erneuereCharList();
    }

    public Stage getPrimary() {
        return primary;
    }

    public StackPane getOberStack() {
        return oberStack;
    }
}
