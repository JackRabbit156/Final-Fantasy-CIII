package de.bundeswehr.auf.final_fantasy.menu.haendler.controller;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.util.Callback;

/**
 * @author OFR Rieger
 * @since 17.01.24
 */
public class ZurueckKaufenController {

    private final Haendler haendler;
    private final PartyController partyController;

    /**
     * Der Konstuktor des HaendlerControllers
     *
     * @param partyController der aktuellen Sitzung
     * @author OFR Rieger
     * @since 17.01.2024
     */
    public ZurueckKaufenController(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;
    }

    /**
     * Kauft das übergebene Accessoire vom Haendler zurück
     *
     * @param accessoire        das zurückgekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass das Accessoire gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass das Accessoire nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void accessoire(Accessoire accessoire, Callback<Accessoire, Void> kaufErfolgreich, Callback<Accessoire, Void> kaufNichtMoeglich) {
        if (partyController.getPartyGold() >= accessoire.getVerkaufswert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(accessoire);
            haendler.getHistorieAccessoire().remove(accessoire);
            partyController.getParty().setGold(partyController.getPartyGold() - accessoire.getVerkaufswert());
            kaufErfolgreich.call(accessoire);
        }
        else {
            kaufNichtMoeglich.call(accessoire);
        }
    }

    /**
     * Kauft das übergebene Material vom Haendler zurück
     *
     * @param material          das gekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass das Material gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass das Material nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void material(Material material, Callback<Material, Void> kaufErfolgreich, Callback<Material, Void> kaufNichtMoeglich) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= anzahl * material.getVerkaufswert() && haendler.getHistorieMaterial().get(material).getValue() >= anzahl) {
            partyController.materialHinzufuegen(material, anzahl);
            haendler.getHistorieMaterial().get(material).setValue(haendler.getHistorieMaterial().get(material).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * material.getVerkaufswert()));
            kaufErfolgreich.call(material);
        }
        else {
            kaufNichtMoeglich.call(material);
        }
    }

    /**
     * Kauft die übergebene Rüstung vom Haendler zurück
     *
     * @param ruestung          die zurückgekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass die Rüstung gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass die Rüstung nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void ruestung(Ruestung ruestung, Callback<Ruestung, Void> kaufErfolgreich, Callback<Ruestung, Void> kaufNichtMoeglich) {
        if (partyController.getPartyGold() >= ruestung.getVerkaufswert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(ruestung);
            haendler.getHistorieRuestung().remove(ruestung);
            partyController.getParty().setGold(partyController.getPartyGold() - ruestung.getVerkaufswert());
            kaufErfolgreich.call(ruestung);
        }
        else {
            kaufNichtMoeglich.call(ruestung);
        }
    }

    /**
     * Kauft den übergebenen Verbrauchsgegenstand vom Händler zurück
     *
     * @param verbrauchsgegenstand der gekauft werden soll
     * @param kaufErfolgreich      Rückmeldung, dass der Trank gekauft wurde
     * @param kaufNichtMoeglich    Rückmeldung, dass der Trank nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 05.12.23
     */
    public void verbrauchsgegenstand(Verbrauchsgegenstand verbrauchsgegenstand, Callback<Verbrauchsgegenstand, Void> kaufErfolgreich, Callback<Verbrauchsgegenstand, Void> kaufNichtMoeglich) {
        int anzahl = 1;
        if (partyController.getPartyGold() >= anzahl * verbrauchsgegenstand.getVerkaufswert() && haendler.getHistorieVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() >= anzahl) {
            partyController.verbrauchsgegenstandHinzufuegen(verbrauchsgegenstand, anzahl);
            haendler.getHistorieVerbrauchsgegenstaende().get(verbrauchsgegenstand).setValue(haendler.getHistorieVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() - anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() - (anzahl * verbrauchsgegenstand.getVerkaufswert()));
            kaufErfolgreich.call(verbrauchsgegenstand);
        }
        else {
            kaufNichtMoeglich.call(verbrauchsgegenstand);
        }
    }

    /**
     * Kauft die übergebene Waffe vom Haendler zurück
     *
     * @param waffe             die zurückgekauft werden soll
     * @param kaufErfolgreich   Rückmeldung, dass die Waffe gekauft wurde
     * @param kaufNichtMoeglich Rückmeldung, dass die Waffe nicht gekauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void waffe(Waffe waffe, Callback<Waffe, Void> kaufErfolgreich, Callback<Waffe, Void> kaufNichtMoeglich) {
        if (partyController.getPartyGold() >= waffe.getVerkaufswert()) {
            partyController.getParty().getAusruestungsgegenstandInventar().ausruestungsgegenstandHinzufuegen(waffe);
            haendler.getHistorieWaffe().remove(waffe);
            partyController.getParty().setGold(partyController.getPartyGold() - waffe.getVerkaufswert());
            kaufErfolgreich.call(waffe);
        }
        else {
            kaufNichtMoeglich.call(waffe);
        }
    }

}

