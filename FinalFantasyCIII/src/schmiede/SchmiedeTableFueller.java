package schmiede;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.material.Eisenerz;
import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Map;

public class SchmiedeTableFueller {

    private static final Integer wertSteigerungWaffe = 1;
    private static final Integer wertSteigerungRuestung = 1;
    private static final Integer wertSteigerungAccessoire = 1;

    /**
     * Fügt das Icon der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void iconFuellen(TableView tabelle) {
        //ToDo Icon einfügen
    }

    /**
     * Fügt den Namen der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn nameFuellen(TableView tabelle) {
        TableColumn<Gegenstand, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        return name;
    }

    /**
     * Fügt die LvlAnforderung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn lvlAnforderungFuellen(TableView tabelle) {
        TableColumn<Ausruestungsgegenstand, Number> lvlAnforderung = new TableColumn<>("Level");
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()));
        return lvlAnforderung;
    }

    /**
     * Fügt den Waffentyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn waffenTypFuellen(TableView tabelle) {
        TableColumn<Waffe, String> waffentyp = new TableColumn<>("Waffentyp");
        waffentyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        return waffentyp;
    }

    /**
     * Fügt den Rüstungstyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn ruestungsTypFuellen(TableView tabelle) {
        TableColumn<Ruestung, String> ruestungstyp = new TableColumn<>("Rüstungstyp");
        ruestungstyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        return ruestungstyp;
    }

    /**
     * Fügt die Attacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn attakeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> attacke = new TableColumn<>("Attacke");
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()));
        return attacke;
    }

    /**
     * Fügt die magischeAttacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn magischeAttakeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MagischeAttacke");
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()));
        return magischeAttacke;
    }

    /**
     * Fügt die Genauigkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn genauigkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("Genauigkeit");
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()));
        return genauigkeitWaffe;
    }

    /**
     * Fügt die Beweglichkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn beweglichkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("Beweglichkeit");
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitWaffe;
    }

    /**
     * Fügt den Verkaufspreis der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn verkaufpreisFuellen(TableView tabelle) {
        TableColumn<Gegenstand, Number> verkaufpreis = new TableColumn<>("Verkaufpreis");
        verkaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerkaufswert()));
        return verkaufpreis;
    }

    /**
     * Fügt die Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn verteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("Verteidigung");
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()));
        return verteidigung;
    }

    /**
     * Fügt die magische Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn magischeVerteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MagischeVerteidigung");
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()));
        return magischeVerteidigung;
    }

    /**
     * Fügt die Resristenz der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn resistenzFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("Resistenz");
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()));
        return resistenz;
    }

    /**
     * Fügt die maxGesundheitspunkte der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn maxGesundheitsPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("MaxGesundheit");
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        return maxGesPRuestung;
    }

    /**
     * Fügt die maxManaPunkte der Rüstung  der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn maxManaPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("MaxManapunkte");
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        return maxManaPRuestung;
    }

    /**
     * Fügt die maxGesundheitspunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn maxGesundheitsPunkteAaccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("MaxGesundheit");
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        return maxGesPAcc;
    }

    /**
     * Fügt die maxManapunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn maxManaPunkteAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("MaxManapunkte");
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        return maxManaPunkteAcc;
    }

    /**
     * Fügt die beweglichkeit des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn beweglichkeitAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("Beweglichkeit");
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        return beweglichkeitAcc;
    }

    /**
     * Fügt die GesundheitsRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn gesundheitsRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GesundheitsRegeneration");
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()));
        return gesundheitsRegenerationAcc;
    }

    /**
     * Fügt die ManaRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn manaRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("ManaRegeneration");
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()));
        return manaRegenerationAcc;
    }

    /**
     * Fügt die Beschreibung des Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static TableColumn beschreibungFuellen(TableView tabelle) {
        TableColumn<Verbrauchsgegenstand, String> beschreibung = new TableColumn<>("Beschreibung");
        beschreibung.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBeschereibung()));
        return beschreibung;
    }

    public static TableColumn nAttackeFuellen(TableView tabelle){
        TableColumn<Waffe, Number> attacke = new TableColumn<>("Attacke Neu");
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()+wertSteigerungWaffe));
        return attacke;
    }

    public static TableColumn nLvlAnforderungFuellen(TableView tabelle) {
        TableColumn<Ausruestungsgegenstand, Number> lvlAnforderung = new TableColumn<>("Level");
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()+wertSteigerungWaffe));
        return lvlAnforderung;
    }

    public static TableColumn nMagischeAttakeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MagischeAttacke");
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()+wertSteigerungWaffe));
        return magischeAttacke;
    }

    public static TableColumn nGenauigkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("Genauigkeit");
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()+ wertSteigerungWaffe));
        return genauigkeitWaffe;
    }

    public static TableColumn nBeweglichkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("Beweglichkeit");
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()+wertSteigerungWaffe));
        return beweglichkeitWaffe;
    }
    public static TableColumn nVerteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("Verteidigung");
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()+wertSteigerungRuestung));
        return verteidigung;
    }
    public static TableColumn nMagischeVerteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MagischeVerteidigung");
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()+wertSteigerungRuestung));
        return magischeVerteidigung;
    }
    public static TableColumn nResistenzFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("Resistenz");
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()+wertSteigerungRuestung));
        return resistenz;
    }
    public static TableColumn nMaxGesundheitsPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("MaxGesundheit");
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()+wertSteigerungRuestung));
        return maxGesPRuestung;
    }
    public static TableColumn nMaxManaPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("MaxManapunkte");
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()+wertSteigerungRuestung));
        return maxManaPRuestung;
    }
    public static TableColumn nMaxGesundheitsPunkteAaccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("MaxGesundheit");
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()+wertSteigerungAccessoire));
        return maxGesPAcc;
    }
    public static TableColumn nMaxManaPunkteAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("MaxManapunkte");
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()+wertSteigerungAccessoire));
        return maxManaPunkteAcc;
    }
    public static TableColumn nBeweglichkeitAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("Beweglichkeit");
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()+wertSteigerungAccessoire));
        return beweglichkeitAcc;
    }
    public static TableColumn nGesundheitsRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GesundheitsRegeneration");
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()+wertSteigerungAccessoire));
        return gesundheitsRegenerationAcc;
    }
    public static TableColumn nManaRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("ManaRegeneration");
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()+wertSteigerungAccessoire));
        return manaRegenerationAcc;
    }
}
