package gegenstand.Ausruestungsgegenstand;

public class Ruestung {
    //TODO hier sind dummy daten drinne für PartyStatusController
    private String name;
    private int pVtg;
    private int mVtg;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;
    private boolean soeldnerItem;

    public Ruestung(String name, int verkaufswert, int levelAnforderung) {
        this.name = name;
        this.verkaufswert = verkaufswert;
        this.levelAnforderung = levelAnforderung;
    }

    public String getName() {
        return name;
    }

    public int getpVtg() {
        return pVtg;
    }

    public int getmVtg() {
        return mVtg;
    }

    public int getKaufwert() {
        return kaufwert;
    }

    public int getVerkaufswert() {
        return verkaufswert;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isKaufbar() {
        return kaufbar;
    }

    public boolean isSoeldnerItem() {
        return soeldnerItem;
    }
}

