package trainer;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainerKlasseAendernView extends BorderPane {
    SpielerCharakter derCharakter;
    ArrayList<Button> overlayButtons;
    ViewController viewController;
    TrainerController trainerController;
    /*
     * Die Basis Kosten zum wechseln der Klasse
     * */
    int basisKostenKlasseWechseln = 50;

    StringBuilder sb;
    Label aktuelleKlasse;
    Label anzeigeCharakter;
    Button btnTank;
    Button btnPDD;
    Button btnMDD;
    Button btnHLR;

    public TrainerKlasseAendernView(ViewController viewController, TrainerController trainerController) {
        this.viewController = viewController;
        this.setCenter(new Label("Klasse ändern "));
        Button btnTrainerView = new Button("Zurück zum Trainer");
        this.overlayButtons = new ArrayList<>(Arrays.asList(btnTrainerView));
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        this.trainerController = trainerController;
        btnTrainerView.setOnAction(event -> viewController.aktuelleNachHinten());
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
        btnTank.setOnAction(event -> {
            if (trainerController.getPartyController().getPartyGold() >= basisKostenKlasseWechseln) {
                CharakterController.klasseAendern(derCharakter, new TNK());
                trainerController.getPartyController().goldAbziehen(basisKostenKlasseWechseln);
                aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
            } else {
                aktuelleKlasse.setText("Das kannst du dir nicht leisten. Du hast " + trainerController.getPartyController().getPartyGold() + " Gold, brauchst aber " + basisKostenKlasseWechseln + " Gold");
            }

            //viewController.aktuelleNachHinten();
        });
        btnPDD.setOnAction(event -> {
            if (trainerController.getPartyController().getPartyGold() >= basisKostenKlasseWechseln) {
                CharakterController.klasseAendern(derCharakter, new PDD());
                aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
            } else {
                aktuelleKlasse.setText("Das kannst du dir nicht leisten. Du hast " + trainerController.getPartyController().getPartyGold() + " Gold, brauchst aber " + basisKostenKlasseWechseln + " Gold");
            }
            viewController.aktuelleNachHinten();
        });
        btnMDD.setOnAction(event -> {
            CharakterController.klasseAendern(derCharakter, new MDD());
            aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
            viewController.aktuelleNachHinten();
        });
        btnHLR.setOnAction(event -> {
            CharakterController.klasseAendern(derCharakter, new HLR());
            aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
            viewController.aktuelleNachHinten();
        });
       // centerKlasseAendern.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setCenter(centerKlasseAendern);
        this.setMaxWidth(1536.0);
    }

    public void anderungVorbereiten() {
        //Vorbereitung der View auf den aktuelle Cgharakter

        aktuelleKlasse.setText(derCharakter.getName() + " ist derzeit " + derCharakter.getKlasse().getBezeichnung());
        // Anzeige Charakter setzen

        sb = new StringBuilder();
        sb.append("Name : " + derCharakter.getName() + "\n");
        sb.append("Klasse :" + derCharakter.getKlasse().getBezeichnung() + "\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold() + "\n");
        sb.append("Die Kosten für den Wechsel der Klasse beträgt " + basisKostenKlasseWechseln);
        anzeigeCharakter.setText(sb.toString());

        btnTank.setDisable(false);
        btnPDD.setDisable(false);
        btnMDD.setDisable(false);
        btnHLR.setDisable(false);

        if (derCharakter.getKlasse().getBezeichnung().equals("Tank")) {
            btnTank.setDisable(true);
        }
        if (derCharakter.getKlasse().getBezeichnung().equals("Physischer DD")) {
            btnPDD.setDisable(true);
        }
        if (derCharakter.getKlasse().getBezeichnung().equals("Magischer DD")) {
            btnMDD.setDisable(true);
        }
        if (derCharakter.getKlasse().getBezeichnung().equals("Healer")) {
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

    // getter <-> Setter
    public SpielerCharakter getDerCharakter() {
        return derCharakter;
    }

    public void setDerCharakter(SpielerCharakter derCharakter) {
        this.derCharakter = derCharakter;
        anderungVorbereiten();
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
