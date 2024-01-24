package de.bundeswehr.auf.final_fantasy;

import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            new HauptmenuController(primaryStage);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
