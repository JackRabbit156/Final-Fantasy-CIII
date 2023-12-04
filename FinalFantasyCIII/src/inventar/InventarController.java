package inventar;

import charakter.model.SpielerCharakter;
import party.PartyController;

import java.util.ArrayList;

public class InventarController {
    private final PartyController partyController;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public InventarController(PartyController partyController) {
        this.partyController = partyController;
        this.aktiveParty = fuellePartyList();
        System.out.println("Hallo");
    }

    private ArrayList<SpielerCharakter> fuellePartyList() {
        ArrayList<SpielerCharakter> auffang = new ArrayList<>();

        auffang.add(partyController.getParty().getHauptCharakter());


        SpielerCharakter[] nebencharArray = this.partyController.getParty().getNebenCharakter();
        for (SpielerCharakter nebencharakter : nebencharArray) {
            if (nebencharakter != null) {
                auffang.add(nebencharakter);
            }
        }
        return auffang;
    }

    public void spielerinventarAnzeige() {
    }
}
