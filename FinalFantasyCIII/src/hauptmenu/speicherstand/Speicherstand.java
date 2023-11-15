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
}
