package charakter.controller;

import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.spezialisierungen.*;
import gegenstand.Ausruestungsgegenstand.AusruestungsgegenstandFabrik;
import party.PartyController;
import trainer.faehigkeiten.Faehigkeit;
import trainer.faehigkeiten.FaehigkeitFabrik;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import party.AusruestungsgegenstandInventar;

import java.util.ArrayList;


public class CharakterController {

    /**
     * Gibt die Ausruestung des Charakters zurueck die keine Soeldneritems sind
     * @param spielerCharakter SpielerCharakter dessen Ausruestung zurueckgegeben werden soll
     * @return Ausruestung die keine Soeldneritems sind
     *
     * @author NA
     * @since 04.12.2023
     */
    public static Ausruestungsgegenstand[] getGekaufteAusruestungsgegenstaendeVonCharakter(SpielerCharakter spielerCharakter) {
        ArrayList<Ausruestungsgegenstand> behalten = new ArrayList<>();
        Accessoire[] accesssoires = spielerCharakter.getAccessoires();
        if (spielerCharakter.getRuestung() != null && !spielerCharakter.getRuestung().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getRuestung());
        }
        if (spielerCharakter.getWaffe() != null && !spielerCharakter.getWaffe().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getWaffe());
        }
        for (int i = 0; i < accesssoires.length; i++) {
            if (accesssoires[i] != null && !accesssoires[i].isIstSoeldnerItem()) {
                behalten.add(accesssoires[i]);
            }
        }
        Ausruestungsgegenstand[] a = new Ausruestungsgegenstand[0];
        return behalten.toArray(a);
    }

    /**
     * Aendert die Klasse des SpielerCharakters
     * @param spielerCharakter SpielerCharakter der genaendert werden soll
     * @param klasse neue Klasse die gesetzt werden soll
     * @param partyController um an das Inventar der Party zu kommen
     *
     * @author Lang
     * @author Ebert
     * @since 05.12.2023
     */
    public static void klasseAendern(SpielerCharakter spielerCharakter, Klasse klasse, PartyController partyController) {
        spielerCharakter.setKlasse(klasse);
        String fabrikInput = "";
        spielerCharakter.setGrafischeDarstellung(klasse.getDarstellung());
        if (spielerCharakter instanceof Spezialisierung) {
            fabrikInput = spielerCharakter.getClass().getSimpleName();
        } else {
            fabrikInput = spielerCharakter.getKlasse().getBezeichnung();
        }
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(
                fabrikInput));
        spielerCharakter.setOffeneFaehigkeitspunkte(spielerCharakter.getVerteilteFaehigkeitspunkte() + spielerCharakter.getOffeneFaehigkeitspunkte());
        pruefeAusruestungNachKlassenwechsel(spielerCharakter, partyController);
    }

    /**
     * Ueberprüft die Ausruestung nach Wechsel der Klasse und ersetzt ggf. durch lvl 1 Gegenstaende
     *
     * @param spielerCharakter SpielerCharakter der genaendert werden soll
     * @param partyController partyController des Charakters, um die nicht passende Ausruestung im Inventar abzulegen
     *
     * @author Ebert
     * @since 05.12.2023
     */
    private static void pruefeAusruestungNachKlassenwechsel(SpielerCharakter spielerCharakter, PartyController partyController){
        Ruestung ruestung = spielerCharakter.getRuestung();
        Waffe waffe = spielerCharakter.getWaffe();
        if(!charakterDarfTragen(spielerCharakter, ruestung)){
            CharakterController.ausruestungAusziehen(spielerCharakter, ruestung, partyController.getParty().getAusruestungsgegenstandInventar());
            CharakterController.ausruestungAnlegen(spielerCharakter, AusruestungsgegenstandFabrik.erstelleRuestungFuer(spielerCharakter, 1), new AusruestungsgegenstandInventar());
        }
        if(!charakterDarfTragen(spielerCharakter, waffe)){
            CharakterController.ausruestungAusziehen(spielerCharakter, waffe, partyController.getParty().getAusruestungsgegenstandInventar());
            CharakterController.ausruestungAnlegen(spielerCharakter, AusruestungsgegenstandFabrik.erstelleWaffeFuer(spielerCharakter, 1), new AusruestungsgegenstandInventar());
        }
    }

    /**
     * Aendert die Spezialisierung und macht alte Aenderungen rueckgaengig
     * Faehigkeiten werden zurueckgesetzt
     *
     * @param spielerCharakter SpielerCharakter
     * @param klasse           String Klassenname der Spezialisierung
     *
     * @author Lang
     * @since 16.11.2023
     */
    public static void spezialisierungAendern(SpielerCharakter spielerCharakter, String klasse) {
        if (spielerCharakter.getKlasse() instanceof Spezialisierung) {
            Integer[] vorzeichenaenderung = ((Spezialisierung) spielerCharakter.getKlasse()).getAttribute();
            for (int i = 0; i < vorzeichenaenderung.length; i++) {
                vorzeichenaenderung[i] = vorzeichenaenderung[i] * -1;
            }
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + vorzeichenaenderung[0]);
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + vorzeichenaenderung[1]);
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + vorzeichenaenderung[2]);
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + vorzeichenaenderung[3]);
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + vorzeichenaenderung[4]);
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + vorzeichenaenderung[5]);
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + vorzeichenaenderung[6]);
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() + vorzeichenaenderung[7]);
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + vorzeichenaenderung[8]);
            spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + vorzeichenaenderung[9]);
            spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + vorzeichenaenderung[10]);
            spielerCharakter.setOffeneFaehigkeitspunkte(spielerCharakter.getOffeneFaehigkeitspunkte() + spielerCharakter.getVerteilteFaehigkeitspunkte());
            spielerCharakter.setVerteilteFaehigkeitspunkte(0);
            spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(spielerCharakter.getKlasse().getBezeichnung()));
        }
        if (klasse.equals("Berserker")) {
            spielerCharakter.setKlasse(new Berserker(spielerCharakter));
        } else if (klasse.equals("Rabauke")) {
            spielerCharakter.setKlasse(new Rabauke(spielerCharakter));
        } else if (klasse.equals("Eismagier")) {
            spielerCharakter.setKlasse(new Eismagier(spielerCharakter));
        } else if (klasse.equals("Feuermagier")) {
            spielerCharakter.setKlasse(new Feuermagier(spielerCharakter));
        } else if (klasse.equals("Sanmaus")) {
            spielerCharakter.setKlasse(new Sanmaus(spielerCharakter));
        } else if (klasse.equals("Priester")) {
            spielerCharakter.setKlasse(new Priester(spielerCharakter));
        } else if (klasse.equals("Paladin")) {
            spielerCharakter.setKlasse(new Paladin(spielerCharakter));
        } else if (klasse.equals("Schurke")) {
            spielerCharakter.setKlasse(new Schurke(spielerCharakter));
        }


    }

    /**
     * Fuegt eine Faehigkeit zum Charakter hinzu
     *
     * @param spielerCharakter SpielerCharakter dem die Faehigkeit hinzugefuegt werden soll
     * @param faehigkeit Faehigkeit die hinzugefuegt werden soll
     * @author Lang
     * @since 15.11.2023
     */
    public static void faehigkeitLernen(SpielerCharakter spielerCharakter, Faehigkeit faehigkeit) {
        ArrayList<Faehigkeit> returnList = new ArrayList<>(spielerCharakter.getFaehigkeiten());
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
        spielerCharakter.setFaehigkeiten(FaehigkeitFabrik.erstelleFaehigkeitFuer(spielerCharakter.getKlasse().getBezeichnung()));
        if (spielerCharakter.getKlasse() instanceof Spezialisierung) {
            FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(spielerCharakter);
        }
    }


    /**
     * Erhoeht die MaxGesundheitspunkte um angegeben Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void maxGesundheitsPunkteVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + wert);
    }

    /**
     * Erhöht die MaxManapunkte um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void maxManaPunkteVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + wert);
    }

    /**
     * Erhöht die PhysischeAttacke um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void physischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + wert);
    }

    /**
     * Erhöht die MagischeAttacke um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void magischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + wert);
    }

    /**
     * Erhöht die Genauigkeit um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void genauigkeitVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + wert);
    }

    /**
     * Erhöht die Verteidigung um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void verteidigungVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + wert);
    }

    /**
     * Erhöht die MagischeVerteidigung um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void magischeVerteidigungVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + wert);
    }

    /**
     * Erhöht die Resistenz um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public static void resistenzVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setResistenz(spielerCharakter.getResistenz() + wert);
    }

    /**
     * Gibt die Angelegte Ausruestung als ArrayList zurueck
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @return ArrayList<Ausruestungsgegenstand>
     * @author Lang
     * @since 18.11.2023
     */
    public static ArrayList<Ausruestungsgegenstand> ausruestungAnzeigen(SpielerCharakter spielerCharakter) {
        ArrayList<Ausruestungsgegenstand> ausruestungsgegenstands = new ArrayList<>();
        ausruestungsgegenstands.add(spielerCharakter.getWaffe());
        ausruestungsgegenstands.add(spielerCharakter.getRuestung());
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[0]);
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[1]);
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[2]);
        return ausruestungsgegenstands;
    }

    /**
     * Zieht dem Charakter dem uebergenenen Ausruestungsgegenstand aus und fuegt diesen dem Inventar hinzu.
     * Sollte der Gegenstand ein SöldnerItem sein, wird dieser beim ablegen vernichtet.
     *
     * @param spielerCharakter WER: SpielerCharakter für den die Ausrüstung abgelegt wird
     * @param ausruestungsgegenstand WAS: Ausrüstungsgegenstand, der abgelegt werden soll
     * @param ausruestungsgegenstandInventar WOHIN: Inventar wo der Ausrüstungsgegenstand abgelegt wird
     * @author Lang
     * @author OF Kretschmer (CharakterAttribute mit GegenstandsAttributen verrechnen verrechnen)
     * @author OLt Ebert - Verrechnung der Waffen/Ruestungsattributen
     * @since 05.12.2023
     */
    public static void ausruestungAusziehen(SpielerCharakter spielerCharakter,
                                            Ausruestungsgegenstand ausruestungsgegenstand,
                                            AusruestungsgegenstandInventar ausruestungsgegenstandInventar) {
        if (!ausruestungsgegenstand.isIstSoeldnerItem()) {
            ausruestungsgegenstandInventar.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        } else {
            //TODO: GUI Ausgabe fuer Soeldneritem
            System.out.println("Ausruestungsgegenstand war Soeldner-Item und wurde entfernt");
        }
        if (ausruestungsgegenstand instanceof Waffe) {
            Waffe waffe = (Waffe) ausruestungsgegenstand;
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() - waffe.getAttacke());
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() - waffe.getMagischeAttacke());
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() - waffe.getGenauigkeit());
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - waffe.getBeweglichkeit());
            spielerCharakter.setWaffe(null);
        } else if (ausruestungsgegenstand instanceof Ruestung) {
            Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() - ruestung.getVerteidigung());
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() - ruestung.getMagischeVerteidigung());
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() - ruestung.getResistenz());
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - ruestung.getMaxGesundheitsPunkte());
            if ((spielerCharakter.getGesundheitsPunkte()-ruestung.getMaxGesundheitsPunkte()) <= 0){
                spielerCharakter.setGesundheitsPunkte(1);
            }else {
                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte()-ruestung.getMaxGesundheitsPunkte());
            }
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - ruestung.getMaxManaPunkte());
            if ((spielerCharakter.getManaPunkte()-ruestung.getMaxManaPunkte()) <= 0){
                spielerCharakter.setManaPunkte(1);
            }else {
                spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte()-ruestung.getMaxManaPunkte());
            }
            spielerCharakter.setRuestung(null);
        } else if (ausruestungsgegenstand instanceof Accessoire) {
                Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < spielerCharakter.getAccessoires().length; i++) {
                if (spielerCharakter.getAccessoire(i) != null) {
                    if (spielerCharakter.getAccessoire(i).getName().equals(accessoire.getName())) {
                        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        if ((spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte()) <= 0) {
                            spielerCharakter.setGesundheitsPunkte(1);
                        } else {
                            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        }
                        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - (accessoire).getMaxManaPunkte());
                        if ((spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte()) <= 0) {
                            spielerCharakter.setManaPunkte(1);
                        } else {
                            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte());
                        }
                        spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() - accessoire.getGesundheitsRegeneration());
                        spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() - accessoire.getManaRegeneration());
                        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - accessoire.getBeweglichkeit());
                        spielerCharakter.setAccessoire(null, i);
                    }
                }
            }
        }
    }

    /**
     * Zieht auch SöldnerItems ins Inventar aus - nur für Trainer
     * @param spielerCharakter
     * @param ausruestungsgegenstand
     * @param ausruestungsgegenstandInventar
     * @author Olli, Nick
     * @since 05.12.2023
     */
    public static void ausruestungAusziehenIgnoriereSoeldnerItem(SpielerCharakter spielerCharakter,
                                            Ausruestungsgegenstand ausruestungsgegenstand,
                                            AusruestungsgegenstandInventar ausruestungsgegenstandInventar) {
            ausruestungsgegenstandInventar.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        if (ausruestungsgegenstand instanceof Waffe) {
            Waffe waffe = (Waffe) ausruestungsgegenstand;
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() - waffe.getAttacke());
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() - waffe.getMagischeAttacke());
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() - waffe.getGenauigkeit());
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - waffe.getBeweglichkeit());
            spielerCharakter.setWaffe(null);
        } else if (ausruestungsgegenstand instanceof Ruestung) {
            Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() - ruestung.getVerteidigung());
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() - ruestung.getMagischeVerteidigung());
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() - ruestung.getResistenz());
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - ruestung.getMaxGesundheitsPunkte());
            if ((spielerCharakter.getGesundheitsPunkte()-ruestung.getMaxGesundheitsPunkte()) <= 0){
                spielerCharakter.setGesundheitsPunkte(1);
            }else {
                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte()-ruestung.getMaxGesundheitsPunkte());
            }
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - ruestung.getMaxManaPunkte());
            if ((spielerCharakter.getManaPunkte()-ruestung.getMaxManaPunkte()) <= 0){
                spielerCharakter.setManaPunkte(1);
            }else {
                spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte()-ruestung.getMaxManaPunkte());
            }
            spielerCharakter.setRuestung(null);
        } else if (ausruestungsgegenstand instanceof Accessoire) {
            Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < spielerCharakter.getAccessoires().length; i++) {
                if (spielerCharakter.getAccessoire(i) != null) {
                    if (spielerCharakter.getAccessoire(i).getName().equals(accessoire.getName())) {
                        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        if ((spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte()) <= 0) {
                            spielerCharakter.setGesundheitsPunkte(1);
                        } else {
                            spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() - accessoire.getMaxGesundheitsPunkte());
                        }
                        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() - (accessoire).getMaxManaPunkte());
                        if ((spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte()) <= 0) {
                            spielerCharakter.setManaPunkte(1);
                        } else {
                            spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() - accessoire.getMaxManaPunkte());
                        }
                        spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() - accessoire.getGesundheitsRegeneration());
                        spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() - accessoire.getManaRegeneration());
                        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() - accessoire.getBeweglichkeit());
                        spielerCharakter.setAccessoire(null, i);
                    }
                }
            }
        }
    }

    /**
     * Legt dem Charakter den Ausruestungsgegenstand an und entfernt ihn aus dem Inventar, wenn er diesen Gegenstand tragen darf.
     * Dabei werden eventuelle Boni auf den Charakter gerechnet
     *
     * @param spielerCharakter SpielerCharakter, der den Ausruestungsgegenstand tragen soll
     * @param ausruestungsgegenstand Ausruestungsgegenstand, der angelegt werden soll
     * @param ausruestungsgegenstandInventar Inventar aus dem der Gegenstand kommt
     * @author Lang
     * @author OF Kretschmer (GegenstandsAttribute mit CharakterAttributen verrechnen hinzugefügt)
     * @author OLt Ebert (Trageeinschränkung und CharakterAttribute/Boni für Waffen/Rüstungen)
     * @since 05.12.2023
     */

    public static void ausruestungAnlegen(SpielerCharakter spielerCharakter,
                                          Ausruestungsgegenstand ausruestungsgegenstand,
                                          AusruestungsgegenstandInventar ausruestungsgegenstandInventar) {
        //Accessoire dürfen von allen getragen werden
        if (ausruestungsgegenstand instanceof Accessoire) {
            Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < 3; i++) {
                if (spielerCharakter.getAccessoire(i) == null) {
                    ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
                    spielerCharakter.setAccessoire(accessoire, i);

                    if (spielerCharakter.getGesundheitsPunkte() == spielerCharakter.getMaxGesundheitsPunkte()){
                        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                    } if (spielerCharakter.getManaPunkte() == spielerCharakter.getMaxManaPunkte()){
                        spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + accessoire.getMaxManaPunkte());
                    }
                    spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                    spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + accessoire.getMaxManaPunkte());

                    spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + accessoire.getGesundheitsRegeneration());
                    spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + accessoire.getManaRegeneration());
                    spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() +accessoire.getBeweglichkeit());
                    break;
                }
            }
        //Waffen oder Ruestungen haben Trage-Einschraenkungen
        } else {
            if (charakterDarfTragen(spielerCharakter, ausruestungsgegenstand)) {
                //Charakter darf tragen
                ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);

                //Waffe
                if (ausruestungsgegenstand instanceof Waffe) {
                    Waffe waffe = (Waffe) ausruestungsgegenstand;
                    spielerCharakter.setWaffe(waffe);
                    spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + waffe.getAttacke());
                    spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + waffe.getMagischeAttacke());
                    spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + waffe.getGenauigkeit());
                    spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + waffe.getBeweglichkeit());

                //Ruestung
                } else if (ausruestungsgegenstand instanceof Ruestung) {
                    Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
                    spielerCharakter.setRuestung(ruestung);
                    spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + ruestung.getMagischeVerteidigung());
                    spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + ruestung.getVerteidigung());
                    spielerCharakter.setResistenz(spielerCharakter.getResistenz() + ruestung.getResistenz());
                    if (spielerCharakter.getGesundheitsPunkte() == spielerCharakter.getMaxGesundheitsPunkte()){
                        spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte()+ruestung.getMaxGesundheitsPunkte());
                    } if (spielerCharakter.getManaPunkte() == spielerCharakter.getMaxManaPunkte()){
                        spielerCharakter.setManaPunkte(spielerCharakter.getManaPunkte() + ruestung.getMaxManaPunkte());
                    }
                    spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + ruestung.getMaxGesundheitsPunkte());
                    spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + ruestung.getMaxManaPunkte());
                }
            } else {
                //Charakter darf nicht tragen
                //TODO: GUI Ausgabe für nicht passende Ausrüstung
                System.out.println(spielerCharakter.getName() + " kann diese Ausruestung nicht tragen!");
            }
        }
    }

    /**
     * Prueft ob der SpielerCharakter den Ausruestungsgegenstand tragen darf
     * @param spielerCharakter SpielerCharakter fuer den geprueft werden soll
     * @param ausruestungsgegenstand Ausruestungegenstand der geprueft werden soll
     * @return true falls der Charakter den Gegenstand anziehen darf
     *
     * @author NA
     * @since 04.12.2023
     */
    private static boolean charakterDarfTragen(SpielerCharakter spielerCharakter, Ausruestungsgegenstand ausruestungsgegenstand) {
        boolean returnBoolean = false;
        String simpleName = ausruestungsgegenstand.getClass().getSimpleName();
        if (spielerCharakter.getKlasse().getNutzbareAusruestung().contains(ausruestungsgegenstand.getClass().getSimpleName())) {
            returnBoolean = true;
        }
        Klasse klasse = spielerCharakter.getKlasse();
        return returnBoolean;
    }


    /**
     * Fuegt dem Charakter Exp hinzu und behandelt Level Ups mit Attribut-/Faehigkeitspunkten
     *
     * @param charakter SpielerCharakter dessen Erfahrung erhöht werden soll
     * @param erfahrung Menge an Erfahrung die hinzugefügt werden soll
     * @author Nick
     * @since 20.11.2023
     */
    public static void erfahrungHinzufuegen(SpielerCharakter charakter, int erfahrung) {
        int altesLevel = charakter.getLevel();
        charakter.setErfahrungsPunkte(charakter.getErfahrungsPunkte() + erfahrung);
        if (((int) Math.floor(charakter.getErfahrungsPunkte() / 100d)) > altesLevel) {
            CharakterController.levelAufstieg(charakter);
        }
    }

    private static void levelAufstieg(SpielerCharakter charakter) {
        charakter.setLevel(charakter.getLevel() + 1);
        charakter.setOffeneAttributpunkte(charakter.getOffeneAttributpunkte() + 1);
        charakter.setOffeneFaehigkeitspunkte(charakter.getOffeneFaehigkeitspunkte() + 1);
        System.out.println(charakter.getName() + " ist auf Level " + charakter.getLevel() + " gestiegen.");
        System.out.println(charakter.getName() + " hat noch " + charakter.getOffeneFaehigkeitspunkte() + " offene Faehigkeitspunkte!");
        System.out.println(charakter.getName() + " hat noch " + charakter.getOffeneAttributpunkte() + " offene Attributspunkte!");
    }

    /**
     * Erhöht die Beweglichkeit um angegebenen Wert
     *
     * @param spielerCharakter SpielerCharakter dessen Wert angepasst werden soll
     * @param wert             int
     * @author Lang
     * @since 15.11.2023
     */
    public void beweglichkeitVerbessern(SpielerCharakter spielerCharakter, int wert) {
        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + wert);
    }

    /**
     * Gibt die Faehigkeiten des Charakters als ArrayList zurueck
     *
     * @param spielerCharakter SpielerCharakter dessen Faehigkeiten abgerufen werden sollen
     * @return ArrayList<Faehigkeit>
     * @author Lang
     * @since 15.11.2023
     */
    public ArrayList<Faehigkeit> faehigkeitenAbrufen(SpielerCharakter spielerCharakter) {
        return spielerCharakter.getFaehigkeiten();
    }
}
