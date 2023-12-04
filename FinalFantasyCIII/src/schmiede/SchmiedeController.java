package schmiede;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.material.*;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import hilfsklassen.ZufallsZahlenGenerator;
import party.AusruestungsgegenstandInventar;
import party.PartyController;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SchmiedeController {
    ViewController viewController;

    // methoden zum upgraden

    PartyController partyController;
    private final ArrayList<Map<Material, Integer>> AUFRUESTUNGSKOSTEN = new ArrayList<>();


    public SchmiedeController(PartyController partyController, ViewController viewController) {
        this.viewController = viewController;
        this.partyController = partyController;
        int maxLvlVerbesserung = 99;


        for (int i = 0; i < maxLvlVerbesserung; i++) {
            AUFRUESTUNGSKOSTEN.add(new HashMap<>());

            if ((i % 5) == 0) {
                AUFRUESTUNGSKOSTEN.get(i).put(Material.MITHRIL, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
                AUFRUESTUNGSKOSTEN.get(i).put(Material.POPEL, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
            } else if ((i % 3) == 0) {
                AUFRUESTUNGSKOSTEN.get(i).put(Material.GOLDERZ, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
                AUFRUESTUNGSKOSTEN.get(i).put(Material.EISENERZ, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
            } else if ((i % 2) == 0) {
                AUFRUESTUNGSKOSTEN.get(i).put(Material.SILBERERZ, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
                AUFRUESTUNGSKOSTEN.get(i).put(Material.SCHLEIM, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
            } else {
                AUFRUESTUNGSKOSTEN.get(i).put(Material.SILBERERZ, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
                AUFRUESTUNGSKOSTEN.get(i).put(Material.GOLDERZ, ZufallsZahlenGenerator.zufallsZahlIntAb1(3));
            }

        }

    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    public void schmiedeAnzeigen() {
        boolean zurueckMenue = false;
        int eingabe;
        while (!zurueckMenue) {
            schmiedeBildAnzeigen();
            schmiedeMenueAnzeigen();
            eingabe = ScannerHelfer.nextInt();
            if (eingabe >= 1 && eingabe <= 4) {
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

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void gegenstandVerbessern() {
        verbessernMenueAnzeigen();
        int eingabe;
        eingabe = ScannerHelfer.nextInt();
        if (eingabe >= 1 && eingabe <= 6) {
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
                    // Geht zurück zur Schmiedeuebersicht
                    break;
                default:
                    System.out.println("Eingabe war Fehlerhaft, versuchen Sie es erneut");
                    break;
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

        System.out.println("Uebersicht vorhandenes Material:");

        for (Material material : partyController.getParty().getMaterialien().keySet()) {
            System.out.printf("Material: %s Menge: %d%n", material.getName(), partyController.getParty().getMaterialien().get(material));
        }
        ScannerHelfer.nextLine();
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
        System.out.println("Welche Waffe moechten Sie verbessern?");
        ArrayList<Waffe> ausgeruesteteWaffen = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneWaffen(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteWaffen.size(); i++) {
                    System.out.printf("%d Waffenname: %s Physische Attacke: %s Magische Attacke: %s%n", i + 1, ausgeruesteteWaffen.get(i).getName(),
                            ausgeruesteteWaffen.get(i).getAttacke(), ausgeruesteteWaffen.get(i).getMagischeAttacke());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteWaffen.size()) {
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        int levelAnforderung = ausgeruesteteWaffen.get(eingabe - 1).getLevelAnforderung();
        System.out.print("Waffe: " + ausgeruesteteWaffen.get(eingabe - 1).getName() + "\nAktuelles Level: "
                + levelAnforderung + " --> Neues Level: " + (levelAnforderung + 1) +
                " Kosten fuer Verbesserung " + ((levelAnforderung + 1) * 100)
                + "\nVorhandenes/Benoetigtes Material: ");
        for (Map.Entry<Material, Integer> entry : AUFRUESTUNGSKOSTEN.get(levelAnforderung - 1).entrySet()) {
            int vorhandeneMenge = partyController.getParty().getMaterialien().get(entry.getKey()).get();
            System.out.print("         " + entry.getKey().getName() + ": " + vorhandeneMenge + "/" + entry.getValue());
        }
        System.out.printf("%nPhysische Attacke: %d  -----> %d%n", ausgeruesteteWaffen.get(eingabe - 1).getAttacke(), ausgeruesteteWaffen.get(eingabe - 1).getAttacke() + 1);
        System.out.printf("Magische Attacke: %d  -----> %d%n", ausgeruesteteWaffen.get(eingabe - 1).getMagischeAttacke(), ausgeruesteteWaffen.get(eingabe - 1).getMagischeAttacke() + 1);

        System.out.println("Upgrade durchfuehren?");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        System.out.println("Bitte waehlen:");
        int auswahl = ScannerHelfer.nextInt();
        switch (auswahl) {
            case 1:
                aufwerten(ausgeruesteteWaffen.get(eingabe - 1));
                break;
            default:
                break;
        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
    }

    private void verbessernRuestungen() {
        boolean istEingabeKorrekt = false;
        int eingabe = 0;
        System.out.println("Welche Ruestung moechten Sie verbessern?");
        ArrayList<Ruestung> ausgeruesteteRuestungen = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneRuestung(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteRuestungen.size(); i++) {
                    System.out.printf("%d Ruestungsname: %s Physische Verteidigung: %s Magische Verteidigung: %s%n", i + 1, ausgeruesteteRuestungen.get(i).getName(),
                            ausgeruesteteRuestungen.get(i).getVerteidigung(), ausgeruesteteRuestungen.get(i).getMagischeVerteidigung());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteRuestungen.size()) {
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        int levelAnforderung = ausgeruesteteRuestungen.get(eingabe - 1).getLevelAnforderung();
        System.out.print("Ruestung: " + ausgeruesteteRuestungen.get(eingabe - 1).getName() + "\nAktuelles Level: "
                + levelAnforderung + " --> Neues Level: " + (levelAnforderung + 1) +
                " Kosten fuer Verbesserung " + ((levelAnforderung + 1) * 100)
                + "\nVorhandenes/Benoetigtes Material: ");
        for (Map.Entry<Material, Integer> entry : AUFRUESTUNGSKOSTEN.get(levelAnforderung - 1).entrySet()) {
            int vorhandeneMenge = partyController.getParty().getMaterialien().get(entry.getKey()).get();
            System.out.print("         " + entry.getKey().getName() + ": " + vorhandeneMenge + "/" + entry.getValue());
        }
        System.out.printf("%nPhysische Verteidigung: %d  -----> %d%n", ausgeruesteteRuestungen.get(eingabe - 1).getVerteidigung(), ausgeruesteteRuestungen.get(eingabe - 1).getVerteidigung() + 1);
        System.out.printf("Magische Verteidigung: %d  -----> %d%n", ausgeruesteteRuestungen.get(eingabe - 1).getMagischeVerteidigung(), ausgeruesteteRuestungen.get(eingabe - 1).getMagischeVerteidigung() + 1);

        System.out.println("Upgrade durchfuehren?");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        System.out.println("Bitte waehlen:");
        int auswahl = ScannerHelfer.nextInt();
        switch (auswahl) {
            case 1:
                aufwerten(ausgeruesteteRuestungen.get(eingabe - 1));
                break;
            default:
                break;

        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
    }

    /**
     * @author OF Stetter
     * @since 18.11.23
     */
    private void verbessernAccessoires() {
        boolean istEingabeKorrekt = false;
        int eingabe = 0;
        System.out.println("Welches Accessoire moechten Sie verbessern?");
        ArrayList<Accessoire> ausgeruesteteAccessoires = new ArrayList<>(AusruestungsgegenstandInventar.getGetrageneAccessiores(partyController.getParty()));

        while (!istEingabeKorrekt) {
            try {
                for (int i = 0; i < ausgeruesteteAccessoires.size(); i++) {
                    System.out.printf("%d Accessoirename: %s Max Gesundheitspunkte: %s Max Manapunkte: %s%n", i + 1, ausgeruesteteAccessoires.get(i).getName(),
                            ausgeruesteteAccessoires.get(i).getMaxGesundheitsPunkte(), ausgeruesteteAccessoires.get(i).getMaxManaPunkte());
                }
                System.out.print("Bitte auswaehlen: ");
                eingabe = ScannerHelfer.nextInt();
                if (eingabe >= 1 && eingabe <= ausgeruesteteAccessoires.size()) {
                    istEingabeKorrekt = true;
                }
            } catch (Exception e) {
                System.out.print("Eingabe Fehlerhaft! Bitte andere Eingabe versuchen!");
                e.printStackTrace();
            }
        }
        int levelAnforderung = ausgeruesteteAccessoires.get(eingabe - 1).getLevelAnforderung();
        System.out.print("Accessoires: " + ausgeruesteteAccessoires.get(eingabe - 1).getName() + "\nAktuelles Level: "
                + levelAnforderung + " --> Neues Level: " + (levelAnforderung + 1) +
                " Kosten fuer Verbesserung " + ((levelAnforderung + 1) * 100)
                + "\nVorhandenes/Benoetigtes Material: ");
        for (Map.Entry<Material, Integer> entry : AUFRUESTUNGSKOSTEN.get(levelAnforderung - 1).entrySet()) {
            int vorhandeneMenge = partyController.getParty().getMaterialien().get(entry.getKey()).get();
            System.out.print("         " + entry.getKey().getName() + ": " + vorhandeneMenge + "/" + entry.getValue());
        }
        System.out.printf("%nMax Gesundheitspunkte: %d  -----> %d%n", ausgeruesteteAccessoires.get(eingabe - 1).getMaxGesundheitsPunkte(), ausgeruesteteAccessoires.get(eingabe - 1).getMaxGesundheitsPunkte() + 1);
        System.out.printf("Max Manapunkte: %d  -----> %d%n", ausgeruesteteAccessoires.get(eingabe - 1).getMaxManaPunkte(), ausgeruesteteAccessoires.get(eingabe - 1).getMaxManaPunkte() + 1);

        System.out.println("Upgrade durchfuehren?");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        System.out.println("Bitte waehlen:");
        int auswahl = ScannerHelfer.nextInt();
        switch (auswahl) {
            case 1:
                aufwerten(ausgeruesteteAccessoires.get(eingabe - 1));
                break;
            default:
                break;

        }
        System.out.println("Eine Taste druecke um zum Schmiedemenue zurueck zu kehren.");
        ScannerHelfer.nextLine();
    }

    private void schmiedeMenueAnzeigen() {
        System.out.println("Was moechten Sie verbessern?");
        System.out.println("1. Gegenstaende verbessern");
        System.out.println("2. Vorhandenes Material anzeigen");
        System.out.println("3. Zurueck zum GameHub");
    }

    private void verbessernMenueAnzeigen() {
        System.out.println("Was moechten Sie verbessern?");
        System.out.println("1. Waffen verbessern");
        System.out.println("2. Ruestungen verbessern");
        System.out.println("3. Accessoires verbessern");
        System.out.println("4. Zurueck zur Schmiedeuebersicht");

    }


    /**
     * Wertet einen Ausruestungsgegenstand auf
     * Laesst betroffenen Charakter den Ausruestungsgegenstand ablegen und anlegen
     * Setzt Ausruestungsgegenstand-Level +1 und passt Attribute an
     *
     * @param ausruestungsgegenstand
     * @author Stetter
     * @since 20.11.2023
     */
    private void aufwerten(Ausruestungsgegenstand ausruestungsgegenstand) {
        int levelAnforderung = ausruestungsgegenstand.getLevelAnforderung();
        boolean genugGold = ((ausruestungsgegenstand.getLevelAnforderung() + 1) * 100) <= partyController.getPartyGold();
        boolean genugMaterial = true;
        for (Map.Entry<Material, Integer> entry : AUFRUESTUNGSKOSTEN.get(levelAnforderung - 1).entrySet()) {
            boolean mengeVorhanden = partyController.getParty().getMaterialien().get(entry.getKey()).get() >= entry.getValue();
            if (!mengeVorhanden) {
                genugMaterial = false;
            }
        }



        if (genugGold && genugMaterial) {
            partyController.goldAbziehen((ausruestungsgegenstand.getLevelAnforderung() + 1) * 100);

            for (Map.Entry<Material, Integer> entry : AUFRUESTUNGSKOSTEN.get(levelAnforderung - 1).entrySet()) {
                partyController.getParty().getMaterialien().get(entry.getKey()).set(partyController.getParty().getMaterialien().get(entry.getKey()).get() - entry.getValue());
            }
            ArrayList<SpielerCharakter> tmp = new ArrayList<>();
            tmp.add(partyController.getParty().getHauptCharakter());
            tmp.addAll(Arrays.asList(partyController.getParty().getNebenCharakter()));
            for (int i = 0; i < tmp.size(); i++) {
                if (tmp.get(i) != null && CharakterController.ausruestungAnzeigen(tmp.get(i)).contains(ausruestungsgegenstand) && (ausruestungsgegenstand.getLevelAnforderung() + 1) <= tmp.get(i).getLevel()) {
                    CharakterController.ausruestungAusziehen(tmp.get(i), ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                    if (ausruestungsgegenstand instanceof Waffe) {
                        ((Waffe) ausruestungsgegenstand).setAttacke(((Waffe) ausruestungsgegenstand).getAttacke() + 1);
                        ((Waffe) ausruestungsgegenstand).setMagischeAttacke(((Waffe) ausruestungsgegenstand).getMagischeAttacke() + 1);
                    } else if (ausruestungsgegenstand instanceof Ruestung) {
                        ((Ruestung) ausruestungsgegenstand).setVerteidigung(((Ruestung) ausruestungsgegenstand).getVerteidigung() + 1);
                        ((Ruestung) ausruestungsgegenstand).setMagischeVerteidigung(((Ruestung) ausruestungsgegenstand).getMagischeVerteidigung() + 1);
                    } else if (ausruestungsgegenstand instanceof Accessoire) {
                        ((Accessoire) ausruestungsgegenstand).setMaxGesundheitsPunkte(((Accessoire) ausruestungsgegenstand).getMaxGesundheitsPunkte() + 1);
                        ((Accessoire) ausruestungsgegenstand).setMaxManaPunkte(((Accessoire) ausruestungsgegenstand).getMaxManaPunkte() + 1);
                    }
                    ausruestungsgegenstand.setLevelAnforderung((ausruestungsgegenstand).getLevelAnforderung() + 1);
                    CharakterController.ausruestungAnlegen(tmp.get(i), ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                    break;
                } else {
                    System.out.println("Level des Aufruestungsgegenstandes wuerde Level des Charakters uebersteigen!");
                }
            }
        } else {
            System.out.println("Nicht genug Ressourcen!");
        }
    }

}

