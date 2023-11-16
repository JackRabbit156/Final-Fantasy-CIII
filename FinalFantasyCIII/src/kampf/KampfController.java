package kampf;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import party.Party;
import party.PartyController;
import statistik.StatistikController;

import java.util.ArrayList;

public class KampfController {
    private FeindController feindController;
    private PartyController partyController;
    private StatistikController statistikController;
    private GameController gameController;
    private GameHubController gameHubController;


    /**
     * Kampfende wird ausgewertet ->
     * Exp wird verteilt
     * Gold und Ressourcen werden verteilt
     * Statistik wird gepflegt
     * GameOver wird geprueft
     * Endet in Hub oder GameOver
     *
     * @author Nick
     * @since 16.11.2023
     */
    private void kampfAuswerten() {
        Party party = partyController.getParty();
        ArrayList<SpielerCharakter> ueberlebende = new ArrayList<>();
        ArrayList<SpielerCharakter> kaputte = new ArrayList<>();
        if (party.getHauptCharakter().getGesundheitsPunkte() > 0) {
            ueberlebende.add(party.getHauptCharakter());
        } else {
            kaputte.add(party.getHauptCharakter());
        }
        SpielerCharakter[] nebenCharakter = party.getNebenCharakter();
        for (int i = 0; i < nebenCharakter.length; i++) {
            if (nebenCharakter[i] != null && nebenCharakter[i].getGesundheitsPunkte() > 0) {
                ueberlebende.add(nebenCharakter[i]);
            } else if (nebenCharakter[i] != null) {
                kaputte.add(nebenCharakter[i]);
            }
        }
        if (ueberlebende.size() > 0) {
            int gewonnenesGold = (int) Math.floor(partyController.getPartyLevel() * 10);
            partyController.goldHinzufuegen(gewonnenesGold);
            for (SpielerCharakter spielerCharakter : ueberlebende) {
                CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
            }
            statistikController.goldErhoehen(gewonnenesGold);
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.gewonneneKaempfeErhoehen();
            //TODO RESSOURCEN DER GEGNER MIT EINER CHANCE INS GLOBALE INVENTAR PACKEN
            gameHubController.hubAnzeigen();
        }
        if (ueberlebende.size() == 0) {
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.verloreneKaempfeErhoehen();
            if (partyController.getPartyGold() < (Math.floor(partyController.getPartyLevel() * 2.5))) {
                partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel() * 2.5));
                if (gameController.isHardcore()) {
                    party.getHauptCharakter().setGesundheitsPunkte(1);
                    party.setNebenCharakter(new SpielerCharakter[3]);
                } else {
                    for (SpielerCharakter spielerCharakter : kaputte) {
                        spielerCharakter.setGesundheitsPunkte(1);
                    }
                }
                gameHubController.hubAnzeigen();
            } else {
                //gameOverAnzeigen();
            }
        }

    }
}
