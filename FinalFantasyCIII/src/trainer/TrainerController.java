package trainer;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import gamehub.GameHubController;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.manatraenke.GrosserManatrank;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

    //Views
    private TrainerView trainerView;
    private TrainerKlasseAendernView trainerKlasseAendernView;

    private SpielerCharakter aktuellerCharakter;

    private int auswahl = 0;

    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.trainer = new Trainer(this);
        this.viewController = viewController;
        this.trainerKlasseAendernView = new TrainerKlasseAendernView(viewController,this);
        Button btnKlasseaendern = new Button("Klasse ändern");
        Button btnSpezialisierungAendern = new Button("Spezialisierung ändern");
        Button btnFaehigkeitAendern = new Button("Fähigkeiten ändern");
        Button btnAttributeAendern = new Button("Attribute ändern");
        Button btnGameHub = new Button("Zurück zum GameHUB");
        btnKlasseaendern.setOnAction(event -> trainerKlasseAendernAnzeigen());
        btnGameHub.setOnAction(event -> viewController.aktuelleNachHinten());
        this.trainerMenuButtons = new ArrayList<Button>(Arrays.asList(btnKlasseaendern, btnSpezialisierungAendern, btnFaehigkeitAendern, btnAttributeAendern, btnGameHub));
        trainerView = new TrainerView(viewController, this);
        VBox gottModus = new VBox();
        gottModus.setAlignment(Pos.BOTTOM_LEFT);
        trainerView.setLeft(gottModus);
        Text pi = new Text("\u03c0");
        gottModus.getChildren().add(pi);
        pi.setOnMouseClicked(event -> {
            gottModus();
            System.out.println("Cheat Aktiv");
        });


        //viewController.anmelden(trainerView,bt);
    }

    // Methoden
    public void trainerAnzeigen() {

        viewController.anmelden(this.trainerView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    public void trainerKlasseAendernAnzeigen() {
        trainerKlasseAendernView.setDerCharakter(aktuellerCharakter);
        viewController.anmelden(trainerKlasseAendernView, trainerKlasseAendernView.getOverlayButtons(), AnsichtsTyp.MIT_OVERLAY);
    }

    public void setCharakterAuswahl(SpielerCharakter charakter) {
        this.aktuellerCharakter = charakter;
        switch (auswahl) {
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

    public void gottModus() {
        SpielerCharakter[] dasTeam = this.getPartyController().getTeammitglieder();

        // GottModus --> Nutzer zum testen
        if (dasTeam[0].getName().equals("Markus")) {
            dasTeam[0].setPhysischeAttacke(99999);
            dasTeam[0].setBeweglichkeit(99999);
            dasTeam[0].setResistenz(99999);
            dasTeam[0].setMagischeVerteidigung(99999);
            dasTeam[0].setVerteidigung(99999);
            dasTeam[0].setMagischeAttacke(99999);
            dasTeam[0].setMaxManaPunkte(99999);
            dasTeam[0].setOffeneAttributpunkte(99999);
            dasTeam[0].setMaxGesundheitsPunkte(99999);
            dasTeam[0].setOffeneFaehigkeitspunkte(99999);
            dasTeam[0].setGenauigkeit(99999);
            dasTeam[0].setLevel(666);
            // Gold setzen
            this.getPartyController().getParty().setGold(999999);
            //Setzen von Materialien
            this.getPartyController().materialHinzufuegen(new Eisenerz(), 999999);
            this.getPartyController().materialHinzufuegen(new Golderz(), 999999);
            this.getPartyController().materialHinzufuegen(new Mithril(), 999999);
            this.getPartyController().materialHinzufuegen(new Popel(), 999999);
            this.getPartyController().materialHinzufuegen(new Schleim(), 999999);
            this.getPartyController().materialHinzufuegen(new Silbererz(), 999999);
            // Setzen von Verbrauchmaterial
            this.getPartyController().verbrauchsgegenstandHinzufuegen(new GrosserHeiltrank(), 999999);
            this.getPartyController().verbrauchsgegenstandHinzufuegen(new GrosserManatrank(), 999999);
            dasTeam[0].setGeschichte("Markus, ein junger Mann, war einst ein gewöhnlicher Büroangestellter, bis er in einen unerklärlichen Unfall geriet, der ihn mit erstaunlichen Kräften ausstattete. Nachdem er einer explosiven Energiewelle ausgesetzt war, entdeckte er, dass sein Körper unverwundbar geworden war. Er konnte sich nicht erklären, wie oder warum dies geschah, aber er beschloss, seine Kräfte zum Wohl anderer einzusetzen.\n" +
                    "Markus, der nun unverwundbar war, nutzte seine neuen Fähigkeiten, um unschuldige Menschen vor Bedrohungen zu schützen. Er wurde zu einem Symbol der Hoffnung und des Schutzes für die Stadt. Seine unverwundbare Haut und seine außergewöhnlichen Fähigkeiten machten ihn zu einem unüberwindbaren Verteidiger gegen das Verbrechen und zu einem leuchtenden Beispiel für Heldentum. Entschlossen, seine Kräfte für das Gute einzusetzen, strebt Markus danach, die Stadt vor jeglicher Gefahr zu bewahren und anderen zu dienen.");
            System.out.println("Markus aktiv");
        }

    }
    public static Background setzeTrainerHintergrund(){
        return (new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }
}
