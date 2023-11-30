package charakter.model.klassen.spezialisierungen;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.PDD;
import trainer.faehigkeiten.FaehigkeitFabrik;

public class Berserker extends PDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,0,3,0,-1,-2,0,0,1,0,0};

    public Berserker(SpielerCharakter charakter){
        charakter.setBeweglichkeit(charakter.getBeweglichkeit() + attribute[8]);
        charakter.setGenauigkeit(charakter.getGenauigkeit() + attribute[4]);
        charakter.setVerteidigung(charakter.getVerteidigung() + attribute[5]);
        charakter.setPhysischeAttacke(charakter.getPhysischeAttacke() + attribute[2]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
