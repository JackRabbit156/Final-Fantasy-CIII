package de.bundeswehr.auf.final_fantasy.menu.kampf.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Zugreihenfolge extends HBox {

    private final Rectangle charakterBox = new Rectangle(65, 50);

    public Zugreihenfolge(double x, double y) {
        getStyleClass().add("leisteTooltip");
        setPrefWidth(729);
        setPrefHeight(50);
        setLayoutX(x);
        setLayoutY(y);
        setOpacity(0.5);
        setSpacing(25);
        setPadding(new Insets(0, 17, 0, 17));

        charakterBox.setFill(Color.TRANSPARENT);
        charakterBox.setStrokeWidth(3.0);
        charakterBox.setLayoutX(x + 17);
        charakterBox.setLayoutY(y);
    }

    public Node charakterBox(Charakter charakter) {
        charakterBox.setStroke(ColorHelper.getStroke(charakter));
        return charakterBox;
    }

    public Zugreihenfolge update(List<Charakter> zugreihenfolge) {
        getChildren().clear();
        for (Charakter charakter : zugreihenfolge) {
            ImageView iv = new ImageView(new Image(charakter.getGrafischeDarstellung(), 0, 45, true, true));
            Tooltip.install(iv, new Tooltip(charakter.getName() + "\n" +
                    charakter.getGesundheitsPunkte() + "/" + charakter.getMaxGesundheitsPunkte() + " HP\n" +
                    charakter.getManaPunkte() + "/" + charakter.getMaxManaPunkte() + " MP"));
            HBox imageBox = new HBox(iv);
            imageBox.setPrefSize(65, 45);
            imageBox.setAlignment(Pos.CENTER);
            imageBox.setBackground(new Background(new BackgroundFill(ColorHelper.getFillTransparent(charakter), CornerRadii.EMPTY, Insets.EMPTY)));
            getChildren().add(imageBox);
        }
        return this;
    }

}
