package de.bundeswehr.auf.final_fantasy.menu.trainer.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;

/**
 * The type Trainer spezialisierung aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
 */
public class SpezialisierungAendernView extends BorderPane {
    /**
     * The Trainer de.bundeswehr.auf.final_fantasy.controller.
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
     * Instantiates a new Trainer spezialisierung aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
     *
     * @param trainerController the de.bundeswehr.auf.final_fantasy.menu.trainer de.bundeswehr.auf.final_fantasy.controller
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public SpezialisierungAendernView(TrainerController trainerController) {
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
        btnRabauke = new Button(Spezialisierung.RABAUKE);
        btnRabauke.setDisable(true);
        btnRabauke.setOnAction(event -> spezialisierungAendernKlick(btnRabauke.getText()));
        btnPaladin = new Button(Spezialisierung.PALADIN);
        btnPaladin.setOnAction(event -> spezialisierungAendernKlick(btnPaladin.getText()));
        btnPaladin.setDisable(true);
        //Klasse Magischer DD
        btnFeuerMagier = new Button(Spezialisierung.FEUER_MAGIER);
        btnFeuerMagier.setOnAction(event -> spezialisierungAendernKlick(btnFeuerMagier.getText()));
        btnFeuerMagier.setDisable(true);
        btnEismagier = new Button(Spezialisierung.EIS_MAGIER);
        btnEismagier.setOnAction(event -> spezialisierungAendernKlick(btnEismagier.getText()));
        btnEismagier.setDisable(true);
        //Klasse Physischer DD
        btnBerserker = new Button(Spezialisierung.BERSERKER);
        btnBerserker.setOnAction(event -> spezialisierungAendernKlick(btnBerserker.getText()));
        btnBerserker.setDisable(true);
        btnSchurke = new Button(Spezialisierung.SCHURKE);
        btnSchurke.setOnAction(event -> spezialisierungAendernKlick(btnSchurke.getText()));
        btnSchurke.setDisable(true);
        // Klasse Healer
        btnPriester = new Button(Spezialisierung.PRIESTER);
        btnPriester.setOnAction(event -> spezialisierungAendernKlick(btnPriester.getText()));
        btnPriester.setDisable(true);
        btnSanMaus = new Button(Spezialisierung.SAN_MAUS);
        btnSanMaus.setOnAction(event -> spezialisierungAendernKlick(btnSanMaus.getText()));
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
        lblanzeigeCharakter.setText("Name : " + trainerController.getAktuellerCharakter().getName() + "\n" +
                "Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n" +
                "Level : " + trainerController.getAktuellerCharakter().getLevel() + "\n" +
                "Gold : " + trainerController.getPartyController().getPartyGold() + "\n" +
                "Die Kosten für den Wechsel der Spezialisierung beträgt " + TrainerController.basisKostenSpezialisierungWechseln + " Gold\n" +
                "Wechsel der Spezialisierung erst ab Level 10 möglich.");
        // Ein bzw Ausblenden der Buttons der Klasse, der der Charakter angehört
        // Tank
        if (trainerController.getAktuellerCharakter().getKlasse() instanceof TNK) {
            btnRabauke.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Rabauke);
            btnPaladin.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Paladin);

            btnBerserker.setDisable(true);
            btnSchurke.setDisable(true);
            btnEismagier.setDisable(true);
            btnFeuerMagier.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //PDD
        if (trainerController.getAktuellerCharakter().getKlasse() instanceof PDD) {
            btnBerserker.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Berserker);
            btnSchurke.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Schurke);

            btnRabauke.setDisable(true);
            btnPaladin.setDisable(true);
            btnEismagier.setDisable(true);
            btnFeuerMagier.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //MDD
        if (trainerController.getAktuellerCharakter().getKlasse() instanceof MDD) {
            btnEismagier.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Eismagier);
            btnFeuerMagier.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Feuermagier);
            btnRabauke.setDisable(true);
            btnPaladin.setDisable(true);
            btnBerserker.setDisable(true);
            btnSchurke.setDisable(true);
            btnPriester.setDisable(true);
            btnSanMaus.setDisable(true);
        }
        //Healer
        if (trainerController.getAktuellerCharakter().getKlasse() instanceof HLR) {
            btnPriester.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Priester);
            btnSanMaus.setDisable(trainerController.getAktuellerCharakter().getKlasse()instanceof Sanmaus);
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
        // label zu der VBOX hinzufuegen
        rechtsCharakterStatsAnzeigen.getChildren().add(trainerCharakterStats);

        this.setRight(rechtsCharakterStatsAnzeigen);
    }

    private void spezialisierungAendernKlick(String zielSpezi) {
        //Kurze Leseprobe auf dem Buch "Der Weg war umsonst !!!
        if (trainerController.getAktuellerCharakter().getLevel() >= 10.0) {
            if (trainerController.spezialisierungAendern(zielSpezi)) {
                lblaktuelleSpezialisierung.setText("Spezialisierung " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + " angenommen.");
            } else {
                lblaktuelleSpezialisierung.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenSpezialisierungWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
            }
        } else {
            lblaktuelleSpezialisierung.setText("Wechsel erst ab Level 10 möglich. Du bist Level " + trainerController.getAktuellerCharakter().getLevel());
        }
        aenderungVorbereiten();
    }

    public Label getLblaktuelleSpezialisierung() {
        return lblaktuelleSpezialisierung;
    }
}
