package hauptmenu;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.ViewController;

public class CreditsView extends VBox {
    ViewController viewController;
    //Hier kommen irgendwann die Credits hin !
    Button derGrosseKnopf;


    public CreditsView(ViewController viewController) {
        this.viewController = viewController;

        Image imgCredits = new Image("background/creditsbackground.jpg");
        ImageView ivCredits = new ImageView(imgCredits);
        Button derGrosseKnopf = new Button();
        derGrosseKnopf.setGraphic(ivCredits);
        //TODO: Eintrag im Hauptmenue zur Anzeige leiten !
        // Hier felht wiedermal die NickMagie
        //viewController.anmelden();

    }





}
