package de.bundeswehr.auf.final_fantasy.menu.trainer.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;

/**
 * The type Trainer klasse aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
 */
public class KlasseAendernView extends BorderPane {

    private final TrainerController trainerController;

    private final Label lblaktuelleKlasse;
    private final Label lblanzeigeCharakter;
    private final Button btnTank;
    private final Button btnPDD;
    private final Button btnMDD;
    private final Button btnHLR;

    /**
     * Konstruktor zu Klasse TrainerKlassAendernView
     *
     * @param trainerController the de.bundeswehr.auf.final_fantasy.menu.trainer de.bundeswehr.auf.final_fantasy.controller
     * @since 05.12.2023
     */
    public KlasseAendernView(TrainerController trainerController) {
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        this.trainerController = trainerController;
        //viewController.ansichtHinzufuegen(this);
        /// --> viewController.anmelden(this,overlayButtons, AnsichtsTyp.MIT_OVERLAY);

        // Klasse ändern
        VBox titel = new VBox();
        Label lblTitel = new Label("Klasse ändern");
        titel.getChildren().add(lblTitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);
        // Label soll nur angezeigt werden, wenn man eine Klasse ändert. Initial ausgeblendet
        lblaktuelleKlasse = new Label();
        lblaktuelleKlasse.setVisible(false);
        lblaktuelleKlasse.textProperty().addListener((observable, oldValue, newValue) -> lblaktuelleKlasse.setVisible(!newValue.isEmpty()));
        lblaktuelleKlasse.setAlignment(Pos.CENTER);
        lblanzeigeCharakter = new Label();
        btnTank = new Button(Klasse.TNK);
        btnPDD = new Button(Klasse.PDD);
        btnMDD = new Button(Klasse.MDD);
        btnHLR = new Button(Klasse.HLR);
        VBox centerKlasseAendern = new VBox(lblaktuelleKlasse, btnTank, btnPDD, btnMDD, btnHLR, lblanzeigeCharakter);
        centerKlasseAendern.setAlignment(Pos.CENTER);
        centerKlasseAendern.setSpacing(15.0);

        // Buttons belegen
        btnTank.setOnAction(event -> klasseAendernKlick(Klasse.TNK));
        btnPDD.setOnAction(event -> klasseAendernKlick(Klasse.PDD));
        btnMDD.setOnAction(event -> klasseAendernKlick(Klasse.MDD));
        btnHLR.setOnAction(event -> klasseAendernKlick(Klasse.HLR));
        // centerKlasseAendern.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //aktuelleKlasse.setText(trainerController.getAktuellerCharakter().getName() + " ist derzeit " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung());
        aenderungVorbereiten();
        this.setCenter(centerKlasseAendern);
        this.setMaxWidth(1536.0);
        //Styling
        //Buttons
        btnHLR.getStyleClass().add("trainerKlasseButton");
        btnTank.getStyleClass().add("trainerKlasseButton");
        btnMDD.getStyleClass().add("trainerKlasseButton");
        btnPDD.getStyleClass().add("trainerKlasseButton");
        //Label
        lblaktuelleKlasse.getStyleClass().add("trainerAttributeGrossesLabel");
        lblanzeigeCharakter.getStyleClass().add("trainerAttributeGrossesLabel");

        this.getStyleClass().add("trainerStyle");
    }

    /**
     * Dient der aktualisierung und Anzeige der Stats
     * Aenderung vorbereiten.
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void aenderungVorbereiten() {
        // Vorbereitung der View auf den aktuelle Charakter
        // Anzeige Charakter setzen
        lblanzeigeCharakter.setText("Name : " + trainerController.getAktuellerCharakter().getName() + "\n" +
                "Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n" +
                "Gold : " + trainerController.getPartyController().getPartyGold() + "\n" +
                "Die Kosten für den Wechsel der Klasse beträgt " + TrainerController.basisKostenKlasseWechseln);

        btnTank.setDisable(false);
        btnPDD.setDisable(false);
        btnMDD.setDisable(false);
        btnHLR.setDisable(false);

        switch (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung()) {
            case Klasse.TNK:
                btnTank.setDisable(true);
                break;
            case Klasse.PDD:
                btnPDD.setDisable(true);
                break;
            case Klasse.MDD:
                btnMDD.setDisable(true);
                break;
            case Klasse.HLR:
                btnHLR.setDisable(true);
                break;
        }
    }

    /**
     * Wird zur Auswertung der Bedingungen aufgerufen. Also hat die Party genug Gold um einen Wechsel durchzufuehren
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    private void klasseAendernKlick(String zielKlasse) {
        if (trainerController.klasseAendern(zielKlasse)) {
            lblaktuelleKlasse.setText("Klasse von " + trainerController.getAktuellerCharakter().getName() + " wurde geändert zu " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung());
        }
        else {
            lblaktuelleKlasse.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenKlasseWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
        }
        aenderungVorbereiten();
    }

    public Label getLblaktuelleKlasse() {
        return lblaktuelleKlasse;
    }

}
