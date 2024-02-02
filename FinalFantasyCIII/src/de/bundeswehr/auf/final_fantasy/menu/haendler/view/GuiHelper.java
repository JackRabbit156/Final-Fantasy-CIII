package de.bundeswehr.auf.final_fantasy.menu.haendler.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.view.TableViewFueller;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.Map;

public final class GuiHelper {

    private GuiHelper() {
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Accessoire
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void accessoireKaufenTabelle(TableView<Accessoire> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableViewFueller.maxManaPunkteAccFuellen(tabelle);
        TableViewFueller.beweglichkeitAccFuellen(tabelle);
        TableViewFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableViewFueller.manaRegenerationAccFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Accessoire
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void accessoireVerkaufenTabelle(TableView<Accessoire> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableViewFueller.maxManaPunkteAccFuellen(tabelle);
        TableViewFueller.beweglichkeitAccFuellen(tabelle);
        TableViewFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableViewFueller.manaRegenerationAccFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    public static void erfolgreich(Label hinweis, String text) {
        hinweis.setText(text);
        hinweis.getStyleClass().clear();
        hinweis.getStyleClass().add("haendlerErfolgreich");
    }

    public static void gescheitert(Label hinweis, String text) {
        hinweis.setText(text);
        hinweis.getStyleClass().clear();
        hinweis.getStyleClass().add("haendlerGescheitert");
    }

    /**
     * Erstellt die Spalten der Tabelle zum kaufen von Materialien
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void materialKaufenTabelle(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableViewFueller.iconMaterialFuellen(tabelle);
        TableViewFueller.nameMaterialFuellen(tabelle);
        TableViewFueller.kaufpreisMaterialFuellen(tabelle);
        TableViewFueller.mengeMaterialFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und  zurückkaufen von Materialien
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void materialVerkaufenTabelle(TableView<Map.Entry<Material, IntegerProperty>> tabelle) {
        TableViewFueller.iconMaterialFuellen(tabelle);
        TableViewFueller.nameMaterialFuellen(tabelle);
        TableViewFueller.verkaufpreisMaterialFuellen(tabelle);
        TableViewFueller.mengeMaterialFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Rüstung
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void ruestungKaufenTabelle(TableView<Ruestung> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.ruestungsTypFuellen(tabelle);
        TableViewFueller.verteidigungFuellen(tabelle);
        TableViewFueller.magischeVerteidigungFuellen(tabelle);
        TableViewFueller.resistenzFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Rüstung
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void ruestungVerkaufenTabelle(TableView<Ruestung> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.ruestungsTypFuellen(tabelle);
        TableViewFueller.verteidigungFuellen(tabelle);
        TableViewFueller.magischeVerteidigungFuellen(tabelle);
        TableViewFueller.resistenzFuellen(tabelle);
        TableViewFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableViewFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verbrauchsgegenstaendeKaufenTabelle(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableViewFueller.iconVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.nameVGFuellen(tabelle);
        TableViewFueller.beschreibungVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.kaufpreisVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.mengeVerbrauchsgegenstandFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Verbrauchsgegenständen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void verbrauchsgegenstaendeVerkaufenTabelle(TableView<Map.Entry<Verbrauchsgegenstand, IntegerProperty>> tabelle) {
        TableViewFueller.iconVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.nameVGFuellen(tabelle);
        TableViewFueller.beschreibungVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.verkaufpreisVerbrauchsgegenstandFuellen(tabelle);
        TableViewFueller.mengeVerbrauchsgegenstandFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum kaufen von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenKaufenTabelle(TableView<Waffe> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.waffenTypFuellen(tabelle);
        TableViewFueller.attackeFuellen(tabelle);
        TableViewFueller.magischeAttackeFuellen(tabelle);
        TableViewFueller.genauigkeitWaffeFuellen(tabelle);
        TableViewFueller.beweglichkeitWaffeFuellen(tabelle);
        TableViewFueller.kaufpreisFuellen(tabelle);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verkaufen und zurückkaufen von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Kretschmer
     * @since 30.11.23
     */
    public static void waffenVerkaufenTabelle(TableView<Waffe> tabelle) {
        TableViewFueller.iconFuellen(tabelle);
        TableViewFueller.nameFuellen(tabelle);
        TableViewFueller.lvlAnforderungFuellen(tabelle);
        TableViewFueller.waffenTypFuellen(tabelle);
        TableViewFueller.attackeFuellen(tabelle);
        TableViewFueller.magischeAttackeFuellen(tabelle);
        TableViewFueller.genauigkeitWaffeFuellen(tabelle);
        TableViewFueller.beweglichkeitWaffeFuellen(tabelle);
        TableViewFueller.verkaufpreisFuellen(tabelle);
    }

}
