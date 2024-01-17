package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke;

public class GrosserManatrank extends Manatrank {

    public GrosserManatrank() {
        this.setName("Grosser Manatrank");
        this.setKaufwert(150);
        this.setVerkaufswert(75);
        this.setManaRegeneration(30);
        this.setBeschreibung("Regeneriert dem Charakter um 30 MP");
        this.setIcon("/icons/grosserManatrank.png");
    }
}
