package gegenstaende.ausruestung;

import gegenstaende.Gegenstand;

public abstract class AusruestungsGegenstand extends Gegenstand {
    private boolean istNichtKaufbar;
    private int levelAnforderung;
    private boolean istSoeldnerItem;

//Konstruktor
    public AusruestungsGegenstand() {
        super();
    }

//Methoden

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
