package de.bundeswehr.auf.final_fantasy.menu.inventar.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.GegenstandController;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Verbrauchsgegenstaende;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InventarPartyMenue extends HBox {

    /**
     * Die Klasse InventarPartyMenue stellt eine grafische Benutzeroberfläche
     * für die Auswahl von Verbrauchsgegenständen im Spielerinventar dar.
     * <p>
     * Sie enthält Buttons für verschiedene Verbrauchsgegenstände wie kleine und
     * mittlere Heiltränke sowie kleine, mittlere und große Manatränke. Die Buttons
     * sind mit entsprechenden Icons versehen und reagieren auf Mausklicks, um die
     * Verwendung der Gegenstände für den ausgewählten Charakter zu ermöglichen.
     * <p>
     * Diese Klasse trägt dazu bei, die Interaktion des Spielers mit dem Inventar
     * zu erleichtern und die Auswahl von Verbrauchsgegenständen intuitiv zu gestalten.
     *
     * @param inventarView    wird benutzt um auf die funktionen vom de.bundeswehr.auf.final_fantasy.menu.inventar zuzugreifen für den refresh
     * @param itemauswahl     wird gebraucht um die items in die box zu legen
     * @param partyController wird benutzt um auf die items und chars zuzugreifen die sich hier befinden
     * @param viewController  wird benutzt um die leisten zu aktuallisieren
     * @author Rode
     * @since 06.12.2023
     */
    public InventarPartyMenue(InventarView inventarView, VBox itemauswahl, PartyController partyController, ViewController viewController) {
        Button btnKleinerHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.KLEINER_HEILTRANK).get() == 0) {
            btnKleinerHeiltrank.setDisable(true);
        }
        btnKleinerHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.KLEINER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.KLEINER_HEILTRANK).get() == 0) {
                    btnKleinerHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnKleinerHeiltrank = new Image(Verbrauchsgegenstaende.KLEINER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnKleinerHeiltrank.setGraphic(new ImageView(imgbtnKleinerHeiltrank));

        Button btnMittlererHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.MITTLERER_HEILTRANK).get() == 0) {
            btnMittlererHeiltrank.setDisable(true);
        }
        btnMittlererHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.MITTLERER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.MITTLERER_HEILTRANK).get() == 0) {
                    btnMittlererHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnMittlererHeiltrank = new Image(Verbrauchsgegenstaende.MITTLERER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnMittlererHeiltrank.setGraphic(new ImageView(imgbtnMittlererHeiltrank));

        Button btnGroeserHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.GROSSER_HEILTRANK).get() == 0) {
            btnGroeserHeiltrank.setDisable(true);
        }
        btnGroeserHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.GROSSER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.GROSSER_HEILTRANK).get() == 0) {
                    btnGroeserHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnGroeserHeiltrank = new Image(Verbrauchsgegenstaende.GROSSER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnGroeserHeiltrank.setGraphic(new ImageView(imgbtnGroeserHeiltrank));

        Button btnKleinerManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.KLEINER_MANATRANK).get() == 0) {
            btnKleinerManatrank.setDisable(true);
        }
        btnKleinerManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.KLEINER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.KLEINER_MANATRANK).get() == 0) {
                    btnKleinerManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnKleinerManatrank = new Image(Verbrauchsgegenstaende.KLEINER_MANATRANK.getIcon(), 60, 60, false, true);
        btnKleinerManatrank.setGraphic(new ImageView(imgbtnKleinerManatrank));

        Button btnMittlererManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.MITTLERER_MANATRANK).get() == 0) {
            btnMittlererManatrank.setDisable(true);
        }
        btnMittlererManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.MITTLERER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.MITTLERER_MANATRANK).get() == 0) {
                    btnMittlererManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnMittlererManatrank = new Image(Verbrauchsgegenstaende.MITTLERER_MANATRANK.getIcon(), 60, 60, false, true);
        btnMittlererManatrank.setGraphic(new ImageView(imgbtnMittlererManatrank));

        Button btnGroeserManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.GROSSER_MANATRANK).get() == 0) {
            btnGroeserManatrank.setDisable(true);
        }
        btnGroeserManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstaende.GROSSER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                viewController.aktualisiereCharListe();
                inventarView.verbrauchsGegenstaendeOeffnen();
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstaende.GROSSER_MANATRANK).get() == 0) {
                    btnGroeserManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnGroeserManatrank = new Image(Verbrauchsgegenstaende.GROSSER_MANATRANK.getIcon(), 60, 60, false, true);
        btnGroeserManatrank.setGraphic(new ImageView(imgbtnGroeserManatrank));

        HBox bxKleinerHeiltrank = new HBox();
        Text kleinerHeiltrank = new Text("" + Verbrauchsgegenstaende.KLEINER_HEILTRANK.getBeschreibung());
        kleinerHeiltrank.getStyleClass().add("spielerCharNameText");
        bxKleinerHeiltrank.getChildren().addAll(btnKleinerHeiltrank, kleinerHeiltrank);

        HBox bxMittlererHeiltrank = new HBox();
        Text MittlererHeiltrank = new Text("" + Verbrauchsgegenstaende.MITTLERER_HEILTRANK.getBeschreibung());
        MittlererHeiltrank.getStyleClass().add("spielerCharNameText");
        bxMittlererHeiltrank.getChildren().addAll(btnMittlererHeiltrank, MittlererHeiltrank);

        HBox bxGroeserHeiltrank = new HBox();
        Text GroeserHeiltrank = new Text("" + Verbrauchsgegenstaende.GROSSER_HEILTRANK.getBeschreibung());
        GroeserHeiltrank.getStyleClass().add("spielerCharNameText");
        bxGroeserHeiltrank.getChildren().addAll(btnGroeserHeiltrank, GroeserHeiltrank);

        HBox bxKleinerManatrank = new HBox();
        Text KleinerManatrank = new Text("" + Verbrauchsgegenstaende.KLEINER_MANATRANK.getBeschreibung());
        KleinerManatrank.getStyleClass().add("spielerCharNameText");
        bxKleinerManatrank.getChildren().addAll(btnKleinerManatrank, KleinerManatrank);

        HBox bxMittlererManatrank = new HBox();
        Text MittlererManatrank = new Text("" + Verbrauchsgegenstaende.MITTLERER_MANATRANK.getBeschreibung());
        MittlererManatrank.getStyleClass().add("spielerCharNameText");
        bxMittlererManatrank.getChildren().addAll(btnMittlererManatrank, MittlererManatrank);

        HBox bxGroeserManatrank = new HBox();
        Text GroeserManatrank = new Text("" + Verbrauchsgegenstaende.GROSSER_MANATRANK.getBeschreibung());
        GroeserManatrank.getStyleClass().add("spielerCharNameText");
        bxGroeserManatrank.getChildren().addAll(btnGroeserManatrank, GroeserManatrank);

        itemauswahl.getChildren().addAll(bxKleinerHeiltrank, bxMittlererHeiltrank, bxGroeserHeiltrank, bxKleinerManatrank, bxMittlererManatrank, bxGroeserManatrank);

        for (Node node : itemauswahl.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                hbox.getStyleClass().add("itemauswahl-box");
                for (Node childNode : hbox.getChildren()) {
                    if (childNode instanceof Button) {
                        Button button = (Button) childNode;
                        button.getStyleClass().clear();
                        button.getStyleClass().add("charboxButton");
                    }
                }
            }

        }

    }

    /**
     * Die Klasse OverlayPartyMenueInventar stellt eine grafische Benutzeroberfläche
     * für die Darstellung eines Spielercharakters im Inventar-Menü dar.
     * <p>
     * Sie enthält ein Bild des Spielercharakters, den Namen, eine Gesundheitsleiste
     * und eine Manaleiste. Durch Klick auf das Spielercharakter-Bild wird der Charakter
     * im Inventar ausgewählt, und die zugehörigen Informationen werden angezeigt.
     * <p>
     * Diese Klasse dient dazu, die Interaktion des Spielers mit den Charakteren im
     * Inventar übersichtlich und benutzerfreundlich zu gestalten.
     *
     * @param spielerCharakter Der Spielercharakter, dessen Informationen angezeigt werden.
     * @param inventarView     Die InventarView, zu der diese OverlayPartyMenueInventar-Instanz gehört.
     * @param charBox          Die VBox, die die Anzeige der Spielercharaktere im Inventar-Menü enthält.
     * @author Rode
     * @since 06.12.2023
     */
    public InventarPartyMenue(SpielerCharakter spielerCharakter, InventarView inventarView, VBox charBox) {
        Button spielerCharImage = new Button();
        setMinSize(350, 80);
        VBox spielerCharVbox = new VBox();
        StackPane spielerCharStackPaneName = new StackPane();
        spielerCharStackPaneName.setPrefSize(250, 27);
        StackPane spielerCharStackPaneHP = new StackPane();
        spielerCharStackPaneHP.setPrefSize(250, 25);
        StackPane spielerCharStackPaneMP = new StackPane();
        spielerCharStackPaneMP.setPrefSize(250, 25);
        setAlignment(Pos.CENTER);

        Image spielerCharAvatar = new Image(spielerCharakter.getGrafischeDarstellung(), 120, 120, false, true);
        spielerCharImage.setGraphic(new ImageView(spielerCharAvatar));
        spielerCharImage.setMinSize(spielerCharImage.getWidth(), spielerCharImage.getHeight());
        spielerCharImage.setMaxSize(spielerCharImage.getWidth(), spielerCharImage.getHeight());
        spielerCharImage.setOnMouseClicked(event -> {
            inventarView.setAusgewaehlterChar(spielerCharakter);
            inventarView.entferneCssVonAllenButtons(charBox);
            spielerCharImage.getStyleClass().add("charboxButton-hover");
        });
        spielerCharImage.getStyleClass().add("charboxButton");
        spielerCharImage.setMaxSize(120, 120);
        Text spielerCharName = new Text(spielerCharakter.getName());
        spielerCharName.getStyleClass().add("spielerCharNameText");
        spielerCharStackPaneName.getChildren().add(spielerCharName);

        ProgressBar healthBarSpielerChar = new ProgressBar((double) spielerCharakter.getGesundheitsPunkte() / (double) spielerCharakter.getMaxGesundheitsPunkte());
        healthBarSpielerChar.setMinSize(220, 27);
        healthBarSpielerChar.setMaxSize(220, 27);
        healthBarSpielerChar.setStyle(ColorHelper.healthBarColor(spielerCharakter));
        Text gesundheitsPunkteSpielerCharAlsText = new Text(spielerCharakter.getGesundheitsPunkte() + " / " + spielerCharakter.getMaxGesundheitsPunkte());
        gesundheitsPunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        ProgressBar manaBarSpielerChar = new ProgressBar((double) spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
        manaBarSpielerChar.setMinSize(220, 27);
        manaBarSpielerChar.setMaxSize(220, 27);
        manaBarSpielerChar.setStyle("-fx-accent: #00BFFF;");
        Text manapunktePunkteSpielerCharAlsText = new Text(spielerCharakter.getManaPunkte() + " / " + spielerCharakter.getMaxManaPunkte());
        manapunktePunkteSpielerCharAlsText.getStyleClass().add("partystatusCharakterBarText");

        spielerCharStackPaneHP.getChildren().addAll(healthBarSpielerChar, gesundheitsPunkteSpielerCharAlsText);
        spielerCharStackPaneMP.getChildren().addAll(manaBarSpielerChar, manapunktePunkteSpielerCharAlsText);

        getChildren().add(spielerCharImage);

        spielerCharVbox.getChildren().add(spielerCharStackPaneName);
        spielerCharVbox.getChildren().add(spielerCharStackPaneHP);
        spielerCharVbox.getChildren().add(spielerCharStackPaneMP);
        getChildren().add(spielerCharVbox);
    }

}
