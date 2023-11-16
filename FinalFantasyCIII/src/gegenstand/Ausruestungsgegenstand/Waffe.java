package gegenstand.Ausruestungsgegenstand;

public class Waffe {
    //TODO Reale Implementierung FIX IT!
    private String name;
    private int staerke;

    public String getName() {
        return name;
    }

    public int getStaerke() {
        return staerke;
    }

    public Waffe(String name, int staerke) {
        this.name = name;
        this.staerke = staerke;
    }
}
