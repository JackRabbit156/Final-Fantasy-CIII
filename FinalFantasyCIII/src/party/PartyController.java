package party;

import charakter.model.SpielerCharakter;

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
        return (partyLevel/nebencharCounter);
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
}
