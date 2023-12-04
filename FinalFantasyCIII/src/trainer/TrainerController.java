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
 * The type Trainer controller.
 *
 * @author Thomas Maass
 * @since Initiale Befuellung des Controllers mit den Variablen und Methoden.
 */
public class TrainerController {
    private GameHubController gameHubController;
    private PartyController partyController;
    private ViewController viewController;
    private ArrayList<Button> trainerMenuButtons;

    //Views
    private TrainerView trainerView;
    private TrainerKlasseAendernView trainerKlasseAendernView;
    private TrainerAttributeAendernView trainerAttributeAendernView;
    private TrainerSpezialisierungAendernView trainerSpezialisierungAendernView;
    private TrainerFaehigkeitAendernView trainerFaehigkeitAendernView;

    private int auswahl = 0;
    /**
     * The constant basisKostenKlasseWechseln.
     */
    public final static int basisKostenKlasseWechseln = 50;
    /**
     * The constant basisKostenSpezialisierungWechseln.
     */
    public final static int basisKostenSpezialisierungWechseln = 100;
    /**
     * The constant basisKostenAttributeAendern.
     */
    public final static int basisKostenAttributeAendern = 1;
    /**
     * The constant basisKostenFaehigkeitenAendern.
     */
    public final static int basisKostenFaehigkeitenAendern = 1;

    private SpielerCharakter aktuellerCharakter;

    /**
     * Instantiates a new Trainer controller.
     *
     * @param gameHubController the game hub controller
     * @param partyController   the party controller
     * @param viewController    the view controller
     */
    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.viewController = viewController;
        this.aktuellerCharakter = partyController.getParty().getHauptCharakter();
        this.trainerKlasseAendernView = new TrainerKlasseAendernView(this);
        this.trainerAttributeAendernView = new TrainerAttributeAendernView(this);
        this.trainerSpezialisierungAendernView = new TrainerSpezialisierungAendernView(this);
        this.trainerFaehigkeitAendernView = new TrainerFaehigkeitAendernView(this);
        Button btnKlasseaendern = new Button("Klasse ändern");
        Button btnSpezialisierungAendern = new Button("Spezialisierung ändern");
        Button btnFaehigkeitAendern = new Button("Fähigkeiten ändern");
        Button btnAttributeAendern = new Button("Attribute ändern");
        Button btnZurueck = new Button("Zurück");
        btnKlasseaendern.setOnAction(event -> trainerKlasseAendernAnzeigen());
        btnSpezialisierungAendern.setOnAction(event -> trainerSpezialisierungAendernView());
        btnFaehigkeitAendern.setOnAction(event -> trainerFaehigkeitenAendernAnzeigen());
        btnAttributeAendern.setOnAction(event -> trainerAttributeAendernAnzeigen());
        btnZurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        this.trainerMenuButtons = new ArrayList<>(Arrays.asList(btnKlasseaendern, btnSpezialisierungAendern, btnFaehigkeitAendern, btnAttributeAendern, btnZurueck));
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

    /**
     * Trainer anzeigen.
     */
// Methoden
    public void trainerAnzeigen() {
        trainerView.aktualisiereParty();
        viewController.anmelden(this.trainerView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Trainer klasse aendern anzeigen.
     */
    public void trainerKlasseAendernAnzeigen() {
        trainerKlasseAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerKlasseAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Trainer spezialisierung aendern view.
     */
    public void trainerSpezialisierungAendernView() {
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerSpezialisierungAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);

    }

    /**
     * Trainer attribute aendern anzeigen.
     */
    public void trainerAttributeAendernAnzeigen() {
        trainerAttributeAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerAttributeAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Oeffnet die TrainerFaehigkeitAendernView
     *
     * @author 11777914 OLt Oliver Ebert
     * @since 04.12.2023
     */
    public void trainerFaehigkeitenAendernAnzeigen(){
        viewController.anmelden(trainerFaehigkeitAendernView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);

    }

    public void setCharakterAuswahl(SpielerCharakter charakter) {
        this.aktuellerCharakter = charakter;
    }

    private void faehigkeitenZuruecksetzen() {

    }

    private void faehigkeitenLernen(Faehigkeit faehigkeit) {
    }

    /**
     * Attribut aendern.
     *
     * @param zuAenderndesAttribut und boolschen Wert erhoehen. (true = erhöhen, false = verringern)
     * @param erhoehen             the erhoehen
     */
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

    /**
     * Gets game hub controller.
     *
     * @return the game hub controller
     */
    public GameHubController getGameHubController() {
        return gameHubController;
    }

    /**
     * Gets party controller.
     *
     * @return the party controller
     */
    public PartyController getPartyController() {
        return partyController;
    }

    /**
     * Gott modus.
     * @author Thomas Maass
     * @since 05.12.2023
     */
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

    /**
     * Sets trainer hintergrund.
     *
     * @return the trainer hintergrund
     */
    public static Background setzeTrainerHintergrund() {
        return (new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

    /**
     * Aktuelle nach hinten.
     */
    public void aktuelleNachHinten() {
        viewController.aktuelleNachHinten();
    }

    /**
     * Klasse aendern boolean.
     *
     * @param zielKlasse the ziel klasse
     * @return the boolean
     */
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

    /**
     * Spezialisierung aendern boolean.
     *
     * @param zielSpezialisierung the ziel spezialisierung
     * @return the boolean
     */
    public boolean spezialisierungAendern(String zielSpezialisierung){
        if(basisKostenSpezialisierungWechseln > partyController.getPartyGold()){
            return false;
        } else{
            CharakterController.spezialisierungAendern(aktuellerCharakter, zielSpezialisierung);
            partyController.getParty().setGold(partyController.getPartyGold()-basisKostenSpezialisierungWechseln);
        }
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        return true;
    }

    /**
     * Trainer spezialisierungprüfen.
     */
    public void trainerSpezialisierungprüfen(){


    }

    /**
     * Aktualisiere party.
     */
    public void aktualisiereParty(){

    }

    /**
     * Gets aktueller charakter.
     *
     * @return the aktueller charakter
     */
    public SpielerCharakter getAktuellerCharakter() {
        return aktuellerCharakter;
    }
}
