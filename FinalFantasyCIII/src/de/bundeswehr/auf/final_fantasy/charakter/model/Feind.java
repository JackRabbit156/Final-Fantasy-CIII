package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

public abstract class Feind extends Charakter {

    /**
     * Erstellt Feind
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
        setKlasse(klasse);
        setLevel(partyLevel);
        setMaxGesundheitsPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[0]));
        setGesundheitsPunkte(getMaxGesundheitsPunkte());
        setMaxManaPunkte(generateRandomValue(getKlasse().getDefaultAttribute()[1]));
        setManaPunkte(getMaxManaPunkte());
        setPhysischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[2]));
        setMagischeAttacke(generateRandomValue(getKlasse().getDefaultAttribute()[3]));
        setGenauigkeit(generateRandomValue(getKlasse().getDefaultAttribute()[4]));
        setVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[5]));
        setMagischeVerteidigung(generateRandomValue(getKlasse().getDefaultAttribute()[6]));
        setResistenz(generateRandomValue(getKlasse().getDefaultAttribute()[7]));
        setBeweglichkeit(generateRandomValue(getKlasse().getDefaultAttribute()[8]));
        setGesundheitsRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[9]));
        setManaRegeneration(generateRandomValue(getKlasse().getDefaultAttribute()[10]));
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
        setFaehigkeiten(FaehigkeitFactory.erstelleFaehigkeitFuer(getKlasse().getBezeichnung(), getLevel()));
        setGrafischeDarstellung(grafischeDarstellung);
    }

}
