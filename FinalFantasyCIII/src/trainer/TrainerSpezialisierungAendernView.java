package trainer;

import charakter.controller.CharakterController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class TrainerSpezialisierungAendernView extends BorderPane {
    TrainerController trainerController;
    private Label lblTitel;
    private Label stats;
    // Aufgrund ständiger Schreibfehler macht sich der alte Herr das etwas einfacher
    // Ausserdem wird der Nick Irre davon ..
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
        center = new VBox();
        rechts = new VBox();
        this.setCenter(center);
        this.setTop(titel);
        this.trainerController = trainerController;
        this.setBackground(TrainerController.setzeTrainerHintergrund());
        lblTitel = new Label();
        titel.setAlignment(Pos.CENTER);
        titel.getChildren().addAll(lblTitel);
        lblTitel.setText("Spezialisierung Ändern");

        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(lblTitel);
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
        btnRabauke.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Rabauke");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnPaladin = new Button("Paladin");
        btnPaladin.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Paladin");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnPaladin.setDisable(tru);
        //Klasse Magischer DD
        btnFeuerMagier = new Button("FeuerMagier");
        btnFeuerMagier.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Feuermagier");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnFeuerMagier.setDisable(tru);
        btnEismagier = new Button("EisMagier");
        btnEismagier.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "EisMagier");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnEismagier.setDisable(tru);
        //Klasse Physischer DD
        btnBerserker = new Button("Berserker");
        btnBerserker.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Berseker");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnBerserker.setDisable(tru);
        btnSchurke = new Button("Schurke");
        btnSchurke.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Schurke");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnSchurke.setDisable(tru);
        // Klasse Healer
        btnPriester = new Button("Priester");
        btnPriester.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Priester");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnPriester.setDisable(tru);
        btnSanMaus = new Button("SanMaus");
        btnSanMaus.setOnAction(event -> {
            CharakterController.spezialisierungAendern(trainerController.getAktuellerCharakter(), "Sanmaus");
            trainerController.getPartyController().goldAbziehen(TrainerController.basisKostenSpezialisierungWechseln);
            //TODO: Fähigkeiten resetten
        });
        btnSanMaus.setDisable(tru);

        center.getChildren().addAll(btnRabauke, btnPaladin, btnFeuerMagier, btnEismagier, btnBerserker, btnSchurke, btnPriester, btnSanMaus);
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
        //Label
        lblTitel.getStyleClass().add("trainerAttributeGrossesLabel");

        this.setMaxWidth(1536.0);

    }

    public void aenderungVorbereiten() {

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
            if (trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().equals("Berserker")) {
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
/*        Image derKnopf = new Image("buttons/holzbutton.png");
        ImageView ivDerKnopf = new ImageView(derKnopf);
        ivDerKnopf.setFitHeight(80.0);
        ivDerKnopf.setFitWidth(200.0);
        //ivDerKnopf.setPreserveRatio(true);


        trainerCharakterStats.setGraphic(ivDerKnopf);
        */


        //Stringbuilder mit den Inhalten erstellen und befüllen
        StringBuilder charakterStats = new StringBuilder();
        // Das label mit Inhalt füllen
        charakterStats.append(trainerController.getAktuellerCharakter().getKlasse().getBezeichnung() + "\n");
        charakterStats.append(trainerController.getAktuellerCharakter().getKlasse().getClass().getSimpleName().toString() + "\n");
        charakterStats.append(trainerController.getPartyController().getPartyGold());
        // Stringbuilder in das Label setzen
        trainerCharakterStats.setText(charakterStats.toString());

        // die VBox zur Anzeige hinzufügen
        this.setRight(rechtsCharakterStatsAnzeigen);
    }
}
