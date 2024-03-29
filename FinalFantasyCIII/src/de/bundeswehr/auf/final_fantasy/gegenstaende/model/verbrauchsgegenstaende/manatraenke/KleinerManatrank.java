package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke;

public class KleinerManatrank extends Manatrank{

    public KleinerManatrank() {
        this.setName("Kleiner Manatrank");
        this.setKaufwert(50);
        this.setVerkaufswert(25);
        this.setManaRegeneration(10);
        this.setBeschreibung("Regeneriert dem Charakter um 10 MP");
        this.setIcon("/icons/kleinerManatrank.png");
    }

}
