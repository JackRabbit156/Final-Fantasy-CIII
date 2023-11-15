package hauptmenu.speicherstand;

import party.Party;

public class Speicherstand {
    private Party party;
    private String schwierigkeitsgrad;
    private boolean hardcore;

    public Speicherstand(Party party, String schwierigkeitsgrad, boolean hardcore) {
        this.party = party;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.hardcore = hardcore;
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
}
