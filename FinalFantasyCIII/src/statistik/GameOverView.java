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

    public GameOverView(Statistik statistik, PartyController partyController, ViewController viewController){
        this.setBackground(new Background(new BackgroundImage(new Image("gameover/schwarzerHintergrund.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));

        VBox hauptVBox = new VBox();
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
        Label spielername = new Label("Spielername: ");
        spielername.setText("Spielername: " + partyController.getParty().getHauptCharakter().getName());
        spielername.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        Label erreichtesLevel = new Label("Erreichtes Level: ");
        erreichtesLevel.setText("Erreichtes Level: " + partyController.getParty().getHauptCharakter().getLevel());
        erreichtesLevel.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        Label durchgefuehrteKaempfe = new Label("Durchgeführte Kämpfe: ");
        durchgefuehrteKaempfe.setText("Durchgeführte Kämpfe: " + statistik.getDurchgefuehrteKaempfe());
        durchgefuehrteKaempfe.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        Label gewonneneKaempfe = new Label("Gewonnene Kämpfe: ");
        gewonneneKaempfe.setText("Gewonnene Kämpfe: " + statistik.getGewonneneKaempfe());
        gewonneneKaempfe.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        Label verloreneKaempfe = new Label("Verlorene Kämpfe: ");
        verloreneKaempfe.setText("Verlorene Kämpfe: " + statistik.getVerloreneKaempfe());
        verloreneKaempfe.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        Label erwirtschaftetesGold = new Label("Erwirtschaftetes Gold: ");
        erwirtschaftetesGold.setText("Erwirtschaftetes Gold: " + statistik.getGesamtErwirtschaftetesGold());
        erwirtschaftetesGold.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");
        statistikVBox.getChildren().addAll(spielername,erreichtesLevel,durchgefuehrteKaempfe,gewonneneKaempfe,verloreneKaempfe,erwirtschaftetesGold);
        statistikVBox.setAlignment(Pos.CENTER);
        statistikVBox.setPadding(new Insets(-70,0,0,0));
        statistikVBox.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ffffff");

        hauptVBox.getChildren().addAll(firegif,statistikVBox,buttonsHBox);
        hauptVBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(hauptVBox);
        this.setAlignment(Pos.CENTER);

    }

}
