package de.bundeswehr.auf.final_fantasy.gegenstaende.model;

import java.util.Objects;

public abstract class Gegenstand {

    private String icon;
    private int kaufwert;
    private String name;
    private int verkaufswert;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gegenstand that = (Gegenstand) o;
        return kaufwert == that.kaufwert &&
                verkaufswert == that.verkaufswert &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, kaufwert, name, verkaufswert);
    }

    public String getIcon() {
        return icon;
    }

    public int getKaufwert() {
        return kaufwert;
    }

    public String getName() {
        return name;
    }

    public int getVerkaufswert() {
        return verkaufswert;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setKaufwert(int kaufwert) {
        this.kaufwert = kaufwert;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVerkaufswert(int verkaufswert) {
        this.verkaufswert = verkaufswert;
    }

}
