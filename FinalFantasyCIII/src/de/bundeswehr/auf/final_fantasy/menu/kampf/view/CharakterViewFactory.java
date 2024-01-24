package de.bundeswehr.auf.final_fantasy.menu.kampf.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Buff;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class CharakterViewFactory {

    private static class Positionen {

        double[] buffs;
        double[] healthBar;
        double[] hp;
        double[] iv;
        double[] level;
        double[] mp;
        double[] name;

    }

    private static final ColorAdjust deadGrey = new ColorAdjust();

    private Pane buffs;
    private String colorHealthBar;
    private Rectangle levelBox;
    private Text nameDesCharakters;
    private final Pane parent;
    private final double xHealthBarOffset = 0;
    private final double yHealthBarOffset = -40;

    CharakterViewFactory(Pane parent) {
        this.parent = parent;
        deadGrey.setBrightness(-0.67);
    }

    void addFeind(Charakter aktuellerCharakter, int pos, Charakter feind) {
        Positionen positionen;
        if (feind != aktuellerCharakter) {
            positionen = createPositionen(KampfView.POSITIONEN_GEGNER_X[pos], KampfView.POSITIONEN_GEGNER_Y[pos], feind.getName());
        }
        else {
            positionen = aktuellerCharakter(feind.getName());
        }
        add(feind, feind(positionen), Color.DARKRED);
    }

    void addFeindTot(int pos, Charakter feind) {
        addTot(feind, feind(createPositionen(KampfView.POSITIONEN_GEGNER_X[pos], KampfView.POSITIONEN_GEGNER_Y[pos], feind.getName())));
    }

    void addHauptCharakter(Charakter aktuellerCharakter, int pos, Charakter charakter) {
        Positionen positionen;
        if (charakter != aktuellerCharakter) {
            positionen = createPositionen(KampfView.POSITIONEN_PARTY_X[pos], KampfView.POSITIONEN_PARTY_Y[pos], charakter.getName());
        }
        else {
            positionen = aktuellerCharakter(charakter.getName());
        }
        add(charakter, positionen, Color.ROYALBLUE);
    }

    void addHauptCharakterTot(int pos, Charakter charakter) {
        addTot(charakter, createPositionen(KampfView.POSITIONEN_PARTY_X[pos], KampfView.POSITIONEN_PARTY_Y[pos], charakter.getName()));
    }

    void addSoeldner(Charakter aktuellerCharakter, int pos, Charakter charakter) {
        Positionen positionen;
        if (charakter != aktuellerCharakter) {
            positionen = createPositionenSoeldner(KampfView.POSITIONEN_PARTY_X[pos], KampfView.POSITIONEN_PARTY_Y[pos], charakter.getName());
        }
        else {
            positionen = aktuellerCharakter(charakter.getName());
        }
        add(charakter, positionen, Color.ROYALBLUE);
    }

    void addSoeldnerTot(int pos, Charakter charakter) {
        addTot(charakter, createPositionenSoeldner(KampfView.POSITIONEN_PARTY_X[pos], KampfView.POSITIONEN_PARTY_Y[pos], charakter.getName()));
    }

    void prepareCharakterView(Charakter charakter, List<Buff> buffs) {
        colorHealthBar = ColorHelper.healthBarColor(charakter);
        levelBox = new Rectangle(40, 37);
        levelBox.setFill(ColorHelper.getFill(charakter));
        levelBox.setStroke(Color.BLACK);

        nameDesCharakters = new Text(charakter.getName());
        nameDesCharakters.setFont(Font.font("verdana", FontPosture.ITALIC, 17));

        this.buffs = new VBox(5);
        for (Buff buff : buffs) {
            Label label = new Label("", buff.getIcon(25));
            label.setBackground(KampfView.FAINT_BACKGROUND);
            label.setTooltip(new Tooltip(buff.getTooltip()));
            Color color = buff.isDebuff() ? ColorHelper.RED : ColorHelper.LIME_GREEN;
            label.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            this.buffs.getChildren().add(label);
        }
    }

    private ImageView add(Charakter charakter, Positionen pos, Color nameTextColor) {
        ImageView iv = new ImageView(new Image(charakter.getGrafischeDarstellung(), 0, 216, true, true));
        iv.setLayoutX(pos.iv[0]);
        iv.setLayoutY(pos.iv[1]);

        ProgressBar healthBar = new ProgressBar(calculateGesundheitsPunktePercent(charakter));
        healthBar.setPrefSize(170, 25);
        healthBar.setStyle(colorHealthBar);
        healthBar.setLayoutX(pos.healthBar[0]);
        healthBar.setLayoutY(pos.healthBar[1]);

        Text gesundheitsPunkteAlsText = new Text(charakter.getGesundheitsPunkte() + "/"
                + charakter.getMaxGesundheitsPunkte() + " HP");
        gesundheitsPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));

        StackPane stackPaneHP = new StackPane();
        stackPaneHP.getChildren().addAll(healthBar, gesundheitsPunkteAlsText);
        stackPaneHP.setLayoutX(pos.hp[0]);
        stackPaneHP.setLayoutY(pos.hp[1]);
        gesundheitsPunkteAlsText.toFront();

        Text level = new Text(charakter.getLevel() + "");
        level.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));

        StackPane stackPaneLevelAnzeige = new StackPane();
        stackPaneLevelAnzeige.getChildren().addAll(levelBox, level);
        stackPaneLevelAnzeige.setLayoutX(pos.level[0]);
        stackPaneLevelAnzeige.setLayoutY(pos.level[1]);

        ProgressBar manaBar = new ProgressBar(calculateManaPunktePercent(charakter));
        manaBar.setPrefSize(170, 15);
        manaBar.setStyle("-fx-accent: -fx-blue;");

        Text manaPunkteAlsText = new Text(charakter.getManaPunkte() + "/"
                + charakter.getMaxManaPunkte() + " MP");
        manaPunkteAlsText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));

        StackPane stackPaneMP = new StackPane();
        stackPaneMP.getChildren().addAll(manaBar, manaPunkteAlsText);
        stackPaneMP.setLayoutX(pos.mp[0]);
        stackPaneMP.setLayoutY(pos.mp[1]);
        manaPunkteAlsText.toFront();

        nameDesCharakters.setFill(nameTextColor);
        nameDesCharakters.getStyleClass().add("kampfnametext");
        nameDesCharakters.setLayoutX(pos.name[0]);
        nameDesCharakters.setLayoutY(pos.name[1]);

        buffs.setLayoutX(pos.buffs[0]);
        buffs.setLayoutY(pos.buffs[1]);

        parent.getChildren().addAll(iv, stackPaneHP, stackPaneMP, stackPaneLevelAnzeige, nameDesCharakters, buffs);
        return iv;
    }

    private void addTot(Charakter charakter, Positionen pos) {
        ImageView iv = add(charakter, pos, Color.DARKGREY);
        iv.setEffect(deadGrey);
    }

    private Positionen aktuellerCharakter(String name) {
        return createPositionen(KampfView.POSITION_AKTUELLER_CHARAKTER[0], KampfView.POSITION_AKTUELLER_CHARAKTER[1], name);
    }

    private double calculateGesundheitsPunktePercent(Charakter charakter) {
        return charakter.getGesundheitsPunkte() / (double) charakter.getMaxGesundheitsPunkte();
    }

    private double calculateManaPunktePercent(Charakter charakter) {
        return charakter.getManaPunkte() / (double) charakter.getMaxManaPunkte();
    }

    private Positionen createPositionen(double ivX, double ivY, String name) {
        Positionen positionen = new Positionen();
        positionen.iv = new double[] { ivX, ivY };
        double x = positionen.iv[0];
        double y = positionen.iv[1];
        positionen.healthBar = new double[] { x + 90, y + yHealthBarOffset };
        positionen.hp = new double[] { x + xHealthBarOffset + 65, y + yHealthBarOffset };
        positionen.level = new double[] { x + xHealthBarOffset + 25, y + yHealthBarOffset };
        positionen.mp = new double[] { x + xHealthBarOffset + 65, y + yHealthBarOffset + 20 };
        positionen.name = new double[] { x + xHealthBarOffset + 150 - (name.length() * 5.7), y + yHealthBarOffset - 3 };
        positionen.buffs = new double[] { x + xHealthBarOffset + 234, y + yHealthBarOffset + 40 };
        return positionen;
    }

    private Positionen createPositionenSoeldner(double ivX, double ivY, String name) {
        Positionen positionen = new Positionen();
        positionen.iv = new double[] { ivX, ivY };
        double x = positionen.iv[0];
        double y = positionen.iv[1];
        positionen.healthBar = new double[] { x + xHealthBarOffset, y + yHealthBarOffset };
        positionen.hp = new double[] { x + xHealthBarOffset, y + yHealthBarOffset };
        positionen.level = new double[] { x + xHealthBarOffset - 40, y + yHealthBarOffset };
        positionen.mp = new double[] { x + xHealthBarOffset, y + yHealthBarOffset + 20 };
        positionen.name = new double[] { x + xHealthBarOffset + 85 - (name.length() * 5.7), y + yHealthBarOffset - 3 };
        positionen.buffs = new double[] { x + xHealthBarOffset + 169, y + yHealthBarOffset + 40 };
        return positionen;
    }

    private Positionen feind(Positionen positionen) {
        positionen.healthBar[0] = positionen.healthBar[0] - 40;
        positionen.hp[0] = positionen.hp[0] - 40;
        positionen.level[0] = positionen.level[0] + 169;
        positionen.mp[0] = positionen.mp[0] - 40;
        positionen.name[0] = positionen.name[0] - 15;
        positionen.buffs[0] = positionen.buffs[0] - 240;
        return positionen;
    }

}
