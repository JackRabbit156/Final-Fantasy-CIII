package de.bundeswehr.auf.final_fantasy.charakter.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.AttributCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.spezialisierungen.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.DebugHelper;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;

import java.util.ArrayList;
import java.util.List;

public class CharakterController {

    /**
     * Legt dem Charakter den Ausrüstungsgegenstand an, wenn er diesen Gegenstand tragen darf.
     * Dabei werden eventuelle Boni auf den Charakter gerechnet.
     * Der Gegenstand kommt aus keinem Inventar!
     *
     * @param spielerCharakter       SpielerCharakter, der den Ausrüstungsgegenstand tragen soll
     * @param ausruestungsgegenstand Ausrüstungsgegenstand, der angelegt werden soll
     * @author Lang
     * @since 05.12.2023
     */
    public static void ausruestungAnlegen(SpielerCharakter spielerCharakter, AusruestungsGegenstand ausruestungsgegenstand) {
        ausruestungAnlegen(spielerCharakter, ausruestungsgegenstand, null);
    }

    /**
     * Legt dem Charakter den Ausrüstungsgegenstand an und entfernt ihn aus dem Inventar, wenn er diesen Gegenstand tragen darf.
     * Dabei werden eventuelle Boni auf den Charakter gerechnet.
     *
     * @param spielerCharakter               SpielerCharakter, der den Ausrüstungsgegenstand tragen soll
     * @param ausruestungsgegenstand         Ausrüstungsgegenstand, der angelegt werden soll
     * @param ausruestungsgegenstandInventar Inventar aus dem der Gegenstand kommt
     * @author Lang
     * @author OF Kretschmer (GegenstandsAttribute mit CharakterAttributen verrechnen hinzugefügt)
     * @author OLt Ebert (Trageeinschränkung und CharakterAttribute/Boni für Waffen/Rüstungen)
     * @since 05.12.2023
     */
    public static void ausruestungAnlegen(SpielerCharakter spielerCharakter,
                                          AusruestungsGegenstand ausruestungsgegenstand,
                                          AusruestungsGegenstandInventar ausruestungsgegenstandInventar) {
        if (charakterDarfTragen(spielerCharakter, ausruestungsgegenstand)) {
            if (ausruestungsgegenstandInventar != null) {
                ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
            }
            if (ausruestungsgegenstand instanceof Accessoire) {
                Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
                for (int i = 0; i < 3; i++) {
                    if (spielerCharakter.getAccessoire(i) == null) {
                        spielerCharakter.setAccessoire(accessoire, i);

                        if (spielerCharakter.getGesundheitsPunkte() == spielerCharakter.getMaxGesundheitsPunkte()) {
                            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                        }
                        if (spielerCharakter.getManaPunkte() == spielerCharakter.getMaxManaPunkte()) {
                            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + accessoire.getMaxManaPunkte());
                        }
                        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + accessoire.getMaxManaPunkte());

                        spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + accessoire.getGesundheitsRegeneration());
                        spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + accessoire.getManaRegeneration());
                        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + accessoire.getBeweglichkeit());
                        break;
                    }
                }
            }
            else if (ausruestungsgegenstand instanceof Waffe) {
                Waffe waffe = (Waffe) ausruestungsgegenstand;
                spielerCharakter.setWaffe(waffe);
                spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + waffe.getAttacke());
                spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + waffe.getMagischeAttacke());
                spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + waffe.getGenauigkeit());
                spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + waffe.getBeweglichkeit());
            }
            else if (ausruestungsgegenstand instanceof Ruestung) {
                Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
                spielerCharakter.setRuestung(ruestung);
                spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + ruestung.getMagischeVerteidigung());
                spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + ruestung.getVerteidigung());
                spielerCharakter.setResistenz(spielerCharakter.getResistenz() + ruestung.getResistenz());
                if (spielerCharakter.getGesundheitsPunkte() == spielerCharakter.getMaxGesundheitsPunkte()) {
                    spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + ruestung.getMaxGesundheitsPunkte());
                }
                if (spielerCharakter.getManaPunkte() == spielerCharakter.getMaxManaPunkte()) {
                    spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + ruestung.getMaxManaPunkte());
                }
                spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + ruestung.getMaxGesundheitsPunkte());
                spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + ruestung.getMaxManaPunkte());
            }
        }
        else {
            throw new RuntimeException(spielerCharakter.getName() + " darf diesen Gegenstand nicht tragen: " + ausruestungsgegenstand.getName());
        }
    }

    /**
     * Gibt die Angelegte Ausrüstung als ArrayList zurück
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @return ArrayList&lt;Ausruestungsgegenstand&gt;
     * @author Lang
     * @since 18.11.2023
     */
    public static List<AusruestungsGegenstand> ausruestungAnzeigen(SpielerCharakter spielerCharakter) {
        List<AusruestungsGegenstand> ausruestungsgegenstaende = new ArrayList<>();
        ausruestungsgegenstaende.add(spielerCharakter.getWaffe());
        ausruestungsgegenstaende.add(spielerCharakter.getRuestung());
        ausruestungsgegenstaende.add(spielerCharakter.getAccessoires()[0]);
        ausruestungsgegenstaende.add(spielerCharakter.getAccessoires()[1]);
        ausruestungsgegenstaende.add(spielerCharakter.getAccessoires()[2]);
        return ausruestungsgegenstaende;
    }

    /**
     * Zieht dem Charakter dem übergebenen Ausrüstungsgegenstand aus.
     * Der Gegenstand wird beim Ablegen vernichtet!
     *
     * @param spielerCharakter       WER: SpielerCharakter für den die Ausrüstung abgelegt wird
     * @param ausruestungsgegenstand WAS: Ausrüstungsgegenstand, der abgelegt werden soll
     * @author Lang
     * @author OF Kretschmer (CharakterAttribute mit GegenstandsAttributen verrechnen verrechnen)
     * @author OLt Ebert - Verrechnung der Waffen/Ruestungsattributen
     * @since 05.12.2023
     */
    public static void ausruestungAusziehen(SpielerCharakter spielerCharakter, AusruestungsGegenstand ausruestungsgegenstand) {
        ausruestungAusziehen(spielerCharakter, ausruestungsgegenstand, null);
    }

    /**
     * Zieht dem Charakter dem übergebenen Ausrüstungsgegenstand aus und fügt diesen dem Inventar hinzu.
     * Sollte der Gegenstand ein SöldnerItem sein, wird dieser beim Ablegen vernichtet.
     *
     * @param spielerCharakter               WER: SpielerCharakter für den die Ausrüstung abgelegt wird
     * @param ausruestungsgegenstand         WAS: Ausrüstungsgegenstand, der abgelegt werden soll
     * @param ausruestungsgegenstandInventar WOHIN: Inventar wo der Ausrüstungsgegenstand abgelegt wird
     * @author Lang
     * @author OF Kretschmer (CharakterAttribute mit GegenstandsAttributen verrechnen verrechnen)
     * @author OLt Ebert - Verrechnung der Waffen/Ruestungsattributen
     * @since 05.12.2023
     */
    public static void ausruestungAusziehen(SpielerCharakter spielerCharakter,
                                            AusruestungsGegenstand ausruestungsgegenstand,
                                            AusruestungsGegenstandInventar ausruestungsgegenstandInventar) {
        if (!ausruestungsgegenstand.isIstSoeldnerItem() && ausruestungsgegenstandInventar != null) {
            ausruestungsgegenstandInventar.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        }
        if (ausruestungsgegenstand instanceof Waffe) {
            Waffe waffe = (Waffe) ausruestungsgegenstand;
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() - waffe.getAttacke());
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() - waffe.getMagischeAttacke());
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() - waffe.getGenauigkeit());
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - waffe.getBeweglichkeit());
            spielerCharakter.setWaffe(null);
        }
        else if (ausruestungsgegenstand instanceof Ruestung) {
            Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() - ruestung.getVerteidigung());
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() - ruestung.getMagischeVerteidigung());
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() - ruestung.getResistenz());
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - ruestung.getMaxGesundheitsPunkte());
            if ((spielerCharakter.getGesundheitsPunkte() - ruestung.getMaxGesundheitsPunkte()) <= 0) {
                spielerCharakter.setGesundheitsPunkte(1);
            }
            else {
                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() - ruestung.getMaxGesundheitsPunkte());
            }
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - ruestung.getMaxManaPunkte());
            if ((spielerCharakter.getManaPunkte() - ruestung.getMaxManaPunkte()) <= 0) {
                spielerCharakter.setManaPunkte(1);
            }
            else {
                spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() - ruestung.getMaxManaPunkte());
            }
            spielerCharakter.setRuestung(null);
        }
        else if (ausruestungsgegenstand instanceof Accessoire) {
            Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < spielerCharakter.getAccessoires().length; i++) {
                if (spielerCharakter.getAccessoire(i) != null) {
                    if (spielerCharakter.getAccessoire(i).equals(accessoire)) {
                        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        if ((spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte()) <= 0) {
                            spielerCharakter.setGesundheitsPunkte(1);
                        }
                        else {
                            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        }
                        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - (accessoire).getMaxManaPunkte());
                        if ((spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte()) <= 0) {
                            spielerCharakter.setManaPunkte(1);
                        }
                        else {
                            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte());
                        }
                        spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() - accessoire.getGesundheitsRegeneration());
                        spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() - accessoire.getManaRegeneration());
                        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - accessoire.getBeweglichkeit());
                        spielerCharakter.setAccessoire(null, i);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Erhöht die Beweglichkeit um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void beweglichkeitVerbessern(AttributCharakter charakter, int wert) {
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + wert);
    }

    /**
     * Prueft ob der SpielerCharakter den Ausruestungsgegenstand tragen darf
     *
     * @param spielerCharakter       SpielerCharakter fuer den geprueft werden soll
     * @param ausruestungsgegenstand Ausruestungegenstand der geprueft werden soll
     * @return true falls der Charakter den Gegenstand anziehen darf
     * @author Oliver Ebert
     * @since 06.12.2023
     */
    public static boolean charakterDarfTragen(SpielerCharakter spielerCharakter, AusruestungsGegenstand ausruestungsgegenstand) {
        boolean result = false;
        if (ausruestungsgegenstand.getLevelAnforderung() <= spielerCharakter.getLevel()) {
            if (ausruestungsgegenstand instanceof Accessoire) {
                result = true;
            }
            if (spielerCharakter.getKlasse().getNutzbareAusruestung().contains(ausruestungsgegenstand.getClass().getSimpleName())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Fuegt dem Charakter Exp hinzu und behandelt Level Ups mit Attribut-/Faehigkeitspunkten
     *
     * @param charakter SpielerCharakter dessen Erfahrung erhöht werden soll
     * @param erfahrung Menge an Erfahrung die hinzugefügt werden soll
     * @author Nick
     * @since 20.11.2023
     */
    public static boolean erfahrungHinzufuegen(SpielerCharakter charakter, int erfahrung) {
        int altesLevel = charakter.getLevel();
        charakter.setErfahrungsPunkte(charakter.getErfahrungsPunkte() + erfahrung);
        if (((int) Math.floor(charakter.getErfahrungsPunkte() / 100d)) > altesLevel) {
            levelAufstieg(charakter);
            return true;
        }
        return false;
    }

    /**
     * Fuegt eine Faehigkeit zum Charakter hinzu
     *
     * @param spielerCharakter SpielerCharakter dem die Faehigkeit hinzugefuegt werden soll
     * @param faehigkeit       Faehigkeit die hinzugefuegt werden soll
     * @author Lang
     * @since 15.11.2023
     */
    public static void faehigkeitLernen(SpielerCharakter spielerCharakter, Faehigkeit faehigkeit) {
        List<Faehigkeit> returnList = new ArrayList<>(spielerCharakter.getFaehigkeiten());
        int index = returnList.indexOf(faehigkeit);
        returnList.remove(faehigkeit);
        Faehigkeit neueFaehigkeit = Faehigkeit.faehigkeitAufwerten(faehigkeit);
        returnList.add(index, neueFaehigkeit);
        spielerCharakter.setFaehigkeiten(returnList);
    }

    /**
     * Setzt die Faehigkeiten zurueck
     * Faehigkeitspunkte werden erstattet
     * Spezialisierungsfaehigkeiten werden zurueckgesetzt bleiben aber erhalten
     *
     * @param spielerCharakter SpielerCharakter Objekt
     * @author Lang
     * @since 30.11.2023
     */
    public static void faehigkeitenZuruecksetzen(SpielerCharakter spielerCharakter) {
        spielerCharakter.setOffeneFaehigkeitspunkte(spielerCharakter.getOffeneFaehigkeitspunkte() + spielerCharakter.getVerteilteFaehigkeitspunkte());
        spielerCharakter.setVerteilteFaehigkeitspunkte(0);
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(spielerCharakter.getKlasse().getBezeichnung()));
        if (spielerCharakter.getKlasse() instanceof Spezialisierung) {
            FaehigkeitFactory.spezialisierungsFaehigkeitHinzufuegen(spielerCharakter);
        }
    }

    /**
     * Erhöht die Genauigkeit um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void genauigkeitVerbessern(AttributCharakter charakter, int wert) {
        charakter.setGenauigkeit(charakter.getGenauigkeit() + wert);
    }

    /**
     * Gibt die Ausruestung des Charakters zurueck die keine Soeldneritems sind
     *
     * @param spielerCharakter SpielerCharakter dessen Ausruestung zurueckgegeben werden soll
     * @return Ausruestung die keine Soeldneritems sind
     * @author NA
     * @since 04.12.2023
     */
    public static AusruestungsGegenstand[] getGekaufteAusruestungsgegenstaendeVonCharakter(SpielerCharakter spielerCharakter) {
        List<AusruestungsGegenstand> behalten = new ArrayList<>();
        Accessoire[] accesssoires = spielerCharakter.getAccessoires();
        if (spielerCharakter.getRuestung() != null && !spielerCharakter.getRuestung().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getRuestung());
        }
        if (spielerCharakter.getWaffe() != null && !spielerCharakter.getWaffe().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getWaffe());
        }
        for (Accessoire accesssoire : accesssoires) {
            if (accesssoire != null && !accesssoire.isIstSoeldnerItem()) {
                behalten.add(accesssoire);
            }
        }
        AusruestungsGegenstand[] a = new AusruestungsGegenstand[0];
        return behalten.toArray(a);
    }

    /**
     * Aendert die Klasse des SpielerCharakters
     *
     * @param spielerCharakter SpielerCharakter der genaendert werden soll
     * @param klasse           neue Klasse die gesetzt werden soll
     * @param partyController  um an das Inventar der Party zu kommen
     * @author Lang
     * @author Ebert
     * @since 05.12.2023
     */
    public static void klasseAendern(SpielerCharakter spielerCharakter, Klasse klasse, PartyController partyController) {
        spielerCharakter.setKlasse(klasse);
        spielerCharakter.setGrafischeDarstellung(klasse.getDarstellung());
        String fabrikInput;
        if (spielerCharakter instanceof Spezialisierung) {
            fabrikInput = spielerCharakter.getClass().getSimpleName();
        }
        else {
            fabrikInput = spielerCharakter.getKlasse().getBezeichnung();
        }
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(fabrikInput));
        spielerCharakter.setOffeneFaehigkeitspunkte(spielerCharakter.getVerteilteFaehigkeitspunkte() +
                spielerCharakter.getOffeneFaehigkeitspunkte());
        pruefeAusruestungNachKlassenwechsel(spielerCharakter, partyController);
    }

    /**
     * Erhöht die MagischeAttacke um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void magischeAttackeVerbessern(AttributCharakter charakter, int wert) {
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + wert);
    }

    /**
     * Erhöht die MagischeVerteidigung um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void magischeVerteidigungVerbessern(AttributCharakter charakter, int wert) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + wert);
    }

    /**
     * Erhoeht die MaxGesundheitspunkte um angegeben Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void maxGesundheitsPunkteVerbessern(AttributCharakter spielerCharakter, int wert) {
        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + wert);
    }

    /**
     * Erhöht die MaxManapunkte um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void maxManaPunkteVerbessern(AttributCharakter charakter, int wert) {
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + wert);
    }

    /**
     * Erhöht die PhysischeAttacke um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void physischeAttackeVerbessern(AttributCharakter charakter, int wert) {
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + wert);
    }

    /**
     * Erhöht die Resistenz um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void resistenzVerbessern(AttributCharakter charakter, int wert) {
        charakter.setResistenz(charakter.getResistenz() + wert);
    }

    /**
     * Aendert die Spezialisierung und macht alte Aenderungen rueckgaengig
     * Faehigkeiten werden zurueckgesetzt
     *
     * @param spielerCharakter SpielerCharakter
     * @param klasse           String Klassenname der Spezialisierung
     * @author Lang
     * @since 16.11.2023
     */
    public static void spezialisierungAendern(SpielerCharakter spielerCharakter, String klasse) {
        if (spielerCharakter.getKlasse() instanceof Spezialisierung) {
            int[] alteKlassenAttribute = spielerCharakter.getKlasse().getDefaultAttribute();
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - alteKlassenAttribute[0]);
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - alteKlassenAttribute[1]);
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() - alteKlassenAttribute[2]);
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() - alteKlassenAttribute[3]);
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() - alteKlassenAttribute[4]);
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() - alteKlassenAttribute[5]);
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() - alteKlassenAttribute[6]);
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() - alteKlassenAttribute[7]);
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - alteKlassenAttribute[8]);
            spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() - alteKlassenAttribute[9]);
            spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() - alteKlassenAttribute[10]);
            spielerCharakter.setOffeneFaehigkeitspunkte(spielerCharakter.getOffeneFaehigkeitspunkte() + spielerCharakter.getVerteilteFaehigkeitspunkte());
            spielerCharakter.setVerteilteFaehigkeitspunkte(0);
        }
        switch (klasse) {
            case Spezialisierung.BERSERKER:
                spielerCharakter.setKlasse(new Berserker(spielerCharakter));
                break;
            case Spezialisierung.RABAUKE:
                spielerCharakter.setKlasse(new Rabauke(spielerCharakter));
                break;
            case Spezialisierung.EIS_MAGIER:
                spielerCharakter.setKlasse(new Eismagier(spielerCharakter));
                break;
            case Spezialisierung.FEUER_MAGIER:
                spielerCharakter.setKlasse(new Feuermagier(spielerCharakter));
                break;
            case Spezialisierung.SAN_MAUS:
                spielerCharakter.setKlasse(new Sanmaus(spielerCharakter));
                break;
            case Spezialisierung.PRIESTER:
                spielerCharakter.setKlasse(new Priester(spielerCharakter));
                break;
            case Spezialisierung.PALADIN:
                spielerCharakter.setKlasse(new Paladin(spielerCharakter));
                break;
            case Spezialisierung.SCHURKE:
                spielerCharakter.setKlasse(new Schurke(spielerCharakter));
                break;
            default:
                throw new IllegalArgumentException("Spezialisierung konnte nicht geändert werden: " + klasse);
        }
        spielerCharakter.setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(spielerCharakter.getKlasse().getBezeichnung()));
    }

    /**
     * Erhöht die Verteidigung um angegebenen Wert
     *
     * @param charakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert      int
     * @author Lang
     * @since 15.11.2023
     */
    public static void verteidigungVerbessern(AttributCharakter charakter, int wert) {
        charakter.setVerteidigung(charakter.getVerteidigung() + wert);
    }

    private static void levelAufstieg(SpielerCharakter charakter) {
        charakter.setLevel(charakter.getLevel() + 1);
        charakter.setOffeneAttributpunkte(charakter.getOffeneAttributpunkte() + 1);
        charakter.setOffeneFaehigkeitspunkte(charakter.getOffeneFaehigkeitspunkte() + 1);

        DebugHelper.log(charakter.getName() + " ist auf Level " + charakter.getLevel() + " gestiegen. ");
        DebugHelper.log(charakter.getName() + " hat noch " + charakter.getOffeneFaehigkeitspunkte() + " offene Faehigkeitspunkte. ");
        DebugHelper.log(charakter.getName() + " hat noch " + charakter.getOffeneAttributpunkte() + " offene Attributspunkte.");
    }

    /**
     * Ueberprüft die Ausruestung nach Wechsel der Klasse und ersetzt ggf. durch lvl 1 Gegenstaende
     *
     * @param spielerCharakter SpielerCharakter der genaendert werden soll
     * @param partyController  partyController des Charakters, um die nicht passende Ausruestung im Inventar abzulegen
     * @author Ebert
     * @since 05.12.2023
     */
    private static void pruefeAusruestungNachKlassenwechsel(SpielerCharakter spielerCharakter, PartyController partyController) {
        Ruestung ruestung = spielerCharakter.getRuestung();
        Waffe waffe = spielerCharakter.getWaffe();
        if (!charakterDarfTragen(spielerCharakter, ruestung)) {
            CharakterController.ausruestungAusziehen(spielerCharakter, ruestung, partyController.getParty().getAusruestungsgegenstandInventar());
            CharakterController.ausruestungAnlegen(spielerCharakter, AusruestungsGegenstandFactory.erstelleRuestungFuer(spielerCharakter, 1));
        }
        if (!charakterDarfTragen(spielerCharakter, waffe)) {
            CharakterController.ausruestungAusziehen(spielerCharakter, waffe, partyController.getParty().getAusruestungsgegenstandInventar());
            CharakterController.ausruestungAnlegen(spielerCharakter, AusruestungsGegenstandFactory.erstelleWaffeFuer(spielerCharakter, 1));
        }
    }

    /**
     * Gibt die Faehigkeiten des Charakters als ArrayList zurueck
     *
     * @param charakter SpielerCharakter dessen Faehigkeiten abgerufen werden sollen
     * @return ArrayList&lt;Faehigkeit&gt;
     * @author Lang
     * @since 15.11.2023
     */
    public List<Faehigkeit> faehigkeitenAbrufen(AttributCharakter charakter) {
        return charakter.getFaehigkeiten();
    }

}
