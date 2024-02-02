package de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model;

public class Faehigkeit {

    public static final String TYP_MAGISCH = "magisch";
    public static final String TYP_PHYSISCH = "physisch";

    // Allgemeine Fähigkeiten
    private String name;
    private String beschreibung;
    private String icon;
    private int manaKosten;
    private int level;
    private int levelAnforderung;
    private final boolean istFreundlich;

    // Attribute für spezielle Fähigkeiten
    private int effektStaerke;
    private int zielAnzahl;
    private double wahrscheinlichkeit;
    private String zielAttribut;
    private final String faehigkeitsTyp;

    public Faehigkeit(String name, String beschreibung, String icon, int manaKosten, int level, int levelAnforderung,
                      boolean istFreundlich, int effektStaerke, int zielAnzahl, double wahrscheinlichkeit, String zielAttribut,
                      String faehigkeitsTyp) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.manaKosten = manaKosten;
        this.level = level;
        this.levelAnforderung = levelAnforderung;
        this.istFreundlich = istFreundlich;
        this.effektStaerke = effektStaerke;
        this.zielAnzahl = zielAnzahl;
        this.wahrscheinlichkeit = wahrscheinlichkeit;
        this.zielAttribut = zielAttribut;
        this.faehigkeitsTyp = faehigkeitsTyp;
    }

    /**
     * Wertet die eingegebene Fähigkeit auf und gibt diese zurück. Dabei wird die Wahrscheinlichkeit, Zielanzahl, Stärke und das Level angehoben.
     *
     * @param faehigkeit: Fähigkeit, die aufgewertet werden soll
     * @return : Gibt die aufgewertete Fähigkeit zurück.
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     */
    public static Faehigkeit faehigkeitAufwerten(Faehigkeit faehigkeit) {
        faehigkeit.setLevel(faehigkeit.getLevel() + 1);
        faehigkeit.setEffektStaerke((int) (faehigkeit.getEffektStaerke() * 1.05));
        if (faehigkeit.getWahrscheinlichkeit() > 1) {
            faehigkeit.setWahrscheinlichkeit(faehigkeit.getWahrscheinlichkeit() * 1.05);
        }
        // Level 10: 2 -> 3, Level 20: 3 -> 4
        if (faehigkeit.getZielAnzahl() > 1 && faehigkeit.getZielAnzahl() < 4) {
            switch (faehigkeit.getLevel()) {
                case 10:
                    faehigkeit.setZielAnzahl(3);
                    break;
                case 20:
                    faehigkeit.setZielAnzahl(4);
                    break;
            }
        }
        return faehigkeit;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getIcon() {
        return icon;
    }

    public int getManaKosten() {
        return manaKosten;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelAnforderung() {
        return levelAnforderung;
    }

    public boolean isIstFreundlich() {
        return istFreundlich;
    }

    public int getEffektStaerke() {
        return effektStaerke;
    }

    public int getZielAnzahl() {
        return zielAnzahl;
    }

    public double getWahrscheinlichkeit() {
        return wahrscheinlichkeit;
    }

    public String getZielAttribut() {
        return zielAttribut;
    }

    public String getFaehigkeitsTyp() {
        return faehigkeitsTyp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setManaKosten(int manaKosten) {
        this.manaKosten = manaKosten;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLevelAnforderung(int levelAnforderung) {
        this.levelAnforderung = levelAnforderung;
    }

    public void setEffektStaerke(int effektStaerke) {
        this.effektStaerke = effektStaerke;
    }

    public void setZielAnzahl(int zielAnzahl) {
        this.zielAnzahl = zielAnzahl;
    }

    public void setWahrscheinlichkeit(double wahrscheinlichkeit) {
        this.wahrscheinlichkeit = wahrscheinlichkeit;
    }

    public void setZielAttribut(String zielAttribut) {
        this.zielAttribut = zielAttribut;
    }

    @Override
    public String toString() {
        return name +
                " {" +
                "MP=" + manaKosten +
                ", lvl=" + level +
                ", anforderung=" + levelAnforderung +
                ", freundlich=" + istFreundlich +
                ", staerke=" + effektStaerke +
                ", ziele=" + zielAnzahl +
                ", wahrscheinlichkeit=" + wahrscheinlichkeit +
                ", attribut=" + zielAttribut +
                ", typ=" + faehigkeitsTyp +
                '}';
    }

}
