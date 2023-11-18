package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;
import charakter.model.klassen.MDD;

public class Eismagier extends MDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    Integer[] attribute = {0,5,0,1,0,0,2,0,0,0,0};
    //TODO faehigkeiten, CharakterDarstellung

    public Eismagier(Charakter charakter) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + attribute[6]);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + attribute[3]);
        charakter.setKlasse(this);
        charakter.setGrafischeDarstellung("Dummy Eismagier-Darstellung");
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
