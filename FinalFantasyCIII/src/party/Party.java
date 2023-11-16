package party;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausrüstungsgegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

import java.util.HashMap;
import java.util.Map;

public class Party {


    /**
     * Erste Implementation für die Erstellung eines neuen Spieles.
     * Attribute, Contstructor und Getter/Setter erstellt.
     *
     * @since 16.11.2023
     * @author Lang
     */
    SpielerCharakter hauptCharakter;
    SpielerCharakter[] nebenCharakter;
    int gold;
    Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstande;
    Map<Material, Integer> materialien;
    Map<Ausrüstungsgegenstand, Integer> ausruestungen;

    public Party(String name, String klasse){
        this.hauptCharakter = new SpielerCharakter(name, klasse, "Der Hauptcharakter des Spielers");
        this.nebenCharakter = new SpielerCharakter[3];
        this.gold = 200;
        this.ausruestungen = new HashMap<>();
        this.materialien = new HashMap<>();
        this.verbrauchsgegenstande = new HashMap<>();
    }

    public SpielerCharakter getHauptCharakter() {
        return hauptCharakter;
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

    public Map<Verbrauchsgegenstand, Integer> getVerbrauchsgegenstande() {
        return verbrauchsgegenstande;
    }

    public void setVerbrauchsgegenstande(Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstande) {
        this.verbrauchsgegenstande = verbrauchsgegenstande;
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
