package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Ruestung extends Ausruestungsgegenstand {

    private int verteidigung;
    private int magischeVerteidigung;

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
}
