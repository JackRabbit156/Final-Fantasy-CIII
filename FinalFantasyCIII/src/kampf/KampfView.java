package kampf;

import java.util.ArrayList;
import java.util.Random;

import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import javafx.geometry.HPos;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
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

public class KampfView extends VBox {
	private KampfController kampfController;
	ArrayList<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
	ArrayList<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
	ArrayList<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
	ArrayList<Feind> feindeDieNochLeben = new ArrayList<>();
	ArrayList<Feind> feindeDieNochActionHaben = new ArrayList<>();
	ArrayList<Charakter> blockendeCharaktere = new ArrayList<>();
	ArrayList<Charakter> gebuffteCharaktere = new ArrayList<>();
	ArrayList<Feind> feindeDieGestorbenSind = new ArrayList<>();
	ArrayList<SpielerCharakter> partyAnordnung = new ArrayList<>();
	ArrayList<Feind> gegnerAnordnung = new ArrayList<>();
	ArrayList<Charakter> aktuelleZugreihenfolge = new ArrayList<>();
	Random random = new Random();

	public KampfView(KampfController kampfController, ArrayList<SpielerCharakter> freundeDieGestorbenSind,
			ArrayList<SpielerCharakter> freundeDieNochLeben, ArrayList<SpielerCharakter> freundeDieNochActionHaben,
			ArrayList<Feind> feindeDieNochLeben, ArrayList<Feind> feindeDieNochActionHaben,
			ArrayList<Charakter> blockendeCharaktere, ArrayList<Charakter> gebuffteCharaktere,
			ArrayList<Feind> feindeDieGestorbenSind, SpielerCharakter hauptCharakterVorKampfbeginn,
			ArrayList<SpielerCharakter> nebenCharaktereVorKampfbeginn, ArrayList<SpielerCharakter> partyAnordnung,
			ArrayList<Feind> gegnerAnordnung, ArrayList<Charakter> aktuelleZugreihenfolge) {
		this.kampfController = kampfController;
		this.freundeDieGestorbenSind = freundeDieGestorbenSind;
		this.freundeDieNochLeben = freundeDieNochLeben;
		this.freundeDieNochActionHaben = freundeDieNochActionHaben;
		this.feindeDieNochLeben = feindeDieNochLeben;
		this.feindeDieGestorbenSind = feindeDieGestorbenSind;
		this.feindeDieNochActionHaben = feindeDieNochActionHaben;
		this.blockendeCharaktere = blockendeCharaktere;
		this.gebuffteCharaktere = gebuffteCharaktere;
		this.partyAnordnung = partyAnordnung;
		this.gegnerAnordnung = gegnerAnordnung;
		this.aktuelleZugreihenfolge = aktuelleZugreihenfolge;

		ColorAdjust deadGrey = new ColorAdjust();
		deadGrey.setBrightness(-0.67);
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
		String[] gegnerBilder = { "charaktere/gegnerChimera.png", "charaktere/gegnerGaruda.png",
				"charaktere/gegnerSahuagin.png" };
		Color levelBoxColor = Color.WHITE;
		String colorHealthBar;

		Pane hauptbildschirm = new Pane();
		hauptbildschirm.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		hauptbildschirm.setPrefSize(1920, 864);
		hauptbildschirm.setBackground(new Background(new BackgroundImage(new Image("background/kampfarena1.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(100, 80, true, true, false, true))));
		// unten
		GridPane actionsmenu = new GridPane();
		Button btnAngriff = new Button("Angriff");
		Button btnVerbrauchsgegenstand = new Button("Gegenstand");
		Button btnBlocken = new Button("Blocken");
		Button btnFliehen = new Button("Fliehen");
		Button btnKampflog = new Button("Kampflog");
		actionsmenu.setGridLinesVisible(false);

		for (int i = 0; i < partyAnordnung.size(); i++) {
			if ((partyAnordnung.get(i).getGesundheitsPunkte()
					/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.5) {
				colorHealthBar = "-fx-accent: #00FF00;";
			}
			else if ((partyAnordnung.get(i).getGesundheitsPunkte()
					/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.2) {
				colorHealthBar = "-fx-accent: #FF8C00;";
			}
			else {
				colorHealthBar = "-fx-accent: #FF0000;";
			}
			if (partyAnordnung.get(i).getKlasse() instanceof HLR) {
				levelBoxColor = Color.LIMEGREEN;
			}
			else if (partyAnordnung.get(i).getKlasse() instanceof MDD) {
				levelBoxColor = Color.CORNFLOWERBLUE;
			}
			else if (partyAnordnung.get(i).getKlasse() instanceof PDD) {
				levelBoxColor = Color.CRIMSON;
			}
			else if (partyAnordnung.get(i).getKlasse() instanceof TNK) {
				levelBoxColor = Color.GREY;
			}
			Rectangle levelBox = new Rectangle(40, 37);
			levelBox.setFill(levelBoxColor);
			levelBox.setStroke(Color.BLACK);

			Text nameDesCharakters = new Text(partyAnordnung.get(i).getName());
			nameDesCharakters.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 17));
			nameDesCharakters.setCache(true);
			nameDesCharakters.setCacheHint(CacheHint.SPEED);

			if (partyAnordnung.get(i).getGesundheitsPunkte() > 0) {
				// Lebender Charakter ist Hauptcharakter
				if (!partyAnordnung.get(i).isSoeldner()) {
					ImageView ivHauptcharakter = new ImageView(
							new Image("charaktere/keulenkrieger.png", 0, 216, true, true));
					ivHauptcharakter.setCache(true);
					ivHauptcharakter.setCacheHint(CacheHint.SPEED);
					ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
					ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (150 - (partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					ProgressBar manaBar = new ProgressBar(
							partyAnordnung.get(i).getManaPunkte() / (double) partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(partyAnordnung.get(i).getManaPunkte() + "/"
							+ partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					Text level = new Text(partyAnordnung.get(i).getLevel() + "");
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
				// Lebender Charakter ist Soeldner
				else {
					ImageView ivSoeldner = new ImageView(
							new Image("charaktere/scherttaenzerin.png", 0, 216, true, true));
					ivSoeldner.setCache(true);
					ivSoeldner.setCacheHint(CacheHint.SPEED);
					ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
					ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					Text level = new Text(partyAnordnung.get(i).getLevel() + "");
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

					ProgressBar manaBar = new ProgressBar(
							partyAnordnung.get(i).getManaPunkte() / (double) partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(partyAnordnung.get(i).getManaPunkte() + "/"
							+ partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (85 - (partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					hauptbildschirm.getChildren().addAll(ivSoeldner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
							nameDesCharakters);

				}
			}
			// Charakter hat am Anfang gelebt aber ist aktuell Tod
			else {
				// Toter Charakter ist Hauptcharakter
				if (!partyAnordnung.get(i).isSoeldner()) {
					ImageView ivHauptcharakter = new ImageView(
							new Image("charaktere/keulenkrieger.png", 0, 216, true, true));
					ivHauptcharakter.setEffect(deadGrey);
					ivHauptcharakter.setCache(true);
					ivHauptcharakter.setCacheHint(CacheHint.SPEED);
					ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
					ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);
					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneHP = new StackPane();
					stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPaneHP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPaneHP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();

					Text level = new Text(partyAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 25);
					stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

					ProgressBar manaBar = new ProgressBar(
							partyAnordnung.get(i).getManaPunkte() / (double) partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(partyAnordnung.get(i).getManaPunkte() + "/"
							+ partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (150 - (partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPaneHP, stackPaneMP,
							stackPaneLevelAnzeige, nameDesCharakters);
				}
				// Toter Charakter ist Soeldner
				else {
					ImageView ivSoeldner = new ImageView(
							new Image("charaktere/scherttaenzerin.png", 0, 216, true, true));
					ivSoeldner.setEffect(deadGrey);
					ivSoeldner.setCache(true);
					ivSoeldner.setCacheHint(CacheHint.SPEED);
					ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
					ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);
					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle(colorHealthBar);
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
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

					Text level = new Text(partyAnordnung.get(i).getLevel() + "");
					level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
					level.setCache(true);
					level.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneLevelAnzeige = new StackPane();
					stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
					stackPaneLevelAnzeige.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset - 40);
					stackPaneLevelAnzeige.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);

					ProgressBar manaBar = new ProgressBar(
							partyAnordnung.get(i).getManaPunkte() / (double) partyAnordnung.get(i).getMaxManaPunkte());
					manaBar.setPrefSize(manaBarWidth, manaBarHeight);
					manaBar.setStyle("-fx-accent: #00BFFF;");
					manaBar.setCache(true);
					manaBar.setCacheHint(CacheHint.SPEED);

					Text manaPunkteAlsText = new Text(partyAnordnung.get(i).getManaPunkte() + "/"
							+ partyAnordnung.get(i).getMaxManaPunkte() + " MP");
					manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					manaPunkteAlsText.setCache(true);
					manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

					StackPane stackPaneMP = new StackPane();
					stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
					stackPaneMP.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPaneMP.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset + 20);
					gesundheitsPunkteAlsText.toFront();

					nameDesCharakters.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset
							+ (85 - (partyAnordnung.get(i).getName().length() * 5.7)));
					nameDesCharakters.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset - 3);

					hauptbildschirm.getChildren().addAll(ivSoeldner, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige,
							nameDesCharakters);
				}
			}
		}
		for (int i = 0; i < gegnerAnordnung.size(); i++) {

			if ((gegnerAnordnung.get(i).getGesundheitsPunkte()
					/ (double) gegnerAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.5) {
				colorHealthBar = "-fx-accent: #00FF00;";
			}
			else if ((gegnerAnordnung.get(i).getGesundheitsPunkte()
					/ (double) gegnerAnordnung.get(i).getMaxGesundheitsPunkte()) >= 0.2) {
				colorHealthBar = "-fx-accent: #FF8C00;";
			}
			else {
				colorHealthBar = "-fx-accent: #FF0000;";
			}
			if (gegnerAnordnung.get(i).getKlasse() instanceof HLR) {
				levelBoxColor = Color.LIMEGREEN;
			}
			else if (gegnerAnordnung.get(i).getKlasse() instanceof MDD) {
				levelBoxColor = Color.CORNFLOWERBLUE;
			}
			else if (gegnerAnordnung.get(i).getKlasse() instanceof PDD) {
				levelBoxColor = Color.CRIMSON;
			}
			else if (gegnerAnordnung.get(i).getKlasse() instanceof TNK) {
				levelBoxColor = Color.GREY;
			}
			Rectangle levelBox = new Rectangle(40, 37);
			levelBox.setFill(levelBoxColor);
			levelBox.setStroke(Color.BLACK);

			Text nameDesCharakters = new Text(gegnerAnordnung.get(i).getName());
			nameDesCharakters.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 17));
			nameDesCharakters.setCache(true);
			nameDesCharakters.setCacheHint(CacheHint.SPEED);
			nameDesCharakters.setFill(Color.DARKRED);

			if (gegnerAnordnung.get(i).getGesundheitsPunkte() > 0) {
				ImageView ivGegner = new ImageView();
				ivGegner.setImage(new Image(gegnerBilder[random.nextInt(3)], 0, 216, true, true));
				ivGegner.setCache(true);
				ivGegner.setCacheHint(CacheHint.SPEED);
				ivGegner.setLayoutX(xPositionenGegnerBilder[i]);
				ivGegner.setLayoutY(yPositionenGegnerBilder[i]);

				ProgressBar healthBar = new ProgressBar(gegnerAnordnung.get(i).getGesundheitsPunkte()
						/ (double) gegnerAnordnung.get(i).getMaxGesundheitsPunkte());
				healthBar.setPrefSize(healthBarWidth, healthBarHeight);
				healthBar.setStyle(colorHealthBar);
				healthBar.setLayoutX(xPositionenGegnerBilder[i] + 90);
				healthBar.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset);
				healthBar.setCache(true);
				healthBar.setCacheHint(CacheHint.SPEED);

				nameDesCharakters.setLayoutX(xPositionenGegnerBilder[i] + xHealthBarOffset
						+ (165 - (gegnerAnordnung.get(i).getName().length() * 5.7)));
				nameDesCharakters.setLayoutY(yPositionenGegnerBilder[i] + yHealthBarOffset - 3);

				Text gesundheitsPunkteAlsText = new Text(gegnerAnordnung.get(i).getGesundheitsPunkte() + "/"
						+ gegnerAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
				gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				healthBar.setCache(true);
				healthBar.setCacheHint(CacheHint.SPEED);

				ProgressBar manaBar = new ProgressBar(
						gegnerAnordnung.get(i).getManaPunkte() / (double) gegnerAnordnung.get(i).getMaxManaPunkte());
				manaBar.setPrefSize(manaBarWidth, manaBarHeight);
				manaBar.setStyle("-fx-accent: #00BFFF;");
				manaBar.setCache(true);
				manaBar.setCacheHint(CacheHint.SPEED);

				Text manaPunkteAlsText = new Text(gegnerAnordnung.get(i).getManaPunkte() + "/"
						+ gegnerAnordnung.get(i).getMaxManaPunkte() + " MP");
				manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
				manaPunkteAlsText.setCache(true);
				manaPunkteAlsText.setCacheHint(CacheHint.SPEED);

				Text level = new Text(gegnerAnordnung.get(i).getLevel() + "");
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
		btnAngriff.getStyleClass().add("aktionwaehlenbutton");
		btnVerbrauchsgegenstand.getStyleClass().add("aktionwaehlenbutton");
		btnBlocken.getStyleClass().add("aktionwaehlenbutton");
		btnFliehen.getStyleClass().add("aktionwaehlenbutton");
		btnKampflog.getStyleClass().add("kampflogbutton");

//		actionsmenu.getColumnConstraints().setAll();
//		actionsmenu.getRowConstraints().setAll();

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
		actionsmenu.setBackground(
				new Background(new BackgroundImage(new Image("background/actionsmenu_multifunktionsfenster_kampf.png"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100, 100, true, true, false, true))));
		HBox detailmenu = new HBox();
		actionsmenu.setPrefSize(960, 216);
		detailmenu.setPrefSize(960, 216);
		detailmenu.setBackground(
				new Background(new BackgroundImage(new Image("background/actionsmenu_multifunktionsfenster_kampf.png"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100, 100, true, true, false, true))));
		actionsmenu.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		detailmenu.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		HBox menubereich = new HBox(actionsmenu, detailmenu);
		this.getChildren().addAll(hauptbildschirm, menubereich);
	}

}
