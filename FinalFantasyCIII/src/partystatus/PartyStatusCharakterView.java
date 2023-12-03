package partystatus;

import charakter.model.SpielerCharakter;
import charakter.model.klassen.*;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PartyStatusCharakterView extends HBox {
    private SpielerCharakter charakter;
    private ImageView charakterBild;
    private Label offeneAttributspunkte;
    private Label offeneSkillpunkte;
    private Label erfahrungBisLevelUp;
    private Label gesundheitReg;
    private Label manaReg;
    private Label physischeAttacke;
    private Label magischeAttacke;
    private Label genauigkeit;
    private Label beweglichkeit;
    private Label pVerteidigung;
    private Label mVerteidigung;
    private Label resistenz;
    private ImageView waffe;
    private ImageView ruestung;
    private ImageView accessoireEins;
    private ImageView accessoireZwei;
    private ImageView accessoireDrei;
    private Tooltip waffeTT;
    private Tooltip ruestungTT;
    private Tooltip accessoireEinsTT;
    private Tooltip accessoireZweiTT;
    private Tooltip accessoireDreiTT;
    private String colorHealthBar;
    private Color levelBoxColor;
    private int barWidth;
    private int barHeight;
    private Rectangle levelBox;
    private Text level;
    private StackPane stackPaneLevelAnzeige;
    private Text nameDesCharakters;
    private ProgressBar healthBar;
    private Text gesundheitsPunkteAlsText;
    private StackPane healthBarStack;
    private ProgressBar manaBar;
    private Text manaPunkteAlsText;
    private StackPane manaBarStack;


    public PartyStatusCharakterView(SpielerCharakter charakter) {
        this.charakter = charakter;
        if (charakter == null) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
        //TODO DUMMY BILD ERSETZEN
        //Charakter mit Leisten bauen
        charakterBild = new ImageView();
        nameDesCharakters = new Text();
        nameDesCharakters.getStyleClass().add("partystatusCharakterName");
        levelBox = new Rectangle(45.0, 40.0);
        level = new Text();
        stackPaneLevelAnzeige = new StackPane(levelBox, level);
        healthBar = new ProgressBar();
        healthBar.getStyleClass().add("partystatusCharakterProgressbar");
        gesundheitsPunkteAlsText = new Text();
        gesundheitsPunkteAlsText.getStyleClass().add("partystatusCharakterBarText");
        healthBarStack = new StackPane(healthBar, gesundheitsPunkteAlsText);
        healthBarStack.getStyleClass().add("partystatusCharakterBarStack");
        manaBar = new ProgressBar();
        manaBar.setStyle("-fx-accent: #00BFFF;");
        manaBar.getStyleClass().add("partystatusCharakterProgressbar");
        manaPunkteAlsText = new Text();
        manaPunkteAlsText.getStyleClass().add("partystatusCharakterBarText");
        manaBarStack = new StackPane(manaBar, manaPunkteAlsText);
        manaBarStack.getStyleClass().add("partystatusCharakterBarStack");
        VBox bars = new VBox(healthBarStack, manaBarStack);
        bars.setPadding(new Insets(2.0, 0.0, 0.0, 0.0));
        HBox bar = new HBox(stackPaneLevelAnzeige, bars);
        VBox charakterDarstellung = new VBox(nameDesCharakters, bar, charakterBild);
        charakterDarstellung.setAlignment(Pos.CENTER);
        ImageView erfahrungBisLevelUpIcon = new ImageView(new Image("/icons/epBisLevelUp.png", 32.0, 0, true, false));
        Tooltip erfahrungBisLevelUpTT = new Tooltip("Erfahrungspunkte bis Level-Up");
        Tooltip.install(erfahrungBisLevelUpIcon, erfahrungBisLevelUpTT);
        erfahrungBisLevelUp = new Label();
        HBox erfahrung = new HBox(erfahrungBisLevelUpIcon, erfahrungBisLevelUp);
        erfahrung.getStyleClass().add("partystatusCharakterZeile");
        ImageView offeneAttributspunkteIcon = new ImageView(new Image("/icons/offeneAttributspunkte.png", 32.0, 0, true, false));
        Tooltip offeneAttributspunkteTT = new Tooltip("Offene Attributspunkte");
        Tooltip.install(offeneAttributspunkteIcon, offeneAttributspunkteTT);
        offeneAttributspunkte = new Label();
        HBox attribute = new HBox(offeneAttributspunkteIcon, offeneAttributspunkte);
        attribute.getStyleClass().add("partystatusCharakterZeile");
        ImageView offeneSkillpunkteIcon = new ImageView(new Image("/icons/offeneFaehigkeitspunkte.png", 32.0, 0, true, false));
        Tooltip offeneSkillpunkteTT = new Tooltip("Offene Fähigkeitspunkte");
        Tooltip.install(offeneSkillpunkteIcon, offeneSkillpunkteTT);
        offeneSkillpunkte = new Label();
        HBox skill = new HBox(offeneSkillpunkteIcon, offeneSkillpunkte);
        skill.getStyleClass().add("partystatusCharakterZeile");
        VBox stufe = new VBox(erfahrung, attribute, skill);
        stufe.getStyleClass().add("partystatusCharakterSpalte");
        ImageView physischeAttackeIcon = new ImageView(new Image("/icons/physischeAttacke.png", 32.0, 0, true, false));
        Tooltip physischeAttackeTT = new Tooltip("Physische Attacke");
        Tooltip.install(physischeAttackeIcon, physischeAttackeTT);
        physischeAttacke = new Label();
        HBox pA = new HBox(physischeAttackeIcon, physischeAttacke);
        pA.getStyleClass().add("partystatusCharakterZeile");
        ImageView magischeAttackeIcon = new ImageView(new Image("/icons/magischeAttacke.png", 32.0, 0, true, false));
        Tooltip magischeAttackeTT = new Tooltip("Magische Attacke");
        Tooltip.install(magischeAttackeIcon, magischeAttackeTT);
        magischeAttacke = new Label();
        HBox mA = new HBox(magischeAttackeIcon, magischeAttacke);
        mA.getStyleClass().add("partystatusCharakterZeile");
        VBox angriff = new VBox(pA, mA);
        angriff.getStyleClass().add("partystatusCharakterSpalte");
        ImageView gesRegIcon = new ImageView(new Image("/icons/gesundheitsRegeneration.png", 32.0, 0, true, false));
        Tooltip gesRegTT = new Tooltip("Gesundheitsregeneration pro Runde");
        Tooltip.install(gesRegIcon, gesRegTT);
        gesundheitReg = new Label();
        HBox geR = new HBox(gesRegIcon, gesundheitReg);
        geR.getStyleClass().add("partystatusCharakterZeile");
        ImageView manaRIcon = new ImageView(new Image("/icons/manaRegeneration.png", 32.0, 0, true, false));
        Tooltip manaRTT = new Tooltip("Manaregeneration pro Runde");
        Tooltip.install(manaRIcon, manaRTT);
        manaReg = new Label();
        HBox maR = new HBox(manaRIcon, manaReg);
        maR.getStyleClass().add("partystatusCharakterZeile");
        VBox reg = new VBox(geR, maR);
        reg.getStyleClass().add("partystatusCharakterSpalte");
        ImageView genauIcon = new ImageView(new Image("/icons/genauigkeit.png", 32.0, 0, true, false));
        Tooltip genauTT = new Tooltip("Genauigkeit");
        Tooltip.install(genauIcon, genauTT);
        genauigkeit = new Label();
        HBox genau = new HBox(genauIcon, genauigkeit);
        genau.getStyleClass().add("partystatusCharakterZeile");
        ImageView bewegIcon = new ImageView(new Image("/icons/beweglichkeit.png", 32.0, 0, true, false));
        Tooltip bewegTT = new Tooltip("Beweglichkeit");
        Tooltip.install(bewegIcon, bewegTT);
        beweglichkeit = new Label();
        HBox beweg = new HBox(bewegIcon, beweglichkeit);
        beweg.getStyleClass().add("partystatusCharakterZeile");
        VBox sekundaerWerte = new VBox(genau, beweg);
        sekundaerWerte.getStyleClass().add("partystatusCharakterSpalte");
        ImageView pVtIcon = new ImageView(new Image("/icons/physischeVerteidigung.png", 32.0, 0, true, false));
        Tooltip pVtTT = new Tooltip("Physische Verteidigung");
        Tooltip.install(pVtIcon, pVtTT);
        pVerteidigung = new Label();
        HBox pVt = new HBox(pVtIcon, pVerteidigung);
        pVt.getStyleClass().add("partystatusCharakterZeile");
        ImageView mVtIcon = new ImageView(new Image("/icons/magischeVerteidigung.png", 32.0, 0, true, false));
        Tooltip mVTT = new Tooltip("Magische Verteidigung");
        Tooltip.install(mVtIcon, mVTT);
        mVerteidigung = new Label();
        HBox mV = new HBox(mVtIcon, mVerteidigung);
        mV.getStyleClass().add("partystatusCharakterZeile");
        ImageView resiIcon = new ImageView(new Image("/icons/resistenz.png", 32.0, 0, true, false));
        Tooltip resiTT = new Tooltip("Resistenz");
        Tooltip.install(resiIcon, resiTT);
        resistenz = new Label();
        HBox resi = new HBox(resiIcon, resistenz);
        resi.getStyleClass().add("partystatusCharakterZeile");
        VBox verteidigung = new VBox(pVt, mV, resi);
        verteidigung.getStyleClass().add("partystatusCharakterSpalte");
        waffe = new ImageView();
        waffeTT = new Tooltip();
        Tooltip.install(waffe, waffeTT);
        ruestung = new ImageView();
        ruestungTT = new Tooltip();
        Tooltip.install(ruestung, ruestungTT);
        VBox waffeRuestung = new VBox(waffe, ruestung);
        waffeRuestung.getStyleClass().add("partystatusCharakterSpalte");
        accessoireEins = new ImageView();
        accessoireEinsTT = new Tooltip();
        Tooltip.install(accessoireEins, accessoireEinsTT);
        accessoireZwei = new ImageView();
        accessoireZweiTT = new Tooltip();
        Tooltip.install(accessoireZwei, accessoireZweiTT);
        accessoireDrei = new ImageView();
        accessoireDreiTT = new Tooltip();
        Tooltip.install(accessoireDrei, accessoireDreiTT);
        VBox accessoires = new VBox(accessoireEins, accessoireZwei, accessoireDrei);
        accessoires.getStyleClass().add("partystatusCharakterSpalte");
        this.getChildren().addAll(charakterDarstellung, stufe, angriff, reg, sekundaerWerte, verteidigung, waffeRuestung, accessoires);
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
        } else {
            levelBox.setStroke(Color.BLACK);
            level.setText(spielerCharakter.getLevel() + "");
            levelBoxFarbeAktualisieren(spielerCharakter.getKlasse());

            nameDesCharakters.setText(spielerCharakter.getName() + " (" + spielerCharakter.getKlasse().getBezeichnung() + ")");
            healthBar.setProgress(spielerCharakter.getGesundheitsPunkte() / (double) spielerCharakter.getMaxGesundheitsPunkte());
            gesundheitsPunkteAlsText.setText(spielerCharakter.getGesundheitsPunkte() + "/" + spielerCharakter.getMaxGesundheitsPunkte() + " HP");
            healthBarColorAktualisieren(spielerCharakter.getGesundheitsPunkte(), spielerCharakter.getMaxGesundheitsPunkte());

            manaBar.setProgress(spielerCharakter.getManaPunkte() / (double) spielerCharakter.getMaxManaPunkte());
            manaPunkteAlsText.setText(spielerCharakter.getManaPunkte() + "/" + spielerCharakter.getMaxManaPunkte() + " MP");

            //TODO DUMMY BILD ERSETZEN!!!!
            charakterBild.setImage(new Image("/soeldner/soeldner1.png", 0.0, 90.0, true, true));

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
                //TODO DUMMY BILD ERSETZEN
                waffe.setImage(new Image("/icons/dummyAusruestungsgegenstand.png", 32.0, 0.0, true, false));
                waffeTT.setText(charakterWaffe.getClass().getSimpleName() + ": " + charakterWaffe.getName() + "\n" +
                        "Physischer Angriff: " + charakterWaffe.getAttacke() + "\n" +
                        "Magischer Angriff: " + charakterWaffe.getMagischeAttacke() + "\n" +
                        "Beweglichkeit: " + charakterWaffe.getBeweglichkeit() + "\n" +
                        "Genauigkeit: " + charakterWaffe.getGenauigkeit() + "\n" +
                        "Verkaufswert: " + charakterWaffe.getVerkaufswert() + "\n"
                );
                waffe.setVisible(true);
            } else {
                waffe.setVisible(false);
            }
            if (spielerCharakter.getRuestung() != null) {
                Ruestung charakterRuestung = spielerCharakter.getRuestung();
                //TODO DUMMY BILD ERSETZEN
                ruestung.setImage(new Image("/icons/dummyAusruestungsgegenstand.png", 32.0, 0.0, true, false));
                ruestungTT.setText(charakterRuestung.getClass().getSimpleName() + ": " + charakterRuestung.getName() + "\n" +
                        "Physische Verteidigung: " + charakterRuestung.getVerteidigung() + "\n" +
                        "Magische Verteidigung: " + charakterRuestung.getMagischeVerteidigung() + "\n" +
                        "Resistenz: " + charakterRuestung.getResistenz() + "\n" +
                        "Maximale Gesundheit: " + charakterRuestung.getMaxGesundheitsPunkte() + "\n" +
                        "Maximales Mana: " + charakterRuestung.getMaxManaPunkte() + "\n" +
                        "Verkaufswert: " + charakterRuestung.getVerkaufswert() + "\n");
                ruestung.setVisible(true);
            } else {
                ruestung.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(0) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(0);
                //TODO DUMMY BILD ERSETZEN
                accessoireEins.setImage(new Image("/icons/dummyAusruestungsgegenstand.png", 32.0, 0.0, true, false));
                accessoireEinsTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireEins.setVisible(true);
            } else {
                accessoireEins.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(1) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(1);
                //TODO DUMMY BILD ERSETZEN
                accessoireZwei.setImage(new Image("/icons/dummyAusruestungsgegenstand.png", 32.0, 0.0, true, false));
                accessoireZweiTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireZwei.setVisible(true);
            } else {
                accessoireZwei.setVisible(false);
            }
            if (spielerCharakter.getAccessoire(2) != null) {
                Accessoire charakterAccessoire = spielerCharakter.getAccessoire(2);
                //TODO DUMMY BILD ERSETZEN
                accessoireDrei.setImage(new Image("/icons/dummyAusruestungsgegenstand.png", 32.0, 0.0, true, false));
                accessoireDreiTT.setText(
                        charakterAccessoire.getName() + "\n" +
                                "Gesundheitsregeneration: " + charakterAccessoire.getGesundheitsRegeneration() + "\n" +
                                "Manaregeneration: " + charakterAccessoire.getManaRegeneration() + "\n" +
                                "Beweglichkeit: " + charakterAccessoire.getBeweglichkeit() + "\n" +
                                "Maximale Gesundheit: " + charakterAccessoire.getMaxGesundheitsPunkte() + "\n" +
                                "Maximales Mana: " + charakterAccessoire.getMaxManaPunkte() + "\n" +
                                "Verkaufswert: " + charakterAccessoire.getVerkaufswert() + "\n"
                );
                accessoireDrei.setVisible(true);
            } else {
                accessoireDrei.setVisible(false);
            }

            this.setMaxSize(1536.0, 200.0);
            this.setAlignment(Pos.CENTER);
            this.setVisible(true);
        }
    }

    private void levelBoxFarbeAktualisieren(Klasse klasse) {
        if (klasse instanceof HLR) {
            levelBoxColor = Color.LIMEGREEN;
        } else if (klasse instanceof MDD) {
            levelBoxColor = Color.CORNFLOWERBLUE;
        } else if (klasse instanceof PDD) {
            levelBoxColor = Color.CRIMSON;
        } else if (klasse instanceof TNK) {
            levelBoxColor = Color.GREY;
        }
        levelBox.setFill(levelBoxColor);
    }

    private void healthBarColorAktualisieren(int gesundheitsPunkte, int maxGesundheitsPunkte) {
        if ((gesundheitsPunkte
                / (double) maxGesundheitsPunkte) >= 0.5) {
            colorHealthBar = "-fx-accent: #00FF00;";
        } else if ((gesundheitsPunkte
                / (double) maxGesundheitsPunkte) >= 0.2) {
            colorHealthBar = "-fx-accent: #FF8C00;";
        } else {
            colorHealthBar = "-fx-accent: #FF0000;";
        }
        healthBar.setStyle(colorHealthBar);
    }
}
