package gegenstand.Ausruestungsgegenstand;

public class Ruestung {
    //TODO Reale Implementierung FIX IT!
    private String name;
    private int staerke;

    public Ruestung(String name, int staerke) {
        this.name = name;
        this.staerke = staerke;
    }

    public String getName() {
        return name;
    }

    public int getStaerke() {
        return staerke;
    }
}
