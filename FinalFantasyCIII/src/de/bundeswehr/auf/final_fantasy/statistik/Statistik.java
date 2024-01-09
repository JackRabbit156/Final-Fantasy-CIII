package de.bundeswehr.auf.final_fantasy.statistik;

public class Statistik {
    private int gesamtErwirtschaftetesGold;
    private int durchgefuehrteKaempfe;
    private int gewonneneKaempfe;
    private int verloreneKaempfe;


    /**
     * Constructor für neues Spiel
     * @author Nick
     * @since 02.12.2023
     */
    public Statistik(){
        gesamtErwirtschaftetesGold = 200;
        durchgefuehrteKaempfe = 0;
        gewonneneKaempfe = 0;
        verloreneKaempfe = 0;
    }

    /**
     * Constructor für Spiel laden
     * @param gesamtErwirtschaftetesGold inklusive Startgold
     * @param durchgefuehrteKaempfe alle Kaempfe
     * @param gewonneneKaempfe gewonnene Kaempfe
     * @param verloreneKaempfe verlorene Kaempfe
     * @author Nick
     * @since 02.12.2023
     */
    public Statistik(int gesamtErwirtschaftetesGold, int durchgefuehrteKaempfe, int gewonneneKaempfe, int verloreneKaempfe) {
        this.gesamtErwirtschaftetesGold = gesamtErwirtschaftetesGold;
        this.durchgefuehrteKaempfe = durchgefuehrteKaempfe;
        this.gewonneneKaempfe = gewonneneKaempfe;
        this.verloreneKaempfe = verloreneKaempfe;
    }

    public int getGesamtErwirtschaftetesGold() {
        return gesamtErwirtschaftetesGold;
    }

    public void setGesamtErwirtschaftetesGold(int gesamtErwirtschaftetesGold) {
        this.gesamtErwirtschaftetesGold = gesamtErwirtschaftetesGold;
    }

    public int getDurchgefuehrteKaempfe() {
        return durchgefuehrteKaempfe;
    }

    public void setDurchgefuehrteKaempfe(int durchgefuehrteKaempfe) {
        this.durchgefuehrteKaempfe = durchgefuehrteKaempfe;
    }

    public int getGewonneneKaempfe() {
        return gewonneneKaempfe;
    }

    public void setGewonneneKaempfe(int gewonneneKaempfe) {
        this.gewonneneKaempfe = gewonneneKaempfe;
    }

    public int getVerloreneKaempfe() {
        return verloreneKaempfe;
    }

    public void setVerloreneKaempfe(int verloreneKaempfe) {
        this.verloreneKaempfe = verloreneKaempfe;
    }
}
