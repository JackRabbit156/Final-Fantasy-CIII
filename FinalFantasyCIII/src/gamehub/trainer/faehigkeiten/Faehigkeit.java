package gamehub.trainer.faehigkeiten;

public class Faehigkeit {
    //Allgemeine Faehigkeiten
    private String name;
    private String beschreibung;
    private int manaKosten;
    private int level;
    private int levelAnforderung;
    private boolean istFreundlich;

    //Attribute für spezielle Faehigkeiten
    private int effektGroesse;
    private int zielAnzahl;
    private int dauer;
    private double wahrscheinlichkeit;
    private String zielAttribut;

    public Faehigkeit(String name, String beschreibung, int manaKosten, int level, int levelAnforderung, boolean istFreundlich,
                      int effektGroesse, int zielAnzahl, int dauer, double wahrscheinlichkeit, String zielAttribut) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.manaKosten = manaKosten;
        this.level = level;
        this.levelAnforderung = levelAnforderung;
        this.istFreundlich = istFreundlich;
        this.effektGroesse = effektGroesse;
        this.zielAnzahl = zielAnzahl;
        this.dauer = dauer;
        this.wahrscheinlichkeit = wahrscheinlichkeit;
        this.zielAttribut = zielAttribut;
    }

    //Methoden
    public static void faehigkeitAufwerten(Faehigkeit faehigkeit) {
        faehigkeit.level++;
        faehigkeit.effektGroesse = (int) (faehigkeit.effektGroesse * 1.2);
        if (faehigkeit.zielAnzahl > 1) {
            faehigkeit.zielAnzahl++;
        }
        if (faehigkeit.dauer > 1) {
            faehigkeit.dauer++;
        }
        if (faehigkeit.wahrscheinlichkeit > 1) {
            faehigkeit.wahrscheinlichkeit = (int) (faehigkeit.wahrscheinlichkeit * 1.2);
        }
    }

    //Getter
    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
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

    public int getEffektGroesse() {
        return effektGroesse;
    }

    public int getZielAnzahl() {
        return zielAnzahl;
    }

    public int getDauer() {
        return dauer;
    }

    public double getWahrscheinlichkeit() {
        return wahrscheinlichkeit;
    }

    public String getZielAttribut() {
        return zielAttribut;
    }
}
