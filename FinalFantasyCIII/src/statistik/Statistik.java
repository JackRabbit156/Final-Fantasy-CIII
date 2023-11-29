package statistik;

public class Statistik {
    private int gesamtErwirtschaftetesGold;
    private int durchgefuehrteKaempfe;
    private int gewonneneKaempfe;
    private int verloreneKaempfe;

    public Statistik(){
        gesamtErwirtschaftetesGold = 0;
        durchgefuehrteKaempfe = 0;
        gewonneneKaempfe = 0;
        verloreneKaempfe = 0;
    }

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
