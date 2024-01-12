package de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;

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

}
