package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke;

public class MittlererManatrank extends Manatrank{

    public MittlererManatrank() {
        this.setName("Mittlerer Manatrank");
        this.setKaufwert(100);
        this.setVerkaufswert(50);
        this.setManaRegeneration(20);
        this.setBeschreibung("Regeneriert dem Charakter um 20 MP");
        this.setIcon("/icons/mittlererManatrank.png");
    }
}
