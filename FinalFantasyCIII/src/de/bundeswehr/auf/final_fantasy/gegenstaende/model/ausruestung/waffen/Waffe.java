package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;

public class Waffe extends AusruestungsGegenstand {

    private int attacke;
    private int beweglichkeit;
    private int genauigkeit;
    private int magischeAttacke;

    public int getAttacke() {
        return attacke;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public int getGenauigkeit() {
        return genauigkeit;
    }

    public int getMagischeAttacke() {
        return magischeAttacke;
    }

    public void setAttacke(int attacke) {
        this.attacke = attacke;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }

    public void setGenauigkeit(int genauigkeit) {
        this.genauigkeit = genauigkeit;
    }

    public void setMagischeAttacke(int magischeAttacke) {
        this.magischeAttacke = magischeAttacke;
    }

}
