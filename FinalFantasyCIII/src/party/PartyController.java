package party;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;

import java.util.Map;

public class PartyController {
    private Party party;

    public PartyController(Party party) {
        this.party = party;
    }


    /**
     * gibt das Party-Objekt zurueck
     * @author Nick
     * @since 16.11.2023
     * @return Party
     */
    public Party getParty(){
        return party;
    }

    /**
     * @author Nick
     * @since 16.11.2023
     * @return double Partydurchschnittslevel
     */
    public double getPartyLevel(){
        double partyLevel = party.getHauptCharakter().getLevel()+0.0;
        int nebencharCounter = 0;
        for (SpielerCharakter nebenchar : party.getNebenCharakter()){
            if(nebenchar != null && nebenchar.getGesundheitsPunkte() > 0){
                partyLevel += (nebenchar.getLevel() + 0.0);
                nebencharCounter++;
            }
        }
        if(nebencharCounter != 0){
        return (partyLevel/nebencharCounter);
        } else {
            return partyLevel;
        }
    }

    /**
     * Fuegt dem Partyinventar Gold hinzu
     * @author Nick
     * @since 16.11.2023
     * @param hinzuzufuegendesGold
     */
    public void goldHinzufuegen(int hinzuzufuegendesGold){
        party.setGold(party.getGold() + hinzuzufuegendesGold);
    }

    /**
     * @author Nick
     * @since 16.11.2023
     * @return int Partygold
     */
    public int getPartyGold(){
        return party.getGold();
    }

    /**
     * DIESE METHODE PRÜFT NICHT OB GENUG GOLD VORHANDEN IST!
     * @author Nick
     * @since 16.11.2023
     * @param abzuziehendesGold
     */
    public void goldAbziehen(int abzuziehendesGold){
        party.setGold(party.getGold() - abzuziehendesGold);
    }

    public void teammitgliedHinzufuegen(SpielerCharakter spielerCharakter){
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
            ausruestungsgegenstandHinzufuegen(behalten[i],1);
        }
    }

    public void ausruestungsgegenstandHinzufuegen(Ausruestungsgegenstand ausruestungsgegenstand, int menge){
        Map<Ausruestungsgegenstand,Integer> ausruestungen = party.getAusruestungen();
        if(ausruestungen.get(ausruestungsgegenstand)!= null){
            ausruestungen.put(ausruestungsgegenstand,ausruestungen.get(ausruestungsgegenstand)+menge);
        } else {
            ausruestungen.put(ausruestungsgegenstand,menge);
        }
        party.setAusruestungen(ausruestungen);
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
