package party;
/* TODO
    -Verbrauchsliste anpassen - Erledigt
    -Itemmaps generisieren - Erledigt jetzt in party
    -Itemmap <Material, Int> - Erledigt jetzt in party
    -Strings anpassen - Erledigt
    -Ausrüstungs menü
    -Söldner einlesen - Erledigt
    -kampfmenü und benutzungd es kampfmenüs
    -Waffen übergeben und abgeben an Schmiede / Händler usw.
 */

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.LeichteRuestung;
import gegenstand.Ausruestungsgegenstand.Ruestungen.MittlereRuestung;
import gegenstand.Ausruestungsgegenstand.Ruestungen.SchwereRuestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Einhandwaffe;
import gegenstand.Ausruestungsgegenstand.Waffen.Magierwaffe;
import gegenstand.Ausruestungsgegenstand.Waffen.Zweihandwaffe;
import gegenstand.Gegenstand;
import gegenstand.GegenstandController;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Manatränke.Manatrank;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class PartyStatusController {
    private PartyController partyController;
    private GegenstandController gegenstandController;
    private int ausgewaehlteOption = 0;
    private String[] menuOption;
    private boolean menueaktive;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public PartyStatusController(PartyController partyController) {
        // Keine TESTDATEN!!!!!!!!!!!!!!!!!!!!!!!!!____________________
        this.partyController = partyController;
        this.aktiveParty = fuellePartyList();
        this.ausgewaehlterChar = partyController.getParty().getHauptCharakter();
        menuOption = new String[]{"Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub", "Waffen Test"};
        // Keine TESTDATEN!!!!!!!!!!!!!!!!!!!!!!!!!____________________

        //---------------------------------------------------------------------------------// TODO RAUSLÖSCHEN der TESTINFOS vor Finalem PUSH!!
        SpielerCharakter Soeldner1 = new SpielerCharakter("Soeldner1", "Tank", "Eines tages blup");
        SpielerCharakter Soeldner2 = new SpielerCharakter("SoeldnerDPS", "Physischer DD", "ich bin ein Kaempfer");
        SpielerCharakter Soeldner3 = new SpielerCharakter("SoeldnerDPS", "Magischer DD", "ich bin ein Magier");
        aktiveParty.add(Soeldner1);
        aktiveParty.add(Soeldner2);
        aktiveParty.add(Soeldner3);
        Material Stein = new Material();
        Material Flasche = new Material();
        Material Schleimtotenkopf = new Material();
        Verbrauchsgegenstand kHeiltrank = new KleinerHeiltrank();
        Verbrauchsgegenstand gheiltrank = new GrosserHeiltrank();
        Verbrauchsgegenstand manatrank = new Manatrank();
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Magierwaffe(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Zweihandwaffe(4));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Magierwaffe(3));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Zweihandwaffe(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Einhandwaffe(22));

        this.partyController.getParty().getMaterialien().put(Stein, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(Stein, 0) + 5);
        this.partyController.getParty().getMaterialien().put(Flasche, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(Flasche, 0) + 5);
        this.partyController.getParty().getMaterialien().put(Schleimtotenkopf, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(Schleimtotenkopf, 0) + 5);

        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new LeichteRuestung(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new SchwereRuestung(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new MittlereRuestung(3));

        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(33));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(230));

        this.partyController.getParty().getVerbrauchsgegenstaende().put(gheiltrank, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(gheiltrank, 0) + 4);
        this.partyController.getParty().getVerbrauchsgegenstaende().put(manatrank, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(manatrank, 0) + 2);
        this.partyController.getParty().getVerbrauchsgegenstaende().put(kHeiltrank, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(kHeiltrank, 0) + 5);
        //---------------------------------------------------------------------------------// TODO RAUSLÖSCHEN der TESTINFOS vor Finalem PUSH!!


    }

    /**
     * Zeigt eine Liste von Ausrüstungsgegenständen in der Konsole an.
     *
     * @param <E>       Der Typ der Ausrüstungsgegenstände.
     * @param arrayList Die Liste von Ausrüstungsgegenständen.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static <E extends Ausruestungsgegenstand> void ausruestungsListeAnzeigen(ArrayList<E> arrayList) {
        int maxItemNameLength = pruefeMaxZeilenLaenge(arrayList, Gegenstand::getName);
        int maxTypLength = pruefeMaxZeilenLaenge(arrayList, Ausruestungsgegenstand -> Ausruestungsgegenstand.getClass().getSimpleName());
        int maxItemLevelLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getLevelAnforderung);
        int maxWertLength = pruefeMaxzeilenLaengeInt(arrayList, Gegenstand::getVerkaufswert);

        String ueberschriftWert = "Verkaufswert";
        maxWertLength = Math.max(maxWertLength, ueberschriftWert.length());
        String ueberschriftLevel = "Gegenstands Level";
        maxItemLevelLength = Math.max(maxItemLevelLength, ueberschriftLevel.length());
        System.out.println(String.format("| %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | %" + maxWertLength + "s |", "Item Name", "Typ", ueberschriftLevel, ueberschriftWert));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxTypLength + maxItemLevelLength + maxWertLength + 14, "-")));

        for (E waffe : arrayList) {
            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " |%n",
                    waffe.getName(), waffe.getClass().getSimpleName(), waffe.getLevelAnforderung(), waffe.getVerkaufswert());
        }
    }

    /**
     * Ermittelt die maximale Länge einer Spalte in einer Liste von Ausrüstungsgegenständen,
     * basierend auf den extrahierten Werten durch die angegebene Funktion.
     *
     * @param <E>             Der Typ der Ausrüstungsgegenstände.
     * @param arrayList       Die Liste von Ausrüstungsgegenständen.
     * @param columnExtractor Die Funktion zur Extraktion des Werts aus einem Ausrüstungsgegenstand.
     *
     * @return Die maximale Länge der Spalte.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static <E extends Ausruestungsgegenstand> int pruefeMaxZeilenLaenge(ArrayList<E> arrayList, Function<Ausruestungsgegenstand, String> columnExtractor) {
        return arrayList.stream()
                .map(columnExtractor)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    /**
     * Ermittelt die maximale Länge einer Spalte in einer Liste von Ausrüstungsgegenständen,
     * basierend auf den extrahierten Integer-Werten durch die angegebene Funktion.
     *
     * @param <E>             Der Typ der Ausrüstungsgegenstände.
     * @param arrayList       Die Liste von Ausrüstungsgegenständen.
     * @param columnExtractor Die Funktion zur Extraktion des Integer-Werts aus einem Ausrüstungsgegenstand.
     *
     * @return Die maximale Länge der Spalte.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static <E extends Ausruestungsgegenstand> int pruefeMaxzeilenLaengeInt(ArrayList<E> arrayList, ToIntFunction<Ausruestungsgegenstand> columnExtractor) {
        return arrayList.stream()
                .mapToInt(columnExtractor)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    /**
     * Erkennt das ausgewählte Verbrauchsgegenstand-Objekt anhand der übergebenen Nummer in der Map.
     *
     * @param map            Die Map von Verbrauchsgegenständen mit zugehörigen Anzahlen.
     * @param selectedNumber Die ausgewählte Nummer des Verbrauchsgegenstands.
     *
     * @return Das Verbrauchsgegenstand-Objekt, das der ausgewählten Nummer entspricht,
     * oder null, wenn keine Übereinstimmung gefunden wurde.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static Verbrauchsgegenstand erkenneAusgewaehltesItem(Map<Verbrauchsgegenstand, Integer> map, int selectedNumber) {
        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : map.entrySet()) {
            if (nummer == selectedNumber) {
                return entry.getKey();
            }
            nummer++;
        }
        return null;
    }

    /**
     * Überprüft die maximale Länge einer Spalte in einer Menge von Elementen,
     * basierend auf den extrahierten Werten durch die angegebene Funktion.
     *
     * @param <T>             Der Typ der Elemente in der Menge.
     * @param set             Die Menge von Elementen.
     * @param columnExtractor Die Funktion zur Extraktion des Werts aus einem Element.
     *
     * @return Die maximale Länge der Spalte.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static <T> int pruefeMaxZeilenLaengeHash(Set<T> set, Function<T, ?> columnExtractor) {
        return set.stream()
                .map(columnExtractor)
                .filter(Objects::nonNull)  // Filter NULL values raus um einen fehler zu vermeiden
                .map(Object::toString)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    /**
     * Überprüft die maximale Länge einer Spalte in einer Map von Verbrauchsgegenständen und zugehörigen Integer-Werten,
     * basierend auf den extrahierten Integer-Werten durch die angegebene Funktion.
     *
     * @param map             Die Map von Verbrauchsgegenständen und zugehörigen Integer-Werten.
     * @param columnExtractor Die Funktion zur Extraktion des Integer-Werts aus einem Verbrauchsgegenstand.
     *
     * @return Die maximale Länge der Spalte.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private static int pruefeMaxZeilenLaengefuerIntHash(Map<Verbrauchsgegenstand, Integer> map, ToIntFunction<Integer> columnExtractor) {
        return map.values()
                .stream()
                .mapToInt(columnExtractor)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    /**
     * Liest eine Benutzereingabe für eine Menüoption ein und überprüft, ob die eingegebene Nummer
     * innerhalb des gültigen Bereichs (minValue bis maxValue) liegt. Falls nicht, wird der Benutzer erneut aufgefordert,
     * eine gültige Nummer einzugeben, bis eine korrekte Eingabe erfolgt.
     *
     * @param minValue Der minimale gültige Wert der Benutzereingabe.
     * @param maxValue Der maximale gültige Wert der Benutzereingabe.
     *
     * @return Die vom Benutzer eingegebene Nummer innerhalb des gültigen Bereichs.
     *
     * @author HF Rode
     * @see ScannerHelfer
     * @since 18.11.2023
     */
    private static int nutzerEingabePruefung(int minValue, int maxValue) {
        int nutzereingabe = -1;
        while (nutzereingabe < minValue || nutzereingabe > maxValue) {
            System.out.print("Bitte Nummer des gewünschten Charakters eingeben: ");
            try {
                nutzereingabe = ScannerHelfer.nextInt();
                if (nutzereingabe < minValue || nutzereingabe > maxValue) {
                    System.out.println("Ungültige Eingabe. Bitte eine Nummer zwischen " + minValue + " und " + maxValue + " wählen.");
                }
            } catch (Exception e) {
                System.out.println("Ungültige Eingabe. Bitte eine Nummer eingeben.");
            }
        }
        return nutzereingabe;
    }

    /**
     * Füllt eine Liste von SpielerCharakter-Objekten mit den Charakteren der Party.
     *
     * @return Eine ArrayList von SpielerCharakter-Objekten, die den Hauptcharakter und die Nebencharaktere der Party enthält.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    private ArrayList<SpielerCharakter> fuellePartyList() {
        ArrayList<SpielerCharakter> auffang = new ArrayList<>();

        auffang.add(partyController.getParty().getHauptCharakter());


        SpielerCharakter[] nebencharArray = this.partyController.getParty().getNebenCharakter();
        for (SpielerCharakter nebencharakter : nebencharArray) {
            if (nebencharakter != null) {
                auffang.add(nebencharakter);
            }
        }
//        List<SpielerCharakter> nebenchars = Arrays.asList(nebencharArray);
//        auffang.addAll(nebenchars);
        return auffang;
    }

    /**
     * Zeigt eine Liste der Verbrauchsgegenstände mit ihren jeweiligen Anzahlen und Verkaufswerten an.
     * Die Liste wird formatiert in der Konsole ausgegeben.
     *
     * @author HF Rode
     * @since 18.11.2023
     */
    public void verbrauchsListeAnzeigen() {
        Map<Verbrauchsgegenstand, Integer> map = this.partyController.getParty().getVerbrauchsgegenstaende();
        int maxItemNameLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getName)) + 1;
        int maxAnzahlLength = (pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue)) + 1;
        int maxWertLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getVerkaufswert)) + 1;

        String ueberschriftWert = "Wert";
        maxWertLength = Math.max(maxWertLength, ueberschriftWert.length());

        System.out.println(String.format("| %-" + maxItemNameLength + "s | %" + maxAnzahlLength + "s | %" + maxWertLength + "s |", "Gegenstand", "Anzahl", ueberschriftWert));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxAnzahlLength + maxWertLength + 9, "-")));

        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : map.entrySet()) {
            Verbrauchsgegenstand gegenstand = entry.getKey();
            int anzahl = entry.getValue();

            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLength + "d | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " |%n",
                    gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
        }
    }

    public boolean kampfInventarAnzeigen(ArrayList<SpielerCharakter> friendlist) {
        boolean benutzt;
        Map<Verbrauchsgegenstand, Integer> map = this.partyController.getParty().getVerbrauchsgegenstaende();
        int maxItemNameLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getName);
        int maxAnzahlLaenge = pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue);
        int maxWertLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getVerkaufswert);

        String ueberschriftWert = "Wert";
        maxWertLaenge = Math.max(maxWertLaenge, ueberschriftWert.length());

        System.out.printf("| %-7s | %-" + maxItemNameLaenge + "s | %-" + maxAnzahlLaenge + "s | %" + maxWertLaenge + "s |%n", "Nummer", "Gegenstand", "Anzahl", ueberschriftWert);
        System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLaenge + maxAnzahlLaenge + maxWertLaenge + 14, "-")));

        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> eintrag : map.entrySet()) {
            Verbrauchsgegenstand gegenstand = eintrag.getKey();
            int anzahl = eintrag.getValue();

            System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLaenge + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLaenge + "d | " + Farbauswahl.YELLOW + "%" + maxWertLaenge + "d" + Farbauswahl.RESET + " |%n",
                    nummer++, gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
        }

        // Nutzereingabe hier abrufen
        System.out.print("Welches Item möchtest du benutzen? ");
        System.out.println("Geben sie nichts ein oder eine Zahl auserhalb der Auswahl um abbzubrechen");
        int ausgewaehlteNummer = ScannerHelfer.nextInt();

        Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesItem(map, ausgewaehlteNummer);
        if (ausgewaehltergegenstand != null) {
            System.out.println("Ausgewähltes Item: " + ausgewaehltergegenstand.getName());
            System.out.println("Auf Welchen Char soll dieses Item angewendet werden? ");
            SpielerCharakter ausgewaehlterChar;
            ausgewaehlterChar = charAuswahlMenue(friendlist);
            //TODO Markus seine Funktion aufrufen.
            System.out.println(ausgewaehlterChar.getName() + "  <-- Ich wurde gewählt mit folgendem Item -->" + ausgewaehltergegenstand.getName());
            this.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(this.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));
            benutzt = true;

        } else {
            benutzt = false;
        }

        return benutzt;
    }

    /**
     * Zeigt die Liste der Verbrauchsgegenstände der Party an und ermöglicht dem Benutzer,
     * ein bestimmtes Item auszuwählen und auf einen ausgewählten Charakter anzuwenden.
     * <p>
     * Nach der Anzeige der Liste wird der Benutzer zur Eingabe aufgefordert,
     * um das gewünschte Item und den Zielcharakter auszuwählen.
     * Anschließend wird das ausgewählte Item auf den ausgewählten Charakter angewendet.
     *
     * @author HF Rode
     * @see Verbrauchsgegenstand
     * @see SpielerCharakter
     * @since 18.11.2023
     */
    public void verbrauchListeBenutzen() {
        Map<Verbrauchsgegenstand, Integer> map = this.partyController.getParty().getVerbrauchsgegenstaende();
        int maxItemNameLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getName);
        int maxAnzahlLaenge = pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue);
        int maxWertLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getVerkaufswert);

        String ueberschriftWert = "Wert";
        maxWertLaenge = Math.max(maxWertLaenge, ueberschriftWert.length());

        System.out.println(String.format("| %-7s | %-" + maxItemNameLaenge + "s | %-" + maxAnzahlLaenge + "s | %" + maxWertLaenge + "s |", "Nummer", "Gegenstand", "Anzahl", ueberschriftWert));
        System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLaenge + maxAnzahlLaenge + maxWertLaenge + 14, "-")));

        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> eintrag : map.entrySet()) {
            Verbrauchsgegenstand gegenstand = eintrag.getKey();
            int anzahl = eintrag.getValue();

            System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLaenge + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLaenge + "d | " + Farbauswahl.YELLOW + "%" + maxWertLaenge + "d" + Farbauswahl.RESET + " |%n",
                    nummer++, gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
        }

        // Nutzereingabe hier abrufen
        System.out.print("Welches Item möchtest du benutzen? ");
        int ausgewaehlteNummer = ScannerHelfer.nextInt();

        Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesItem(map, ausgewaehlteNummer);
        if (ausgewaehltergegenstand != null) {
            System.out.println("Ausgewähltes Item: " + ausgewaehltergegenstand.getName());
            System.out.println("Auf Welchen Char soll dieses Item angewendet werden? ");
            SpielerCharakter ausgewaehlterChar;
            ausgewaehlterChar = charAuswahlMenue(this.aktiveParty);
            System.out.println(ausgewaehlterChar.getName() + "  <-- Ich wurde gewählt mit folgendem Item -->" + ausgewaehltergegenstand.getName());
            ausgewaehlteNummer = ScannerHelfer.nextInt();
            this.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(this.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));

        } else {
            System.out.println("Bitte wähle ein Item das benutzbar ist");
        }
    }

    /**
     * Zeigt ein Menü zur Auswahl eines Charakters aus der aktiven Party an.
     * Der Benutzer kann einen Charakter anhand seiner Nummer auswählen.
     *
     * @param aktiveParty Die Liste der aktiven Spielercharaktere.
     *
     * @return Der ausgewählte Spielercharakter oder null, wenn die Party leer ist.
     *
     * @see SpielerCharakter
     * @since 18.11.2023
     */
    private SpielerCharakter charAuswahlMenue(ArrayList<SpielerCharakter> aktiveParty) {
        if (aktiveParty.isEmpty()) {
            System.out.println("Die Party ist leer. Was Eigentlich unmöglich ist aber hey, Easter EGG I guess <3");

            return null;
        }

        System.out.println("| Nummer | Char name | Lebenspunkte | Mana Punkte | Klasse |");
        System.out.println("----------------------------------------------------------");

        int nummer = 1;
        for (SpielerCharakter charakter : aktiveParty) {
            System.out.printf("| %-6d| %-10s| %-13s| %-12s| %-7s|%n",
                    nummer++,
                    charakter.getName(),
                    charakter.getGesundheitsPunkte() + "/" + charakter.getMaxGesundheitsPunkte(),
                    charakter.getManaPunkte() + "/" + charakter.getMaxManaPunkte(),
                    charakter.getKlasse().getBezeichnung());
        }

        int ausgewaehlterCharIndex = nutzerEingabePruefung(1, aktiveParty.size()) - 1;
        return aktiveParty.get(ausgewaehlterCharIndex);
    }

    //------------------------------------------------------------------------------------------------  Hier beginnt das Nutzer Menü

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
                ausgewaehlterChar = this.charAuswahlMenue(this.aktiveParty);
                this.ausgewaehlterCharAusruestungAnzeigen();
                System.out.println(ausgewaehlterChar.getName() + " <--- Hier sollte.. eine Übersicht meiner getragenen Items Sein haha");
                menuOption = new String[]{"Waffe Tauschen", "Ruestung Tauschen", "Accessoire Tauschen", "Zurueck"};
                break;
            case "Waffe Tausche":
                this.waffeAuswahl(ausgewaehlterChar);
                break;
            case "Ruestung Tauschen":
                this.ruestungAuswahl(ausgewaehlterChar);
                break;
            case "Accessoire Tauschen":
                this.accessoireAuswahl(ausgewaehlterChar);
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

    //------------------------------------------------------------------------------------------------  Hier Ended das Nutzer Menü
    private void accessoireAuswahl(SpielerCharakter ausgewaehlterChar) {
        //---TODO ruestungAuswahl menü bearbeiten und anpassen
    }

    private void waffeAuswahl(SpielerCharakter ausgewaehlterChar) {
        //---TODO ruestungAuswahl menü bearbeiten und anpassen
    }

    private void ruestungAuswahl(SpielerCharakter ausgewaehlterChar) {
        //---TODO ruestungAuswahl menü bearbeiten und anpassen
    }

    private void ausgewaehlterCharAusruestungAnzeigen() {
        //---TODO Ausrüestungs menü bearbeiten und anpassen
    }

    private void verbrauchsgegenstaendeBenutzen() {
        this.verbrauchListeBenutzen();
    }

    private void accesssoireAnzeigen() {
        ausruestungsListeAnzeigen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
    }

    private void ruestungAnzeigen() {
        ausruestungsListeAnzeigen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
    }

    private void waffenAnzeigen() {
        ausruestungsListeAnzeigen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
    }

    /**
     * Zeigt das Spielerinventar-Menü an und ermöglicht es dem Benutzer, zwischen verschiedenen Optionen zu navigieren.
     * Der Benutzer kann durch Drücken von 'w' nach oben navigieren, durch Drücken von 's' nach unten navigieren
     * und durch Drücken von 'e' die ausgewählte Option ausführen.
     * <p>
     * Das Menü wird in einer Schleife angezeigt, bis der Benutzer eine Option ausführt oder das Menü verlässt.
     *
     * @see Farbauswahl
     * @see KonsolenAssistent
     * @see ScannerHelfer
     * @since 18.11.2023
     */
    public void spielerinventarAnzeige() {
        menueaktive = true;
        while (menueaktive) {
            System.out.println(Farbauswahl.RED + "Waehle eine Option:" + Farbauswahl.RESET);
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
        this.upgradematerialienAnzeigen();
    }

    private void upgradematerialienAnzeigen() {
        this.upgradeListeAnzeigen();
    }

    private void upgradeListeAnzeigen() {
       //TODO Material anpassen
//        Map<Material, Integer> map = this.partyController.getParty().getMaterialien();
//        int maxItemNameLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Material::getName)) + 1;
//        int maxAnzahlLength = (pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue)) + 1;
//        int maxWertLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Material::getVerkaufswert)) + 1;
//
//        String ueberschriftWert = "Wert";
//        maxWertLength = Math.max(maxWertLength, ueberschriftWert.length());
//
//        System.out.println(String.format("| %-" + maxItemNameLength + "s | %" + maxAnzahlLength + "s | %" + maxWertLength + "s |", "Gegenstand", "Anzahl", ueberschriftWert));
//        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxAnzahlLength + maxWertLength + 9, "-")));
//
//        for (Map.Entry<Material, Integer> entry : map.entrySet()) {
//            Material gegenstand = entry.getKey();
//            int anzahl = entry.getValue();
//
//            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLength + "d | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " |%n",
//                    gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
//        }

    }


    //-----Hier werden Verbauchsgegenstände bearbeitet und benutzt v--
    private void verbrauchsgegenstaendeAnzeige() {
        this.verbrauchsListeAnzeigen();
    }
}
