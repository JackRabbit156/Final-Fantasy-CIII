package hauptmenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.ViewController;

public class TitelView extends VBox {

    public TitelView(ViewController viewController) {
        this.setBackground(new Background(new BackgroundImage(new Image("hintergruende/hauptmenue.jpg"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(1920,1080,false,false,false,false))));
        Label hinweis = new Label("Klicken zum fortfahren");
        hinweis.setTextFill(Color.WHITE);
        hinweis.setFont(new Font(Font.getDefault().getName(), 50.0));

        Label spielName = new Label("Final Fantasy CIII");
        spielName.setTextFill(Color.WHITE);
        spielName.setFont(new Font("Lucida Calligraphy Italic", 100.0));

        this.setSpacing(600.0);
        this.setPadding(new Insets(40.0));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(spielName, hinweis);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> viewController.toFront("hauptmenu"));
    }
}
