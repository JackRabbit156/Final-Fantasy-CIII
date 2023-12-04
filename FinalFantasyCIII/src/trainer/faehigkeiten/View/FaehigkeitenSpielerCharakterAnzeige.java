package trainer.faehigkeiten.View;

import charakter.model.SpielerCharakter;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import trainer.faehigkeiten.Faehigkeit;

public class FaehigkeitenSpielerCharakterAnzeige extends ListView<Faehigkeit> {
    public static final double BREITE = 200;
    public static final double HOEHE = 60;

    private SpielerCharakter aktuellerCharakter;

    public FaehigkeitenSpielerCharakterAnzeige(SpielerCharakter aktuellerCharakter) {
        this.aktuellerCharakter = aktuellerCharakter;
        this.setCellFactory(param -> new FaehigkeitListenEintraege());
        for (trainer.faehigkeiten.Faehigkeit faehigkeit : this.aktuellerCharakter.getFaehigkeiten()) {
            this.getItems().add(faehigkeit);
        }
        this.getSelectionModel().selectFirst();
        BackgroundImage hintergrund = new BackgroundImage(
                new Image("background/faehigkeitenHintergrund.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.setBackground(new Background(hintergrund));
        this.setMinWidth(8*BREITE);
        this.setMaxWidth(8*BREITE);
        this.setPadding(new Insets(HOEHE));
        this.setEditable(true);
    }

    private class FaehigkeitListenEintraege extends ListCell<Faehigkeit> {
        BackgroundFill transparentFill = new BackgroundFill(Color.rgb(255, 255, 255, 0.4), CornerRadii.EMPTY, Insets.EMPTY);
        Background transparentBackground = new Background(transparentFill);
        BackgroundFill selectiertFill = new BackgroundFill(Color.rgb(149, 69, 53, 0.8), CornerRadii.EMPTY, Insets.EMPTY);
        Background selectedBackground = new Background(selectiertFill);

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
                        setBackground(selectedBackground);
                    } else {
                        setBackground(transparentBackground);
                    }
                    faehigkeitAnzeige.setIstSelektiert(newValue);
                });
                setGraphic(faehigkeitAnzeige);
            }
            setBackground(transparentBackground);

        }
    }
}
