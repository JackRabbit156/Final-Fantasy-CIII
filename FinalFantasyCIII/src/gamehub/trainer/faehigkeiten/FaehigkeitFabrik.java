package gamehub.trainer.faehigkeiten;

import charakter.model.Charakter;

import java.util.ArrayList;

public class FaehigkeitFabrik {

    public static ArrayList<Faehigkeit> erstelleFaehigkeitFuer(Charakter charakter) {
        String klassenNamen = charakter.getKlasse().getBezeichnung();
        //TODO: level zu skill-Punkte Ratio
        int faehigkeitsPunkte = charakter.getLevel();

        ArrayList<Faehigkeit> returnFaehigkeiten;
        switch (klassenNamen) {
            case "Physicher DD":
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
        if (faehigkeitsPunkte > 1) {
            faehigkeitenVerteilen(returnFaehigkeiten, faehigkeitsPunkte);
        }
        return returnFaehigkeiten;
    }

    public static void spezialisierungsFaehigkeitHinzufuegen(Charakter charakter){
        String klassenNamen = charakter.getKlasse().getBezeichnung();

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

    private static void faehigkeitenVerteilen(ArrayList<Faehigkeit> faehigkeits, int level) {
        ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : faehigkeits) {
            if (faehigkeit.getLevelAnforderung() >= level) {
                moeglicheFaehigkeiten.add(faehigkeit);
            }
        }
        int anzahlMoeglicherFaehigkeiten = moeglicheFaehigkeiten.size();
        for (int i = 0; i < level; i++) {
            Faehigkeit zufaelligeFaehigkeit = moeglicheFaehigkeiten.get((int) (Math.random() * anzahlMoeglicherFaehigkeiten));
            Faehigkeit.faehigkeitAufwerten(zufaelligeFaehigkeit);
        }
    }
}
