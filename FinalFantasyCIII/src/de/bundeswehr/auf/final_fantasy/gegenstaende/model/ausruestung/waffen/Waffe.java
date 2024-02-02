package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;

import java.util.Objects;

public class Waffe extends AusruestungsGegenstand {

    private int attacke;
    private int beweglichkeit;
    private int genauigkeit;
    private int magischeAttacke;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Waffe waffe = (Waffe) o;
        return getName() == waffe.getName() &&
                getKaufwert() == waffe.getKaufwert() &&
                getVerkaufswert() == waffe.getVerkaufswert() &&
                attacke == waffe.attacke &&
                beweglichkeit == waffe.beweglichkeit &&
                genauigkeit == waffe.genauigkeit &&
                magischeAttacke == waffe.magischeAttacke;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKaufwert(), getVerkaufswert(),
                attacke, beweglichkeit, genauigkeit, magischeAttacke);
    }

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

    @Override
    public String toString() {
        return getName() +
                ", lvl=" + getLevelAnforderung() +
                " {" +
                "A=" + attacke +
                ", B=" + beweglichkeit +
                ", G=" + genauigkeit +
                ", MA=" + magischeAttacke +
                "} " +
                "kau=" + getKaufwert() +
                ", ver=" + getVerkaufswert();
    }

}
