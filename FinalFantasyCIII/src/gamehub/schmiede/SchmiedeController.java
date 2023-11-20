package gamehub.schmiede;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.GegenstandController;
import gegenstand.material.Material;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.AusruestungsgegenstandInventar;
import party.PartyController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SchmiedeController {

   // methoden zum upgraden

    PartyController partyController;
    private final HashMap<Integer, Integer> AUFRUESTUNGSKOSTEN = new HashMap<>();
    private final HashMap<Integer, String[]> AUFRUESTUNGSKOSTENMAT1 = new HashMap<>();
    private final HashMap<Integer, String[]> AUFRUESTUNGSKOSTENMAT2 = new HashMap<>();


    public SchmiedeController(PartyController partyController) {
        this.partyController = partyController;
        AUFRUESTUNGSKOSTEN.put(2,200);
        AUFRUESTUNGSKOSTEN.put(3,300);
        AUFRUESTUNGSKOSTEN.put(4,400);
        AUFRUESTUNGSKOSTEN.put(5,500);

        AUFRUESTUNGSKOSTENMAT1.put(2, new String[]{"Eisenerz", "3"});
        AUFRUESTUNGSKOSTENMAT1.put(3, new String[]{"Silbererz", "3"});
        AUFRUESTUNGSKOSTENMAT1.put(4, new String[]{"Golderz", "3"});
        AUFRUESTUNGSKOSTENMAT1.put(5, new String[]{"Mithril", "2"});
        AUFRUESTUNGSKOSTENMAT2.put(2, new String[]{"Popel", "3"});
        AUFRUESTUNGSKOSTENMAT2.put(3, new String[]{"Schleim", "3"});

    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    public void schmiedeAnzeigen() {
        boolean zurueckMenue = false;
        int eingabe;
        boolean eingabeKorrekt = false;
        while (!zurueckMenue) {
            schmiedeBildAnzeigen();
            schmiedeMenueAnzeigen();


            while (!eingabeKorrekt) {
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= 4) {
                    eingabeKorrekt = true;
                    switch (eingabe) {
                        case 1:
                            KonsolenAssistent.clear();
                            gegenstandVerbessern();
                            // Öffnet ausgeruestete Waffen (Party)
                            break;
                        case 2:
                            KonsolenAssistent.clear();
                            vorhandenesMaterialAnzeigen();
                            // zeigt vorhandenes Material im Party-Inventar an
                            break;
                        case 3:
                            zurueckMenue = true;
                            // Zurück zum GameHubMenü
                            break;
                        default:
                            System.out.println("Eingabe war Fehlerhaft");
                            break;
                    }
                }
            }
        }
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void gegenstandVerbessern() {
        verbessernMenueAnzeigen();
        int eingabe;
        boolean eingabeKorrekt = false;

        while (!eingabeKorrekt) {
            eingabe = ScannerHelfer.nextInt();
            if (eingabe >= 1 && eingabe <= 6) {
                eingabeKorrekt = true;
                switch (eingabe) {
                    case 1:
                        KonsolenAssistent.clear();
                        verbessernWaffen();
                        // Öffnen Waffeninventar mit verbesserungsOption
                        break;
                    case 2:
                        KonsolenAssistent.clear();
                        verbessernRuestungen();
                        // Öffnen Rüstungsinventar mit verbesserungsOption
                        break;
                    case 3:
                        KonsolenAssistent.clear();
                        verbessernAccessoires();
                        // Öffnen Accessoireinventar mit verbesserungsOption
                        break;
                    case 4:
                        KonsolenAssistent.clear();
                        schmiedeAnzeigen();
                        // Geht zurück zur Schmiedeuebersicht
                        break;
                    default:
                        System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                        break;
                }
            }
        }
    }

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
    /*
     * @author OF Stetter
     * @since 18.11.23
     * oeffnet eine Uebersicht ueber das vorhandene Material im Inventar
     */
    private void vorhandenesMaterialAnzeigen() {

        System.out.println("Übersicht vorhandenes Material:");

        for (Material material : partyController.getParty().getMaterialien().keySet()) {
            System.out.printf("Material: %s Menge: %d%n", material.getName(), partyController.getParty().getMaterialien().get(material));
        }
        ScannerHelfer.nextLine();
        schmiedeAnzeigen();
    }

    /*
     * @author OF Stetter
     * @since 18.11.23
     * oeffnet das Menue fuer die Waffenverbesserung, zeigt alle ausgeruesteten Waffen der Party an mit akt. Lvl -> neues Level,
     * Kosten(Gold) der Verbesserung und das dafuer benoetigte sowie vorhandenes Material
     */
    private void verbessernWaffen() {
        boolean istEingabeKorrekt = false;
        int eingabe = 0;
        System.out.println("Welche Waffe möchten Sie verbessern?");
        ArrayList<Waffe> ausgeruesteteWaffen = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneWaffen(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteWaffen.size(); i++) {
                    System.out.printf("%d Waffenname: %s Physische Attacke: %s Magische Attacke: %s%n", i+1, ausgeruesteteWaffen.get(i).getName(),
                            ausgeruesteteWaffen.get(i).getAttacke(), ausgeruesteteWaffen.get(i).getMagischeAttacke());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteWaffen.size()){
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        istEingabeKorrekt = false;
        System.out.println("Waffe: " + ausgeruesteteWaffen.get(eingabe-1).getName() + "\nAktuelles Level: "
                + ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung() + " --> Neues Level: " + ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1 +
                        " Kosten fuer Verbesserung " + AUFRUESTUNGSKOSTEN.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)
                + "\nBenoetigtes/Vorhandenes Material: " + AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[0])) +
                ", " + AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteWaffen.get(eingabe-1).getLevelAnforderung()+1)[0])));
        System.out.printf("Physische Attacke: %d  -----> %d%n", ausgeruesteteWaffen.get(eingabe-1).getAttacke(), ausgeruesteteWaffen.get(eingabe-1).getAttacke()+1);
        System.out.printf("Magische Attacke: %d  -----> %d%n", ausgeruesteteWaffen.get(eingabe-1).getMagischeAttacke(), ausgeruesteteWaffen.get(eingabe-1).getMagischeAttacke()+1);

        while (!istEingabeKorrekt){
            System.out.println("Upgrade durchfuehren?");
            System.out.println("1. Ja");
            System.out.println("2. Nein");
            System.out.println("Bitte waehlen:");
            int auswahl = ScannerHelfer.nextInt();
            switch (auswahl){
                case 1:
                    aufwerten(ausgeruesteteWaffen.get(eingabe-1));
                    istEingabeKorrekt = true;
                    break;
                default:
                    verbessernWaffen();
                    break;
            }
        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
        schmiedeMenueAnzeigen();
    }

    private void verbessernRuestungen() {
        boolean istEingabeKorrekt = false;
        int eingabe = 0;
        System.out.println("Welche Ruestung möchten Sie verbessern?");
        ArrayList<Ruestung> ausgeruesteteRuestungen = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneRuestung(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteRuestungen.size(); i++) {
                    System.out.printf("%d Waffenname: %s Physische Verteidigung: %s Magische Verteidigung: %s%n", i+1, ausgeruesteteRuestungen.get(i).getName(),
                            ausgeruesteteRuestungen.get(i).getVerteidigung(), ausgeruesteteRuestungen.get(i).getMagischeVerteidigung());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteRuestungen.size()){
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        istEingabeKorrekt = false;
        System.out.println("Ruestung: " + ausgeruesteteRuestungen.get(eingabe-1).getName() + "\nAktuelles Level: "
                + ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung() + " --> Neues Level: " + ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1 +
                "Kosten fuer Verbesserung " + AUFRUESTUNGSKOSTEN.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)
                + "\nBenoetigtes/Vorhandenes Material: " + AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[0])) +
                ", " + AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteRuestungen.get(eingabe-1).getLevelAnforderung()+1)[0])));
        System.out.printf("Physische Verteidigung: %d  -----> %d%n", ausgeruesteteRuestungen.get(eingabe-1).getVerteidigung(), ausgeruesteteRuestungen.get(eingabe-1).getVerteidigung()+1);
        System.out.printf("Magische Verteidigung: %d  -----> %d%n", ausgeruesteteRuestungen.get(eingabe-1).getMagischeVerteidigung(), ausgeruesteteRuestungen.get(eingabe-1).getMagischeVerteidigung()+1);

        while (!istEingabeKorrekt){
            System.out.println("Upgrade durchfuehren?");
            System.out.println("1. Ja");
            System.out.println("2. Nein");
            System.out.println("Bitte waehlen:");
            int auswahl = ScannerHelfer.nextInt();
            switch (auswahl){
                case 1:
                    aufwerten(ausgeruesteteRuestungen.get(eingabe-1));
                    istEingabeKorrekt = true;
                    break;
                default:
                    verbessernRuestungen();
                    break;
            }
        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
        schmiedeMenueAnzeigen();
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void verbessernAccessoires() {
        boolean istEingabeKorrekt = false;
        int eingabe = 0;
        System.out.println("Welches Accessoire möchten Sie verbessern?");
        ArrayList<Accessoire> ausgeruesteteAccessoires = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneAccessiores(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteAccessoires.size(); i++) {
                    System.out.printf("%d Accessoirename: %s Max Gesundheitspunkte: %s Max Manapunkte: %s%n", i+1, ausgeruesteteAccessoires.get(i).getName(),
                            ausgeruesteteAccessoires.get(i).getMaxGesundheitsPunkte(), ausgeruesteteAccessoires.get(i).getMaxManaPunkte());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteAccessoires.size()){
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        istEingabeKorrekt = false;
        System.out.println("Accessoire: " + ausgeruesteteAccessoires.get(eingabe-1).getName() + "\nAktuelles Level: "
                + ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung() + " --> Neues Level: " + ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1 +
                "Kosten fuer Verbesserung " + AUFRUESTUNGSKOSTEN.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)
                + "\nBenoetigtes/Vorhandenes Material: " + AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT1.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[0])) +
                ", " + AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[0] + " " +
                AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[1] + " / " +
                partyController.getParty().getMaterialien().get(
                        GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT2.get(ausgeruesteteAccessoires.get(eingabe-1).getLevelAnforderung()+1)[0])));
        System.out.printf("Max Gesundheitspunkte: %d  -----> %d%n", ausgeruesteteAccessoires.get(eingabe-1).getMaxGesundheitsPunkte(), ausgeruesteteAccessoires.get(eingabe-1).getMaxGesundheitsPunkte()+1);
        System.out.printf("Max Manapunkte: %d  -----> %d%n", ausgeruesteteAccessoires.get(eingabe-1).getMaxManaPunkte(), ausgeruesteteAccessoires.get(eingabe-1).getMaxManaPunkte()+1);

        while (!istEingabeKorrekt){
            System.out.println("Upgrade durchfuehren?");
            System.out.println("1. Ja");
            System.out.println("2. Nein");
            System.out.println("Bitte waehlen:");
            int auswahl = ScannerHelfer.nextInt();
            switch (auswahl){
                case 1:
                    aufwerten(ausgeruesteteAccessoires.get(eingabe-1));
                    istEingabeKorrekt = true;
                    break;
                default:
                    verbessernAccessoires();
                    break;
            }
        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
        schmiedeMenueAnzeigen();
    }

    private void schmiedeMenueAnzeigen() {
        System.out.println("Was möchten Sie verbessern?");
        System.out.println("1. Gegenstände verbessern");
        System.out.println("2. Vorhandenes Material anzeigen");
        System.out.println("3. Zurück zum GameHub");
    }

    private void verbessernMenueAnzeigen() {
        System.out.println("Was möchten Sie verbessern?");
        System.out.println("1. Waffen verbessern");
        System.out.println("2. Rüstungen verbessern");
        System.out.println("3. Accessoires verbessern");
        System.out.println("4. Zurück zur Schmiedeuebersicht");
    }


    /**
     * Wertet einen Ausruestungsgegenstand auf
     * Laesst betroffenen Charakter den Ausruestungsgegenstand ablegen und anlegen
     * Setzt Ausruestungsgegenstand-Level +1 und passt Attribute an
     * @param ausruestungsgegenstand
     *
     * @since 20.11.2023
     * @author Stetter
     */
    private void aufwerten(Ausruestungsgegenstand ausruestungsgegenstand){
        ArrayList<SpielerCharakter> tmp = new ArrayList<>();
        tmp.add(partyController.getParty().getHauptCharakter());
        tmp.addAll(Arrays.asList(partyController.getParty().getNebenCharakter()));
        if (partyController.getParty().getGold() >= AUFRUESTUNGSKOSTEN.get(ausruestungsgegenstand.getLevelAnforderung()+1)
                && partyController.getParty().getMaterialien().
                get(GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT1.get(ausruestungsgegenstand.getLevelAnforderung()+1)[0]))
        >= Integer.parseInt(AUFRUESTUNGSKOSTENMAT1.get(ausruestungsgegenstand.getLevelAnforderung()+1)[1]) && partyController.getParty().getMaterialien().
                get(GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT2.get(ausruestungsgegenstand.getLevelAnforderung()+1)[0]))
                >= Integer.parseInt(AUFRUESTUNGSKOSTENMAT2.get(ausruestungsgegenstand.getLevelAnforderung()+1)[1])){
            partyController.goldAbziehen(AUFRUESTUNGSKOSTEN.get(ausruestungsgegenstand.getLevelAnforderung()+1));
            partyController.getParty().setMaterialien(GegenstandController.materialVerwenden(partyController.getParty().getMaterialien(),
                    GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT1.get(ausruestungsgegenstand.getLevelAnforderung()+1)[0]),
                    Integer.parseInt(AUFRUESTUNGSKOSTENMAT1.get(ausruestungsgegenstand.getLevelAnforderung()+1)[1])));
            partyController.getParty().setMaterialien(GegenstandController.materialVerwenden(partyController.getParty().getMaterialien(),
                    GegenstandController.rueckgabeSpezifischerMaterialien(AUFRUESTUNGSKOSTENMAT2.get(ausruestungsgegenstand.getLevelAnforderung()+1)[0]),
                    Integer.parseInt(AUFRUESTUNGSKOSTENMAT2.get(ausruestungsgegenstand.getLevelAnforderung()+1)[1])));
            for (int i = 0; i < tmp.size(); i++) {
                if (CharakterController.ausruestungAnzeigen(tmp.get(i)).contains(ausruestungsgegenstand)){
                    CharakterController.ausruestungAusziehen(tmp.get(i), ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                    if (ausruestungsgegenstand instanceof Waffe){
                        ((Waffe)ausruestungsgegenstand).setAttacke(((Waffe) ausruestungsgegenstand).getAttacke()+1);
                        ((Waffe)ausruestungsgegenstand).setMagischeAttacke(((Waffe) ausruestungsgegenstand).getMagischeAttacke()+1);

                    } else if (ausruestungsgegenstand instanceof Ruestung){
                        ((Ruestung) ausruestungsgegenstand).setVerteidigung(((Ruestung)ausruestungsgegenstand).getVerteidigung()+1);
                        ((Ruestung) ausruestungsgegenstand).setMagischeVerteidigung(((Ruestung)ausruestungsgegenstand).getMagischeVerteidigung()+1);
                    } else if (ausruestungsgegenstand instanceof Accessoire){
                        ((Accessoire) ausruestungsgegenstand).setMaxGesundheitsPunkte(((Accessoire) ausruestungsgegenstand).getMaxGesundheitsPunkte()+1);
                        ((Accessoire) ausruestungsgegenstand).setMaxManaPunkte(((Accessoire) ausruestungsgegenstand).getMaxManaPunkte()+1);
                    }
                    ausruestungsgegenstand.setLevelAnforderung((ausruestungsgegenstand).getLevelAnforderung()+1);
                    CharakterController.ausruestungAnlegen(tmp.get(i), ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                    break;
                }
            }
        } else {
            System.out.println("Nicht genug Ressourcen!");
        }
    }

}

