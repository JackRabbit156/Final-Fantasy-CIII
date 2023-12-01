package view;

import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import party.PartyController;

import java.util.Map;

public class LeisteOben extends HBox {
    public LeisteOben(PartyController partyController) {
        this.setMaxHeight(30.0);
        this.setMaxWidth(1536.0);
        Background containerBackground = new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY));
        Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende = partyController.getParty().getVerbrauchsgegenstaende();
        Map<Material, IntegerProperty> materialien = partyController.getParty().getMaterialien();
        //HEILTRAENKE
        Label heilTraenkeTitel = new Label("Heiltränke (G/M/T): ");
        Label grosseHeiltraenke = new Label();
        grosseHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.GROSSER_HEILTRANK).asString());
        Label trennungGrosseHTMittlereHT = new Label("/");
        Label mittlereHeiltraenke = new Label();
        mittlereHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.MITTLERER_HEILTRANK).asString());
        Label trennungMittlereHTMKleineHT = new Label("/");
        Label kleineHeiltraenke = new Label();
        kleineHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.KLEINER_HEILTRANK).asString());
        HBox heiltraenke = new HBox(heilTraenkeTitel, grosseHeiltraenke, trennungGrosseHTMittlereHT, mittlereHeiltraenke, trennungMittlereHTMKleineHT, kleineHeiltraenke);
        heiltraenke.setBackground(containerBackground);
        //MANATRAENKE
        Label manaTraenkeTitel = new Label("Manatränke (G/M/T): ");
        Label grosseManatraenke = new Label();
        grosseManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.GROSSER_MANATRANK).asString());
        Label trennungGrosseMTMittlereMT = new Label("/");
        Label mittlereManatraenke = new Label();
        mittlereManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.MITTLERER_MANATRANK).asString());
        Label trennungMittlereMTMKleineMT = new Label("/");
        Label kleineManatraenke = new Label();
        kleineManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.KLEINER_MANATRANK).asString());
        HBox manatraenke = new HBox(manaTraenkeTitel, grosseManatraenke, trennungGrosseMTMittlereMT, mittlereManatraenke, trennungMittlereMTMKleineMT, kleineManatraenke);
        manatraenke.setBackground(containerBackground);
        //Materialien
        Label eisenErzLbl = new Label("Eisenerz: ");
        Label eisenErz = new Label();
        eisenErz.textProperty().bind(materialien.get(Material.EISENERZ).asString());
        HBox eisenErzContainer = new HBox(eisenErzLbl, eisenErz);
        eisenErzContainer.setBackground(containerBackground);

        Label goldErzLbl = new Label("Golderz: ");
        Label goldErz = new Label();
        goldErz.textProperty().bind(materialien.get(Material.GOLDERZ).asString());
        HBox goldErzContainer = new HBox(goldErzLbl, goldErz);
        goldErzContainer.setBackground(containerBackground);

        Label mithrilLbl = new Label("Mithril: ");
        Label mithril = new Label();
        mithril.textProperty().bind(materialien.get(Material.MITHRIL).asString());
        HBox mithrilContainer = new HBox(mithrilLbl, mithril);
        mithrilContainer.setBackground(containerBackground);

        Label popelLbl = new Label("Popel: ");
        Label popel = new Label();
        popel.textProperty().bind(materialien.get(Material.POPEL).asString());
        HBox popelContainer = new HBox(popelLbl, popel);
        popelContainer.setBackground(containerBackground);

        Label schleimLbl = new Label("Schleim: ");
        Label schleim = new Label();
        schleim.textProperty().bind(materialien.get(Material.SCHLEIM).asString());
        HBox schleimContainer = new HBox(schleimLbl, schleim);
        schleimContainer.setBackground(containerBackground);

        Label silberErzLbl = new Label("Silbererz: ");
        Label silberErz = new Label();
        silberErz.textProperty().bind(materialien.get(Material.SILBERERZ).asString());
        HBox silberErzContainer = new HBox(silberErzLbl, silberErz);
        silberErzContainer.setBackground(containerBackground);

        //Gold
        Label goldLbl = new Label("Gold: ");
        Label gold = new Label();
        gold.textProperty().bind(partyController.getParty().goldProperty().asString());
        HBox goldContainer = new HBox(goldLbl, gold);
        goldContainer.setBackground(containerBackground);

        this.getChildren().addAll(heiltraenke, manatraenke, eisenErzContainer, goldErzContainer, mithrilContainer, popelContainer, schleimContainer, silberErzContainer, goldContainer);
        this.setSpacing(15.0);
    }
}
