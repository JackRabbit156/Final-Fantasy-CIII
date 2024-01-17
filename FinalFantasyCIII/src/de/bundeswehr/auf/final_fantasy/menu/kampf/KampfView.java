package de.bundeswehr.auf.final_fantasy.menu.kampf;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KampfView extends StackPane {

    static final double[] POSITIONEN_GEGNER_X = { 1000, 1200, 1400, 1600 };
    static final double[] POSITIONEN_GEGNER_Y = { 350, 440, 530, 620 };
    static final double[] POSITIONEN_PARTY_X = { 600, 430, 260, 90 };
    static final double[] POSITIONEN_PARTY_Y = { 350, 440, 530, 620 };
    static final double[] POSITION_AKTUELLER_CHARAKTER = { 700, 620 };

    private static final Pattern TRANK_PATTERN = Pattern.compile("\\dx(.*)\\(.*\\)");
    private static final Background semiTransparent = new Background(new BackgroundFill(Color.rgb(0, 125, 125, 0.625), CornerRadii.EMPTY, Insets.EMPTY));

    BorderPane aktionAusgefuehrtInfoAnzeige = new BorderPane();
    Label kampfErgebnis = new Label();
    VBox kampfErgebnisContainer = new VBox();
    ImageView niederlage = new ImageView(new Image("/icons/niederlage.png", 0.0, 320.0, true, false));
    ImageView sieg = new ImageView(new Image("/icons/sieg.png", 0.0, 320.0, true, false));

    private final GridPane actionsMenu = new GridPane();
    private final HBox actionsMenuContainer = new HBox();
    private final TextArea aktionAusgefuehrtInfo = new TextArea();
    private final Rectangle aktuellerCharakterBox = new Rectangle(65, 50);
    private int anzahlZiele = 0;
    private final ListView<Faehigkeit> anzeigeFaehigkeiten = new ListView<>();
    private final ListView<String> anzeigeVerbrauchsgegenstaende = new ListView<>();
    private final HBox detailMenu = new HBox();
    private final HBox detailMenuContainer = new HBox();
    private final HBox detailMenuLeer = new HBox();
    private Faehigkeit faehigkeit;
    private final Button faehigkeitAbbrechen = new Button("Abbrechen");
    private final Pane hauptbildschirm = new Pane();
    private final KampfController kampfController;
    private final TextArea kampflogText = new TextArea();
    private final BorderPane kampflogView = new BorderPane();
    private final StackPane untererBildschirm = new StackPane();
    private Verbrauchsgegenstand verbrauchsgegenstand = null;
    private final Button verbrauchsgegenstandAbbrechen = new Button("Abbrechen");
    private List<Charakter> zielAuswahl = new ArrayList<>();
    private ZielAuswahlFactory zielAuswahlFactory;
    private final HBox zugreihenfolgeAnzeige = new HBox();
    private final StackPane zugreihenfolgeAnzeigeMitKasten = new StackPane();

    public KampfView(KampfController kampfController) {
        this.kampfController = kampfController;

        kampflogView.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8);");

        Button kampflogAbbrechen = new Button("Zurück zum Kampf");
        kampflogAbbrechen.setMinWidth(190);
        kampflogAbbrechen.setMaxWidth(190);
        kampflogAbbrechen.getStyleClass().add("kampflogbutton");
        kampflogAbbrechen.setAlignment(Pos.TOP_CENTER);

        HBox kampfLogBottom = new HBox();
        kampfLogBottom.setPrefSize(1920, 450);
        kampfLogBottom.getChildren().add(kampflogAbbrechen);
        kampfLogBottom.setAlignment(Pos.TOP_CENTER);
        kampflogView.setBottom(kampfLogBottom);

        kampflogText.setEditable(false);
        kampflogText.setPrefSize(1000, 300);
        kampflogText.setPadding(new Insets(0, 0, 1, 0));

        HBox kampfLogCenter = new HBox();
        kampfLogCenter.setMaxSize(1920, 550);
        kampfLogCenter.getChildren().add(kampflogText);
        kampfLogCenter.setAlignment(Pos.CENTER);
        kampflogView.setCenter(kampfLogCenter);

        faehigkeitAbbrechen.getStyleClass().add("kampflogbutton");

        verbrauchsgegenstandAbbrechen.getStyleClass().add("kampflogbutton");

        HBox aktionObenLeer = new HBox();
        aktionObenLeer.setPrefSize(1920, 320);
        HBox aktionLinksLeer = new HBox();
        aktionLinksLeer.setPrefSize(760, 200);
        HBox aktionRechtsLeer = new HBox();
        aktionRechtsLeer.setPrefSize(760, 200);
        HBox aktionUntenLeer = new HBox();
        aktionUntenLeer.setPrefSize(1920, 580);

        aktionAusgefuehrtInfo.setPrefSize(300, 200);
        aktionAusgefuehrtInfo.setEditable(false);

        Button ok = new Button("OK");
        ok.getStyleClass().add("kampflogbutton");

        VBox anordnungAktionsInfo = new VBox();
        anordnungAktionsInfo.getChildren().addAll(aktionAusgefuehrtInfo, ok);
        anordnungAktionsInfo.setAlignment(Pos.CENTER);
        anordnungAktionsInfo.setSpacing(10);

        aktionAusgefuehrtInfoAnzeige.setBackground(semiTransparent);
        aktionAusgefuehrtInfoAnzeige.setCenter(anordnungAktionsInfo);
        aktionAusgefuehrtInfoAnzeige.setTop(aktionObenLeer);
        aktionAusgefuehrtInfoAnzeige.setBottom(aktionUntenLeer);
        aktionAusgefuehrtInfoAnzeige.setLeft(aktionLinksLeer);
        aktionAusgefuehrtInfoAnzeige.setRight(aktionRechtsLeer);

        kampfErgebnis.setMinSize(500, 250);
//		kampfErgebnis.setEditable(false);
        kampfErgebnis.getStyleClass().add("kampfErgebnisArea");

        Button kampfErgebnisBestaetigen = new Button("OK");
        kampfErgebnisBestaetigen.getStyleClass().add("kampflogbutton");
        kampfErgebnisBestaetigen.setOnAction(event -> kampfController.zurueckZumHub());

        kampfErgebnisContainer.getChildren().addAll(kampfErgebnis, kampfErgebnisBestaetigen);
//		kampfErgebnis.setMaxWidth(400.0);
        kampfErgebnisContainer.setAlignment(Pos.CENTER);
        kampfErgebnisContainer.setBackground(semiTransparent);
        kampfErgebnisContainer.setSpacing(10.0);

        HBox actionsMenuLeer = new HBox();
        actionsMenuLeer.setMinSize(960, 216);
        actionsMenuContainer.getChildren().addAll(actionsMenu, actionsMenuLeer);
        detailMenuLeer.setMinSize(960, 216);
        detailMenuContainer.getChildren().addAll(detailMenuLeer, detailMenu);

        Button btnAngriff = new Button("Angriff");
        btnAngriff.getStyleClass().add("aktionwaehlenbutton");
        Button btnVerbrauchsgegenstand = new Button("Gegenstand");
        btnVerbrauchsgegenstand.getStyleClass().add("aktionwaehlenbutton");
        Button btnBlocken = new Button("Blocken");
        btnBlocken.getStyleClass().add("aktionwaehlenbutton");
        Button btnFliehen = new Button("Fliehen");
        btnFliehen.getStyleClass().add("aktionwaehlenbutton");
        Button btnKampflog = new Button("Kampflog");
        btnKampflog.getStyleClass().add("kampflogbutton");

        ColumnConstraints[] col = new ColumnConstraints[3];
        RowConstraints[] row = new RowConstraints[3];

        col[0] = new ColumnConstraints();
        col[0].setPercentWidth(40);
        col[0].setHalignment(HPos.CENTER);

        col[1] = new ColumnConstraints();
        col[1].setPercentWidth(20);
        col[1].setHalignment(HPos.CENTER);

        col[2] = new ColumnConstraints();
        col[2].setPercentWidth(40);
        col[2].setHalignment(HPos.CENTER);

        row[0] = new RowConstraints();
        row[0].setPercentHeight(40);

        row[1] = new RowConstraints();
        row[1].setPercentHeight(20);

        row[2] = new RowConstraints();
        row[2].setPercentHeight(40);

        actionsMenu.getColumnConstraints().addAll(col[0], col[1], col[2]);
        actionsMenu.getRowConstraints().addAll(row[0], row[1], row[2]);
        actionsMenu.setPrefSize(960, 216);
        actionsMenu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        actionsMenu.add(btnAngriff, 0, 0);
        actionsMenu.add(btnBlocken, 0, 2);
        actionsMenu.add(btnKampflog, 1, 1);
        actionsMenu.add(btnVerbrauchsgegenstand, 2, 0);
        actionsMenu.add(btnFliehen, 2, 2);

        detailMenu.setAlignment(Pos.CENTER);
        detailMenu.setSpacing(10);
        detailMenu.setPrefSize(960, 216);
        detailMenu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        untererBildschirm.getChildren().addAll(actionsMenuContainer, detailMenuContainer);
        untererBildschirm.setMaxSize(1920, 216);
        untererBildschirm.setBackground(new Background(new BackgroundImage(new Image("background/actionsmenu_multifunktionsfenster_kampf.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 20, true, true, false, true))));
        actionsMenuContainer.toFront();

        kampflogText.appendText("[" + timestamp() + "] " + "\nDER KAMPF HAT BEGONNEN");

        this.getChildren().addAll(kampfErgebnisContainer, hauptbildschirm, untererBildschirm, kampflogView,
                aktionAusgefuehrtInfoAnzeige);
        StackPane.setAlignment(hauptbildschirm, Pos.TOP_CENTER);
        StackPane.setAlignment(untererBildschirm, Pos.BOTTOM_LEFT);

        btnAngriff.setOnAction(event -> updateFaehigkeitenView(KampfController.getAktiveFaehigkeiten(kampfController.aktuellerCharakter)));

        btnVerbrauchsgegenstand.setOnAction(event -> updateGegenstaendeView());

        btnKampflog.setOnAction(event -> kampflogView.toFront());

        btnFliehen.setOnAction(event -> fliehen());

        btnBlocken.setOnAction(event -> blocken());

        ok.setOnAction(event -> {
            if (kampfController.istKampfVorbei[0]) {
                kampfController.kampfAuswerten();
            }
            else {
                hauptbildschirm.getChildren().clear();
                updateKampfBildschirm();
            }
        });

        kampflogAbbrechen.setOnAction(event -> kampflogView.toBack());

        anzeigeVerbrauchsgegenstaende.getStyleClass().add("verbrauchsgegenstaende-list-view");
        anzeigeVerbrauchsgegenstaende.setPrefSize(800, 200);

        anzeigeFaehigkeiten.getStyleClass().add("faehigkeiten-list-view");
        anzeigeFaehigkeiten.setPrefSize(800, 200);
        anzeigeFaehigkeiten.setCellFactory(cell -> new ListCell<Faehigkeit>() {

            final Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(Faehigkeit faehigkeit, boolean empty) {
                super.updateItem(faehigkeit, empty);
                if (empty || faehigkeit == null) {
                    setGraphic(null);
                    setText(null);
                    setTooltip(null);
                }
                else {
                    String zielGruppe;
                    if (faehigkeit.isIstFreundlich()) {
                        zielGruppe = "Party";
                    }
                    else {
                        zielGruppe = "Gegner-Team";
                    }
                    String faehigkeitsTyp;
                    if (faehigkeit.getFaehigkeitsTyp().equals("physisch")) {
                        faehigkeitsTyp = "Physisch";
                    }
                    else {
                        faehigkeitsTyp = "Magisch";
                    }
                    tooltip.setText(faehigkeit.getBeschreibung() + "\n" +
                            "Zielgruppe: " + zielGruppe + "\n" +
                            "Anzahl Ziele: " + faehigkeit.getZielAnzahl() + "\n" +
                            "Stärke: " + faehigkeit.getEffektStaerke() + "\n" +
                            "Fähigkeits-Typ: " + faehigkeitsTyp);
                    setTooltip(tooltip);
                    setGraphic(new ImageView(new Image(faehigkeit.getIcon())));
                    setText(String.format("%s (%d MP)", faehigkeit.getName(), faehigkeit.getManaKosten()));
                }
            }

        });

        faehigkeitAbbrechen.setOnAction(event -> {
            detailMenuLeer.setBackground(null);
            detailMenu.getChildren().clear();
            detailMenuContainer.toBack();
            updateKampfBildschirm();
        });

        anzeigeFaehigkeiten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateKampfBildschirm();
                detailMenuContainer.toFront();
                faehigkeit = newValue;
                if (faehigkeit.isIstFreundlich()) {
                    zielauswahlTeammitglieder(faehigkeit.getZielAnzahl());
                }
                else {
                    zielauswahlGegner(faehigkeit.getZielAnzahl());
                }
            }
        });

        verbrauchsgegenstandAbbrechen.setOnAction(event -> {
            detailMenuLeer.setBackground(null);
            detailMenu.getChildren().clear();
            detailMenuContainer.toBack();
            updateKampfBildschirm();
        });

        anzeigeVerbrauchsgegenstaende.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nutzeVerbrauchsgegenstand(newValue);
            }
        });

        kampflogText.appendText(backendFeedbackKampf());
    }

    /**
     * Feedback vom KampfController, was für Schaden / Heilung, etc. verursacht
     * wurde
     *
     * @return text fürs Kampflog und Infoanzeige nach Aktionsausführung
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public String backendFeedbackKampf() {
        StringBuilder returnString = new StringBuilder("\n");
        for (int counter = 0; counter < kampfController.kampfWerteLog.size(); counter++) {
            returnString.append(kampfController.kampfWerteLog.get(counter));
        }
        return returnString.toString();
    }

    public void faehigkeitVerwendet() {
        Charakter charakter = kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1);
        if (faehigkeit != null) {
            StringBuilder ausgabe = new StringBuilder(charakter.getName());
            ausgabe.append(" hat '").append(faehigkeit.getName()).append("' auf ");
            boolean erster = true;
            for (Charakter ziel : zielAuswahl) {
                if (!erster) {
                    ausgabe.append(" und ");
                }
                ausgabe.append(ziel.getName());
                erster = false;
            }
            ausgabe.append(" benutzt.").append(backendFeedbackKampf());
            aktionAusgefuehrtInfo.setText(ausgabe.toString());
            kampflogText.appendText("\n[" + timestamp() + "] " + "\n" + ausgabe);
            if (Game.debugModus) {
                kampflogText.appendText("[DEBUG] genutzte Fähigkeit: " + faehigkeit + "\n");
            }
            logStopBlocken();
        }
        else {
            aktionAusgefuehrtInfo.setText(charakter.getName()
                    + " fängt an zu blocken.\nBis zu seinem nächsten Zug blockt er\n"
                    + charakter.getPhysischeAttacke() + " physischen Schaden und "
                    + charakter.getMagischeAttacke() + " magischen Schaden.\n");
            kampflogText.appendText("\n[" + timestamp() + "] " + "\n" + charakter.getName()
                    + " fängt an zu blocken. Bis zu seinem nächsten Zug blockt er "
                    + charakter.getPhysischeAttacke() + " physischen Schaden und "
                    + charakter.getMagischeAttacke() + " magischen Schaden.\n");
        }
        faehigkeit = null;
        zielAuswahl.clear();
        detailMenuLeer.setBackground(null);
        detailMenu.getChildren().clear();
        detailMenuContainer.toBack();
        aktionAusgefuehrtInfoAnzeige.toFront();
    }

    public void setFaehigkeit(Faehigkeit faehigkeit) {
        this.faehigkeit = faehigkeit;
    }

    public void setZielGruppe(List<Charakter> zielGruppe) {
        this.zielAuswahl = zielGruppe;
    }

    /**
     * Anzeige vom Hauptbildschirm im Kampf wird hier aktualisiert
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public void updateKampfBildschirm() {
        Charakter aktuellerCharakter = kampfController.aktuelleZugreihenfolge.get(0);
        zugreihenfolgeAnzeige.getChildren().clear();
        zugreihenfolgeAnzeigeMitKasten.getChildren().clear();
        hauptbildschirm.getChildren().clear();
        hauptbildschirm.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        hauptbildschirm.setBackground(new Background(new BackgroundImage(new Image("background/kampfarena1.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 80, true, true, false, true))));

        actionsMenu.setGridLinesVisible(false);
        zugreihenfolgeAnzeige.getStyleClass().add("leisteTooltip");
        zugreihenfolgeAnzeige.setPrefWidth(720);
        zugreihenfolgeAnzeige.setPrefHeight(50);
        zugreihenfolgeAnzeige.setLayoutX(600);
        zugreihenfolgeAnzeige.setLayoutY(30);
        zugreihenfolgeAnzeige.setOpacity(0.5);

        aktuellerCharakterBox.setFill(Color.TRANSPARENT);
        aktuellerCharakterBox.setStroke(ColorHelper.getStroke(aktuellerCharakter));
        aktuellerCharakterBox.setStrokeWidth(3.0);
        aktuellerCharakterBox.setLayoutX(617);
        aktuellerCharakterBox.setLayoutY(30);
        hauptbildschirm.getChildren().add(aktuellerCharakterBox);

        for (Charakter charakter : kampfController.aktuelleZugreihenfolge) {
            Tooltip ttCharakterAnzeige = new Tooltip(charakter.getName() + "\n" + charakter.getGesundheitsPunkte() + "/"
                    + charakter.getMaxGesundheitsPunkte() + " HP\n" + charakter.getManaPunkte() + "/"
                    + charakter.getMaxManaPunkte() + " MP");
            if (charakter instanceof SpielerCharakter && !((SpielerCharakter) charakter).isSoeldner()) {
                ImageView ivHauptcharakterAnzeige = new ImageView(
                        new Image(charakter.getGrafischeDarstellung(), 0, 45, true, true));
                Tooltip.install(ivHauptcharakterAnzeige, ttCharakterAnzeige);
                zugreihenfolgeAnzeige.getChildren().add(ivHauptcharakterAnzeige);
            }
            else if (charakter instanceof SpielerCharakter) {
                ImageView ivSoeldnerAnzeige = new ImageView(
                        new Image(charakter.getGrafischeDarstellung(), 0, 45, true, true));
                Tooltip.install(ivSoeldnerAnzeige, ttCharakterAnzeige);
                zugreihenfolgeAnzeige.getChildren().add(ivSoeldnerAnzeige);
            }
            else {
                ImageView ivGegnerAnzeige = new ImageView(
                        new Image(charakter.getGrafischeDarstellung(), 0, 45, true, true));
                Tooltip.install(ivGegnerAnzeige, ttCharakterAnzeige);
                zugreihenfolgeAnzeige.getChildren().add(ivGegnerAnzeige);
            }
        }
        zugreihenfolgeAnzeige.setSpacing(35);
        zugreihenfolgeAnzeige.setPadding(new Insets(0, 0, 0, 20));
        hauptbildschirm.getChildren().add(zugreihenfolgeAnzeige);

        zielAuswahlFactory = new ZielAuswahlFactory(hauptbildschirm, kampfController);
        CharakterViewFactory charakterViewFactory = new CharakterViewFactory(hauptbildschirm);

        for (int i = 0; i < kampfController.partyAnordnung.size(); i++) {
            SpielerCharakter charakter = kampfController.partyAnordnung.get(i);
            charakterViewFactory.prepareCharakterView(charakter, kampfController.blockendeCharaktere.contains(charakter));
            if (charakter.getGesundheitsPunkte() > 0) {
                // Lebender Charakter ist Hauptcharakter
                if (!charakter.isSoeldner()) {
                    charakterViewFactory.addHauptCharakter(aktuellerCharakter, i, charakter);
                }
                // Lebender Charakter ist Soeldner
                else {
                    charakterViewFactory.addSoeldner(aktuellerCharakter, i, charakter);
                }
            }
            // Charakter hat am Anfang gelebt aber ist aktuell Tod
            else {
                // Toter Charakter ist Hauptcharakter
                if (!charakter.isSoeldner()) {
                    charakterViewFactory.addHauptCharakterTot(i, charakter);
                }
                // Toter Charakter ist Soeldner
                else {
                    charakterViewFactory.addSoeldnerTot(i, charakter);
                }
            }
        }
        for (int i = 0; i < kampfController.gegnerAnordnung.size(); i++) {
            Feind feind = kampfController.gegnerAnordnung.get(i);
            charakterViewFactory.prepareCharakterView(feind, kampfController.blockendeCharaktere.contains(feind));
            if (feind.getGesundheitsPunkte() > 0) {
                charakterViewFactory.addFeind(aktuellerCharakter, i, feind);
            }
            else {
                charakterViewFactory.addFeindTot(i, feind);
            }
        }

        hauptbildschirm.setMaxSize(1920, 864);
        untererBildschirm.setMaxSize(1920, 216);
        actionsMenuContainer.toFront();
        actionsMenuContainer.setMaxSize(1920, 216);
        detailMenuContainer.setMaxSize(1920, 216);
        kampflogView.toBack();
        aktionAusgefuehrtInfoAnzeige.toBack();
        StackPane.setAlignment(actionsMenuContainer, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(detailMenuContainer, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(hauptbildschirm, Pos.TOP_CENTER);
        StackPane.setAlignment(untererBildschirm, Pos.BOTTOM_CENTER);

        if (kampfController.aktuellerCharakter instanceof Feind) {
            if (!kampfController.istKampfVorbei[0]) {
                kampfController.gegnerlogik((Feind) kampfController.aktuellerCharakter);
                faehigkeitVerwendet();
            }
            else {
                aktionAusgefuehrtInfoAnzeige.toFront();
            }
        }
    }

    /**
     * Auswahl von Feinden bei Fähigkeiten die man auf Feinde wirken muss
     *
     * @param anzahlZiele Anzahl an Feinden die man treffen will - int
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public void zielauswahlGegner(int anzahlZiele) {
        hauptbildschirm.toFront();
        for (int i = 0; i < kampfController.gegnerAnordnung.size(); i++) {
            Feind feind = kampfController.gegnerAnordnung.get(i);
            zielAuswahlFactory.addFeind(i, feind, event -> {
                zielAuswahl.add(feind);
                if (zielAuswahl.size() == anzahlZiele) {
                    kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter,
                            zielAuswahl, faehigkeit);
                    faehigkeitVerwendet();
                }
            });
        }
    }

    /**
     * Auswahl von Freunden bei Fähigkeiten und Gegenständen die man auf
     * Partymitglieder wirken muss
     *
     * @param anzahlZiele Anzahl an Freunden die man treffen will - int
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public void zielauswahlTeammitglieder(int anzahlZiele) {
        hauptbildschirm.toFront();
        for (int i = 0; i < kampfController.partyAnordnung.size(); i++) {
            SpielerCharakter charakter = kampfController.partyAnordnung.get(i);
            zielAuswahlFactory.addTeam(i, charakter, event -> {
                zielAuswahl.add(charakter);
                if (zielAuswahl.size() == anzahlZiele) {
                    if (verbrauchsgegenstand != null) {
                        kampfController.gegenstand(verbrauchsgegenstand, charakter);
                        logVerbrauchsgegenstandVerwendet();
                    }
                    else {
                        kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter,
                                zielAuswahl, faehigkeit);
                        faehigkeitVerwendet();
                    }
                }
            });
        }
    }

    private void blocken() {
        kampfController.blocken();
        Charakter blockenderCharakter = kampfController.aktuelleZugreihenfolge
                .get(kampfController.aktuelleZugreihenfolge.size() - 1);
        aktionAusgefuehrtInfo.setText(blockenderCharakter.getName()
                + " fängt an zu blocken.\n" + "Bis zu seinem nächsten Zug blockt er \n"
                + blockenderCharakter.getPhysischeAttacke() + " physischen und "
                + blockenderCharakter.getMagischeAttacke() + " magischen Schaden.");
        kampflogText.appendText("\n[" + timestamp() + "] " + "\n"
                + blockenderCharakter.getName() + " fängt an zu blocken. Bis zu seinem nächsten Zug blockt er "
                + blockenderCharakter.getPhysischeAttacke() + " physischen und "
                + blockenderCharakter.getMagischeAttacke() + " magischen Schaden.");
        logStopBlocken();
        aktionAusgefuehrtInfoAnzeige.toFront();
    }

    private void fliehen() {
        kampfController.fliehen();
        if (!kampfController.istKampfVorbei[0]) {
            Charakter fliehenderCharakter = kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1);
            aktionAusgefuehrtInfo.setText(
                    fliehenderCharakter.getName() + " hat versucht zu fliehen!\nDie Flucht ist fehlgeschlagen...");
            kampflogText.appendText("\n[" + timestamp() + "] " + "\n" + fliehenderCharakter.getName()
                    + " hat versucht zu fliehen! Die Flucht ist fehlgeschlagen...");
            logStopBlocken();
        }
        else {
            aktionAusgefuehrtInfo.setText(kampfController.aktuellerCharakter.getName()
                    + " hat versucht zu fliehen!\nDie Flucht war erfolgreich!\n'OK' drücken für Kampfauswertung.");
        }
        aktionAusgefuehrtInfoAnzeige.toFront();
    }

    private void logStopBlocken() {
        Charakter blockenderCharakter = kampfController.aktuelleZugreihenfolge.get(0);
        if (kampfController.blockendeCharaktere.contains(blockenderCharakter)) {
            aktionAusgefuehrtInfo.appendText("\n" + blockenderCharakter.getName() + "hört auf zu blocken.");
            kampflogText.appendText("\n[" + timestamp() + "] " + "\n"
                    + blockenderCharakter.getName() + "hört auf zu blocken.");
        }
    }

    private void logVerbrauchsgegenstandVerwendet() {
        Charakter charakter = kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1);
        aktionAusgefuehrtInfo.setText(charakter.getName()
                + " hat '" + verbrauchsgegenstand.getName() + "' auf "
                + zielAuswahl.get(0).getName() + "\nbenutzt.\n");
        kampflogText.appendText("\n[" + timestamp() + "] " + "\n"
                + charakter.getName() + " hat '" + verbrauchsgegenstand.getName() + "' auf " +
                zielAuswahl.get(0).getName() + "benutzt.\n");
        logStopBlocken();
        verbrauchsgegenstand = null;
        zielAuswahl.clear();
        detailMenuLeer.setBackground(null);
        detailMenu.getChildren().clear();
        detailMenuContainer.toBack();
        aktionAusgefuehrtInfoAnzeige.toFront();
    }

    private void nutzeVerbrauchsgegenstand(String verbrauchsgegenstandString) {
        if (verbrauchsgegenstandString == null) {
            return;
        }
        Matcher matcher = TRANK_PATTERN.matcher(verbrauchsgegenstandString);
        if (!matcher.matches()) {
            throw new RuntimeException("Fehlerhafter Eintrag in der Gegenstandliste: " + verbrauchsgegenstandString);
        }
        String verbrauchsgegenstandName = matcher.group(1).trim();
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.party.getVerbrauchsgegenstaende().entrySet()) {
            if (entry.getKey().getName().equals(verbrauchsgegenstandName)) {
                verbrauchsgegenstand = entry.getKey();
            }
        }
        zielauswahlTeammitglieder(1);
    }

    private String timestamp() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
    }

    private void updateFaehigkeitenView(List<Faehigkeit> aktiveFaehigkeiten) {
        List<Faehigkeit> auswaehlbareFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : aktiveFaehigkeiten) {
            if (kampfController.aktuellerCharakter.getManaPunkte() >= faehigkeit.getManaKosten()) {
                auswaehlbareFaehigkeiten.add(faehigkeit);
            }
        }
        anzeigeFaehigkeiten.setItems(FXCollections.observableArrayList(auswaehlbareFaehigkeiten));
        anzeigeFaehigkeiten.getSelectionModel().selectFirst();
        detailMenuLeer.setBackground(semiTransparent);
        detailMenu.getChildren().addAll(anzeigeFaehigkeiten, faehigkeitAbbrechen);
        detailMenuContainer.toFront();
        anzeigeFaehigkeiten.requestFocus();
    }

    private void updateGegenstaendeView() {
        List<String> partyVerbrauchsGegenstaende = new ArrayList<>();
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.party.getVerbrauchsgegenstaende().entrySet()) {
            if (entry.getValue().get() > 0) {
                partyVerbrauchsGegenstaende.add(String.format("%dx %s (%s)", entry.getValue().get(), entry.getKey().getName(), entry.getKey().getBeschreibung()));
            }
        }
        anzeigeVerbrauchsgegenstaende.setItems(FXCollections.observableArrayList(partyVerbrauchsGegenstaende));
        anzeigeVerbrauchsgegenstaende.setPlaceholder(new PlaceHolder("Keine nutzbaren Verbrauchsgegenstände verfügbar"));
        anzeigeVerbrauchsgegenstaende.getSelectionModel().selectFirst();
        detailMenuLeer.setBackground(semiTransparent);
        detailMenu.getChildren().addAll(anzeigeVerbrauchsgegenstaende, verbrauchsgegenstandAbbrechen);
        detailMenuContainer.toFront();
        anzeigeVerbrauchsgegenstaende.requestFocus();
    }

}
