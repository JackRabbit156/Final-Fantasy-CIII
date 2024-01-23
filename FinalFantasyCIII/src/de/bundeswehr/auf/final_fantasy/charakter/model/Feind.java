package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public abstract class Feind extends Charakter {

    /**
     * Erstellt einen Feind, bzw. Gegner.
     *
     * @param partyLevel           zur Berechnung der Klassenwerte
     * @param name                 Anzeigename
     * @param klasse
     * @param grafischeDarstellung Pfad und Dateiname des Bildes
     * @author Lang
     * @since 20.11.2023
     */
    public Feind(int partyLevel, String name, Klasse klasse, String grafischeDarstellung) {
        setName(name);
        setLevel(partyLevel);
        setAttribute(new AttributCharakter(this));
        setKlasse(klasse);
        getAttribute().setMaxGesundheitsPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[0]));
        getAttribute().setMaxManaPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[1]));
        getAttribute().setPhysischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[2]));
        getAttribute().setMagischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[3]));
        getAttribute().setGenauigkeit(generateRandomValue(getKlasse().getDefaultAttribute()[4]));
        getAttribute().setVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[5]));
        getAttribute().setMagischeVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[6]));
        getAttribute().setResistenz(generateRandomValue(getKlasse().getDefaultAttribute()[7]));
        getAttribute().setBeweglichkeit(generateRandomValue(getKlasse().getDefaultAttribute()[8]));
        getAttribute().setGesundheitsRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[9]));
        getAttribute().setManaRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[10]));
        setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(getKlasse().getBezeichnung(), getLevel()));
        setGrafischeDarstellung(grafischeDarstellung);
        FeindController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleWaffeFuer(this.getKlasse(), getLevel()));
        FeindController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleRuestungFuer(this.getKlasse(), getLevel()));
        FeindController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), getLevel()));
        FeindController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), getLevel()));
        FeindController.ausruestungAnlegen(this,
                AusruestungsGegenstandFactory.erstelleAccessoireFuer(this.getKlasse(), getLevel()));
    }

}
