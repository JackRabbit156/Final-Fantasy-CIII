package partystatus;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.Farbauswahl;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;
import party.AusruestungsgegenstandInventar;
import party.PartyController;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class PartyStatusController {
    private final PartyController partyController;
    private int ausgewaehlteOption = 0;
    private String[] menuOption;
    private boolean menueaktive;
    private ArrayList<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;

    public PartyStatusController(PartyController partyController) {
        this.partyController = partyController;
    }


    public void partyStatusAnzeigen() {}
    public SpielerCharakter[] getPartyMitglieder(){
        return partyController.getTeammitglieder();
    }

}
