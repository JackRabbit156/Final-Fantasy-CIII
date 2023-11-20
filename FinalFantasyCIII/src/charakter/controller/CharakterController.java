package charakter.controller;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.spezialisierungen.*;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static void faehigkeitLernen(SpielerCharakter spielerCharakter, Faehigkeit faehigkeit){
        spielerCharakter.addFaehigkeit(faehigkeit);
    }

    public static void maxGesundheitsPunkteVerbessern( SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + wert);
    }

    public static void maxManaPunkteVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + wert);
    }

    public static void physischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke()+ wert);
    }

    public static void magischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + wert);
    }

    public static void genauigkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + wert);
    }

    public static void verteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + wert);
    }

    public static void magischeVerteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + wert);
    }

    public static void resistenzVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setResistenz(spielerCharakter.getResistenz() + wert);
    }

    public static void beweglichkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + wert);
    }

    public static ArrayList<Ausruestungsgegenstand> ausruestungAnzeigen(SpielerCharakter spielerCharakter){
        ArrayList<Ausruestungsgegenstand> ausruestungsgegenstands = new ArrayList<>();
        /* Fehlende Implementation
        ausrüstungsgegenstands.add(spielerCharakter.getWaffe());
        ausrüstungsgegenstands.add(spielerCharakter.getRuestung());
        ausrüstungsgegenstands.add(spielerCharakter.getAccesssoires());*/
        return ausruestungsgegenstands;
    }

    public static void ausruestungAusziehen(SpielerCharakter spielerCharakter, Ausruestungsgegenstand ausruestungsgegenstand){
        //TODO implement wenn Inventar fertig
    }

    public static void ausruestungAnlegen(SpielerCharakter spielerCharakter, Ausruestungsgegenstand ausruestungsgegenstand){
        //TODO implement wenn Inventar fertig
    }

    public static void charakterInventarAnzeigen(SpielerCharakter spielerCharakter){
        //TODO System.out.println(spielerCharakter.getWaffe().toString());
        //TODO System.out.println(spielerCharakter.getRuestung().toString());
        System.out.println(Arrays.toString(spielerCharakter.getAccessoires()));
    }

    public static void statsAnzeigen(SpielerCharakter spielerCharakter){
        System.out.println(spielerCharakter.getName());
        System.out.println(spielerCharakter.getMaxGesundheitsPunkte());
        System.out.println(spielerCharakter.getMaxManaPunkte());
        System.out.println(spielerCharakter.getLevel());
        System.out.println(spielerCharakter.getPhysischeAttacke());
        System.out.println(spielerCharakter.getMagischeAttacke());
        System.out.println(spielerCharakter.getVerteidigung());
        System.out.println(spielerCharakter.getMagischeVerteidigung());
        System.out.println(spielerCharakter.getResistenz());
        System.out.println(spielerCharakter.getBeweglichkeit());
    }

    public ArrayList<Faehigkeit> faehigkeitenAbrufen(SpielerCharakter spielerCharakter){
        return spielerCharakter.getFaehigkeiten();
    }

    public static void erfahrungHinzufuegen(SpielerCharakter charakter, int erfahrung){
        //TODO UMSETZEN LEVEL SYSTEM UND SKILL-/ATTRIBUTSPUNKTE
    }
}
