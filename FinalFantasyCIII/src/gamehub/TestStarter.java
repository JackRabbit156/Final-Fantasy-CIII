package gamehub;

import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import party.Party;
import party.PartyController;
import statistik.Statistik;
import statistik.StatistikController;
import view.ViewController;

public class TestStarter extends Application {
    public static void main(String[] args) {


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane stackPane = new StackPane();
        PartyController partyController = new PartyController(new Party("Tiberius", "DD"));
        GameController gameController = new GameController(false, partyController);
        StatistikController statistikController = new StatistikController(new Statistik());
        HauptmenuController hauptmenuController = new HauptmenuController(primaryStage);
        SpeicherstandController speicherstandController = new SpeicherstandController();
        ViewController viewController = new ViewController(primaryStage, hauptmenuController);
        GameHubController gameHubController = new GameHubController(gameController, partyController, statistikController, hauptmenuController, speicherstandController, viewController);

        VBox oberesOverlay = new VBox();
    }
}
