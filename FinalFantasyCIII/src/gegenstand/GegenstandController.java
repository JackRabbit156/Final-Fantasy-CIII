package gegenstand;

import charakter.model.SpielerCharakter;
import gegenstand.verbrauchsgegenstand.Manatränke.GrosserManatrank;
import gegenstand.verbrauchsgegenstand.Manatränke.KleinerManatrank;
import gegenstand.verbrauchsgegenstand.Manatränke.MittlererManatrank;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.MittlererHeiltrank;

import java.util.HashMap;

public class GegenstandController{

    private static final Verbrauchsgegenstand[] VERBRAUCHSGEGENSTAENDE = {new KleinerHeiltrank(), new MittlererHeiltrank(),
            new GrosserHeiltrank(), new KleinerManatrank(), new MittlererManatrank(),
            new GrosserManatrank()};

    /**
     * Gibt alle Verbrauchsgegenstaende als Array zurueck
     * @return Verbrauchsgegenstand[]
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static Verbrauchsgegenstand[] rueckgabeAllerVerbrauchsgegenstaende(){return VERBRAUCHSGEGENSTAENDE;}

    /**
     * Gibt einen spezifischen Verbrauchsgegenstand zurueck
     * @param name
     * @return Verbrauchsgegenstand
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static Verbrauchsgegenstand rueckgabeSpezifischerVerbrauchsgegenstand(String name){
        Verbrauchsgegenstand rueckgabe = null;
        for (Verbrauchsgegenstand verbrauchsgegenstand : VERBRAUCHSGEGENSTAENDE) {
            if (verbrauchsgegenstand.getName().equals(name)){
                rueckgabe = verbrauchsgegenstand;
            }
        }
        return rueckgabe;
    }

    /**
     * Verwendet einen Verbrauchsgegenstand reduziert die Anzahl der verfügbaren Verbrauchsgegenstaende um 1
     * Sollte die Anzahl 0 erreichen wird der Verbrauchsgegenstand aus der HashMap geloescht
     * @param verbrauchsgegenstaende
     * @param verwendeterVerbGgst
     * @param spielerCharakter
     * @return HashMap<Verbrauchsgegenstand, Integer>
     *
     * @since 18.11.2023
     * @author Lang
     */
    public static HashMap<Verbrauchsgegenstand, Integer> verwendeVerbrauchsgegenstand(HashMap<Verbrauchsgegenstand, Integer> verbrauchsgegenstaende,
                                                                               Verbrauchsgegenstand verwendeterVerbGgst,
                                                                               SpielerCharakter spielerCharakter){
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst) > 0){
            verwendeterVerbGgst.gegenstandVerwenden(spielerCharakter);
            verbrauchsgegenstaende.put(verwendeterVerbGgst, verbrauchsgegenstaende.get(verwendeterVerbGgst) - 1);
        }
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst) == 0){
            verbrauchsgegenstaende.remove(verwendeterVerbGgst);
        }
        return verbrauchsgegenstaende;
    }
}
