package hauptmenu.speicherstand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import charakter.model.SpielerCharakter;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;

//import gegenstand.Ausruestungsgegenstand.Ruestung;
//import gegenstand.Ausruestungsgegenstand.Waffe;

import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import party.Party;
import party.PartyController;
import statistik.Statistik;
import trainer.faehigkeiten.Faehigkeit;

public class SpeicherstandController {

	int gewaehlterSpeicherstand = 0;

	/**
	 * Schreibt alle Daten die gespeichert werden muessen in den entsprechenden
	 * Spielstand (SQLite Datenbank). Wenn es noch ueberhaupt gar keinen Spielstand
	 * gibt, werden alle Tabellen erstellt, die zum Speichern der Daten benoetigt
	 * werden.
	 * 
	 * @author Melvin
	 * @throws SQLException
	 * @since 16.11.2023
	 */
	public void speichern(Speicherstand speicherstand) {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			System.out.println("Aktueller Spielstand wird gespeichert. Bitte warten...");
			try (Statement statement = connection.createStatement()) {
				// @formatter:off
//				statement.execute("DROP TABLE IF EXISTS Charakter;");
				// Attribute als Enum gespeichert
				statement.execute("CREATE TABLE IF NOT EXISTS  Charakter ("
						+ "  charakter_ID	         INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  party_ID    	         INTEGER REFERENCES Party(party_ID),"
						+ "  istHauptCharakter    	 BOOLEAN             ,"
						+ "  klasseBezeichnung	     TEXT REFERENCES Klasse(bezeichnung),"
						+ "  name	                 TEXT        		 ,"
						+ "  grafischeDarstellung    TEXT        		 ,"
						+ "  level	                 INTEGER     		 ,"
						+ "  gesundheitsPunkte       INTEGER     		 ,"
						+ "  maxGesundheitsPunkte    INTEGER    		 ,"
						+ "  manaPunkte              INTEGER     		 ,"
						+ "  maxManaPunkte           INTEGER     		 ,"
						+ "  physischeAttacke        INTEGER     		 ,"
						+ "  magischeAttacke         INTEGER     		 ,"
						+ "  genauigkeit             INTEGER     		 ,"
						+ "  verteidigung            INTEGER     		 ,"
						+ "  magischeVerteidigung    INTEGER     		 ,"
						+ "  resistenz                INTEGER     		 ,"
						+ "  beweglichkeit           INTEGER     		 ,"
						+ "  gesundheitsregeneration INTEGER     		 ,"
						+ "  manaRegeneration        	INTEGER     	 ," 
						+ "  geschichte				 	TEXT	     	 ,"
						+ "  erfahrungsPunkte		 	INTEGER	         ,"
						+ "  offeneFaehigkeitspunkte 	INTEGER	     	 ,"
						+ "  verteilteFaehigkeitspunkte INTEGER	     	 ,"
						+ "  offeneAttributpunkte       INTEGER	     	  " + ");");
						

				//TODO Waffen haben sich geändert! Spiel Laden und Speichern WICHTIG
				statement.execute("DROP TABLE IF EXISTS Waffe;");
				statement.execute("CREATE TABLE IF NOT EXISTS   Waffe   ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  party_ID     	         INTEGER REFERENCES Party(party_ID),"
						+ "  waffe_ID     	         INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  name     	             TEXT        ,"
						+ "  kaufwert     	         INTEGER     ,"
						+ "  icon     	     		 TEXT    	 ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  istNichtKaufbar     	 BOOLEAN     ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  istSoeldnerItem     	 BOOLEAN     ,"
						+ "  attacke	        	 INTEGER 	 ,"
						+ "  magischeAttacke		 INTEGER 	 ,"
						+ "  genauigkeit	         INTEGER 	 ,"
						+ "  beweglichkeit	         INTEGER 	 ,"
						+ "  waffenTyp	             TEXT         " + ");");

				
				//TODO Ruestungen haben sich geaendert! Spiel Laden und Speichern WICHTIG
//				statement.execute("DROP TABLE IF EXISTS Ruestung;");
				statement.execute("CREATE TABLE IF NOT EXISTS   Ruestung ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  party_ID     	         INTEGER REFERENCES Party(party_ID),"
						+ "  ruestung_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  name     	             TEXT        ,"
						+ "  icon     	             TEXT        ,"
						+ "  kaufwert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  istNichtKaufbar     	 BOOLEAN     ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  istSoeldnerItem     	 BOOLEAN     ,"
						+ "  magischeVerteidigung	 INTEGER     ,"
						+ "  verteidigung			 INTEGER     ,"
						+ "  resistenz				 INTEGER     ,"
						+ "  maxGesundheitsPunkte	 INTEGER     ,"
						+ "  maxManaPunkte	         INTEGER     ,"
						+ "  ruestungsTyp            TEXT 	      " + ");");
				
				
//				statement.execute("DROP TABLE IF EXISTS Faehigkeit;");
				statement.execute("CREATE TABLE IF NOT EXISTS   Faehigkeit ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  faehigkeit_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  name     	             TEXT        ,"
						+ "  beschreibung     	     TEXT        ,"
						+ "  manaKosten     	     INTEGER     ,"
						+ "  level     	 			 INTEGER     ,"
						+ "  levelAnforderung	     INTEGER     ,"
						+ "  istFreundlich           BOOLEAN 	 ,"
						+ "  effektStaerke           INTEGER 	 ,"
						+ "  zielAnzahl     	     INTEGER     ,"
						+ "  wahrscheinlichkeit  	 DOUBLE      ,"
						+ "  zielAttribut            TEXT      	 ," 
						+ "  faehigkeitsTyp  	     TEXT         "+ ");");
				
				
				
//				statement.execute("DROP TABLE IF EXISTS Accessoire;");
				statement.execute("CREATE TABLE IF NOT EXISTS   Accessoire ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  party_ID     	         INTEGER REFERENCES Party(party_ID),"
						+ "  accessoire_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  icon     	             TEXT        ,"
						+ "  name     	             TEXT        ,"
						+ "  kaufwert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  istNichtKaufbar     	 BOOLEAN	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  istSoeldnerItem     	 BOOLEAN     ,"
						+ "  gesundheitsRegeneration INTEGER     ,"
						+ "  manaRegeneration        INTEGER     ,"
						+ "  beweglichkeit           INTEGER      "+ ");");

				
				
//				statement.execute("DROP TABLE IF EXISTS Party;");
				statement.execute("CREATE TABLE IF NOT EXISTS    Party   ("
						+ "  party_ID    	 		 INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID)        ,"
						+ "  hauptCharakter     	 TEXT          				   ,"
						+ "  nebenCharakter1	     TEXT         				   ,"
						+ "  nebenCharakter2	     TEXT         				   ,"
						+ "  nebenCharakter3	     TEXT        				   ,"
						+ "  gold	     			 INTEGER       " + ");");
				
				
//				statement.execute("DROP TABLE IF EXISTS Statistik;");
				statement.execute("CREATE TABLE IF NOT EXISTS    Statistik   ("
						+ "  statistik_ID    	         INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID)        ,"
						+ "  gesamtErwirtschaftetesGold  INTEGER         													   ,"
						+ "  durchgefuehrteKaempfe	     INTEGER         													   ,"
						+ "  gewonneneKaempfe	     	 INTEGER     														   ,"
						+ "  verloreneKaempfe	     	 INTEGER      														    " + ");");
				
//				statement.execute("DROP TABLE IF EXISTS Verbrauchsgegenstand;");
				statement.execute("CREATE TABLE IF NOT EXISTS    Verbrauchsgegenstand             ("
						+ "  party_ID    	 	 INTEGER REFERENCES Party(party_ID)		   		  ,"
						+ "  name	             TEXT         									  ,"
						+ "  kaufwert	         INTEGER         								  ,"
						+ "  verkaufswert	     INTEGER         								  ,"
						+ "  anzahl	             INTEGER" 										 + ");");
				
//				statement.execute("DROP TABLE IF EXISTS Material;");
				statement.execute("CREATE TABLE IF NOT EXISTS    Material			              ("
						+ "  party_ID    	 	 INTEGER REFERENCES Party(party_ID)		   		  ,"
						+ "  name	             TEXT         									  ,"
						+ "  anzahl	             INTEGER" + ");");
				
//				statement.execute("DROP TABLE IF EXISTS Speicherstand;");
				statement.execute("CREATE TABLE IF NOT EXISTS    Speicherstand   ("
						+ "  speicherstand_ID   	 INTEGER PRIMARY KEY AUTOINCREMENT        ,"
						+ "  speicherstand_name   	 TEXT       							  ,"
						+ "  schwierigkeitsgrad   	 TEXT        							  ,"
						+ "  hardcore  	 			 BOOLEAN        						  ,"
						+ "  datum                   DATE 	                                   " + ");");
				// @formatter:on
			}

			// Erstelle einen neuen Spielstand zum Abspeichern mit Schwierigkeit, Hardcore
			// und Datum
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Speicherstand (speicherstand_name, schwierigkeitsgrad, hardcore, datum) VALUES (?, ?, ?, ?);")) {
				preparedStatement.setString(1, speicherstand.getParty().getHauptCharakter().getName());
				preparedStatement.setString(2, speicherstand.getSchwierigkeitsgrad());
				preparedStatement.setBoolean(3, speicherstand.isHardcore());
				preparedStatement.setObject(4, LocalDateTime.now());
				preparedStatement.execute();
			}
			int speicherstand_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(speicherstand_ID) FROM Speicherstand;");
				resultset.next();
				speicherstand_ID = resultset.getInt(1);

			}
			SpielerCharakter hauptCharakter = speicherstand.getParty().getHauptCharakter();

			// Save Party ohne Charaktere
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Party (party_ID, hauptCharakter, nebenCharakter1, nebenCharakter2, nebenCharakter3, gold) VALUES (?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setString(2, hauptCharakter.getName());
				for (int counter = 0; counter < 3; counter++) {
					if (speicherstand.getParty().getNebenCharakter()[counter] != null) {
						preparedStatement.setString(3 + counter,
								speicherstand.getParty().getNebenCharakter()[counter].getName());
					}
					else {
						preparedStatement.setString(3 + counter, null);
					}
				}
				preparedStatement.setInt(6, speicherstand.getParty().getGold());
				preparedStatement.execute();
			}

			// Save Hauptcharakter der Party
			saveSpielerCharakter(speicherstand_ID, hauptCharakter, connection, true);

			// Save Nebencharaktere der Party
			for (int counter = 0, len = speicherstand.getParty().getNebenCharakter().length; counter < len; counter++) {
				saveSpielerCharakter(speicherstand_ID, speicherstand.getParty().getNebenCharakter()[counter],
						connection, false);
			}

			// Speichern der aktuellen Statistik
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Statistik (statistik_ID, gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe) VALUES (?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setInt(2, speicherstand.getStatistik().getGesamtErwirtschaftetesGold());
				preparedStatement.setInt(3, speicherstand.getStatistik().getDurchgefuehrteKaempfe());
				preparedStatement.setInt(4, speicherstand.getStatistik().getGewonneneKaempfe());
				preparedStatement.setInt(5, speicherstand.getStatistik().getVerloreneKaempfe());
				preparedStatement.execute();
			}

			// Speichern aller Verbrauchsgegenstaende (Traenke)

			for (Entry<Verbrauchsgegenstand, IntegerProperty> entry : speicherstand.getParty()
					.getVerbrauchsgegenstaende().entrySet()) {
				Verbrauchsgegenstand item = entry.getKey();
				int itemAnzahl = entry.getValue().get();
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Verbrauchsgegenstand (party_ID, name, kaufwert, verkaufswert, anzahl) VALUES (?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, item.getName());

					preparedStatement.setInt(3, item.getKaufwert());
					preparedStatement.setInt(4, item.getVerkaufswert());
					preparedStatement.setInt(5, itemAnzahl);
					preparedStatement.execute();
				}
			}

			for (Entry<Material, IntegerProperty> entry : speicherstand.getParty().getMaterialien().entrySet()) {
				String materialName = entry.getKey().getName();
				int materialAnzahl = entry.getValue().get();
				try (final PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO Material (party_ID, name, anzahl) VALUES (?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, materialName);
					preparedStatement.setInt(3, materialAnzahl);
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getAusruestungsgegenstandInventar().getInventarWaffen()
					.size(); counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Waffe (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getName());
					preparedStatement.setInt(3, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getKaufwert());
					preparedStatement.setInt(4, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).isIstNichtKaufbar());
					preparedStatement.setInt(6, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getLevelAnforderung());
					preparedStatement.setBoolean(7, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).isIstSoeldnerItem());
					preparedStatement.setInt(8, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getAttacke());
					preparedStatement.setInt(9, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getMagischeAttacke());
					preparedStatement.setString(10, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarWaffen().get(counter).getClass().getSimpleName());
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getAusruestungsgegenstandInventar()
					.getInventarRuestung().size(); counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Ruestung (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, Verteigung, ruestungsTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getName());
					preparedStatement.setInt(3, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getKaufwert());
					preparedStatement.setInt(4, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).isIstNichtKaufbar());
					preparedStatement.setInt(6, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getLevelAnforderung());
					preparedStatement.setBoolean(7, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).isIstSoeldnerItem());
					preparedStatement.setInt(8, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getMagischeVerteidigung());
					preparedStatement.setInt(9, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getVerteidigung());
					preparedStatement.setString(10, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarRuestung().get(counter).getClass().getSimpleName());
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getAusruestungsgegenstandInventar()
					.getInventarAccessiore().size(); counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Accessoire (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getName());
					preparedStatement.setInt(3, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getKaufwert());
					preparedStatement.setInt(4, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).isIstNichtKaufbar());
					preparedStatement.setInt(6, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getLevelAnforderung());
					preparedStatement.setBoolean(7, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).isIstSoeldnerItem());
					preparedStatement.setInt(8, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getMaxGesundheitsPunkte());
					preparedStatement.setInt(9, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getMaxManaPunkte());
					preparedStatement.setInt(10, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getGesundheitsRegeneration());
					preparedStatement.setInt(11, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getManaRegeneration());
					preparedStatement.setInt(12, speicherstand.getParty().getAusruestungsgegenstandInventar()
							.getInventarAccessiore().get(counter).getBeweglichkeit());
					preparedStatement.execute();
				}
			}

		} catch (Exception e) {
			System.out.println("Fehler 2");

		}
		System.out.println("Speichern erfolgreich");
	}

	private void saveSpielerCharakter(int speicherstand_ID, SpielerCharakter charakter, final Connection connection,
			boolean istHauptCharakter) throws SQLException {
		// Gibt es den Charakter? (Wichtig beim Nebencharakter-Array!)
		if (charakter != null) {
			// Speicher die Charakterdaten vom aktuellen Charakter
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setBoolean(2, istHauptCharakter);
				preparedStatement.setString(3, charakter.getKlasse().getBezeichnung());
				preparedStatement.setString(4, charakter.getName());
				preparedStatement.setString(5, charakter.getGrafischeDarstellung());
				preparedStatement.setInt(6, charakter.getLevel());
				preparedStatement.setInt(7, charakter.getGesundheitsPunkte());
				preparedStatement.setInt(8, charakter.getMaxGesundheitsPunkte());
				preparedStatement.setInt(9, charakter.getManaPunkte());
				preparedStatement.setInt(10, charakter.getMaxManaPunkte());
				preparedStatement.setInt(11, charakter.getPhysischeAttacke());
				preparedStatement.setInt(12, charakter.getMagischeAttacke());
				preparedStatement.setInt(13, charakter.getGenauigkeit());
				preparedStatement.setInt(14, charakter.getVerteidigung());
				preparedStatement.setInt(15, charakter.getMagischeVerteidigung());
				preparedStatement.setInt(16, charakter.getResistenz());
				preparedStatement.setInt(17, charakter.getBeweglichkeit());
				preparedStatement.setInt(18, charakter.getGesundheitsRegeneration());
				preparedStatement.setInt(19, charakter.getManaRegeneration());
				preparedStatement.setString(20, charakter.getGeschichte());
				preparedStatement.setInt(21, charakter.getErfahrungsPunkte());
				preparedStatement.setInt(22, charakter.getOffeneFaehigkeitspunkte());
				preparedStatement.setInt(23, charakter.getVerteilteFaehigkeitspunkte());
				preparedStatement.setInt(24, charakter.getOffeneAttributpunkte());
				preparedStatement.execute();
			}

			int aktuelleCharakter_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(charakter_ID) FROM Charakter;");
				resultset.next();
				aktuelleCharakter_ID = resultset.getInt(1);
			}

			// Speicher die ausgeruestete Waffe des aktuellen Charakters
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Waffe (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, icon, genauigkeit, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, charakter.getWaffe().getName());
				preparedStatement.setInt(3, charakter.getWaffe().getKaufwert());
				preparedStatement.setInt(4, charakter.getWaffe().getVerkaufswert());
				preparedStatement.setBoolean(5, charakter.getWaffe().isIstNichtKaufbar());
				preparedStatement.setInt(6, charakter.getWaffe().getLevelAnforderung());
				preparedStatement.setBoolean(7, charakter.getWaffe().isIstSoeldnerItem());
				preparedStatement.setInt(8, charakter.getWaffe().getAttacke());
				preparedStatement.setInt(9, charakter.getWaffe().getMagischeAttacke());
				preparedStatement.setString(10, charakter.getWaffe().getClass().getSimpleName());
				preparedStatement.setString(11, charakter.getWaffe().getIcon());
				preparedStatement.setInt(12, charakter.getWaffe().getGenauigkeit());
				preparedStatement.setInt(13, charakter.getWaffe().getBeweglichkeit());
				preparedStatement.execute();
			}

			// Speichert die Faehigkeiten des aktuellen Charakters
			for (int counter = 0, len = charakter.getFaehigkeiten().size(); counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Faehigkeit (charakter_ID, name, beschreibung, manaKosten, level, levelAnforderung, istFreundlich, effektStaerke, zielAnzahl, wahrscheinlichkeit, zielAttribut, faehigkeitsTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, aktuelleCharakter_ID);
					preparedStatement.setString(2, charakter.getFaehigkeiten().get(counter).getName());
					preparedStatement.setString(3, charakter.getFaehigkeiten().get(counter).getBeschreibung());
					preparedStatement.setInt(4, charakter.getFaehigkeiten().get(counter).getManaKosten());
					preparedStatement.setInt(5, charakter.getFaehigkeiten().get(counter).getLevel());
					preparedStatement.setInt(6, charakter.getFaehigkeiten().get(counter).getLevelAnforderung());
					preparedStatement.setBoolean(7, charakter.getFaehigkeiten().get(counter).isIstFreundlich());
					preparedStatement.setInt(8, charakter.getFaehigkeiten().get(counter).getEffektStaerke());
					preparedStatement.setInt(9, charakter.getFaehigkeiten().get(counter).getZielAnzahl());
					preparedStatement.setDouble(10, charakter.getFaehigkeiten().get(counter).getWahrscheinlichkeit());
					preparedStatement.setString(11, charakter.getFaehigkeiten().get(counter).getZielAttribut());
					preparedStatement.setString(12, charakter.getFaehigkeiten().get(counter).getFaehigkeitsTyp());
					preparedStatement.execute();
				}
			}

			// Speicher die ausgeruestete Ruestung des aktuellen Charakters
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Ruestung (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, resistenz, maxGesundheitsPunkte, maxManaPunkte, icon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, charakter.getRuestung().getName());
				preparedStatement.setInt(3, charakter.getRuestung().getKaufwert());
				preparedStatement.setInt(4, charakter.getRuestung().getVerkaufswert());
				preparedStatement.setBoolean(5, charakter.getRuestung().isIstNichtKaufbar());
				preparedStatement.setInt(6, charakter.getRuestung().getLevelAnforderung());
				preparedStatement.setBoolean(7, charakter.getRuestung().isIstSoeldnerItem());
				preparedStatement.setInt(8, charakter.getRuestung().getMagischeVerteidigung());
				preparedStatement.setInt(9, charakter.getRuestung().getVerteidigung());
				preparedStatement.setString(10, charakter.getRuestung().getClass().getSimpleName());
				preparedStatement.setInt(11, charakter.getRuestung().getResistenz());
				preparedStatement.setInt(12, charakter.getRuestung().getMaxGesundheitsPunkte());
				preparedStatement.setInt(13, charakter.getRuestung().getMaxManaPunkte());
				preparedStatement.setString(14, charakter.getRuestung().getIcon());
				preparedStatement.execute();
			}

			// Speicher die ausgeruesteten Accessoires des aktuellen Charakters
			for (int counter = 0; counter < 3; counter++) {
				// Der aktulelle Accessoire-Slot vom aktuellen Charakter ist befuellt und
				// Attribute werden gespeichert
				if (charakter.getAccessoires()[counter] != null) {
					try (final PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO Accessoire (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, gesundheitsRegeneration, manaRegeneration, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
						preparedStatement.setInt(1, aktuelleCharakter_ID);
						preparedStatement.setString(2, charakter.getAccessoires()[counter].getName());
						preparedStatement.setInt(3, charakter.getAccessoires()[counter].getKaufwert());
						preparedStatement.setInt(4, charakter.getAccessoires()[counter].getVerkaufswert());
						preparedStatement.setBoolean(5, charakter.getAccessoires()[counter].isIstNichtKaufbar());
						preparedStatement.setInt(6, charakter.getAccessoires()[counter].getLevelAnforderung());
						preparedStatement.setBoolean(7, charakter.getAccessoires()[counter].isIstSoeldnerItem());
						preparedStatement.setInt(8, charakter.getAccessoires()[counter].getGesundheitsRegeneration());
						preparedStatement.setInt(9, charakter.getAccessoires()[counter].getManaRegeneration());
						preparedStatement.setInt(10, charakter.getAccessoires()[counter].getBeweglichkeit());
						preparedStatement.execute();
					}
				}
				// Der aktulelle Accessoire-Slot vom aktuellen Charakter ist nicht befuellt und
				// ein leer-Accessoire wird gespeichert
				else {
					try (final PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO Accessoire (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
						preparedStatement.setInt(1, aktuelleCharakter_ID);
						preparedStatement.setString(2, null);
						preparedStatement.setInt(3, -1);
						preparedStatement.setInt(4, -1);
						preparedStatement.setBoolean(5, false);
						preparedStatement.setInt(6, -1);
						preparedStatement.setBoolean(7, false);
						preparedStatement.setInt(8, -1);
						preparedStatement.setInt(9, -1);
						preparedStatement.setInt(10, -1);
						preparedStatement.execute();
					}
				}
			}
		}
		// Der Nebencharakterslot ist leer
		else {
			// Leerer Charakter wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {

				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setBoolean(2, istHauptCharakter);
				preparedStatement.setString(3, null);
				preparedStatement.setString(4, null);
				preparedStatement.setString(5, null);
				preparedStatement.setInt(6, -1);
				preparedStatement.setInt(7, -1);
				preparedStatement.setInt(8, -1);
				preparedStatement.setInt(9, -1);
				preparedStatement.setInt(10, -1);
				preparedStatement.setInt(11, -1);
				preparedStatement.setInt(12, -1);
				preparedStatement.setInt(13, -1);
				preparedStatement.setInt(14, -1);
				preparedStatement.setInt(15, -1);
				preparedStatement.setInt(16, -1);
				preparedStatement.setInt(17, -1);
				preparedStatement.setInt(18, -1);
				preparedStatement.setInt(19, -1);
				preparedStatement.setString(20, null);
				preparedStatement.setInt(21, -1);
				preparedStatement.setInt(22, -1);
				preparedStatement.setInt(23, -1);
				preparedStatement.setInt(24, -1);
				preparedStatement.execute();
			}
			int aktuelleCharakter_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(charakter_ID) FROM Charakter;");
				resultset.next();
				aktuelleCharakter_ID = resultset.getInt(1);
			}
			// Leere Waffe wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Waffe (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, icon, genauigkeit, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, null);
				preparedStatement.setInt(3, -1);
				preparedStatement.setInt(4, -1);
				preparedStatement.setBoolean(5, false);
				preparedStatement.setInt(6, -1);
				preparedStatement.setBoolean(7, false);
				preparedStatement.setInt(8, -1);
				preparedStatement.setInt(9, -1);
				preparedStatement.setString(10, null);
				preparedStatement.setString(11, null);
				preparedStatement.setInt(12, -1);
				preparedStatement.setInt(13, -1);
				preparedStatement.execute();

			}
			// Leere Ruestung wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Ruestung (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, resistenz, maxGesundheitsPunkte, maxManaPunkte, icon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, null);
				preparedStatement.setInt(3, -1);
				preparedStatement.setInt(4, -1);
				preparedStatement.setBoolean(5, false);
				preparedStatement.setInt(6, -1);
				preparedStatement.setBoolean(7, false);
				preparedStatement.setInt(8, -1);
				preparedStatement.setInt(9, -1);
				preparedStatement.setString(10, null);
				preparedStatement.setInt(11, -1);
				preparedStatement.setInt(12, -1);
				preparedStatement.setInt(13, -1);
				preparedStatement.setString(14, null);
				preparedStatement.execute();
			}
			// Drei Leere Accessoire-Slots werden erstellt fuer den leeren Charakter
			for (int counter = 0; counter < 3; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Accessoire (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, aktuelleCharakter_ID);
					preparedStatement.setString(2, null);
					preparedStatement.setInt(3, -1);
					preparedStatement.setInt(4, -1);
					preparedStatement.setBoolean(5, false);
					preparedStatement.setInt(6, -1);
					preparedStatement.setBoolean(7, false);
					preparedStatement.setInt(8, -1);
					preparedStatement.setInt(9, -1);
					preparedStatement.setInt(10, -1);
					preparedStatement.setInt(11, -1);
					preparedStatement.setInt(12, -1);
					preparedStatement.execute();
				}
			}

		}
	}

	public ObservableList<String> speicherstaendeAbrufen() {
		ObservableList<String> speicherstaende = FXCollections.observableArrayList();
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT datum, speicherstand_name, speicherstand_ID FROM Speicherstand;");
			while (resultSet.next()) {
				String aktuellerSpeicherstand = "";
				LocalDateTime datum = LocalDateTime.parse(resultSet.getString("datum"));
				aktuellerSpeicherstand += datum + " | " + resultSet.getString("speicherstand_name");
				speicherstaende.add(aktuellerSpeicherstand);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return speicherstaende;

	}

	public boolean istSpeicherstandVorhanden() {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT datum, speicherstand_name, speicherstand_ID FROM Speicherstand;");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * lässt den Nutzer den Speicherstand auswählen und laden
	 * 
	 * @author Melvin
	 * @since 16.11.2023
	 */
	public Speicherstand speicherstandAuswahl() {
		gewaehlterSpeicherstand = 0;
		int zuLadenderSpeicherstand_ID = 0;
		int aktuelleCharakter_ID = 0;
		try {
			try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
				Statement statement = connection.createStatement();
				try {
					ResultSet resultSet = statement
							.executeQuery("SELECT datum, speicherstand_name, speicherstand_ID FROM Speicherstand;");
				} catch (Exception e) {
					return null;
				}

				ResultSet resultSet = statement
						.executeQuery("SELECT datum, speicherstand_name, speicherstand_ID FROM Speicherstand;");
				int counter = 1;
				int positionAuswahlSpieler = 0;
				int aktuelleZeile = 1;
				resultSet = statement
						.executeQuery("SELECT datum, speicherstand_name, speicherstand_ID FROM Speicherstand;");

				zuLadenderSpeicherstand_ID = gewaehlterSpeicherstand;

				resultSet = statement.executeQuery(
						"SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID FROM Charakter WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + " AND istHauptCharakter =" + true + ";");
				resultSet.next();
				aktuelleCharakter_ID = resultSet.getInt("charakter_ID");
				SpielerCharakter hauptCharakter = new SpielerCharakter(resultSet.getString("name"),
						resultSet.getString("klasseBezeichnung"), resultSet.getString("geschichte"));
				hauptCharakter.setGrafischeDarstellung(resultSet.getString("grafischeDarstellung"));
				hauptCharakter.setLevel(resultSet.getInt("level"));
				hauptCharakter.setGesundheitsPunkte(resultSet.getInt("gesundheitsPunkte"));
				hauptCharakter.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
				hauptCharakter.setManaPunkte(resultSet.getInt("manaPunkte"));
				hauptCharakter.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
				hauptCharakter.setPhysischeAttacke(resultSet.getInt("physischeAttacke"));
				hauptCharakter.setMagischeAttacke(resultSet.getInt("magischeAttacke"));
				hauptCharakter.setGenauigkeit(resultSet.getInt("genauigkeit"));
				hauptCharakter.setVerteidigung(resultSet.getInt("verteidigung"));
				hauptCharakter.setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
				hauptCharakter.setResistenz(resultSet.getInt("resistenz"));
				hauptCharakter.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
				hauptCharakter.setGesundheitsRegeneration(resultSet.getInt("gesundheitsregeneration"));
				hauptCharakter.setManaRegeneration(resultSet.getInt("manaRegeneration"));
				hauptCharakter.setErfahrungsPunkte(resultSet.getInt("erfahrungspunkte"));
				hauptCharakter.setOffeneFaehigkeitspunkte(resultSet.getInt("offeneFaehigkeitspunkte"));
				hauptCharakter.setVerteilteFaehigkeitspunkte(resultSet.getInt("verteilteFaehigkeitspunkte"));
				hauptCharakter.setOffeneAttributpunkte(resultSet.getInt("offeneAttributpunkte"));

				resultSet = statement.executeQuery(
						"SELECT name, beschreibung, manaKosten, level, levelAnforderung, istFreundlich, effektStaerke, zielAnzahl, wahrscheinlichkeit, zielAttribut, faehigkeitsTyp FROM Faehigkeit WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");

				// TODO Faehigkeiten Hauptcharakter

//				 public Faehigkeit(String name, String beschreibung, int manaKosten, int level, int levelAnforderung,
//	            boolean istFreundlich, int effektStaerke, int zielAnzahl, double wahrscheinlichkeit, String zielAttribut,
//	            String faehigkeitsTyp) {
				List<Faehigkeit> hauptCharakterFaehigkeiten = new ArrayList<>();
				while (resultSet.next()) {
//					Faehigkeit faehigkeit = new Faehigkeit(resultSet.getString("name"),
//							resultSet.getString("beschreibung"), resultSet.getInt("manaKosten"),
//							resultSet.getInt("level"), resultSet.getInt("levelAnforderung"),
//							resultSet.getBoolean("istFreundlich"), resultSet.getInt("effektStaerke"),
//							resultSet.getInt("zielAnzahl"), resultSet.getDouble("wahrscheinlichkeit"),
//							resultSet.getString("zielAttribut"), resultSet.getString("faehigkeitsTyp"));
//					hauptCharakterFaehigkeiten.add(faehigkeit);
				}

				// TODO Waffe Hauptcharakter (waffenTyp)
				resultSet = statement.executeQuery(
						"SELECT charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp FROM Waffe WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				Waffe waffe = new Waffe();
				waffe.setName(resultSet.getString("name"));
				waffe.setKaufwert(resultSet.getInt("kaufwert"));
				waffe.setVerkaufswert(resultSet.getInt("verkaufswert"));
				waffe.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
				waffe.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
				waffe.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
				waffe.setAttacke(resultSet.getInt("attacke"));
				waffe.setMagischeAttacke(resultSet.getInt("magischeAttacke"));
				hauptCharakter.setWaffe(waffe);

				// TODO Ruestung Hauptcharakter (ruestungsTyp)
				resultSet = statement.executeQuery(
						"SELECT charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp FROM Ruestung WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				Ruestung ruestung = new Ruestung();
				ruestung.setName(resultSet.getString("name"));
				ruestung.setKaufwert(resultSet.getInt("kaufwert"));
				ruestung.setVerkaufswert(resultSet.getInt("verkaufswert"));
				ruestung.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
				ruestung.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
				ruestung.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
				ruestung.setVerteidigung(resultSet.getInt("verteidigung"));
				ruestung.setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
				hauptCharakter.setRuestung(ruestung);

				// TODO Accessoires Hauptcharakter
				resultSet = statement.executeQuery(
						"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit FROM Accessoire WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				Accessoire[] hauptcharakterAccessoires = new Accessoire[3];
				int accessoireCounter = 0;
				while (resultSet.next()) {
					if (resultSet.getString("name") != null) {
						Accessoire accessoire = new Accessoire(0);
						accessoire.setName(resultSet.getString("name"));
						accessoire.setVerkaufswert(resultSet.getInt("verkaufswert"));
						accessoire.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
						accessoire.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
						accessoire.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
						accessoire.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
						accessoire.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
						accessoire.setGesundheitsRegeneration(resultSet.getInt("gesundheitsRegeneration"));
						accessoire.setManaRegeneration(resultSet.getInt("manaRegeneration"));
						accessoire.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
					}
					else {
						hauptcharakterAccessoires[accessoireCounter] = null;
					}
					accessoireCounter++;
				}
				hauptCharakter.setAccessoires(hauptcharakterAccessoires);

				SpielerCharakter[] nebenCharaktere = new SpielerCharakter[3];
				try (Statement statement2 = connection.createStatement()) {
					ResultSet resultsetNebencharaktere = statement2.executeQuery(
							"SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID, party_ID "
									+ "FROM Charakter " + "WHERE party_ID = " + zuLadenderSpeicherstand_ID
									+ " AND istHauptCharakter = " + false + ";");
					int nebenCharaktereCounter = 0;
					while (resultsetNebencharaktere.next()) {
						if (resultsetNebencharaktere.getString("name") != null) {
							aktuelleCharakter_ID = resultsetNebencharaktere.getInt("charakter_ID");
							SpielerCharakter nebenCharakter = new SpielerCharakter(
									resultsetNebencharaktere.getString("name"),
									resultsetNebencharaktere.getString("klasseBezeichnung"),
									resultsetNebencharaktere.getString("geschichte"));
							nebenCharakter.setGrafischeDarstellung(
									resultsetNebencharaktere.getString("grafischeDarstellung"));
							nebenCharakter.setLevel(resultsetNebencharaktere.getInt("level"));
							nebenCharakter.setGesundheitsPunkte(resultsetNebencharaktere.getInt("gesundheitsPunkte"));
							nebenCharakter
									.setMaxGesundheitsPunkte(resultsetNebencharaktere.getInt("maxGesundheitsPunkte"));
							nebenCharakter.setManaPunkte(resultsetNebencharaktere.getInt("manaPunkte"));
							nebenCharakter.setMaxManaPunkte(resultsetNebencharaktere.getInt("maxManaPunkte"));
							nebenCharakter.setPhysischeAttacke(resultsetNebencharaktere.getInt("physischeAttacke"));
							nebenCharakter.setMagischeAttacke(resultsetNebencharaktere.getInt("magischeAttacke"));
							nebenCharakter.setGenauigkeit(resultsetNebencharaktere.getInt("genauigkeit"));
							nebenCharakter.setVerteidigung(resultsetNebencharaktere.getInt("verteidigung"));
							nebenCharakter
									.setMagischeVerteidigung(resultsetNebencharaktere.getInt("magischeVerteidigung"));
							nebenCharakter.setResistenz(resultsetNebencharaktere.getInt("resistenz"));
							nebenCharakter.setBeweglichkeit(resultsetNebencharaktere.getInt("beweglichkeit"));
							nebenCharakter.setGesundheitsRegeneration(
									resultsetNebencharaktere.getInt("gesundheitsregeneration"));
							nebenCharakter.setManaRegeneration(resultsetNebencharaktere.getInt("manaRegeneration"));
							nebenCharakter.setErfahrungsPunkte(resultsetNebencharaktere.getInt("erfahrungspunkte"));
							nebenCharakter.setOffeneFaehigkeitspunkte(
									resultsetNebencharaktere.getInt("offeneFaehigkeitspunkte"));
							nebenCharakter.setVerteilteFaehigkeitspunkte(
									resultsetNebencharaktere.getInt("verteilteFaehigkeitspunkte"));
							nebenCharakter
									.setOffeneAttributpunkte(resultsetNebencharaktere.getInt("offeneAttributpunkte"));

							// TODO Faehigkeiten Hauptcharakter
							ResultSet innerResultSet = statement.executeQuery(
									"SELECT name, beschreibung, manaKosten, faehigkeitsTyp, level, effektStaerke, istFreundlich, levelAnforderung, zielAttribut, zielAnzahl, wahrscheinlichkeit, charakter_ID "
											+ "FROM Faehigkeit " + "WHERE charakter_ID =" + aktuelleCharakter_ID + ";");
							List<Faehigkeit> nebenCharakterFaehigkeiten = new ArrayList<>();
							while (resultSet.next()) {
//								Faehigkeit faehigkeit = new Faehigkeit(resultSet.getString("name"),
//										innerResultSet.getString("beschreibung"), resultSet.getInt("manaKosten"),
//										innerResultSet.getInt("level"), resultSet.getInt("levelAnforderung"),
//										innerResultSet.getBoolean("istFreundlich"), resultSet.getInt("effektStaerke"),
//										innerResultSet.getInt("zielAnzahl"), resultSet.getDouble("wahrscheinlichkeit"),
//										innerResultSet.getString("zielAttribut"),
//										resultSet.getString("faehigkeitsTyp"));
//								nebenCharakterFaehigkeiten.add(faehigkeit);
							}

							// TODO Waffe Nebencharaktere
							innerResultSet = statement.executeQuery(
									"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp "
											+ "FROM Waffe " + "WHERE charakter_ID =" + aktuelleCharakter_ID + ";");
							innerResultSet.next();
							waffe = new Waffe();
							waffe.setName(innerResultSet.getString("name"));
							waffe.setKaufwert(innerResultSet.getInt("kaufwert"));
							waffe.setVerkaufswert(innerResultSet.getInt("verkaufswert"));
							waffe.setIstNichtKaufbar(innerResultSet.getBoolean("istNichtKaufbar"));
							waffe.setLevelAnforderung(innerResultSet.getInt("levelAnforderung"));
							waffe.setIstSoeldnerItem(innerResultSet.getBoolean("istSoeldnerItem"));
							waffe.setAttacke(innerResultSet.getInt("attacke"));
							waffe.setMagischeAttacke(innerResultSet.getInt("magischeAttacke"));
							nebenCharakter.setWaffe(waffe);

							// TODO Ruestung Nebencharaktere
							innerResultSet = statement.executeQuery(
									"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp FROM Ruestung WHERE charakter_ID ="
											+ aktuelleCharakter_ID + ";");
							innerResultSet.next();
							ruestung = new Ruestung();
							ruestung.setName(innerResultSet.getString("name"));
							ruestung.setKaufwert(innerResultSet.getInt("kaufwert"));
							ruestung.setVerkaufswert(innerResultSet.getInt("verkaufswert"));
							ruestung.setIstNichtKaufbar(innerResultSet.getBoolean("istNichtKaufbar"));
							ruestung.setLevelAnforderung(innerResultSet.getInt("levelAnforderung"));
							ruestung.setIstSoeldnerItem(innerResultSet.getBoolean("istSoeldnerItem"));
							ruestung.setVerteidigung(innerResultSet.getInt("verteidigung"));
							ruestung.setMagischeVerteidigung(innerResultSet.getInt("magischeVerteidigung"));
							nebenCharakter.setRuestung(ruestung);
							innerResultSet = statement.executeQuery(
									"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit FROM Accessoire WHERE charakter_ID ="
											+ aktuelleCharakter_ID + ";");
							Accessoire[] nebenCharakterAccessoires = new Accessoire[3];
							accessoireCounter = 0;
							while (resultSet.next()) {
								if (resultSet.getString("name") != null) {
									Accessoire accessoire = new Accessoire(0);
									if (resultSet.getString("name") != null) {
										accessoire.setName(innerResultSet.getString("name"));
										accessoire.setKaufwert(innerResultSet.getInt("kaufwert"));
										accessoire.setVerkaufswert(innerResultSet.getInt("verkaufswert"));
										accessoire.setIstNichtKaufbar(innerResultSet.getBoolean("istNichtKaufbar"));
										accessoire.setLevelAnforderung(innerResultSet.getInt("levelAnforderung"));
										accessoire.setIstSoeldnerItem(innerResultSet.getBoolean("istSoeldnerItem"));
										accessoire
												.setMaxGesundheitsPunkte(innerResultSet.getInt("maxGesundheitsPunkte"));
										accessoire.setMaxManaPunkte(innerResultSet.getInt("maxManaPunkte"));
										accessoire.setGesundheitsRegeneration(
												innerResultSet.getInt("gesundheitsRegeneration"));
										accessoire.setManaRegeneration(innerResultSet.getInt("manaRegeneration"));
										accessoire.setBeweglichkeit(innerResultSet.getInt("beweglichkeit"));
									}
								}
								else {
									nebenCharakterAccessoires[accessoireCounter] = null;
								}
								accessoireCounter++;
							}
							nebenCharakter.setAccessoires(nebenCharakterAccessoires);
							nebenCharaktere[nebenCharaktereCounter] = nebenCharakter;
						}
						else {
							nebenCharaktere[nebenCharaktereCounter] = null;
						}
						nebenCharaktereCounter++;
					}
				}

				int zuLadendePartyGold = 0;
				ArrayList<Accessoire> zuLadendePartyAccessoireInventar = new ArrayList<>();
				ArrayList<Waffe> zuLadendePartyWaffenInventar = new ArrayList<>();
				ArrayList<Ruestung> zuLadendePartyRuestungsInventar = new ArrayList<>();
				Map<Material, IntegerProperty> zuLadendePartyMaterialien = new HashMap<Material, IntegerProperty>();
				Map<Verbrauchsgegenstand, IntegerProperty> zuLadendePartyVerbrauchsgegenstaende = new HashMap<Verbrauchsgegenstand, IntegerProperty>();

				// TODO AccessoireInventar laden sobald Accessoire-Constructor vorhanden
				resultSet = statement.executeQuery(
						"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit FROM Accessoire WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				while (resultSet.next()) {
					Accessoire accessoire = new Accessoire(0);
					accessoire.setName(resultSet.getString("name"));
					accessoire.setVerkaufswert(resultSet.getInt("verkaufswert"));
					accessoire.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
					accessoire.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
					accessoire.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
					accessoire.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
					accessoire.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
					accessoire.setGesundheitsRegeneration(resultSet.getInt("gesundheitsRegeneration"));
					accessoire.setManaRegeneration(resultSet.getInt("manaRegeneration"));
					accessoire.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
					zuLadendePartyAccessoireInventar.add(accessoire);
				}
				// TODO WaffenInventar laden sobald Waffen-Constructor vorhanden
				resultSet = statement.executeQuery(
						"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp FROM Waffe WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				while (resultSet.next()) {
					waffe = new Waffe();
					waffe.setName(resultSet.getString("name"));
					waffe.setKaufwert(resultSet.getInt("kaufwert"));
					waffe.setVerkaufswert(resultSet.getInt("verkaufswert"));
					waffe.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
					waffe.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
					waffe.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
					waffe.setAttacke(resultSet.getInt("attacke"));
					waffe.setMagischeAttacke(resultSet.getInt("magischeAttacke"));
					zuLadendePartyWaffenInventar.add(waffe);
				}
				// TODO RuestungsInventar laden sobald Ruestung-Constructor vorhanden
				resultSet = statement.executeQuery(
						"SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp FROM Ruestung WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				while (resultSet.next()) {
					ruestung = new Ruestung();
					ruestung.setName(resultSet.getString("name"));
					ruestung.setKaufwert(resultSet.getInt("kaufwert"));
					ruestung.setVerkaufswert(resultSet.getInt("verkaufswert"));
					ruestung.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
					ruestung.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
					ruestung.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
					ruestung.setVerteidigung(resultSet.getInt("verteidigung"));
					ruestung.setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
					zuLadendePartyRuestungsInventar.add(ruestung);
				}

				// TODO Party-Materialien laden sobald Material-Constructor vorhanden
				resultSet = statement.executeQuery(
						"SELECT name, anzahl FROM Material WHERE party_ID =" + zuLadenderSpeicherstand_ID + ";");
				while (resultSet.next()) {
					if (resultSet.getString("name").equals("Eisenerz")) {
						zuLadendePartyMaterialien.put(Material.EISENERZ,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Silbererz")) {
						zuLadendePartyMaterialien.put(Material.SILBERERZ,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Golderz")) {
						zuLadendePartyMaterialien.put(Material.GOLDERZ,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Mithril")) {
						zuLadendePartyMaterialien.put(Material.MITHRIL,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Popel")) {
						zuLadendePartyMaterialien.put(Material.POPEL,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Schleim")) {
						zuLadendePartyMaterialien.put(Material.SCHLEIM,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
				}

				// TODO Party-Verbrauchsgegenstaende laden sobald
				// Verbrauchsgegenstand-Constructor vorhanden
				resultSet = statement.executeQuery(
						"SELECT name, kaufwert, verkaufswert, anzahl FROM Verbrauchsgegenstand WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				while (resultSet.next()) {
					if (resultSet.getString("name").equals("Grosser Heiltrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_HEILTRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Mittlerer Heiltrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_HEILTRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Kleiner Heiltrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_HEILTRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Grosser Manatrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.GROSSER_MANATRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Mittlerer Manatrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.MITTLERER_MANATRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}
					if (resultSet.getString("name").equals("Kleiner Manatrank")) {
						zuLadendePartyVerbrauchsgegenstaende.put(Verbrauchsgegenstand.KLEINER_MANATRANK,
								new SimpleIntegerProperty(resultSet.getInt("anzahl")));
					}

				}

				resultSet = statement
						.executeQuery("SELECT gold FROM Party WHERE party_ID =" + zuLadenderSpeicherstand_ID + ";");
				zuLadendePartyGold = resultSet.getInt("gold");
				Party zuLadendeParty = new Party(hauptCharakter.getName(), hauptCharakter.getKlasse().getBezeichnung());
				zuLadendeParty.setHauptCharakter(hauptCharakter);
				zuLadendeParty.setNebenCharakter(nebenCharaktere);
				zuLadendeParty.setGold(zuLadendePartyGold);
				zuLadendeParty.getAusruestungsgegenstandInventar()
						.setInventarAccessiore(zuLadendePartyAccessoireInventar);
				zuLadendeParty.getAusruestungsgegenstandInventar().setInventarWaffen(zuLadendePartyWaffenInventar);
				zuLadendeParty.getAusruestungsgegenstandInventar().setInventarRuestung(zuLadendePartyRuestungsInventar);

				zuLadendeParty.setMaterialien(zuLadendePartyMaterialien);
				zuLadendeParty.setVerbrauchsgegenstaende(zuLadendePartyVerbrauchsgegenstaende);

				Statistik zuLadendeStatistik = new Statistik();
				String schwierigkeitsgrad = null;
				boolean istHardcore = false;
				resultSet = statement
						.executeQuery("SELECT schwierigkeitsgrad, hardcore FROM Speicherstand WHERE speicherstand_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				schwierigkeitsgrad = resultSet.getString("schwierigkeitsgrad");
				istHardcore = resultSet.getBoolean("hardcore");
				resultSet = statement.executeQuery(
						"SELECT gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe FROM Statistik WHERE statistik_ID ="
								+ zuLadenderSpeicherstand_ID + ";");
				zuLadendeStatistik.setGesamtErwirtschaftetesGold(resultSet.getInt("gesamtErwirtschaftetesGold"));
				zuLadendeStatistik.setDurchgefuehrteKaempfe(resultSet.getInt("durchgefuehrteKaempfe"));
				zuLadendeStatistik.setGewonneneKaempfe(resultSet.getInt("gewonneneKaempfe"));
				zuLadendeStatistik.setVerloreneKaempfe(resultSet.getInt("verloreneKaempfe"));

				Speicherstand zuLadenderSpeicherstand = new Speicherstand(zuLadendeParty, schwierigkeitsgrad,
						istHardcore, zuLadendeStatistik);
				return zuLadenderSpeicherstand;
			}
		} catch (Exception e) {
		}
		return null;

	}

//	statement.execute("CREATE TABLE IF NOT EXISTS    Speicherstand   ("
//			+ "  speicherstand_ID   	 INTEGER PRIMARY KEY AUTOINCREMENT        ,"
//			+ "  speicherstand_name   	 TEXT       							  ,"
//			+ "  schwierigkeitsgrad   	 TEXT        							  ,"
//			+ "  hardcore  	 			 BOOLEAN        						  ,"
//			+ "  datum                   DATE 	

	public void entferneSpeicherstandHardcore(PartyController partyController) {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(
						"SELECT speicherstand_ID FROM Speicherstand WHERE hardcore = true AND speicherstand_name='"
								+ partyController.getParty().getHauptCharakter().getName() + "'");
				while (resultSet.next()) {
					Statement statement2 = connection.createStatement();
					statement2.execute(
							"DELETE FROM Speicherstand WHERE speicherstand_ID=" + resultSet.getInt("speicherstand_ID"));
				}
			}
		} catch (Exception e) {
			e.getLocalizedMessage();
		}

	}

	public void setGewaehlterSpeicherstand(int gewaehlterSpeicherstand) {
		this.gewaehlterSpeicherstand = gewaehlterSpeicherstand;
	}
}
