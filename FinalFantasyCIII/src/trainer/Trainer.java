package trainer;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import charakter.model.klassen.spezialisierungen.*;
import trainer.faehigkeiten.FahigkeitenMenu;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.manatraenke.GrosserManatrank;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;


/**
 * The type Trainer.
 *
 * @param
 * @author Thomas Maass
 * @return
 * @since 20.11.23
 */
public class Trainer {
    private TrainerController trainerController;

    /**
     * Instantiates a new Trainer.
     *
     * @param trainerController the trainer controller
     */
    public Trainer(TrainerController trainerController) {
        this.trainerController = trainerController;
    }

    //Deklaration der Variablen
    private SpielerCharakter currentCharakter = null;


    // VORGABEWERTE !!!!!

    /**
     * The Basis kosten klassenwechsel.
     */
    private static int basisKostenKlassenwechsel = 50; // Vorgaben fuer die Kosten des Klassenwechsels
    /**
     * The Basis kosten spezialisierung.
     */
    private static int basisKostenSpezialisierung = 100; // Vorgaben fuer die Kosten der Spezialisierung
    /**
     * The Basis kosten faehigkeiten anpassen.
     */
    private static int basisKostenFaehigkeitenAnpassen = 1; // Vorgaben fuer die Anpassung der Faehigkeiten
    /**
     * The Basis Kosten fuer Attributsanpassung
     */
    private static int basisKostensAttributeAnpassen = 1;  // VorgabeWert fuer das Anpassen der Attribute

    /**
     * Trainer anzeigen.
     */
// Gottmodus / testModus
    public void trainerAnzeigen() {
        SpielerCharakter[] dasTeam = trainerController.getPartyController().getTeammitglieder();

        // GottModus --> Nutzer zum testen
        if (dasTeam[0].getName().equals("Markus")) {
            dasTeam[0].setPhysischeAttacke(99999);
            dasTeam[0].setBeweglichkeit(99999);
            dasTeam[0].setResistenz(99999);
            dasTeam[0].setMagischeVerteidigung(99999);
            dasTeam[0].setVerteidigung(99999);
            dasTeam[0].setMagischeAttacke(99999);
            dasTeam[0].setMaxManaPunkte(99999);
            dasTeam[0].setOffeneAttributpunkte(99999);
            dasTeam[0].setMaxGesundheitsPunkte(99999);
            dasTeam[0].setOffeneFaehigkeitspunkte(99999);
            dasTeam[0].setGenauigkeit(99999);
            dasTeam[0].setLevel(666);
            // Gold setzen
            trainerController.getPartyController().getParty().setGold(999999);
            //Setzen von Materialien
            trainerController.getPartyController().materialHinzufuegen(new Eisenerz(), 999999);
            trainerController.getPartyController().materialHinzufuegen(new Golderz(), 999999);
            trainerController.getPartyController().materialHinzufuegen(new Mithril(), 999999);
            trainerController.getPartyController().materialHinzufuegen(new Popel(), 999999);
            trainerController.getPartyController().materialHinzufuegen(new Schleim(), 999999);
            trainerController.getPartyController().materialHinzufuegen(new Silbererz(), 999999);
            // Setzen von Verbrauchmaterial
            trainerController.getPartyController().verbrauchsgegenstandHinzufuegen(new GrosserHeiltrank(), 999999);
            trainerController.getPartyController().verbrauchsgegenstandHinzufuegen(new GrosserManatrank(), 999999);
            dasTeam[0].setGeschichte("Weil Er Markus ist !!!!");
        }
        boolean gueltigeEingabe = false;
        while (!gueltigeEingabe) {
            //Hauptmenu des Trainers
            // Klasse tauschen
            StringBuilder sb = new StringBuilder();
            sb.append("\n" +
                    "___________             .__                     \n" +
                    "\\__    ___/___________  |__| ____   ___________ \n" +
                    "  |    |  \\_  __ \\__  \\ |  |/    \\_/ __ \\_  __ \\\n" +
                    "  |    |   |  | \\// __ \\|  |   |  \\  ___/|  | \\/\n" +
                    "  |____|   |__|  (____  /__|___|  /\\___  >__|   \n" +
                    "                      \\/        \\/     \\/       \n");

            //loeschen der Konsole
            KonsolenAssistent.clear();
            System.out.println(sb);
            // Die rote Schrift von Nils resetten !
            System.out.println(Farbauswahl.RESET);
            System.out.println("1.         Klasse tauschen");

            // Spezialisierung waehlen
            System.out.println("2.         Spezialisierung tauschen");

            // Attributpunkte vergeben
            System.out.println("3.         Faehigkeiten anpassen/erweitern");
            System.out.println("4.         Anpassen von Attributen");

            // zurueck zum HUB
            System.out.println("0.         Zurueck zum HUB");
            System.out.println("\n");
            System.out.println("Bitte treffen Sie Ihre Auswahl");
            System.out.println("----------------------------------------------------------------------");

            int scannerEingabe = ScannerHelfer.nextInt();
            switch (scannerEingabe) {
                case 1:
                    //Klasse tauschen aufrufen
                    gueltigeEingabe = true;
                    menuKlasseWechseln();
                    break;
                case 2:
                    //Spezialisierung tasuchen aufrufen
                    gueltigeEingabe = true;
                    menuSpezialisierungKaufen();
                    break;
                case 3:
                    //Attribute vergeben
                    menuFaehigkeitenAufwerten();
                    break;
                case 4:
                    gueltigeEingabe = true;

                    menuAttributeAnpassen();
                    break;
                case 0:
                    //Erstmal auskommentiert. Nils sagt das geht so ! Hoffen wir es
                    trainerController.getGameHubController().hubAnzeigen();
                    gueltigeEingabe = true;
                    break;
                default:
                    System.out.println("Die Eingabe ist ungueltig");
                    break;
            }
        }
    }

    /**
     * Trainer charakter auswahl.
     *
     * @return the spieler charakter
     */
    public SpielerCharakter trainerCharakterAuswahl() {
        SpielerCharakter[] dasTeam = trainerController.getPartyController().getTeammitglieder();

        boolean gueltigeEingabe = false;
        int charakterZeaehler = 0;
        for (SpielerCharakter derCharakter : dasTeam) {
            if (derCharakter != null) {
                charakterZeaehler++;

            }
        }
        while (!gueltigeEingabe) {
            //Bildschirm loeschen
            KonsolenAssistent.clear();
            for (int i = 0; i < dasTeam.length; i++) {
                if (dasTeam[i] != null) {
                    System.out.printf("%d.   %10s %s    %15s %s %15s %s %25s %s \n", i, "Name :", dasTeam[i].getName(), "Level :", dasTeam[i].getLevel(), "Klasse :", dasTeam[i].getKlasse().getBezeichnung(), "Spezialisierung :", dasTeam[i].getKlasse().getClass().getSimpleName());
                }
            }
            System.out.println("\n");
            System.out.println("Entspricht die Spezialisierung der Klasse, so hat der Held keine Spzialisierung");
            System.out.println("Bitte treffen Sie Ihre Auswahl. Welchen Charakter wollen Sie anpassen !");
            int scannerEingabe = ScannerHelfer.nextInt();
            if (scannerEingabe >= 0 && scannerEingabe < charakterZeaehler) {

                currentCharakter = dasTeam[scannerEingabe];
                gueltigeEingabe = true;

            } else {
                System.out.println("Falsche Eingabe !");
            }

        }
        return currentCharakter;
    }


    /**
     * Menu klasse wechseln.
     */
    public void menuKlasseWechseln() {
        // Wechsel der Klasse gegen Gebuehr
        // Eine Int Variable fuer den Kalssenwechsel. Sperrt ein AuswahlFeld
        int gesperrteAuswahl = -1;
        // Aufruf des Charakterauswahl
        SpielerCharakter derCharakter = trainerCharakterAuswahl();

        // Wir brauchen mehr Scanner
        int nutzerAuswahl;
        boolean gueltigeEingabe = false;
        int klassenauswahl = 0;
        KonsolenAssistent.clear();
        System.out.println("Der Charakter hat folgende Werte");
        System.out.println("Name : " + derCharakter.getName() + " hat die Klasse " + derCharakter.getKlasse().getBezeichnung());
        System.out.println("Der Wechsel zu einer anderen Klasse kostet " + basisKostenKlassenwechsel + " Gold. Sie haben derzeit " + trainerController.getPartyController().getPartyGold());
        // Anzeigend er moeglichen Klassen
        while (!gueltigeEingabe) {
            System.out.println("Folgende Klassen stehen zum Wechsel zur Verfuegung !");
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Physischer DD"))) {
                System.out.println("1.      Physischer DD");
            } else {
                gesperrteAuswahl = 1;

            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Magischer DD"))) {
                System.out.println("2.      Magischer DD");
            } else {
                gesperrteAuswahl = 2;

            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Tank"))) {
                System.out.println("3.      Tank");
            } else {
                gesperrteAuswahl = 3;
            }
            if (!(derCharakter.getKlasse().getBezeichnung().equals("Healer"))) {
                System.out.println("4.      Healer");
            } else {
                gesperrteAuswahl = 4;
            }
            System.out.println("0.      Abbruch");

            System.out.println("Bitte waehlen Sie aus den verfuegbaren Klassen");

            nutzerAuswahl = ScannerHelfer.nextInt();
            switch (nutzerAuswahl) {
                case 1:
                    // Wechsel zu PDD1
                    if (gesperrteAuswahl == 1) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }

                case 2:
                    // wechsel zu MDD
                    if (gesperrteAuswahl == 2) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }
                case 3:
                    // Wechsel zu TNK
                    if (gesperrteAuswahl == 3) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;

                    }

                case 4:
                    // Wechsel zu HLR
                    if (gesperrteAuswahl == 4) {
                        System.out.println("Ungueltige Klasse");
                        break;
                    } else {
                        klassenauswahl = nutzerAuswahl;
                        gueltigeEingabe = true;
                        break;
                    }

                case 0:
                    System.out.println("Klassen wechsel wurde abgebrochen");
                    gueltigeEingabe = true;
                    trainerController.trainerAnzeigen();
                    break;
                default:
                    System.out.println("ungueltige Eingabe");
                    break;
            }
        }
        gueltigeEingabe = false;
        //0=MDD / 1=HLR / 2=TNK / 3=HLR
        Klasse[] meineKlasse = {new PDD(), new MDD(), new TNK(), new HLR()};

        //charakterController.klasseAendern(derCharakter, meineKlasse[0]);

        // Nach Auswahl der neuen Klassen wird der Spieler erneut gefragt ob es das ist was er will
        // mit dem Hinweis darauf das seine (Klassen Level) KOMPLETT VERLOREN GEHEN !
        if (trainerController.getPartyController().getPartyGold() < basisKostenKlassenwechsel) {
            System.out.println("Du kannst die den Wechsel der Klasse nicht leisten");
            System.out.println("Es wuerde dich " + basisKostenKlassenwechsel + " kosten. Du hast aber nur " + trainerController.getPartyController().getPartyGold() + ". Schwierig !!");
        } else {
            // Hier wird die Klasse gewechselt
            CharakterController.klasseAendern(derCharakter, meineKlasse[klassenauswahl - 1]);
            trainerController.getPartyController().goldAbziehen(basisKostenKlassenwechsel);
            System.out.println("Der Charakter hat nur die Klasse " + derCharakter.getKlasse().getBezeichnung());
            System.out.println("Das Golder der Party betraegt nun " + trainerController.getPartyController().getPartyGold());
            // Durch den Wechsel der Klasse wir die Spezialisierung zurueckgesetzt !

        }
        System.out.println("\n Druecke eine Taste um in Trainer Menue zu gelangen");
        ScannerHelfer.nextLine();

        trainerAnzeigen();

    }

    /**
     * Menu spezialisierung kaufen.
     */
    public void menuSpezialisierungKaufen() {

        // Erstmal den Charakter zum bearbeiten auswaehlen
        SpielerCharakter derCharakter = trainerCharakterAuswahl();

        // Sollte er keine Haben mit einer temp Variable anziegen !!!!
        boolean gueltigeEingabe = false;
        int nutzerAuswahl;
        boolean[] auswahlMoeglichkeiten = new boolean[10];
        boolean hatSpezialisierung = false;
        int alleBedingungenErfuellt = 0;
        while (!gueltigeEingabe) {
            //Das Boolean Arrey mit false fuellen
            for (int i = 0; i < auswahlMoeglichkeiten.length; i++) {
                auswahlMoeglichkeiten[i] = false;
            }
            KonsolenAssistent.clear();
            System.out.println("Der Charakter hat folgende Werte");
            System.out.println("Name : " + Farbauswahl.GREEN + derCharakter.getName() + Farbauswahl.RESET + " hat die Klasse " + derCharakter.getKlasse().getBezeichnung());
            if (hatSpezialisierung) {
                System.out.println("Spezialisierung = " + derCharakter.getKlasse().getClass().getSimpleName());
            }

            System.out.println("Der Wechsel zu einer anderen Klasse kostet " + basisKostenSpezialisierung + " Gold . Sie haben derzeit " + trainerController.getPartyController().getPartyGold());
            System.out.println("Und ist erst ab Spieler Level 10 moeglich. Dein SpielerLevel ist " + derCharakter.getLevel());
            System.out.println("");

            // Kann er ueberhaupt die Spezialisierung nutzen ?
            // Wenn int alleBedingungen auf 3 steht passt es
            if (trainerController.getPartyController().getPartyGold() >= basisKostenSpezialisierung) {
                alleBedingungenErfuellt = alleBedingungenErfuellt + 1;
            }
            ;
            if (derCharakter.getLevel() >= 10) {
                alleBedingungenErfuellt = alleBedingungenErfuellt + 1;
            }

            // Einzeln einlesen der einzelenen Klassen

            if (derCharakter.getKlasse().getBezeichnung().equals("Tank") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Rabauke")) {
                System.out.println("1. TANK --> Rabauke");
                auswahlMoeglichkeiten[1] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Tank") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Paladin")) {
                System.out.println("2. TANK --> Paladin");
                auswahlMoeglichkeiten[2] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Magischer DD") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Feuermagier")) {
                System.out.println("3. MDD --> Feuermagier");
                auswahlMoeglichkeiten[3] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Magischer DD") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Eismagier")) {
                System.out.println("4. MDD --> Eismagier");
                auswahlMoeglichkeiten[4] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Physischer DD") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Berserker")) {
                System.out.println("5. PDD --> Berserker");
                auswahlMoeglichkeiten[5] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Physischer DD") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Schurke")) {
                System.out.println("6. PDD --> Schurke");
                auswahlMoeglichkeiten[6] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Healer") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Priester")) {
                System.out.println("7. HLR --> Priester");
                auswahlMoeglichkeiten[7] = true;
            }
            if (derCharakter.getKlasse().getBezeichnung().equals("Healer") && !derCharakter.getKlasse().getClass().getSimpleName().equals("Sanmaus")) {
                System.out.println("8. HLR --> Sanmaus");
                auswahlMoeglichkeiten[8] = true;
            }
            System.out.println("");
            System.out.println("0. Abbruch");
            // Scanner und Auswertung
            nutzerAuswahl = ScannerHelfer.nextInt();

            switch (nutzerAuswahl) {
                case 1:
                    //TANK -> Rabauke
                    if (auswahlMoeglichkeiten[1] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Rabauke(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 2:
                    // TANK -> Paladin
                    if (auswahlMoeglichkeiten[2] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Paladin(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 3:
                    // MDD -> FeuerMagier
                    if (auswahlMoeglichkeiten[3] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Feuermagier(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 4:
                    //MDD -> EisMagier
                    if (auswahlMoeglichkeiten[4] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Eismagier(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 5:
                    // PDD -> Berserker
                    if (auswahlMoeglichkeiten[5] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Berserker(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 6:
                    // PDD -> Schurke
                    if (auswahlMoeglichkeiten[6] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Schurke(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 7:
                    // HLR -> Priester
                    if (auswahlMoeglichkeiten[7] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Priester(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 8:
                    // HLR -> Sanmaus
                    if (auswahlMoeglichkeiten[8] == true) {

                        if (alleBedingungenErfuellt >= 2) {
                            System.out.println("Wechsel vollzogen !");
                            derCharakter.setKlasse(new Sanmaus(derCharakter));
                            trainerController.getPartyController().goldAbziehen(basisKostenSpezialisierung);
                            //@TODO: Mit Markus diesen Reset zuruecksetzen bzw Konstruktor bedienen !
                        }
                    }
                    break;
                case 0:
                    trainerAnzeigen();
                    break;
                default:
                    System.out.println("Ungueltige Eingabe");
                    break;

            }
            System.out.println("Die Spezialisierung ist " + derCharakter.getKlasse().getClass().getSimpleName());
            System.out.println("Du hast jetzt " + trainerController.getPartyController().getPartyGold() + " Gold !");
            trainerAnzeigen();
        }
    }

    /**
     * @author 11777914 OLt Oliver Ebert
     * @since 17.11.2023
     * {@see Faehigkeit }: Hier liegen die Attribute der Faehigkeit. Auch kann eine Faehigkeit hier aufgewertet werden.
     * Oefnnet das Menue, um Faehigkeiten fuer einen gewaehlten Charakter aufzuwerten
     */
    private void menuFaehigkeitenAufwerten() {
        System.out.println("\nFuer welchen Charakter sollen Faehigkeiten aufgwertet werden?: (Bitte waehlen)");
        trainerCharakterAuswahl();

        if (currentCharakter != null) {
            FahigkeitenMenu.menuFaehigkeitenVorauswahl(currentCharakter);
        } else {
            System.err.println("Trainer.menuFaehigkeitenKaufen: Keinen Charakter ausgewaehlt");
        }
    }

    private void menuAttributeAnpassen() {

        // Faehigkeiten
        // 1 Max Gesundheit verbessern
        // 2 Max Mana Punkte verbessern
        // 3 physische Attacke verbessern
        // 4 magische Attacke verbessern
        // 5 genauigkeit verbessern
        // 6 verteidigung verbessern
        // 7 magische Verteidigung verbessern
        // 8 resistenz verbessern
        // 9 beweglichkeit verbessern

        // Variablen
        int nutzerEingabe = 0;
        int auswahlAttribut = 0;


        // Erst die Charakterauswahl
        SpielerCharakter derCharakter = trainerCharakterAuswahl();
        KonsolenAssistent.clear();
        System.out.printf(Farbauswahl.RED + "%-50s %-40s %2s %s %n", "", Farbauswahl.PURPLE + "Name", ":", derCharakter.getName());
        System.out.printf(Farbauswahl.RED + "%-50s %-40s %2s %s %n", "", Farbauswahl.PURPLE + "Attributspunkte", ":", derCharakter.getOffeneAttributpunkte());
        System.out.printf(Farbauswahl.YELLOW + "%-50s %-40s %2s %s %n", "1. Maximale Gesundheit veraendern", Farbauswahl.BLUE + "Max Gesundheitspunkte", ":", derCharakter.getMaxGesundheitsPunkte());
        System.out.printf(Farbauswahl.GREEN + "%-50s %-40s %2s %s %n", "2. Maximale Mana Punkte veraendern", Farbauswahl.CYAN + "ManaPunkte", ":", derCharakter.getMaxManaPunkte());
        System.out.printf(Farbauswahl.YELLOW + "%-50s %-40s %2s %s %n", "3. Physische Attacke veraendern", Farbauswahl.BLUE + "Physische Attacke", ":", derCharakter.getPhysischeAttacke());
        System.out.printf(Farbauswahl.GREEN + "%-50s %-40s %2s %s %n", "4. Magische Attacke veraendern", Farbauswahl.CYAN + "Magische Attacke", ":", derCharakter.getMagischeAttacke());
        System.out.printf(Farbauswahl.YELLOW + "%-50s %-40s %2s %s %n", "5. Genauigkeit veraendern", Farbauswahl.BLUE + "Genauigkeit", ":", derCharakter.getGenauigkeit());
        System.out.printf(Farbauswahl.GREEN + "%-50s %-40s %2s %s %n", "6. Verteidigung veraendern", Farbauswahl.CYAN + "Verteidigung", ":", derCharakter.getVerteidigung());
        System.out.printf(Farbauswahl.YELLOW + "%-50s %-40s %2s %s %n", "7. Magische Verteidigung veraendern", Farbauswahl.BLUE + "Magische Verteidigung", ":", derCharakter.getMagischeVerteidigung());
        System.out.printf(Farbauswahl.GREEN + "%-50s %-40s %2s %s %n", "8. Resistenz veraendern", Farbauswahl.CYAN + "Resistenz", ":", derCharakter.getResistenz());
        System.out.printf(Farbauswahl.YELLOW + "%-50s %-40s %2s %s %n", "9. Beweglichkeit veraendern", Farbauswahl.BLUE + "Beweglichkeit", ":", derCharakter.getBeweglichkeit());
        System.out.println("");
        System.out.println("0. Abbruch");
        System.out.println("");
        System.out.println("Das erhoehen eines Punktes kostest " + basisKostensAttributeAnpassen + " Attributspunkte.");
        System.out.println("Beim verkaufen eines Punktes bekommt man " + (basisKostensAttributeAnpassen % 2) + " Attributspunkte zurueck.");
        System.out.println("Bitte treffen Sie Ihre Auswahl");

        // Scanner zum Einlesen der NutzerAuswahl
        auswahlAttribut = ScannerHelfer.nextInt();

        switch (auswahlAttribut) {
            case 1:
                // Max Gesundheit
                aendereMaxGesundheit(derCharakter);
                break;
            case 2:
                // Max Mana
                aendereMaxMana(derCharakter);
                break;
            case 3:
                // Physische Attacke
                aenderePhysischeAttacke(derCharakter);
                break;
            case 4:
                // Magische Attacke
                aendereMagischeAttacke(derCharakter);
                break;
            case 5:
                // Genauigkeit
                aendereGenauigkeit(derCharakter);
                break;
            case 6:
                //Verteidigung
                aendereVerteidigung(derCharakter);
                break;
            case 7:
                // Magische Verteidigung
                aendereMagischeVerteidigung(derCharakter);
                break;
            case 8:
                // Resistenz
                aendereResistenz(derCharakter);
                break;
            case 9:
                // Beweglichkeit
                aendereBeweglichkeit(derCharakter);
                break;
            case 0:
                // Abbruch
                trainerAnzeigen();
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;

        }

        trainerAnzeigen();
    }

    private static void aendereMaxGesundheit(SpielerCharakter einCharakter) {
        // Veraendern der MaxGesundheit
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Max Gesundheit veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getMaxGesundheitsPunkte());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMaxGesundheitsPunkte(derCharakter.getMaxGesundheitsPunkte() + nutzerEingabe);
                    derCharakter.setGesundheitsPunkte(derCharakter.getGesundheitsPunkte() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Max Gesundheitspunkte sind nun auf " + derCharakter.getMaxGesundheitsPunkte() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getMaxGesundheitsPunkte()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getMaxGesundheitsPunkte() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getMaxGesundheitsPunkte()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMaxGesundheitsPunkte(derCharakter.getMaxGesundheitsPunkte() - nutzerEingabe);
                    derCharakter.setGesundheitsPunkte(derCharakter.getMaxGesundheitsPunkte());
                    System.out.println("");
                    System.out.println("Die Max Gesundheitspunkte sind nun auf " + derCharakter.getMaxGesundheitsPunkte() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;
        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereMaxMana(SpielerCharakter einCharakter) {
        // Veraendern der MaxMana
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Max Mana veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getMaxManaPunkte());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie !");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMaxManaPunkte(derCharakter.getMaxManaPunkte() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Max ManaPunkte sind nun auf " + derCharakter.getMaxManaPunkte() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getMaxManaPunkte()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getMaxManaPunkte() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                };
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getMaxManaPunkte()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMaxManaPunkte(derCharakter.getMaxManaPunkte() - nutzerEingabe);
                    derCharakter.setManaPunkte(derCharakter.getMaxManaPunkte());
                    System.out.println("");
                    System.out.println("Die Max ManaPunkte sind nun auf " + derCharakter.getMaxManaPunkte() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;
        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aenderePhysischeAttacke(SpielerCharakter einCharakter) {
        // Veraendern der Physische Attacke
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Physische Attacke veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getPhysischeAttacke());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setPhysischeAttacke(derCharakter.getPhysischeAttacke() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Pysische AttackePunkte sind nun auf " + derCharakter.getPhysischeAttacke() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getPhysischeAttacke()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getPhysischeAttacke() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getPhysischeAttacke()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setPhysischeAttacke(derCharakter.getPhysischeAttacke() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Physische Attacke Punkte sind nun auf " + derCharakter.getPhysischeAttacke() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereMagischeAttacke(SpielerCharakter einCharakter) {
        // Veraendern der Magische Attacke
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Magische Attacke veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getMagischeAttacke());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMagischeAttacke(derCharakter.getMagischeAttacke() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Magische Attacke Punkte sind nun auf " + derCharakter.getMagischeAttacke() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getMagischeAttacke()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getMagischeAttacke() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getMagischeAttacke()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMagischeAttacke(derCharakter.getMagischeAttacke() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Magische Attacke Punkte nun auf " + derCharakter.getMagischeAttacke() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;
        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereGenauigkeit(SpielerCharakter einCharakter) {
        // Veraendern der Genauigkeit
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Genauigkeit veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getGenauigkeit());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setGenauigkeit(derCharakter.getGenauigkeit() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Genauigkeit sind nun auf " + derCharakter.getGenauigkeit() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getGenauigkeit()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getGenauigkeit() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getGenauigkeit()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setGenauigkeit(derCharakter.getGenauigkeit() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die GenauigkeitsPunkte sind nun auf " + derCharakter.getGenauigkeit() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereVerteidigung(SpielerCharakter einCharakter) {
        // Veraendern der Verteidigung
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Verteidigung veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getVerteidigung());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setVerteidigung(derCharakter.getVerteidigung() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die VerteidigungsPunkte sind nun auf " + derCharakter.getVerteidigung() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getVerteidigung()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getVerteidigung() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getVerteidigung()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setVerteidigung(derCharakter.getVerteidigung() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die VerteidigungsPunkte sind nun auf " + derCharakter.getVerteidigung() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereMagischeVerteidigung(SpielerCharakter einCharakter) {
        // Veraendern der Magische Verteidigung
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Magische Verteidigung veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getMagischeVerteidigung());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMagischeVerteidigung(derCharakter.getMagischeVerteidigung() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Magische VerteidigungsPunkte sind nun auf " + derCharakter.getMagischeVerteidigung() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getMagischeVerteidigung()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getMagischeVerteidigung() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getMagischeVerteidigung()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setMagischeVerteidigung(derCharakter.getMagischeVerteidigung() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die Magische Verteidigung sind nun auf " + derCharakter.getMagischeVerteidigung() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereResistenz(SpielerCharakter einCharakter) {
        // Veraendern der Resistenz
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Resistenz veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getResistenz());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setResistenz(derCharakter.getResistenz() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die ResistenzPunkte sind nun auf " + derCharakter.getResistenz() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getResistenz()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getResistenz() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getResistenz()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setResistenz(derCharakter.getResistenz() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die ResistenzPunkte sind nun auf " + derCharakter.getResistenz() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

    private static void aendereBeweglichkeit(SpielerCharakter einCharakter) {
        // Veraendern der Beweglichkeit
        //Variablen
        SpielerCharakter derCharakter = einCharakter;
        int kaufenoderverkaufen = 0;
        int nutzerEingabe = 0;
        KonsolenAssistent.clear();
        System.out.println("Wollen Sie Punkte kaufen oder verkaufen ?");
        System.out.println("Beweglichkeit veraendern");
        System.out.println("Aktueller Wert " + derCharakter.getBeweglichkeit());
        System.out.println("Aktuelle AttributsPunkte " + derCharakter.getOffeneAttributpunkte());
        System.out.println("1. Kaufen       2. Verkaufen ");
        System.out.println("");
        System.out.println("Bitte waehlen Sie");
        kaufenoderverkaufen = ScannerHelfer.nextInt();
        switch (kaufenoderverkaufen) {
            case 1:
                // kaufen
                System.out.println("Wieviele Punkte wollen Sie kaufen");
                System.out.println("Sie koennten " + (derCharakter.getOffeneAttributpunkte()) + " Punkte kaufen");
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getOffeneAttributpunkte()) {
                    // Abziehen des Gegenwertes fuer die neuen Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() - nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setBeweglichkeit(derCharakter.getBeweglichkeit() + nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die BeweglichkeitsPunkte sind nun auf " + derCharakter.getBeweglichkeit() + " gestiegen");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            case 2:
                // verkaufen
                System.out.println("Wieviele Punkte wollen Sie verkaufen");
                if (derCharakter.getBeweglichkeit()-1 > 0){
                    System.out.println("Sie koennten " + (derCharakter.getBeweglichkeit() - 1) + " Punkte verkaufen");
                }else {
                    System.out.println("Sie koennten " + (0 + " Punkte verkaufen"));
                }
                nutzerEingabe = ScannerHelfer.nextInt();
                //pruefen ob die nutzerEingabe * basispreis > als offene AttributsPunkte
                if (nutzerEingabe < derCharakter.getBeweglichkeit()-1) {
                    // Rueckgabe der Attributspunkte
                    derCharakter.setOffeneAttributpunkte(derCharakter.getOffeneAttributpunkte() + nutzerEingabe);
                    // Hochleveln der gekauften Punkte zum Charakter
                    derCharakter.setBeweglichkeit(derCharakter.getBeweglichkeit() - nutzerEingabe);
                    System.out.println("");
                    System.out.println("Die BeweglichkeitsPunkte sind nun auf " + derCharakter.getBeweglichkeit() + " gesunken");
                    System.out.println("Die Anzahl der Attributspunkte ist nun " + derCharakter.getOffeneAttributpunkte());
                    System.out.println("");
                }
                break;
            default:
                System.out.println("Ungueltige Eingabe !");
                break;


        }
        System.out.println("Druecken Sie Return um zurueck zum Menue zu kommen");
        ScannerHelfer.nextLine();
    }

}
