package partystatus;

import charakter.controller.CharakterController;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.soeldner.Supporter;
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
import view.AnsichtsTyp;
import view.ViewController;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class PartyStatusController {
    private final PartyController partyController;
    private PartyStatusView partyStatusView;
    private ViewController viewController;

    public PartyStatusController(PartyController partyController, ViewController viewController) {
        this.partyController = partyController;
        partyStatusView = new PartyStatusView(this);
        this.viewController = viewController;
    }


    public void partyStatusAnzeigen() {
        partyStatusView.anzeigeAktualiseren();
        viewController.anmelden(partyStatusView, null, AnsichtsTyp.MIT_OVERLAY);
    }
    public SpielerCharakter[] getPartyMitglieder(){
        return partyController.getTeammitglieder();
    }

}
