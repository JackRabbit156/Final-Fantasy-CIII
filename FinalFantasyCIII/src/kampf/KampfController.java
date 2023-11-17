package kampf;

import java.util.ArrayList;
import java.util.List;

import charakter.controller.CharakterController;
import charakter.controller.FeindController;
import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import gamehub.GameHubController;
import hauptmenu.gamecontroller.GameController;
import hilfsklassen.ScannerHelfer;
import party.Party;
import party.PartyController;
import statistik.StatistikController;

public class KampfController {
	private FeindController feindController;
	private PartyController partyController;
	private StatistikController statistikController;
	private GameController gameController;
	private GameHubController gameHubController;

	public void kampfDurchfuehren(ArrayList<Charakter> initialeZugreihenfolge) {
		int runde;
		boolean istKampfVorbei;
		boolean istKampfVerloren;
		List<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
		List<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
		List<Feind> feindeDieNochLeben = new ArrayList<>();
		List<Feind> feindeDieNochActionHaben = new ArrayList<>();
		List<Charakter> zugreihenfolge = new ArrayList<>();
		Charakter naechsterCharakter;
		runde = 1;
		istKampfVorbei = false;
		istKampfVerloren = false;
		zugreihenfolge = initialeZugreihenfolge;
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
		naechsterCharakter = initialeZugreihenfolge.get(0);
	}

	public Charakter naechstenCharakterBestimmen(ArrayList<SpielerCharakter> freundeDieNochActionHaben,
			ArrayList<Feind> feindeDieNochActionHaben) {
		List<Charakter> alleCharakterDieNochActionHaben = new ArrayList<>();
		Charakter naechsterCharakter = null;
		for (int counter = 0; counter < 4; counter++) {
			if (freundeDieNochActionHaben.get(counter) != null) {
				alleCharakterDieNochActionHaben.add(freundeDieNochActionHaben.get(counter));
			}
			if (feindeDieNochActionHaben.get(counter) != null) {
				alleCharakterDieNochActionHaben.add(feindeDieNochActionHaben.get(counter));
			}
		}
		for (int counter = 0, len = alleCharakterDieNochActionHaben.size(); counter < len; counter++) {
			if (naechsterCharakter == null) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(counter);
			}
			else if (alleCharakterDieNochActionHaben.get(counter).getBeweglichkeit() > naechsterCharakter
					.getBeweglichkeit()) {
				naechsterCharakter = alleCharakterDieNochActionHaben.get(counter);
			}
		}
		return naechsterCharakter;
	}

	public void aktionWaehlen(Charakter aktuellerCharakter) {
		int input = 0;
		boolean gueltigeEingabe = true;
		System.out.println("Angreifen  (1)     Blocken (2)");
		System.out.println("Gegenstand (3)     Fliehen (4)");
		do {
			try {
				input = ScannerHelfer.nextInt();
			} catch (Exception e) {
				gueltigeEingabe = false;
				System.out.println("Eingabe nicht gueltig. Moeglichkeiten: 1 | 2 | 3 | 4");
			}
		} while (!gueltigeEingabe);
		switch (input) {
		case 1:
			angreifen(aktuellerCharakter);
			break;
		case 2:
			blocken(aktuellerCharakter);
			break;
		case 3:
			gegenstand(aktuellerCharakter);
			break;
		case 4:
			fliehen(aktuellerCharakter);
			break;
		}
	}

	public void angreifen(Charakter aktuellerCharakter) {
		int skillWahlAlsInt = 0;
		if (aktuellerCharakter instanceof SpielerCharakter) {
			System.out.println("Fähigkeiten:");
			for (int counter = 0, len = aktuellerCharakter.getFaehigkeiten().size(); counter < len; counter++) {
				System.out.println(counter + ". " + aktuellerCharakter.getFaehigkeiten().get(counter).getName());
			}
			while (0 > skillWahlAlsInt || skillWahlAlsInt == 0
					|| skillWahlAlsInt > aktuellerCharakter.getFaehigkeiten().size()) {
				System.out
						.println("Skill zwischen 1 und " + aktuellerCharakter.getFaehigkeiten().size() + " bestimmen:");
				skillWahlAlsInt = ScannerHelfer.nextInt();
			}
		}
	}

	public void blocken(Charakter aktuellerCharakter) {

	}

	public void gegenstand(Charakter aktuellerCharakter) {

	}

	public void fliehen(Charakter aktuellerCharakter) {

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
				// gameOverAnzeigen();
			}
		}

	}
}
