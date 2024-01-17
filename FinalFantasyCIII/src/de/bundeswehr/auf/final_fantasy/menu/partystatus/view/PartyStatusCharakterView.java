package de.bundeswehr.auf.final_fantasy.menu.partystatus.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.ColorHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PartyStatusCharakterView extends HBox {

    private Label accessoireDrei;
    private ImageView accessoireDreiIcon;
    private Tooltip accessoireDreiTT;
    private Label accessoireEins;
    private ImageView accessoireEinsIcon;
    private Tooltip accessoireEinsTT;
    private Label accessoireZwei;
    private ImageView accessoireZweiIcon;
    private Tooltip accessoireZweiTT;
    private Label beweglichkeit;
    private SpielerCharakter charakter;
    private ImageView charakterBild;
    private Label erfahrungBisLevelUp;
    private Label genauigkeit;
    private Label gesundheitReg;
    private Text gesundheitsPunkteAlsText;
    private ProgressBar healthBar;
    private Text level;
    private Rectangle levelBox;
    private Label mVerteidigung;
    private Label magischeAttacke;
    private ProgressBar manaBar;
    private Text manaPunkteAlsText;
    private Label manaReg;
    private Text nameDesCharakters;
    private Label offeneAttributspunkte;
    private Label offeneSkillpunkte;
    private Label pVerteidigung;
    private Label physischeAttacke;
    private Label resistenz;
    private Label ruestung;
    private ImageView ruestungIcon;
    private Tooltip ruestungTT;
    private Label waffe;
    private ImageView waffeIcon;
    private Tooltip waffeTT;

    /**
     * Baut einen Charakter für den PartyStatus auf
     *
     * @param charakter de.bundeswehr.auf.final_fantasy.charakter für die Zeile
     * @author Nick
     * @since 06.12.2023
     */
    public PartyStatusCharakterView(SpielerCharakter charakter) {
        this.charakter = charakter;
        this.setVisible(charakter != null);
        charakter();
        stufe();
        angriff();
        verteidigung();
        regeneration();
        sekundarWerte();
        waffeRuestung();
        accessoires();
        this.getStyleClass().add("partystatusCharakterContainer");
        this.ansichtAktualisieren(this.charakter);
    }

    /**
     * Aktualisiert alle Anzeigekomponenten der Ansicht anhand des neuen/aktualisierten Charakters
     *
     * @param spielerCharakter neuer/aktualisierter Charakter
     * @author Nick
     * @since 02.12.2023
     */
    public void ansichtAktualisieren(SpielerCharakter spielerCharakter) {
        this.charakter = spielerCharakter;
        if (spielerCharakter == null) {
            this.setVisible(false);
            this.setManaged(false);
        }
        else {
            levelBox.setStroke(Color.BLACK);
            level.setText(spielerCharakter.getLevel() + "");
            levelBox.setFill(ColorHelper.getFill(spielerCharakter.getKlasse()));

            nameDesCharakters.setText(spielerCharakter.getName() + " (" + spielerCharakter.getKlasse().getBezeichnung() + ")");
            healthBar.setProgress(spielerCharakter.getGesundheitsPunkte() / (double) spielerCharakter.getMaxGesundheitsPunkte());
            gesundheitsPunkteAlsText.setText(spielerCharakter.getGesundheitsPunkte() + "/" + spielerCharakter.getMaxGesundheitsPunkte() + " HP");
            healthBar.setStyle(ColorHelper.healthBarColor(spielerCharakter));

            manaBar.setProgress(spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
            manaPunkteAlsText.setText(spielerCharakter.getManaPunkte() + "/" + spielerCharakter.getMaxManaPunkte() + " MP");

            charakterBild.setImage(new Image(spielerCharakter.getGrafischeDarstellung(), 0.0, 90.0, true, true));

            erfahrungBisLevelUp.setText((100 - (spielerCharakter.getErfahrungsPunkte() % 100)) + "");
            offeneAttributspunkte.setText("" + spielerCharakter.getOffeneAttributpunkte());
            offeneSkillpunkte.setText("" + spielerCharakter.getOffeneFaehigkeitspunkte());

            gesundheitReg.setText("" + ((int) Math.round(spielerCharakter.getGesundheitsRegeneration() / 8.0)));
            manaReg.setText("" + ((int) Math.round(spielerCharakter.getManaRegeneration() / 8.0)));

            physischeAttacke.setText("" + spielerCharakter.getPhysischeAttacke());
            magischeAttacke.setText("" + spielerCharakter.getMagischeAttacke());

            genauigkeit.setText("" + spielerCharakter.getGenauigkeit());
            beweglichkeit.setText("" + spielerCharakter.getBeweglichkeit());

            pVerteidigung.setText("" + spielerCharakter.getVerteidigung());
            mVerteidigung.setText("" + spielerCharakter.getMagischeVerteidigung());
            resistenz.setText("" + spielerCharakter.getResistenz());

            if (spielerCharakter.getWaffe() != null) {
                Waffe charakterWaffe = spielerCharakter.getWaffe();
                waffe.setText(charakterWaffe.getName());
                waffeIcon.setImage(new Image(charakterWaffe.getIcon(), 32.0, 0.0, true, false));
                waffeTT.setText(charakterWaffe.getClass().getSimpleName() + ": " + charakterWaffe.getName() + "\n" +
                        "Physischer Angriff: " + charakterWaffe.getAttacke() + "\n" +
                        "Magischer Angriff: " + charakterWaffe.getMagischeAttacke() + "\n" +
                        "Beweglichkeit: " + charakterWaffe.getBeweglichkeit() + "\n" +
                        "Genauigkeit: " + charakterWaffe.getGenauigkeit() + "\n" +
                        "Verkaufswert: " + charakterWaffe.getVerkaufswert() + "\n"
                );
                waffeIcon.setVisible(true);
            }
            else {
                waffe.setText("");
                waffeIcon.setVisible(false);
            }
            if (spielerCharakter.getRuestung() != null) {
                Ruestung charakterRuestung = spielerCharakter.getRuestung();
                ruestung.setText(charakterRuestung.getName());
                ruestungIcon.setImage(new Image(charakterRuestung.getIcon(), 32.0, 0.0, true, false));
                ruestungTT.setText(charakterRuestung.getClass().getSimpleName() + ": " + charakterRuestung.getName() + "\n" +
                        "Physische Verteidigung: " + charakterRuestung.getVerteidigung() + "\n" +
                        "Magische Verteidigung: " + charakterRuestung.getMagischeVerteidigung() + "\n" +
                        "Resistenz: " + charakterRuestung.getResistenz() + "\n" +
                        "Maximale Gesundheit: " + charakterRuestung.getMaxGesundheitsPunkte() + "\n" +
                        "Maximales Mana: " + charakterRuestung.getMaxManaPunkte() + "\n" +
                        "Verkaufswert: " + charakterRuestung.getVerkaufswert() + "\n");
                ruestungIcon.setVisible(true);
            }
            else {
                ruestung.setText("");
                ruestungIcon.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(0) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(0);
                accessoireEins.setText(charakterAccessoire.getName());
                accessoireEinsIcon.setImage(new Image(charakterAccessoire.getIcon(), 32.0, 0.0, true, false));
                accessoireEinsTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireEinsIcon.setVisible(true);
            }
            else {
                accessoireEins.setText("");
                accessoireEinsIcon.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(1) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(1);
                accessoireZwei.setText(charakterAccessoire.getName());
                accessoireZweiIcon.setImage(new Image(charakterAccessoire.getIcon(), 32.0, 0.0, true, false));
                accessoireZweiTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireZweiIcon.setVisible(true);
            }
            else {
                accessoireZwei.setText("");
                accessoireZweiIcon.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(2) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(2);
                accessoireDrei.setText(charakterAccessoire.getName());
                accessoireDreiIcon.setImage(new Image(charakterAccessoire.getIcon(), 32.0, 0.0, true, false));
                accessoireDreiTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireDreiIcon.setVisible(true);
            }
            else {
                accessoireDrei.setText("");
                accessoireDreiIcon.setVisible(false);
            }
            this.setMaxSize(1300.0, 200.0);
            this.setAlignment(Pos.CENTER);
            this.setManaged(true);
            this.setVisible(true);
        }
    }

    private void accessoires() {
        accessoireEins = new Label();
        accessoireEins.getStyleClass().add("partystatusCharakterText");
        accessoireEinsIcon = new ImageView();
        accessoireEinsTT = new Tooltip();
        Tooltip.install(accessoireEinsIcon, accessoireEinsTT);
        HBox eins = new HBox(accessoireEinsIcon, accessoireEins);
        eins.getStyleClass().add("partystatusCharakterZeileRechts");
        accessoireZwei = new Label();
        accessoireZwei.getStyleClass().add("partystatusCharakterText");
        accessoireZweiIcon = new ImageView();
        accessoireZweiTT = new Tooltip();
        Tooltip.install(accessoireZweiIcon, accessoireZweiTT);
        HBox zwei = new HBox(accessoireZweiIcon, accessoireZwei);
        zwei.getStyleClass().add("partystatusCharakterZeileRechts");
        accessoireDrei = new Label();
        accessoireDrei.getStyleClass().add("partystatusCharakterText");
        accessoireDreiIcon = new ImageView();
        accessoireDreiTT = new Tooltip();
        Tooltip.install(accessoireDreiIcon, accessoireDreiTT);
        HBox drei = new HBox(accessoireDreiIcon, accessoireDrei);
        drei.getStyleClass().add("partystatusCharakterZeileRechts");
        VBox accessoires = new VBox(eins, zwei, drei);
        accessoires.getStyleClass().add("partystatusCharakterSpalteRechts");
        accessoires.setPrefWidth(200.0);
        this.getChildren().add(accessoires);
    }

    private void angriff() {
        ImageView physischeAttackeIcon = new ImageView(new Image("/icons/physischeAttacke.png", 32.0, 0, true, false));
        Tooltip physischeAttackeTT = new Tooltip("Physische Attacke");
        Tooltip.install(physischeAttackeIcon, physischeAttackeTT);
        physischeAttacke = new Label();
        physischeAttacke.getStyleClass().add("partystatusCharakterWert");
        HBox pA = new HBox(physischeAttackeIcon, physischeAttacke);
        pA.getStyleClass().add("partystatusCharakterZeile");
        ImageView magischeAttackeIcon = new ImageView(new Image("/icons/magischeAttacke.png", 32.0, 0, true, false));
        Tooltip magischeAttackeTT = new Tooltip("Magische Attacke");
        Tooltip.install(magischeAttackeIcon, magischeAttackeTT);
        magischeAttacke = new Label();
        magischeAttacke.getStyleClass().add("partystatusCharakterWert");
        HBox mA = new HBox(magischeAttackeIcon, magischeAttacke);
        mA.getStyleClass().add("partystatusCharakterZeile");
        VBox angriff = new VBox(pA, mA);
        angriff.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().add(angriff);
    }

    private void charakter() {
        //Charakter mit Leisten bauen
        charakterBild = new ImageView();
        nameDesCharakters = new Text();
        nameDesCharakters.getStyleClass().add("partystatusCharakterName");
        levelBox = new Rectangle(45.0, 40.0);
        level = new Text();
        level.getStyleClass().add("partystatusCharakterLevel");
        StackPane stackPaneLevelAnzeige = new StackPane(levelBox, level);
        healthBar = new ProgressBar();
        healthBar.getStyleClass().add("partystatusCharakterProgressbar");
        gesundheitsPunkteAlsText = new Text();
        gesundheitsPunkteAlsText.getStyleClass().add("partystatusCharakterBarText");
        StackPane healthBarStack = new StackPane(healthBar, gesundheitsPunkteAlsText);
        healthBarStack.getStyleClass().add("partystatusCharakterBarStack");
        manaBar = new ProgressBar();
        manaBar.setStyle("-fx-accent: #00BFFF;");
        manaBar.getStyleClass().add("partystatusCharakterProgressbar");
        manaPunkteAlsText = new Text();
        manaPunkteAlsText.getStyleClass().add("partystatusCharakterBarText");
        StackPane manaBarStack = new StackPane(manaBar, manaPunkteAlsText);
        manaBarStack.getStyleClass().add("partystatusCharakterBarStack");
        VBox bars = new VBox(healthBarStack, manaBarStack);
        bars.setPadding(new Insets(2.0, 0.0, 0.0, 0.0));
        HBox bar = new HBox(stackPaneLevelAnzeige, bars);
        bar.setMaxWidth(200.0);
        VBox charakterDarstellung = new VBox(nameDesCharakters, bar, charakterBild);
        charakterDarstellung.setAlignment(Pos.CENTER);
        charakterDarstellung.setMinWidth(270.0);
        this.getChildren().add(charakterDarstellung);
    }

    private void regeneration() {
        ImageView gesRegIcon = new ImageView(new Image("/icons/gesundheitsRegeneration.png", 32.0, 0, true, false));
        Tooltip gesRegTT = new Tooltip("Gesundheitsregeneration pro Runde");
        Tooltip.install(gesRegIcon, gesRegTT);
        gesundheitReg = new Label();
        gesundheitReg.getStyleClass().add("partystatusCharakterWert");
        HBox geR = new HBox(gesRegIcon, gesundheitReg);
        geR.getStyleClass().add("partystatusCharakterZeile");
        ImageView manaRIcon = new ImageView(new Image("/icons/manaRegeneration.png", 32.0, 0, true, false));
        Tooltip manaRTT = new Tooltip("Manaregeneration pro Runde");
        Tooltip.install(manaRIcon, manaRTT);
        manaReg = new Label();
        manaReg.getStyleClass().add("partystatusCharakterWert");
        HBox maR = new HBox(manaRIcon, manaReg);
        maR.getStyleClass().add("partystatusCharakterZeile");
        VBox reg = new VBox(geR, maR);
        reg.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().add(reg);
    }

    private void sekundarWerte() {
        ImageView genauIcon = new ImageView(new Image("/icons/genauigkeit.png", 32.0, 0, true, false));
        Tooltip genauTT = new Tooltip("Genauigkeit");
        Tooltip.install(genauIcon, genauTT);
        genauigkeit = new Label();
        genauigkeit.getStyleClass().add("partystatusCharakterWert");
        HBox genau = new HBox(genauIcon, genauigkeit);
        genau.getStyleClass().add("partystatusCharakterZeile");
        ImageView bewegIcon = new ImageView(new Image("/icons/beweglichkeit.png", 32.0, 0, true, false));
        Tooltip bewegTT = new Tooltip("Beweglichkeit");
        Tooltip.install(bewegIcon, bewegTT);
        beweglichkeit = new Label();
        beweglichkeit.getStyleClass().add("partystatusCharakterWert");
        HBox beweg = new HBox(bewegIcon, beweglichkeit);
        beweg.getStyleClass().add("partystatusCharakterZeile");
        ImageView resiIcon = new ImageView(new Image("/icons/resistenz.png", 32.0, 0, true, false));
        Tooltip resiTT = new Tooltip("Resistenz");
        Tooltip.install(resiIcon, resiTT);
        resistenz = new Label();
        resistenz.getStyleClass().add("partystatusCharakterWert");
        HBox resi = new HBox(resiIcon, resistenz);
        resi.getStyleClass().add("partystatusCharakterZeile");
        VBox sekundaerWerte = new VBox(genau, beweg, resi);
        sekundaerWerte.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().add(sekundaerWerte);
    }

    private void stufe() {
        // Stufe (EP, Attributspunkte, Skillpunkte)
        ImageView erfahrungBisLevelUpIcon = new ImageView(new Image("/icons/epBisLevelUp.png", 32.0, 0, true, false));
        Tooltip erfahrungBisLevelUpTT = new Tooltip("Erfahrungspunkte bis Level-Up");
        Tooltip.install(erfahrungBisLevelUpIcon, erfahrungBisLevelUpTT);
        erfahrungBisLevelUp = new Label();
        erfahrungBisLevelUp.getStyleClass().add("partystatusCharakterWert");
        HBox erfahrung = new HBox(erfahrungBisLevelUpIcon, erfahrungBisLevelUp);
        erfahrung.getStyleClass().add("partystatusCharakterZeile");
        ImageView offeneAttributspunkteIcon = new ImageView(new Image("/icons/offeneAttributspunkte.png", 32.0, 0, true, false));
        Tooltip offeneAttributspunkteTT = new Tooltip("Offene Attributspunkte");
        Tooltip.install(offeneAttributspunkteIcon, offeneAttributspunkteTT);
        offeneAttributspunkte = new Label();
        offeneAttributspunkte.getStyleClass().add("partystatusCharakterWert");
        HBox attribute = new HBox(offeneAttributspunkteIcon, offeneAttributspunkte);
        attribute.getStyleClass().add("partystatusCharakterZeile");
        ImageView offeneSkillpunkteIcon = new ImageView(new Image("/icons/offeneFaehigkeitspunkte.png", 32.0, 0, true, false));
        Tooltip offeneSkillpunkteTT = new Tooltip("Offene Fähigkeitspunkte");
        Tooltip.install(offeneSkillpunkteIcon, offeneSkillpunkteTT);
        offeneSkillpunkte = new Label();
        HBox skill = new HBox(offeneSkillpunkteIcon, offeneSkillpunkte);
        offeneSkillpunkte.getStyleClass().add("partystatusCharakterWert");
        skill.getStyleClass().add("partystatusCharakterZeile");
        VBox stufe = new VBox(erfahrung, attribute, skill);
        stufe.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().add(stufe);
    }

    private void verteidigung() {
        ImageView pVtIcon = new ImageView(new Image("/icons/physischeVerteidigung.png", 32.0, 0, true, false));
        Tooltip pVtTT = new Tooltip("Physische Verteidigung");
        Tooltip.install(pVtIcon, pVtTT);
        pVerteidigung = new Label();
        pVerteidigung.getStyleClass().add("partystatusCharakterWert");
        HBox pVt = new HBox(pVtIcon, pVerteidigung);
        pVt.getStyleClass().add("partystatusCharakterZeile");
        ImageView mVtIcon = new ImageView(new Image("/icons/magischeVerteidigung.png", 32.0, 0, true, false));
        Tooltip mVTT = new Tooltip("Magische Verteidigung");
        Tooltip.install(mVtIcon, mVTT);
        mVerteidigung = new Label();
        mVerteidigung.getStyleClass().add("partystatusCharakterWert");
        HBox mV = new HBox(mVtIcon, mVerteidigung);
        mV.getStyleClass().add("partystatusCharakterZeile");
        VBox verteidigung = new VBox(pVt, mV);
        verteidigung.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().add(verteidigung);
    }

    private void waffeRuestung() {
        waffe = new Label();
        waffe.getStyleClass().add("partystatusCharakterText");
        waffeIcon = new ImageView();
        waffeTT = new Tooltip();
        Tooltip.install(waffeIcon, waffeTT);
        HBox wBox = new HBox(waffe, waffeIcon);
        wBox.getStyleClass().add("partystatusCharakterZeileLinks");
        ruestung = new Label();
        ruestung.getStyleClass().add("partystatusCharakterText");
        ruestungIcon = new ImageView();
        ruestungTT = new Tooltip();
        Tooltip.install(ruestungIcon, ruestungTT);
        HBox rBox = new HBox(ruestung, ruestungIcon);
        rBox.getStyleClass().add("partystatusCharakterZeileLinks");
        VBox waffeRuestung = new VBox(wBox, rBox);
        waffeRuestung.getStyleClass().add("partystatusCharakterSpalteLinks");
        waffeRuestung.setPrefWidth(200.0);
        this.getChildren().add(waffeRuestung);
    }

}
