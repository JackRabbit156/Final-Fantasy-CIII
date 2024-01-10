package de.bundeswehr.auf.final_fantasy.party.model;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.Map;

public class Party {

    private SpielerCharakter hauptCharakter;
    private SpielerCharakter[] nebenCharakter;
    private IntegerProperty gold;
    private Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende;
    private Map<Material, IntegerProperty> materialien;
    private AusruestungsGegenstandInventar ausruestungsgegenstandInventar;

    public Party(String name, String klasse) {
        this.hauptCharakter = new SpielerCharakter(name, klasse, "Der Hauptcharakter des Spielers hat einen ganzen Haufen Git-Tickets in ueberzogenem Umfang erhalten und ist deswegen sauer.");
        this.nebenCharakter = new SpielerCharakter[3];
        this.gold = new SimpleIntegerProperty(200);
        this.ausruestungsgegenstandInventar = new AusruestungsGegenstandInventar();
        befuelleMaterial();
        befuelleVerbrauchsgegenstaende();
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

    public SpielerCharakter getNebenCarakter(int i) {
        return nebenCharakter[i];
    }

    public void setNebenCharakter(SpielerCharakter[] nebenCharakter) {
        this.nebenCharakter = nebenCharakter;
    }

    public int getGold() {
        return this.gold.get();
    }

    public void setGold(int gold) {
        this.gold.set(gold);
    }

    public Map<Verbrauchsgegenstand, IntegerProperty> getVerbrauchsgegenstaende() {
        return verbrauchsgegenstaende;
    }

    public void setVerbrauchsgegenstaende(Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende) {
        this.verbrauchsgegenstaende = verbrauchsgegenstaende;
    }

    public Map<Material, IntegerProperty> getMaterialien() {
        return materialien;
    }

    public void setMaterialien(Map<Material, IntegerProperty> materialien) {
        this.materialien = materialien;
    }

    public AusruestungsGegenstandInventar getAusruestungsgegenstandInventar() {
        return ausruestungsgegenstandInventar;
    }

    public void setAusruestungsgegenstandInventar(AusruestungsGegenstandInventar ausruestungsgegenstandInventar) {
        this.ausruestungsgegenstandInventar = ausruestungsgegenstandInventar;
    }

    private void befuelleMaterial() {
        this.materialien = new HashMap<>();
        this.materialien.put(Material.EISENERZ, new SimpleIntegerProperty(0));
        this.materialien.put(Material.GOLDERZ, new SimpleIntegerProperty(0));
        this.materialien.put(Material.MITHRIL, new SimpleIntegerProperty(0));
        this.materialien.put(Material.POPEL, new SimpleIntegerProperty(0));
        this.materialien.put(Material.SCHLEIM, new SimpleIntegerProperty(0));
        this.materialien.put(Material.SILBERERZ, new SimpleIntegerProperty(0));
    }

    private void befuelleVerbrauchsgegenstaende() {
        this.verbrauchsgegenstaende = new HashMap<>();
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_MANATRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_MANATRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_MANATRANK, new SimpleIntegerProperty(0));
    }

    public IntegerProperty goldProperty() {
        return gold;
    }
}
