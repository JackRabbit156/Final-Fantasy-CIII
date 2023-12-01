package trainer;

import charakter.model.SpielerCharakter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainerKlasseAendernView extends BorderPane {
    SpielerCharakter derCharakter;
    ArrayList<Button> overlayButtons;
    ViewController viewController;

    public TrainerKlasseAendernView(ViewController viewController, SpielerCharakter derCharakter) {
        this.setCenter(new Label("Klasse ändern "));
        this.derCharakter = derCharakter;
        this.viewController = viewController;
        Button btnTrainerView = new Button("Zurück zum Trainer");
        this.overlayButtons = new ArrayList<>(Arrays.asList(btnTrainerView));
        btnTrainerView.setOnAction(event -> viewController.aktuelleNachHinten());
        viewController.ansichtHinzufuegen(this);

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
