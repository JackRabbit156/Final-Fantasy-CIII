package de.bundeswehr.auf.final_fantasy.menu.haendler.controller;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.util.Callback;

/**
 * @author OFR Rieger
 * @since 17.01.24
 */
public class KaufenController {

    private final Haendler haendler;
    private final PartyController partyController;

    /**
     * Der Konstuktor des HaendlerControllers
     *
     * @param partyController der aktuellen Sitzung
     * @author OFR Rieger
     * @since 17.01.24
     */
    public KaufenController(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;
    }

    /**
     * Kauft das übergebene Accessoire
     *
     * @param gegenstand        das gekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass das Accessoire gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass das Accessoire nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public <A extends AusruestungsGegenstand> void ausruestung(A gegenstand, Callback<A, Void> kaufErfolgreich, Callback<A, Void> kaufNichtMoeglich) {
        if (partyController.getPartyGold() >= gegenstand.getKaufwert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(gegenstand);
            haendler.getKaufInventar().ausruestungsgegenstandEntfernen(gegenstand);
            partyController.getParty().setGold(partyController.getPartyGold() - gegenstand.getKaufwert());
            kaufErfolgreich.call(gegenstand);
        }
        else {
            kaufNichtMoeglich.call(gegenstand);
        }
    }

    /**
     * Kauft das übergebene Material
     *
     * @param material          das gekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass das Material gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass das Material nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void material(Material material, Callback<Material, Void> kaufErfolgreich, Callback<Material, Void> kaufNichtMoeglich) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= anzahl * material.getKaufwert() && haendler.getKaufMaterialInventar().get(material).getValue() >= anzahl) {
            partyController.materialHinzufuegen(material, anzahl);
            haendler.getKaufMaterialInventar().get(material).setValue(haendler.getKaufMaterialInventar().get(material).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * material.getKaufwert()));
            kaufErfolgreich.call(material);
        }
        else {
            kaufNichtMoeglich.call(material);
        }
    }

    /**
     * Kauft den übergebenen Verbrauchsgegenstand
     *
     * @param verbrauchsgegenstand der gekauft werden soll
     * @param kaufErfolgreich      Rückmeldung, dass der Verbrauchsgegenstand gekauft wurde
     * @param kaufNichtMoeglich    Rückmeldung, dass der Verbrauchsgegenstand nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void verbrauchsgegenstand(Verbrauchsgegenstand verbrauchsgegenstand, Callback<Verbrauchsgegenstand, Void> kaufErfolgreich, Callback<Verbrauchsgegenstand, Void> kaufNichtMoeglich) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= anzahl * verbrauchsgegenstand.getKaufwert() && haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).getValue() >= anzahl) {
            partyController.verbrauchsgegenstandHinzufuegen(verbrauchsgegenstand, anzahl);
            haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).setValue(haendler.getKaufVerbrauchsInventar().get(verbrauchsgegenstand).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * verbrauchsgegenstand.getKaufwert()));
            kaufErfolgreich.call(verbrauchsgegenstand);
        }
        else {
            kaufNichtMoeglich.call(verbrauchsgegenstand);
        }
    }

}

