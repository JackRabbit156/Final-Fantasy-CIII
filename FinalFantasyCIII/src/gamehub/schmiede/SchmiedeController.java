package gamehub.schmiede;

import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

public class SchmiedeController {

    methoden zum upgraden


    public void hubAnzeigen() {
        String[] menuOption = {"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status", "Kaempfen Gehen", "DEVELOP: Spiel beenden"};


        while (true) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
            // TODO Hier kommt die MAP hin für die ansicht später
            for (int i = 0; i < menuOption.length; i++) {
                if (i == ausgewaehlteOption) {
                    System.out.println(Farbauswahl.YELLOW + ">> " + menuOption[i] + Farbauswahl.RESET);
                } else {
                    System.out.println(Farbauswahl.GREEN + "   " + menuOption[i] + Farbauswahl.RESET);
                }
            }

            char userInput = ScannerHelfer.nextChar();

            switch (userInput) {
                case 'w':
                    KonsolenAssistent.clear();
                    this.navigateMenu(-1, menuOption.length);
                    break;
                case 's':
                    KonsolenAssistent.clear();
                    this.navigateMenu(1, menuOption.length);
                    break;
                case 'e':
                    KonsolenAssistent.clear();
                    this.executeSelectedOption(ausgewaehlteOption, menuOption);
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Invalid input. Use 'w' to move up, 's' to move down, and e to select.");
                    break;
            }
        }

}
