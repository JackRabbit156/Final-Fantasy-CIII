package haendler;

import gegenstaende.ausruestung.Accessoire;
import gegenstaende.ausruestung.ruestungen.Ruestung;
import gegenstaende.ausruestung.waffen.Waffe;
import gegenstaende.material.Material;
import gegenstaende.traenke.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import party.AusruestungsGegenstandInventar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class Haendler {

    // kaufen
    private AusruestungsGegenstandInventar kaufInventar;
    private Map<Verbrauchsgegenstand, IntegerProperty> kaufVerbrauchsInventar;
    private Map<Material, IntegerProperty> kaufMaterialInventar;
    //zurückkaufen
    private ArrayList<Waffe> zurueckkaufenHistorieWaffe;
    private ArrayList<Ruestung> zurueckkaufenHistorieRuestung;
    private ArrayList<Accessoire> zurueckkaufenHistorieAccessoire;

    private Map<Verbrauchsgegenstand, IntegerProperty> zurueckkaufenVerbrauchsgegenstaende;
    private Map<Material, IntegerProperty> zurueckkaufenMaterial;
    // Puffer ArrayLists
    private ArrayList<Verbrauchsgegenstand> verbrauchsgegenstandsKaufenPuffer;

    /**
     * Der Konstuktor des Händlers
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public Haendler() {
        this.zurueckkaufenHistorieWaffe = new ArrayList<>();
        this.zurueckkaufenHistorieRuestung = new ArrayList<>();
        this.zurueckkaufenHistorieAccessoire = new ArrayList<>();
        this.kaufVerbrauchsInventar = new HashMap<>();
        this.kaufMaterialInventar = new HashMap<>();
        this.zurueckkaufenVerbrauchsgegenstaende = new HashMap<Verbrauchsgegenstand, IntegerProperty>();
        this.zurueckkaufenMaterial = new HashMap<Material, IntegerProperty>();
        this.kaufInventar = new AusruestungsGegenstandInventar();
        this.verbrauchsgegenstandsKaufenPuffer = new ArrayList<>();


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
        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_HEILTRANK, new SimpleIntegerProperty(0));
        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_HEILTRANK, new SimpleIntegerProperty(0));
        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_HEILTRANK, new SimpleIntegerProperty(0));

        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_MANATRANK, new SimpleIntegerProperty(0));
        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_MANATRANK, new SimpleIntegerProperty(0));
        zurueckkaufenVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_MANATRANK, new SimpleIntegerProperty(0));

        zurueckkaufenMaterial.put(Material.EISENERZ, new SimpleIntegerProperty(0));
        zurueckkaufenMaterial.put(Material.SILBERERZ, new SimpleIntegerProperty(0));
        zurueckkaufenMaterial.put(Material.GOLDERZ, new SimpleIntegerProperty(0));
        zurueckkaufenMaterial.put(Material.MITHRIL, new SimpleIntegerProperty(0));
    }


    public Map<Verbrauchsgegenstand, IntegerProperty> getKaufVerbrauchsInventar() {
        return kaufVerbrauchsInventar;
    }

    public void setKaufVerbrauchsInventar(Map<Verbrauchsgegenstand, IntegerProperty> kaufVerbrauchsInventar) {
        this.kaufVerbrauchsInventar = kaufVerbrauchsInventar;
    }

    public void setKaufMaterialInventar(Map<Material, IntegerProperty> kaufMaterialInventar) {
        this.kaufMaterialInventar = kaufMaterialInventar;
    }

    public Map<Material, IntegerProperty> getKaufMaterialInventar() {
        return kaufMaterialInventar;
    }

    public AusruestungsGegenstandInventar getKaufInventar() {
        return kaufInventar;
    }

    public void setKaufInventar(AusruestungsGegenstandInventar kaufInventar) {
        this.kaufInventar = kaufInventar;
    }

    public ArrayList<Waffe> getZurueckkaufenHistorieWaffe() {
        return zurueckkaufenHistorieWaffe;
    }

    public ArrayList<Ruestung> getZurueckkaufenHistorieRuestung() {
        return zurueckkaufenHistorieRuestung;
    }

    public ArrayList<Accessoire> getZurueckkaufenHistorieAccessoire() {
        return zurueckkaufenHistorieAccessoire;
    }

    public Map<Verbrauchsgegenstand, IntegerProperty> getZurueckkaufenVerbrauchsgegenstaende() {
        return zurueckkaufenVerbrauchsgegenstaende;
    }

    public Map<Material, IntegerProperty> getZurueckkaufenMaterial() {
        return zurueckkaufenMaterial;
    }

    public ArrayList<Verbrauchsgegenstand> getVerbrauchsgegenstandsKaufenPuffer() {
        return verbrauchsgegenstandsKaufenPuffer;
    }

    public void setVerbrauchsgegenstandsKaufenPuffer(ArrayList<Verbrauchsgegenstand> verbrauchsgegenstandsKaufenPuffer) {
        this.verbrauchsgegenstandsKaufenPuffer = verbrauchsgegenstandsKaufenPuffer;
    }
}
