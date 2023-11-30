package view;

import hauptmenu.HauptmenuController;
import hauptmenu.HauptmenuView;
import hauptmenu.TitelView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class ViewController {

    private Stage primary;
    private StackPane oberStack;
    private TitelView titelbildschirm;
    private HauptmenuView hauptmenuView;

    public ViewController(Stage primary, HauptmenuController hauptmenuController) {
        this.primary = primary;
        this.titelbildschirm = new TitelView(this);
        this.hauptmenuView = new HauptmenuView(hauptmenuController);
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
        switch (ansichtsTyp) {
            case OHNE_OVERLAY:
                view.toFront();
                break;
            case MIT_OVERLAY:
                break;
            default:
                break;
        }
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
}
