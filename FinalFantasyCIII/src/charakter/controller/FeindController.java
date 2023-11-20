package charakter.controller;

import charakter.model.Feind;
import charakter.model.klassen.gegnertypen.*;
import party.PartyController;

import java.util.Random;

public class FeindController {
    
    private static final Feind[] feindListeGesamt = new Feind[16];
    private final Random rnd = new Random();

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

        Feind[] feindlisteReturn = new Feind[partyController.getParty().getNebenCharakter().length+1];
        for (int i = 0; i < feindlisteReturn.length; i++) {
           feindlisteReturn[i] = feindListeGesamt[rnd.nextInt(feindlisteReturn.length)];
        }
        return feindlisteReturn;
    }
}
