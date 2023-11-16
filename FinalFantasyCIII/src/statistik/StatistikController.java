package statistik;

public class StatistikController {
    private Statistik statistik;

    public StatistikController(){
        statistik = new Statistik();
    }

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
}
