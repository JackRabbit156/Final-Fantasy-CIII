package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke;

public class GrosserHeiltrank extends Heiltrank{

    public GrosserHeiltrank() {
        this.setName("Grosser Heiltrank");
        this.setKaufwert(150);
        this.setVerkaufswert(75);
        this.setHeilwert(30);
        this.setBeschreibung("Heilt den Charakter um 30 HP");
        this.setIcon("/icons/grosserHeiltrank.png");
    }

}

