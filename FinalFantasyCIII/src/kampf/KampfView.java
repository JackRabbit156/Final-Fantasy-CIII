package kampf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import trainer.faehigkeiten.Faehigkeit;

public class KampfView extends StackPane {
	private KampfController kampfController;
	boolean istEingabeNotwendig = false;

	StackPane zugreihenfolgeAnzeigeMitKasten = new StackPane();
	Verbrauchsgegenstand verbrauchsgegenstand = null;
	Faehigkeit faehigkeit;
	Pane hauptbildschirm = new Pane();
	StackPane untererBildschirm = new StackPane();
	GridPane actionsmenu = new GridPane();
	Button ok = new Button("OK");
	Button btnAngriff = new Button("Angriff");
	Button btnVerbrauchsgegenstand = new Button("Gegenstand");
	Button btnBlocken = new Button("Blocken");
	Button btnFliehen = new Button("Fliehen");
	Button btnKampflog = new Button("Kampflog");
	HBox detailmenu = new HBox();
	Button kampflogAbbrechen = new Button("Zurück zum Kampf");
	Button faehigkeitAbbrechen = new Button("Abbrechen");
	Button verbrauchsgegenstandAbbrechen = new Button("Abbrechen");
	Button faehigkeitAuswaehlen = new Button("Auswählen");
	Button verbrauchsgegenstandAuswaehlen = new Button("Auswählen");
	String[] gegnerBilder = { "charaktere/gegnerChimera.png", "charaktere/gegnerGaruda.png",
			"charaktere/gegnerGaruda.png", "charaktere/gegnerSahuagin.png" };
	Color levelBoxColor = Color.WHITE;
	Color aktuellerCharakterBoxColor = Color.WHITE;
	String colorHealthBar;
	ColorAdjust deadGrey = new ColorAdjust();
	HBox zugreihenfolgeAnzeige = new HBox();
	BorderPane kampflogView = new BorderPane();
	BorderPane aktionAusgefuehrtInfoAnzeige = new BorderPane();
	TextArea aktionAusgefuehrtInfo = new TextArea();
	Label kampfErgebnis = new Label();
	Button kampfErgebnisBestaetigen = new Button("OK");
	ImageView sieg = new ImageView(new Image("/icons/sieg.png", 0.0, 320.0, true, false));
	ImageView niederlage = new ImageView(new Image("/icons/niederlage.png", 0.0, 320.0, true, false));
	VBox kampfErgebnisContainer = new VBox();
	TextArea kampflogText = new TextArea();
	Rectangle aktuellerCharakterBox = new Rectangle(65, 50);

	ListView<Faehigkeit> anzeigeFaehigkeiten = new ListView<>();
	ListView<String> anzeigeVerbrauchsgegenstaende = new ListView<>();
	ObservableList<Faehigkeit> olAktiveFaehigkeiten = FXCollections.observableArrayList();
	ObservableList<String> olVerbrauchsgegenstaende = FXCollections.observableArrayList();
	ArrayList<Charakter> zielAuswahl = new ArrayList<>();
	double[] xPosyPosAktuellerCharakter = { 700, 620 };
	double[] xPositionenPartyBilder = { 600, 430, 260, 90 };
	double[] yPositionenPartyBilder = { 350, 440, 530, 620 };
	double[] xPositionenGegnerBilder = { 1000, 1200, 1400, 1600 };
	double[] yPositionenGegnerBilder = { 350, 440, 530, 620 };
	double xHealthBarOffset = 0;
	double yHealthBarOffset = -40;
	double healthBarWidth = 170;
	double healthBarHeight = 25;
	double manaBarWidth = 170;
	double manaBarHeight = 15;
	int anzahlZiele = 0;

	HBox actionsmenuContainer = new HBox();
	HBox detailmenuContainer = new HBox();

	public KampfView(KampfController kampfController) {

		this.kampfController = kampfController;
		kampflogText.setEditable(false);
		kampflogText.setMaxSize(750, 300);
		kampflogView.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8);");
		kampflogView.setCenter(kampflogText);
		HBox kampfLogBottom = new HBox();
		HBox kampfLogCenter = new HBox();
		kampfLogBottom.setPrefSize(1920, 450);
		kampfLogCenter.setPrefSize(1920, 550);
		kampflogText.setPadding(new Insets(0, 0, 1, 0));
		kampflogAbbrechen.setMinWidth(190);
		kampflogAbbrechen.setMaxWidth(190);
		kampfLogBottom.getChildren().add(kampflogAbbrechen);
		kampfLogCenter.getChildren().add(kampflogText);
		kampflogAbbrechen.getStyleClass().add("kampflogbutton");
		kampflogAbbrechen.setAlignment(Pos.TOP_CENTER);
		kampfLogBottom.setAlignment(Pos.TOP_CENTER);
		kampfLogCenter.setAlignment(Pos.CENTER);
		kampflogView.setCenter(kampfLogCenter);
		kampflogView.setBottom(kampfLogBottom);

		faehigkeitAbbrechen.getStyleClass().add("kampflogbutton");
		verbrauchsgegenstandAbbrechen.getStyleClass().add("kampflogbutton");
		faehigkeitAuswaehlen.getStyleClass().add("kampflogbutton");
		verbrauchsgegenstandAuswaehlen.getStyleClass().add("kampflogbutton");

		VBox anordnungAktionsInfo = new VBox();
		HBox aktionObenLeer = new HBox();
		HBox aktionLinksLeer = new HBox();
		HBox aktionRechtsLeer = new HBox();
		HBox aktionUntenLeer = new HBox();
		aktionObenLeer.setPrefSize(1920, 320);
		aktionUntenLeer.setPrefSize(1920, 580);
		aktionLinksLeer.setPrefSize(760, 200);
		aktionRechtsLeer.setPrefSize(760, 200);
		aktionAusgefuehrtInfo.setPrefSize(300, 100);
		aktionAusgefuehrtInfo.setEditable(false);
		kampfErgebnis.setMinSize(500, 250);
//		kampfErgebnis.setEditable(false);
		kampfErgebnis.getStyleClass().add("kampfErgebnisArea");
		ok.getStyleClass().add("kampflogbutton");
		kampfErgebnisBestaetigen.getStyleClass().add("kampflogbutton");
		kampfErgebnisBestaetigen.setOnAction(event -> kampfController.zurueckZumHub());
		anordnungAktionsInfo.getChildren().addAll(aktionAusgefuehrtInfo, ok);
		anordnungAktionsInfo.setAlignment(Pos.CENTER);
		anordnungAktionsInfo.setSpacing(10);
		kampfErgebnisContainer.getChildren().addAll( kampfErgebnis, kampfErgebnisBestaetigen);
//		kampfErgebnis.setMaxWidth(400.0);
		kampfErgebnisContainer.setAlignment(Pos.CENTER);
		kampfErgebnisContainer.setStyle("-fx-background-color: rgba(0, 125, 125, 0.625);");
		kampfErgebnisContainer.setSpacing(10.0);
		aktionAusgefuehrtInfoAnzeige.setStyle("-fx-background-color: rgba(0, 125, 125, 0.625);");
		aktionAusgefuehrtInfoAnzeige.setCenter(anordnungAktionsInfo);
		aktionAusgefuehrtInfoAnzeige.setTop(aktionObenLeer);
		aktionAusgefuehrtInfoAnzeige.setBottom(aktionUntenLeer);
		aktionAusgefuehrtInfoAnzeige.setLeft(aktionLinksLeer);
		aktionAusgefuehrtInfoAnzeige.setRight(aktionRechtsLeer);

		HBox actionsmenuLeer = new HBox();
		HBox detailmenuLeer = new HBox();
		actionsmenuLeer.setMinSize(960, 216);
		detailmenuLeer.setMinSize(960, 216);
		actionsmenuContainer.getChildren().addAll(actionsmenu, actionsmenuLeer);
		detailmenuContainer.getChildren().addAll(detailmenuLeer, detailmenu);

		btnAngriff.getStyleClass().add("aktionwaehlenbutton");
		btnVerbrauchsgegenstand.getStyleClass().add("aktionwaehlenbutton");
		btnBlocken.getStyleClass().add("aktionwaehlenbutton");
		btnFliehen.getStyleClass().add("aktionwaehlenbutton");
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

		actionsmenu.getColumnConstraints().addAll(col[0], col[1], col[2]);
		actionsmenu.getRowConstraints().addAll(row[0], row[1], row[2]);

		actionsmenu.add(btnAngriff, 0, 0);
		actionsmenu.add(btnBlocken, 0, 2);
		actionsmenu.add(btnKampflog, 1, 1);
		actionsmenu.add(btnVerbrauchsgegenstand, 2, 0);
		actionsmenu.add(btnFliehen, 2, 2);

		actionsmenu.setPrefSize(960, 216);
		detailmenu.setPrefSize(960, 216);
		actionsmenu.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		detailmenu.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		untererBildschirm.getChildren().addAll(actionsmenuContainer, detailmenuContainer);
		untererBildschirm.setMaxSize(1920, 216);
		untererBildschirm.setBackground(
				new Background(new BackgroundImage(new Image("background/actionsmenu_multifunktionsfenster_kampf.png"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100, 20, true, true, false, true))));
		actionsmenuContainer.toFront();
		kampflogText.appendText("[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
				+ LocalDateTime.now().getSecond() + "] " + "\nDER KAMPF HAT BEGONNEN");

		this.getChildren().addAll(kampfErgebnisContainer, hauptbildschirm, untererBildschirm, kampflogView,
				aktionAusgefuehrtInfoAnzeige);
		StackPane.setAlignment(hauptbildschirm, Pos.TOP_CENTER);
		StackPane.setAlignment(untererBildschirm, Pos.BOTTOM_LEFT);

		btnAngriff.setOnMouseClicked(event -> updateFaehigkeitenView(
				KampfController.getAktiveFaehigkeiten(kampfController.aktuellerCharakter)));

		btnVerbrauchsgegenstand.setOnMouseClicked(event -> updateGegenstaendeView());

		btnKampflog.setOnMouseClicked(event -> kampflogView.toFront());

		btnFliehen.setOnMouseClicked(event -> {
			kampfController.fliehen();
			if (!kampfController.istKampfVorbei[0]) {
				aktionAusgefuehrtInfo.setText(
						kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1)
								.getName() + " hat versucht zu fliehen!\nDie Flucht ist fehlgeschlagen...");
				kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
						+ ":" + LocalDateTime.now().getSecond() + "] " + "\n" + kampfController.aktuelleZugreihenfolge
								.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName()
						+ " hat versucht zu fliehen!\nDie Flucht ist fehlgeschlagen...");
				if (kampfController.blockendeCharaktere.contains(kampfController.aktuelleZugreihenfolge.get(0))) {
					aktionAusgefuehrtInfo
							.appendText("\n" + kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
					kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":"
							+ LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond() + "] " + "\n"
							+ kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
				}
				aktionAusgefuehrtInfoAnzeige.toFront();
			}
			else {
				aktionAusgefuehrtInfo.setText(kampfController.aktuellerCharakter.getName()
						+ " hat versucht zu fliehen!\nDie Flucht war erfolgreich!\n'OK' drücken für Kampfauswertung.");
			}
			aktionAusgefuehrtInfoAnzeige.toFront();
		});

		btnBlocken.setOnMouseClicked(event -> {
			kampfController.blocken();
			aktionAusgefuehrtInfo
					.setText(kampfController.aktuelleZugreihenfolge
							.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName()
							+ " faengt an zu blocken\n" + "Bis zu seinem naechsten Zug blockt er \n"
							+ kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getPhysischeAttacke()
							+ " physischen und "
							+ kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getMagischeAttacke()
							+ " magischen Schaden.");
			kampflogText
					.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
							+ LocalDateTime.now().getSecond() + "] " + "\n"
							+ kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName()
							+ " faengt an zu blocken\n" + "Bis zu seinem naechsten Zug blockt er \n"
							+ kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getPhysischeAttacke()
							+ " physischen und "
							+ kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getMagischeAttacke()
							+ " magischen Schaden.");
			if (kampfController.blockendeCharaktere.contains(kampfController.aktuelleZugreihenfolge.get(0))) {
				aktionAusgefuehrtInfo
						.appendText("\n" + kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
				kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
						+ ":" + LocalDateTime.now().getSecond() + "] " + "\n"
						+ kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
			}
			aktionAusgefuehrtInfoAnzeige.toFront();
		});

		ok.setOnMouseClicked(event -> {
			if (kampfController.istKampfVorbei[0]) {
				kampfController.kampfAuswerten();
			}
			else {
				hauptbildschirm.getChildren().clear();
				fuehreAktionDurch();
			}
		});

		kampflogAbbrechen.setOnMouseClicked(event -> kampflogView.toBack());

		faehigkeitAbbrechen.setOnMouseClicked(event -> {
			detailmenu.getChildren().clear();
			detailmenu.setPrefSize(960, 216);
			detailmenuContainer.toBack();
			updateKampfBildschirm();
		});

		faehigkeitAuswaehlen.setOnMouseClicked(event -> {
			setzeFaehigkeit();
			if (faehigkeit.isIstFreundlich()) {
				zielauswahlTeammitglieder(faehigkeit.getZielAnzahl());
			}
			else {
				zielauswahlGegnerteam(faehigkeit.getZielAnzahl());
			}
		});

		verbrauchsgegenstandAuswaehlen.setOnMouseClicked(event -> {
			setzeVerbrauchsgegenstand();
			zielauswahlTeammitglieder(1);
		});

		verbrauchsgegenstandAbbrechen.setOnMouseClicked(event -> {
			detailmenu.getChildren().clear();
			detailmenu.setPrefSize(960, 216);
			detailmenuContainer.toBack();
			updateKampfBildschirm();
		});

		updateKampfBildschirm();
	}

	public void updateKampfBildschirm() {

		zugreihenfolgeAnzeige.getChildren().clear();
		zugreihenfolgeAnzeigeMitKasten.getChildren().clear();
		hauptbildschirm.getChildren().clear();

		Charakter aktuellerCharakter = kampfController.aktuelleZugreihenfolge.get(0);
		deadGrey.setBrightness(-0.67);

		hauptbildschirm.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		hauptbildschirm.setBackground(new Background(new BackgroundImage(new Image("background/kampfarena1.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(100, 80, true, true, false, true))));

		actionsmenu.setGridLinesVisible(false);
		zugreihenfolgeAnzeige.getStyleClass().add("leisteTooltip");
		zugreihenfolgeAnzeige.setPrefWidth(720);
		zugreihenfolgeAnzeige.setPrefHeight(50);
		zugreihenfolgeAnzeige.setLayoutX(600);
		zugreihenfolgeAnzeige.setLayoutY(30);
		zugreihenfolgeAnzeige.setOpacity(0.4);

		if (aktuellerCharakter.getKlasse() instanceof HLR) {
			aktuellerCharakterBoxColor = Color.LIMEGREEN;
		}
		else if (aktuellerCharakter.getKlasse() instanceof PDD) {
			aktuellerCharakterBoxColor = Color.CRIMSON;
		}
		else if (aktuellerCharakter.getKlasse() instanceof MDD) {
			aktuellerCharakterBoxColor = Color.CORNFLOWERBLUE;
		}
		else {
			aktuellerCharakterBoxColor = Color.GREY;
		}

		aktuellerCharakterBox.setFill(Color.TRANSPARENT);
		aktuellerCharakterBox.setStroke(aktuellerCharakterBoxColor);
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
				ivHauptcharakterAnzeige.setCache(true);
				ivHauptcharakterAnzeige.setCacheHint(CacheHint.SPEED);
				Tooltip.install(ivHauptcharakterAnzeige, ttCharakterAnzeige);
				zugreihenfolgeAnzeige.getChildren().add(ivHauptcharakterAnzeige);
			}
			else if (charakter instanceof SpielerCharakter) {
				ImageView ivSoeldnerAnzeige = new ImageView(
						new Image(charakter.getGrafischeDarstellung(), 0, 45, true, true));
				ivSoeldnerAnzeige.setCache(true);
				ivSoeldnerAnzeige.setCacheHint(CacheHint.SPEED);
				Tooltip.install(ivSoeldnerAnzeige, ttCharakterAnzeige);
				zugreihenfolgeAnzeige.getChildren().add(ivSoeldnerAnzeige);
			}
			else {
				ImageView ivGegnerAnzeige = new ImageView(new Image(gegnerBilder[0], 0, 45, true, true));
				ivGegnerAnzeige.setCache(true);
				ivGegnerAnzeige.setCacheHint(CacheHint.SPEED);
				Tooltip.install(ivGegnerAnzeige, ttCharakterAnzeige);
				zugreihenfolgeAnzeige.getChildren().add(ivGegnerAnzeige);
			}
		}
		zugreihenfolgeAnzeige.setSpacing(35);
		zugreihenfolgeAnzeige.setPadding(new Insets(0, 0, 0, 20));
		hauptbildschirm.getChildren().add(zugreihenfolgeAnzeige);

		for (int i = 0; i < kampfController.partyAnordnung.size(); i++) {
			if ((kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
					/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.5) {
				colorHealthBar = "-fx-accent: #00FF00;";
			}
			else if ((kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
					/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.2) {
				colorHealthBar = "-fx-accent: #FF8C00;";
			}
			else {
				colorHealthBar = "-fx-accent: #FF0000;";
			}
			if (kampfController.partyAnordnung.get(i).getKlasse() instanceof HLR) {
				levelBoxColor = Color.LIMEGREEN;
			}
			else if (kampfController.partyAnordnung.get(i).getKlasse() instanceof MDD) {
				levelBoxColor = Color.CORNFLOWERBLUE;
			}
			else if (kampfController.partyAnordnung.get(i).getKlasse() instanceof PDD) {
				levelBoxColor = Color.CRIMSON;
			}
			else if (kampfController.partyAnordnung.get(i).getKlasse() instanceof TNK) {
				levelBoxColor = Color.GREY;
			}
			Rectangle levelBox = new Rectangle(40, 37);
			levelBox.setFill(levelBoxColor);
			levelBox.setStroke(Color.BLACK);

			Text nameDesCharakters = new Text(kampfController.partyAnordnung.get(i).getName());
			nameDesCharakters.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 17));
			nameDesCharakters.setCache(true);
			nameDesCharakters.setCacheHint(CacheHint.SPEED);

			if (kampfController.partyAnordnung.get(i).getGesundheitsPunkte() > 0) {
				// Lebender Charakter ist Hauptcharakter
				if (!kampfController.partyAnordnung.get(i).isSoeldner()) {
					if (kampfController.partyAnordnung.get(i) != aktuellerCharakter) {
						ImageView ivHauptcharakter = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivHauptcharakter.setCache(true);
						ivHauptcharakter.setCacheHint(CacheHint.SPEED);
						ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
						ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

						ProgressBar healthBar = new ProgressBar(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
										/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
						healthBar.setPrefSize(healthBarWidth, healthBarHeight);
						healthBar.setStyle(colorHealthBar);
						healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
						healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
								+ (150 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
						nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

						Text gesundheitsPunkteAlsText = new Text(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
										+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
						gesundheitsPunkteAlsText
								.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
								/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
						manaBar.setPrefSize(manaBarWidth, manaBarHeight);
						manaBar.setStyle("-fx-accent: #00BFFF;");
						manaBar.setCache(true);
						manaBar.setCacheHint(CacheHint.SPEED);

						Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
								+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
						manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
						manaPunkteAlsText.setCache(true);
						manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

						Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
						level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
						level.setCache(true);
						level.setCacheHint(CacheHint.SPEED);

						StackPane stackPaneLevelAnzeige = new StackPane();
						stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
						stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 25);
						stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

						StackPane stackPaneMP = new StackPane();
						stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
						stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
						stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
						gesundheitsPunkteAlsText.toFront();

						StackPane stackPaneHP = new StackPane();
						stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
						stackPaneHP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
						stackPaneHP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
						gesundheitsPunkteAlsText.toFront();
						hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPaneHP, stackPaneMP,
								stackPaneLevelAnzeige, nameDesCharakters);
					}
					else {
						ImageView ivHauptcharakter = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivHauptcharakter.setCache(true);
						ivHauptcharakter.setCacheHint(CacheHint.SPEED);
						ivHauptcharakter.setLayoutX(xPosyPosAktuellerCharakter[0]);
						ivHauptcharakter.setLayoutY(xPosyPosAktuellerCharakter[1]);
						ProgressBar healthBar = new ProgressBar(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
										/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
						healthBar.setPrefSize(healthBarWidth, healthBarHeight);
						healthBar.setStyle(colorHealthBar);
						healthBar.setLayoutX(xPosyPosAktuellerCharakter[0] + 90);
						healthBar.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						nameDesCharakters.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset
								+ (150 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
						nameDesCharakters.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset - 3);

						Text gesundheitsPunkteAlsText = new Text(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
										+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
						gesundheitsPunkteAlsText
								.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
								/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
						manaBar.setPrefSize(manaBarWidth, manaBarHeight);
						manaBar.setStyle("-fx-accent: #00BFFF;");
						manaBar.setCache(true);
						manaBar.setCacheHint(CacheHint.SPEED);

						Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
								+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
						manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
						manaPunkteAlsText.setCache(true);
						manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

						Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
						level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
						level.setCache(true);
						level.setCacheHint(CacheHint.SPEED);

						StackPane stackPaneLevelAnzeige = new StackPane();
						stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
						stackPaneLevelAnzeige.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 25);
						stackPaneLevelAnzeige.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);

						StackPane stackPaneMP = new StackPane();
						stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
						stackPaneMP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
						stackPaneMP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset + 20);
						gesundheitsPunkteAlsText.toFront();

						StackPane stackPaneHP = new StackPane();
						stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
						stackPaneHP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
						stackPaneHP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
						gesundheitsPunkteAlsText.toFront();
						hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPaneHP, stackPaneMP,
								stackPaneLevelAnzeige, nameDesCharakters);
					}
				}
				// Lebender Charakter ist Soeldner
				else {
					if (kampfController.partyAnordnung.get(i) != aktuellerCharakter) {
						ImageView ivSoeldner = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivSoeldner.setCache(true);
						ivSoeldner.setCacheHint(CacheHint.SPEED);
						ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
						ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);

						ProgressBar healthBar = new ProgressBar(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
										/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
						healthBar.setPrefSize(healthBarWidth, healthBarHeight);
						healthBar.setStyle(colorHealthBar);
						Text gesundheitsPunkteAlsText = new Text(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
										+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
						gesundheitsPunkteAlsText
								.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
						healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
						healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
						level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
						level.setCache(true);
						level.setCacheHint(CacheHint.SPEED);

						StackPane stackPaneLevelAnzeige = new StackPane();
						stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
						stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset - 40);
						stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

						StackPane stackPaneHP = new StackPane();
						stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
						stackPaneHP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
						stackPaneHP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
						gesundheitsPunkteAlsText.toFront();

						ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
								/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
						manaBar.setPrefSize(manaBarWidth, manaBarHeight);
						manaBar.setStyle("-fx-accent: #00BFFF;");
						manaBar.setCache(true);
						manaBar.setCacheHint(CacheHint.SPEED);

						Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
								+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
						manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
						manaPunkteAlsText.setCache(true);
						manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

						StackPane stackPaneMP = new StackPane();
						stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
						stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
						stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
						gesundheitsPunkteAlsText.toFront();

						nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
								+ (85 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
						nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

						hauptbildschirm.getChildren().addAll(ivSoeldner, stackPaneHP, stackPaneMP,
								stackPaneLevelAnzeige, nameDesCharakters);

					}
					else {

						ImageView ivSoeldner = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivSoeldner.setCache(true);
						ivSoeldner.setCacheHint(CacheHint.SPEED);
						ivSoeldner.setLayoutX(xPosyPosAktuellerCharakter[0]);
						ivSoeldner.setLayoutY(xPosyPosAktuellerCharakter[1]);

						ProgressBar healthBar = new ProgressBar(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
										/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
						healthBar.setPrefSize(healthBarWidth, healthBarHeight);
						healthBar.setStyle(colorHealthBar);
						healthBar.setLayoutX(xPosyPosAktuellerCharakter[0] + 90);
						healthBar.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						nameDesCharakters.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset
								+ (150 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
						nameDesCharakters.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset - 3);

						Text gesundheitsPunkteAlsText = new Text(
								kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
										+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
						gesundheitsPunkteAlsText
								.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
						healthBar.setCache(true);
						healthBar.setCacheHint(CacheHint.SPEED);

						ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
								/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
						manaBar.setPrefSize(manaBarWidth, manaBarHeight);
						manaBar.setStyle("-fx-accent: #00BFFF;");
						manaBar.setCache(true);
						manaBar.setCacheHint(CacheHint.SPEED);

						Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
								+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
						manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
						manaPunkteAlsText.setCache(true);
						manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

						Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
						level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
						level.setCache(true);
						level.setCacheHint(CacheHint.SPEED);

						StackPane stackPaneLevelAnzeige = new StackPane();
						stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
						stackPaneLevelAnzeige.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 25);
						stackPaneLevelAnzeige.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);

						StackPane stackPaneMP = new StackPane();
						stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
						stackPaneMP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
						stackPaneMP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset + 20);
						gesundheitsPunkteAlsText.toFront();

						StackPane stackPaneHP = new StackPane();
						stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
						stackPaneHP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
						stackPaneHP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
						gesundheitsPunkteAlsText.toFront();
						hauptbildschirm.getChildren().addAll(ivSoeldner, stackPaneHP, stackPaneMP,
								stackPaneLevelAnzeige, nameDesCharakters);
					}
				}
			}
			// Charakter hat am Anfang gelebt aber ist aktuell Tod
			else {
				// Toter Charakter ist Hauptcharakter
				if (!kampfController.partyAnordnung.get(i).isSoeldner()) {
					ImageView ivHauptcharakter = new ImageView(new Image(
							kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
					ivHauptcharakter.setEffect(deadGrey);
					ivHauptcharakter.setCache(true);
					ivHauptcharakter.setCacheHint(CacheHint.SPEED);
					ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
					ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);
					Text gesundheitsPunkteAlsText = new Text(
							kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
									+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneHP = new StackPane();
					stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPaneHP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPaneHP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();

					Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 25);
					stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

					ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
							/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
							+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (150 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPaneHP, stackPaneMP,
							stackPaneLevelAnzeige, nameDesCharakters);
				}
				// Toter Charakter ist Soeldner
				else {
					ImageView ivSoeldner = new ImageView(new Image(
							kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
					ivSoeldner.setEffect(deadGrey);
					ivSoeldner.setCache(true);
					ivSoeldner.setCacheHint(CacheHint.SPEED);
					ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
					ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);
					ProgressBar healthBar = new ProgressBar(kampfController.partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					Text gesundheitsPunkteAlsText = new Text(
							kampfController.partyAnordnung.get(i).getGesundheitsPunkte() + "/"
									+ kampfController.partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneHP = new StackPane();
					stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPaneHP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPaneHP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();

					Text level = new Text(kampfController.partyAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset - 40);
					stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

					ProgressBar manaBar = new ProgressBar(kampfController.partyAnordnung.get(i).getManaPunkte()
							/ (double) kampfController.partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(kampfController.partyAnordnung.get(i).getManaPunkte() + "/"
							+ kampfController.partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (85 - (kampfController.partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					hauptbildschirm.getChildren().addAll(ivSoeldner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
							nameDesCharakters);
				}
			}
		}
		for (int i = 0; i < kampfController.gegnerAnordnung.size(); i++) {

			if ((kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
					/ (double) kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.5) {
				colorHealthBar = "-fx-accent: #00FF00;";
			}
			else if ((kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
					/ (double) kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.2) {
				colorHealthBar = "-fx-accent: #FF8C00;";
			}
			else {
				colorHealthBar = "-fx-accent: #FF0000;";
			}
			if (kampfController.gegnerAnordnung.get(i).getKlasse() instanceof HLR) {
				levelBoxColor = Color.LIMEGREEN;
			}
			else if (kampfController.gegnerAnordnung.get(i).getKlasse() instanceof MDD) {
				levelBoxColor = Color.CORNFLOWERBLUE;
			}
			else if (kampfController.gegnerAnordnung.get(i).getKlasse() instanceof PDD) {
				levelBoxColor = Color.CRIMSON;
			}
			else if (kampfController.gegnerAnordnung.get(i).getKlasse() instanceof TNK) {
				levelBoxColor = Color.GREY;
			}
			Rectangle levelBox = new Rectangle(40, 37);
			levelBox.setFill(levelBoxColor);
			levelBox.setStroke(Color.BLACK);

			Text nameDesCharakters = new Text(kampfController.gegnerAnordnung.get(i).getName());
			nameDesCharakters.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 17));
			nameDesCharakters.setCache(true);
			nameDesCharakters.setCacheHint(CacheHint.SPEED);
			nameDesCharakters.setFill(Color.DARKRED);

			if (kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte() > 0) {
				if (kampfController.gegnerAnordnung.get(i) != aktuellerCharakter) {
					ImageView ivGegner = new ImageView();
					ivGegner.setImage(new Image(gegnerBilder[i], 0, 216, true, true));
					ivGegner.setCache(true);
					ivGegner.setCacheHint(CacheHint.SPEED);
					ivGegner.setLayoutX(xPositionenGegnerBilder[i]);
					ivGegner.setLayoutY(yPositionenGegnerBilder[i]);

					ProgressBar healthBar = new ProgressBar(
							kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
									/ (double) kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenGegnerBilder[i] + 90);
					healthBar.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					nameDesCharakters.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset
							+ (165 - (kampfController.gegnerAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset - 3);

					Text gesundheitsPunkteAlsText = new Text(
							kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte() + "/"
									+ kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					ProgressBar manaBar = new ProgressBar(kampfController.gegnerAnordnung.get(i).getManaPunkte()
							/ (double) kampfController.gegnerAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(kampfController.gegnerAnordnung.get(i).getManaPunkte() + "/"
							+ kampfController.gegnerAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					Text level = new Text(kampfController.gegnerAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 209 + 25);
					stackPaneLevelAnzeige.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 65);
					stackPaneMP.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					StackPane stackPaneHP = new StackPane();
					stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPaneHP.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 65);
					stackPaneHP.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivGegner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
							nameDesCharakters);
				}
				else {
					ImageView ivGegner = new ImageView();
					ivGegner.setImage(new Image(gegnerBilder[i], 0, 216, true, true));
					ivGegner.setCache(true);
					ivGegner.setCacheHint(CacheHint.SPEED);
					ivGegner.setLayoutX(xPosyPosAktuellerCharakter[0]);
					ivGegner.setLayoutY(xPosyPosAktuellerCharakter[1]);

					ProgressBar healthBar = new ProgressBar(
							kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
									/ (double) kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPosyPosAktuellerCharakter[0] + 90);
					healthBar.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					nameDesCharakters.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset
							+ (165 - (kampfController.gegnerAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset - 3);

					Text gesundheitsPunkteAlsText = new Text(
							kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte() + "/"
									+ kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					ProgressBar manaBar = new ProgressBar(kampfController.gegnerAnordnung.get(i).getManaPunkte()
							/ (double) kampfController.gegnerAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(kampfController.gegnerAnordnung.get(i).getManaPunkte() + "/"
							+ kampfController.gegnerAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					Text level = new Text(kampfController.gegnerAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 209 + 25);
					stackPaneLevelAnzeige.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
					stackPaneMP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					StackPane stackPaneHP = new StackPane();
					stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPaneHP.setLayoutX(xPosyPosAktuellerCharakter[0] + xHealthBarOffset + 65);
					stackPaneHP.setLayoutY(xPosyPosAktuellerCharakter[1] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivGegner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
							nameDesCharakters);
				}
			}
			else {
				ImageView ivGegner = new ImageView();
				ivGegner.setEffect(deadGrey);
				ivGegner.setImage(new Image(gegnerBilder[i], 0, 216, true, true));
				ivGegner.setCache(true);
				ivGegner.setCacheHint(CacheHint.SPEED);
				ivGegner.setLayoutX(xPositionenGegnerBilder[i]);
				ivGegner.setLayoutY(yPositionenGegnerBilder[i]);

				ProgressBar healthBar = new ProgressBar(kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
						/ (double) kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte());
				healthBar.setPrefSize(healthBarWidth, healthBarHeight);
				healthBar.setStyle(colorHealthBar);
				healthBar.setLayoutX(xPositionenGegnerBilder[i] + 90);
				healthBar.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);
				healthBar.setCache(true);
				healthBar.setCacheHint(CacheHint.SPEED);

				nameDesCharakters.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset
						+ (165 - (kampfController.gegnerAnordnung.get(i).getName().length() * 5.7)));
				nameDesCharakters.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset - 3);

				Text gesundheitsPunkteAlsText = new Text(kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte()
						+ "/" + kampfController.gegnerAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
				gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				healthBar.setCache(true);
				healthBar.setCacheHint(CacheHint.SPEED);

				ProgressBar manaBar = new ProgressBar(kampfController.gegnerAnordnung.get(i).getManaPunkte()
						/ (double) kampfController.gegnerAnordnung.get(i).getMaxManaPunkte());
				manaBar.setPrefSize(manaBarWidth, manaBarHeight);
				manaBar.setStyle("-fx-accent: #00BFFF;");
				manaBar.setCache(true);
				manaBar.setCacheHint(CacheHint.SPEED);

				Text manaPunkteAlsText = new Text(kampfController.gegnerAnordnung.get(i).getManaPunkte() + "/"
						+ kampfController.gegnerAnordnung.get(i).getMaxManaPunkte() + " MP");
				manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
				manaPunkteAlsText.setCache(true);
				manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

				Text level = new Text(kampfController.gegnerAnordnung.get(i).getLevel() + "");
				level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
				level.setCache(true);
				level.setCacheHint(CacheHint.SPEED);

				StackPane stackPaneLevelAnzeige = new StackPane();
				stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
				stackPaneLevelAnzeige.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 209 + 25);
				stackPaneLevelAnzeige.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);

				StackPane stackPaneMP = new StackPane();
				stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
				stackPaneMP.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 65);
				stackPaneMP.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset + 20);
				gesundheitsPunkteAlsText.toFront();

				StackPane stackPaneHP = new StackPane();
				stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
				stackPaneHP.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset + 65);
				stackPaneHP.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);
				gesundheitsPunkteAlsText.toFront();
				hauptbildschirm.getChildren().addAll(ivGegner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
						nameDesCharakters);
			}
		}

		hauptbildschirm.setMaxSize(1920, 864);
		untererBildschirm.setMaxSize(1920, 216);
		actionsmenuContainer.toFront();
		actionsmenuContainer.setMaxSize(1920, 216);
		detailmenuContainer.setMaxSize(1920, 216);
		kampflogView.toBack();
		aktionAusgefuehrtInfoAnzeige.toBack();
		StackPane.setAlignment(actionsmenuContainer, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(detailmenuContainer, Pos.BOTTOM_RIGHT);
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

	private void fuehreAktionDurch() {
		updateKampfBildschirm();
	}

	private void updateFaehigkeitenView(ArrayList<Faehigkeit> cKAktiveFaehigkeiten) {
		ArrayList<Faehigkeit> cKAktiveFaehigkeitenMana = new ArrayList<>();
		anzeigeFaehigkeiten.setCellFactory(cell -> new ListCell<Faehigkeit>() {
			final Tooltip tooltip = new Tooltip();

			@Override
			public void updateItem(Faehigkeit faehigkeit, boolean empty) {
				super.updateItem(faehigkeit, empty);
				if (empty || faehigkeit == null) {
					setText(null);
					setTooltip(null);
				}
				else {
					tooltip.setText(faehigkeit.getBeschreibung());
					setTooltip(tooltip);
					setText(String.format("%-30s%3s%12s%d", faehigkeit.getName(), "|  ", "Manakosten: ",
							faehigkeit.getManaKosten()));
				}
			}
		});
		for (Faehigkeit faehigkeit : new ArrayList<Faehigkeit>(cKAktiveFaehigkeiten)) {
			if (kampfController.aktuellerCharakter.getManaPunkte() >= faehigkeit.getManaKosten()) {
				cKAktiveFaehigkeitenMana.add(faehigkeit);
			}
		}
		olAktiveFaehigkeiten = FXCollections.observableArrayList(cKAktiveFaehigkeitenMana);
		anzeigeFaehigkeiten.setItems(olAktiveFaehigkeiten);
		anzeigeFaehigkeiten.getSelectionModel().selectFirst();
		anzeigeFaehigkeiten.setStyle(" -fx-control-inner-background: #7C8FA8;"
				+ " -fx-control-inner-background-alt: derive(-fx-control-inner-background, 20%);"
				+ " -fx-font-size: 20px; -fx-font-family: 'SketchFlow Print';");
		anzeigeFaehigkeiten.setPrefSize(770, 200);
//		anzeigeFaehigkeiten.setFixedCellSize(value);
		detailmenu.getChildren().add(anzeigeFaehigkeiten);
		detailmenu.setAlignment(Pos.CENTER);
		detailmenuContainer.toFront();
		detailmenu.getChildren().addAll(faehigkeitAuswaehlen, faehigkeitAbbrechen);
		detailmenu.setSpacing(10);

	}

	private void updateGegenstaendeView() {
		ArrayList<String> partyVerbrauchsgegenstaende = new ArrayList<>();
		for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.party.getVerbrauchsgegenstaende()
				.entrySet()) {
			if (entry.getValue().get() > 0) {
				partyVerbrauchsgegenstaende.add(entry.getKey().getName() + ", " + entry.getValue().get());
			}
		}
		olVerbrauchsgegenstaende = FXCollections.observableArrayList(partyVerbrauchsgegenstaende);
		anzeigeVerbrauchsgegenstaende.setItems(olVerbrauchsgegenstaende);
		anzeigeVerbrauchsgegenstaende.getSelectionModel().selectFirst();
		anzeigeVerbrauchsgegenstaende.setStyle(" -fx-control-inner-background: #D5A85A;"
				+ " -fx-control-inner-background-alt: derive(-fx-control-inner-background, 20%);"
				+ " -fx-font-size: 20px; -fx-font-family: 'SketchFlow Print';");
		anzeigeVerbrauchsgegenstaende.setPrefSize(770, 200);
//		anzeigeFaehigkeiten.setFixedCellSize(value);
		detailmenu.getChildren().add(anzeigeVerbrauchsgegenstaende);
		detailmenu.setAlignment(Pos.CENTER);
		detailmenuContainer.toFront();
		detailmenu.getChildren().addAll(verbrauchsgegenstandAuswaehlen, verbrauchsgegenstandAbbrechen);
		detailmenu.setSpacing(10);
	}

	public void setzeFaehigkeit() {
		faehigkeit = anzeigeFaehigkeiten.getSelectionModel().getSelectedItem();
	}

	public void setzeVerbrauchsgegenstand() {
		String verbrauchsgegenstandString = anzeigeVerbrauchsgegenstaende.getSelectionModel().getSelectedItem();
		String[] tmp = verbrauchsgegenstandString.split(",");
		String verbrauchsgegenstandName = tmp[0].trim();
		for (Map.Entry<Verbrauchsgegenstand, IntegerProperty> entry : kampfController.party.getVerbrauchsgegenstaende()
				.entrySet()) {
			if (entry.getKey().getName().equals(verbrauchsgegenstandName)) {
				verbrauchsgegenstand = entry.getKey();
			}
		}
	}

	public void verbrauchsgegenstandVerwendet() {
		aktionAusgefuehrtInfo.setText(
				kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName()
						+ " hat den Gegenstand '" + verbrauchsgegenstand.getName() + "' auf "
						+ zielAuswahl.get(0).getName() + "\nbenutzt.\n");
		kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
				+ LocalDateTime.now().getSecond() + "] " + "\n"
				+ kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1)
						.getName()
				+ " hat den Gegenstand '" + verbrauchsgegenstand.getName() + "' auf " + zielAuswahl.get(0).getName()
				+ "\nbenutzt.\n");
		if (kampfController.blockendeCharaktere.contains(kampfController.aktuelleZugreihenfolge.get(0))) {
			aktionAusgefuehrtInfo
					.appendText("\n" + kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
			kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
					+ ":" + LocalDateTime.now().getSecond() + "] " + "\n"
					+ kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
		}
		verbrauchsgegenstand = null;
		zielAuswahl.clear();
		detailmenu.getChildren().clear();
		detailmenu.setPrefSize(960, 216);
		detailmenuContainer.toBack();
		aktionAusgefuehrtInfoAnzeige.toFront();
	}

	public void faehigkeitVerwendet() {

		if (faehigkeit != null) {
			String ausgabe = (kampfController.aktuelleZugreihenfolge
					.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName() + " hat die Fähigkeit '"
					+ faehigkeit.getName() + "' auf: ");
			for (int counter = 0; counter < zielAuswahl.size(); counter++) {
				ausgabe += ("\n" + zielAuswahl.get(counter).getName());
			}
			ausgabe += "\nbenutzt." + backendFeedbackKampf();
			aktionAusgefuehrtInfo.setText(ausgabe);
			kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
					+ ":" + LocalDateTime.now().getSecond() + "] " + "\n" + ausgabe);
			if (kampfController.blockendeCharaktere.contains(kampfController.aktuelleZugreihenfolge.get(0))) {
				aktionAusgefuehrtInfo
						.appendText("\n" + kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
				kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
						+ ":" + LocalDateTime.now().getSecond() + "] " + "\n"
						+ kampfController.aktuelleZugreihenfolge.get(0) + "hört auf zu blocken.");
			}
			faehigkeit = null;
			zielAuswahl.clear();
			detailmenu.getChildren().clear();
			detailmenu.setPrefSize(960, 216);
			detailmenuContainer.toBack();
			aktionAusgefuehrtInfoAnzeige.toFront();
		}
		else {
			String ausgabe = (kampfController.aktuelleZugreihenfolge
					.get(kampfController.aktuelleZugreihenfolge.size() - 1).getName()
					+ " Fängt an zu blocken\nBis zu seinem nächsten Zug blockt er\n"
					+ (kampfController.aktuelleZugreihenfolge.get(kampfController.aktuelleZugreihenfolge.size() - 1)
							.getPhysischeAttacke()
							+ " physischen Schaden und "
							+ (kampfController.aktuelleZugreihenfolge
									.get(kampfController.aktuelleZugreihenfolge.size() - 1).getMagischeAttacke()
									+ " magischen Schaden.\n")));
			aktionAusgefuehrtInfo.setText(ausgabe);
			kampflogText.appendText("\n\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute()
					+ ":" + LocalDateTime.now().getSecond() + "] " + "\n" + ausgabe);
			faehigkeit = null;
			zielAuswahl.clear();
			detailmenu.getChildren().clear();
			detailmenu.setPrefSize(960, 216);
			detailmenuContainer.toBack();
			aktionAusgefuehrtInfoAnzeige.toFront();
		}
	}

	public void zielauswahlTeammitglieder(int anzahlZiele) {
		hauptbildschirm.toFront();
		for (int i = 0; i < kampfController.partyAnordnung.size(); i++) {
			if (kampfController.partyAnordnung.get(i).getGesundheitsPunkte() > 0) {
				// Hauptcharakter ist im Aktionsbereich
				if (!kampfController.partyAnordnung.get(i).isSoeldner()) {
					if (kampfController.partyAnordnung.get(i) != kampfController.aktuellerCharakter) {
						int index = i;
						ImageView ivHauptcharakter = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivHauptcharakter.setCache(true);
						ivHauptcharakter.setCacheHint(CacheHint.SPEED);
						ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
						ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);
						ivHauptcharakter.getStyleClass().add("teamCharakterHover");
						ivHauptcharakter.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
							zielAuswahl.add(kampfController.partyAnordnung.get(index));
							if (verbrauchsgegenstand != null && zielAuswahl.size() == anzahlZiele) {
								kampfController.gegenstand(verbrauchsgegenstand,
										kampfController.partyAnordnung.get(index));
								verbrauchsgegenstandVerwendet();
							}
							else if (zielAuswahl.size() == anzahlZiele) {
								kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter, zielAuswahl,
										faehigkeit);
								faehigkeitVerwendet();
							}
							event.consume();
						});
						hauptbildschirm.getChildren().addAll(ivHauptcharakter);
					}
					else {
						// Hauptcharakter ist im Hintergrund
						int index = i;
						ImageView ivHauptcharakter = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivHauptcharakter.setCache(true);
						ivHauptcharakter.setCacheHint(CacheHint.SPEED);
						ivHauptcharakter.setLayoutX(xPosyPosAktuellerCharakter[0]);
						ivHauptcharakter.setLayoutY(xPosyPosAktuellerCharakter[1]);
						ivHauptcharakter.getStyleClass().add("teamCharakterHover");
						ivHauptcharakter.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

							ImageView ivHauptcharakterGeklickt = new ImageView(
									new Image(kampfController.partyAnordnung.get(index).getGrafischeDarstellung(), 0,
											216, true, true));
							ivHauptcharakterGeklickt.setCache(true);
							ivHauptcharakterGeklickt.setCacheHint(CacheHint.SPEED);
							ivHauptcharakterGeklickt.setLayoutX(xPosyPosAktuellerCharakter[0]);
							ivHauptcharakterGeklickt.setLayoutY(xPosyPosAktuellerCharakter[1]);
							ivHauptcharakterGeklickt.getStyleClass().add("teamCharakterAusgewaehlt");
							hauptbildschirm.getChildren().addAll(ivHauptcharakterGeklickt);
							zielAuswahl.add(kampfController.partyAnordnung.get(index));
							if (verbrauchsgegenstand != null && zielAuswahl.size() == anzahlZiele) {
								kampfController.gegenstand(verbrauchsgegenstand,
										kampfController.partyAnordnung.get(index));
								verbrauchsgegenstandVerwendet();
							}
							else if (zielAuswahl.size() == anzahlZiele) {
								kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter, zielAuswahl,
										faehigkeit);
								faehigkeitVerwendet();
							}
							event.consume();
						});
						hauptbildschirm.getChildren().addAll(ivHauptcharakter);
					}
				}
				// Lebender Charakter ist Soeldner
				else {
					// Soeldner ist im Aktionsbereich
					if (kampfController.partyAnordnung.get(i) != kampfController.aktuellerCharakter) {
						int index = i;
						ImageView ivSoeldner = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivSoeldner.setCache(true);
						ivSoeldner.setCacheHint(CacheHint.SPEED);
						ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
						ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);
						ivSoeldner.getStyleClass().add("teamCharakterHover");
						ivSoeldner.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
							ImageView ivSoeldnerGeklickt = new ImageView(
									new Image(kampfController.partyAnordnung.get(index).getGrafischeDarstellung(), 0,
											216, true, true));
							ivSoeldnerGeklickt.setCache(true);
							ivSoeldnerGeklickt.setCacheHint(CacheHint.SPEED);
							ivSoeldnerGeklickt.setLayoutX(xPositionenPartyBilder[index]);
							ivSoeldnerGeklickt.setLayoutY(yPositionenPartyBilder[index]);
							ivSoeldnerGeklickt.getStyleClass().add("teamCharakterAusgewaehlt");
							hauptbildschirm.getChildren().addAll(ivSoeldnerGeklickt);
							zielAuswahl.add(kampfController.partyAnordnung.get(index));
							if (verbrauchsgegenstand != null && zielAuswahl.size() == anzahlZiele) {
								kampfController.gegenstand(verbrauchsgegenstand,
										kampfController.partyAnordnung.get(index));
								verbrauchsgegenstandVerwendet();
							}
							else if (zielAuswahl.size() == anzahlZiele) {
								kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter, zielAuswahl,
										faehigkeit);
								faehigkeitVerwendet();
							}
							event.consume();
						});
						hauptbildschirm.getChildren().addAll(ivSoeldner);
					}
					// Soeldner ist im Hintergrund
					else {
						int index = i;
						ImageView ivSoeldner = new ImageView(new Image(
								kampfController.partyAnordnung.get(i).getGrafischeDarstellung(), 0, 216, true, true));
						ivSoeldner.setCache(true);
						ivSoeldner.setCacheHint(CacheHint.SPEED);
						ivSoeldner.setLayoutX(xPosyPosAktuellerCharakter[0]);
						ivSoeldner.setLayoutY(xPosyPosAktuellerCharakter[1]);
						ivSoeldner.getStyleClass().add("teamCharakterHover");
						ivSoeldner.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
							ImageView ivSoeldnerGeklickt = new ImageView(
									new Image(kampfController.partyAnordnung.get(index).getGrafischeDarstellung(), 0,
											216, true, true));
							ivSoeldnerGeklickt.setCache(true);
							ivSoeldnerGeklickt.setCacheHint(CacheHint.SPEED);
							ivSoeldnerGeklickt.setLayoutX(xPosyPosAktuellerCharakter[0]);
							ivSoeldnerGeklickt.setLayoutY(xPosyPosAktuellerCharakter[1]);
							ivSoeldnerGeklickt.getStyleClass().add("teamCharakterAusgewaehlt");
							hauptbildschirm.getChildren().addAll(ivSoeldnerGeklickt);
							zielAuswahl.add(kampfController.partyAnordnung.get(index));
							if (verbrauchsgegenstand != null && zielAuswahl.size() == anzahlZiele) {
								kampfController.gegenstand(verbrauchsgegenstand,
										kampfController.partyAnordnung.get(index));
								verbrauchsgegenstandVerwendet();
							}
							else if (zielAuswahl.size() == anzahlZiele) {
								kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter, zielAuswahl,
										faehigkeit);
								faehigkeitVerwendet();
							}
							event.consume();
						});
						hauptbildschirm.getChildren().addAll(ivSoeldner);
					}
				}
			}
		}
	}

	public void zielauswahlGegnerteam(int anzahlZiele) {
		hauptbildschirm.toFront();
		for (int i = 0; i < kampfController.gegnerAnordnung.size(); i++) {
			if (kampfController.gegnerAnordnung.get(i).getGesundheitsPunkte() > 0) {
				if (kampfController.gegnerAnordnung.get(i) != kampfController.aktuellerCharakter) {
					int index = i;
					ImageView ivGegner = new ImageView();
					ivGegner.setImage(new Image(gegnerBilder[i], 0, 216, true, true));
					ivGegner.setCache(true);
					ivGegner.setCacheHint(CacheHint.SPEED);
					ivGegner.getStyleClass().add("gegnerCharakterHover");
					ivGegner.setLayoutX(xPositionenGegnerBilder[i]);
					ivGegner.setLayoutY(yPositionenGegnerBilder[i]);
					ivGegner.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						ImageView ivGegnerGeklickt = new ImageView(new Image(gegnerBilder[index], 0, 216, true, true));
						ivGegnerGeklickt.setCache(true);
						ivGegnerGeklickt.setCacheHint(CacheHint.SPEED);
						ivGegnerGeklickt.setLayoutX(xPositionenGegnerBilder[index]);
						ivGegnerGeklickt.setLayoutY(yPositionenGegnerBilder[index]);
						ivGegnerGeklickt.getStyleClass().add("gegnerCharakterAusgewaehlt");
						hauptbildschirm.getChildren().addAll(ivGegnerGeklickt);
						zielAuswahl.add(kampfController.gegnerAnordnung.get(index));
						if (zielAuswahl.size() == anzahlZiele) {
							kampfController.faehigkeitBenutzen(kampfController.aktuellerCharakter, zielAuswahl,
									faehigkeit);
							faehigkeitVerwendet();
						}
					});
					hauptbildschirm.getChildren().addAll(ivGegner);
				}
			}
		}
	}

	public String backendFeedbackKampf() {
		String returnString = "\n";
		for (int counter = 0; counter < kampfController.kampfWerteLog.size(); counter++) {
			returnString += kampfController.kampfWerteLog.get(counter);
		}
		return returnString;
	}

	public void setZielGruppe(ArrayList<Charakter> zielGruppe) {
		this.zielAuswahl = zielGruppe;
	}

	public void setFaehigkeit(Faehigkeit faehigkeit) {
		this.faehigkeit = faehigkeit;
	}
}
