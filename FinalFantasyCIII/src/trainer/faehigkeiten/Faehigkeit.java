package trainer.faehigkeiten;

public class Faehigkeit {
    // Allgemeine Faehigkeiten
    private String name;
    private String beschreibung;
    private String icon;
    private int manaKosten;
    private int level;
    private int levelAnforderung;
    private boolean istFreundlich;

    // Attribute für spezielle Faehigkeiten
    private int effektStaerke;
    private int zielAnzahl;
    private double wahrscheinlichkeit;
    private String zielAttribut;
    private String faehigkeitsTyp;

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

    // Eigene Methoden
    /**
     * Wertet die eingegebene Faehigkeit auf und gibt diese zurueck. Dabei wird die Wahrscheinlichkeit, Zielanzahl, Staerke und das Level angehoben.
     * @param faehigkeit: Faehigkeit, die aufgewertet werden soll
     * @return : Gibt die aufgewertete Faehigkeit zurueck.
     * @author 11777914 OLt Oliver Ebert
     * @since 05.12.2023
     */
    public static Faehigkeit faehigkeitAufwerten(Faehigkeit faehigkeit) {
        faehigkeit.setLevel(faehigkeit.getLevel() + 1);
        faehigkeit.setEffektStaerke((int) (faehigkeit.getEffektStaerke() * 1.05));
        faehigkeit.setEffektStaerke((int) (faehigkeit.getEffektStaerke() * 1.05));
        if (faehigkeit.getWahrscheinlichkeit() > 1) {
            faehigkeit.setWahrscheinlichkeit(faehigkeit.getWahrscheinlichkeit() * 1.05);
        }
        if (faehigkeit.getZielAnzahl() > 1) {
            if (faehigkeit.getZielAnzahl() < 4) {
                faehigkeit.setZielAnzahl(faehigkeit.getZielAnzahl() + 1);
            }
        }
        return faehigkeit;
    }

    // Getter
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

    //Setter

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

    public void setIstFreundlich(boolean istFreundlich) {
        this.istFreundlich = istFreundlich;
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

    public void setFaehigkeitsTyp(String faehigkeitsTyp) {
        this.faehigkeitsTyp = faehigkeitsTyp;
    }
}
