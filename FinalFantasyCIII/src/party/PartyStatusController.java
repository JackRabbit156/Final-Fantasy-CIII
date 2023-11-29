package party;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.GegenstandController;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class PartyStatusController {
    private final PartyController partyController;
    private int ausgewaehlteOption = 0;
    private String[] menuOption;
    private boolean menueaktive;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public PartyStatusController(PartyController partyController) {
        this.partyController = partyController;
        this.aktiveParty = fuellePartyList();
        this.ausgewaehlterChar = partyController.getParty().getHauptCharakter();
        menuOption = new String[]{"Party Status Uebersicht Anzeigen", "Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};

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
        if (arrayList.isEmpty()) {
            System.out.println("Sorry Leider haben Sie keine Ausruestung");
        } else {


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
    }

    private static Ausruestungsgegenstand ausruestungsListeAnlegen(ArrayList<? extends Ausruestungsgegenstand> arrayList, SpielerCharakter ausgewaehlteChar) {
        KonsolenAssistent.clear();
        boolean gueltigeAuswahl = false;
        Ausruestungsgegenstand ausgewaehltesAusruestungsgegenstand = null;
        int maxItemNameLength = pruefeMaxZeilenLaenge(arrayList, Gegenstand::getName);
        int maxTypLength = pruefeMaxZeilenLaenge(arrayList, Ausruestungsgegenstand -> Ausruestungsgegenstand.getClass().getSimpleName());
        int maxItemLevelLength = pruefeMaxzeilenLaengeInt(arrayList, Ausruestungsgegenstand::getLevelAnforderung);
        int maxWertLength = pruefeMaxzeilenLaengeInt(arrayList, Gegenstand::getVerkaufswert);
        int maxMaxGesundheitLength = 5;
        int maxMaxManaLength = 5;
        int maxBeweglichkeitLength = 5;
        int maxRegenerationLength = 5;
        int maxManaRegenerationLength = 5;
        int maxAttackeLength = 5;
        int maxMagischeAttackeLength = 5;
        int maxDefenseLength = 5;
        int maxMagischeDefenseLength = 5;

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

        String ueberschriftAttacke = "Physiche Attacke";
        maxAttackeLength = Math.max(maxAttackeLength, ueberschriftAttacke.length());
        String ueberschriftMAttack = "Magische Attacke";
        maxMagischeAttackeLength = Math.max(maxMagischeAttackeLength, ueberschriftMAttack.length());
        String ueberschriftDefense = "Physische Verteidigung";
        maxDefenseLength = Math.max(maxDefenseLength, ueberschriftDefense.length());
        String ueberschriftMDefense = "Magische Verteidigung";
        maxMagischeDefenseLength = Math.max(maxMagischeDefenseLength, ueberschriftMDefense.length());


        if (arrayList.get(0) instanceof Accessoire) {
            System.out.println(String.format("| %7s | %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | %" + maxWertLength + "s | %" + maxMaxGesundheitLength + "s | %" + maxMaxManaLength + "s | %" + maxBeweglichkeitLength + "s | %" + maxRegenerationLength + "s | %" + maxManaRegenerationLength + "s |",
                    "Nummer", "Item Name", "Typ", ueberschriftLevel, ueberschriftWert, ueberschriftMaxGesundheit, ueberschriftMaxMana, ueberschriftBeweglichkeit, ueberschriftRegeneration, ueberschriftManaRegeneration));
            System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLength + maxTypLength + maxItemLevelLength + maxWertLength + maxMaxGesundheitLength + maxMaxManaLength + maxBeweglichkeitLength + maxRegenerationLength + maxManaRegenerationLength + 10, "-")));
        } else if (arrayList.get(0) instanceof Waffe) {
            System.out.println(String.format("| %7s | %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | %" + maxWertLength + "s | %" + maxAttackeLength + "s | %" + maxMagischeAttackeLength + "s |",
                    "Nummer", "Item Name", "Typ", ueberschriftLevel, ueberschriftWert, ueberschriftAttacke, ueberschriftMAttack));
            System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLength + maxTypLength + maxItemLevelLength + maxWertLength + maxAttackeLength + maxMagischeAttackeLength + 10, "-")));
        } else if (arrayList.get(0) instanceof Ruestung) {
            System.out.println(String.format("| %7s | %-" + maxItemNameLength + "s | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | %" + maxWertLength + "s | %" + maxDefenseLength + "s | %" + maxMagischeDefenseLength + "s |",
                    "Nummer", "Item Name", "Typ", ueberschriftLevel, ueberschriftWert, ueberschriftDefense, ueberschriftMDefense));
            System.out.println(String.join("", Collections.nCopies(7 + maxItemNameLength + maxTypLength + maxItemLevelLength + maxWertLength + maxDefenseLength + maxMagischeDefenseLength + 10, "-")));
        }


        int nummer = 1;
        for (Ausruestungsgegenstand ausruestung : arrayList) {
            if (ausruestung instanceof Accessoire) {
                Accessoire ausruestungAccessoire = (Accessoire) ausruestung;
                if (ausruestungAccessoire.getLevelAnforderung() <= ausgewaehlteChar.getLevel()) {
                    System.out.printf("| %7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " | %" + maxMaxGesundheitLength + "d | %" + maxMaxManaLength + "d | %" + maxBeweglichkeitLength + "d | %" + maxRegenerationLength + "d | %" + maxManaRegenerationLength + "d |%n",
                            nummer++, ausruestungAccessoire.getName(), ausruestungAccessoire.getClass().getSimpleName(), ausruestungAccessoire.getLevelAnforderung(), ausruestungAccessoire.getVerkaufswert(),
                            ausruestungAccessoire.getMaxGesundheitsPunkte(), ausruestungAccessoire.getMaxManaPunkte(), ausruestungAccessoire.getBeweglichkeit(), ausruestungAccessoire.getGesundheitsRegeneration(), ausruestungAccessoire.getManaRegeneration());
                } else {
                    nummer++;
                }

            } else if (ausruestung instanceof Waffe) {
                Waffe ausruestungWaffe = (Waffe) ausruestung;
                if (ausruestungWaffe.getLevelAnforderung() <= ausgewaehlteChar.getLevel() && AusruestungsgegenstandInventar.charakterKannTragen(ausruestungWaffe, ausgewaehlteChar)) {
                    System.out.printf("| %7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " | %" + maxAttackeLength + "d | %" + maxMagischeAttackeLength + "d |%n",
                            nummer++, ausruestungWaffe.getName(), ausruestungWaffe.getClass().getSimpleName(), ausruestungWaffe.getLevelAnforderung(), ausruestungWaffe.getVerkaufswert(),
                            ausruestungWaffe.getAttacke(), ausruestungWaffe.getMagischeAttacke());
                } else {
                    nummer++;
                }
            } else if (ausruestung instanceof Ruestung) {
                Ruestung ausruestungRuestung = (Ruestung) ausruestung;
                if (ausruestungRuestung.getLevelAnforderung() <= ausgewaehlteChar.getLevel() && AusruestungsgegenstandInventar.charakterKannTragen(ausruestungRuestung, ausgewaehlteChar)) {
                    System.out.printf("| %7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxTypLength + "s | %" + maxItemLevelLength + "s | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " | %" + maxDefenseLength + "d | %" + maxMagischeDefenseLength + "d |%n",
                            nummer++, ausruestungRuestung.getName(), ausruestungRuestung.getClass().getSimpleName(), ausruestungRuestung.getLevelAnforderung(), ausruestungRuestung.getVerkaufswert(),
                            ausruestungRuestung.getVerteidigung(), ausruestungRuestung.getMagischeVerteidigung());
                } else {
                    nummer++;
                }
            }
        }


        int nutzerAuswahl = nutzerEingabePruefung(1, arrayList.size());


        if (nutzerAuswahl >= 1 && nutzerAuswahl <= arrayList.size()) {
            ausgewaehltesAusruestungsgegenstand = arrayList.get(nutzerAuswahl - 1);
        } else if (nutzerAuswahl == 0) {
            ausgewaehltesAusruestungsgegenstand = null;
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
        int nutzereingabe = 0;
        while (true) {
            System.out.print("Bitte Gewuenschte Nummer eingeben: ");
            try {
                nutzereingabe = ScannerHelfer.nextInt();
                if (nutzereingabe < minValue || nutzereingabe > maxValue) {
                    System.out.println("Ungueltige Eingabe. Bitte eine Nummer zwischen " + minValue + " und " + maxValue + " waehlen. Oder 99 zum abbrechen");
                } else {
                    break;
                }
                if (nutzereingabe == 99) {
                    nutzereingabe = 0;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ungueltige Eingabe. Bitte eine Nummer eingeben. Oder 99 zum abbrechen");
            }
        }
        return nutzereingabe;
    }

    /**
     * Berechnet die maximale Breite für den Tabellenheader in einer Liste von SpielerCharakter-Objekten.
     * <p>
     * Die Funktion verwendet die Liste der Spaltenüberschriften und Extraktoren, um die
     * maximale Breite für jede Spalte im Tabellenheader zu berechnen. Die Länge der
     * Spaltenüberschrift wird dabei berücksichtigt.
     *
     * @param aktiveParty Die Liste von aktiven SpielerCharakter-Objekten.
     *
     * @return Die maximale Breite für den Tabellenheader.
     *
     * @author HF Rode
     * @since 20.11.2023
     */
    private static int errechneMaximaleHeaderWeite(ArrayList<SpielerCharakter> aktiveParty) {
        List<String> headers = Arrays.asList(
                "Name", "Level", "Erfahrungspunkte", "GesundheitsPunkte",
                "Mana Punkte", "Beweglichkeit", "Genauigkeit",
                "Magische Attacke", "Gesundheitsregeneration",
                "Physische Attacke", "Geschichte"
        );

        List<Function<SpielerCharakter, ?>> extractors = Arrays.asList(
                SpielerCharakter::getName, SpielerCharakter::getLevel,
                SpielerCharakter::getErfahrungsPunkte, SpielerCharakter::getGesundheitsPunkte,
                SpielerCharakter::getManaPunkte, SpielerCharakter::getBeweglichkeit,
                SpielerCharakter::getGenauigkeit, SpielerCharakter::getMagischeAttacke,
                SpielerCharakter::getGesundheitsRegeneration, SpielerCharakter::getPhysischeAttacke,
                SpielerCharakter::getGeschichte
        );

        return IntStream.range(0, headers.size())
                .mapToObj(i -> errechneMaximaleStringLaenge(aktiveParty, extractors.get(i), headers.get(i)))
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Berechnet die maximale Länge für eine bestimmte Spalte in einer Liste von SpielerCharakter-Objekten.
     * <p>
     * Die Funktion verwendet den Extraktor, um die Werte für eine bestimmte Spalte zu extrahieren,
     * und vergleicht die Längen der extrahierten Werte mit der Länge der Spaltenüberschrift. Die
     * maximale Länge wird dann zurückgegeben.
     *
     * @param list      Die Liste von SpielerCharakter-Objekten.
     * @param extractor Die Funktion, um den Wert der Spalte zu extrahieren.
     * @param header    Die Spaltenüberschrift.
     *
     * @return Die maximale Länge der Spalte.
     *
     * @author HF Rode
     * @since 20.11.2023
     */
    private static int errechneMaximaleStringLaenge(ArrayList<SpielerCharakter> list, Function<SpielerCharakter, ?> extractor, String header) {
        return list.stream()
                .map(extractor)
                .map(Object::toString)
                .mapToInt(String::length)
                .map(len -> Math.max(len, header.length()))
                .max()
                .orElse(0);
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
        if (map.isEmpty()) {
            this.menuOption = new String[]{"Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};
            System.out.println("Sie besitzen leider noch keine Items");
        } else {


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
    }

    public boolean kampfInventarAnzeigen(ArrayList<SpielerCharakter> friendlist, PartyStatusController partyStatusController) {
        KonsolenAssistent.clear();
        boolean benutzt = false;
        Map<Verbrauchsgegenstand, Integer> map = partyStatusController.partyController.getParty().getVerbrauchsgegenstaende();
        int maxItemNameLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getName);
        int maxAnzahlLaenge = pruefeMaxZeilenLaengefuerIntHash(map, Integer::intValue);
        int maxWertLaenge = pruefeMaxZeilenLaengeHash(map.keySet(), Verbrauchsgegenstand::getVerkaufswert);

        String ueberschriftWert = "Wert";
        maxWertLaenge = Math.max(maxWertLaenge, ueberschriftWert.length());
        if (map.isEmpty()) {
            System.out.println("Sorry Diesen Kampf musst du ohne Items durchfuehren... good luck <3");
        } else {


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
            System.out.print("Welches Item moechtest du benutzen? ");
            System.out.println("Geben sie nichts ein oder eine Zahl ausserhalb der Auswahl um abbzubrechen");
            int ausgewaehlteNummer = ScannerHelfer.nextInt();

            Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesItem(map, ausgewaehlteNummer);
            if (ausgewaehltergegenstand != null) {
                System.out.println("Ausgewaehltes Item: " + ausgewaehltergegenstand.getName());
                System.out.println("Auf Welchen Char soll dieses Item angewendet werden? ");
                SpielerCharakter ausgewaehlterChar;
                ausgewaehlterChar = charAuswahlMenue(friendlist);
                partyStatusController.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(partyStatusController.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));
                benutzt = true;

            } else {
                benutzt = false;
            }
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
        if (map.isEmpty()) {
            System.out.println("Leider haben Sie keine verbrauchsgegenstande ");
        } else {


            for (Map.Entry<Verbrauchsgegenstand, Integer> eintrag : map.entrySet()) {
                Verbrauchsgegenstand gegenstand = eintrag.getKey();
                int anzahl = eintrag.getValue();

                System.out.printf("| %-7d | " + Farbauswahl.BLUE + "%-" + maxItemNameLaenge + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLaenge + "d | " + Farbauswahl.YELLOW + "%" + maxWertLaenge + "d" + Farbauswahl.RESET + " |%n",
                        nummer++, gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
            }

            // Nutzereingabe hier abrufen
            System.out.print("Welches Item moechtest du benutzen? ");
            int ausgewaehlteNummer = ScannerHelfer.nextInt();

            Verbrauchsgegenstand ausgewaehltergegenstand = erkenneAusgewaehltesItem(map, ausgewaehlteNummer);
            if (ausgewaehltergegenstand != null) {
                System.out.println("Ausgewaehltes Item: " + ausgewaehltergegenstand.getName());
                System.out.println("Auf Welchen Char soll dieses Item angewendet werden? ");
                SpielerCharakter ausgewaehlterChar;
                ausgewaehlterChar = charAuswahlMenue(this.aktiveParty);
                this.partyController.getParty().setVerbrauchsgegenstaende(GegenstandController.verwendeVerbrauchsgegenstand(this.partyController.getParty().getVerbrauchsgegenstaende(), ausgewaehltergegenstand, ausgewaehlterChar));
                this.spielerinventarAnzeige();
            } else {
                System.out.println("Bitte waehle ein Item das benutzbar ist");
            }
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
            System.out.println("Die Party ist leer. Was Eigentlich unmoeglich ist aber hey, Easter EGG I guess <3");

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
            System.out.println("----------------------------------------------------------");
        }

        int ausgewaehlterCharIndex = nutzerEingabePruefung(1, aktiveParty.size()) - 1;
        if (ausgewaehlterCharIndex >= 5) {
            System.out.println("Bitte waehlen sie einen Verfuegbaren char aus in diesem Menu");
            ausgewaehlterCharIndex = nutzerEingabePruefung(1, aktiveParty.size()) - 1;
        }
        return aktiveParty.get(ausgewaehlterCharIndex);
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
    private void ausgewaehlteMenuOption() {
        switch (menuOption[ausgewaehlteOption]) {
            case "Ausgeruestet":
                ausgewaehlterChar = this.charAuswahlMenue(this.aktiveParty);

                menuOption = new String[]{"Waffe Tauschen", "Ruestung Tauschen", "Accessoire Tauschen", "Zurueck"};
                ausgewaehlteOption = 0;
                break;
            case "Waffe Tauschen":
                this.waffeAuswahl(ausgewaehlterChar);

                ausgewaehlteOption = 0;
                break;
            case "Party Status Uebersicht Anzeigen":
                this.partyStatusAnzeigen();
                ausgewaehlteOption = 0;
                break;
            case "Ruestung Tauschen":
                this.ruestungAuswahl(ausgewaehlterChar);

                ausgewaehlteOption = 0;
                break;
            case "Accessoire Tauschen":
                this.accessoireAuswahl(ausgewaehlterChar);

                ausgewaehlteOption = 0;
                break;
            case "Gelagerte Ausruestung":
                menuOption = new String[]{"Waffen", "Ruestung", "Accesoirs", "Zurueck"};
                ausgewaehlteOption = 0;
                break;
            case "Verbrauchsgegenstaende":
                menuOption = new String[]{"Benutzen", "Zurueck"};
                this.verbrauchsgegenstaendeAnzeige();
                ausgewaehlteOption = 0;
                break;
            case "Benutzen":
                this.verbrauchsgegenstaendeBenutzen();
                ausgewaehlteOption = 0;
                break;
            case "Upgrade Materialien":
                this.upgradematerialienAnzeige();
                ausgewaehlteOption = 0;
                break;
            case "Waffen":
                this.waffenAnzeigen();
                ausgewaehlteOption = 0;
                break;
            case "Ruestung":
                this.ruestungAnzeigen();
                ausgewaehlteOption = 0;
                break;
            case "Accesoirs":
                this.accesssoireAnzeigen();
                ausgewaehlteOption = 0;
                break;
            case "Zurueck":
                menuOption = new String[]{"Party Status Uebersicht Anzeigen", "Ausgeruestet", "Gelagerte Ausruestung", "Verbrauchsgegenstaende", "Upgrade Materialien", "Zueruck zum Hub"};
                ausgewaehlteOption = 0;
                break;
            case "Zueruck zum Hub":
                menueaktive = false;
                KonsolenAssistent.clear();
                break;


        }
    }

    private void ausruestungGewaehlterCharAnzeigen(SpielerCharakter ausgewaehlterChar) {
        KonsolenAssistent.clear();
        if (aktiveParty.isEmpty()) {
            System.out.println("Die Party ist leer. Was Eigentlich unmoeglich ist aber hey, Easter EGG I guess <3");
        }

        System.out.println("| Nummer | Char name | Lebenspunkte | Mana Punkte | Klasse |");
        System.out.println("----------------------------------------------------------");

        int nummer = 1;

        System.out.printf("| %-6d| %-10s| %-13s| %-12s| %-7s|%n",
                nummer++,
                ausgewaehlterChar.getName(),
                ausgewaehlterChar.getGesundheitsPunkte() + "/" + ausgewaehlterChar.getMaxGesundheitsPunkte(),
                ausgewaehlterChar.getManaPunkte() + "/" + ausgewaehlterChar.getMaxManaPunkte(),
                ausgewaehlterChar.getKlasse().getBezeichnung());
        System.out.println("----------------------------------------------------------");
        System.out.printf("|" + Farbauswahl.RED + " %-30s" + Farbauswahl.RESET + "|" + Farbauswahl.BLUE + " %-30s" + Farbauswahl.RESET + "|" + Farbauswahl.YELLOW + " %-30s" + Farbauswahl.RESET + "%n",
                "Weapon: " + ausgewaehlterChar.getWaffe().getName() + " Attacke: " + ausgewaehlterChar.getWaffe().getAttacke() + "  Magische Attacke: " + ausgewaehlterChar.getWaffe().getMagischeAttacke() + "\n",
                "Ruestung: " + ausgewaehlterChar.getRuestung().getName() + " Verteidigung: " + ausgewaehlterChar.getRuestung().getVerteidigung() + "  Magische Verteidigung: " + ausgewaehlterChar.getRuestung().getMagischeVerteidigung() + "\n",
                "Accessoire: " +
                        "1. " + ausgewaehlterChar.getAccessoire(0).getName() + " Beweglichkeit: " + ausgewaehlterChar.getAccessoire(0).getBeweglichkeit() + " Gesundheits Generation: " + ausgewaehlterChar.getAccessoire(0).getGesundheitsRegeneration() + " Maximale Gesundheitspunkte " + ausgewaehlterChar.getAccessoire(0).getMaxGesundheitsPunkte() + " Maximale Mana Punkte: " + ausgewaehlterChar.getAccessoire(0).getMaxManaPunkte() + " Mana Regeneration: " + ausgewaehlterChar.getAccessoire(0).getManaRegeneration() + "\n"
                        + "" + Farbauswahl.RESET + "|" + Farbauswahl.YELLOW + " Accessoire: 2. " + ausgewaehlterChar.getAccessoire(1).getName() + " Beweglichkeit: " + ausgewaehlterChar.getAccessoire(1).getBeweglichkeit() + " Gesundheits Generation: " + ausgewaehlterChar.getAccessoire(1).getGesundheitsRegeneration() + " Maximale Gesundheitspunkte " + ausgewaehlterChar.getAccessoire(1).getMaxGesundheitsPunkte() + " Maximale Mana Punkte: " + ausgewaehlterChar.getAccessoire(1).getMaxManaPunkte() + " Mana Regeneration: " + ausgewaehlterChar.getAccessoire(1).getManaRegeneration() + "\n"
                        + "" + Farbauswahl.RESET + "|" + Farbauswahl.YELLOW + " Accessoire: 3. " + ausgewaehlterChar.getAccessoire(2).getName() + " Beweglichkeit: " + ausgewaehlterChar.getAccessoire(2).getBeweglichkeit() + " Gesundheits Generation: " + ausgewaehlterChar.getAccessoire(2).getGesundheitsRegeneration() + " Maximale Gesundheitspunkte " + ausgewaehlterChar.getAccessoire(2).getMaxGesundheitsPunkte() + " Maximale Mana Punkte: " + ausgewaehlterChar.getAccessoire(2).getMaxManaPunkte() + " Mana Regeneration: " + ausgewaehlterChar.getAccessoire(2).getManaRegeneration() + "\n");
        System.out.println("---------------------------------------------------------------------------");


    }

    private void partyStatusAnzeigen() {
        this.partyStatusAusgeben(this.aktiveParty);
    }

    /**
     * Gibt den Status der Spielercharaktere in einer tabellarischen Form aus.
     * <p>
     * Die Funktion gibt den aktuellen Status der Spielercharaktere in einer
     * übersichtlichen Tabelle aus. Falls die aktive Party leer ist, wird eine
     * entsprechende Meldung ausgegeben.
     *
     * @param aktiveParty Die Liste der aktiven Spielercharaktere.
     *
     * @author HF Rode
     * @since 20.11.2023
     */
    private void partyStatusAusgeben(ArrayList<SpielerCharakter> aktiveParty) {
        KonsolenAssistent.clear();
        if (aktiveParty.isEmpty()) {
            System.out.println("Die Party ist leer. Was eigentlich unmoeglich ist, aber hey, Easter EGG I guess <3");
        } else {
            // Tabellenkopf erstellen
            System.out.printf("| %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getName, "Name") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getLevel, "Level") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getErfahrungsPunkte, "Erfahrungspunkte") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGesundheitsPunkte, "Gesundheitspunkte") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getManaPunkte, "Manapunkte") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getBeweglichkeit, "Beweglichkeit") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGenauigkeit, "Genauigkeit") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getMagischeAttacke, "Magische Attacke") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGesundheitsRegeneration, "Gesundheitsregeneration") + "s | %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getPhysischeAttacke, "Physische Attacke") + "s | %-33s |%n", "Name", "Level", "Erfahrungspunkte", "Gesundheitspunkte", "Manapunkte", "Beweglichkeit", "Genauigkeit", "MagischeAttacke", "Gesundheitsregeneration", "Physische Attacke", "Geschichte");
            // Trennlinie erstellen
            System.out.println(String.join("", Collections.nCopies(errechneMaximaleHeaderWeite(aktiveParty) + 75, "-")));
            // Spielercharaktere ausgeben
            for (SpielerCharakter charakter : aktiveParty) {
                String geschichte = charakter.getGeschichte();
                // Spielercharakterdaten ausgeben
                System.out.printf("| %-" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getName, "Name") + "s | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getLevel, "Level") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getErfahrungsPunkte, "Erfahrungspunkte") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGesundheitsPunkte, "Gesundheitspunkte") + "s | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getManaPunkte, "Manapunkte") + "s | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getBeweglichkeit, "Beweglichkeit") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGenauigkeit, "Genauigkeit") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getMagischeAttacke, "Magische Attacke") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getGesundheitsRegeneration, "Gesundheitsregeneration") + "d | %" + errechneMaximaleStringLaenge(aktiveParty, SpielerCharakter::getPhysischeAttacke, "Physische Attacke") + "d | %-30s"+"..."+" |%n",
                        charakter.getName(),
                        charakter.getLevel(),
                        charakter.getErfahrungsPunkte(),
                        charakter.getGesundheitsPunkte() + "/" + charakter.getMaxGesundheitsPunkte(),
                        charakter.getManaPunkte() + "/" + charakter.getMaxManaPunkte(),
                        charakter.getBeweglichkeit(),
                        charakter.getGenauigkeit(),
                        charakter.getMagischeAttacke(),
                        charakter.getGesundheitsRegeneration(),
                        charakter.getPhysischeAttacke(),
                        geschichte.substring(0,Math.min(30,geschichte.length())));
                // Trennlinie nach jedem Spielercharakter erstellen
                System.out.println(String.join("", Collections.nCopies(errechneMaximaleHeaderWeite(aktiveParty) + 75, "-")));
            }
        }
    }

    private void accessoireAuswahl(SpielerCharakter ausgewaehlterChar) {
        this.accessoireAuswahlAnlegen(ausgewaehlterChar);
    }

    private void accessoireAuswahlAnlegen(SpielerCharakter ausgewaehlterChar) {

        int maxAccessoires = 3;  // wenn mehr dan mehr

        for (int i = 0; i < maxAccessoires; i++) {
            Accessoire accessoire = ausgewaehlterChar.getAccessoire(i);
            String accessoireNumber = "Accessoire " + (i + 1);

            if (accessoire != null) {
                System.out.println(accessoireNumber + ": " + accessoire.getName());
            } else {
                System.out.println(accessoireNumber + ": -Empty-");
            }
        }

        // hier kann der nutzer ein ausrüstungsgegenstand wählen
        int nutzerauswahl = nutzerEingabePruefung(1, maxAccessoires);

        //Hier wird die Nutzerauswahl benutzt um das accessoire auszuwählen
        Accessoire ausgewaehltesAccessoire = ausgewaehlterChar.getAccessoire(nutzerauswahl - 1);
        if (ausgewaehltesAccessoire != null) {
            Ausruestungsgegenstand neuesAccessoire;
            CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehltesAccessoire, this.partyController.getParty().getAusruestungsgegenstandInventar());
            neuesAccessoire = ausruestungsListeAnlegen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore(), ausgewaehlterChar);
            if (neuesAccessoire == null) {
                CharakterController.ausruestungAnlegen(ausgewaehlterChar, ausgewaehltesAccessoire, this.partyController.getParty().getAusruestungsgegenstandInventar());
            } else {
                CharakterController.ausruestungAnlegen(ausgewaehlterChar, neuesAccessoire, this.partyController.getParty().getAusruestungsgegenstandInventar());
            }

        } else {
            System.err.println("Wie auch immer du hierhingekommen bist hier is ein easteregg accessoireAuswahlAnlegen <-----");
        }
    }

    private void waffeAuswahl(SpielerCharakter ausgewaehlterChar) {
        Waffe waffe = ausgewaehlterChar.getWaffe();
        System.out.println("Waffen name: " + waffe.getName() + " | Phys Attacke: " + waffe.getAttacke() + " | Magische Attacke: " + waffe.getMagischeAttacke());

        System.out.println("Druecken sie enter oder geben sie 'e' ein um die Waffe zu Wechseln oder geben sie irgendeinen anderen Buchstaben ein um abbzubrechen");
        char nutzerauswahl = ScannerHelfer.nextChar();
        if (nutzerauswahl == 'e') {
            Waffe ausgewaehlteWaffe = ausgewaehlterChar.getWaffe();
            if (ausgewaehlteWaffe != null) {
                Ausruestungsgegenstand neueWaffe;
                CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehlteWaffe, this.partyController.getParty().getAusruestungsgegenstandInventar());
                neueWaffe = ausruestungsListeAnlegen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen(), ausgewaehlterChar);
                if (neueWaffe == null) {
                    CharakterController.ausruestungAnlegen(ausgewaehlterChar, ausgewaehlteWaffe, this.partyController.getParty().getAusruestungsgegenstandInventar());
                } else {
                    CharakterController.ausruestungAnlegen(ausgewaehlterChar, neueWaffe, this.partyController.getParty().getAusruestungsgegenstandInventar());
                }
            } else {
                System.err.println("Wie auch immer du hier hin gekommen bist hier is ein easteregg accessoireAuswahlAnlegen <-----");
            }
        } else {
            System.out.println("abbruch");
        }

    }

    private void ruestungAuswahl(SpielerCharakter ausgewaehlterChar) {
        Ruestung ruestung = ausgewaehlterChar.getRuestung();
        System.out.println("Ruestungsname name: " + ruestung.getName() + " | Phys Verteidigung: " + ruestung.getVerteidigung() + " | Magische Verteidigung: " + ruestung.getMagischeVerteidigung());

        System.out.println("Druecken sie enter oder geben sie 'e' ein um die Ruestung zu Wechseln oder geben sie irgendeinen anderen Buchstaben ein um abbzubrechen");
        char nutzerauswahl = ScannerHelfer.nextChar();
        if (nutzerauswahl == 'e') {
            Ruestung ausgewaehlteRuestung = ausgewaehlterChar.getRuestung();
            if (ausgewaehlteRuestung != null) {
                Ausruestungsgegenstand neueRuestung;
                CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehlteRuestung, this.partyController.getParty().getAusruestungsgegenstandInventar());
                neueRuestung = ausruestungsListeAnlegen(this.partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung(), ausgewaehlterChar);
                if (neueRuestung == null) {
                    CharakterController.ausruestungAnlegen(ausgewaehlterChar, ausgewaehlteRuestung, this.partyController.getParty().getAusruestungsgegenstandInventar());
                } else {
                    CharakterController.ausruestungAnlegen(ausgewaehlterChar, neueRuestung, this.partyController.getParty().getAusruestungsgegenstandInventar());
                }
            } else {
                System.err.println("Wie auch immer du hier hin gekommen bist hier is ein easteregg accessoireAuswahlAnlegen <-----");
            }
        } else {
            System.out.println("abbruch");
        }
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
        this.aktiveParty = fuellePartyList();
        KonsolenAssistent.clear();
        menueaktive = true;

        while (menueaktive) {
            switch (menuOption[ausgewaehlteOption]) {
                case "Waffe Tauschen":
                    this.ausruestungGewaehlterCharAnzeigen(ausgewaehlterChar);
                    break;
                case "Ruestung Tauschen":
                    this.ausruestungGewaehlterCharAnzeigen(ausgewaehlterChar);
                    break;
                case "Accessoire Tauschen":
                    this.ausruestungGewaehlterCharAnzeigen(ausgewaehlterChar);
                    break;
            }
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
                    this.ausgewaehlteMenuOption();
                    break;
                default:
                    KonsolenAssistent.clear();
                    System.out.println("Fehlerhafte Eingabe. Benutze 'w' zum Hochgehen, 's' um runter zu gehen  und Enter-taste um zu bestaetigen.");
                    break;
            }
        }
    }

    private void upgradematerialienAnzeige() {
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
        if (map.isEmpty()) {
            System.out.println("Leider besitzen sie Momentan noch keine Materialien");
        } else {


            System.out.println(String.format("| %-" + maxItemNameLength + "s | %" + maxAnzahlLength + "s | %" + maxWertLength + "s |", "Gegenstand", "Anzahl", ueberschriftWert));
            System.out.println(String.join("", Collections.nCopies(maxItemNameLength + maxAnzahlLength + maxWertLength + 9, "-")));

            for (Map.Entry<Material, Integer> entry : map.entrySet()) {
                Material gegenstand = entry.getKey();
                int anzahl = entry.getValue();

                System.out.printf("| " + Farbauswahl.BLUE + "%-" + maxItemNameLength + "s" + Farbauswahl.RESET + " | %-" + maxAnzahlLength + "d | " + Farbauswahl.YELLOW + "%" + maxWertLength + "d" + Farbauswahl.RESET + " |%n",
                        gegenstand.getName(), anzahl, gegenstand.getVerkaufswert());
            }
        }

    }

    private void verbrauchsgegenstaendeAnzeige() {
        this.verbrauchsListeAnzeigen();
    }
}
