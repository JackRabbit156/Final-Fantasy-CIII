package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;

public abstract class AusruestungsGegenstand extends Gegenstand {

    private boolean istNichtKaufbar;
    private int levelAnforderung;
    private boolean istSoeldnerItem;

    public boolean isIstNichtKaufbar() {
        return istNichtKaufbar;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isIstSoeldnerItem() {
        return istSoeldnerItem;
    }

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
