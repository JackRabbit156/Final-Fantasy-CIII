package de.bundeswehr.auf.final_fantasy.menu.trainer;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.*;
import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Trainer de.bundeswehr.auf.final_fantasy.controller.
 *
 * @author Thomas Maass
 * @since 05.12.2023
 */
public class TrainerController {
    private GameHubController gameHubController;
    private PartyController partyController;
    private ViewController viewController;
    private List<Button> trainerMenuButtons;
    private List<Button> attributeButtons;
    private List<Button> inViewButtons;

    //Views
    private TrainerView trainerView;
    private KlasseAendernView trainerKlasseAendernView;
    private AttributeAendernView trainerAttributeAendernView;
    private SpezialisierungAendernView trainerSpezialisierungAendernView;
    private FaehigkeitAendernView trainerFaehigkeitAendernView;
    private AusruestungsGegenstandInventar ausgezogeneAusruestung;

    private int auswahl = 0;

    public final static int basisKostenKlasseWechseln = 50;
    public final static int basisKostenSpezialisierungWechseln = 100;
    public final static int basisKostenAttributeAendern = 1;
    public final static int basisKostenFaehigkeitenAendern = 1;

    private SpielerCharakter aktuellerCharakter;

    /**
     * Instantiates a new Trainer de.bundeswehr.auf.final_fantasy.controller.
     * @author Thomas Maass
     * @since 05.12.2023
     * @param gameHubController the game hub de.bundeswehr.auf.final_fantasy.controller
     * @param partyController   the de.bundeswehr.auf.final_fantasy.party de.bundeswehr.auf.final_fantasy.controller
     * @param viewController    the de.bundeswehr.auf.final_fantasy.menu.overlay.view de.bundeswehr.auf.final_fantasy.controller
     */
    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        ausgezogeneAusruestung = new AusruestungsGegenstandInventar();
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.viewController = viewController;
        this.aktuellerCharakter = partyController.getParty().getHauptCharakter();
        this.trainerKlasseAendernView = new KlasseAendernView(this);
        this.trainerAttributeAendernView = new AttributeAendernView(this);
        this.trainerSpezialisierungAendernView = new SpezialisierungAendernView(this);
        this.trainerFaehigkeitAendernView = new FaehigkeitAendernView(this);
        Button btnKlasseaendern = new Button("Klasse ändern");
        Button btnSpezialisierungAendern = new Button("Spezialisierung ändern");
        Button btnFaehigkeitAendern = new Button("Fähigkeiten ändern");
        Button btnAttributeAendern = new Button("Attribute ändern");
        Button btnZurueck = new Button("Zurück");
        Button btnZurueckAttribute = new Button("Zurück");
        btnKlasseaendern.setOnAction(event -> trainerKlasseAendernAnzeigen());
        btnSpezialisierungAendern.setOnAction(event -> trainerSpezialisierungAendernView());
        btnFaehigkeitAendern.setOnAction(event -> trainerFaehigkeitenAendernAnzeigen());
        btnAttributeAendern.setOnAction(event -> {aktuellenCharakterAusziehen(); trainerAttributeAendernAnzeigen();});
        btnZurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        btnZurueckAttribute.setOnAction(event -> {aktuellenCharakterAnziehen();viewController.aktuelleNachHinten();});
        attributeButtons = new ArrayList<>();
        attributeButtons.add(btnZurueckAttribute);
        inViewButtons = new ArrayList<>();
        inViewButtons.add(btnZurueck);
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



    }

    /** Steuer die aktualisierte Anzeige der Tariners
     * @author Thomas Maass
     * @since 05.12.2023
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
        trainerKlasseAendernView.getLblaktuelleKlasse().setText("");
        trainerKlasseAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerKlasseAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Trainer spezialisierung aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
     */
    public void trainerSpezialisierungAendernView() {
        trainerSpezialisierungAendernView.getLblaktuelleSpezialisierung().setText("");
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerSpezialisierungAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);

    }

    /**
     * Trainer attribute aendern anzeigen.
     */
    public void trainerAttributeAendernAnzeigen() {
        trainerAttributeAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerAttributeAendernView, this.attributeButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Oeffnet die TrainerFaehigkeitAendernView
     *
     * @author 11777914 OLt Oliver Ebert
     * @since 04.12.2023
     */
    public void trainerFaehigkeitenAendernAnzeigen(){
        trainerFaehigkeitAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerFaehigkeitAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);

    }
    /**
         * Setz die Variable aktueller Charakter.
     * @author Thomas Maass
     * @param charakter the de.bundeswehr.auf.final_fantasy.charakter
     * @since 05.12.2023
     **/
    public void setCharakterAuswahl(SpielerCharakter charakter) {
        this.aktuellerCharakter = charakter;
    }



    /**
     * Stellt die Verbindung zwischen TrainerController und CharakterController fürs zurücksetzten der Faehigkeiten her und aktualisiert danach die View.
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     */
    public void faehigkeitenZuruecksetzen() {
        CharakterController.faehigkeitenZuruecksetzen(aktuellerCharakter);
        trainerFaehigkeitAendernView.anzeigeVorbereiten();
    }

    /**
     * Stellt die Verbindung zwischen TrainerController und CharakterController fürs lernen der Faehigkeiten her und aktualisiert danach die View.
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     * @param faehigkeit uebergibt die Faehigkeit zur weiteren Bearbeitung
     */
    public void faehigkeitenLernen(Faehigkeit faehigkeit) {
        int offeneFaehigkeitspunkte = aktuellerCharakter.getOffeneFaehigkeitspunkte();
        if (offeneFaehigkeitspunkte > 0) {
            aktuellerCharakter.setOffeneFaehigkeitspunkte(offeneFaehigkeitspunkte - 1);
            aktuellerCharakter.setVerteilteFaehigkeitspunkte(aktuellerCharakter.getVerteilteFaehigkeitspunkte() + 1);
            CharakterController.faehigkeitLernen(aktuellerCharakter, faehigkeit);
        }
        trainerFaehigkeitAendernView.anzeigeVorbereiten();
    }

    /** steuert die Aenderung von Attributen im Charakter
     * Attribut aendern.
     *
     * @param zuAenderndesAttribut und boolschen Wert erhoehen. (true = erhöhen, false = verringern)
     * @param erhoehen             the erhoehen
     * @author Thomas Maass
     * @since 05.12.2023
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
     * Gets game hub de.bundeswehr.auf.final_fantasy.controller.
     *
     * @return the game hub de.bundeswehr.auf.final_fantasy.controller
     */
    public GameHubController getGameHubController() {
        return gameHubController;
    }

    /**
     * Gets de.bundeswehr.auf.final_fantasy.party de.bundeswehr.auf.final_fantasy.controller.
     *
     * @return the de.bundeswehr.auf.final_fantasy.party de.bundeswehr.auf.final_fantasy.controller
     */
    public PartyController getPartyController() {
        return partyController;
    }

    /**
     * Gott modus.
     *
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
     * setzen des Hingrundbildes für alle Ansichten im Trainer
     * @author Thomas Maass
     * @since 05.12.2023
     * @return the de.bundeswehr.auf.final_fantasy.menu.trainer hintergrund
     */
    public static Background setzeTrainerHintergrund() {
        return (new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

    /** wird genutzt um den zurueckeButton mit Leben zu befuellen und eine Ansicht zurueck zu gehen
     * Aktuelle nach hinten.
     * @author Thomas Maass
     * @since 05.12.2023
     *
     */
    public void aktuelleNachHinten() {
        viewController.aktuelleNachHinten();
    }

    /**
     * Klasse aendern boolean.
     *
     * @param zielKlasse übergibt die Zieklasse (TNK,MDD,PDD,HLR) und bekommt entweder true/flase zurück um die Bestaetigung zu haben
     * @return boolean (false wenn nicht durgefuehrt, true wenn durchgefuerhrt. Fuehrt zur Anzeige im Frontend ...
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public boolean klasseAendern(String zielKlasse) {
        if (partyController.getPartyGold() >= basisKostenKlasseWechseln) {
            switch (zielKlasse) {
                case Klasse.TNK:
                    CharakterController.klasseAendern(aktuellerCharakter, new TNK(), partyController);
                    break;
                case Klasse.PDD:
                    CharakterController.klasseAendern(aktuellerCharakter, new PDD(), partyController);
                    break;
                case Klasse.MDD:
                    CharakterController.klasseAendern(aktuellerCharakter, new MDD(), partyController);
                    break;
                case Klasse.HLR:
                    CharakterController.klasseAendern(aktuellerCharakter, new HLR(), partyController);
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
     * @author Thomas Maass
     *
     * @param zielSpezialisierung welche Spezialisierung wurde gewaehlt
     * @return Selbe Taktik wie bei Klasse wechseln
     * @since 05.12.2023
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
     * Der aktuelle Charakter zieht vor der Attributsanpassung seine Ausrüstung wieder aus
     * @author Thomas
     * @since 05.12.203
     */
    public void aktuellenCharakterAusziehen(){
        CharakterController.ausruestungAusziehenIgnoriereSoeldnerItem(aktuellerCharakter, aktuellerCharakter.getRuestung(), ausgezogeneAusruestung);
        CharakterController.ausruestungAusziehenIgnoriereSoeldnerItem(aktuellerCharakter, aktuellerCharakter.getWaffe(), ausgezogeneAusruestung);
        if(aktuellerCharakter.getAccessoire(0) != null){
        CharakterController.ausruestungAusziehenIgnoriereSoeldnerItem(aktuellerCharakter, aktuellerCharakter.getAccessoire(0), ausgezogeneAusruestung);
        }
        if(aktuellerCharakter.getAccessoire(1) != null){
            CharakterController.ausruestungAusziehenIgnoriereSoeldnerItem(aktuellerCharakter, aktuellerCharakter.getAccessoire(1), ausgezogeneAusruestung);
        }
        if(aktuellerCharakter.getAccessoire(2) != null){
            CharakterController.ausruestungAusziehenIgnoriereSoeldnerItem(aktuellerCharakter, aktuellerCharakter.getAccessoire(2), ausgezogeneAusruestung);
        }
    }

    /**
     * Der aktuelle Charakter zieht nach der Attributsanpassung seine Ausrüstung wieder an
     * @author Thomas
     * @since 05.12.203
     */
    public void aktuellenCharakterAnziehen(){
        CharakterController.ausruestungAnlegen(aktuellerCharakter, ausgezogeneAusruestung.getInventarWaffen().get(0), ausgezogeneAusruestung);
        CharakterController.ausruestungAnlegen(aktuellerCharakter, ausgezogeneAusruestung.getInventarRuestung().get(0), ausgezogeneAusruestung);
        if(ausgezogeneAusruestung.getInventarAccessiore().size() > 0){
            CharakterController.ausruestungAnlegen(aktuellerCharakter, ausgezogeneAusruestung.getInventarAccessiore().get(0), ausgezogeneAusruestung);
        }
        if(ausgezogeneAusruestung.getInventarAccessiore().size() > 0){
            CharakterController.ausruestungAnlegen(aktuellerCharakter, ausgezogeneAusruestung.getInventarAccessiore().get(0), ausgezogeneAusruestung);
        }
        if(ausgezogeneAusruestung.getInventarAccessiore().size() > 0){
            CharakterController.ausruestungAnlegen(aktuellerCharakter, ausgezogeneAusruestung.getInventarAccessiore().get(0), ausgezogeneAusruestung);
        }
    }


    /**
     * holt und uebergibt den aktuell ausgewaehlten Charakter
     * @author Thomas Maass
     * @since 05.12.2023
     * @return the aktueller de.bundeswehr.auf.final_fantasy.charakter
     */
    public SpielerCharakter getAktuellerCharakter() {
        return aktuellerCharakter;
    }
}
