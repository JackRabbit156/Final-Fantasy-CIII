package gamehub.taverne;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.soeldner.Kaempfer;
import charakter.model.klassen.soeldner.Magier;
import charakter.model.klassen.soeldner.Supporter;
import gamehub.GameHubController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;
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
        this.letzteGeneration = 0;
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
        if (nebenCharaktere.size() < 3) {
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
                    System.out.println("Party voll!");
                    taverneAnzeigen();
                }
                break;
            case 3:
                KonsolenAssistent.clear();
                if (nebenCharaktere.size() > 0) {
                    zuEntlassendeMitgliederAnzeigen();
                } else {
                    System.out.println("Keine Freunde!");
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

    private void generiereSoeldner() {
        SpielerCharakter[] generierteSoeldner = new SpielerCharakter[3];
        generierteSoeldner[0] = new Kaempfer("Kloppi","Physicher DD","Hauptschuleeeee - aufs Maul?",(int)Math.floor(partyController.getPartyLevel()));
        generierteSoeldner[1] = new Magier("Voldemort","Magischer DD","Klassenbester aus Hogwarts!",(int)Math.floor(partyController.getPartyLevel()));
        generierteSoeldner[2] = new Supporter("DerRiese","Tank","Alles fuers Team!",(int)Math.floor(partyController.getPartyLevel()));
        letzteGeneration = statistikController.getStatistik().getDurchgefuehrteKaempfe();
        this.taverne = new Taverne(generierteSoeldner);
    }

    private void ausruhen() {
        Party party = partyController.getParty();
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
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


    private void zuEinstellendeMitgliederAnzeigen() {
        for (int i = 0; i < taverne.getSoeldner().length; i++) {
            if (taverne.getSoeldner()[i] != null) {
                //TODO Ausgabe eines Charakters anpassen
                System.out.println((i+1) + ": " + taverne.getSoeldner()[i]);
            }
        }
        System.out.println("4: zurueck");
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe) {
            case 1:
            case 2:
            case 3:
                if (taverne.getSoeldner()[eingabe-1] != null ) {
                    SpielerCharakter[] soeldner = taverne.getSoeldner();
                    teammitgliedEinstellen(soeldner[eingabe-1]);
                    soeldner[eingabe-1] = null;
                    taverne.setSoeldner(soeldner);
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
                //TODO Ausgabe eines Charakters
                System.out.println((i+1) + ": " + partyController.getParty().getNebenCharakter()[i]);
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

    private void teammitgliedEinstellen(SpielerCharakter soeldner)    {
        if (partyController.getPartyGold() >= (int)Math.floor(partyController.getPartyLevel()*2)){
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
            partyController.teammitgliedHinzufuegen(soeldner);
            System.out.println("Soeldner angeheuert!");
        } else {
            System.out.println("Deine Armut kotzt mich an!");
        }
            taverneAnzeigen();
    }

    private void teammitgliedEntlassen(SpielerCharakter soeldner) {
        partyController.teammitgliedEntfernen(soeldner);
        System.out.println("Soeldner entlassen!");
        taverneAnzeigen();
    }
}
