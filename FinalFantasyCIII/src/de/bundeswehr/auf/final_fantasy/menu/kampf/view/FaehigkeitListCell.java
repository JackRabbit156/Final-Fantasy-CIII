package de.bundeswehr.auf.final_fantasy.menu.kampf.view;

import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class FaehigkeitListCell extends ListCell<Faehigkeit> {

    final Tooltip tooltip = new Tooltip();

    @Override
    public void updateItem(Faehigkeit faehigkeit, boolean empty) {
        super.updateItem(faehigkeit, empty);
        if (empty || faehigkeit == null) {
            setGraphic(null);
            setText(null);
            setTooltip(null);
        }
        else {
            String zielGruppe = faehigkeit.isIstFreundlich() ? "Party" : "Gegner-Team";
            String faehigkeitsTyp = faehigkeit.getFaehigkeitsTyp().equals("physisch") ? "Physisch" : "Magisch";
            tooltip.setText(faehigkeit.getBeschreibung() + "\n" +
                    "Zielgruppe: " + zielGruppe + "\n" +
                    "Anzahl Ziele: " + faehigkeit.getZielAnzahl() + "\n" +
                    "Stärke: " + faehigkeit.getEffektStaerke() + "\n" +
                    "Fähigkeits-Typ: " + faehigkeitsTyp);
            setTooltip(tooltip);
            setGraphic(new ImageView(new Image(faehigkeit.getIcon())));
            setText(String.format("%s (%d MP)", faehigkeit.getName(), faehigkeit.getManaKosten()));
        }
    }

}
