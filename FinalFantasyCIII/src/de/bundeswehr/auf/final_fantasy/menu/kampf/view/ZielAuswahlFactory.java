package de.bundeswehr.auf.final_fantasy.menu.kampf.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.menu.kampf.Kampf;
import de.bundeswehr.auf.final_fantasy.menu.kampf.controller.KampfController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ZielAuswahlFactory {

    private final Kampf kampf;
    private final Pane parent;

    ZielAuswahlFactory(Pane parent, Kampf kampf) {
        this.parent = parent;
        this.kampf = kampf;
    }

    void addFeind(int pos, Charakter feind, EventHandler<MouseEvent> eventHandler) {
        if (feind != kampf.getAktuellerCharakter()) {
            double x = KampfView.POSITIONEN_GEGNER_X[pos];
            double y = KampfView.POSITIONEN_GEGNER_Y[pos];
            add(feind, x, y, "gegnerCharakterHover", "gegnerCharakterAusgewaehlt", eventHandler);
        }
    }

    void addTeam(int pos, Charakter charakter, EventHandler<MouseEvent> eventHandler) {
        double x;
        double y;
        if (charakter != kampf.getAktuellerCharakter()) {
            // Hauptcharakter ist im Hintergrund
            x = KampfView.POSITIONEN_PARTY_X[pos];
            y = KampfView.POSITIONEN_PARTY_Y[pos];
        }
        else {
            // Hauptcharakter ist im Aktionsbereich
            x = KampfView.POSITION_AKTUELLER_CHARAKTER[0];
            y = KampfView.POSITION_AKTUELLER_CHARAKTER[1];
        }
        add(charakter, x, y, "teamCharakterHover", "teamCharakterAusgewaehlt", eventHandler);
    }

    private void add(Charakter charakter, double x, double y, String styleHover, String styleAusgewaehlt, EventHandler<MouseEvent> eventHandler) {
        if (charakter.getGesundheitsPunkte() > 0) {
            ImageView iv = new ImageView(new Image(charakter.getGrafischeDarstellung(), 0, 216, true, true));
            iv.getStyleClass().add(styleHover);
            iv.setLayoutX(x);
            iv.setLayoutY(y);
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                ImageView ivGeklickt = new ImageView(new Image(charakter.getGrafischeDarstellung(), 0, 216, true, true));
                ivGeklickt.setLayoutX(x);
                ivGeklickt.setLayoutY(y);
                ivGeklickt.getStyleClass().add(styleAusgewaehlt);
                parent.getChildren().add(ivGeklickt);
                eventHandler.handle(event);
                event.consume();
            });
            parent.getChildren().addAll(iv);
        }
    }

}
