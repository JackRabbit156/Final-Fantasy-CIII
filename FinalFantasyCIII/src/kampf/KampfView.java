package kampf;

import java.util.ArrayList;

import charakter.model.Charakter;
import charakter.model.Feind;
import charakter.model.SpielerCharakter;
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

	public KampfView(KampfController kampfController, ArrayList<SpielerCharakter> freundeDieGestorbenSind,
			ArrayList<SpielerCharakter> freundeDieNochLeben, ArrayList<SpielerCharakter> freundeDieNochActionHaben,
			ArrayList<Feind> feindeDieNochLeben, ArrayList<Feind> feindeDieNochActionHaben,
			ArrayList<Charakter> blockendeCharaktere, ArrayList<Charakter> gebuffteCharaktere,
			ArrayList<Feind> feindeDieGestorbenSind, SpielerCharakter hauptCharakterVorKampfbeginn,
			ArrayList<SpielerCharakter> nebenCharaktereVorKampfbeginn) {
		this.kampfController = kampfController;
		this.freundeDieGestorbenSind = freundeDieGestorbenSind;
		this.freundeDieNochLeben = freundeDieNochLeben;
		this.freundeDieNochActionHaben = freundeDieNochActionHaben;
		this.feindeDieNochLeben = feindeDieNochLeben;
		this.feindeDieGestorbenSind = feindeDieGestorbenSind;
		this.feindeDieNochActionHaben = feindeDieNochActionHaben;
		this.blockendeCharaktere = blockendeCharaktere;
		this.gebuffteCharaktere = gebuffteCharaktere;
		this.partyAnordnung.add(hauptCharakterVorKampfbeginn);
		this.partyAnordnung.addAll(nebenCharaktereVorKampfbeginn);

		ColorAdjust deadGrey = new ColorAdjust();
		deadGrey.setBrightness(-0.67);
		double[] xPositionenPartyBilder = { 580, 400, 220, 40 };
		double[] yPositionenPartyBilder = { 321, 381, 472, 586 };
		double xHealthBarOffset = 0;
		double yHealthBarOffset = -40;
		double healthBarWidth = 200;
		double healthBarHeight = 25;
		ArrayList<String> nameAllerLebendenPartyMitglieder = new ArrayList<>();
		ArrayList<String> geschichteAllerLebendenPartyMitglieder = new ArrayList<>();

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

		for (SpielerCharakter partymitglied : freundeDieNochLeben) {
			nameAllerLebendenPartyMitglieder.add(partymitglied.getName());
			geschichteAllerLebendenPartyMitglieder.add(partymitglied.getGeschichte());
		}
		for (int i = 0; i < partyAnordnung.size(); i++) {
			if (nameAllerLebendenPartyMitglieder.contains(partyAnordnung.get(i).getName())
					&& geschichteAllerLebendenPartyMitglieder.contains(partyAnordnung.get(i).getGeschichte())) {
				// Lebender Charakter ist Hauptcharakter
				if (!partyAnordnung.get(i).isSoeldner()) {
					ImageView ivHauptcharakter = new ImageView();
					ivHauptcharakter.setImage(new Image("charaktere/keulenkrieger.png"));
					ivHauptcharakter.prefHeight(100);
					ivHauptcharakter.prefWidth(100);
					ivHauptcharakter.setSmooth(true);
					ivHauptcharakter.setCache(true);
					ivHauptcharakter.setCacheHint(CacheHint.SPEED);
					ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
					ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle("-fx-accent: #00FF00;");
					healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.requestFocus();
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPane = new StackPane();
					stackPane.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPane.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPane.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPane);
				}
				// Lebender Charakter ist Soeldner
				else {
					ImageView ivSoeldner = new ImageView();
					ivSoeldner.setImage(new Image("charaktere/scherttaenzerin.png"));
					ivSoeldner.prefHeight(120);
					ivSoeldner.prefWidth(120);
					ivSoeldner.setSmooth(true);
					ivSoeldner.setCache(true);
					ivSoeldner.setCacheHint(CacheHint.SPEED);
					ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
					ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle("-fx-accent: #00FF00;");
					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.requestFocus();
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPane = new StackPane();
					stackPane.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPane.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPane.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivSoeldner, stackPane);

				}
			}
			// Charakter hat am Anfang gelebt aber ist aktuell Tod
			else {
				// Toter Charakter ist Hauptcharakter
				if (!partyAnordnung.get(i).isSoeldner()) {
					ImageView ivHauptcharakter = new ImageView();
					ivHauptcharakter.setImage(new Image("charaktere/keulenkrieger.png"));
					ivHauptcharakter.prefHeight(100);
					ivHauptcharakter.prefWidth(100);
					ivHauptcharakter.setEffect(deadGrey);
					ivHauptcharakter.setSmooth(true);
					ivHauptcharakter.setCache(true);
					ivHauptcharakter.setCacheHint(CacheHint.SPEED);
					ivHauptcharakter.setLayoutX(xPositionenPartyBilder[i]);
					ivHauptcharakter.setLayoutY(yPositionenPartyBilder[i]);

					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle("-fx-accent: #00FF00;");
					healthBar.setLayoutX(xPositionenPartyBilder[i] + 90);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);
					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					gesundheitsPunkteAlsText.requestFocus();
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPane = new StackPane();
					stackPane.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPane.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset + 65);
					stackPane.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivHauptcharakter, stackPane);
				}
				// Toter Charakter ist Soeldner
				else {
					ImageView ivSoeldner = new ImageView();
					ivSoeldner.setImage(new Image("charaktere/scherttaenzerin.png"));
					ivSoeldner.prefHeight(120);
					ivSoeldner.prefWidth(120);
					ivSoeldner.setEffect(deadGrey);
					ivSoeldner.setSmooth(true);
					ivSoeldner.setCache(true);
					ivSoeldner.setCacheHint(CacheHint.SPEED);
					ivSoeldner.setLayoutX(xPositionenPartyBilder[i]);
					ivSoeldner.setLayoutY(yPositionenPartyBilder[i]);
					ProgressBar healthBar = new ProgressBar(partyAnordnung.get(i).getGesundheitsPunkte()
							/ (double) partyAnordnung.get(i).getMaxGesundheitsPunkte());
					healthBar.setPrefSize(healthBarWidth, healthBarHeight);
					healthBar.setStyle("-fx-accent: #00FF00;");
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					Text gesundheitsPunkteAlsText = new Text(partyAnordnung.get(i).getGesundheitsPunkte() + "/"
							+ partyAnordnung.get(i).getMaxGesundheitsPunkte() + " HP");
					healthBar.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					healthBar.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					healthBar.setCache(true);
					healthBar.setCacheHint(CacheHint.SPEED);

					StackPane stackPane = new StackPane();
					stackPane.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
					stackPane.setLayoutX(xPositionenPartyBilder[i] + xHealthBarOffset);
					stackPane.setLayoutY(yPositionenPartyBilder[i] + yHealthBarOffset);
					gesundheitsPunkteAlsText.toFront();
					hauptbildschirm.getChildren().addAll(ivSoeldner, stackPane);
				}
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
