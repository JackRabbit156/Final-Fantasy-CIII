package view;

import hauptmenu.HauptmenuController;
import hauptmenu.HauptmenuView;
import hauptmenu.TitelView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ViewController {

    private Stage primary;
    private StackPane oberStack;
    private TitelView titelbildschirm;
    private HauptmenuView hauptmenuView;
    private Stack<ViewObjekt> verlauf;

    class ViewObjekt {
        Node view;
        List<Button> buttons;
        AnsichtsTyp ansichtsTyp;
        public ViewObjekt(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
            this.view = view;
            this.buttons = buttons;
            this.ansichtsTyp = ansichtsTyp;
        }
    }
    public ViewController(Stage primary, HauptmenuController hauptmenuController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController, this);
        this.verlauf = new Stack<>();
        oberStack = new StackPane();
        oberStack.getChildren().addAll(hauptmenuView, titelbildschirm);
        oberStack.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primary.setScene(new Scene(this.oberStack));
        primary.setTitle("Final Fantasy CIII");
        primary.getIcons().add(new Image("icons/gameicon.png"));
        primary.setFullScreen(true);
        primary.setResizable(false);
        primary.show();
    }

    /**
     * setzt Eine Ansicht nach Vorne und behandelt die Möglichkeit des Overlays anhand des Enums; Aktualisiert die Buttons im Overlay;
     * @param view
     * @param buttons
     * @param ansichtsTyp
     * @author Dennis, Nick, Markus
     * @since 30.11.2023
     */
    public void anmelden(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp) {
        toFront(view, buttons, ansichtsTyp);
    this.verlauf.push(new ViewObjekt(view, buttons, ansichtsTyp));
    }

    /**
     * Fügt die gegebene Node der StackPane hinzu
     * @param ansicht die View welche von den Controllern erstellt wird
     * @author Nick, Markus
     * @since 30.11.2023
     */
    public void ansichtHinzufuegen(Node ansicht){
        this.oberStack.getChildren().add(ansicht);
    }

    public HauptmenuView getHauptmenuView() {
        return hauptmenuView;
    }
    /**
     * Setzt die oberste View nach ganz hinten um die zuletzt geöffnete anzuzeigen
     * @author Nick
     * @since 30.11.2023
     */
    public void aktuelleNachHinten(){
        this.verlauf.pop();
        toFront(verlauf.peek().view, verlauf.peek().buttons, verlauf.peek().ansichtsTyp);
    }

    private void toFront(Node view, List<Button> buttons, AnsichtsTyp ansichtsTyp){
        switch (ansichtsTyp) {
            case OHNE_OVERLAY:
                view.toFront();
                break;
            case MIT_OVERLAY:
                view.toFront();
                VBox butons = new VBox();
                if(buttons != null) {
                    for (Button button : buttons) {
                        butons.getChildren().add(button);
                    }
                }
                butons.setMaxSize(250.0, 500.0);
                butons.setSpacing(30.0);
                ansichtHinzufuegen(butons);
                oberStack.setAlignment(butons, Pos.BOTTOM_RIGHT);
                butons.toFront();
                break;
            default:
                break;
        }
    }
}
