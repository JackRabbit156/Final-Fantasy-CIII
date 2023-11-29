package hauptmenu.neuesspiel;

import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;

import java.io.IOException;


/**
 * Ausgelagerte Methoden für das erstellen eines neuen Spiels.
 *
 * @since 16.11.2023
 * @author Lang
 */
public class NeuesSpielMethoden {


    /**
     * Erstellt eine neue Party mit Nutzereingaben.
     *
     * @return PartyController
     * @throws IOException
     * @throws InterruptedException
     *
     * @since 16.11.2023
     * @author Lang
     */
    public PartyController neueParty() throws IOException, InterruptedException {
        // Hauptmenue-Auswahlmoeglichkeiten
        System.out.println(Farbauswahl.YELLOW + "Bitte Name eingeben:" + Farbauswahl.RESET);
        String name = ScannerHelfer.nextLine();
        String klasse = null;
        boolean tryAgain = true;
        while (tryAgain) {
            System.out.println(Farbauswahl.YELLOW + "Bitte Klasse waehlen:" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "1 = Physischer DD" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "2 = Magischer DD" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "3 = Tank" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "4 = Healer" + Farbauswahl.RESET);
            klasse = "";
            int auswahl = ScannerHelfer.nextInt();
            switch (auswahl){
                case 1:
                    klasse = "Physischer DD";
                    tryAgain = false;
                    break;
                case 2:
                    klasse = "Magischer DD";
                    tryAgain = false;
                    break;
                case 3:
                    klasse = "Tank";
                    tryAgain = false;
                    break;
                case 4:
                    klasse = "Healer";
                    tryAgain = false;
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                    break;
            }
        }
        return new PartyController(new Party(name, klasse));
    }
}
