package statistik;

import charakter.controller.CharakterController;
import charakter.model.Charakter;
import hauptmenu.HauptmenuController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

/**
 *
 * Dient zum Anzeigen des GameOverScreens mit Moeglichkeit zum Beenden des Spiels oder zurueckkehren zum Hauptmenue
 * @author OF Ridder
 * @since 16.11.2023
 *
 */

public class GameOver {

    public static void gameOverAnzeigen(Statistik statistik, PartyController partyController) {
        HauptmenuController hauptmenu = new HauptmenuController();

        System.out.println(Farbauswahl.RED + "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼" + Farbauswahl.RESET);
        System.out.println(Farbauswahl.CYAN + "Name: " + partyController.getParty().getHauptCharakter().getName() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.PURPLE + "Level: " + partyController.getParty().getHauptCharakter().getLevel() + Farbauswahl.RESET);
        System.out.println("Durchgefuehrte Kaempfe: " + statistik.getDurchgefuehrteKaempfe());
        System.out.println(Farbauswahl.GREEN + "Gewonnene Kaempfe: " + statistik.getGewonneneKaempfe() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.RED + "Verlorene Kaempfe: " + statistik.getVerloreneKaempfe() + Farbauswahl.RESET);
        System.out.println(Farbauswahl.YELLOW + "Gesamt erwirtschaftetes Gold: " + statistik.getGesamtErwirtschaftetesGold() + Farbauswahl.RESET);
        System.out.println("Mit '1' Spiel beenden");
        System.out.println("Mit anderer beliebiger Taste zurueck zum Hauptmenue");
        String eingabe = ScannerHelfer.nextLine();
        if ("1".equals(eingabe)) {
            ScannerHelfer.close();
            System.out.println(Farbauswahl.RED_BACKGROUND + "Viel Spaß bei der Auskleidung ;)" + Farbauswahl.RESET);
            System.exit(0);
        } else {
            KonsolenAssistent.clear();
            hauptmenu.hauptmenuAnzeigen();
        }
    }
}