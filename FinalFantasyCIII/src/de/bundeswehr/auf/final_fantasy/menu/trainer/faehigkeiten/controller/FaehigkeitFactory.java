package de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.Faehigkeit;

import java.util.ArrayList;

public final class FaehigkeitFactory {

    private FaehigkeitFactory() {
    }

    /**
     * erstelleFaehigkeitFuer verwaltet die Erstellung von neuen Faehigkeitslisten. Je nach Klassenbezeichnung werden andere Listen zurückgegeben
     * @param klasse: Fuer welchen Charakter sollen Faehigkeiten erstellt werden
     * @param lvl:    Level des Charakters
     * @return : Gibt eine ArrayList von Faehigkeit zurueck.
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static ArrayList<Faehigkeit> erstelleFaehigkeitFuer(String klasse, int lvl) {
        ArrayList<Faehigkeit> returnFaehigkeiten;
        switch (klasse) {
            case "Physischer DD":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                break;
            case "Magischer DD":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                break;
            case "Tank":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                break;
            case "Healer":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                break;
            case "Berserker":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSanmausFaehigkeiten());
                break;
            default:
                throw new IllegalArgumentException("Keinen solchen Klassennamen gefunden!");
        }
        if (lvl > 1) {
            returnFaehigkeiten = faehigkeitenVerteilen(returnFaehigkeiten, lvl);
        }
        return returnFaehigkeiten;
    }

    public static ArrayList<Faehigkeit> erstelleFaehigkeitFuer(String klasse) {
        ArrayList<Faehigkeit> returnFaehigkeiten;
        switch (klasse) {
            case "Physischer DD":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                break;
            case "Magischer DD":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                break;
            case "Tank":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                break;
            case "Healer":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                break;
            case "Berserker":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSanmausFaehigkeiten());
                break;
            default:
                throw new IllegalArgumentException("Keinen solchen Klassennamen gefunden!");
        }
        return returnFaehigkeiten;
    }

    /**
     * @param charakter: Fuer welchen Charakter sollen Faehigkeiten hinzugefuegt werden
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     * spezialisierungsFaehigkeitHinzufuegen erweitert die Faehigkeitsliste eines Charakters, wenn eine Spezialisierung gewaehlt wird
     */
    public static void spezialisierungsFaehigkeitHinzufuegen(Charakter charakter) {
        String klassenNamen = charakter.getKlasse().getClass().getSimpleName();

        switch (klassenNamen) {
            case "Berserker":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueSanmausFaehigkeiten());
                break;
            default:
                System.err.println("FaehigkeitFabrik: Keinen solchen Klassennamen gefunden!");
        }
    }

    /**
     * @param faehigkeits: Welche Faehigkeiten sollen zufaellig erweitert werden
     * @param level        : Wie viele Stufen können aufgewertet werden?
     * @author 11777914 OLt Oliver Ebert
     * @since 21.11.2023
     * faehigkeitenVerteilen erweitert die Faehigkeitsliste eines Charakters, wenn eine Spezialisierung gewaehlt wird
     */
    private static ArrayList<Faehigkeit> faehigkeitenVerteilen(ArrayList<Faehigkeit> faehigkeits, int level) {
        ArrayList<Faehigkeit> returnList = faehigkeits;
        ArrayList<Integer> moeglicheFaehigkeiten = new ArrayList<>();
        //Bekomme den Index aller Faehigkeiten, die erweitert werden koennen
        for (int i = 0; i < faehigkeits.size(); i++) {
            if (faehigkeits.get(i).getLevelAnforderung() <= level) {
                moeglicheFaehigkeiten.add(i);
            }
        }
        int anzahlMoeglicherFaehigkeiten = moeglicheFaehigkeiten.size();
        //verteilen der
        for (int i = 0; i < level; i++) {
            int zufaelligerIndexEinerMoeglichenFaehigkeit = (int) (Math.random() * anzahlMoeglicherFaehigkeiten);
            Faehigkeit faehigkeitZumAufwerten = returnList.get(zufaelligerIndexEinerMoeglichenFaehigkeit);
            returnList.remove(faehigkeitZumAufwerten);
            Faehigkeit neueFaehigkeit = Faehigkeit.faehigkeitAufwerten(faehigkeitZumAufwerten);
            returnList.add(zufaelligerIndexEinerMoeglichenFaehigkeit, neueFaehigkeit);
        }
        return returnList;
    }

}
