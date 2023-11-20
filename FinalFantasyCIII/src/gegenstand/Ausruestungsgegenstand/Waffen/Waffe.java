package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Waffe extends Ausruestungsgegenstand {
    private int attacke;
    private int magischeAttacke;

    public int getAttacke() {
        return attacke;
    }

    public void setAttacke(int attacke) {
        this.attacke = attacke;
    }

    public int getMagischeAttacke() {
        return magischeAttacke;
    }

    public void setMagischeAttacke(int magischeAttacke) {
        this.magischeAttacke = magischeAttacke;
    }
}
