package schmiede;

import javafx.scene.control.*;
import view.ViewController;

public class SchmiedeView extends TabPane {
    private SchmiedeController schmiedeController;
    TabPane root = new TabPane();

    public SchmiedeView(ViewController viewController, SchmiedeController schmiedeController) {
        this.schmiedeController = schmiedeController;

        //this.setBackground(SchmiedeController.setzeSchmiedeHintergrund());
    }

    Tab tab1 = new Tab("Waffen", new Label("Zeige alle Waffen"));
    Tab tab2 = new Tab("Rüstungen"  , new Label("Zeige alle Rüstungen"));
    Tab tab3 = new Tab("Accessoires" , new Label("Zeige alle Accessoires"));

}
