package de.bundeswehr.auf.final_fantasy.menu.trainer.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.*;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The type Trainer spezialisierung aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
 */
public class SpezialisierungAendernView extends BorderPane {

    /**
     * The Btn berserker.
     */
    private final Button btnBerserker;
    /**
     * The Btn eismagier.
     */
    private final Button btnEismagier;
    /**
     * The Btn feuer magier.
     */
    private final Button btnFeuerMagier;
    /**
     * The Btn paladin.
     */
    private final Button btnPaladin;
    /**
     * The Btn priester.
     */
    private final Button btnPriester;
    /**
     * The Btn rabauke.
     */
    private final Button btnRabauke;
    /**
     * The Btn san maus.
     */
    private final Button btnSanMaus;
    /**
     * The Btn schurke.
     */
    private final Button btnSchurke;
    private final Label lblaktuelleSpezialisierung;
    private final Label lblanzeigeCharakter;
    private final TrainerController trainerController;

    /**
     * Instantiates a new Trainer spezialisierung aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
     *
     * @param trainerController the de.bundeswehr.auf.final_fantasy.menu.trainer de.bundeswehr.auf.final_fantasy.controller
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public SpezialisierungAendernView(TrainerController trainerController) {
        VBox titel = new VBox();
        Label lblTitel = new Label("Spezialisierung ändern");
        titel.getChildren().add(lblTitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);
        lblaktuelleSpezialisierung = new Label();
        lblaktuelleSpezialisierung.setVisible(false);
        lblaktuelleSpezialisierung.textProperty().addListener((observable, oldValue, newValue) -> lblaktuelleSpezialisierung.setVisible(!newValue.isEmpty()));
        lblanzeigeCharakter = new Label();

        lblaktuelleSpezialisierung.getStyleClass().add("trainerAttributeGrossesLabel");
        lblaktuelleSpezialisierung.setAlignment(Pos.CENTER);
        lblanzeigeCharakter.getStyleClass().add("trainerAttributeGrossesLabel");

        VBox center = new VBox();
        this.setCenter(center);
        this.trainerController = trainerController;
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        center.setAlignment(Pos.CENTER);

        trainerCharakterStatsAnzeigen();

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
                "Die Kosten für den Wechsel der Spezialisierung beträgt " + TrainerController.BASIS_KOSTEN_SPEZIALISIERUNG_WECHSELN + " Gold\n" +
                "Wechsel der Spezialisierung erst ab Level 10 möglich.");
        // Ein bzw Ausblenden der Buttons der Klasse, der der Charakter angehört
        btnRabauke.setDisable(true);
        btnPaladin.setDisable(true);
        btnBerserker.setDisable(true);
        btnSchurke.setDisable(true);
        btnEismagier.setDisable(true);
        btnFeuerMagier.setDisable(true);
        btnPriester.setDisable(true);
        btnSanMaus.setDisable(true);

        switch (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung()) {
            case Klasse.TNK:
                btnRabauke.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Rabauke);
                btnPaladin.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Paladin);
                break;
            case Klasse.PDD:
                btnBerserker.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Berserker);
                btnSchurke.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Schurke);
                break;
            case Klasse.MDD:
                btnEismagier.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Eismagier);
                btnFeuerMagier.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Feuermagier);
                break;
            case Klasse.HLR:
                btnPriester.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Priester);
                btnSanMaus.setDisable(trainerController.getAktuellerCharakter().getKlasse() instanceof Sanmaus);
                break;
        }
    }

    public Label getLblaktuelleSpezialisierung() {
        return lblaktuelleSpezialisierung;
    }

    private void spezialisierungAendernKlick(String zielSpezi) {
        //Kurze Leseprobe auf dem Buch "Der Weg war umsonst !!!
        if (trainerController.getAktuellerCharakter().getLevel() >= 10.0) {
            if (trainerController.spezialisierungAendern(zielSpezi)) {
                lblaktuelleSpezialisierung.setText("Spezialisierung " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + " angenommen.");
            }
            else {
                lblaktuelleSpezialisierung.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.BASIS_KOSTEN_SPEZIALISIERUNG_WECHSELN - trainerController.getPartyController().getPartyGold()) + " Gold.");
            }
        }
        else {
            lblaktuelleSpezialisierung.setText("Wechsel erst ab Level 10 möglich. Du bist Level " + trainerController.getAktuellerCharakter().getLevel());
        }
        aenderungVorbereiten();
    }

    /**
     * Anzeige der notwendigen Werte
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    private void trainerCharakterStatsAnzeigen() {
        VBox rechtsCharakterStatsAnzeigen = new VBox();
        rechtsCharakterStatsAnzeigen.setAlignment(Pos.CENTER);

        Label trainerCharakterStats = new Label();
        rechtsCharakterStatsAnzeigen.getChildren().add(trainerCharakterStats);

        this.setRight(rechtsCharakterStatsAnzeigen);
    }

}
