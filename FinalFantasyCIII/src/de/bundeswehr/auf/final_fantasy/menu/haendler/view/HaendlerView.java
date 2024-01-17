package de.bundeswehr.auf.final_fantasy.menu.haendler.view;


import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.TableViewFueller;
import de.bundeswehr.auf.final_fantasy.menu.haendler.Haendler;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import de.bundeswehr.auf.final_fantasy.party.PartyController;

import java.util.Map;


public class HaendlerView extends BorderPane {

    /**
     * Der Konstruktor der HaendlerView
     *
     * @author OF Kretschmer
     * @since 04.12.23
     */
    public HaendlerView() {
        this.setBackground(new Background(new BackgroundImage(new Image("/haendler/bild2.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
    }

}
