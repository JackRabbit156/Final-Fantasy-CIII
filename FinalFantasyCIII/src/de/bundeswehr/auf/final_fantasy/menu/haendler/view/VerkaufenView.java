package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.FXHelper;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.menu.haendler.controller.VerkaufenController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Map;
import java.util.stream.Collectors;

public class VerkaufenView extends BorderPane {

    private final ObservableList<Accessoire> accessoiresSpieler;
    private final Label hinweis = new Label();
    private final ObservableList<Map.Entry<Material, IntegerProperty>> materialSpieler;
    private final PartyController partyController;
    private final ObservableList<Ruestung> ruestungsSpieler;
    private final ObservableList<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandSpieler;
    private final ObservableList<Waffe> waffenSpieler;

    /**
     * Der Konstuktor der VerkaufenView
     *
     * @param partyController der aktuellen Sitzung
     * @param haendler        der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public VerkaufenView(PartyController partyController, Haendler haendler) {
        VerkaufenController verkaufen = new VerkaufenController(partyController, haendler);
        this.partyController = partyController;

        TabPane verkaufenPane = new TabPane();
        verkaufenPane.getStyleClass().addAll("tabpane-final-fantasy");
        verkaufenPane.setStyle("selected-tab-color: red");
        Tab verkaufenWaffeTab = new Tab("Waffen");
        verkaufenWaffeTab.setClosable(false);
        Tab verkaufenRuestungTab = new Tab("Rüstungen");
        verkaufenRuestungTab.setClosable(false);
        Tab verkaufenAccessoireTab = new Tab("Accessoires");
        verkaufenAccessoireTab.setClosable(false);
        Tab verkaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        verkaufenVerbrauchsgegenstaendeTab.setClosable(false);
        Tab verkaufenMaterialTab = new Tab("Material");
        verkaufenMaterialTab.setClosable(false);

        // Füllt den Inhalt der Verkaufstabellen
        Party party = partyController.getParty();
        waffenSpieler = FXCollections.observableArrayList(party.getAusruestungsgegenstandInventar().getInventarWaffen());
        ruestungsSpieler = FXCollections.observableArrayList(party.getAusruestungsgegenstandInventar().getInventarRuestung());
        accessoiresSpieler = FXCollections.observableArrayList(party.getAusruestungsgegenstandInventar().getInventarAccessoire());
        verbrauchsgegenstandSpieler = FXCollections.observableArrayList(party.getVerbrauchsgegenstaende().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet()));
        materialSpieler = FXCollections.observableArrayList(party.getMaterialien().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet()));

        // Befüllt die einzelnen Tabs mit (Waffe/Rüstung/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenVerkaufenTableView = new TableView<>(waffenSpieler);
        FXHelper.autoFitColumns(waffenVerkaufenTableView);
        waffenVerkaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Waffen zu verkaufen"));
        GuiHelper.waffenVerkaufenTabelle(waffenVerkaufenTableView);
        verkaufenWaffeTab.setContent(waffenVerkaufenTableView);
        waffenVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                verkaufen.waffe(waffenVerkaufenTableView.getSelectionModel().getSelectedItem(), waffe -> {
                    waffenSpieler.remove(waffe);
                    erfolgreich(waffe.getName() + " wurde für " + waffe.getVerkaufswert() + " Gold verkauft.");
                    return null;
                });
            }
        });
        TableView<Ruestung> ruestungVerkaufenTableView = new TableView<>(ruestungsSpieler);
        FXHelper.autoFitColumns(ruestungVerkaufenTableView);
        ruestungVerkaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Rüstungen zu verkaufen"));
        GuiHelper.ruestungVerkaufenTabelle(ruestungVerkaufenTableView);
        verkaufenRuestungTab.setContent(ruestungVerkaufenTableView);
        ruestungVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                verkaufen.ruestung(ruestungVerkaufenTableView.getSelectionModel().getSelectedItem(), ruestung -> {
                    ruestungsSpieler.remove(ruestung);
                    erfolgreich(ruestung.getName() + " wurde für " + ruestung.getVerkaufswert() + " Gold verkauft.");
                    return null;
                });
            }
        });
        TableView<Accessoire> accessoireVerkaufenTableView = new TableView<>(accessoiresSpieler);
        FXHelper.autoFitColumns(accessoireVerkaufenTableView);
        accessoireVerkaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Accessoires zu verkaufen"));
        GuiHelper.accessoireVerkaufenTabelle(accessoireVerkaufenTableView);
        verkaufenAccessoireTab.setContent(accessoireVerkaufenTableView);
        accessoireVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                verkaufen.accessoire(accessoireVerkaufenTableView.getSelectionModel().getSelectedItem(), accessoire -> {
                    accessoiresSpieler.remove(accessoire);
                    erfolgreich(accessoire.getName() + " wurde für " + accessoire.getVerkaufswert() + " Gold verkauft.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandVerkaufenTableView = new TableView<>(verbrauchsgegenstandSpieler);
        FXHelper.autoFitColumns(verbrauchsgegenstandVerkaufenTableView);
//        verbrauchsgegenstandSpieler.addListener((MapChangeListener<Verbrauchsgegenstand, IntegerProperty>) change -> update(verbrauchsgegenstandSpieler, change));
        verbrauchsgegenstandVerkaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Verbrauchsgegenstände zu verkaufen"));
        GuiHelper.verbrauchsgegenstaendeVerkaufenTabelle(verbrauchsgegenstandVerkaufenTableView);
        verkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandVerkaufenTableView);
        verbrauchsgegenstandVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                verkaufen.verbrauchsgegenstand(verbrauchsgegenstandVerkaufenTableView.getSelectionModel().getSelectedItem().getKey(), gegenstand -> {
                    verbrauchsgegenstandVerkaufenTableView.refresh();
                    erfolgreich(gegenstand.getName() + " wurde für " + gegenstand.getVerkaufswert() + " Gold verkauft.");
                    return null;
                }, gegenstand -> {
                    gescheitert(gegenstand.getName() + " konnte nicht verkauft werden.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Material, IntegerProperty>> materialVerkaufenTableView = new TableView<>(materialSpieler);
        FXHelper.autoFitColumns(materialVerkaufenTableView);
//        materialSpieler.addListener((MapChangeListener<Material, IntegerProperty>) change -> update(materialSpieler, change));
        materialVerkaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Materialien zu verkaufen"));
        verkaufenMaterialTab.setContent(materialVerkaufenTableView);
        GuiHelper.materialVerkaufenTabelle(materialVerkaufenTableView);
        materialVerkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialVerkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                verkaufen.material(materialVerkaufenTableView.getSelectionModel().getSelectedItem().getKey(), materialien -> {
                    materialVerkaufenTableView.refresh();
                    erfolgreich(materialien.getName() + " wurde für " + materialien.getVerkaufswert() + " Gold verkauft.");
                    return null;
                }, materialien -> {
                    gescheitert(materialien.getName() + " konnte nicht verkauft werden.");
                    return null;
                });
            }
        });

        //Fügt die Komponenten der Ansicht hinzu
        Label verkaufenText = new Label("Verkaufen: Wir kaufen Ihren Schrott!");
        verkaufenText.getStyleClass().add("haendler");
        verkaufenText.setWrapText(true);
        VBox top = new VBox(verkaufenText);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(50, 0, 20, 0));
        this.setTop(top);

        verkaufenPane.getTabs().addAll(verkaufenWaffeTab, verkaufenRuestungTab, verkaufenAccessoireTab, verkaufenVerbrauchsgegenstaendeTab, verkaufenMaterialTab);
        verkaufenPane.setMaxSize(1300, 450);
        this.setCenter(verkaufenPane);

        hinweis.setFont(Font.font(30));
        VBox bottom = new VBox(hinweis);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(0, 0, 50, 0));
        this.setBottom(bottom);

        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setPadding(new Insets(0, 384, 0, 0));
    }

    /**
     * aktualisiert die Tabellen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void aktualisieren() {
        hinweis.setText("");
        hinweis.getStyleClass().clear();

        // FIXME Hack
        waffenSpieler.clear();
        waffenSpieler.removeAll();
        waffenSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
        ruestungsSpieler.clear();
        ruestungsSpieler.removeAll();
        ruestungsSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
        accessoiresSpieler.clear();
        accessoiresSpieler.removeAll();
        accessoiresSpieler.addAll(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessoire());
        verbrauchsgegenstandSpieler.clear();
        verbrauchsgegenstandSpieler.addAll(FXCollections.observableArrayList(partyController.getParty().getVerbrauchsgegenstaende().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet())));
        materialSpieler.clear();
        materialSpieler.addAll(FXCollections.observableArrayList(partyController.getParty().getMaterialien().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet())));
    }

    private void erfolgreich(String text) {
        GuiHelper.erfolgreich(hinweis, text);
    }

    private void gescheitert(String text) {
        GuiHelper.gescheitert(hinweis, text);
    }

    private <K> void update(ObservableList<Map.Entry<K, IntegerProperty>> list, MapChangeListener.Change<? extends K, ? extends IntegerProperty> change) {
        System.out.println("update");
        boolean changed = false;
        for (Map.Entry<K, IntegerProperty> entry : list) {
            if (entry.getKey().equals(change.getKey())) {
                entry.setValue(change.getValueAdded());
                changed = true;
            }
        }
        if (!changed) {
            for (Map.Entry<? extends K, ? extends IntegerProperty> entry : change.getMap().entrySet()) {
                if (entry.getKey().equals(change.getKey())) {
                    list.add((Map.Entry<K, IntegerProperty>) entry);
                }
            }
        }
        System.out.println(list);
    }

}


