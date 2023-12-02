package partystatus;

import charakter.model.SpielerCharakter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PartyStatusCharakterView extends HBox {
    private SpielerCharakter charakter;
    private Label name;
    private Label klasse;
    private Label spezialisierung;
    private Label level;
    private Label offeneAttributspunkte;
    private Label offeneSkillpunkte;
    private Label gesundheit;
    private Label mana;
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
    private Tooltip accesoireZweiTT;
    private Tooltip accesoireDreiTT;


    public PartyStatusCharakterView(SpielerCharakter charakter) {
        this.charakter = charakter;
        if(charakter == null){
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
        name = new Label();
        klasse = new Label();
        spezialisierung = new Label();
        VBox klassen = new VBox(klasse, spezialisierung);
        level = new Label();
        offeneAttributspunkte = new Label();
        offeneSkillpunkte = new Label();
        VBox stufe = new VBox(level, offeneAttributspunkte,offeneSkillpunkte);
        gesundheit = new Label();
        mana = new Label();
        VBox hpMp = new VBox(gesundheit, mana);
        physischeAttacke = new Label();
        magischeAttacke = new Label();
        VBox angriff = new VBox(physischeAttacke, magischeAttacke);
        genauigkeit = new Label();
        beweglichkeit = new Label();
        VBox sekundaerWerte = new VBox(genauigkeit, beweglichkeit);
        pVerteidigung = new Label();
        mVerteidigung = new Label();
        resistenz = new Label();
        VBox verteidigung = new VBox(pVerteidigung, mVerteidigung, resistenz);
        waffe = new ImageView();
        waffeTT = new Tooltip();
        Tooltip.install(waffe, waffeTT);
        ruestung = new ImageView();
        ruestungTT = new Tooltip();
        Tooltip.install(ruestung, ruestungTT);
        VBox waffeRuestung = new VBox(waffe, ruestung);
        accessoireEins = new ImageView();
        accessoireEinsTT = new Tooltip();
        Tooltip.install(accessoireEins, accessoireEinsTT);
        accessoireZwei = new ImageView();
        accesoireZweiTT = new Tooltip();
        Tooltip.install(accessoireZwei, accesoireZweiTT);
        accessoireDrei = new ImageView();
        accesoireDreiTT = new Tooltip();
        Tooltip.install(accessoireDrei, accesoireDreiTT);
        VBox accessoires = new VBox(accessoireEins, accessoireZwei, accessoireDrei);
        this.getChildren().addAll(name, klassen, stufe, hpMp, angriff, sekundaerWerte, verteidigung, waffeRuestung, accessoires);
        this.ansichtAktualisieren(this.charakter);
        this.setMaxSize(1536.0, 200.0);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Aktualisiert alle Anzeigekomponenten der Ansicht anhand des neuen/aktualisierten Charakters
     * @param spielerCharakter neuer/aktualisierter Charakter
     * @author Nick
     * @since 02.12.2023
     */
    public void ansichtAktualisieren(SpielerCharakter spielerCharakter){
        this.charakter = spielerCharakter;
        if(spielerCharakter == null){
            this.setVisible(false);
        } else {
            name.setText(spielerCharakter.getName());
            klasse.setText(spielerCharakter.getKlasse().getBezeichnung());
            spezialisierung.setText(spielerCharakter.);
            this.setVisible(true);
        }
    }
}
