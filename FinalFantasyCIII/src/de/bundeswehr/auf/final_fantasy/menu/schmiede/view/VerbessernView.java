package de.bundeswehr.auf.final_fantasy.menu.schmiede.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.final_fantasy.party.model.AusruestungsGegenstandInventar;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.SchmiedeController;

import java.util.Comparator;

public class VerbessernView extends BorderPane {

    private final PartyController partyController;
    private final SchmiedeController schmiedeController;
    private final ObservableList<Waffe> waffenSpieler;
    private final ObservableList<Ruestung> ruestungsSpieler;
    private final ObservableList<Accessoire> accessoiresSpieler;

    /**
     * Die Klasse VerbessernView stellt die Benutzeroberfläche für die Verbesserung von Ausrüstungsgegenständen dar.
     * Sie verwendet Tabs, um zwischen verschiedenen Kategorien von Ausrüstungsgegenständen (Waffen, Rüstungen, Accessoires) zu wechseln.
     * Jeder Tab enthält eine TableView, in der die Ausrüstungsgegenstände der entsprechenden Kategorie angezeigt werden.
     * Die Benutzer können durch Doppelklicken auf einen Ausrüstungsgegenstand die Aufrüstansicht öffnen, um diesen zu verbessern.
     * <p>
     * Die VerbessernView wird vom ViewController erstellt und angezeigt, um dem Benutzer die Verbesserung von Ausrüstungsgegenständen zu ermöglichen.
     *
     * @param partyController    Die Instanz des PartyControllers, der für die Verwaltung der Spielerparty und ihrer Ausrüstungsgegenstände zuständig ist.
     * @param schmiedeController Die Instanz des SchmiedeControllers, der für die Implementierung der Aufrüstfunktion verantwortlich ist.
     * @param viewController     Die Instanz des ViewControllers, der für die Anzeige und Steuerung der Benutzeroberfläche verantwortlich ist.
     * @author OF Stetter
     * @since 05.12.23
     */
    public VerbessernView(PartyController partyController, SchmiedeController schmiedeController, ViewController viewController) {
        this.partyController = partyController;
        this.schmiedeController = schmiedeController;

        // Erstellung der Tabs und TabPane
        TabPane verbessernPane = new TabPane();
        Tab verbessernWaffen = new Tab("Waffen");
        verbessernWaffen.setClosable(false);
        Tab verbessernRuestungen = new Tab("Rüstungen");
        verbessernRuestungen.setClosable(false);
        Tab verbessernAccessoires = new Tab("Accessoires");
        verbessernAccessoires.setClosable(false);

        // Verbessern Tabelle mit Inhalt füllen
        waffenSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen()
        );
        waffenSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneWaffen(partyController.getParty()));

        ruestungsSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung()
        );
        ruestungsSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneRuestung(partyController.getParty()));

        accessoiresSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore()
        );
        accessoiresSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneAccessiores(partyController.getParty()));


        // Verbessern Tab 1 - 3 erstellen und befüllen
        TableView<Waffe> waffenVerbessern = new TableView<>(waffenSpieler);
        SchmiedeView.waffenVerbessernTabelle(waffenVerbessern);
        verbessernWaffen.setContent(waffenVerbessern);
        waffenVerbessern.setOnMouseClicked(event -> {
            if (event.getClickCount() % 2 == 0 && waffenVerbessern.getSelectionModel().getSelectedItem() != null) {
                AufruestenView aufruestenView = new AufruestenView(waffenVerbessern.getSelectionModel().getSelectedItem(), schmiedeController, viewController, this,
                        partyController);
                viewController.anmelden(aufruestenView, schmiedeController.getSchmiedeMenuButtonsDisabled(), AnsichtsTyp.MIT_OVERLAY);
            }
        });
        TableView<Ruestung> ruestungVerbessern = new TableView<>(ruestungsSpieler);
        SchmiedeView.ruestungVerbessernTabelle(ruestungVerbessern);
        verbessernRuestungen.setContent(ruestungVerbessern);
        ruestungVerbessern.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungVerbessern.getSelectionModel().getSelectedItem() != null) {
                AufruestenView aufruestenView = new AufruestenView(ruestungVerbessern.getSelectionModel().getSelectedItem(), schmiedeController, viewController, this,
                        partyController);
                viewController.anmelden(aufruestenView, schmiedeController.getSchmiedeMenuButtonsDisabled(), AnsichtsTyp.MIT_OVERLAY);

            }
        });
        TableView<Accessoire> accessoireVerbessern = new TableView<>(accessoiresSpieler);
        SchmiedeView.accessoireVerbessernTabelle(accessoireVerbessern);
        verbessernAccessoires.setContent(accessoireVerbessern);
        accessoireVerbessern.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireVerbessern.getSelectionModel().getSelectedItem() != null) {
                AufruestenView aufruestenView = new AufruestenView(accessoireVerbessern.getSelectionModel().getSelectedItem(), schmiedeController, viewController, this,
                        partyController);
                viewController.anmelden(aufruestenView, schmiedeController.getSchmiedeMenuButtonsDisabled(), AnsichtsTyp.MIT_OVERLAY);
            }
        });
        verbessernPane.getTabs().addAll(verbessernWaffen, verbessernRuestungen, verbessernAccessoires);
        verbessernPane.getStyleClass().addAll("tabpane-final-fantasy");
        verbessernPane.setStyle("selected-tab-color: red");
        verbessernPane.setMaxSize(1300, 450);
        VBox top = new VBox();
        top.setMinHeight(50);
        this.setTop(top);
        this.setCenter(verbessernPane);
        setPadding(new Insets(0, 384, 0, 0));
    }

    /**
     * Aktualisiert die Anzeige der verbesserten Waffen in der SchmiedeView.
     * Dazu werden die ObservableList waffenSpieler geleert, mit den aktuellen Daten aktualisiert und anschließend sortiert.
     */
    public void verbessernWaffenAnzeigeAktualisieren() {
        waffenSpieler.clear();
        waffenSpieler.removeAll();
        System.out.println(waffenSpieler.size());
        waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
        waffenSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneWaffen(partyController.getParty()));
        waffenSpieler.sort(Comparator.comparingInt(AusruestungsGegenstand::getLevelAnforderung).reversed());
        waffenSpieler.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getClass().getSimpleName(), o2.getClass().getSimpleName()));
    }

    /**
     * Aktualisiert die Anzeige der verbesserten Rüstungen in der SchmiedeView.
     * Dazu werden die ObservableList ruestungsSpieler geleert, mit den aktuellen Daten aktualisiert und anschließend sortiert.
     */
    public void verbessernRuestungAnzeigeAktualisieren() {
        ruestungsSpieler.clear();
        ruestungsSpieler.removeAll();
        ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
        ruestungsSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneRuestung(partyController.getParty()));
        ruestungsSpieler.sort(Comparator.comparingInt(AusruestungsGegenstand::getLevelAnforderung).reversed());
        ruestungsSpieler.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getClass().getSimpleName(), o2.getClass().getSimpleName()));
    }

    /**
     * Aktualisiert die Anzeige der verbesserten Accessoires in der SchmiedeView.
     * Dazu werden die ObservableList accessoiresSpieler geleert, mit den aktuellen Daten aktualisiert und anschließend sortiert.
     */
    public void verbessernAccessoireAnzeigeAktualisieren() {
        accessoiresSpieler.clear();
        accessoiresSpieler.removeAll();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
        accessoiresSpieler.addAll(AusruestungsGegenstandInventar.getGetrageneAccessiores(partyController.getParty()));
        accessoiresSpieler.sort(Comparator.comparingInt(AusruestungsGegenstand::getLevelAnforderung).reversed());
    }

}

