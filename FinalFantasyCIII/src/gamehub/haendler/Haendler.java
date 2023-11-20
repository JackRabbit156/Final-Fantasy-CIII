package gamehub.haendler;

import gegenstand.Ausruestungsgegenstand.Accesssoire;
import gegenstand.Ausruestungsgegenstand.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

import java.util.ArrayList;

/**
 * @author OF Kretschmer
 * @since 16.11.23
 */

public class Haendler {

    private Waffe[] waffen;
    private Accesssoire[] accessoires;
    private Ruestung[] ruestungen;
    private Verbrauchsgegenstand[] verbrauchsgegenstaende;
    private Material[] materialien;
    private ArrayList<Gegenstand> zurueckkaufenHistorie;

    public Haendler() {

        this.zurueckkaufenHistorie = new ArrayList<>();
    }


    public ArrayList<Gegenstand> getZurueckkaufenHistorie() {
        return zurueckkaufenHistorie;
    }

}
