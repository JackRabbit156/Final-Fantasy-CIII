package gamehub.haendler;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import gegenstand.Gegenstand;
import party.PartyController;

import java.util.Scanner;

public class HaendlerController {

    Haendler haendler;


    public void haendlerAnzeigen(PartyController partyController) {
        menueAnzeigen();
//        Scanner aus Hilfsklasse übernehmen
        Scanner scanner = new Scanner(System.in);
        int eingabe;
        boolean eingabeKorrekt = false;

        eingabe = scanner.nextInt();
        while(!eingabeKorrekt){
    if (eingabe >= 1 && eingabe <= 4){
        eingabeKorrekt= true;
        switch (eingabe) {
            case 1:
                // Öffnen kaufmenü
                break;
            case 2:
                // Öffnen Spielerinventar mit verkaufsmöglichkeit
                break;
            case 3:
                // Öffnet die Verkaufshistory
                break;
            case 4:
                // Zurück zum Menü
                break;
        }}else {
        System.out.println("Eingabe war Fehlerhaft");
    }}

    }


    private void gegenstandVerkaufen(Gegenstand gegenstand) {


    }

    /**
     * @author HF Rode
     * @param gegenstand
     */
    private void gegenstandKaufen(Gegenstand gegenstand) {


    }

    /**
     * @author OF Kretschmer
     *
     * Zeigt das Menü des Händlers an
     */
    private void menueAnzeigen() {
        System.out.println("1. Kaufen");
        System.out.println("2. Verkaufen");
        System.out.println("3. Zurückkaufen");
        System.out.println("4. Zurück zum Menü");
    }


    /**
     * @author OF Kretschmer
     *
     * Zeigt die Grafik im Menü des Händlers an
     */
    private void bildAnzeigen() {
//        Grafik einbinden
    }

}
