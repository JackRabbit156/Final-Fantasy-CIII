package gamehub.trainer;

import charakter.model.klassen.Klasse;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import party.PartyController;

/**
 * @author Thomas Maass
 * @since Initiale Befuellung des Controllers mit den Variablen und Methoden.
 */
public class TrainerController {

    private Trainer trainer;

    // Methoden
    public void trainerAnzeigen(PartyController partyController) {

        System.out.println("Willkommen beim Trainer !");
        System.out.println("Der Trainer ist insolvent.");
        trainerAnzeigen(partyController);
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

}
