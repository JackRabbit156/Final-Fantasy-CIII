package kampf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;
import party.PartyStatusController;
import statistik.GameOver;
import statistik.StatistikController;

public class KampfController {
	private FeindController feindController;
	private PartyController partyController;
	private PartyStatusController partyStatusController;
	private StatistikController statistikController;
	private GameController gameController;
	private GameHubController gameHubController;
	private Random random = new Random();

	public KampfController(PartyController partyController, StatistikController statistikController,
			GameController gameController, GameHubController gameHubController) {
		this.feindController = new FeindController();
		this.partyController = partyController;
		this.statistikController = statistikController;
		this.gameController = gameController;
		this.gameHubController = gameHubController;

	}

	/**
	 * Startpunkt für kaempfe
	 *
	 * @since 19.11.2023
	 * @author Maass
	 */
	public void kampfBeginnen() {
		ArrayList<Charakter> zugReihenfolge = new ArrayList<>();
		zugReihenfolge.add(partyController.getParty().getHauptCharakter());
		for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
			zugReihenfolge.add(spielerCharakter);
		}
		Feind[] feinde = feindController.gegnerGenerieren((int) partyController.getPartyLevel());
		for (Feind feind : feinde) {
			zugReihenfolge.add(feind);
		}
		zugReihenfolge.sort(Comparator.comparingInt(Charakter::getBeweglichkeit));
		kampfStart(zugReihenfolge);

	}

	/**
	 * Kampf wird gestartet und innerhalb der Funktion komplett ausgefuehrt.
	 * Benoetigt die initiale Zugreihenfolge der SpielerCharaktere und Feinde.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private void kampfStart(ArrayList<Charakter> initialeZugreihenfolge) {
		int runde = 1;
		boolean istKampfVorbei = false;
		boolean istKampfVerloren = false;
		ArrayList<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
		ArrayList<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
		ArrayList<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
		ArrayList<Feind> feindeDieNochLeben = new ArrayList<>();
		ArrayList<Feind> feindeDieNochActionHaben = new ArrayList<>();
		ArrayList<Charakter> blockendeCharaktere = new ArrayList<>();
		ArrayList<Charakter> selbstBuffCharaktere = new ArrayList<>();
		Charakter aktuellerCharakter;

		// Statuswerte des Hauptcharakters vor Kampfbeginn
		SpielerCharakter hauptCharakterVorKampfbeginn = partyController.getParty().getHauptCharakter().clone();

		// Statuswerte aller Nebencharaktere vor Kampfbeginn
		ArrayList<SpielerCharakter> nebenCharaktereVorKampfbeginn = new ArrayList<>();
		for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
			nebenCharaktereVorKampfbeginn.add(nebenCharakter.clone());
		}

		// freundeDieNochLeben, feindeDieNochLeben, etc. wird alles befuellt
		for (int counter = 0, len = initialeZugreihenfolge.size(); counter < len; counter++) {
			if (initialeZugreihenfolge.get(counter) instanceof SpielerCharakter) {
				freundeDieNochLeben.add((SpielerCharakter) initialeZugreihenfolge.get(counter));
			}
			else if (initialeZugreihenfolge.get(counter) instanceof Feind) {
				feindeDieNochLeben.add((Feind) initialeZugreihenfolge.get(counter));
			}
		}
		for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
			freundeDieNochActionHaben.add(freundeDieNochLeben.get(counter));
		}
		for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
			feindeDieNochActionHaben.add(feindeDieNochLeben.get(counter));
		}

		// Der gesamte Kampf befindet sich innerhalb der auesseren while-Schleife
		while (!istKampfVorbei) {

			// Eine Runde ist vorbei wenn jeder lebende Charakter einen Zug ausgefuehrt hat
			while (!freundeDieNochActionHaben.isEmpty() && !feindeDieNochActionHaben.isEmpty()) {

				// Eine einzelne Iterration der inneren while-Schleife ist der Zug eines
				// einzelnen Charakters (SpielerCharakter ODER Feind, abhaengig von
				// Zugreihenfolge, also Beweglichkeitsattribut)
				aktuellerCharakter = naechstenCharakterBestimmen(freundeDieNochActionHaben, feindeDieNochActionHaben);

				// Wenn aktueller Charakter als letzte Action geblockt hat, wird er jetzt, wo er
				// wieder dran ist, zuerst von der 'blocken-Liste' runter genommen und die
				// Statuswerte werden wieder normalisiert.
				if (blockendeCharaktere.contains(aktuellerCharakter)) {
					aktuellerCharakter.setVerteidigung(
							aktuellerCharakter.getVerteidigung() - aktuellerCharakter.getPhysischeAttacke());
					aktuellerCharakter.setMagischeVerteidigung(
							aktuellerCharakter.getMagischeVerteidigung() - aktuellerCharakter.getMagischeAttacke());
					blockendeCharaktere.remove(aktuellerCharakter);
					System.out.println(aktuellerCharakter.getName() + " blockt jetzt nicht mehr.");
				}

				// SpielerCharakter ist dran und hat die eigene Wahl eine Action auszuwaehlen
				if (aktuellerCharakter instanceof SpielerCharakter) {
					boolean hatActionBeendet = false;
					while (!hatActionBeendet) {
						aktionWaehlen(aktuellerCharakter, freundeDieNochLeben, feindeDieNochLeben, blockendeCharaktere);
						freundeDieNochActionHaben.remove(aktuellerCharakter);
					}
				}

				// Feind ist dran und Gegnerlogik uebernimmt die Entscheidungen
				else if (aktuellerCharakter instanceof Feind) {
					switch (aktuellerCharakter.getKlasse().getBezeichnung()) {
					case "Tank":
						// 65% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
						// SpielerCharaktere-Team)
						if (random.nextDouble() < 0.65) {
							angreifen(aktuellerCharakter, freundeDieNochLeben, feindeDieNochLeben);
						}
						// 35% Chance, dass der Tank blockt
						else {
							blocken(aktuellerCharakter);
						}
						break;
					// Alle anderen Klassen haben die gleichen Wahrscheinlichkeiten zu blocken (10%)
					// oder eine Faehigkeit zu benutzen (90%)
					case "Healer":
					case "Magischer DD":
					case "Physischer DD":
						if (random.nextDouble() < 0.9) {
							angreifen(aktuellerCharakter, freundeDieNochLeben, feindeDieNochLeben);
						}
						else {
							blocken(aktuellerCharakter);
						}
						break;
					default:
						System.out.println("FEHLER: Gegnerklasse nicht gefunden - Action wird uebersprungen");
					}
					feindeDieNochActionHaben.remove(aktuellerCharakter);
				}
				entferneToteCharaktereNachAction(freundeDieNochLeben, freundeDieNochActionHaben, feindeDieNochLeben,
						feindeDieNochActionHaben, freundeDieGestorbenSind);
			}

			// Runde vorbei. Alle noch lebenden SpielerCharaktere und Feinde regenerieren HP
			// und MP
			for (SpielerCharakter freund : freundeDieNochLeben) {
				freund.setGesundheitsPunkte(freund.getGesundheitsPunkte() + freund.getGesundheitsRegeneration());
				freund.setManaPunkte(freund.getManaPunkte() + freund.getManaRegeneration());
				if (freund.getGesundheitsPunkte() > freund.getMaxGesundheitsPunkte()) {
					freund.setGesundheitsPunkte(freund.getMaxGesundheitsPunkte());
				}
				if (freund.getManaPunkte() > freund.getMaxManaPunkte()) {
					freund.setManaPunkte(freund.getMaxManaPunkte());
				}
			}
			for (Feind feind : feindeDieNochLeben) {
				feind.setGesundheitsPunkte(feind.getGesundheitsPunkte() + feind.getGesundheitsRegeneration());
				feind.setManaPunkte(feind.getManaPunkte() + feind.getManaRegeneration());
				if (feind.getGesundheitsPunkte() > feind.getMaxGesundheitsPunkte()) {
					feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
				}
				if (feind.getManaPunkte() > feind.getMaxManaPunkte()) {
					feind.setManaPunkte(feind.getMaxManaPunkte());
				}
			}
			runde++;
		}
		System.out.println("Kampf nach " + runde + "Runden vorbei! Hier ist die Kampfauswertung: ");

		// Vor Kampfauswertung muessen alle Statuswerte (ausser aktuelle HP) wieder auf
		// ihren Wert von vor Kampfbeginn gesetzt werden.
		boolean hautpcharakterLebtNoch = false;

		// Hauptcharakter Statuswerte werden zurueckgesetzt (inklusive Leben)
		partyController.getParty().setHauptCharakter(hauptCharakterVorKampfbeginn);

		// Nebencharakter Statuspunkte werden zurueckgesetzt (inklusive Leben)
		SpielerCharakter[] partyUeberschreibung = new SpielerCharakter[3];
		int counter = 0;
		for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
			partyUeberschreibung[counter] = spielerCharakter;
			counter++;
		}

		// Es gibt SpielerCharaktere die noch Leben
		if (!freundeDieNochLeben.isEmpty()) {
			for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
				// Wenn die Geschichte des Charakters zeigt, dass er der Hauptcharakter ist
				// werden die HP des Hauptcharakters auf die von diesem Spielercharakter
				// gesetzt
				if (spielerCharakter.getGeschichte()
						.equals(partyController.getParty().getHauptCharakter().getGeschichte())) {
					partyController.getParty().getHauptCharakter()
							.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte());

					// Das bedeutet, dass der hauptcharakter noch lebt
					hautpcharakterLebtNoch = true;
				}
			}

			// Wenn der Hauptcharakter nicht mehr lebt werden seine HP auf 0 gesetzt
			if (!hautpcharakterLebtNoch) {
				partyController.getParty().getHauptCharakter().setGesundheitsPunkte(0);
			}

			counter = 0;
			for (SpielerCharakter nebenCharakterVorKampfBeginn : nebenCharaktereVorKampfbeginn) {

				// Aus allen leben SpielerCharakteren wird geguckt, welcher Nebencharakter noch
				// lebt
				for (SpielerCharakter spielerCharakterDerNochLebt : freundeDieNochLeben) {

					// Wenn der Nebencharakter noch unter den Leben weilt, werden die HP des
					// entsprechenden Partymitgliedes aktualisiert
					if (spielerCharakterDerNochLebt.getName().equals(nebenCharakterVorKampfBeginn.getName())) {
						partyUeberschreibung[counter]
								.setGesundheitsPunkte(spielerCharakterDerNochLebt.getGesundheitsPunkte());
					}
				}

				// Wenn der Nebencharakter nicht mehr unter den Lebenden weilt, werden die HP
				// des entsprechenden Partymitgliedes auf 0 gesetzt.
				for (SpielerCharakter spielerCharakterDerTotIst : freundeDieGestorbenSind) {
					if (spielerCharakterDerTotIst.getName().equals(nebenCharakterVorKampfBeginn.getName())) {
						partyUeberschreibung[counter].setGesundheitsPunkte(0);
					}
				}
				counter++;
			}
		}

		// Alles Partymitglieder sind tot und der Kampf wurde verloren. Alle am Anfang
		// des Kampfes erstellten Koepien koennen mit einem HP Wert von 0 an die Party
		// zurueckgegeben werden
		else {
			SpielerCharakter hauptCharakterVerloren = hauptCharakterVorKampfbeginn.clone();
			SpielerCharakter[] nebenCharaktereVerloren = new SpielerCharakter[3];
			hauptCharakterVerloren.setGesundheitsPunkte(0);
			counter = 0;
			for (SpielerCharakter nebenCharakter : nebenCharaktereVorKampfbeginn) {
				nebenCharaktereVerloren[counter] = nebenCharakter;
				nebenCharaktereVerloren[counter].setGesundheitsPunkte(0);
				counter++;
			}
			partyController.getParty().setHauptCharakter(hauptCharakterVerloren);
			partyController.getParty().setNebenCharakter(nebenCharaktereVerloren);
		}

		// Aktualisierter Nebencharakter-Array wird der Party uebergeben
		partyController.getParty().setNebenCharakter(partyUeberschreibung);
		kampfAuswerten();
	}

	/**
	 * SpielerCharaktere und Feinde die noch Actionen haetten werden aus der
	 * Actionsliste ausgeschlossen sollten Sie vor Ausfuehrung ihrer Action
	 * gestorben sein
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private void entferneToteCharaktereNachAction(ArrayList<SpielerCharakter> freundeDieNochLeben,
			ArrayList<SpielerCharakter> freundeDieNochActionHaben, ArrayList<Feind> feindeDieNochLeben,
			ArrayList<Feind> feindeDieNochActionHaben, ArrayList<SpielerCharakter> freundeDieGestorbenSind) {

		// Wenn SpielerCharaktere noch eine Action in dieser Runde haetten ausfuehren
		// koennen, aber vorher gestorben sind werden sie von der Actionsliste genommen
		for (int counter = 0; counter < freundeDieNochActionHaben.size(); counter++) {
			if (freundeDieNochActionHaben.get(counter).getGesundheitsPunkte() < 1) {
				freundeDieNochActionHaben.remove(counter);
			}
		}

		// Tote SpielerCharaktere werden von der freundeDieNochLeben-Liste genommen
		for (int counter = 0; counter < freundeDieNochLeben.size(); counter++) {
			if (freundeDieNochLeben.get(counter).getGesundheitsPunkte() < 1) {
				freundeDieGestorbenSind.add(freundeDieNochLeben.get(counter));
				freundeDieNochLeben.remove(counter);
			}
		}

		// Wenn Feinde noch eine Action in dieser Runde haetten ausfuehren koennen,
		// aber vorher gestorben sind werden sie von der Actionsliste genommen
		for (int counter = 0; counter < feindeDieNochActionHaben.size(); counter++) {
			if (feindeDieNochActionHaben.get(counter).getGesundheitsPunkte() < 1) {
				feindeDieNochActionHaben.remove(counter);
			}
		}

		// Tote Feinde werden von der feindeDieNochLeben-Liste genommen
		for (int counter = 0; counter < feindeDieNochLeben.size(); counter++) {
			if (feindeDieNochLeben.get(counter).getGesundheitsPunkte() < 1) {
				feindeDieNochLeben.remove(counter);
			}
		}
	}

	/**
	 * Aus allen Charakteren die noch eine Action in dieser Runde haben wird der mit
	 * der hoechsten Beweglichkeit bestimmt und ist als naechstes dran.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private Charakter naechstenCharakterBestimmen(ArrayList<SpielerCharakter> freundeDieNochActionHaben,
			ArrayList<Feind> feindeDieNochActionHaben) {
		List<Charakter> alleCharakterDieNochActionHaben = new ArrayList<>();
		Charakter naechsterCharakter = null;
		int counter = 0;
		// Wenn es noch lebende SpielerCharaktere gibt, die in dieser Runde noch eine
		// Action zur Verfuegung haben, kommen sie in die Gesamtauswahl zur Bestimmung
		// des Charakters der als naechstes am Zug ist.
		for (SpielerCharakter spielerCharakter : freundeDieNochActionHaben) {
			alleCharakterDieNochActionHaben.add(freundeDieNochActionHaben.get(counter));
			counter++;
		}
		counter = 0;

		// Wenn es noch lebende Feinde gibt, die in dieser Runde noch eine
		// Action zur Verfuegung haben, kommen sie in die Gesamtauswahl zur Bestimmung
		// des Charakters der als naechstes am Zug ist.
		for (Feind feind : feindeDieNochActionHaben) {
			alleCharakterDieNochActionHaben.add(feindeDieNochActionHaben.get(counter));
			counter++;
		}
		counter = 0;

		// Aus allen Charakteren die in dieser Runde noch eine Action ausfuehren koennen
		// wird der mit der hoechsten Beweglichkeit ermittelt.
		for (int index = 0, len = alleCharakterDieNochActionHaben.size(); index < len; index++) {

			// Als default Charakter wird immer der erste Charakter in der Liste als
			// naechster bestimmt.
			if (index == 0) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(index);
			}

			// Nun wird in jeder weiteren Iterration ueberprueft, ob der aktuell gepruefte
			// Charakter eine hoehere Beweglichkeit hat, als der zuvor als 'naechster'
			// abgespeicherte. Wenn ja, ist der neue Charakter (bis jetzt hoechster
			// Beweglichkeitswert) als naechstes dran.
			else if (alleCharakterDieNochActionHaben.get(index).getBeweglichkeit() > naechsterCharakter
					.getBeweglichkeit()) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(index);
			}
		}

		// Der Charakter mit dem hoechsten Beweglichkeitswert wird zurueckgegeben.
		return naechsterCharakter;
	}

	/**
	 * Nur SpielerCharaktere koennen diese Methode aufrufen. Hier kann der Spieler
	 * waehlen, welche Action als naechstes von einem der SpielerCharaktere
	 * ausgefuehrt werden soll
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private boolean aktionWaehlen(Charakter aktuellerCharakter, ArrayList<SpielerCharakter> freundeDieNochLeben,
			ArrayList<Feind> feindeDieNochLeben, ArrayList<Charakter> blockendeCharaktere) {

		boolean wurdeActionDurchgefuehrt = false;
		while (!wurdeActionDurchgefuehrt) {
			int input = 0;
			boolean gueltigeEingabe = true;
			System.out.println("Angreifen  (1)     Blocken (2)");
			System.out.println("Gegenstand (3)     Fliehen (4)");
			do {
				try {
					input = ScannerHelfer.nextInt();
					if (input < 1 || input > 4) {
						gueltigeEingabe = false;
						System.out.println("Eingabe nicht gueltig. Moeglichkeiten: | 1 | 2 | 3 | 4 |");
					}
				} catch (Exception e) {
					gueltigeEingabe = false;
					System.out.println("Eingabe nicht gueltig. Moeglichkeiten: | 1 | 2 | 3 | 4 |");
				}
			} while (!gueltigeEingabe);
			switch (input) {
			case 1:
				wurdeActionDurchgefuehrt = angreifen(aktuellerCharakter, freundeDieNochLeben, feindeDieNochLeben);
				break;
			case 2:
				wurdeActionDurchgefuehrt = blocken(aktuellerCharakter);
				blockendeCharaktere.add(aktuellerCharakter);
				break;
			case 3:
				wurdeActionDurchgefuehrt = gegenstand(aktuellerCharakter, freundeDieNochLeben);
				break;
			case 4:
				wurdeActionDurchgefuehrt = fliehen(freundeDieNochLeben, feindeDieNochLeben);
				break;
			default:
				System.out.println("FEHLER: Fehlerhafte Eingabe wurde nicht richt abgefangen. Zug beendet.");
			}
		}
		return true;
	}

	/**
	 * In dieser Methode befindet sich der Grossteil der Gegner-Kampflogik sowie das
	 * Auswahlverfahren des Spielers fuer seinen Angriff. Dazu zaehlt vor allem ob
	 * es moeglich ist eine Faehigkeit zu benutzen, auf welches Team die Faehigkeit
	 * benutzt werden kann, auf wie viele Ziele und AUF WELCHE Ziele innerhalb der
	 * Zielgruppe. Ebenfalls wird die Schaden/Heal-Staerke hier berechnet und auf
	 * die entsprechenden Charaktere angewendet.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private boolean angreifen(Charakter aktuellerCharakter, ArrayList<SpielerCharakter> freundeDieNochLeben,
			ArrayList<Feind> feindeDieNochLeben) {
		int skillWahlAlsInt = 0;
		boolean hatCharakterGenugMana = true;
		Faehigkeit eingesetzteFaehigkeit = null;
		ArrayList<Charakter> zielGruppe;
		ArrayList<Integer> zielWahl;

		// Spielerlogik
		if (aktuellerCharakter instanceof SpielerCharakter) {
			// Faehigkeitsauswahl bis gueltige Faehigkeit ausgewaehlt
			do {
				System.out.println("Fähigkeiten:");
				for (int counter = 0, len = aktuellerCharakter.getFaehigkeiten().size(); counter < len; counter++) {
					System.out.println(
							(1 + counter) + ". " + aktuellerCharakter.getFaehigkeiten().get(counter).getName());
				}
				while (skillWahlAlsInt < 1 || skillWahlAlsInt > aktuellerCharakter.getFaehigkeiten().size()) {
					try {
						System.out.println("Faehigkeit zwischen 1 und " + aktuellerCharakter.getFaehigkeiten().size()
								+ " waehlen:");
						skillWahlAlsInt = ScannerHelfer.nextInt();
					} catch (Exception e) {
						System.out.println("Faehigkeitswahl ungueltig.");
					}
				}
				eingesetzteFaehigkeit = aktuellerCharakter.getFaehigkeiten().get(skillWahlAlsInt - 1);

				// Reicht Mana aus, um Faehigkeit zu benutzen?
				if (eingesetzteFaehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte()) {
					System.out.println("Nicht genug Mana, um diese Faehigkeit zu benutzen!");
					hatCharakterGenugMana = false;
				}
				// Wenn Mana nicht ausreicht, wieder zur Faehigkeitsuebersicht und Auswahl
				// zurueck
			} while (!hatCharakterGenugMana);

			// Faehkeit soll auf Team gewirkt werden (Heal, Buff, etc.)
			if (eingesetzteFaehigkeit.istFreundlich()) {
				for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
					zielGruppe.add(freundeDieNochLeben.get(counter));
				}
			}

			// Faehkeit soll auf Gegner gewirkt werden (Schaden, Debuff, etc.)
			else {
				for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
					zielGruppe.add(feindeDieNochLeben.get(counter));
				}
			}

			System.out.println("Auf wen soll " + eingesetzteFaehigkeit.getName() + " gewirkt werden?");
			if (eingesetzteFaehigkeit.getZielAnzahl() == 1) {
				System.out.println("1 Ziel waehlen");
			}
			else {
				System.out.println(eingesetzteFaehigkeit.getZielAnzahl() + " Ziele waehlen");
			}
			for (int counter = 0, len = zielGruppe.size(); counter < len; counter++) {
				System.out.println((counter + 1) + "| " + zielGruppe.get(counter).getName());
			}

			// Richtige Anzahl an Zielen auswaehlen
			int zielWahlCounter = 0;
			int zielCharakterID = 0;
			boolean zielGueltig = false;
			while (zielWahl.size() != eingesetzteFaehigkeit.getZielAnzahl()) {
				while (!zielGueltig) {
					zielGueltig = false;
					System.out.println("Ziel " + (1 + zielWahl.size()) + " waehlen:");
					try {
						zielCharakterID = ScannerHelfer.nextInt();
						if (zielCharakterID < 1 || zielCharakterID > zielGruppe.size()) {
							System.out.println("Ziel nicht gueltig.");
						}
						else {
							zielGueltig = true;
						}
					} catch (Exception e) {
						System.out.println("Eingabe ungueltig.");
					}
				}
				zielWahl.add(zielCharakterID - 1);
			}
			// Alle Ziele richtig ausgewaehlt, Faehigkeit kann jetzt gecastet werden!
		}
		// Ab hier faengt die Gegnerlogik an
		else if (aktuellerCharakter instanceof Feind) {
			String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
			ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
			ArrayList<Feind> moeglicheFeinde = new ArrayList<>();
			ArrayList<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>();
			int nochZuWaehlendeZiele = 0;

			// Befuellt Feind-Ziel-ArrayList (Feind-Team)
			for (Feind feind : feindeDieNochLeben) {
				moeglicheFeinde.add(feind);
			}
			// Befuellt SpielerCharakter-Ziel-ArrayList (SpielerCharakter-Team)
			for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
				moeglicheSpielerCharaktere.add(spielerCharakter);
			}

			// Nur Faehigkeiten sind moeglich fuer die die Manapunkte auch reichen
			for (Faehigkeit faehigkeit : aktuellerCharakter.getFaehigkeiten()) {
				if (faehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
					moeglicheFaehigkeiten.add(faehigkeit);
				}
			}

			// Gegnerlogik ist Klassenabhaengig!!!
			switch (feindKlasse) {

			// Healer versuchen immer zuerst ihre Teammitglieder (inklusive sich selbst) zu
			// heilen!
			case "Healer":
				// Zielgruppe ist immer zuerst das eigene Team, ausser es wird im spaeteren
				// Logikverlauf anders entschieden
				for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
					zielGruppe.add(feindeDieNochLeben.get(counter));
				}

				// Entfernt alle Feinde aus dem eigenen Team als moegliche Ziele die die
				// maximale Gesundheit haben
				for (Feind feind : moeglicheFeinde) {
					if (feind.getGesundheitsPunkte() == feind.getMaxGesundheitsPunkte()) {
						moeglicheFeinde.remove(feind);
					}
				}

				// Entfernt alle Faehigkeiten die nicht aufs eigene Team genutzt werden koennen
				// und entfernt alle Faehigkeiten die mehr Ziele heilen koennen als es
				// Teammitglieder gibt die die Heilung benoetigen.
				for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
					if (faehigkeit.getZielAnzahl() > moeglicheFeinde.size() || !faehigkeit.istFreundlich()) {
						moeglicheFaehigkeiten.remove(faehigkeit);
					}
				}

				// Wenn nach den ganzen Filter keine Faehigkeiten mehr uebrig sind bedeutet das,
				// dass alle Feinde 100% ihrer Lebenspunkte haben. Erst jetzt will der Healer in
				// den Angriffsmodus gehen.
				if (moeglicheFaehigkeiten.isEmpty()) {
					System.out.println("Gegner-Heiler kann nichts heilen, da alle lebenden Gegner 100% HP haben.");

					// Ziel-Gruppe aendert sich von eigener (Feind) zur SpielerCharakter-Gruppe
					zielGruppe.clear();
					for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
						zielGruppe.add(freundeDieNochLeben.get(counter));
					}

					// Alle Faehigkeiten die aufs eigene Team angewendet werden koennen fliegen raus
					// Alle Faehigkeiten die auf mehr Charaktere angewendet werden koennen als es
					// Ziele gibt fliegen raus
					for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
						if (faehigkeit.getZielAnzahl() > zielGruppe.size() || faehigkeit.istFreundlich()) {
							moeglicheFaehigkeiten.remove(faehigkeit);
						}
					}

					// Faehigkeit wird aus dem moeglichen Pool zufaellig gewaehlt
					eingesetzteFaehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
					nochZuWaehlendeZiele = eingesetzteFaehigkeit.getZielAnzahl();
					while (nochZuWaehlendeZiele > 0) {
						SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
						for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
							if (spielerCharakter.getGesundheitsPunkte() < aktuellesZielSpielerCharakter
									.getGesundheitsPunkte()) {
								aktuellesZielSpielerCharakter = spielerCharakter;
							}
						}
						zielWahl.add(zielGruppe.indexOf(aktuellesZielSpielerCharakter));
						moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
						nochZuWaehlendeZiele--;
					}

				}
				// Es gibt Feind-Charaktere (eigenes Team) die geheilt werden koennen.
				// Das Faehigkeitsset besteht aus den zu Anfang bestimmten Faehigkeiten
				else {
					// Faehigkeit wird aus dem moeglichen Pool zufaellig gewaehlt
					eingesetzteFaehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
					nochZuWaehlendeZiele = eingesetzteFaehigkeit.getZielAnzahl();

					// Ziele werden auf Grundlage ihrer Lebenspunkte gewaehlt
					// Beim heilen werden Feinde mit niedriger Gesundheit priorisiert
					// Beim Schaden verursachen werden SpielerCharaktere mit niedriger Gesundheit
					// priorisiert
					while (nochZuWaehlendeZiele > 0) {
						Feind aktuellesZielFeind = moeglicheFeinde.get(0);
						for (Feind feind : moeglicheFeinde) {
							if (feind.getGesundheitsPunkte() < aktuellesZielFeind.getGesundheitsPunkte()) {
								aktuellesZielFeind = feind;
							}
						}
						zielWahl.add(zielGruppe.indexOf(aktuellesZielFeind));
						moeglicheFeinde.remove(aktuellesZielFeind);
						nochZuWaehlendeZiele--;
					}
				}
				break;

			// Tanks heilen sich entweder selbst, oder greifen die SpielerCharaktere-Gruppe
			// an, abhaengig von ihren eigenen Lebenspunkten
			case "Tank":
				boolean willIchMichHeilen = false;
				// Wenn der Tank weniger als 50% seiner maximalen Lebenspunkte hat, will er sich
				// selbst heilen
				if (aktuellerCharakter.getGesundheitsPunkte() * 2 < aktuellerCharakter.getMaxGesundheitsPunkte()) {
					willIchMichHeilen = true;
				}
				// In allen anderen Faellen will er die SpielerCharaktere-Gruppe angreifen
				else {
					willIchMichHeilen = false;
				}
				if (willIchMichHeilen) {

					// Wenn sich der Tank heilen will ist die Zielgruppe der Faehigkeit die eigene
					// Gruppe (Feind-Team)
					for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
						zielGruppe.add(feindeDieNochLeben.get(counter));
					}

					// Alle Faehigkeiten die nicht aufs eigene Team angewendet werden koennen
					// fliegen raus.
					// Alle Faehigkeiten die auf mehr als einen Charakter angewendet werden koennen
					// fliegen raus.
					for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
						if (!faehigkeit.istFreundlich()) {
							moeglicheFaehigkeiten.remove(faehigkeit);
						}
						if (faehigkeit.getZielAnzahl() != 1) {
							moeglicheFaehigkeiten.remove(faehigkeit);
						}
					}
					if (!moeglicheFaehigkeiten.isEmpty()) {
						// Faehigkeit wird zufaellig aus dem moeglichen Pool bestimmt und auf sich
						// selbst angewendet
						eingesetzteFaehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
						zielWahl.add(zielGruppe.indexOf(aktuellerCharakter));
					}

					// Tank will sich zwar selber heilen, aber kann aus welchen Gruenden auch immer
					// keine Faehigkeit auf sich wirken. Also muss er wohl in den Angriff wechseln.
					else {
						willIchMichHeilen = false;
					}

				}

				// Der Tank hat 50% oder mehr seiner maximalen Lebenspunkte oder kann keine
				// seiner Selbstheilungen benutzen. Daher will er nun Schaden an den
				// SpielerCharakteren verursachen
				else if (!willIchMichHeilen) {

					// Zielgruppe ist die SpielCharaktere-Gruppe
					for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
						zielGruppe.add(moeglicheSpielerCharaktere.get(counter));
					}

					// Faehigkeiten die aufs eigene Team angewendet werden fliegen raus
					for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
						if (faehigkeit.istFreundlich()) {
							moeglicheFaehigkeiten.remove(faehigkeit);
						}
					}

					// Faehigkeiten die mehr Ziele haben als es noch auswaehlbare SpielerCharaktere
					// gibt fliegen raus
					for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
						if (faehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size()) {
							moeglicheFaehigkeiten.remove(faehigkeit);
						}
					}

					// Faehigkteit wird zufaellig aus dem moeglichen Pool bestimmt
					eingesetzteFaehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
					nochZuWaehlendeZiele = eingesetzteFaehigkeit.getZielAnzahl();

					// Ziele werden bestimmt, wobei niedrige Lebenspunkte priorisiert werden
					while (nochZuWaehlendeZiele > 0) {
						SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
						for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
							if (spielerCharakter.getGesundheitsPunkte() < aktuellesZielSpielerCharakter
									.getGesundheitsPunkte()) {
								aktuellesZielSpielerCharakter = spielerCharakter;
							}
						}

						// Ziele werden hinzugefuegt
						zielWahl.add(zielGruppe.indexOf(aktuellesZielSpielerCharakter));
						moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
						nochZuWaehlendeZiele--;
					}
				}
				break;

			// 'Physische DD' und 'Magische DD' haben beide die gleiche offensive Logik,
			// welche der Logik entspricht, die der Tank verfolgt, solange er 50% oder mehr
			// seiner maximalen Lebenspunkte hat.
			// Daher wird der Code hier fuer beide Klassen nicht weiter erklaert.
			case "Physischer DD":
			case "Magischer DD":
				for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
					zielGruppe.add(moeglicheSpielerCharaktere.get(counter));
				}
				for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
					if (faehigkeit.istFreundlich()) {
						moeglicheFaehigkeiten.remove(faehigkeit);
					}
				}
				for (Faehigkeit faehigkeit : moeglicheFaehigkeiten) {
					if (faehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size()) {
						moeglicheFaehigkeiten.remove(faehigkeit);
					}
				}
				eingesetzteFaehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
				nochZuWaehlendeZiele = eingesetzteFaehigkeit.getZielAnzahl();
				while (nochZuWaehlendeZiele > 0) {
					SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
					for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
						if (spielerCharakter.getGesundheitsPunkte() < aktuellesZielSpielerCharakter
								.getGesundheitsPunkte()) {
							aktuellesZielSpielerCharakter = spielerCharakter;
						}
					}
					zielWahl.add(zielGruppe.indexOf(aktuellesZielSpielerCharakter));
					moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
					nochZuWaehlendeZiele--;
				}
				break;
			}
		}

		// Faehigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
		// entsprechend durchgefuehrt
		aktuellerCharakter.setManaPunkte(aktuellerCharakter.getManaPunkte() - eingesetzteFaehigkeit.getManaKosten());
		System.out.println("Faehigkeit " + eingesetzteFaehigkeit.getName() + " wird eingesetzt.");

		// Jeder Charakter hat eine Grundchance von 60% zu treffen. Jeder Punkt in
		// Genauigkeit, bis zum Wert '20', erhoeht die Trefferwahrscheinlichkeit um 2%.
		// Wenn das Genauigkeitsattribut den Wert '20' oder hoeher erreicht hat,
		// betraegt die Wahrscheinlichkeit zu treffen 100%. Jeder Attributpunkt
		// in Genauigkeit ueber 20 wird fuer die Berechnung der
		// kritischerTreffer-Wahrscheinlichkeit benutzt, wodurch eine 'Ueberskillung'
		// keine Verschwendung darstellt.
		if (random.nextDouble() < (0.6 + 0.02 * aktuellerCharakter.getGenauigkeit())) {
			int aktuellerCharakterMacht = 0;
			int betroffenerCharakterAbwehr = 0;
			int basisSchadensWert = 100;
			// Effekt einzeln auf jedes Ziel angewendet bis alle Ziele abgearbeitet wurden
			while (!zielWahl.isEmpty()) {
				Charakter betroffenerCharakter = zielGruppe.get(zielWahl.get(0));
				String zielAttribut = eingesetzteFaehigkeit.getZielAttribut();
				// Zuerst wird geguckt, ob es sich um eine physische oder magische Faehigkeit
				// handelt Abhaengig davon werden physische bzw. magische Angriffs und
				// Verteidigungswerte zur Berechnung verwendet
				if (eingesetzteFaehigkeit.getFaehigkeitsTyp().equals("physisch")) {
					aktuellerCharakterMacht = aktuellerCharakter.getPhysischeAttacke();
					betroffenerCharakterAbwehr = betroffenerCharakter.getVerteidigung();
				}
				else {
					aktuellerCharakterMacht = aktuellerCharakter.getMagischeAttacke();
					betroffenerCharakterAbwehr = betroffenerCharakter.getMagischeVerteidigung();
				}

				// Es wird geguckt, ob die genutzte Faehigkeit kritisch trifft (Heal und
				// Schaden!) Jeder Charakter hat eine Grundchance von 30% kritisch zu treffen.
				// Bei einem kritischen Treffer ist die Faehigkeit grundsaetzlich 66% staerker.
				// Die Wahrscheinlichkeit kritisch zu treffen wird durch Beweglichkeit erhoeht.
				// Zusaetzlich kann eine Faehigkeit selbst eine erhoehte Wahrscheinlichkeit
				// haben kritisch zu treffen. Der Grundwert bei Faehigkeiten ist 1.0.
				// Jeder Attributpunkt des Charakters erhoeht die Wahrscheinlichkeit um 2%.
				// Spaetestens wenn man 35 Attributpunkte in Beweglichkeit hat (100%
				// Wahrscheinlichkeit kritisch zu treffen), erhoeht jeder weitere
				// Punkt den kritischen Schaden-Multiplikator um 1%. Wie bereits im Abschnitt
				// 'Genauigkeit' erklaert, wird jeder Punkt in Genauigkeit ueber einem Wert
				// von 20 in die kritische Treffer Berechnung einbezogen.
				// Hierbei wird der Genauigkeitsbonus 1:1 so behandelt wie ein
				// Geschwindigkeit-Attributspunkt. Sollte also die Wahrscheinlichkeit kritisch
				// zu treffen bereits bei 100% liegen, tragen die Bonus-Genauigkeitspunkte
				// ebenfalls zur Erhoehung des kritischen Schaden-Multiplikators bei.
				double kritMultiplikator = 1.0;
				int genauigkeitsBonus = 0;
				int ergebnisWert = 0;
				if (aktuellerCharakter.getGenauigkeit() > 20) {
					genauigkeitsBonus = aktuellerCharakter.getGenauigkeit() - 20;
				}
				double kritWahrscheinlichkeit = (0.3 + (eingesetzteFaehigkeit.getWahrscheinlichkeit() - 1.0)
						+ 0.02 * (aktuellerCharakter.getBeweglichkeit() + genauigkeitsBonus));
				if (random.nextDouble() < kritWahrscheinlichkeit) {
					if (kritWahrscheinlichkeit > 1.0) {
						kritMultiplikator = 1.66 + ((kritWahrscheinlichkeit - 1) / 2);
					}
					else {
						kritMultiplikator = 1.66;
					}
					System.out.printf("KRITISCHER TREFFER! (Krit-Multiplikator: x%.2f)", kritMultiplikator);
				}

				ergebnisWert = Math.floor((eingesetzteFaehigkeit.getEffektStaerke() / basisSchadensWert)
						* aktuellerCharakterMacht * kritMultiplikator);

				// Es wird geguckt welches das ZielAttribut der Faehigkeit ist
				// Heal und Schaden gehen beide auf 'gesundheitsPunkte'
				switch (zielAttribut) {
				case "gesundheitsPunkte":
					// gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);

						// Wenn der Verbuendete durch den Heal mehr HP haette als durch seine maxHP
						// moeglich, werden seine aktuellen HP gleich dem maxHP-Wert gesetzt
						if (betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
								.getMaxGesundheitsPunkte()) {
							betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
						}
					}
					// feindliches Team -> Schaden -> Verteidigung des Zieles muss beachtet werden
					// Wenn die Verteidigung des Zieles zu gross ist wird kein Schaden verursacht
					else {
						ergebnisWert -= betroffenerCharakterAbwehr;
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);

						// Wenn der toedliche Schaden dazu fuehrt, dass ein Charakter UNTER 0 HP faellt
						// werden die HP auf 0 gesetzt.
						if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
							betroffenerCharakter.setGesundheitsPunkte(0);
						}
					}
					break;
				case "maxGesundheitsPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() + ergebnisWert);

						// Die aktuellen Gesundheitspunkte muessen um den gleichen Wert erhoeht werden
						// da die Erhoehung der maxHP mit einem Heal einhergeht
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() - ergebnisWert);

						// Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxHP nun
						// mehr aktuelle als maxHP hat, muessen die aktuellen HP auf den neunen
						// maxHP-Stand gesetzt werden (impliziert Schaden verursachen)
						if (betroffenerCharakter.getMaxGesundheitsPunkte() < betroffenerCharakter
								.getGesundheitsPunkte()) {
							betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
						}

						// Wenn der Charakter dadurch stirbt und seine HP unter 0 fallen werden sie auf
						// 0 gesetzt
						if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
							betroffenerCharakter.setGesundheitsPunkte(0);
						}
					}
					break;
				case "manaPunkte":
					// gleiches Team -> Heal -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);

						// Wenn der Verbuendete durch den Buff mehr MP haette als durch seine maxMP
						// moeglich, werden seine aktuellen MP gleich dem maxMP-Wert gesetzt
						if (betroffenerCharakter.getManaPunkte() > betroffenerCharakter.getMaxManaPunkte()) {
							betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
						}
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() - ergebnisWert);

						// Wenn MP dadurch UNTER 0 MP fallen werden MP auf 0 gesetzt.
						if (betroffenerCharakter.getManaPunkte() < 0) {
							betroffenerCharakter.setManaPunkte(0);
						}
					}
					break;
				case "maxManaPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() + ergebnisWert);

						// Die aktuellen MP muessen um den gleichen Wert erhoeht werden
						// da die Erhoehung der maxMP diese mit anhebt
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() - ergebnisWert);

						// Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxMP nun
						// mehr aktuelle als maxMP hat, muessen die aktuellen MP auf den neunen
						// maxMP-Stand gesetzt werden (impliziert Reduktion der MP)
						if (betroffenerCharakter.getMaxManaPunkte() < betroffenerCharakter.getManaPunkte()) {
							betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
						}

						// Wenn seine MP UNTER 0 fallen werden sie auf 0 gesetzt
						if (betroffenerCharakter.getManaPunkte() < 0) {
							betroffenerCharakter.setManaPunkte(0);
						}
					}
					break;
				case "physischeAttacke":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() - ergebnisWert);

						// Wenn seine PhysischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getPhysischeAttacke() < 0) {
							betroffenerCharakter.setPhysischeAttacke(0);
						}
					}
					break;
				case "magischeAttacke":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() - ergebnisWert);

						// Wenn seine MagischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getMagischeAttacke() < 0) {
							betroffenerCharakter.setMagischeAttacke(0);
						}
					}
					break;
				case "genauigkeit":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() - ergebnisWert);

						// Wenn seine Genauigkeit UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getGenauigkeit() < 0) {
							betroffenerCharakter.setGenauigkeit(0);
						}
					}
					break;
				case "verteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - ergebnisWert);

						// Wenn seine Verteidigung UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getVerteidigung() < 0) {
							betroffenerCharakter.setVerteidigung(0);
						}
					}
					break;
				case "magischeVerteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - ergebnisWert);

						// Wenn seine MagischeVerteidigung UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getMagischeVerteidigung() < 0) {
							betroffenerCharakter.setMagischeVerteidigung(0);
						}
					}
					break;
				case "resistenz":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() - ergebnisWert);

						// Wenn seine Resistenz UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getResistenz() < 0) {
							betroffenerCharakter.setResistenz(0);
						}
					}
					break;
				case "beweglichkeit":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() - ergebnisWert);

						// Wenn seine Beweglichkeit UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getBeweglichkeit() < 0) {
							betroffenerCharakter.setBeweglichkeit(0);
						}
					}
					break;
				case "gesundheitsRegeneration":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter.setGesundheitsRegeneration(
								betroffenerCharakter.getGesundheitsRegeneration() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setGesundheitsRegeneration(
								betroffenerCharakter.getGesundheitsRegeneration() - ergebnisWert);

						// Wenn seine GesundheitsRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getGesundheitsRegeneration() < 0) {
							betroffenerCharakter.setGesundheitsRegeneration(0);
						}
					}
					break;
				case "manaRegeneration":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.istFreundlich()) {
						betroffenerCharakter
								.setManaRegeneration(betroffenerCharakter.getManaRegeneration() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter
								.setManaRegeneration(betroffenerCharakter.getManaRegeneration() - ergebnisWert);

						// Wenn seine ManaRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getManaRegeneration() < 0) {
							betroffenerCharakter.setManaRegeneration(0);
						}
					}
					break;
				default:
					System.out.println("Irgend ein Fehler ist aufgetreten");
					break;
				}
				zielWahl.remove(0);
			}
			System.out
					.println("Die Faehigkeit " + eingesetzteFaehigkeit.getName() + " wurde auf alle Ziele angewendet.");
		}
		// Die Faehigkeit hat nicht getroffen. Mana wurde trotzdem abgezogen und der Zug
		// des Charakters ist vorbei
		else {
			System.out.println("Die Faehigkeit " + eingesetzteFaehigkeit.getName() + "ist daneben gegangen!");
		}
		System.out.println("Charakter " + aktuellerCharakter.getName() + " hat den Zug beendet.");
		return true;
	}

	/**
	 * Blocken wird durchgefuehrt bis der Charakter erneut dran ist oder stirbt.
	 * Verteidigung und Magische Verteidigung wird um Angriff repektive Magischen
	 * Angriff erhoeht.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private boolean blocken(Charakter aktuellerCharakter) {
		aktuellerCharakter
				.setVerteidigung(aktuellerCharakter.getVerteidigung() + aktuellerCharakter.getPhysischeAttacke());
		aktuellerCharakter.setMagischeVerteidigung(
				aktuellerCharakter.getMagischeVerteidigung() + aktuellerCharakter.getMagischeAttacke());
		System.out.println(aktuellerCharakter.getName() + " faengt an zu blocken");
		return true;
	}

	/**
	 * Hier kann auf das Party-Verbrauchsgegenstandsinventar zugegriffen werden.
	 * Methoden sind alles ausgelagert.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private boolean gegenstand(Charakter aktuellerCharakter, ArrayList<SpielerCharakter> freundeDieNochLeben) {
		boolean wurdeItemBenutzt = false;
		wurdeItemBenutzt = partyStatusController.kampfInventarAnzeigen(freundeDieNochLeben);
		if (!wurdeItemBenutzt) {
			System.out.println("Es wurde kein Item benutzt.");
			return false;
		}
		else {
			return true;
		}
//		System.out.println("Welcher Gegenstand soll benutzt werden?");
//		while (!istGegenstandValide) {
//			try {
//				gegenstandInventarID = ScannerHelfer.nextInt();
//				if (gegenstandInventarID < 1
//						|| gegenstandInventarID > partyStatusController.getAnzahlVerbrauchsgegenstaende()) {
//					istGegenstandValide = false;
//				}
//				else {
//					istGegenstandValide = true;
//				}
//			} catch (Exception e) {
//				System.out.println("Bitte einen gueltigen Gegenstand auswaehlen.");
//			}
//		}
//		System.out.println("Auf welchen Charakter soll der Gegenstand angewendet werden?");
//		while (!istCharakterValide) {
//			try {
//				for (SpielerCharakter charakter : freundeDieNochLeben) {
//					System.out.println((1 + freundeDieNochLeben.indexOf(charakter)) + "| " + charakter.getName());
//				}
//				gegenstandAnwendenAufCharakterID = ScannerHelfer.nextInt();
//				if (gegenstandAnwendenAufCharakterID < 1
//						|| gegenstandAnwendenAufCharakterID > freundeDieNochLeben.size() + 1) {
//					istCharakterValide = false;
//				}
//				else {
//					istCharakterValide = true;
//				}
//			} catch (Exception e) {
//				System.out.println("Bitte ein gueltiges Ziel wahelen.");
//			}
//		}
//		verbrauchsgegenstandController.itemBenutzen(
//				partyStatusController.getVerbrauchsgegenstandByID(gegenstandInventarID),
//				freundeDieNochLeben.get(gegenstandAnwendenAufCharakterID - 1));
//		System.out.println("Der Gegenstand wurde erfolgreich eingesetzt.");
	}

	/**
	 * Jeder SpielerCharakter hat die Moeglichkeit fliehen() als Action
	 * auszuwaehlen. Ist das Fliehen erfolgreich, flieht die gesamte Gruppe und der
	 * Kampf ist beendet.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private boolean fliehen(ArrayList<SpielerCharakter> freundeDieNochLeben, ArrayList<Feind> feindeDieNochLeben) {
		int nettoBeweglichkeit = 0;
		for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
			nettoBeweglichkeit += spielerCharakter.getBeweglichkeit();
		}
		for (Feind feind : feindeDieNochLeben) {
			nettoBeweglichkeit -= feind.getBeweglichkeit();
		}
		if (nettoBeweglichkeit > 0) {
			System.out.println("Die Flucht war erfolgreich!");
			return true;
		}
		else {
			System.out.println("Die Flucht ist fehlgeschlagen...");
			return false;
		}
	}

	/**
	 * Kampfende wird ausgewertet -> Exp wird verteilt Gold und Ressourcen werden
	 * verteilt Statistik wird gepflegt GameOver wird geprueft Endet in Hub oder
	 * GameOver
	 *
	 * @author Nick
	 * @since 16.11.2023
	 */
	private void kampfAuswerten() {
		Party party = partyController.getParty();
		ArrayList<SpielerCharakter> ueberlebende = new ArrayList<>();
		ArrayList<SpielerCharakter> kaputte = new ArrayList<>();
		if (party.getHauptCharakter().getGesundheitsPunkte() > 0) {
			ueberlebende.add(party.getHauptCharakter());
		}
		else {
			kaputte.add(party.getHauptCharakter());
		}
		SpielerCharakter[] nebenCharakter = party.getNebenCharakter();
		for (int i = 0; i < nebenCharakter.length; i++) {
			if (nebenCharakter[i] != null && nebenCharakter[i].getGesundheitsPunkte() > 0) {
				ueberlebende.add(nebenCharakter[i]);
			}
			else if (nebenCharakter[i] != null) {
				kaputte.add(nebenCharakter[i]);
			}
		}
		if (ueberlebende.size() > 0) {
			int gewonnenesGold = (int) Math.floor(partyController.getPartyLevel() * 10);
			partyController.goldHinzufuegen(gewonnenesGold);
			for (SpielerCharakter spielerCharakter : ueberlebende) {
				CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
			}
			statistikController.goldErhoehen(gewonnenesGold);
			statistikController.durchgefuehrteKaempfeErhoehen();
			statistikController.gewonneneKaempfeErhoehen();
			// TODO RESSOURCEN DER GEGNER MIT EINER CHANCE INS GLOBALE INVENTAR PACKEN
			gameHubController.hubAnzeigen();
		}
		if (ueberlebende.size() == 0) {
			statistikController.durchgefuehrteKaempfeErhoehen();
			statistikController.verloreneKaempfeErhoehen();
			if (partyController.getPartyGold() < (Math.floor(partyController.getPartyLevel() * 2.5))) {
				partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel() * 2.5));
				if (gameController.isHardcore()) {
					party.getHauptCharakter().setGesundheitsPunkte(1);
					party.setNebenCharakter(new SpielerCharakter[3]);
				}
				else {
					for (SpielerCharakter spielerCharakter : kaputte) {
						spielerCharakter.setGesundheitsPunkte(1);
					}
				}
				gameHubController.hubAnzeigen();
			}
			else {
				GameOver.gameOverAnzeigen(statistikController.getStatistik(), partyController);
			}
		}

	}
}
