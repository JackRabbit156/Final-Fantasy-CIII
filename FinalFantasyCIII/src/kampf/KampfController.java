package kampf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.PDD;
import charakter.model.klassen.soeldner.Kaempfer;
import charakter.model.klassen.soeldner.Magier;
import charakter.model.klassen.soeldner.Supporter;
import gamehub.GameHubController;
import gegenstand.GegenstandController;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import hilfsklassen.Farbauswahl;
import hilfsklassen.ScannerHelfer;
import hilfsklassen.ZufallsZahlenGenerator;
import party.Party;
import party.PartyController;
import partystatus.PartyStatusController;
import statistik.GameOver;
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
	int healWert = 0;
	ArrayList<Integer> schadensWerte = new ArrayList<Integer>();
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
		SpielerCharakter soeldner = new Kaempfer("Hans im Glück", "Physischer DD", "Ist auch sehr Langweilig", 1);
		partyController.getParty().getHauptCharakter().setBeweglichkeit(9000);
		partyController.getParty().getHauptCharakter().setGenauigkeit(9000);
		partyController.getParty().getHauptCharakter().setPhysischeAttacke(9000);
		partyController.getParty().getHauptCharakter().getFaehigkeiten().get(0).setLevel(3);
		partyController.getParty().getHauptCharakter().getFaehigkeiten().get(1).setLevel(3);
		partyController.getParty().getHauptCharakter().getFaehigkeiten().get(2).setLevel(3);
		SpielerCharakter[] alleNebencharaktere = { soeldner, null, null };
		partyController.getParty().setNebenCharakter(alleNebencharaktere);
		hauptCharakterVorKampfbeginn = partyController.getParty().getHauptCharakter().clone();
		partyController.verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand.KLEINER_HEILTRANK, 3);
		partyController.verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand.MITTLERER_HEILTRANK, 3);
		partyController.verbrauchsgegenstandHinzufuegen(Verbrauchsgegenstand.MITTLERER_MANATRANK, 3);
		for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
			if (nebenCharakter != null) {
				nebenCharaktereVorKampfbeginn.add(nebenCharakter.clone());
			}
		}
		this.party = partyController.getParty();
		this.feinde = feindController.gegnerGenerieren(partyController);
		this.speicherstandController = speicherstandController;
		this.viewController = viewController;

	}

	/**
	 * Startpunkt für kaempfe
	 *
	 * @since 19.11.2023
	 * @author Maass
	 */
	public void kaempfenAnzeigen() {
		kampfStarten();
	}

	public void kampfStarten() {
		ArrayList<Charakter> zugReihenfolge = new ArrayList<>();
//		partyController.getParty().getHauptCharakter().getFaehigkeiten().get(1).setLevel(1);
//		partyController.getParty().getHauptCharakter().getFaehigkeiten().get(2).setLevel(1);
//		partyController.getParty().getHauptCharakter().setMaxManaPunkte(50);
//		partyController.getParty().getHauptCharakter().setManaPunkte(50);
		zugReihenfolge.add(partyController.getParty().getHauptCharakter());
		for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
			if (spielerCharakter != null && spielerCharakter.getGesundheitsPunkte() > 0) {
//				spielerCharakter.getFaehigkeiten().get(1).setLevel(1);
//				spielerCharakter.getFaehigkeiten().get(2).setLevel(1);
//				spielerCharakter.setMaxManaPunkte(50);
//				spielerCharakter.setManaPunkte(50);
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
	 * Nach Initialisierung des Kampfes geht es hier los und Kampf wird innerhalb
	 * der Funktion komplett ausgefuehrt. Benoetigt die initiale Zugreihenfolge der
	 * SpielerCharaktere und Feinde.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private void kampfBeginn(ArrayList<Charakter> initialeZugreihenfolge) {
		boolean[] istKampfVorbei = { false };

		partyAnordnung.add(partyController.getParty().getHauptCharakter());
		for (SpielerCharakter nebencharakter : partyController.getParty().getNebenCharakter()) {
			if(nebencharakter != null){
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
			if(nebenCharakter != null) {
				if (nebenCharakter.getGesundheitsPunkte() > 0) {
					freundeDieNochLeben.add(partyController.getParty().getNebenCarakter(index));
				} else {
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
		viewController.anmelden(kampfView, null, AnsichtsTyp.OHNE_OVERLAY);
	}
//		new Thread(() -> {
//			try {
//				TimeUnit.SECONDS.sleep(10L);
//			} catch (InterruptedException e) {
//			}
//			Platform.runLater(() -> {
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setHeaderText("10 Sekunden sind um!");
//				alert.show();
//			});
//		}).start();

	// TODO Ab hier Asynchron
//		new Thread(() -> {
	int runde = 1;
//		// Der gesamte Kampf befindet sich innerhalb der auesseren while-Schleife
//		while (!istKampfVorbei[0]) {
//
//			// Eine Runde ist vorbei wenn jeder lebende Charakter einen Zug ausgefuehrt hat
//			while (((!freundeDieNochActionHaben.isEmpty() || !feindeDieNochActionHaben.isEmpty())
//					&& !istKampfVorbei[0])) {
//
//			}

	// Runde vorbei. Alle noch lebenden SpielerCharaktere und Feinde regenerieren HP
	// und MP
	public void regenerationNachRunde() {
		if (!istKampfVorbei[0]) {
			System.out.println(
					Farbauswahl.YELLOW + "Runde vorbei. Alle Charaktere regenerieren HP und MP" + Farbauswahl.RESET);
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
			runde++;
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
	 * SpielerCharaktere und Feinde die noch Actionen haetten werden aus der
	 * Actionsliste ausgeschlossen sollten Sie vor Ausfuehrung ihrer Action
	 * gestorben sein
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private void entferneToteCharaktereNachAction() {

		// Wenn SpielerCharaktere noch eine Action in dieser Runde haetten ausfuehren
		// koennen, aber vorher gestorben sind werden sie von der Actionsliste genommen
		if (!freundeDieNochActionHaben.isEmpty()) {
			for (SpielerCharakter spielerCharakter : new ArrayList<>(freundeDieNochActionHaben)) {
				if (spielerCharakter.getGesundheitsPunkte() < 1) {
					freundeDieNochActionHaben.remove(spielerCharakter);
				}
			}
		}

		// Tote SpielerCharaktere werden von der freundeDieNochLeben-Liste genommen
		if (!freundeDieNochLeben.isEmpty()) {
			for (SpielerCharakter spielerCharakter : new ArrayList<>(freundeDieNochLeben)) {
				if (spielerCharakter.getGesundheitsPunkte() < 1) {
					freundeDieNochLeben.remove(spielerCharakter);
				}
			}
		}

		// Wenn Feinde noch eine Action in dieser Runde haetten ausfuehren koennen,
		// aber vorher gestorben sind werden sie von der Actionsliste genommen
		if (!feindeDieNochActionHaben.isEmpty()) {
			for (Feind feind : new ArrayList<>(feindeDieNochActionHaben)) {
				if (feind.getGesundheitsPunkte() < 1) {
					feindeDieNochActionHaben.remove(feind);
				}
			}
		}

		// Tote Feinde werden von der feindeDieNochLeben-Liste genommen
		if (!feindeDieNochLeben.isEmpty()) {
			for (Feind feind : new ArrayList<>(feindeDieNochLeben)) {
				if (feind.getGesundheitsPunkte() < 1) {
					feindeDieNochLeben.remove(feind);
				}
			}
		}
	}

	// TODO angreifen() mit richtigen Parametern fuellen bzw. angreifen Parameter
	// aendern
	public void gegnerlogik(Feind gegner) {
		switch (gegner.getKlasse().getBezeichnung()) {
		case "Tank":
			// 65% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
			// SpielerCharaktere-Team)
			if (random.nextDouble() < 0.99) {
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
			if (random.nextDouble() < 0.99) {
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
		aktualisiereZugreihenfolge();
	}

	/**
	 * Aus allen Charakteren die noch eine Action in dieser Runde haben wird der mit
	 * der hoechsten Beweglichkeit bestimmt und ist als naechstes dran.
	 *
	 *
	 * @author Melvin
	 * @since 18.11.2023
	 */
	private Charakter naechstenCharakterBestimmen() {
		List<Charakter> alleCharakterDieNochActionHaben = new ArrayList<>();
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
		alleCharakterDieNochActionHaben.sort(Comparator.comparingInt(Charakter::getBeweglichkeit));
		Charakter aktuellerCharakter = alleCharakterDieNochActionHaben.get(alleCharakterDieNochActionHaben.size() - 1);
		System.out.println();
		System.out.println(Farbauswahl.RESET + "Zugreihenfolge:");
		for (Charakter charakter : alleCharakterDieNochActionHaben) {
			System.out.println(charakter.getName() + " (Bew.: " + charakter.getBeweglichkeit() + ")");
		}
		System.out.println("\n" + aktuellerCharakter.getName() + " ist am Zug:");
		// Wenn aktueller Charakter als letzte Action geblockt hat, wird er jetzt, wo er
		// wieder dran ist, zuerst von der 'blocken-Liste' runter genommen und die
		// Statuswerte werden wieder normalisiert.
		if (blockendeCharaktere.contains(aktuellerCharakter)) {
			aktuellerCharakter
					.setVerteidigung(aktuellerCharakter.getVerteidigung() - aktuellerCharakter.getPhysischeAttacke());
			aktuellerCharakter.setMagischeVerteidigung(
					aktuellerCharakter.getMagischeVerteidigung() - aktuellerCharakter.getMagischeAttacke());
			blockendeCharaktere.remove(aktuellerCharakter);
			System.out.println(aktuellerCharakter.getName() + " blockt jetzt nicht mehr.");
		}

		// Der Charakter mit dem hoechsten Beweglichkeitswert wird zurueckgegeben.
		return aktuellerCharakter;
	}

	public void gegnerLogikFaehigkeitundZielGruppe() {
		String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
		ArrayList<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
		ArrayList<Feind> moeglicheFeinde = new ArrayList<>();
		ArrayList<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>();
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
				System.out.println(
						aktuellerCharakter.getName() + " kann nichts heilen, da alle lebenden Gegner 100% HP haben.");

				// Ziel-Gruppe aendert sich von eigener (Feind) zur SpielerCharakter-Gruppe
				zielGruppe.clear();
				for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
					zielGruppe.add(freundeDieNochLeben.get(counter));
				}

				// Alle Faehigkeiten die aufs eigene Team angewendet werden koennen fliegen raus
				// Alle Faehigkeiten die auf mehr Charaktere angewendet werden koennen als es
				// Ziele gibt fliegen raus
				for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
					if (eineFaehigkeit.getZielAnzahl() > zielGruppe.size() || eineFaehigkeit.isIstFreundlich()) {
						moeglicheFaehigkeiten.remove(eineFaehigkeit);
					}
				}

				// Faehigkeit wird aus dem moeglichen Pool zufaellig gewaehlt
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
					// zielWahl.add(zielGruppe.indexOf(aktuellesZielFeind));
					moeglicheFeinde.remove(aktuellesZielFeind);
					nochZuWaehlendeZiele--;
				}
			}
			if (faehigkeit == null) {
				faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
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
				faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
				nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();

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
					// zielWahl.add(zielGruppe.indexOf(aktuellesZielSpielerCharakter));
					moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
					nochZuWaehlendeZiele--;
				}
			}
			if (faehigkeit == null) {
				faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
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
			}
			break;
		}

		if (faehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte()) {
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
		}

		if (faehigkeit.getZielAnzahl() > zielGruppe.size()) {
			faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
		}

		gegnerFaehigkeit = faehigkeit;
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

	void faehigkeitBenutzen(Charakter charakterDerFaehigkeitBenutzt, ArrayList<Charakter> ziele,
			Faehigkeit faehigkeit) {
		boolean hatCharakterGenugMana = true;
		Faehigkeit eingesetzteFaehigkeit = faehigkeit;
		ArrayList<Charakter> zielGruppe = ziele;
		ArrayList<Charakter> zielWahl = new ArrayList<Charakter>(ziele);
		healWert = 0;
		schadensWerte.clear();

		// Faehigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
		// entsprechend durchgefuehrt
		aktuellerCharakter.setManaPunkte(aktuellerCharakter.getManaPunkte() - eingesetzteFaehigkeit.getManaKosten());
		System.out.println(eingesetzteFaehigkeit.getName() + " wird eingesetzt.");

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
					System.out.printf(
							Farbauswahl.RED + "KRITISCHER TREFFER! (Krit-Multiplikator: x%.2f)%n" + Farbauswahl.RESET,
							kritMultiplikator);
				}

				ergebnisWert = (int) Math.floor((eingesetzteFaehigkeit.getEffektStaerke() / basisSchadensWert)
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
					// gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);
						healWert = ergebnisWert;

						// Wenn der Verbuendete durch den Heal mehr HP haette als durch seine maxHP
						// moeglich, werden seine aktuellen HP gleich dem maxHP-Wert gesetzt
						if (betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
								.getMaxGesundheitsPunkte()) {
							healWert = ergebnisWert + (betroffenerCharakter.getMaxGesundheitsPunkte()
									- betroffenerCharakter.getGesundheitsPunkte());
							betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
						}
						System.out.println(Farbauswahl.RED + betroffenerCharakter.getName() + " wurde um "
								+ ergebnisWert + " geheilt!" + Farbauswahl.RESET);
					}
					// feindliches Team -> Schaden -> Verteidigung des Zieles muss beachtet werden
					// Wenn die Verteidigung des Zieles zu gross ist wird kein Schaden verursacht
					else {
						ergebnisWert -= betroffenerCharakterAbwehr;
						if (ergebnisWert < 1) {
							ergebnisWert = 1;
						}
						int tmpSchaden = 0;
						betroffenerCharakter
								.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);
						System.out.println(Farbauswahl.RED + betroffenerCharakter.getName() + " hat " + ergebnisWert
								+ " Schaden erlitten!" + Farbauswahl.RESET);
						tmpSchaden = ergebnisWert;

						// Wenn der toedliche Schaden dazu fuehrt, dass ein Charakter UNTER 0 HP faellt
						// werden die HP auf 0 gesetzt.

						if (betroffenerCharakter.getGesundheitsPunkte() <= 0) {
							System.out.println(Farbauswahl.RED + betroffenerCharakter.getName() + " ist gestorben."
									+ Farbauswahl.RESET);
//							tmpSchaden =  ergebnisWert + 
							betroffenerCharakter.setGesundheitsPunkte(0);
						}
					}
					break;
				case "maxGesundheitsPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
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
					int manaPunkteVorher = betroffenerCharakter.getManaPunkte();
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);

						// Wenn der Verbuendete durch den Buff mehr MP haette als durch seine maxMP
						// moeglich, werden seine aktuellen MP gleich dem maxMP-Wert gesetzt
						if (betroffenerCharakter.getManaPunkte() > betroffenerCharakter.getMaxManaPunkte()) {
							betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
						}
						System.out.println(Farbauswahl.RED + "Mana von " + betroffenerCharakter.getName() + " wurde um "
								+ (betroffenerCharakter.getManaPunkte() - manaPunkteVorher) + " aufgefuellt"
								+ Farbauswahl.RESET);
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
						System.out.println(Farbauswahl.RED + "Mana von " + betroffenerCharakter.getName() + " wurde um "
								+ (manaPunkteVorher - betroffenerCharakter.getManaPunkte()) + " verringert"
								+ Farbauswahl.RESET);
					}
					break;
				case "maxManaPunkte":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
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

						// Wenn Genauigkeit UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getGenauigkeit() < 0) {
							betroffenerCharakter.setGenauigkeit(0);
						}
					}
					break;
				case "verteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
					else {
						ergebnisWert -= betroffenerCharakter.getResistenz();
						if (ergebnisWert < 1) {
							ergebnisWert = 0;
						}
						betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - ergebnisWert);

						// Wenn Verteidigung UNTER 0 faellt wird sie auf 0 gesetzt
						if (betroffenerCharakter.getVerteidigung() < 0) {
							betroffenerCharakter.setVerteidigung(0);
						}
					}
					break;
				case "magischeVerteidigung":
					// gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter
								.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter.setGesundheitsRegeneration(
								betroffenerCharakter.getGesundheitsRegeneration() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
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
					if (eingesetzteFaehigkeit.isIstFreundlich()) {
						betroffenerCharakter
								.setManaRegeneration(betroffenerCharakter.getManaRegeneration() + ergebnisWert);
					}
					// feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
					// Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
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
				case "berserkerSpezial":
					// Berseker Spezialfaehigkeit
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

					aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getGesundheitsPunkte() - 10);
					System.out.println(
							aktuellerCharakter.getName() + " hat durch die Berserker-Faehigkeit 10 HP verloren");
					if (aktuellerCharakter.getGesundheitsPunkte() < 0) {
						aktuellerCharakter.setGesundheitsPunkte(0);
						System.out.println(
								Farbauswahl.RED + aktuellerCharakter.getName() + " ist gestorben." + Farbauswahl.RESET);
					}
					break;
				case "feuermagierSpezial":
					// Berseker Spezialfaehigkeit
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
						System.out.println(Farbauswahl.RED + betroffenerCharakter.getName() + " ist gestorben."
								+ Farbauswahl.RESET);
					}
					break;
				case "eismagierSpezial":
					// Berseker Spezialfaehigkeit
					if (betroffenerCharakter instanceof Feind) {
						if (feindeDieNochActionHaben.contains(betroffenerCharakter)) {
							System.out.println(Farbauswahl.RED + betroffenerCharakter.getName()
									+ " wurde eingefroren und kann diese Runde keine Action mehr durchfuehren."
									+ Farbauswahl.RESET);
							feindeDieNochActionHaben.remove(betroffenerCharakter);
						}
						else {
							System.out.println(Farbauswahl.RED + betroffenerCharakter.getName()
									+ " hat keine Action mehr. Faehigkeit ist fehlgeschlagen." + Farbauswahl.RESET);
						}
					}
					if (betroffenerCharakter instanceof SpielerCharakter) {
						if (freundeDieNochActionHaben.contains(betroffenerCharakter)) {
							System.out.println(Farbauswahl.RED + betroffenerCharakter.getName()
									+ " wurde eingefroren und kann diese Runde keine Action mehr durchfuehren."
									+ Farbauswahl.RESET);
							freundeDieNochActionHaben.remove(betroffenerCharakter);
						}
						else {
							System.out.println(Farbauswahl.RED + betroffenerCharakter.getName()
									+ " hat keine Action mehr. Faehigkeit ist fehlgeschlagen." + Farbauswahl.RESET);
						}
					}
					System.out.println(Farbauswahl.RED + aktuellerCharakter.getName()
							+ " hat die Eismagier-Faehigekit eingesetzt!" + Farbauswahl.RESET);
					break;
				case "rabaukeSpezial":
					// Berseker Spezialfaehigkeit
					aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() + 999999);
					aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() + 999999);
					System.out.println(Farbauswahl.RED + aktuellerCharakter.getName()
							+ " hat die Rabauken-Faehigekit eingesetzt und wird eine Runde unverwundbar!"
							+ Farbauswahl.RESET);
					break;
				case "paladinSpezial":
					// Berseker Spezialfaehigkeit
					aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() + 30);
					aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte());
					System.out.println(Farbauswahl.RED + aktuellerCharakter.getName()
							+ " hat die Paladin-Faehigekit eingesetzt! MaxHP um 30 erhoeht und voll geheilt!"
							+ Farbauswahl.RESET);
					break;
				case "priesterSpezial":
					// Berseker Spezialfaehigkeit
					if (betroffenerCharakter instanceof Feind) {
						for (Feind feind : feindeDieNochLeben) {
							feind.setPhysischeAttacke(feind.getPhysischeAttacke() + 5);
							feind.setMagischeAttacke(feind.getMagischeAttacke() + 5);
							feind.setVerteidigung(feind.getVerteidigung() + 5);
							feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + 5);
							feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + 5);
							feind.setManaRegeneration(feind.getManaRegeneration() + 5);
							feind.setGenauigkeit(feind.getGenauigkeit() + 3);
							feind.setBeweglichkeit(feind.getBeweglichkeit() + 3);
						}

					}
					else if (betroffenerCharakter instanceof SpielerCharakter) {
						for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
							spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + 5);
							spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + 5);
							spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + 5);
							spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + 5);
							spielerCharakter
									.setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + 5);
							spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + 5);
							spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + 3);
							spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + 3);
						}
					}
					System.out.println(Farbauswahl.RED + aktuellerCharakter.getName()
							+ " hat die Priester-Faehigekit eingesetzt! Statuswerte des Teams wurden erhoeht!"
							+ Farbauswahl.RESET);
					break;
				case "sanmausSpezial":
					// Berseker Spezialfaehigkeit
					betroffenerCharakter.setGesundheitsPunkte(
							(int) Math.floor((betroffenerCharakter.getMaxGesundheitsPunkte() * 0.7)));
					betroffenerCharakter
							.setManaPunkte((int) Math.floor((betroffenerCharakter.getMaxManaPunkte() * 0.5)));
					System.out.println(Farbauswahl.RED + aktuellerCharakter.getName()
							+ " hat die Sanmaus-Faehigekit eingesetzt! " + betroffenerCharakter.getName()
							+ " wurde wiederbelebt. HP auf 70% und MP auf 50% gesetzt." + Farbauswahl.RESET);
					break;
				}
				zielWahl.remove(0);
			}
		}
		// Die Faehigkeit hat nicht getroffen. Mana wurde trotzdem abgezogen und der Zug
		// des Charakters ist vorbei
		else {
			System.out.println(Farbauswahl.RED + eingesetzteFaehigkeit.getName()
					+ " ist daneben gegangen! (Trefferchance: "
					+ (int) ((0.65 + 0.02 * aktuellerCharakter.getGenauigkeit()) * 100) + "%)" + Farbauswahl.RESET);
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
	private void blocken() {
		aktuellerCharakter
				.setVerteidigung(aktuellerCharakter.getVerteidigung() + aktuellerCharakter.getPhysischeAttacke());
		aktuellerCharakter.setMagischeVerteidigung(
				aktuellerCharakter.getMagischeVerteidigung() + aktuellerCharakter.getMagischeAttacke());
		System.out.println(aktuellerCharakter.getName() + " faengt an zu blocken");
		System.out.println("Bis zu seinem naechsten Zug blockt er " + aktuellerCharakter.getPhysischeAttacke()
				+ " physischen und " + aktuellerCharakter.getMagischeAttacke() + " magischen Schaden.");
		blockendeCharaktere.add(aktuellerCharakter);
		aktualisiereZugreihenfolge();
	}

	/**
	 * Hier kann auf das Party-Verbrauchsgegenstandsinventar zugegriffen werden.
	 * Methoden sind alles ausgelagert.
	 *
	 *
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

		// 20% + 1.125% pro Beweglichkeitsvorteil der Gruppe (positive Differenz)
		if (nettoBeweglichkeit > 0) {
			fluchtchance += (nettoBeweglichkeit / 80.0);
		}
		if (fluchtchance > 1.0) {
			fluchtchance = 1.0;
		}
		fluchtchance = 0;
		if (random.nextDouble() < fluchtchance) {
			istKampfVorbei[0] = true;
		}
		else {
			// Flucht ist fehlgeschlagen, Aktion wird als ausgeführt betrachtet und
			aktualisiereZugreihenfolge();
		}
	}

	public void aktualisiereZugreihenfolge() {
		Charakter charakterDerAktionAusgefuehrtHat = aktuelleZugreihenfolge.get(0);
		aktuelleZugreihenfolge.remove(0);
		aktuelleZugreihenfolge.add(charakterDerAktionAusgefuehrtHat);
		for (Charakter charakter : new ArrayList<>(aktuelleZugreihenfolge)) {
			if (charakter.getGesundheitsPunkte() < 1) {
				aktuelleZugreihenfolge.remove(charakter);
			}
		}
		aktuellerCharakter = aktuelleZugreihenfolge.get(0);
	}

	/**
	 * Kampfende wird ausgewertet -> Exp wird verteilt Gold und Ressourcen werden
	 * verteilt Statistik wird gepflegt GameOver wird geprueft Endet in Hub oder
	 * GameOver
	 *
	 * @author Nick
	 * @since 04.12.2023
	 */
	public void kampfAuswerten() {
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
				kampfView.kampfErgebnis.appendText(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!\n");
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
							kampfView.kampfErgebnis.appendText(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n");
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
					kampfView.kampfErgebnis.appendText(feinde[0].getWaffe().getName() + " erhalten!\n");
				}
				if (ausruestungsArt == 2) {
					partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getRuestung());
					kampfView.kampfErgebnis.appendText(feinde[0].getRuestung().getName() + " erhalten!\n");
				}
				if (ausruestungsArt == 3) {
					if(feinde[0].getAccessoires()[0] != null){
					partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getAccessoires()[0]);
						kampfView.kampfErgebnis.appendText(feinde[0].getAccessoires()[0].getName() + " erhalten!\n");
					}
				}
			}
			Material material = Material.zufaelligeMaterialArt();
			partyController.materialHinzufuegen(material, ((int) Math.floor(partyController.getPartyLevel())));
			kampfView.kampfErgebnis.appendText(((int) Math.floor(partyController.getPartyLevel())) + "x "
					+ material.getClass().getSimpleName() + " erhalten.\nSie haben " + gewonnenesGold + " Gold erhalten.\n");
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
				kampfView.kampfErgebnis.appendText("Ihre ohnmaechtigen Charaktere wurden für "
						+ ((int) (Math.floor(partyController.getPartyLevel() * 2.5))) + " Gold wiederbelebt.\n");
			}
			else {
				if (gameController.isHardcore()) {
					// Verloren und ist Hardcore
					try {
						speicherstandController.entferneSpeicherstandHardcore(partyController);
					} catch (Exception e) {
						System.out.println("Loeschen des Speicherstandes auf 'Hardcore' fehlgeschlagen...");
					}
				}
				// Verloren und nicht genug Gold oder Hardcore = GameOver
				GameOver.gameOverAnzeigen(statistikController.getStatistik(), partyController, hauptmenuController);
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
							soeldner[i] = null;
							kampfView.kampfErgebnis.appendText(soeldner[i].getName() + "ist tot und hat die Party verlassen.\n");
						}
					}
				}
				party.setNebenCharakter(soeldner);
			}
			kampfView.kampfErgebnis.appendText("Flucht erfolgreich.\nFeigling!");
		}
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

	public void fuehreAktionDurch() {
		Charakter aktuellerCharakter = aktuelleZugreihenfolge.get(0);
		// Rabauke hat in seiner letzten Runde Spezialfaehigkeit eingesetzt und Abwehr
		// wird jetzt wieder normalisiert
		if (aktuellerCharakter.getVerteidigung() > 500000) {
			aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() - 999999);
			aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() - 999999);
			final Charakter name = aktuellerCharakter;
			Object mutex = new Object();
//				Platform.runLater(() -> {
			System.out.println("Unverwundbarkeit von " + name.getName() + " aufgehoben.");
//					synchronized (mutex) {
//						mutex.notify();
//					}
//				});
//				synchronized (mutex) {
//					try {
//						mutex.wait();
//					} catch (InterruptedException e) {
//					}
//
//				}
//			}

		}
		entferneToteCharaktereNachAction();
		if (feindeDieNochLeben.isEmpty() || freundeDieNochLeben.isEmpty()) {
			istKampfVorbei[0] = true;
		}
		if (!istKampfVorbei[0]) {
			System.out.println(aktuellerCharakter.getName() + " hat den Zug beendet.\n");
			System.out.print("'Eingabe' druecken, fuer den naechsten Zug.");
			ScannerHelfer.nextLine();
		}
		kampfView.updateKampfBildschirm();
		// kampf vorbei?
	}

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

	public void zurueckZumHub(){
		viewController.aktuelleNachHinten();
	}
}
