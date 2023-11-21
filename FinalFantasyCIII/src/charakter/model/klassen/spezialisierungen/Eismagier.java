package charakter.model.klassen.spezialisierungen;

import charakter.model.Charakter;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.MDD;
import gamehub.trainer.faehigkeiten.FaehigkeitFabrik;

public class Eismagier extends MDD implements Spezialisierung{

    // 0 = maxGesundheitsPunkt, 1 = maxManaPunkte, 2 = physischeAttacke, 3 = magischeAttacke, 4 = genauigkeit,
    // 5 = verteidigung, 6 = magischeVerteidigung, 7 = resistenz, 8 = beweglichkeit, 9 = gesundheitsRegenartion,
    // 10 = manaRegeneration
    private Integer[] attribute = {0,5,0,1,0,0,2,0,0,0,0};
    //TODO CharakterDarstellung

    public Eismagier(SpielerCharakter charakter) {
        charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() + attribute[6]);
        charakter.setMaxManaPunkte(charakter.getMaxManaPunkte() + attribute[1]);
        charakter.setMagischeAttacke(charakter.getMagischeAttacke() + attribute[3]);
        charakter.setKlasse(this);
        FaehigkeitFabrik.spezialisierungsFaehigkeitHinzufuegen(charakter);
    }

    public Integer[] getAttribute() {
        return attribute;
    }
}
