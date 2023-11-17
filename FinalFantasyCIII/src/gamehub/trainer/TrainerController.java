package gamehub.trainer;

import charakter.controller.CharakterController;
import charakter.model.klassen.Klasse;
import gamehub.GameHubController;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import party.PartyController;

/**
 * @author Thomas Maass
 * @since Initiale Befuellung des Controllers mit den Variablen und Methoden.
 */

public class TrainerController {
    private GameHubController gameHubController;
    private PartyController partyController;
    private Trainer trainer;

    public TrainerController(GameHubController gameHubController, PartyController partyController) {
        this.gameHubController = gameHubController;
        this.partyController = partyController;
        this.trainer = new Trainer(this);
    }

    // Methoden
    public void trainerAnzeigen() {
        // Aufruf der eigentlichen Methode trainerAnzeigen !
        trainer.trainerAnzeigen();
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
    } //@TODO : Vllt. Aenderung der Bezeichnung um zwischen der Klasse (hat jeder) und der spezialisierung (ab gewissen lvl. mgl) !!! /TM

    public GameHubController getGameHubController() {
        return gameHubController;
    }

    public PartyController getPartyController() {
        return partyController;
    }

}
