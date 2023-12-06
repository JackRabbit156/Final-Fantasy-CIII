package gegenstand.verbrauchsgegenstand;

import charakter.model.SpielerCharakter;
import gegenstand.Gegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.MittlererHeiltrank;
import gegenstand.verbrauchsgegenstand.manatraenke.GrosserManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.KleinerManatrank;
import gegenstand.verbrauchsgegenstand.manatraenke.MittlererManatrank;

public abstract class Verbrauchsgegenstand extends Gegenstand {
    public static final KleinerHeiltrank KLEINER_HEILTRANK = new KleinerHeiltrank();
    public static final MittlererHeiltrank MITTLERER_HEILTRANK = new MittlererHeiltrank();
    public static final GrosserHeiltrank GROSSER_HEILTRANK  = new GrosserHeiltrank();
    public static final KleinerManatrank KLEINER_MANATRANK = new KleinerManatrank();
    public static final MittlererManatrank MITTLERER_MANATRANK = new MittlererManatrank();
    public static final GrosserManatrank GROSSER_MANATRANK = new GrosserManatrank();


    private String beschereibung;


    /**
     * Wendet den Gegenstand auf den Charakter an
     * @param spielerCharakter zielCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public abstract void gegenstandVerwenden(SpielerCharakter spielerCharakter);

    public String getBeschereibung() {
        return beschereibung;
    }

    public void setBeschereibung(String beschereibung) {
        this.beschereibung = beschereibung;
    }
}
