package trainer;

import charakter.model.SpielerCharakter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainerKlasseAendernView extends BorderPane {
    private TrainerController trainerController;

    private StringBuilder sb;
    private Label aktuelleKlasse;
    private Label anzeigeCharakter;
    private Button btnTank;
    private Button btnPDD;
    private Button btnMDD;
    private Button btnHLR;

    public TrainerKlasseAendernView(TrainerController trainerController) {
        this.setCenter(new Label("Klasse ändern "));
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        this.trainerController = trainerController;
        //viewController.ansichtHinzufuegen(this);
        /// --> viewController.anmelden(this,overlayButtons, AnsichtsTyp.MIT_OVERLAY);

        // Klasse aendern
        aktuelleKlasse = new Label();
        anzeigeCharakter = new Label();
        btnTank = new Button("Tnk");
        btnPDD = new Button("PDD");
        btnMDD = new Button("MDD");
        btnHLR = new Button("HLR");
        VBox centerKlasseAendern = new VBox(aktuelleKlasse, btnTank, btnPDD, btnMDD, btnHLR, anzeigeCharakter);
        centerKlasseAendern.setAlignment(Pos.CENTER);
        // Statistik Anzeige aufrufen
        trainerCharakterStatsAnzeigen();

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
    }

    public void aenderungVorbereiten() {
        //Vorbereitung der View auf den aktuelle Cgharakter


        // Anzeige Charakter setzen

        sb = new StringBuilder();
        sb.append("Name : " + trainerController.getAktuellerCharakter().getName() + "\n");
        sb.append("Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold() + "\n");
        sb.append("Die Kosten für den Wechsel der Klasse beträgt " + TrainerController.basisKostenKlasseWechseln);
        anzeigeCharakter.setText(sb.toString());

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

    private void trainerCharakterStatsAnzeigen() {

        // VBOx für die Statistik anzeigen anlegen
        VBox rechtsCharakterStatsAnzeigen = new VBox();
        // das Label soll auf der rechten Seite zentriert angezeigt werden
        rechtsCharakterStatsAnzeigen.setAlignment(Pos.CENTER);
        // Soll ein Labe mit den Inhalten des Stringbuilders werden
        Label trainerCharakterStats = new Label();
        // label zu der VBOX hinzufügen
        rechtsCharakterStatsAnzeigen.getChildren().add(trainerCharakterStats);
        //Stringbuilder mit den Inhalten erstellen und befüllen
        StringBuilder charakterStats = new StringBuilder();
        // Das label mit Inhalt füllen
        charakterStats.append("Hier steht die Statitik");
        // Stringbuilder in das Label setzen
        trainerCharakterStats.setText(charakterStats.toString());

        // die VBox zur Anzeige hinzufügen
        this.setRight(rechtsCharakterStatsAnzeigen);
    }

    private void klasseAenderKlick(String zielKlasse) {
        if (trainerController.klasseAendern(zielKlasse)) {
            aktuelleKlasse.setText("Klasse von " + trainerController.getAktuellerCharakter().getName() + " wurde geändert zu " + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung());
        } else {
            aktuelleKlasse.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenKlasseWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
        }
        aenderungVorbereiten();
    }

    // getter <-> Setter

}
