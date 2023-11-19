package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;
import charakter.model.klassen.PDD;

public class Rabauke extends PDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    Integer[] attribute = {0,0,3,0,-2,0,0,0,0,0,0};
    //TODO faehigkeiten, CharakterDarstellung

    public Rabauke(Charakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() - attribute[4]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        charakter.setGrafischeDarstellung("Dummy Rabauke-Darstellung");
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
