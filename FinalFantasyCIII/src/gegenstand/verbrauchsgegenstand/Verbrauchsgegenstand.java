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
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final KleinerHeiltrank KLEINER_HEILTRANK = new KleinerHeiltrank();
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final MittlererHeiltrank MITTLERER_HEILTRANK = new MittlererHeiltrank();
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final GrosserHeiltrank GROSSER_HEILTRANK  = new GrosserHeiltrank();
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final KleinerManatrank KLEINER_MANATRANK = new KleinerManatrank();
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final MittlererManatrank MITTLERER_MANATRANK = new MittlererManatrank();
    /**
     * Singleton zur Nutzung der Verbrauchsgegenstände in der Map
     * @author Nick
     * @since 06.12.2023
     */
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
