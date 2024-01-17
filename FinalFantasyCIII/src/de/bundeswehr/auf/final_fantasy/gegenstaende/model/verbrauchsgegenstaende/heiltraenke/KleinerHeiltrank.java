package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke;

public class KleinerHeiltrank extends Heiltrank {


    public KleinerHeiltrank() {
        this.setName("Kleiner Heiltrank");
        this.setKaufwert(50);
        this.setVerkaufswert(25);
        this.setHeilwert(10);
        this.setBeschreibung("Heilt den Charakter um 10 HP");
        this.setIcon("/icons/kleinerHeiltrank.png");
    }
}
