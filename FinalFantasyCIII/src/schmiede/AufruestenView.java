package schmiede;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.material.Material;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import party.PartyController;
import partystatus.PartyStatusController;
import view.ViewController;

public class AufruestenView extends VBox {

    AufruestenView(Ausruestungsgegenstand ausruestungsgegenstand, SchmiedeController schmiedeController , ViewController viewController, VerbessernView verbessernView,
                   PartyController partyController) {

        Text waffenname = new Text(ausruestungsgegenstand.getName());
        waffenname.getStyleClass().add("text-schmiede");
        Text material = new Text("Benötigtes Material:");
        material.getStyleClass().add("text-schmiede");

        schmiedeController.materialKostenErzeugen();

        HBox anordnungMaterial = new HBox();

        VBox eisenerzAnzeige = new VBox();
        ImageView eisenerz = new ImageView(new Image(Material.EISENERZ.getIcon(),32,32,true,true));
        Text eisenerzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.EISENERZ) != null) {
            eisenerzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.EISENERZ).toString());
        }
        eisenerzAnzahl.getStyleClass().add("text-schmiede");
        eisenerzAnzeige.getChildren().addAll(eisenerz,eisenerzAnzahl);

        VBox golderzAnzeige = new VBox();
        ImageView golderz = new ImageView(new Image(Material.GOLDERZ.getIcon(),32,32,true, true));
        Text golderzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.GOLDERZ) != null) {
            golderzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.GOLDERZ).toString());
        }
        golderzAnzahl.getStyleClass().add("text-schmiede");
        golderzAnzeige.getChildren().addAll(golderz,golderzAnzahl);

        VBox mithrilAnzeige = new VBox();
        ImageView mithril = new ImageView(new Image(Material.MITHRIL.getIcon(),32,32,true,true));
        Text mithrilAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.MITHRIL) != null) {
            mithrilAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.MITHRIL).toString());
        }
        mithrilAnzahl.getStyleClass().add("text-schmiede");
        mithrilAnzeige.getChildren().addAll(mithril,mithrilAnzahl);

        VBox popelAnzeige = new VBox();
        ImageView popel = new ImageView(new Image(Material.POPEL.getIcon(),32, 32, true, true));
        Text popelAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.POPEL) != null) {
            popelAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.POPEL).toString());
        }
        popelAnzahl.getStyleClass().add("text-schmiede");
        popelAnzeige.getChildren().addAll(popel,popelAnzahl);

        VBox schleimAnzeige = new VBox();
        ImageView schleim = new ImageView(new Image(Material.SCHLEIM.getIcon(),32,32,true,true));
        Text schleimAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.SCHLEIM) != null) {
            schleimAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.SCHLEIM).toString());
        }
        schleimAnzahl.getStyleClass().add("text-schmiede");
        schleimAnzeige.getChildren().addAll(schleim,schleimAnzahl);

        VBox silbererzAnzeige = new VBox();
        ImageView silbererz = new ImageView(new Image(Material.SILBERERZ.getIcon(),32,32,true,true));
        Text silbererzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.SILBERERZ) != null) {
            silbererzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Material.SILBERERZ).toString());
        }
        silbererzAnzahl.getStyleClass().add("text-schmiede");
        silbererzAnzeige.getChildren().addAll(silbererz,silbererzAnzahl);

        VBox goldAnzeige = new VBox();
        ImageView gold = new ImageView(new Image("/icons/gold.png",32,32,true,true));
        Text goldAnzahl = new Text("0");
        goldAnzahl = new Text(""+((ausruestungsgegenstand.getLevelAnforderung()+1)*100));
        goldAnzahl.getStyleClass().add("text-schmiede");
        goldAnzeige.getChildren().addAll(gold,goldAnzahl);

        anordnungMaterial.getChildren().addAll(eisenerzAnzeige,golderzAnzeige,mithrilAnzeige,popelAnzeige,schleimAnzeige,silbererzAnzeige,goldAnzeige);
        anordnungMaterial.setAlignment(Pos.CENTER);
        anordnungMaterial.setSpacing(40);


        Button aufruesten = new Button("Aufrüsten");
        aufruesten.setOnAction(e -> {boolean ergebnis = schmiedeController.aufwerten(ausruestungsgegenstand);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(viewController.getPrimary());
            alert.setTitle("Verbesserungsergebnis");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("dialog-pane-schmiede");
            alert.getDialogPane().setGraphic(null);
        if (ergebnis){
                alert.setHeaderText("Verbesserung Erfolgreich!");
                alert.setContentText("Der Gegenstand wurde auf das nächste Level verbessert!");
            } else {
                alert.setHeaderText("Verbesserung Fehlgeschlagen!");
                alert.setContentText("Der Gegenstand wurde NICHT auf das nächste Level verbessert!");
            }
            alert.showAndWait();
            viewController.getOberStack().setAlignment(Pos.CENTER_LEFT);
            viewController.aktuelleNachHinten();
            verbessernView.verbessernWaffenAnzeigeAktualisieren();
            verbessernView.verbessernRuestungAnzeigeAktualisieren();
            verbessernView.verbessernAccessoireAnzeigeAktualisieren();});
        aufruesten.disableProperty().bind(Bindings.when(Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(eisenerzAnzahl.getText()), partyController.getParty().getMaterialien().get(Material.EISENERZ)),
                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(golderzAnzahl.getText()),partyController.getParty().getMaterialien().get(Material.GOLDERZ)),
                        Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(mithrilAnzahl.getText()),partyController.getParty().getMaterialien().get(Material.MITHRIL)),
                                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(popelAnzahl.getText()), partyController.getParty().getMaterialien().get(Material.POPEL)),
                                        Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(schleimAnzahl.getText()), partyController.getParty().getMaterialien().get(Material.SCHLEIM)),
                                                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(silbererzAnzahl.getText()),partyController.getParty().getMaterialien().get(Material.SILBERERZ)),
                                                        Bindings.lessThanOrEqual(Integer.parseInt(goldAnzahl.getText()), partyController.getParty().goldProperty())))))))).then(false).otherwise(true));


        Button zurueck = new Button("Zurück");
        zurueck.getStyleClass().add("hauptmenubutton");
        aufruesten.getStyleClass().add("hauptmenubutton");
        HBox buttons = new HBox(aufruesten, zurueck);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        zurueck.setOnAction(event -> {
            viewController.getOberStack().setAlignment(Pos.CENTER_LEFT);
            viewController.aktuelleNachHinten();});
        this.getChildren().addAll(waffenname,material,anordnungMaterial,buttons);
        viewController.getOberStack().setAlignment(Pos.CENTER);
        this.setSpacing(50);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("VBoxStyle-neuesspiel");
        this.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, new Insets(0))));
        this.setMaxSize(600,400);
    }
}
