package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.GrosserHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.KleinerHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.MittlererHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.GrosserManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.KleinerManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.MittlererManatrank;

public final class Verbrauchsgegenstaende {

    public static final KleinerHeiltrank KLEINER_HEILTRANK = new KleinerHeiltrank();
    public static final MittlererHeiltrank MITTLERER_HEILTRANK = new MittlererHeiltrank();
    public static final GrosserHeiltrank GROSSER_HEILTRANK  = new GrosserHeiltrank();
    public static final KleinerManatrank KLEINER_MANATRANK = new KleinerManatrank();
    public static final MittlererManatrank MITTLERER_MANATRANK = new MittlererManatrank();
    public static final GrosserManatrank GROSSER_MANATRANK = new GrosserManatrank();

    private Verbrauchsgegenstaende() {
    }

}
