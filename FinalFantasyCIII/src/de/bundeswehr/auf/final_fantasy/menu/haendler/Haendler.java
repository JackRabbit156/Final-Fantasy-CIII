package de.bundeswehr.auf.final_fantasy.menu.haendler;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */
public class Haendler {

    private final List<Accessoire> historieAccessoire = new ArrayList<>();
    private final Map<Material, IntegerProperty> historieMaterial = new HashMap<>();
    private final List<Ruestung> historieRuestung = new ArrayList<>();
    private final Map<Verbrauchsgegenstand, IntegerProperty> historieVerbrauchsgegenstaende = new HashMap<>();
    private final List<Waffe> historieWaffe = new ArrayList<>();
    private final AusruestungsGegenstandInventar kaufInventar;
    private final Map<Material, IntegerProperty> kaufMaterialInventar = new HashMap<>();
    private final Map<Verbrauchsgegenstand, IntegerProperty> kaufVerbrauchsInventar = new HashMap<>();

    /**
     * Der Konstuktor des Händlers
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public Haendler() {
        kaufInventar = new AusruestungsGegenstandInventar();
        // Initialisierung der MAPs für das kaufen
        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.KLEINER_HEILTRANK, new SimpleIntegerProperty(10));
        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.MITTLERER_HEILTRANK, new SimpleIntegerProperty(10));
        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.GROSSER_HEILTRANK, new SimpleIntegerProperty(10));

        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.KLEINER_MANATRANK, new SimpleIntegerProperty(10));
        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.MITTLERER_MANATRANK, new SimpleIntegerProperty(10));
        kaufVerbrauchsInventar.put(Verbrauchsgegenstand.GROSSER_MANATRANK, new SimpleIntegerProperty(10));

        kaufMaterialInventar.put(Material.EISENERZ, new SimpleIntegerProperty(10));
        kaufMaterialInventar.put(Material.SILBERERZ, new SimpleIntegerProperty(10));
        kaufMaterialInventar.put(Material.GOLDERZ, new SimpleIntegerProperty(10));
        kaufMaterialInventar.put(Material.MITHRIL, new SimpleIntegerProperty(10));

        // Initialisierung der MAPs für das zurückkaufen
        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_HEILTRANK, new SimpleIntegerProperty(0));
        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_HEILTRANK, new SimpleIntegerProperty(0));
        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_HEILTRANK, new SimpleIntegerProperty(0));

        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_MANATRANK, new SimpleIntegerProperty(0));
        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_MANATRANK, new SimpleIntegerProperty(0));
        historieVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_MANATRANK, new SimpleIntegerProperty(0));

        historieMaterial.put(Material.EISENERZ, new SimpleIntegerProperty(0));
        historieMaterial.put(Material.SILBERERZ, new SimpleIntegerProperty(0));
        historieMaterial.put(Material.GOLDERZ, new SimpleIntegerProperty(0));
        historieMaterial.put(Material.MITHRIL, new SimpleIntegerProperty(0));
    }

    public List<Accessoire> getHistorieAccessoire() {
        return historieAccessoire;
    }

    public Map<Material, IntegerProperty> getHistorieMaterial() {
        return historieMaterial;
    }

    public List<Ruestung> getHistorieRuestung() {
        return historieRuestung;
    }

    public Map<Verbrauchsgegenstand, IntegerProperty> getHistorieVerbrauchsgegenstaende() {
        return historieVerbrauchsgegenstaende;
    }

    public List<Waffe> getHistorieWaffe() {
        return historieWaffe;
    }

    public AusruestungsGegenstandInventar getKaufInventar() {
        return kaufInventar;
    }

    public Map<Material, IntegerProperty> getKaufMaterialInventar() {
        return kaufMaterialInventar;
    }

    public Map<Verbrauchsgegenstand, IntegerProperty> getKaufVerbrauchsInventar() {
        return kaufVerbrauchsInventar;
    }

}
