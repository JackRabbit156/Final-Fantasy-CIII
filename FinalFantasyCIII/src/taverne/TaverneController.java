package taverne;

import java.util.ArrayList;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import charakter.model.klassen.soeldner.Kaempfer;
import charakter.model.klassen.soeldner.Magier;
import charakter.model.klassen.soeldner.Supporter;
import gamehub.GameHubController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import hilfsklassen.ZufallsZahlenGenerator;
import party.Party;
import party.PartyController;
import statistik.StatistikController;
import view.AnsichtsTyp;
import view.ViewController;

public class TaverneController {

    private static final String[] NAMEN = {"Finn", "Ivy", "Zane", "Luna", "Blaze", "Nova", "Kai", "Ember", "Aria", "Orion", "Orio", "Jade", "Axel", "Zaza", "Griffin", "Serena", "Titan", "Scarlett", "Asher", "Lyra", "Jasper", "Celeste", "Silas", "Elara", "Kian", "Phonix", "Dax", "Sable", "Ryder", "Hawk", "Dawn", "Hans", "Greta", "Klaus", "Ingrid", "Friedrich", "Heidi", "Otto", "Liesl", "Dieter", "Anneliese", "Wolfgang", "Ilse", "Ludwig", "Gerda", "Gunther", "Helga", "Heinrich", "Ursula", "Ernst", "Hilde"};

	private Taverne taverne;
	private PartyController partyController;
	private StatistikController statistikController;
	private int letzteGeneration;
	private GameHubController gameHubController;
    private SpielerCharakter[] soeldner;
    private ViewController viewController;


	public TaverneController(PartyController partyController, StatistikController statistikController,
			GameHubController gameHubController, ViewController viewController) {
		this.partyController = partyController;
		this.statistikController = statistikController;
		this.letzteGeneration = -4;
		this.gameHubController = gameHubController;
		this.viewController = viewController;
	}

	/**
	 * Dienst zum Anzeigen der Taverne, welche die Moeglichkeit bietet, sich
	 * auszuruhen, um Gesundheit und Mana der Party gegen Gold wiederherzustellen,
	 * sowie die Moeglichkeiten zum Einstellen und Entlassen von Soeldnern. Bei
	 * jedem Aufruf des Anzeigens werden neue Soeldner generiert, sofern min. drei
	 * Kaempfe durchgefuehrt worden sind.
	 *
	 * @author OF Ridder / OF Schroeder
	 * @since 21.11.2023
	 */
	public void taverneAnzeigen() {
	    TaverneView taverneView = new TaverneView(this);
	    viewController.ansichtHinzufuegen(taverneView);
	    viewController.anmelden(taverneView,null, AnsichtsTyp.MIT_OVERLAY);
//		Party party = partyController.getParty();
//		if (statistikController.getStatistik().getDurchgefuehrteKaempfe() - letzteGeneration >= 3) {
//			generiereSoeldner();
//		}
//		ArrayList<SpielerCharakter> nebenCharaktere = new ArrayList<>();
//		for (int i = 0; i < party.getNebenCharakter().length; i++) {
//			if (party.getNebenCharakter()[i] != null) {
//				nebenCharaktere.add(party.getNebenCharakter()[i]);
//			}
//		}
//
//        // Taverne-Auswahlmoeglichkeiten:
//        System.out.println(Farbauswahl.WHITE_BOLD + " _____                                       _____ \n" +
//                "( ___ )                                     ( ___ )\n" +
//                " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
//                " |   |  _____                                |   | \n" +
//                " |   | |_   _|_ ___   _____ _ __ _ __   ___  |   | \n" +
//                " |   |   | |/ _` \\ \\ / / _ \\ '__| '_ \\ / _ \\ |   | \n" +
//                " |   |   | | (_| |\\ V /  __/ |  | | | |  __/ |   | \n" +
//                " |   |   |_|\\__,_| \\_/ \\___|_|  |_| |_|\\___| |   | \n" +
//                " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
//                "(_____)                                     (_____)" + Farbauswahl.RESET);
//        System.out.println(Farbauswahl.YELLOW_BOLD + "Dein aktuelles Gold: " + partyController.getPartyGold() + Farbauswahl.RESET);
//        System.out.println(Farbauswahl.WHITE_BOLD + "Bitte auswaehlen:" + Farbauswahl.RESET);
//        System.out.println(Farbauswahl.CYAN + "1 = Ausruhen, fuer " + (int) Math.floor(partyController.getPartyLevel()) + " Gold die Gesundheits- und Manapunkte der gesamten Party wieder komplett auffuellen" + Farbauswahl.RESET);
//        if (nebenCharaktere.size() < 3 && !istKeinSoeldnerVorhanden()) {
//            System.out.println(Farbauswahl.CYAN + "2 = Soeldner anheuern" + Farbauswahl.RESET);
//        }
//        if (nebenCharaktere.size() > 0) {
//            System.out.println(Farbauswahl.CYAN + "3 = Soeldner entlassen" + Farbauswahl.RESET);
//        }
//        System.out.println(Farbauswahl.CYAN + "4 = Zurueck ins GameHub" + Farbauswahl.RESET);
//        int eingabe = ScannerHelfer.nextInt();
//        switch (eingabe) {
//            case 1:
//                KonsolenAssistent.clear();
//                ausruhen();
//                break;
//            case 2:
//                KonsolenAssistent.clear();
//                if (nebenCharaktere.size() < 3 && !istKeinSoeldnerVorhanden()) {
//                    zuEinstellendeMitgliederAnzeigen();
//                } else {
//                    if (nebenCharaktere.size() >= 3) {
//                        System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe - Party ist bereits voll!" + Farbauswahl.RESET);
//                        taverneAnzeigen();
//                    }
//                    if (nebenCharaktere.size() == 3 && istKeinSoeldnerVorhanden()) {
//                        System.out.println(Farbauswahl.RED_BACKGROUND + "Es stehen keine Soeldner zum einstellen zur Verfuegung + deine Party ist bereits voll!" + Farbauswahl.RESET);
//                        taverneAnzeigen();
//                    }
//                    if (nebenCharaktere.size() == 3 && letzteGeneration < 3) {
//                        System.out.println(Farbauswahl.RED_BACKGROUND + "Deine Party ist voll + noch keine drei Kaempfe fuer neue Soeldner hinter dir" + Farbauswahl.RESET);
//                    } else {
//                        System.out.println(Farbauswahl.RED_BACKGROUND + "Du hast noch keine drei Kaempfe fuer neue Soeldner hinter dir!" + Farbauswahl.RESET);
//                        taverneAnzeigen();
//                    }
//                }
//                break;
//            case 3:
//                KonsolenAssistent.clear();
//                if (nebenCharaktere.size() > 0) {
//                    zuEntlassendeMitgliederAnzeigen();
//                } else {
//                    System.out.println(Farbauswahl.RED_BACKGROUND + "Fehlerhafte Eingabe - Deine Party hat keine Mitglieder!" + Farbauswahl.RESET);
//                    taverneAnzeigen();
//                }
//                break;
//            case 4:
//                KonsolenAssistent.clear();
////                    gameHubController.hubAnzeigen();
//                break;
////            case 5:
////                KonsolenAssistent.clear();
////                System.out.println("Cheat;)");
////                party.getHauptCharakter().setLevel(100);
////                party.setGold(5000);
////                taverneAnzeigen();
//            default:
//                KonsolenAssistent.clear();
//                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
//                taverneAnzeigen();
//                break;
//        }
    }

    private boolean istKeinSoeldnerVorhanden() {
        for (int i = 0; i < soeldner.length; i++) {
            if (soeldner[i] != null) {
                return false;
            }
        }
        return true;
    }

    private void generiereSoeldner() { // Soeldner werden wie Gegner zufällig generiert (Klasse, Attribute, Ausrüstung)
        soeldner = new SpielerCharakter[3];
        soeldner[0] = new Magier("Voldemort", "Magischer DD", "Klassenbester aus Hogwarts!", (int) Math.floor(partyController.getPartyLevel()));
        soeldner[1] = new Kaempfer("Kloppi", "Physischer DD", "Hauptschuleeeee - aufs Maul?", (int) Math.floor(partyController.getPartyLevel()));
        soeldner[2] = new Supporter("DerSupporter", "Tank", "Alles fuers Team!", (int) Math.floor(partyController.getPartyLevel()));
        /*
        Nach jeweils X Kaempfen (ein Kampf zaehlt, egal ob er gewonnen oder verloren wurde) werden die rekrutierbaren Soeldner in voller Anzahl neu generiert. (Bereits in die Party rekrutierte Soeldner bleiben bestehen).
         */

        for (int i = 0; i < 3; i++) {
            generierteSoeldner[i] = generiereEinenZufaelligenSoeldner((int)partyController.getPartyLevel());
        }
        letzteGeneration = statistikController.getStatistik().getDurchgefuehrteKaempfe();
    }

    public static SpielerCharakter generiereEinenZufaelligenSoeldner(int level){
	    String zufaelligerName = NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(NAMEN.length-1)];
	    String zufaelligeKlasse = Klasse.KLASSEN_NAMEN[ZufallsZahlenGenerator.zufallsZahlIntAb0(Klasse.KLASSEN_NAMEN.length-1)];
	    //TODO: Geschichte generieren
        return new SpielerCharakter(zufaelligerName, zufaelligeKlasse, "..eine tolle Geschichte",level, true);
    }

    private void ausruhen() {
        Party party = partyController.getParty();
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
            ArrayList<SpielerCharakter> nebenCharaktere = new ArrayList<>();
            for (int i = 0; i < party.getNebenCharakter().length; i++) {
                if (party.getNebenCharakter()[i] != null) {
                    nebenCharaktere.add(party.getNebenCharakter()[i]);
                }
            }
                party.getHauptCharakter().setGesundheitsPunkte(party.getHauptCharakter().getMaxGesundheitsPunkte());
                party.getHauptCharakter().setManaPunkte(party.getHauptCharakter().getMaxManaPunkte());
            for (SpielerCharakter spielerCharakter : nebenCharaktere) {
                    spielerCharakter.setGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte());
                    spielerCharakter.setManaPunkte(spielerCharakter.getMaxManaPunkte());
            }
            System.out.println("" +
                    "            (                 ,&&&.\n" +
                    "             )                .,.&&\n" +
                    "            (  (              \\=__/\n" +
                    "                )             ,'-'.\n" +
                    "          (    (  ,,      _.__|/ /|\n" +
                    "           ) /\\ -((------((_|___/ |\n" +
                    "         (  // | (`'      ((  `'--|\n" +
                    "       _ -.;_/ \\\\--._      \\\\ \\-._/.\n" +
                    "      (_;-// | \\ \\-'.\\    <_,\\_\\`--'|\n" +
                    "      ( `.__ _  ___,')      <_,-'__,'\n" +
                    "       `'(_ )_)(_)_)'");
            System.out.println();
            System.out.println();
            System.out.println(
                    "      _____|~~\\_____      _____________\n" +
                            "  _-~               \\    |    \\\n" +
                            "  _-    | )     \\    |__/   \\   \\\n" +
                            "  _-         )   |   |  |     \\  \\\n" +
                            "  _-    | )     /    |--|      |  |\n" +
                            " __-_______________ /__/_______|  |_________\n" +
                            "(                |----         |  |\n" +
                            " `---------------'--\\\\\\\\      .`--'\n" +
                            "                              `||||");
            System.out.println(Farbauswahl.GREEN_BACKGROUND + "Gesundheit und Mana der Partymitglieder wieder aufgefuellt!" + Farbauswahl.RESET);
            System.out.println("Zum zurueckkehren in die Taverne beliebige Taste druecken!");
            ScannerHelfer.nextLine();
            KonsolenAssistent.clear();
        } else {
            System.out.println(Farbauswahl.RED_BACKGROUND + "Nicht genug Gold!" + Farbauswahl.RESET);
        }
        taverneAnzeigen();
    }

	private void zuEinstellendeMitgliederAnzeigen() { // Im Hub steht eine feste Anzahl an Soeldnern zur Verfuegung die
														// in einer Uebersicht eingesehen werden koennen (Liste,
														// Durchschaltmenue, etc..)

// Simple Ausgabe, zum fall-back dringelassen
//        for (int i = 0; i < taverne.getSoeldner().length; i++) {
//            if (taverne.getSoeldner()[i] != null) {
//                System.out.println(Farbauswahl.GREEN_BOLD + "        " + (i + 1) + ".Auswahlmoeglichkeit" + Farbauswahl.RESET);
//                System.out.println(taverne.getSoeldner()[i].getGrafischeDarstellung()); // Grafische Darstellung
//                System.out.println(Farbauswahl.YELLOW + "Name: " + taverne.getSoeldner()[i].getName() + Farbauswahl.RESET);
//                System.out.println(Farbauswahl.YELLOW + "Level: " + taverne.getSoeldner()[i].getLevel() + Farbauswahl.RESET);
//                System.out.println(Farbauswahl.YELLOW + "Klasse: " + taverne.getSoeldner()[i].getKlasse().getBezeichnung() + Farbauswahl.RESET);
//                System.out.println(Farbauswahl.YELLOW + "Geschichte: " + taverne.getSoeldner()[i].getGeschichte() + Farbauswahl.RESET);
//                System.out.println(Farbauswahl.YELLOW + "Kosten Goldmuenzen: " + (int) Math.floor(partyController.getPartyLevel()) + Farbauswahl.RESET);
//                System.out.println(Farbauswahl.RED + "----------------------------------------" + Farbauswahl.RESET);
//            }
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("4: zurueck");
//        int eingabe = ScannerHelfer.nextInt();
//        switch (eingabe) {
//            case 1:
//            case 2:
//            case 3:
//                if (taverne.getSoeldner()[eingabe-1] != null ) {
//                    teammitgliedEinstellen(eingabe-1);
//                } else {
//                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
//                    taverneAnzeigen();
//                }
//                break;
//            case 4:
//                taverneAnzeigen();
//                break;
//            default:
//                KonsolenAssistent.clear();
//                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
//                taverneAnzeigen();
//                break;
//        }

// das erste nicht-null Element finden
		int i = 0;
		while (i < soeldner.length && soeldner[i] == null) {
			i++;
		}
		if (i < soeldner.length) {
			while (true) {
				if (soeldner[i] != null) {
					System.out.println(soeldner[i].getGrafischeDarstellung());
					System.out.println(
							Farbauswahl.GREEN_BOLD + "Name: " + soeldner[i].getName() + Farbauswahl.RESET);
					System.out.println(
							Farbauswahl.YELLOW + "Level: " + soeldner[i].getLevel() + Farbauswahl.RESET);
					System.out.println(Farbauswahl.YELLOW + "Klasse: "
							+ soeldner[i].getKlasse().getBezeichnung() + Farbauswahl.RESET);
					System.out.println(Farbauswahl.YELLOW + "Geschichte: " + soeldner[i].getGeschichte()
							+ Farbauswahl.RESET);
					System.out.println(Farbauswahl.YELLOW + "Kosten Goldmuenzen: "
							+ (int) Math.floor(partyController.getPartyLevel()) + Farbauswahl.RESET);
					System.out
							.println(Farbauswahl.RED + "----------------------------------------" + Farbauswahl.RESET);
					System.out.println(Farbauswahl.YELLOW_BOLD + "Dein aktuelles Gold: "
							+ partyController.getPartyGold() + Farbauswahl.RESET);
					System.out.println("'W' fuer den naechsten Soeldner, 'E' zum anheuern, oder 'Q' zum Beenden: ");
					String input = ScannerHelfer.nextLine();
					if ("Q".equalsIgnoreCase(input)) {
							taverneAnzeigen();
						break;
					}
					else if ("W".equalsIgnoreCase(input)) {
						i = (i + 1) % soeldner.length; // sicherstellen, dass der Index im Bereich des
																	// Arrays bleibt
						KonsolenAssistent.clear();
					}
					else if ("E".equalsIgnoreCase(input)) {
						KonsolenAssistent.clear();
						teammitgliedEinstellen(i);
						i++; // fuer den naechsten Durchlauf
					}
					else {
						KonsolenAssistent.clear();
						System.out.println(Farbauswahl.RED_BACKGROUND
								+ "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
						zuEinstellendeMitgliederAnzeigen();
					}
				}
				else {
					i = (i + 1) % soeldner.length; // wenn das aktuelle Element null ist, gehe zum
																// naechsten Index
				}
			}
		}
		else {
			System.out.println("Es gibt keine verfuegbaren Soeldner.");
		}
	}

    private void zuEntlassendeMitgliederAnzeigen() {
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCharakter()[i] != null) {
                System.out.println(Farbauswahl.YELLOW + (i + 1) + ": " + partyController.getParty().getNebenCharakter()[i].getName() + Farbauswahl.RESET);
                System.out.println(Farbauswahl.RED + "----------------------------------------" + Farbauswahl.RESET);
            }
        }
        System.out.println("4: zurueck");
        int eingabe = ScannerHelfer.nextInt();
        switch (eingabe) {
            case 1:
            case 2:
            case 3:
                SpielerCharakter[] soeldner = partyController.getParty().getNebenCharakter();
                if (soeldner[eingabe - 1] != null) {
                    teammitgliedEntlassen(soeldner[eingabe - 1]);
                } else {
                    System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                    taverneAnzeigen();
                }
                break;
            case 4:
                taverneAnzeigen();
                break;
            default:
                KonsolenAssistent.clear();
                System.out.println(Farbauswahl.RED_BACKGROUND + "Falsche Eingabe, bitte eine gueltige Auswahl treffen!" + Farbauswahl.RESET);
                taverneAnzeigen();
                break;
        }
    }

    private void teammitgliedEinstellen(int index) { // Soeldner koennen im Hub rekrutiert werden
        if (partyController.getPartyGold() >= (int) Math.floor(partyController.getPartyLevel())) {
            partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel()));
            partyController.teammitgliedHinzufuegen(soeldner[index]);
            System.out.println(Farbauswahl.GREEN_BACKGROUND + soeldner[index].getName() + " angeheuert!" + Farbauswahl.RESET);
            soeldner[index] = null; // Beim rekrutieren eines Soeldners wird dieser aus der Uebersicht entfernt und kein neuer Soeldner erzeugt. Die Anzahl verbleibender Soeldner bleibt vorerst reduziert

		}
		else {
			System.out.println("Deine Armut kotzt mich an!");
		}
			taverneAnzeigen();
	}

	private void teammitgliedEntlassen(SpielerCharakter soeldner) {
		/*
		 * Beim “Entlassen” von Soeldnern geht die gewechselte Ausruestung zurueck ins
		 * Spielerinventar Die Ruestung die der Soeldner initial beim rekrutieren traegt
		 * geht verloren, bekommt der Soeldner neue Ausruestungsgegenstaende vom Spieler
		 * zugewiesen gehen diese Ausruestungsteile nicht verloren
		 */
		partyController.teammitgliedEntfernen(soeldner);
		KonsolenAssistent.clear();
		System.out.println(Farbauswahl.RED_BACKGROUND + soeldner.getName() + " entlassen!" + Farbauswahl.RESET);
			taverneAnzeigen();
	}

}
