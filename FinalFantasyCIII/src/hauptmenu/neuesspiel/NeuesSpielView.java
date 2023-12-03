package hauptmenu.neuesspiel;

import charakter.model.klassen.HLR;
import charakter.model.klassen.MDD;
import charakter.model.klassen.PDD;
import charakter.model.klassen.TNK;
import charakter.model.klassen.spezialisierungen.Berserker;
import gamehub.GameHubController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import party.Party;
import party.PartyController;
import statistik.StatistikController;
import view.ViewController;


/**
 * NeuesSpielMenü
 *
 * @since 01.12.2023
 * @author Lang
 */
public class NeuesSpielView extends BorderPane {


    public NeuesSpielView(ViewController viewController, HauptmenuController hauptmenuController,
                          SpeicherstandController speicherstandController) {

        this.setBackground(new Background(new BackgroundImage(new Image("background/neuesspiel/neuesspiel.jpeg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));


        //Titel
        Text titel = new Text("Neues Spiel");
        titel.getStyleClass().add("text-neuesspiel");
        titel.setUnderline(true);
        HBox top = new HBox(titel);
        top.setAlignment(Pos.CENTER);
        this.setTop(top);

        //Charaktername
        TextField charakterName = new TextField();
        charakterName.setPromptText("Name eingeben!");
        charakterName.getStyleClass().add("text-field-neuesspiel");
        Platform.runLater(charakterName::requestFocus);

        //Klassenwahl
        Text klassentext = new Text("Klasse");
        klassentext.getStyleClass().add("text-neuesspiel-auswahl");
        ToggleGroup klassenwahl = new ToggleGroup();
        RadioButton tank = new RadioButton("Tank");
        Tooltip tankTooltip = new Tooltip(TNK.getGeschichte().replaceAll("#NAME#", "Charakter"));
        tankTooltip.setWrapText(true);
        tankTooltip.setMaxSize(800,800);
        tankTooltip.getStyleClass().add("tooltip-neuesspiel");
        tank.setTooltip(tankTooltip);
        tank.setToggleGroup(klassenwahl);
        tank.getStyleClass().add("radio-button-neuesspiel");
        RadioButton physischerDD = new RadioButton("Physischer DD");
        Tooltip pddTooltip = new Tooltip(PDD.getGeschichte().replaceAll("#NAME#", "Charakter"));
        pddTooltip.getStyleClass().add("tooltip-neuesspiel");
        pddTooltip.setWrapText(true);
        pddTooltip.setMaxSize(800,800);
        physischerDD.setToggleGroup(klassenwahl);
        physischerDD.getStyleClass().add("radio-button-neuesspiel");
        physischerDD.setTooltip(pddTooltip);
        RadioButton magischerDD = new RadioButton("Magischer DD");
        Tooltip mddTooltip = new Tooltip(MDD.getGeschichte().replaceAll("#NAME#", "Charakter"));
        mddTooltip.getStyleClass().add("tooltip-neuesspiel");
        mddTooltip.setWrapText(true);
        mddTooltip.setMaxSize(800,800);
        magischerDD.setTooltip(mddTooltip);
        magischerDD.setToggleGroup(klassenwahl);
        magischerDD.getStyleClass().add("radio-button-neuesspiel");
        RadioButton heiler = new RadioButton("Healer");
        Tooltip hlrTooltip = new Tooltip(HLR.getGeschichte().replaceAll("#NAME#", "Charakter"));
        hlrTooltip.getStyleClass().add("tooltip-neuesspiel");
        hlrTooltip.setWrapText(true);
        hlrTooltip.setMaxSize(800,800);
        heiler.setTooltip(hlrTooltip);
        heiler.setToggleGroup(klassenwahl);
        heiler.getStyleClass().add("radio-button-neuesspiel");
        klassenwahl.selectToggle(tank);

        //Schwierigkeitswahl
        Text schwierigkeitText = new Text("Schwierigkeit");
        schwierigkeitText.getStyleClass().add("text-neuesspiel-auswahl");
        ToggleGroup schwierigkeitsGrad = new ToggleGroup();
        RadioButton leicht = new RadioButton("Leicht");
        leicht.setToggleGroup(schwierigkeitsGrad);
        leicht.getStyleClass().add("radio-button-neuesspiel");
        RadioButton mittel = new RadioButton("Mittel");
        mittel.setToggleGroup(schwierigkeitsGrad);
        mittel.getStyleClass().add("radio-button-neuesspiel");
        RadioButton schwer = new RadioButton("Schwer");
        schwer.setToggleGroup(schwierigkeitsGrad);
        schwierigkeitsGrad.selectToggle(leicht);
        schwer.getStyleClass().add("radio-button-neuesspiel");

        //Hardcore-Checkbox
        CheckBox hardcore = new CheckBox("Hardcoremodus");
        hardcore.getStyleClass().add("check-box-neuesspiel");
        Tooltip hardcoreTip = new Tooltip("Hardcoremodus Modifikationen:\n" +
                "Wird ein Kampf verloren ist das Spiel vorbei.\n" +
                "Gestorbenen Charaktere können nicht wiederbelebt werden.\n" +
                "Speicherstände werden gelöscht");
        hardcoreTip.getStyleClass().add("tooltip-neuesspiel");
        hardcore.setTooltip(hardcoreTip);

        //Buttons
        Button spielstart = new Button("Spiel starten");
        spielstart.getStyleClass().add("hauptmenubutton");
        spielstart.disableProperty().bind(Bindings.isEmpty(charakterName.textProperty()));
        spielstart.setOnAction(event -> {
            RadioButton gewaehlteSchwierigkeitRB = (RadioButton) schwierigkeitsGrad.getSelectedToggle();
            String gewaehlteSchwierigkeit = gewaehlteSchwierigkeitRB.getText();
            RadioButton gewaehlteKlasseRB = (RadioButton) klassenwahl.getSelectedToggle();
            String gewaehlteKlasse = gewaehlteKlasseRB.getText();
            PartyController partyController = new PartyController(new Party(charakterName.getText(), gewaehlteKlasse));
            new GameHubController(new GameController(gewaehlteSchwierigkeit, hardcore.isSelected(),
                    partyController), partyController, new StatistikController(),hauptmenuController, speicherstandController,
                    viewController);
        });

        Button zurueck = new Button("Zurück");
        zurueck.getStyleClass().add("hauptmenubutton");
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        HBox buttons = new HBox(spielstart, zurueck);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        // Center-Styling
        VBox auswahl = new VBox(klassentext,tank, physischerDD, magischerDD, heiler,
                schwierigkeitText, leicht, mittel, schwer, hardcore);
        auswahl.setAlignment(Pos.CENTER_LEFT);
        auswahl.setPrefSize(300,240);
        HBox auswahlBox = new HBox(auswahl);
        auswahlBox.setAlignment(Pos.CENTER);
        auswahlBox.setMaxSize(400,240);
        auswahlBox.setPadding(new Insets(10));

        VBox menu = new VBox(charakterName, auswahlBox, buttons);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(20));
        menu.setMaxSize(640,480);
        menu.getStyleClass().add("VBoxStyle-neuesspiel");
        menu.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, new Insets(0))));

        Pane fire = new Pane();
        ImageView firegif = new ImageView(new Image("background/neuesspiel/fireGif.gif"));
        firegif.setFitHeight(240);
        firegif.setFitWidth(120);
        fire.getChildren().add(firegif);
        firegif.setLayoutX(945);
        firegif.setLayoutY(590);

        StackPane menuview = new StackPane(menu);

        ImageView anime = new ImageView(new Image("background/neuesspiel/animeGirls.gif", 300,0,true,true));
        StringProperty check = new SimpleStringProperty("Markus");
        anime.visibleProperty().bind(Bindings.equal(charakterName.textProperty(), check));
        anime.setLayoutX(50);
        anime.setLayoutY(200);
        fire.getChildren().add(anime);

        StackPane background = new StackPane(fire,menuview);
        this.setCenter(background);



    }

}
