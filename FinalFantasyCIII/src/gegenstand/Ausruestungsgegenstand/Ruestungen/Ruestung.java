package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public abstract class Ruestung extends Ausruestungsgegenstand {

    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpVtg() {
        return pVtg;
    }

    public void setpVtg(int pVtg) {
        this.pVtg = pVtg;
    }

    public int getmVtg() {
        return mVtg;
    }

    public void setmVtg(int mVtg) {
        this.mVtg = mVtg;
    }

    @Override
    public int getKaufwert() {
        return kaufwert;
    }

    public void setKaufwert(int kaufwert) {
        this.kaufwert = kaufwert;
    }

    @Override
    public int getVerkaufswert() {
        return verkaufswert;
    }

    public void setVerkaufswert(int verkaufswert) {
        this.verkaufswert = verkaufswert;
    }

    @Override
    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public void setLevelAnforderung(int levelAnforderung) {
        this.levelAnforderung = levelAnforderung;
    }

    @Override
    public boolean isKaufbar() {
        return kaufbar;
    }

    public void setKaufbar(boolean kaufbar) {
        this.kaufbar = kaufbar;
    }

    @Override
    public boolean isSoeldnerItem() {
        return soeldnerItem;
    }

    @Override
    public void setSoeldnerItem(boolean soeldnerItem) {
        this.soeldnerItem = soeldnerItem;
    }
}
