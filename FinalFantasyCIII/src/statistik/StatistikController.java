package statistik;

public class StatistikController {
    private Statistik statistik;

    /**
     * Constructor zur Erstellung eines neuen Spiels
     * @author Nick
     * @since 02.12.2023
     */
    public StatistikController(){
        statistik = new Statistik();
    }

    /**
     * Constructor für aus der DB geladene Statistiken
     * @param statistik die aus dem Speicherstand geladene Statistik
     * @author Nick
     * @since 02.12.2023
     */
    public StatistikController(Statistik statistik){
        this.statistik = statistik;
    }

    /**
     * Funktion zum erhöhen des Goldes in der Statistik
     * @param gold hinzugekommenes Gold
     * @author Nick
     * @since 02.12.2023
     */
    public void goldErhoehen(int gold){
        statistik.setGesamtErwirtschaftetesGold(statistik.getGesamtErwirtschaftetesGold() + gold);
    }

    /**
     * Funktion zum erhöhen der Durchgeführten Kämpfe
     * Erhöht je Aufruf um 1;
     * @author Nick
     * @since 02.12.2023
     */
    public void durchgefuehrteKaempfeErhoehen(){
        statistik.setDurchgefuehrteKaempfe(statistik.getDurchgefuehrteKaempfe() +1);
    }
    /**
     * Funktion zum erhöhen der gewonnenen Kämpfe
     * Erhöht je Aufruf um 1;
     * @author Nick
     * @since 02.12.2023
     */
    public void gewonneneKaempfeErhoehen(){
        statistik.setGewonneneKaempfe(statistik.getGewonneneKaempfe()+1);
    }
    /**
     * Funktion zum erhöhen der verlorenen Kämpfe
     * Erhöht je Aufruf um 1;
     * @author Nick
     * @since 02.12.2023
     */
    public void verloreneKaempfeErhoehen(){
        statistik.setVerloreneKaempfe(statistik.getVerloreneKaempfe()+1);
    }

    public Statistik getStatistik() {
        return statistik;
    }
}
