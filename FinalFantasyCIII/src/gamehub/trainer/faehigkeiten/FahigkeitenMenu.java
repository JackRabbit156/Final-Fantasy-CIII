package gamehub.trainer.faehigkeiten;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;

import java.util.ArrayList;

public class FahigkeitenMenu {

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * {@object character}: Der Charakter, fuer den eine Aenderung der Faehigkeiten vorgenommen wird.
     * {@see Faehigkeit }: Hier liegen die Attribute der Faehigkeit. Auch kann eine Faehigkeit hier aufgewertet werden.
     * Oefnnet das Menue, um eine Faehigkeit fuer einen  gegebenen Charakter aufzuwerten
     */
    public static void menuFaehigkeitWaehlen(SpielerCharakter charakter) {
        ArrayList<Faehigkeit> faehigkeiten = charakter.getFaehigkeiten();
        System.out.println(Farbauswahl.YELLOW + "Faehigkeiten-Menue:" + Farbauswahl.RESET);
        String charakterName = charakter.getName();
        System.out.println(Farbauswahl.RED + charakterName + Farbauswahl.RESET + " hat diesen Faehigkeiten-Baum: ");
        FaehigkeitFabrik.faehigkeitenAusgeben(faehigkeiten);
        System.out.println();
        System.out.println("\nWelche Faehigkeit soll verbessert werden?: (Zahl eingeben - mit 0 abbrechen)");
        for (int i = 1; i <= faehigkeiten.size(); i++) {
            System.out.println(i + ". :" + faehigkeiten.get(i-1).getName());
        }
        int offeneFaehigkeitspunkte = charakter.getOffeneFaehigkeitspunkte();
        System.out.println("Es koennen " + offeneFaehigkeitspunkte + " Faehigkeitspunkte vergeben werden!\n" +
                "Eingabe: ");
        int nutzerAuswahl = ScannerHelfer.nextInt();
        if (1 <= nutzerAuswahl && nutzerAuswahl <= faehigkeiten.size()) {
            Faehigkeit gewaehlteFaehigkeit = faehigkeiten.get(nutzerAuswahl-1);
            if (offeneFaehigkeitspunkte > 0) {
                charakter.setOffeneFaehigkeitspunkte(offeneFaehigkeitspunkte - 1);
                CharakterController.faehigkeitLernen(charakter, gewaehlteFaehigkeit);
                System.out.println(charakterName + "hat nun die Faehigkeit " + gewaehlteFaehigkeit.getName() + " auf Level " + gewaehlteFaehigkeit.getLevel());
                while (true) { // SubMenu: Weitere Faehigkeiten waehlen!
                    System.out.println("Soll eine weitere Faehigkeit fuer " + charakterName + " erweitert werden? (1 = Ja | 0 = Nein)");
                    System.out.println("Eingabe: ");
                    nutzerAuswahl = ScannerHelfer.nextInt();
                    if (nutzerAuswahl == 1) {
                        menuFaehigkeitWaehlen(charakter);
                    } else if (nutzerAuswahl == 0) {
                        return; // Menu Faehigkeiten wird geschlossen
                    } else {
                        System.out.println("Ungueltige Eingabe!");
                    }
                }
            } else {
                System.out.println("Leider reichen die offenen Faehigkeitspunkte von " + charakterName + " nicht, um " +
                        " die Faehigkeit " + gewaehlteFaehigkeit.getName() + " auf Level" + (gewaehlteFaehigkeit.getLevel() + 1) + " zu" +
                        " erweitern."
                );
                menuFaehigkeitWaehlen(charakter);
            }
            //TODO:Kosten für Aufwertung verrechnen
            //TODO: Faehigkeit soll zurueckgeben was fuer wen aufgwertet wurde
        } else if (nutzerAuswahl != 0) { //Bei 0 wird das Menue geschlossen
            System.err.println("Nutzereingabe ungueltig! -  erneut waehlen!");
            menuFaehigkeitWaehlen(charakter);

        }
    }
}
