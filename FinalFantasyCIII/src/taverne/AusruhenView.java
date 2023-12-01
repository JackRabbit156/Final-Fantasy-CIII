package taverne;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class AusruhenView extends VBox {

    public AusruhenView() {

//        this.setBackground(new Background(new BackgroundImage(new Image("background/taverne1.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        getChildren().add(new ImageView(new Image("background/taverne1.jpg")));
        Label label = new Label("HP und MP der Party wieder aufgefüllt!");
        label.setStyle("-fx-font: 50px 'Lucida Calligraphy Italic'; -fx-text-fill: #cff473");

//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        this.getChildren().add(label);
        this.setAlignment(Pos.TOP_CENTER);
    }

}
