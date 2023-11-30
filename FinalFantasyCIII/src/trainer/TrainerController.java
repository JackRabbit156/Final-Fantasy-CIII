package trainer;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import gamehub.GameHubController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import trainer.faehigkeiten.Faehigkeit;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Maass
 * @since Initiale Befuellung des Controllers mit den Variablen und Methoden.
 */

public class TrainerController {
    private GameHubController gameHubController;
    private PartyController partyController;
    private ViewController viewController;
    private Trainer trainer;
    private ArrayList<Button> trainerMenuButtons;
    private TrainerView trainerView;
    private SpielerCharakter trainerAuswahl;
    private int auswahl = 0;

    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.trainer = new Trainer(this);
        this.viewController = viewController;
        Button btnKlasseaendern = new Button("Klasse ändern");
        Button btnSpezialisierungAendern = new Button("Spezialisierung ändern");
        Button btnFaehigkeitAendern = new Button("Fähigkeiten ändern");
        Button btnAttributeAendern = new Button("Attribute ändern");
        Button btnGameHub = new Button("Zurück zum GameHUB");
        btnGameHub.setOnAction(event -> viewController.aktuelleNachHinten());
        this.trainerMenuButtons = new ArrayList<Button>(Arrays.asList(btnKlasseaendern, btnSpezialisierungAendern, btnFaehigkeitAendern, btnAttributeAendern, btnGameHub));
        trainerView = new TrainerView(viewController);
        VBox gottModus = new VBox();
        gottModus.setAlignment(Pos.BOTTOM_LEFT);
        trainerView.setLeft(gottModus);
        Text pi = new Text("\u03c0");
        gottModus.getChildren().add(pi);
        pi.setOnMouseClicked(event -> {
            pi.setText("Trainer aktiviert");
            gottModus();

        });



        viewController.ansichtHinzufuegen(trainerView);
    }

    // Methoden
    public void trainerAnzeigen() {

        // Aufruf der eigentlichen Methode trainerAnzeigen !
        TrainerCharakterAuswahlView trainerCharakterAuswahlView = new TrainerCharakterAuswahlView(this, partyController);
        for (int i = 0; i < trainerMenuButtons.size() - 2; i++) {
            trainerMenuButtons.get(i).setOnAction(event -> {
                viewController.anmelden(trainerCharakterAuswahlView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
                this.auswahl=this.trainerMenuButtons.indexOf(event.getTarget());
            });
        }

        viewController.ansichtHinzufuegen(trainerCharakterAuswahlView);
        System.out.println(this.trainerMenuButtons);
        viewController.anmelden(this.trainerView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    public void setCharakterAuswahl(SpielerCharakter charakter){
        this.trainerAuswahl = charakter;
        switch (auswahl){
            case 0:
                //KlasseAendern anzeigen
                break;
            case 1:
                //Spezialisierung anzeigen
                break;
            case 2:
                //Fähigkeit ändern anzeigen
                break;
            case 3:
                //Attribut ändern anzeigen
                break;
            default:
                break;
        }
    }
    private void faehigkeitenZuruecksetzen() {

    }

    private void faehigkeitenLernen(Faehigkeit faehigkeit) {
    }

    private void maxGesundheitsPunkteVerbessern(int gesundheitsPunkte) {
    }

    private void maxManaPunkteVerbessern(int manaPunkte) {
    }

    private void physischeAttackeVerbessern(int angriffsPunkte) {
    }

    private void magischeAttackeVerbessern(int magischeAtk) {
    }

    private void genauigkeitVerbessern(int genauigkeitsWert) {
    }

    private void verteidigungVerbessern(int verteidigungsWert) {
    }

    private void resistenzVerbessern(int resistenzWert) {
    }

    private void bewegklichkeitVerbessern(int beweglichkeitsWert) {
    }

    private void klasseAendern(Klasse klasse) {
    }

    private void spezialisierungAendern(Klasse klasse) {
    }

    public GameHubController getGameHubController() {
        return gameHubController;
    }

    public PartyController getPartyController() {
        return partyController;
    }
    public void gottModus(){

    }

}
