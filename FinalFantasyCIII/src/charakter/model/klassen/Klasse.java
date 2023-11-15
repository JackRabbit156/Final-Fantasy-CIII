package charakter.model.klassen;

public abstract class Klasse {

    private String bezeichnung;
    private String gewichtsklasse;
    private String ruestungstyp;
    private String waffentyp;


    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setGewichtsklasse(String gewichtsklasse) {
        this.gewichtsklasse = gewichtsklasse;
    }

    public void setRuestungstyp(String ruestungstyp) {
        this.ruestungstyp = ruestungstyp;
    }

    public void setWaffentyp(String waffentyp) {
        this.waffentyp = waffentyp;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getGewichtsklasse() {
        return gewichtsklasse;
    }

    public String getRuestungstyp() {
        return ruestungstyp;
    }

    public String getWaffentyp() {
        return waffentyp;
    }
}
