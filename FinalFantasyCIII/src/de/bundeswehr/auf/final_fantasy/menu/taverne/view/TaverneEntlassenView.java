package de.bundeswehr.auf.final_fantasy.menu.taverne.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.taverne.TaverneController;

import java.util.concurrent.atomic.AtomicInteger;

public class TaverneEntlassenView extends VBox {

    private Label soeldnerName;
    private ImageView soeldnerView;
    private Label soeldnerKlasse;
//    private Label soeldnerGeschichte;
    private int soeldnerIndex;
    private Button naechster = new Button("nächster");
    private Button vorheriger = new Button("vorheriger");
    private HBox naechsterHBox = new HBox();

    /**
     * Konstruktor für die TaverneEntlassenView-Klasse.
     * Die Klasse TaverneEntlassenView zeigt die grafische Benutzeroberfläche zum Entlassen von Söldnern in der Taverne an.
     * @author Dennis, Markus
     * @since 05.12.2023
     * @param taverneController Der Controller für die Taverne, um die dortigen NebenCharaktere zu bekommen
     * @param partyController   Der Controller für die Party, um die dortigen NebenCharaktere zu bekommen
     */
    public TaverneEntlassenView(TaverneController taverneController, PartyController partyController) {
        this.setBackground(new Background(new BackgroundImage(new Image("background/taverne.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false))));
        soeldnerName = new Label();
        soeldnerView = new ImageView();
        HBox soeldnerHBox = new HBox();
        HBox buttons = new HBox();
        VBox soeldnerKlasseGeschichte = new VBox();
        AtomicInteger lastIndex = new AtomicInteger();
        for (int i = 0; i < partyController.getParty().getNebenCharakter().length; i++) {
            if (partyController.getParty().getNebenCarakter(i) != null) {
                soeldnerName.setText(partyController.getParty().getNebenCharakter()[i].getName());
                lastIndex.set(i);
                soeldnerIndex = i;
                break;
            }
        }
        if (partyController.getParty().getNebenCarakter(soeldnerIndex) != null) {
            soeldnerName.setText(partyController.getParty().getNebenCharakter()[soeldnerIndex].getName());
            Image soeldnerBild = new Image(partyController.getParty().getNebenCharakter()[soeldnerIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerView.setImage(soeldnerBild);
            Button soeldnerAnzeige;
            soeldnerAnzeige = new Button("");
            soeldnerName.setText(partyController.getParty().getNebenCharakter()[lastIndex.get()].getName());
            soeldnerBild = new Image(partyController.getParty().getNebenCarakter(lastIndex.get()).getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerAnzeige = new Button();
            soeldnerView = new ImageView(soeldnerBild);
            soeldnerAnzeige.setBackground(null);
            soeldnerAnzeige.setGraphic(soeldnerView);
            soeldnerHBox.setAlignment(Pos.CENTER);
            soeldnerHBox.getChildren().add(soeldnerAnzeige);
            Polygon naechsterPfeil = new Polygon();
            naechsterPfeil.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
            naechsterHBox.setAlignment(Pos.CENTER);
            naechster.setBackground(null);
            naechster.setMaxHeight(30.0);
            naechster.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            naechsterHBox.getStyleClass().add("hauptmenubutton");
            naechsterHBox.setMaxHeight(30.0);
            naechsterHBox.getChildren().addAll(naechster, naechsterPfeil);
            naechster.setOnAction(event -> {
                for (int i = lastIndex.get() + 1; i < partyController.getParty().getNebenCharakter().length; i++) {
                    if (partyController.getParty().getNebenCarakter(i) != null) {
                        lastIndex.set(i);
                        break;
                    }
                }
                soeldnerIndex = lastIndex.get();
                updateSoeldnerAnzeige(taverneController, partyController, lastIndex.get());
            });
            naechsterHBox.setOnMouseClicked(event -> {
                for (int i = lastIndex.get() + 1; i < partyController.getParty().getNebenCharakter().length; i++) {
                    if (partyController.getParty().getNebenCarakter(i) != null) {
                        lastIndex.set(i);
                        break;
                    }
                }
                soeldnerIndex = lastIndex.get();
                updateSoeldnerAnzeige(taverneController, partyController, lastIndex.get());
            });
            Polygon vorherigerPfeil = new Polygon();
            vorherigerPfeil.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
            vorherigerPfeil.setScaleX(-1.0);
            vorheriger.setGraphic(vorherigerPfeil);
            vorheriger.setPrefHeight(68);
            vorheriger.setBackground(null);
            vorheriger.getStyleClass().add("hauptmenubutton");
            vorheriger.setOnAction(event -> {
                for (int i = lastIndex.get() - 1; i >= 0; i--) {
                    if (partyController.getParty().getNebenCarakter(i) != null) {
                        lastIndex.set(i);
                        break;
                    }
                }
                updateSoeldnerAnzeige(taverneController, partyController, lastIndex.get());
            });
            buttons.setSpacing(50.0);
            buttons.setAlignment(Pos.CENTER);
            buttons.getChildren().addAll(vorheriger, naechsterHBox);
            buttons.setDisable(taverneController.getNebenCharaktere().size() == 0);
            buttons.setVisible(taverneController.getNebenCharaktere().size() > 0);
            if (taverneController.getNebenCharaktere().size() > 0 && partyController.getParty().getNebenCarakter(lastIndex.get()) != null) {
                soeldnerKlasse = new Label(partyController.getParty().getNebenCharakter()[lastIndex.get()].getKlasse().getBezeichnung());
                soeldnerKlasse.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #ddb622");
//                soeldnerGeschichte = new Label(partyController.getParty().getNebenCharakter()[lastIndex.get()].getGeschichte());
//                soeldnerGeschichte.setStyle("-fx-font: 20px 'Lucida Calligraphy Italic'; -fx-text-fill: #b3744c");
//                soeldnerKlasseGeschichte.setAlignment(Pos.CENTER);
//                soeldnerKlasseGeschichte.getChildren().addAll(soeldnerKlasse, soeldnerGeschichte);
            }
        } else {
            soeldnerName.setText("Keine Söldner zum entlassen vorhanden!");
            soeldnerView.setImage(null);
            naechster.setVisible(false);
            naechster.setDisable(true);
            vorheriger.setVisible(false);
            vorheriger.setDisable(true);
            naechsterHBox.setVisible(false);
        }
        soeldnerName.setStyle("-fx-font: 30px 'Lucida Calligraphy Italic'; -fx-text-fill: #fefdfc");
        this.getChildren().addAll(soeldnerName, soeldnerHBox, buttons, soeldnerKlasseGeschichte);
        this.setMaxWidth(1536.0);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);
    }

    /**
     * Aktualisiert die Anzeige für den ausgewählten Söldner.
     * @author Dennis
     * @since 05.12.2023
     * @param taverneController Der Controller für die Taverne, um die dortigen NebenCharaktere zu bekommen
     * @param partyController   Der Controller für die Party, um die dortigen NebenCharaktere zu bekommen
     * @param lastIndex         Der Index des ausgewählten Söldners, dessen Anzeige dargestellt werden soll
     */
    public void updateSoeldnerAnzeige(TaverneController taverneController, PartyController partyController, int lastIndex) {
        if (taverneController.getNebenCharaktere().size() > 0) {
            soeldnerName.setText(partyController.getParty().getNebenCharakter()[lastIndex].getName());
            Image soeldnerBild = new Image(partyController.getParty().getNebenCharakter()[lastIndex].getGrafischeDarstellung(), 0.0, 400.0, true, false);
            soeldnerView.setImage(soeldnerBild);
            soeldnerKlasse.setText(partyController.getParty().getNebenCharakter()[lastIndex].getKlasse().getBezeichnung());
//            soeldnerGeschichte.setText(partyController.getParty().getNebenCharakter()[lastIndex].getGeschichte());
        } else {
            soeldnerName.setText("Keine Söldner vorhanden!");
            soeldnerView.setImage(null);
            soeldnerKlasse.setText("");
//            soeldnerGeschichte.setText("");
        }
    }

    public int getSoeldnerIndex() {
        return soeldnerIndex;
    }

    public void setSoeldnerIndex(int soeldnerIndex) {
        this.soeldnerIndex = soeldnerIndex;
    }
}
