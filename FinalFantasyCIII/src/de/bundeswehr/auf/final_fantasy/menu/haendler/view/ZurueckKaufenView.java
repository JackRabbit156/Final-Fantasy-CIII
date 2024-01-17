package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.FXHelper;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.menu.haendler.controller.ZurueckKaufenController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
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

public class ZurueckKaufenView extends BorderPane {

    private final ObservableList<Accessoire> accessoiresHaendlerHistorie;
    private final Haendler haendler;
    private final Label hinweis = new Label();
    private final ObservableList<Map.Entry<Material, IntegerProperty>> materialHaendlerHistorie;
    private final ObservableList<Ruestung> ruestungsHaendlerHistorie;
    private final ObservableList<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandHaendlerHistorie;
    private final ObservableList<Waffe> waffenHaendlerHistorie;

    /**
     * Der Konstuktor der ZurueckKaufenView
     *
     * @param partyController der aktuellen Sitzung
     * @param haendler        der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public ZurueckKaufenView(PartyController partyController, Haendler haendler) {
        ZurueckKaufenController zurueckKaufen = new ZurueckKaufenController(partyController, haendler);
        this.haendler = haendler;

        TabPane zurueckkaufenPane = new TabPane();
        zurueckkaufenPane.getStyleClass().addAll("tabpane-final-fantasy");
        zurueckkaufenPane.setStyle("selected-tab-color: red");
        Tab zurueckkaufenWaffeTab = new Tab("Waffen");
        zurueckkaufenWaffeTab.setClosable(false);
        Tab zurueckkaufenRuestungTab = new Tab("Rüstungen");
        zurueckkaufenRuestungTab.setClosable(false);
        Tab zurueckkaufenAccessoireTab = new Tab("Accessoires");
        zurueckkaufenAccessoireTab.setClosable(false);
        Tab zurueckkaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        zurueckkaufenVerbrauchsgegenstaendeTab.setClosable(false);
        Tab zurueckkaufenMaterialTab = new Tab("Material");
        zurueckkaufenMaterialTab.setClosable(false);

        /// Füllt den Inhalt der ZurueckKaufentabellen
        waffenHaendlerHistorie = FXCollections.observableArrayList(haendler.getHistorieWaffe());
        ruestungsHaendlerHistorie = FXCollections.observableArrayList(haendler.getHistorieRuestung());
        accessoiresHaendlerHistorie = FXCollections.observableArrayList(haendler.getHistorieAccessoire());
        verbrauchsgegenstandHaendlerHistorie = FXCollections.observableArrayList(haendler.getHistorieVerbrauchsgegenstaende().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet()));
        materialHaendlerHistorie = FXCollections.observableArrayList(haendler.getHistorieMaterial().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet()));

        // Befüllt die einzelnen Tabs mit (Waffe/Rüstund/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenZurueckkaufenTableView = new TableView<>(waffenHaendlerHistorie);
        FXHelper.autoFitColumns(waffenZurueckkaufenTableView);
        waffenZurueckkaufenTableView.setPlaceholder(new PlaceHolder("Es wurden keine Waffen verkauft"));
        GuiHelper.waffenVerkaufenTabelle(waffenZurueckkaufenTableView);
        zurueckkaufenWaffeTab.setContent(waffenZurueckkaufenTableView);
        waffenZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                zurueckKaufen.waffe(waffenZurueckkaufenTableView.getSelectionModel().getSelectedItem(), waffe -> {
                    waffenHaendlerHistorie.remove(waffe);
                    erfolgreich(waffe.getName() + " wurde für " + waffe.getVerkaufswert() + " Gold gekauft.");
                    return null;
                }, waffe -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + waffe.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Ruestung> ruestungZurueckaufenTableView = new TableView<>(ruestungsHaendlerHistorie);
        FXHelper.autoFitColumns(ruestungZurueckaufenTableView);
        ruestungZurueckaufenTableView.setPlaceholder(new PlaceHolder("Es wurden keine Rüstungen verkauft"));
        GuiHelper.ruestungVerkaufenTabelle(ruestungZurueckaufenTableView);
        zurueckkaufenRuestungTab.setContent(ruestungZurueckaufenTableView);
        ruestungZurueckaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem() != null) {
                zurueckKaufen.ruestung(ruestungZurueckaufenTableView.getSelectionModel().getSelectedItem(), ruestung -> {
                    ruestungsHaendlerHistorie.remove(ruestung);
                    erfolgreich(ruestung.getName() + " wurde für " + ruestung.getVerkaufswert() + " Gold gekauft.");
                    return null;
                }, ruestung -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + ruestung.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Accessoire> accessoireZurueckkaufenTableView = new TableView<>(accessoiresHaendlerHistorie);
        FXHelper.autoFitColumns(accessoireZurueckkaufenTableView);
        accessoireZurueckkaufenTableView.setPlaceholder(new PlaceHolder("Es wurden keine Accessoires verkauft"));
        GuiHelper.accessoireVerkaufenTabelle(accessoireZurueckkaufenTableView);
        zurueckkaufenAccessoireTab.setContent(accessoireZurueckkaufenTableView);
        accessoireZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                zurueckKaufen.accessoire(accessoireZurueckkaufenTableView.getSelectionModel().getSelectedItem(), accessoire -> {
                    accessoiresHaendlerHistorie.remove(accessoire);
                    erfolgreich(accessoire.getName() + " wurde für " + accessoire.getVerkaufswert() + " Gold gekauft.");
                    return null;
                }, accessoire -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + accessoire.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandZurueckkaufenTableView = new TableView<>(verbrauchsgegenstandHaendlerHistorie);
        FXHelper.autoFitColumns(verbrauchsgegenstandZurueckkaufenTableView);
        verbrauchsgegenstandZurueckkaufenTableView.setPlaceholder(new PlaceHolder("Es wurden keine Verbrauchsgegenstände verkauft"));
        GuiHelper.verbrauchsgegenstaendeVerkaufenTabelle(verbrauchsgegenstandZurueckkaufenTableView);
        zurueckkaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandZurueckkaufenTableView);
        verbrauchsgegenstandZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                zurueckKaufen.verbrauchsgegenstand(verbrauchsgegenstandZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey(), gegenstand -> {
                    verbrauchsgegenstandZurueckkaufenTableView.refresh();
                    erfolgreich(gegenstand.getName() + " wurde für " + gegenstand.getVerkaufswert() + " Gold gekauft.");
                    return null;
                }, gegenstand -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + gegenstand.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Material, IntegerProperty>> materialZurueckkaufenTableView = new TableView<>(materialHaendlerHistorie);
        FXHelper.autoFitColumns(materialZurueckkaufenTableView);
        materialZurueckkaufenTableView.setPlaceholder(new PlaceHolder("Es wurde kein Material verkauft"));
        zurueckkaufenMaterialTab.setContent(materialZurueckkaufenTableView);
        GuiHelper.materialVerkaufenTabelle(materialZurueckkaufenTableView);
        materialZurueckkaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialZurueckkaufenTableView.getSelectionModel().getSelectedItem() != null) {
                zurueckKaufen.material(materialZurueckkaufenTableView.getSelectionModel().getSelectedItem().getKey(), material -> {
                    materialZurueckkaufenTableView.refresh();
                    erfolgreich(material.getName() + " wurde für " + material.getVerkaufswert() + " Gold gekauft.");
                    return null;
                }, material -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + material.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        //Fügt die Komponenten der Ansicht hinzu
        Label zurueckKaufenText = new Label("Zurückkaufen: Hier bekommen Sie Ihren Schrott wieder!");
        zurueckKaufenText.getStyleClass().add("haendler");
        zurueckKaufenText.setWrapText(true);
        VBox top = new VBox(zurueckKaufenText);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(50, 0, 20, 0));
        this.setTop(top);

        zurueckkaufenPane.getTabs().addAll(zurueckkaufenWaffeTab, zurueckkaufenRuestungTab, zurueckkaufenAccessoireTab, zurueckkaufenVerbrauchsgegenstaendeTab, zurueckkaufenMaterialTab);
        zurueckkaufenPane.setMaxSize(1300, 450);
        this.setCenter(zurueckkaufenPane);

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
     * aktualisiert die Tabelle der Waffen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void aktualisieren() {
        hinweis.setText("");
        hinweis.getStyleClass().clear();

        // FIXME Hack
        waffenHaendlerHistorie.clear();
        waffenHaendlerHistorie.addAll(haendler.getHistorieWaffe());
        ruestungsHaendlerHistorie.clear();
        ruestungsHaendlerHistorie.addAll(haendler.getHistorieRuestung());
        accessoiresHaendlerHistorie.clear();
        accessoiresHaendlerHistorie.addAll(haendler.getHistorieAccessoire());
        verbrauchsgegenstandHaendlerHistorie.clear();
        verbrauchsgegenstandHaendlerHistorie.addAll(FXCollections.observableArrayList(haendler.getHistorieVerbrauchsgegenstaende().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet())));
        materialHaendlerHistorie.clear();
        materialHaendlerHistorie.addAll(FXCollections.observableArrayList(haendler.getHistorieMaterial().entrySet().stream().filter(entry -> entry.getValue().get() > 0).collect(Collectors.toSet())));
    }

    private void erfolgreich(String text) {
        GuiHelper.erfolgreich(hinweis, text);
    }

    private void gescheitert(String text) {
        GuiHelper.gescheitert(hinweis, text);
    }

}



