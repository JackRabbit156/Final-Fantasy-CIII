package view;

import hauptmenu.HauptmenuController;
import hauptmenu.HauptmenuView;
import hauptmenu.TitelView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
     * Setzt die Scene, nicht anfassen!
     *
     * @param nameView Bezeichner der Ansicht -> ("hauptmenu" || "gamehub" ||TODO )
     * @author Nick, Dennis
     * @since 30.11.2023
     */
    public void toFront(String nameView) {
        switch (nameView) {
            case "hauptmenu":
                this.hauptmenuView.toFront();
                break;
            case "gamehub":
                //TODO this.primary.getScene().setRoot(this.gamehubView);
            default:
                break;
        }
    }

}
