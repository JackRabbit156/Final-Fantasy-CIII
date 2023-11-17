package gegenstand.Ausruestungsgegenstand.Ruestungen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Ruestung extends Ausruestungsgegenstand {

    private String name;
    private int pVtg;
    private int mVtg;

    public int getpVtg() {
        return pVtg;
    }

    public void setpVtg(int pVtg) {
        this.pVtg = pVtg;
    }

    public int getmVtg() {
        return mVtg;
    }

    public void setmVtg(int mVtg) {
        this.mVtg = mVtg;
    }
}
