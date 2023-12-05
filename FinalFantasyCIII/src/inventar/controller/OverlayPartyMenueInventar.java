package inventar.controller;

import charakter.model.SpielerCharakter;
import gegenstand.GegenstandController;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import inventar.view.InventarView;
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
import party.PartyController;

public class OverlayPartyMenueInventar extends HBox {

    public OverlayPartyMenueInventar(InventarView inventarView, VBox itemauswahl, PartyController partyController) {
        Button btnKleinerHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.KLEINER_HEILTRANK).get() == 0) {
            btnKleinerHeiltrank.setDisable(true);
        }
        btnKleinerHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.KLEINER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.KLEINER_HEILTRANK).get() == 0) {
                    btnKleinerHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnKleinerHeiltrank = new Image(Verbrauchsgegenstand.KLEINER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnKleinerHeiltrank.setGraphic(new ImageView(imgbtnKleinerHeiltrank));


        Button btnMittlererHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.MITTLERER_HEILTRANK).get() == 0) {
            btnMittlererHeiltrank.setDisable(true);
        }
        btnMittlererHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.MITTLERER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.MITTLERER_HEILTRANK).get() == 0) {
                    btnMittlererHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnMittlererHeiltrank = new Image(Verbrauchsgegenstand.MITTLERER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnMittlererHeiltrank.setGraphic(new ImageView(imgbtnMittlererHeiltrank));


        Button btnGroeserHeiltrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.GROSSER_HEILTRANK).get() == 0) {
            btnGroeserHeiltrank.setDisable(true);
        }
        btnGroeserHeiltrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.GROSSER_HEILTRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.GROSSER_HEILTRANK).get() == 0) {
                    btnGroeserHeiltrank.setDisable(true);
                }
            }
        });
        Image imgbtnGroeserHeiltrank = new Image(Verbrauchsgegenstand.GROSSER_HEILTRANK.getIcon(), 60, 60, false, true);
        btnGroeserHeiltrank.setGraphic(new ImageView(imgbtnGroeserHeiltrank));


        Button btnKleinerManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.KLEINER_MANATRANK).get() == 0) {
            btnKleinerManatrank.setDisable(true);
        }
        btnKleinerManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.KLEINER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.KLEINER_MANATRANK).get() == 0) {
                    btnKleinerManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnKleinerManatrank = new Image(Verbrauchsgegenstand.KLEINER_MANATRANK.getIcon(), 60, 60, false, true);
        btnKleinerManatrank.setGraphic(new ImageView(imgbtnKleinerManatrank));


        Button btnMittlererManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.MITTLERER_MANATRANK).get() == 0) {
            btnMittlererManatrank.setDisable(true);
        }
        btnMittlererManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.MITTLERER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.MITTLERER_MANATRANK).get() == 0) {
                    btnMittlererManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnMittlererManatrank = new Image(Verbrauchsgegenstand.MITTLERER_MANATRANK.getIcon(), 60, 60, false, true);
        btnMittlererManatrank.setGraphic(new ImageView(imgbtnMittlererManatrank));


        Button btnGroeserManatrank = new Button();
        if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.GROSSER_MANATRANK).get() == 0) {
            btnGroeserManatrank.setDisable(true);
        }
        btnGroeserManatrank.setOnMouseClicked(event -> {
            if (inventarView.getAusgewaehlterChar() != null) {
                GegenstandController.verwendeVerbrauchsgegenstandInventar(partyController.getParty().getVerbrauchsgegenstaende(), Verbrauchsgegenstand.GROSSER_MANATRANK, inventarView.getAusgewaehlterChar(), partyController);
                if (partyController.getParty().getVerbrauchsgegenstaende().get(Verbrauchsgegenstand.GROSSER_MANATRANK).get() == 0) {
                    btnGroeserManatrank.setDisable(true);
                }
            }
        });
        Image imgbtnGroeserManatrank = new Image(Verbrauchsgegenstand.GROSSER_MANATRANK.getIcon(), 60, 60, false, true);
        btnGroeserManatrank.setGraphic(new ImageView(imgbtnGroeserManatrank));


        HBox bxKleinerHeiltrank = new HBox();
        Text kleinerHeiltrank = new Text("" + Verbrauchsgegenstand.KLEINER_HEILTRANK.getBeschereibung());
        kleinerHeiltrank.getStyleClass().add("spielerCharNameText");
        bxKleinerHeiltrank.getChildren().addAll(btnKleinerHeiltrank, kleinerHeiltrank);

        HBox bxMittlererHeiltrank = new HBox();
        Text MittlererHeiltrank = new Text("" + Verbrauchsgegenstand.MITTLERER_HEILTRANK.getBeschereibung());
        MittlererHeiltrank.getStyleClass().add("spielerCharNameText");
        bxMittlererHeiltrank.getChildren().addAll(btnMittlererHeiltrank, MittlererHeiltrank);

        HBox bxGroeserHeiltrank = new HBox();
        Text GroeserHeiltrank = new Text("" + Verbrauchsgegenstand.GROSSER_HEILTRANK.getBeschereibung());
        GroeserHeiltrank.getStyleClass().add("spielerCharNameText");
        bxGroeserHeiltrank.getChildren().addAll(btnGroeserHeiltrank, GroeserHeiltrank);

        HBox bxKleinerManatrank = new HBox();
        Text KleinerManatrank = new Text("" + Verbrauchsgegenstand.KLEINER_MANATRANK.getBeschereibung());
        KleinerManatrank.getStyleClass().add("spielerCharNameText");
        bxKleinerManatrank.getChildren().addAll(btnKleinerManatrank, KleinerManatrank);

        HBox bxMittlererManatrank = new HBox();
        Text MittlererManatrank = new Text("" + Verbrauchsgegenstand.MITTLERER_MANATRANK.getBeschereibung());
        MittlererManatrank.getStyleClass().add("spielerCharNameText");
        bxMittlererManatrank.getChildren().addAll(btnMittlererManatrank, MittlererManatrank);

        HBox bxGroeserManatrank = new HBox();
        Text GroeserManatrank = new Text("" + Verbrauchsgegenstand.GROSSER_MANATRANK.getBeschereibung());
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

    public OverlayPartyMenueInventar(SpielerCharakter spielerCharakter, InventarView inventarView, VBox charBox) {
//        getStyleClass().add("charboxButton");
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
        healthBarSpielerChar.setStyle(healthBarColorAktualisieren(spielerCharakter.getGesundheitsPunkte(), spielerCharakter.getMaxGesundheitsPunkte()));
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

    private static String healthBarColorAktualisieren(double gesundheitsPunkte, double maxGesundheitsPunkte) {
        String colorHealthBar;
        if ((gesundheitsPunkte / maxGesundheitsPunkte) >= 0.5) {
            colorHealthBar = "-fx-accent: #00FF00;";
        } else if ((gesundheitsPunkte / maxGesundheitsPunkte) >= 0.2) {
            colorHealthBar = "-fx-accent: #FF8C00;";
        } else {
            colorHealthBar = "-fx-accent: #FF0000;";
        }
        return colorHealthBar;
    }
}