package de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.ruestungen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.ausruestung.AusruestungsGegenstand;

public class Ruestung extends AusruestungsGegenstand {

    private int verteidigung;
    private int magischeVerteidigung;
    private int resistenz;
    private int maxGesundheitsPunkte;
    private int maxManaPunkte;

    public int getVerteidigung() {
        return verteidigung;
    }

    public void setVerteidigung(int verteidigung) {
        this.verteidigung = verteidigung;
    }

    public int getMagischeVerteidigung() {
        return magischeVerteidigung;
    }

    public void setMagischeVerteidigung(int magischeVerteidigung) {
        this.magischeVerteidigung = magischeVerteidigung;
    }

    public int getResistenz() {
        return resistenz;
    }

    public void setResistenz(int resistenz) {
        this.resistenz = resistenz;
    }

    public int getMaxGesundheitsPunkte() {
        return maxGesundheitsPunkte;
    }

    public void setMaxGesundheitsPunkte(int maxGesundheitsPunkte) {
        this.maxGesundheitsPunkte = maxGesundheitsPunkte;
    }

    public int getMaxManaPunkte() {
        return maxManaPunkte;
    }

    public void setMaxManaPunkte(int maxManaPunkte) {
        this.maxManaPunkte = maxManaPunkte;
    }
}
