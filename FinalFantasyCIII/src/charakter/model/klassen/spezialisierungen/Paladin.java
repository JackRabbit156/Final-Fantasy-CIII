package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.TNK;

public class Paladin extends TNK implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,0,0,0,0,0,0,0,0,0};
    //TODO faehigkeiten, CharakterDarstellung

    public Paladin(SpielerCharakter charakter){
        charakter.setKlasse(this);
        charakter.setGrafischeDarstellung("Dummy Paladin-Darstellung");
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
