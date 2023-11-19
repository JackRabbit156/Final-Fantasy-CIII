package party;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.LeichteRuestung;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Ruestungen.SchwereRuestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Einhandwaffe;
import gegenstand.Ausruestungsgegenstand.Waffen.Magierwaffe;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Ausruestungsgegenstand.Waffen.Zweihandwaffe;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class PartyStatusController {
    Map<String, Integer> itemsMap = new HashMap<>();
    ArrayList<Waffe> waffenListe = new ArrayList<>();
    ArrayList<Ruestung> ruestungsListe = new ArrayList<>();
    ArrayList<Accessoire> accesoirsListe = new ArrayList<>();
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
        waffenListe.add(new Einhandwaffe(1));
        waffenListe.add(new Magierwaffe(1));
        waffenListe.add(new Zweihandwaffe(1));
        ruestungsListe.add(new LeichteRuestung(1));
        ruestungsListe.add(new SchwereRuestung(1));
        accesoirsListe.add(new Accessoire(1));
        accesoirsListe.add(new Accessoire(2));
        //---------------------------------------------------------------------------------// TODO RAUSLÖSCHEN der TESTINFOS vor PUSH!!


        menuOption = new String[]{"Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};
    }

    private static void druckeAusruestungWaffeListe(ArrayList<Waffe> arrayList) {
        int maxItemNameLength = getMaxColumnLength(arrayList, Waffe::getName);
        int maxTypLength = getMaxColumnLength(arrayList, waffe -> waffe.getClass().getSimpleName());
        int maxItemLevelLength = getMaxColumnLengthForInt(arrayList, Waffe::getLevelAnforderung);
        int maxWertLength = getMaxColumnLength(arrayList, waffe -> String.valueOf(waffe.getVerkaufswert()));

        System.out.println(String.format("| %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | %-" + maxWertLength + "s |", "Item Name", "Typ", "ILvl", "Wert"));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxTypLength + maxItemLevelLength + (maxWertLength + 3) + 14, "-")));

        for (Waffe waffe : arrayList) {
            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%-" + (maxWertLength + 1) + "d" + Farbauswahl.RESET + " |%n",
                    waffe.getName(), waffe.getClass().getSimpleName(), waffe.getLevelAnforderung(), waffe.getVerkaufswert());
        }
    }

    private static int getMaxColumnLength(ArrayList<Waffe> arrayList, Function<Waffe, String> columnExtractor) {
        return arrayList.stream()
                .map(columnExtractor)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static int getMaxColumnLengthForInt(ArrayList<Waffe> arrayList, ToIntFunction<Waffe> columnExtractor) {
        return arrayList.stream()
                .mapToInt(columnExtractor)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }


    private static void druckeAusruestungRuestungListe(ArrayList<Ruestung> arrayList) {
        int maxItemNameLength = getMaxColumnRuestungLength(arrayList, Ruestung::getName);
        int maxTypLength = getMaxColumnRuestungLength(arrayList, ruestung -> ruestung.getClass().getSimpleName());
        int maxItemLevelLength = getMaxColumnRuestungLengthForInt(arrayList, Ruestung::getLevelAnforderung);
        int maxWertLength = getMaxColumnRuestungLengthForInt(arrayList, ruestung -> ruestung.getVerkaufswert());

        System.out.println(String.format("| %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | %-" + maxWertLength + "s |", "Item Name", "Typ", "ILvl", "Wert"));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxTypLength + maxItemLevelLength + (maxWertLength + 3) + 14, "-")));

        for (Ruestung ruestung : arrayList) {
            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%-" + (maxWertLength + 1) + "d" + Farbauswahl.RESET + " |%n",
                    ruestung.getName(), ruestung.getClass().getSimpleName(), ruestung.getLevelAnforderung(), ruestung.getVerkaufswert());
        }
    }

    private static int getMaxColumnRuestungLength(ArrayList<Ruestung> arrayList, Function<Ruestung, String> columnExtractor) {
        return arrayList.stream()
                .map(columnExtractor)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static int getMaxColumnRuestungLengthForInt(ArrayList<Ruestung> arrayList, ToIntFunction<Ruestung> columnExtractor) {
        return arrayList.stream()
                .mapToInt(columnExtractor)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static void druckeAusruestungAccesssoireListe(ArrayList<Accessoire> arrayList) {
        int maxItemNameLength = getMaxColumnAccesssoireLength(arrayList, Accessoire::getName);
        int maxTypLength = getMaxColumnAccesssoireLength(arrayList, accessoire -> accessoire.getClass().getSimpleName());
        int maxItemLevelLength = getMaxColumnAccesssoireLengthForInt(arrayList, Accessoire::getLevelAnforderung);
        int maxWertLength = getMaxColumnAccesssoireLengthForInt(arrayList, accessoire -> accessoire.getVerkaufswert());

        System.out.println(String.format("| %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | %-" + maxWertLength + "s |", "Item Name", "Typ", "ILvl", "Wert"));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxTypLength + maxItemLevelLength + (maxWertLength + 3) + 14, "-")));

        for (Accessoire accessoire : arrayList) {
            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %-" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%-" + (maxWertLength + 1) + "d" + Farbauswahl.RESET + " |%n",
                    accessoire.getName(), accessoire.getClass().getSimpleName(), accessoire.getLevelAnforderung(), accessoire.getVerkaufswert());
        }
    }
    private static int getMaxColumnAccesssoireLength(ArrayList<Accessoire> arrayList, Function<Accessoire, String> columnExtractor) {
        return arrayList.stream()
                .map(columnExtractor)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static int getMaxColumnAccesssoireLengthForInt(ArrayList<Accessoire> arrayList, ToIntFunction<Accessoire> columnExtractor) {
        return arrayList.stream()
                .mapToInt(columnExtractor)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }



    private static void printItemListMap(Map<String, Integer> itemsMap) {
        System.out.println(Farbauswahl.RED + "Number | Gegenstand | Anzahl | Wert" + Farbauswahl.RESET);
        System.out.println("----------------------------------");

        int itemNumber = 1;
        for (Map.Entry<String, Integer> entry : itemsMap.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            int itemValue = getItemValue(itemName); // TODO item wert abfragen


            System.out.printf(Farbauswahl.RED + "%-6d | %-10s | %-6d | %-4d%n" + Farbauswahl.RESET, itemNumber, itemName, itemCount, itemValue);
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
        System.out.println(Farbauswahl.RED + "Starte: " + menuOption[ausgewaehlteOption] + Farbauswahl.RESET);
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
                this.waffenAnzeigen();
                break;
            case "Ruestung":
                this.ruestungAnzeigen();
                break;
            case "Accesoirs":
                this.accesssoireAnzeigen();
                break;
            case "Zurueck":
                menuOption = new String[]{"Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};
                ausgewaehlteOption = 0;
                break;
            case "Zueruck zum Hub":
                menueaktive = false;

                break;


        }
    }

    private void accesssoireAnzeigen() {
        druckeAusruestungAccesssoireListe(accesoirsListe);
    }

    private void ruestungAnzeigen() {
        druckeAusruestungRuestungListe(ruestungsListe);
    }

    private void waffenAnzeigen() {
        druckeAusruestungWaffeListe(waffenListe);
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

        printItemListMap(itemsMap);
        //this.partyController.getParty().getVerbrauchsgegenstaende();
    }

    private void verbrauchsgegenstaendeBenutzen() {
        printItemListMap(itemsMap);
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
        this.verbrauchsgegenstaendeAnzeige();
        //this.partyController.getParty().getVerbrauchsgegenstaende();
    }
    //-----Hier werden Verbauchsgegenstände bearbeitet und benutzt ^--

    public void partystatusmenuAnzeigen() {
        this.spielerinventarAnzeige();
    }
}
