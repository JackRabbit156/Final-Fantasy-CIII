package kampf;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.material.Material;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.ZufallsZahlenGenerator;
import party.Party;
import party.PartyController;
import statistik.GameOver;
import statistik.Statistik;
import statistik.StatistikController;

import java.util.ArrayList;
import java.util.Map;

public class KampfController {
    private FeindController feindController;
    private PartyController partyController;
    private StatistikController statistikController;
    private GameController gameController;
    private GameHubController gameHubController;

    public KampfController(FeindController feindController, PartyController partyController, StatistikController statistikController, GameController gameController, GameHubController gameHubController) {
        this.feindController = feindController;
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.gameController = gameController;
        this.gameHubController = gameHubController;
    }

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
            int gewonnenesGold = ((int) Math.floor(partyController.getPartyLevel()) * 10);
            partyController.goldHinzufuegen(gewonnenesGold);
            for (SpielerCharakter spielerCharakter : ueberlebende) {
                CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
                System.out.println(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!");
            }
            statistikController.goldErhoehen(gewonnenesGold);
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.gewonneneKaempfeErhoehen();
            boolean ausruestungsloot = (ZufallsZahlenGenerator.zufallsZahlIntAb1(10) <= 1);
            if(ausruestungsloot){
                int ausruestungsArt = ZufallsZahlenGenerator.zufallsZahlIntAb1(3);
                if(ausruestungsArt == 1){
                    partyController.ausruestungsgegenstandHinzufuegen(feindController.getFeinde()[0].getWaffe());
                    System.out.println(feindController.getFeinde()[0].getWaffe() + " erhalten!");
                }
                if(ausruestungsArt == 2){
                    partyController.ausruestungsgegenstandHinzufuegen(feindController.getFeinde()[0].getRuestung());
                    System.out.println(feindController.getFeinde()[0].getRuestung() + " erhalten!");
                }
                if(ausruestungsArt == 3){
                    partyController.ausruestungsgegenstandHinzufuegen(feindController.getFeinde()[0].getAccessoires()[0]);
                    System.out.println(feindController.getFeinde()[0].getAccessoires()[0] + " erhalten!");
                }
            }
            Material material = Material.zufaelligeMaterialArt();
            partyController.materialHinzufuegen(material, ((int) Math.floor(partyController.getPartyLevel())));
            System.out.println(((int) Math.floor(partyController.getPartyLevel()))+ "x " + material.getClass().getSimpleName() + " erhalten." );
            System.out.println("Sie haben " + gewonnenesGold + " Gold erhalten.");
        }
        if (ueberlebende.size() == 0) {
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.verloreneKaempfeErhoehen();
            if (partyController.getPartyGold() < (Math.floor(partyController.getPartyLevel() * 2.5))) {
                partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel() * 2.5));
                if (gameController.isHardcore()) {
                    party.getHauptCharakter().setGesundheitsPunkte(1);
                    party.setNebenCharakter(new SpielerCharakter[3]);
                    System.out.println("Ihr Hauptcharakter wurde fuer " + ((int)(Math.floor(partyController.getPartyLevel() * 2.5))) + "Gold wiederbelebt. Ihre Soelnder sind gestorben!");
                } else {
                    for (SpielerCharakter spielerCharakter : kaputte) {
                        spielerCharakter.setGesundheitsPunkte(1);
                    }
                System.out.println("Ihre ohnmaechtigen Charaktere wurden fuer " + ((int)(Math.floor(partyController.getPartyLevel() * 2.5))) + "Gold wiederbelebt.");
                }
                gameHubController.hubAnzeigen();
            } else {
                GameOver.gameOverAnzeigen(statistikController.getStatistik(), partyController);
            }
        }

    }
}
