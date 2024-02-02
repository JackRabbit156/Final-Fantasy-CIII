package de.bundeswehr.auf.final_fantasy.charakter.model.feinde;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;

public class Krankenschwester extends Feind {

    public Krankenschwester(int partyLevel) {
        super(partyLevel, "Krankenschwester", new HLR(), "charakter/feind/SF_Nurse.png");
    }
}
