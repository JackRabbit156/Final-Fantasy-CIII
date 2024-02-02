package de.bundeswehr.auf.final_fantasy.menu.speicherstand;

import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.statistik.Statistik;

public class Speicherstand {

    private final Party party;
    private final String schwierigkeitsgrad;
    private final boolean hardcore;
    private final Statistik statistik;

    public Speicherstand(Party party, String schwierigkeitsgrad, boolean hardcore, Statistik statistik) {
        this.party = party;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.hardcore = hardcore;
        this.statistik = statistik;
    }

    public Party getParty() {
        return party;
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public Statistik getStatistik() {
        return statistik;
    }

}
