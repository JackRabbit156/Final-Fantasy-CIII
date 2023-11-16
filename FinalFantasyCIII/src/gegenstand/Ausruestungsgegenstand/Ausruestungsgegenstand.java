package gegenstand.Ausruestungsgegenstand;

import gegenstand.Gegenstand;

public abstract class Ausruestungsgegenstand extends Gegenstand {
    private boolean istNichtKaufbar;
    //TODO private CharakterAttribut bonus;
    //private int bonusUmfang;
    private int levelAnforderung;
    private boolean istSoeldnerItem;

//Konstruktor
    public Ausruestungsgegenstand() {
        super();
    }

//Methoden

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     * Diese Methode verbessert den Bonus des Ausruestungsgegenstandes.
     * {@link gamehub.schmiede.SchmiedeController }: Hier werden Ausruestungsgegenstaende verbessert
     */
    public void aufbessern() {
        //TODO: Waffenaufbessern
    }

//Getter
    public boolean isIstNichtKaufbar() {
        return istNichtKaufbar;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isIstSoeldnerItem() {
        return istSoeldnerItem;
    }

//Setter
    public void setIstSoeldnerItem(boolean istSoeldnerItem) {
        this.istSoeldnerItem = istSoeldnerItem;
    }

    public void setIstNichtKaufbar(boolean istNichtKaufbar) {
        this.istNichtKaufbar = istNichtKaufbar;
    }

    public void setLevelAnforderung(int levelAnforderung) {
        this.levelAnforderung = levelAnforderung;
    }
}
