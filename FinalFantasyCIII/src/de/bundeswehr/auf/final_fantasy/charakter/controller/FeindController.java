package de.bundeswehr.auf.final_fantasy.charakter.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.feinde.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

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
        int feindAnzahl = ZufallsZahlenGenerator.zufallsZahlIntAb1Inklusive(4);
        int partyLevel = (int) partyController.getPartyLevel();


        feindListeGesamt[0] = new VerueckterDoktor(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[1] = new Suzaku(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[2] = new GefallenerEngel(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[3] = new GefallenerEngel(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[4] = new SahuaginKampfMagier(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[5] = new SahuaginKrieger(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[6] = new Krankenschwester(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[7] = new Genbu(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[8] = new GothicLolita(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[9] = new Inugami(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[10] = new Tengu(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[11] = new Seiryu(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[12] = new KillerRoboter(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[13] = new UntoterSamurai(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[14] = new SahuaginTank(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));
        feindListeGesamt[15] = new Schlaechter(getFeindLevel(partyLevel+ZufallsZahlenGenerator.zufallsZahlIntAb0Inklusive(4)-feindAnzahl));


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

    private int getFeindLevel(int feindlevel) {
        return feindlevel <= 0 ? 1 : feindlevel;
    }

    /**Verrechnet die Werte beim Anlegen der Feind-Ausrüstung
     *
     * @param feind der ausgerüstet werden soll
     * @param ausruestungsgegenstand der verrechnet werden wird
     * @author OF Kretschmer ( Verrechnung)
     * @since  05.12.23
     */
    public static void ausruestungAnlegen(Feind feind, AusruestungsGegenstand ausruestungsgegenstand) {
        if (ausruestungsgegenstand instanceof Waffe) {
            Waffe waffe = (Waffe) ausruestungsgegenstand;
            feind.setWaffe(waffe);
            feind.setPhysischeAttacke(feind.getPhysischeAttacke() +waffe.getAttacke());
            feind.setMagischeAttacke(feind.getMagischeAttacke() +waffe.getMagischeAttacke());
            feind.setGenauigkeit(feind.getGenauigkeit() + waffe.getGenauigkeit());
            feind.setBeweglichkeit(feind.getBeweglichkeit() + waffe.getBeweglichkeit());
        } else if (ausruestungsgegenstand instanceof Ruestung) {
            Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
            feind.setRuestung(ruestung);
            feind.setVerteidigung(feind.getVerteidigung() + (ruestung.getVerteidigung()));
            feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + (ruestung).getMagischeVerteidigung());
            feind.setResistenz(feind.getResistenz() + ruestung.getResistenz());
            feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + ruestung.getMaxGesundheitsPunkte());
            feind.setMaxManaPunkte(feind.getMaxManaPunkte() + ruestung.getMaxManaPunkte());
        } else if (ausruestungsgegenstand instanceof Accessoire) {
            Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < 3; i++) {
                if (feind.getAccessoire(i) == null) {
                    feind.setAccessoire(accessoire, i);
                    feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                    feind.setMaxManaPunkte(feind.getMaxManaPunkte() + accessoire.getMaxManaPunkte());
                    feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + accessoire.getGesundheitsRegeneration());
                    feind.setManaRegeneration(feind.getManaRegeneration() + accessoire.getManaRegeneration());
                    feind.setBeweglichkeit(feind.getBeweglichkeit() + accessoire.getBeweglichkeit());
                    break;
                }
            }
        }
    }

}
