package kampf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import gegenstaende.GegenstandController;
import gegenstaende.material.Material;
import gegenstaende.traenke.Verbrauchsgegenstand;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import hilfsklassen.ZufallsZahlenGenerator;
import party.Party;
import party.PartyController;
import partystatus.PartyStatusController;
import statistik.GameOverView;
import statistik.StatistikController;
import trainer.faehigkeiten.Faehigkeit;
import view.AnsichtsTyp;
import view.ViewController;

public class KampfController {
	private FeindController feindController;
	private PartyController partyController;
	private PartyStatusController partyStatusController;
	private StatistikController statistikController;
	private GameController gameController;
	private GameHubController gameHubController;
	private Random random = new Random();
	private Feind[] feinde;
	private HauptmenuController hauptmenuController;
	private SpeicherstandController speicherstandController;
	private ViewController viewController;
	private KampfView kampfView;

	ArrayList<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
	ArrayList<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
	ArrayList<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
	ArrayList<Feind> feindeDieNochLeben = new ArrayList<>();
	ArrayList<Feind> feindeDieNochActionHaben = new ArrayList<>();
	ArrayList<Charakter> blockendeCharaktere = new ArrayList<>();
	ArrayList<Charakter> selbstBuffCharaktere = new ArrayList<>();
	ArrayList<Feind> feindeDieGestorbenSind = new ArrayList<>();
	ArrayList<SpielerCharakter> partyAnordnung = new ArrayList<>();
	ArrayList<Feind> gegnerAnordnung = new ArrayList<>();
	ArrayList<Charakter> aktuelleZugreihenfolge = new ArrayList<>();
	ArrayList<SpielerCharakter> nebenCharaktereVorKampfbeginn = new ArrayList<>();
	ArrayList<Charakter> zielGruppe = new ArrayList<>();
	SpielerCharakter hauptCharakterVorKampfbeginn;
	Faehigkeit gegnerFaehigkeit;
	Party party;
	Charakter aktuellerCharakter;
	ArrayList<String> kampfWerteLog = new ArrayList<String>();
	boolean[] istKampfVorbei = { false };

	public KampfController(FeindController feindController, PartyController partyController,
			StatistikController statistikController, GameController gameController, GameHubController gameHubController,
			HauptmenuController hauptmenuController, PartyStatusController partyStatusController,
			SpeicherstandController speicherstandController, ViewController viewController) {
		this.feindController = feindController;
		this.partyController = partyController;
		this.statistikController = statistikController;
		this.gameController = gameController;
		this.partyStatusController = partyStatusController;
		this.gameHubController = gameHubController;
		this.hauptmenuController = hauptmenuController;

		hauptCharakterVorKampfbeginn = partyController.getParty().getHauptCharakter().clone();
		for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
			if (nebenCharakter != null) {
				nebenCharaktereVorKampfbeginn.add(nebenCharakter.clone());
			}
		}
		this.party = partyController.getParty();
		this.speicherstandController = speicherstandController;
		this.viewController = viewController;

	}

	public void kaempfenAnzeigen() {
		kampfStarten();
	}

	/**
	 * Startpunkt für kaempfe
	 *
	 * @since 19.11.2023
	 * @author Maass
	 */
	public void kampfStarten() {
		this.feinde = feindController.gegnerGenerieren(partyController);
		ArrayList<Charakter> zugReihenfolge = new ArrayList<>();
		zugReihenfolge.add(partyController.getParty().getHauptCharakter());
		for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
			if (spielerCharakter != null && spielerCharakter.getGesundheitsPunkte() > 0) {
				zugReihenfolge.add(spielerCharakter);
			}
		}
		for (Feind feind : feinde) {
			zugReihenfolge.add(feind);
		}
		zugReihenfolge.sort(Comparator.comparingInt(Charakter::getBeweglichkeit));
		kampfBeginn(zugReihenfolge);

	}

	/**
	 * Nach Initialisierung des Kampfes geht es hier los. Alle noch benötigten
	 * ArrayLists und Werte werden befüllt, bevor an den KampfView-Controller
	 * übergeben wird
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private void kampfBeginn(ArrayList<Charakter> initialeZugreihenfolge) {
		boolean[] istKampfVorbei = { false };

		partyAnordnung.add(partyController.getParty().getHauptCharakter());
		for (SpielerCharakter nebencharakter : partyController.getParty().getNebenCharakter()) {
			if (nebencharakter != null) {
				partyAnordnung.add(nebencharakter);
			}
		}

		// freundeDieNochLeben, feindeDieNochLeben, etc. wird alles befuellt
		if (partyController.getParty().getHauptCharakter().getGesundheitsPunkte() > 0) {
			freundeDieNochLeben.add(partyController.getParty().getHauptCharakter());
		}
		else {
			freundeDieGestorbenSind.add(partyController.getParty().getHauptCharakter());
		}
		int index = 0;
		for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
			if (nebenCharakter != null) {
				if (nebenCharakter.getGesundheitsPunkte() > 0) {
					freundeDieNochLeben.add(partyController.getParty().getNebenCarakter(index));
				}
				else {
					freundeDieGestorbenSind.add(partyController.getParty().getNebenCarakter(index));
				}
			}
			index++;
		}
		for (int counter = 0, len = initialeZugreihenfolge.size(); counter < len; counter++) {
			if (initialeZugreihenfolge.get(counter) instanceof Feind) {
				feindeDieNochLeben.add((Feind) initialeZugreihenfolge.get(counter));
				gegnerAnordnung.add((Feind) initialeZugreihenfolge.get(counter));
			}
		}
		for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
			freundeDieNochActionHaben.add(freundeDieNochLeben.get(counter));
		}
		for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
			feindeDieNochActionHaben.add(feindeDieNochLeben.get(counter));
		}
		for (Charakter charakter : initialeZugreihenfolge) {
			aktuelleZugreihenfolge.add(charakter);
		}
		Collections.reverse(aktuelleZugreihenfolge);
		aktuellerCharakter = aktuelleZugreihenfolge.get(0);

		this.kampfView = new KampfView(this);
		viewController.anmelden(this.kampfView, null, AnsichtsTyp.NICHT_CACHE);
	}

	// Runde vorbei. Alle noch lebenden SpielerCharaktere und Feinde regenerieren HP
	// und MP
	public void regenerationNachRunde() {
		if (!istKampfVorbei[0]) {
			for (SpielerCharakter freund : freundeDieNochLeben) {
				freund.setGesundheitsPunkte(
						freund.getGesundheitsPunkte() + (int) Math.round(freund.getGesundheitsRegeneration() / 8.0));
				freund.setManaPunkte(freund.getManaPunkte() + (int) Math.round(freund.getManaRegeneration() / 8.0));
				if (freund.getGesundheitsPunkte() > freund.getMaxGesundheitsPunkte()) {
					freund.setGesundheitsPunkte(freund.getMaxGesundheitsPunkte());
				}
				if (freund.getManaPunkte() > freund.getMaxManaPunkte()) {
					freund.setManaPunkte(freund.getMaxManaPunkte());
				}
			}
			for (Feind feind : feindeDieNochLeben) {
				feind.setGesundheitsPunkte(
						feind.getGesundheitsPunkte() + (int) Math.round(feind.getGesundheitsRegeneration() / 8.0));
				feind.setManaPunkte(feind.getManaPunkte() + (int) Math.round(feind.getManaRegeneration() / 8.0));
				if (feind.getGesundheitsPunkte() > feind.getMaxGesundheitsPunkte()) {
					feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
				}
				if (feind.getManaPunkte() > feind.getMaxManaPunkte()) {
					feind.setManaPunkte(feind.getMaxManaPunkte());
				}
			}
		}
		for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
			if (spielerCharakter.getGesundheitsPunkte() > 0) {
				freundeDieNochActionHaben.add(spielerCharakter);
			}
			else {
				freundeDieNochActionHaben.remove(spielerCharakter);
			}
		}
		for (Feind feind : feindeDieNochLeben) {
			if (feind.getGesundheitsPunkte() > 0) {
				feindeDieNochActionHaben.add(feind);
			}
			else {
				feindeDieNochActionHaben.remove(feind);
			}
		}
		if (!istKampfVorbei[0]) {
		}
		if (feindeDieNochLeben.isEmpty() || freundeDieNochLeben.isEmpty()) {
			istKampfVorbei[0] = true;
			if (feindeDieNochLeben.isEmpty()) {
				freundeDieNochActionHaben.clear();
			}
			else {
				feindeDieNochActionHaben.clear();
			}
		}
	}

	/**
	 * Resettet Statuswerte nach Kampf bevor es zurück ins GameGub geht
	 *
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void buffsUndDebuffsEntferne() {
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
			for (SpielerCharakter spielerCharakter : new ArrayList<>(freundeDieNochLeben)) {
				// Wenn die Geschichte des Charakters zeigt, dass er der Hauptcharakter ist
				// werden die HP des Hauptcharakters auf die von diesem Spielercharakter
				// gesetzt
				if (spielerCharakter.getGeschichte()
						.equals(partyController.getParty().getHauptCharakter().getGeschichte())) {
					partyController.getParty().getHauptCharakter()
							.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte());
					partyController.getParty().getHauptCharakter().setManaPunkte(spielerCharakter.getManaPunkte());
					freundeDieNochLeben.remove(spielerCharakter);
					// Das bedeutet, dass der hauptcharakter noch lebt
					hautpcharakterLebtNoch = true;
				}
			}

			// Wenn der Hauptcharakter nicht mehr lebt werden seine HP auf 0 gesetzt
			if (!hautpcharakterLebtNoch) {
				partyController.getParty().getHauptCharakter().setGesundheitsPunkte(0);
				partyController.getParty().getHauptCharakter().setManaPunkte(0);
			}

			counter = 0;
			SpielerCharakter[] nebencharaktere = new SpielerCharakter[3];

			for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
				for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieNochLeben) {
					if (spielerCharakter.getName().equals(nebenCharaktereDieUeberlebtHaben.getName())) {
						if (nebencharaktere[0] == null) {
							nebencharaktere[0] = spielerCharakter;
							spielerCharakter
									.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
							spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
						}
						else if (nebencharaktere[1] == null) {
							nebencharaktere[1] = spielerCharakter;
							spielerCharakter
									.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
							spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
						}
						else if (nebencharaktere[2] == null) {
							nebencharaktere[2] = spielerCharakter;
							spielerCharakter
									.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
							spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
						}
					}
				}
			}
			for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
				for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieGestorbenSind) {

					if (nebencharaktere[0] == null) {
						nebencharaktere[0] = spielerCharakter;
						spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
						spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
					}
					else if (nebencharaktere[1] == null) {
						nebencharaktere[1] = spielerCharakter;
						spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
						spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
					}
					else if (nebencharaktere[2] == null) {
						nebencharaktere[2] = spielerCharakter;
						spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
						spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
					}
				}
			}

			partyController.getParty().setNebenCharakter(nebencharaktere);

		}

		// Alles Partymitglieder sind tot und der Kampf wurde verloren. Alle am Anfang
		// des Kampfes erstellten Koepien koennen mit einem HP Wert von 0 an die Party
		// zurueckgegeben werden
		else

		{
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
		// partyController.getParty().setNebenCharakter(partyUeberschreibung);
		kampfAuswerten();
	}

//		}).start();

	/**
	 * Abhängig von der Klasse des Gegners wird hier seine Angriffs/Heal- Logik
	 * bestimmt und entweder geblockt oder eine Fähigkeit genutzt
	 *
	 * @param gegner Gegner für den die Logik bestimmt werden soll - Feind
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void gegnerlogik(Feind gegner) {
		switch (gegner.getKlasse().getBezeichnung()) {
		case "Tank":
			// 65% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
			// SpielerCharaktere-Team)
			if (random.nextDouble() < 0.65) {
				gegnerLogikFaehigkeitundZielGruppe();
				kampfView.setZielGruppe(zielGruppe);
				kampfView.setFaehigkeit(gegnerFaehigkeit);
				faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
			}
			// 35% Chance, dass der Tank blockt
			else {
				blocken();
			}
			break;
		// Alle anderen Klassen haben die gleichen Wahrscheinlichkeiten zu blocken (10%)
		// oder eine Faehigkeit zu benutzen (90%)
		case "Healer":
		case "Magischer DD":
		case "Physischer DD":
			if (random.nextDouble() < 0.9) {
				gegnerLogikFaehigkeitundZielGruppe();
				kampfView.setZielGruppe(zielGruppe);
				kampfView.setFaehigkeit(gegnerFaehigkeit);
				faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
			}
			else {
				blocken();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Lässt Gegner Fähigkeit und Ziele wählen, falls er angreift und nicht blockt
	 *
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void gegnerLogikFaehigkeitundZielGruppe() {
		String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
		ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
		ArrayList<Feind> moeglicheFeinde = new ArrayList<>();
		ArrayList<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>();
		moeglicheSpielerCharaktere.clear();
		moeglicheFeinde.clear();
		this.zielGruppe.clear();
		this.kampfWerteLog.clear();
		ArrayList<Charakter> zielGruppe = this.zielGruppe;
		Faehigkeit faehigkeit = null;
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
		for (Faehigkeit eineFaehigkeit : getAktiveFaehigkeiten(aktuellerCharakter)) {
			if (eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
				moeglicheFaehigkeiten.add(eineFaehigkeit);
			}
		}

		// Gegnerlogik ist Klassenabhaengig!!!
		switch (feindKlasse) {

		// Healer versuchen immer zuerst ihre Teammitglieder (inklusive sich selbst) zu
		// heilen!
		case "Healer":
			zielGruppe.clear();
			// Zielgruppe ist immer zuerst das eigene Team, ausser es wird im spaeteren
			// Logikverlauf anders entschieden
			for (int counter = 0, len = feindeDieNochLeben.size(); counter < len; counter++) {
				zielGruppe.add(feindeDieNochLeben.get(counter));
			}

			// Entfernt alle Feinde aus dem eigenen Team als moegliche Ziele die die
			// maximale Gesundheit haben

			for (Feind feind : new ArrayList<>(moeglicheFeinde)) {
				if (feind.getGesundheitsPunkte() == feind.getMaxGesundheitsPunkte()) {
					moeglicheFeinde.remove(feind);
				}
			}

			// Entfernt alle Faehigkeiten die nicht aufs eigene Team genutzt werden koennen
			// und entfernt alle Faehigkeiten die mehr Ziele heilen koennen als es
			// Teammitglieder gibt die die Heilung benoetigen.
			for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
				if (eineFaehigkeit.getZielAnzahl() > moeglicheFeinde.size() || !eineFaehigkeit.isIstFreundlich()) {
					moeglicheFaehigkeiten.remove(eineFaehigkeit);
				}
			}

			// Wenn nach den ganzen Filter keine Faehigkeiten mehr uebrig sind bedeutet das,
			// dass alle Feinde 100% ihrer Lebenspunkte haben. Erst jetzt will der Healer in
			// den Angriffsmodus gehen.
			if (moeglicheFaehigkeiten.isEmpty()) {
				// Ziel-Gruppe aendert sich von eigener (Feind) zur SpielerCharakter-Gruppe
				zielGruppe.clear();

				// Alle Faehigkeiten die aufs eigene Team angewendet werden koennen fliegen raus
				// Alle Faehigkeiten die auf mehr Charaktere angewendet werden koennen als es
				// Ziele gibt fliegen raus
				for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
					if (eineFaehigkeit.getZielAnzahl() > zielGruppe.size() || eineFaehigkeit.isIstFreundlich()) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
				}

				zielGruppe.clear();
				if (!moeglicheFaehigkeiten.isEmpty()) {
					faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
					nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
					while (nochZuWaehlendeZiele > 0) {
						SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
						for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
							if (spielerCharakter.getGesundheitsPunkte() < aktuellesZielSpielerCharakter
									.getGesundheitsPunkte()) {
								aktuellesZielSpielerCharakter = spielerCharakter;
							}
						}
						// zielWahl.add(zielGruppe.indexOf(aktuellesZielSpielerCharakter));
						moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
						nochZuWaehlendeZiele--;
						if (moeglicheSpielerCharaktere.size() == 0) {
							nochZuWaehlendeZiele = 0;
						}
					}
					for (SpielerCharakter charakter : moeglicheSpielerCharaktere) {
						zielGruppe.add(charakter);
					}
				}
				else {
					zielGruppe.clear();
					faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
					if (faehigkeit.isIstFreundlich()) {
						if (aktuellerCharakter instanceof Feind) {
							zielGruppe.add(feindeDieNochLeben.get(0));
						}
						else {
							zielGruppe.add(freundeDieNochLeben.get(0));
						}
					}
					else {
						if (aktuellerCharakter instanceof Feind) {
							zielGruppe.add(freundeDieNochLeben.get(0));
						}
						else {
							zielGruppe.add(feindeDieNochLeben.get(0));
						}
					}
				}
			}
			// Es gibt Feind-Charaktere (eigenes Team) die geheilt werden koennen.
			// Das Faehigkeitsset besteht aus den zu Anfang bestimmten Faehigkeiten
			else {
				// Faehigkeit wird aus dem moeglichen Pool zufaellig gewaehlt
				faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
				nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();

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
					moeglicheFeinde.remove(aktuellesZielFeind);
					nochZuWaehlendeZiele--;
					if (moeglicheFeinde.size() == 0) {
						nochZuWaehlendeZiele = 0;
					}
					zielGruppe.add(aktuellesZielFeind);
				}
			}
			break;

		// Tanks heilen sich entweder selbst, oder greifen die SpielerCharaktere-Gruppe
		// an, abhaengig von ihren eigenen Lebenspunkten
		case "Tank":
			zielGruppe.clear();
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
				for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
					if (!eineFaehigkeit.isIstFreundlich()) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
					if (eineFaehigkeit.getZielAnzahl() != 1) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
				}
				if (!moeglicheFaehigkeiten.isEmpty()) {
					// Faehigkeit wird zufaellig aus dem moeglichen Pool bestimmt und auf sich
					// selbst angewendet
					faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
					// zielWahl.add(zielGruppe.indexOf(aktuellerCharakter));
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
				for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
					if (eineFaehigkeit.isIstFreundlich()) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
				}

				// Faehigkeiten die mehr Ziele haben als es noch auswaehlbare SpielerCharaktere
				// gibt fliegen raus
				for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
					if (eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size()) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
				}

				// Faehigkteit wird zufaellig aus dem moeglichen Pool bestimmt
				if (moeglicheFaehigkeiten.size() == 0) {
					moeglicheFaehigkeiten.add(aktuellerCharakter.getFaehigkeiten().get(0));
				}
				faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
				nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
				zielGruppe.clear();
				// Ziele werden bestimmt, wobei niedrige Lebenspunkte priorisiert werden
				while (nochZuWaehlendeZiele > 0) {
					SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
					for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
						if ((spielerCharakter.getGesundheitsPunkte() > 0) && (spielerCharakter
								.getGesundheitsPunkte() < aktuellesZielSpielerCharakter.getGesundheitsPunkte())) {
							aktuellesZielSpielerCharakter = spielerCharakter;
						}
					}

					moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
					nochZuWaehlendeZiele--;
					zielGruppe.add(aktuellesZielSpielerCharakter);
					if (moeglicheSpielerCharaktere.size() == 0) {
						nochZuWaehlendeZiele = 0;
					}
				}
			}
			if (faehigkeit == null) {
				faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
				kampfView.setFaehigkeit(faehigkeit);
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
			for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
				if (eineFaehigkeit.isIstFreundlich()) {
					moeglicheFaehigkeiten.remove(eineFaehigkeit);
				}
			}
			for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
				if (eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size()) {
					moeglicheFaehigkeiten.remove(eineFaehigkeit);
				}
			}
			zielGruppe.clear();
			faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
			nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
			while (nochZuWaehlendeZiele > 0) {
				SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
				for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
					if ((spielerCharakter.getGesundheitsPunkte() > 0) && (spielerCharakter
							.getGesundheitsPunkte() < aktuellesZielSpielerCharakter.getGesundheitsPunkte())) {
						aktuellesZielSpielerCharakter = spielerCharakter;
					}
				}
				moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
				nochZuWaehlendeZiele--;
				zielGruppe.add(aktuellesZielSpielerCharakter);
				if (moeglicheSpielerCharaktere.size() == 0) {
					nochZuWaehlendeZiele = 0;
				}
			}
			break;
		}

		if (faehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte()) {
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
		}

		if (faehigkeit.getZielAnzahl() > zielGruppe.size()) {
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
		}

		if (faehigkeit == null) {
			zielGruppe.clear();
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
			if (faehigkeit.isIstFreundlich()) {
				if (aktuellerCharakter instanceof Feind) {
					zielGruppe.add(feindeDieNochLeben.get(0));
				}
				else {
					zielGruppe.add(freundeDieNochLeben.get(0));
				}
			}
			else {
				if (aktuellerCharakter instanceof Feind) {
					zielGruppe.add(freundeDieNochLeben.get(0));
				}
				else {
					zielGruppe.add(feindeDieNochLeben.get(0));
				}
			}
		}
		gegnerFaehigkeit = faehigkeit;
		if (kampfView == null) {
			this.kampfView = new KampfView(this);
		}
		kampfView.setFaehigkeit(gegnerFaehigkeit);
	}

	/**
	 * Ausgewähle Fähigkeit wird vom aktuellen Charakter auf die gewählten Ziele
	 * benutzt
	 *
	 * @param charakterDerFaehigkeitBenutzt Charakter der die Fähigkeit benutzt -
	 *                                      Charakter
	 * @param ziele                         Ziele der Fähigkeit -
	 *                                      ArrayList<Charakter>
	 * @param faehigkeit                    Benutzte Fähigkeit - Fähigkeit
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */

	void faehigkeitBenutzen(Charakter charakterDerFaehigkeitBenutzt, ArrayList<Charakter> ziele,
			Faehigkeit faehigkeit) {

		boolean hatCharakterGenugMana = true;
		ArrayList<Charakter> zielWahl = new ArrayList<Charakter>(ziele);
		kampfWerteLog.clear();

		// Faehigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
		// entsprechend durchgefuehrt
		try {
			aktuellerCharakter.setManaPunkte(aktuellerCharakter.getManaPunkte() - faehigkeit.getManaKosten());
		} catch (Exception e) {
			zielWahl.clear();
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
			if (faehigkeit.isIstFreundlich()) {
				if (aktuellerCharakter instanceof Feind) {
					zielWahl.add(feindeDieNochLeben.get(0));
				}
				else {
					zielWahl.add(freundeDieNochLeben.get(0));
				}
			}
			else {
				if (aktuellerCharakter instanceof Feind) {
					zielWahl.add(freundeDieNochLeben.get(0));
				}
				else {
					zielWahl.add(feindeDieNochLeben.get(0));
				}
			}
		}

		// Jeder Charakter hat eine Grundchance von 60% zu treffen. Jeder Punkt in
		// Genauigkeit, bis zum Wert '20', erhoeht die Trefferwahrscheinlichkeit um 2%.
		// Wenn das Genauigkeitsattribut den Wert '20' oder hoeher erreicht hat,
		// betraegt die Wahrscheinlichkeit zu treffen 100%. Jeder Attributpunkt
		// in Genauigkeit ueber 20 wird fuer die Berechnung der
		// kritischerTreffer-Wahrscheinlichkeit benutzt, wodurch eine 'Ueberskillung'
		// keine Verschwendung darstellt.
		double treffer = random.nextDouble();
		if (treffer < (0.65 + 0.02 * aktuellerCharakter.getGenauigkeit())) {
			int aktuellerCharakterMacht = 0;
			int betroffenerCharakterAbwehr = 0;
			double basisSchadensWert = 100.0;
			// Effekt einzeln auf jedes Ziel angewendet bis alle Ziele abgearbeitet wurden
			while (!zielWahl.isEmpty()) {
				Charakter betroffenerCharakter = zielWahl.get(0);
				String zielAttribut = faehigkeit.getZielAttribut();
				// Zuerst wird geguckt, ob es sich um eine physische oder magische Faehigkeit
				// handelt Abhaengig davon werden physische bzw. magische Angriffs und
				// Verteidigungswerte zur Berechnung verwendet
				if (faehigkeit.getFaehigkeitsTyp().equals("physisch")) {
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
				// Jeder Attributpunkt des Charakters erhoeht die Wahrscheinlichkeit um 0.2%.
				// Spaetestens wenn man 350 Attributpunkte in Beweglichkeit hat (100%
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
				boolean krit = false;
				if (aktuellerCharakter.getGenauigkeit() > 20) {
					genauigkeitsBonus = aktuellerCharakter.getGenauigkeit() - 20;
				}
				double kritWahrscheinlichkeit = (0.3 + (faehigkeit.getWahrscheinlichkeit() - 1.0)
						+ 0.002 * (aktuellerCharakter.getBeweglichkeit() + genauigkeitsBonus));
				if (random.nextDouble() < kritWahrscheinlichkeit) {
					if (kritWahrscheinlichkeit > 1.0) {
						kritMultiplikator = 1.66 + ((kritWahrscheinlichkeit - 1) / 2);
					}
					else {
						kritMultiplikator = 1.66;
					}
					krit = true;
				}

				ergebnisWert = (int) Math.floor((faehigkeit.getEffektStaerke() / basisSchadensWert)
						* aktuellerCharakterMacht * kritMultiplikator);

				// Der Schwierigkeitsgrad beeinflusst den Basiswert VOR Abzug der
				// Verteidigungen.
				// =================================================================//
				// Leicht -> Spielerwert ist um 20% erhoeht.
				// -> Gegnerwert ist um 20% verringert.

				// Mittel -> Werte fuer alle unveraendert.

				// Schwer -> Spielerwerte 20% verringert.
				// -> Gegnerwerte 20% erhoeht.

				switch (gameController.getSchwierigkeitsgrad()) {
				case "Leicht":
					if (aktuellerCharakter instanceof SpielerCharakter) {
						ergebnisWert *= 1.2;
					}
					if (aktuellerCharakter instanceof Feind) {
						ergebnisWert *= 0.8;
					}
					break;
				case "Schwer":
					if (aktuellerCharakter instanceof SpielerCharakter) {
						ergebnisWert *= 0.8;
					}
					if (aktuellerCharakter instanceof Feind) {
						ergebnisWert *= 1.2;
					}
				}

				// Es wird geguckt welches das ZielAttribut der Faehigkeit ist
				// Heal und Schaden gehen beide auf 'gesundheitsPunkte'
				switch (zielAttribut) {
				case "gesundheitsPunkte":
					if (faehigkeit.isIstFreundlich()) {
						int healWert = 0;
						// gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
						if (ergebnisWert + betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
								.getMaxGesundheitsPunkte()) {
							healWert = betroffenerCharakter.getMaxGesundheitsPunkte()
									- betroffenerCharakter.getGesundheitsPunkte();
						}
						else {
							healWert = ergebnisWert;
						}
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);

						// Wenn der Verbuendete durch den Heal mehr HP haette als durch seine maxHP
						// moeglich, werden seine aktuellen HP gleich dem maxHP-Wert gesetzt
						if (betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
								.getMaxGesundheitsPunkte()) {
							betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
						}
						if (krit) {
							kampfWerteLog.add("KRITISCHER TREFFER!\n" + betroffenerCharakter.getName() + " wurde um "
									+ healWert + " geheilt!\n");
						}
						else {
							kampfWerteLog.add(betroffenerCharakter.getName() + " wurde um " + healWert + " geheilt!\n");
						}
					}
					// feindliches Team -> Schaden -> Verteidigung des Zieles muss beachtet werden
					// Wenn die Verteidigung des Zieles zu gross ist wird kein Schaden verursacht
					else {
						ergebnisWert -= betroffenerCharakterAbwehr;
						if (ergebnisWert < 1) {
							ergebnisWert = 1;
						}
						int tmpSchaden = 0;
						if (ergebnisWert > betroffenerCharakter.getGesundheitsPunkte()) {
							tmpSchaden = betroffenerCharakter.getGesundheitsPunkte();
						}
						else {
							tmpSchaden = ergebnisWert;
						}
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);
						String istGestorbenString = "";

						// Wenn der toedliche Schaden dazu fuehrt, dass ein Charakter UNTER 0 HP faellt
						// werden die HP auf 0 gesetzt.

						if (betroffenerCharakter.getGesundheitsPunkte() <= 0) {
							istGestorbenString = (betroffenerCharakter.getName() + " ist gestorben.\n");
							betroffenerCharakter.setGesundheitsPunkte(0);
						}
						if (krit) {
							kampfWerteLog.add("KRITISCHER TREFFER!\n" + betroffenerCharakter.getName() + " hat "
									+ tmpSchaden + " Schaden erlitten!\n" + istGestorbenString);
						}
						else {
							kampfWerteLog.add(betroffenerCharakter.getName() + " hat " + tmpSchaden
									+ " Schaden erlitten!\n" + istGestorbenString);
						}
					}
					break;
				case "maxGesundheitsPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int maxHP = ergebnisWert;
						betroffenerCharakter
								.setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() + ergebnisWert);

						// Die aktuellen Gesundheitspunkte muessen um den gleichen Wert erhoeht werden
						// da die Erhoehung der maxHP mit einem Heal einhergeht
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);
						kampfWerteLog.add("Maximale Gesundheit von " + betroffenerCharakter.getName() + " wurde um "
								+ ergebnisWert + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int maxHP = 0;
						String istGestorben = "";
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getMaxGesundheitsPunkte()) {
							maxHP = betroffenerCharakter.getMaxGesundheitsPunkte();
						}
						else {
							maxHP = ergebnisWert;
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
							istGestorben = betroffenerCharakter.getName() + " ist gestorben.\n";
						}
						kampfWerteLog.add("Maximale Gesundheit von " + betroffenerCharakter.getName() + "\nwurde um "
								+ ergebnisWert + " verringert.\n" + istGestorben);
					}
					break;
				case "manaPunkte":
					// gleiches Team -> Heal -> Resistenz des Zieles spielt keine Rolle
					int manaPunkteVorher = betroffenerCharakter.getManaPunkte();
					if (faehigkeit.isIstFreundlich()) {
						int mana = 0;
						if (betroffenerCharakter.getManaPunkte() + ergebnisWert > betroffenerCharakter
								.getMaxManaPunkte()) {
							mana = betroffenerCharakter.getMaxManaPunkte() - betroffenerCharakter.getManaPunkte();
						}
						else {
							mana = ergebnisWert;
						}
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);

						// Wenn der Verbuendete durch den Buff mehr MP haette als durch seine maxMP
						// moeglich, werden seine aktuellen MP gleich dem maxMP-Wert gesetzt
						if (betroffenerCharakter.getManaPunkte() > betroffenerCharakter.getMaxManaPunkte()) {
							betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
						}

						kampfWerteLog.add(
								"Mana von " + betroffenerCharakter.getName() + "\nwurdeum " + mana + " aufgefuellt\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird mindestwert angewendet
					else {
						int mana = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 1;
						}
						if (betroffenerCharakter.getManaPunkte() - ergebnisWert < 0) {
							mana = betroffenerCharakter.getManaPunkte();
						}
						else {
							mana = ergebnisWert;
						}
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() - ergebnisWert);

						// Wenn MP dadurch UNTER 0 MP fallen werden MP auf 0 gesetzt.
						if (betroffenerCharakter.getManaPunkte() < 0) {
							betroffenerCharakter.setManaPunkte(0);
						}
						kampfWerteLog.add(
								"Mana von " + betroffenerCharakter.getName() + "\nwurde um " + mana + " verringert\n");
					}
					break;
				case "maxManaPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int maxMP = ergebnisWert;
						betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() + ergebnisWert);

						// Die aktuellen MP muessen um den gleichen Wert erhoeht werden
						// da die Erhoehung der maxMP diese mit anhebt
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);
						kampfWerteLog.add("Maximales Mana von " + betroffenerCharakter.getName() + "\nwurde um " + maxMP
								+ " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int maxMP = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > aktuellerCharakter.getMaxManaPunkte()) {
							maxMP = aktuellerCharakter.getManaPunkte();
						}
						else {
							maxMP = ergebnisWert;
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
						kampfWerteLog.add("Maximales Mana von " + betroffenerCharakter.getName() + "\nwurde um " + maxMP
								+ " verringert.\n");
					}
					break;
				case "physischeAttacke":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int physAtk = ergebnisWert;
						betroffenerCharakter
								.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() + ergebnisWert);
						kampfWerteLog.add("Physische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
								+ physAtk + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int physAtk = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getPhysischeAttacke()) {
							physAtk = betroffenerCharakter.getPhysischeAttacke();
						}
						else {
							physAtk = ergebnisWert;
						}
						betroffenerCharakter
								.setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() - ergebnisWert);

						// Wenn seine PhysischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getPhysischeAttacke() < 0) {
							betroffenerCharakter.setPhysischeAttacke(0);
						}
						kampfWerteLog.add("Physische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
								+ physAtk + " verringert.\n");
					}
					break;
				case "magischeAttacke":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int magAtk = ergebnisWert;
						betroffenerCharakter
								.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() + ergebnisWert);
						kampfWerteLog.add("Magische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
								+ magAtk + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int magAtk = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getMagischeAttacke()) {
							magAtk = betroffenerCharakter.getMagischeAttacke();
						}
						else {
							magAtk = ergebnisWert;
						}
						betroffenerCharakter
								.setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() - ergebnisWert);

						// Wenn seine MagischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getMagischeAttacke() < 0) {
							betroffenerCharakter.setMagischeAttacke(0);
						}
						kampfWerteLog.add("Magische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
								+ magAtk + " verringert\n");
					}
					break;
				case "genauigkeit":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int genauigkeit = ergebnisWert;
						betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() + ergebnisWert);
						kampfWerteLog.add("Genauigkeit von " + betroffenerCharakter.getName() + "\nwurde um "
								+ genauigkeit + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int genauigkeit = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getGenauigkeit()) {
							genauigkeit = betroffenerCharakter.getGenauigkeit();
						}
						else {
							genauigkeit = ergebnisWert;
						}
						betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() - ergebnisWert);

						// Wenn Genauigkeit UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getGenauigkeit() < 0) {
							betroffenerCharakter.setGenauigkeit(0);
						}
						kampfWerteLog.add("Genauigkeit von " + betroffenerCharakter.getName() + "\nwurde um "
								+ genauigkeit + " verringert.\n");
					}
					break;
				case "verteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int verteidigung = ergebnisWert;
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() + ergebnisWert);
						kampfWerteLog.add("Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
								+ verteidigung + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int verteidigung = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getVerteidigung()) {
							verteidigung = betroffenerCharakter.getVerteidigung();
						}
						else {
							verteidigung = ergebnisWert;
						}
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - ergebnisWert);

						// Wenn Verteidigung UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getVerteidigung() < 0) {
							betroffenerCharakter.setVerteidigung(0);
						}
						kampfWerteLog.add("Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
								+ verteidigung + " verringert.\n");
					}
					break;
				case "magischeVerteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int magVer = ergebnisWert;
						betroffenerCharakter
								.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() + ergebnisWert);
						kampfWerteLog.add("Magische Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
								+ magVer + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int magVer = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getMagischeVerteidigung()) {
							magVer = betroffenerCharakter.getMagischeVerteidigung();
						}
						else {
							magVer = ergebnisWert;
						}
						betroffenerCharakter
								.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - ergebnisWert);

						// Wenn seine MagischeVerteidigung UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getMagischeVerteidigung() < 0) {
							betroffenerCharakter.setMagischeVerteidigung(0);
						}
						kampfWerteLog.add("Magische Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
								+ magVer + " verringert.\n");
					}
					break;
				case "resistenz":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int resistenz = ergebnisWert;
						betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() + ergebnisWert);
						kampfWerteLog.add("Resistenz von " + betroffenerCharakter.getName() + "\nwurde um " + resistenz
								+ " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int resistenz = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getResistenz()) {
							resistenz = betroffenerCharakter.getResistenz();
						}
						else {
							resistenz = ergebnisWert;
						}
						betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() - ergebnisWert);

						// Wenn seine Resistenz UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getResistenz() < 0) {
							betroffenerCharakter.setResistenz(0);
						}
						kampfWerteLog.add("Resistenz von " + betroffenerCharakter.getName() + "\nwurde um " + resistenz
								+ " verringert.\n");
					}
					break;
				case "beweglichkeit":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int beweglichkeit = ergebnisWert;
						betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() + ergebnisWert);
						kampfWerteLog.add("Beweglichkeit von " + betroffenerCharakter.getName() + "\nwurde um "
								+ beweglichkeit + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int beweglichkeit = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getBeweglichkeit()) {
							beweglichkeit = betroffenerCharakter.getBeweglichkeit();
						}
						else {
							beweglichkeit = ergebnisWert;
						}
						betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() - ergebnisWert);

						// Wenn seine Beweglichkeit UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getBeweglichkeit() < 0) {
							betroffenerCharakter.setBeweglichkeit(0);
						}
						kampfWerteLog.add("Beweglichkeit von " + betroffenerCharakter.getName() + "\nwurde um "
								+ beweglichkeit + " verringert.\n");
					}
					break;
				case "gesundheitsRegeneration":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int gesReg = ergebnisWert;
						betroffenerCharakter.setGesundheitsRegeneration(
								betroffenerCharakter.getGesundheitsRegeneration() + ergebnisWert);
						kampfWerteLog.add("Gesundheitsregeneration von " + betroffenerCharakter.getName()
								+ "\nwurde um " + gesReg + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int gesReg = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getGesundheitsRegeneration()) {
							gesReg = betroffenerCharakter.getGesundheitsRegeneration();
						}
						else {
							gesReg = ergebnisWert;
						}
						betroffenerCharakter.setGesundheitsRegeneration(
								betroffenerCharakter.getGesundheitsRegeneration() - ergebnisWert);

						// Wenn seine GesundheitsRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getGesundheitsRegeneration() < 0) {
							betroffenerCharakter.setGesundheitsRegeneration(0);
						}
						kampfWerteLog.add("Gesundheitsregeneration von " + betroffenerCharakter.getName()
								+ "\nwurde um " + gesReg + " verringert.\n");
					}
					break;
				case "manaRegeneration":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (faehigkeit.isIstFreundlich()) {
						int manaReg = ergebnisWert;
						betroffenerCharakter
								.setManaRegeneration(betroffenerCharakter.getManaRegeneration() + ergebnisWert);
						kampfWerteLog.add("Manaregeneration von " + betroffenerCharakter.getName() + "\nwurde um "
								+ manaReg + " erhöht.\n");
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						int manaReg = 0;
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						if (ergebnisWert > betroffenerCharakter.getManaRegeneration()) {
							manaReg = betroffenerCharakter.getManaRegeneration();
						}
						else {
							manaReg = ergebnisWert;
						}
						betroffenerCharakter
								.setManaRegeneration(betroffenerCharakter.getManaRegeneration() - ergebnisWert);

						// Wenn seine ManaRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getManaRegeneration() < 0) {
							betroffenerCharakter.setManaRegeneration(0);
						}
						kampfWerteLog.add("Manaregeneration von " + betroffenerCharakter.getName() + "\nwurde um "
								+ manaReg + " verringert.\n");
					}
					break;
				case "abwehr":
					int reduktionPhyDef = 0;
					int reduktionMagDef = 0;
					ergebnisWert -= betroffenerCharakter.getResistenz();
					if (ergebnisWert < 1) {
						ergebnisWert = 1;
					}
					if (ergebnisWert > betroffenerCharakter.getVerteidigung()) {
						reduktionPhyDef = betroffenerCharakter.getVerteidigung();
					}
					else {
						reduktionPhyDef = ergebnisWert;
					}
					if (ergebnisWert > betroffenerCharakter.getMagischeVerteidigung()) {
						reduktionMagDef = betroffenerCharakter.getMagischeVerteidigung();
					}
					else {
						reduktionMagDef = ergebnisWert;
					}
					betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - ergebnisWert);
					betroffenerCharakter
							.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - ergebnisWert);

					// Wenn seine ManaRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
					if (betroffenerCharakter.getVerteidigung() < 0) {
						betroffenerCharakter.setVerteidigung(0);
					}
					if (betroffenerCharakter.getMagischeVerteidigung() < 0) {
						betroffenerCharakter.setMagischeVerteidigung(0);
					}
					kampfWerteLog.add(
							"Abwehr von " + betroffenerCharakter.getName() + "\nwurde verringert." + "\n Verteidigung -"
									+ reduktionPhyDef + " , Mag. Verteidigung -" + reduktionMagDef + "\n");
					break;
				case "berserkerSpezial":
					// Berseker Spezialfaehigkeit
					int hp = 0;
					ergebnisWert -= betroffenerCharakterAbwehr;
					if (ergebnisWert < 1) {
						ergebnisWert = 1;
					}
					if (ergebnisWert > betroffenerCharakter.getGesundheitsPunkte()) {
						hp = betroffenerCharakter.getGesundheitsPunkte();
					}
					else {
						hp = ergebnisWert;
					}
					betroffenerCharakter
							.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);

					// Wenn seine HP UNTER 0 fallen werden sie auf 0 gesetzt
					if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
						betroffenerCharakter.setGesundheitsPunkte(0);
					}

					aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getGesundheitsPunkte() - 10);
					if (aktuellerCharakter.getGesundheitsPunkte() < 0) {
						aktuellerCharakter.setGesundheitsPunkte(0);
					}
					kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Berserker-Fähigkeit eingesetzt!\n"
							+ aktuellerCharakter.getName() + " hat sich einmalig selbst 10 HP Schaden zugefügt.\n"
							+ betroffenerCharakter.getName() + " hat " + hp + " Schaden erlitten.");
					break;
				case "feuermagierSpezial":
					// Berseker Spezialfaehigkeit
					hp = 0;
					ergebnisWert -= betroffenerCharakterAbwehr;
					if (ergebnisWert < 1) {
						ergebnisWert = 0;
					}
					betroffenerCharakter
							.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);

					// Wenn seine HP UNTER 0 fallen werden sie auf 0 gesetzt
					if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
						betroffenerCharakter.setGesundheitsPunkte(0);
					}
					if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
						betroffenerCharakter.setGesundheitsPunkte(0);
						kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Feuermagier-Fähigkeit eingesetzt!\n"
								+ aktuellerCharakter.getName() + " SO EIN FEUERBALL, JUNGE!\n"
								+ betroffenerCharakter.getName() + " hat" + hp + " Schaden erlitten.");
					}
					break;
				case "eismagierSpezial":
					// Berseker Spezialfaehigkeit
					aktuelleZugreihenfolge.remove(betroffenerCharakter);
					aktuelleZugreihenfolge.add(aktuelleZugreihenfolge.size() - 1, betroffenerCharakter);
					kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Eismagier-Fähigkeit eingesetzt!\n"
							+ "Oh Mai Oh Mai!\n" + betroffenerCharakter.getName()
							+ " ist diese Runde als letztes dran.");
					break;
				case "rabaukeSpezial":
					// Berseker Spezialfaehigkeit
					aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() + 999999);
					aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() + 999999);
					kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Rabauken-Fähigkeit eingesetzt!\n"
							+ "Du bischt prutal...!\nAbwehr von " + betroffenerCharakter.getName()
							+ "wurde enorm erhöht.");
					break;
				case "paladinSpezial":
					// Paldin Spezialfaehigkeit
					aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() + 120);
					aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte());
					kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Paladin-Fähigkeit eingesetzt!\n"
							+ "Ja bist du Deppert?!\n" + "100% Heilung und Maximale Gesundheit\nwurde stark erhöht.");
					break;
				case "priesterSpezial":
					// Berseker Spezialfaehigkeit
					if (betroffenerCharakter instanceof Feind) {
						for (Feind feind : feindeDieNochLeben) {
							feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + 20);
							feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + 20);
							feind.setPhysischeAttacke(feind.getPhysischeAttacke() + 80);
							feind.setMagischeAttacke(feind.getMagischeAttacke() + 80);
							feind.setVerteidigung(feind.getVerteidigung() + 80);
							feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + 80);
							feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + 80);
							feind.setManaRegeneration(feind.getManaRegeneration() + 80);
							feind.setGenauigkeit(feind.getGenauigkeit() + 80);
							feind.setBeweglichkeit(feind.getBeweglichkeit() + 80);
						}

					}
					else if (betroffenerCharakter instanceof SpielerCharakter) {
						for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
							spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + 20);
							spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + 20);
							spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + 30);
							spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + 30);
							spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + 30);
							spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + 30);
							spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + 30);
							spielerCharakter
									.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + 30);
							spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + 30);
							spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + 30);
							spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + 30);
						}
						kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Paladin-Fähigkeit eingesetzt!\n"
								+ "Da brat mir doch einer nen Storch...\n"
								+ "Statuswerte des Teams wurden stark erhöht.");
					}
					break;
				case "sanmausSpezial":
					// Berseker Spezialfaehigkeit
					betroffenerCharakter.setGesundheitsPunkte(
							(int) Math.floor((betroffenerCharakter.getMaxGesundheitsPunkte() * 0.7)));
					betroffenerCharakter
							.setManaPunkte((int) Math.floor((betroffenerCharakter.getMaxManaPunkte() * 0.5)));
					kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Sanmaus-Fähigkeit eingesetzt!\n"
							+ "Rettung in letzter Sekunde!\n" + "Gesundheitspunkte von "
							+ betroffenerCharakter.getName() + "\nwurden auf 70% gesetzt.\n"
							+ "Manapunkte wurden auf 70% gesetzt.\n");
					break;
				}
				if (betroffenerCharakter.getManaPunkte() < 0) {
					betroffenerCharakter.setManaPunkte(0);
				}
				if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
					betroffenerCharakter.setGesundheitsPunkte(0);
				}
				zielWahl.remove(0);
			}
		}
		// Die Faehigkeit hat nicht getroffen. Mana wurde trotzdem abgezogen und der Zug
		// des Charakters ist vorbei
		else {
			kampfWerteLog.add(faehigkeit.getName() + " ist daneben gegangen!\nTrefferchance liegt bei "
					+ (int) ((0.65 + 0.02 * aktuellerCharakter.getGenauigkeit()) * 100) + "%\n");
		}
		aktualisiereZugreihenfolge();
		aktualisiereIstKampfVorbei();
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
	public void blocken() {
		aktuellerCharakter
				.setVerteidigung(aktuellerCharakter.getVerteidigung() + aktuellerCharakter.getPhysischeAttacke());
		aktuellerCharakter.setMagischeVerteidigung(
				aktuellerCharakter.getMagischeVerteidigung() + aktuellerCharakter.getMagischeAttacke());
		blockendeCharaktere.add(aktuellerCharakter);
		aktualisiereZugreihenfolge();
	}

	/**
	 * Hier kann auf das Party-Verbrauchsgegenstandsinventar zugegriffen werden.
	 * Methoden sind alles ausgelagert.
	 *
	 * @param benutzenAuf Zielcharakter für Verbrauchsgegenstand - SpielerCharakter
	 * @param item Verbrauchsgegenstand - Verbrauchsgegenstand
	 * @author Melvin
	 * @since 18.11.2023
	 */

	public void gegenstand(Verbrauchsgegenstand item, SpielerCharakter benutzenAuf) {
		GegenstandController.verwendeVerbrauchsgegenstand(partyController.getParty().getVerbrauchsgegenstaende(), item,
				benutzenAuf);
		aktualisiereZugreihenfolge();
	}

	/**
	 * Jeder SpielerCharakter hat die Moeglichkeit fliehen() als Action
	 * auszuwaehlen. Die Party hat eine Grundchance von 20% zu fliehen. Die
	 * Differenz von der Gesamtbeweglichkeit der Party und der Gegenrgruppe
	 * entscheidet darüber, ob die Fluchtchance sich erhöht oder nicht. Ist das
	 * Fliehen erfolgreich, flieht die gesamte Gruppe und der Kampf ist beendet.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	public void fliehen() {
		int nettoBeweglichkeit = 0;
		double fluchtchance = 0.2;
		for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
			nettoBeweglichkeit += spielerCharakter.getBeweglichkeit();
		}
		for (Feind feind : feindeDieNochLeben) {
			nettoBeweglichkeit -= feind.getBeweglichkeit();
		}

		// 20% + 0.1125% pro Beweglichkeitsvorteil der Gruppe (positive Differenz)
		if (nettoBeweglichkeit > 0) {
			fluchtchance += (nettoBeweglichkeit / 800.0);
		}
		if (fluchtchance > 1.0) {
			fluchtchance = 1.0;
		}
//		fluchtchance = 0;
		if (random.nextDouble() < fluchtchance) {
			istKampfVorbei[0] = true;
		}
		else {
			// Flucht ist fehlgeschlagen, Aktion wird als ausgeführt betrachtet und
			aktualisiereZugreihenfolge();
		}
	}

	/**
	 * Aktualisiert die Zugreihenfolge nach einer ausgeführten Aktion und entfernt
	 * gegenbenfalls blockende Charaktere
	 *
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void aktualisiereZugreihenfolge() {
		Charakter charakterDerAktionAusgefuehrtHat = aktuelleZugreihenfolge.get(0);
		aktuelleZugreihenfolge.remove(0);
		aktuelleZugreihenfolge.add(charakterDerAktionAusgefuehrtHat);
		for (Charakter charakter : new ArrayList<>(aktuelleZugreihenfolge)) {
			if (charakter.getGesundheitsPunkte() < 1) {
				aktuelleZugreihenfolge.remove(charakter);
				if (charakter instanceof Feind) {
					feindeDieNochLeben.remove(charakter);
					feindeDieGestorbenSind.add((Feind) charakter);
				}
				else {
					freundeDieNochLeben.remove(charakter);
					freundeDieGestorbenSind.add((SpielerCharakter) charakter);
				}
			}
		}
		aktuellerCharakter = aktuelleZugreihenfolge.get(0);
		if (blockendeCharaktere.contains(aktuellerCharakter)) {
			blockendeCharaktere.remove(aktuellerCharakter);
		}
	}

	/**
	 * Kampfende wird ausgewertet - Exp wird verteilt Gold und Ressourcen werden
	 * verteilt Statistik wird gepflegt GameOver wird geprueft Endet in Hub oder
	 * GameOver
	 *
	 * @author Nick
	 * @since 04.12.2023
	 */
	public void kampfAuswerten() {
		kampfView.updateKampfBildschirm();
		resetKampfDaten();
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
		int ueberlebendeGegner = 0;
		for (Feind feind : feinde) {
			if (feind != null && feind.getGesundheitsPunkte() > 0) {
				ueberlebendeGegner++;
			}
		}
		if (ueberlebende.size() > 0 && ueberlebendeGegner <= 0) {
			// Sieg
			int gewonnenesGold = ((int) Math.floor(partyController.getPartyLevel()) * 10);
			partyController.goldHinzufuegen(gewonnenesGold);
			for (SpielerCharakter spielerCharakter : ueberlebende) {
				CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
				kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
						.concat(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!\n"));
			}
			statistikController.goldErhoehen(gewonnenesGold);
			statistikController.durchgefuehrteKaempfeErhoehen();
			statistikController.gewonneneKaempfeErhoehen();
			if (gameController.isHardcore()) {
				SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
						: new SpielerCharakter[0];
				for (int i = 0; i < soeldner.length; i++) {
					if (soeldner[i] != null) {
						if (soeldner[i].getGesundheitsPunkte() == 0) {
							kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
									.concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
							soeldner[i] = null;
						}
					}
				}
				party.setNebenCharakter(soeldner);
			}
			boolean ausruestungsloot = (ZufallsZahlenGenerator.zufallsZahlIntAb1(10) <= 1);
			if (ausruestungsloot) {
				int ausruestungsArt = ZufallsZahlenGenerator.zufallsZahlIntAb1(3);
				if (ausruestungsArt == 1) {
					partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getWaffe());
					kampfView.kampfErgebnis.setText(
							kampfView.kampfErgebnis.getText().concat(feinde[0].getWaffe().getName() + " erhalten!\n"));
				}
				if (ausruestungsArt == 2) {
					partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getRuestung());
					kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
							.concat(feinde[0].getRuestung().getName() + " erhalten!\n"));
				}
				if (ausruestungsArt == 3) {
					if (feinde[0].getAccessoires()[0] != null) {
						partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getAccessoires()[0]);
						kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
								.concat(feinde[0].getAccessoires()[0].getName() + " erhalten!\n"));
					}
				}
			}
			Material material = Material.zufaelligeMaterialArt();
			partyController.materialHinzufuegen(material, ((int) Math.floor(partyController.getPartyLevel())));
			kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
					.concat(((int) Math.floor(partyController.getPartyLevel())) + "x "
							+ material.getClass().getSimpleName() + " erhalten.\n" + gewonnenesGold
							+ " Gold erhalten.\n"));
			kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.sieg);
		}
		if (ueberlebende.size() == 0) {
			// Niederlage
			statistikController.durchgefuehrteKaempfeErhoehen();
			statistikController.verloreneKaempfeErhoehen();
			if (partyController.getPartyGold() >= (Math.floor(partyController.getPartyLevel() * 2.5))
					&& !gameController.isHardcore()) {
				// Genug gold im Nicht-Hardcore zum wiederbeleben
				partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel() * 2.5));
				for (SpielerCharakter spielerCharakter : kaputte) {
					spielerCharakter.setGesundheitsPunkte(1);
				}
				kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
						.concat("Die ohnmächtigen Charaktere wurden für "
								+ ((int) (Math.floor(partyController.getPartyLevel() * 2.5)))
								+ " Gold wiederbelebt.\n"));
				kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.niederlage);
			}
			else {
				if (gameController.isHardcore()) {
					// Verloren und ist Hardcore
					try {
						speicherstandController.entferneSpeicherstandHardcore(partyController);
					} catch (Exception e) {
					}
				}
				hauptmenuController.spielVorhandenProperty().set(false);
				GameOverView gameOverView = new GameOverView(statistikController.getStatistik(), partyController,
						viewController);
				viewController.anmelden(gameOverView, null, AnsichtsTyp.OHNE_OVERLAY);
			}
		}
		if (ueberlebende.size() > 0 && ueberlebendeGegner > 0) {
			// Flucht
			if (gameController.isHardcore()) {
				// HardCore
				SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
						: new SpielerCharakter[0];
				for (int i = 0; i < soeldner.length; i++) {
					if (soeldner[i] != null) {
						if (soeldner[i].getGesundheitsPunkte() == 0) {
							kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
									.concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
							soeldner[i] = null;
						}
					}
				}
				party.setNebenCharakter(soeldner);
			}
			kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText().concat("Flucht erfolgreich.\nFeigling!"));
		}
		kampfView.aktionAusgefuehrtInfoAnzeige.toBack();
		kampfView.kampfErgebnisContainer.toFront();
	}

	public static ArrayList<Faehigkeit> getAktiveFaehigkeiten(Charakter charakter) {
		ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
		for (Faehigkeit faehigkeit : charakter.getFaehigkeiten()) {
			if (faehigkeit.getLevel() > 0) {
				moeglicheFaehigkeiten.add(faehigkeit);
			}
		}
		return moeglicheFaehigkeiten;
	}

	/**
	 * Prüft, ob der Kampf vorbei ist
	 *
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void aktualisiereIstKampfVorbei() {
		int feindeCounter = 0;
		int partyCounter = 0;
		for (Charakter charakter : aktuelleZugreihenfolge) {
			if (charakter instanceof Feind) {
				feindeCounter++;
			}
			else {
				partyCounter++;
			}
		}
		if (feindeCounter == 0 || partyCounter == 0) {
			istKampfVorbei[0] = true;
		}
		else {
			istKampfVorbei[0] = false;
		}

	}

	/**
	 * Resettet alle für den Kampf wichtigen Daten nach Kampfabschluss für den
	 * nächsten Kampf
	 *
	 *
	 * @author OL Schiffer-Schmidl
	 * @since 06.12.23
	 */
	public void resetKampfDaten() {
		for (Charakter charakter : blockendeCharaktere) {
			if (charakter instanceof SpielerCharakter) {
				charakter.setVerteidigung(charakter.getVerteidigung() - charakter.getPhysischeAttacke());
				charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - charakter.getMagischeAttacke());
			}
		}
		freundeDieGestorbenSind.clear();
		freundeDieNochLeben.clear();
		freundeDieNochActionHaben.clear();
		feindeDieNochLeben.clear();
		feindeDieNochActionHaben.clear();
		feindeDieGestorbenSind.clear();
		aktuelleZugreihenfolge.clear();
		gegnerAnordnung.clear();
		partyAnordnung.clear();
		istKampfVorbei[0] = false;
		blockendeCharaktere.clear();
		selbstBuffCharaktere.clear();
		nebenCharaktereVorKampfbeginn.clear();
		zielGruppe.clear();
	}

	public void zurueckZumHub() {
		viewController.aktuelleNachHinten();
	}
}
