package hauptmenu.gamecontroller;

import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.PartyController;

public class GameController {
	private String schwierigkeitsgrad;
	private boolean hardcore;
	private PartyController partyController;

	public GameController(boolean schwierigkeitsAuswahl, PartyController partyController) {
		if (schwierigkeitsAuswahl) {
			schwierigkeitsAuswahl();
		}
		else {
			this.hardcore = false;
			this.schwierigkeitsgrad = "Einfach";
		}
		this.partyController = partyController;
	}

	public GameController(String schwierigkeitsgrad, boolean hardcore, PartyController partyController) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
		this.hardcore = hardcore;
		this.partyController = partyController;
	}

	public String getSchwierigkeitsgrad() {
		return schwierigkeitsgrad;
	}

	public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	public boolean isHardcore() {
		return hardcore;
	}

	public void setHardcore(boolean hardcore) {
		this.hardcore = hardcore;
	}

	public PartyController getPartyController() {
		return partyController;
	}

	public void setPartyController(PartyController partyController) {
		this.partyController = partyController;
	}

	/**
	 * @author Nick
	 * @since 15.11.2023
	 */
	public void schwierigkeitsAuswahl() {
		System.out.println("******Schwierigkeitsauswahl******");
		System.out.println("1: Leicht");
		System.out.println("2: Mittel");
		System.out.println("3: Schwer");
		int eingabe = ScannerHelfer.nextInt();
		switch (eingabe) {
		case 1:
			schwierigkeitsgrad = "Leicht";
			break;
		case 2:
			schwierigkeitsgrad = "Mittel";
			break;
		case 3:
			schwierigkeitsgrad = "Schwer";
			break;

		default:
			;
			schwierigkeitsAuswahl();
			break;
		}
		System.out.println("Schwierigkeitsgrad wurde eingestellt.");
		System.out.println("Waehlen Sie den Spielmodus");
		System.out.println("1: Normal");
		System.out.println("2: Hardcore");
		eingabe = ScannerHelfer.nextInt();
		switch (eingabe) {
		case 1:
			hardcore = false;
			KonsolenAssistent.clear();
			break;
		case 2:
			hardcore = true;
			KonsolenAssistent.clear();
			break;
		default:
			System.err.println("Falsche Eingabe!");
			schwierigkeitsAuswahl();
			KonsolenAssistent.clear();
			break;
		}
	}
}
