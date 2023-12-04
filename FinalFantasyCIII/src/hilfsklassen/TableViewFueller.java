package hilfsklassen;

import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Die Hilfsklasse hilft beim erstellen verschiedener TableViews
 * Es wird die jeweilige Tabellenspalte hinzugefügt
 *
 * @author OF Kretschmer
 * @since 30.11.23
 */
public class TableViewFueller {


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
    public static void nameFuellen(TableView tabelle) {
        TableColumn<Gegenstand, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tabelle.getColumns().add(name);
    }

    /**
     * Fügt die LvlAnforderung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void lvlAnforderungFuellen(TableView tabelle) {
        TableColumn<Ausruestungsgegenstand, Number> lvlAnforderung = new TableColumn<>("LvlAnforderung");
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()));
        tabelle.getColumns().add(lvlAnforderung);
    }

    /**
     * Fügt den Waffentyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenTypFuellen(TableView tabelle) {
        TableColumn<Waffe, String> waffentyp = new TableColumn<>("Waffentyp");
        waffentyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        tabelle.getColumns().add(waffentyp);
    }

    /**
     * Fügt den Rüstungstyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void ruestungsTypFuellen(TableView tabelle) {
        TableColumn<Ruestung, String> ruestungstyp = new TableColumn<>("Rüstungstyp");
        ruestungstyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        tabelle.getColumns().add(ruestungstyp);
    }

    /**
     * Fügt die Attacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void attakeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> attacke = new TableColumn<>("Attacke");
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()));
        tabelle.getColumns().add(attacke);
    }

    /**
     * Fügt die magischeAttacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void magischeAttakeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MagischeAttacke");
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()));
        tabelle.getColumns().add(magischeAttacke);
    }

    /**
     * Fügt die Genauigkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void genauigkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("Genauigkeit");
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()));
        tabelle.getColumns().add(genauigkeitWaffe);
    }

    /**
     * Fügt die Beweglichkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void beweglichkeitWaffeFuellen(TableView tabelle) {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("Beweglichkeit");
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        tabelle.getColumns().add(beweglichkeitWaffe);
    }

    /**
     * Fügt den Kaufpreis der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void kaufpreisFuellen(TableView tabelle) {
        TableColumn<Gegenstand, Number> kaufpreis = new TableColumn<>("Kaufpreis");
        kaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getKaufwert()));
        tabelle.getColumns().add(kaufpreis);
    }

    /**
     * Fügt den Verkaufspreis der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verkaufpreisFuellen(TableView tabelle) {
        TableColumn<Gegenstand, Number> verkaufpreis = new TableColumn<>("Verkaufpreis");
        verkaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerkaufswert()));
        tabelle.getColumns().add(verkaufpreis);
    }

    /**
     * Fügt die Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("Verteidigung");
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()));
        tabelle.getColumns().add(verteidigung);
    }

    /**
     * Fügt die magische Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void magischeVerteidigungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MagischeVerteidigung");
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()));
        tabelle.getColumns().add(magischeVerteidigung);
    }

    /**
     * Fügt die Resristenz der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void resistenzFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("Resistenz");
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()));
        tabelle.getColumns().add(resistenz);
    }

    /**
     * Fügt die maxGesundheitspunkte der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxGesundheitsPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("MaxGesundheitspunkte");
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        tabelle.getColumns().add(maxGesPRuestung);
    }

    /**
     * Fügt die maxManaPunkte der Rüstung  der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxManaPunkteRuestungFuellen(TableView tabelle) {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("MaxManapunkte");
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        tabelle.getColumns().add(maxManaPRuestung);
    }

    /**
     * Fügt die maxGesundheitspunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxGesundheitsPunkteAaccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("MaxGesundheitspunkte");
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        tabelle.getColumns().add(maxGesPAcc);
    }

    /**
     * Fügt die maxManapunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxManaPunkteAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("MaxManapunkte");
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        tabelle.getColumns().add(maxManaPunkteAcc);
    }

    /**
     * Fügt die beweglichkeit des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void beweglichkeitAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("Beweglichkeit");
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        tabelle.getColumns().add(beweglichkeitAcc);
    }

    /**
     * Fügt die GesundheitsRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void gesundheitsRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GesundheitsRegeneration");
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()));
        tabelle.getColumns().add(gesundheitsRegenerationAcc);
    }

    /**
     * Fügt die ManaRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void manaRegenerationAccFuellen(TableView tabelle) {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("ManaRegeneration");
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()));
        tabelle.getColumns().add(manaRegenerationAcc);
    }

    /**
     * Fügt die Beschreibung des Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void beschreibungFuellen(TableView tabelle) {
        TableColumn<Verbrauchsgegenstand, String> beschreibung = new TableColumn<>("Beschreibung");
        beschreibung.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBeschereibung()));
        tabelle.getColumns().add(beschreibung);
    }
}