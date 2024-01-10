package de.bundeswehr.auf.final_fantasy.menu.schmiede.view;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.SchmiedeController;

public class SchmiedeView extends BorderPane{

    PartyController partyController;
    SchmiedeController schmiedeController;

    /**
     * Die SchmiedeView repräsentiert die visuelle Darstellung der Schmiede-Ansicht im Spiel.
     * Sie enthält verschiedene UI-Elemente, wie Hintergrundbilder und Layouts, um die Schmiede-Funktionen anzuzeigen.
     * @param partyController Die Instanz des PartyControllers, die Informationen über die Spielcharaktere und Materialien enthält.
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
     * Erstellt die Spalten der Tabelle zum verbessern von Waffen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void waffenVerbessernTabelle(TableView tabelle) {
        tabelle.getColumns().clear();
        TableColumn<Waffe, String> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn icon = SchmiedeTableFueller.iconFuellen(tabelle);
        TableColumn name = SchmiedeTableFueller.nameFuellen(tabelle);
        TableColumn lvlAnford = SchmiedeTableFueller.lvlAnforderungFuellen(tabelle);
        TableColumn waffenTyp = SchmiedeTableFueller.waffenTypFuellen(tabelle);
        TableColumn attacke = SchmiedeTableFueller.attakeFuellen(tabelle);
        TableColumn magAttack = SchmiedeTableFueller.magischeAttakeFuellen(tabelle);
        TableColumn genauigkeit = SchmiedeTableFueller.genauigkeitWaffeFuellen(tabelle);
        TableColumn beweglichkeit = SchmiedeTableFueller.beweglichkeitWaffeFuellen(tabelle);
        TableColumn verkaufspreis = SchmiedeTableFueller.verkaufpreisFuellen(tabelle);
        aktuellesLvl.getColumns().addAll(icon,name,lvlAnford,waffenTyp,attacke,magAttack,genauigkeit,beweglichkeit,verkaufspreis);

        TableColumn<Accessoire, String> pfeil = new TableColumn<>(" => ");


        TableColumn<Waffe, String> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen(tabelle);
        TableColumn attackeNL = SchmiedeTableFueller.nAttackeFuellen(tabelle);
        TableColumn magAttackNL = SchmiedeTableFueller.nMagischeAttakeFuellen(tabelle);
        TableColumn genauigkeitNL = SchmiedeTableFueller.nGenauigkeitWaffeFuellen(tabelle);
        TableColumn beweglichkeitNL = SchmiedeTableFueller.nBeweglichkeitWaffeFuellen(tabelle);
        naechsteLvl.getColumns().addAll(lvlNL,attackeNL,magAttackNL,genauigkeitNL,beweglichkeitNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);
    }

    /**
     * Erstellt die Spalten der  Tabelle zum verbessern von Rüstungen
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void ruestungVerbessernTabelle(TableView tabelle) {
        TableColumn<Ruestung, String> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn icon = SchmiedeTableFueller.iconFuellen(tabelle);
        TableColumn name = SchmiedeTableFueller.nameFuellen(tabelle);
        TableColumn lvlAnforderung = SchmiedeTableFueller.lvlAnforderungFuellen(tabelle);
        TableColumn ruestungsTyp = SchmiedeTableFueller.ruestungsTypFuellen(tabelle);
        TableColumn verteidigung = SchmiedeTableFueller.verteidigungFuellen(tabelle);
        TableColumn magVerteidigung = SchmiedeTableFueller.magischeVerteidigungFuellen(tabelle);
        TableColumn resistenz = SchmiedeTableFueller.resistenzFuellen(tabelle);
        TableColumn maxGesundheit = SchmiedeTableFueller.maxGesundheitsPunkteRuestungFuellen(tabelle);
        TableColumn maxManaPunkte = SchmiedeTableFueller.maxManaPunkteRuestungFuellen(tabelle);
        TableColumn verkauspreis = SchmiedeTableFueller.verkaufpreisFuellen(tabelle);
        aktuellesLvl.getColumns().addAll(icon,name,lvlAnforderung,ruestungsTyp,verteidigung,magVerteidigung,resistenz,maxGesundheit,maxManaPunkte,verkauspreis);

        TableColumn<Accessoire, String> pfeil = new TableColumn<>(" => ");

        TableColumn<Ruestung, String> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen(tabelle);
        TableColumn verteidigungNL = SchmiedeTableFueller.nVerteidigungFuellen(tabelle);
        TableColumn magVerteidigungNL = SchmiedeTableFueller.nMagischeVerteidigungFuellen(tabelle);
        TableColumn resistenzNL = SchmiedeTableFueller.nResistenzFuellen(tabelle);
        TableColumn maxGesundheitNL = SchmiedeTableFueller.nMaxGesundheitsPunkteRuestungFuellen(tabelle);
        TableColumn maxManaPunkteNL = SchmiedeTableFueller.nMaxManaPunkteRuestungFuellen(tabelle);
        naechsteLvl.getColumns().addAll(lvlNL,verteidigungNL,magVerteidigungNL,resistenzNL,maxGesundheitNL,maxManaPunkteNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);

    }

    /**
     * Erstellt die Spalten der Tabelle zum verbessern von Accessoires
     *
     * @param tabelle Die Tableview wo es hinzugefügt werden soll.
     * @author OF Stetter
     * @since 05.12.23
     */
    public static void accessoireVerbessernTabelle(TableView tabelle) {
        TableColumn<Accessoire, String> aktuellesLvl = new TableColumn<>("Aktuelles Level");
        TableColumn icon = SchmiedeTableFueller.iconFuellen(tabelle);
        TableColumn name = SchmiedeTableFueller.nameFuellen(tabelle);
        TableColumn lvlAnforderung =  SchmiedeTableFueller.lvlAnforderungFuellen(tabelle);
        TableColumn maxGesundheit = SchmiedeTableFueller.maxGesundheitsPunkteAaccFuellen(tabelle);
        TableColumn maxManaPunkte = SchmiedeTableFueller.maxManaPunkteAccFuellen(tabelle);
        TableColumn beweglichkeit = SchmiedeTableFueller.beweglichkeitAccFuellen(tabelle);
        TableColumn gesundheitsRegeneration = SchmiedeTableFueller.gesundheitsRegenerationAccFuellen(tabelle);
        TableColumn manaRegeneration = SchmiedeTableFueller.manaRegenerationAccFuellen(tabelle);
        TableColumn verkaufspreis = SchmiedeTableFueller.verkaufpreisFuellen(tabelle);
        aktuellesLvl.getColumns().addAll(icon,name,lvlAnforderung,maxGesundheit,maxManaPunkte,beweglichkeit,gesundheitsRegeneration,manaRegeneration,verkaufspreis);

        TableColumn<Accessoire, String> pfeil = new TableColumn<>(" => ");


        TableColumn<Accessoire, String> naechsteLvl = new TableColumn<>("Nächstes Level");
        TableColumn lvlNL = SchmiedeTableFueller.nLvlAnforderungFuellen(tabelle);
        TableColumn maxGesundheitNL = SchmiedeTableFueller.nMaxGesundheitsPunkteAaccFuellen(tabelle);
        TableColumn maxManaPunkteNL = SchmiedeTableFueller.nMaxManaPunkteAccFuellen(tabelle);
        TableColumn beweglichkeitNL = SchmiedeTableFueller.nBeweglichkeitAccFuellen(tabelle);
        TableColumn gesundheitsRegenerationNL = SchmiedeTableFueller.nGesundheitsRegenerationAccFuellen(tabelle);
        TableColumn manaRegenerationNL = SchmiedeTableFueller.nManaRegenerationAccFuellen(tabelle);
        naechsteLvl.getColumns().addAll(lvlNL,maxGesundheitNL,maxManaPunkteNL,beweglichkeitNL,gesundheitsRegenerationNL,manaRegenerationNL);
        tabelle.getColumns().addAll(aktuellesLvl, pfeil, naechsteLvl);
    }

}
