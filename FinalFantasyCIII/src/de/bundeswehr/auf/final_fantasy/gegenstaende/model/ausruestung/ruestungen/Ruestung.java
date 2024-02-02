package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;

import java.util.Objects;

public class Ruestung extends AusruestungsGegenstand {

    private int magischeVerteidigung;
    private int maxGesundheitsPunkte;
    private int maxManaPunkte;
    private int resistenz;
    private int verteidigung;

    public int getMagischeVerteidigung() {
        return magischeVerteidigung;
    }

    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public int getResistenz() {
        return resistenz;
    }

    public int getVerteidigung() {
        return verteidigung;
    }

    public void setMagischeVerteidigung(int magischeVerteidigung) {
        this.magischeVerteidigung = magischeVerteidigung;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }

    public void setResistenz(int resistenz) {
        this.resistenz = resistenz;
    }

    public void setVerteidigung(int verteidigung) {
        this.verteidigung = verteidigung;
    }

    @Override
    public String toString() {
        return getName() +
                ", lvl=" + getLevelAnforderung() +
                " {" +
                "MV=" + magischeVerteidigung +
                ", maxGP=" + maxGesundheitsPunkte +
                ", maxMP=" + maxManaPunkte +
                ", R=" + resistenz +
                ", V=" + verteidigung +
                "} " +
                "kau=" + getKaufwert() +
                ", ver=" + getVerkaufswert();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ruestung ruestung = (Ruestung) o;
        return getName() == ruestung.getName() &&
                getKaufwert() == ruestung.getKaufwert() &&
                getVerkaufswert() == ruestung.getVerkaufswert() &&
                magischeVerteidigung == ruestung.magischeVerteidigung &&
                maxGesundheitsPunkte == ruestung.maxGesundheitsPunkte &&
                maxManaPunkte == ruestung.maxManaPunkte &&
                resistenz == ruestung.resistenz &&
                verteidigung == ruestung.verteidigung;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKaufwert(), getVerkaufswert(),
                magischeVerteidigung, maxGesundheitsPunkte, maxManaPunkte, resistenz, verteidigung);
    }
}
