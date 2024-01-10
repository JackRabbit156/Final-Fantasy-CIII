package de.bundeswehr.auf.final_fantasy.charakter.model;

import java.util.ArrayList;

public abstract class Klasse {

    public static final String[] KLASSEN_NAMEN = {"Healer", "Magischer DD", "Physischer DD", "Tank"};

    private String bezeichnung;
    private ArrayList<String> nutzbareAusruestung;
    private static String geschichte;
    private static String darstellung;

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public ArrayList<String> getNutzbareAusruestung() {
        return nutzbareAusruestung;
    }

    public void setNutzbareAusruestung(ArrayList<String> nutzbareAusruestung) {
        this.nutzbareAusruestung = nutzbareAusruestung;
    }

    public static String getGeschichte() {
        return geschichte;
    }

    public String getDarstellung() {
        return darstellung;
    }
}
