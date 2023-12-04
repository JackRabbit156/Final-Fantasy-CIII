package trainer.faehigkeiten.View;
import charakter.model.SpielerCharakter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import trainer.TrainerController;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;


public class TrainerFaehigkeitenView extends BorderPane {
    SpielerCharakter derCharakter;
    ArrayList<Button> overlayButtons;
    ViewController viewController;
    TrainerController trainerController;

    public TrainerFaehigkeitenView(ViewController viewController, TrainerController trainerController) {
        this.viewController = viewController;
        this.trainerController = trainerController;
        Button btnTrainerView = new Button("Zurück zum Trainer");
        btnTrainerView.setOnAction(event -> viewController.aktuelleNachHinten());
        this.overlayButtons = new ArrayList<>(Arrays.asList(btnTrainerView));

        ImageView iconCharakter = new ImageView(new Image("icons/gameicon.png")); //TODO: auf spielerCharakter.getIcon() setzten
        iconCharakter.setFitHeight(100);
        iconCharakter.setFitWidth(100);

        GridPane charakterEigenschaften = new GridPane();
        TextField headerText = new TextField("Fähigkeiten für ");
        charakterEigenschaften.add(headerText, 0, 0, 3, 1);
        TextField klasseText = new TextField("Klasse: ");
        charakterEigenschaften.add(klasseText, 0, 1);
        TextField spezialisierungText = new TextField("Spezialisierung: ");
        charakterEigenschaften.add(spezialisierungText, 2, 1);

        TextField effektStaerkeText = new TextField("Stärke");
        TextField anzahlZieleText = new TextField("Anzahl Ziele");
        TextField wahrscheinlichkeitText = new TextField("Wahrscheinlichkeit auf kritischen Treffer");
        HBox headerZeile = new HBox(iconCharakter, charakterEigenschaften, effektStaerkeText, anzahlZieleText, wahrscheinlichkeitText);
        headerZeile.setPadding(new Insets(20));
        headerZeile.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void viewVorbereitenAufCharakter(){

    }

    public SpielerCharakter getDerCharakter() {
        return derCharakter;
    }

    public void setDerCharakter(SpielerCharakter derCharakter) {
        this.derCharakter = derCharakter;
    }

    public ArrayList<Button> getOverlayButtons() {
        return overlayButtons;
    }

    public void setOverlayButtons(ArrayList<Button> overlayButtons) {
        this.overlayButtons = overlayButtons;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
