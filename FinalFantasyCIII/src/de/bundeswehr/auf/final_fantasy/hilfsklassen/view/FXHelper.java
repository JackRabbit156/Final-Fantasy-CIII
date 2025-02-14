package de.bundeswehr.auf.final_fantasy.hilfsklassen.view;

import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.skin.TableViewSkin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class FXHelper {

    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private FXHelper() {
    }

    public static <G> void autoFitColumns(TableView<G> tableView) {
        tableView.getItems().addListener((ListChangeListener<G>) c -> {
            if (tableView.getSkin() != null) {
                for (TableColumn<G, ?> column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
