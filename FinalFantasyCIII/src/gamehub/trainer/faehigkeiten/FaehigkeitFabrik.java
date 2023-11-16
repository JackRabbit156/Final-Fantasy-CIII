package gamehub.trainer.faehigkeiten;

import charakter.model.Charakter;

import java.util.ArrayList;

public class FaehigkeitFabrik {

    public static Faehigkeit[] erstelleFaehigkeitFuer(Charakter charakter){
        String klassenNamen = charakter.getKlasse().getBezeichnung();
        int faehigkeitsPunkte = charakter.getLevel(); //TODO: level zu skill-Punkte Ratio

        Faehigkeit[] returnFaehigkeiten;
        switch(klassenNamen){
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
            default:
                throw new IllegalArgumentException("Keinen solchen Klassennamen gefunden!");
        }
        if(faehigkeitsPunkte > 1){
            faehigkeitenVerteilen(returnFaehigkeiten, faehigkeitsPunkte);
        }
        return returnFaehigkeiten;
    }

    private static void faehigkeitenVerteilen(Faehigkeit[] faehigkeits, int level){
        ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : faehigkeits){
            if(faehigkeit.getLevelAnforderung() >= level){
                moeglicheFaehigkeiten.add(faehigkeit);
            }
        }
        int anzahlMoeglicherFaehigkeiten = moeglicheFaehigkeiten.size();
        for (int i = 0; i < level; i++) {
            Faehigkeit zufaelligeFaehigkeit = moeglicheFaehigkeiten.get((int)(Math.random()*anzahlMoeglicherFaehigkeiten));
            Faehigkeit.faehigkeitAufwerten(zufaelligeFaehigkeit);
        }
    }
}
