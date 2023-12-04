package trainer.faehigkeiten.View;

import charakter.model.SpielerCharakter;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import trainer.faehigkeiten.Faehigkeit;

public class FaehigkeitenSpielerCharakterAnzeige extends ListView<Faehigkeit> {
    private SpielerCharakter aktuellerCharakter;

    public static final double BREITE = 200;
    public static final double HOEHE = 60;
    CornerRadii hintergrundRadii = new CornerRadii(15);
    BackgroundFill transparentFuellung = new BackgroundFill(Color.rgb(0, 0, 0, 0),  hintergrundRadii, Insets.EMPTY);
    Background transparentHintergrund = new Background(transparentFuellung);
    BackgroundFill selektiertFuellung = new BackgroundFill(Color.rgb(249, 167, 79, 0.8), hintergrundRadii, Insets.EMPTY);
    Background selektiertHintergrund = new Background(selektiertFuellung);
    BackgroundFill unselektiertFuellung = new BackgroundFill(Color.rgb(94, 57, 34, 0.6), hintergrundRadii, Insets.EMPTY);
    Background unselektiertHintergrund = new Background(unselektiertFuellung);
    CornerRadii rahmenRadii = new CornerRadii(10);
    BorderWidths rahmenBreite = new BorderWidths(10);
    Border selektiertRahmen = new Border(new BorderStroke(unselektiertFuellung.getFill(), BorderStrokeStyle.SOLID, rahmenRadii, rahmenBreite));
    Border unselektiertRahmen = new Border(new BorderStroke(selektiertFuellung.getFill(), BorderStrokeStyle.SOLID, rahmenRadii, rahmenBreite));


    public FaehigkeitenSpielerCharakterAnzeige(SpielerCharakter aktuellerCharakter) {
        this.aktuellerCharakter = aktuellerCharakter;
        this.setCellFactory(param -> new FaehigkeitListenEintraege());
        for (trainer.faehigkeiten.Faehigkeit faehigkeit : this.aktuellerCharakter.getFaehigkeiten()) {
            this.getItems().add(faehigkeit);
        }
        this.getSelectionModel().selectFirst();
        this.setMinWidth(8 * BREITE);
        this.setMaxWidth(8 * BREITE);
        this.setPadding(new Insets(0, 0, 0, HOEHE));
        this.setEditable(true);
        this.setBackground(transparentHintergrund);
    }

    private class FaehigkeitListenEintraege extends ListCell<Faehigkeit> {


        @Override
        protected void updateItem(Faehigkeit item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                FaehigkeitAnzeige faehigkeitAnzeige = new FaehigkeitAnzeige(item);
                if (item.getLevelAnforderung() > aktuellerCharakter.getLevel()) {
                    faehigkeitAnzeige.setDisable(true);
                }

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
                faehigkeitAnzeige.setBorder(unselektiertRahmen);
                setGraphic(faehigkeitAnzeige);
            }
            setBackground(transparentHintergrund);
        }
    }
}
