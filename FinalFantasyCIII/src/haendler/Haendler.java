package haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import party.AusruestungsgegenstandInventar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class Haendler {

    // kaufen
    private AusruestungsgegenstandInventar kaufInventar;
    private Map<Verbrauchsgegenstand, Integer> kaufVerbrauchsInventar;
    private Map<Material, Integer> kaufMaterialInventar;
    //zurückkaufen
    private ArrayList<Waffe> zurueckkaufenHistorieWaffe;
    private ArrayList<Ruestung> zurueckkaufenHistorieRuestung;
    private ArrayList<Accessoire> zurueckkaufenHistorieAccessoire;

    private Map<Verbrauchsgegenstand, Integer> zurueckkaufenVerbrauchsgegenstaende;
    private Map<Material, Integer> zurueckkaufenMaterial;

    public Haendler() {
        this.zurueckkaufenHistorieWaffe = new ArrayList<>();
        this.zurueckkaufenHistorieRuestung = new ArrayList<>();
        this.zurueckkaufenHistorieAccessoire = new ArrayList<>();
        this.kaufVerbrauchsInventar = new HashMap<>();
        this.kaufMaterialInventar = new HashMap<>();
        this.zurueckkaufenVerbrauchsgegenstaende = new HashMap<Verbrauchsgegenstand, Integer>();
        this.zurueckkaufenMaterial = new HashMap<Material, Integer>();
        this.kaufInventar = new AusruestungsgegenstandInventar();
    }

    public Map<Verbrauchsgegenstand, Integer> getKaufVerbrauchsInventar() {
        return kaufVerbrauchsInventar;
    }

    public void setKaufVerbrauchsInventar(Map<Verbrauchsgegenstand, Integer> kaufVerbrauchsInventar) {
        this.kaufVerbrauchsInventar = kaufVerbrauchsInventar;
    }

    public void setKaufMaterialInventar(Map<Material, Integer> kaufMaterialInventar) {
        this.kaufMaterialInventar = kaufMaterialInventar;
    }

    public Map<Material, Integer> getKaufMaterialInventar() {
        return kaufMaterialInventar;
    }

    public AusruestungsgegenstandInventar getKaufInventar() {
        return kaufInventar;
    }

    public void setKaufInventar(AusruestungsgegenstandInventar kaufInventar) {
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

    public Map<Verbrauchsgegenstand, Integer> getZurueckkaufenVerbrauchsgegenstaende() {
        return zurueckkaufenVerbrauchsgegenstaende;
    }

    public Map<Material, Integer> getZurueckkaufenMaterial() {
        return zurueckkaufenMaterial;
    }


}
