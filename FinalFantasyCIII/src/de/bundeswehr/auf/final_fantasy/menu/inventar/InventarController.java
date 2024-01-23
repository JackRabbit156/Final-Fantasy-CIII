package de.bundeswehr.auf.final_fantasy.menu.inventar;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.menu.inventar.view.InventarView;
import javafx.beans.property.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;

import java.util.ArrayList;
import java.util.List;

public class InventarController {

    private final List<Button> lstInventoryButtons;
    private final PartyController partyController;
    private final ViewController viewController;
    private final List<SpielerCharakter> aktiveParty;
    private SpielerCharakter ausgewaehlterChar;
    private final InventarView inventarView;
    private final AnsichtsTyp ansichtsTyp = AnsichtsTyp.MIT_OVERLAY;
    private final StringProperty aktiveView = new SimpleStringProperty();

    /**
     * Der InventarController ist für die Steuerung der Inventaransicht in der
     * grafischen Benutzeroberfläche verantwortlich. Er ermöglicht die Interaktion
     * mit dem Spielerinventar, die Anzeige von Inventarinformationen und die
     * Verwaltung von Verbrauchsgegenständen und Ausrüstung.
     *
     * Der Controller erhält Referenzen auf den PartyController und den
     * ViewController, um auf Partydaten zuzugreifen und die Benutzeroberfläche
     * zu steuern. Bei der Initialisierung werden die aktive Party, die InventarView,
     * und eine Liste von Buttons erstellt, um verschiedene Funktionen der
     * Inventaransicht zu steuern.
     *
     * Die erstellten Buttons (btnInventarOeffnen, btnBenutzenOeffnen,
     * btnAusruestungAendern, btnZuerueckZum) ermöglichen das Öffnen des
     * Inventars, die Anzeige von Verbrauchsgegenständen, das Ändern der Ausrüstung
     * und das Zurückkehren zu vorherigen Ansichten. Die entsprechenden Aktionen
     * werden durch Event-Handler ausgelöst.
     *
     * @param partyController Der PartyController zur Verwaltung der Spielpartei.
     * @param viewController Der ViewController zur Steuerung der Benutzeroberfläche.
     * @author Rode
     * @since 06.12.2023
     */
    public InventarController(PartyController partyController, ViewController viewController) {
        this.viewController = viewController;
        this.partyController = partyController;
        this.aktiveParty = fuellePartyList();
        this.inventarView = new InventarView(partyController, viewController);
        lstInventoryButtons = new ArrayList<>();

        Button btnInventarOeffnen = new Button("Inventar");
        btnInventarOeffnen.disableProperty().bind(aktiveView.isEqualTo("Inventar"));
        btnInventarOeffnen.setOnMouseClicked(event -> {
            aktiveView.set("Inventar");
            inventarView.inventarOeffnen();
        });

        Button btnBenutzenOeffnen = new Button("Verbrauchsgegenstände");
        btnBenutzenOeffnen.disableProperty().bind(aktiveView.isEqualTo("Verbrauchsgegenstände"));
        btnBenutzenOeffnen.setOnMouseClicked(event -> {
            aktiveView.set("Verbrauchsgegenstände");
            inventarView.verbrauchsGegenstaendeOeffnen();
        });

        Button btnAusruestungAendern = new Button("Ausrüstung");
        btnAusruestungAendern.disableProperty().bind(aktiveView.isEqualTo("Ausrüstung"));
        btnAusruestungAendern.setOnMouseClicked(event -> {
            aktiveView.set("Ausrüstung");
            inventarView.ausruestungAendern();
        });

        Button btnZuerueck = new Button("Zurück");
        btnZuerueck.setOnMouseClicked(event -> {
            ausgewaehlterChar = null;
            viewController.aktuelleNachHinten();
        });

        lstInventoryButtons.add(btnInventarOeffnen);
        lstInventoryButtons.add(btnBenutzenOeffnen);
        lstInventoryButtons.add(btnAusruestungAendern);
        lstInventoryButtons.add(btnZuerueck);
    }

    public SpielerCharakter getAusgewaehlterChar() {
        return ausgewaehlterChar;
    }

    private List<SpielerCharakter> fuellePartyList() {
        // TODO entfernen?
        List<SpielerCharakter> auffang = new ArrayList<>();

        auffang.add(partyController.getParty().getHauptCharakter());


        SpielerCharakter[] nebencharArray = this.partyController.getParty().getNebenCharaktere();
        for (SpielerCharakter nebencharakter : nebencharArray) {
            if (nebencharakter != null) {
                auffang.add(nebencharakter);
            }
        }
        return auffang;
    }

    /**
     * Die Methode spielerinventarAnzeige() aktualisiert die Anzeige des
     * Spielerinventars in der grafischen Benutzeroberfläche.
     *
     * Die Methode leert zunächst alle Kinderkomponenten der InventarView und setzt
     * ein Hintergrundbild für die Ansicht. Anschließend wird die InventarView zusammen
     * mit der Liste von Buttons (lstInventoryButtons) und dem aktuellen Ansichtstyp
     * beim ViewController angemeldet, um die Benutzeroberfläche entsprechend zu steuern.
     *
     * Diese Methode dient dazu, die Spielerinventaransicht zu aktualisieren und
     * sicherzustellen, dass die richtigen UI-Elemente angezeigt werden.
     *
     * @author Rode
     * @since 06.12.2023
     */
    public void spielerInventarAnzeige() {
        inventarView.getChildren().clear();
        inventarView.setBackground(new Background(new BackgroundImage(inventarView.getHintergrundBild(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1536, 1080, false, false, false, false))));
        aktiveView.set("Inventar");
        inventarView.inventarOeffnen();
        viewController.anmelden(inventarView, lstInventoryButtons, ansichtsTyp);
    }

}
