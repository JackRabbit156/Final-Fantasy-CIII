package haendler;

import javafx.scene.control.TabPane;
import party.PartyController;

public class VerkaufenView extends TabPane {


        PartyController partyController;
        Haendler haendler;

        public VerkaufenView(PartyController partyController, Haendler haendler) {
            this.partyController = partyController;
            this.haendler = haendler;

        }
}
