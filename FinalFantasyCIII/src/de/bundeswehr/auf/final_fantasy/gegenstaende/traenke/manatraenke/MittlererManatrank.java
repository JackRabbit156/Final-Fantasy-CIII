package de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke;

public class MittlererManatrank extends Manatrank{

    public MittlererManatrank() {
        this.setName("Mittlerer Manatrank");
        this.setKaufwert(100);
        this.setVerkaufswert(50);
        this.setManaregenartion(20);
        this.setBeschereibung("Regeneriert dem Charakter um 20MP");
        this.setIcon("/icons/mittlererManatrank.png");
    }
}
