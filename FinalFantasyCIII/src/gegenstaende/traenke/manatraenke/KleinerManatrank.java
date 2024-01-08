package gegenstaende.traenke.manatraenke;

public class KleinerManatrank extends Manatrank{

    public KleinerManatrank() {
        this.setName("Kleiner Manatrank");
        this.setKaufwert(50);
        this.setVerkaufswert(25);
        this.setManaregenartion(10);
        this.setBeschereibung("Regeneriert dem Charakter um 10MP");
        this.setIcon("/icons/kleinerManatrank.png");
    }
}
