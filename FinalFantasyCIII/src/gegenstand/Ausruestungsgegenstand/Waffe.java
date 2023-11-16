package gegenstand.Ausruestungsgegenstand;

public class Waffe {
    //TODO hier sind dummy daten drinne für PartyStatusController
    private String name;
    private int pAtk;
    private int mAtk;
    private int kaufwert;
    private int verkaufswert;
    private int levelAnforderung;
    private boolean kaufbar;

    public Waffe(String name, int verkaufswert, int levelAnforderung) {
        this.name = name;
        this.verkaufswert = verkaufswert;
        this.levelAnforderung = levelAnforderung;
    }

    public int getpAtk() {
        return pAtk;
    }

    public String getName() {
        return name;
    }

    public int getmAtk() {
        return mAtk;
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

}
