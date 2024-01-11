package de.bundeswehr.auf.final_fantasy.charakter.model;

import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.AusruestungsGegenstandFactory;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller.FaehigkeitFactory;

import java.util.Random;

public abstract class Feind extends Charakter {

    /**
     * Erstellt Feind
     *
     * @param partyLevel zur Berechnung der Klassenwerte
     * @param name Anzeigename
     * @param klasse
     * @param grafischeDarstellung Pfad und DAteiname des Bildes
     * @author Lang
     * @since 20.11.2023
     */
    public Feind(int partyLevel, String name, Klasse klasse, String grafischeDarstellung) {
        setLevel(partyLevel);
        setMaxGesundheitsPunkte(generateRandomValue());
        setGesundheitsPunkte(getMaxGesundheitsPunkte());
        setMaxManaPunkte(generateRandomValue());
        setManaPunkte(getMaxManaPunkte());
        setPhysischeAttacke(generateRandomValue());
        setMagischeAttacke(generateRandomValue());
        setGenauigkeit(generateRandomValue());
        setVerteidigung(generateRandomValue());
        setMagischeVerteidigung(generateRandomValue());
        setResistenz(generateRandomValue());
        setBeweglichkeit(generateRandomValue());
        setGesundheitsRegeneration(generateRandomValue());
        setManaRegeneration(generateRandomValue());
        setName(name);
        setKlasse(klasse);
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
