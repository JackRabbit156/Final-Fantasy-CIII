package de.bundeswehr.auf.final_fantasy.menu.trainer;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.AttributCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Attribute;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Verbrauchsgegenstaende;
import de.bundeswehr.auf.final_fantasy.menu.gamehub.GameHubController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.menu.trainer.view.*;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

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

    public final static int BASIS_KOSTEN_ATTRIBUTE_AENDERN = 1;
    public final static int BASIS_KOSTEN_FAEHIGKEITEN_WECHSELN = 1;
    public final static int BASIS_KOSTEN_KLASSE_WECHSELN = 50;
    public final static int BASIS_KOSTEN_SPEZIALISIERUNG_WECHSELN = 100;

    private SpielerCharakter aktuellerCharakter;
    private final List<Button> attributeButtons;
    private final GameHubController gameHubController;
    private final List<Button> inViewButtons;
    private final PartyController partyController;
    private final AttributeAendernView trainerAttributeAendernView;
    private final FaehigkeitAendernView trainerFaehigkeitAendernView;
    private final KlasseAendernView trainerKlasseAendernView;
    private final List<Button> trainerMenuButtons;
    private final SpezialisierungAendernView trainerSpezialisierungAendernView;
    private final TrainerView trainerView;
    private final ViewController viewController;

    /**
     * Instantiates a new Trainer de.bundeswehr.auf.final_fantasy.controller.
     *
     * @param gameHubController the game hub de.bundeswehr.auf.final_fantasy.controller
     * @param partyController   the de.bundeswehr.auf.final_fantasy.party de.bundeswehr.auf.final_fantasy.controller
     * @param viewController    the de.bundeswehr.auf.final_fantasy.menu.overlay.view de.bundeswehr.auf.final_fantasy.controller
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public TrainerController(GameHubController gameHubController, PartyController partyController, ViewController viewController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.viewController = viewController;
        aktuellerCharakter = partyController.getParty().getHauptCharakter();
        trainerKlasseAendernView = new KlasseAendernView(this);
        trainerAttributeAendernView = new AttributeAendernView(this, viewController);
        trainerSpezialisierungAendernView = new SpezialisierungAendernView(this);
        trainerFaehigkeitAendernView = new FaehigkeitAendernView(this);

        Button btnKlasseaendern = new Button("Klasse");
        btnKlasseaendern.setOnAction(event -> trainerKlasseAendernAnzeigen());
        Button btnSpezialisierungAendern = new Button("Spezialisierung");
        btnSpezialisierungAendern.setOnAction(event -> trainerSpezialisierungAendernView());
        Button btnFaehigkeitAendern = new Button("Fähigkeiten");
        btnFaehigkeitAendern.setOnAction(event -> trainerFaehigkeitenAendernAnzeigen());
        Button btnAttributeAendern = new Button("Attribute");
        btnAttributeAendern.setOnAction(event -> trainerAttributeAendernAnzeigen());
        Button btnZurueck = new Button("Zurück");
        btnZurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        Button btnZurueckAttribute = new Button("Zurück");
        btnZurueckAttribute.setOnAction(event -> viewController.aktuelleNachHinten());

        attributeButtons = new ArrayList<>();
        attributeButtons.add(btnZurueckAttribute);
        inViewButtons = new ArrayList<>();
        inViewButtons.add(btnZurueck);
        trainerMenuButtons = Arrays.asList(btnKlasseaendern, btnSpezialisierungAendern, btnFaehigkeitAendern, btnAttributeAendern, btnZurueck);

        VBox gottModus = new VBox();
        gottModus.setAlignment(Pos.BOTTOM_LEFT);
        Text pi = new Text("\u03c0");
        gottModus.getChildren().add(pi);
        pi.setOnMouseClicked(event -> {
            gottModus();
            System.out.println("Cheat Aktiv");
        });
        trainerView = new TrainerView(viewController, this);
        trainerView.setLeft(gottModus);
    }

    /**
     * setzen des Hingrundbildes für alle Ansichten im Trainer
     *
     * @return the de.bundeswehr.auf.final_fantasy.menu.trainer hintergrund
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public static Background setzeTrainerHintergrund() {
        return (new Background(new BackgroundImage(new Image("/background/hintergrundtrainer.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

    /**
     * wird genutzt um den zurückButton mit Leben zu befüllen und eine Ansicht zurück zu gehen
     * Aktuelle nach hinten.
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void aktuelleNachHinten() {
        viewController.aktuelleNachHinten();
    }

    /**
     * Erhöht oder verringert das entsprechende Attribut um 1 und passt die offenen Attribute an.
     *
     * @param zuAenderndesAttribut Attribut welches verändert werden soll
     * @param erhoehen             true = erhöhen um 1, false = verringern um 1
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void attributAendern(String zuAenderndesAttribut, boolean erhoehen) {
        int wert = erhoehen ? 1 : -1;
        AttributCharakter attribute = aktuellerCharakter.getAttribute();
        switch (zuAenderndesAttribut) {
            case Attribute.MAX_GP:
                CharakterController.maxGesundheitsPunkteVerbessern(attribute, wert);
                break;
            case Attribute.MAX_MP:
                attribute.setMaxManaPunkte(attribute.getMaxManaPunkte() + wert);
                break;
            case Attribute.A:
                attribute.setPhysischeAttacke(attribute.getPhysischeAttacke() + wert);
                break;
            case Attribute.MA:
                attribute.setMagischeAttacke(attribute.getMagischeAttacke() + wert);
                break;
            case Attribute.G:
                attribute.setGenauigkeit(attribute.getGenauigkeit() + wert);
                break;
            case Attribute.V:
                attribute.setVerteidigung(attribute.getVerteidigung() + wert);
                break;
            case Attribute.MV:
                attribute.setMagischeVerteidigung(attribute.getMagischeVerteidigung() + wert);
                break;
            case Attribute.R:
                attribute.setResistenz(attribute.getResistenz() + wert);
                break;
            case Attribute.B:
                attribute.setBeweglichkeit(attribute.getBeweglichkeit() + wert);
                break;
            default:
                break;
        }
        aktuellerCharakter.setOffeneAttributpunkte(aktuellerCharakter.getOffeneAttributpunkte() - wert);
        trainerAttributeAendernView.anzeigeVorbereiten();
    }

    /**
     * Stellt die Verbindung zwischen TrainerController und CharakterController fürs lernen der Fähigkeiten her und aktualisiert danach die View.
     *
     * @param faehigkeit übergibt die Fähigkeit zur weiteren Bearbeitung
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
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

    /**
     * Stellt die Verbindung zwischen TrainerController und CharakterController fürs zurücksetzten der Faehigkeiten her und aktualisiert danach die View.
     *
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     */
    public void faehigkeitenZuruecksetzen() {
        CharakterController.faehigkeitenZuruecksetzen(aktuellerCharakter);
        trainerFaehigkeitAendernView.anzeigeVorbereiten();
    }

    public SpielerCharakter getAktuellerCharakter() {
        return aktuellerCharakter;
    }

    public GameHubController getGameHubController() {
        return gameHubController;
    }

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
            this.getPartyController().materialHinzufuegen(Materialien.EISENERZ, 999999);
            this.getPartyController().materialHinzufuegen(Materialien.GOLDERZ, 999999);
            this.getPartyController().materialHinzufuegen(Materialien.MITHRIL, 999999);
            this.getPartyController().materialHinzufuegen(Materialien.POPEL, 999999);
            this.getPartyController().materialHinzufuegen(Materialien.SCHLEIM, 999999);
            this.getPartyController().materialHinzufuegen(Materialien.SILBERERZ, 999999);
            // Setzen von Verbrauchmaterial
            this.getPartyController().verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstaende.GROSSER_HEILTRANK, 999999);
            this.getPartyController().verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstaende.GROSSER_MANATRANK, 999999);
            dasTeam[0].setGeschichte("Markus, ein junger Mann, war einst ein gewöhnlicher Büroangestellter, bis er in einen unerklärlichen Unfall geriet, der ihn mit erstaunlichen Kräften ausstattete. Nachdem er einer explosiven Energiewelle ausgesetzt war, entdeckte er, dass sein Körper unverwundbar geworden war. Er konnte sich nicht erklären, wie oder warum dies geschah, aber er beschloss, seine Kräfte zum Wohl anderer einzusetzen.\n" +
                    "Markus, der nun unverwundbar war, nutzte seine neuen Fähigkeiten, um unschuldige Menschen vor Bedrohungen zu schützen. Er wurde zu einem Symbol der Hoffnung und des Schutzes für die Stadt. Seine unverwundbare Haut und seine außergewöhnlichen Fähigkeiten machten ihn zu einem unüberwindbaren Verteidiger gegen das Verbrechen und zu einem leuchtenden Beispiel für Heldentum. Entschlossen, seine Kräfte für das Gute einzusetzen, strebt Markus danach, die Stadt vor jeglicher Gefahr zu bewahren und anderen zu dienen.");
            System.out.println("Markus aktiv");
        }

    }

    /**
     * Klasse ändern.
     *
     * @param zielKlasse übergibt die Zielklasse (TNK,MDD,PDD,HLR) und bekommt true zurück, wenn genug Geld für den
     *                   Klassenwechsel vorhanden war, und false, wenn nicht
     * @return boolean (false wenn nicht durchgeführt, true wenn durchgeführt. Führt zur Anzeige im Frontend ...
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public boolean klasseAendern(String zielKlasse) {
        if (partyController.getPartyGold() >= BASIS_KOSTEN_KLASSE_WECHSELN) {
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
            partyController.goldAbziehen(BASIS_KOSTEN_KLASSE_WECHSELN);
            return true;
        }
        else {
            return false;
        }
    }

    public void setCharakterAuswahl(SpielerCharakter charakter) {
        this.aktuellerCharakter = charakter;
    }

    /**
     * @param zielSpezialisierung welche Spezialisierung wurde gewählt
     * @return Selbe Taktik wie bei Klasse wechseln
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public boolean spezialisierungAendern(String zielSpezialisierung) {
        if (BASIS_KOSTEN_SPEZIALISIERUNG_WECHSELN > partyController.getPartyGold()) {
            return false;
        }
        else {
            CharakterController.spezialisierungAendern(aktuellerCharakter, zielSpezialisierung);
            partyController.getParty().setGold(partyController.getPartyGold() - BASIS_KOSTEN_SPEZIALISIERUNG_WECHSELN);
        }
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        return true;
    }

    /**
     * Steuert die aktualisierte Anzeige der Trainers
     *
     * @author Thomas Maass
     * @since 05.12.2023
     */
    public void trainerAnzeigen() {
        trainerView.aktualisiereParty();
        viewController.anmelden(this.trainerView, this.trainerMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Trainer attribute ändern anzeigen.
     */
    public void trainerAttributeAendernAnzeigen() {
        trainerAttributeAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerAttributeAendernView, this.attributeButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Öffnet die TrainerFaehigkeitAendernView
     *
     * @author 11777914 OLt Oliver Ebert
     * @since 04.12.2023
     */
    public void trainerFaehigkeitenAendernAnzeigen() {
        trainerFaehigkeitAendernView.anzeigeVorbereiten();
        viewController.anmelden(trainerFaehigkeitAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);

    }

    /**
     * Trainer Klasse ändern anzeigen.
     */
    public void trainerKlasseAendernAnzeigen() {
        trainerKlasseAendernView.getLblaktuelleKlasse().setText("");
        trainerKlasseAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerKlasseAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);
    }

    /**
     * Trainer spezialisierung ändern View.
     */
    public void trainerSpezialisierungAendernView() {
        trainerSpezialisierungAendernView.getLblaktuelleSpezialisierung().setText("");
        trainerSpezialisierungAendernView.aenderungVorbereiten();
        viewController.anmelden(trainerSpezialisierungAendernView, this.inViewButtons, AnsichtsTyp.MIT_OVERLAY);
    }

}
