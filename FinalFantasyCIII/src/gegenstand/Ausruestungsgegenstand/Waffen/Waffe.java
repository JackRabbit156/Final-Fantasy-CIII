package gegenstand.Ausruestungsgegenstand.Waffen;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

public class Waffe extends Ausruestungsgegenstand {
    private int pAtk;
    private int mAtk;

    public Waffe() {
    }

    public int getpAtk() {
        return pAtk;
    }

    public void setpAtk(int pAtk) {
        this.pAtk = pAtk;
    }

    public int getmAtk() {
        return mAtk;
    }

    public void setmAtk(int mAtk) {
        this.mAtk = mAtk;
    }
}
