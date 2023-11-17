package gegenstand;

public abstract class Gegenstand {
    private String name;
    private int kaufwert;
    private int verkaufswert;

    public Gegenstand() {
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getKaufwert() {
        return kaufwert;
    }

    public int getVerkaufswert() {
        return verkaufswert;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setKaufwert(int kaufwert) {
        this.kaufwert = kaufwert;
    }

    public void setVerkaufswert(int verkaufswert) {
        this.verkaufswert = verkaufswert;
    }
}
