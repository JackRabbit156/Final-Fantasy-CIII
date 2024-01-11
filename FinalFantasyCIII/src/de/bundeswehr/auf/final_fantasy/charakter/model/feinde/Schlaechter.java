package de.bundeswehr.auf.final_fantasy.charakter.model.feinde;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;

public class Schlaechter extends Feind {

    public Schlaechter(int partyLevel) {
        super(partyLevel, "Schlächter", new TNK(), "charakter/feind/SF_Butcher.png");
    }
}
