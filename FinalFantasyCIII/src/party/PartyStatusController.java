package party;
/* TODO
    -Verbrauchsliste anpassen - Erledigt
    -Itemmaps generisieren - Erledigt jetzt in party
    -Itemmap <Material, Int> - Erledigt jetzt in party
    -Strings anpassen - Erledigt
    -Ausrüstungs menü
    -Söldner einlesen - Erledigt
    -kampfmenü und benutzungd es kampfmenüs - Erledigt
    -Waffen übergeben und abgeben an Schmiede / Händler usw. - Erledigt
 */

import charakter.controller.CharakterController;
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
import gegenstand.verbrauchsgegenstand.Manatränke.KleinerManatrank;
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
    private final PartyController partyController;
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
//        Material Stein = new Material();
//        Material Flasche = new Material();
//        Material Schleimtotenkopf = new Material();
        Verbrauchsgegenstand kHeiltrank = new KleinerHeiltrank();
        Verbrauchsgegenstand gheiltrank = new GrosserHeiltrank();
        Verbrauchsgegenstand kmanatrank = new KleinerManatrank();
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Magierwaffe(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Zweihandwaffe(4));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Magierwaffe(3));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Zweihandwaffe(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().add(new Einhandwaffe(22));

        Material[] material = GegenstandController.rueckgabeAllerMaterialien();
        for (int i = 0; i < material.length; i++) {
            this.partyController.getParty().getMaterialien().put(material[i], i + 1);
        }

        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new LeichteRuestung(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new SchwereRuestung(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().add(new MittlereRuestung(3));

        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(1));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(33));
        this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore().add(new Accessoire(230));

        this.partyController.getParty().getVerbrauchsgegenstaende().put(gheiltrank, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(gheiltrank, 0) + 4);
        this.partyController.getParty().getVerbrauchsgegenstaende().put(kmanatrank, this.partyController.getParty().getVerbrauchsgegenstaende().getOrDefault(kmanatrank, 0) + 2);
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
        KonsolenAssistent.clear();
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

    private static <E extends Ausruestungsgegenstand> Ausruestungsgegenstand ausruestungsListeAnlegen(ArrayList<E> arrayList) {
        KonsolenAssistent.clear();
        Ausruestungsgegenstand ausgewaehltesAusruestungsgegenstand = null;
        int maxItemNameLength = pruefeMaxZeilenLaenge(arrayList, Gegenstand::getName);
        int maxTypLength = pruefeMaxZeilenLaenge(arrayList, Ausruestungsgegenstand -> Ausruestungsgegenstand.getClass().getSimpleName());
        int maxItemLevelLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getLevelAnforderung);
        int maxWertLength = pruefeMaxzeilenLaengeInt(arrayList, Gegenstand::getVerkaufswert);
        int maxMaxGesundheitLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getMaxGesundheit);
        int maxMaxManaLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getMaxMana);
        int maxBeweglichkeitLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getBeweglichkeit);
        int maxRegenerationLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getRegeneration);
        int maxManaRegenerationLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getManageneration);

        String ueberschriftWert = "Verkaufswert";
        maxWertLength = Math.max(maxWertLength, ueberschriftWert.length());
        String ueberschriftLevel = "Gegenstands Level";
        maxItemLevelLength = Math.max(maxItemLevelLength, ueberschriftLevel.length());
        String ueberschriftMaxGesundheit = "Max Gesundheit";
        maxMaxGesundheitLength = Math.max(maxMaxGesundheitLength, ueberschriftMaxGesundheit.length());
        String ueberschriftMaxMana = "Max Mana";
        maxMaxManaLength = Math.max(maxMaxManaLength, ueberschriftMaxMana.length());
        String ueberschriftBeweglichkeit = "Beweglichkeit";
        maxBeweglichkeitLength = Math.max(maxBeweglichkeitLength, ueberschriftBeweglichkeit.length());
        String ueberschriftRegeneration = "Regeneration";
        maxRegenerationLength = Math.max(maxRegenerationLength, ueberschriftRegeneration.length());
        String ueberschriftManaRegeneration = "Mana Regeneration";
        maxManaRegenerationLength = Math.max(maxManaRegenerationLength, ueberschriftManaRegeneration.length());

        System.out.println(String.format("| %7s | %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | %" + maxWertLength + "s | %" + maxMaxGesundheitLength + "s | %" + maxMaxManaLength + "s | %" + maxBeweglichkeitLength + "s | %" + maxRegenerationLength + "s | %" + maxManaRegenerationLength + "s |",
                "Nummer", "Item Name", "Typ", ueberschriftLevel, ueberschriftWert, ueberschriftMaxGesundheit, ueberschriftMaxMana, ueberschriftBeweglichkeit, ueberschriftRegeneration, ueberschriftManaRegeneration));
        System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLength + maxTypLength + maxItemLevelLength + maxWertLength + maxMaxGesundheitLength + maxMaxManaLength + maxBeweglichkeitLength + maxRegenerationLength + maxManaRegenerationLength + 10, "-")));

        int nummer = 1;
        for (E ausruestung : arrayList) {
            System.out.printf("| %7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " | %" + maxMaxGesundheitLength + "d | %" + maxMaxManaLength + "d | %" + maxBeweglichkeitLength + "d | %" + maxRegenerationLength + "d | %" + maxManaRegenerationLength + "d |%n",
                    nummer++, ausruestung.getName(), ausruestung.getClass().getSimpleName(), ausruestung.getLevelAnforderung(), ausruestung.getVerkaufswert(),
                    ausruestung.getMaxGesundheit(), ausruestung.getMaxMana(), ausruestung.getBeweglichkeit(), ausruestung.getRegeneration(), ausruestung.getManageneration());
        }

        int nutzerAuswahl = nutzerEingabePruefung(1, arrayList.size());

        // Retrieve the selected item
        if (nutzerAuswahl >= 1 && nutzerAuswahl <= arrayList.size()) {
            ausgewaehltesAusruestungsgegenstand = arrayList.get(nutzerAuswahl - 1);
        } else {
            System.out.println("Du musst schon ein Item auswählen.. xD .");
            ausgewaehltesAusruestungsgegenstand = ausruestungsListeAnlegen(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
        }

        return ausgewaehltesAusruestungsgegenstand;
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
    private static <T> int pruefeMaxZeilenLaengefuerIntHash(Map<T, Integer> map, ToIntFunction<Integer> columnExtractor) {
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
        KonsolenAssistent.clear();
        Map<Verbrauchsgegenstand, Integer> map = this.partyController.getParty().getVerbrauchsgegenstaende();
        int maxItemNameLaenge = (pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getName)) + 1;
        int maxAnzahlLaenge = (pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue)) + 1;
        int maxWertLaenge = (pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getVerkaufswert)) + 1;

        String ueberschriftWert = "Wert";
        maxWertLaenge = Math.max(maxWertLaenge, ueberschriftWert.length());

        System.out.println(String.format("| %-" + maxItemNameLaenge + "s | %" + maxAnzahlLaenge + "s | %" + maxWertLaenge + "s |", "Gegenstand", "Anzahl", ueberschriftWert));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLaenge + maxAnzahlLaenge + maxWertLaenge + 9, "-")));

        int nummer = 1;
        for (Map.Entry<Verbrauchsgegenstand, Integer> eintrag : map.entrySet()) {
            Verbrauchsgegenstand gegenstand = eintrag.getKey();
            int anzahl = eintrag.getValue();

            System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLaenge + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLaenge + "d | " + Farbauswahl.YELLOW + "%" + maxWertLaenge + "d" + Farbauswahl.RESET + " |%n",
                    nummer++, gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
        }
    }

    public boolean kampfInventarAnzeigen(ArrayList<SpielerCharakter> friendlist) {
        KonsolenAssistent.clear();
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
        System.out.println("Geben sie nichts ein oder eine Zahl außerhalb der Auswahl um abbzubrechen");
        int ausgewaehlteNummer = ScannerHelfer.nextInt();

        Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesItem(map, ausgewaehlteNummer);
        if (ausgewaehltergegenstand != null) {
            System.out.println("Ausgewähltes Item: " + ausgewaehltergegenstand.getName());
            System.out.println("Auf Welchen Char soll dieses Item angewendet werden? ");
            SpielerCharakter ausgewaehlterChar;
            ausgewaehlterChar = charAuswahlMenue(friendlist);
            this.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(this.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));
            benutzt = true;
            KonsolenAssistent.clear();

        } else {
            benutzt = false;
            KonsolenAssistent.clear();
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
        KonsolenAssistent.clear();
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
            this.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(this.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));
            this.spielerinventarAnzeige();
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
        KonsolenAssistent.clear();
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
        switch (menuOption[ausgewaehlteOption]) {
            case "Ausgeruestet":
                ausgewaehlterChar = this.charAuswahlMenue(this.aktiveParty);
                this.ausgewaehlterCharAusruestungAnzeigen();
                //Todo haupt TODO ERST LÖSCHEN WEN AUSRÜSTUNGS MENÜ LAUFT! REMINDER <--- RODE GIB GAS
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
        this.accessoireAuswahlAnlegen(ausgewaehlterChar);
    }

    private void accessoireAuswahlAnlegen(SpielerCharakter ausgewaehlterChar) {

        int maxAccessoires = 3;  // wenn mehr dan mehr

        for (int i = 0; i < maxAccessoires; i++) {
            Accessoire accessoire = ausgewaehlterChar.getAccessoire(i);
            String accessoireNumber = "Accessoire " + (i + 1);

            if (accessoire != null) {
                System.out.println(accessoireNumber + ": " + accessoire);
            } else {
                System.out.println(accessoireNumber + ": -Empty-");
            }
        }

        // hier kann der nutzer ein ausrüstungsgegenstand wählen
        int nutzerauswahl = nutzerEingabePruefung(1, maxAccessoires);

        //Hier wird die Nutzerauswahl benutzt um das accessoire auszuwählen
        Accessoire ausgewaehltesAccessoire = ausgewaehlterChar.getAccessoire(nutzerauswahl - 1);
        if (ausgewaehltesAccessoire != null) {
            //TODO --------------------------------------AUSRÜSTUNG HIER BEARBEITEN ACCESSOIRE
            Ausruestungsgegenstand neuesAccessoire;
            neuesAccessoire = ausruestungsListeAnlegen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
            CharakterController.ausruestungAnlegen(ausgewaehlterChar, ausgewaehltesAccessoire, neuesAccessoire, this.partyController.getParty().getAusruestungsgegenstandInventar());
        } else {
            // Handle the case when the selected accessoire is empty
        }
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
        KonsolenAssistent.clear();
        menueaktive = true;
        while (menueaktive) {
            System.out.println(Farbauswahl.GREEN_BOLD + "Waehle eine Option:" + Farbauswahl.RESET);
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
                    System.out.println("Fehlerhafte Eingabe. Benutze 'w' zum Hochgehen, 's' um runter zu gehen  und Enter-taste um zu bestaetigen.");
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
        KonsolenAssistent.clear();
        Map<Material, Integer> map = this.partyController.getParty().getMaterialien();
        int maxItemNameLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Material::getName)) + 1;
        int maxAnzahlLength = (pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue)) + 1;
        int maxWertLength = (pruefeMaxZeilenLaengeHash(map.keySet(), Material::getVerkaufswert)) + 1;

        String ueberschriftWert = "Wert";
        maxWertLength = Math.max(maxWertLength, ueberschriftWert.length());

        System.out.println(String.format("| %-" + maxItemNameLength + "s | %" + maxAnzahlLength + "s | %" + maxWertLength + "s |", "Gegenstand", "Anzahl", ueberschriftWert));
        System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxAnzahlLength + maxWertLength + 9, "-")));

        for (Map.Entry<Material, Integer> entry : map.entrySet()) {
            Material gegenstand = entry.getKey();
            int anzahl = entry.getValue();

            System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLength + "d | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " |%n",
                    gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
        }

    }


    //-----Hier werden Verbauchsgegenstände bearbeitet und benutzt v--
    private void verbrauchsgegenstaendeAnzeige() {
        this.verbrauchsListeAnzeigen();
    }
}
