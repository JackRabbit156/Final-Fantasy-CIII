package de.bundeswehr.auf.final_fantasy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

public class Game {

    public static final boolean debugModus = false;

    private StringProperty schwierigkeitsgrad;
    private BooleanProperty hardcore;
    private PartyController partyController;

    public Game(String schwierigkeitsgrad, boolean hardcore, PartyController partyController) {
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

    public BooleanProperty hardcoreProperty() {
        return this.hardcore;
    }

    public StringProperty schwierigkeitsgradProperty() {
        return this.schwierigkeitsgrad;
    }
}
