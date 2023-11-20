package charakter.controller;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.spezialisierungen.*;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import party.AusruestungsgegenstandInventar;
import java.util.ArrayList;


public class CharakterController {

    public static Ausruestungsgegenstand[] getGekaufteAusruestungsgegenstaendeVonCharakter(SpielerCharakter spielerCharakter){
        ArrayList<Ausruestungsgegenstand> behalten = new ArrayList<>();
        Accessoire[] accesssoires = spielerCharakter.getAccessoires();
        if (spielerCharakter.getRuestung() != null && !spielerCharakter.getRuestung().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getRuestung());
        }
        if (spielerCharakter.getWaffe() != null && !spielerCharakter.getWaffe().isIstSoeldnerItem()) {
            behalten.add(spielerCharakter.getWaffe());
        }
        for (int i = 0; i < accesssoires.length; i++) {
            if (accesssoires[i] != null && !accesssoires[i].isIstSoeldnerItem() ) {
                behalten.add(accesssoires[i]);
            }
        }
        Ausruestungsgegenstand[] a = new Ausruestungsgegenstand[0];
        return behalten.toArray(a);
    }

    public static void klasseAendern(SpielerCharakter spielerCharakter, Klasse klasse){
        spielerCharakter.setKlasse(klasse);
    }

    /**
     * Aendert die Spezialisierung und macht alte Aenderungen rueckgaengig
     * @param spielerCharakter SpielerCharakter
     * @param klasse String
     *
     * @since 16.11.2023
     * @author Lang
     */
    public static void spezialisierungAendern(SpielerCharakter spielerCharakter, String klasse){

        if (spielerCharakter.getKlasse() instanceof Spezialisierung){
            Integer[] vorzeichenaenderung = ((Spezialisierung) spielerCharakter.getKlasse()).getAttribute();
            for (int i = 0; i < vorzeichenaenderung.length; i++) {
                vorzeichenaenderung[i] = vorzeichenaenderung[i]*-1;
            }
            spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + vorzeichenaenderung[0]);
            spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte()+ vorzeichenaenderung[1]);
            spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + vorzeichenaenderung[2]);
            spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + vorzeichenaenderung[3]);
            spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + vorzeichenaenderung[4]);
            spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + vorzeichenaenderung[5]);
            spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + vorzeichenaenderung[6]);
            spielerCharakter.setResistenz(spielerCharakter.getResistenz() + vorzeichenaenderung[7]);
            spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + vorzeichenaenderung[8]);
            spielerCharakter.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + vorzeichenaenderung[9]);
            spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + vorzeichenaenderung[10]);
        }
        if (klasse.equals("Beserker")){
            spielerCharakter.setKlasse(new Beserker(spielerCharakter));
        } else if (klasse.equals("Rabauke")){
            spielerCharakter.setKlasse(new Rabauke(spielerCharakter));
        } else if (klasse.equals("Eismagier")){
            spielerCharakter.setKlasse(new Eismagier(spielerCharakter));
        } else if (klasse.equals("Feuermagier")){
            spielerCharakter.setKlasse(new Feuermagier(spielerCharakter));
        } else if (klasse.equals("Sanmaus")){
            spielerCharakter.setKlasse(new Sanmaus(spielerCharakter));
        } else if (klasse.equals("Priester")){
            spielerCharakter.setKlasse(new Priester(spielerCharakter));
        } else if (klasse.equals("Paladin")){
            spielerCharakter.setKlasse(new Paladin(spielerCharakter));
        } else if (klasse.equals("Schurke")){
            spielerCharakter.setKlasse(new Schurke(spielerCharakter));
        }


    }

    /**
     * Fuegt eine Faehigkeit zum Charakter hinzu
     * @param spielerCharakter
     * @param faehigkeit
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void faehigkeitLernen(SpielerCharakter spielerCharakter, Faehigkeit faehigkeit){
        spielerCharakter.addFaehigkeit(faehigkeit);
    }


    /**
     * Erhoeht die MaxGesundheitspunkte um angegeben Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void maxGesundheitsPunkteVerbessern( SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + wert);
    }

    /**
     * Erhöht die MaxManapunkte um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void maxManaPunkteVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + wert);
    }

    /**
     * Erhöht die PhysischeAttacke um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void physischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke()+ wert);
    }

    /**
     * Erhöht die MagischeAttacke um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void magischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + wert);
    }

    /**
     * Erhöht die Genauigkeit um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void genauigkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + wert);
    }

    /**
     * Erhöht die Verteidigung um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void verteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + wert);
    }

    /**
     * Erhöht die MagischeVerteidigung um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void magischeVerteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + wert);
    }

    /**
     * Erhöht die Resistenz um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public static void resistenzVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setResistenz(spielerCharakter.getResistenz() + wert);
    }

    /**
     * Erhöht die Beweglichkeit um angegebenen Wert
     * @param spielerCharakter
     * @param wert int
     *
     * @since 15.11.2023
     * @author Lang
     */
    public void beweglichkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + wert);
    }

    /**
     * Gibt die Angelegte Ausruestung als ArrayList zurueck
     * @param spielerCharakter
     * @return ArrayList<Ausruestungsgegenstand>
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static ArrayList<Ausruestungsgegenstand> ausruestungAnzeigen(SpielerCharakter spielerCharakter){
        ArrayList<Ausruestungsgegenstand> ausruestungsgegenstands = new ArrayList<>();
        ausruestungsgegenstands.add(spielerCharakter.getWaffe());
        ausruestungsgegenstands.add(spielerCharakter.getRuestung());
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[0]);
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[1]);
        ausruestungsgegenstands.add(spielerCharakter.getAccessoires()[2]);
        return ausruestungsgegenstands;
    }

    /**
     * Zieht dem Charakter dem uebergenenen Ausruestungsgegenstand aus und fuegt diesen dem Inventar hinzu
     * @param spielerCharakter
     * @param ausruestungsgegenstand
     * @param ausruestungsgegenstandInventar
     *
     * @since 20.11.2023
     * @author Lang
     */
    public static void ausruestungAusziehen(SpielerCharakter spielerCharakter,
                                            Ausruestungsgegenstand ausruestungsgegenstand,
                                            AusruestungsgegenstandInventar ausruestungsgegenstandInventar){
        ausruestungsgegenstandInventar.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        if (ausruestungsgegenstand instanceof Waffe){
            spielerCharakter.setWaffe(null);
        } else if (ausruestungsgegenstand instanceof Ruestung){
            spielerCharakter.setRuestung(null);
        } else if (ausruestungsgegenstand instanceof Accessoire){
            for (int i = 0; i < spielerCharakter.getAccessoires().length; i++) {
                if (spielerCharakter.getAccessoire(i).getName().equals(ausruestungsgegenstand.getName())){
                    spielerCharakter.setAccessoire(null, i);
                }
            }
        }
    }

    /**
     * Legt dem Charakter den Ausruestungsgegenstand an und entfernt ihn aus dem Inventar
     * @param spielerCharakter
     * @param ausruestungsgegenstand
     * @param ausruestungsgegenstandInventar
     *
     * @since 20.11.2023
     * @author Lang
     */
    public static void ausruestungAnlegen(SpielerCharakter spielerCharakter,
                                          Ausruestungsgegenstand ausruestungsgegenstand,
                                          AusruestungsgegenstandInventar ausruestungsgegenstandInventar){
        ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
        if (ausruestungsgegenstand instanceof Waffe){
            spielerCharakter.setWaffe((Waffe) ausruestungsgegenstand);
        } else if (ausruestungsgegenstand instanceof Ruestung){
            spielerCharakter.setRuestung((Ruestung) ausruestungsgegenstand);
        } else if (ausruestungsgegenstand instanceof Accessoire){
            for (int i = 0; i < spielerCharakter.getAccessoires().length; i++) {
                if (spielerCharakter.getAccessoire(i) != null){
                    spielerCharakter.setAccessoire((Accessoire) ausruestungsgegenstand, i);
                    break;
                }
            }
        }
    }

    /**
     * String ausgabe der Angelegten Ausrüstung
     * @param spielerCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static void charakterInventarAnzeigen(SpielerCharakter spielerCharakter){
        System.out.printf("Waffenname: %s%nPhysische Attacke: %s%nMagische Attacke: %s%nSoeldnerItem: %s%n",
                spielerCharakter.getWaffe().getName(), spielerCharakter.getWaffe().getpAtk(),
                spielerCharakter.getWaffe().getmAtk(), spielerCharakter.getWaffe().isIstSoeldnerItem());
        System.out.println("------------------------------------");
        System.out.printf("Ruestungsname: %s%nVerteidigung: %s%nMagische Verteidigung: %s%nSoeldnerItem: %s%n",
                spielerCharakter.getRuestung().getName(), spielerCharakter.getRuestung().getpVtg(),
                spielerCharakter.getRuestung().getmVtg(), spielerCharakter.getWaffe().isIstSoeldnerItem());
        System.out.println("------------------------------------");
        for (Accessoire accessoire : spielerCharakter.getAccessoires()) {
            System.out.printf("Accessoirename: %s%nSoeldnerItem: %s%n",
                    accessoire.getName(), spielerCharakter.getWaffe().isIstSoeldnerItem());
        }
    }

    /**
     * String Ausgabe der Stats
     * @param spielerCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static void statsAnzeigen(SpielerCharakter spielerCharakter){
        System.out.println("Name: " + spielerCharakter.getName());
        System.out.println("Maximale Gesundheit: " + spielerCharakter.getMaxGesundheitsPunkte());
        System.out.println("Maximale Manapunkte: " + spielerCharakter.getMaxManaPunkte());
        System.out.println("Aktuelles Level: " + spielerCharakter.getLevel());
        System.out.println("Physische Attacke: " + spielerCharakter.getPhysischeAttacke());
        System.out.println("Magische Attacke: " + spielerCharakter.getMagischeAttacke());
        System.out.println("Verteidigung: " + spielerCharakter.getVerteidigung());
        System.out.println("Magische Verteidigung: " + spielerCharakter.getMagischeVerteidigung());
        System.out.println("Resistenz: " + spielerCharakter.getResistenz());
        System.out.println("Beweglichkeit: " + spielerCharakter.getBeweglichkeit());
    }

    /**
     * Gibt die Faehigkeiten des Charakters als ArrayList zurueck
     * @param spielerCharakter
     * @return ArrayList<Faehigkeit>
     *
     * @since 15.11.2023
     * @author Lang
     */
    public ArrayList<Faehigkeit> faehigkeitenAbrufen(SpielerCharakter spielerCharakter){
        return spielerCharakter.getFaehigkeiten();
    }

    public static void erfahrungHinzufuegen(SpielerCharakter charakter, int erfahrung){
        //TODO UMSETZEN LEVEL SYSTEM UND SKILL-/ATTRIBUTSPUNKTE
    }
}
