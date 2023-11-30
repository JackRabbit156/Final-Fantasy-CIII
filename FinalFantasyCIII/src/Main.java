import hauptmenu.HauptmenuController;
import hauptmenu.HauptmenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.ViewController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HauptmenuController hauptmenuController = new HauptmenuController(primaryStage);
    }
}
