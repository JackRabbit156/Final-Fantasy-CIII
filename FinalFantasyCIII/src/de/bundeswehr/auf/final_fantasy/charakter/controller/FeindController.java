package de.bundeswehr.auf.final_fantasy.charakter.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.feinde.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

public class FeindController {

    /**
     * Verrechnet die Werte beim Anlegen der Feind-Ausrüstung
     *
     * @param feind                  der ausgerüstet werden soll
     * @param ausruestungsgegenstand der verrechnet werden wird
     * @author OF Kretschmer ( Verrechnung)
     * @since 05.12.23
     */
    public static void ausruestungAnlegen(Feind feind, AusruestungsGegenstand ausruestungsgegenstand) {
        if (ausruestungsgegenstand instanceof Waffe) {
            Waffe waffe = (Waffe) ausruestungsgegenstand;
            feind.setWaffe(waffe);
            feind.setPhysischeAttacke(feind.getPhysischeAttacke() + waffe.getAttacke());
            feind.setMagischeAttacke(feind.getMagischeAttacke() + waffe.getMagischeAttacke());
            feind.setGenauigkeit(feind.getGenauigkeit() + waffe.getGenauigkeit());
            feind.setBeweglichkeit(feind.getBeweglichkeit() + waffe.getBeweglichkeit());
        }
        else if (ausruestungsgegenstand instanceof Ruestung) {
            Ruestung ruestung = (Ruestung) ausruestungsgegenstand;
            feind.setRuestung(ruestung);
            feind.setVerteidigung(feind.getVerteidigung() + (ruestung.getVerteidigung()));
            feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + (ruestung).getMagischeVerteidigung());
            feind.setResistenz(feind.getResistenz() + ruestung.getResistenz());
            feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + ruestung.getMaxGesundheitsPunkte());
            feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
            feind.setMaxManaPunkte(feind.getMaxManaPunkte() + ruestung.getMaxManaPunkte());
            feind.setManaPunkte(feind.getMaxManaPunkte());
        }
        else if (ausruestungsgegenstand instanceof Accessoire) {
            Accessoire accessoire = (Accessoire) ausruestungsgegenstand;
            for (int i = 0; i < 3; i++) {
                if (feind.getAccessoire(i) == null) {
                    feind.setAccessoire(accessoire, i);
                    feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + accessoire.getMaxGesundheitsPunkte());
                    feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
                    feind.setMaxManaPunkte(feind.getMaxManaPunkte() + accessoire.getMaxManaPunkte());
                    feind.setManaPunkte(feind.getMaxManaPunkte());
                    feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + accessoire.getGesundheitsRegeneration());
                    feind.setManaRegeneration(feind.getManaRegeneration() + accessoire.getManaRegeneration());
                    feind.setBeweglichkeit(feind.getBeweglichkeit() + accessoire.getBeweglichkeit());
                    break;
                }
            }
        }
    }

    /**
     * Generiert Feinde auf Basis Partylevel
     * Größe der Feindgruppe zufällig zwischen 1 und 4
     * Je weniger Gegner, desto höher deren Level
     * Erstellt ein Array aus Feinden
     *
     * @param partyController Der PartyController der aktuellen Party
     * @return Feind[] Ein Array aus Feinden
     * @author Lang
     * @since 04.12.2023
     */
    public Feind[] gegnerGenerieren(PartyController partyController) {
        int feindAnzahl = ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(4);
        int partyLevel = (int) partyController.getPartyLevel();
        Feind[] feinde = new Feind[feindAnzahl];
        for (int i = 0; i < feinde.length; i++) {
            int feindLevel = getFeindLevel(partyLevel + ZufallsZahlenGenerator.zufallsZahlAb0Inklusive(4) - feindAnzahl);
            switch (ZufallsZahlenGenerator.zufallsZahlAb0(16)) {
                case 0:
                    feinde[i] = new VerueckterDoktor(feindLevel);
                    break;
                case 1:
                    feinde[i] = new Suzaku(feindLevel);
                    break;
                case 2:
                    feinde[i] = new GefallenerEngel(feindLevel);
                    break;
                case 3:
                    feinde[i] = new Mecha(feindLevel);
                    break;
                case 4:
                    feinde[i] = new SahuaginKampfMagier(feindLevel);
                    break;
                case 5:
                    feinde[i] = new SahuaginKrieger(feindLevel);
                    break;
                case 6:
                    feinde[i] = new Krankenschwester(feindLevel);
                    break;
                case 7:
                    feinde[i] = new Genbu(feindLevel);
                    break;
                case 8:
                    feinde[i] = new GothicLolita(feindLevel);
                    break;
                case 9:
                    feinde[i] = new Inugami(feindLevel);
                    break;
                case 10:
                    feinde[i] = new Tengu(feindLevel);
                    break;
                case 11:
                    feinde[i] = new Seiryu(feindLevel);
                    break;
                case 12:
                    feinde[i] = new KillerRoboter(feindLevel);
                    break;
                case 13:
                    feinde[i] = new UntoterSamurai(feindLevel);
                    break;
                case 14:
                    feinde[i] = new SahuaginTank(feindLevel);
                    break;
                case 15:
                    feinde[i] = new Schlaechter(feindLevel);
            }
        }
        updateNamen(feinde);
        return feinde;
    }

    private int getFeindLevel(int feindlevel) {
        return feindlevel <= 0 ? 1 : feindlevel;
    }

    private void updateNamen(Feind[] feinde) {
        for (int i = 0; i < feinde.length; i++) {
            String name = feinde[i].getName();
            StringBuilder zusatz = new StringBuilder(" II");
            for (int ii = i + 1; ii < feinde.length; ii++) {
                if (name.equals(feinde[ii].getName())) {
                    feinde[i].setName(name + " I");
                    feinde[ii].setName(feinde[ii].getName() + zusatz);
                    zusatz.append("I");
                }
            }
        }
    }

}
