package trainer;

import charakter.model.SpielerCharakter;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TrainerSpezialisierungAendernView extends BorderPane {
    TrainerController trainerController;
    private Label titel;

    public TrainerSpezialisierungAendernView(TrainerController trainerController) {
        VBox center = new VBox();
        this.setCenter(center);
        this.trainerController = trainerController;
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        titel = new Label();
        titel.setText("Spezialisierung Ändern");
        center.getChildren().addAll(titel);
    }

    public void aenderungVorbereiten() {

        // welche Klasse ist der aktuelleCharakter ?
        // Welche Spezialisierungen kann die Klasse habe ?


    }
}
