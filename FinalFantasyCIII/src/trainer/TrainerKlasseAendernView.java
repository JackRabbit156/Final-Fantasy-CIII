package trainer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The type Trainer klasse aendern view.
 */
public class TrainerKlasseAendernView extends BorderPane {
    private TrainerController trainerController;

    private StringBuilder sb;
    private Label lblaktuelleKlasse;
    private Label lblanzeigeCharakter;
    private Label lblTitel;
    private Button btnTank;
    private Button btnPDD;
    private Button btnMDD;
    private Button btnHLR;

    /**
     * Konstruktor zu Klasse TrainerKlassAendernView
     *
     * @param trainerController the trainer controller
     * @since 05.12.2023
     */
    public TrainerKlasseAendernView(TrainerController trainerController) {
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        this.trainerController = trainerController;
        //viewController.ansichtHinzufuegen(this);
        /// --> viewController.anmelden(this,overlayButtons, AnsichtsTyp.MIT_OVERLAY);

        // Klasse aendern
        VBox titel = new VBox();
        lblTitel = new Label("Klasse ändern");
        titel.getChildren().add(lblTitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);
        // Label soll nur angezeigt werden, wenn man eine Klasse änert. Initial ausgeblendet
        lblaktuelleKlasse = new Label();
        lblaktuelleKlasse.setVisible(false);
        lblaktuelleKlasse.textProperty().addListener((observable, oldValue, newValue) -> {if(newValue.isEmpty()){
        lblaktuelleKlasse.setVisible(false);
        } else {
            lblaktuelleKlasse.setVisible(true);
        }
        });
        lblaktuelleKlasse.setAlignment(Pos.CENTER);
        lblanzeigeCharakter = new Label();
        btnTank = new Button("Tank");
        btnPDD = new Button("Physischer DD");
        btnMDD = new Button("Magischer DD");
        btnHLR = new Button("Healer");
        VBox centerKlasseAendern = new VBox(lblaktuelleKlasse, btnTank, btnPDD, btnMDD, btnHLR, lblanzeigeCharakter);
        centerKlasseAendern.setAlignment(Pos.CENTER);
        centerKlasseAendern.setSpacing(15.0);

        // Buttons belegen
        btnTank.setOnAction(event -> klasseAenderKlick("TNK"));
        btnPDD.setOnAction(event -> klasseAenderKlick("PDD"));
        btnMDD.setOnAction(event -> klasseAenderKlick("MDD"));
        btnHLR.setOnAction(event -> klasseAenderKlick("HLR"));
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

    /** Dient der aktualisierung und Anzeige der Stats
     * Aenderung vorbereiten.
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void aenderungVorbereiten() {
        //Vorbereitung der View auf den aktuelle Cgharakter
        // Anzeige Charakter setzen
        sb = new StringBuilder();
        sb.append("Name : " + trainerController.getAktuellerCharakter().getName() + "\n");
        sb.append("Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold() + "\n");
        sb.append("Die Kosten für den Wechsel der Klasse beträgt " + TrainerController.basisKostenKlasseWechseln);
        lblanzeigeCharakter.setText(sb.toString());

        btnTank.setDisable(false);
        btnPDD.setDisable(false);
        btnMDD.setDisable(false);
        btnHLR.setDisable(false);

        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")) {
            btnTank.setDisable(true);
        }
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Physischer DD")) {
            btnPDD.setDisable(true);
        }
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Magischer DD")) {
            btnMDD.setDisable(true);
        }
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Healer")) {
            btnHLR.setDisable(true);
        }
    }
    /** Wird zur Auswertung der Bedingungen aufgerufen. Also hat die Party genug Gold um einen Wechsel durchzuführen
     * @author Thomas Maass
     * @since 05.12.2023
     */
    private void klasseAenderKlick(String zielKlasse) {
        if (trainerController.klasseAendern(zielKlasse)) {
            lblaktuelleKlasse.setText("Klasse von " + trainerController.getAktuellerCharakter().getName() + " wurde geändert zu " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung());
        } else {
            lblaktuelleKlasse.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenKlasseWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
        }
        aenderungVorbereiten();
    }



}
