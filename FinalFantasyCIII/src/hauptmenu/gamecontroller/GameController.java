package hauptmenu.gamecontroller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import party.PartyController;

public class GameController {
	private StringProperty schwierigkeitsgrad;
	private BooleanProperty hardcore;
	private PartyController partyController;

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

	public BooleanProperty hardcoreProperty(){
		return this.hardcore;
	}

	public StringProperty schwierigkeitsgradProperty(){
		return this.schwierigkeitsgrad;
	}
}
