package haendler;

import gegenstand.Ausruestungsgegenstand.Ausruestungsgegenstand;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;
import gegenstand.Gegenstand;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HaendlerView extends TabPane {

    TabPane root = new TabPane();

    TableView <Waffe> waffenKaufen = new TableView<>();



    private void iconFuellen (TableView tabelle) {
        //ToDo Icon einfügen
    }
    private void nameFuellen (TableView tabelle){
        TableColumn <Gegenstand, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
    }
    private void lvlAnforderungFuellen (TableView tabelle){
        TableColumn <Ausruestungsgegenstand, Number> lvlAnforderung = new TableColumn<>("LvlAnforderung");
        lvlAnforderung.setCellValueFactory(param -> new SimpleIntegerProperty( param.getValue().getLevelAnforderung()));
    }
    private void waffenTypFuellen (TableView tabelle){
        TableColumn <Waffe, String> waffentyp = new TableColumn<>("Waffentyp");
        waffentyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
    }
    private void ruestungsTypFuellen (TableView tabelle){
        TableColumn <Ruestung, String> ruestungstyp = new TableColumn<>("Rüstungstyp");
        ruestungstyp.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
    }
    private void attakeFuellen (TableView tabelle){
        TableColumn <Waffe, Number> attacke = new TableColumn<>("Attacke");
        attacke.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getAttacke()));
    }
    private void magischeAttakeFuellen (TableView tabelle){
        TableColumn <Waffe, Number> magischeAttacke = new TableColumn<>("MagischeAttacke");
        magischeAttacke.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getMagischeAttacke()));
    }
    private void genauigkeitWaffeFuellen (TableView tabelle){
        TableColumn <Waffe, Number> genauigkeitWaffe = new TableColumn<>("Genauigkeit");
        genauigkeitWaffe.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getGenauigkeit()));
    }
    private void beweglichkeitWaffeFuellen (TableView tabelle){
        TableColumn <Waffe, Number> beweglichkeitWaffe = new TableColumn<>("Beweglichkeit");
        beweglichkeitWaffe.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getBeweglichkeit()));
    }
    private void kaufpreisFuellen (TableView tabelle){
        TableColumn <Gegenstand, Number> kaufpreis = new TableColumn<>("Kaufpreis");
        kaufpreis.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getKaufwert()));
    }
    private void verkaufpreisFuellen (TableView tabelle){
        TableColumn <Gegenstand, Number> verkaufpreis = new TableColumn<>("Verkaufpreis");
        verkaufpreis.setCellValueFactory(param ->  new SimpleIntegerProperty( param.getValue().getVerkaufswert()));
    }









}
