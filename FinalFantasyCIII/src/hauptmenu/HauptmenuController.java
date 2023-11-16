package hauptmenu;

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.neuesspiel.NeuesSpielMethoden;
import hauptmenu.speicherstand.SpeicherstandController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import hauptmenu.speicherstand.Speicherstand;
import java.io.IOException;

import party.Party;
import party.PartyController;

/**
 *  Der HauptmenuController ist verantwortlich für die Steuerung des Hauptmenues des Spiels.
 *  Hier koennen Benutzer zwischen verschiedenen Optionen wie einem neuen Spiel, dem Laden eines Spiels,
 *  den Optionen und den Credits waehlen.
 *
 * @author Dennis Ridder
 * @since Version 0.2
 *
 */

public class HauptmenuController {

    private GameController gameController;
    private SpeicherstandController speicherstandController;
    private PartyController partyController;
    GameHubController gameHubController;

    public HauptmenuController() {
        speicherstandController = new SpeicherstandController();
    }

    // Hauptmenue anzeigen
    public void hauptmenuAnzeigen() {
        try {
            // BANNER
            System.out.println(" _____                                                          _____ \n" +
                    "( ___ )                                                        ( ___ )\n" +
                    " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
                    " |   |  _   _                   _                        _   _  |   | \n" +
                    " |   | | | | | __ _ _   _ _ __ | |_ _ __ ___   ___ _ __ (_) (_) |   | \n" +
                    " |   | | |_| |/ _` | | | | '_ \\| __| '_ ` _ \\ / _ \\ '_ \\| | | | |   | \n" +
                    " |   | |  _  | (_| | |_| | |_) | |_| | | | | |  __/ | | | |_| | |   | \n" +
                    " |   | |_| |_|\\__,_|\\__,_| .__/ \\__|_| |_| |_|\\___|_| |_|\\__,_| |   | \n" +
                    " |   |                   |_|                                    |   | \n" +
                    " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
                    "(_____)                                                        (_____)\n");

            // Hauptmenue-Auswahlmoeglichkeiten
            System.out.println(Farbauswahl.YELLOW + "Bitte auswaehlen:" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "1 = Neues Spiel" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "2 = Spiel laden" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "3 = Optionen" + Farbauswahl.RESET);
            System.out.println(Farbauswahl.CYAN + "4 = Credits" + Farbauswahl.RESET);
            int eingabe;
            eingabe = ScannerHelfer.sc.nextInt();
            switch (eingabe) {
                case 1:
                    KonsolenAssistent.clear();
                    neuesSpiel();
                    break;
                case 2:
                    KonsolenAssistent.clear();
                    spielLaden();
                    break;
                case 3:
                    KonsolenAssistent.clear();
                    optionen();
                    break;
                case 4:
                    KonsolenAssistent.clear();
                    credits();
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                    hauptmenuAnzeigen();
                    break;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Neues Spiel

    /**
     * Erste Implementation von neuesSpiel.
     * Methoden ausgelagert on NeuesSpielMethoden.
     *
     * @since 16.11.2023
     * @author Lang
     */
    private void neuesSpiel() {

        try{
            // BANNER
            //TODO richtiges Banner einsetzen
            System.out.println(" _____                                                          _____ \n" +
                    "( ___ )                                                        ( ___ )\n" +
                    " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
                    " |   |  _   _                   _                        _   _  |   | \n" +
                    " |   | | | | | __ _ _   _ _ __ | |_ _ __ ___   ___ _ __ (_) (_) |   | \n" +
                    " |   | | |_| |/ _` | | | | '_ \\| __| '_ ` _ \\ / _ \\ '_ \\| | | | |   | \n" +
                    " |   | |  _  | (_| | |_| | |_) | |_| | | | | |  __/ | | | |_| | |   | \n" +
                    " |   | |_| |_|\\__,_|\\__,_| .__/ \\__|_| |_| |_|\\___|_| |_|\\__,_| |   | \n" +
                    " |   |                   |_|                                    |   | \n" +
                    " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
                    "(_____)                                                        (_____)\n");


            NeuesSpielMethoden ngm = new NeuesSpielMethoden();
            PartyController partyController = ngm.neueParty();
            String schwierigkeit = ngm.schwierigkeitAuswaehlen();
            boolean hardcoreModus = ngm.hardcoreModus();
            GameController gameController = new GameController(schwierigkeit, hardcoreModus, partyController);
            GameHubController gameHubController = new GameHubController(gameController, partyController);
            gameHubController.hubAnzeigen();



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Spiel Laden
    private static void spielLaden() {
//        System.out.println("hier spielLaden-Kram einfuegen"); // TODO
    }

    // Optionen
    /**
     * Gibt die Optionsansicht aus
     * @author 11750396
     * @since 15.11.2023
     */
    public void optionen(){
        System.out.println("++++++Optionen++++++");
        System.out.println("1: Spiel speichern");
        System.out.println("2: Speicherstand laden");
        System.out.println("3: Schwierigkeitsgrad ändern");
        System.out.println("4: Zurück zum Hauptmenü");
        int eingabe = ScannerHelfer.sc.nextInt();
        switch(eingabe){
            case 1:
                // speicherstandController.speichern(new Speicherstand(partyController.getParty(), gameController.getSchwierigkeitsgrad(), gameController.isHardcore())); // TODO
                break;
            case 2:
                Speicherstand auswahl = speicherstandController.speicherstandAuswahl();
                partyController = new PartyController(auswahl.getParty());
                gameController = new GameController(auswahl.getSchwierigkeitsgrad(), auswahl.isHardcore(), partyController);
                gameHubController= new GameHubController(gameController, partyController);
                break;
            case 3:
                if(gameController == null){
                    System.err.println("Erstellen Sie ein neues Spiel, oder laden Sie ein vorhandenes!");
                    hauptmenuAnzeigen();
                } else {
                gameController.schwierigkeitsAuswahl();
                }
                break;
            case 4:
                hauptmenuAnzeigen();
                break;
            default:
                System.err.println("Falsche Eingabe!");
                optionen();
                break;
        }
    }

    // Credits
    /**
     * @author Thomas Maass
     * @since 0.2
     */
    public void credits() {

        // Methode geschrieben von TangoMike
        StringBuilder sb = new StringBuilder();

        sb.append(" ________ ___  ________   ________  ___               ________ ________  ________   _________  ________  ________       ___    ___ \n" +
                "|\\  _____\\\\  \\|\\   ___  \\|\\   __  \\|\\  \\             |\\  _____\\\\   __  \\|\\   ___  \\|\\___   ___\\\\   __  \\|\\   ____\\     |\\  \\  /  /|\n" +
                "\\ \\  \\__/\\ \\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\            \\ \\  \\__/\\ \\  \\|\\  \\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___|_    \\ \\  \\/  / /\n" +
                " \\ \\   __\\\\ \\  \\ \\  \\\\ \\  \\ \\   __  \\ \\  \\            \\ \\   __\\\\ \\   __  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\   __  \\ \\_____  \\    \\ \\    / / \n" +
                "  \\ \\  \\_| \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\____        \\ \\  \\_| \\ \\  \\ \\  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\|____|\\  \\    \\/  /  /  \n" +
                "   \\ \\__\\   \\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\ \\_______\\       \\ \\__\\   \\ \\__\\ \\__\\ \\__\\\\ \\__\\   \\ \\__\\ \\ \\__\\ \\__\\____\\_\\  \\ __/  / /    \n" +
                "    \\|__|    \\|__|\\|__| \\|__|\\|__|\\|__|\\|_______|        \\|__|    \\|__|\\|__|\\|__| \\|__|    \\|__|  \\|__|\\|__|\\_________\\\\___/ /     \n" +
                "                                                                                                           \\|_________\\|___|/      \n" +
                "                                                                                                                                   \n" +
                "                                      " + Farbauswahl.GREEN + "Geschrieben von Hoersaal 103 (08/23-12/23)" + Farbauswahl.RESET + "                " + Farbauswahl.RED + "Hörsaalleiter / Ausbilder" + Farbauswahl.RESET + "           \n" +
                " ________  ___  ___  ___              " + Farbauswahl.BLUE + "OL,Oliver,Ebert" + Farbauswahl.RESET + "                                          " + Farbauswahl.YELLOW + "H, Wente, Dominik" + Farbauswahl.RESET + "                \n" +
                "|\\   ____\\|\\  \\|\\  \\|\\  \\             " + Farbauswahl.BLUE + "OL,Melvin,Schiffer-Schmidl" + Farbauswahl.RESET + "                               " + Farbauswahl.BLUE + "OFR, Rieger, Frank" + Farbauswahl.RESET + "                 \n" +
                "\\ \\  \\___|\\ \\  \\ \\  \\ \\  \\            " + Farbauswahl.GREEN + "SF,Thomas,Maass" + Farbauswahl.RESET + "                                                                              \n" +
                " \\ \\  \\    \\ \\  \\ \\  \\ \\  \\           " + Farbauswahl.PURPLE + "HF,Niels-Torben,Rode" + Farbauswahl.RESET + "                                                                         \n" +
                "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\          " + Farbauswahl.YELLOW + "OF,Dennis,Ridder" + Farbauswahl.RESET + "                                                                             \n" +
                "   \\ \\_______\\ \\__\\ \\__\\ \\__\\         " + Farbauswahl.BLUE + "OF,Tobias,Kretschmer" + Farbauswahl.RESET + "                                                                         \n" +
                "    \\|_______|\\|__|\\|__|\\|__|         " + Farbauswahl.YELLOW + "OF,Nick,Schroeder" + Farbauswahl.RESET + "                                                                            \n" +
                "                                      " + Farbauswahl.YELLOW + "OF,Christian,Stetter" + Farbauswahl.RESET + "                                                                         \n" +
                "                                      " + Farbauswahl.BLUE + "F,Markus,Lang" + Farbauswahl.RESET + "                                                                                \n" +
                "                                                                                                                                   \n"+
                "Mit beliebiger Taste zurueck zum Hauptmenue!");
        System.out.println(sb);
        String weiter = "";
        weiter = ScannerHelfer.sc.nextLine();
        weiter = ScannerHelfer.sc.nextLine(); // Scanner spinnt - doppelte Eingabe erwartet, sonst springt er direkt zurueck!
        try {
            KonsolenAssistent.clear();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        hauptmenuAnzeigen();
    }

}
