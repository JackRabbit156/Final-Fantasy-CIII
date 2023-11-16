package gegenstand.Ausruestungsgegenstand;

import gegenstand.Gegenstand;

public abstract class Ausruestungsgegenstand extends Gegenstand {
    private boolean kaufbar;
    //TODO private CharakterAttribut bonus;
    private int bonusUmfang;
    private int levelAnforderung;
    private boolean soeldnerItem;

//Methoden
    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 16.11.2023
     * Diese Methode verbessert den Bonus des Ausruestungsgegenstandes.
     * {@link gamehub.schmiede.SchmiedeController }: Hier werden Ausruestungsgegenstaende verbessert
     */
    public void aufbessern(){
        this.bonusUmfang++;
    }

//Getter
    public boolean isKaufbar() {
        return kaufbar;
    }

    //TODO public CharakterAttribut getBonus() {
    //    return bonus;
    //}

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