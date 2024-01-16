package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlaceHolder extends VBox {

    public PlaceHolder(String text) {
        super();
        getStyleClass().add("placeholder");
        Label label = new Label(text);
        label.getStyleClass().add("placeholderLabel");
        label.setWrapText(true);
        setAlignment(Pos.CENTER);
        getChildren().add(label);
    }

}
