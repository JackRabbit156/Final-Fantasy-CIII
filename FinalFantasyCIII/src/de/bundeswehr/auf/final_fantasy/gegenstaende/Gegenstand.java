package de.bundeswehr.auf.final_fantasy.gegenstaende;

public abstract class Gegenstand {
    private String name;
    private int kaufwert;
    private int verkaufswert;
    private String icon;

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

    public String getIcon() {
        return icon;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
