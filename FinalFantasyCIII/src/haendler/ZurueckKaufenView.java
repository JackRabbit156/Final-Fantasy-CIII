package haendler;

import javafx.scene.control.TabPane;
import party.PartyController;

public class ZurueckKaufenView extends TabPane {
    PartyController partyController;
    Haendler haendler;

    public ZurueckKaufenView(PartyController partyController,HaendlerController haendlerController, Haendler haendler) {
        this.partyController = partyController;
        this.haendler = haendler;

    }
}
