package party;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;

import java.util.Map;

public class PartyController {
    private Party party;

    public PartyController(Party party) {
        this.party = party;
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
     * @return double Partydurchschnittslevel
     * @author Nick
     * @since 16.11.2023
     */
    public double getPartyLevel() {
        double partyLevel = party.getHauptCharakter().getLevel() + 0.0;
        int nebencharCounter = 1;
        for (SpielerCharakter nebenchar : party.getNebenCharakter()) {
            if (nebenchar != null && nebenchar.getGesundheitsPunkte() > 0) {
                partyLevel += (nebenchar.getLevel() + 0.0);
                nebencharCounter++;
            }
        }
        if (nebencharCounter != 0) {
            return (partyLevel / nebencharCounter);
        } else {
            return partyLevel;
        }
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
     * @return int Partygold
     * @author Nick
     * @since 16.11.2023
     */
    public int getPartyGold() {
        return party.getGold();
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
    //TODO JAVADOC
    public void teammitgliedHinzufuegen(SpielerCharakter spielerCharakter) {
        SpielerCharakter[] nebenCharaktere = party.getNebenCharakter();
        if (nebenCharaktere[0] == null) {
            nebenCharaktere[0] = spielerCharakter;
        } else {
            if (nebenCharaktere[1] == null) {
                nebenCharaktere[1] = spielerCharakter;
            } else {
                if (nebenCharaktere[2] == null) {
                    nebenCharaktere[2] = spielerCharakter;
                }
            }
        }
        party.setNebenCharakter(nebenCharaktere);
    }
    //TODO JAVADOC
    public void teammitgliedEntfernen(SpielerCharakter spielerCharakter) {
        SpielerCharakter[] nebenCharaktere = party.getNebenCharakter();
        for (int i = 0; i < nebenCharaktere.length; i++) {
            if (nebenCharaktere[i] == spielerCharakter) {
                nebenCharaktere[i] = null;
            }
        }
        party.setNebenCharakter(nebenCharaktere);

        Ausruestungsgegenstand[] behalten = CharakterController.getGekaufteAusruestungsgegenstaendeVonCharakter(spielerCharakter);
        for (int i = 0; i < behalten.length; i++) {
            ausruestungsgegenstandHinzufuegen(behalten[i]);
        }
    }
    //TODO JAVADOC
    public void ausruestungsgegenstandHinzufuegen(Ausruestungsgegenstand ausruestungsgegenstand) {
        AusruestungsgegenstandInventar ausruestungen = party.getAusruestungsgegenstandInventar();
        if (!ausruestungen.getGesamteAusruestungsgegenstaende().contains(ausruestungsgegenstand)) {
            ausruestungen.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        } else {
            System.err.println("PartyController.ausruestungsgegenstandHinzufuegen(): Gegenstand ist bereits im AusreustungsgegenstandInventar");
        }
    }
    //TODO JAVADOC
    public void ausruestungsgegenstandEntfernen(Ausruestungsgegenstand ausruestungsgegenstand) {
        AusruestungsgegenstandInventar ausruestungsgegenstandInventar = party.getAusruestungsgegenstandInventar();
        ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
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
     * Entnimmt vom übergebenen Material die übergebene Anzahl
     * @param mat-
     * @param anzahl-
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void materialEntnehmen(Material mat, int anzahl) {
        party.getMaterialien().get(mat).set(party.getMaterialien().get(mat).get() - anzahl);
    }

    /**
     * Gibt die vorhandenen Materialien mit ihrer Anzahl aus
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
     * Fügt dem Inventar vom übergebenen Verbrauchsgegenstand die übergebene Anzahl hinzu
     * @param verbrauchsgegenstand -
     * @param anzahl -
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).set(party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).get() + anzahl);
    }


    /**
     * Entnimmt vom übergebenen Verbrauchsgegenstand die übergebene Anzahl
     * @param verbrauchsgegenstand-
     * @param anzahl-
     * @author OF Kretschmer
     * @since 20.11.23
     */
    public void verbrauchsgegenstandEntnehmen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).set(party.getVerbrauchsgegenstaende().get(verbrauchsgegenstand).get() - anzahl);
    }

    /**
     * Gibt die vorhandenen Verbrauchsgegenstände mit ihrer Anzahl aus
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
     * Gibt die Party zurück, Index 0 = Hauptcharakter;
     * Index 1-3 = Nebencharaktere, können Null sein.
     * @return Spielercharakter[4]
     * @author Nick
     * @since 04.12.2023
     */
    public SpielerCharakter[] getTeammitglieder(){
        SpielerCharakter[] myTeam = new SpielerCharakter[4];
        myTeam[0] = party.getHauptCharakter();

        for (int i = 0; i < party.getNebenCharakter().length; i++) {
            myTeam[i+1]= party.getNebenCharakter()[i];
        }
        return myTeam;
    }
}



