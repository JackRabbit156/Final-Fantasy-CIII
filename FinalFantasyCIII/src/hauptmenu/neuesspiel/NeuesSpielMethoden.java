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
        String name = ScannerHelfer.sc.nextLine();
        System.out.println(Farbauswahl.YELLOW + "Bitte Name eingeben:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1 = Physischer DD" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2 = Magischer DD" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3 = Tank" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "4 = Healer" + Farbauswahl.RESET);
        String klasse = "";
        int auswahl = Integer.parseInt(ScannerHelfer.sc.nextLine());
        switch (auswahl){
            case 1:
                klasse = "Physischer DD";
                break;
            case 2:
                klasse = "Magischer DD";
                break;
            case 3:
                klasse = "Tank";
                break;
            case 4:
                klasse = "Healer";
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                neueParty();
                break;
        }
        return new PartyController(new Party(name, klasse));
    }


    /**
     * Wählt die Schwierigkeit anhand von Nutzereingaben.
     *
     * @return String schwierigkeit
     * @throws IOException
     * @throws InterruptedException
     *
     * @since 16.11.2023
     * @author Lang
     */
    public String schwierigkeitAuswaehlen() throws IOException, InterruptedException {
        System.out.println(Farbauswahl.YELLOW + "Bitte Schwierigkeit auswählen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1 = Leicht" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2 = Mittel" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "3 = Schwer" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "4 = Sehr Schwer" + Farbauswahl.RESET);
        String schwierigkeit = "";

        int auswahl = Integer.parseInt(ScannerHelfer.sc.nextLine());
        switch (auswahl){
            case 1:
                schwierigkeit = "Leicht";
                break;
            case 2:
                schwierigkeit = "Mittel";
                break;
            case 3:
                schwierigkeit = "Schwer";
                break;
            case 4:
                schwierigkeit = "Sehr Schwer";
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                schwierigkeitAuswaehlen();
                break;
        }
        return schwierigkeit;
    }


    /**
     * Wählt ob der HardcoreModus gewünscht ist anhand von Nutzereingaben.
     *
     * @return boolean hardcoreModus
     * @throws IOException
     * @throws InterruptedException
     *
     * @since 16.11.2023
     * @author Lang
     */
    public boolean hardcoreModus() throws IOException, InterruptedException {
        System.out.println(Farbauswahl.YELLOW + "Bitte Schwierigkeit auswählen:" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "1 = Ja" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "2 = Nein" + Farbauswahl.RESET);
        boolean hardcore = false;
        int auswahl = Integer.parseInt(ScannerHelfer.sc.nextLine());
        if (auswahl == 1){
            hardcore = true;
        } else if (auswahl == 0){
            hardcore = false;
        } else {
            KonsolenAssistent.clear();
            System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
            hardcoreModus();
        }
        return hardcore;
    }
}
