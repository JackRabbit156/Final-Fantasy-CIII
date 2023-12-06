package statistik;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import party.PartyController;
import view.AnsichtsTyp;
import view.ViewController;

public class GameOverView extends VBox {
    /**
     * Konstruktor für die GameOverView-Klasse.
     * Die GameOverView-Klasse zeigt die namensgebende "GameOver"-Ansicht,
     * welche angezeigt wird wenn der GameOver-Zustand erreicht wird (nach einem Kampf alle Mitglieder tot, und kein Gold für das wiederbeleben mehr vorhanden).
     *
     * @author Dennis
     * @since 05.12.2023
     * @param statistik         Zum Anzeigen der Statistik.
     * @param partyController   Der PartyController zum Anzeigen des Namens und des Levels
     * @param viewController    Der ViewController um ins Hauptmenü zu kommen
     */
    public GameOverView(Statistik statistik, PartyController partyController, ViewController viewController){
        this.setBackground(new Background(new BackgroundImage(new Image("gameover/schwarzerHintergrund.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        ImageView firegif = new ImageView(new Image("gameover/gameovergif.gif"));

        HBox buttonsHBox = new HBox();
        Button hauptmenu = new Button("Zurück zum Hauptmenü");
        hauptmenu.getStyleClass().add("hauptmenubutton");
        hauptmenu.setOnAction(event -> viewController.anmelden(viewController.getHauptmenuView(), null, AnsichtsTyp.OHNE_OVERLAY));
        Button spielBeenden = new Button("Spiel Beenden");
        spielBeenden.getStyleClass().add("hauptmenubutton");
        spielBeenden.setOnAction(event -> Platform.exit());
        buttonsHBox.getChildren().addAll(hauptmenu,spielBeenden);
        buttonsHBox.setSpacing(40);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setPadding(new Insets(50,0,0,0));

        VBox statistikVBox = new VBox();
        Label spielername = new Label("Spielername: " + partyController.getParty().getHauptCharakter().getName());
        Label erreichtesLevel = new Label("Erreichtes Level: " + partyController.getParty().getHauptCharakter().getLevel());
        Label durchgefuehrteKaempfe = new Label("Durchgeführte Kämpfe: " + statistik.getDurchgefuehrteKaempfe());
        Label gewonneneKaempfe = new Label("Gewonnene Kämpfe: " + statistik.getGewonneneKaempfe());
        Label verloreneKaempfe = new Label("Verlorene Kämpfe: " + statistik.getVerloreneKaempfe());
        Label erwirtschaftetesGold = new Label("Erwirtschaftetes Gold: " + statistik.getGesamtErwirtschaftetesGold());
        statistikVBox.getChildren().addAll(spielername,erreichtesLevel,durchgefuehrteKaempfe,gewonneneKaempfe,verloreneKaempfe,erwirtschaftetesGold);
        statistikVBox.setAlignment(Pos.CENTER);
        statistikVBox.getStyleClass().add("gameover");
        statistikVBox.setPadding(new Insets(-70,0,0,0));
        
        this.getChildren().addAll(firegif,statistikVBox,buttonsHBox);
        this.setAlignment(Pos.CENTER);

    }

}
