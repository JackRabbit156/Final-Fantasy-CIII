package party;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

import java.util.Map;
import java.util.HashMap;

public class Party {
    private SpielerCharakter hauptCharakter;
    private SpielerCharakter[] nebenCharakter;
    private int gold;
    private Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstaende;
    private Map<Material, Integer> materialien;
    private AusruestungsgegenstandInventar ausruestungsgegenstandInventar;

    public Party(String name, String klasse){
        this.hauptCharakter = new SpielerCharakter(name, klasse, "Der Hauptcharakter des Spielers");
        this.nebenCharakter = new SpielerCharakter[3];
        this.gold = 200;
        this.ausruestungsgegenstandInventar = new AusruestungsgegenstandInventar();
        this.materialien = new HashMap<>();
        this.verbrauchsgegenstaende = new HashMap<>();
    }

    public SpielerCharakter getHauptCharakter() {
        return hauptCharakter;
    }

    public void setHauptCharakter(SpielerCharakter hauptCharakter) {
        this.hauptCharakter = hauptCharakter;
    }

    public SpielerCharakter[] getNebenCharakter() {
        return nebenCharakter;
    }

    public SpielerCharakter getNebenCarakter(int i) {return nebenCharakter[i];}

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

    public AusruestungsgegenstandInventar getAusruestungsgegenstandInventar() {
        return ausruestungsgegenstandInventar;
    }

    public void setAusruestungsgegenstandInventar(AusruestungsgegenstandInventar ausruestungsgegenstandInventar) {
        this.ausruestungsgegenstandInventar = ausruestungsgegenstandInventar;
    }
}
