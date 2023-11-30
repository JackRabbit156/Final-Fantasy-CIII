package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Feuermagier extends MDD implements Spezialisierung {

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,0,4,-1,0,-2,0,0,0,0};


    public Feuermagier(SpielerCharakter charakter){
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + attribute[3]);
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + attribute[6]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
