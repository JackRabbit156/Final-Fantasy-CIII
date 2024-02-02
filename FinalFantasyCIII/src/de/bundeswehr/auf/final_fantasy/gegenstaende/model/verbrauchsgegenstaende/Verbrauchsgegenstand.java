package de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.GrosserHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.KleinerHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.heiltraenke.MittlererHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.GrosserManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.KleinerManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.manatraenke.MittlererManatrank;

public abstract class Verbrauchsgegenstand extends Gegenstand {

    private String beschreibung;

    /**
     * Wendet den Gegenstand auf den Charakter an
     * @param spielerCharakter zielCharakter
     *
     * @since 18.11.2023
     * @author Lang
     */
    public abstract void gegenstandVerwenden(SpielerCharakter spielerCharakter);

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

}
