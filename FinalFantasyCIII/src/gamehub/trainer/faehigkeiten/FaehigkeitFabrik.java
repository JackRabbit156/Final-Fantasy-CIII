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
            case "PDD":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                break;
            case "MDD":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                break;
            case "TNK":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                break;
            case "HLR":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                break;
            case "Berserker":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueBerserkerFaehigkeit());
                break;
            case "Schurke":
                returnFaehigkeiten = NeueFaehigkeiten.neuePDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSchurkeFaehigkeit());
                break;
            case "Feuermagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueFeuermagierFaehigkeit());
                break;
            case "Eismagier":
                returnFaehigkeiten = NeueFaehigkeiten.neueMDDFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueEismagierFaehigkeit());
                break;
            case "Rabauke":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueRabaukeFaehigkeit());
                break;
            case "Paladin":
                returnFaehigkeiten = NeueFaehigkeiten.neueTNKFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePaladinFaehigkeit());
                break;
            case "Priester":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neuePriesterFaehigkeit());
                break;
            case "Sanmaus":
                returnFaehigkeiten = NeueFaehigkeiten.neueHLRFaehigkeiten();
                returnFaehigkeiten.addAll(NeueFaehigkeiten.neueSanmausFaehigkeit());
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
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueBerserkerFaehigkeit());
                break;
            case "Schurke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueSchurkeFaehigkeit());
                break;
            case "Feuermagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueFeuermagierFaehigkeit());
                break;
            case "Eismagier":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueEismagierFaehigkeit());
                break;
            case "Rabauke":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueRabaukeFaehigkeit());
                break;
            case "Paladin":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neuePaladinFaehigkeit());
                break;
            case "Priester":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neuePriesterFaehigkeit());
                break;
            case "Sanmaus":
                charakter.getFaehigkeiten().addAll(NeueFaehigkeiten.neueSanmausFaehigkeit());
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
