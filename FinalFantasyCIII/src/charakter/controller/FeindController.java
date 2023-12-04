package charakter.controller;

import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.gegnertypen.*;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import hilfsklassen.ZufallsZahlenGenerator;
import party.PartyController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class FeindController {
    
    private static final Feind[] feindListeGesamt = new Feind[16];
    private final Random rnd = new Random();

    /**
     * Generiert Feinde auf Basis Partylevel
     * Groeße der Feindgruppe zufaellig zwischen 1 und 4
     * Je weniger Gegner, desto höher deren Level
     * Erstellt ein Array aus Feinden
     * @param partyController Der PartyController der aktuellen Party
     * @return Feind[] Ein Array aus Feinden
     *
     * @author Lang
     * @since 04.12.2023
     */
    public Feind[] gegnerGenerieren(PartyController partyController){
        int feindAnzahl = ZufallsZahlenGenerator.zufallsZahlIntAb1(4);
        int partyLevel = (int) partyController.getPartyLevel();


        feindListeGesamt[0] = new VerueckterDoktor(partyLevel+4-feindAnzahl);
        feindListeGesamt[1] = new Suzaku(partyLevel+4-feindAnzahl);
        feindListeGesamt[2] = new GefallenerEngel(partyLevel+4-feindAnzahl);
        feindListeGesamt[3] = new GefallenerEngel(partyLevel+4-feindAnzahl);
        feindListeGesamt[4] = new SahuaginKampfMagier(partyLevel+4-feindAnzahl);
        feindListeGesamt[5] = new SahuaginKrieger(partyLevel+4-feindAnzahl);
        feindListeGesamt[6] = new Krankenschwester(partyLevel+4-feindAnzahl);
        feindListeGesamt[7] = new Genbu(partyLevel+4-feindAnzahl);
        feindListeGesamt[8] = new GothicLolita(partyLevel+4-feindAnzahl);
        feindListeGesamt[9] = new Inugami(partyLevel+4-feindAnzahl);
        feindListeGesamt[10] = new Tengu(partyLevel+4-feindAnzahl);
        feindListeGesamt[11] = new Seiryu(partyLevel+4-feindAnzahl);
        feindListeGesamt[12] = new KillerRoboter(partyLevel+4-feindAnzahl);
        feindListeGesamt[13] = new UntoterSamurai(partyLevel+4-feindAnzahl);
        feindListeGesamt[14] = new SahuaginTank(partyLevel+4-feindAnzahl);
        feindListeGesamt[15] = new Schlaechter(partyLevel+4-feindAnzahl);


        Feind[] feindlisteReturn = new Feind[feindAnzahl];
        for (int i = 0; i < feindlisteReturn.length; i++) {
           int randomValue =  rnd.nextInt(feindListeGesamt.length);
           feindlisteReturn[i] = feindListeGesamt[randomValue];
           try {
               int param = partyLevel;
               String className = feindListeGesamt[randomValue].getClass().getName();
               Class cl = Class.forName(className);
               Constructor con = cl.getConstructor(int.class);
               Object newEntry = con.newInstance(param);
               feindListeGesamt[randomValue] = (Feind) newEntry;
           } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
               e.printStackTrace();
           }
        }
        return feindlisteReturn;
    }

    public static void ausruestungAnlegen(Feind feind, Ausruestungsgegenstand ausruestungsgegenstand) {
        if (ausruestungsgegenstand instanceof Waffe) {
            feind.setWaffe((Waffe) ausruestungsgegenstand);
            feind.setPhysischeAttacke(feind.getPhysischeAttacke() + ((Waffe) ausruestungsgegenstand).getAttacke());
            feind.setMagischeAttacke(feind.getMagischeAttacke() + ((Waffe) ausruestungsgegenstand).getMagischeAttacke());
        } else if (ausruestungsgegenstand instanceof Ruestung) {
            feind.setRuestung((Ruestung) ausruestungsgegenstand);
            feind.setVerteidigung(feind.getVerteidigung() + (((Ruestung) ausruestungsgegenstand).getVerteidigung()));
            feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + (((Ruestung) ausruestungsgegenstand).getMagischeVerteidigung()));
        } else if (ausruestungsgegenstand instanceof Accessoire) {
            for (int i = 0; i < 3; i++) {
                if (feind.getAccessoire(i) == null) {
                    feind.setAccessoire((Accessoire) ausruestungsgegenstand, i);
                    feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + ((Accessoire) ausruestungsgegenstand).getMaxGesundheitsPunkte());
                    feind.setMaxManaPunkte(feind.getMaxManaPunkte() + ((Accessoire) ausruestungsgegenstand).getMaxManaPunkte());
                    feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + ((Accessoire) ausruestungsgegenstand).getGesundheitsRegeneration());
                    feind.setManaRegeneration(feind.getManaRegeneration() + ((Accessoire) ausruestungsgegenstand).getManaRegeneration());
                    feind.setBeweglichkeit(feind.getBeweglichkeit() + ((Accessoire) ausruestungsgegenstand).getBeweglichkeit());
                    break;
                }
            }
        }
    }
}
