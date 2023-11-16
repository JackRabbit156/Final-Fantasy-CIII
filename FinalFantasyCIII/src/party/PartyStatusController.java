package party;

import gegenstand.Ausruestungsgegenstand.Accesssoire;
import gegenstand.Ausruestungsgegenstand.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffe;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartyStatusController {
    Map<String, Integer> itemsMap = new HashMap<>();
    ArrayList<Waffe> waffenListe = new ArrayList<>();
    ArrayList<Ruestung> ruestungsListe = new ArrayList<>();
    ArrayList<Accesssoire> accesoirsListe = new ArrayList<>();
    private PartyController partyController;
    private int ausgewaehlteOption = 0;
    private String[] menuOption;
    private boolean menueaktive;

    public PartyStatusController(PartyController partyController) {

        this.partyController = partyController;

        //---------------------------------------------------------------------------------// TODO RAUSLÖSCHEN der TESTINFOS vor PUSH!!
        itemsMap.put("Heiltrank", 5);
        itemsMap.put("Manatrank", 10);
        itemsMap.put("Todestrank", 1);
        waffenListe.add(new Waffe("Stab der Weisen", 22));
        waffenListe.add(new Waffe("Katastrophen Schwert", 33));
        ruestungsListe.add(new Ruestung("Burstpanzer der Erhabenen", 44));
        ruestungsListe.add(new Ruestung("Kaleidoskop Hose der Seelenlosen", 88));
        accesoirsListe.add(new Accesssoire("Ring der Blutigen Eichel", 199));
        accesoirsListe.add(new Accesssoire("Zungenpiercing der Ananasesser", 1));
        //---------------------------------------------------------------------------------// TODO RAUSLÖSCHEN der TESTINFOS vor PUSH!!


        menuOption = new String[]{"Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};
    }

    private static void printItemList(Map<String, Integer> itemsMap) {
        System.out.println("Number | Gegenstand | Anzahl | Wert");
        System.out.println("----------------------------------");

        int itemNumber = 1;
        for (Map.Entry<String, Integer> entry : itemsMap.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            int itemValue = getItemValue(itemName); // TODO item wert abfragen

            // Output the formatted entry
            System.out.printf("%-6d | %-10s | %-6d | %-4d%n", itemNumber, itemName, itemCount, itemValue);
            itemNumber++;
        }
    }

    private static int getItemValue(String itemName) {
        // gibt ne zufallszahl zurück
        //TODO Muss noch dem Itemwert hier festlegen
        return (int) (Math.random() * 100);
    }

    private static String getItemNameByNumber(Map<String, Integer> itemsMap, int itemNumber) {
        int currentNumber = 1;
        for (Map.Entry<String, Integer> entry : itemsMap.entrySet()) {
            if (currentNumber == itemNumber) {
                return entry.getKey();
            }
            currentNumber++;
        }
        return null; // Invalid item number
    }

    /**
     * Navigiert durch die Menüoptionen basierend auf der Benutzereingabe.
     *
     * @param direction  Die Richtung der Navigation (-1 für nach oben, 1 für nach unten).
     * @param menuLength Die Länge des Menüs.
     *
     * @author HF Rode
     */
    private void navigateMenu(int direction, int menuLength) {
        ausgewaehlteOption = (ausgewaehlteOption + direction + menuLength) % menuLength;
    }

    /**
     * Führt die ausgewählte Menüoption basierend auf der Benutzereingabe aus.
     *
     * @author HF Rode
     */
    private void executeSelectedOption() {
        System.out.println(Farbauswahl.RED + "Starte: " + menuOption[ausgewaehlteOption]);
       // "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"
        switch (menuOption[ausgewaehlteOption]) {
            case "Ausgeruestet":

                break;
            case "Gelagerte Ausruestung":
                menuOption = new String[]{"Waffen", "Ruestung", "Accesoirs", "Zurueck"};
                ausgewaehlteOption = 0;
                break;
            case "Verbrauchsgegenstaende":
                this.verbrauchsgegenstaendeAnzeige();
                menuOption = new String[]{"Benutzen", "Zurueck"};
                ausgewaehlteOption = 0;
                break;
            case "Benutzen":
                this.verbrauchsgegenstaendeBenutzen();
                break;
            case "Upgrade Materialien":
                this.upgradematerialienAnzeige();
                break;
            case "Waffen":
           //     this.waffenAnzeigen();
                break;
            case "Ruestung":
            //    this.ruestungAnzeigen();
                break;
            case "Accesoirs":
              //  this.accesoirsAnzeigen();
                break;
            case "Zurueck":
                menuOption = new String[]{"Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien"};
                ausgewaehlteOption = 0;
                break;
            case "Zueruck zum Hub":
                menueaktive = false;
                break;


        }
    }

    public void spielerinventarAnzeige() {
        menueaktive = true;
        while (menueaktive) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
            // TODO Hir könnte ASCII ART SEIN
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
                    this.executeSelectedOption();
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Fehlerhafte Eingabe. Benutze 'w' zum Hochgehen, 's' um runter zu gehen  und 'e' um zu bestaetigen.");
                    break;
            }
        }
    }


    private void upgradematerialienAnzeige() {
        System.out.println("VERSTAERKEND!");
    }


    //-----Hier werden Verbauchsgegenstände bearbeitet und benutzt v--
    private void verbrauchsgegenstaendeAnzeige() {

        printItemList(itemsMap);
        //this.partyController.getParty().getVerbrauchsgegenstaende();
    }

    private void verbrauchsgegenstaendeBenutzen() {
        printItemList(itemsMap);
        System.out.println(Farbauswahl.YELLOW + "Welches Item wollen sie benutzen: " + Farbauswahl.RESET);
        int selectedItemNumber = ScannerHelfer.nextInt();
        String selectedItemName = getItemNameByNumber(itemsMap, selectedItemNumber);
        if (selectedItemName != null) {
            System.out.println("Sie haben: " + selectedItemName + " Benutzt");
            itemsMap.put(selectedItemName, itemsMap.get(selectedItemName) - 1);
            if (itemsMap.get(selectedItemName) <= 0) {
                itemsMap.remove(selectedItemName);
            }
        } else {
            System.out.println("Bitte Geben sie ein Item ein das Existiert");
        }
        //this.partyController.getParty().getVerbrauchsgegenstaende();
    }
    //-----Hier werden Verbauchsgegenstände bearbeitet und benutzt ^--

    public void partystatusmenuAnzeigen() {
        this.spielerinventarAnzeige();
    }
}
