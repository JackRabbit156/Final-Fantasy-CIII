package view;

import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import party.PartyController;

import java.util.Map;

public class LeisteOben extends HBox {
    public LeisteOben(PartyController partyController) {
        Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende = partyController.getParty().getVerbrauchsgegenstaende();
        Map<Material, IntegerProperty> materialien = partyController.getParty().getMaterialien();
        //HEILTRAENKE
        double iconSeitenLaenge = 22.0;
        ImageView heiltrank = new ImageView(new Image("/icons/grosserHeiltrank.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label grosseHeiltraenke = new Label();
        grosseHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.GROSSER_HEILTRANK).asString());

        Label trennungGrosseHTMittlereHT = new Label("/");

        Label mittlereHeiltraenke = new Label();
        mittlereHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.MITTLERER_HEILTRANK).asString());

        Label trennungMittlereHTMKleineHT = new Label("/");

        Label kleineHeiltraenke = new Label();
        kleineHeiltraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.KLEINER_HEILTRANK).asString());

        HBox heiltraenke = new HBox(heiltrank, grosseHeiltraenke, trennungGrosseHTMittlereHT, mittlereHeiltraenke, trennungMittlereHTMKleineHT, kleineHeiltraenke);
        heiltraenke.getStyleClass().add("blockInLeiste");
        Tooltip heiltraenkeTT = new Tooltip("Heiltränke: Groß/Mittel/Klein");
        heiltraenkeTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(heiltraenke, heiltraenkeTT);

        //MANATRAENKE
        ImageView manatrank = new ImageView(new Image("/icons/grosserManatrank.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label grosseManatraenke = new Label();
        grosseManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.GROSSER_MANATRANK).asString());

        Label trennungGrosseMTMittlereMT = new Label("/");

        Label mittlereManatraenke = new Label();
        mittlereManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.MITTLERER_MANATRANK).asString());

        Label trennungMittlereMTMKleineMT = new Label("/");

        Label kleineManatraenke = new Label();
        kleineManatraenke.textProperty().bind(verbrauchsgegenstaende.get(Verbrauchsgegenstand.KLEINER_MANATRANK).asString());

        HBox manatraenke = new HBox(manatrank, grosseManatraenke, trennungGrosseMTMittlereMT, mittlereManatraenke, trennungMittlereMTMKleineMT, kleineManatraenke);
        manatraenke.getStyleClass().add("blockInLeiste");
        Tooltip manatraenkeTT = new Tooltip("Manatränke: Groß/Mittel/Klein");
        manatraenkeTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(manatraenke, manatraenkeTT);

        //Materialien
        ImageView eisenErzLbl = new ImageView(new Image("/icons/eisenErz.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label eisenErz = new Label();
        eisenErz.textProperty().bind(materialien.get(Material.EISENERZ).asString());
        HBox eisenErzContainer = new HBox(eisenErzLbl, eisenErz);
        eisenErzContainer.getStyleClass().add("blockInLeiste");
        Tooltip eisenErzTT = new Tooltip("Eisenerz");
        eisenErzTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(eisenErzContainer, eisenErzTT);

        ImageView goldErzLbl = new ImageView(new Image("/icons/goldErz.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label goldErz = new Label();
        goldErz.textProperty().bind(materialien.get(Material.GOLDERZ).asString());
        HBox goldErzContainer = new HBox(goldErzLbl, goldErz);
        goldErzContainer.getStyleClass().add("blockInLeiste");
        Tooltip goldErzTT = new Tooltip("Golderz");
        goldErzTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(goldErzContainer, goldErzTT);

        ImageView mithrilLbl = new ImageView(new Image("/icons/mithril.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label mithril = new Label();
        mithril.textProperty().bind(materialien.get(Material.MITHRIL).asString());
        HBox mithrilContainer = new HBox(mithrilLbl, mithril);
        mithrilContainer.getStyleClass().add("blockInLeiste");
        Tooltip mithrilTT = new Tooltip("Mithril");
        mithrilTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(mithrilContainer, mithrilTT);

        ImageView popelLbl = new ImageView(new Image("/icons/popel.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label popel = new Label();
        popel.textProperty().bind(materialien.get(Material.POPEL).asString());
        HBox popelContainer = new HBox(popelLbl, popel);
        popelContainer.getStyleClass().add("blockInLeiste");
        Tooltip popelTT = new Tooltip("Popel");
        popelTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(popelContainer, popelTT);

        ImageView schleimLbl = new ImageView(new Image("/icons/schleim.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label schleim = new Label();
        schleim.textProperty().bind(materialien.get(Material.SCHLEIM).asString());
        HBox schleimContainer = new HBox(schleimLbl, schleim);
        schleimContainer.getStyleClass().add("blockInLeiste");
        Tooltip schleimTT = new Tooltip("Schleim");
        schleimTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(schleimContainer, schleimTT);

        ImageView silberErzLbl = new ImageView(new Image("/icons/silberErz.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label silberErz = new Label();
        silberErz.textProperty().bind(materialien.get(Material.SILBERERZ).asString());
        HBox silberErzContainer = new HBox(silberErzLbl, silberErz);
        silberErzContainer.getStyleClass().add("blockInLeiste");
        Tooltip silberErzTT = new Tooltip("Silbererz");
        silberErzTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(silberErzContainer, silberErzTT);

        //Gold
        ImageView goldLbl = new ImageView(new Image("/icons/gold.png", iconSeitenLaenge, iconSeitenLaenge,true,false));
        Label gold = new Label();
        gold.textProperty().bind(partyController.getParty().goldProperty().asString());
        HBox goldContainer = new HBox(goldLbl, gold);
        goldContainer.getStyleClass().add("blockInLeiste");
        Tooltip goldTT = new Tooltip("Gold");
        goldTT.getStyleClass().add("leisteTooltip");
        Tooltip.install(goldContainer, goldTT);
        HBox root = new HBox(heiltraenke, manatraenke, eisenErzContainer, goldErzContainer, mithrilContainer, popelContainer, schleimContainer, silberErzContainer, goldContainer);
        root.setMaxWidth(1536.0);
        root.minWidth(1536.0);
        root.setSpacing(15.0);
        this.getChildren().add(root);
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("leiste");
        this.setMaxWidth(1536.0);
        this.setMaxHeight(30.0);
    }
}