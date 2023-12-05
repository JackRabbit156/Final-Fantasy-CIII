package schmiede;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import hilfsklassen.TableViewFueller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import party.AusruestungsgegenstandInventar;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

import java.util.Comparator;

public class VerbessernView extends BorderPane {

        PartyController partyController;
        SchmiedeController schmiedeController;
        ObservableList<Waffe> waffenSpieler;
        ObservableList<Ruestung> ruestungsSpieler;
        ObservableList<Accessoire> accessoiresSpieler;

    public VerbessernView(PartyController partyController, SchmiedeController schmiedeController, ViewController viewController) {
        this.partyController = partyController;
        this.schmiedeController = schmiedeController;

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
        waffenSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneWaffen(partyController.getParty()));

        ruestungsSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung()
        );
        ruestungsSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneRuestung(partyController.getParty()));

        accessoiresSpieler = FXCollections.observableArrayList(
                partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore()
        );
        accessoiresSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneAccessiores(partyController.getParty()));


        // Verbessern Tab 1 - 5 erstellen und befüllen
            TableView<Waffe> waffenVerbessern = new TableView<>(waffenSpieler);
//            waffenVerbessern.
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
            VBox top = new VBox();
            top.setMinHeight(50);
            verbessernPane.getStyleClass().addAll("tabpaneschmiede");
            verbessernPane.setStyle("selected-tab-color: red");
            this.setTop(top);
            this.setCenter(verbessernPane);
    }

        void verbessernWaffenAnzeigeAktualisieren() {
            waffenSpieler.clear();
            waffenSpieler.removeAll();
            System.out.println(waffenSpieler.size());
            waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
            waffenSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneWaffen(partyController.getParty()));
            waffenSpieler.sort(Comparator.comparingInt(Ausruestungsgegenstand::getLevelAnforderung).reversed());
            waffenSpieler.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getClass().getSimpleName(),o2.getClass().getSimpleName()));
        }

        void verbessernRuestungAnzeigeAktualisieren() {
            ruestungsSpieler.clear();
            ruestungsSpieler.removeAll();
            ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
            ruestungsSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneRuestung(partyController.getParty()));
            ruestungsSpieler.sort(Comparator.comparingInt(Ausruestungsgegenstand::getLevelAnforderung).reversed());
            ruestungsSpieler.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getClass().getSimpleName(),o2.getClass().getSimpleName()));
        }
        void verbessernAccessoireAnzeigeAktualisieren() {
            accessoiresSpieler.clear();
            accessoiresSpieler.removeAll();
            accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
            accessoiresSpieler.addAll(AusruestungsgegenstandInventar.getGetrageneAccessiores(partyController.getParty()));
            accessoiresSpieler.sort(Comparator.comparingInt(Ausruestungsgegenstand::getLevelAnforderung).reversed());
        }
}

