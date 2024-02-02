package de.bundeswehr.auf.final_fantasy.hilfsklassen.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PlaceHolder extends VBox {

    public PlaceHolder(String text) {
        getStyleClass().add("placeholder");
        Label label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.getStyleClass().add("placeholderLabel");
        label.setWrapText(true);
        setAlignment(Pos.CENTER);
        getChildren().add(label);
    }

}
