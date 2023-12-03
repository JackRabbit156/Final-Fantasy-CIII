package trainer;

import charakter.model.SpielerCharakter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class TrainerSpezialisierungAendernView extends BorderPane {
    TrainerController trainerController;
    private Label titel;
// Aufgrund ständiger Schreibfehler macht sich der alte Herr das etwas einfacher
    // Ausserdem wird der Nick Irre davon ..
    boolean flase = false;
    boolean tru=true;
    VBox center;
    Button btnRabauke;
    Button btnPaladin;
    //Klasse Magischer DD
    Button btnFeuerMagier;
    Button btnEismagier;
    //Klasse Physischer DD
    Button btnBerserker;
    Button btnSchurke;
    // Klasse Healer
    Button btnPriester;
    Button btnSanMaus;

    public TrainerSpezialisierungAendernView(TrainerController trainerController) {
        center = new VBox();
        this.setCenter(center);
        this.trainerController = trainerController;
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        titel = new Label();
        titel.setText("Spezialisierung Ändern");
        center.getChildren().addAll(titel);



        // Prüfung ob der Button erscheint ist
        //
        //trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")
        //trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Paladin");

    }

    public void aenderungVorbereiten() {
        //Erstmal ein paar Buttons
        //Klasse Tank / und Butt std disabled
        btnRabauke = new Button("Rabauke");
        btnRabauke.setDisable(tru);
        btnPaladin = new Button("Paladin");
        btnPaladin.setDisable(tru);
        //Klasse Magischer DD
        btnFeuerMagier = new Button("FeuerMagier");
        btnFeuerMagier.setDisable(tru);
        btnEismagier = new Button("EisMagier");
        btnEismagier.setDisable(tru);
        //Klasse Physischer DD
        btnBerserker = new Button("Berserker");
        btnBerserker.setDisable(tru);
        btnSchurke = new Button("Schurke");
        btnSchurke.setDisable(tru);
        // Klasse Healer
        btnPriester = new Button("Priester");
        btnPriester.setDisable(tru);
        btnSanMaus = new Button("SanMaus");
        btnSanMaus.setDisable(tru);
        center.getChildren().addAll(btnRabauke,btnPaladin,btnFeuerMagier,btnEismagier,btnBerserker,btnSchurke,btnPriester,btnSanMaus);
        // Tank
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")) {
            btnRabauke.setDisable(flase);
            btnPaladin.setDisable(flase);
        }
        //PDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Physischer DD")) {
            btnBerserker.setDisable(flase);
            btnSchurke.setDisable(flase);
        }
        //MDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Magischer DD")) {
            btnEismagier.setDisable(flase);
            btnFeuerMagier.setDisable(flase);
        }
        //Healer
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Healer")) {
            btnPriester.setDisable(flase);
            btnSanMaus.setDisable(flase);
        }
        // welche Klasse ist der aktuelleCharakter ? wenn er keine Spezialisierung hat

        // Welche Spezialisierungen kann die Klasse habe ?




    }
}
