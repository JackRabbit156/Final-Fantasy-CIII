package gegenstand.Ausruestungsgegenstand;

import gegenstand.Gegenstand;

public abstract class Ausruestungsgegenstand extends Gegenstand {
    private boolean kaufbar;
    private CharakterAttribut bonus;
    private int bonusUmfang;
    private int levelAnforderung;
    private boolean soeldnerItem;

//Methoden
    public void aufbessern(){
        this.bonusUmfang++;
    }

//Getter
    public boolean isKaufbar() {
        return kaufbar;
    }

    public CharakterAttribut getBonus() {
        return bonus;
    }

    public int getBonusUmfang() {
        return bonusUmfang;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isSoeldnerItem() {
        return soeldnerItem;
    }

//Setter
    public void setSoeldnerItem(boolean soeldnerItem) {
        this.soeldnerItem = soeldnerItem;
    }
}
