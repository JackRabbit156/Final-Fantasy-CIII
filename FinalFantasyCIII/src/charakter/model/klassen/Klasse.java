package charakter.model.klassen;

import java.util.ArrayList;

public abstract class Klasse {

    private String bezeichnung;
    private ArrayList<String> nutzbareAusruestung;

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
}
