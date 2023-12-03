package trainer;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import gamehub.GameHubController;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
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
    private TrainerAttributeAendernView trainerAttributeAendernView;
    private TrainerSpezialisierungAendernView trainerSpezialisierungAendernView;

    private int auswahl = 0;
    public final static int basisKostenKlasseWechseln = 50;
    public final static int basisKostenSpezialisierungWechseln = 100;
    public final static int basisKostenAttributeAendern = 1;
    public final static int basisKostenFaehigkeitenAendern = 1;

    private SpielerCharakter aktuellerCharakter;

    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.trainer = new Trainer(this);
        this.viewController = viewController;
        this.aktuellerCharakter = partyController.getParty().getHauptCharakter();
        this.trainerKlasseAendernView = new TrainerKlasseAendernView(this);
        this.trainerAttributeAendernView = new TrainerAttributeAendernView(this);
        this.trainerSpezialisierungAendernView = new TrainerSpezialisierungAendernView(this);
        Button btnKlasseaendern = new Button("Klasse ändern");
        Button btnSpezialisierungAendern = new Button("Spezialisierung ändern");
        Button btnFaehigkeitAendern = new Button("Fähigkeiten ändern");
        Button btnAttributeAendern = new Button("Attribute ändern");
        Button btnZurueck = new Button("Zurück");
        btnKlasseaendern.setOnAction(event -> trainerKlasseAendernAnzeigen());
        btnSpezialisierungAendern.setOnAction(event -> trainerSpezialisierungAendernView());
        btnAttributeAendern.setOnAction(event -> trainerAttributeAendernAnzeigen());
        btnZurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        this.trainerMenuButtons = new ArrayList<Button>(Arrays.asList(btnKlasseaendern, btnSpezialisierungAendern, btnFaehigkeitAendern, btnAttributeAendern, btnZurueck));
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
        trainerKlasseAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerKlasseAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    public void trainerSpezialisierungAendernView() {
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerSpezialisierungAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);

    }

    public void trainerAttributeAendernAnzeigen() {
        trainerAttributeAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerAttributeAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    public void setCharakterAuswahl(SpielerCharakter charakter) {
        this.aktuellerCharakter = charakter;
    }

    private void faehigkeitenZuruecksetzen() {

    }

    private void faehigkeitenLernen(Faehigkeit faehigkeit) {
    }

    public void attributAendern(String zuAenderndesAttribut, boolean erhoehen) {
        if (erhoehen) {
            switch (zuAenderndesAttribut) {
                case "maxGesundheit":
                    aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() + 1);
                    break;
                case "maxMana":
                    aktuellerCharakter.setMaxManaPunkte(aktuellerCharakter.getMaxManaPunkte() + 1);
                    break;
                case "physischeAttacke":
                    aktuellerCharakter.setPhysischeAttacke(aktuellerCharakter.getPhysischeAttacke() + 1);
                    break;
                case "MagischeAttacke":
                    aktuellerCharakter.setMagischeAttacke(aktuellerCharakter.getMagischeAttacke() + 1);
                    break;
                case "genauigkeit":
                    aktuellerCharakter.setGenauigkeit(aktuellerCharakter.getGenauigkeit() + 1);
                    break;
                case "verteidigung":
                    aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() + 1);
                    break;
                case "magischeVerteidigung":
                    aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() + 1);
                    break;
                case "resistenz":
                    aktuellerCharakter.setResistenz(aktuellerCharakter.getResistenz() + 1);
                    break;
                case "beweglichkeit":
                    aktuellerCharakter.setBeweglichkeit(aktuellerCharakter.getBeweglichkeit() + 1);
                    break;
                default:
                    break;
            }
            aktuellerCharakter.setOffeneAttributpunkte(aktuellerCharakter.getOffeneAttributpunkte() - 1);
        } else {
            switch (zuAenderndesAttribut) {
                case "maxGesundheit":
                    aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() - 1);
                    if (aktuellerCharakter.getGesundheitsPunkte() > aktuellerCharakter.getMaxGesundheitsPunkte()) {
                        aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte());
                    }
                    break;
                case "maxMana":
                    aktuellerCharakter.setMaxManaPunkte(aktuellerCharakter.getMaxManaPunkte() - 1);
                    if (aktuellerCharakter.getManaPunkte() > aktuellerCharakter.getMaxManaPunkte()) {
                        aktuellerCharakter.setManaPunkte(aktuellerCharakter.getMaxManaPunkte());
                    }
                    break;
                case "physischeAttacke":
                    aktuellerCharakter.setPhysischeAttacke(aktuellerCharakter.getPhysischeAttacke() - 1);
                    break;
                case "MagischeAttacke":
                    aktuellerCharakter.setMagischeAttacke(aktuellerCharakter.getMagischeAttacke() - 1);
                    break;
                case "genauigkeit":
                    aktuellerCharakter.setGenauigkeit(aktuellerCharakter.getGenauigkeit() - 1);
                    break;
                case "verteidigung":
                    aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() - 1);
                    break;
                case "magischeVerteidigung":
                    aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() - 1);
                    break;
                case "resistenz":
                    aktuellerCharakter.setResistenz(aktuellerCharakter.getResistenz() - 1);
                    break;
                case "beweglichkeit":
                    aktuellerCharakter.setBeweglichkeit(aktuellerCharakter.getBeweglichkeit() - 1);
                    break;
                default:
                    break;
            }
            aktuellerCharakter.setOffeneAttributpunkte(aktuellerCharakter.getOffeneAttributpunkte() + 1);
        }
        trainerAttributeAendernView.anzeigeVorbereiten();

    }

    private void klasseAendern(Klasse klasse) {
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
            this.getPartyController().materialHinzufuegen(Material.EISENERZ, 999999);
            this.getPartyController().materialHinzufuegen(Material.GOLDERZ, 999999);
            this.getPartyController().materialHinzufuegen(Material.MITHRIL, 999999);
            this.getPartyController().materialHinzufuegen(Material.POPEL, 999999);
            this.getPartyController().materialHinzufuegen(Material.SCHLEIM, 999999);
            this.getPartyController().materialHinzufuegen(Material.SILBERERZ, 999999);
            // Setzen von Verbrauchmaterial
            this.getPartyController().verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand.GROSSER_HEILTRANK, 999999);
            this.getPartyController().verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand.GROSSER_MANATRANK, 999999);
            dasTeam[0].setGeschichte("Markus, ein junger Mann, war einst ein gewöhnlicher Büroangestellter, bis er in einen unerklärlichen Unfall geriet, der ihn mit erstaunlichen Kräften ausstattete. Nachdem er einer explosiven Energiewelle ausgesetzt war, entdeckte er, dass sein Körper unverwundbar geworden war. Er konnte sich nicht erklären, wie oder warum dies geschah, aber er beschloss, seine Kräfte zum Wohl anderer einzusetzen.\n" +
                    "Markus, der nun unverwundbar war, nutzte seine neuen Fähigkeiten, um unschuldige Menschen vor Bedrohungen zu schützen. Er wurde zu einem Symbol der Hoffnung und des Schutzes für die Stadt. Seine unverwundbare Haut und seine außergewöhnlichen Fähigkeiten machten ihn zu einem unüberwindbaren Verteidiger gegen das Verbrechen und zu einem leuchtenden Beispiel für Heldentum. Entschlossen, seine Kräfte für das Gute einzusetzen, strebt Markus danach, die Stadt vor jeglicher Gefahr zu bewahren und anderen zu dienen.");
            System.out.println("Markus aktiv");
        }

    }

    public static Background setzeTrainerHintergrund() {
        return (new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

    public void aktuelleNachHinten() {
        viewController.aktuelleNachHinten();
    }

    public boolean klasseAendern(String zielKlasse) {
        if (partyController.getPartyGold() >= basisKostenKlasseWechseln) {
            switch (zielKlasse) {
                case "TNK":
                    CharakterController.klasseAendern(aktuellerCharakter, new TNK());
                    break;
                case "PDD":
                    CharakterController.klasseAendern(aktuellerCharakter, new PDD());
                    break;
                case "MDD":
                    CharakterController.klasseAendern(aktuellerCharakter, new MDD());
                    break;
                case "HLR":
                    CharakterController.klasseAendern(aktuellerCharakter, new HLR());
                    break;
                default:
                    return false;
            }
            partyController.goldAbziehen(basisKostenKlasseWechseln);
            return true;
        } else {
            return false;
        }
    }
    public void trainerSpezialisierungprüfen(){


    }

    public SpielerCharakter getAktuellerCharakter() {
        return aktuellerCharakter;
    }
}
