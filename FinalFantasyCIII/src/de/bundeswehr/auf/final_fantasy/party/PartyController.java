package de.bundeswehr.auf.final_fantasy.party;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import javafx.beans.property.IntegerProperty;

import java.util.Map;

public class PartyController {

    private final Party party;

    public PartyController(Party party) {
        this.party = party;
    }

    /**
     * Entfernt ein Ausrüstungsgegenstand aus dem Partyinventar
     *
     * @param ausruestungsgegenstand zu entfernender Ausrüstungsgegenstand
     * @author Nick
     * @since 16.11.2023
     */
    public void ausruestungsgegenstandEntfernen(AusruestungsGegenstand ausruestungsgegenstand) {
        AusruestungsGegenstandInventar ausruestungsgegenstandInventar = party.getAusruestungsgegenstandInventar();
        ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
    }

    /**
     * Fügt ein Ausrüstungsgegenstand dem Partyinventar hinzu
     *
     * @param ausruestungsgegenstand hinzuzufügender Ausrüstungsgegenstand
     * @author Nick
     * @since 16.11.2023
     */
    public void ausruestungsgegenstandHinzufuegen(AusruestungsGegenstand ausruestungsgegenstand) {
        AusruestungsGegenstandInventar ausruestungen = party.getAusruestungsgegenstandInventar();
        if (!ausruestungen.getGesamteAusruestungsgegenstaende().contains(ausruestungsgegenstand)) {
            ausruestungen.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        }
    }

    /**
     * gibt das Party-Objekt zurueck
     *
     * @return Party
     * @author Nick
     * @since 16.11.2023
     */
    public Party getParty() {
        return party;
    }

    /**
     * @return int Partygold
     * @author Nick
     * @since 16.11.2023
     */
    public int getPartyGold() {
        return party.getGold();
    }

    /**
     * @return double Partydurchschnittslevel
     * @author Nick
     * @since 16.11.2023
     */
    public double getPartyLevel() {
        double partyLevel = party.getHauptCharakter().getLevel() + 0.0;
        int nebencharCounter = 1;
        for (SpielerCharakter nebenchar : party.getNebenCharaktere()) {
            if (nebenchar != null && nebenchar.getGesundheitsPunkte() > 0) {
                partyLevel += (nebenchar.getLevel() + 0.0);
                nebencharCounter++;
            }
        }
        if (nebencharCounter != 0) {
            return (partyLevel / nebencharCounter);
        }
        else {
            return partyLevel;
        }
    }

    /**
     * Gibt die Party zurück, Index 0 = Hauptcharakter;
     * Index 1-3 = Nebencharaktere, können Null sein.
     *
     * @return Spielercharakter[4]
     * @author Nick
     * @since 04.12.2023
     */
    public SpielerCharakter[] getTeammitglieder() {
        SpielerCharakter[] team = new SpielerCharakter[4];
        team[0] = party.getHauptCharakter();
        for (int i = 0; i < party.getNebenCharaktere().length; i++) {
            team[i + 1] = party.getNebenCharaktere()[i];
        }
        return team;
    }

    /**
     * DIESE METHODE PRÜFT NICHT OB GENUG GOLD VORHANDEN IST!
     *
     * @param abzuziehendesGold-
     * @author Nick
     * @since 16.11.2023
     */
    public void goldAbziehen(int abzuziehendesGold) {
        party.setGold(party.getGold() - abzuziehendesGold);
    }

    /**
     * Fuegt dem Partyinventar Gold hinzu
     *
     * @param hinzuzufuegendesGold-
     * @author Nick
     * @since 16.11.2023
     */
    public void goldHinzufuegen(int hinzuzufuegendesGold) {
        party.setGold(party.getGold() + hinzuzufuegendesGold);
    }

    /**
     * Entnimmt vom übergebenen Material die übergebene Anzahl
     *
     * @param mat-
     * @param anzahl-
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void materialEntnehmen(Material mat, int anzahl) {
        party.getMaterialien().get(mat).set(party.getMaterialien().get(mat).get() - anzahl);
    }

    /**
     * Fuegt anhand der uebergebenenen Materialart und Anzhal Material dem globalen Inventar zu.
     *
     * @param mat-
     * @param anzahl-
     * @author Nick
     * @since 20.11.2023
     */
    public void materialHinzufuegen(Material mat, int anzahl) {
        party.getMaterialien().get(mat).set(party.getMaterialien().get(mat).get() + anzahl);
    }

    /**
     * Gibt die vorhandenen Materialien mit ihrer Anzahl aus
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void materialienAusgeben() {
        Map<Material, IntegerProperty> matInventar = party.getMaterialien();
        for (Map.Entry<Material, IntegerProperty> entry : matInventar.entrySet()) {
            System.out.printf("%5d x  %s", entry.getValue().get(), entry.getKey().toString());
        }
    }

    /**
     * Entfernt ein Teammitglied aus der Party
     *
     * @param spielerCharakter zu entfernender Teammitglied
     * @author Nick
     * @since 16.11.2023
     */
    public void teammitgliedEntfernen(SpielerCharakter spielerCharakter) {
        SpielerCharakter[] nebenCharaktere = party.getNebenCharaktere();
        for (int i = 0; i < nebenCharaktere.length; i++) {
            if (nebenCharaktere[i] == spielerCharakter) {
                nebenCharaktere[i] = null;
            }
        }
        party.setNebenCharaktere(nebenCharaktere);

        AusruestungsGegenstand[] behalten = CharakterController.getGekaufteAusruestungsgegenstaendeVonCharakter(spielerCharakter);
        for (AusruestungsGegenstand ausruestungsGegenstand : behalten) {
            ausruestungsgegenstandHinzufuegen(ausruestungsGegenstand);
        }
    }

    /**
     * Fügt ein Teammitglied der Party hinzu
     *
     * @param spielerCharakter hinzuzufügendes Teammitglied
     * @author Nick
     * @since 16.11.2023
     */
    public void teammitgliedHinzufuegen(SpielerCharakter spielerCharakter) {
        SpielerCharakter[] nebenCharaktere = party.getNebenCharaktere();
        if (nebenCharaktere[0] == null) {
            nebenCharaktere[0] = spielerCharakter;
        }
        else {
            if (nebenCharaktere[1] == null) {
                nebenCharaktere[1] = spielerCharakter;
            }
            else {
                if (nebenCharaktere[2] == null) {
                    nebenCharaktere[2] = spielerCharakter;
                }
            }
        }
        party.setNebenCharaktere(nebenCharaktere);
    }

    /**
     * Gibt die vorhandenen Verbrauchsgegenstände mit ihrer Anzahl aus
     *
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void verbrauchsgegenstaendeAusgeben() {
        Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstandInventar = party.getVerbrauchsgegenstaende();
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstandInventar.entrySet()) {
            System.out.printf("%5d x  %s", entry.getValue().get(), entry.getKey().toString());
        }
    }

    /**
     * Entnimmt vom übergebenen Verbrauchsgegenstand die übergebene Anzahl
     *
     * @param verbrauchsgegenstand-
     * @param anzahl-
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void verbrauchsgegenstandEntnehmen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).set(party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).get() - anzahl);
    }

    /**
     * Fügt dem Inventar vom übergebenen Verbrauchsgegenstand die übergebene Anzahl hinzu
     *
     * @param verbrauchsgegenstand -
     * @param anzahl               -
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).set(party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).get() + anzahl);
    }

}