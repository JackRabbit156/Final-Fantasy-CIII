package trainer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The type Trainer spezialisierung aendern view.
 */
public class TrainerSpezialisierungAendernView extends BorderPane {
    /**
     * The Trainer controller.
     */
    TrainerController trainerController;
    private Label lblTitel;
    private Label lblaktuelleSpezialisierung;
    private Label lblanzeigeCharakter;


    /** Ist die VBox die in der Mitte angezeigt wird
     * The Center.
     */
    VBox center;
    /** Ist dei VBox die auf der rechten Seite angezeigt werden soll (wenn benoetigt)
     * The Rechts. Inhalt von BorderPane setCenter
     */
    VBox rechts;
    /**
     * The Titel. Inhalt von BorderPane setTop
     */
    VBox titel;
    // Initialisierung der Buttons
    /**
     * The Btn rabauke.
     */
    Button btnRabauke;
    /**
     * The Btn paladin.
     */
    Button btnPaladin;
    /**
     * The Btn feuer magier.
     */
//Klasse Magischer DD
    Button btnFeuerMagier;
    /**
     * The Btn eismagier.
     */
    Button btnEismagier;
    /**
     * The Btn berserker.
     */
//Klasse Physischer DD
    Button btnBerserker;
    /**
     * The Btn schurke.
     */
    Button btnSchurke;
    /**
     * The Btn priester.
     */
// Klasse Healer
    Button btnPriester;
    /**
     * The Btn san maus.
     */
    Button btnSanMaus;

    /**
     * Instantiates a new Trainer spezialisierung aendern view.
     *
     * @param trainerController the trainer controller
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public TrainerSpezialisierungAendernView(TrainerController trainerController) {
        titel = new VBox();
        lblTitel = new Label("Spezialisierung ändern");
        titel.getChildren().add(lblTitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);
        lblaktuelleSpezialisierung = new Label();
        lblaktuelleSpezialisierung.setVisible(false);
        lblaktuelleSpezialisierung.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                lblaktuelleSpezialisierung.setVisible(false);
            } else {
                lblaktuelleSpezialisierung.setVisible(true);
            }
        });
        lblanzeigeCharakter = new Label();

        //Label
        lblaktuelleSpezialisierung.getStyleClass().add("trainerAttributeGrossesLabel");
        lblaktuelleSpezialisierung.setAlignment(Pos.CENTER);
        lblanzeigeCharakter.getStyleClass().add("trainerAttributeGrossesLabel");

        center = new VBox();
        rechts = new VBox();
        this.setCenter(center);
        this.trainerController = trainerController;
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        center.setAlignment(Pos.CENTER);

        // Anzeige der Stats
        trainerCharakterStatsAnzeigen();

        //Erstmal ein paar Buttons
        //Klasse Tank / und Button std disabled
        btnRabauke = new Button("Rabauke");
        btnRabauke.setDisable(true);
        btnRabauke.setOnAction(event -> spezialisierungAendernKlick("Rabauke"));
        btnPaladin = new Button("Paladin");
        btnPaladin.setOnAction(event -> spezialisierungAendernKlick("Paladin"));
        btnPaladin.setDisable(true);
        //Klasse Magischer DD
        btnFeuerMagier = new Button("FeuerMagier");
        btnFeuerMagier.setOnAction(event -> spezialisierungAendernKlick("Feuermagier"));
        btnFeuerMagier.setDisable(true);
        btnEismagier = new Button("EisMagier");
        btnEismagier.setOnAction(event -> spezialisierungAendernKlick("Eismagier"));
        btnEismagier.setDisable(true);
        //Klasse Physischer DD
        btnBerserker = new Button("Berserker");
        btnBerserker.setOnAction(event -> spezialisierungAendernKlick("Berserker"));
        btnBerserker.setDisable(true);
        btnSchurke = new Button("Schurke");
        btnSchurke.setOnAction(event -> spezialisierungAendernKlick("Schurke"));
        btnSchurke.setDisable(true);
        // Klasse Healer
        btnPriester = new Button("Priester");
        btnPriester.setOnAction(event -> spezialisierungAendernKlick("Priester"));
        btnPriester.setDisable(true);
        btnSanMaus = new Button("SanMaus");
        btnSanMaus.setOnAction(event -> spezialisierungAendernKlick("Sanmaus"));
        btnSanMaus.setDisable(true);

        center.getChildren().addAll(lblaktuelleSpezialisierung, btnRabauke, btnPaladin, btnBerserker, btnSchurke, btnFeuerMagier, btnEismagier, btnPriester, btnSanMaus, lblanzeigeCharakter);
        center.setSpacing(15.0);

        //Styling
        //Button
        btnRabauke.getStyleClass().add("trainerSpezialisierungButton");
        btnPaladin.getStyleClass().add("trainerSpezialisierungButton");
        btnBerserker.getStyleClass().add("trainerSpezialisierungButton");
        btnSchurke.getStyleClass().add("trainerSpezialisierungButton");
        btnEismagier.getStyleClass().add("trainerSpezialisierungButton");
        btnFeuerMagier.getStyleClass().add("trainerSpezialisierungButton");
        btnPriester.getStyleClass().add("trainerSpezialisierungButton");
        btnSanMaus.getStyleClass().add("trainerSpezialisierungButton");

        this.setMaxWidth(1536.0);
        this.getStyleClass().add("trainerStyle");

    }


    /**
     * Dient hauptsaechlich der aktuellen Anzeige der Daten
     * Aenderung vorbereiten.
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void aenderungVorbereiten() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name : " + trainerController.getAktuellerCharakter().getName() + "\n");
        sb.append("Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n");
        sb.append("Level : " + trainerController.getAktuellerCharakter().getLevel() + "\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold() + "\n");
        sb.append("Die Kosten für den Wechsel der Spezialisierung beträgt " + TrainerController.basisKostenSpezialisierungWechseln + " Gold\n");
        sb.append("Wechsel der Spezialisierung erst ab Level 10 möglich.");
        lblanzeigeCharakter.setText(sb.toString());
        // Ein bzw Ausblenden der Buttons der Klasse, der der Charakter angehoert
        // Tank
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Rabauke")) {
                btnRabauke.setDisable(true);
            } else {
                btnRabauke.setDisable(false);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Paladin")) {
                btnPaladin.setDisable(true);
            } else {
                btnPaladin.setDisable(false);
            }

            btnBerserker.setDisable(true);
            btnSchurke.setDisable(true);
            btnEismagier.setDisable(true);
            btnFeuerMagier.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //PDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Physischer DD")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Berserker")) {
                btnBerserker.setDisable(true);
            } else {
                btnBerserker.setDisable(false);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Schurke")) {
                btnSchurke.setDisable(true);
            } else {
                btnSchurke.setDisable(false);
            }

            btnRabauke.setDisable(true);
            btnPaladin.setDisable(true);
            btnEismagier.setDisable(true);
            btnFeuerMagier.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //MDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Magischer DD")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Eismagier")) {
                btnEismagier.setDisable(true);
            } else {
                btnEismagier.setDisable(false);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Feuermagier")) {
                btnFeuerMagier.setDisable(true);
            } else {
                btnFeuerMagier.setDisable(false);
            }
            btnRabauke.setDisable(true);
            btnPaladin.setDisable(true);
            btnBerserker.setDisable(true);
            btnSchurke.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //Healer
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Healer")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Priester")) {
                btnPriester.setDisable(true);
            } else {
                btnPriester.setDisable(false);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Sanmaus")) {
                btnSanMaus.setDisable(true);
            } else {
                btnSanMaus.setDisable(false);
            }
            btnRabauke.setDisable(true);
            btnPaladin.setDisable(true);
            btnBerserker.setDisable(true);
            btnSchurke.setDisable(true);
            btnEismagier.setDisable(true);
            btnFeuerMagier.setDisable(true);
        }
        // welche Klasse ist der aktuelleCharakter ? wenn er keine Spezialisierung hat
        // Welche Spezialisierungen kann die Klasse habe ?
    }
    /** Anzeige der notwendigen Werte
     * @author Thomas Maass
     * @since 05.12.2023
     *
     */
    private void trainerCharakterStatsAnzeigen() {

        // VBox für die Statistik anzeigen anlegen
        VBox rechtsCharakterStatsAnzeigen = new VBox();
        // das Label soll auf der rechten Seite zentriert angezeigt werden
        rechtsCharakterStatsAnzeigen.setAlignment(Pos.CENTER);

        // Soll ein Labe mit den Inhalten des Stringbuilders werden
        Label trainerCharakterStats = new Label();
        // label zu der VBOX hinzufügen
        rechtsCharakterStatsAnzeigen.getChildren().add(trainerCharakterStats);

        this.setRight(rechtsCharakterStatsAnzeigen);
    }

    private void spezialisierungAendernKlick(String zielSpezi) {
        //Kurze Leseprobe auf dem Buch "Der Weg war umsonst !!!
        if (trainerController.getAktuellerCharakter().getLevel() >= 10.0) {
            if (trainerController.spezialisierungAendern(zielSpezi)) {
                lblaktuelleSpezialisierung.setText("Spezialisierung " + trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName() + " angenommen.");
            } else {
                lblaktuelleSpezialisierung.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenSpezialisierungWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
            }
        } else {
            lblaktuelleSpezialisierung.setText("Wechsel erst ab Level 10 möglich. Du bist Level " + trainerController.getAktuellerCharakter().getLevel());
        }
        aenderungVorbereiten();
    }
}
