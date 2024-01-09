package de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke;

public class MittlererHeiltrank extends Heiltrank{

    public MittlererHeiltrank(){
        this.setName("Mittlerer Heiltrank");
        this.setKaufwert(100);
        this.setVerkaufswert(50);
        this.setHeilwert(20);
        this.setBeschereibung("Heilt den Charakter um 20HP");
        this.setIcon("/icons/mittlererHeiltrank.png");
    }
}
