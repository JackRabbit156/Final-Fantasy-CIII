package de.bundeswehr.auf.final_fantasy.charakter.model.feinde;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public class Tengu extends Feind {

    public Tengu(int partyLevel) {
        super(partyLevel, "Tengu", new PDD(), "charakter/feind/SF_Tengu.png");
    }
}
