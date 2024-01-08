package trainer.faehigkeiten;

import charakter.model.Charakter;

import java.util.ArrayList;

public class FaehigkeitFactory {

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
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                break;
            case "Magischer DD":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                break;
            case "Tank":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                break;
            case "Healer":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                break;
            case "Berserker":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSanmausFaehigkeiten());
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
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                break;
            case "Magischer DD":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                break;
            case "Tank":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                break;
            case "Healer":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                break;
            case "Berserker":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSanmausFaehigkeiten());
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
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueBerserkerFaehigkeiten());
                break;
            case "Schurke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueSchurkeFaehigkeiten());
                break;
            case "Feuermagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueFeuermagierFaehigkeiten());
                break;
            case "Eismagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueEismagierFaehigkeiten());
                break;
            case "Rabauke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueRabaukeFaehigkeiten());
                break;
            case "Paladin":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neuePaladinFaehigkeiten());
                break;
            case "Priester":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neuePriesterFaehigkeiten());
                break;
            case "Sanmaus":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueSanmausFaehigkeiten());
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
