package de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke;

public class GrosserManatrank extends Manatrank {

    public GrosserManatrank() {
        this.setName("Grosser Manatrank");
        this.setKaufwert(150);
        this.setVerkaufswert(75);
        this.setManaregenartion(30);
        this.setBeschereibung("Regeneriert dem Charakter um 30MP");
        this.setIcon("/icons/grosserManatrank.png");
    }
}
