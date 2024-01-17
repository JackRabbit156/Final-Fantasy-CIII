package de.bundeswehr.auf.final_fantasy.menu.schmiede.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.CenterCell;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.LeftCenterCell;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SchmiedeTableFueller {

    private static final Integer wertSteigerungWaffe = 1;
    private static final Integer wertSteigerungRuestung = 1;
    private static final Integer wertSteigerungAccessoire = 1;

    /**
     * Fügt das Icon der übergebenen TableView hinzu
     *
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static <G extends Gegenstand> TableColumn<G, String> iconFuellen() {
        TableColumn<G, String> icon = new TableColumn<>("");
        icon.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIcon()));
        icon.setCellFactory(param -> new TableCell<G, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (item != null) {
                    setGraphic(new ImageView(new Image(item)));
                } else {
                    setGraphic(new ImageView((new Image("icons/gold.png"))));
                }
                setAlignment(Pos.CENTER);
            }
        });
        return icon;
    }
    
    /**
     * Fügt den Namen der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static <G extends Gegenstand> TableColumn<G, String> nameFuellen() {
        TableColumn<G, String> name = new TableColumn<>("Name");
        name.setCellFactory(param -> new LeftCenterCell<>());
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        return name;
    }

    /**
     * Fügt die LvlAnforderung der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static <A extends AusruestungsGegenstand> TableColumn<A, Number> lvlAnforderungFuellen() {
        TableColumn<A, Number> lvlAnforderung = new TableColumn<>("Level");
        lvlAnforderung.setCellFactory(param -> new CenterCell<>());
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()));
        return lvlAnforderung;
    }
    /**
     * Fügt den Waffentyp der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, String> waffenTypFuellen() {
        TableColumn<Waffe, String> waffentyp = new TableColumn<>("Waffentyp");
        waffentyp.setCellFactory(param -> new LeftCenterCell<>());
        waffentyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        return waffentyp;
    }
    /**
     * Fügt den Rüstungstyp der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, String> ruestungsTypFuellen() {
        TableColumn<Ruestung, String> ruestungstyp = new TableColumn<>("Rüstungstyp");
        ruestungstyp.setCellFactory(param -> new LeftCenterCell<>());
        ruestungstyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        return ruestungstyp;
    }
    /**
     * Fügt die Attacke der Waffe der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> attakeFuellen() {
        TableColumn<Waffe, Number> attacke = new TableColumn<>("A");
        attacke.setCellFactory(param -> new CenterCell<>());
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()));
        return attacke;
    }
    /**
     * Fügt die magischeAttacke der Waffe der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> magischeAttakeFuellen() {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MA");
        magischeAttacke.setCellFactory(param -> new CenterCell<>());
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()));
        return magischeAttacke;
    }
    /**
     * Fügt die Genauigkeit der Waffe der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> genauigkeitWaffeFuellen() {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("G");
        genauigkeitWaffe.setCellFactory(param -> new CenterCell<>());
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()));
        return genauigkeitWaffe;
    }
    /**
     * Fügt die Beweglichkeit der Waffe der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> beweglichkeitWaffeFuellen() {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("B");
        beweglichkeitWaffe.setCellFactory(param -> new CenterCell<>());
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitWaffe;
    }
    /**
     * Fügt den Verkaufspreis der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static <G extends Gegenstand> TableColumn<G, Number> verkaufpreisFuellen() {
        TableColumn<G, Number> verkaufpreis = new TableColumn<>("Verkaufpreis");
        verkaufpreis.setCellFactory(param -> new CenterCell<>());
        verkaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerkaufswert()));
        return verkaufpreis;
    }
    /**
     * Fügt die Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> verteidigungFuellen() {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("V");
        verteidigung.setCellFactory(param -> new CenterCell<>());
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()));
        return verteidigung;
    }
    /**
     * Fügt die magische Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> magischeVerteidigungFuellen() {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MV");
        magischeVerteidigung.setCellFactory(param -> new CenterCell<>());
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()));
        return magischeVerteidigung;
    }
    /**
     * Fügt die Resistenz der Rüstung der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> resistenzFuellen() {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("R");
        resistenz.setCellFactory(param -> new CenterCell<>());
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()));
        return resistenz;
    }
    /**
     * Fügt die maxGesundheitspunkte der Rüstung der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> maxGesundheitsPunkteRuestungFuellen() {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("maxGP");
        maxGesPRuestung.setCellFactory(param -> new CenterCell<>());
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        return maxGesPRuestung;
    }

    /**
     * Fügt die maxManaPunkte der Rüstung  der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> maxManaPunkteRuestungFuellen() {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("maxMP");
        maxManaPRuestung.setCellFactory(param -> new CenterCell<>());
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        return maxManaPRuestung;
    }
    /**
     * Fügt die maxGesundheitspunkte des Accessoire der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> maxGesundheitsPunkteAaccFuellen() {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("maxGP");
        maxGesPAcc.setCellFactory(param -> new CenterCell<>());
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        return maxGesPAcc;
    }
    /**
     * Fügt die maxManapunkte des Accessoire der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> maxManaPunkteAccFuellen() {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("maxMP");
        maxManaPunkteAcc.setCellFactory(param -> new CenterCell<>());
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        return maxManaPunkteAcc;
    }
    /**
     * Fügt die beweglichkeit des Accessoire der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> beweglichkeitAccFuellen() {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("B");
        beweglichkeitAcc.setCellFactory(param -> new CenterCell<>());
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitAcc;
    }
    /**
     * Fügt die GesundheitsRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> gesundheitsRegenerationAccFuellen() {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GR");
        gesundheitsRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()));
        return gesundheitsRegenerationAcc;
    }
    /**
     * Fügt die ManaRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> manaRegenerationAccFuellen() {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("MR");
        manaRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()));
        return manaRegenerationAcc;
    }
    /**
     * Fügt die Attacke der Waffe für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> nAttackeFuellen(){
        TableColumn<Waffe, Number> attacke = new TableColumn<>("A");
        attacke.setCellFactory(param -> new CenterCell<>());
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()+wertSteigerungWaffe));
        return attacke;
    }
    /**
     * Fügt die LvlAnforderung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static <A extends AusruestungsGegenstand> TableColumn<A, Number> nLvlAnforderungFuellen() {
        TableColumn<A, Number> lvlAnforderung = new TableColumn<>("Level");
        lvlAnforderung.setCellFactory(param -> new CenterCell<>());
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()+wertSteigerungWaffe));
        return lvlAnforderung;
    }
    /**
     * Fügt die magischeAttacke der Waffe für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> nMagischeAttakeFuellen() {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MA");
        magischeAttacke.setCellFactory(param -> new CenterCell<>());
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()+wertSteigerungWaffe));
        return magischeAttacke;
    }
    /**
     * Fügt die Genauigkeit der Waffe für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> nGenauigkeitWaffeFuellen() {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("G");
        genauigkeitWaffe.setCellFactory(param -> new CenterCell<>());
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()));
        return genauigkeitWaffe;
    }
    /**
     * Fügt die Beweglichkeit der Waffe für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Waffe, Number> nBeweglichkeitWaffeFuellen() {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("B");
        beweglichkeitWaffe.setCellFactory(param -> new CenterCell<>());
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitWaffe;
    }
    /**
     * Fügt die Verteidigung der Rüstung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> nVerteidigungFuellen() {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("V");
        verteidigung.setCellFactory(param -> new CenterCell<>());
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()+wertSteigerungRuestung));
        return verteidigung;
    }
    /**
     * Fügt die magische Verteidigung der Rüstung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> nMagischeVerteidigungFuellen() {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MV");
        magischeVerteidigung.setCellFactory(param -> new CenterCell<>());
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()+wertSteigerungRuestung));
        return magischeVerteidigung;
    }
    /**
     * Fügt die Resistenz der Rüstung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> nResistenzFuellen() {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("R");
        resistenz.setCellFactory(param -> new CenterCell<>());
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()));
        return resistenz;
    }
    /**
     * Fügt die maxGesundheitspunkte der Rüstung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> nMaxGesundheitsPunkteRuestungFuellen() {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("maxGP");
        maxGesPRuestung.setCellFactory(param -> new CenterCell<>());
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        return maxGesPRuestung;
    }
    /**
     * Fügt die maxManaPunkte der Rüstung für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Ruestung, Number> nMaxManaPunkteRuestungFuellen() {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("maxMP");
        maxManaPRuestung.setCellFactory(param -> new CenterCell<>());
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        return maxManaPRuestung;
    }
    /**
     * Fügt die maxGesundheitspunkte des Accessoire für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> nMaxGesundheitsPunkteAaccFuellen() {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("maxGP");
        maxGesPAcc.setCellFactory(param -> new CenterCell<>());
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()+wertSteigerungAccessoire));
        return maxGesPAcc;
    }
    /**
     * Fügt die maxManapunkte des Accessoire für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> nMaxManaPunkteAccFuellen() {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("maxMP");
        maxManaPunkteAcc.setCellFactory(param -> new CenterCell<>());
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()+wertSteigerungAccessoire));
        return maxManaPunkteAcc;
    }
    /**
     * Fügt die beweglichkeit des Accessoire für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> nBeweglichkeitAccFuellen() {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("B");
        beweglichkeitAcc.setCellFactory(param -> new CenterCell<>());
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitAcc;
    }
    /**
     * Fügt die GesundheitsRegeneration des Accessoire für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> nGesundheitsRegenerationAccFuellen() {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GR");
        gesundheitsRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()));
        return gesundheitsRegenerationAcc;
    }
    /**
     * Fügt die ManaRegeneration des Accessoire für das nächste Lvl der übergebenen TableView hinzu
     *
     * 
     * @return TableColumn
     * @author OF Stetter
     * @since 05.12.23
     */
    public static TableColumn<Accessoire, Number> nManaRegenerationAccFuellen() {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("MR");
        manaRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()));
        return manaRegenerationAcc;
    }

}
