package de.bundeswehr.auf.final_fantasy.hilfsklassen.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class ColorHelper {

    public static final Color LIME_GREEN = Color.web("#7FFF00");
    public static final Color RED = Color.web("#CD423F");

    private ColorHelper() {
    }

    public static Color getFill(Charakter charakter) {
        return getFill(charakter.getKlasse());
    }

    public static Color getFill(Klasse klasse) {
        if (klasse instanceof HLR) {
            return Color.LIMEGREEN;
        }
        else if (klasse instanceof PDD) {
            return Color.CRIMSON;
        }
        else if (klasse instanceof MDD) {
            return Color.CORNFLOWERBLUE;
        }
        else if (klasse instanceof TNK) {
            return Color.GREY;
        }
        return Color.WHITE;
    }

    public static Paint getFillTransparent(Klasse klasse) {
        return getFill(klasse).deriveColor(1.0, 1.0, 1.0, 0.5);
    }

    public static Paint getFillTransparent(Charakter charakter) {
        return getFillTransparent(charakter.getKlasse());
    }

    public static Color getStroke(Charakter charakter) {
        return getStroke(charakter.getKlasse());
    }

    public static Color getStroke(Klasse klasse) {
        if (klasse instanceof HLR) {
            return Color.LIMEGREEN;
        }
        else if (klasse instanceof PDD) {
            return Color.CRIMSON;
        }
        else if (klasse instanceof MDD) {
            return Color.CORNFLOWERBLUE;
        }
        else if (klasse instanceof TNK) {
            return Color.WHITE;
        }
        return Color.GREY;
    }

    public static String healthBarColor(Charakter charakter) {
        return healthBarColor(charakter.getGesundheitsPunkte(), charakter.getMaxGesundheitsPunkte());
    }

    public static String healthBarColor(int gesundheitsPunkte, int maxGesundheitsPunkte) {
        String colorHealthBar;
        double gesundheitsPunktePercent = gesundheitsPunkte / (double) maxGesundheitsPunkte;
        if (gesundheitsPunktePercent >= 0.5) {
            colorHealthBar = "-fx-accent: #00FF00;";
        }
        else if (gesundheitsPunktePercent >= 0.2) {
            colorHealthBar = "-fx-accent: #FF8C00;";
        }
        else {
            colorHealthBar = "-fx-accent: #FF0000;";
        }
        return colorHealthBar;
    }

}
