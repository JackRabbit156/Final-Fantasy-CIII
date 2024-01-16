package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class LeftCenterCell<S, T> extends TableCell<S, T> {

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText("");
        }
        else {
            setText(item + "");
        }
        setAlignment(Pos.CENTER_LEFT);
    }

}
