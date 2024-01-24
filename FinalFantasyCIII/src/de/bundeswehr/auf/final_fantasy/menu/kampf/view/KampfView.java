package de.bundeswehr.auf.final_fantasy.menu.kampf.view;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.PlaceHolder;
import de.bundeswehr.auf.final_fantasy.menu.kampf.Kampf;
import de.bundeswehr.auf.final_fantasy.menu.kampf.controller.KampfController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    private static final Background FAINT_BACKGROUND = new Background(new BackgroundFill(Color.rgb(0, 125, 125, 0.625), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Pattern TRANK_PATTERN = Pattern.compile("\\dx(.*)\\(.*\\)");
    private final GridPane actionsMenu = new GridPane();
    private final TextArea aktionAusgefuehrtInfo = new TextArea();
    private final BorderPane aktionAusgefuehrtInfoAnzeige = new BorderPane();
    private final HBox aktionsMenuContainer = new HBox();
    private final ListView<Faehigkeit> anzeigeFaehigkeiten = new ListView<>();
    private final ListView<String> anzeigeVerbrauchsgegenstaende = new ListView<>();
    private final CharakterViewFactory charakterViewFactory;
    private final HBox detailMenu = new HBox();
    private final HBox detailMenuContainer = new HBox();
    private final HBox detailMenuLeer = new HBox();
    private Faehigkeit faehigkeit;
    private final Button faehigkeitAbbrechen = new Button("Abbrechen");
    private final Pane hauptbildschirm = new Pane();
    private final Kampf kampf;
    private final KampfController kampfController;
    private final Label kampfErgebnis = new Label();
    private final VBox kampfErgebnisContainer = new VBox();
    private final Button kampflogAbbrechen;
    private final TextArea kampflogText = new TextArea();
    private final BorderPane kampflogView = new BorderPane();
    private final EventHandler<KeyEvent> keyAction;
    private final ImageView niederlage = new ImageView(new Image("/icons/niederlage.png", 0.0, 320.0, true, false));
    private final Button ok;
    private final ImageView sieg = new ImageView(new Image("/icons/sieg.png", 0.0, 320.0, true, false));
    private final StackPane untererBildschirm = new StackPane();
    private final Button verbrauchsgegenstaendeAbbrechen = new Button("Abbrechen");
    private Verbrauchsgegenstand verbrauchsgegenstand = null;
    private List<Charakter> zielAuswahl = new ArrayList<>();
    private final ZielAuswahlFactory zielAuswahlFactory;
    private final Zugreihenfolge zugreihenfolgeAnzeige = new Zugreihenfolge(571, 30);

    public KampfView(Kampf kampf, KampfController kampfController) {
        this.kampf = kampf;
        this.kampfController = kampfController;

        kampflogView.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8);");

        kampflogAbbrechen = new Button("Zurück zum Kampf");
        kampflogAbbrechen.setMinWidth(190);
        kampflogAbbrechen.setMaxWidth(190);
        kampflogAbbrechen.getStyleClass().add("kampflogbutton");
        kampflogAbbrechen.setAlignment(Pos.TOP_CENTER);
        kampflogAbbrechen.setOnAction(event -> kampflogView.toBack());

        HBox kampfLogBottom = new HBox();
        kampfLogBottom.setPrefSize(1920, 450);
        kampfLogBottom.getChildren().add(kampflogAbbrechen);
        kampfLogBottom.setAlignment(Pos.TOP_CENTER);
        kampflogView.setBottom(kampfLogBottom);

        kampflogText.setEditable(false);
        kampflogText.setPrefSize(1000, 300);
        kampflogText.setPadding(new Insets(0, 0, 1, 0));
        kampflogText.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> kampflogText.setScrollTop(Double.MAX_VALUE));

        HBox kampfLogCenter = new HBox();
        kampfLogCenter.setMaxSize(1920, 550);
        kampfLogCenter.getChildren().add(kampflogText);
        kampfLogCenter.setAlignment(Pos.CENTER);
        kampflogView.setCenter(kampfLogCenter);

        faehigkeitAbbrechen.getStyleClass().add("kampflogbutton");

        verbrauchsgegenstaendeAbbrechen.getStyleClass().add("kampflogbutton");

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

        ok = new Button("OK");
        ok.getStyleClass().add("kampflogbutton");
        ok.setOnAction(event -> {
            if (kampf.isKampfVorbei()) {
                kampfController.kampfAuswerten();
            }
            else {
                updateKampfBildschirm();
            }
        });

        VBox anordnungAktionsInfo = new VBox();
        anordnungAktionsInfo.getChildren().addAll(aktionAusgefuehrtInfo, ok);
        anordnungAktionsInfo.setAlignment(Pos.CENTER);
        anordnungAktionsInfo.setSpacing(10);

        aktionAusgefuehrtInfoAnzeige.setBackground(FAINT_BACKGROUND);
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
        kampfErgebnisContainer.setBackground(FAINT_BACKGROUND);
        kampfErgebnisContainer.setSpacing(10.0);

        HBox aktionsMenuLeer = new HBox();
        aktionsMenuLeer.setMinSize(960, 216);
        aktionsMenuContainer.setMaxSize(1920, 216);
        aktionsMenuContainer.getChildren().addAll(actionsMenu, aktionsMenuLeer);

        detailMenuLeer.setMinSize(960, 216);
        detailMenuContainer.setMaxSize(1920, 216);
        detailMenuContainer.getChildren().addAll(detailMenuLeer, detailMenu);

        Button btnAngriff = new Button("Angriff");
        btnAngriff.getStyleClass().add("aktionwaehlenbutton");
        btnAngriff.setOnAction(event -> updateFaehigkeitenView(kampfController.getAktiveFaehigkeiten()));

        Button btnVerbrauchsgegenstand = new Button("Gegenstand");
        btnVerbrauchsgegenstand.getStyleClass().add("aktionwaehlenbutton");
        btnVerbrauchsgegenstand.setOnAction(event -> updateGegenstaendeView());

        Button btnBlocken = new Button("Blocken");
        btnBlocken.getStyleClass().add("aktionwaehlenbutton");
        btnBlocken.setOnAction(event -> blocken());

        Button btnFliehen = new Button("Fliehen");
        btnFliehen.getStyleClass().add("aktionwaehlenbutton");
        btnFliehen.setOnAction(event -> fliehen());

        Button btnKampflog = new Button("Kampflog");
        btnKampflog.getStyleClass().add("kampflogbutton");
        btnKampflog.setOnAction(event -> showKampflog());

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
        actionsMenu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        actionsMenu.add(btnAngriff, 0, 0);
        actionsMenu.add(btnBlocken, 0, 2);
        actionsMenu.add(btnKampflog, 1, 1);
        actionsMenu.add(btnVerbrauchsgegenstand, 2, 0);
        actionsMenu.add(btnFliehen, 2, 2);

        detailMenu.setAlignment(Pos.CENTER);
        detailMenu.setSpacing(10);
        detailMenu.setPrefSize(960, 216);
        detailMenu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        untererBildschirm.getChildren().addAll(aktionsMenuContainer, detailMenuContainer);
        untererBildschirm.setMaxSize(1920, 216);
        untererBildschirm.setBackground(new Background(new BackgroundImage(new Image("background/actionsmenu_multifunktionsfenster_kampf.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 20, true, true, false, true))));
        aktionsMenuContainer.toFront();

        this.getChildren().addAll(kampfErgebnisContainer, untererBildschirm, hauptbildschirm, kampflogView,
                aktionAusgefuehrtInfoAnzeige);
        StackPane.setAlignment(hauptbildschirm, Pos.TOP_CENTER);
        StackPane.setAlignment(untererBildschirm, Pos.BOTTOM_LEFT);

        anzeigeVerbrauchsgegenstaende.getStyleClass().add("verbrauchsgegenstaende-list-view");
        anzeigeVerbrauchsgegenstaende.setPrefSize(800, 200);

        anzeigeFaehigkeiten.getStyleClass().add("faehigkeiten-list-view");
        anzeigeFaehigkeiten.setPrefSize(800, 200);
        anzeigeFaehigkeiten.setCellFactory(cell -> new FaehigkeitListCell());

        faehigkeitAbbrechen.setOnAction(event -> {
            faehigkeit = null;
            hideDetailMenu();
            updateKampfBildschirm();
        });

        anzeigeFaehigkeiten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateKampfBildschirm();
                detailMenuContainer.toFront();
                faehigkeit = newValue;
                if (faehigkeit.isIstFreundlich()) {
                    zielauswahlTeammitglieder();
                }
                else {
                    zielauswahlGegner();
                }
            }
        });

        verbrauchsgegenstaendeAbbrechen.setOnAction(event -> {
            verbrauchsgegenstand = null;
            hideDetailMenu();
            updateKampfBildschirm();
        });

        anzeigeVerbrauchsgegenstaende.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nutzeVerbrauchsgegenstand(newValue);
            }
        });

        hauptbildschirm.setMaxSize(1920, 864);

        keyAction = event -> {
            Node vordergrund = getChildren().get(getChildren().size() - 1);
            Node vordergrundMenu = untererBildschirm.getChildren().get(untererBildschirm.getChildren().size() - 1);
            // Kampflog wird angezeigt
            if (vordergrund == kampflogView) {
                if (event.getCode() == KeyCode.ENTER) {
                    kampflogAbbrechen.fire();
                }
            }
            // Aktions-Info wird angezeigt
            else if (vordergrund == aktionAusgefuehrtInfoAnzeige) {
                if (event.getCode() == KeyCode.ENTER) {
                    ok.fire();
                }
            }
            // Fähigkeiten-Auswahl wird angezeigt
            else if (vordergrundMenu == detailMenuContainer &&
                    detailMenu.getChildren().get(0) == anzeigeFaehigkeiten) {
                switch (event.getCode()) {
                    case A:
                    case ENTER:
                        faehigkeitAbbrechen.fire();
                }
            }
            // Gegenstand-Auswahl wird angezeigt
            else if (vordergrundMenu == detailMenuContainer &&
                    detailMenu.getChildren().get(0) == anzeigeVerbrauchsgegenstaende) {
                switch (event.getCode()) {
                    case A:
                    case ENTER:
                        verbrauchsgegenstaendeAbbrechen.fire();
                }
            }
            // Aktionen-Auswahl wird angezeigt
            else if (vordergrund == hauptbildschirm &&
                    vordergrundMenu == aktionsMenuContainer) {
                switch (event.getCode()) {
                    case A:
                        btnAngriff.fire();
                        break;
                    case B:
                        btnBlocken.fire();
                        break;
                    case F:
                        btnFliehen.fire();
                        break;
                    case G:
                        btnVerbrauchsgegenstand.fire();
                        break;
                    case K:
                        btnKampflog.fire();
                        break;
                }
            }
        };

        kampflogText.appendText("[" + timestamp() + "] " + "\nDER KAMPF HAT BEGONNEN\n");
        kampflogText.appendText(kampfController.backendFeedbackKampf());

        zielAuswahlFactory = new ZielAuswahlFactory(hauptbildschirm, kampf);
        charakterViewFactory = new CharakterViewFactory(hauptbildschirm);
    }

    public void addNiederlage() {
        kampfErgebnisContainer.getChildren().add(0, niederlage);
    }

    public void addSieg() {
        kampfErgebnisContainer.getChildren().add(0, sieg);
    }

    public void appendErgebnis(String text) {
        kampfErgebnis.setText(kampfErgebnis.getText().concat(text));
    }

    public void setFaehigkeit(Faehigkeit faehigkeit) {
        this.faehigkeit = faehigkeit;
    }

    public void setZielGruppe(List<Charakter> zielGruppe) {
        this.zielAuswahl = zielGruppe;
    }

    public void showErgebnis() {
        aktionAusgefuehrtInfoAnzeige.toBack();
        kampfErgebnisContainer.toFront();
        getScene().getWindow().removeEventHandler(KeyEvent.KEY_RELEASED, keyAction);
    }

    @Override
    public void toFront() {
        super.toFront();
        getScene().getWindow().addEventHandler(KeyEvent.KEY_RELEASED, keyAction);
    }

    /**
     * Anzeige vom Hauptbildschirm im Kampf wird hier aktualisiert
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public void updateKampfBildschirm() {
        Charakter aktuellerCharakter = kampfController.getNext();
        hauptbildschirm.getChildren().clear();
        hauptbildschirm.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        hauptbildschirm.setBackground(new Background(new BackgroundImage(new Image("background/kampfarena1.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 80, true, true, false, true))));

        actionsMenu.setGridLinesVisible(false);

        hauptbildschirm.getChildren().add(zugreihenfolgeAnzeige.charakterBox(aktuellerCharakter));
        hauptbildschirm.getChildren().add(zugreihenfolgeAnzeige.update(kampfController.zugreihenfolge()));

        for (int i = 0; i < kampfController.getParty().size(); i++) {
            SpielerCharakter charakter = kampfController.getParty().get(i);
            charakterViewFactory.prepareCharakterView(charakter, kampfController.blockt(charakter));
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
        for (int i = 0; i < kampfController.getFeinde().size(); i++) {
            Feind feind = kampfController.getFeinde().get(i);
            charakterViewFactory.prepareCharakterView(feind, kampfController.blockt(feind));
            if (feind.getGesundheitsPunkte() > 0) {
                charakterViewFactory.addFeind(aktuellerCharakter, i, feind);
            }
            else {
                charakterViewFactory.addFeindTot(i, feind);
            }
        }

        aktionsMenuContainer.toFront();
        kampflogView.toBack();
        aktionAusgefuehrtInfoAnzeige.toBack();
        StackPane.setAlignment(aktionsMenuContainer, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(detailMenuContainer, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(hauptbildschirm, Pos.TOP_CENTER);
        StackPane.setAlignment(untererBildschirm, Pos.BOTTOM_CENTER);

        if (kampf.getAktuellerCharakter() instanceof Feind) {
            if (!kampf.isKampfVorbei()) {
                kampfController.gegnerlogik();
                logFaehigkeitVerwendet();
            }
            else {
                showAktionAusgefuehrt();
            }
        }
    }

    private void blocken() {
        kampfController.blocken();
        logBlocken(kampfController.getLast());
        logStopBlockenNext();
        showAktionAusgefuehrt();
    }

    private void fliehen() {
        kampfController.fliehen();
        if (!kampf.isKampfVorbei()) {
            Charakter fliehenderCharakter = kampfController.getLast();
            aktionAusgefuehrtInfo.setText(
                    fliehenderCharakter.getName() + " hat versucht zu fliehen!\nDie Flucht ist fehlgeschlagen...\n");
            kampflogText.appendText("[" + timestamp() + "] " + "\n" + fliehenderCharakter.getName()
                    + " hat versucht zu fliehen! Die Flucht ist fehlgeschlagen...\n");
            kampflogText.appendText(kampfController.backendFeedbackKampf());
            logStopBlockenNext();
        }
        else {
            aktionAusgefuehrtInfo.setText(kampf.getAktuellerCharakter().getName()
                    + " hat versucht zu fliehen!\nDie Flucht war erfolgreich!\n'OK' drücken für Kampfauswertung.");
        }
        showAktionAusgefuehrt();
    }

    private void hideDetailMenu() {
        zielAuswahl.clear();
        detailMenuLeer.setBackground(null);
        detailMenu.getChildren().clear();
        detailMenuContainer.toBack();
    }

    private void logBlocken(Charakter charakter) {
        aktionAusgefuehrtInfo.setText((charakter.getName()
                + " fängt an zu blocken.\nBis zu seinem nächsten Zug blockt er\n"
                + charakter.getPhysischeAttacke() + " physischen Schaden und "
                + charakter.getMagischeAttacke() + " magischen Schaden.") + "\n");
        kampflogText.appendText("[" + timestamp() + "] " + "\n" +
                charakter.getName() + " fängt an zu blocken.\nBis zu seinem nächsten Zug blockt er " +
                charakter.getPhysischeAttacke() + " physischen Schaden und " +
                charakter.getMagischeAttacke() + " magischen Schaden." + "\n" +
                kampfController.backendFeedbackKampf());
    }

    private void logFaehigkeitVerwendet() {
        Charakter charakter = kampfController.getLast();
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
            ausgabe.append(" benutzt.\n");
            if (Game.DEBUG_MODUS) {
                ausgabe.append("[DEBUG] genutzte Fähigkeit: ").append(faehigkeit).append("\n");
            }
            ausgabe.append(kampfController.backendFeedbackKampf());
            aktionAusgefuehrtInfo.setText(ausgabe.toString());
            kampflogText.appendText("[" + timestamp() + "] " + "\n" + ausgabe);
            logStopBlockenNext();
        }
        else {
            kampfController.blocken();
            logBlocken(charakter);
        }
        faehigkeit = null;
        showAktionAusgefuehrtNachZielauswahl();
    }

    private void logStopBlockenNext() {
        Charakter blockenderCharakter = kampfController.getNext();
        if (kampfController.blockt(blockenderCharakter)) {
            aktionAusgefuehrtInfo.appendText("\n" + blockenderCharakter.getName() + "hört auf zu blocken.");
            kampflogText.appendText("[" + timestamp() + "] " + "\n"
                    + blockenderCharakter.getName() + "hört auf zu blocken.");
        }
    }

    private void logVerbrauchsgegenstandVerwendet() {
        Charakter charakter = kampfController.getLast();
        aktionAusgefuehrtInfo.setText(charakter.getName()
                + " hat '" + verbrauchsgegenstand.getName() + "' auf "
                + zielAuswahl.get(0).getName() + "\nbenutzt.\n");
        kampflogText.appendText("[" + timestamp() + "] " + "\n"
                + charakter.getName() + " hat '" + verbrauchsgegenstand.getName() + "' auf " +
                zielAuswahl.get(0).getName() + "benutzt.\n");
        logStopBlockenNext();
        verbrauchsgegenstand = null;
        showAktionAusgefuehrtNachZielauswahl();
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
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.getVerbrauchsgegenstaende()) {
            if (entry.getKey().getName().equals(verbrauchsgegenstandName)) {
                verbrauchsgegenstand = entry.getKey();
            }
        }
        zielauswahlTeammitglieder();
    }

    private void showAktionAusgefuehrt() {
        aktionAusgefuehrtInfoAnzeige.toFront();
        ok.requestFocus();
    }

    private void showAktionAusgefuehrtNachZielauswahl() {
        hideDetailMenu();
        showAktionAusgefuehrt();
    }

    private void showDetailMenu(Node focus) {
        detailMenuContainer.toFront();
        focus.requestFocus();
    }

    private void showKampflog() {
        kampflogView.toFront();
        kampflogAbbrechen.requestFocus();
    }

    private String timestamp() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
    }

    private void updateFaehigkeitenView(List<Faehigkeit> aktiveFaehigkeiten) {
        List<Faehigkeit> auswaehlbareFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : aktiveFaehigkeiten) {
            if (kampf.getAktuellerCharakter().getManaPunkte() >= faehigkeit.getManaKosten()) {
                auswaehlbareFaehigkeiten.add(faehigkeit);
            }
        }
        anzeigeFaehigkeiten.setItems(FXCollections.observableArrayList(auswaehlbareFaehigkeiten));
        anzeigeFaehigkeiten.getSelectionModel().selectFirst();
        detailMenuLeer.setBackground(FAINT_BACKGROUND);
        detailMenu.getChildren().addAll(anzeigeFaehigkeiten, faehigkeitAbbrechen);
        showDetailMenu(anzeigeFaehigkeiten);
    }

    private void updateGegenstaendeView() {
        List<String> partyVerbrauchsGegenstaende = new ArrayList<>();
        for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.getVerbrauchsgegenstaende()) {
            if (entry.getValue().get() > 0) {
                partyVerbrauchsGegenstaende.add(String.format("%dx %s (%s)", entry.getValue().get(), entry.getKey().getName(), entry.getKey().getBeschreibung()));
            }
        }
        anzeigeVerbrauchsgegenstaende.setItems(FXCollections.observableArrayList(partyVerbrauchsGegenstaende));
        anzeigeVerbrauchsgegenstaende.setPlaceholder(new PlaceHolder("Keine nutzbaren Verbrauchsgegenstände verfügbar"));
        anzeigeVerbrauchsgegenstaende.getSelectionModel().selectFirst();
        detailMenuLeer.setBackground(FAINT_BACKGROUND);
        detailMenu.getChildren().addAll(anzeigeVerbrauchsgegenstaende, verbrauchsgegenstaendeAbbrechen);
        showDetailMenu(anzeigeVerbrauchsgegenstaende);
    }

    /**
     * Auswahl von Feinden bei Fähigkeiten die man auf Feinde wirken muss
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    private void zielauswahlGegner() {
        hauptbildschirm.toFront();
        for (int i = 0; i < kampfController.getFeinde().size(); i++) {
            Feind feind = kampfController.getFeinde().get(i);
            zielAuswahlFactory.addFeind(i, feind, event -> {
                zielAuswahl.add(feind);
                if (zielAuswahl.size() == faehigkeit.getZielAnzahl()) {
                    kampfController.faehigkeitBenutzen(zielAuswahl, faehigkeit);
                    logFaehigkeitVerwendet();
                }
            });
        }
    }

    /**
     * Auswahl von Freunden bei Fähigkeiten und Gegenständen die man auf
     * Partymitglieder wirken muss
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    private void zielauswahlTeammitglieder() {
        hauptbildschirm.toFront();
        for (int i = 0; i < kampfController.getParty().size(); i++) {
            SpielerCharakter charakter = kampfController.getParty().get(i);
            zielAuswahlFactory.addTeam(i, charakter, event -> {
                zielAuswahl.add(charakter);
                if (verbrauchsgegenstand != null && zielAuswahl.size() == 1) {
                    kampfController.gegenstand(verbrauchsgegenstand, charakter);
                    logVerbrauchsgegenstandVerwendet();
                }
                else if (zielAuswahl.size() == faehigkeit.getZielAnzahl()) {
                    kampfController.faehigkeitBenutzen(zielAuswahl, faehigkeit);
                    logFaehigkeitVerwendet();
                }
            });
        }
    }

}
