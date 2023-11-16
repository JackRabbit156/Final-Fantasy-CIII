package charakter.controller;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.Klasse;
import charakter.model.klassen.spezialisierungen.Spezialisierung;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import java.util.ArrayList;
import java.util.Arrays;

public class CharakterController {

    public Ausruestungsgegenstand[] getGekaufteAusruestungsgegenstaendeVonCharakter(SpielerCharakter spielerCharakter){
        //TODO implement
        return null;
    }

    public void klasseAendern(SpielerCharakter spielerCharakter, Klasse klasse){
        spielerCharakter.setKlasse(klasse);
    }

    public void spezialisierungAendern(SpielerCharakter spielerCharakter, Spezialisierung spezialisierung){
        spielerCharakter.setSpezialisierung(spezialisierung);
    }

    public void faehigkeitLernen(SpielerCharakter spielerCharakter, Faehigkeit faehigkeit){
        spielerCharakter.addFaehigkeit(faehigkeit);
    }

    public void maxGesundheitsPunkteVerbessern( SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + wert);
    }

    public void maxManaPunkteVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMaxManaPunkte(spielerCharakter.getMaxManaPunkte() + wert);
    }

    public void physischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke()+ wert);
    }

    public void magischeAttackeVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + wert);
    }

    public void genauigkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + wert);
    }

    public void verteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + wert);
    }

    public void magischeVerteidigungVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + wert);
    }

    public void resistenzVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setResistenz(spielerCharakter.getResistenz() + wert);
    }

    public void beweglichkeitVerbessern(SpielerCharakter spielerCharakter, int wert){
        spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + wert);
    }

    public ArrayList<Ausruestungsgegenstand> ausruestungAnzeigen(SpielerCharakter spielerCharakter){
        ArrayList<Ausruestungsgegenstand> ausrüstungsgegenstands = new ArrayList<>();
        /* Fehlende Implementation
        ausrüstungsgegenstands.add(spielerCharakter.getWaffe());
        ausrüstungsgegenstands.add(spielerCharakter.getRuestung());
        ausrüstungsgegenstands.add(spielerCharakter.getAccesssoires());*/
        return ausrüstungsgegenstands;
    }

    public void ausruestungAusziehen(SpielerCharakter spielerCharakter, Ausruestungsgegenstand ausrüstungsgegenstand){
        //TODO implement wenn Inventar fertig
    }

    public void ausruestungAnlegen(SpielerCharakter spielerCharakter, Ausruestungsgegenstand ausrüstungsgegenstand){
        //TODO implement wenn Inventar fertig
    }

    public void charakterInventarAnzeigen(SpielerCharakter spielerCharakter){
        System.out.println(spielerCharakter.getWaffe().toString());
        System.out.println(spielerCharakter.getRuestung().toString());
        System.out.println(Arrays.toString(spielerCharakter.getAccesssoires()));
    }

    public void statsAnzeigen(SpielerCharakter spielerCharakter){
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
