package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Waffe extends Ausruestungsgegenstand {
    private int attacke;
    private int magischeAttacke;
    private int genauigkeit;
    private int beweglichkeit;

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

    public int getGenauigkeit() {
        return genauigkeit;
    }

    public void setGenauigkeit(int genauigkeit) {
        this.genauigkeit = genauigkeit;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }
}
