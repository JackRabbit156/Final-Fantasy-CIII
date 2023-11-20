package gamehub.haendler;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
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

    private Waffe[] waffen;
    private AusruestungsgegenstandInventar kaufInventar;
    private Ruestung[] ruestungen;
    private Accessoire[] accessoires;
    private Verbrauchsgegenstand[] verbrauchsgegenstaende;
    private Material[] materialien;
    private ArrayList<Gegenstand> zurueckkaufenHistorie;
    private Map<Verbrauchsgegenstand, Integer> zurueckkaufenVerbrauchsgegenstaende;
    private Map<Material, Integer> zurueckkaufenMaterial;

    public Haendler() {
        this.zurueckkaufenHistorie = new ArrayList<>();
        this.zurueckkaufenVerbrauchsgegenstaende = new HashMap<Verbrauchsgegenstand, Integer>();
        this.zurueckkaufenMaterial = new HashMap<Material, Integer>();
        this.kaufInventar = new AusruestungsgegenstandInventar();
    }

    public AusruestungsgegenstandInventar getKaufInventar() {
        return kaufInventar;
    }

    public void setKaufInventar(AusruestungsgegenstandInventar kaufInventar) {
        this.kaufInventar = kaufInventar;
    }

    public ArrayList<Gegenstand> getZurueckkaufenHistorie() {
        return zurueckkaufenHistorie;
    }

    public Map<Verbrauchsgegenstand, Integer> getZurueckkaufenVerbrauchsgegenstaende() {
        return zurueckkaufenVerbrauchsgegenstaende;
    }

    public Map<Material, Integer> getZurueckkaufenMaterial() {
        return zurueckkaufenMaterial;
    }


}
