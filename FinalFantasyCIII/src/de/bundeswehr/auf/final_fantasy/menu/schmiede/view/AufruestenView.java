package de.bundeswehr.auf.final_fantasy.menu.schmiede.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.SchmiedeController;

public class AufruestenView extends VBox {

/**
 * Die AufruestenView repräsentiert die visuelle Darstellung der Ansicht, in der der Spieler Ausrüstungsgegenstände aufrüsten kann.
 * Sie enthält Informationen über die benötigten Materialien, den Namen des Ausrüstungsgegenstands und bietet Buttons für den Aufrüstvorgang.
 * @author OF Stetter
 * @since 05.12.23
 */
    AufruestenView(AusruestungsGegenstand ausruestungsgegenstand, SchmiedeController schmiedeController , ViewController viewController, VerbessernView verbessernView,
                   PartyController partyController) {

        Text waffenname = new Text(ausruestungsgegenstand.getName());
        waffenname.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        Text material = new Text("Benötigtes Material:");
        material.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");

        schmiedeController.materialKostenErzeugen();

        HBox anordnungMaterial = new HBox();

        VBox eisenerzAnzeige = new VBox();
        ImageView eisenerz = new ImageView(new Image(Materialien.EISENERZ.getIcon(),32,32,true,true));
        Text eisenerzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.EISENERZ) != null) {
            eisenerzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.EISENERZ).toString());
        }
        eisenerzAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        eisenerzAnzeige.getChildren().addAll(eisenerz,eisenerzAnzahl);

        VBox golderzAnzeige = new VBox();
        ImageView golderz = new ImageView(new Image(Materialien.GOLDERZ.getIcon(),32,32,true, true));
        Text golderzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.GOLDERZ) != null) {
            golderzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.GOLDERZ).toString());
        }
        golderzAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        golderzAnzeige.getChildren().addAll(golderz,golderzAnzahl);

        VBox mithrilAnzeige = new VBox();
        ImageView mithril = new ImageView(new Image(Materialien.MITHRIL.getIcon(),32,32,true,true));
        Text mithrilAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.MITHRIL) != null) {
            mithrilAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.MITHRIL).toString());
        }
        mithrilAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        mithrilAnzeige.getChildren().addAll(mithril,mithrilAnzahl);

        VBox popelAnzeige = new VBox();
        ImageView popel = new ImageView(new Image(Materialien.POPEL.getIcon(),32, 32, true, true));
        Text popelAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.POPEL) != null) {
            popelAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.POPEL).toString());
        }
        popelAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        popelAnzeige.getChildren().addAll(popel,popelAnzahl);

        VBox schleimAnzeige = new VBox();
        ImageView schleim = new ImageView(new Image(Materialien.SCHLEIM.getIcon(),32,32,true,true));
        Text schleimAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.SCHLEIM) != null) {
            schleimAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.SCHLEIM).toString());
        }
        schleimAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        schleimAnzeige.getChildren().addAll(schleim,schleimAnzahl);

        VBox silbererzAnzeige = new VBox();
        ImageView silbererz = new ImageView(new Image(Materialien.SILBERERZ.getIcon(),32,32,true,true));
        Text silbererzAnzahl = new Text("0");
        if (schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.SILBERERZ) != null) {
            silbererzAnzahl = new Text(schmiedeController.getAufruestungskosten().get(ausruestungsgegenstand.getLevelAnforderung()).get(Materialien.SILBERERZ).toString());
        }
        silbererzAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        silbererzAnzeige.getChildren().addAll(silbererz,silbererzAnzahl);

        VBox goldAnzeige = new VBox();
        ImageView gold = new ImageView(new Image("/icons/gold.png",32,32,true,true));
        Text goldAnzahl = new Text("0");
        goldAnzahl = new Text(""+((ausruestungsgegenstand.getLevelAnforderung()+1)*100));
        goldAnzahl.getStyleClass().add("text-de.bundeswehr.auf.final_fantasy.menu.schmiede");
        goldAnzeige.getChildren().addAll(gold,goldAnzahl);

        anordnungMaterial.getChildren().addAll(eisenerzAnzeige,golderzAnzeige,mithrilAnzeige,popelAnzeige,schleimAnzeige,silbererzAnzeige,goldAnzeige);
        anordnungMaterial.setAlignment(Pos.CENTER);
        anordnungMaterial.setSpacing(40);

        // Logik für den Aufrüst-Button
        Button aufruesten = new Button("Aufrüsten");
        aufruesten.setOnAction(e -> {boolean ergebnis = schmiedeController.aufwerten(ausruestungsgegenstand);
        // Anzeige des Verbesserungsergebnisses in einem Dialogfenster
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(viewController.getPrimary());
            alert.setTitle("Verbesserungsergebnis");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("dialog-pane-de.bundeswehr.auf.final_fantasy.menu.schmiede");
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
        // Deaktivierung des Aufrüst-Buttons, wenn nicht genügend Material oder Gold vorhanden ist
        aufruesten.disableProperty().bind(Bindings.when(Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(eisenerzAnzahl.getText()), partyController.getParty().getMaterialien().get(Materialien.EISENERZ)),
                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(golderzAnzahl.getText()),partyController.getParty().getMaterialien().get(Materialien.GOLDERZ)),
                        Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(mithrilAnzahl.getText()),partyController.getParty().getMaterialien().get(Materialien.MITHRIL)),
                                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(popelAnzahl.getText()), partyController.getParty().getMaterialien().get(Materialien.POPEL)),
                                        Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(schleimAnzahl.getText()), partyController.getParty().getMaterialien().get(Materialien.SCHLEIM)),
                                                Bindings.and(Bindings.lessThanOrEqual(Integer.parseInt(silbererzAnzahl.getText()),partyController.getParty().getMaterialien().get(Materialien.SILBERERZ)),
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
