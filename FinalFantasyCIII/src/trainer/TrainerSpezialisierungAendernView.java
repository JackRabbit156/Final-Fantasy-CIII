package trainer;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.spezialisierungen.Paladin;
import javafx.geometry.Pos;
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
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(titel);

        // Prüfung ob der Button erscheint ist
        //
        //trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")
        //trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Paladin");
        //Erstmal ein paar Buttons
        //Klasse Tank / und Butt std disabled
        btnRabauke = new Button("Rabauke");
        btnRabauke.setDisable(tru);
        btnRabauke.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Raubauke"));
        btnPaladin = new Button("Paladin");
        btnPaladin.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Paladin"));
        btnPaladin.setDisable(tru);
        //Klasse Magischer DD
        btnFeuerMagier = new Button("FeuerMagier");
        btnFeuerMagier.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Feuermagier");
            System.out.println(trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName());

        });

        btnFeuerMagier.setDisable(tru);
        btnEismagier = new Button("EisMagier");
        btnEismagier.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"EisMagier"));
        btnEismagier.setDisable(tru);
        //Klasse Physischer DD
        btnBerserker = new Button("Berserker");
        btnBerserker.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Berseker"));
        btnBerserker.setDisable(tru);
        btnSchurke = new Button("Schurke");
        btnSchurke.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Schurke"));
        btnSchurke.setDisable(tru);
        // Klasse Healer
        btnPriester = new Button("Priester");
        btnPriester.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Priester"));
        btnPriester.setDisable(tru);
        btnSanMaus = new Button("SanMaus");
        btnSanMaus.setOnAction(event -> CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(),"Sanmaus"));
        btnSanMaus.setDisable(tru);

        center.getChildren().addAll(btnRabauke,btnPaladin,btnFeuerMagier,btnEismagier,btnBerserker,btnSchurke,btnPriester,btnSanMaus);
    }

    public void aenderungVorbereiten() {

        // Tank
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")) {
            btnRabauke.setDisable(flase);
            btnPaladin.setDisable(flase);
            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //PDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Physischer DD")) {
            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnBerserker.setDisable(flase);
            btnSchurke.setDisable(flase);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //MDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Magischer DD")) {
            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnEismagier.setDisable(flase);
            btnFeuerMagier.setDisable(flase);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //Healer
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Healer")) {
            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
            btnPriester.setDisable(flase);
            btnSanMaus.setDisable(flase);
        }
        // welche Klasse ist der aktuelleCharakter ? wenn er keine Spezialisierung hat

        // Welche Spezialisierungen kann die Klasse habe ?




    }
}
