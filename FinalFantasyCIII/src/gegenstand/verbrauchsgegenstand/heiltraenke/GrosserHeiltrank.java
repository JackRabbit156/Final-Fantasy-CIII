package gegenstand.verbrauchsgegenstand.heiltraenke;

public class GrosserHeiltrank extends Heiltrank{

    public GrosserHeiltrank() {
        this.setName("Grosser Heiltrank");
        this.setKaufwert(150);
        this.setVerkaufswert(75);
        this.setHeilwert(30);
        this.setBeschereibung("Heilt den Charakter um 30HP");
    }
}

