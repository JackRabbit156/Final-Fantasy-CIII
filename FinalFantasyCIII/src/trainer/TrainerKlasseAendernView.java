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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainerKlasseAendernView extends BorderPane {
    SpielerCharakter derCharakter;
    ArrayList<Button> overlayButtons;
    ViewController viewController;
    TrainerController trainerController;


    StringBuilder sb;
    Label aktuelleKlasse;
    Label anzeigeCharakter;
    Button btnTank;
    Button btnPDD;
    Button btnMDD;
    Button btnHLR;

    public TrainerKlasseAendernView(ViewController viewController,TrainerController trainerController) {
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


        // Buttons belegen
        btnTank.setOnAction(event -> {
            CharakterController.klasseAendern(derCharakter, new TNK());
            aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
            viewController.aktuelleNachHinten();
        });
        btnPDD.setOnAction(event -> {
            CharakterController.klasseAendern(derCharakter, new PDD());
            aktuelleKlasse.setText("Klasse von " + derCharakter.getName() + " wurde geändert zu " + derCharakter.getKlasse().getBezeichnung());
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
        this.setCenter(centerKlasseAendern);
    }

    public void anderungVorbereiten() {
        //Vorbereitung der View auf den aktuelle Cgharakter

        aktuelleKlasse.setText(derCharakter.getName() + " ist derzeit " + derCharakter.getKlasse().getBezeichnung());
        // Anzeige Charakter setzen
        //@TODO: Hier wird gecheatet ! Vor Release entfernen
        trainerController.getPartyController().getParty().setGold(99);
        System.out.println();
        sb = new StringBuilder();
        sb.append("Name : " + derCharakter.getName()+"\n");
        sb.append("Klasse :" + derCharakter.getKlasse().getBezeichnung()+"\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold()+"\n");
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
