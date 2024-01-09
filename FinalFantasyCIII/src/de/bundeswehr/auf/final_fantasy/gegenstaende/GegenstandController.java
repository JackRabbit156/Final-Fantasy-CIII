package de.bundeswehr.auf.final_fantasy.gegenstaende;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.material.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.GrosserHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.KleinerHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.heiltraenke.MittlererHeiltrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.GrosserManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.KleinerManatrank;
import de.bundeswehr.auf.final_fantasy.gegenstaende.traenke.manatraenke.MittlererManatrank;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

import java.util.Map;

public class GegenstandController {

    private static final Verbrauchsgegenstand[] VERBRAUCHSGEGENSTAENDE = {new KleinerHeiltrank(),
            new MittlererHeiltrank(), new GrosserHeiltrank(), new KleinerManatrank(), new MittlererManatrank(),
            new GrosserManatrank()};
    private static final Material[] MATERIALIEN = {new Eisenerz(), new Golderz(), new Silbererz(), new Mithril(),
            new Schleim(), new Popel()};

    /**
     * Gibt alle Verbrauchsgegenstaende als Array zurueck
     *
     * @return Verbrauchsgegenstand[]
     *
     * @author Lang
     * @since 18.11.2023
     */
    public static Verbrauchsgegenstand[] rueckgabeAllerVerbrauchsgegenstaende() {
        return VERBRAUCHSGEGENSTAENDE;
    }

    /**
     * Gibt einen spezifischen Verbrauchsgegenstand zurueck
     *
     * @param name name
     *
     * @return Verbrauchsgegenstand
     *
     * @author Lang
     * @since 18.11.2023
     */
    public static Verbrauchsgegenstand rueckgabeSpezifischerVerbrauchsgegenstand(String name) {
        Verbrauchsgegenstand rueckgabe = null;
        for (Verbrauchsgegenstand verbrauchsgegenstand : VERBRAUCHSGEGENSTAENDE) {
            if (verbrauchsgegenstand.getName().equals(name)) {
                rueckgabe = verbrauchsgegenstand;
            }
        }
        return rueckgabe;
    }

    /**
     * Verwendet einen Verbrauchsgegenstand reduziert die Anzahl der verfügbaren
     * Verbrauchsgegenstaende um 1 Sollte die Anzahl 0 erreichen wird der
     * Verbrauchsgegenstand aus der HashMap geloescht
     *
     * @param verbrauchsgegenstaende Map
     * @param verwendeterVerbGgst Verbrauchsgegenstand
     * @param spielerCharakter zielCharakter
     *
     * @return HashMap&lt;Verbrauchsgegenstand, Integer&gt;
     *
     * @author Lang
     * @since 18.11.2023
     */
    public static Map<Verbrauchsgegenstand, IntegerProperty> verwendeVerbrauchsgegenstand(
            Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende, Verbrauchsgegenstand verwendeterVerbGgst,
            SpielerCharakter spielerCharakter) {
        IntegerProperty updatedValue = new SimpleIntegerProperty(1);
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() > 0) {
            verwendeterVerbGgst.gegenstandVerwenden(spielerCharakter);
            updatedValue.set(verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() - 1);
            verbrauchsgegenstaende.put(verwendeterVerbGgst, updatedValue);
        }
        return verbrauchsgegenstaende;
    }

    public static Map<Verbrauchsgegenstand, IntegerProperty> verwendeVerbrauchsgegenstandInventar(
            Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende, Verbrauchsgegenstand verwendeterVerbGgst,
            SpielerCharakter spielerCharakter, PartyController partyController) {
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst).getValue() > 0) {
            verwendeterVerbGgst.gegenstandVerwenden(spielerCharakter);
            partyController.verbrauchsgegenstandEntnehmen(verwendeterVerbGgst, 1);
        }
        return verbrauchsgegenstaende;
    }

    public static Material[] rueckgabeAllerMaterialien() {
        return MATERIALIEN;
    }

    public static Material rueckgabeSpezifischerMaterialien(String name) {
        Material rueckgabe = null;
        for (Material material : MATERIALIEN) {
            if (material.getName().equals(name)) {
                rueckgabe = material;
            }
        }
        return rueckgabe;
    }

    public static Map<Material, Integer> materialVerwenden(Map<Material, Integer> map, Material material, int anzahl) {
        map.put(material, map.get(material) - anzahl);
        return map;
    }
}
