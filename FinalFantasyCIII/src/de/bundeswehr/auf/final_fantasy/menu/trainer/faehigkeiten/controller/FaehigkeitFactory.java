package de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.controller;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.Spezialisierung;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.Faehigkeit;

import java.util.ArrayList;
import java.util.List;

public final class FaehigkeitFactory {

    private FaehigkeitFactory() {
    }

    /**
     * erstelleFaehigkeitFuer verwaltet die Erstellung von neuen Faehigkeitslisten. Je nach Klassenbezeichnung werden andere Listen zurückgegeben
     * @param klasse Fuer welchen Charakter sollen Faehigkeiten erstellt werden
     * @param level    Level des Charakters
     * @return Gibt eine ArrayList von Faehigkeit zurueck.
     * @author 11777914 OLt Oliver Ebert
     * @since 20.11.2023
     */
    public static List<Faehigkeit> erstelleFaehigkeitFuer(String klasse, int level) {
        List<Faehigkeit> returnFaehigkeiten;
        switch (klasse) {
            case Klasse.PDD:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                break;
            case Klasse.MDD:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                break;
            case Klasse.TNK:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                break;
            case Klasse.HLR:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                break;
            case Spezialisierung.BERSERKER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case Spezialisierung.SCHURKE:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case Spezialisierung.FEUER_MAGIER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case Spezialisierung.EIS_MAGIER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case Spezialisierung.RABAUKE:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case Spezialisierung.PALADIN:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case Spezialisierung.PRIESTER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case Spezialisierung.SAN_MAUS:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSanmausFaehigkeiten());
                break;
            default:
                throw new IllegalArgumentException("Keinen solchen Klassennamen gefunden: " + klasse);
        }
        if (level > 1) {
            returnFaehigkeiten = faehigkeitenVerteilen(returnFaehigkeiten, level);
        }
        return returnFaehigkeiten;
    }

    public static List<Faehigkeit> erstelleFaehigkeitFuer(String klasse) {
        List<Faehigkeit> returnFaehigkeiten;
        switch (klasse) {
            case Klasse.PDD:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                break;
            case Klasse.MDD:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                break;
            case Klasse.TNK:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                break;
            case Klasse.HLR:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                break;
            case Spezialisierung.BERSERKER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case Spezialisierung.SCHURKE:
                returnFaehigkeiten = NeueFaehigkeitFactory.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case Spezialisierung.FEUER_MAGIER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case Spezialisierung.EIS_MAGIER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case Spezialisierung.RABAUKE:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case Spezialisierung.PALADIN:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case Spezialisierung.PRIESTER:
                returnFaehigkeiten = NeueFaehigkeitFactory.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case Spezialisierung.SAN_MAUS:
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
            case Spezialisierung.BERSERKER:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueBerserkerFaehigkeiten());
                break;
            case Spezialisierung.SCHURKE:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueSchurkeFaehigkeiten());
                break;
            case Spezialisierung.FEUER_MAGIER:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueFeuermagierFaehigkeiten());
                break;
            case Spezialisierung.EIS_MAGIER:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueEismagierFaehigkeiten());
                break;
            case Spezialisierung.RABAUKE:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueRabaukeFaehigkeiten());
                break;
            case Spezialisierung.PALADIN:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neuePaladinFaehigkeiten());
                break;
            case Spezialisierung.PRIESTER:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neuePriesterFaehigkeiten());
                break;
            case Spezialisierung.SAN_MAUS:
                charakter.getFaehigkeiten().addAll(NeueFaehigkeitFactory.neueSanmausFaehigkeiten());
                break;
            default:
                throw new RuntimeException("FaehigkeitFabrik: Keinen solchen Klassennamen gefunden: " + klassenNamen);
        }
    }

    /**
     * @param faehigkeiten: Welche Faehigkeiten sollen zufaellig erweitert werden
     * @param level        : Wie viele Stufen können aufgewertet werden?
     * @author 11777914 OLt Oliver Ebert
     * @since 21.11.2023
     * faehigkeitenVerteilen erweitert die Faehigkeitsliste eines Charakters, wenn eine Spezialisierung gewaehlt wird
     */
    private static List<Faehigkeit> faehigkeitenVerteilen(List<Faehigkeit> faehigkeiten, int level) {
        List<Integer> moeglicheFaehigkeiten = new ArrayList<>();
        //Bekomme den Index aller Faehigkeiten, die erweitert werden koennen
        for (int i = 0; i < faehigkeiten.size(); i++) {
            if (faehigkeiten.get(i).getLevelAnforderung() <= level) {
                moeglicheFaehigkeiten.add(i);
            }
        }
        int anzahlMoeglicherFaehigkeiten = moeglicheFaehigkeiten.size();
        //verteilen der
        for (int i = 0; i < level; i++) {
            int zufaelligerIndexEinerMoeglichenFaehigkeit = (int) (Math.random() * anzahlMoeglicherFaehigkeiten);
            Faehigkeit faehigkeitZumAufwerten = faehigkeiten.get(zufaelligerIndexEinerMoeglichenFaehigkeit);
            faehigkeiten.remove(faehigkeitZumAufwerten);
            Faehigkeit neueFaehigkeit = Faehigkeit.faehigkeitAufwerten(faehigkeitZumAufwerten);
            faehigkeiten.add(zufaelligerIndexEinerMoeglichenFaehigkeit, neueFaehigkeit);
        }
        return faehigkeiten;
    }

}
