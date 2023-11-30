package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Sanmaus extends HLR implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,15,-5,0,5,0,0,0,0,0,0};


    public Sanmaus(SpielerCharakter charakter){
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
