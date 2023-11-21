package party;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;

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
     * @param hinzuzufuegendesGold
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
     * @param abzuziehendesGold
     * @author Nick
     * @since 16.11.2023
     */
    public void goldAbziehen(int abzuziehendesGold) {
        party.setGold(party.getGold() - abzuziehendesGold);
    }

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

    public void ausruestungsgegenstandHinzufuegen(Ausruestungsgegenstand ausruestungsgegenstand) {
        AusruestungsgegenstandInventar ausruestungen = party.getAusruestungsgegenstandInventar();
        if (!ausruestungen.getGesamteAusruestungsgegenstaende().contains(ausruestungsgegenstand)) {
            ausruestungen.ausruestungsgegenstandHinzufuegen(ausruestungsgegenstand);
        } else {
            System.err.println("PartyController.ausruestungsgegenstandHinzufuegen(): Gegenstand ist bereits im AusreustungsgegenstandInventar");
        }
    }

    public void ausruestungsgegenstandEntfernen(Ausruestungsgegenstand ausruestungsgegenstand) {
        AusruestungsgegenstandInventar ausruestungsgegenstandInventar = party.getAusruestungsgegenstandInventar();
        ausruestungsgegenstandInventar.ausruestungsgegenstandEntfernen(ausruestungsgegenstand);
    }

    /**
     * Fuegt anhand der uebergebenenen Materialart und Anzhal Material dem globalen Inventar zu.
     *
     * @param mat
     * @param anzahl
     * @author Nick
     * @since 20.11.2023
     */
    public void materialHinzufuegen(Material mat, int anzahl) {
        boolean bereitsVorhanden = false;
        Map<Material, Integer> matInventar = party.getMaterialien();
        for (Map.Entry<Material, Integer> entry : matInventar.entrySet()) {
            if (entry.getClass().getSimpleName().equals(mat.getClass().getSimpleName())) {
                matInventar.put(entry.getKey(), (entry.getValue() + anzahl));
                bereitsVorhanden = true;
            }
        }
        if(!bereitsVorhanden){
            matInventar.put(mat, anzahl);
        }
        party.setMaterialien(matInventar);
    }

    /**
     * @param mat
     * @param anzahl
     * @author OF Kretschmer
     * @since 20.11.23
     * <p>
     * Entnimmt vom übergebenen Material die übergebene Anzahl
     */
    public void materialEntnehmen(Material mat, int anzahl) {
        Map<Material, Integer> matInventar = party.getMaterialien();
        for (Map.Entry<Material, Integer> entry : matInventar.entrySet()) {
            if (entry.getClass().getSimpleName().equals(mat.getClass().getSimpleName())) {
                matInventar.put(entry.getKey(), (entry.getValue() - anzahl));
            }
        }
    }

    /**
     * @author OF Kretschmer
     * @since 20.11.23
     * <p>
     * Gibt die vorhandenen Materialien mit ihrer Anzahl aus
     */
    public void materialienAusgeben() {
        Map<Material, Integer> matInventar = party.getMaterialien();
        for (Map.Entry<Material, Integer> entry : matInventar.entrySet()) {
            System.out.printf("%5d x  %s", entry.getValue(), entry.getKey().toString());
        }
    }

    /**
     * @param verbrauchsgegenstand
     * @param anzahl
     * @author OF Kretschmer
     * @since 20.11.23
     * <p>
     * Fügt dem Inventar vom übergebenen Verbrauchsgegenstand die übergebene Anzahl hinzu
     */
    public void verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandInventar = party.getVerbrauchsgegenstaende();
        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
            if (entry.getClass().getSimpleName().equals(verbrauchsgegenstand.getClass().getSimpleName())) {
                verbrauchsgegenstandInventar.put(entry.getKey(), (entry.getValue() + anzahl));
            }
        }
    }


    /**
     * @param verbrauchsgegenstand
     * @param anzahl
     * @author OF Kretschmer
     * @since 20.11.23
     * <p>
     * Entnimmt vom übergebenen Verbrauchsgegenstand die übergebene Anzahl
     */
    public void verbrauchsgegenstandEntnehmen(Verbrauchsgegenstand verbrauchsgegenstand, int anzahl) {
        Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandInventar = party.getVerbrauchsgegenstaende();
        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
            if (entry.getClass().getSimpleName().equals(verbrauchsgegenstand.getClass().getSimpleName())) {
                verbrauchsgegenstandInventar.put(entry.getKey(), (entry.getValue() - anzahl));
            }
        }
    }

    /**
     * @author OF Kretschmer
     * @since 20.11.23
     * <p>
     * Gibt die vorhandenen Verbrauchsgegenstände mit ihrer Anzahl aus
     */
    public void verbrauchsgegenstaendeAusgeben() {
        Map<Verbrauchsgegenstand, Integer> verbrauchsgegenstandInventar = party.getVerbrauchsgegenstaende();
        for (Map.Entry<Verbrauchsgegenstand, Integer> entry : verbrauchsgegenstandInventar.entrySet()) {
            System.out.printf("%5d x  %s", entry.getValue(), entry.getKey().toString());
        }
    }
    public SpielerCharakter[] getTeammitglieder(){
        SpielerCharakter[] myTeam = new SpielerCharakter[4];
        myTeam[0] = party.getHauptCharakter();

        for (int i = 0; i < party.getNebenCharakter().length; i++) {
            myTeam[i+1]= party.getNebenCharakter()[i];
        }
        return myTeam;
    }
}



