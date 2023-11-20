package charakter.controller;

import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.gegnertypen.*;
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
        feindListeGesamt[0] = new BanditenHealer(partyLevel);
        feindListeGesamt[1] = new BanditenKampfMagier(partyLevel);
        feindListeGesamt[2] = new BanditenKrieger(partyLevel);
        feindListeGesamt[3] = new BanditenKrieger(partyLevel);
        feindListeGesamt[4] = new EchsenKampfMagier(partyLevel);
        feindListeGesamt[5] = new EchsenKrieger(partyLevel);
        feindListeGesamt[6] = new EchsenSchamane(partyLevel);
        feindListeGesamt[7] = new GepanzerterMinotauer(partyLevel);
        feindListeGesamt[8] = new MinotauerKampfmagier(partyLevel);
        feindListeGesamt[9] = new MinotauerKrieger(partyLevel);
        feindListeGesamt[10] = new MinotauerSchamane(partyLevel);
        feindListeGesamt[11] = new OrkKampfMagier(partyLevel);
        feindListeGesamt[12] = new OrkKrieger(partyLevel);
        feindListeGesamt[13] = new OrkSchamane(partyLevel);
        feindListeGesamt[14] = new SchwererEchsenKrieger(partyLevel);
        feindListeGesamt[15] = new SchwererOrk(partyLevel);

        int charakterAnzahl = 1;
        for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
            if (spielerCharakter != null){
                charakterAnzahl++;
            }
        }
        Feind[] feindlisteReturn = new Feind[charakterAnzahl];
        for (int i = 0; i < feindlisteReturn.length; i++) {
           int randomValue =  rnd.nextInt(feindlisteReturn.length);
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
}
