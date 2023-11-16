package party;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausrüstungsgegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

import java.util.Map;

public class Party {
    private SpielerCharakter hauptCharakter;
    private SpielerCharakter[] nebenCharakter;
    private int gold;
    private Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstaende;
    private Map<Material, Integer> materialien;
    private Map<Ausrüstungsgegenstand, Integer> ausruestungen;

    public SpielerCharakter getHauptCharakter() {
        return hauptCharakter;
    }

    public void setHauptCharakter(SpielerCharakter hauptCharakter) {
        this.hauptCharakter = hauptCharakter;
    }

    public SpielerCharakter[] getNebenCharakter() {
        return nebenCharakter;
    }

    public void setNebenCharakter(SpielerCharakter[] nebenCharakter) {
        this.nebenCharakter = nebenCharakter;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Map<Verbrauchsgegenstand, Integer> getVerbrauchsgegenstaende() {
        return verbrauchsgegenstaende;
    }

    public void setVerbrauchsgegenstaende(Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstaende) {
        this.verbrauchsgegenstaende = verbrauchsgegenstaende;
    }

    public Map<Material, Integer> getMaterialien() {
        return materialien;
    }

    public void setMaterialien(Map<Material, Integer> materialien) {
        this.materialien = materialien;
    }

    public Map<Ausrüstungsgegenstand, Integer> getAusruestungen() {
        return ausruestungen;
    }

    public void setAusruestungen(Map<Ausrüstungsgegenstand, Integer> ausruestungen) {
        this.ausruestungen = ausruestungen;
    }
}
