package hauptmenu.neuesspiel;

import gamehub.GameHubController;
import hauptmenu.HauptmenuController;
import hauptmenu.gamecontroller.GameController;
import hauptmenu.speicherstand.SpeicherstandController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import party.Party;
import party.PartyController;
import statistik.StatistikController;
import view.ViewController;


public class NeuesSpielView extends BorderPane {


    public NeuesSpielView(ViewController viewController, HauptmenuController hauptmenuController,
                          SpeicherstandController speicherstandController) {

        this.setBackground(new Background(new BackgroundImage(new Image("hintergruende/neuesspiel.jpeg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));

        Text titel = new Text("Neues Spiel");
        HBox top = new HBox(titel);
        top.setAlignment(Pos.CENTER);
        this.setTop(top);

        TextField charakterName = new TextField();
        charakterName.setPromptText("Geben Sie den gewünschten Charakternamen ein!");

        ToggleGroup klassenwahl = new ToggleGroup();
        RadioButton tank = new RadioButton("Tank");
        tank.setToggleGroup(klassenwahl);
        RadioButton physischerDD = new RadioButton("Physischer DD");
        physischerDD.setToggleGroup(klassenwahl);
        RadioButton magischerDD = new RadioButton("Magischer DD");
        magischerDD.setToggleGroup(klassenwahl);
        RadioButton heiler = new RadioButton("Healer");
        heiler.setToggleGroup(klassenwahl);
        klassenwahl.selectToggle(tank);

        ToggleGroup schwierigkeitsGrad = new ToggleGroup();
        RadioButton leicht = new RadioButton("Leicht");
        leicht.setToggleGroup(schwierigkeitsGrad);
        RadioButton mittel = new RadioButton("Mittel");
        mittel.setToggleGroup(schwierigkeitsGrad);
        RadioButton schwer = new RadioButton("Schwer");
        schwer.setToggleGroup(schwierigkeitsGrad);
        schwierigkeitsGrad.selectToggle(leicht);

        CheckBox hardcore = new CheckBox("Harcoremodus");

        Button spielstart = new Button("Spiel starten");
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
        zurueck.setOnAction(event -> viewController.aktuelleNachHinten());
        HBox buttons = new HBox(spielstart, zurueck);
        buttons.setAlignment(Pos.CENTER);

        VBox menu = new VBox(charakterName, tank, physischerDD, magischerDD, heiler, leicht, mittel, schwer,
                hardcore,buttons);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(20));
        this.setCenter(menu);


    }

}
