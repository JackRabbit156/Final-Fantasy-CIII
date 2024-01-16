package de.bundeswehr.auf.final_fantasy.menu.schmiede.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.SchmiedeController;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SchmiedeView extends BorderPane {

    PartyController partyController;
    SchmiedeController schmiedeController;

    /**
     * Die SchmiedeView repräsentiert die visuelle Darstellung der Schmiede-Ansicht im Spiel.
     * Sie enthält verschiedene UI-Elemente, wie Hintergrundbilder und Layouts, um die Schmiede-Funktionen anzuzeigen.
     *
     * @param partyController    Die Instanz des PartyControllers, die Informationen über die Spielcharaktere und Materialien enthält.
     * @param schmiedeController Die Instanz des SchmiedeControllers, die für die Interaktionen zwischen der SchmiedeView und der Spiellogik verantwortlich ist.
     * @author OF Stetter
     * @since 05.12.23
     */
    public SchmiedeView(PartyController partyController, SchmiedeController schmiedeController) {
        this.partyController = partyController;
        this.schmiedeController = schmiedeController;

        VBox top = new VBox();
        top.setMinHeight(30);
        this.setTop(top);

        VBox center = new VBox();
        this.setBackground(new Background(new BackgroundImage(new Image("/background/schmiedeHintergrund.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        this.setCenter(center);
    }

    /**
     * Erstellt die Spalten der Tabelle zum verbessern von Accessoires
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void accessoireVerbessernTabelle(TableView<Accessoire> tabelle) {
        TableColumn<Accessoire, ?> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn<Accessoire, String> icon = SchmiedeTableFueller.iconFuellen();
        TableColumn<Accessoire, String> name = SchmiedeTableFueller.nameFuellen();
        TableColumn<Accessoire, Number> lvlAnforderung = SchmiedeTableFueller.lvlAnforderungFuellen();
        TableColumn<Accessoire, Number> maxGesundheit = SchmiedeTableFueller.maxGesundheitsPunkteAaccFuellen();
        TableColumn<Accessoire, Number> maxManaPunkte = SchmiedeTableFueller.maxManaPunkteAccFuellen();
        TableColumn<Accessoire, Number> beweglichkeit = SchmiedeTableFueller.beweglichkeitAccFuellen();
        TableColumn<Accessoire, Number> gesundheitsRegeneration = SchmiedeTableFueller.gesundheitsRegenerationAccFuellen();
        TableColumn<Accessoire, Number> manaRegeneration = SchmiedeTableFueller.manaRegenerationAccFuellen();
        TableColumn<Accessoire, Number> verkaufspreis = SchmiedeTableFueller.verkaufpreisFuellen();
        aktuellesLvl.getColumns().addAll(icon, name, lvlAnforderung, maxGesundheit, maxManaPunkte, beweglichkeit, gesundheitsRegeneration, manaRegeneration, verkaufspreis);

        TableColumn<Accessoire, String> pfeil = new TableColumn<>(" => ");

        TableColumn<Accessoire, ?> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn<Accessoire, Number> lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen();
        TableColumn<Accessoire, Number> maxGesundheitNL = SchmiedeTableFueller.nMaxGesundheitsPunkteAaccFuellen();
        TableColumn<Accessoire, Number> maxManaPunkteNL = SchmiedeTableFueller.nMaxManaPunkteAccFuellen();
        TableColumn<Accessoire, Number> beweglichkeitNL = SchmiedeTableFueller.nBeweglichkeitAccFuellen();
        TableColumn<Accessoire, Number> gesundheitsRegenerationNL = SchmiedeTableFueller.nGesundheitsRegenerationAccFuellen();
        TableColumn<Accessoire, Number> manaRegenerationNL = SchmiedeTableFueller.nManaRegenerationAccFuellen();
        naechsteLvl.getColumns().addAll(lvlNL, maxGesundheitNL, maxManaPunkteNL, beweglichkeitNL, gesundheitsRegenerationNL, manaRegenerationNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verbessern von Rüstungen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void ruestungVerbessernTabelle(TableView<Ruestung> tabelle) {
        TableColumn<Ruestung, ?> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn<Ruestung, String> icon = SchmiedeTableFueller.iconFuellen();
        TableColumn<Ruestung, String> name = SchmiedeTableFueller.nameFuellen();
        TableColumn<Ruestung, Number> lvlAnforderung = SchmiedeTableFueller.lvlAnforderungFuellen();
        TableColumn<Ruestung, String> ruestungsTyp = SchmiedeTableFueller.ruestungsTypFuellen();
        TableColumn<Ruestung, Number> verteidigung = SchmiedeTableFueller.verteidigungFuellen();
        TableColumn<Ruestung, Number> magVerteidigung = SchmiedeTableFueller.magischeVerteidigungFuellen();
        TableColumn<Ruestung, Number> resistenz = SchmiedeTableFueller.resistenzFuellen();
        TableColumn<Ruestung, Number> maxGesundheit = SchmiedeTableFueller.maxGesundheitsPunkteRuestungFuellen();
        TableColumn<Ruestung, Number> maxManaPunkte = SchmiedeTableFueller.maxManaPunkteRuestungFuellen();
        TableColumn<Ruestung, Number> verkauspreis = SchmiedeTableFueller.verkaufpreisFuellen();
        aktuellesLvl.getColumns().addAll(icon, name, lvlAnforderung, ruestungsTyp, verteidigung, magVerteidigung, resistenz, maxGesundheit, maxManaPunkte, verkauspreis);

        TableColumn<Ruestung, String> pfeil = new TableColumn<>(" => ");

        TableColumn<Ruestung, ?> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn<Ruestung, Number> lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen();
        TableColumn<Ruestung, Number> verteidigungNL = SchmiedeTableFueller.nVerteidigungFuellen();
        TableColumn<Ruestung, Number> magVerteidigungNL = SchmiedeTableFueller.nMagischeVerteidigungFuellen();
        TableColumn<Ruestung, Number> resistenzNL = SchmiedeTableFueller.nResistenzFuellen();
        TableColumn<Ruestung, Number> maxGesundheitNL = SchmiedeTableFueller.nMaxGesundheitsPunkteRuestungFuellen();
        TableColumn<Ruestung, Number> maxManaPunkteNL = SchmiedeTableFueller.nMaxManaPunkteRuestungFuellen();
        naechsteLvl.getColumns().addAll(lvlNL, verteidigungNL, magVerteidigungNL, resistenzNL, maxGesundheitNL, maxManaPunkteNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);
    }

    /**
     * Erstellt die Spalten der Tabelle zum verbessern von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void waffenVerbessernTabelle(TableView<Waffe> tabelle) {
        tabelle.getColumns().clear();
        TableColumn<Waffe, ?> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn<Waffe, String> icon = SchmiedeTableFueller.iconFuellen();
        TableColumn<Waffe, String> name = SchmiedeTableFueller.nameFuellen();
        TableColumn<Waffe, Number> lvlAnford = SchmiedeTableFueller.lvlAnforderungFuellen();
        TableColumn<Waffe, String> waffenTyp = SchmiedeTableFueller.waffenTypFuellen();
        TableColumn<Waffe, Number> attacke = SchmiedeTableFueller.attakeFuellen();
        TableColumn<Waffe, Number> magAttack = SchmiedeTableFueller.magischeAttakeFuellen();
        TableColumn<Waffe, Number> genauigkeit = SchmiedeTableFueller.genauigkeitWaffeFuellen();
        TableColumn<Waffe, Number> beweglichkeit = SchmiedeTableFueller.beweglichkeitWaffeFuellen();
        TableColumn<Waffe, Number> verkaufspreis = SchmiedeTableFueller.verkaufpreisFuellen();
        aktuellesLvl.getColumns().addAll(icon, name, lvlAnford, waffenTyp, attacke, magAttack, genauigkeit, beweglichkeit, verkaufspreis);

        TableColumn<Waffe, String> pfeil = new TableColumn<>(" => ");

        TableColumn<Waffe, ?> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn<Waffe, Number> lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen();
        TableColumn<Waffe, Number> attackeNL = SchmiedeTableFueller.nAttackeFuellen();
        TableColumn<Waffe, Number> magAttackNL = SchmiedeTableFueller.nMagischeAttakeFuellen();
        TableColumn<Waffe, Number> genauigkeitNL = SchmiedeTableFueller.nGenauigkeitWaffeFuellen();
        TableColumn<Waffe, Number> beweglichkeitNL = SchmiedeTableFueller.nBeweglichkeitWaffeFuellen();
        naechsteLvl.getColumns().addAll(lvlNL, attackeNL, magAttackNL, genauigkeitNL, beweglichkeitNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);
    }

}
