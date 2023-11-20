package gamehub.trainer.faehigkeiten;

import charakter.model.Charakter;
import hilfsklassen.Farbauswahl;

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

    public static void spezialisierungsFaehigkeitHinzufuegen(Charakter charakter) {
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

    public static void faehigkeitenAusgeben(ArrayList<Faehigkeit> faehigkeitsList) {
        System.out.println("Legende: \n" +
                "     maK = Mana-Kosten zum einsetzen der Faehigkeit\n" +
                "     lvl = Level der Faehigkeit (Faehigkeiten mit Level = 0 sind nicht einsetzbar) \n" +
                "     min = erforderliches Level des Charakters zum erlernen der Faehigkeit. \n" +
                "     eSt = Effektstaerke der Faehigkeit. \n" +
                "     num = Anzahl von Charakteren, die mit Faehigkeit belegt werden koennen. \n" +
                "      p  = Wahrscheinlichkeit der Faehigkeit eine besondere Wirkung zu erzielen. \n" +
                "------------------------------------------------------------------------------------------");
        //1.Zeile
        //NAME | Beschreibung | manaKosten | level | minLevel | effektStaerke | zielAnzahl | wahrscheinlichkeit |
        System.out.printf("%20s|%5s|%5s|%5s|%5s|%5s|%5s", "Name & Beschreibung", " mak ", " lvl ", " min ", " eSt ", " num ", "  p  ");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Faehigkeit faehigkeit : faehigkeitsList) {
            System.out.printf("%20s|%5s|%5s|%5s|%5s|%5s|%5s%n", Farbauswahl.RED + faehigkeit.getName() + Farbauswahl.RESET,
                    faehigkeit.getManaKosten(), faehigkeit.getLevel(), faehigkeit.getLevelAnforderung(), faehigkeit.getEffektStaerke(),
                    faehigkeit.getZielAnzahl(), faehigkeit.getWahrscheinlichkeit());
            String[] beschreibung = stringFormatHelfer(20, faehigkeit.getBeschreibung());
            for (String zeile : beschreibung) {
                System.out.printf("%20s|%5s|%5s|%5s|%5s|%5s|%5s%n", Farbauswahl.GREY + zeile + Farbauswahl.RESET,
                        "", "", "", "", "", "");
            }
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }

    private static String[] stringFormatHelfer(int stringBreite, String stringZuFormatieren) {
        StringBuilder returnString = new StringBuilder();
        String[] stringArray = stringZuFormatieren.split("\\s+");
        int charCount = 0;
        for (String string : stringArray) {
            int laenge = string.length();
            if ((charCount + laenge) > stringBreite) {
                returnString.append("\n");
                charCount = 0;
            }
            returnString.append(string);
            charCount += laenge;
        }
        return returnString.toString().split("\r\n|\r|\n");
    }
}
