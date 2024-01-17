package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.FXHelper;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.menu.haendler.controller.KaufenController;
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

public class KaufenView extends BorderPane {

    private final ObservableList<Accessoire> accessoires;
    private final Haendler haendler;
    private final Label hinweis = new Label();
    private final ObservableList<Map.Entry<Material, IntegerProperty>> materialien;
    private final ObservableList<Ruestung> ruestungen;
    private final ObservableList<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstaende;
    private final ObservableList<Waffe> waffen;

    /**
     * Der Konstuktor der KaufView
     *
     * @param partyController der aktuellen Sitzung
     * @param haendler        der aktuellen Sitzung
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public KaufenView(PartyController partyController, Haendler haendler) {
        KaufenController kaufen = new KaufenController(partyController, haendler);
        this.haendler = haendler;

        TabPane kaufenPane = new TabPane();
        kaufenPane.getStyleClass().addAll("tabpane-final-fantasy");
        kaufenPane.setStyle("selected-tab-color: red");
        Tab kaufenWaffeTab = new Tab("Waffen");
        kaufenWaffeTab.setClosable(false);
        Tab kaufenRuestungTab = new Tab("Rüstungen");
        kaufenRuestungTab.setClosable(false);
        Tab kaufenAccessoireTab = new Tab("Accessoires");
        kaufenAccessoireTab.setClosable(false);
        Tab kaufenVerbrauchsgegenstaendeTab = new Tab("Verbrauchsgegenstände");
        kaufenVerbrauchsgegenstaendeTab.setClosable(false);
        Tab kaufenMaterialTab = new Tab("Material");
        kaufenMaterialTab.setClosable(false);

        // Füllt den Inhalt der Kauftabellen
        waffen = FXCollections.observableArrayList(haendler.getKaufInventar().getInventarWaffen());
        ruestungen = FXCollections.observableArrayList(haendler.getKaufInventar().getInventarRuestung());
        accessoires = FXCollections.observableArrayList(haendler.getKaufInventar().getInventarAccessiore());
        verbrauchsgegenstaende = FXCollections.observableArrayList(FXCollections.observableMap(haendler.getKaufVerbrauchsInventar()).entrySet());
        materialien = FXCollections.observableArrayList(FXCollections.observableMap(haendler.getKaufMaterialInventar()).entrySet());

        // Befüllt die einzelnen Tabs mit (Waffe/Rüstung/Accessoire/Verbrauchsgegenstand/Material)
        TableView<Waffe> waffenKaufenTableView = new TableView<>(waffen);
        FXHelper.autoFitColumns(waffenKaufenTableView);
        waffenKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Waffen zu kaufen"));
        GuiHelper.waffenKaufenTabelle(waffenKaufenTableView);
        kaufenWaffeTab.setContent(waffenKaufenTableView);
        waffenKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && waffenKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                kaufen.ausruestung(waffenKaufenTableView.getSelectionModel().getSelectedItem(), waffe -> {
                    waffen.remove(waffe);
                    erfolgreich(waffe.getName() + " wurde für " + waffe.getKaufwert() + " Gold gekauft.");
                    return null;
                }, waffe -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + waffe.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Ruestung> ruestungKaufenTableView = new TableView<>(ruestungen);
        FXHelper.autoFitColumns(ruestungKaufenTableView);
        ruestungKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Rüstungen zu kaufen"));
        GuiHelper.ruestungKaufenTabelle(ruestungKaufenTableView);
        kaufenRuestungTab.setContent(ruestungKaufenTableView);
        ruestungKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ruestungKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                kaufen.ausruestung(ruestungKaufenTableView.getSelectionModel().getSelectedItem(), ruestung -> {
                    ruestungen.remove(ruestung);
                    erfolgreich(ruestung.getName() + " wurde für " + ruestung.getKaufwert() + " Gold gekauft.");
                    return null;
                }, ruestung -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + ruestung.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Accessoire> accessoireKaufenTableView = new TableView<>(accessoires);
        FXHelper.autoFitColumns(accessoireKaufenTableView);
        accessoireKaufenTableView.setPlaceholder(new PlaceHolder("Es gibt keine Accessoires zu kaufen"));
        GuiHelper.accessoireKaufenTabelle(accessoireKaufenTableView);
        kaufenAccessoireTab.setContent(accessoireKaufenTableView);
        accessoireKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && accessoireKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                kaufen.ausruestung(accessoireKaufenTableView.getSelectionModel().getSelectedItem(), accessoire -> {
                    accessoires.remove(accessoire);
                    erfolgreich(accessoire.getName() + " wurde für " + accessoire.getKaufwert() + " Gold gekauft.");
                    return null;
                }, accessoire -> {
                    gescheitert("Sie haben nicht genug Gold, um '" + accessoire.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> verbrauchsgegenstandKaufenTableView = new TableView<>(verbrauchsgegenstaende);
        GuiHelper.verbrauchsgegenstaendeKaufenTabelle(verbrauchsgegenstandKaufenTableView);
        kaufenVerbrauchsgegenstaendeTab.setContent(verbrauchsgegenstandKaufenTableView);
        verbrauchsgegenstandKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                kaufen.verbrauchsgegenstand(verbrauchsgegenstandKaufenTableView.getSelectionModel().getSelectedItem().getKey(), trank -> {
                    verbrauchsgegenstandKaufenTableView.refresh();
                    erfolgreich(trank.getName() + " wurde für " + trank.getKaufwert() + " Gold gekauft.");
                    return null;
                }, trank -> {
                    gescheitert("Sie haben nicht genug Gold, um 1x '" + trank.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        TableView<Map.Entry<Material, IntegerProperty>> materialKaufenTableView = new TableView<>(materialien);
        kaufenMaterialTab.setContent(materialKaufenTableView);
        GuiHelper.materialKaufenTabelle(materialKaufenTableView);
        materialKaufenTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && materialKaufenTableView.getSelectionModel().getSelectedItem() != null) {
                kaufen.material(materialKaufenTableView.getSelectionModel().getSelectedItem().getKey(), material -> {
                    materialKaufenTableView.refresh();
                    erfolgreich(material.getName() + " wurde für " + material.getKaufwert() + " Gold gekauft.");
                    return null;
                }, material -> {
                    gescheitert("Sie haben nicht genug Gold, um 1x '" + material.getName() + "' zu kaufen.");
                    return null;
                });
            }
        });
        //Fügt die Komponenten der Ansicht hinzu
        Label kaufenText = new Label("Kaufen: Lieber Kunde! Bitte fassen Sie nur die Ware an die Sie kaufen möchten!");
        kaufenText.getStyleClass().add("haendler");
        VBox top = new VBox(kaufenText);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(50, 0, 20, 0));
        this.setTop(top);

        kaufenPane.getTabs().addAll(kaufenWaffeTab, kaufenRuestungTab, kaufenAccessoireTab, kaufenVerbrauchsgegenstaendeTab, kaufenMaterialTab);
        kaufenPane.setMaxSize(1300, 450);
        this.setCenter(kaufenPane);

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
     * Aktualisiert die Tabellen
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public void aktualisieren() {
        hinweis.setText("");
        hinweis.getStyleClass().clear();

        // FIXME Hack
        waffen.clear();
        waffen.addAll(haendler.getKaufInventar().getInventarWaffen());
        ruestungen.clear();
        ruestungen.addAll(haendler.getKaufInventar().getInventarRuestung());
        accessoires.clear();
        accessoires.addAll(haendler.getKaufInventar().getInventarAccessiore());
        verbrauchsgegenstaende.clear();
        verbrauchsgegenstaende.addAll(FXCollections.observableArrayList(FXCollections.observableMap(haendler.getKaufVerbrauchsInventar()).entrySet()));
        materialien.clear();
        materialien.addAll(FXCollections.observableArrayList(FXCollections.observableMap(haendler.getKaufMaterialInventar()).entrySet()));
    }

    private void erfolgreich(String text) {
        GuiHelper.erfolgreich(hinweis, text);
    }

    private void gescheitert(String text) {
        GuiHelper.gescheitert(hinweis, text);
    }


}


