package charakter.controller;

import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.gegnertypen.*;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import party.PartyController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class FeindController {
    
    private static final Feind[] feindListeGesamt = new Feind[16];
    private final Random rnd = new Random();

    /**
     * Generiert Feinde auf Partylevel
     * Erstellt ein Array aus Feinden in der groeße der Party
     * @param partyController
     * @return Feind[]
     *
     * @since 20.11.2023
     * @author Lang
     */
    public Feind[] gegnerGenerieren(PartyController partyController){
        int partyLevel = (int) partyController.getPartyLevel();
        feindListeGesamt[0] = new VerueckterDoktor(partyLevel);
        feindListeGesamt[1] = new Suzaku(partyLevel);
        feindListeGesamt[2] = new GefallenerEngel(partyLevel);
        feindListeGesamt[3] = new GefallenerEngel(partyLevel);
        feindListeGesamt[4] = new SahuaginKampfMagier(partyLevel);
        feindListeGesamt[5] = new SahuaginKrieger(partyLevel);
        feindListeGesamt[6] = new Krankenschwester(partyLevel);
        feindListeGesamt[7] = new Genbu(partyLevel);
        feindListeGesamt[8] = new GothicLolita(partyLevel);
        feindListeGesamt[9] = new Inugami(partyLevel);
        feindListeGesamt[10] = new Tengu(partyLevel);
        feindListeGesamt[11] = new Seiryu(partyLevel);
        feindListeGesamt[12] = new KillerRoboter(partyLevel);
        feindListeGesamt[13] = new UntoterSamurai(partyLevel);
        feindListeGesamt[14] = new SahuaginTank(partyLevel);
        feindListeGesamt[15] = new Schlaechter(partyLevel);

        int charakterAnzahl = 1;
        for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
            if (spielerCharakter != null){
                charakterAnzahl++;
            }
        }
        Feind[] feindlisteReturn = new Feind[charakterAnzahl];
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
