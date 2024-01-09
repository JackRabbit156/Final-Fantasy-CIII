package de.bundeswehr.auf.final_fantasy.gegenstaende.traenke;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.Gegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.GrosserHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.KleinerHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.MittlererHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.GrosserManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.KleinerManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.MittlererManatrank;

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
