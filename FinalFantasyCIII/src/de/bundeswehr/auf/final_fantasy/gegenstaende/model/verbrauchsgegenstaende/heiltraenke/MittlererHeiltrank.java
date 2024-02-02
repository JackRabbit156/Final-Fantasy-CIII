package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke;

public class MittlererHeiltrank extends Heiltrank{

    public MittlererHeiltrank(){
        this.setName("Mittlerer Heiltrank");
        this.setKaufwert(100);
        this.setVerkaufswert(50);
        this.setHeilwert(20);
        this.setBeschreibung("Heilt den Charakter um 20 HP");
        this.setIcon("/icons/mittlererHeiltrank.png");
    }

}
