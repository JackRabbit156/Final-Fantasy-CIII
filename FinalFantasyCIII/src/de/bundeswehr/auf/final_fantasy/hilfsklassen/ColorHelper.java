package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.HLR;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.MDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.PDD;
import de.bundeswehr.auf.final_fantasy.charakter.model.klassen.TNK;
import javafx.scene.paint.Color;

public final class ColorHelper {

    private ColorHelper() {
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

}
