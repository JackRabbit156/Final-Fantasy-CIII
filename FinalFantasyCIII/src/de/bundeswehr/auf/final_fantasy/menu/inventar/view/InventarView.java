package de.bundeswehr.auf.final_fantasy.menu.inventar.view;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.TableViewFueller;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class InventarView extends BorderPane {

    private ObservableList<Accessoire> accessoiresSpieler;
    private SpielerCharakter ausgewaehlterChar;
    private final Image hintergrundBild;
    private final ImageView hintergrundBildAnsicht;
    private final PartyController partyController;
    private ObservableList<Ruestung> ruestungsSpieler;
    private final ViewController viewController;
    private ObservableList<Waffe> waffenSpieler;

    /**
     * Der Konstruktor initialisiert die Instanz, leert zunächst alle Kinderkomponenten,
     * setzt die übergebenen Controller und die aktive Party, lädt ein Hintergrundbild,
     * aktualisiert die Observable listen durch Aufruf der refresh-Methode
     * und fügt schließlich die Hintergrundansicht zum Layout hinzu. Die Mindestbreite
     * und -höhe des Layouts werden auf 1536 x 1080 gesetzt.
     *
     * @param partyController Der PartyController zur Verwaltung der Chars und der Ausrüstung um Zugriff zu erhalten auf alle dinge innerhalb der de.bundeswehr.auf.final_fantasy.party.
     * @param viewController  Der ViewController zur Steuerung der Benutzeroberfläche and zum refresh oder neuaufbau.
     * @author Rode
     * @since 06.12.2023
     */
    public InventarView(PartyController partyController, ViewController viewController) {
        this.partyController = partyController;
        this.viewController = viewController;
        hintergrundBild = new Image("background/inventoryBG.png");
        hintergrundBildAnsicht = new ImageView(hintergrundBild);
        refresh(partyController);

        getChildren().add(hintergrundBildAnsicht);
        setMinSize(1536, 1080);
    }

    private static void accessoirefuellenTabelle(TableView<Accessoire> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableViewFueller.maxManaPunkteAccFuellen(tabelle);
        TableViewFueller.beweglichkeitAccFuellen(tabelle);
        TableViewFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableViewFueller.manaRegenerationAccFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    private static void ruestungfuellenTabelle(TableView<Ruestung> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.ruestungsTypFuellen(tabelle);
        TableViewFueller.verteidigungFuellen(tabelle);
        TableViewFueller.magischeVerteidigungFuellen(tabelle);
        TableViewFueller.resistenzFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    private static void waffenbefuellenTabelle(TableView<Waffe> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.waffenTypFuellen(tabelle);
        TableViewFueller.attakeFuellen(tabelle);
        TableViewFueller.magischeAttakeFuellen(tabelle);
        TableViewFueller.genauigkeitWaffeFuellen(tabelle);
        TableViewFueller.beweglichkeitWaffeFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    /**
     * Die Methode ausruestungAendern() öffnet die Ansicht zur Änderung der
     * Ausrüstung für einen ausgewählten Spielercharakter in der grafischen
     * Benutzeroberfläche.
     * <p>
     * Die Methode aktualisiert zuerst das Inventar durch Aufruf der refresh-Methode
     * und leert anschließend alle Kinderkomponenten des Layouts.
     * Ein Hintergrundbild wird hinzugefügt, und die Mindestbreite und -höhe des Layouts
     * werden auf 1536 x 1080 festgelegt. Es werden verschiedene UI-Elemente wie Buttons,
     * TextFields und Tabs erstellt, um die Auswahl und Änderung der Ausrüstung zu ermöglichen.
     * <p>
     * Tabellen für Waffen, Rüstungen und Accessoires werden erstellt und mit den
     * entsprechenden Daten befüllt. Die Tabellen werden in Tabs platziert, die einem
     * TabPane hinzugefügt werden. Für jeden Spielercharakter in der Party wird ein Button
     * erstellt, um den Spielercharakter auszuwählen und die Ausrüstung zu ändern.
     * <p>
     * Die Methode enthält auch Logik zum Wechseln zwischen Tabs, zur Auswahl und Änderung
     * der Ausrüstung sowie zur Aktualisierung von UI-Elementen.
     *
     * @author Rode
     * @since 06.12.2023
     */
    public void ausruestungAendern() {
        refresh(partyController);
        setAusgewaehlterChar(null);
        getChildren().clear();
        AtomicInteger accessoirezahler = new AtomicInteger();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
        HBox ansichtAusrustung = new HBox();
        VBox charUndRuestung = new VBox();
        HBox hbwaffenButton = new HBox();
        HBox hbruestungButton = new HBox();
        HBox hbacc1Button = new HBox();
        HBox hbacc2Button = new HBox();
        HBox hbacc3Button = new HBox();
        Button btnWaffe = new Button("Waffe");
        TextField waffetxtf = new TextField();
        Button btnRuestung = new Button("Rüstung");
        TextField ruestungtxtf = new TextField();
        Button btnAcc1 = new Button("Accessoire");
        TextField acc1txtf = new TextField();
        Button btnAcc2 = new Button("Accessoire");
        TextField acc2txtf = new TextField();
        Button btnAcc3 = new Button("Accessoire");
        TextField acc3txtf = new TextField();

        TabPane tpInventarListe = new TabPane();
        tpInventarListe.getStyleClass().add("tabpaneschmiede");
        Tab tbWaffe = new Tab("Waffen");
        tbWaffe.setClosable(false);
        tbWaffe.setDisable(true);
        Tab tbRuestung = new Tab("Rüstung");
        tbRuestung.setClosable(false);
        tbRuestung.setDisable(true);
        Tab tbAccessoire = new Tab("Accessoire");
        tbAccessoire.setClosable(false);
        tbAccessoire.setDisable(true);

        TableView<Waffe> waffenAnzeigen = new TableView<>(waffenSpieler);
        waffenbefuellenTabelle(waffenAnzeigen);
        tbWaffe.setContent(waffenAnzeigen);
        waffenAnzeigen.setPlaceholder(new PlaceHolder("Leider haben sie momentan keine Ausrüstbaren Waffen"));
        waffenAnzeigen.setOnMouseClicked(event2 -> {
            if (event2.getClickCount() == 2) {
                if (waffenAnzeigen.hasProperties()) {
                    Waffe kleinwaffe = waffenAnzeigen.getSelectionModel().getSelectedItem();
                    SpielerCharakter ausgewaehlterChar = getAusgewaehlterChar();
                    if (CharakterController.charakterDarfTragen(ausgewaehlterChar, kleinwaffe)) {
                        if (!ausgewaehlterChar.getWaffe().isIstSoeldnerItem()) {
                            waffenSpieler.add(ausgewaehlterChar.getWaffe());
                        }
                        CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehlterChar.getWaffe(), partyController.getParty().getAusruestungsgegenstandInventar());
                        CharakterController.ausruestungAnlegen(ausgewaehlterChar, kleinwaffe, partyController.getParty().getAusruestungsgegenstandInventar());
                        waffenSpieler.remove(kleinwaffe);
                        btnWaffe.setText(ausgewaehlterChar.getWaffe().getName());
                        btnWaffe.setGraphic(new ImageView(new Image(ausgewaehlterChar.getWaffe().getIcon())));
                        refreshButtonInfo(waffetxtf, ruestungtxtf, acc1txtf, acc2txtf, acc3txtf, ausgewaehlterChar);
                        viewController.aktualisiereCharListe();

                    }
                }
            }
        });


        TableView<Ruestung> ruestungAnzeigen = new TableView<>(ruestungsSpieler);
        ruestungfuellenTabelle(ruestungAnzeigen);
        tbRuestung.setContent(ruestungAnzeigen);
        ruestungAnzeigen.setPlaceholder(new PlaceHolder("Leider haben sie momentan keine Ausrüstbaren Rüstungen"));
        ruestungAnzeigen.setOnMouseClicked(event2 -> {
            if (event2.getClickCount() == 2) {
                if (ruestungAnzeigen.hasProperties()) {
                    Ruestung kleinruestung = ruestungAnzeigen.getSelectionModel().getSelectedItem();
                    SpielerCharakter ausgewaehlterChar = getAusgewaehlterChar();
                    if (CharakterController.charakterDarfTragen(ausgewaehlterChar, kleinruestung)) {
                        if (!ausgewaehlterChar.getRuestung().isIstSoeldnerItem()) {
                            ruestungsSpieler.add(ausgewaehlterChar.getRuestung());
                        }
                        CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehlterChar.getRuestung(), partyController.getParty().getAusruestungsgegenstandInventar());
                        CharakterController.ausruestungAnlegen(ausgewaehlterChar, kleinruestung, partyController.getParty().getAusruestungsgegenstandInventar());
                        ruestungsSpieler.remove(kleinruestung);
                        btnRuestung.setText(ausgewaehlterChar.getRuestung().getName());
                        btnRuestung.setGraphic(new ImageView(new Image(ausgewaehlterChar.getRuestung().getIcon())));
                        refreshButtonInfo(waffetxtf, ruestungtxtf, acc1txtf, acc2txtf, acc3txtf, ausgewaehlterChar);
                        viewController.aktualisiereCharListe();

                    }
                }
            }
        });

        TableView<Accessoire> accessoiresAnzeigen = new TableView<>(accessoiresSpieler);
        accessoirefuellenTabelle(accessoiresAnzeigen);
        tbAccessoire.setContent(accessoiresAnzeigen);
        accessoiresAnzeigen.setPlaceholder(new PlaceHolder("Leider haben sie momentan keine Accessoires"));
        accessoiresAnzeigen.setOnMouseClicked(event2 -> {
            if (event2.getClickCount() == 2) {
                if (accessoiresAnzeigen.hasProperties()) {
                    Accessoire kleinesAccessoire = accessoiresAnzeigen.getSelectionModel().getSelectedItem();
                    SpielerCharakter ausgewaehlterChar = getAusgewaehlterChar();
                    if (CharakterController.charakterDarfTragen(ausgewaehlterChar, kleinesAccessoire)) {

                        if (!ausgewaehlterChar.getAccessoires()[accessoirezahler.get()].isIstSoeldnerItem()) {
                            accessoiresSpieler.add(ausgewaehlterChar.getAccessoire(accessoirezahler.get()));
                        }
                        CharakterController.ausruestungAusziehen(ausgewaehlterChar, ausgewaehlterChar.getAccessoire(accessoirezahler.get()), partyController.getParty().getAusruestungsgegenstandInventar());
                        CharakterController.ausruestungAnlegen(ausgewaehlterChar, kleinesAccessoire, partyController.getParty().getAusruestungsgegenstandInventar());
                        accessoiresSpieler.remove(kleinesAccessoire);
                        btnAcc1.setText(ausgewaehlterChar.getAccessoire(0).getName());
                        btnAcc1.setGraphic(new ImageView(new Image(ausgewaehlterChar.getAccessoire(0).getIcon())));
                        btnAcc2.setText(ausgewaehlterChar.getAccessoire(1).getName());
                        btnAcc2.setGraphic(new ImageView(new Image(ausgewaehlterChar.getAccessoire(1).getIcon())));
                        btnAcc3.setText(ausgewaehlterChar.getAccessoire(2).getName());
                        btnAcc3.setGraphic(new ImageView(new Image(ausgewaehlterChar.getAccessoire(2).getIcon())));
                        refreshButtonInfo(waffetxtf, ruestungtxtf, acc1txtf, acc2txtf, acc3txtf, ausgewaehlterChar);
                        viewController.aktualisiereCharListe();
                    }

                }

            }

        });

        tpInventarListe.getTabs().addAll(tbWaffe, tbRuestung, tbAccessoire);

        for (SpielerCharakter spielerCharakter : partyController.getTeammitglieder()) {
            if (spielerCharakter != null) {
                Button charButton = new Button(spielerCharakter.getName());
                charButton.setGraphic(new ImageView(new Image(spielerCharakter.getGrafischeDarstellung(), 80, 80, false, true)));
                charUndRuestung.getChildren().add(charButton);
                charButton.getStyleClass().add("charboxButton");
                charButton.setOnAction(event -> {
                    this.setAusgewaehlterChar(spielerCharakter);
                    btnWaffe.setText(spielerCharakter.getWaffe().getName());
                    btnWaffe.setGraphic(new ImageView(new Image(spielerCharakter.getWaffe().getIcon())));
                    btnWaffe.setOnAction(event1 -> {
                        tbWaffe.setDisable(false);
                        tpInventarListe.getSelectionModel().select(tbWaffe);
                        tbRuestung.setDisable(true);
                        tbAccessoire.setDisable(true);

                    });
                    btnRuestung.setText(spielerCharakter.getRuestung().getName());
                    btnRuestung.setGraphic(new ImageView(new Image(spielerCharakter.getRuestung().getIcon())));
                    btnRuestung.setOnAction(event1 -> {
                        tbWaffe.setDisable(true);
                        tbRuestung.setDisable(false);
                        tpInventarListe.getSelectionModel().select(tbRuestung);
                        tbAccessoire.setDisable(true);
                    });
                    btnAcc1.setText(spielerCharakter.getAccessoire(0).getName());
                    btnAcc1.setGraphic(new ImageView(new Image(spielerCharakter.getAccessoire(0).getIcon())));
                    btnAcc1.setOnAction(event1 -> {
                        tbWaffe.setDisable(true);
                        tbRuestung.setDisable(true);
                        tbAccessoire.setDisable(false);
                        tpInventarListe.getSelectionModel().select(tbAccessoire);
                        accessoirezahler.set(0);
                    });
                    btnAcc2.setText(spielerCharakter.getAccessoire(1).getName());
                    btnAcc2.setGraphic(new ImageView(new Image(spielerCharakter.getAccessoire(1).getIcon())));
                    btnAcc2.setOnAction(event1 -> {
                        tbWaffe.setDisable(true);
                        tbRuestung.setDisable(true);
                        tbAccessoire.setDisable(false);
                        tpInventarListe.getSelectionModel().select(tbAccessoire);
                        accessoirezahler.set(1);
                    });
                    btnAcc3.setText(spielerCharakter.getAccessoire(2).getName());
                    btnAcc3.setGraphic(new ImageView(new Image(spielerCharakter.getAccessoire(2).getIcon())));
                    btnAcc3.setOnAction(event1 -> {
                        tbWaffe.setDisable(true);
                        tbRuestung.setDisable(true);
                        tbAccessoire.setDisable(false);
                        tpInventarListe.getSelectionModel().select(tbAccessoire);
                        accessoirezahler.set(2);
                    });
                    refreshButtonInfo(waffetxtf, ruestungtxtf, acc1txtf, acc2txtf, acc3txtf, ausgewaehlterChar);
                });
            }
        }

        hbwaffenButton.getChildren().addAll(btnWaffe, waffetxtf);
        hbruestungButton.getChildren().addAll(btnRuestung, ruestungtxtf);
        hbacc1Button.getChildren().addAll(btnAcc1, acc1txtf);
        hbacc2Button.getChildren().addAll(btnAcc2, acc2txtf);
        hbacc3Button.getChildren().addAll(btnAcc3, acc3txtf);

        charUndRuestung.getChildren().addAll(hbwaffenButton, hbruestungButton, hbacc1Button, hbacc2Button, hbacc3Button);

        for (Node node : charUndRuestung.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                hbox.getStyleClass().add("itemauswahl-box-aendern");
                hbox.setPadding(new Insets(5, 0, 2, 0));
                for (Node node2 : hbox.getChildren()) {
                    if (node2 instanceof TextField) {
                        TextField txtfield = (TextField) node2;
                        txtfield.setMinWidth(580);
                        txtfield.setMaxWidth(580);
                        txtfield.setMaxHeight(50);
                        txtfield.setMinHeight(50);
                        txtfield.setEditable(false);
                    }
                    if (node2 instanceof Button) {
                        Button button = (Button) node2;
                        button.maxHeight(50);
                        button.minHeight(50);
                    }
                }
            }
        }

        VBox top = new VBox();
        top.setMinHeight(40);
        charUndRuestung.setMinWidth(760);
        setTop(top);
        ansichtAusrustung.getChildren().addAll(charUndRuestung, tpInventarListe);
        this.setCenter(ansichtAusrustung);
    }

    /**
     * Die Methode entferneCssVonAllenButtons(VBox vbox) entfernt sämtliche
     * CSS-Stile von Buttons innerhalb einer VBox und setzt den Standardstil
     * ("charboxButton") für jeden Button neu.
     * <p>
     * Dies ermöglicht das Zurücksetzen aller Stile von Buttons in einer bestimmten
     * Containerstruktur, insbesondere von HBox-Elementen in einer VBox. Die Methode
     * durchläuft die Kinder der übergebenen VBox und prüft, ob es sich um HBox- oder
     * Button-Elemente handelt. Für jeden Button wird die Liste der vorhandenen
     * Stile gelöscht und der Standardstil "charboxButton" hinzugefügt. Dadurch werden
     * vorherige Stile entfernt und die ursprüngliche Darstellung wiederhergestellt.
     *
     * @param vbox Die VBox, deren Kinder auf Buttons und HBoxen überprüft werden.
     * @author Rode
     * @since 06.12.2023
     */
    public void entferneCssVonAllenButtons(VBox vbox) {
        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                for (Node childNode : hbox.getChildren()) {
                    if (childNode instanceof Button) {
                        Button button = (Button) childNode;
                        button.getStyleClass().clear();
                        button.getStyleClass().add("charboxButton");
                    }
                }
            }
            else if (node instanceof Button) {
                Button button = (Button) node;
                button.getStyleClass().clear();
                button.getStyleClass().add("charboxButton");
            }
        }
    }

    public SpielerCharakter getAusgewaehlterChar() {
        return ausgewaehlterChar;
    }

    public Image getHintergrundBild() {
        return hintergrundBild;
    }

    /**
     * Die Methode inventarOeffnen() öffnet die Ansicht des Spielerinventars
     * in der grafischen Benutzeroberfläche.
     * <p>
     * Die Methode aktualisiert zuerst das Inventar durch Aufruf der refresh-Methode
     * und leert anschließend alle Kinderkomponenten des Layouts.
     * Es wird ein Hintergrundbild zur Ansicht hinzugefügt, und die Mindestbreite und
     * -höhe des Layouts werden auf 1536 x 1080 festgelegt.
     * <p>
     * Dann werden Tabs für Waffen, Rüstungen und Accessoires erstellt, jeweils mit
     * zugehörigen Tabellen für die Anzeige der entsprechenden Inventarobjekte.
     * Die Tabellen werden mit den entsprechenden Daten befüllt. Diese Tabs werden
     * einem TabPane hinzugefügt, der wiederum dem Zentrum des Layouts zugewiesen wird.
     * <p>
     * Ein VBox-Container wird erstellt und als oberste Komponente (Top) des Layouts
     * gesetzt, um nicht in das obere menu reinzuglitchen
     *
     * @author Rode
     * @since 06.12.2023
     */
    public void inventarOeffnen() {
        refresh(partyController);
        getChildren().clear();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
        TabPane tpInventarListe = new TabPane();
        tpInventarListe.getStyleClass().add("tabpaneschmiede");
        Tab tbWaffe = new Tab("Waffen");
        tbWaffe.setClosable(false);
        Tab tbRuestung = new Tab("Rüstung");
        tbRuestung.setClosable(false);
        Tab tbAccessoire = new Tab("Accessoire");
        tbAccessoire.setClosable(false);

        TableView<Waffe> waffenAnzeigen = new TableView<>(waffenSpieler);
        waffenAnzeigen.setPlaceholder(new PlaceHolder("Es befinden sich keine Waffen im Inventar"));
        waffenAnzeigen.setMinHeight(450);
        waffenbefuellenTabelle(waffenAnzeigen);
        tbWaffe.setContent(waffenAnzeigen);

        TableView<Ruestung> ruestungAnzeigen = new TableView<>(ruestungsSpieler);
        ruestungAnzeigen.setPlaceholder(new PlaceHolder("Es befinden sich keine Rüstungen im Inventar"));
        ruestungAnzeigen.setMinHeight(450);
        ruestungfuellenTabelle(ruestungAnzeigen);
        tbRuestung.setContent(ruestungAnzeigen);

        TableView<Accessoire> accessoiresAnzeigen = new TableView<>(accessoiresSpieler);
        accessoiresAnzeigen.setPlaceholder(new PlaceHolder("Es befinden sich keine Accessoires im Inventar"));
        accessoiresAnzeigen.setMinHeight(450);
        accessoirefuellenTabelle(accessoiresAnzeigen);
        tbAccessoire.setContent(accessoiresAnzeigen);

        tpInventarListe.getTabs().addAll(tbWaffe, tbRuestung, tbAccessoire);
        tpInventarListe.setMaxSize(1300, 450);
        VBox top = new VBox();
        top.setMinHeight(40);
        this.setTop(top);
        this.setCenter(tpInventarListe);
    }

    private void refresh(PartyController partyController) {
        this.waffenSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarWaffen());
        this.accessoiresSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarAccessiore());
        this.ruestungsSpieler = FXCollections.observableArrayList(partyController.getParty().getAusruestungsgegenstandInventar().getInventarRuestung());
    }

    public void setAusgewaehlterChar(SpielerCharakter ausgewaehlterChar) {
        this.ausgewaehlterChar = ausgewaehlterChar;
    }

    /**
     * Die Methode verbrauchsGegenstaendeOeffnen() öffnet die Ansicht zur Verwaltung
     * von Verbrauchsgegenständen in der grafischen Benutzeroberfläche.
     * <p>
     * Die Methode setzt zuerst den ausgewählten Charakter auf null und leert alle
     * Kinderkomponenten des Layouts. Anschließend wird ein Hintergrundbild hinzugefügt,
     * und die Mindestbreite und -höhe des Layouts werden auf 1536 x 1080 festgelegt.
     * <p>
     * Die Ansicht wird in zwei Bereiche unterteilt: einen Bereich für die Auswahl
     * der Charaktere und einen Bereich für die Auswahl von Verbrauchsgegenständen.
     * Für jeden Spielercharakter in der Party wird eine OverlayPartyMenueInventar-
     * Komponente erstellt und in den Charakterbereich eingefügt. Der Verbrauchsgegenstand-
     * Bereich enthält eine OverlayPartyMenueInventar-Komponente sowie spezifische Logik
     * zur Verwaltung von Verbrauchsgegenständen.
     * <p>
     * Das Layout wird abschließend konfiguriert und den oberen und zentralen Bereich
     * zugewiesen.
     *
     * @author Rode
     * @since 06.12.2023
     */
    public void verbrauchsGegenstaendeOeffnen() {
        this.setAusgewaehlterChar(null);
        getChildren().clear();
        getChildren().add(hintergrundBildAnsicht);
        setMinWidth(1536);
        setMinHeight(1080);
        HBox geteilteAnsicht = new HBox();
        VBox charBox = new VBox();
        VBox gegenstandAuswahlBox = new VBox();
        gegenstandAuswahlBox.setPrefSize(768, 1080);

        gegenstandAuswahlBox.getChildren().add(new OverlayPartyMenueInventar(this, gegenstandAuswahlBox, partyController, viewController));

        charBox.getChildren().clear();
        for (SpielerCharakter spielerCharakter : partyController.getTeammitglieder()) {
            if (spielerCharakter != null) {
                charBox.getChildren().add(new OverlayPartyMenueInventar(spielerCharakter, this, charBox));
            }
        }

        charBox.setPadding(new Insets(200, 0, 0, 0));
        gegenstandAuswahlBox.setPadding(new Insets(200, 0, 0, 0));
        geteilteAnsicht.setAlignment(Pos.CENTER);
        geteilteAnsicht.getChildren().add(charBox);
        geteilteAnsicht.getChildren().add(gegenstandAuswahlBox);
        VBox top = new VBox();
        top.setMinHeight(40);
        this.setTop(top);
        this.setCenter(geteilteAnsicht);
    }

    private void refreshButtonInfo(TextField waffetxtf, TextField ruestungtxtf, TextField acc1txtf, TextField acc2txtf, TextField acc3txtf, SpielerCharakter ausgewaehlterChar) {
        waffetxtf.setText("Physische Attacke: " + ausgewaehlterChar.getWaffe().getAttacke() + "  | Magische Attacke: " + ausgewaehlterChar.getWaffe().getMagischeAttacke()
                + "  |Genauigkeit: " + ausgewaehlterChar.getWaffe().getGenauigkeit() + "  | Beweglichkeit: " + ausgewaehlterChar.getWaffe().getBeweglichkeit());
        ruestungtxtf.setText("Verteidigung:" + ausgewaehlterChar.getRuestung().getVerteidigung() + "  | Mag.Verteidigung: " + ausgewaehlterChar.getRuestung().getMagischeVerteidigung()
                + "  | Resistenz: " + ausgewaehlterChar.getRuestung().getResistenz() + "  | Gesundheit+: " + ausgewaehlterChar.getRuestung().getMaxGesundheitsPunkte() + "  | Mana+:" + ausgewaehlterChar.getRuestung().getMaxManaPunkte());
        acc1txtf.setText("Maxgesundheit+: " + ausgewaehlterChar.getAccessoire(0).getMaxGesundheitsPunkte() + "  | Mana+:" + ausgewaehlterChar.getAccessoire(0).getMaxManaPunkte()
                + "  | Beweglichkeit: " + ausgewaehlterChar.getAccessoire(0).getBeweglichkeit() + "  | GesundheitReg+: " + ausgewaehlterChar.getAccessoire(0).getGesundheitsRegeneration()
                + "  | ManaReg+: " + ausgewaehlterChar.getAccessoire(0).getManaRegeneration());
        acc2txtf.setText("Maxgesundheit+: " + ausgewaehlterChar.getAccessoire(1).getMaxGesundheitsPunkte() + "  | Mana+:" + ausgewaehlterChar.getAccessoire(1).getMaxManaPunkte()
                + "  | Beweglichkeit: " + ausgewaehlterChar.getAccessoire(1).getBeweglichkeit() + "  | GesundheitReg+: " + ausgewaehlterChar.getAccessoire(1).getGesundheitsRegeneration()
                + "  | ManaReg+: " + ausgewaehlterChar.getAccessoire(1).getManaRegeneration());
        acc3txtf.setText("Maxgesundheit+: " + ausgewaehlterChar.getAccessoire(2).getMaxGesundheitsPunkte() + "  | Mana+:" + ausgewaehlterChar.getAccessoire(2).getMaxManaPunkte()
                + "  | Beweglichkeit: " + ausgewaehlterChar.getAccessoire(2).getBeweglichkeit() + "  | GesundheitReg+: " + ausgewaehlterChar.getAccessoire(2).getGesundheitsRegeneration()
                + "  | ManaReg+: " + ausgewaehlterChar.getAccessoire(2).getManaRegeneration());
    }

}



