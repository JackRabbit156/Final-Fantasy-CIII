package charakter.model.klassen;

import java.util.ArrayList;

public abstract class Klasse {

    private String bezeichnung;
    private ArrayList<String> nutzbareAusruestung;
    private static String geschichte;

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
}
