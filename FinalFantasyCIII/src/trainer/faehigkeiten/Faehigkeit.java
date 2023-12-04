package trainer.faehigkeiten;

import javafx.beans.binding.StringBinding;

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
    public static Faehigkeit faehigkeitAufwerten(Faehigkeit faehigkeit) {
        Faehigkeit neueFaehigkeit = new Faehigkeit(faehigkeit.getName(),
                faehigkeit.getBeschreibung(),
                faehigkeit.getIcon(),
                faehigkeit.getManaKosten(),
                faehigkeit.getLevel(),
                faehigkeit.getLevelAnforderung(),
                faehigkeit.isIstFreundlich(),
                faehigkeit.getEffektStaerke(),
                faehigkeit.getZielAnzahl(),
                faehigkeit.getWahrscheinlichkeit(),
                faehigkeit.getZielAttribut(),
                faehigkeit.getFaehigkeitsTyp());

        neueFaehigkeit.setLevel(faehigkeit.getLevel()+1);
        neueFaehigkeit.setEffektStaerke((int) (faehigkeit.getEffektStaerke() * 1.2));
        neueFaehigkeit.setWahrscheinlichkeit(faehigkeit.getWahrscheinlichkeit() * 1.2);
        faehigkeit.setEffektStaerke((int) (faehigkeit.getEffektStaerke() * 1.2));
        if (faehigkeit.getZielAnzahl() > 1) {
            if (faehigkeit.getZielAnzahl() < 4) {
                neueFaehigkeit.setZielAnzahl(faehigkeit.getZielAnzahl() + 1);
            }
        }
        return neueFaehigkeit;
    }

    // Objekt Methoden
    @Override
    public String toString() {
        return "Hier soll noch eine schoene Ausgabe kommen";
    }

    public String toStringKurz() {
        return "Hier soll moch eine Kurzversion kommen!";
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
