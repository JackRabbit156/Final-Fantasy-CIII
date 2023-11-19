package gamehub.schmiede;

import gamehub.haendler.Haendler;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.Material;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

import java.util.ArrayList;

public class SchmiedeController {

   // methoden zum upgraden

    PartyController partyController;
    Schmiede schmiede;

    public SchmiedeController(PartyController partyController) {
        this.partyController = partyController;
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    public void schmiedeAnzeigen(PartyController partyController) {
        boolean zurückMenue = false;
        int eingabe;
        boolean eingabeKorrekt = false;
        while (!zurückMenue) {
            schmiedeBildAnzeigen();
            schmiedeMenueAnzeigen();


            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= 4) {
                    eingabeKorrekt = true;
                    switch (eingabe) {
                        case 1:
                            gegenstandVerbessern(partyController);
                            // Öffnet ausgeruestete Waffen (Party)
                            break;
                        case 2:
                            vorhandenesMaterialAnzeigen(partyController);
                            // zeigt vorhandenes Material im Party-Inventar an
                            break;
                        case 3:
                            zurückMenue = true;
                            // Zurück zum GameHubMenü
                            break;
                    }
                } else {
                    System.out.println("Eingabe war Fehlerhaft");
                }
            }
        }
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void gegenstandVerbessern(PartyController partyController) {
        verbessernMenueAnzeigen();
        int eingabe;
        boolean eingabeKorrekt = false;

        while (!eingabeKorrekt) {
            eingabe = ScannerHelfer.nextInt();
            if (eingabe >= 1 && eingabe <= 6) {
                eingabeKorrekt = true;
                switch (eingabe) {
                    case 1:
                        verbessernWaffen(partyController);
                        // Öffnen Waffeninventar mit verbesserungsOption
                        break;
                    case 2:
                        verbessernRuestungen(partyController);
                        // Öffnen Rüstungsinventar mit verbesserungsOption
                        break;
                    case 3:
                        verbessernAccessoires(partyController);
                        // Öffnen Accessoireinventar mit verbesserungsOption
                        break;
                    case 4:
                        schmiedeAnzeigen(partyController);
                        // Geht zurück zur Schmiedeuebersicht
                        break;
                }
            } else {
                System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
            }
        }
    }
    //TODO ASCII einfuegen
    private void schmiedeBildAnzeigen() {
        System.out.println("Schmiede\n" +
                "          ) ) )                     ) ) )\n" +
                "        ( ( (                      ( ( (\n" +
                "      ) ) )                       ) ) )\n" +
                "   (~~~~~~~~~)                 (~~~~~~~~~)\n" +
                "    |       |                   |        |\n" +
                "    |       |                   |       |\n" +
                "    I      _._                  I       _._\n" +
                "    I    /'   `\\                I     /'   `\\\n" +
                "    I   |   N   |               I    |   N   |\n" +
                "    f   |   |~~~~~~~~~~~~~~|    f    |    |~~~~~~~~~~~~~~|\n" +
                "  .'    |   ||~~~~~~~~|    |  .'     |    | |~~~~~~~~|   |\n" +
                "/'______|___||__###___|____|/'_______|____|_|__###___|___|");
    }
    private void vorhandenesMaterialAnzeigen(PartyController partyController) {

        System.out.println("Übersicht vorhandenes Material:");
        for (int i = 0; i < partyController.getInventar.getMaterialInventar.size(); i++) {
            Material tmp = partyController.getInventar.getMaterialInventar(i);
            System.out.printf("%d. %n", i + 1);
            printMaterial(tmp);
        }
    }

    /*
     * @author OF Stetter
     * @since 18.11.23
     * oeffnet das Menue fuer die Waffenverbesserung, zeigt alle ausgeruesteten Waffen der Party an mit akt. Lvl -> neues Level
     * Kosten der Verbesserung und das dafuer benoetigte sowie vorhandenes Material
     */
    private void verbessernWaffen(PartyController partyController) {
        boolean istEingabeKorrekt = false;
        ArrayList<Waffe> ausgeruesteteWaffen = new ArrayList<>();
        System.out.println("Welche Waffe möchten Sie verbessern?");
        for (int i = 0; i < partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(party).size(); i++) {
            if (partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i) instanceof Waffe) {
                Waffe tmp = partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i);
                ausgeruesteteWaffen.add(tmp);
                System.out.printf("%d. %n", i + 1);
                printWaffe(tmp);
            }
        }
        int eingabe = 0;
        while (!istEingabeKorrekt) {
            try {
                System.out.print("Ihre Auswahl: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteWaffen.size()){
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        System.out.println("Waffe: " + ausgeruesteteWaffen.get(eingabe-1).getName() + "/nAktuelles Level: "
                + ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung() + " --> Neues Level: " + ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1 +
                "Benoetigtes/Vorhandenes Material: " + );


        System.out.printf("%n%d. Zurück zur Verbesserungsübersicht");
    }

    private void verbessernRuestungen(PartyController partyController) {

        int auswahlObjekt;
        boolean eingabeKorrekt = false;


        System.out.println("Welche Rüstung möchten Sie verbessern?");
        for (int i = 0; i < partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(party).size(); i++) {
            if (partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i) instanceof Ruestung) {
                Ruestung tmp = partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i);
                System.out.printf("%d. %n", i + 1);
                printRuestung(tmp);
            }
            System.out.printf("%n%d. Zurück zur Verbesserungsübersicht");
        }
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void verbessernAccessoires(PartyController partyController) {

        System.out.println("Welches Accessoire möchten Sie verbessern?");
        for (int i = 0; i < partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(party).size(); i++) {
            if (partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i) instanceof Accessoire) {
                Accessoire tmp = partyController.getAusruestungsgegenstandInventar.getGetrageneAusruestungsgegenstaende(i);
                System.out.printf("%d. %n", i + 1);
                printAccessoire(tmp);
            }
        }
        System.out.printf("%n%d. Zurück zur Verbesserungsübersicht");
    }

    private void schmiedeMenueAnzeigen() {
        System.out.println("Was möchten Sie verbessern?");
        System.out.println("1. Gegenstände verbessern");
        System.out.println("2. Vorhandenes Material anzeigen");
        System.out.println("3. Zurück zum GameHub");
    }

    private void verbessernMenueAnzeigen() {
        System.out.println("Was möchten Sie verkaufen?");
        System.out.println("1. Waffen verbessern");
        System.out.println("2. Rüstungen verbessern");
        System.out.println("3. Accessoires verbessern");
        System.out.println("4. Zurück zur Schmiedeuebersicht");
    }

    private void printWaffe(Waffe waffe) {
        System.out.println("Name: " + waffe.getName());
        if (waffe.getAttacke() > 0) {
            System.out.println("Attacke: " + waffe.getAttacke());
        } else {
            System.out.println("MagischeAttacke: " + waffe.getMagischeAttacke()());
        }
        System.out.println("Bonus: " + waffe.getBonus() + " " + waffe.getBonusUmfang());
        System.out.println("LevelAnforderung: " + waffe.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + waffe.getVerkaufswert());
        System.out.println();
    }

    /**
     * @param ruestung
     * @author OF Stetter
     * @since 18.11.23
     */
    private void printRuestung(Ruestung ruestung) {
        System.out.println("Name: " + ruestung.getName());
        if (ruestung.getVerteidigung() > 0) {
            System.out.println("Verteidigung: " + ruestung.getVerteidigung());
        } else {
            System.out.println("MagischeVerteidigung: " + ruestung.getMagischeVerteidigung());
        }
        System.out.println("Bonus: " + ruestung.getBonus() + " " + ruestung.getBonusUmfang());
        System.out.println("LevelAnforderung: " + ruestung.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + ruestung.getVerkaufswert());
        System.out.println();
    }

    /*
     * @param accessoire
     * @author OF Stetter
     * @since 18.11.23
     */
    private void printAccessoire(Accessoire accessoire) {
        System.out.println("Name: " + accessoire.getName());
        System.out.println("Bonus: " + accessoire.getBonus + " " + accessoire.getBonusumfang()); // Bonus noch im Accessoire
        System.out.println("LevelAnforderung: " + accessoire.getLevelAnforderung());
        System.out.println();
        System.out.println("Verkaufspreis: " + accessoire.getVerkaufswert());
        System.out.println();
    }
    private void printMaterial(Material material) {
        System.out.println("Name: " + material.getName());
        System.out.println();
        System.out.println("Verkaufspreis: " + material.getVerkaufswert());
        System.out.println();
    }

}

