package trainer;

import charakter.controller.CharakterController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class TrainerSpezialisierungAendernView extends BorderPane {
    TrainerController trainerController;
    private Label lblTitel;
    private Label lblaktuelleSpezialisierung;
    private Label lblanzeigeCharakter;
    boolean flase = false;
    boolean tru = true;
    VBox center;
    VBox rechts;
    VBox titel;

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
        titel = new VBox();
        lblTitel = new Label("Spezialisierung ändern");
        titel.getChildren().add(lblTitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);
        lblaktuelleSpezialisierung = new Label();
        lblaktuelleSpezialisierung.setVisible(false);
        lblaktuelleSpezialisierung.textProperty().addListener((observable, oldValue, newValue) -> {if(newValue.isEmpty()){
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

        // Prüfung ob der Button erscheint ist
        //
        //trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")
        //trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Paladin");
        //Erstmal ein paar Buttons
        //Klasse Tank / und Button std disabled
        btnRabauke = new Button("Rabauke");
        btnRabauke.setDisable(tru);
        btnRabauke.setOnAction(event -> spezialisierungAendernKlick("Rabauke"));
        btnPaladin = new Button("Paladin");
        btnPaladin.setOnAction(event -> spezialisierungAendernKlick("Paladin"));
        btnPaladin.setDisable(tru);
        //Klasse Magischer DD
        btnFeuerMagier = new Button("FeuerMagier");
        btnFeuerMagier.setOnAction(event -> spezialisierungAendernKlick("Feuermagier"));
        btnFeuerMagier.setDisable(tru);
        btnEismagier = new Button("EisMagier");
        btnEismagier.setOnAction(event -> spezialisierungAendernKlick("Eismagier"));
        btnEismagier.setDisable(tru);
        //Klasse Physischer DD
        btnBerserker = new Button("Berserker");
        btnBerserker.setOnAction(event -> spezialisierungAendernKlick("Berserker"));
        btnBerserker.setDisable(tru);
        btnSchurke = new Button("Schurke");
        btnSchurke.setOnAction(event -> spezialisierungAendernKlick("Schurke"));
        btnSchurke.setDisable(tru);
        // Klasse Healer
        btnPriester = new Button("Priester");
        btnPriester.setOnAction(event -> spezialisierungAendernKlick("Priester"));
        btnPriester.setDisable(tru);
        btnSanMaus = new Button("SanMaus");
        btnSanMaus.setOnAction(event -> spezialisierungAendernKlick("Sanmaus"));
        btnSanMaus.setDisable(tru);

        center.getChildren().addAll(lblaktuelleSpezialisierung, btnRabauke, btnPaladin, btnBerserker, btnSchurke, btnFeuerMagier, btnEismagier, btnPriester, btnSanMaus, lblanzeigeCharakter);
        center.setSpacing(15.0);
        // prüfung ober man noch genug Gold hat um die Spezialisierung zu aendern

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

    public void aenderungVorbereiten() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name : " + trainerController.getAktuellerCharakter().getName() + "\n");
        sb.append("Klasse :" + trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n");
        sb.append("Gold : " + trainerController.getPartyController().getPartyGold() + "\n");
        sb.append("Die Kosten für den Wechsel der Spezialisierung beträgt " + TrainerController.basisKostenSpezialisierungWechseln);
        lblanzeigeCharakter.setText(sb.toString());
        // Ein bzw Ausblenden der Buttons der Klasse, der der Charakter angehoert
        // Tank
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Tank")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Rabauke")) {
                btnRabauke.setDisable(tru);
            } else {
                btnRabauke.setDisable(flase);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Paladin")) {
                btnPaladin.setDisable(tru);
            } else {
                btnPaladin.setDisable(flase);
            }

            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //PDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Physischer DD")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Berserker")) {
                btnBerserker.setDisable(tru);
            } else {
                btnBerserker.setDisable(flase);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Schurke")) {
                btnSchurke.setDisable(tru);
            } else {
                btnSchurke.setDisable(flase);
            }

            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //MDD
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Magischer DD")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Eismagier")) {
                btnEismagier.setDisable(tru);
            } else {
                btnEismagier.setDisable(flase);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Feuermagier")) {
                btnFeuerMagier.setDisable(tru);
            } else {
                btnFeuerMagier.setDisable(flase);
            }
            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnPriester.setDisable(tru);
            btnSanMaus.setDisable(tru);
        }
        //Healer
        if (trainerController.getAktuellerCharakter().getKlasse().getBezeichnung().equals("Healer")) {
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Priester")) {
                btnPriester.setDisable(tru);
            } else {
                btnPriester.setDisable(flase);
            }
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Sanmaus")) {
                btnSanMaus.setDisable(tru);
            } else {
                btnSanMaus.setDisable(flase);
            }
            btnRabauke.setDisable(tru);
            btnPaladin.setDisable(tru);
            btnBerserker.setDisable(tru);
            btnSchurke.setDisable(tru);
            btnEismagier.setDisable(tru);
            btnFeuerMagier.setDisable(tru);
        }
        // welche Klasse ist der aktuelleCharakter ? wenn er keine Spezialisierung hat

        // Welche Spezialisierungen kann die Klasse habe ?


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
        // Erster versuch eine Hintergrundbildes für das Label
        //TODO: Bild hinter das Lable setzen
        Image derKnopf = new Image("buttons/holzbutton.png");
        ImageView ivDerKnopf = new ImageView(derKnopf);
        ivDerKnopf.setFitHeight(80.0);
        ivDerKnopf.setPreserveRatio(true);


        //trainerCharakterStats.setStyle("-fx-background-image: url(\"/buttons/holzbutton.png\");");

        //trainerCharakterStats.setGraphic(ivDerKnopf);
    }
    private void spezialisierungAendernKlick(String zielSpezi) {
        if (trainerController.spezialisierungAendern(zielSpezi)) {
            lblaktuelleSpezialisierung.setText("Spezialisierung " + trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName() + " angenommen.");
        } else {
            lblaktuelleSpezialisierung.setText("Das kannst du dir nicht leisten. Dir fehlen " + (TrainerController.basisKostenSpezialisierungWechseln - trainerController.getPartyController().getPartyGold()) + " Gold.");
        }
        aenderungVorbereiten();
    }
}
