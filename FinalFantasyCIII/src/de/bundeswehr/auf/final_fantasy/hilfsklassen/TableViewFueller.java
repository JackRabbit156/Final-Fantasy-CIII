package de.bundeswehr.auf.final_fantasy.hilfsklassen;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

/**
 * Die Hilfsklasse hilft beim erstellen verschiedener TableViews
 * Es wird die jeweilige Tabellenspalte hinzugefügt
 *
 * @author OF Kretschmer
 * @since 30.11.23
 */
public class TableViewFueller {

    /**
     * Fügt die Attacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void attakeFuellen(TableView<Waffe> tabelle) {
        TableColumn<Waffe, Number> attacke = new TableColumn<>("Attacke");
        attacke.setCellFactory(param -> new CenterCell<>());
        attacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAttacke()));
        tabelle.getColumns().add(attacke);
    }

    /**
     * Fügt die Beschreibung des Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void beschreibungVerbrauchsgegenstandFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> beschreibung = new TableColumn<>("Beschreibung");
        beschreibung.setCellFactory(param -> new LeftCenterCell<>());
        beschreibung.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().getBeschereibung()));
        tabelle.getColumns().add(beschreibung);
    }

    /**
     * Fügt die beweglichkeit des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void beweglichkeitAccFuellen(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, Number> beweglichkeitAcc = new TableColumn<>("Beweglichkeit");
        beweglichkeitAcc.setCellFactory(param -> new CenterCell<>());
        beweglichkeitAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        tabelle.getColumns().add(beweglichkeitAcc);
    }

    /**
     * Fügt die Beweglichkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void beweglichkeitWaffeFuellen(TableView<Waffe> tabelle) {
        TableColumn<Waffe, Number> beweglichkeitWaffe = new TableColumn<>("Beweglichkeit");
        beweglichkeitWaffe.setCellFactory(param -> new CenterCell<>());
        beweglichkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getBeweglichkeit()));
        tabelle.getColumns().add(beweglichkeitWaffe);
    }

    /**
     * Fügt die Genauigkeit der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void genauigkeitWaffeFuellen(TableView<Waffe> tabelle) {
        TableColumn<Waffe, Number> genauigkeitWaffe = new TableColumn<>("Genauigkeit");
        genauigkeitWaffe.setCellFactory(param -> new CenterCell<>());
        genauigkeitWaffe.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGenauigkeit()));
        tabelle.getColumns().add(genauigkeitWaffe);
    }

    /**
     * Fügt die GesundheitsRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void gesundheitsRegenerationAccFuellen(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, Number> gesundheitsRegenerationAcc = new TableColumn<>("GesundheitsRegeneration");
        gesundheitsRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        gesundheitsRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getGesundheitsRegeneration()));
        tabelle.getColumns().add(gesundheitsRegenerationAcc);
    }

    /**
     * Fügt das Icon eines Ausrüstungsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static <G extends Gegenstand> void iconFuellen(TableView<G> tabelle) {
        TableColumn<G, String> icon = new TableColumn<>("Icon");
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
                }
                setAlignment(Pos.CENTER);
            }

        });
        tabelle.getColumns().add(icon);
    }

    /**
     * Fügt das Icon des Materials der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void iconMaterialFuellen(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Material, IntegerProperty>, String> icon = new TableColumn<>("Icon");
        icon.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().getIcon()));
        icon.setCellFactory(param -> new TableCell<Map.Entry<Material, IntegerProperty>, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (item != null) {
                    setGraphic(new ImageView(new Image(item)));
                }
                else {
                    setGraphic(new ImageView((new Image("icons/gold.png"))));
                }
                setAlignment(Pos.CENTER);
            }
        });
        tabelle.getColumns().add(icon);
    }

    /**
     * Fügt das Icon eines Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void iconVerbrauchsgegenstandFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> icon = new TableColumn<>("Icon");
        icon.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().getIcon()));
        icon.setCellFactory(param -> new TableCell<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (item != null) {
                    setGraphic(new ImageView(new Image(item)));
                }
                else {
                    setGraphic(new ImageView((new Image("icons/gold.png"))));
                }
                setAlignment(Pos.CENTER);
            }
        });
        tabelle.getColumns().add(icon);
    }

    /**
     * Fügt den Kaufpreis des Ausrüstungsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static <G extends Gegenstand> void kaufpreisFuellen(TableView<G> tabelle) {
        TableColumn<G, Number> kaufpreis = new TableColumn<>("Kaufpreis");
        kaufpreis.setCellFactory(param -> new CenterCell<>());
        kaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getKaufwert()));
        tabelle.getColumns().add(kaufpreis);
    }

    /**
     * Fügt den Kaufpreis des Materials der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void kaufpreisMaterialFuellen(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Material, IntegerProperty>, String> kaufPreis = new TableColumn<>("Kaufpreis");
        kaufPreis.setCellFactory(param -> new CenterCell<>());
        kaufPreis.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey().getKaufwert())));
        tabelle.getColumns().add(kaufPreis);
    }

    /**
     * Fügt den Kaufpreis des Verbrauchsgegenstands der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void kaufpreisVerbrauchsgegenstandFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> kaufPreis = new TableColumn<>("Kaufpreis");
        kaufPreis.setCellFactory(param -> new CenterCell<>());
        kaufPreis.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey().getKaufwert())));
        tabelle.getColumns().add(kaufPreis);
    }

    /**
     * Fügt die LvlAnforderung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static <A extends AusruestungsGegenstand> void lvlAnforderungFuellen(TableView<A> tabelle) {
        TableColumn<A, Number> lvlAnforderung = new TableColumn<>("LvlAnforderung");
        lvlAnforderung.setCellFactory(param -> new CenterCell<>());
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getLevelAnforderung()));
        tabelle.getColumns().add(lvlAnforderung);
    }

    /**
     * Fügt die magischeAttacke der Waffe der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void magischeAttakeFuellen(TableView<Waffe> tabelle) {
        TableColumn<Waffe, Number> magischeAttacke = new TableColumn<>("MagischeAttacke");
        magischeAttacke.setCellFactory(param -> new CenterCell<>());
        magischeAttacke.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeAttacke()));
        tabelle.getColumns().add(magischeAttacke);
    }

    /**
     * Fügt die magische Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void magischeVerteidigungFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, Number> magischeVerteidigung = new TableColumn<>("MagischeVerteidigung");
        magischeVerteidigung.setCellFactory(param -> new CenterCell<>());
        magischeVerteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMagischeVerteidigung()));
        tabelle.getColumns().add(magischeVerteidigung);
    }

    /**
     * Fügt die ManaRegeneration des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void manaRegenerationAccFuellen(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, Number> manaRegenerationAcc = new TableColumn<>("ManaRegeneration");
        manaRegenerationAcc.setCellFactory(param -> new CenterCell<>());
        manaRegenerationAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getManaRegeneration()));
        tabelle.getColumns().add(manaRegenerationAcc);
    }

    /**
     * Fügt die maxGesundheitspunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxGesundheitsPunkteAaccFuellen(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, Number> maxGesPAcc = new TableColumn<>("MaxGesundheitspunkte");
        maxGesPAcc.setCellFactory(param -> new CenterCell<>());
        maxGesPAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        tabelle.getColumns().add(maxGesPAcc);
    }

    /**
     * Fügt die maxGesundheitspunkte der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxGesundheitsPunkteRuestungFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, Number> maxGesPRuestung = new TableColumn<>("MaxGesundheitspunkte");
        maxGesPRuestung.setCellFactory(param -> new CenterCell<>());
        maxGesPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxGesundheitsPunkte()));
        tabelle.getColumns().add(maxGesPRuestung);
    }

    /**
     * Fügt die maxManapunkte des Accessoire der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxManaPunkteAccFuellen(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, Number> maxManaPunkteAcc = new TableColumn<>("MaxManapunkte");
        maxManaPunkteAcc.setCellFactory(param -> new CenterCell<>());
        maxManaPunkteAcc.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        tabelle.getColumns().add(maxManaPunkteAcc);
    }

    /**
     * Fügt die maxManaPunkte der Rüstung  der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void maxManaPunkteRuestungFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, Number> maxManaPRuestung = new TableColumn<>("MaxManapunkte");
        maxManaPRuestung.setCellFactory(param -> new CenterCell<>());
        maxManaPRuestung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getMaxManaPunkte()));
        tabelle.getColumns().add(maxManaPRuestung);
    }

    /**
     * T
     * Fügt die Beschreibung des Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void mengeMaterialFuellen(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Material, IntegerProperty>, String> menge = new TableColumn<>("Menge");
        menge.setCellFactory(param -> new CenterCell<>());
        menge.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().get())));
        tabelle.getColumns().add(menge);
    }

    /**
     * Fügt die Beschreibung des Verbrauchsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void mengeVerbrauchsgegenstandFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> menge = new TableColumn<>("Menge");
        menge.setCellFactory(param -> new CenterCell<>());
        menge.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().get())));
        tabelle.getColumns().add(menge);
    }

    /**
     * Fügt den Namen des Ausrüstungsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static <G extends Gegenstand> void nameFuellen(TableView<G> tabelle) {
        TableColumn<G, String> name = new TableColumn<>("Name");
        name.setCellFactory(param -> new LeftCenterCell<>());
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        tabelle.getColumns().add(name);
    }

    /**
     * TestMethode
     * Fügt den Namen des Materials der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void nameMaterialFuellen(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Material, IntegerProperty>, String> name = new TableColumn<>("Name");
        name.setCellFactory(param -> new LeftCenterCell<>());
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().getName()));
        tabelle.getColumns().add(name);
    }

    /**
     * TestMethode
     * Fügt den Namen des Verbrauchsgegenstands  der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void nameVGFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> name = new TableColumn<>("Name");
        name.setCellFactory(param -> new LeftCenterCell<>());
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().getName()));
        tabelle.getColumns().add(name);
    }

    /**
     * Fügt die Resristenz der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void resistenzFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, Number> resistenz = new TableColumn<>("Resistenz");
        resistenz.setCellFactory(param -> new CenterCell<>());
        resistenz.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getResistenz()));
        tabelle.getColumns().add(resistenz);
    }

    /**
     * Fügt den Rüstungstyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void ruestungsTypFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, String> ruestungstyp = new TableColumn<>("Rüstungstyp");
        ruestungstyp.setCellFactory(param -> new LeftCenterCell<>());
        ruestungstyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        tabelle.getColumns().add(ruestungstyp);
    }

    /**
     * Fügt den Verkaufspreis eines Ausrüstungsgegenstandes der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static <G extends Gegenstand> void verkaufpreisFuellen(TableView<G> tabelle) {
        TableColumn<G, Number> verkaufpreis = new TableColumn<>("Verkaufpreis");
        verkaufpreis.setCellFactory(param -> new CenterCell<>());
        verkaufpreis.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerkaufswert()));
        tabelle.getColumns().add(verkaufpreis);
    }

    /**
     * Fügt den Verkaufspreis des Materials der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void verkaufpreisMaterialFuellen(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Material, IntegerProperty>, String> verkaufPreis = new TableColumn<>("Verkaufspreis");
        verkaufPreis.setCellFactory(param -> new CenterCell<>());
        verkaufPreis.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey().getVerkaufswert())));
        tabelle.getColumns().add(verkaufPreis);
    }

    /**
     * Fügt den Verkaufspreis des Verbrauchsgegenstands der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 05.12.23
     */
    public static void verkaufpreisVerbrauchsgegenstandFuellen(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableColumn<Map.Entry<Verbrauchsgegenstand, IntegerProperty>, String> verkaufPreis = new TableColumn<>("Verkaufspreis");
        verkaufPreis.setCellFactory(param -> new CenterCell<>());
        verkaufPreis.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey().getKaufwert())));
        tabelle.getColumns().add(verkaufPreis);
    }

    /**
     * Fügt die Verteidigung der Rüstung der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verteidigungFuellen(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, Number> verteidigung = new TableColumn<>("Verteidigung");
        verteidigung.setCellFactory(param -> new CenterCell<>());
        verteidigung.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getVerteidigung()));
        tabelle.getColumns().add(verteidigung);
    }

    /**
     * Fügt den Waffentyp der übergebenen TableView hinzu
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenTypFuellen(TableView<Waffe> tabelle) {
        TableColumn<Waffe, String> waffentyp = new TableColumn<>("Waffentyp");
        waffentyp.setCellFactory(param -> new LeftCenterCell<>());
        waffentyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        tabelle.getColumns().add(waffentyp);
    }

}
