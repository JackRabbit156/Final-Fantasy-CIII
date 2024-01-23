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
public class VerkaufenController {

    private final Haendler haendler;
    private final PartyController partyController;

    /**
     * Der Konstuktor des HaendlerControllers
     *
     * @param partyController der aktuellen Sitzung
     * @author OFR Rieger
     * @since 17.01.24
     */
    public VerkaufenController(PartyController partyController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;
    }

    /**
     * verkauft das übergebene Accessoire an den Haendler
     *
     * @param accessoire         das verkauft werden soll
     * @param verkaufErfolgreich Rückmeldung, dass das Accessoire verkauft wurde
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void accessoire(Accessoire accessoire, Callback<Accessoire, Void> verkaufErfolgreich) {
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessoire().remove(accessoire);
        haendler.getHistorieAccessoire().add(accessoire);
        partyController.getParty().setGold(partyController.getPartyGold() + accessoire.getVerkaufswert());
        verkaufErfolgreich.call(accessoire);
    }

    /**
     * verkauft das übergebene Material
     *
     * @param material           das gekauft werden soll
     * @param verkaufErfolgreich Rückmeldung, dass das Accessoire verkauft wurde
     * @param nichtGenugMaterial Rückmeldung, dass das Accessoire nicht verkauft werden konnte
     * @author OFR Rieger
     * @since 05.12.23
     */
    public void material(Material material, Callback<Material, Void> verkaufErfolgreich, Callback<Material, Void> nichtGenugMaterial) {
        int anzahl = 1;
        if (partyController.getParty().getMaterialien().get(material).getValue() >= anzahl) {
            partyController.materialEntnehmen(material, anzahl);
            haendler.getHistorieMaterial().get(material).setValue(haendler.getHistorieMaterial().get(material).getValue() + anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() + (anzahl * material.getKaufwert()));
            verkaufErfolgreich.call(material);
        }
        else {
            nichtGenugMaterial.call(material);
        }
    }

    /**
     * verkauft die übergebene Rüstung an den Haendler
     *
     * @param ruestung           die verkauft werden soll
     * @param verkaufErfolgreich Rückmeldung, dass die Rüstung verkauft wurde
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void ruestung(Ruestung ruestung, Callback<Ruestung, Void> verkaufErfolgreich) {
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung().remove(ruestung);
        haendler.getHistorieRuestung().add(ruestung);
        partyController.getParty().setGold(partyController.getPartyGold() + ruestung.getVerkaufswert());
        verkaufErfolgreich.call(ruestung);
    }

    /**
     * verkauft den übergebenen Verbrauchsgegenstand
     *
     * @param verbrauchsgegenstand   der gekauft werden soll
     * @param verkaufErfolgreich     Rückmeldung, dass das Material verkauft wurde
     * @param nichtGenugGegenstaende Rückmeldung, dass das Material nicht verkauft werden konnte
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void verbrauchsgegenstand(Verbrauchsgegenstand verbrauchsgegenstand, Callback<Verbrauchsgegenstand, Void> verkaufErfolgreich, Callback<Verbrauchsgegenstand, Void> nichtGenugGegenstaende) {
        int anzahl = 1;
        if (partyController.getParty().getVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() >= anzahl) {
            partyController.verbrauchsgegenstandEntnehmen(verbrauchsgegenstand, anzahl);
            haendler.getHistorieVerbrauchsgegenstaende().get(verbrauchsgegenstand).setValue(haendler.getHistorieVerbrauchsgegenstaende().get(verbrauchsgegenstand).getValue() + anzahl);
            partyController.getParty().setGold(partyController.getPartyGold() + (anzahl * verbrauchsgegenstand.getKaufwert()));
            verkaufErfolgreich.call(verbrauchsgegenstand);
        }
        else {
            nichtGenugGegenstaende.call(verbrauchsgegenstand);
        }
    }

    /**
     * verkauft die übergebene Waffe an den Haendler
     *
     * @param waffe              die verkauft werden soll
     * @param verkaufErfolgreich Rückmeldung, dass die Waffe verkauft wurde
     * @author OFR Rieger
     * @since 17.01.24
     */
    public void waffe(Waffe waffe, Callback<Waffe, Void> verkaufErfolgreich) {
        partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen().remove(waffe);
        haendler.getHistorieWaffe().add(waffe);
        partyController.getParty().setGold(partyController.getPartyGold() + waffe.getVerkaufswert());
        verkaufErfolgreich.call(waffe);
    }

}

