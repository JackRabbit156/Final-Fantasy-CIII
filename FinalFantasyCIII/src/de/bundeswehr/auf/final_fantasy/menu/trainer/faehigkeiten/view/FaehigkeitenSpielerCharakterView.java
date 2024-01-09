package de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.Faehigkeit;

public class FaehigkeitenSpielerCharakterView extends ListView<Faehigkeit> {
    private SpielerCharakter aktuellerCharakter;
    private TrainerController trainerController;

    public double breite = 180;
    public double hoehe = 60;
    CornerRadii hintergrundRadii = new CornerRadii(15);
    BackgroundFill transparentFuellung = new BackgroundFill(Color.rgb(0, 0, 0, 0), hintergrundRadii, Insets.EMPTY);
    Background transparentHintergrund = new Background(transparentFuellung);
    BackgroundFill selektiertFuellung = new BackgroundFill(Color.rgb(249, 167, 79, 0.8), hintergrundRadii, Insets.EMPTY);
    Background selektiertHintergrund = new Background(selektiertFuellung);
    BackgroundFill unselektiertFuellung = new BackgroundFill(Color.rgb(94, 57, 34, 0.6), hintergrundRadii, Insets.EMPTY);
    Background unselektiertHintergrund = new Background(unselektiertFuellung);
    CornerRadii rahmenRadii = new CornerRadii(10);
    BorderWidths rahmenBreite = new BorderWidths(10);
    Border selektiertRahmen = new Border(new BorderStroke(unselektiertFuellung.getFill(), BorderStrokeStyle.SOLID, rahmenRadii, rahmenBreite));
    Border unselektiertRahmen = new Border(new BorderStroke(selektiertFuellung.getFill(), BorderStrokeStyle.SOLID, rahmenRadii, rahmenBreite));
    ObservableList<Faehigkeit> observableListFaehigkeit;


    /**
     * FaehigkeitenSpielerCharakterAnzeige legt den Style der ListView fest in der die Fähigkeiten angezeigt werden sollen
     * @param trainerController Verbindung dieser Klasse mit dem Model/Rest der Anwendung
     * @author 11777914 OLt Oliver Ebert
     * @since 06.12.2023
     */
    public FaehigkeitenSpielerCharakterView(TrainerController trainerController) {
        this.trainerController = trainerController;
        this.aktuellerCharakter = trainerController.getAktuellerCharakter();
        this.observableListFaehigkeit = FXCollections.observableArrayList(aktuellerCharakter.getFaehigkeiten());
        this.setCellFactory(param -> new FaehigkeitListenEintraege());
        this.getItems().setAll(this.observableListFaehigkeit);
        this.getSelectionModel().selectFirst();
        this.setMinWidth(8 * breite);
        this.setMaxWidth(8 * breite);
        this.setPadding(new Insets(0, 20, 0, 20));
        this.setEditable(true);
        this.setBackground(transparentHintergrund);
        this.getStyleClass().add("trainerListView");
    }

    private class FaehigkeitListenEintraege extends ListCell<Faehigkeit> {
        @Override
        protected void updateItem(Faehigkeit item, boolean empty) {
            aktuellerCharakter = trainerController.getAktuellerCharakter();
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                FaehigkeitView faehigkeitAnzeige = new FaehigkeitView(item, trainerController);
                if (item.getLevelAnforderung() > aktuellerCharakter.getLevel()) {
                    faehigkeitAnzeige.setDisable(true);
                }
                faehigkeitAnzeige.getAufwertenButton().setDisable((aktuellerCharakter.getOffeneFaehigkeitspunkte() <= 0));
                selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        faehigkeitAnzeige.setBackground(selektiertHintergrund);
                        faehigkeitAnzeige.setBorder(selektiertRahmen);
                        faehigkeitAnzeige.setBackground(selektiertHintergrund);
                    } else {
                        faehigkeitAnzeige.setBackground(transparentHintergrund);
                        faehigkeitAnzeige.setBorder(unselektiertRahmen);
                        faehigkeitAnzeige.setBackground(unselektiertHintergrund);
                    }
                    faehigkeitAnzeige.setIstSelektiert(newValue);
                });
                setGraphic(faehigkeitAnzeige);
                faehigkeitAnzeige.setBackground(transparentHintergrund);
                faehigkeitAnzeige.setBorder(unselektiertRahmen);
                faehigkeitAnzeige.setBackground(unselektiertHintergrund);
            }
            setBackground(transparentHintergrund);
        }
    }
}
