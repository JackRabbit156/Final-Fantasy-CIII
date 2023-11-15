package hauptmenu;

/**
 * @author Dennis Ridder
 * @param ohne
 * @return void
 * @since Version 0.1
 */

import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.io.IOException;
import java.util.Scanner;

public class HauptmenuController {
    public static void main(String[] args) throws IOException, InterruptedException {
        hauptmenuAnzeigen();
    }

//    private GameController gameController = new GameController; // gem. UML TODO
//    private SpeicherstandController speicherstandController = new SpeicherstandController; // gem. UML  TODO
//    private PartyController partycontroller = new PartyController; // gem. UML TODO
    GameHubController gameHubController = new GameHubController(); // gem. UML  TODO

    // Hauptmenue anzeigen
    public static void hauptmenuAnzeigen() throws IOException, InterruptedException {
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
        System.out.println(Farbauswahl.YELLOW + "Bitte auswählen:" + Farbauswahl.RESET);
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
    }

    // Neues Spiel
    private static void neuesSpiel() {
        gameHubController.hubAnzeigen(); // TODO
    }

    // Spiel Laden
    private static void spielLaden() {
        System.out.println("hier spielLaden-Kram einfuegen"); // TODO
    }

    // Optionen
    private static void optionen() {
        System.out.println("hier optionen-Kram einfuegen"); // TODO
    }

    // Credits
    public static void credits() throws IOException, InterruptedException {
        /**
         * @author Thomas Maass
         * @param ohne
         * @return void
         * @since 0.2
         */

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
                "                                      " + Farbauswahl.GREEN + "Geschrieben von Hörsaal 103 (08/23-12/23)" + Farbauswahl.RESET + "                " + Farbauswahl.RED + "Hörsaalleiter / Ausbilder" + Farbauswahl.RESET + "           \n" +
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
        KonsolenAssistent.clear();
        hauptmenuAnzeigen();
    }



}
