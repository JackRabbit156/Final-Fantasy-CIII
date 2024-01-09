package de.bundeswehr.auf.final_fantasy.menu.hauptmenu.speicherstand;

import de.bundeswehr.auf.final_fantasy.party.Party;
import de.bundeswehr.auf.final_fantasy.statistik.Statistik;

public class Speicherstand {
    private Party party;
    private String schwierigkeitsgrad;
    private boolean hardcore;
    private Statistik statistik;

    public Speicherstand(Party party, String schwierigkeitsgrad, boolean hardcore, Statistik statistik) {
        this.party = party;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.hardcore = hardcore;
        this.statistik = statistik;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public Statistik getStatistik() {
        return statistik;
    }
}
