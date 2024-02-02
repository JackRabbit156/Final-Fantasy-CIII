package de.bundeswehr.auf.final_fantasy.party.model;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Verbrauchsgegenstaende;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.Map;

public class Party {

    private AusruestungsGegenstandInventar ausruestungsgegenstandInventar;
    private final IntegerProperty gold;
    private SpielerCharakter hauptCharakter;
    private Map<Material, IntegerProperty> materialien;
    private SpielerCharakter[] nebenCharaktere;
    private Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende;

    /**
     * Leer-Konstruktor zum Laden einer neuen Party
     */
    public Party() {
        this.gold = new SimpleIntegerProperty();
        this.ausruestungsgegenstandInventar = new AusruestungsGegenstandInventar();
    }

    public Party(String name, String klasse) {
        this.hauptCharakter = new SpielerCharakter(name, klasse, "Der Hauptcharakter des Spielers hat einen ganzen Haufen Git-Tickets in ueberzogenem Umfang erhalten und ist deswegen sauer.");
        this.nebenCharaktere = new SpielerCharakter[3];
        this.gold = new SimpleIntegerProperty(200);
        this.ausruestungsgegenstandInventar = new AusruestungsGegenstandInventar();
        befuelleMaterial();
        befuelleVerbrauchsgegenstaende();
    }

    public AusruestungsGegenstandInventar getAusruestungsgegenstandInventar() {
        return ausruestungsgegenstandInventar;
    }

    public int getGold() {
        return this.gold.get();
    }

    public SpielerCharakter getHauptCharakter() {
        return hauptCharakter;
    }

    public Map<Material, IntegerProperty> getMaterialien() {
        return materialien;
    }

    public SpielerCharakter getNebenCarakter(int i) {
        return nebenCharaktere[i];
    }

    public SpielerCharakter[] getNebenCharaktere() {
        return nebenCharaktere;
    }

    public Map<Verbrauchsgegenstand, IntegerProperty> getVerbrauchsgegenstaende() {
        return verbrauchsgegenstaende;
    }

    public IntegerProperty goldProperty() {
        return gold;
    }

    public void setAusruestungsgegenstandInventar(AusruestungsGegenstandInventar ausruestungsgegenstandInventar) {
        this.ausruestungsgegenstandInventar = ausruestungsgegenstandInventar;
    }

    public void setGold(int gold) {
        this.gold.set(gold);
    }

    public void setHauptCharakter(SpielerCharakter hauptCharakter) {
        this.hauptCharakter = hauptCharakter;
    }

    public void setMaterialien(Map<Material, IntegerProperty> materialien) {
        this.materialien = materialien;
    }

    public void setNebenCharaktere(SpielerCharakter[] nebenCharaktere) {
        this.nebenCharaktere = nebenCharaktere;
    }

    public void setVerbrauchsgegenstaende(Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende) {
        this.verbrauchsgegenstaende = verbrauchsgegenstaende;
    }

    private void befuelleMaterial() {
        this.materialien = new HashMap<>();
        this.materialien.put(Materialien.EISENERZ, new SimpleIntegerProperty(0));
        this.materialien.put(Materialien.GOLDERZ, new SimpleIntegerProperty(0));
        this.materialien.put(Materialien.MITHRIL, new SimpleIntegerProperty(0));
        this.materialien.put(Materialien.POPEL, new SimpleIntegerProperty(0));
        this.materialien.put(Materialien.SCHLEIM, new SimpleIntegerProperty(0));
        this.materialien.put(Materialien.SILBERERZ, new SimpleIntegerProperty(0));
    }

    private void befuelleVerbrauchsgegenstaende() {
        this.verbrauchsgegenstaende = new HashMap<>();
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.GROSSER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.MITTLERER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.KLEINER_HEILTRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.GROSSER_MANATRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.MITTLERER_MANATRANK, new SimpleIntegerProperty(0));
        this.verbrauchsgegenstaende.put(Verbrauchsgegenstaende.KLEINER_MANATRANK, new SimpleIntegerProperty(0));
    }

}
