package gamehub.taverne;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.soeldner.Kaempfer;
import charakter.model.klassen.soeldner.Magier;
import charakter.model.klassen.soeldner.Supporter;
import gamehub.GameHubController;
import hauptmenu.HauptmenuController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;
import statistik.GameOver;
import statistik.StatistikController;
import java.util.ArrayList;

public class TaverneController {

    private Taverne taverne;
    private PartyController partyController;
    private StatistikController statistikController;
    private int letzteGeneration;
    private GameHubController gameHubController;

    public TaverneController(PartyController partyController, StatistikController statistikController, GameHubController gameHubController) {
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.letzteGeneration = -4;
        this.gameHubController = gameHubController;
    }

    public void taverneAnzeigen() {
        Party party = partyController.getParty();
        if (statistikController.getStatistik().getDurchgefuehrteKaempfe() - letzteGeneration >= 3) {
            generiereSoeldner();
        }
        ArrayList<SpielerCharakter> nebenCharaktere = new ArrayList<>();
        for (int i = 0; i < party.getNebenCharakter().length; i++) {
            if (party.getNebenCharakter()[i] != null) {
                nebenCharaktere.add(party.getNebenCharakter()[i]);
            }
        }

        // Taverne-Auswahlmoeglichkeiten:
        System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1 = Ausruhen, fuer " + (int) Math.floor(partyController.getPartyLevel()) + " Gold die Gesundheits- und Manapunkte der gesamten Party wieder komplett auffuellen" + Farbauswahl.RESET);
        if (nebenCharaktere.size() < 3 && !keinSoeldnerVorhanden()) {
            System.out.println(Farbauswahl.CYAN + "2 = Soeldner anheuern" + Farbauswahl.RESET);
        }
        if (nebenCharaktere.size() > 0) {
            System.out.println(Farbauswahl.CYAN + "3 = Soeldner entlassen" + Farbauswahl.RESET);
        }
        System.out.println(Farbauswahl.CYAN + "4 = Zurueck ins GameHub" + Farbauswahl.RESET);
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe) {
            case 1:
                KonsolenAssistent.clear();
                ausruhen();
                break;
            case 2:
                KonsolenAssistent.clear();
                if (nebenCharaktere.size() < 3) {
                    zuEinstellendeMitgliederAnzeigen();
                } else {
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe - Party ist bereits voll!" + Farbauswahl.RESET);
                    taverneAnzeigen();
                }
                break;
            case 3:
                KonsolenAssistent.clear();
                if (nebenCharaktere.size() > 0) {
                    zuEntlassendeMitgliederAnzeigen();
                } else {
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe - Deine Party hat keine Mitglieder!" + Farbauswahl.RESET);
                    taverneAnzeigen();
                }
                break;
            case 4:
                KonsolenAssistent.clear();
                gameHubController.hubAnzeigen();
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                taverneAnzeigen();
                break;
        }
    }

    private boolean keinSoeldnerVorhanden() {
        for (int i = 0; i < taverne.getSoeldner().length; i++) {
            if (taverne.getSoeldner()[i] != null) {
                return false;
            }
        }
        return true;
    }

    private void generiereSoeldner() { // Soeldner werden wie Gegner zufällig generiert (Klasse, Attribute, Ausrüstung)
        SpielerCharakter[] generierteSoeldner = new SpielerCharakter[3];
        generierteSoeldner[0] = new Magier("Voldemort","Magischer DD","Klassenbester aus Hogwarts!",(int)Math.floor(partyController.getPartyLevel()));
        generierteSoeldner[1] = new Kaempfer("Kloppi","Physischer DD","Hauptschuleeeee - aufs Maul?",(int)Math.floor(partyController.getPartyLevel()));
        generierteSoeldner[2] = new Supporter("DerRiese","Tank","Alles fuers Team!",(int)Math.floor(partyController.getPartyLevel()));
        /*
        Nach jeweils X Kaempfen (ein Kampf zaehlt egal ob er gewonnen oder verloren wurde) werden die rekrutierbaren Soeldner in voller Anzahl neu generiert. (Bereits in die Party rekrutierte Soeldner bleiben bestehen).
         */
        letzteGeneration = statistikController.getStatistik().getDurchgefuehrteKaempfe();
        this.taverne = new Taverne(generierteSoeldner);
    }

    private void ausruhen() {
        Party party = partyController.getParty();
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int)Math.floor(partyController.getPartyLevel()));
            ArrayList<SpielerCharakter> nebenCharaktere = new ArrayList<>();
            for (int i = 0; i < party.getNebenCharakter().length; i++) {
                if (party.getNebenCharakter()[i] != null) {
                    nebenCharaktere.add(party.getNebenCharakter()[i]);
                }
            }
            party.getHauptCharakter().setGesundheitsPunkte(party.getHauptCharakter().getMaxGesundheitsPunkte());
            party.getHauptCharakter().setManaPunkte(party.getHauptCharakter().getMaxManaPunkte());
            for (SpielerCharakter spielerCharakter : nebenCharaktere) {
                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
                spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
            }
            System.out.println("" +
                    "            (                 ,&&&.\n" +
                    "             )                .,.&&\n" +
                    "            (  (              \\=__/\n" +
                    "                )             ,'-'.\n" +
                    "          (    (  ,,      _.__|/ /|\n" +
                    "           ) /\\ -((------((_|___/ |\n" +
                    "         (  // | (`'      ((  `'--|\n" +
                    "       _ -.;_/ \\\\--._      \\\\ \\-._/.\n" +
                    "      (_;-// | \\ \\-'.\\    <_,\\_\\`--'|\n" +
                    "      ( `.__ _  ___,')      <_,-'__,'\n" +
                    "       `'(_ )_)(_)_)'");
            System.out.println();
            System.out.println();
            System.out.println(
                    "      _____|~~\\_____      _____________\n" +
                            "  _-~               \\    |    \\\n" +
                            "  _-    | )     \\    |__/   \\   \\\n" +
                            "  _-         )   |   |  |     \\  \\\n" +
                            "  _-    | )     /    |--|      |  |\n" +
                            " __-_______________ /__/_______|  |_________\n" +
                            "(                |----         |  |\n" +
                            " `---------------'--\\\\\\\\      .`--'\n" +
                            "                              `||||");
            taverneAnzeigen();
        } else {
            System.out.println("Nicht genug Gold!");
            taverneAnzeigen();
        }
    }

    private void zuEinstellendeMitgliederAnzeigen() { // Im Hub steht eine feste Anzahl an Soeldnern zur Verfuegung die in einer Uebersicht eingesehen werden koennen (Liste, Durchschaltmenue, etc..)
        for (int i = 0; i < taverne.getSoeldner().length; i++) {
            if (taverne.getSoeldner()[i] != null) {
                System.out.println(Farbauswahl.GREEN_BOLD + "        " + (i+1) + ".Auswahlmoeglichkeit" + Farbauswahl.RESET);
//              TODO ?  System.out.println("Grafische Darstellung?" + taverne.getSoeldner()[i].getGrafischeDarstellung());
                System.out.println(Farbauswahl.YELLOW + "Name: " + taverne.getSoeldner()[i].getName() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.YELLOW + "Level: " + taverne.getSoeldner()[i].getLevel() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.YELLOW + "Klasse: " + taverne.getSoeldner()[i].getKlasse().getBezeichnung() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.YELLOW + "Geschichte: " + taverne.getSoeldner()[i].getGeschichte() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.YELLOW + "Kosten Goldmuenzen: " + (int)Math.floor(partyController.getPartyLevel()) + Farbauswahl.RESET);
                System.out.println(Farbauswahl.RED + "----------------------------------------" + Farbauswahl.RESET);
            }
        }
        System.out.println("4: zurueck");
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe) {
            case 1:
            case 2:
            case 3:
                if (taverne.getSoeldner()[eingabe-1] != null ) {
                    teammitgliedEinstellen(eingabe-1);
                } else {
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                    taverneAnzeigen();
                }
                break;
            case 4:
                taverneAnzeigen();
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                taverneAnzeigen();
                break;
        }
    }

    private void zuEntlassendeMitgliederAnzeigen() {
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCharakter()[i] != null) {
                System.out.println(Farbauswahl.YELLOW + (i+1) + ": " + partyController.getParty().getNebenCharakter()[i].getName() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.RED + "----------------------------------------" + Farbauswahl.RESET);
            }
        }
        System.out.println("4: zurueck");
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe) {
            case 1:
            case 2:
            case 3:
                    SpielerCharakter[] soeldner = partyController.getParty().getNebenCharakter();
                if (soeldner[eingabe-1] != null ) {
                    teammitgliedEntlassen(soeldner[eingabe-1]);
                } else {
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                    taverneAnzeigen();
                }
                break;
            case 4:
                taverneAnzeigen();
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                taverneAnzeigen();
                break;
        }
    }

    private void teammitgliedEinstellen(int index)    { // Soeldner können im Hub rekrutiert werden
        if (partyController.getPartyGold() >= (int)Math.floor(partyController.getPartyLevel()*2)){
            SpielerCharakter[] soeldner = taverne.getSoeldner();
            partyController.goldAbziehen((int)Math.floor(partyController.getPartyLevel()));
            partyController.teammitgliedHinzufuegen(soeldner[index]);
            System.out.println(Farbauswahl.GREEN + "Soeldner angeheuert!" + Farbauswahl.RESET);
            soeldner[index] = null; // Beim rekrutieren eines Soeldners wird dieser aus der Uebersicht entfernt und kein neuer Soeldner erzeugt. Die Anzahl verbleibender Soeldner bleibt vorerst reduziert

        } else {
            System.out.println("Deine Armut kotzt mich an!");
        }
            taverneAnzeigen();
    }

    private void teammitgliedEntlassen(SpielerCharakter soeldner) {
        /*
        Beim “Entlassen” von Soeldnern geht die gewechselte Ausruestung zurueck ins Spielerinventar
        Die Ruestung die der Soeldner initial beim rekrutieren traegt geht verloren, bekommt der Soeldner neue Ausruestungsgegenstaende vom Spieler zugewiesen gehen diese Ausruestungsteile nicht verloren
         */
        partyController.teammitgliedEntfernen(soeldner);
        System.out.println("Soeldner entlassen!");
        taverneAnzeigen();
    }

//    private void ausruestungWechseln() {
//        partyController.getParty().getHauptCharakter().getRuestung();
//        partyController.getParty().getHauptCharakter().getWaffe();
//        partyController.getParty().getHauptCharakter().getAccessoires();
//    }
}
