package hauptmenu.gamecontroller;

import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;
import party.PartyController;

public class GameController {
	private StringProperty schwierigkeitsgrad;
	private BooleanProperty hardcore;
	private PartyController partyController;

	public GameController(boolean schwierigkeitsAuswahl, PartyController partyController) {
		if (schwierigkeitsAuswahl) {
			schwierigkeitsAuswahl();
		}
		else {
			this.hardcore = new SimpleBooleanProperty(false);
			this.schwierigkeitsgrad = new SimpleStringProperty( "Einfach");
		}
		this.partyController = partyController;
	}

	public GameController(String schwierigkeitsgrad, boolean hardcore, PartyController partyController) {
		this.schwierigkeitsgrad = new SimpleStringProperty(schwierigkeitsgrad);
		this.hardcore = new SimpleBooleanProperty(hardcore);
		this.partyController = partyController;
	}

	public String getSchwierigkeitsgrad() {
		return schwierigkeitsgrad.get();
	}

	public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
		this.schwierigkeitsgrad.set(schwierigkeitsgrad);
	}

	public boolean isHardcore() {
		return hardcore.get();
	}

	public void setHardcore(boolean hardcore) {
		this.hardcore.set(hardcore);
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
			schwierigkeitsgrad.set("Leicht");
			break;
		case 2:
			schwierigkeitsgrad.set("Mittel");
			break;
		case 3:
			schwierigkeitsgrad.set("Schwer");
			break;
		default:
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
			hardcore.set(false);
			KonsolenAssistent.clear();
			break;
		case 2:
			hardcore.set(true);
			KonsolenAssistent.clear();
			break;
		default:
			System.err.println("Falsche Eingabe!");
			schwierigkeitsAuswahl();
			KonsolenAssistent.clear();
			break;
		}
	}

	public BooleanProperty getHardcoreProperty(){
		return this.hardcore;
	}

	public StringProperty getSchwierigkeitsProperty(){
		return this.schwierigkeitsgrad;
	}
}
