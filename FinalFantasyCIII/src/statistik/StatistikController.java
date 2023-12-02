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

    public void goldErhoehen(int gold){
        statistik.setGesamtErwirtschaftetesGold(statistik.getGesamtErwirtschaftetesGold() + gold);
    }

    public void durchgefuehrteKaempfeErhoehen(){
        statistik.setDurchgefuehrteKaempfe(statistik.getDurchgefuehrteKaempfe() +1);
    }

    public void gewonneneKaempfeErhoehen(){
        statistik.setGewonneneKaempfe(statistik.getGewonneneKaempfe()+1);
    }

    public void verloreneKaempfeErhoehen(){
        statistik.setVerloreneKaempfe(statistik.getVerloreneKaempfe()+1);
    }

    public Statistik getStatistik() {
        return statistik;
    }
}
